package Game.Shop.dao;

import Game.Shop.model.ClientAccount;
import Game.Shop.model.Order;
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

public class GenreDAOImpl implements GenreDAO{

    private JdbcTemplate jdbcTemplate;

    public GenreDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Product> findRecommandation(String clientID) {
        String sql="Insert into orders (client_id,order_date) values(?, NOW());";

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


        String gamesGenre1 ="select * from product where genre="+"'"+gamer.getLikedGenre1()+"'"+";";

        List<Product> genres1 = jdbcTemplate.query(gamesGenre1, new RowMapper<Product>() {

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

        String gamesGenre2 ="select * from product where genre="+"'"+gamer.getLikedGenre2()+"'"+";";

        List<Product> genres2 = jdbcTemplate.query(gamesGenre2, new RowMapper<Product>() {

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

        Random rand = new Random();
        Random rand2 = new Random();

        int rand_genre1 = rand.nextInt(genres1.size());
        int rand_genre2 = rand2.nextInt(genres2.size());

        List<Product> recommandations = new ArrayList<Product>();
        recommandations.add(0, genres1.get(rand_genre1));
        recommandations.add(1, genres2.get(rand_genre2));

        return recommandations;
    }
}
