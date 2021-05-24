package pl.lublin.wsei.java.cwiczenia.lab5;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class Pong1 extends Application {

    private static final double HEIGHT = 600;
    private static final double WIDTH = 800;
    private static final double MARGIN = 50;
    private static final double ARENAWIDTH = WIDTH - 2*MARGIN;
    private static final double ARENAHEIGHT = HEIGHT - 2*MARGIN;
    private static final double ARENAX1 = MARGIN;
    private static final double ARENAY1 = MARGIN;
    private static final double ARENAX2 = ARENAX1 + ARENAWIDTH;
    private static final double ARENAY2 = ARENAY1 + ARENAHEIGHT;

    private static final int LICZBAKULEK = 10;
    private Kulka[] kulki = new Kulka[LICZBAKULEK];



    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Timeline t = new Timeline(new KeyFrame(Duration.millis(100), e -> run(gc)));
        t.setCycleCount(Timeline.INDEFINITE);

        stage.setTitle("Kulki");
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();

        initKula();
        t.play();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void initKula(){
        Random lott = new Random();
        for (int i = 0; i < LICZBAKULEK/2; i++){
            kulki[i] = new Kulka(
                    lott.nextDouble() * ARENAWIDTH + ARENAX1,
                    lott.nextDouble() * ARENAHEIGHT + ARENAY1,
                    5 + lott.nextDouble() * 20,
                    5 + lott.nextDouble() * 20,
                    10,
                    Color.WHITESMOKE);
        }
        for (int i = LICZBAKULEK/2; i < LICZBAKULEK; i++){
            kulki[i] = new Rugby(
                    lott.nextDouble() * ARENAWIDTH + ARENAX1,
                    lott.nextDouble() * ARENAHEIGHT + ARENAY1,
                    5 + lott.nextDouble() * 20,
                    5 + lott.nextDouble() * 20,
                    10,
                    15,
                    2);
        }
    }

    private void run(GraphicsContext gc){
        gc.setFill(Color.BLACK);
        gc.fillRect(ARENAX1, ARENAY1, ARENAWIDTH, ARENAHEIGHT);

        for (int i = 0; i < LICZBAKULEK; i++){
            kulki[i].checkBoundaryCollision(ARENAX1, ARENAY1, ARENAX2, ARENAY2);

            kulki[i].update();

            kulki[i].draw(gc);
        }
    }
}