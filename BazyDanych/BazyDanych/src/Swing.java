import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Swing extends JFrame {

    JTextField title = new JTextField();
    JTextField author = new JTextField();
    JTextField year = new JTextField();

    JTable table;
    DefaultTableModel model;

    JDBC jdbc = new JDBC();

    public Swing() {

        setTitle("Ksiazki CRUD");
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Title");
        model.addColumn("Author");
        model.addColumn("Year");

        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(4,2));

        panel.add(new JLabel("Title"));
        panel.add(title);

        panel.add(new JLabel("Author"));
        panel.add(author);

        panel.add(new JLabel("Year"));
        panel.add(year);

        JButton add = new JButton("Dodaj");
        JButton delete = new JButton("Usun");
        JButton update = new JButton("Aktualizuj");

        panel.add(add);
        panel.add(delete);

        add(panel, BorderLayout.NORTH);
        add(update, BorderLayout.SOUTH);

        add.addActionListener(e -> {
            jdbc.addBook(title.getText(), author.getText(), Integer.parseInt(year.getText()));
            jdbc.loadBooks(model);
        });

        delete.addActionListener(e -> {
            int row = table.getSelectedRow();
            int id = (int) model.getValueAt(row,0);

            jdbc.deleteBook(id);
            jdbc.loadBooks(model);
        });

        update.addActionListener(e -> {
            int row = table.getSelectedRow();
            int id = (int) model.getValueAt(row,0);

            jdbc.updateBook(
                    id,
                    title.getText(),
                    author.getText(),
                    Integer.parseInt(year.getText())
            );

            jdbc.loadBooks(model);
        });

        jdbc.loadBooks(model);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Swing();
    }
}