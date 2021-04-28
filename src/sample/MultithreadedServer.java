package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;

import org.json.simple.*;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.*;

public class MultithreadedServer extends Thread{

    private final Socket clientConnected; //Instantiate new Socket
    private static List<String> clientChannels = new ArrayList<String>(); //Instantiate new List of Channels
    private static HashMap<String, Integer> activeClientChannels = new HashMap<>(); //Instantiate new List of Channels
    private String clientName = "client";
    private static File clientFile;
    private PrintWriter newOutput; //Instantiate new PrintWriter
    private BufferedReader newReader; //Instantiate new BufferedReader

    String rID; //Instantiate new String
    String rClass; //Instantiate new String

    public MultithreadedServer(Socket socket) throws IOException //Client Handler constructor - passing in a socket and a client
    {
        this.clientConnected = socket; //refers to current object in method scope
    }
}


