package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

import static sample.ImageHandler.*;
import static sample.ImageHandler.searchButtonImage;
import static sample.Main.*;

public class ObjectHandler {

    public static Button messageFeedButton(Stage stage) {

        Button btn = new Button();

        try {
            btn.setPrefHeight(50);
            btn.setPrefWidth(129);
            btn.setStyle("-fx-background-color: transparent;");
            btn.setGraphic(messagesButtonImage());
            btn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e)
                {
                    btn.setStyle("-fx-background-color: #4A708B;");;
                }
            });
            btn.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e)
                {
                    btn.setStyle("-fx-background-color: transparent;");;
                }
            });
            btn.setOnAction(e -> {
                try
                {
                    stage.setScene(messageFeedPage(stage));

                } catch (FileNotFoundException fileNotFoundException)
                {
                    fileNotFoundException.printStackTrace();
                }
            });

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return btn;
    }

    public static Button publishMessageButton(Stage stage) {

        Button btn = new Button();

        try {
            btn.setPrefHeight(50);
            btn.setPrefWidth(129);
            btn.setStyle("-fx-background-color: transparent;");
            btn.setGraphic(publishButtonImage());
            btn.setCursor(Cursor.HAND);
            btn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e)
                {
                    btn.setStyle("-fx-background-color: #4A708B;");;
                }
            });
            btn.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e)
                {
                    btn.setStyle("-fx-background-color: transparent;");;
                }
            });
            btn.setOnAction(e -> {
                try
                {
                    stage.setScene(publishMessagePage(stage));

                } catch (FileNotFoundException fileNotFoundException)
                {
                    fileNotFoundException.printStackTrace();
                }
            });

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return btn;
    }

    public static Button viewSubscribersButton(Stage stage) {

        Button btn = new Button();

        try {
            btn.setPrefHeight(50);
            btn.setPrefWidth(129);
            btn.setStyle("-fx-background-color: transparent;");
            btn.setGraphic(subscribersButtonImage());
            btn.setCursor(Cursor.HAND);
            btn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e)
                {
                    btn.setStyle("-fx-background-color: #4A708B;");;
                }
            });
            btn.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e)
                {
                    btn.setStyle("-fx-background-color: transparent;");;
                }
            });
            btn.setOnAction(e -> {
                try
                {
                    stage.setScene(viewSubscribersPage(stage));

                } catch (FileNotFoundException fileNotFoundException)
                {
                    fileNotFoundException.printStackTrace();
                }
            });

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return btn;
    }

    public static Button searchButton(Stage stage) {

        Button btn = new Button();

        try {
            btn.setPrefHeight(50);
            btn.setPrefWidth(129);
            btn.setStyle("-fx-background-color: transparent;");
            btn.setGraphic(searchButtonImage());
            btn.setCursor(Cursor.HAND);
            btn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e)
                {
                    btn.setStyle("-fx-background-color: #4A708B;");;
                }
            });
            btn.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e)
                {
                    btn.setStyle("-fx-background-color: transparent;");;
                }
            });
            btn.setOnAction(e -> {
                try
                {
                    stage.setScene(searchPage(stage, ""));

                } catch (FileNotFoundException fileNotFoundException)
                {
                    fileNotFoundException.printStackTrace();
                }
            });

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return btn;
    }

    public static Button universalButton (Button newBtn) throws FileNotFoundException
    {
        newBtn.setPrefHeight(50);
        newBtn.setPrefWidth(129);
        newBtn.setAlignment(Pos.CENTER);
        newBtn.setStyle("-fx-background-color: transparent;");
        newBtn.setGraphic(submitButtonImage());
        newBtn.setCursor(Cursor.HAND);
        newBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e)
            {
                newBtn.setStyle("-fx-background-color: #4A708B;");;
            }
        });
        newBtn.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e)
            {
                newBtn.setStyle("-fx-background-color: transparent;");
            }
        });
        return newBtn;
    }

    public static Button subscribeButton (Button newBtn) throws FileNotFoundException
    {
        newBtn.setPrefHeight(50);
        newBtn.setPrefWidth(129);
        newBtn.setAlignment(Pos.CENTER);
        newBtn.setStyle("-fx-background-color: transparent;");
        newBtn.setGraphic(subscribeButtonImage());
        newBtn.setCursor(Cursor.HAND);
        newBtn.setPadding(new Insets(10, 0, 20, 0)); //TOP RIGHT BOTTOM LEFT
        newBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e)
            {
                newBtn.setStyle("-fx-background-color: #4A708B;");;
            }
        });
        newBtn.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e)
            {
                newBtn.setStyle("-fx-background-color: transparent;");
            }
        });

        return newBtn;
    }

    public static Button unsubscribeButton (Button newBtn) throws FileNotFoundException
    {
        newBtn.setPrefHeight(50);
        newBtn.setPrefWidth(129);
        newBtn.setAlignment(Pos.CENTER);
        newBtn.setStyle("-fx-background-color: transparent;");
        newBtn.setGraphic(unsubscribeButtonImage());
        newBtn.setCursor(Cursor.HAND);
        newBtn.setPadding(new Insets(10, 0, 20, 0)); //TOP RIGHT BOTTOM LEFT
        newBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e)
            {
                newBtn.setStyle("-fx-background-color: #4A708B;");;
            }
        });
        newBtn.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e)
            {
                newBtn.setStyle("-fx-background-color: transparent;");
            }
        });

        return newBtn;
    }

    public static TextArea publishArea (Stage stage)
    {
        TextArea txt = new TextArea();
        final int MAX_CHARS = 1000 ;

        txt.setMinWidth(400);
        txt.setMinHeight(520);
        txt.setMaxWidth(400);
        txt.setMaxHeight(520);
        txt.setEffect(new DropShadow(8, Color.rgb(0, 0, 0, 0.8)));
        txt.setWrapText(true);
        txt.setPromptText("Enter Text Here");

        txt.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {

                    if (txt.getText().length() >= MAX_CHARS) {

                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Error");
                        alert.setHeaderText("Message Limit Reached");
                        alert.setContentText("You have reached the message limit (300 characters)");
                        alert.showAndWait();

                        txt.setText(txt.getText().substring(0, MAX_CHARS));
                    }
                }
            }
        });


        return txt;
    }

    public static VBox createMenu(Stage stage)
    {
        VBox navbar = new VBox();
        HBox buttonLayout = new HBox();

        try
        {
            buttonLayout.getChildren().add(messageFeedButton(stage));
            buttonLayout.getChildren().add(publishMessageButton(stage));
            buttonLayout.getChildren().add(viewSubscribersButton(stage));
            buttonLayout.getChildren().add(searchButton(stage));

            buttonLayout.setPadding(new Insets(5, 3, 5, 3));
            buttonLayout.setSpacing(5);

            navbar.getChildren().add(navbar());
            navbar.getChildren().add(buttonLayout);
            navbar.setSpacing(1);

        } catch (FileNotFoundException e)
        {
            System.out.println("File has not been found in directory");
            e.printStackTrace();
        }
        stage.setResizable(false);
        return navbar;
    }

    public static ScrollPane scrollPane(Stage stage)
    {
        ScrollPane pne = new ScrollPane();

        pne.setMinWidth(400);
        pne.setMinHeight(560);
        pne.setMaxWidth(400);
        pne.setMaxHeight(560);
        pne.setEffect(new DropShadow(8, Color.rgb(0, 0, 0, 0.8)));

        return pne;
    }

    public static TextField txtField(Stage stage)
    {
        TextField txt = new TextField();

        txt.setMinHeight(50);
        txt.setMinWidth(400);
        txt.setMaxHeight(50);
        txt.setMaxWidth(400);
        txt.setAlignment(Pos.CENTER);

        return txt;
    }


}
