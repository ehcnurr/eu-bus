
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionHistory {
    public static boolean saveTransaction(
            int busNum,
            String seatNum,
            String passengerName,
            String paymentMethod,
            String paymentProvider,
            String accountNumber) {

        String query = """
                INSERT INTO transaction_history
                (driver_username, bus_num, seat_num, passenger_name,
                 payment_method, payment_provider, account_number, amount_paid)
                SELECT username, ?, ?, ?, ?, ?, ?, 10.00
                FROM drivers
                WHERE selected_bus = ?
                LIMIT 1
                """;

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, busNum);
            stmt.setString(2, seatNum);
            stmt.setString(3, passengerName);
            stmt.setString(4, paymentMethod);
            stmt.setString(5, paymentProvider);
            stmt.setString(6, accountNumber);
            stmt.setInt(7, busNum);

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
