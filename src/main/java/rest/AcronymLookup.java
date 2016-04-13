package rest;

import com.fasterxml.jackson.databind.util.JSONPObject;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import org.json.*;

@Path("/acronyms")
@Singleton
public class AcronymLookup {

    private ArrayList<Acronym> acronyms = new ArrayList<>();

    public AcronymLookup() {
        String[] links = {"foo", "bar", "baz"};
        String[] labels = {"DevOps"};
        String[] links2 = {"www.google.com", "www.wikipedia.com", "www.bbc.co.uk"};
        String[] labels2 = {"IT", "Group Digital"};
        String[] labels3 = {"DevOps", "Group Digital"};
        String[] labels4 = {"Group Digital", "Group IT"};
        Acronym uat = new Acronym("UAT", "User Acceptance Testing", links, labels);
        Acronym poc = new Acronym("POC", "Proof of Concept", links, labels);
        Acronym serviceDesk = new Acronym("SD", "Service Desk. This is the place for reporting IT problems and requests", links2, new String[]{"IT", "Group Digital", "IT Support"});
        Acronym serviceDelivery = new Acronym("SD", "Service Delivery.", links2, new String[] {"Group Digital", "Group IT"});
        Acronym businessReleaseAndImpl = new Acronym("BR&I", "Business Release and Implementation. This is the team which manages business releases.", new String[]{"www.br&i.lloydsbank.com"}, new String[]{"DevOps", "Group Digital"});
        Acronym qa = new Acronym("QA", "Quality Assurance. QA are responsible for testing software at various stages of the development lifecycle. They work aginst pre arranged test plans manually check Business Rules and Use Cases have been correctly implemented", links, new String[]{"Testing", "Initiatives"});
        Acronym ui = new Acronym("UI", "User Interface is the the part of a website or app which the user directly interacts with. Components of a UI might be buttons, input boxes and file menus", links2, new String[]{"Internet Banking", "User Interface"});
        Acronym ib = new Acronym("IB", "Internet Banking is the platform used by commercial and retail customers to access Lloyds Bank services on the internet. It features an ever growing set of features and is available on mobile ", links2, new String[] {"Group Digital", "Group IT"});
        Acronym nga = new Acronym("NGA", "User Acceptance Testing", links, labels);
        Acronym salsa = new Acronym("SALSA", "Simple Architecture for Lightweight Service Aggregation. This is the middleware layer used by all channels to perform various operations e.g. applying for products or making payments.", links, new String[]{"Middleware", "Internet Banking"});
        acronyms.add(uat);
        acronyms.add(poc);
        acronyms.add(serviceDesk);
        acronyms.add(serviceDelivery);
        acronyms.add(businessReleaseAndImpl);
        acronyms.add(qa);
        acronyms.add(ui);
        acronyms.add(ib);
        acronyms.add(nga);
        acronyms.add(salsa);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response helloResource(@QueryParam("name") String name) {
        String acronymsJson = "[";
        List<Acronym> acronymList = new ArrayList<>();
        for(int i = 0; i< acronyms.size(); i++) {
            Acronym acronym = acronyms.get(i);
            if(acronym.getName().startsWith(name) || acronym.getName().equalsIgnoreCase(name)) {
                acronymList.add(acronym);
                acronymsJson += acronym.toString();
                if (i != acronyms.size() - 1) {
                    acronymsJson += ",";
                }
            }
        }

        if(acronymsJson.length() != 1) {
            acronymsJson = acronymsJson.substring(0, acronymsJson.length() - 1);
        }
        acronymsJson += "]";

        return Response.ok().entity(acronymList).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Header", "*").header("Access-Control-Allow-Method", "*").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postAcronym(Acronym acronym) {
        JSONObject incomingJson = new JSONObject(acronym);

        acronyms.add(acronym);

        return Response.status(200).entity("").build();
    }
}
