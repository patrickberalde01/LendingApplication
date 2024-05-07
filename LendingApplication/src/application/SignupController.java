package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class SignupController {
	    @FXML
        private Label PasswordLabel1;
	    @FXML
	    private TextField txtemail;
	    @FXML
	    private TextField txtfirstname;
	    @FXML
	    private TextField txtlastname;
	    @FXML
	    private TextField txtuname;
	    @FXML
	    private PasswordField txtpass1;
	    @FXML
	    private PasswordField txtpass2;
	    @FXML
	    private CheckBox logInCheckbox;
	    @FXML
	    private Button signupButton;
	    @FXML
	    private Label PasswordLabel;
	    @FXML
	    private Label EmailLabel;
	    @FXML
	    private Label AllLabel;
	    @FXML
	    private Button AuthenticBotton;
	    

	    @FXML
	    void goToUserAut(ActionEvent event) throws IOException {
	        Dialog<Void> dialog = new Dialog<>();
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("AgreementPage.fxml"));
	        try {
	            dialog.getDialogPane().setContent(loader.load());
	        } catch (IOException e) {
	            e.printStackTrace();
	            return;
	        }

	        dialog.setTitle("User Agreement");
	        // Get the dialog window and remove the close button (X button)
	        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
	        stage.initStyle(StageStyle.UNDECORATED);

	        dialog.showAndWait();

	        // Get the controller of the agreement page
	        AgreementPageController agreementPageController = loader.getController();
	        
	        // Check if the user agreed to the terms
	        if (agreementPageController.isAgreed()) {
	            // Call the method to set checkbox status in SignupController
	            setCheckboxSelected(true);
	        }
	    }
	    public void initialize() {
	        // Disable signup button by default
	        signupButton.setDisable(true);
	        
	        // Set checkbox action
	        logInCheckbox.setOnAction(event -> {
	            boolean isChecked = logInCheckbox.isSelected();
	            signupButton.setDisable(!isChecked);
	            txtemail.setDisable(!isChecked);
	            txtfirstname.setDisable(!isChecked);
	            txtlastname.setDisable(!isChecked);
	            txtuname.setDisable(!isChecked);
	            txtpass1.setDisable(!isChecked);
	            txtpass2.setDisable(!isChecked);
	        });
	    }
	    public void setCheckboxSelected(boolean selected) {
	        logInCheckbox.setSelected(selected);
	        // Enable/disable text fields based on checkbox status
	        txtemail.setDisable(!selected);
	        txtfirstname.setDisable(!selected);
	        txtlastname.setDisable(!selected);
	        txtuname.setDisable(!selected);
	        txtpass1.setDisable(!selected);
	        txtpass2.setDisable(!selected);
	        // Enable/disable signup button based on checkbox status
	        signupButton.setDisable(!selected);
	    }
	    @FXML
	    void switchToLogin(ActionEvent event) throws IOException {
	        String uname = txtuname.getText();
	        String pass = txtpass1.getText();
	        String pass2 = txtpass2.getText(); // Retrieve confirm password
	        String email = txtemail.getText();
	        String firstname = txtfirstname.getText();
	        String lastname = txtlastname.getText();
	        
	        // Reset the error message label
	        AllLabel.setText("");
	        EmailLabel.setText("");
	        PasswordLabel.setText("");
	       

	        if (uname.equals("") || pass.equals("") || pass2.equals("") || email.equals("") || firstname.equals("") || lastname.equals("")) {
	        	AllLabel.setText("All Fields are Required!!");
	            return; // Exit method if any field is empty
	        }
	        // Check if the email ends with @gmail.com
	        if (!email.toLowerCase().endsWith("@gmail.com")) {
	        	EmailLabel.setText("Invalid Email.");
	            return;
	        }
	        if (!pass.equals(pass2)) {
	        	PasswordLabel.setText("Passwords do not match!");
	        	PasswordLabel1.setText("Passwords do not match!");
	            return; // Exit method if passwords don't match
	        }
	        
	        Connection con = null;
	        PreparedStatement pst = null;
	        ResultSet rs = null;

	        try {
	            con = DriverManager.getConnection("jdbc:mysql://localhost/lendingapp_db", "root", "");
	            pst = con.prepareStatement("SELECT * FROM users WHERE username = ?");
	            pst.setString(1, uname);
	            rs = pst.executeQuery();
	            
	            if (rs.next()) {
	            	AllLabel.setText("The Username is already taken");
	            	return; // Exit the method if username exists
	            }
	            	pst = con.prepareStatement("SELECT * FROM users WHERE email = ?");
	                pst.setString(1, email);
	                rs = pst.executeQuery();
	                
	                if (rs.next()) {
	                    AllLabel.setText("The Email is already taken");
	                    return; // Exit the method if email exists
	                
	            } else {
	                pst = con.prepareStatement("INSERT INTO users (firstName, lastName, email, username, password, date_registered) VALUES (?, ?, ?, ?, ?, NOW())");
	                pst.setString(1, firstname);
	                pst.setString(2, lastname);
	                pst.setString(3, email);
	                pst.setString(4, uname);
	                pst.setString(5, pass);
	                pst.executeUpdate();
	                System.out.println("User Registered Successfully!");
	             

	                Alert alert = new Alert(Alert.AlertType.INFORMATION);
	                alert.setContentText("User Registered Successfully!");
	                alert.show();
	                Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
	                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	                Scene scene = new Scene(root);
	                stage.setScene(scene);
	                stage.show();
	               	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (pst != null) pst.close();
	                if (con != null) con.close();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
	    @FXML
	    void backToLogin(ActionEvent event) throws IOException {
	        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml")); // Assuming Login.fxml is your login page FXML file
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	    }
}