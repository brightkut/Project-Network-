package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

//นายดิศรณ์  ฐืติกรโกวิท 5810400990
public class Controller {
    Client client;
    String detail = "";
    @FXML
    String u = "";
    @FXML
    TextField textUser;


    @FXML
    TextArea textArea;



    @FXML
    TextField textMsg;

    @FXML
    public void total(ActionEvent event){
        int total = (20*client.getCola())+(40*client.getFrench())+(40*client.getChicken())+(60*client.getBurger());
        detail+="\nThe price is " +total + "baths.";
        textArea.setText(detail);
    }


    @FXML
    public void login(ActionEvent event) {
        User();
        client.sent("connect");
        u =textUser.getText();

    }

    @FXML
    public void send(ActionEvent event){
        client.sent(textMsg.getText());
        detail+="\nUser to server " + textMsg.getText();
        textArea.setText(detail);

    }
    @FXML
    public void cola(ActionEvent event){
        client.sent("Cola");
        detail+="\nUser to server :" + " Cola";
        textArea.setText(detail);

    }
    @FXML
    public void french(ActionEvent event){
        client.sent("French fries");
        detail+="\nUser to server :" + " French fries";
        textArea.setText(detail);

    }
    @FXML
    public void chicken(ActionEvent event){
        client.sent("Chicken");
        detail+="\nUser to server :" + " Chicken";
        textArea.setText(detail);

    }
    @FXML
    public void burger(ActionEvent event){
        client.sent("Burger");
        detail+="\nUser to server :" + " Burger";
        textArea.setText(detail);

    }
    @FXML
    public void menu(ActionEvent event){
        client.sent("Menu");
        detail+="\nUser to server :" + " Menu";
        textArea.setText(detail);

    }


    public void setTextArea() {
        textArea.setText("User "+ u +" is login");
        detail+="User "+ u +" is login";

    }
    public void setA(String t) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                detail += t;
                textArea.setText(detail);
            }
        });

    }





    public void User() {
        client = new Client(this);
        try {
            client.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
