package it.eng.cam.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import it.eng.cam.rest.CAMRestImpl;
import it.eng.cam.rest.sesame.SesameRepoInstance;
import it.eng.msee.ontorepo.ClassItem;
import it.eng.msee.ontorepo.IndividualItem;
import it.eng.msee.ontorepo.PropertyValueItem;
import it.eng.msee.ontorepo.RepositoryDAO;
import it.eng.msee.ontorepo.sesame2.Sesame2RepositoryDAO;

public class Test extends Assert {

	private static final int MAX_RANDOM = 10000;
	RepositoryDAO dao;

	@BeforeClass
	public static void oneTimeSetUp() {
		System.out.println("CAMService Test Starting......");
	}

	@AfterClass
	public static void oneTimeTearDown() {
		System.out.println("CAMService Test has ended!");
	}

	@Before
	public void setUp() {
		dao = SesameRepoInstance.getRepoInstance();
	}

	@After
	public void tearDown() {
		SesameRepoInstance.releaseRepoDaoConn();
	}

	@org.junit.Test
	public void getClassHierarchy() {
		ClassItem classHierarchy = CAMRestImpl.getClassHierarchy(dao);
		assertNotNull(classHierarchy);

	}

	@org.junit.Test
	public void getIndividuals() {
		List<IndividualItem> individuals = CAMRestImpl.getIndividuals(dao);
		assertNotNull(individuals);
		assertFalse(individuals.isEmpty());
	}

	//
	@org.junit.Test
	public void getOwners() {
		List<String> owners = CAMRestImpl.getOwners(dao);
		assertNotNull(owners);
		// Onwers empty is not problem
		// assertFalse(owners.isEmpty());
	}

	//
	@org.junit.Test
	public void getIndividualsForClass() {
		String className = dao.getClassHierarchy().getClassName();
		List<IndividualItem> individuals = CAMRestImpl.getIndividuals(dao, className);
		assertNotNull(individuals);
		// assertFalse(individuals.isEmpty());
	}

	//
	@org.junit.Test
	public void getIndividualForClassAndAsset() {
		String className = dao.getClassHierarchy().getClassName();
		String assetName = "Asset";
		IndividualItem individual = CAMRestImpl.getIndividual(dao, className, assetName);
		assertNotNull(individual);
	}

	@org.junit.Test()
	public void createClass() {
		String name = "NewClass_" + getNextRandom();
		String parentName = dao.getClassHierarchy().getClassName();
		try {
			CAMRestImpl.createClass(dao, name, parentName);
		} catch (Exception e) {
			assertFalse(e.getMessage(), true);
		}
		List<ClassItem> subClasses =  dao.getClassHierarchy().getSubClasses();
		List<ClassItem> subClassesFiltered = subClasses.stream()
				.filter(indiv -> indiv.getClassName().equals(name)).collect(Collectors.toList());
		
		assertNotNull("Null element retrieved! ClassName: "+name, subClassesFiltered);
		assertFalse("Empty element retrieved! ClassName: "+name, subClassesFiltered.isEmpty());
		assertTrue(subClassesFiltered.size() == 1);
	}

	@org.junit.Test()
	public void deleteClass() {
		String name = "NewClass_" + getNextRandom();
		String parentName = dao.getClassHierarchy().getClassName();
		try {
			CAMRestImpl.createClass(dao, name, parentName);
		} catch (Exception e) {
			assertFalse(e.getMessage(), true);
		}
		try {
			CAMRestImpl.deleteClass(dao, name);
		} catch (Exception e) {
			assertFalse(e.getMessage(), true);
		}
		List<ClassItem> subClasses =  dao.getClassHierarchy().getSubClasses();
		List<ClassItem> classInserted = subClasses.stream().filter(csi -> csi.getClassName().equals(name))
				.collect(Collectors.toList());
		assertNotNull(classInserted);
		assertTrue(classInserted.isEmpty());
	}

	@org.junit.Test()
	public void moveClass() {
		String className = "NewClass_" + getNextRandom();
		String rootName = dao.getClassHierarchy().getClassName(); // Thing
		try {
			CAMRestImpl.createClass(dao, className, rootName);
		} catch (Exception e) {
			assertFalse(e.getMessage(), true);
		}
		String className2 = "NewClass_" + getNextRandom();
		try {
			CAMRestImpl.createClass(dao, className2, rootName);
		} catch (Exception e) {
			assertFalse(e.getMessage(), true);
		}
		tearDown();
		setUp();
		try {
			CAMRestImpl.moveClass(dao, className2, className);
		} catch (Exception e) {
			assertFalse(e.getMessage(), true);
		}
		List<ClassItem> subClasses =  dao.getClassHierarchy().getSubClasses();
		List<ClassItem> classInserted = subClasses.stream().filter(csi -> csi.getClassName().equals(className))
				.collect(Collectors.toList());
		List<ClassItem> classInserted2 = null;;
		try {
			classInserted2 = classInserted.get(0).getSubClasses()
					.stream().filter(csi -> csi.getClassName()
							.equals(className2)).collect(Collectors.toList());
		} catch (Exception e) {
			assertFalse(e.getMessage(), true);
		}	
		assertNotNull(classInserted2);
		assertFalse(classInserted2.isEmpty());
		assertTrue(classInserted2.size() == 1);
	}

	@org.junit.Test
	public void createOwner() {
		String ownerName = "John_" + getNextRandom();
		try {
			CAMRestImpl.createOwner(dao, ownerName);
		} catch (Exception e) {
			assertFalse(e.getMessage(), true);
		}
		List<String> owners = CAMRestImpl.getOwners(dao);
		List<String> ownersFiltered = owners.stream().filter(own -> own.equals(ownerName)).collect(Collectors.toList());

		assertNotNull(ownersFiltered);
		assertFalse(ownersFiltered.isEmpty());
		assertTrue(ownersFiltered.size() == 1);
	}

	@org.junit.Test
	public void createAssetModel() {
		String className = "NewClass_" + getNextRandom();
		ClassItem root = dao.getClassHierarchy();
		String parentName = root.getClassName();
		try {
			CAMRestImpl.createClass(dao, className, parentName);
		} catch (Exception e) {
			assertFalse(e.getMessage(), true);
		}
		String ownerName = "Jack_Frost_" + getNextRandom();
		try {
			CAMRestImpl.createOwner(dao, ownerName);
		} catch (Exception e) {
			assertFalse(e.getMessage(), true);
		}
		tearDown();
		setUp();
		String assetModelName = "Test_Driven_Development_" + getNextRandom();
		try {
			CAMRestImpl.createAssetModel(dao, assetModelName, className, ownerName);
		} catch (Exception e) {
			assertFalse(e.getMessage(), true);
		}
		List<IndividualItem> individuals = dao.getIndividuals();
		List<IndividualItem> individualsFiltered = individuals.stream()
				.filter(ind -> ind.getNormalizedName().equals(assetModelName))
				.collect(Collectors.toList());
		
		assertNotNull("Null element retrieved! AssetModelName: "+assetModelName, individualsFiltered);
		assertFalse("Empty element retrieved! AssetModelName: "+assetModelName, individualsFiltered.isEmpty());
		assertTrue(individualsFiltered.size() == 1);
	}

	private int getNextRandom() {
		Random rand = new Random();
		return Math.abs(rand.nextInt(MAX_RANDOM));
	}
	
	
	private void printDocument(Document doc, String file) {
		try {
			OutputStream out = new FileOutputStream(file);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.transform(new DOMSource(doc), new StreamResult(new OutputStreamWriter(out, "UTF-8")));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
