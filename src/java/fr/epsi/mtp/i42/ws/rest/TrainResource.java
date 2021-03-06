/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.mtp.i42.ws.rest;

import javax.xml.bind.annotation.XmlRootElement; 
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/trains")
@Produces("application/xml")
public class TrainResource {
    
    public TrainResource(){
        
    }
    
    @GET
    public Response getTrains(){
        System.out.println("getTrains");
        
        return Response.status(Status.OK).entity(new GenericEntity<List<Train>>(BookTrainBD.getTrains()){}).build();
    }
    
    @GET
    @Path("numTrain-{id}")
    public Response getTrain(@PathParam("id") String numTrain){
        System.out.println("getTrain");
        
        for(Train current: BookTrainBD.getTrains()){
            if(numTrain.equals(current.getNumTrain())){
                return Response.status(Status.OK).entity(current).build();
            }
        }
        
        return Response.status(Status.NO_CONTENT).build();
    }
    
    
  
    @POST
    public Response createTrain(Train train) {
        System.out.println("createTrain");
        System.out.println("ajouter Train " + train);
        
        if (train.getNumTrain() != null
                && !train.getNumTrain().equals("null")
                && train.getVilleDepart() != null
                && !train.getVilleDepart().equals("null")
                && train.getVilleArrivee() != null
                && !train.getVilleArrivee().equals("null")) {
            BookTrainBD.getTrains().add(train);
            return Response
                    .status(Status.CREATED)
                    .entity(train)
                    .build();
        }

        return Response
                .status(Status.NO_CONTENT)
                .build();
    }
    
    @DELETE
    @Path("deleteTrain-{id}")
    public Response removeTrain(@PathParam("id") String numTrain){
       System.out.println("removeTrain");
        for(Train current : BookTrainBD.getTrains()){
            if(current.getNumTrain().equals(numTrain)){
                BookTrainBD.getTrains().remove(current);
                return Response.status(Status.ACCEPTED).build();
            }
        }
        return Response.status(Status.NO_CONTENT).build();
    }
    
    @GET // Méthode HTTP utilisée pour déclencher cette méthode
    @Path("/search") // Chemin suivant /trains/search pour invoquer cette méthode
    public Response searchTrainsByCriteria(@QueryParam("departure") String departure, @QueryParam("arrival") String arrival, @QueryParam("arrivalhour") String arrivalHour) {
        System.out.println("searchTrainsByCriteria");
        System.out.println(arrivalHour);
        if(arrivalHour != null){
            System.out.println(arrivalHour);
        }else{
            System.out.println("fuck");
        }
        List<Train> list = new ArrayList<>();
        for (Train current : BookTrainBD.getTrains()) {
            if ((departure != null && current.getVilleDepart().toLowerCase().equals(departure.toLowerCase()))
                    || (arrival != null && current.getVilleArrivee().toLowerCase().equals(arrival.toLowerCase()))
                    || (arrivalHour != null && String.valueOf(current.getHeureDepart()).equals(arrivalHour))) {
                list.add(current);
                System.out.println(current.getHeureDepart());
                System.out.println("saisie utilisateur");
                System.out.println(arrivalHour);
            }
        }
        return Response.status(Status.OK).entity(new GenericEntity<List<Train>>(list){}).build();
    }

	
}