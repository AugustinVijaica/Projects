package Game.Shop.dao;

import java.util.List;

import Game.Shop.model.Product;


public interface ProductDAO {
	
	public void saveOrUpdate(Product product);
	
	public void delete(String productId);
	
	public Product get(String productId);
	
	public List<Product> list();
}
