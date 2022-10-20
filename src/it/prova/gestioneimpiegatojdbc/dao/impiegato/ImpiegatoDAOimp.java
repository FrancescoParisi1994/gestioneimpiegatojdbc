package it.prova.gestioneimpiegatojdbc.dao.impiegato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.prova.gestioneimpiegatojdbc.dao.AbstractMySQLDAO;
import it.prova.gestioneimpiegatojdbc.model.Compagnia;
import it.prova.gestioneimpiegatojdbc.model.Impiegato;

public class ImpiegatoDAOimp extends AbstractMySQLDAO implements ImpiegatoDao{
	

	public ImpiegatoDAOimp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImpiegatoDAOimp(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Impiegato> list() throws Exception {
		
		if (isNotActive()) {
			throw new RuntimeException("connessione non abilitata");
		}
		 List<Impiegato> result=new ArrayList<>();
		 try (Statement s=connection.createStatement();ResultSet rs=s.executeQuery("select * from impiegato;")){
			while(rs.next()) {
				Impiegato tempImpiegato=new Impiegato();
				tempImpiegato.setId(rs.getLong("id"));
				tempImpiegato.setNome(rs.getString("nome"));
				tempImpiegato.setCognome(rs.getString("cognome"));
				tempImpiegato.setCodiceFiscale(rs.getString("codicefiscale"));
				tempImpiegato.setDataNascita(rs.getDate("datanascita"));
				tempImpiegato.setDataAssunzione(rs.getDate("dataassunzione"));
				
				Compagnia tempCompagnia=new Compagnia();
				tempCompagnia.setId(rs.getLong("id"));
				tempCompagnia.setRagioneSociale(rs.getString("ragionesociale"));
				tempCompagnia.setFatturatoAnnuo(rs.getInt("fatturatoannuo"));
				tempCompagnia.setDataFondazione(rs.getDate("datafondazione"));
				
				tempImpiegato.setCompagnia(tempCompagnia);
				result.add(tempImpiegato);
				}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public Impiegato get(Long idInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Impiegato input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Impiegato input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Impiegato input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Impiegato> findByExample(Impiegato input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Impiegato> findAllByCompagnia(Compagnia input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByDataFondazioneCompagniaGreaterThan(Date input) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Impiegato> findAllErroriAssunzione() {
		// TODO Auto-generated method stub
		return null;
	}

}
