package it.eng.cam.test.rest;

import static com.eclipsesource.restfuse.Assert.assertOk;

import java.util.Random;

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

	private Destination getDestination() {
		Destination destination = new Destination(this, "http://localhost:8080/CAMService/"); // TODO
																								// Configuration
		RequestContext context = destination.getRequestContext();
		context.addPathSegment("rootName", "Thing").addPathSegment("className", "Individual_Management")
				.addPathSegment("classNameToCreate", "New_Class_" + getNextRandom())
				.addPathSegment("classNameToMove", "Government").addPathSegment("classNameToDelete", "Surface_related")
				.addPathSegment("assetName", "Mass_Customization_Production");
		return destination;
	}

	@HttpTest(method = Method.GET, path = "/classes")
	public void testGetClassHierarchy() {
		assertOk(response);
	}

	@HttpTest(method = Method.GET, path = "/classes/{rootName}")
	public void testGetIndividuals() {
		assertOk(response);
	}

	// @HttpTest(method = Method.POST, path =
	// "/classes/{classNameToCreate}/{rootName}")
	public void testCreateClass() {
		assertOk(response);
	}

	// @HttpTest(method = Method.PUT, path =
	// "/classes/{className}/{classNameToMove}")
	public void testMoveClass() {
		assertOk(response);
	}

	// @HttpTest(method = Method.DELETE, path = "/classes/{classNameToDelete}")
	public void testDeleteClass(@PathParam("name") String name) {
		assertOk(response);
	}

	@HttpTest(method = Method.GET, path = "/classes/{rootName}/{className}")
	public void testGetIndividual() {
		assertOk(response);
	}
	
		
	private int getNextRandom() {
		Random rand = new Random();
		return Math.abs(rand.nextInt(Integer.MAX_VALUE));
	}

}
