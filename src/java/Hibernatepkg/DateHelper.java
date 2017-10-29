/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hibernatepkg;

//import UserBean.HibernateUtil;
//import linshi.HibernateUtil;
import Hibernatepkg.Admintable;
import Hibernatepkg.Jobtable;
import UserBean.HibernateUtil;
//import UserBean.HibernateUtil;
import java.util.List;
import newreflect.UserInf;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



/**
 *
 * @author NIIT
 */
public class DateHelper {
    Session session = null;
    
    public DateHelper(){
        SessionFactory sf  = HibernateUtil.getSessionFactory();
        session = sf.openSession();
    }
    
    //Method to get Employee Record
    public List<Admintable> getAdmData(String name,String Password1,String Password2){
        //Initialize Transaction
        if(!session.isOpen()){
            session = HibernateUtil.getSessionFactory().getCurrentSession();
        }
        Transaction tx = session.beginTransaction();
        //Initialize the Query
        Query q = session.createSQLQuery("select * from Admintable where name ='"+name+"' and password1 = '" + Password1 + "' and password2 = '" + Password2 + "'" );
        List<Admintable> admList = (List<Admintable>)q.list();
        tx.commit();
        return admList;
    }
    public String addJobData(String job_ID,String N_Vacancies,String Location,String Skill,String D_interview,String W_contact,String Email){
        //Initialize Transaction
        
        String Messg=null;
        
        if(!session.isOpen()){
            session = HibernateUtil.getSessionFactory().getCurrentSession();
        }
        Transaction tx = session.beginTransaction();
        //Initialize the Query
        //Query q = session.createSQLQuery("insert into Jobtable values('"+job_ID+"'"+",'"+N_Vacancies+"'"+",'"+Location+"'"+",'"+Skill+"'"+",'"+D_interview+"'"+",'"+W_contact+"'"+",'"+Email+"')");
        //List<Jobtable> jobList = (List<Jobtable>)q.list();
        Query q = session.createSQLQuery("select * from Jobtable where job_ID = '" + job_ID+"'" );
        List<Jobtable> jobList = (List<Jobtable>)q.list();
        if(jobList.isEmpty()){
            Jobtable job = new Jobtable(job_ID,N_Vacancies,Location,Skill,D_interview,W_contact,Email);
            session.save(job);
            session.flush();
            Messg = "It is add into datebase.";
         }
        else{
            
            Messg = "It is not add into datebase";
            
        }
        
        tx.commit();
        
        return Messg; 
    }
    public List<Jobtable> getJobData(){
        //Initialize Transaction
        if(!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        Transaction tx = session.beginTransaction();
        //Initialize the Query
        Query q = session.createQuery("from Jobtable");
        List<Jobtable> jobList = (List<Jobtable>)q.list();
        tx.commit();
        //session.flush();
        
        return jobList;
    }
    
    public List<UserInf> getUserData(){
        //Initialize Transaction
        if(!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        Transaction tx = session.beginTransaction();
        //Initialize the Query
        Query q = session.createQuery("from UserInf");
        List<UserInf> UserList = (List<UserInf>)q.list();
        tx.commit();
        //session.flush();
        
        return UserList;
    }
    
}
