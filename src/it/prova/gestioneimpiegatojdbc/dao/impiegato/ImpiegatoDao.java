package it.prova.gestioneimpiegatojdbc.dao.impiegato;

import java.util.Date;
import java.util.List;

import it.prova.gestioneimpiegatojdbc.dao.IBaseDAO;
import it.prova.gestioneimpiegatojdbc.model.Compagnia;
import it.prova.gestioneimpiegatojdbc.model.Impiegato;

public interface ImpiegatoDao extends IBaseDAO<Impiegato> {
	
	
	public List<Impiegato> findAllByCompagnia(Compagnia input);

	public int countByDataFondazioneCompagniaGreaterThan(Date input);

	public List<Impiegato> findAllErroriAssunzione();
}
