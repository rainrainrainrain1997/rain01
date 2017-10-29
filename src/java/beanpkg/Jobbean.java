/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanpkg;

import Hibernatepkg.DateHelper;
import Hibernatepkg.Jobtable;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author NIIT
 */
@Named(value = "jobbean")
@RequestScoped
public class Jobbean {

    /**
     * Creates a new instance of Jobbean
     */
    String job_ID;
    String N_Vacancies,Location,Skill,D_interview,W_contact,Email;
    DateHelper datehelper = null;
    String Mssg;

    

    
    
    public Jobbean(){
        Mssg = null;
        datehelper = new DateHelper();
    }
    
    
    
    public String getMssg() {
        return Mssg;
    }

    public void setMssg(String Mssg) {
        this.Mssg = Mssg;
    }

    public String getJob_ID() {
        return job_ID;
    }

    public void setJob_ID(String job_ID) {
        this.job_ID = job_ID;
    }

    public String getN_Vacancies() {
        return N_Vacancies;
    }

    public void setN_Vacancies(String N_Vacancies) {
        this.N_Vacancies = N_Vacancies;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getSkill() {
        return Skill;
    }

    public void setSkill(String Skill) {
        this.Skill = Skill;
    }

    public String getD_interview() {
        return D_interview;
    }

    public void setD_interview(String D_interview) {
        this.D_interview = D_interview;
    }

    public String getW_contact() {
        return W_contact;
    }

    public void setW_contact(String W_contact) {
        this.W_contact = W_contact;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    public void addJob(){
       Mssg = datehelper.addJobData(job_ID, N_Vacancies, Location, Skill, D_interview, W_contact, Email);
    }
   
    public List<Jobtable> GetJobTable(){
        List<Jobtable> jobs = new DateHelper().getJobData();
        
        return jobs;
    }
}
