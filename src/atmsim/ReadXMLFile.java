/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 package atmsim;
 
 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.net.URL;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;



/**
 *
 * @author n06rin
 */
public class ReadXMLFile {
    public static double currencyValue(String charCode) {
        double actualValue = 0;
 
    try {
 
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
	Document doc = dBuilder.parse(new URL("http://www.cbr.ru/scripts/XML_daily.asp").openStream());
	doc.getDocumentElement().normalize(); 
	NodeList nList = doc.getElementsByTagName("Valute");
        
        
 
	for (int temp = 0; temp < nList.getLength(); temp++) {
		Node nNode = nList.item(temp); 
		if (nNode.getNodeType() == Node.ELEMENT_NODE) { 
			Element eElement = (Element) nNode;                        
                        if ((eElement.getElementsByTagName("CharCode").item(0).getTextContent()).equals (charCode)) {
                            actualValue =  Double.parseDouble(eElement.getElementsByTagName("Value").item(0).getTextContent().replaceAll(",", ".")) / Double.parseDouble(eElement.getElementsByTagName("Nominal").item(0).getTextContent());
                        }   
		}
	}
    } catch (Exception e) {
	e.printStackTrace();
        return (-1);
    }
        
        return actualValue;
    
  }
}
