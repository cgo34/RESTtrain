/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.mtp.i42.ws.rest;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Geoffroy
 */
@XmlRootElement(name="booktrain")
public class BookTrain {
    
    private String bookNumber;
    private Train currentTrain;
    private int numberPlaces;
    
    public String getBookNumber(){
        return bookNumber;
    }
    
    public void setBookNumber(String bookNumber){
        this.bookNumber = bookNumber;
    }
    
    public Train getCurrentTrain(){
        return currentTrain;
    }
    
    /**
     * @param currentTrain the currentTrain to set
     */
    public void setCurrentTrain(Train currentTrain){
        this.currentTrain = currentTrain;
    }
    
    /**
     * @return the numberPlaces
     */
    public int getNumberPlaces(){
        return numberPlaces;
    }
    
    /**
     * @param numberPlaces the numberPlaces to set
     */
    public void setNumberPlaces(int numberPlaces){
        this.numberPlaces = numberPlaces;
    }
}   
