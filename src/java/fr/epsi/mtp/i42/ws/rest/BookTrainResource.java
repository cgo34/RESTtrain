/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.mtp.i42.ws.rest;

import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.GenericEntity;


/**
 *
 * @author Geoffroy
 */

public class BookTrainResource {
    
    @Path("/booktrains")
    @Produces("application/xml")
    public BookTrainResource getBookTrainResource(){
        return new BookTrainResource();
    }
    
    @POST
    public Response createBookTrain(String numTrain, int numberPlaces){
        Train currentTrain = null;
        for(Train current : BookTrainBD.getTrains()){
            if(current.getNumTrain().equals(numTrain)){
                currentTrain = current;
            }
        }
        if(currentTrain == null){
            return Response.status(Status.NO_CONTENT).build();
        }
        
        BookTrain newBookTrain = new BookTrain();
        newBookTrain.setNumberPlaces(numberPlaces);
        newBookTrain.setCurrentTrain(currentTrain);
        newBookTrain.setBookNumber(Long.toString(System.currentTimeMillis()));
        BookTrainBD.getBookTrains().add(newBookTrain);
        
        return Response.status(Status.OK).entity(newBookTrain.getBookNumber()).build();
    }
    
    @GET
    @Path ("/get")
    public Response getBookTrains(){
        System.out.println("getBookTrains");
        
        return Response.status(Status.OK).entity(BookTrainBD.getBookTrains()).build();
    }
    
    @GET
    @Path("{id}")
    public Response getBookTrain(@PathParam("id") String bookNumber){
        List<BookTrain> bookTrains = BookTrainBD.getBookTrains();
        
        for(BookTrain current : bookTrains){
            if(current.getBookNumber().equals(bookNumber)){
                return Response.status(Status.OK).entity(current).build();
            }
        }
        
        return Response.status(Status.NO_CONTENT).build();
    }
    
    
    
    @DELETE
    @Path("{id}")
    public Response removeBookTrain(@PathParam("id") String bookNumber){
        BookTrain currentBookTrain = null;
        for(BookTrain current : BookTrainBD.getBookTrains()){
            if(current.getBookNumber().equals(bookNumber)){
                currentBookTrain = current;
            }
        }
        return Response.status(Status.ACCEPTED).build();
    }
}