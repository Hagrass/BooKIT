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
public class EmergencyRequest {
    private Reservation MyReservation;
    private Reservation ReplacementReservation;

    public Reservation getMyReservation() {
        return MyReservation;
    }

    public void setMyReservation(Reservation MyReservation) {
        this.MyReservation = MyReservation;
    }

    public Reservation getReplacementReservation() {
        return ReplacementReservation;
    }

    public void setReplacementReservation(Reservation ReplacementReservation) {
        this.ReplacementReservation = ReplacementReservation;
    }
    
    
}
