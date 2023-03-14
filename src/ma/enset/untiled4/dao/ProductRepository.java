package ma.enset.untiled4.dao;

import ma.enset.untiled4.dao.entities.Product;

import java.util.List;

public interface ProductRepository extends DAO<Product, Long>{

    List<Product> findByQuery(String query);

}
