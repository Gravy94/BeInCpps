package it.eng.cam.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;

import it.eng.cam.rest.sesame.SesameRepoInstance;
import it.eng.msee.ontorepo.ClassItem;
import it.eng.msee.ontorepo.IndividualItem;
import it.eng.msee.ontorepo.PropertyValueItem;

@Path("/")
public class CAMRest extends ResourceConfig {
	private static final Logger logger = LogManager.getLogger(CAMRest.class.getName());

	public CAMRest() {
		packages("it.eng.cam.rest");
	}

	@GET
	@Path("/classes")
	@Produces(MediaType.APPLICATION_JSON)
	public ClassItem getClassHierarchy() {
		try {
			return CAMRestImpl.getClassHierarchy(SesameRepoInstance.getRepoInstance());
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}

	@GET
	@Path("/classes/{className}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IndividualItem> getIndividuals(@PathParam("className") String className) {
		try {
			return CAMRestImpl.getIndividuals(SesameRepoInstance.getRepoInstance(), className);
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}

	@POST
	@Path("/classes/{name}/{parentName}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createClass(@PathParam("name") String name, @PathParam("parentName") String parentName) {
		try {
			CAMRestImpl.createClass(SesameRepoInstance.getRepoInstance(), name, parentName);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}

	@PUT
	@Path("/classes/{name}/{parentName}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void moveClass(@PathParam("name") String name, @PathParam("parentName") String parentName) {
		try {
			CAMRestImpl.moveClass(SesameRepoInstance.getRepoInstance(), name, parentName);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}

	@DELETE
	@Path("/classes/{name}")
	public void deleteClass(@PathParam("name") String name) {
		try {
			CAMRestImpl.deleteClass(SesameRepoInstance.getRepoInstance(), name);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
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
		try {
			return CAMRestImpl.getIndividual(SesameRepoInstance.getRepoInstance(), className, assetName);
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}

	@GET
	@Path("/classes/{className}/{assetName}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PropertyValueItem> getIndividualAttributes(@PathParam("assetName") String assetName) {
		try {
			return CAMRestImpl.getIndividualAttributes(SesameRepoInstance.getRepoInstance(), assetName);
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}

	@POST
	@Path("/classes/{name}/{className}/{ownerName}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createAssetModel(@PathParam("name") String name, @PathParam("className") String className,
			@PathParam("ownerName") String ownerName) {
		try {
			CAMRestImpl.createAssetModel(SesameRepoInstance.getRepoInstance(), name, className, ownerName);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}

	@POST
	@Path("/classes/{name}/{modelName}/{ownerName}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createAsset(@PathParam("name") String name, @PathParam("modelName") String modelName,
			@PathParam("ownerName") String ownerName) {
		try {
			CAMRestImpl.createAsset(SesameRepoInstance.getRepoInstance(), name, modelName, ownerName);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}

	@POST
	@Path("/classes/{name}/{individualName}/{referredName}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setRelationship(@PathParam("name") String name, @PathParam("individualName") String individualName,
			@PathParam("referredName") String referredName) {
		try {
			CAMRestImpl.setRelationship(SesameRepoInstance.getRepoInstance(), name, individualName, referredName);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}

	//// @POST
	//// @Path("/classes/{name}/{individualName}/{value}/{type}")
	//// @Consumes(MediaType.APPLICATION_JSON)
	//// public void setAttribute(String name, String individualName, String
	//// value, Class<?> type) {
	//// SesameRepoInstance.getRepoInstance().setAttribute(name, individualName,
	//// value, type);
	//// }

	@PUT
	@Path("/classes/{name}/{individualName}/{referredName}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateRelationship(@PathParam("name") String name, @PathParam("individualName") String individualName,
			@PathParam("referredName") String referredName) {
		try {
			CAMRestImpl.setRelationship(SesameRepoInstance.getRepoInstance(), name, individualName, referredName);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}

	//// @PUT
	//// @Path("/classes/{name}/{individualName}/{value}/{type}")
	//// @Consumes(MediaType.APPLICATION_JSON)
	//// public void updateAttribute(String name, String individualName, String
	//// value, Class<?> type) {
	//// SesameRepoInstance.getRepoInstance().setAttribute(name, individualName,
	//// value, type);
	//// }

	@DELETE
	@Path("/classes/{className}/{assetName}")
	public void deleteIndividual(@PathParam("assetName") String assetName) {
		try {
			CAMRestImpl.deleteIndividual(SesameRepoInstance.getRepoInstance(), assetName);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}

	@DELETE
	@Path("/classes/{className}/{assetName}/{propertyName}")
	public void removeProperty(@PathParam("assetName") String assetName,
			@PathParam(" propertyName") String propertyName) {
		try {
			CAMRestImpl.removeProperty(SesameRepoInstance.getRepoInstance(), assetName, propertyName);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}

	@GET
	@Path("/assets")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IndividualItem> getIndividuals() {
		try {
			return CAMRestImpl.getIndividuals(SesameRepoInstance.getRepoInstance());
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}

	// TODO Da affinare
	@GET
	@Path("/models")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IndividualItem> getModelsIndividuals() {
		try {
			return CAMRestImpl.getIndividuals(SesameRepoInstance.getRepoInstance());
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
		// necessita di un filtro ulteriore
	}

	@GET
	@Path("/owners")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getOwners() {
		try {
			return CAMRestImpl.getOwners(SesameRepoInstance.getRepoInstance());
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}

	@POST
	@Path("/owners/{ownerName}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createOwner(@PathParam("ownerName") String ownerName) {
		try {
			CAMRestImpl.createOwner(SesameRepoInstance.getRepoInstance(), ownerName);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}

	@DELETE
	@Path("/owners/{ownerName}")
	public void deleteOwner(@PathParam("ownerName") String ownerName) {
		try {
			CAMRestImpl.deleteOwner(SesameRepoInstance.getRepoInstance(), ownerName);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}
}
