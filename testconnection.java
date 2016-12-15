/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_mysql_lab7;
import java.util.*;
import java.time.*;
//import java.time.te   mporal.ChronoUnit;
/**
 *
 * @author root
 */

public class testconnection {
    


     public static void main(String[] args) {
         
          //LocalDate date1=LocalDate.of(2016, Month.DECEMBER, 14);
          //System.out.println("date1: " + date1.toString());
          //LocalDate date2=date1.plus(3,ChronoUnit.WEEKS);
          //System.out.println("date2: " + date2);
          //int flg=date2.compareTo(date1);
          //System.out.println(flg);
       DBConnect connect = new DBConnect();
       //Reservation ress=connect.getreservation(4);
       //System.out.println(ress.getDate());
       //PremiumUser curuser=new PremiumUser(connect);
       /*
       Reservation res=new Reservation();
       //curuser.setUserID(20134567);
       Classroom room=new Classroom();
       room.setClassroomID(null);
       res.setClassroom(room);
       res.setTimeslot("09:30:00");
       res.setDate(LocalDate.of(2016, Month.DECEMBER,15));
       res.setUserID(20134567);
       res.setPriority(45);
       res.setStatus("recurringWaiting");
       res.setNumberofStudents(20);
       res.setPurpose("Lecture");
       res.setMic(0);
       res.setProj(0);
       res.setComp(0);
       res.setSmart(0);
      *///curuser.reserveClass(res);
       //EmergencyRequest req=curuser.requestEmergencyRequest(res);
       PremiumUser hh=new PremiumUser(connect);
       Admin pop=new Admin(connect);
       //pop.reserveClass(res);
       Reservation res=connect.getreservation(42);
       System.out.println(res.getUserID());
       //try{
       //ArrayList<Integer> xx=pop.GenerateRecurringReport(res);
       //System.out.print(xx);
       //pop.ApproveRecurringRequest(xx, res);

       
       //}
      // catch (Exception ex){};
      try{
       hh.CancelRecurring(res);
      }
      catch (Exception ex ){}
       // pop.ReplayEmergencyRequest(req);
      // curuser.cancelReservation(connect.getreservation(22));
       //ArrayList emails=new ArrayList();
       //emails.add("fsdfsdfdsfsdf");
       //emails.add("ewhjgjggjfgf");
       
       //curuser.addEmaillist(connect.getreservation(9), emails);
       
               
//ArrayList<Reservation> ress=curuser.viewmyReservation("2016-02-25", "2016-06-25");
     //   System.out.println(req.getMyReservation().getReservationID());
       // for(Reservation x:ress){
       // System.out.println(x.getReservationID());
      //  }
       /* 
       ArrayList<MyPair> test=new ArrayList();
       MyPair x=new MyPair(5,1);
       test.add(x);
       x=null;
       x=new MyPair(6,2);
       test.add(x);
       x=null;
       x=new MyPair(5,3);
       test.add(x);
       Collections.sort(test);
       for(MyPair temp:test){
          //System.out.println(temp.getCount()+" "+temp.getRoomID());
       
               }
       Map<Integer,Integer> mymap=new TreeMap<Integer,Integer>();
       mymap.put(7,1 );
       mymap.put(4,1);
       mymap.put(5,1);
       System.out.println(mymap.containsKey(4)+" " + mymap.get(4));
       
       for(Map.Entry<Integer,Integer> temp:mymap.entrySet()){
          //System.out.println(temp.getKey()+" "+temp.getValue());
           
          }
       ArrayList<TreeSet<Integer>> slotRooms=new ArrayList<TreeSet<Integer>>();
       TreeSet Slot1=new TreeSet();
       Slot1.add(5); 
       */
       
    
}
}

     
