package it.eng.cam.rest;

import it.eng.msee.ontorepo.ClassItem;
import it.eng.msee.ontorepo.IndividualItem;
import it.eng.msee.ontorepo.PropertyValueItem;
import it.eng.msee.ontorepo.sesame2.Sesame2RepositoryDAO;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class CAMRest extends ResourceConfig {
	public CAMRest() {
		packages("it.eng.cam.rest");
	}

	private static final String server = "http://localhost:8180/openrdf-sesame/"; // if
																					// you
																					// are
																					// not
																					// running
																					// on
																					// a
																					// local
																					// instance,
																					// set
																					// the
																					// correct
																					// URL
	private static final String repository = "DEFAULT"; // the name YOU gave to
														// your test repository:
														// change ad libitum
	private static final String namespace = "http://www.msee-ip.eu/ontology/bivolino#"; // you
																						// SHOULD
																						// use
																						// TA_BIVOLINO.owl,
																						// so
																						// this
																						// is
																						// unlikely
																						// to
																						// change

	private static Sesame2RepositoryDAO dao = new Sesame2RepositoryDAO(server, repository, namespace);

	@GET
	@Path("/classes")
	@Produces(MediaType.APPLICATION_JSON)
	public ClassItem getClassHierarchy() {
		return CAMRestImpl.getClassHierarchy(dao);
	}

	@GET
	@Path("/classes/{className}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IndividualItem> getIndividuals(@PathParam("className") String className) {
		return CAMRestImpl.getIndividuals(dao, className);
	}

	@POST
	@Path("/classes/{name}/{parentName}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createClass(@PathParam("name") String name, @PathParam("parentName") String parentName) {
		CAMRestImpl.createClass(dao, name, parentName);
	}

	@PUT
	@Path("/classes/{name}/{parentName}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void moveClass(@PathParam("name") String name, @PathParam("parentName") String parentName) {
		CAMRestImpl.moveClass(dao, name, parentName);
	}

	@DELETE
	@Path("/classes/{name}")
	public void deleteClass(@PathParam("name") String name) {
		CAMRestImpl.deleteClass(dao, name);
	}

	//
	// // This method is called if XML is request
	// @GET
	// @Produces(MediaType.TEXT_XML)
	// public String sayXMLHello() {
	// return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
	// }
	//
	@GET
	@Path("/classes/{className}/{assetName}")
	@Produces(MediaType.APPLICATION_JSON)
	public IndividualItem getIndividual(@PathParam("className") String className,
			@PathParam("assetName") String assetName) {
		return CAMRestImpl.getIndividual(dao, className, assetName);
	}

	@GET
	@Path("/classes/{className}/{assetName}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PropertyValueItem> getIndividualAttributes(@PathParam("assetName") String assetName) {
		return CAMRestImpl.getIndividualAttributes(dao, assetName);
	}

	@POST
	@Path("/classes/{name}/{className}/{ownerName}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createAssetModel(@PathParam("name") String name, @PathParam("className") String className,
			@PathParam("ownerName") String ownerName) {
		CAMRestImpl.createAssetModel(dao, name, className, ownerName);
	}

	@POST
	@Path("/classes/{name}/{modelName}/{ownerName}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createAsset(@PathParam("name") String name, @PathParam("modelName") String modelName,
			@PathParam("ownerName") String ownerName) {
		CAMRestImpl.createAsset(dao, name, modelName, ownerName);
	}

	@POST
	@Path("/classes/{name}/{individualName}/{referredName}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setRelationship(@PathParam("name") String name, @PathParam("individualName") String individualName,
			@PathParam("referredName") String referredName) {
		CAMRestImpl.setRelationship(dao, name, individualName, referredName);
	}

	//// @POST
	//// @Path("/classes/{name}/{individualName}/{value}/{type}")
	//// @Consumes(MediaType.APPLICATION_JSON)
	//// public void setAttribute(String name, String individualName, String
	//// value, Class<?> type) {
	//// dao.setAttribute(name, individualName, value, type);
	//// }

	@PUT
	@Path("/classes/{name}/{individualName}/{referredName}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateRelationship(@PathParam("name") String name, @PathParam("individualName") String individualName,
			@PathParam("referredName") String referredName) {
		CAMRestImpl.setRelationship(dao, name, individualName, referredName);
	}

	//// @PUT
	//// @Path("/classes/{name}/{individualName}/{value}/{type}")
	//// @Consumes(MediaType.APPLICATION_JSON)
	//// public void updateAttribute(String name, String individualName, String
	//// value, Class<?> type) {
	//// dao.setAttribute(name, individualName, value, type);
	//// }

	@DELETE
	@Path("/classes/{className}/{assetName}")
	public void deleteIndividual(@PathParam("assetName") String assetName) {
		CAMRestImpl.deleteIndividual(dao, assetName);
	}

	@DELETE
	@Path("/classes/{className}/{assetName}/{propertyName}")
	public void removeProperty(@PathParam("assetName") String assetName,
			@PathParam(" propertyName") String propertyName) {
		CAMRestImpl.removeProperty(dao, assetName, propertyName);
	}

	@GET
	@Path("/assets")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IndividualItem> getIndividuals() {
		return CAMRestImpl.getIndividuals(dao);
	}

	// TODO Da affinare
	@GET
	@Path("/models")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IndividualItem> getModelsIndividuals() {
		return CAMRestImpl.getIndividuals(dao);
		// necessita di un filtro ulteriore
	}

	@GET
	@Path("/owners")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getOwners() {
		return CAMRestImpl.getOwners(dao);
	}
	
	 @POST
	 @Path("/owners/{ownerName}")
	 @Consumes(MediaType.APPLICATION_JSON)
	 public void createOwner(@PathParam("ownerName") String ownerName) {
		 CAMRestImpl.createOwner(dao, ownerName);
	 }
	
	 @DELETE
	 @Path("/owners/{ownerName}")
	 public void deleteOwner(@PathParam("ownerName") String ownerName) {
		 CAMRestImpl.deleteOwner(dao, ownerName);
	 }
}
