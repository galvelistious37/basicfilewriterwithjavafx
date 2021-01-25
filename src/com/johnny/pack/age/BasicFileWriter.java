package com.johnny.pack.age;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import java.io.*;

public class BasicFileWriter extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    TextField txtTitle;
    TextField txtYear;
    TextField txtPrice;
    PrintWriter out;

    @Override
    public void start(Stage primaryStage){
        Label lblTitle = new Label();
        lblTitle.setPadding(new Insets(10, 10, 10, 10));
        lblTitle.setText("Enter Title: ");

        txtTitle = new TextField();
        txtTitle.setPadding(new Insets(10, 10, 10,10));
        txtTitle.setPromptText("Title");

        HBox paneTitle = new HBox(10);
        paneTitle.getChildren().addAll(lblTitle, txtTitle);

        Label lblYear = new Label();
        lblYear.setPadding(new Insets(10, 10, 10, 10));
        lblYear.setText("Enter Year: ");

        txtYear = new TextField();
        txtYear.setPadding(new Insets(10, 10, 10,10));
        txtYear.setPromptText("2021");

        HBox paneYear = new HBox(10);
        paneYear.getChildren().addAll(lblYear, txtYear);

        Label lblPrice = new Label();
        lblPrice.setPadding(new Insets(10, 10, 10, 10));
        lblPrice.setText("Enter Price: ");

        txtPrice = new TextField();
        txtPrice.setPadding(new Insets(10, 10, 10,10));
        txtPrice.setPromptText("24.95");

        HBox panePrice = new HBox();
        panePrice.getChildren().addAll(lblPrice, txtPrice);

        Button btnEnter = new Button("Enter");
        btnEnter.setPadding(new Insets(10, 10, 10, 10));
        btnEnter.setOnAction(e -> btnEnter_Clicked());

        Button btnQuit = new Button("Quit");
        btnQuit.setPadding(new Insets(10, 10, 10, 10));
        btnQuit.setOnAction(e -> btnQuit_Clicked());

        HBox paneButton = new HBox(10, btnEnter, btnQuit);

        VBox mainPane = new VBox(10);
        mainPane.getChildren().addAll(paneTitle, paneYear, panePrice, paneButton);

        Scene scene = new Scene(mainPane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Enter a Movie");
        primaryStage.show();
    }

    private void btnQuit_Clicked() {
        try{
            out.close();
            System.out.println("PrintWriter closed!");
        } catch (NullPointerException e){
            System.out.println("Nothing to close: " + e.getMessage());
        } finally{
            System.exit(0);
        }
    }

    private void btnEnter_Clicked() {
        Movie movie = createMovieObject();
        writeToFile(movie);
    }

    private void writeToFile(Movie movie) {
        try {
            getPrintWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println(movie.toString());
        System.out.println("Text has been added to the file");
    }

    private void getPrintWriter() throws IOException {
        File file = new File(Constant.START_PATH);
        out =  new PrintWriter(
                new BufferedWriter(
                        new FileWriter(file, true)
                )
        , true);
    }

    private Movie createMovieObject() {
        String title = txtTitle.getText();
        int year = Integer.parseInt(txtYear.getText());
        double price = Double.parseDouble(txtPrice.getText());
        return new Movie(title, year, price);
    }
}
