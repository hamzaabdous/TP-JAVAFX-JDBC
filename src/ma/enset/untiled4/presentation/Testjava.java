package ma.enset.untiled4.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ma.enset.untiled4.dao.connectiondb.ConnectionDBSingleton;
import ma.enset.untiled4.dao.entities.Category;
import ma.enset.untiled4.dao.entities.Product;
import ma.enset.untiled4.dao.implementation.CategoryRepositoryImpl;
import ma.enset.untiled4.dao.implementation.ProductRepositoryImpl;
import ma.enset.untiled4.service.CatalogService;
import ma.enset.untiled4.service.implementation.CatalogServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Testjava {


    public static void main(String[] args) {

        try {

            Connection connection = ConnectionDBSingleton.getConnection();
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            String sqlQuery = "SELECT * from PRODUCTS";
            List<Product> products = new ArrayList<>();
            try {

                preparedStatement = connection.prepareStatement(sqlQuery);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    //Display values
                    System.out.println("ID: " + resultSet.getLong("id"));
                    System.out.println("name: " + resultSet.getString("name"));
                    System.out.println("REFERENCE: " + resultSet.getString("REFERENCE"));
                    System.out.println("PRIX: " + resultSet.getFloat("PRIX"));
                    System.out.println("------------");
                    String test=resultSet.getString("name");
                    System.out.println("test :"+test);

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
