package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import static sample.ImageHandler.*;
import static sample.ObjectHandler.*;

public class Main extends Application {

    public static String username ="";
    public static String password ="";
    private static InputStream readIn = null;
    private static OutputStream writeOut = null;
    private static PrintStream printStream = null;
    private static BufferedReader buffReader = null;
    private static Socket serverSocket = null;
    int port = 12345;
    static String hostName = "localhost";
    private static final Font AppFont = (Font.font("Monospaced", 16));

    public static void main(String[] args)
    {
        try {

            serverSocket = new Socket(hostName, 12345);

            readIn = serverSocket.getInputStream();

            writeOut = serverSocket.getOutputStream();

            printStream = new PrintStream(writeOut);
            buffReader = new BufferedReader(new InputStreamReader(readIn));


        } catch (UnknownHostException e)
        {
            System.out.println("Unknown Host Connection");
            e.printStackTrace();
            serverSocket = null;
            readIn = null;
            writeOut = null;

        } catch (IOException e)
        {
            System.out.println("Input/Output error has been caught");
            e.printStackTrace();
            serverSocket = null;
            readIn = null;
            writeOut = null;
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException
    {

        if (serverSocket == null)
        {
            //primaryStage.setScene();
        }
        else
        {
            primaryStage.setScene(searchPage(primaryStage, ""));
        }

        primaryStage.setScene(loginPage(primaryStage));
        primaryStage.show();


    }

    public static Scene loginPage(Stage stage) throws FileNotFoundException
    {
        BorderPane landingPage = new BorderPane();
        Scene mainWindow = new Scene(landingPage, 600, 900);

        VBox loginBox = new VBox();
        HBox userName = new HBox();
        HBox password = new HBox();
        HBox submit = new HBox();
        Image welcomeImage;
        ImageView welcomeImageView;
        submit.setAlignment(Pos.CENTER);
        loginBox.setAlignment(Pos.CENTER);

        loginBox.setSpacing(20);
        loginBox.setPadding((new Insets(20, 100, 0, 100)));

        welcomeImage = new Image(new FileInputStream("images/Welcome.jpg"));
        welcomeImageView = new ImageView(welcomeImage);

        TextField userNameTF = txtField(stage);
        TextField passwordTF = txtField(stage);

        Button newBtn = new Button();
        newBtn = universalButton(newBtn);

        newBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (!userNameTF.getText().equals(""))
                {
                    setUsername(userNameTF.getText());
                    try
                    {
                        OpenRequestClass newOpenRequest = new OpenRequestClass(getUsername());
                        JSONObject newJSON = newOpenRequest.newJSONObj();
                        byte[] encode = Base64.getEncoder().encode(newJSON.toString().getBytes());
                        printStream.println(new String(encode));
                        byte[] decode = Base64.getDecoder().decode(buffReader.readLine());

                        String output = new String(decode);

                        JSONParser jsonParser = new JSONParser();
                        JSONObject jsonResult = (JSONObject) jsonParser.parse(output);

                        if(jsonResult.get("_class").equals("SuccessResponse"))
                        {
                            SubscribeRequestClass subRequest = new SubscribeRequestClass(newOpenRequest.getIdentity(), newOpenRequest.getIdentity());
                            JSONObject newJSON2 = subRequest.newJSONObj();
                            byte[] encode2 = Base64.getEncoder().encode(newJSON2.toString().getBytes());
                            printStream.println(new String(encode2));
                            byte[] decode2 = Base64.getDecoder().decode(buffReader.readLine());
                            String output2 = new String(decode2);
                            JSONParser jsonParser2 = new JSONParser();
                            JSONObject jsonResult2 = (JSONObject) jsonParser2.parse(output2);

                            if(jsonResult2.get("_class").equals("SuccessResponse"))
                            {
                                setUsername(username);
                                stage.setScene(messageFeedPage(stage));
                            }
                            else
                            {
                                System.out.println("Error Response Received 1");
                            }
                        }
                        else
                        {
                            System.out.println("Error Response Received 2");
                        }

                    } catch (IOException | ParseException e)
                    {
                        e.printStackTrace();

                    }
                }
            }
        });

        userNameTF.setPromptText("Enter Username...");
        passwordTF.setPromptText("Enter Password...");

        landingPage.setCenter(userNameTF);
        landingPage.setCenter(newBtn);

        userName.getChildren().addAll(userNameTF);
        password.getChildren().addAll(passwordTF);
        submit.getChildren().add(newBtn);
        loginBox.getChildren().addAll(userName, password, submit);

        landingPage.setTop(navbar());
        landingPage.setCenter(loginBox);
        landingPage.setBottom(welcomeImageView);
        landingPage.setBackground(backgroundImage());

        stage.setTitle("Dash - Login Page");
        stage.setResizable(false);
        return mainWindow;
    }


    public static Scene messageFeedPage(Stage stage) throws FileNotFoundException
    {
        GetRequestClass newGetRequest = new GetRequestClass(username, 0);

        List<MessageClass> newMessages = new ArrayList<>();

        try
        {
            JSONObject newJSON = newGetRequest.newJSONObj();
            byte[] encode = Base64.getEncoder().encode(newJSON.toString().getBytes());
            System.out.println(newJSON);
            printStream.println(new String(encode));
            byte[] decode = Base64.getDecoder().decode(buffReader.readLine());
            String buffResponsePublish = new String(decode);

            JSONParser newParser = new JSONParser();
            JSONObject newMessageObj = (JSONObject) newParser.parse(buffResponsePublish);
            System.out.println(newMessageObj);

            MessageListResponseClass newMessageList = new MessageListResponseClass(newMessageObj);

            newMessages = newMessageList.getMessages();


        } catch (IOException | ParseException e)
        {
            e.printStackTrace();
        }

        BorderPane landingPage = new BorderPane();
        Scene mainWindow = new Scene(landingPage, 600, 900);
        VBox messageFeedVBox = new VBox();

        ScrollPane newScrollPane = scrollPane(stage);
        VBox newMessageVBox = new VBox();

        if (newMessages != null)
        {
            Collections.reverse(newMessages);

            for (MessageClass newMessage : newMessages)
            {
                VBox messageVBox = new VBox();
                Label spacingLabel = new Label("------------------------");
                Label lineLabel = new Label("");
                Label newLabel1 = new Label(newMessage.getMessageTimestamp() + "");
                Label newLabel2 = new Label(newMessage.getMessageBody());
                Label newLabel3 = new Label(newMessage.getMessageRecipient());
                messageVBox.getChildren().addAll(newLabel3, newLabel2, newLabel1, spacingLabel, lineLabel);
                newMessageVBox.getChildren().add(messageVBox);

            }
        }

        newScrollPane.setContent(newMessageVBox);
        messageFeedVBox.getChildren().add(createMenu(stage));
        messageFeedVBox.setAlignment(Pos.CENTER);
        messageFeedVBox.getChildren().add(newScrollPane);

        landingPage.setTop(messageFeedVBox);
        landingPage.setBottom(footer());
        landingPage.setBackground(backgroundImage());

        stage.setTitle("Message Feed - Dash");
        stage.setResizable(false);
        return mainWindow;
    }

    public static Scene publishMessagePage(Stage stage) throws FileNotFoundException
    {
        BorderPane landingPage = new BorderPane();
        Scene mainWindow = new Scene(landingPage, 600, 900);
        VBox publishMessageVBox = new VBox();

        TextArea newTextArea = publishArea(stage);
        publishMessageVBox.getChildren().add(createMenu(stage));
        publishMessageVBox.setAlignment(Pos.CENTER);
        publishMessageVBox.getChildren().add(newTextArea);

        Button newBtn = new Button();
        newBtn = universalButton(newBtn);

        newBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                MessageClass newMessage = new MessageClass(username, newTextArea.getText());
                PublishRequestClass newPublishRequest = new PublishRequestClass(getUsername(), newMessage);

                try
                {
                    JSONObject newJSON = newPublishRequest.newJSONObj();
                    byte[] encode = Base64.getEncoder().encode(newJSON.toString().getBytes());
                    System.out.println(newJSON);
                    printStream.println(new String(encode));
                    byte[] decode = Base64.getDecoder().decode(buffReader.readLine());
                    String buffResponsePublish = new String(decode);

                    JSONParser newParser = new JSONParser();
                    JSONObject newPublishObj = (JSONObject) newParser.parse(buffResponsePublish);

                    if(newPublishObj.get("_class").equals("SuccessResponse"))
                    {
                        stage.setScene(messageFeedPage(stage));
                    }

                } catch (IOException | ParseException e)
                {
                    e.printStackTrace();
                }

            }
        });

        landingPage.setTop(publishMessageVBox);
        landingPage.setCenter(newBtn);
        landingPage.setBottom(footer());
        landingPage.setBackground(backgroundImage());
        stage.setTitle("Publish Message - Dash");
        stage.setResizable(false);
        return mainWindow;
    }

    public static Scene viewSubscribersPage(Stage stage) throws FileNotFoundException
    {
        BorderPane landingPage = new BorderPane();
        Scene mainWindow = new Scene(landingPage, 600, 900);
        VBox subsVBox = new VBox();

        TextField newTxtField = txtField(stage);
        txtField(stage).setPromptText("Find Channels to Subscribe to");
        TextField newTxtField2 = txtField(stage);
        txtField(stage).setPromptText("Find Channels to Unsubscribe to");
        subsVBox.setAlignment(Pos.CENTER);

        Button subBtn = new Button();
        subBtn = subscribeButton(subBtn);

        subBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent) {

                try {
                    SubscribeRequestClass subRequest = new SubscribeRequestClass(getUsername(), newTxtField.getText());
                    JSONObject newJSON2 = subRequest.newJSONObj();
                    byte[] encode2 = Base64.getEncoder().encode(newJSON2.toString().getBytes());
                    printStream.println(new String(encode2));
                    byte[] decode2 = Base64.getDecoder().decode(buffReader.readLine());
                    String output2 = new String(decode2);
                    JSONParser jsonParser2 = new JSONParser();
                    JSONObject jsonResult2 = (JSONObject) jsonParser2.parse(output2);

                    if(jsonResult2.get("_class").equals("SuccessResponse"))
                    {
                        stage.setScene(messageFeedPage(stage));
                    }
                    else
                    {
                        System.out.println("Error, No channel");
                    }
                } catch (IOException | ParseException e)
                {
                    e.printStackTrace();
                }


            }
        });

        Button unsubBtn = new Button();
        unsubBtn = unsubscribeButton(unsubBtn);

        unsubBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent) {

                try {
                    UnsubscribeRequestClass unsubRequest = new UnsubscribeRequestClass(getUsername(), newTxtField2.getText());
                    JSONObject newJSON2 = unsubRequest.newJSONObj();
                    byte[] encode2 = Base64.getEncoder().encode(newJSON2.toString().getBytes());
                    printStream.println(new String(encode2));
                    byte[] decode2 = Base64.getDecoder().decode(buffReader.readLine());
                    String output2 = new String(decode2);
                    JSONParser jsonParser2 = new JSONParser();
                    JSONObject jsonResult2 = (JSONObject) jsonParser2.parse(output2);

                    if(jsonResult2.get("_class").equals("SuccessResponse"))
                    {
                        stage.setScene(messageFeedPage(stage));
                    }
                    else
                    {
                        System.out.println("Error, No channel");
                    }
                } catch (IOException | ParseException e)
                {
                    e.printStackTrace();
                }

            }
        });

        subsVBox.getChildren().add(createMenu(stage));
        subsVBox.getChildren().add(newTxtField);
        subsVBox.getChildren().add(subBtn);
        subsVBox.getChildren().add(newTxtField2);
        subsVBox.getChildren().add(unsubBtn);

        landingPage.setTop(subsVBox);
        landingPage.setBottom(footer());
        landingPage.setBackground(backgroundImage());
        stage.setTitle("View Subscribers - Dash");
        stage.setResizable(false);
        return mainWindow;
    }

    public static Scene searchPage (Stage stage, String searchString) throws FileNotFoundException
    {
        GetRequestClass newGetRequest = new GetRequestClass(username, 0);

        List<MessageClass> newMessages = new ArrayList<>();

        try
        {
            JSONObject newJSON = newGetRequest.newJSONObj();
            byte[] encode = Base64.getEncoder().encode(newJSON.toString().getBytes());
            System.out.println(newJSON);
            printStream.println(new String(encode));
            byte[] decode = Base64.getDecoder().decode(buffReader.readLine());
            String buffResponsePublish = new String(decode);

            JSONParser newParser = new JSONParser();
            JSONObject newMessageObj = (JSONObject) newParser.parse(buffResponsePublish);
            System.out.println(newMessageObj);

            MessageListResponseClass newMessageList = new MessageListResponseClass(newMessageObj);

            newMessages = newMessageList.getMessages();


        } catch (IOException | ParseException e)
        {
            e.printStackTrace();
        }

        BorderPane landingPage = new BorderPane();
        Scene mainWindow = new Scene(landingPage, 600, 900);
        VBox searchVBox = new VBox();

        TextField searchBar = new TextField();
        searchBar.setMinWidth(400);
        searchBar.setMinHeight(50);
        searchBar.setMaxWidth(400);
        searchBar.setMaxHeight(50);
        searchBar.setPromptText("Search Messages...");
        searchBar.setAlignment(Pos.CENTER);

        ScrollPane pne = new ScrollPane();
        pne.setMinWidth(400);
        pne.setMinHeight(450);
        pne.setMaxWidth(400);
        pne.setMaxHeight(450);
        pne.setEffect(new DropShadow(8, Color.rgb(0, 0, 0, 0.8)));

        Button newBtn = new Button();
        newBtn = universalButton(newBtn);

        newBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (!searchBar.getText().equals(""))
                {
                    try
                    {
                        stage.setScene(searchPage(stage, searchBar.getText()));

                    } catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });

        VBox newMessageVBox = new VBox();

        if (newMessages != null)
        {
            Collections.reverse(newMessages);

            for (MessageClass newMessage : newMessages)
            {
                if (newMessage.getMessageBody().contains(searchString))
                {
                    VBox messageVBox = new VBox();
                    Label spacingLabel = new Label("------------------------");
                    Label lineLabel = new Label("");
                    Label newLabel1 = new Label(newMessage.getMessageTimestamp() + "");
                    Label newLabel2 = new Label(newMessage.getMessageBody());
                    Label newLabel3 = new Label(newMessage.getMessageRecipient());
                    messageVBox.getChildren().addAll(newLabel3, newLabel2, newLabel1, spacingLabel, lineLabel);
                    newMessageVBox.getChildren().add(messageVBox);
                }
            }
        }

        pne.setContent(newMessageVBox);

        searchVBox.getChildren().add(createMenu(stage));
        searchVBox.getChildren().add(searchBar);
        searchVBox.getChildren().add(newBtn);
        searchVBox.getChildren().add(pne);
        searchVBox.setAlignment(Pos.CENTER);

        landingPage.setTop(searchVBox);
        landingPage.setBottom(footer());
        landingPage.setBackground(backgroundImage());
        stage.setTitle("Search - Dash");
        stage.setResizable(false);
        return mainWindow;
    }

    public static Scene errorPage(Stage stage) throws FileNotFoundException
    {
        BorderPane landingPage = new BorderPane();
        Scene mainWindow = new Scene(landingPage, 600, 900);



        landingPage.setBottom(footer());
        landingPage.setBackground(backgroundImage());
        stage.setResizable(false);
        return mainWindow;
    }

    public static String getUsername()
    {
        return username;
    }

    public static void setUsername(String newUsername)
    {
        username = newUsername;
    }





}

