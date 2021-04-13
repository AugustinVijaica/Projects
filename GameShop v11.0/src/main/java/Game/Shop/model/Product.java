package Game.Shop.model;

public class Product {
	private String id;
	private String name;
	private float price;
	private int quantity;
	private String genre;
	private String image;
	private String description;

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Product() {
	}

	public Product(String name, float price, int quantity) {

		this.name = name;
		this.price=price;
		this.quantity = quantity;

	}

	public String getId() {
		return id;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}
}
