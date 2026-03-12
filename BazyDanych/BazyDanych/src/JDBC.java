import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class JDBC {

    public void loadBooks(DefaultTableModel model) {

        model.setRowCount(0);

        try {
            Connection con = Baza.connect();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ksiazki");

            while (rs.next()) {

                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("tytul"),
                        rs.getString("autor"),
                        rs.getInt("rok_wydania")
                });

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addBook(String title, String author, int year) {

        try {
            Connection con = Baza.connect();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO ksiazki(tytul,autor,rok_wydania) VALUES(?,?,?)"
            );

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setInt(3, year);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteBook(int id) {

        try {
            Connection con = Baza.connect();

            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM ksiazki WHERE id=?"
            );

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateBook(int id, String title, String author, int year) {

        try {
            Connection con = Baza.connect();

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE ksiazki SET tytul=?, autor=?, rok_wydania=? WHERE id=?"
            );

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setInt(3, year);
            ps.setInt(4, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}