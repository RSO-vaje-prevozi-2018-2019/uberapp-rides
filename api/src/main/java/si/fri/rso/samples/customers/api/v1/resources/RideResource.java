package si.fri.rso.samples.customers.api.v1.resources;

import com.kumuluz.ee.common.runtime.EeRuntime;
import com.kumuluz.ee.logs.cdi.Log;
import si.fri.rso.samples.customers.api.v1.dtos.HealthDto;
import si.fri.rso.samples.customers.api.v1.dtos.LoadDto;
import si.fri.rso.samples.customers.models.entities.Ride;
import si.fri.rso.samples.customers.services.beans.RidesBean;
import si.fri.rso.samples.customers.services.configuration.AppProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@Path("rides")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Log
public class RideResource {

    private Logger log = Logger.getLogger(RideResource.class.getName());

    @Inject
    private AppProperties appProperties;

    @Inject
    private RidesBean ridesBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getRides() {

        List<Ride> rides = ridesBean.getRides();

        return Response.ok(rides).build();
    }

    @GET
    @Path("/{rideId}")
    public Response getRide(@PathParam("rideId") Integer rideId) {

        Ride ride = ridesBean.getRide(rideId);

        if (ride == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(ride).build();
    }


}
