import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class TableStage extends Stage {

    public static class Book{
        private SimpleStringProperty isbn;
        private SimpleStringProperty title;
        private SimpleStringProperty author;
        private SimpleStringProperty year;

        public Book(String isbn, String title, String author, String year) {
            this.isbn = new SimpleStringProperty(isbn);
            this.title = new SimpleStringProperty(title);
            this.author = new SimpleStringProperty(author);
            this.year = new SimpleStringProperty(year);
        }

        public String getIsbn() {
            return isbn.get();
        }

        public String getTitle() {
            return title.get();
        }

        public String getAuthor() {
            return author.get();
        }

        public String getYear() {
            return year.get();
        }
    }

    private String[] bookProperties = {"isbn", "title", "author", "year"};

    private TableView<ObservableList> createTable(ResultSet rs) throws SQLException{
        TableView<ObservableList> table = new TableView<ObservableList>();
        table.setEditable(true);
        ObservableList<ObservableList> rows = FXCollections.observableArrayList();
        ResultSetMetaData metaData = rs.getMetaData();
        TableColumn[] columns = new TableColumn[metaData.getColumnCount()];
        for(int i=0; i < metaData.getColumnCount(); ++i) {
            final int j = i;
            columns[i] = new TableColumn(metaData.getColumnLabel(i + 1));
            columns[i].setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String> >() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });
        }
        while(rs.next()){
            ObservableList<String> row = FXCollections.observableArrayList();
            for(int i=0;i< metaData.getColumnCount(); ++i){
                row.add(rs.getString(i+1));
            }
            rows.add(row);
        }
        table.setItems(rows);
        table.getColumns().addAll(columns);

        return table;
    }

    public TableStage(ResultSet rs) throws SQLException{
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(0, 0, 0, 0));

        Scene scene = new Scene(grid, 700, 500);
        this.setScene(scene);

        //Setting title to the scene
        this.setTitle("Database - books");

        TableView<ObservableList> tableView = createTable(rs);
        tableView.setPrefWidth(scene.getWidth());
        tableView.setPrefHeight(scene.getHeight());

        grid.add(tableView, 0, 0);


        //Adding the scene to the stage
        this.setScene(scene);

        //Displaying the contents of a scene
        this.show();
    }

}
