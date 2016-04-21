package it.eng.cam.rest;

import java.util.List;
import it.eng.msee.ontorepo.ClassItem;
import it.eng.msee.ontorepo.IndividualItem;
import it.eng.msee.ontorepo.PropertyValueItem;
import it.eng.msee.ontorepo.RepositoryDAO;

public class CAMRestImpl {

	public static ClassItem getClassHierarchy(RepositoryDAO dao) {
		return dao.getClassHierarchy();
	}

	public static List<IndividualItem> getIndividuals(RepositoryDAO dao) {
			return dao.getIndividuals();
	}

	public static List<String> getOwners(RepositoryDAO dao) {
		return dao.getOwners();
	}

	public static List<IndividualItem> getIndividuals(RepositoryDAO dao, String className) {
		return dao.getIndividuals(className);
	}

	public static IndividualItem getIndividual(RepositoryDAO dao, String className, String assetName) {
		return dao.getIndividual(className);
	}

	public static void createClass(RepositoryDAO dao, String name, String parentName) {
		dao.createClass(name, parentName);
	}

	public static void moveClass(RepositoryDAO dao, String name, String parentName) {
		dao.moveClass(name, parentName);
	}

	public static void deleteClass(RepositoryDAO dao, String name) {
		dao.deleteClass(name);
	}

	public static List<PropertyValueItem> getIndividualAttributes(RepositoryDAO dao, String assetName) {
		return dao.getIndividualAttributes(assetName);
	}

	public static void createAssetModel(RepositoryDAO dao, String name, String className, String ownerName) {
		dao.createAssetModel(name, className, ownerName);

	}

	public static void createAsset(RepositoryDAO dao, String name, String modelName, String ownerName) {
		dao.createAsset(name, modelName, ownerName);

	}

	public static void setRelationship(RepositoryDAO dao, String name, String individualName, String referredName) {
		dao.setRelationship(name, individualName, referredName);

	}

	public static void deleteIndividual(RepositoryDAO dao, String assetName) {
		dao.deleteIndividual(assetName);
	}

	public static void removeProperty(RepositoryDAO dao, String assetName, String propertyName) {
		dao.removeProperty(assetName, propertyName);

	}

	public static List<IndividualItem> getModelsIndividuals(RepositoryDAO dao) {
		// Neccessita di un filtro ulteriore per estrarre solo i modelli
		return dao.getIndividuals();
	}

	public static void createOwner(RepositoryDAO dao, String ownerName) {
		dao.createOwner(ownerName);
	}

	public static void deleteOwner(RepositoryDAO dao, String ownerName) {
		dao.deleteOwner(ownerName);

	}
}
