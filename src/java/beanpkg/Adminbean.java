/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanpkg;

import Hibernatepkg.Admintable;
import Hibernatepkg.DateHelper;
import Hibernatepkg.DateHelper;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author NIIT
 */
public class Adminbean {

    /**
     * Creates a new instance of Adminbean
     */
    String adminName;
    String Password1;
    String Password2;
    DateHelper datehelper = null;
    String errMssg;
    
    public Adminbean(){
        errMssg = null;
        datehelper = new DateHelper();
    }
    

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String AdminName) {
        this.adminName = AdminName;
    }

    public String getPassword1() {
        return Password1;
    }

    public void setPassword1(String Password1) {
        this.Password1 = Password1;
    }

    public String getPassword2() {
        return Password2;
    }

    public void setPassword2(String Password2) {
        this.Password2 = Password2;
    }
    
    public String TestAdminDetail(){
        List<Admintable> admin = datehelper.getAdmData(adminName,Password1,Password2);
        
        if(admin.isEmpty()){
            return "error";
        }else{
            return "ok";
        }
    }
    
    
}
