package Game.Shop.dao;

import Game.Shop.model.ClientAccount;
import Game.Shop.model.Country;
import Game.Shop.model.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CountryDAOImpl implements CountryDAO {

    private JdbcTemplate jdbcTemplate;

    public CountryDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveOrUpdate(Country country) {

            // update
            // insert
            String sql = "INSERT INTO country (name, product_id, productName)"
                    + " VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, country.getName(), country.getProductId(), country.getProductName());

    }

    @Override
    public void delete(String name) {
        String sql = "DELETE FROM country WHERE name=?";
        jdbcTemplate.update(sql, name);
    }

    @Override
    public List<Country> list(String productId) {
        String sql = "SELECT * FROM country WHERE product_id =" + "'"+productId+"'";
        List<Country> listCountry = jdbcTemplate.query(sql, new RowMapper<Country>() {

            @Override
            public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
                Country aCountry = new Country();

                aCountry.setName(rs.getString("name"));
                aCountry.setProductId(rs.getString("product_id"));
                aCountry.setProductName(rs.getString("productName"));
                return aCountry;
            }

        });

        return listCountry;
    }

    @Override
    public Product findRecommandation(String clientID) {

        String getClient = "SELECT * FROM clients WHERE client_id= " + "'"+clientID+"'";

        ClientAccount gamer=jdbcTemplate.query(getClient, new ResultSetExtractor<ClientAccount>() {
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


        String countryName ="select * from country where name="+"'"+gamer.getCountry()+"'"+";";

        List<Country> country1 = jdbcTemplate.query(countryName, new RowMapper<Country>() {

            @Override
            public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
                Country aCountry = new Country();

                aCountry.setName(rs.getString("name"));
                aCountry.setProductId(rs.getString("product_id"));
                aCountry.setProductName(rs.getString("productName"));
                return aCountry;
            }

        });

        String theProduct ="select * from product where product_id="+"'"+country1.get(0).getProductId()+"'"+";";

        List<Product> product1 = jdbcTemplate.query(theProduct, new RowMapper<Product>() {

            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product aProduct = new Product();

                aProduct.setId(rs.getString("product_id"));
                aProduct.setName(rs.getString("name"));
                aProduct.setPrice((rs.getBigDecimal("price").floatValue()));
                aProduct.setQuantity(rs.getInt("quantity"));
                aProduct.setGenre(rs.getString("genre"));
                aProduct.setImage(rs.getString("image"));
                aProduct.setDescription(rs.getString("description"));
                return aProduct;
            }

        });

        return product1.get(0);
    }
}
