package lk.SMP.repository;

import lk.SMP.db.DbConnection;
import lk.SMP.model.Harvest;
import lk.SMP.model.Salary;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaryRepo {

    public static boolean save(Salary salary) throws SQLException {
        String sql = "INSERT INTO salary VALUES(?,?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, salary.getSalaryId());
        pstm.setObject(2, salary.getAmount());
        pstm.setObject(3, salary.getPaymentDate());
        pstm.setObject(4, salary.getEmployeeId());


        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Salary salary) throws SQLException {
        String sql = "UPDATE salary SET amount = ?, employeeId = ? WHERE salaryId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, salary.getAmount());
        pstm.setObject(2, salary.getEmployeeId());
        pstm.setObject(3, salary.getSalaryId());


        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String salaryId) throws SQLException {
        String sql = "DELETE FROM salary WHERE salaryId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, salaryId);

        return pstm.executeUpdate() > 0;
    }

    public static List<Salary> getAll() throws SQLException {
        String sql = "SELECT * FROM salary";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Salary> salaryList = new ArrayList<>();

        while (resultSet.next()){
            String salaryId = resultSet.getString(1);
            String amount = resultSet.getString(2);
            Date paymentDate = resultSet.getDate(3);
            String employeeId = resultSet.getString(4);

            Salary salary = new Salary(salaryId,amount,paymentDate,employeeId);
            salaryList.add(salary);
        }
            return salaryList;
    }
}
