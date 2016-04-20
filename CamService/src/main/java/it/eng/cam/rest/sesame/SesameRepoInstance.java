package it.eng.cam.rest.sesame;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import it.eng.msee.ontorepo.RepositoryDAO;
import it.eng.msee.ontorepo.sesame2.Sesame2RepositoryDAO;

public class SesameRepoInstance {
	private static RepositoryDAO repoInstance;
	private static final Logger logger = Logger.getLogger(SesameRepoInstance.class.getName());
	public static ResourceBundle finder = null;

	private static String SESAME_REPO_URL = null;
	private static String SESAME_REPO_NAME = null;
	private static String SESAME_REPO_NAMESPACE = null;
	
	static {
		try {
			finder = ResourceBundle.getBundle("cam-service");
			SESAME_REPO_URL = finder.getString("sesame.url");
			SESAME_REPO_NAME = finder.getString("sesame.repository");
			SESAME_REPO_NAMESPACE = finder.getString("sesame.namespace");
		} catch (MissingResourceException e) {
			logger.warning(e.getMessage());
		}
	}

	// SINGLETON
	public static RepositoryDAO getRepoInstance() {
		if (repoInstance == null) {
			synchronized (SesameRepoInstance.class) {
				if (repoInstance == null) {
					repoInstance = new Sesame2RepositoryDAO(SESAME_REPO_URL, SESAME_REPO_NAME, SESAME_REPO_NAMESPACE);

				}
			}
		}
		return repoInstance;
	}
	
	// SINGLETON
	public static RepositoryDAO getRepoInstance(String sesameRepoUrl, String sesameRepoName, String sesameRepoNameSpace) {
		if (repoInstance == null) {
			synchronized (SesameRepoInstance.class) {
				if (repoInstance == null) {
					repoInstance = new Sesame2RepositoryDAO(sesameRepoUrl, sesameRepoName, sesameRepoNameSpace);

				}
			}
		}
		return repoInstance;
	}

	public synchronized static void releaseRepoDaoConn() {
		if (repoInstance != null) {
			Sesame2RepositoryDAO sRepo = (Sesame2RepositoryDAO) repoInstance;
			sRepo.release();
			repoInstance = null;
		}
	}
}
