package Game.Shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import Game.Shop.model.ClientAccount;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class ClientDAOImpl implements ClientDAO {

    private JdbcTemplate jdbcTemplate;

    public ClientDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveOrUpdate(ClientAccount client) {
        if (client.getId()!=null) {
            // update
            String sql = "UPDATE clients SET username=?, pw=?, firstName=?, lastName=?, email=?, phoneNumber=?, country=?, address=?, likedGenre1=?, likedGenre2=? "
                    + "WHERE client_id=?";
            jdbcTemplate.update(sql, client.getUsername(), client.getPassword(), client.getFirstName(), client.getLastName(),
                    client.getEmail(), client.getPhoneNumber(),
                    client.getCountry(), client.getAddress(), client.getLikedGenre1(), client.getLikedGenre2(), client.getId());
        } else {
            // insert
            String sql = "INSERT INTO clients (client_id, username, pw, firstName, lastName, email, phoneNumber, country, address, likedGenre1, likedGenre2)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, UUID.randomUUID().toString(),client.getUsername(), client.getPassword(),
                    client.getFirstName(), client.getLastName(), client.getEmail(), client.getPhoneNumber(),
                    client.getCountry(), client.getAddress(), client.getLikedGenre1(),
                    client.getLikedGenre2());
        }

    }

    @Override
    public void delete(String clientId) {
        String sql = "DELETE FROM clients WHERE client_id=?";
        jdbcTemplate.update(sql, clientId);
    }

    @Override
    public List<ClientAccount> list() {
        String sql = "SELECT * FROM clients";
        List<ClientAccount> listClients = jdbcTemplate.query(sql, new RowMapper<ClientAccount>() {

            @Override
            public ClientAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
                ClientAccount aClient = new ClientAccount();

                aClient.setId(rs.getString("client_id"));
                aClient.setUsername(rs.getString("username"));
                aClient.setPassword(rs.getString("pw"));
                aClient.setFirstName(rs.getString("firstName"));
                aClient.setLastName(rs.getString("lastName"));
                aClient.setEmail(rs.getString("email"));
                aClient.setPhoneNumber(rs.getString("phoneNumber"));
                aClient.setCountry(rs.getString("country"));
                aClient.setAddress(rs.getString("address"));
                aClient.setLikedGenre1(rs.getString("likedGenre1"));
                aClient.setLikedGenre2(rs.getString("likedGenre2"));
                return aClient;
            }

        });

        return listClients;
    }

    @Override
    public ClientAccount get(String clientId) {
        String sql = "SELECT * FROM clients WHERE client_id= " + "'"+clientId+"'";
        return jdbcTemplate.query(sql, new ResultSetExtractor<ClientAccount>() {

            @Override
            public ClientAccount extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    ClientAccount client = new ClientAccount();
                    client.setId(rs.getString("client_id"));
                    client.setUsername(rs.getString("username"));
                    client.setPassword(rs.getString("pw"));
                    client.setFirstName(rs.getString("firstName"));
                    client.setLastName(rs.getString("lastName"));
                    client.setEmail(rs.getString("email"));
                    client.setPhoneNumber(rs.getString("phoneNumber"));
                    client.setCountry(rs.getString("country"));
                    client.setAddress(rs.getString("address"));
                    client.setLikedGenre1(rs.getString("likedGenre1"));
                    client.setLikedGenre2(rs.getString("likedGenre2"));
                    return client;
                }

                return null;
            }

        });
    }

    @Override
    public ClientAccount getUsernamePassword(String username, String password) {
        String sql = "SELECT * FROM clients WHERE username= "+"'"+username+"' and pw="+"'"+password+"';";
        return jdbcTemplate.query(sql, new ResultSetExtractor<ClientAccount>() {
            @Override
            public ClientAccount extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    ClientAccount client = new ClientAccount();
                    client.setId(rs.getString("client_id"));
                    client.setUsername(rs.getString("username"));
                    client.setPassword(rs.getString("pw"));
                    client.setFirstName(rs.getString("firstName"));
                    client.setLastName(rs.getString("lastName"));
                    client.setEmail(rs.getString("email"));
                    client.setPhoneNumber(rs.getString("phoneNumber"));
                    client.setCountry(rs.getString("country"));
                    client.setAddress(rs.getString("address"));
                    client.setLikedGenre1(rs.getString("likedGenre1"));
                    client.setLikedGenre2(rs.getString("likedGenre2"));

                    return client;
                }

                return null;
            }

        });
    }

}