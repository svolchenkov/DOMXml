/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
import javax.ejb.EJB;
import ejb.DOMEjb;

/**
 *
 * @author Sergey
 */
@ManagedBean
@SessionScoped
public class IndexBean {
    
    private Part fileXml;
    private String messageUploadFile = null;
    private String rootElement;
    private String studentElement;
    
    @EJB
    private DOMEjb domejb;
    
    public IndexBean() {
    }
    
    public Part getFileXml() {
        return fileXml;
    }

    public void setFileXml(Part fileXml) {
        this.fileXml = fileXml;
    }
    
    public void uploadFile () {
        if (fileXml == null) {
            setMessageUploadFile("Please select file for uploading");
        } else {
            setMessageUploadFile("");
            domejb.upload(fileXml);
        }
    }
    
    public void receiveRootElement () {
        setRootElement(domejb.receiveRootElement());
    }
    
    public void receiveElement () {
        setStudentElement(domejb.receiveNextElement());
    }
    
    public String getMessageUploadFile() {
        return messageUploadFile;
    }

    public void setMessageUploadFile(String messageUploadFile) {
        this.messageUploadFile = messageUploadFile;
    }

    public String getRootElement() {
        return rootElement;
    }

    public void setRootElement(String rootElement) {
        this.rootElement = rootElement;
    }

    public String getStudentElement() {
        return studentElement;
    }

    public void setStudentElement(String studentElement) {
        this.studentElement = studentElement;
    }
    
}
