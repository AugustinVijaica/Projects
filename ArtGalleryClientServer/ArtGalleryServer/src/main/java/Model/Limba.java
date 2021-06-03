package Model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Limba implements Serializable {

    private String limba;

    public Limba(){
        this.limba = "English";
    }

    public Limba(String limba){
        this.limba=limba;
    }

    public String getLimba() {
        return limba;
    }

    public void setLimba(String limba) {
        this.limba = limba;
    }

    public HashMap<String,Object> getInfo(){

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(new File("Languages.xml"));

            document.getDocumentElement().normalize();


            Element root = document.getDocumentElement();


            return (HashMap<String, Object>) ((HashMap<String, Object>)getMap(root)).get(this.limba);


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;

    }

    public Object getMap(Element root){


        NodeList nodes = root.getChildNodes();
        if(nodes.getLength()==1){
            return root.getTextContent();
        }
        HashMap<String,Object> map = new HashMap<>();

        for(int i=0;i<nodes.getLength();i++){
            Node n = nodes.item(i);
            if(n.getNodeType()==Node.ELEMENT_NODE) {
                map.put(n.getNodeName(), getMap((Element) n));
            }
        }

        return map;
    }


}
