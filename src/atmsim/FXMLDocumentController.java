/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package atmsim;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * FXML Controller class
 *
 * @author n06rin
 */
public class FXMLDocumentController implements Initializable {
    
    private String user;
    //private String customerPin = "1234";
    @FXML
    private Button backToBills;
    @FXML
    private Button goToMainScreen;
    @FXML
    private Label statusOfBill;
    @FXML
    private Button dollarBill;
    @FXML
    private Button translateToUSD;
    @FXML
    private Button translateToEUR;
    @FXML
    private Button translateToJPY;
    @FXML
    private Label USDcurrency;
    @FXML
    private Label EURcurrency;
    @FXML
    private Label JPYcurrency;
    @FXML
    private AnchorPane message;
    @FXML
    private Label messageText;
    @FXML
    private Button messageButton1;
    @FXML
    private Button messageButton2;
    @FXML
    private Button eurobill;
    @FXML
    private Button yenbill;
    @FXML
    private Label welcomeLabel;
    @FXML
    private AnchorPane profileScreen;
    @FXML
    private Button goToMainScreenFromProfileScreen;
    @FXML
    private Font x2;
    @FXML
    private Button translateRUBToUSD;
    @FXML
    private Button translateEURToRUB;
    @FXML
    private Button translateJPYToRUB;
    @FXML
    private Color x4;
    @FXML
    private Color x3;
    @FXML
    private AnchorPane outOfNet;

    @FXML
    private void backToBillsButton(ActionEvent event) {
        bills.setDisable(false);
        bills.setVisible(true);
        BillScreen.setDisable(true);
        BillScreen.setVisible(false);
        translateEURToRUB.setDisable(true);
        translateEURToRUB.setVisible(false);
        translateJPYToRUB.setDisable(true);
        translateJPYToRUB.setVisible(false);
        translateRUBToUSD.setDisable(true);
        translateRUBToUSD.setVisible(false);
        
    }

    @FXML
    private void goToMainScreenFromBills(ActionEvent event) {
        mainMenu.setDisable(false);
        mainMenu.setVisible(true);
        bills.setDisable(true);
        bills.setVisible(false);
    }

    @FXML
    private void goToDollarBill(ActionEvent event) {
        bills.setDisable(true);
        bills.setVisible(false);
        BillScreen.setDisable(false);
        BillScreen.setVisible(true);
        translateToUSD.setDisable(true);
        translateToUSD.setVisible(false);
        translateToEUR.setDisable(true);
        translateToEUR.setVisible(false);
        translateToJPY.setDisable(true);
        translateToJPY.setVisible(false);
        translateRUBToUSD.setDisable(false);
        translateRUBToUSD.setVisible(true);
        USDcurrency.setVisible(true);
        EURcurrency.setVisible(false);
        JPYcurrency.setVisible(false);
        
        //billname.setText(((Button)event.getTarget()).getText ());
        if ((((Button)event.getTarget()).getText ()).equals ("доллары")){
            billname.setText("Ваш долларовый счет");
            statusOfBill.setText("на вашем счету: " + customer.dollars + " долларов");
            
            
            
        }
        
    }
    
    @FXML
    private void goToRoubleBill(ActionEvent event) {
        bills.setDisable(true);
        bills.setVisible(false);
        BillScreen.setDisable(false);
        BillScreen.setVisible(true);
        translateToUSD.setDisable(false);
        translateToUSD.setVisible(true);
        translateToEUR.setDisable(false);
        translateToEUR.setVisible(true);
        translateToJPY.setDisable(false);
        translateToJPY.setVisible(true);
        USDcurrency.setVisible(true);
        EURcurrency.setVisible(true);
        JPYcurrency.setVisible(true);
        
        
        //billname.setText(((Button)event.getTarget()).getText ());
        if ((((Button)event.getTarget()).getText ()).equals ("рубли")){
            billname.setText("Ваш рублевый счет");
            statusOfBill.setText("на вашем счету: " + customer.roubles + " рублей");
            
            
        }
        
        
    }

    @FXML
    private void translateToUSDfunc(ActionEvent event) {
        System.out.println("Translate your roubles to USD");
        BillScreen.setDisable(true);
        //BillScreen.setVisible(false);
        message.setDisable(false);
        message.setVisible(true);
        messageText.setText("вы правда хотите перевести все деньги в доллары?");
    }

    @FXML
    private void translateToEURf(ActionEvent event) {
        System.out.println("Translate your roubles to EUR");
        BillScreen.setDisable(true);
        //BillScreen.setVisible(false);
        message.setDisable(false);
        message.setVisible(true);
        messageText.setText("вы правда хотите перевести все деньги в евро?");
    }

    @FXML
    private void translateToJPYf(ActionEvent event) {
        System.out.println("Translate your roubles to JPY");
        BillScreen.setDisable(true);
        //BillScreen.setVisible(false);
        message.setDisable(false);
        message.setVisible(true);
        messageText.setText("вы правда хотите перевести все деньги в иены?");
    }

    @FXML
    private void messageButton1f(ActionEvent event) {
        if (messageText.getText().contains("доллар")){
            customer.dollars+= customer.roubles / ReadXMLFile.currencyValue("USD");
            customer.roubles = (int) (customer.roubles % ReadXMLFile.currencyValue("USD"));
            message.setDisable(true);
            message.setVisible(false);
            BillScreen.setDisable(false);
            statusOfBill.setText("на вашем счету: " + customer.roubles + " рублей");
        }     
        
        if (messageText.getText().contains("евро")){
            customer.euro += customer.roubles / ReadXMLFile.currencyValue("EUR");
            customer.roubles = (int) (customer.roubles % ReadXMLFile.currencyValue("EUR"));
            message.setDisable(true);
            message.setVisible(false);
            BillScreen.setDisable(false);
            statusOfBill.setText("на вашем счету: " + customer.roubles + " рублей");
        }
        
        if (messageText.getText().contains("иены")){
            customer.yen += customer.roubles / ReadXMLFile.currencyValue("JPY");
            customer.roubles = (int) (customer.roubles % ReadXMLFile.currencyValue("JPY"));
            message.setDisable(true);
            message.setVisible(false);
            BillScreen.setDisable(false);
            statusOfBill.setText("на вашем счету: " + customer.roubles + " рублей");
        }
        if (messageText.getText().contains("доллары в рубли")){
            customer.roubles += customer.dollars * ReadXMLFile.currencyValue("USD");
            customer.dollars = 0;
            message.setDisable(true);
            message.setVisible(false);
            BillScreen.setDisable(false);
            statusOfBill.setText("на вашем счету: " + customer.dollars + " долларов");
        }
        if (messageText.getText().contains("евро в рубли")){
            customer.roubles += customer.euro * ReadXMLFile.currencyValue("EUR");
            customer.euro = 0;
            message.setDisable(true);
            message.setVisible(false);
            BillScreen.setDisable(false);
            statusOfBill.setText("на вашем счету: " + customer.euro + " евро");
        }
        if (messageText.getText().contains("иены в рубли")){
            customer.roubles += customer.yen * ReadXMLFile.currencyValue("JPY");
            customer.yen = 0;
            message.setDisable(true);
            message.setVisible(false);
            BillScreen.setDisable(false);
            statusOfBill.setText("на вашем счету: " + customer.yen + " иен");
        }
        
    }

    @FXML
    private void messageButton2(ActionEvent event) {
        message.setDisable(true);
        message.setVisible(false);
        BillScreen.setDisable(false);        
    }

    @FXML
    private void goToEurobill(ActionEvent event) {
        bills.setDisable(true);
        bills.setVisible(false);
        BillScreen.setDisable(false);
        BillScreen.setVisible(true);
        translateToUSD.setDisable(true);
        translateToUSD.setVisible(false);
        translateToEUR.setDisable(true);
        translateToEUR.setVisible(false);
        translateToJPY.setDisable(true);
        translateToJPY.setVisible(false);
        translateEURToRUB.setDisable(false);
        translateEURToRUB.setVisible(true);
        USDcurrency.setVisible(false);
        JPYcurrency.setVisible(false);
        EURcurrency.setVisible(true);
        
        
        //billname.setText(((Button)event.getTarget()).getText ());
        if ((((Button)event.getTarget()).getText ()).equals ("евро")){
            billname.setText("Ваш евро счет");
            statusOfBill.setText("на вашем счету: " + customer.euro + " евро");
            
            
            
        }
    }

    @FXML
    private void goToYenBill(ActionEvent event) {
        bills.setDisable(true);
        bills.setVisible(false);
        BillScreen.setDisable(false);
        BillScreen.setVisible(true);
        translateToUSD.setDisable(true);
        translateToUSD.setVisible(false);
        translateToEUR.setDisable(true);
        translateToEUR.setVisible(false);
        translateToJPY.setDisable(true);
        translateToJPY.setVisible(false);
        translateJPYToRUB.setDisable(false);
        translateJPYToRUB.setVisible(true);
        JPYcurrency.setVisible(true);
        USDcurrency.setVisible(false);
        EURcurrency.setVisible(false);
        
        //billname.setText(((Button)event.getTarget()).getText ());
        if ((((Button)event.getTarget()).getText ()).equals ("иена")){
            billname.setText("Ваш счет японских иен");
            statusOfBill.setText("на вашем счету: " + customer.yen + " иен");
            
            
            
        }
    }

    @FXML
    private void goToMainScreenFromProfileScreen(ActionEvent event) {
        mainMenu.setDisable(false);
        mainMenu.setVisible(true);
        profileScreen.setDisable(true);
        profileScreen.setVisible(false);
    }

    @FXML
    private void translateRUBToUSDfunc(ActionEvent event) {
        System.out.println("Translate your roubles to USD");
        BillScreen.setDisable(true);
        //BillScreen.setVisible(false);
        message.setDisable(false);
        message.setVisible(true);
        messageText.setText("вы правда хотите перевести все доллары в рубли?");
    }

    @FXML
    private void translateEURToRUBfunc(ActionEvent event) {
        System.out.println("Translate your roubles to USD");
        BillScreen.setDisable(true);
        //BillScreen.setVisible(false);
        message.setDisable(false);
        message.setVisible(true);
        messageText.setText("вы правда хотите перевести все евро в рубли?");
    }

    @FXML
    private void translateJPYToRUBfunc(ActionEvent event) {
        System.out.println("Translate your JPY to RUB");
        BillScreen.setDisable(true);
        //BillScreen.setVisible(false);
        message.setDisable(false);
        message.setVisible(true);
        messageText.setText("вы правда хотите перевести все иены в рубли?");
    }
    private class client {
        client (String a, String b, int r, int d, int e, int y) {
            name= a;
            pin = b;
            roubles = r;
            dollars = d;
            euro = e;
            yen = y;
            
        }
        String name;
        String pin;
        int roubles;
        int dollars;
        int euro;
        int yen;
        
    }
    
    client customer = new client ("Abstract User", "1234", 5000, 100, 70, 500);
    
    
    @FXML
    private AnchorPane pincodeScreen;
    @FXML
    private Button checkPin;
    @FXML
    private PasswordField pincodeField;
    @FXML
    private Label statusBar;
    @FXML
    private AnchorPane mainMenu;
    @FXML
    private Button profile;
    @FXML
    private Button billsButton;
    @FXML
    private Button IOCash;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5fired;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private Button button0;
    @FXML
    private AnchorPane bills;
    @FXML
    private Font x1;
    @FXML
    private Button RoubleBill;
    @FXML
    private Label billname;
    @FXML
    private AnchorPane BillScreen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void checkOwnerOfPin(ActionEvent event) {
        if (customer.pin.equals(pincodeField.getText())){
            
            System.out.println ("WELCOME " + customer.name);
            pincodeScreen.setDisable(true);
            pincodeScreen.setVisible(false);
            mainMenu.setDisable(false);
            mainMenu.setVisible(true);
                    }
        else {
            pincodeField.deleteText(0, pincodeField.getText().length());
            //statusBar.setText("Неправильный pin-код");
        }
    }

    @FXML
    private void whatpincode(ActionEvent event) {
    }

    @FXML
    private void goToProfile(ActionEvent event) {
        System.out.println ("go to the your profile");
        mainMenu.setDisable(true);
        mainMenu.setVisible(false);
        
        profileScreen.setDisable(false);
        profileScreen.setVisible(true);
        welcomeLabel.setText("Здаравствуйте, наш дорогой " + customer.name);
        
    }

    @FXML
    private void goToBills(ActionEvent event) {
        System.out.println ("go to bills page!");
        mainMenu.setDisable(true);
        mainMenu.setVisible(false);
        bills.setDisable(false);
        bills.setVisible(true);
        if (ReadXMLFile.currencyValue("USD") == -1) outOfNet.setVisible(true);
            USDcurrency.setText("Курс доллара сегодня: " + Double.toString(ReadXMLFile.currencyValue("USD")));
            EURcurrency.setText("Курс евро сегодня: " + Double.toString(ReadXMLFile.currencyValue("EUR")));
            JPYcurrency.setText("Курс японской иены сегодня: " + Double.toString(ReadXMLFile.currencyValue("JPY")));
        
    }

    @FXML
    private void goToIO(ActionEvent event) {
        System.out.println ("go to IO");
    }

    @FXML
    private void button1fired(ActionEvent event) {
        String s = "1";
        String text = pincodeField.getText();
        if (text == null) {
            text = "";
        }
        pincodeField.setText(text+s);
    }

    @FXML
    private void button2fired(ActionEvent event) {
        String s = "2";
        String text = pincodeField.getText();
        if (text == null) {
            text = "";
        }
        pincodeField.setText(text+s);
    }

    @FXML
    private void button3fired(ActionEvent event) {
        String s = "3";
        String text = pincodeField.getText();
        if (text == null) {
            text = "";
        }
        pincodeField.setText(text+s);
    }

    @FXML
    private void button4fired(ActionEvent event) {
        String s = "4";
        String text = pincodeField.getText();
        if (text == null) {
            text = "";
        }
        pincodeField.setText(text+s);
    }

    @FXML
    private void button5fired(ActionEvent event) {
        String s = "5";
        String text = pincodeField.getText();
        if (text == null) {
            text = "";
        }
        pincodeField.setText(text+s);
    }

    @FXML
    private void button6fired(ActionEvent event) {
        String s = "6";
        String text = pincodeField.getText();
        if (text == null) {
            text = "";
        }
        pincodeField.setText(text+s);
    }

    @FXML
    private void button7fired(ActionEvent event) {
        String s = "7";
        String text = pincodeField.getText();
        if (text == null) {
            text = "";
        }
        pincodeField.setText(text+s);
    }

    @FXML
    private void button8fired(ActionEvent event) {
        String s = "8";
        String text = pincodeField.getText();
        if (text == null) {
            text = "";
        }
        pincodeField.setText(text+s);
    }

    @FXML
    private void button9(ActionEvent event) {
        String s = "9";
        String text = pincodeField.getText();
        if (text == null) {
            text = "";
        }
        pincodeField.setText(text+s);
    }

    @FXML
    private void button0fired(ActionEvent event) {
        String s = "0";
        String text = pincodeField.getText();
        if (text == null) {
            text = "";
        }
        pincodeField.setText(text+s);
    }


    
}
