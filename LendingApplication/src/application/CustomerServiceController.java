package application;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CustomerServiceController {
	@FXML
    private TextArea txtconcern;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtfullname;
    @FXML
    private Label AllLabels;
    @FXML
    private Button AttachButton;
    private Stage stage;
    private File selectedFile;
    
    @FXML
    void AttachPicture(ActionEvent event) {
    	 FileChooser fileChooser = new FileChooser();
 	    fileChooser.setTitle("Open a file");
 	    fileChooser.setInitialDirectory(new File("C:\\"));
 	    fileChooser.getExtensionFilters().addAll(
 	        new FileChooser.ExtensionFilter("JPEG Image", "*.jpg"),
 	        new FileChooser.ExtensionFilter("PNG Image", "*.png"),
 	        new FileChooser.ExtensionFilter("All image files", "*.jpg", "*.png")
 	    );
 	    selectedFile = fileChooser.showOpenDialog(stage);

 	    if (selectedFile != null) {
 	        try {
 	            AttachButton.setText(selectedFile.getName());
 	          
 	        } catch (Exception e) {
 	            // Handle image loading error
 	            System.err.println("Error loading image: " + e.getMessage());
 	        }
 	    } else {
 	    	AttachButton.setText("Insert File");
 	    }

    }

    @FXML
    void SendRequest(ActionEvent event) throws  IOException {
    	String email = txtemail.getText();
    	String fname = txtfullname.getText();
    	String concern = txtconcern.getText();
    	
    	Connection con;
    	PreparedStatement pst;
    	
    	  if (email.equals("") || fname.equals("") || concern.equals("")) {
	        	AllLabels.setText("All Fields are Required!!");
	            return; // Exit method if any field is empty
	        }
    	 if (!email.toLowerCase().endsWith("@gmail.com")) {
	        	AllLabels.setText("Email invalid!..@gmail.com");
	            return;
	        }
	    try { 
	    	con = DriverManager.getConnection("jdbc:mysql://localhost/lendingapp_db", "root", "");
			pst = con.prepareStatement("INSERT INTO c_service (email, full_name,concern,image, date) VALUES(?,?,?,?,NOW())");
			
			pst.setString(1, email);
		    pst.setString(2, fname);
		    pst.setString(3, concern);
		    // Assuming selectedFile is a File object representing the selected image
		    if (selectedFile != null) {
	            pst.setString(4, selectedFile.getPath()); // Or selectedFile.getAbsolutePath() for the full path
	        } else {
	            pst.setNull(4, Types.VARCHAR); // Set the column to NULL if selectedFile is null
	        }
		    pst.executeUpdate();
		    
		    if (selectedFile != null) {
		        pst.setString(4, selectedFile.getPath());
		    } else {
		        pst.setNull(4, Types.VARCHAR); // Set the column to NULL if selectedFile is null
		    }
		    
		    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setContentText("Concern Request Successfully!");
	        alert.show();
	        
	        Parent root = FXMLLoader.load(getClass().getResource("OptionPage.fxml"));
	        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	    		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	 @FXML
	    void goBack(ActionEvent event) throws IOException {
		    Parent root = FXMLLoader.load(getClass().getResource("OptionPage.fxml")); // Assuming Login.fxml is your login page FXML file
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	    }

}
