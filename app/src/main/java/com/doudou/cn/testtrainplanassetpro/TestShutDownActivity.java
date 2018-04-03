package com.doudou.cn.testtrainplanassetpro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Xml;

import com.doudou.cn.utils.DayTrainPlan;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jinliang on 18-4-2.
 */

public class TestShutDownActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shut_down);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.test_shut_down)
    public void clickShutDownBtn(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<DayTrainPlan> list = new ArrayList<>() ;
                for (int i = 0; i < 8 ; i++) {
                    DayTrainPlan dayTrainPlan = new DayTrainPlan() ;
                    dayTrainPlan.setDesc("desc");
                    dayTrainPlan.setDistance(1.0);
                    list.add(dayTrainPlan);
                }
                productModelToXmlFile(list);
            }
        }).start();


    }

    private void productModelToXmlFile(List<DayTrainPlan> list){

        if(list==null || list.size()==0){
            return ;
        }
//        XmlSerializer xmlSerializer = Xml.newSerializer();
//        try {
//            File file = new File(Environment.getExternalStorageDirectory(),"person.xml");
//            FileOutputStream os = new FileOutputStream(file);
//            //实例化,将数据导出为utf-8的person.xml文件
//            xmlSerializer.setOutput(os, "utf-8");
//            //设置xml表头，相当于<?xml version='1.0' encoding='utf-8' standalone='yes' ?>
//            xmlSerializer.startDocument("utf-8", true);
//            //persons根节点
//            xmlSerializer.startTag(null, "persons");
//            for (int i = 0; i < list.size(); i++) {
//                //一个person对象的根节点
//                xmlSerializer.startTag(null, "person");
//                //各个属性的开始节点
//                xmlSerializer.startTag(null, "sno");
//                xmlSerializer.text(list.get(i).getSno()+i);
//                xmlSerializer.endTag(null, "sno");
//
//                xmlSerializer.startTag(null, "sname");
//                xmlSerializer.text(list.get(i).getSname()+i);
//                xmlSerializer.endTag(null, "sname");
//
//                xmlSerializer.startTag(null, "sage");
//                xmlSerializer.text(list.get(i).getSage()+1+"");
//                xmlSerializer.endTag(null, "sage");
//
//                xmlSerializer.endTag(null, "person");
//            }
//            xmlSerializer.endTag(null, "persons");
//            xmlSerializer.endDocument();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            return false;
//        }

    }


    private void replaceXmlFileData(){

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

    private static void addElement(Document doc) {
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


    private static void deleteElement(Document doc) {
        NodeList employees = doc.getElementsByTagName("Employee");
        Element emp = null;
        //loop for each employee
        for(int i=0; i<employees.getLength();i++){
            emp = (Element) employees.item(i);
            Node genderNode = emp.getElementsByTagName("gender").item(0);
            emp.removeChild(genderNode);
        }

    }

    private static void updateElementValue(Document doc) {
        NodeList employees = doc.getElementsByTagName("Employee");
        Element emp = null;
        //loop for each employee
        for(int i=0; i<employees.getLength();i++){
            emp = (Element) employees.item(i);
            Node name = emp.getElementsByTagName("name").item(0).getFirstChild();
            name.setNodeValue(name.getNodeValue().toUpperCase());
        }
    }

    private static void updateAttributeValue(Document doc) {
        NodeList employees = doc.getElementsByTagName("Employee");
        Element emp = null;
        //loop for each employee
        for(int i=0; i<employees.getLength();i++){
            emp = (Element) employees.item(i);
            String gender = emp.getElementsByTagName("gender").item(0).getFirstChild().getNodeValue();
            if(gender.equalsIgnoreCase("male")){
                //prefix id attribute with M
                emp.setAttribute("id", "M"+emp.getAttribute("id"));
            }else{
                //prefix id attribute with F
                emp.setAttribute("id", "F"+emp.getAttribute("id"));
            }
        }
    }
    @OnClick(R.id.test_shut_down_intent)
    public void testShutDownIntent(){


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
