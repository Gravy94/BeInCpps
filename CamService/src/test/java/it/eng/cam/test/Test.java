package it.eng.cam.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;

import it.eng.cam.rest.CAMRestImpl;
import it.eng.cam.rest.sesame.SesameRepoInstance;
import it.eng.msee.ontorepo.ClassItem;
import it.eng.msee.ontorepo.IndividualItem;
import it.eng.msee.ontorepo.RepositoryDAO;

public class Test {

	private static final int MAX_RANDOM = 1000;
	RepositoryDAO dao;

	@Before
	public void init() {
		dao = SesameRepoInstance.getRepoInstance();
	}

	@After
	public void release() {
		SesameRepoInstance.releaseRepoDaoConn();
	}

	//
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
		Random rnd = new Random();
		String name = "NewClass_"+rnd.nextInt(MAX_RANDOM);
		ClassItem root = dao.getClassHierarchy();
		String parentName = root.getClassName();
		RepositoryDAO dao = SesameRepoInstance.getRepoInstance();
		try {
			CAMRestImpl.createClass(dao, name, parentName);
		} catch (Exception e) {
			assertFalse(e.getMessage(), true);
		}
		List<ClassItem> subClasses = root.getSubClasses();
		subClasses.forEach(System.out::println);
		List<ClassItem> classInserted = subClasses.stream().filter(csi -> csi.getClassName().equals(name))
				.collect(Collectors.toList());
		assertNotNull(classInserted);
		assertFalse(classInserted.isEmpty());
		assertTrue(classInserted.size() == 1);
	}

	@org.junit.Test()
	public void deleteClass() {
		Random rnd = new Random();
		String name = "NewClass_"+rnd.nextInt(MAX_RANDOM);
		ClassItem root = dao.getClassHierarchy();
		String parentName = root.getClassName();
		RepositoryDAO dao = SesameRepoInstance.getRepoInstance();
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
		List<ClassItem> subClasses = root.getSubClasses();
		List<ClassItem> classInserted = subClasses.stream().filter(csi -> csi.getClassName().equals(name))
				.collect(Collectors.toList());
		assertNotNull(classInserted);
		assertTrue(classInserted.isEmpty());
	}

}
