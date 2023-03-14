package ma.enset.untiled4.dao.entities;

public class Product {
    private long id;
    private String name;
    private String reference;
    private float price;
    private Category category;

    public Product() {
    }

    public Product( String name, String reference, float price, Category category) {
        this.name = name;
        this.reference = reference;
        this.price = price;
        this.category = category;
    }

    public Product(long productId, String name, String ref, double price) {
    }

    public Product(long productId, String name, String ref, double price, Category category) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
