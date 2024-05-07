package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class AgreementPageController {
	private boolean agreed = false;
	  @FXML
	  private CheckBox CheckBoxButton1;
	  @FXML
	  private CheckBox CheckBoxButton2;
	  @FXML
	  void YesCheckBox(ActionEvent event) throws IOException {
	        // Get the instance of SignupController
	    	agreed = true;
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
	        Parent parent = loader.load();
	        SignupController signupController = loader.getController();
	        
	        // Set the checkbox status in SignupController
	        signupController.setCheckboxSelected(true);

	        // Close the current stage or dialog
	        Stage stage = (Stage) CheckBoxButton1.getScene().getWindow();
	        stage.close();}
	  @FXML
	  void NoCheckbox(ActionEvent event) throws IOException {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
	        Parent parent = loader.load();
	        SignupController signupController = loader.getController();
	        
	        // Set the checkbox status in SignupController
	        signupController.setCheckboxSelected(false);

	        // Close the current stage or dialog
	        Stage stage = (Stage) CheckBoxButton2.getScene().getWindow();
	        stage.close();  }
		public boolean isAgreed() {
		return agreed;
		} 
	}


