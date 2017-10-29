/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserBean;


import Hibernatepkg.DateHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import newreflect.UserInf;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


/**
 *
 * @author m1785
 */
public class UserRigisterBean {
    private String[] skills;
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
     private Part file;

     
     
     

    public String getPhotopath() {
        return photopath;
    }

    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }
    public Part getFile() {
      return file;
    }

    public void setFile(Part file) {
      this.file = file;
    }

    public String getPasscheck() {
        return passcheck;
    }

    public void setPasscheck(String passcheck) {
        this.passcheck = passcheck;
    }

   
    
    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    
     public List<UserInf> GetUserTable(){
        List<UserInf> Users = new DateHelper().getUserData();
        
        return Users;
    }

    public String UserRegisterSubmit(){        
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();        
        UserInf u;
        String NavigationMessage;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        if((session.get(UserInf.class,id))==null && this.ensurePass()==true){
            //数据库中没有，密码匹配可以注册
            this.upload();
            this.setValues();
            UserInf user = new UserInf( id,  name,  phone,  gender,  age,  password,  passcheck,  qualification,  java,  cplus,  javaweb,  photopath,  expirence,  address);
            session.save(user);
            NavigationMessage = "success";
        }else if( (session.get(UserInf.class,id))==null && this.ensurePass()==false){
            //数据库中没有，但是密码不匹配，不能注册
            NavigationMessage = "passmatch";            
        }  else if ((session.get(UserInf.class,id))!=null && this.ensurePass()==true){
            //数据库中已存在用户，密码匹配，不能注册
            NavigationMessage = "exist";
        } 
        else {
            //数据库中已存在用户，密码不匹配，不能注册
            NavigationMessage = "both";
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        session.flush();
        tr.commit();
        return NavigationMessage;
    }
    //ensure the two passswords are equal
    public boolean ensurePass(){
        if(this.password.equals(this.passcheck)){
            //密码相同，yes
            return true;
        }else{
//            密码不同，no
            return false;
        }
    }
    //set the values that will save to database
    public void setValues(){
        int i = 0;
        this.java = false;
        this.javaweb = false;
        this.cplus = false;
       while(i < skills.length){
           
           switch(skills[i]){
               case "java":
                   this.java = true;
                   break;
               case "cplus":
                   this.cplus = true;
                   break;
               case "javaweb":
                   this.javaweb = true;
                   break;
           }

           i++;
       }
}
    public String Test(){
        System.out.println("wdsklahdkhj");
return "";
}

    public void upload() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        String filename;
      try {
//          if(file!=null){
          
          
          //get the path of current project to store the photo that the user upload.
          HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
          String uri = request.getRequestURI();
          
          String dir = request.getSession(true).getServletContext().getRealPath("/resources/");   
   
//          filename = dir+"\\photo\\"+String.valueOf(this.id)+".jpg";
          File files = new File("D:\\photos");
          if(files.isDirectory()){
              
          }else{
          files.mkdir();
          }
          filename = "D:\\photos\\"+String.valueOf(this.id)+".png";
          this.setPhotopath(filename);
          fos = new FileOutputStream(filename);
          byte bytes[] = new byte[512];
          int n = 0;
          fis = (FileInputStream) file.getInputStream();
          while((n = fis.read(bytes)) != -1){
              fos.write(bytes);
          }
//          }
        
      } catch (IOException e) {
        // Error handling
      }finally{
          try{
              fis.close();
              fos.close();
              
          }catch(Exception e){
              
          }
      }
    }
}



        
//public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  
//    {  
//        String uri = request.getRequestURI();  
//        String fileName = uri.substring(1).replaceAll("/", "_")+".json";  
//        //web下的目录  
//        String dir = request.getSession(true).getServletContext().getRealPath("/WEB-INF/pcie_json_data/");  
//        //web目录下的文件   
//        String filePath = dir+"\\"+fileName;  
//          
//        response.setContentType("text/html; charset=UTF-8");  
//        InputStream inputStream = new FileInputStream(filePath);  
//        //利用第三方工具，直接读取文件所有内容   
//         String text = IOUtils.toString(inputStream,"UTF-8");  
//        //通过PrintWriter对象，直接将文件内容输出到页面  
//         PrintWriter out =response.getWriter();  
//        out.println(text);  
//         out.close();  
//    }  

