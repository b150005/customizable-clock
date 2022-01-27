package com.b150005.customizableclock;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * JavaFX App
 */
public class CustomizableClockApplication extends Application {
    public static void main(String[] args) {
      launch();
    }

    @Override
    public void start(Stage stage) {
      // .fxmlファイルのロード
      try {
        URL url = CustomizableClockApplication.class.getResource("..\\customizableclock\\settings.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
      } catch (IOException e) {
        e.printStackTrace();
      }
  
      // 終了時の処理
      stage.showingProperty().addListener((observable, oldValue, newValue) -> {
        if (oldValue == true && newValue == false) {
          // 終了時に実行する処理
        }
      });
      
      // ウィンドウバーに表示するタイトル
      stage.setTitle("Settings");

      // ウィンドウの表示
      stage.show();
    }
}