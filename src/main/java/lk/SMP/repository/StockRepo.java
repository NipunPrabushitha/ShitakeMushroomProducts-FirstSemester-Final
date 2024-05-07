package lk.SMP.repository;

import lk.SMP.db.DbConnection;
import lk.SMP.model.Stock;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockRepo {
    public static List<Stock> getAll() throws SQLException {
        String sql = "SELECT * FROM stock";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Stock> stockList = new ArrayList<>();

        while (resultSet.next()) {
            String productId = resultSet.getString(1);
            String name = resultSet.getString(2);
            double quantity = Double.parseDouble(resultSet.getString(3));
            String expireDate = resultSet.getString(4);
            double unitPrice= Double.parseDouble(resultSet.getString(5));

            Stock stock = new Stock(productId,name,quantity,expireDate,unitPrice);
            stockList.add(stock);
        }
        return stockList;
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

    public static boolean update2(String productId, double quantity, double unitPrice, String expiryDate, String name) throws SQLException {
        String sql = "UPDATE stock SET quantity =?, unitprice =?, expiryDate =?, name =? WHERE productId =?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,quantity);
        pstm.setObject(2,unitPrice);
        pstm.setObject(3,expiryDate);
        pstm.setObject(4,name);
        pstm.setObject(5,productId);

        return pstm.executeUpdate() > 0;
    }
}
