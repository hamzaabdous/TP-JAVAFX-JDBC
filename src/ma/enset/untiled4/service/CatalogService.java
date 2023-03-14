package ma.enset.untiled4.service;


import ma.enset.untiled4.dao.entities.Category;
import ma.enset.untiled4.dao.entities.Product;

import java.util.List;

public interface CatalogService {

    Product addProduct(Product product);
    Product updateProduct(Product product);
    List<Product> findAllProducts();
    List<Product> findProductsByQuery(String query);
    void deleteProduct(Long id);

    Category addCategory(Category category);
    Category updateCategory(Category category);
    List<Category> findAllCategories();
    void deleteCategory(Long id);

}
