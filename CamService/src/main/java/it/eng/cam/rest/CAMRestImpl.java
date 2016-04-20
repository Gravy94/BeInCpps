package it.eng.cam.rest;

import java.security.acl.Owner;
import java.util.List;

import javax.ws.rs.PathParam;

import it.eng.msee.ontorepo.ClassItem;
import it.eng.msee.ontorepo.IndividualItem;
import it.eng.msee.ontorepo.PropertyValueItem;
import it.eng.msee.ontorepo.sesame2.Sesame2RepositoryDAO;

public class CAMRestImpl {

	public static ClassItem getClassHierarchy(Sesame2RepositoryDAO dao) {
		if (isDAOOpen(dao))
			return dao.getClassHierarchy();
		return null;
	}

	public static List<IndividualItem> getIndividuals(Sesame2RepositoryDAO dao) {
		if (isDAOOpen(dao))
			return dao.getIndividuals();
		return null;
	}

	public static List<String> getOwners(Sesame2RepositoryDAO dao) {
		if (isDAOOpen(dao))
			return dao.getOwners();
		return null;
	}

	public static List<IndividualItem> getIndividuals(Sesame2RepositoryDAO dao, String className) {
		if (isDAOOpen(dao))
			return dao.getIndividuals(className);
		return null;
	}

	public static IndividualItem getIndividual(Sesame2RepositoryDAO dao, String className, String assetName) {
		if (isDAOOpen(dao))
			return dao.getIndividual(className);
		return null;
	}

	public static void createClass(Sesame2RepositoryDAO dao, String name, String parentName) {
		if (isDAOOpen(dao))
			dao.createClass(name, parentName);
	}

	public static void moveClass(Sesame2RepositoryDAO dao, String name, String parentName) {
		if (isDAOOpen(dao))
			dao.moveClass(name, parentName);
	}

	public static void deleteClass(Sesame2RepositoryDAO dao, String name) {
		if (isDAOOpen(dao))
			dao.deleteClass(name);
	}

	public static List<PropertyValueItem> getIndividualAttributes(Sesame2RepositoryDAO dao, String assetName) {
		if (isDAOOpen(dao))
			return dao.getIndividualAttributes(assetName);
		return null;
	}

	public static void createAssetModel(Sesame2RepositoryDAO dao, String name, String className, String ownerName) {
		if (isDAOOpen(dao))
			dao.createAssetModel(name, className, ownerName);

	}

	public static void createAsset(Sesame2RepositoryDAO dao, String name, String modelName, String ownerName) {
		if (isDAOOpen(dao))
			dao.createAsset(name, modelName, ownerName);

	}

	public static void setRelationship(Sesame2RepositoryDAO dao, String name, String individualName,
			String referredName) {
		if (isDAOOpen(dao))
			dao.setRelationship(name, individualName, referredName);

	}

	public static void deleteIndividual(Sesame2RepositoryDAO dao, String assetName) {
		if (isDAOOpen(dao))
			dao.deleteIndividual(assetName);
	}

	public static void removeProperty(Sesame2RepositoryDAO dao, String assetName, String propertyName) {
		if (isDAOOpen(dao))
			dao.removeProperty(assetName, propertyName);

	}

	public static List<IndividualItem> getModelsIndividuals(Sesame2RepositoryDAO dao) {
		if (isDAOOpen(dao))
//		Neccessita di un filtro ulteriore per estrarre solo i modelli
			return dao.getIndividuals();

		return null;
	}


	public static void createOwner(Sesame2RepositoryDAO dao, String ownerName) {
		if (isDAOOpen(dao))
			dao.createOwner(ownerName);	
	}


	public static void deleteOwner(Sesame2RepositoryDAO dao, String ownerName) {
		if (isDAOOpen(dao))
			dao.deleteOwner(ownerName);	
		
	}

	private static boolean isDAOOpen(Sesame2RepositoryDAO dao) {
		if (dao == null || !(dao.isInitiliazed()))
			return false;
		return true;
	}

}
