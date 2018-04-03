package com.doudou.cn.utils;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by jinliang on 18-4-2.
 */

public class ReplaceXmlDataTools {


    public static ReplaceXmlDataTools instance ;

    public static ReplaceXmlDataTools getInstance(){
        if(instance==null){
            synchronized (ReplaceXmlDataTools.class){
                if(instance== null){
                    instance = new ReplaceXmlDataTools();
                }
            }
        }
        return instance;
    }

    private static final String TAG = ReplaceXmlDataTools.class.getSimpleName();



//          <daytrainplan id="0">
//            <desc>休息日</desc>
//            <stageDesc>基础锻炼</stageDesc>
//            <distance>0.0</distance>
//            <offsetWeeks>0</offsetWeeks>
//            <offsetDays>0</offsetDays>
//            <offsetTotal>0</offsetTotal>
//            <runremindId>9</runremindId>
//            <rateStart>1</rateStart>
//            <rateEnd>0</rateEnd>
//        </daytrainplan>


    public class MODEL_DAY_TRAIN_PLAN {

        public static final String NAME = "daytrainplan" ;

        public static final String DESC = "desc" ;

        public static final String stageDesc = "stageDesc" ;

        public static final String distance = "distance" ;
        public static final String offsetWeeks = "offsetWeeks" ;
        public static final String offsetDays = "offsetDays" ;
        public static final String offsetTotal = "offsetTotal" ;


        public static final String runremindId = "runremindId" ;
        public static final String rateStart = "rateStart" ;
        public static final String rateEnd = "rateEnd" ;



    }






    public static void replaceXmlFileData(){

        String filePath = "employee.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            //update attribute value
            updateAttributeValue(doc);

            //update Element value
            updateElementValue(doc);

            //delete element
            deleteElement(doc);

            //add new element
            addElement(doc);

            //write the updated document to file or console
            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("employee_updated.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            System.out.println("XML file updated successfully");

        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
            e1.printStackTrace();
        }
    }

    public static void addElement(Document doc) {
        NodeList employees = doc.getElementsByTagName("Employee");
        Element emp = null;

        //loop for each employee
        for(int i=0; i<employees.getLength();i++){
            emp = (Element) employees.item(i);
            Element salaryElement = doc.createElement("salary");
            salaryElement.appendChild(doc.createTextNode("10000"));
            emp.appendChild(salaryElement);
        }
    }


    public static void deleteElement(Document doc) {
        NodeList employees = doc.getElementsByTagName("Employee");
        Element emp = null;
        //loop for each employee
        for(int i=0; i<employees.getLength();i++){
            emp = (Element) employees.item(i);
            Node genderNode = emp.getElementsByTagName("gender").item(0);
            emp.removeChild(genderNode);
        }

    }

    public static void updateElementValue(Document doc) {
        NodeList employees = doc.getElementsByTagName("Employee");
        Element emp = null;
        //loop for each employee
        for(int i=0; i<employees.getLength();i++){
            emp = (Element) employees.item(i);
            Node name = emp.getElementsByTagName("name").item(0).getFirstChild();
            name.setNodeValue(name.getNodeValue().toUpperCase());
        }
    }

    public static void updateAttributeValue(Document doc) {
        NodeList employees = doc.getElementsByTagName("Employee");
        Element emp = null;
        //loop for each employee
        for(int i=0; i<employees.getLength();i++){
            emp = (Element) employees.item(i);
            String attributeNodeValue = emp.getAttributeNode(MODEL_DAY_TRAIN_PLAN.DESC).getValue();
            String attributeNodeName = emp.getAttributeNode(MODEL_DAY_TRAIN_PLAN.DESC).getName();

            String nodeValue = emp.getElementsByTagName(MODEL_DAY_TRAIN_PLAN.DESC).item(0).getFirstChild().getNodeValue();

            Log.i(TAG," attributeNodeValue:"+ attributeNodeValue
                    +",attributeNodeName:"+attributeNodeName +",nodeValue:"+nodeValue);


//            String gender = emp.getElementsByTagName("gender").item(0).getFirstChild().getNodeValue();
//            if(gender.equalsIgnoreCase("male")){
//                //prefix id attribute with M
//                emp.setAttribute("id", "M"+emp.getAttribute("id"));
//            }else{
//                //prefix id attribute with F
//                emp.setAttribute("id", "F"+emp.getAttribute("id"));
//            }
        }
    }


//   replace xml data content



    public static void copyToSdCard(Context mContext ,String fileName){
        String dbPath = "/data/data/" + mContext.getPackageName()
                + "/cache/" + fileName;
        Log.i(TAG, " dbPath:"+ dbPath);
        if (!new File(dbPath).exists()) {
            try {
                FileOutputStream out = new FileOutputStream(dbPath);
                InputStream in = mContext.getAssets().open("db/"+fileName);
                byte[] buffer = new byte[1024];
                int readBytes = 0;
                while ((readBytes = in.read(buffer)) != -1){
                    Log.i(TAG," db  insert batch ");
                    out.write(buffer, 0, readBytes);
                }
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void replaceXmlDataTrainPlan(Context mContext, List<TrainPlan> list, String fileName){
        copyToSdCard(mContext,fileName);
        String dbPath = "/data/data/" + mContext.getPackageName()
                + "/cache/" + fileName;
        String dbCachePath = "/data/data/" + mContext.getPackageName()
                + "/cache/" + "huhu.xml";
        Log.i(TAG,"replaceXmlDataTrainPlan start:dbPath:"+dbPath
        ) ;
        File currnetXmlFile = new File(dbPath) ;


        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(currnetXmlFile);
            doc.getDocumentElement().normalize();

            // update mode 
            updateTrainPlanZhToCha(doc);
            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(dbCachePath));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            System.out.println("XML file updated successfully");

        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
            e1.printStackTrace();
        }
    }

    public static void updateTrainPlanZhToCha(Document doc){
        // tag replace
        NodeList employees = doc.getElementsByTagName(MODEL_DAY_TRAIN_PLAN.NAME);
        Element emp = null;
        for(int i=0; i<employees.getLength();i++){
            emp = (Element) employees.item(i);
            String nodeValue = emp.getElementsByTagName(MODEL_DAY_TRAIN_PLAN.DESC).item(0).getFirstChild().getNodeValue();
            Log.i(TAG," attributeNodeValue:"
                    +",attributeNodeName:" +",nodeValue:"+nodeValue);
            emp.getElementsByTagName(MODEL_DAY_TRAIN_PLAN.DESC).item(0).getFirstChild().setNodeValue("huhu");
        }


    }


}
