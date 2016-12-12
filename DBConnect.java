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
import java.sql.*;
import java.util.*;
public class DBConnect {
    
 private Connection con;
    private Statement st;
    private ResultSet rs;
    
    public DBConnect() {
 try {
     Class.forName("com.mysql.jdbc.Driver");
    // con = DriverManager.getConnection("jdbc:mysql://10.2.101.59:13306/BookIT", "root", "921949");
      con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7148349", "sql7148349", "CznnP1w1TK");
    
    st = con.createStatement();
 } catch (Exception ex) {
     System.out.println("Error: " + ex);
     
 }
    }    
    public Boolean checkuserwithIDorEmail(int UserID,String Email) //checks if userID or user email exists before
    {                       //used in register function
        try{
            //int UserID=1;
            //String Email="aaa@gmail.com";
            String query="Select UserID From Users Where Email='"+Email+"' or UserID="+UserID;
            rs=st.executeQuery(query);
            if(rs.next()==false) return false;//System.out.println("This ID or Email already registered");
            return true;
        }
        catch(Exception ex){
            //System.out.println(ex);
            return false;
        }
    }
    public Boolean checkuserwithIDandEmail(int UserID,String Email) //checks if userID or user email exists before
    {                       //used in register function
        try{
            //int UserID=1;
            //String Email="aaa@gmail.com";
            String query="Select UserID From Users Where Email='"+Email+"' and UserID="+UserID;
            rs=st.executeQuery(query);
            if(rs.next()==false) return false;//System.out.println("This ID or Email already registered");
            return true;
        }
        catch(Exception ex){
            //System.out.println(ex);
            return false;
        }
    }
        public Boolean checkuserwithIDandpassword(int UserID,String Password) //checks if userID or user email exists before
    {                       //used in register function
        try{
            //int UserID=1;
            //String Email="aaa@gmail.com";
            String query="Select UserID From Users Where Password='"+Password+"' and UserID="+UserID;
            rs=st.executeQuery(query);
            if(rs.next()!=false) return false;//System.out.println("This ID or Email already registered");
            return true;
        }
        catch(Exception ex){
            //System.out.println(ex);
            return false;
        }
        }
    public Boolean insertuser(int UserID,String UniversityPosition,String Password,String Email,String Phone) { // this will take a user object and takes its variables which will come from gui
 try {
    
     String query = "Insert into Users(UserID,UniversityPosition,Password,Email,PhoneNumber)\n" 
          + "VALUES ("+UserID+",'"+UniversityPosition+"','"+Password+"','"+Email+"','"+Phone+"')";
    // System.out.printf(query);
     st.executeUpdate(query);
    // System.out.println("Insertion Done");
     return true;
 } catch(Exception ex) {
     System.out.println(ex);
     return false;
 }
    }

    
    public Boolean updatepassword(int UserID,String newpassword){  //takes old password,email and new password //note he user would be logged in already so we have his UserID  or email from the user object
        try{
            //String oldpassword="321243";
            //String newpassword="56789";
            
            //String email="s-alaa.abdelhameed@zewailcity.edu.eg";
            //check old password nad update
            String query="UPDATE Users SET Password='" +newpassword+"'where UserID="+UserID;
            st.executeUpdate(query);
            return true;
        }   
        catch(Exception ex){
                System.out.println(ex);
                return false;        
        }
    }
    
    public Boolean resetPassword(int UserID,String Email){ //will be called in main
        Boolean flg=this.checkuserwithIDandEmail(UserID,Email);
        if(flg==false) {
            return false;
        }
        else{
            String password="default";
            flg=this.updatepassword(UserID,password);
            return flg;   
        }
        
    }
    

    
    public Boolean getuser(User newuser,int UserID){ //this takes userID and password from GUI and should end by creating a user object
        
        try{
            
            //String UniversityID="201304766";
            //String Password="921949";
            String query="Select * FROM Users Where UserID='"+UserID+"'"; 
            rs=st.executeQuery(query);
          int flg=0; // to indicate if it is empty or not
          while(rs.next()){
              if(flg==0) flg=1;
              newuser.setUniversityPosition(rs.getString("UniversityPosition"));   
              newuser.setUserID(UserID);   
              newuser.setEmail(rs.getString("Email"));
              newuser.setPassword("Password");
              newuser.setPhone_number("PhoneNumber");

              //System.out.println(UniversityPosition);
          }
          if(flg==0) return false;
          return true;
        }
        catch(Exception ex){
                   System.out.println(ex);
                   return false;
        }
    }
     public Boolean Login(int UserID,String Password,User newuser){
        Boolean flg=this.checkuserwithIDandpassword(UserID, Password);
        if(flg==false){
            return false;
        }
        else{
            flg=this.getuser(newuser, UserID);
            return flg;
        }
        
        
    }
        public Boolean Register(int UserID,String UniversityPosition,String Password,String Email,String Phone){
               Boolean flg=this.checkuserwithIDorEmail(UserID,Email);
               if(flg==false) return false;
               flg=this.insertuser(UserID,UniversityPosition,Password,Email,Phone); //flg is true when registeration is done
                   
               
               return flg;

    }
    public ArrayList SearchAvaliableClasses(String Date,String TimeSlot,int numberofStudents,int mic,int comp,int proj,int smart){ //note this can be used also for viewrooms() by creating an open req object 
        
           // this should take apointer to an array and fill the array(array of classes of only class IDs) inside the while loop     
        try{
           String query= "Select Classrooms.ClassroomID FROM Classrooms WHERE Classrooms.ClassroomID not IN (SELECT Classrooms.ClassroomID FROM Classrooms,Reservations WHERE Classrooms.ClassroomID=Reservations.ClassRoomID AND ResDate='"+Date+"'AND TimeSlot='"+TimeSlot+"') And NumberofChairs>= "+numberofStudents+ " And Microphone>="+mic+ " And Projector>="+proj+ " And SmartBoard>="+smart+ " And Computer>="+comp;
           
           rs=st.executeQuery(query);
           
           ArrayList classIDs=new ArrayList();
              while(rs.next()){
             
              Integer classID=rs.getInt("ClassroomID");
              classIDs.add(classID);
              
          }
          
                  return classIDs;

            
        }
        catch(Exception ex){
                  System.out.println(ex);
                  return null;
        }
        
        
    }
    public Boolean addreservation(Reservation res){ 
//this should take a full reservation object and add it -->it will be used also in upload sechedule and recurring booking
  //in updateschedule ->priority=1000 , requirments =zeros , we may let user enter the purpose also
//in recurring booking ->algorithm
        try{
       //int  UserID=5;
       Integer ClassRoomID=res.getClassroom().getClassroomID();
       int comp=res.getComp();
       int mic=res.getMic();
       int proj=res.getProj();
       int smart=res.getSmart();
       int numofStudents=res.getNumberofStudents();
       int priority=res.getPriority();
       String Status= res.getStatus();
       String Date=res.getDate();
       String TimeSlot=res.getTimeslot();
       String Purpose=res.getPurpose();
       int UserID=res.getUserID();
       
       String query="Insert INTO Reservations(ClassRoomID,UserID,ResDate,TimeSlot,Status,Purpose,NumberofStudents,Priority,SmartBoard,Computer,Microphone,Projector)"+
"VALUES("+ClassRoomID+","+UserID+",'"+Date+"','"+TimeSlot+"','"+Status+"','"+Purpose+"',"+","+numofStudents+","+priority+","+smart+","+comp+","+mic+","+proj+")";
       st.executeUpdate(query);
       return true;
        }
       catch(Exception ex){
           System.out.println(ex);
           return false;
       }
       
    }
    public void getMaxrecurringID(){ //useg to get the next recurring ID
        
        try{
            String query="Select Max(RecurringResID) AS 'ResID' FROM Reservations";
            rs=st.executeQuery(query);
            rs.next();
            int resID=rs.getInt("ResID");
            System.out.println(resID);
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        
    }
    public ArrayList viewmyreservation(int UserID,String startDate,String endDate){ // will take a userID,interval of DAtes and can be used also to view schedule by controlling the st-end date and put priority=1000
           //this will return an array of reservations or at least reservation IDs to be used for cancelation
        try{
            
            //String startDate="2016-05-25";
                    
            //String endDate="2016-06-24";        
            String query="Select * from Reservations Where ResDate<="+"'"+endDate+"'"+"AND ResDate>='"+startDate+"'"+"AND UserID="+UserID;       
           rs=st.executeQuery(query);
           int flg=0;
           ArrayList Reservations=new ArrayList();
            while (rs.next()){ // this should return the values of resID,classroomID,Date,time because they will be used later to handle the waiting list
                if(flg==0) flg=1;
                Reservation res=new Reservation();
                res.setReservationID(rs.getInt("ReservationID"));
                res.setClassroom(getClassRoom(rs.getInt("ClassRoomID")));
                res.setUserID(rs.getInt("UserID"));
                res.setTimeslot(rs.getString("TimeSlot"));
                res.setDate(rs.getString("ResDate"));
                res.setPriority(rs.getInt("Priority"));
                res.setStatus(rs.getString("Status"));
                res.setNumberofStudents(rs.getInt("NumberofStudents"));
                res.setPurpose(rs.getString("Purpose"));
                res.setMic(rs.getInt("Microphone"));
                res.setComp(rs.getInt("Computer"));
                res.setProj(rs.getInt("Projector"));
                res.setSmart(rs.getInt("SmartBoard"));
                Reservations.add(res);
                
            }
            
            if(flg==0) return null;
            return Reservations;
        }
        catch(Exception ex){
            System.out.println(ex);
            return null;
        }
    }
    public Boolean DeleteReservation(int resID){ //this cancel a reservation by the reservation ID used for the normal cancel reservation function 
        try{
            //int resID=3;
            //Integer RecurringResID=null; //we will remove this column but discuss first
            String query="Delete from Reservations Where ReservationID="+resID;
            st.executeUpdate(query);
            return true;
        }
        catch(Exception ex){
            System.out.println(ex);
            return false;
        }
    }
    public Reservation getreservation(int resID){ //this gets classroom ,data,time of areservation using its ID because it will be needed to handle cancel recurring 
             try{
            //int resID=1;
            //Integer RecurringResID=null; //we will remove this column but discuss first
            String query="Select * fROM Reservations Where ReservationID="+resID;
            rs=st.executeQuery(query);
            int flg=1;
            
            if(rs.next()==false){
             flg=0;
             return null;
             
             }
            else{
                Reservation res=new Reservation();
                res.setReservationID(resID);
                res.setClassroom(getClassRoom(rs.getInt("ClassRoomID")));
                res.setUserID(resID);
                res.setTimeslot(rs.getString("TimeSlot"));
                res.setDate(rs.getString("ResDate"));
                res.setPriority(rs.getInt("Priority"));
                res.setStatus(rs.getString("Status"));
                res.setNumberofStudents(rs.getInt("NumberofStudents"));
                res.setPurpose(rs.getString("Purpose"));
                res.setMic(rs.getInt("Microphone"));
                res.setComp(rs.getInt("Computer"));
                res.setProj(rs.getInt("Projector"));
                res.setSmart(rs.getInt("SmartBoard"));
                return res;
 //System.out.println(ClassroomID);
                //System.out.println(Date);
                //System.out.println(TimeSlot);
                
            }
             }
        catch(Exception ex){
            System.out.println(ex);
            return null;
        }
    }
    public Classroom getClassRoom(int ClassID){ //get the properties of a class by id and create a full class object
        try{
        
        String query="Select * from Classrooms Where ClassroomID="+ClassID;
        rs=st.executeQuery(query);
        rs.next();
        Classroom room=new Classroom();
        room.setClassroomID(ClassID);
        room.setNumberofChairs(rs.getInt("NumberofChairs"));
        room.setMic(rs.getInt("Microphone"));
        room.setProj(rs.getInt("Projector"));
        room.setSmart(rs.getInt("SmartBoard"));
        room.setComp(rs.getInt("Computer"));
        return room;
        //System.out.printf("chairs=%d\nmic=%d\ncomp=%d\nsmart=%d\nproj=%d\n",numofchairs,mic,comp,smart,proj);
        }
        catch(Exception ex){
            System.out.println(ex);
            return null;
        }
    }         
    public Integer ChooseFromWaiting(String TimeSlot,String Date,Classroom freeclass){ //will return the reservation ID whose status will be changed from waiting to reserved
            try{
                // these parameters come from the calling get class properties
                //String Date="2016-06-24";
                //String TimeSlot="11:50:00";
                 int comp=freeclass.getComp();
                 int mic=freeclass.getMic();
                 int proj=freeclass.getProj();
                 int smart=freeclass.getSmart();
                 int numofStudents=freeclass.getNumberofChairs();
                 String query="Select ReservationID From Reservations where ResDate='"+Date+"' And TimeSlot='"+TimeSlot+"' And Status='waiting' And NumberofStudents<="+numofStudents+" And Computer<="+comp+" And Microphone<="+mic+" And SmartBoard<="+smart+" And Projector<="+proj+" Order by Priority DESC,ReservationID ASC";
              rs=st.executeQuery(query);
              if(rs.next()==false){
                 return null;
              }
              else
              {
              int ResIDChosen=rs.getInt("ReservationID");
              return ResIDChosen;
            }
            }
            catch(Exception ex){
                System.out.println(ex);
                return null;
            }
        }
    public Integer chooseAttackedReservation(Reservation res){ //return the attacked reservation ID for Emerency request
        try{    //we will already have attacking reservation object and its requirment
                //so this function search reservations by requirments,date,time,priority
                String Date=res.getDate();
                String TimeSlot=res.getTimeslot();
                 int comp=res.getComp();
                 int mic=res.getMic();
                 int proj=res.getProj();
                 int smart=res.getSmart();
                 int numofStudents=res.getNumberofStudents();
                 int priority=res.getPriority();
                 String query="Select ReservationID From Reservations where ResDate='"+Date+"' And TimeSlot='"+TimeSlot+"' And Status='reserved' And ClassroomID in (Select ClassRoomID from Classrooms where NumberofChairs>= "+numofStudents+ " And Microphone>="+mic+ " And Projector>="+proj+ " And SmartBoard>="+smart+ " And Computer>="+comp +")"  + "And priority <="+priority + " Order by Priority ASC,ReservationID DESC";
                 rs=st.executeQuery(query);
                
                 if(rs.next()==false){
                    // System.out.println("No available reservation to attack");
                    return null;
                 }
                 else{
                     int ResID=rs.getInt("ReservationID");
                     //System.out.println(ResID);
                     return ResID;
                 }
                 
                 
            
        }
        catch(Exception ex){
            System.out.println(ex);
            return null;
        }
    }
    public void addEmergency(){
            try{
                int PerpetratorID=16;
                int VictimID=13;
                String query="Insert Into EmergencyRequests(PerpetratorID,VictimID) Values("+PerpetratorID+","+VictimID+")";
                st.executeUpdate(query);
            }
            catch(Exception ex){
                System.out.println(ex);
            }
        }
    
    public Boolean changeReservationStatus(int ResID,int ClassroomID){ //used to reply to emergency
            try{
                //int ResID=11;
                //int ClassroomID=145;
                String query="UPDATE Reservations SET ClassRoomID=" +ClassroomID+" ,Status='reserved' WHERE ReservationID=" + ResID;
                st.executeUpdate(query);
                return true;
            }
            catch(Exception ex){
                System.out.println(ex);
                return false;
            }
        }
    
    public Boolean addemail(int resID,String Email){
            try{
                //int resID= 1;
                //String Email="s-kfdfd@gmail.com";
                String query="Insert Into EmailLists(ReservationID,Email) Values("+resID+",'"+Email+"')";
                st.executeUpdate(query);
                return true;
            }   
            catch(Exception ex){
                System.out.println(ex);
                return false;
            }
        }
  // schedule shift not written
}

