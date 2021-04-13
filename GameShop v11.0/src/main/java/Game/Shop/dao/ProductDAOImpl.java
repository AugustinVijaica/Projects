package Game.Shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import Game.Shop.model.Product;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;


public class ProductDAOImpl implements ProductDAO {

	private JdbcTemplate jdbcTemplate;
	
	public ProductDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveOrUpdate(Product product) {
		//System.out.println("["+product.getId()+"]");
		if (!product.getId().contentEquals("")) {
			// update
			String sql = "UPDATE product SET name=?, price=?, quantity=?, genre=?, image=?, description=?"
						+ "WHERE product_id=?";
			jdbcTemplate.update(sql, product.getName(),
					product.getPrice(), product.getQuantity(),product.getGenre(),product.getImage(),product.getDescription(), product.getId());
		} else {
			// insert
			String sql = "INSERT INTO product (product_id,name, price, quantity,genre,image,description)"
						+ " VALUES (?, ?, ?, ?,?,?,?)";
			jdbcTemplate.update(sql, UUID.randomUUID().toString(),product.getName(),
					product.getPrice(), product.getQuantity(),product.getGenre(),product.getImage(),product.getDescription());
		}
		
	}

	@Override
	public void delete(String productId) {
		String sql = "DELETE FROM product WHERE product_id=?";
		jdbcTemplate.update(sql, productId);
	}

	@Override
	public List<Product> list() {
		String sql = "SELECT * FROM product";
		List<Product> listProduct = jdbcTemplate.query(sql, new RowMapper<Product>() {

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
		
		return listProduct;
	}

	@Override
	public Product get(String productId) {
		String sql = "SELECT * FROM product WHERE product_id= " + "'"+productId+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Product>() {

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
	}

}
