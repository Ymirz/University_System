/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boola_university_management_system_main;

import static boola_university_management_system_main.MATRICULATED_USER_GUI.setTextLimit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Myles
 */
public class NON_MATRICULATED_USER_GUI {
    
    public TextField userSsnTf = new TextField();
    
    public TextField userFnameTf = new TextField();
    public TextField userLnameTf = new TextField();
    public TextField userMnameTf = new TextField();
    
    public TextField userStreetAddressTf = new TextField();
    
    public Label userSsnLb = new Label("SSN# ");
    public Label userFnameLb = new Label("First Name: ");
    public Label userLnameLb = new Label("Last Name: ");
    public Label userMnameLb = new Label("MI: ");
    public Label userStrtAddrLb = new Label("Street Address: ");
    public Label recordImmuneLb = new Label("Record Of Immunization ?");
    
    public Button clearBtn = new Button("Clear");
    public Button submitBtn = new Button("Submit");
    public Button cancelBtn = new Button("Cancel");
    
    public CheckBox recordImmunization = new CheckBox();
    
    public GridPane root = new GridPane();
    
    NON_MATRICULATED_USER_BACKEND userInfo;
    
    Stage primaryStage = new Stage();
    
    Scene scene;
    
    public NON_MATRICULATED_USER_GUI () {
        
        BOOLA_UNIVERSITY_MANAGEMENT_SYSTEM_MAIN main = new BOOLA_UNIVERSITY_MANAGEMENT_SYSTEM_MAIN();
        
        
         submitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    userInfo = new NON_MATRICULATED_USER_BACKEND(userSsnTf.getText(),userFnameTf.getText(), userLnameTf.getText(), userMnameTf.getText(), userStreetAddressTf.getText()
                            ,recordImmunization.isSelected());
                    primaryStage.close();
                    
                } catch (Exception ex) {
                    Logger.getLogger(NON_MATRICULATED_USER_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
         
        setTextLimit(userSsnTf, 8);
        
        setTextLimit(userMnameTf,1);
        
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(10,10,10,10));
                
        root.add(userSsnLb,0,0);
        root.add(userSsnTf,1,0);
        
        root.add(userFnameLb,0,1);
        root.add(userFnameTf,1,1);
    
        root.add(userLnameLb,2,1);
        root.add(userLnameTf,3,1);
    
        root.add(userMnameLb, 4, 1);
        root.add(userMnameTf, 5, 1);
        
        root.add(userStrtAddrLb,0,2);
        root.add(userStreetAddressTf,1,2);
        
        root.add(recordImmuneLb,0,3);
        root.add(recordImmunization,1,3);
        
        root.add(clearBtn, 0, 4);
        root.add(submitBtn, 1, 4);
        root.add(cancelBtn, 2, 4);
        
        scene = new Scene(root, 750, 640);
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("Boola Boola University");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
        public static void setTextLimit(TextField textField, int length) {
        textField.setOnKeyTyped(even -> {
        String string = textField.getText();
        
        if (string.length() > length) {
            textField.setText(string.substring(0, length));
            textField.positionCaret(string.length());
        }
        });
    }
}
