package si.fri.rso.samples.customers.services.beans;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import org.eclipse.microprofile.metrics.annotation.Timed;
import si.fri.rso.samples.customers.models.dtos.Order;
import si.fri.rso.samples.customers.models.entities.Customer;
import si.fri.rso.samples.customers.models.entities.Ride;
import si.fri.rso.samples.customers.models.entities.User;
import si.fri.rso.samples.customers.services.configuration.AppProperties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@RequestScoped
public class RidesBean {

    private Logger log = Logger.getLogger(RidesBean.class.getName());

    @Inject
    private EntityManager em;

    @Inject
    private AppProperties appProperties;

    @Inject
    private RidesBean ridesBean;

    private Client httpClient;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
//        baseUrl = "http://localhost:8081"; // only for demonstration
    }


    public List<Ride> getRides() {

        TypedQuery<Ride> query = em.createNamedQuery("Ride.getAll", Ride.class);

        return query.getResultList();

    }


    public Ride getRide(Integer rideId) {

        Ride ride = em.find(Ride.class, rideId);

        if (ride == null) {
            throw new NotFoundException();
        }

        return ride;
    }

    public Ride createRide(Ride ride) {

        try {
            beginTx();
            em.persist(ride);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return ride;
    }

    public Ride putRide(String rideId, Ride ride) {

        Ride c = em.find(Ride.class, rideId);

        if (c == null) {
            return null;
        }

        try {
            beginTx();
            ride.setId(c.getId());
            ride = em.merge(ride);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return ride;
    }

    public boolean deleteRide(String rideId) {

        Ride ride = em.find(Ride.class, rideId);

        if (ride != null) {
            try {
                beginTx();
                em.remove(ride);
                commitTx();
            } catch (Exception e) {
                rollbackTx();
            }
        } else
            return false;

        return true;
    }


    private void beginTx() {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    private void commitTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().rollback();
    }

    public void loadOrder(Integer n) {


    }
}
