package com.doudou.cn.xml;

import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by jinliang on 18-3-12.
 */

public class XmlTools {



    public static void productXmlFile(final Context mContext , final  String settings){

        new Thread(new Runnable() {

            @Override
            public void run() {
                FileOutputStream fos = null;
                try {

                    String filePath = "/data/data/" + mContext.getPackageName()
                            + "/databases/"  + settings + ".xml";
                    File file  = new File(filePath);
                    if(!file.exists()){
                        file.createNewFile();
                    }
                    fos = new FileOutputStream(settings);
                    XmlSerializer serializer = Xml.newSerializer();
                    serializer.setOutput(fos, "UTF-8");
                    serializer.startDocument("UTF-8", true);

                    serializer.startTag(null, "config");
                    serializer.startTag(null, "category");
                    serializer.attribute(null, "name", "hot");
                    // server
                    serializer.startTag(null, "item");
                    serializer.attribute(null, "id", "server");
                    serializer.attribute(null, "value", "");
                    serializer.endTag(null, "item");
                    // hid
                    serializer.startTag(null, "item");
                    serializer.attribute(null, "id", "hotel");
                    serializer.attribute(null, "value", "");
                    serializer.endTag(null, "item");
                    // room
                    serializer.startTag(null, "item");
                    serializer.attribute(null, "id", "room");
                    serializer.attribute(null, "value", "");
                    serializer.endTag(null, "item");

                    serializer.endTag(null, "category");
                    serializer.endTag(null, "config");
                    serializer.endDocument();
                    serializer.flush();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
