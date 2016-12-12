/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_mysql_lab7;

/**
 *
 * @author root
 */
import java.util.*;

public class PremiumUser extends User {

    public PremiumUser(DBConnect connect) {
        super(connect);
    }
    
    
    public Boolean reserveClass(Reservation res){
       Boolean flg=connect.addreservation(res);
       return flg;
    }
    public ArrayList viewmyReservation(String startDate,String endDate){
        ArrayList myReservations;
        myReservations=connect.viewmyreservation(this.userID,startDate,endDate);
        return myReservations;
    } 
    public Boolean cancelReservation(Reservation res){//missing notify users
        int CurrentresID=res.getReservationID();
        Boolean flg=connect.DeleteReservation(CurrentresID);
        if(flg==false){
            return false;
        }
        else{
            Integer waiting_resID=connect.ChooseFromWaiting(res.getTimeslot(), res.getDate(), res.getClassroom());
            flg=connect.changeReservationStatus(waiting_resID,res.getClassroom().getClassroomID());
            return flg;
        }
    }
    public Boolean addEmaillist(Reservation res,ArrayList Emails){
        for (Object Email : Emails) {
            Boolean flg = connect.addemail(res.getReservationID(), Email.toString());
            if(flg==false) return flg;
        }
        return true;
    }
    public Boolean NotifyUsers(Reservation res,int Status){
        return null;
    }
    public EmergencyRequest requestEmergencyRequest(Reservation res){
        
        Integer victimresID=connect.chooseAttackedReservation(res);
        EmergencyRequest emgreq=new EmergencyRequest();
        emgreq.setMyReservation(res);
        emgreq.setReplacementReservation(connect.getreservation(victimresID));
        return emgreq;
    }
    public void RequestRecurring(){
        
    }
}
    