package zt.assignment.resource;

import zt.assignment.representation.DecisionRepresentation;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/decision")
@Produces(MediaType.APPLICATION_JSON)
public class DecisionResource {

    @POST
    public DecisionRepresentation assess() {
        return new DecisionRepresentation(true, "ok");
    }
}
