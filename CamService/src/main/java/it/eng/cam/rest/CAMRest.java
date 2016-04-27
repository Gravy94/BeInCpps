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
import it.eng.ontorepo.ClassItem;
import it.eng.ontorepo.IndividualItem;

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
			return CAMRestImpl.getClassHierarchy(SesameRepoInstance.getRepoInstance(getClass()));
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
			return CAMRestImpl.getIndividuals(SesameRepoInstance.getRepoInstance(getClass()), className);
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}

	@POST
	@Path("/classes/{name}/{parentName}")
	public void createClass(@PathParam("name") String name, @PathParam("parentName") String parentName) {
		try {
			CAMRestImpl.createClass(SesameRepoInstance.getRepoInstance(getClass()), name, parentName);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}

	@PUT
	@Path("/classes/{name}/{parentName}")
	public void moveClass(@PathParam("name") String name, @PathParam("parentName") String parentName) {
		try {
			CAMRestImpl.moveClass(SesameRepoInstance.getRepoInstance(getClass()), name, parentName);
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
			CAMRestImpl.deleteClass(SesameRepoInstance.getRepoInstance(getClass()), name);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}

	// TODO Questo metodo come firma è equivalente a getIndividuals
	// @GET
	// @Path("/classes/{className}")
	// @Produces(MediaType.APPLICATION_JSON)
	// public IndividualItem getIndividual(@PathParam("className") String
	// className) {
	// try {
	// return
	// CAMRestImpl.getIndividual(SesameRepoInstance.getRepoInstance(getClass()),
	// className);
	// } catch (Exception e) {
	// logger.error(e);
	// return null;
	// } finally {
	// SesameRepoInstance.releaseRepoDaoConn();
	// }
	// }

	// TODO Nel documento dei requisiti il metodo createAsset ed il metodo
	// createAssetModel
	// hanno la stessa firma rest @Path("/classes/{className}/{assetName}") che
	// non va bene !!!
	@POST
	@Path("/classes/model/{name}/{modelName}/{ownerName}")
	public void createAssetModel(@PathParam("name") String name, @PathParam("modelName") String modelName,
			@PathParam("ownerName") String ownerName) {
		try {
			CAMRestImpl.createAssetModel(SesameRepoInstance.getRepoInstance(getClass()), name, modelName, ownerName);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}

	@POST
	@Path("/classes/{name}/{modelName}/{ownerName}")
	public void createAsset(@PathParam("name") String name, @PathParam("modelName") String modelName,
			@PathParam("ownerName") String ownerName) {
		try {
			CAMRestImpl.createAsset(SesameRepoInstance.getRepoInstance(getClass()), name, modelName, ownerName);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}

	@PUT
	@Path("/classes/{name}/{individualName}/{referredName}")
	public void setRelationship(@PathParam("name") String name, @PathParam("individualName") String individualName,
			@PathParam("referredName") String referredName) {
		try {
			CAMRestImpl.setRelationship(SesameRepoInstance.getRepoInstance(getClass()), name, individualName,
					referredName);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}

	@POST
	@Path("/classes/{name}/{individualName}/{value}/{type}")
	public void setAttribute(@PathParam("name") String name, @PathParam("individualName") String individualName,
			@PathParam("value") String value, @PathParam("type") String type) {
		try {
			CAMRestImpl.setAttribute(SesameRepoInstance.getRepoInstance(getClass()), name, individualName, value, type);
		} catch (IllegalArgumentException e) {
			logger.error(e);
		} catch (ClassNotFoundException e) {
			logger.error(e);
		} catch (RuntimeException e) {
			logger.error(e);
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}

	@DELETE
	@Path("/classes/individual/{className}/{assetName}")
	public void deleteIndividual(@PathParam("assetName") String assetName) {
		try {
			CAMRestImpl.deleteIndividual(SesameRepoInstance.getRepoInstance(getClass()), assetName);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}

	@DELETE
	@Path("/classes/property/{propertyName}/{assetName}")
	public void removeProperty(@PathParam("propertyName") String propertyName,
			@PathParam(" assetName") String assetName) {
		try {
			CAMRestImpl.removeProperty(SesameRepoInstance.getRepoInstance(getClass()), propertyName, assetName);
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
			return CAMRestImpl.getIndividuals(SesameRepoInstance.getRepoInstance(getClass()));
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
			return CAMRestImpl.getIndividuals(SesameRepoInstance.getRepoInstance(getClass()));
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
			return CAMRestImpl.getOwners(SesameRepoInstance.getRepoInstance(getClass()));
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}

	@POST
	@Path("/owners/{ownerName}")
	public void createOwner(@PathParam("ownerName") String ownerName) {
		try {
			CAMRestImpl.createOwner(SesameRepoInstance.getRepoInstance(getClass()), ownerName);
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
			CAMRestImpl.deleteOwner(SesameRepoInstance.getRepoInstance(getClass()), ownerName);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			SesameRepoInstance.releaseRepoDaoConn();
		}
	}
}
