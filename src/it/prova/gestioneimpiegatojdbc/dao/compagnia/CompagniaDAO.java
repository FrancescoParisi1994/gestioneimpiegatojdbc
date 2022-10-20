package it.prova.gestioneimpiegatojdbc.dao.compagnia;

import java.util.Date;
import java.util.List;

import it.prova.gestioneimpiegatojdbc.dao.IBaseDAO;
import it.prova.gestioneimpiegatojdbc.model.Compagnia;
import it.prova.gestioneimpiegatojdbc.model.Impiegato;

public interface CompagniaDAO extends IBaseDAO<Compagnia> {

	public List<Impiegato> findAllByDataAssunzioneMaggioreDi(Date input);

	public List<Compagnia> findAllByRagioneSocialeContiene(String input);
}
