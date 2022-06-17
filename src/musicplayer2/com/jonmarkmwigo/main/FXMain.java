/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer2.com.jonmarkmwigo.main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import musicplayer2.FXMLController;

/**
 *
 * @author MWIGO-JON-MARK
 */
public class FXMain extends Application
{
    Parent root;
    Scene scene;
    @Override
    public void init() throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        scene = new Scene(root);
    }

    @Override
    public void start(Stage stage)
    {
        stage.setTitle("Audio Player");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    @Override
    public void stop()
    {
        if (FXMLController.mediaPlayer != null) {
            FXMLController.mediaPlayer.stop();
            FXMLController.mediaPlayer.dispose();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Application.launch(args);
    }

}
