/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.Part;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Sergey
 */
@Stateless
@LocalBean
public class DOMEjb {

    private Part fileXml;
    private String path = "/Users/Sergey/Desktop/temt.tmp";
    private File file;
    private Document doc;

    public DOMEjb() {
    }

    public void upload(Part fileXml) {
        this.fileXml = fileXml;
        try (InputStream is = fileXml.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                PrintWriter outputStream = new PrintWriter(path);) {
            String line;
            while ((line = br.readLine()) != null) {
                outputStream.write(line);
            }
            setFile(new File(path));
        } catch (IOException ex) {
            System.out.println("");
        }
    }

    private Document createDocument() {
        try {
            DocumentBuilderFactory factory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(file);
             doc.getDocumentElement().normalize();
        } catch (ParserConfigurationException ex) {
            System.out.println(ex.getMessage());
        } catch (SAXException | IOException ex) {
            Logger.getLogger(DOMEjb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doc;
    }

    public String receiveRootElement() {
        doc = createDocument();
        String rootElement = doc.getDocumentElement().getNodeName();
        return rootElement;
    }
    
    public String receiveNextElement () {
        doc = createDocument();
        NodeList nodeList = doc.getElementsByTagName("lastname");
        return nodeList.item(0).getNodeName() + ": " + nodeList.item(0).getTextContent();
    }

    public Part getFileXml() {
        return fileXml;
    }

    public void setFileXml(Part fileXml) {
        this.fileXml = fileXml;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
