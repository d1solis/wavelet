import java.io.IOException;
import java.net.URI;

class StringHandler implements URLHandler {
    // The one bit of state on the server: a string that will be manipulated by
    // various requests.
    String serverString = "";

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Starting...");

        } else if (url.getPath().contains("/add-message")) {
            String[] val1 = url.getQuery().split("=");

            if(val1[0].equals("s")){
                serverString += "\n" + val1[1];
                return serverString;
            }
            else{
                return "404 Not Found!";
            }

        } else {
            System.out.println("Path: " + url.getPath());
            return "404 Not Found!";
        }
    }
}

class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new StringHandler());
    }
}