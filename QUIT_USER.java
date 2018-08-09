/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boola_university_management_system_main;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Myles
 */
public class QUIT_USER {
    
    public Button btnYes = new Button("Yes");
    
    public Button btnNo = new Button("No");
    
    public Label notifyLb = new Label("Are you sure you want to quit?");
    
    public Pane root = new Pane();
    
    public Scene scene;
    
    Stage primaryStage = new Stage();
    
    
    public QUIT_USER() {
        
        notifyLb.setLayoutX(180);
        notifyLb.setLayoutY(50);
        
        
        btnYes.setLayoutX(240);
        btnYes.setLayoutY(100);
        
        btnNo.setLayoutX(240);
        btnNo.setLayoutY(130);
        
        btnYes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
                System.exit(0);
                
            }
        });
        
        btnNo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               primaryStage.close();
                
            }
        });
        
        root.getChildren().addAll(notifyLb,btnYes, btnNo);
        
        scene = new Scene(root, 480, 320);
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("Boola Boola University - QUIT");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
