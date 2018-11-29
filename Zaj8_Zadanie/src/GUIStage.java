import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;

public class GUIStage extends Stage {

    public GUIStage(Connection connection){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 950, 275);
        this.setScene(scene);

        //Setting title to the scene
        this.setTitle("Database - books");

        Label showTableLabel = new Label("Show full table");
        grid.add(showTableLabel, 0, 0);
        Button showTableButton = new Button("Show");
        showTableButton.setOnAction(event -> {
            if(!DB.executeQuery(connection, "SELECT * FROM books",
                    new DBQueryFunction() {
                        @Override
                        public void execute(ResultSet rs) throws SQLException {
                            new TableStage(rs);
                        }
                    })){
                this.close();
            }
        });
        grid.add(showTableButton, 1, 0);

        Label searchAuthorLabel = new Label("Search author");
        grid.add(searchAuthorLabel, 0, 1);
        TextField searchAuthorTextField = new TextField();
        grid.add(searchAuthorTextField, 1, 1);
        Button searchAuthorButton = new Button("Show");
        searchAuthorButton.setOnAction(event -> {
            if(!DB.executeQuery(connection, "SELECT * FROM books WHERE author like \"%"+searchAuthorTextField.getText()+"%\"",
                    new DBQueryFunction() {
                        @Override
                        public void execute(ResultSet rs) throws SQLException {
                            new TableStage(rs);
                        }
                    })){
                this.close();
            }
        });
        grid.add(searchAuthorButton, 2, 1);

        Label searchISBNLabel = new Label("Search isbn");
        grid.add(searchISBNLabel, 0, 2);
        TextField searchISBNTextField = new TextField();
        grid.add(searchISBNTextField, 1, 2);
        Button searchISBNButton = new Button("Show");
        searchISBNButton.setOnAction(event -> {
            if(!DB.executeQuery(connection, "SELECT * FROM books WHERE isbn = '"+searchISBNTextField.getText()+"'",
                    new DBQueryFunction() {
                        @Override
                        public void execute(ResultSet rs) throws SQLException {
                            new TableStage(rs);
                        }
                    })){
                this.close();
            }
        });
        grid.add(searchISBNButton, 2, 2);


        //Add book
        Label addBookLabel = new Label("Add book:");
        grid.add(addBookLabel, 0, 4);

        Label isbnLabel = new Label("ISBN:");
        grid.add(isbnLabel, 0, 5);
        TextField isbnTextField = new TextField();
        grid.add(isbnTextField, 0, 6);
        Label titleLabel = new Label("Title:");
        grid.add(titleLabel, 1, 5);
        TextField titleTextField = new TextField();
        grid.add(titleTextField, 1, 6);
        Label authorLabel = new Label("Author:");
        grid.add(authorLabel, 2, 5);
        TextField authorTextField = new TextField();
        grid.add(authorTextField, 2, 6);
        Label yearLabel = new Label("Year:");
        grid.add(yearLabel, 3, 5);
        TextField yearTextField = new TextField();
        grid.add(yearTextField, 3, 6);


        Text successLabel = new Text("");
        successLabel.setFill(Color.GREEN);
        grid.add(successLabel, 0,7);

        Text errorLabel = new Text("");
        errorLabel.setFill(Color.FIREBRICK);
        grid.add(errorLabel, 3,7);

        Button addBookButton = new Button("Add book");
        addBookButton.setOnAction(event -> {
            try{
                Integer.parseInt(yearTextField.getText());
                errorLabel.setText("");
            }catch (Exception e){
                errorLabel.setText("Year has to be integer value");
                successLabel.setText("");
                return;
            }
            if(!DB.executeInput(connection,
                    new TableStage.Book( isbnTextField.getText(), titleTextField.getText(),
                            authorTextField.getText(), yearTextField.getText()))){
                successLabel.setText("");
                this.close();
            }
            successLabel.setText("Book successfully added");
        });
        grid.add(addBookButton, 4, 6);


        //Adding the scene to the stage
        this.setScene(scene);

        //Displaying the contents of a scene
        this.show();
    }


    public static void printTable(ResultSet rs) throws SQLException{
        ResultSetMetaData metaData = rs.getMetaData();
        for(int i=1; i<= metaData.getColumnCount(); ++i) {
            String name = metaData.getColumnLabel(i);
            System.out.print(name + " ");
        }
        System.out.print("\n");
        while(rs.next()){
            for(int i=1; i<= metaData.getColumnCount(); ++i) {
                String name = rs.getString(i);
                System.out.print(name + " ");
            }
            System.out.print("\n");
        }
    }

}
