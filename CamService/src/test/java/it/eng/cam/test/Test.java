package it.eng.cam.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.security.acl.Owner;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.openrdf.model.vocabulary.OWL;

import it.eng.cam.rest.CAMRestImpl;
import it.eng.msee.ontorepo.ClassItem;
import it.eng.msee.ontorepo.IndividualItem;
import it.eng.msee.ontorepo.sesame2.Sesame2RepositoryDAO;

public class Test {
	
	String server = "http://localhost:8180/openrdf-sesame/"; // if you are not running on a local instance, set the correct URL
	String repository = "test_00"; // the name YOU gave to your test repository: change ad libitum
	String namespace = "http://www.msee-ip.eu/ontology/bivolino#"; // you SHOULD use TA_BIVOLINO.owl, so this is unlikely to change 

	Sesame2RepositoryDAO dao;
	@Before 
	public void init() {
		dao =  new Sesame2RepositoryDAO(server, repository, namespace);
	}
	@After
	public void release() {
		dao = null;
	}
	
	
//	@org.junit.Test
//	public void getClassHierarchy(){
//		ClassItem classHierarchy = CAMRestImpl.getClassHierarchy(dao);
//	      assertNotNull(classHierarchy);
//	      List<IndividualItem> individuals = dao.getIndividuals();
//	      IndividualItem individualItem = individuals.get(0);
//	      String name = individualItem.getIndividualName();
//	      IndividualItem individualItem2 = dao.getIndividual(name);
//	}
//	
//	@org.junit.Test
//	public void getIndividuals(){
//		List<IndividualItem> individuals = CAMRestImpl.getIndividuals(dao);
//	      assertNotNull(individuals);
//	      assertFalse(individuals.isEmpty());
//	}
//	
//	@org.junit.Test
//	public void getOwners(){
//		List<String> owners = CAMRestImpl.getOwners(dao);
//	      assertNotNull(owners);
////	      Onwers empty is not problem
////	      assertFalse(owners.isEmpty());
//	}
//
//	@org.junit.Test
//	public void getIndividualsForClass(){
//		String className = "http://www.w3.org/2002/07/owl#Thing";
//		List<IndividualItem> individuals = CAMRestImpl.getIndividuals(dao, className);
//	      assertNotNull(individuals);
////	      assertFalse(individuals.isEmpty());
//	}
//	
//	@org.junit.Test
//	public void getIndividualForClassAndAsset(){
//		String className = "http://www.w3.org/2002/07/owl#Thing";
//		String assetName = "Asset";
//		IndividualItem individual = CAMRestImpl.getIndividual(dao, className, assetName);
//	      assertNotNull(individual);
//	}

	@org.junit.Test
	public void createClass(){
		String name = "NewClass";
		String parentName = OWL.THING.stringValue();
//		CAMRestImpl.createClass(dao, name, parentName);
//		dao.createAssetModel("MassiAsset", name, null);
//		dao.createAsset("AssetVero", "MassiAsset", null);
//		dao.deleteClass(name);
		List<IndividualItem> individuals = CAMRestImpl.getIndividuals(dao,name);
		IndividualItem itm = dao.getIndividual("MassiAsset");
		IndividualItem itm2 = dao.getIndividual("AssetVero");
		assertNotNull(individuals);
//		assertFalse(individuals.isEmpty());
	}
	
}
