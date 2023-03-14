package ma.enset.untiled4.dao.implementation;

import ma.enset.untiled4.dao.CategoryRepository;
import ma.enset.untiled4.dao.connectiondb.ConnectionDBSingleton;

import ma.enset.untiled4.dao.entities.Category;
import ma.enset.untiled4.dao.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {

    @Override
    public Category findById(Long id) {
        Connection connection = ConnectionDBSingleton.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sqlQuery = "SELECT * FROM CATEGORY WHERE id = ?";
        Category category = null;
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setLong(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                category = new Category(
                        resultSet.getLong("id"),
                        resultSet.getString("name")

                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }

    @Override
    public List<Category> findAll(){
        Connection connection = ConnectionDBSingleton.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sqlQuery = "SELECT * FROM category";
        List<Category> categories = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Category c = new Category();
                c.setId(resultSet.getLong("id"));
                c.setName(resultSet.getString("name"));
                categories.add(c);
                System.out.println("category name :"+resultSet.getString("name"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public Category save(Category category) {
        Connection connection = ConnectionDBSingleton.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sqlQuery = "INSERT INTO category(name) VALUES (?)";
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,category.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return category;
    }

    @Override
    public Category update(Category object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
