package it.prova.gestioneimpiegatojdbc.dao.compagnia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import it.prova.gestioneimpiegatojdbc.dao.AbstractMySQLDAO;
import it.prova.gestioneimpiegatojdbc.model.Compagnia;
import it.prova.gestioneimpiegatojdbc.model.Impiegato;

public class CompagniaDAOimp extends AbstractMySQLDAO implements CompagniaDAO {

	public CompagniaDAOimp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompagniaDAOimp(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Compagnia> list() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		ArrayList<Compagnia> result = new ArrayList<Compagnia>();
		Compagnia userTemp = null;

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from compagnia")) {

			while (rs.next()) {
				userTemp = new Compagnia();
				userTemp.setId(rs.getLong("ID"));
				userTemp.setRagioneSociale(rs.getString("ragionesociale"));
				userTemp.setFatturatoAnnuo(rs.getInt("fatturatoannuo"));
				userTemp.setDataFondazione(rs.getDate("datafondazione"));
				result.add(userTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Compagnia get(Long idInput) throws Exception {

		if (idInput < 1 || idInput == null) {
			throw new RuntimeException("inserire elemento valido");
		}
		if (isNotActive()) {
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		}

		Compagnia result = new Compagnia();
		try (PreparedStatement ps = connection.prepareStatement("select * from compagnia where id=?")) {
			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result.setId(rs.getLong("id"));
					result.setRagioneSociale(rs.getString("ragionesociale"));
					result.setFatturatoAnnuo(rs.getInt("fatturatoannuo"));
					result.setDataFondazione(rs.getDate("datafondazione"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int update(Compagnia input) throws Exception {
		
		return 0;
	}

	@Override
	public int insert(Compagnia input) throws Exception {
		
		if (input==null||input.getRagioneSociale()==null||input.getRagioneSociale().isBlank()||input.getFatturatoAnnuo()<1||input.getDataFondazione().before(new SimpleDateFormat("dd/MM/yyyy").parse("00/00/0000"))) {
			throw new RuntimeException("immettere un elemento valido");
		}
		if (isNotActive()) {
			throw new RuntimeException("non connesso");
		}
		
		int result=0;
		try (PreparedStatement preparedStatement=connection.prepareStatement("insert into compagnia(ragionesociale,fatturatoannuo,datafondazione) values (?,?,?) ")){
			preparedStatement.setString(1, input.getRagioneSociale());
			preparedStatement.setInt(2, input.getFatturatoAnnuo());
			preparedStatement.setDate(3, new java.sql.Date(input.getDataFondazione().getTime()));
			result=preparedStatement.executeUpdate();

		} catch (Exception e) {
		
			e.printStackTrace();
			throw e;
			
		}
		return result;
	}

	@Override
	public int delete(Compagnia input) throws Exception {
		
		if (input==null||input.getId()<1||input.getId()==null) {
			throw new RuntimeException("elemento inserito non valido");
		}
		if (isNotActive()) {
			throw new RuntimeException("connessione fallita");
		}
		
		int result=0;
		try (PreparedStatement preparedStatement=connection.prepareStatement("delete from compagnia where id=?")){
			preparedStatement.setLong(1, input.getId());
			result=preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Compagnia> findByExample(Compagnia input) throws Exception {
		
		if (isNotActive()) {
			throw new RuntimeException("connessione fallita");
		}
		String query="select * from compagnia where 1=1";
		
		if (!(input.getRagioneSociale()==null||input.getRagioneSociale().isBlank())) {
			query+=" and ragionesociale like '"+input.getRagioneSociale()+"%'";
		}
		if (!(input.getFatturatoAnnuo()<0)) {
			query+=" and fatturatoannuo>"+input.getFatturatoAnnuo();
		}
		/*if (!(input)) {
			
		}*/
		return null;
	}

	@Override
	public List<Impiegato> findAllByDataAssunzioneMaggioreDi(Date input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compagnia> findAllByRagioneSocialeContiene(String input) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
