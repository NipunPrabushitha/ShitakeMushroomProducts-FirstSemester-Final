package lk.SMP.repository;

import lk.SMP.db.DbConnection;
import lk.SMP.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo {
    public static List<Customer> getAll() throws SQLException {
        String sql = "SELECT * FROM customer";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Customer> cusList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String tel = resultSet.getString(3);
            String address = resultSet.getString(4);

            Customer customer = new Customer(id, name,tel,address);
            cusList.add(customer);
        }
        return cusList;
    }
    public static List<String> getIds() throws SQLException {
        String sql = "SELECT customerId FROM customer";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> ids = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String CustomerId = resultSet.getNString(1);
            ids.add(CustomerId);
        }
        return ids;
    }

    public static boolean update (Customer customer) throws SQLException {
        String sql = "UPDATE customer SET name =?, contactNumber =?, address =? WHERE customerId =?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1, customer.getName());
        pstm.setString(2, customer.getContactNumber());
        pstm.setString(3, customer.getAddress());
        pstm.setString(4, customer.getCustomerId());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update2(String customerId, String Name, String Contact, String Address) throws SQLException {
        String sql = "UPDATE customer SET name =?, address =?, contactNumber =? WHERE customerId =?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,Name);
        pstm.setObject(2,Address);
        pstm.setObject(3,Contact);
        pstm.setObject(4,customerId);

        return pstm.executeUpdate() > 0;
    }


    public static Customer searchById(String id) {
        return null;
    }
}