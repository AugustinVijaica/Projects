package Game.Shop.dao;

import Game.Shop.model.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderDAOImpl implements OrderDAO {

    private JdbcTemplate jdbcTemplate;

    public OrderDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addOrder(String clientID,String produsID,int QuantityToBuy,int rating){

        String insertOrder="Insert into orders (client_id,order_date) values(?, ?);";

        String  updateQuantity="update product set quantity=? where product_id=?";

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

        String getGame="select * from product where product_id="+"'"+produsID+"'"+";";

        Product game=jdbcTemplate.query(getGame, new ResultSetExtractor<Product>() {

            @Override
            public Product extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getString("product_id"));
                    product.setName(rs.getString("name"));
                    product.setPrice((rs.getBigDecimal("price").floatValue()));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setGenre(rs.getString("genre"));
                    product.setImage(rs.getString("image"));
                    product.setDescription(rs.getString("description"));
                    return product;
                }

                return null;
            }

        });

        jdbcTemplate.update(insertOrder,gamer.getId(), LocalDate.now());

        String getOrder="select * from orders where client_id="+"'"+clientID+"' ORDER BY order_id DESC LIMIT 1;";

        Order o=jdbcTemplate.query(getOrder, new ResultSetExtractor<Order>() {
            @Override
            public Order extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    Order order=new Order();
                    order.setId(rs.getInt("order_id"));
                    order.setClientId(rs.getString("client_id"));
                    order.setDate(rs.getDate("order_date").toLocalDate());

                    return order;
                }
                return null;
            }
        });

        String insertItem="Insert into order_items (order_id,product_id,quantity,list_price,rating) values(?,?,?,?,?)";

        jdbcTemplate.update(insertItem,o.getId(),game.getId(),QuantityToBuy,game.getPrice()*QuantityToBuy,rating);

        jdbcTemplate.update(updateQuantity,game.getQuantity()-QuantityToBuy,produsID);

        String getBasket="select * from basket where client_id="+"'"+gamer.getId()+"' and product_id="+"'"+game.getId()+"';";

        ClientAccount c=jdbcTemplate.query(getBasket, new ResultSetExtractor<ClientAccount>() {
            @Override
            public ClientAccount extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()){
                    ClientAccount client=new ClientAccount();
                    client.setId(rs.getString("client_id"));
                    return client;
                }
                return null;
            }
        });

        String insertHistoric= "insert into historic values(?,?,?,?,?,?,?)";
        jdbcTemplate.update(insertHistoric,o.getId(),
                        gamer.getId(),gamer.getUsername(),
                        game.getId(),game.getName(),
                        QuantityToBuy,game.getPrice()*QuantityToBuy);

        if(c==null) {

            String insertBasket = "insert into basket values (?, ?, ?);";

            jdbcTemplate.update(insertBasket, gamer.getId(), game.getId(), rating);
        }

        else{
            String updateBasket= "update basket set rating=? where client_id=? and product_id=?";

            jdbcTemplate.update(updateBasket,rating,c.getId(),game.getId());
        }

    }

    @Override
    public List<OrderItem> list(String clientId) {
        String sql = "SELECT * FROM order_items, orders where orders.client_id="+"'"+clientId+"'and order_items.order_id=orders.order_id;";
        List<OrderItem> listItem = jdbcTemplate.query(sql, new RowMapper<OrderItem>() {

            @Override
            public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
                OrderItem anItem = new OrderItem();
                anItem.setOrderId(rs.getInt("order_id"));
                String getGame="select * from product where product_id="+"'"+rs.getString("product_id")+"'"+";";

                Product game=jdbcTemplate.query(getGame, new ResultSetExtractor<Product>() {

                    @Override
                    public Product extractData(ResultSet rs) throws SQLException,
                            DataAccessException {
                        if (rs.next()) {
                            Product product = new Product();
                            product.setId(rs.getString("product_id"));
                            product.setName(rs.getString("name"));
                            product.setPrice((rs.getBigDecimal("price").floatValue()));
                            product.setQuantity(rs.getInt("quantity"));
                            product.setGenre(rs.getString("genre"));
                            product.setImage(rs.getString("image"));
                            product.setDescription(rs.getString("description"));
                            return product;
                        }

                        return null;
                    }

                });
                anItem.setProductName(game.getName());
                anItem.setQuantity(rs.getInt("quantity"));
                anItem.setListPrice(rs.getBigDecimal("list_price").floatValue());
                return anItem;
            }

        });

        return listItem;
    }

    @Override
    public void delete(int orderId) {
        String incrementQuantity = "update product set quantity=((select quantity from product where product_id=" +
                "(select product_id from order_items where order_id=?)) + " +
                "(select quantity from order_items where order_id=?)) " +
                "where product_id=(select product_id from order_items where order_id=?)";
        jdbcTemplate.update(incrementQuantity,orderId,orderId,orderId);
        String deleteItem = "DELETE FROM order_items WHERE order_id=?";
        jdbcTemplate.update(deleteItem, orderId);
        String deleteOrder = "DELETE FROM orders WHERE order_id=?";
        jdbcTemplate.update(deleteOrder, orderId);

        String deleteHistoric = "DELETE FROM historic WHERE order_id=?";
        jdbcTemplate.update(deleteHistoric, orderId);

    }

    @Override
    public void deleteFinal(int orderId) {

        String deleteItem = "DELETE FROM order_items WHERE order_id=?";
        jdbcTemplate.update(deleteItem, orderId);

        String deleteOrder = "DELETE FROM orders WHERE order_id=?";
        jdbcTemplate.update(deleteOrder, orderId);

    }

    @Override
    public List<Product> findRecommandation(String clientId) {
            String allBasket = "select * from basket where client_id=" + "'" + clientId + "'" + ";";

            List<Basket> basketContents = jdbcTemplate.query(allBasket, new RowMapper<Basket>() {

                @Override
                public Basket mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Basket aBasket = new Basket();

                    aBasket.setProductId(rs.getString("product_id"));
                    aBasket.setRating(rs.getInt("rating"));
                    return aBasket;
                }

            });

            List<Product> wellRatedProducts = new ArrayList<Product>();

            for (int i = 0; i < basketContents.size(); i++) {
                if (basketContents.get(i).getRating() > 3) {
                    String sql = "SELECT * FROM product WHERE product_id= " + "'" + basketContents.get(i).getProductId() + "'";
                    Product wellRatedProduct = jdbcTemplate.query(sql, new ResultSetExtractor<Product>() {

                        @Override
                        public Product extractData(ResultSet rs) throws SQLException,
                                DataAccessException {
                            if (rs.next()) {
                                Product product = new Product();
                                product.setId(rs.getString("product_id"));
                                product.setName(rs.getString("name"));
                                product.setPrice((rs.getBigDecimal("price").floatValue()));
                                product.setQuantity(rs.getInt("quantity"));
                                product.setGenre(rs.getString("genre"));
                                product.setImage(rs.getString("image"));
                                product.setDescription(rs.getString("description"));
                                return product;
                            }

                            return null;
                        }

                    });
                    wellRatedProducts.add(wellRatedProduct);
                }
            }

            List<Product> allGenreProducts = new ArrayList<Product>();

            for (int i = 0; i < wellRatedProducts.size(); i++) {

                String ratedGames = "select * from product where genre=" + "'" + wellRatedProducts.get(i).getGenre() + "'" + ";";

                List<Product> genres = jdbcTemplate.query(ratedGames, new RowMapper<Product>() {

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

                for (int j = 0; j < genres.size(); j++) {
                    allGenreProducts.add(genres.get(j));
                }
            }

            int k = 0;
            //elimin din allGenre cele deja cumparate sau rate-uite
            for (int i = 0; i < allGenreProducts.size(); i++) {
                if (allGenreProducts.get(i).getName().contentEquals(wellRatedProducts.get(k).getName())) {
                    allGenreProducts.remove(allGenreProducts.get(i));
                    System.out.println("The size of well rated product: " + wellRatedProducts.size());
                    if(k == wellRatedProducts.size() - 1){
                        break;
                    }
                    k++;
                    i = 0;
                }
            }

            System.out.println("I am here");
            //acum am in allGenre doar ce imi trebuie

            //acum fac random pentru allGenre ca sa imi selectez numai ce trebuie
            Random rand = new Random();
            Random rand2 = new Random();

            if(allGenreProducts.size() < 1){
                return null;
            }
            int rand_rate1 = rand.nextInt(allGenreProducts.size());
            int rand_rate2 = rand2.nextInt(allGenreProducts.size());

            System.out.println("The size = " + allGenreProducts.size());
            while (rand_rate1 == rand_rate2) {
                rand_rate2 = rand2.nextInt(allGenreProducts.size());
            }

        System.out.println("I am here2");

            List<Product> recommandations = new ArrayList<Product>();
            recommandations.add(0, allGenreProducts.get(rand_rate1));
            recommandations.add(1, allGenreProducts.get(rand_rate2));

            return recommandations;
    }
}

