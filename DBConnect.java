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
public class DBConnect {
    
 private Connection con;
    private Statement st;
    private ResultSet rs;
    
    public DBConnect() {
 try {
     Class.forName("com.mysql.jdbc.Driver");
     con = DriverManager.getConnection("jdbc:mysql://localhost:13306/BookITBranch", "root", "921949");
     st = con.createStatement();
 } catch (Exception ex) {
     System.out.println("Error: " + ex);
     
 }
    }    
    
    public void Register() {
 try {
     ///check that user is new is missing
     int UniversityID=20134567;
     String UniversityPosition="Student";
     String Password="921123";
     String Email="s-dfd@gmail.com";
     String Phone="010101010";
     String query = "Insert into Users(UniversityID,UniversityPosition,Password,Email,PhoneNumber)\n" 
          + "VALUES ("+UniversityID+",'"+UniversityPosition+"','"+Password+"','"+Email+"','"+Phone+"')";
     System.out.printf(query);
     st.executeUpdate(query);
     System.out.println("Insertion Done");
    // while (rs.next()) {
  //String name = rs.getInt("UserID");
  //String price = rs.getString("UnitPrice");
  //System.out.println("Name: " + name + " Price: " + price );
    // }
 } catch(Exception ex) {
     System.out.println(ex);
 }
    }
        public void resetpassword() {
 try {
     //check email exists
     String email="s-dfdd@gmail.com";
     String query="Select * FROM Users WHERE Email="+"'"+email+"'";
     rs=st.executeQuery(query);
     if(rs.next()==false){
      System.out.println("no such email");    
     }
     else{
         String newpassword="default";  // make it random missing
         String query2="UPDATE Users SET Password='"+newpassword+"'WHERE Email='"+email+"'";
         st.executeUpdate(query2);
     }
 

 } catch(Exception ex) {
     System.out.println(ex);
 }
    }
        
    public void changepassword(){
        try{
            String oldpassword="321243";
            String newpassword="56789";
            String email="s-alaa.abdelhameed@zewailcity.edu.eg";
            //check old password nad update
            String query="UPDATE Users SET Password='" +newpassword+"'WHERE Password='" + oldpassword+"'And Email='"+email+"'";
           int row_affected=st.executeUpdate(query);
           if(row_affected==0) System.out.println("Check your Email and Old password");
        }   
        catch(Exception ex){
                System.out.println(ex);
                }
    }
    public void login(){
        
        try{
            String UniversityID="201304766";
            String Password="921949";
            String query="Select * FROM Users Where UniversityID='"+UniversityID+"'"+"AND Password='"+Password+"'"; 
            String UniversityPosition; ///note add any other attribute that need to be filled because they will be used later
            rs=st.executeQuery(query);
          int flg=0; // to indicate if it is empty or not
          while(rs.next()){
              if(flg==0) flg=1;
              UniversityPosition=rs.getString("UniversityPosition");
              System.out.println(UniversityPosition);
          }
          if(flg==0) System.out.println("Check ID and Password");
          
        }
        catch(Exception ex){
                   System.out.println(ex);
        }
    }
    
    public void SearchAvaliableClasses(){ //note this can be used also for viewrooms() by creating an open req object 
        
           // missing this should take apointer to an array and fill the array inside the while loop     
        try{
           String Date="2016-5-24";
           String TimeSlot="08:20:00";
           int numberofStudents=5;
           int mic=0;
           int comp=1;
           int proj=0;
           int smart=0;
           String query= "Select Classrooms.ClassroomID FROM Classrooms WHERE Classrooms.ClassroomID not IN (SELECT Classrooms.ClassroomID FROM Classrooms,Reservations WHERE Classrooms.ClassroomID=Reservations.ClassRoomID AND ResDate='"+Date+"'AND TimeSlot='"+TimeSlot+"') And NumberofChairs>= "+numberofStudents+ " And Microphone>="+mic+ " And Projector>="+proj+ " And SmartBoard>="+smart+ " And Computer>="+comp;
           
           rs=st.executeQuery(query);
           int flg=0;
              while(rs.next()){
              if(flg==0) flg=1;
              // here we should fill the array
              int classId=rs.getInt("ClassroomID");
              System.out.println(classId);
          }
          if(flg==0) System.out.println("no available classes");
          
        
            
        }
        catch(Exception ex){
                  System.out.println(ex);
        }
        
        
    }
    public void addreservation(){ //this should take a full reservation object and add it -->it will be used also in upload sechedule and recurring booking
       try{
       int  UserID=5;
       Integer ClassRoomID=205;
       int comp=0;
       int mic=0;
       int proj=0;
       int smart=0;
       int numofStudents=5;
       int priority=1000;
       String Status= "waiting";
       String Date="2016-6-24";
       String TimeSlot="11:50:00";
       String Purpose="Lecture";
       Integer recurringresID=2;
       
       String query="Insert INTO Reservations(ClassRoomID,UserID,ResDate,TimeSlot,Status,Purpose,RecurringResID,NumberofStudents,Priority,SmartBoard,Computer,Microphone,Projector)"+
"VALUES("+ClassRoomID+","+UserID+",'"+Date+"','"+TimeSlot+"','"+Status+"','"+Purpose+"',"+recurringresID+","+numofStudents+","+priority+","+smart+","+comp+","+mic+","+proj+")";
       st.executeUpdate(query);
       }
       catch(Exception ex){
           System.out.println(ex);
       }
    }
    public void getMaxrecurringID(){
        
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
    public void viewmyreservation(){ // will take a userID,interval of DAtes and can be used also to view schedule by controlling the st-end date and put priority=1000
        try{
            int UserID=5;
            String startDate="2016-05-25";
                    
            String endDate="2016-06-24";        
            String query="Select * from Reservations Where ResDate<="+"'"+endDate+"'"+"AND ResDate>='"+startDate+"'"+"AND UserID="+UserID;       
           rs=st.executeQuery(query);
            while (rs.next()){ // this should return the values of resID,classroomID,Date,time because they will be used later to handle the waiting list
                int resID=rs.getInt("ReservationID");
                System.out.println(resID);
            }
            
        
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public void cancelReservation(){ //this cancel a reservation by the reservation ID used for the normal cancel reservation function 
        try{
            int resID=3;
            //Integer RecurringResID=null; //we will remove this column but discuss first
            String query="Delete from Reservations Where ReservationID="+resID;
            st.executeUpdate(query);
        }
        catch(Exception ex){
            System.out.println(ex);
            
        }
    }
    public void getreservation(){ //this gets classroom ,data,time of areservation using its ID because it will be needed to handle cancel recurring 
             try{
            int resID=1;
            //Integer RecurringResID=null; //we will remove this column but discuss first
            String query="Select * fROM Reservations Where ReservationID="+resID;
            rs=st.executeQuery(query);
            int flg=1;
            
            if(rs.next()==false){
             flg=0;
             
             
             }
            else{
                int ClassroomID=rs.getInt("ClassRoomID");
                String Date=rs.getString("ResDate");
                String TimeSlot=rs.getString("TimeSlot");
                System.out.println(ClassroomID);
                System.out.println(Date);
                System.out.println(TimeSlot);
                
            }
             }
        catch(Exception ex){
            System.out.println(ex);
            
        }
    }
    public void getClassProperties(){ //get the properties of a class by id and create a full class object
        try{
        int ClassID=135;
        String query="Select * from Classrooms Where ClassroomID="+ClassID;
        rs=st.executeQuery(query);
        rs.next();
        int numofchairs=rs.getInt("NumberofChairs");
        int mic=rs.getInt("Microphone");
        int proj=rs.getInt("Projector");
        int smart=rs.getInt("SmartBoard");
        int comp=rs.getInt("Computer");
        System.out.printf("chairs=%d\nmic=%d\ncomp=%d\nsmart=%d\nproj=%d\n",numofchairs,mic,comp,smart,proj);
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }         
    public void ChooseFromWaiting(){ //will return the reservation ID whose status will be changed from waiting to reserved
            try{
                // these parameters come from the calling get class properties
                String Date="2016-06-24";
                String TimeSlot="11:50:00";
                 int comp=1;
                 int mic=1;
                 int proj=1;
                 int smart=1;
                 int numofStudents=15;
                 String query="Select ReservationID From Reservations where ResDate='"+Date+"' And TimeSlot='"+TimeSlot+"' And Status='waiting' And NumberofStudents<="+numofStudents+" And Computer<="+comp+" And Microphone<="+mic+" And SmartBoard<="+smart+" And Projector<="+proj+" Order by Priority DESC,ReservationID ASC";
              rs=st.executeQuery(query);
              if(rs.next()==false){
                  System.out.println("no waiting reservation can be added");
              }
              else
              {
              int ResIDChosen=rs.getInt("ReservationID");
              System.out.println(ResIDChosen);
            }}
            catch(Exception ex){
                System.out.println(ex);
            }
        }
    public void chooseAttackedReservation(){ //return the attacked reservation ID for Emerency request
        try{    //we will already have attacking reservation object and its requirment
                //so this function search reservations by requirments,date,time,priority
                String Date="2016-05-24";
                String TimeSlot="08:20:00";
                 int comp=1;
                 int mic=1;
                 int proj=1;
                 int smart=1;
                 int numofStudents=5;
                 int priority=10;
                 String query="Select ReservationID From Reservations where ResDate='"+Date+"' And TimeSlot='"+TimeSlot+"' And Status='reserved' And ClassroomID in (Select ClassRoomID from Classrooms where NumberofChairs>= "+numofStudents+ " And Microphone>="+mic+ " And Projector>="+proj+ " And SmartBoard>="+smart+ " And Computer>="+comp +")"  + "And priority <="+priority + " Order by Priority ASC,ReservationID DESC";
                 rs=st.executeQuery(query);
                 if(rs.next()==false){
                     System.out.println("No available reservation to attack");
                 }
                 else{
                     int ResID=rs.getInt("ReservationID");
                     System.out.println(ResID);
                     
                 }
                 
                 
            
        }
        catch(Exception ex){
            System.out.println(ex);
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
    
    public void changeReservationStatus(){ //used to reply to emergency
            try{
                int ResID=11;
                int ClassroomID=145;
                String query="UPDATE Reservations SET ClassRoomID=" +ClassroomID+" ,Status='reserved' WHERE ReservationID=" + ResID;
                st.executeUpdate(query);
                
            }
            catch(Exception ex){
                System.out.println(ex);
            }
        }
    
    public void addemaillist(){
            try{
                
                
            }
            catch(Exception ex){
                System.out.println(ex);
            }
        }
  
}

