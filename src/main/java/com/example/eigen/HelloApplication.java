package com.example.eigen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo.svg")));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Eigen Vector Visualizer");
        stage.getIcons().add(logo);
        stage.setResizable(true);
        stage.setScene(scene);
//        stage.setFullScreen(true);
//        stage.setOnCloseRequest((e)->{
//            System.out.println("closing window");
//        });
        stage.show();
    }
}
