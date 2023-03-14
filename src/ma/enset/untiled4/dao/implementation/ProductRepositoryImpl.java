package ma.enset.untiled4.dao.implementation;


import ma.enset.untiled4.dao.ProductRepository;
import ma.enset.untiled4.dao.connectiondb.ConnectionDBSingleton;
import ma.enset.untiled4.dao.entities.Category;
import ma.enset.untiled4.dao.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public Product findById(Long id) {
        Connection connection = ConnectionDBSingleton.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sqlQuery = "SELECT * FROM PRODUCT WHERE id = ?";
        Product product = null;
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setLong(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                product = new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("REFERENCE"),
                        resultSet.getDouble("PRIX")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(product == null){
            throw new RuntimeException("product not found!!");
        }

        return product;
    }

    @Override
    public List<Product> findAll() {
        Connection connection = ConnectionDBSingleton.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sqlQuery = "SELECT P.*, C.name as name_CAT FROM products P INNER JOIN CATEGORY C on P.`ID_CAT`=C.ID";
        List<Product> products = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Product p = new Product();
                p.setId(resultSet.getLong("id"));
                p.setName(resultSet.getString("name"));
                p.setReference(resultSet.getString("REFERENCE"));
                p.setPrice(resultSet.getFloat("PRIX"));
                Category c = new Category();
                c.setId(resultSet.getLong("ID_CAT"));
                c.setName(resultSet.getString("name_CAT"));
                p.setCategory(c);
                products.add(p);
            }
            System.out.println("products :"+products);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public Product save(Product product) {
        Connection connection = ConnectionDBSingleton.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sqlQuery = "INSERT INTO PRODUCTS(name,REFERENCE,PRIX,ID_CAT) VALUES (?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setString(2,product.getReference());
            preparedStatement.setDouble(3,product.getPrice());
            preparedStatement.setLong(4,product.getCategory().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return product;
    }

    @Override
    public Product update(Product product) {
        Connection connection = ConnectionDBSingleton.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sqlQuery = "UPDATE PRODUCTS set name = ?, reference = ?, prix = ?, ID_CAT = ? WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setString(2,product.getReference());
            preparedStatement.setDouble(3,product.getPrice());
            preparedStatement.setLong(4,product.getCategory().getId());
            preparedStatement.setLong(5,product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return product;
    }

    @Override
    public void delete(Long id) {
        Connection connection = ConnectionDBSingleton.getConnection();
        PreparedStatement preparedStatement;
        String sqlQuery = "DELETE FROM PRODUCTS WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findByQuery(String query) {
        Connection connection = ConnectionDBSingleton.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sqlQuery = "SELECT P.*, C.name as name_CAT FROM products P INNER JOIN CATEGORY C on P.`ID_CAT`=C.ID WHERE P.name like ? or P.reference like ? or P.prix like ? or C.name like ?";
        List<Product> products = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setString(1,"%"+query+"%");
            preparedStatement.setString(2,"%"+query+"%");
            preparedStatement.setString(3,"%"+query+"%");
            preparedStatement.setString(4,"%"+query+"%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Product p = new Product();
                p.setId(resultSet.getLong("id"));
                p.setName(resultSet.getString("name"));
                p.setReference(resultSet.getString("REFERENCE"));
                p.setPrice(resultSet.getFloat("PRIX"));
                Category c = new Category();
                c.setId(resultSet.getLong("ID_CAT"));
                c.setName(resultSet.getString("name_CAT"));
                p.setCategory(c);
                products.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}
