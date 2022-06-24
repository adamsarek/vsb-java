package pl1.lab12;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

public class Stopwatch extends Application {

    Scene scene;
    VBox vBox;
    HBox hBox;
    Button sButton, rButton, pButton;
    Text text;
    Timeline timeline;
    boolean started = false;
    int mins = 0, secs = 0, millis = 0;

    List<String> times;

    void updateTime(Text text){

        if(millis == 1000) {
            secs++;
            millis = 0;
        }
        if(secs == 60) {
            mins++;
            secs = 0;
        }

        text.setText((((mins/10) == 0) ? "0" : "") + mins + ":"
                + (((secs/10) == 0) ? "0" : "") + secs + ":"
                + (((millis/10) == 0) ? "00" : (((millis/100) == 0) ? "0" : "")) + millis++);
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        text = new Text("00:00:000");

        timeline = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateTime(text);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);


        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (started) {
                    //change(text);
                    Thread.sleep(1);

                    Platform.runLater(() -> {   // Ensure data is updated on JavaFX thread
                        updateTime(text);
                    });
                }
                return null;
            }
        };

        final Runnable threadBody = new Runnable() {
            @Override
            public void run() {
                // kód prováděný vláknem
            }
        };

        //threadBody.
        //threadBody.run();
        //
        //
        //new Thread(threadBody).start();

        sButton = new Button("Start");
        sButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                started = true;
                //timeline.play();

                //new Thread(task).start();

                new Thread(){
                    @Override
                    public void run() {
                        while (started) {
                            //change(text);
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            Platform.runLater(() -> {   // Ensure data is updated on JavaFX thread
                                updateTime(text);
                            });
                        }
                    }
                }.start();
            }
        });

        pButton = new Button("Pause");
        pButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ///timeline.pause();
                started =false;
            }
        });

        rButton = new Button("Stop");
        rButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mins = 0;
                secs = 0;
                millis = 0;
                //timeline.pause();
                started = false;
                text.setText("00:00:000");
            }
        });

        hBox = new HBox(30);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(sButton, pButton, rButton);


        vBox = new VBox(30);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(text, hBox);

        scene = new Scene(vBox, 200, 150);
        primaryStage.setScene(scene);
        primaryStage.setTitle("stopwatch");
        primaryStage.show();





        //primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root, 800, 500));
        //primaryStage.show();
    }

    public synchronized void pause() {

        started = false;
        while (!started) {
            try {
                //th.wait();
                Thread.sleep(1);
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
            }
        }
        started = true;

        notifyAll();
    }

    public synchronized void startTh() {
        started = true;
        while (started) {
            try {
                //th.start();
                Thread.sleep(10);
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
            }
        }
        started = false;

        notifyAll();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
