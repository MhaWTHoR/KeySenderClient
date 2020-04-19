package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Main extends Application {

    public static String nickname = "";
    static DatagramChannel client = null;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("KeySender");
        primaryStage.setScene(new Scene(root, 500, 275));
        primaryStage.show();
        client = DatagramChannel.open();
        client.bind(null);



    }

    public static boolean validChar(char c) { //bunun olayı zaten mc de algılanan tuşların verisini sunucuya göndermemek
        String valids = "bcfghijklmnoprtuvyzx";
        //System.out.println(nickname);
        return (valids.contains(String.valueOf(c)));
    }


    public static void sendData(String msg) {


        System.out.println("gönderiliyor.");
        try {

            ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
            InetSocketAddress serverAddress = new InetSocketAddress("35.189.251.114",
                    1231);

            client.send(buffer, serverAddress);
            System.out.println(msg + " gönderildi.");
            buffer.clear();
            buffer.flip();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
