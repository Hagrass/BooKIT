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
import java.time.*;
public class Reservation implements Cloneable {
    protected int reservationID;
    protected Classroom classroom;
    protected int comp;
    protected int mic;
    protected int proj;
    protected int smart;
    protected int NumberofStudents;
    protected String status;
    protected String Timeslot;
    protected LocalDate Date;
    protected String Purpose;
    protected int UserID;
    protected int priority;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }
    
    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }
    

    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String Purpose) {
        this.Purpose = Purpose;
    }

    public int getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeslot() {
        return Timeslot;
    }

    public void setTimeslot(String Timeslot) {
        this.Timeslot = Timeslot;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate Date) {
        this.Date = Date;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    public int getComp() {
        return comp;
    }

    public int getNumberofStudents() {
        return NumberofStudents;
    }

    public void setNumberofStudents(int NumberofStudents) {
        this.NumberofStudents = NumberofStudents;
    }

    public void setComp(int comp) {
        this.comp = comp;
    }

    public int getMic() {
        return mic;
    }

    public void setMic(int mic) {
        this.mic = mic;
    }

    public int getProj() {
        return proj;
    }

    public void setProj(int proj) {
        this.proj = proj;
    }

    public int getSmart() {
        return smart;
    }

    public void setSmart(int smart) {
        this.smart = smart;
    }
   

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
    
    protected Object clone()  throws CloneNotSupportedException{
        return super.clone();
    }
    
}
