package si.fri.rso.samples.customers.api.v1.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class TestRideResource {

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getNumber() {

        int rideNumber = 0;

        return Response.ok(rideNumber).build();
    }

}
