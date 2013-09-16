/****************************************
/*        CS5223 - Assignment 1
/*      Apoorva Tyagi / Remi Pradal
/*
/*				Server.java
/* Main class for the Server program
/****************************************/

import java.net.*;
import java.io.*;
import java.util.*;


//Main class for the client program
//the syntax is Client server_address port
//the default server address is 127.0.0.1
//and the default port 9409
public class Client
{
    protected static String m_ipServer; //server ip address
    protected static int m_serverPort; //server port
       
    public static void main(String args[])
    {
        //we try read the parameters
        try
        {
            m_ipServer = args[0];
            m_serverPort = Integer.parseInt(args[1]);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            //We set default parameters
            m_ipServer = "127.0.0.1";
            m_serverPort = 9409;    
        }
        
        
        Socket socket;      //the socket for the server
        ObjectInputStream in;  //buffer to read server's messages
        
        //We contact the server
        try
        {
            socket = new Socket(InetAddress.getByName(m_ipServer),m_serverPort);
            in = new ObjectInputStream (socket.getInputStream());
            Object serverAnswer;
            
            //We read the server's answer, which the player ID
            try
            {
                serverAnswer = in.readObject();
            }
            catch (ClassNotFoundException e)
            {
               System.out.println("Impossible to read given ID");
               serverAnswer = (int)0;
            }
            System.out.println("Connected to Server. Identified with ID : " + serverAnswer);
                 
            socket.close();
        }
        catch (UnknownHostException e)
        {
            
        }
        catch (IOException e)
        {
            
        }
    }
}

