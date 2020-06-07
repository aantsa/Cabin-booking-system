
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mokkivarausjarjestelma;

import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author alisa
 */
public class Mokkivarausjarjestelma extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        //PostiDAO postidao = new PostiDAOImpl();
        //postidao.lueNumerotJaToimipaikat();   NAILLA LAITETAAN POSTINROT JA TOIMIPAIKAT KANTAAN
        //postidao.vieNumerotJaToimipaikatKantaan();

    }

}
