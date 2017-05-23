import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.File;
import java.util.ArrayList;
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

    }


    public void submitSearch(ActionEvent actionEvent) {

        try {
            DBHandler handler = new DBHandler();
            String pathDirectory = fileSearch.getText();
            System.out.println(pathDirectory);
            Scanner scanner = new Scanner(new File(pathDirectory));
            ArrayList rows = new ArrayList();
            while(scanner.hasNext()) {
                String str = scanner.next();

                System.out.println(str);
                rows.add(str);

            }
            
            dbTable.setItems(FXCollections.observableArrayList(rows));
        }catch(Exception e){
            System.out.println("failed to find file");
        }

        fileSearch.clear();

    }
}
