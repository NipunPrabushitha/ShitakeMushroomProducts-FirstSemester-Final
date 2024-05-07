package lk.SMP.repository;

import lk.SMP.db.DbConnection;
import lk.SMP.model.Harvest;
import lk.SMP.model.OrderDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HarvestRepo {

    public static boolean save(Harvest harvest) throws SQLException {
        String sql = "INSERT INTO harvest VALUES(?, ?, ?, ?,?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, harvest .getHarvestId() );
        pstm.setObject(2, harvest.getCropType());
        pstm.setObject(3, harvest .getQuantity());
        pstm.setObject(4, harvest .getDate());
        pstm.setObject(5, harvest .getFieldId() );
        pstm.setObject(6, harvest .getUnitPrice());
        pstm.setObject(7, harvest .getWaste());

        return pstm.executeUpdate() > 0;
    }
    public static List<String> getfieldName() throws SQLException {
        String sql = "SELECT cropType FROM field";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
    public static String getfieldName(String harvestId) throws SQLException {
        String sql = "SELECT cropType FROM field WHERE fieldId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, harvestId);
        ResultSet resultSet = pstm.executeQuery();

        String cropType = null;
        while (resultSet.next()) {
            cropType = (resultSet.getString(1));
        }
        return cropType;
    }

    public static String getId(String fieldName) throws SQLException {
        String sql = "SELECT fieldId FROM field WHERE cropType = ?";

        //SELECT name FROM customer WHERE customerId = 'C001';

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, fieldName);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            String fieldId = resultSet.getString(1);

            return fieldId;
        }
        return null;
    }

    public static boolean delete(String harvestId) throws SQLException {
        String sql = "DELETE FROM harvest WHERE harvestId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, harvestId);

        return pstm.executeUpdate() > 0;
    }
    public static Harvest searchById(String harvestid) throws SQLException {
        String sql = "SELECT * FROM harvest WHERE harvestId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, harvestid);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String harvestId = resultSet.getString(1);
            String cropType = resultSet.getString(2);
            double quantity = Double.parseDouble(resultSet.getString(3));
            Date date = Date.valueOf(resultSet.getString(4));
            String fieldId = resultSet.getString(5);
            double unitPrice = Double.parseDouble(resultSet.getString(6));
            double waste = Double.parseDouble(resultSet.getString(7));


            Harvest harvest = new Harvest(harvestId,cropType,quantity,date,fieldId,unitPrice,waste );

            return harvest;
        }

        return null;
    }

    public static boolean update(Harvest harvest) throws SQLException {
        String sql = "UPDATE harvest SET cropType = ?, quantity = ?, fieldId = ?, unitPrice_200g =  ?, waste = ?   WHERE harvestId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, harvest.getCropType());
        pstm.setObject(2, harvest.getQuantity());
       // pstm.setObject(3, harvest.getDate());
        pstm.setObject(3, harvest.getFieldId());
        pstm.setObject(4, harvest.getUnitPrice());
        pstm.setObject(5, harvest.getWaste());
        pstm.setObject(6, harvest.getHarvestId());


        return pstm.executeUpdate() > 0;
    }
    public static List<Harvest> getAll() throws SQLException {
        String sql = "SELECT * FROM harvest";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Harvest> harvestList = new ArrayList<>();

        while (resultSet.next()){
            String harvestId = resultSet.getString(1);
            String cropType = resultSet.getString(2);
            double quantity = resultSet.getDouble(3);
            Date date = resultSet.getDate(4);
            String fieldId = resultSet.getString(5);
            double unitPrice = resultSet.getDouble(6);
            double waste = resultSet.getDouble(7);

            Harvest harvest = new Harvest(harvestId,cropType,quantity,date,fieldId,unitPrice,waste);
            harvestList.add(harvest);

        }

        return harvestList;
    }

    public static List<String> getIds() {
        return null;
    }


    public static boolean update(List<OrderDetail> odList) throws SQLException {
        for (OrderDetail od : odList) {
            boolean isUpdateQty = updateQty(od.getHarvestId(), od.getQty());
            if(!isUpdateQty) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(String itemCode, int qty) throws SQLException {
        String sql = "UPDATE harvest SET unitPrice_200g = unitPrice_200g - ? WHERE harvestId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setInt(1, qty);
        pstm.setString(2, itemCode);

        return pstm.executeUpdate() > 0;
    }

    public static List<String> getCodes() throws SQLException {
        String sql = "SELECT harvestId FROM harvest";
        ResultSet resultSet = DbConnection.getInstance()
                .getConnection()
                .prepareStatement(sql)
                .executeQuery();

        List<String> codeList = new ArrayList<>();
        while (resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;
    }
}
