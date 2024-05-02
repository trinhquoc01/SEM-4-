import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            getAllCustomer();
            //addCustomer(3,"Nguyen", "Nam", "nam1@gmail.com");
            //updateCustomer(1, "Trinh", "Vu", "nam10@gmail.com");
            //deleteCustomer(3);

        } catch (SQLException e) {
            System.out.println("Error occurred while fetching customers: " + e.getMessage());
        }
    }
    public static void getAllCustomer() throws SQLException {
        // gọi dt connection từng lớp
        Connection connection = MySQLConnectionDB.getMySQLConnection();
        // tạo statemnent để thực thi truy vấn
        Statement stm = connection.createStatement();
        // câu lênh truuy vấn cơ sở dữ liệu
        String query = "SELECT * FROM customers";
        //thực thi truy vấn
        ResultSet rs = stm.executeQuery(query);

        while (rs.next()) {
            System.out.println("===========");
            System.out.println("Customer id : " + rs.getInt(1));
            System.out.println("Fisrt name : " + rs.getString(2));
            System.out.println("Last name : " + rs.getString(3));
            System.out.println("email : " + rs.getString(4));
        }
        connection.close();

    }

    public static void addCustomer(Integer customerId , String firstName, String lastName, String email) throws SQLException {
        Connection connection = MySQLConnectionDB.getMySQLConnection();

        String query = "INSERT INTO customers (customer_id, first_name, last_name, email) VALUES (?, ?, ?, ?)";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, customerId);
        pstmt.setString(2, firstName);
        pstmt.setString(3, lastName);
        pstmt.setString(4, email);
        pstmt.executeUpdate();

        System.out.println("Customer added successfully.");
        connection.close();
    }

    public static void updateCustomer(int customerId, String firstName, String lastName, String email) throws SQLException {
        Connection connection = MySQLConnectionDB.getMySQLConnection();

        String query = "UPDATE customers SET first_name = ?, last_name = ?, email = ? WHERE customer_id = ?";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, firstName);
        pstmt.setString(2, lastName);
        pstmt.setString(3, email);
        pstmt.setInt(4, customerId);
        pstmt.executeUpdate();

        System.out.println("Customer updated successfully.");
        connection.close();
    }

    public static void deleteCustomer(int customerId) throws SQLException {
        Connection connection = MySQLConnectionDB.getMySQLConnection();

        String query = "DELETE FROM customers WHERE customer_id = ?";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, customerId);
        pstmt.executeUpdate();

        System.out.println("Customer deleted successfully.");
        connection.close();
    }

}

      