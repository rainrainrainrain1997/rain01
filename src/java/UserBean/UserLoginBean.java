/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserBean;

import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Section;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileOutputStream;
import newreflect.UserInf;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author m1785
 */
public class UserLoginBean {

    /**
     * Creates a new instance of UserLoginBean
     */
        private String uid;
        private String pass;
        private String id;
        private String name;
        private String phone;
        private Character gender;
        private Integer age;
        private String password;
        private String passcheck;
        private String qualification;
        private Boolean java;
        private Boolean cplus;
        private Boolean javaweb;
        private String photopath;
        private String expirence;
        private String address;
        private String pdf;

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasscheck() {
        return passcheck;
    }

    public void setPasscheck(String passcheck) {
        this.passcheck = passcheck;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Boolean getJava() {
        return java;
    }

    public void setJava(Boolean java) {
        this.java = java;
    }

    public Boolean getCplus() {
        return cplus;
    }

    public void setCplus(Boolean cplus) {
        this.cplus = cplus;
    }

    public Boolean getJavaweb() {
        return javaweb;
    }

    public void setJavaweb(Boolean javaweb) {
        this.javaweb = javaweb;
    }

    public String getPhotopath() {
        return photopath;
    }

    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }

    public String getExpirence() {
        return expirence;
    }

    public void setExpirence(String expirence) {
        this.expirence = expirence;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
        

        public UserLoginBean() {
        }

        public String getId() {
            return uid;
        }

        public void setId(String uid) {
            this.uid = uid;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }
        
        public String UserLoginSubmit(){        
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();       

        String NavigationMessage;
        if((session.get(UserInf.class,uid))!=null){      
        UserInf user = (UserInf) session.get(UserInf.class, uid);        
        String truepass = user.getPassword();       
            if(this.validatePass(truepass, this.pass)==true){
                //数据库中有，密码正确，可以登录
                this.Setvalues(user);
                System.out.println(this.photopath+"90909");
                NavigationMessage = "succeedlogin";
                
                        
            }else{
                NavigationMessage = "passerror";
            }           
        }
        else {
            NavigationMessage="dontexist";
        }
        session.flush();
        tr.commit();
        return NavigationMessage;
    }
        public boolean validatePass(String realpass,String currentpass){
            if(realpass.equals(currentpass)){
                return true;
            }else{
                return false;
            }
        }
        public void Setvalues(UserInf user){
            this.id = user.getId();
            this.age = user.getAge();
            this.address = user.getAddress();
            this.expirence = user.getExpirence();
            this.cplus = user.getCplus();
            this.gender = user.getGender();
            this.java = user.getJava();
            this.name = user.getName();
            this.qualification = user.getQualification();
            this.password = user.getPassword();
            this.phone = user.getPhone();
            this.photopath = user.getPhotopath();            
            this.javaweb = user.getJavaweb();
            System.out.println(this.photopath+"qweqw");
        }

        
    public void  test(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();     
        UserInf user = (UserInf) session.get(UserInf.class, uid);  
        user.setAge(age);
        user.setAddress(address);
        user.setName(name);
        user.setPhone(phone);
        user.setJava(java);
        user.setJavaweb(javaweb);
        user.setCplus(cplus);
        user.setPhotopath(photopath);
        user.setExpirence(expirence);
        user.setQualification(qualification);
        user.setGender(gender);
        session.saveOrUpdate(user);
        session.flush();
        tr.commit();


        
    }
    public String jinru(){
        return "yes";
    }
    public void CreatePDF(){



        //调用第二个方法，向C盘名字为ITextTest.pdf的文件，添加章节。
        try {
         writeCharpter();
        } 
        catch (Exception e) { e.printStackTrace(); }
    
        
    }


 public  void writeCharpter() throws Exception {
              SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();     
        UserInf user = (UserInf) session.get(UserInf.class, uid);  

  // 新建document对象 第一个参数是页面大小。接下来的参数分别是左、右、上和下页边距。
  Document document = new Document(PageSize.A4, 20, 20, 20, 20);

  // 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
  PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf));

  // 打开文件
  document.open();

  // document.newPage();
  // 向文档中添加内容
 
  document.add(new Paragraph("name:" + user.getName(), FontFactory.getFont(FontFactory.defaultEncoding, 20, Font.BOLD, new Color(0, 0, 0))));
document.add(new Paragraph("\n")); 
  document.add(new Paragraph("Id:" + user.getId(), FontFactory.getFont(FontFactory.defaultEncoding, 10, Font.BOLD, new Color(0, 0, 0))));
document.add(new Paragraph("\n"));
  document.add(new Paragraph("phone:" + user.getPhone(), FontFactory.getFont(FontFactory.defaultEncoding, 10, Font.BOLD, new Color(0, 0, 0))));
document.add(new Paragraph("\n"));
  document.add(new Paragraph("address:" + user.getAddress(), FontFactory.getFont(FontFactory.defaultEncoding, 10, Font.BOLD, new Color(0, 0, 0))));
document.add(new Paragraph("\n"));
  document.add(new Paragraph("expirence:" + user.getExpirence(), FontFactory.getFont(FontFactory.defaultEncoding, 10, Font.BOLD, new Color(0, 0, 0))));
document.add(new Paragraph("\n"));
  document.add(new Paragraph("qualification:" + user.getQualification(), FontFactory.getFont(FontFactory.defaultEncoding, 10, Font.BOLD, new Color(0, 0, 0))));
document.add(new Paragraph("\n"));
  document.add(new Paragraph("age:" + user.getAge(), FontFactory.getFont(FontFactory.defaultEncoding, 10, Font.BOLD, new Color(0, 0, 0))));
document.add(new Paragraph("\n"));
  document.add(new Paragraph("java:" + user.getJava(), FontFactory.getFont(FontFactory.defaultEncoding, 10, Font.BOLD, new Color(0, 0, 0))));
document.add(new Paragraph("\n"));
  document.add(new Paragraph("javaweb:" + user.getJavaweb(), FontFactory.getFont(FontFactory.defaultEncoding, 10, Font.BOLD, new Color(0, 0, 0))));
document.add(new Paragraph("\n"));
  document.add(new Paragraph("c++:" + user.getCplus(), FontFactory.getFont(FontFactory.defaultEncoding, 10, Font.BOLD, new Color(0, 0, 0))));
document.add(new Paragraph("\n"));
  document.add(new Paragraph("gender:" + user.getGender(), FontFactory.getFont(FontFactory.defaultEncoding, 10, Font.BOLD, new Color(0, 0, 0))));
document.add(new Paragraph("\n"));
  document.add(new Paragraph("password:" + user.getPassword(), FontFactory.getFont(FontFactory.defaultEncoding, 10, Font.BOLD, new Color(0, 0, 0))));
document.add(new Paragraph("\n"));
//  document.add(new Paragraph("Some more text on the first page with different color and font type.", FontFactory.getFont(FontFactory.defaultEncoding, 10, Font.BOLD, new Color(0, 0, 0))));
//  Paragraph title1 = new Paragraph(this.name, FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC, new Color(0, 0, 255)));

  // 新建章节
//  Chapter chapter1 = new Chapter(title1, 1);
//  chapter1.setNumberDepth(0);
//  Paragraph title11 = new Paragraph("This is Section 1 in Chapter 1", FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new Color(255, 0, 0)));
//  Section section1 = chapter1.addSection(title11);
//  Paragraph someSectionText = new Paragraph("This text comes as part of section 1 of chapter 1.");
//  section1.add(someSectionText);
//  someSectionText = new Paragraph("Following is a 3 X 2 table.");
//  section1.add(someSectionText);
//  document.add(chapter1);

  // 关闭文档
  document.close();
 }
 

}
