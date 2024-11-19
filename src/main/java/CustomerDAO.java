import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private final String url = "jdbc:postgresql://localhost:5432/postgress";
    private final String user = "postgres";
    private final String password = "1111";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customers(id, surname, name, fathername, address, phone_number, card_number, bonus_balance) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customer.getId());
            pstmt.setString(2, customer.getSurname());
            pstmt.setString(3, customer.getName());
            pstmt.setString(4, customer.getFathername());
            pstmt.setString(5, customer.getAddress());
            pstmt.setLong(6, customer.getPhoneNumber());
            pstmt.setLong(7, customer.getCardNumber());
            pstmt.setDouble(8, customer.getBonusBalance());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM customers WHERE id = ?";
        Customer customer = null;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                customer = new Customer(
                        rs.getInt("id"),
                        rs.getString("surname"),
                        rs.getString("name"),
                        rs.getString("fathername"),
                        rs.getString("address"),
                        rs.getLong("phone_number"),
                        rs.getLong("card_number"),
                        rs.getDouble("bonus_balance")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customer;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("id"),
                        rs.getString("surname"),
                        rs.getString("name"),
                        rs.getString("fathername"),
                        rs.getString("address"),
                        rs.getLong("phone_number"),
                        rs.getLong("card_number"),
                        rs.getDouble("bonus_balance")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customers;
    }

    public void updateCustomer(Customer customer) {
        String sql = "UPDATE customers SET surname = ?, name = ?, fathername = ?, address = ?, phone_number = ?, card_number = ?, bonus_balance = ? WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getSurname());
            pstmt.setString(2, customer.getName());
            pstmt.setString(3, customer.getFathername());
            pstmt.setString(4, customer.getAddress());
            pstmt.setLong(5, customer.getPhoneNumber());
            pstmt.setLong(6, customer.getCardNumber());
            pstmt.setDouble(7, customer.getBonusBalance());
            pstmt.setInt(8, customer.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteCustomer(int id) {
        String sql = "DELETE FROM customers WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
