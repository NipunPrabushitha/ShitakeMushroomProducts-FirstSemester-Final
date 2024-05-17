package lk.SMP.repository;

import lk.SMP.db.DbConnection;
import lk.SMP.model.Employee;
import lk.SMP.model.Harvest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo {
    public static List<String> getEmployeedName() throws SQLException {
        String sql = "SELECT name FROM employee";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    public static Employee searchById(String employeeId) throws SQLException {
        String sql = "SELECT * FROM employee WHERE employeeId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, employeeId);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String name = resultSet.getString(2);
            String contact = resultSet.getString(3);
            String fieldId = resultSet.getString(4);
            String userId = resultSet.getString(5);

            Employee employee = new Employee(employeeId,name,contact,fieldId,userId);

            return employee;
        }
        return null;
    }

    public static boolean save(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee VALUES(?,?,?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, employee.getEmployeeId());
        pstm.setObject(2, employee.getName());
        pstm.setObject(3, employee.getContactNumber());
        pstm.setObject(4, employee.getFieldId());
        pstm.setObject(5, employee.getUserId());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET name = ?, contactNumber = ?, fieldId = ?, userId =  ? WHERE employeeId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, employee.getName());
        pstm.setObject(2, employee.getContactNumber());
        pstm.setObject(3, employee.getFieldId());
        pstm.setObject(4, employee.getUserId());
        pstm.setObject(5, employee.getEmployeeId());

        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String employeeId) throws SQLException {
        String sql = "DELETE FROM employee WHERE employeeId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, employeeId);

        return pstm.executeUpdate() > 0;

    }

    public static List<Employee> getAll() throws SQLException {
        String sql = "SELECT * FROM employee";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Employee> employeeList = new ArrayList<>();

        while (resultSet.next()){
            String employeeId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String contactNumber = resultSet.getString(3);
            String fieldId = resultSet.getString(4);
            String userId = resultSet.getString(5);

            Employee employee = new Employee(employeeId,name,contactNumber,fieldId,userId);

            employeeList.add(employee);
        }
        return employeeList;
    }

    public static String getId(String employeeName) throws SQLException {
        String sql = "SELECT employeeId FROM employee WHERE name = ?";

        //SELECT name FROM customer WHERE customerId = 'C001';

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, employeeName);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            String employeeId = resultSet.getString(1);

            return employeeId;
        }
        return null;

    }
}
