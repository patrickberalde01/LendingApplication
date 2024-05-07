package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class ApplicationController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private CheckBox logInCheckbox;
    @FXML
	private AnchorPane loginTermsButton;
	@FXML
	private Button ExitButton;
	@FXML
	private AnchorPane OptionPage;
	@FXML
    private Button LoginButton;
    @FXML
    private PasswordField txtpass;
    @FXML
    private TextField txtuname;
    @FXML
    private Button createAccButton;
    @FXML
    private Label LoginLabel;
    private BooleanProperty loginDisabled = new SimpleBooleanProperty(true);
    Connection con;
   	PreparedStatement pst;
   	ResultSet rs;
public void initialize() {
	 LoginButton.disableProperty().bind(loginDisabled);
	 txtuname.textProperty().addListener((observable, oldValue, newValue) -> updateLoginDisabled());
	    txtpass.textProperty().addListener((observable, oldValue, newValue) -> updateLoginDisabled());

	    // Call updateLoginDisabled() once to set the initial state of the login button
	    updateLoginDisabled();	    
}
private void updateLoginDisabled() {
    // Disable the login button if either of the text fields is empty
    loginDisabled.set(txtuname.getText().isEmpty() || txtpass.getText().isEmpty());
}
	public void logout(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You are about to Logout!");
		alert.setContentText("Are you sure you want to exit?");
		if(alert.showAndWait().get() == ButtonType.OK) {
			 Parent root = FXMLLoader.load(getClass().getResource("Main.fxml")); // Assuming Login.fxml is your login page FXML file
		        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        Scene scene = new Scene(root);
		        stage.setScene(scene);
		        stage.show();
	}
}
	public void switchToSignUp(ActionEvent event) throws IOException {
    	 root = (Parent) FXMLLoader.load(getClass().getResource("SignUp.fxml"));
		 stage =(Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
    public void switchToOptionPage(ActionEvent event) throws IOException {
    	String uname = txtuname.getText();
        String pass = txtpass.getText();
        
             if(uname.equals("") && pass.equals("")){
            	 System.out.println("Try Again");
            	 LoginLabel.setText("Your Input is Incorrect, Please Try Again!");
            	 
             }else {
            	 try {
 					Class.forName("com.mysql.cj.jdbc.Driver");
 					con = DriverManager.getConnection("jdbc:mysql://localhost/lendingapp_db","root","");
 					
 					pst = con.prepareStatement("select firstName, lastName from users where username =? and password = ?");
 					pst.setString(1, uname);
 					pst.setString(2, pass);
 					
 					
 					
 					rs = pst.executeQuery();
 					if(rs.next()) {
 						String firstName = rs.getString("firstName");
 		                String lastName = rs.getString("lastName");
 						System.out.println("Success Login");

 	                    FXMLLoader loader = new FXMLLoader(getClass().getResource("OptionPage.fxml"));
 	                    Parent root = loader.load();

 	                    // Pass first name and last name to OptionPageController
 	                    OptionPageController controller = loader.getController();
 	                    controller.setUserInfo(firstName,lastName);

 	                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 	                    Scene scene = new Scene(root);
 	                    stage.setScene(scene);
 	                    stage.show();
 					}else {
 						System.out.println("Login Failed");
 						LoginLabel.setText("Your Input is Incorrect, Please Try Again!");
 						txtuname.setText("");
 						txtpass.setText("");
 						txtuname.requestFocus();
 					}
 					
 				} catch (ClassNotFoundException ex) {
 					Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE,null,ex);
 			
 				} catch (SQLException ex) {
 					Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE,null,ex);
 				}
             }
    }


    
}