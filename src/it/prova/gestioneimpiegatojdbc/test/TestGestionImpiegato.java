package it.prova.gestioneimpiegatojdbc.test;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;

import it.prova.gestioneimpiegatojdbc.connection.MyConnection;
import it.prova.gestioneimpiegatojdbc.dao.Constants;
import it.prova.gestioneimpiegatojdbc.dao.compagnia.CompagniaDAO;
import it.prova.gestioneimpiegatojdbc.dao.compagnia.CompagniaDAOimp;
import it.prova.gestioneimpiegatojdbc.dao.impiegato.ImpiegatoDAOimp;
import it.prova.gestioneimpiegatojdbc.dao.impiegato.ImpiegatoDao;
import it.prova.gestioneimpiegatojdbc.model.Compagnia;
import it.prova.gestioneimpiegatojdbc.model.Impiegato;

public class TestGestionImpiegato {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CompagniaDAO compagniaDAOInstance = null;
		ImpiegatoDao impiegatoDaoInstanceDao = null;

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			compagniaDAOInstance = new CompagniaDAOimp(connection);
			impiegatoDaoInstanceDao = new ImpiegatoDAOimp(connection);

			testListImpiegato(impiegatoDaoInstanceDao);
			System.out.println();

			testListCompagnia(compagniaDAOInstance);
			System.out.println();

			testInsertCompagnia(compagniaDAOInstance);
			System.out.println();

			testDeleteCompagnia(compagniaDAOInstance);
			System.out.println();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// ListImpiegato============
	private static void testListImpiegato(ImpiegatoDao impiegatoDao) throws Exception {

		System.out.println("\tInizio Test List");
		List<Impiegato> elementiAttuali = impiegatoDao.list();
		System.out.println("attualmente sono: " + elementiAttuali.size());
		if (elementiAttuali.size() == 0) {
			System.out.println("non ci sono elementi");
		} else {
			for (Impiegato impiegatoItem : elementiAttuali) {
				System.out.println(impiegatoItem.toString());
			}
		}
		System.out.println("...Fine Metodo...");
	}

	// TestListCompagnia=============
	private static void testListCompagnia(CompagniaDAO compagniaDAO) throws Exception {

		System.out.println("\tInizio Test ListCompagnia");
		List<Compagnia> elementiAttuali = compagniaDAO.list();
		System.out.println("attualmente sono: " + elementiAttuali.size());
		if (elementiAttuali.size() == 0) {
			System.out.println("non ci sono elementi");
		} else {
			for (Compagnia compagniaItem : elementiAttuali) {
				System.out.println(compagniaItem.toString());
			}
		}
		System.out.println("...Fine Metodo...");
	}

	// TestInsertCompagnia====================00
	private static void testInsertCompagnia(CompagniaDAO compagniaDAO) throws Exception {

		System.out.println("\tInizio Test InsertCompagnia");
		List<Compagnia> elementiAttuali = compagniaDAO.list();
		System.out.println("attualmente sono: " + elementiAttuali.size());
		Compagnia daInserireCompagnia = new Compagnia("Alianz", 500000,
				new SimpleDateFormat("dd/MM/yyyy").parse("05/09/2000"));
		System.out.println(compagniaDAO.insert(daInserireCompagnia));
		for (Compagnia compagniaItem : compagniaDAO.list()) {
			System.out.println(compagniaItem.toString());
		}
		System.out.println("...Fine Metodo...");
	}

	// TestDeleteCompagnia===========================
	private static void testDeleteCompagnia(CompagniaDAO compagniaDAO) throws Exception {

		System.out.println("\tInizio Test DeleteCompagnia");
		List<Compagnia> elementiAttuali = compagniaDAO.list();
		System.out.println("attualmente sono: " + elementiAttuali.size());
		for (Compagnia compagniaItem : elementiAttuali) {
			System.out.println(compagniaItem.toString());
		}
		Compagnia daEliminareCompagnia = new Compagnia(6l, null, 0, null);
		int conferma = compagniaDAO.delete(daEliminareCompagnia);
		System.out.println(conferma);
		if (conferma == 0) {
			System.out.println("elemento non trovato");
		}
		
		for (Compagnia compagniaitem : compagniaDAO.list()) {
			System.out.println(compagniaitem.toString());
		}

		System.out.println("...Fine Metodo...");
	}
}
