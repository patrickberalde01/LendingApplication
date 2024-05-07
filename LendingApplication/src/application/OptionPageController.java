package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class OptionPageController {
    @FXML
	private Label displayFname;
	@FXML
	private Label displayLname;
    @FXML
    private Button ExitButton;
    @FXML
    private AnchorPane OptionPage;
    @FXML
	public void switchToLoanOption(ActionEvent event ) throws IOException {
		 Parent root = (Parent) FXMLLoader.load(getClass().getResource("InformationPage.fxml"));
		 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 Scene scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();}
public void logout(ActionEvent event) throws IOException {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You are about to Logout!");
		alert.setContentText("Are you sure you want to exit?");
		
		ButtonType buttonTypeYes = new ButtonType("YES");
		ButtonType buttonTypeNo = new ButtonType("NO");
		
		alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
		if(alert.showAndWait().get() == buttonTypeYes) {
			 Parent root = FXMLLoader.load(getClass().getResource("Main.fxml")); // Assuming Login.fxml is your login page FXML file
		        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        Scene scene = new Scene(root);
		        stage.setScene(scene);
		        stage.show();
	}
		
	}
public void setUserInfo(String firstName, String lastName) {
    displayFname.setText(firstName);
    displayLname.setText(lastName);
   
}
@FXML
void goToCustomerService(ActionEvent event) throws IOException {
	Parent root = FXMLLoader.load(getClass().getResource("CustomerServicePage.fxml")); // Assuming Login.fxml is your login page FXML file
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();

}


}