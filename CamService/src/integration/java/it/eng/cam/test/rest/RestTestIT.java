package it.eng.cam.test.rest;

import static com.eclipsesource.restfuse.Assert.assertOk;

import java.util.Random;
import java.util.ResourceBundle;

import javax.ws.rs.PathParam;

import org.junit.Rule;
import org.junit.runner.RunWith;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.RequestContext;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;

@RunWith(HttpJUnitRunner.class)
public class RestTestIT {

	@Rule
	public Destination destination = getDestination();

	@Context
	private Response response;
	
	private static ResourceBundle finder = ResourceBundle.getBundle("cam-service");
	private static ResourceBundle vocabulary = ResourceBundle.getBundle("vocabulary-integration");


	private Destination getDestination() {
		Destination destination = new Destination(this, finder.getString("destination.url.integration"));
		RequestContext context = destination.getRequestContext();
		context.addPathSegment("rootName", vocabulary.getString("rootName"))
		.addPathSegment("className",  vocabulary.getString("className"))
		.addPathSegment("classNameToCreate",  vocabulary.getString("classNameToCreate")+"_"+getNextRandom())
		.addPathSegment("classNameToMove", vocabulary.getString("classNameToMove"))
		.addPathSegment("classNameToDelete", vocabulary.getString("classNameToDelete"))
		.addPathSegment("assetName", vocabulary.getString("assetName"))
		.addPathSegment("assetNameToCreate", vocabulary.getString("assetNameToCreate")+"_"+getNextRandom())
		.addPathSegment("assetModelNameToCreate", vocabulary.getString("assetModelNameToCreate")+"_"+getNextRandom())
		.addPathSegment("ownerName", vocabulary.getString("ownerName"))
		.addPathSegment("assetName2", vocabulary.getString("assetName2"))
		.addPathSegment("assetName3", vocabulary.getString("assetName3"))
		.addPathSegment("relationShipName", vocabulary.getString("relationShipName")+"_"+getNextRandom())
		.addPathSegment("assetToDelete", vocabulary.getString("assetToDelete"))
		.addPathSegment("parentNameAssetToDelete", vocabulary.getString("parentNameAssetToDelete"))
		.addPathSegment("ownerNameToCreate", vocabulary.getString("ownerNameToCreate")+"_"+getNextRandom());
		return destination;
	}

	@HttpTest(method = Method.GET, path = "/classes", order = 1)
	
	public void testGetClassHierarchy() {
		assertOk(response);
	}

	@HttpTest(method = Method.GET, path = "/classes/{rootName}", order = 2)
	public void testGetIndividualsByClasses() {
		assertOk(response);
	}

	@HttpTest(method = Method.POST, path = "/classes/{classNameToCreate}/{rootName}", order = 3)
	public void testCreateClass() {
		assertOk(response);
	}

	@HttpTest(method = Method.PUT, path = "/classes/{classNameToMove}/{className}", order = 4)
	public void testMoveClass() {
		assertOk(response);
	}

	//@HttpTest(method = Method.DELETE, path = "/classes/{classNameToMove}/{classNameToDelete}", order = 5)
	public void testDeleteClass(@PathParam("name") String name) {
		assertOk(response);
	}

	@HttpTest(method = Method.GET, path = "/classes/{className}", order = 6)
	public void testGetIndividual() {
		assertOk(response);
	}
	
	
	@HttpTest(method = Method.POST, path = "/classes/model/{className}/{assetModelNameToCreate}/{ownerName}", order = 7)
	public void testCreateAssetModel(){
		assertOk(response);
	}

	@HttpTest(method = Method.POST, path ="/classes/{assetNameToCreate}/{assetModelName}/{ownerName}", order = 8)
	public void testCreateAsset() {
		assertOk(response);
	}

	@HttpTest(method = Method.PUT, path ="/classes/{relationShipName}/{assetName2}/{assetName3}", order = 9)
	public void testSetRelationship() {
		assertOk(response);
	}

	//@HttpTest(method = Method.DELETE, path ="/classes/{parentNameAssetToDelete}/{assetNameToDelete}", order = 11)
	public void testDeleteIndividual() {
		assertOk(response);
	}
	
	//TODO
	//@HttpTest(method = Method.DELETE, path ="/classes/{propertyName}/{assetName}", order = 16)
	public void testRemoveProperty() {
		assertOk(response);
	}

	@HttpTest(method = Method.GET, path = "/assets", order = 17)
	public void testGetIndividuals() {
		assertOk(response);
	}
	

//	// TODO Da affinare
//	@GET
//	@Path("/models")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<IndividualItem> getModelsIndividuals() {
//		try {
//			return CAMRestImpl.getIndividuals(SesameRepoInstance.getRepoInstance(getClass()));
//		} catch (Exception e) {
//			logger.error(e);
//			return null;
//		} finally {
//			SesameRepoInstance.releaseRepoDaoConn();
//		}
//		// necessita di un filtro ulteriore
//	}

	@HttpTest(method = Method.GET, path = "/owners", order = 0)
	public void testGetOwners() {
		assertOk(response);
	}

	@HttpTest(method = Method.POST, path = "/owners/{ownerNameToCreate}", order = 0)
	public void testCreateOwner() {
		assertOk(response);
	}

	//@HttpTest(method = Method.DELETE, path = "/owners/{ownerName}", order = 0)
	public void testDeleteOwner() {
		assertOk(response);
	}
		
	private int getNextRandom() {
		Random rand = new Random();
		return Math.abs(rand.nextInt(Integer.MAX_VALUE));
	}


}
