import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class OpenWindowController {
    public TableView dbTable;
    public TableColumn idC;
    public TableColumn lastC;
    public TableColumn firstC;
    public TableColumn stateC;
    public TableColumn cityC;
    public TextField fileSearch;



    public void initialize(){
        firstC.setCellValueFactory(new PropertyValueFactory("firstName"));

    }


    public void submitSearch(ActionEvent actionEvent) {

        try {
            DBHandler handler = new DBHandler();
            String pathDirectory = fileSearch.getText();
            System.out.println(pathDirectory);
            Scanner scanner = new Scanner(new File(pathDirectory));
            LinkedList<Person> rows = new LinkedList<Person>();
            while(scanner.hasNext()) {
                String str = scanner.next();

                String[] props = str.split(",");
                Person p = new Person(props[1], props[2], props[3], props[4]);
                rows.add(p);

            }
            ObservableList cells = FXCollections.observableArrayList();
            cells.addAll(rows);

            dbTable.setItems(cells);
            dbTable.refresh();
        }catch(Exception e){
            System.out.println("failed to find file");
        }

        fileSearch.clear();

    }
}
