package lk.SMP.repository;

import lk.SMP.db.DbConnection;
import lk.SMP.model.Customer;
import lk.SMP.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepo {
    public static List<Supplier> getAll() throws SQLException {
        String sql = "SELECT * FROM supplier";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Supplier> supList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String tel = resultSet.getString(3);
            String address = resultSet.getString(4);

            Supplier supplier = new Supplier(id, name,tel,address);
            supList.add(supplier);
        }
        return supList;
    }
    public static List<String> getIds() throws SQLException {
        String sql = "SELECT supplierId FROM supplier";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> ids = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String SupplierId = resultSet.getNString(1);
            ids.add(SupplierId);
        }
        return ids;
    }

    /*public static boolean update (Supplier supplier) throws SQLException {
        String sql = "UPDATE supplier SET supplierName =?, address =?, contactNumber =? WHERE supplierId =?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1, supplier.getName());
        pstm.setString(2, supplier.getAddress());
        pstm.setString(3, supplier.getContactNumber());
        pstm.setString(4, supplier.getSupplierId());

        return pstm.executeUpdate() > 0;
    }*/

    public static boolean update2(String supplierId, String Name, String Contact, String Address) throws SQLException {
        String sql = "UPDATE supplier SET SupplierName =?, address =?, contactNumber =? WHERE supplierId =?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,Name);
        pstm.setObject(2,Address);
        pstm.setObject(3,Contact);
        pstm.setObject(4,supplierId);

        return pstm.executeUpdate() > 0;
    }
}
