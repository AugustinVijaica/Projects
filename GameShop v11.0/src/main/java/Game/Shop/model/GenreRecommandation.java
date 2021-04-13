package Game.Shop.model;

public class GenreRecommandation {

    private String client_id;
    private String product_id;

    public GenreRecommandation() {

    }

    public GenreRecommandation(String client_id, String product_id){
        this.client_id = client_id;
        this.product_id = product_id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
