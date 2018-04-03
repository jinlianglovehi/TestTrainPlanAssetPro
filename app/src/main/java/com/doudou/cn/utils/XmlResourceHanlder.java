package com.doudou.cn.utils;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jinliang on 18-4-3.
 */

public class XmlResourceHanlder  extends DefaultHandler {


    private static final String TAG = XmlResourceHanlder.class.getSimpleName();
    private HashMap<String, String> map = new HashMap<>();
    private String tagName = null;//当前解析的元素标签

    private String NODE_STRING = "string";


    public HashMap<String,String> getMap(){
        return map;

    }

    private List<String> listData = new ArrayList<String>();


    public List<String> getListData (){
        return listData;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        listData.clear();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        this.tagName = localName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String data = new String(ch, start, length);
        if (tagName!=null && tagName.equals(NODE_STRING)) {
            Log.i(TAG,"value:"+ data );
            listData.add(data);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        this.tagName = null;

    }
}

