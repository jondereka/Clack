package CSDUO.clack.endpoint;

import CSDUO.clack.message.*;
import java.util.Scanner;

public class Client
{
    /**
     * Default port for connecting to server. This should be
     * a port listed as "unassigned" in
     * <a href="https://www.iana.org/assignments/service-names-port-numbers/service-names-port-numbers.txt">IANA</a>.
     */
    public static final int DEFAULT_SERVER_PORT = 12 ; //TODO: choose an unassigned port (NOT 0!!)

    /**
     *  The server to connect to if one is not explicitly given.
     */
    public static final String DEFAULT_SERVER_NAME = "localhost";

    private final String username;
    private final String serverName;
    private final int serverPort;
    //private final String saveDirectory;
    private Message messageToSent;
    private Message messageReceived;

    //TODO: JavaDoc
    /**
     * @param username is the name of the user
     * @param serverName is the name of the server that the user is connected to
     * @param serverPort is the port that the server is on
     */
    public Client(String username, String serverName, int serverPort) {
        this.username = username;
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    //TODO: JavaDoc
    /**
     * @param username is the name of the user
     * @param serverName is the name of the server that the user is connected to
     */
    public Client(String username, String serverName) {
        this(username, serverName, DEFAULT_SERVER_PORT);
    }

    //TODO: JavaDoc
    /**
     * @param username is the name of the user
     * @param serverPort is the port that the server is on
     */
    public Client(String username, int serverPort) {
        this(username, DEFAULT_SERVER_NAME, serverPort);
    }

    //TODO: JavaDoc
    /**
     * @param username is the name of the user
     */
    public Client(String username) {
        this(username, DEFAULT_SERVER_NAME, DEFAULT_SERVER_PORT);
    }

    /**
     * The client's REPL loop. Prompt for input, build
     * message from it, send message and receive/process
     * the reply, print info for user; repeat until
     * user enters "LOGOUT".
     */
    public void start() {
        //TODO: Implement this.
        Scanner input = new Scanner(System.in);
        Message messageToSend;
        //System.out.println("Client started.");

        while(true){
            System.out.print("Enter command: ");
            messageToSend = readUserInput();

            if(messageToSend == null){
                System.out.println("Empty input, please try again.");
            }

            messageReceived = messageToSend;
            printMessage();

            if (messageToSend instanceof LogoutMessage) {
                System.out.println("Client logged out.");
                break;
            }
        }
    }

    /**
     * Parse the line of user input and create the appropriate
     * message.
     * @return an object of the appropriate Message subclass.
     */
    public Message readUserInput() {
        //TODO: implement this.
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        //logout
        if (input.equalsIgnoreCase("LOGOUT")){
            return new LogoutMessage(username);
        }
        //list users
        else if (input.equalsIgnoreCase("LIST USERS")){
            return new ListUsersMessage(username);
        }
        //send file
        else if (input.startsWith("SEND FILE")) {
            String[] parts = input.split("\\s+");

            if(parts.length == 5 && "AS".equalsIgnoreCase(parts[3])) {
                String filePath1 = parts[2];
                String filePath2 = parts[4];
                return new FileMessage(username,filePath1,filePath2);
            }
        }
        //encryption on/off
        else if (input.startsWith("ENCRYPTION")){
            String[] parts = input.trim().split("\\s+");
            if (parts.length == 3 && ("ON".equalsIgnoreCase(parts[2]) || "OFF".equalsIgnoreCase(parts[2]))) {
                String option = parts[2].toUpperCase();
                // Handle encryption state change here (store the state)
                return new EncryptionOptionMessage(username, option);
            } else {
                System.out.println("Error: Invalid ENCRYPTION command format.");
                return null;
            }
        }
        //help
        else if (input.equalsIgnoreCase("HELP")) {
            System.out.println("Available commands:");
            System.out.println("LOGOUT - Disconnect from the server.");
            System.out.println("LIST USERS - Show connected users.");
            System.out.println("SEND FILE <filepath1> [AS <filepath2>] - Send a file.");
            System.out.println("ENCRYPTION KEY <key> - Set the encryption key.");
            System.out.println("ENCRYPTION ON/OFF - Turn encryption on or off.");
            return null; // No message to send to the server
        }
        // Handle empty input
        else if (input.isEmpty()) {
            return null; // No message to send
        }
        //default
        return new TextMessage(username, input);
    }

    /**
     * Print the current messageReceived object to System.out.
     * What is printed is the result of calling toString()
     * on the messageReceived object.
     */
    public void printMessage() {
        //TODO: implement this.
        if (messageReceived != null) {
            System.out.println("Received: " + messageReceived.toString());
        }
        else {
            System.out.println("No message received");
        }
    }

    //TODO: JavaDoc
    /**
     * @return the username
     */
    public String getUsername() {
        //TODO: implement this (return something other than null)
        return this.username;
    }

    //TODO: JavaDoc
    /**
     * @return the server name
     */
    public String getServerName() {
        //TODO: implement this (return something other than null)
        return this.serverName;
    }

    //TODO: JavaDoc
    /**
     * @return all the information about the client such as
     * default server name, default server port, username, server name
     * server port, message sent, and message received
     */
    public String toString() {
        return "{class=Client|"
                + "|DEFAULT_SERVER_NAME=" + DEFAULT_SERVER_NAME
                + "|DEFAULT_SERVER_PORT=" + DEFAULT_SERVER_PORT
                + "|username=" + this.username
                + "|serverName=" + this.serverName
                + "|serverPort=" + this.serverPort
                + "|messageToSend={" + this.messageToSent.toString() + "}"
                + "|messageReceived={" + this.messageReceived.toString() + "}"
                + "}";
    }
    // Test comment
}