package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InformationPageController implements Initializable  {
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
    private TextField Schoolnametxt;
    @FXML
    private TextField Schoolnumtxt;
    @FXML
    private TextField Schoolownertxt;
    @FXML
    private TextField agetxt;
    @FXML
    private TextField contacttxt;
    @FXML
    private TextField emailtxt;
    @FXML
    private TextField firstnametxt;
    @FXML
    private TextField lastnametxt;
    @FXML
    private TextField Qcownertxt;
    @FXML
    private TextField middlenametxt;
    @FXML
    private TextField QcNumtxt;
    @FXML
    private TextField nationalitytxt;
    @FXML
	private Button QcidBackButton;
	@FXML
	private ImageView QcidBackImage;
	@FXML
    private Label QcidBackLabel;
    @FXML
    private Button QcidFrontButton;
    @FXML
    private ImageView QcidFrontImage;
	@FXML
    private Label QcidFrontLabel;
	@FXML
	private Button SchoolBackButton;
	@FXML
    private ImageView SchoolBackImage;
	@FXML
	private Label SchoolBackLabel;
	@FXML
    private Button SchoolFrontButton;
	@FXML
	private ImageView SchoolFrontImage;
    @FXML
    private Label SchoolFrontLabel;
	@FXML
    private Label AccountName;
    @FXML
    private TextField AccountNameField;
    @FXML
    private Label AccountNumber;
    @FXML
    private TextField AccountNumberField;
    @FXML
    private TextField AmountField;
    @FXML
    private ToggleGroup Period;
    @FXML
    private CheckBox agreeButton;
    @FXML
    private RadioButton annualRate;
    @FXML
    private Button backButton;
    @FXML
    private Button nextButton;
    @FXML
    private RadioButton radio1;
    @FXML
    private RadioButton radio2;
    @FXML
    private RadioButton radio3;
    @FXML
    private RadioButton radio4;
    @FXML
    private RadioButton radio5;
    @FXML
    private DatePicker bdayPicker;
    @FXML
    private CheckBox sameAddressBox;
    @FXML
    private TextField cAddresstxt;
    @FXML
    private TextField pAddresstxt;
    @FXML
    private Button ButtonNext;
    @FXML
    private ComboBox<String> withdrawalMode;
    @FXML
    private ComboBox<String> sexBox;
    
    String[] sex = {"Male","Female","Sige ba"};
    String[] modeofWithdrawal = {"Gcash","Paymaya"};
	 @FXML
	    void GotoLoanAgree(ActionEvent event) throws IOException {
	        Dialog<Void> dialog = new Dialog<>();

	        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoanAgreePage.fxml"));
	        try {
	            dialog.getDialogPane().setContent(loader.load());
	        } catch (IOException e) {
	            e.printStackTrace();
	            return;
	        }
	        // Get the dialog window and remove the close button (X button)
	        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
	        stage.initStyle(StageStyle.UNDECORATED);
	        dialog.showAndWait();
	    
	        dialog.close();
	        LoanAgreementController loanAgreementController = loader.getController();
	        
	     // Check if the user agreed to the terms
	        if (loanAgreementController.isAgreed()) {
	            // Call the method to set checkbox status in SignupController
	            setCheckboxSelected(true);
	        }
	    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		sexBox.getItems().addAll(sex);
		withdrawalMode.getItems().addAll(modeofWithdrawal);
		withdrawalMode.setOnAction(this::getMode);
		withdrawalMode.setDisable(true); 
		
		agreeButton.setOnAction(event -> {
            boolean isChecked = agreeButton.isSelected();
            //Section I
            lastnametxt.setDisable(!isChecked);
            firstnametxt.setDisable(!isChecked);
            middlenametxt.setDisable(!isChecked);
            cAddresstxt.setDisable(!isChecked);
            pAddresstxt.setDisable(!isChecked);
            sameAddressBox.setDisable(!isChecked);
            agetxt.setDisable(!isChecked);
            bdayPicker.setDisable(!isChecked);
            sexBox.setDisable(!isChecked);
            nationalitytxt.setDisable(!isChecked);
            contacttxt.setDisable(!isChecked);
            emailtxt.setDisable(!isChecked);
            //Section II
            AccountName.setDisable(!isChecked);
            AccountNameField.setDisable(!isChecked);
            AccountNumber.setDisable(!isChecked);
            AccountNumberField.setDisable(!isChecked);
            annualRate.setDisable(!isChecked);
            radio1.setDisable(!isChecked);
            radio2.setDisable(!isChecked);
            radio3.setDisable(!isChecked);
            radio4.setDisable(!isChecked);
            radio5.setDisable(!isChecked);	
            AmountField.setDisable(!isChecked);
            withdrawalMode.setDisable(!isChecked);
            //Section III
            QcNumtxt.setDisable(!isChecked);
            Qcownertxt.setDisable(!isChecked);
            QcidFrontButton.setDisable(!isChecked);
            QcidBackButton.setDisable(!isChecked);
            Schoolnumtxt.setDisable(!isChecked);
            Schoolownertxt.setDisable(!isChecked);
            Schoolnametxt.setDisable(!isChecked);
            SchoolFrontButton.setDisable(!isChecked);
            SchoolBackButton.setDisable(!isChecked);
            ButtonNext.setDisable(!isChecked);
            });
	}
	public void setCheckboxSelected(boolean selected) {
		agreeButton.setSelected(selected);
		 lastnametxt.setDisable(!selected);
         firstnametxt.setDisable(!selected);
         middlenametxt.setDisable(!selected);
         cAddresstxt.setDisable(!selected);
         pAddresstxt.setDisable(!selected);
         sameAddressBox.setDisable(!selected);
         agetxt.setDisable(!selected);
         bdayPicker.setDisable(!selected);
         sexBox.setDisable(!selected);
         nationalitytxt.setDisable(!selected);
         contacttxt.setDisable(!selected);
         emailtxt.setDisable(!selected);
         //Section II
         AccountName.setDisable(!selected);
         AccountNameField.setDisable(!selected);
         AccountNumber.setDisable(!selected);
         AccountNumberField.setDisable(!selected);
         annualRate.setDisable(!selected);
         radio1.setDisable(!selected);
         radio2.setDisable(!selected);
         radio3.setDisable(!selected);
         radio4.setDisable(!selected);
         radio5.setDisable(!selected);	
         AmountField.setDisable(!selected);
         withdrawalMode.setDisable(!selected);
         //Section III
         QcNumtxt.setDisable(!selected);
         Qcownertxt.setDisable(!selected);
         QcidFrontButton.setDisable(!selected);
         QcidBackButton.setDisable(!selected);
         Schoolnumtxt.setDisable(!selected);
         Schoolownertxt.setDisable(!selected);
         Schoolnametxt.setDisable(!selected);
         SchoolFrontButton.setDisable(!selected);
         SchoolBackButton.setDisable(!selected);
         ButtonNext.setDisable(!selected);
    }
	public void getDate(ActionEvent event) {
		LocalDate bday = bdayPicker.getValue();
		System.out.println(bday.toString());
	}
	public void sameAddressMethod(ActionEvent event) {
		if(sameAddressBox.isSelected()) {
			String address = cAddresstxt.getText();
			pAddresstxt.setText(address);
		}else {
		    pAddresstxt.setText("");}
	} 
	public void switchToOptionPage(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Go Back");
		alert.setHeaderText("Your data will not be saved!");
		alert.setContentText("Are you sure you want to go back?");
		
		ButtonType buttonTypeYes = new ButtonType("YES");
		ButtonType buttonTypeNo = new ButtonType("NO");
		
		alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
		if(alert.showAndWait().get() == buttonTypeYes) {
			root = (Parent) FXMLLoader.load(getClass().getResource("OptionPage.fxml"));
			 stage =(Stage)((Node)event.getSource()).getScene().getWindow();
			 scene = new Scene(root);
			 stage.setScene(scene);
			 stage.show();
	}
	}
	public void getPeriod(ActionEvent event) {
    	if(radio1.isSelected()) {
    		String Period = radio1.getText();
    		System.out.println(Period);    		
    	}else if(radio2.isSelected()){
    		String Period = radio2.getText();
    		System.out.println(Period);  
    	}else if (radio3.isSelected()) {
    		String Period = radio3.getText();
    		System.out.println(Period);
    	}else if(radio4.isSelected()) {
    		String Period = radio4.getText();
    		System.out.println(Period);
    	}else if(radio5.isSelected()) {
    		String Period = radio5.getText();
    		System.out.println(Period);
    	}
    }
	public void getMode(ActionEvent event) {
		String WithdrawalMode = withdrawalMode.getValue();
		System.out.println(WithdrawalMode);
		if (WithdrawalMode != null) {
            if (WithdrawalMode.equals("Gcash")|| WithdrawalMode.equals("Paymaya")) { 
                AccountName.setVisible(true);
                AccountNameField.setVisible(true);
                AccountNumber.setVisible(true);
                AccountNumberField.setVisible(true);
            } else {
                AccountName.setVisible(false);
                AccountNameField.setVisible(false);
                AccountNumber.setVisible(false);
                AccountNumberField.setVisible(false);
            }
        }
	}
	public void getInterest(ActionEvent event) {
		if(annualRate.isSelected()) {
			float Interest = 0.05f;
			System.out.println(Interest);
		}
		
	}
	public void FrontQcidFile(ActionEvent event) {
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Open a file");
	    fileChooser.setInitialDirectory(new File("C:\\"));
	    fileChooser.getExtensionFilters().addAll(
	        new FileChooser.ExtensionFilter("JPEG Image", "*.jpg"),
	        new FileChooser.ExtensionFilter("PNG Image", "*.png"),
	        new FileChooser.ExtensionFilter("All image files", "*.jpg", "*.png")
	    );
	    File selectedFile = fileChooser.showOpenDialog(stage);

	    if (selectedFile != null) {
	        try {
	            QcidFrontLabel.setText(selectedFile.getName());
	            Image QcidFrontimage = new Image(selectedFile.toURI().toString());
	            QcidFrontImage.setImage(QcidFrontimage);
	        } catch (Exception e) {
	            // Handle image loading error
	            System.err.println("Error loading image: " + e.getMessage());
	        }
	    } else {
	        QcidFrontLabel.setText("Insert File");
	    }
	}
	public void BackQcidFile(ActionEvent event) {
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Open a file");
	    fileChooser.setInitialDirectory(new File("C:\\"));
	    fileChooser.getExtensionFilters().addAll(
	        new FileChooser.ExtensionFilter("JPEG Image", "*.jpg"),
	        new FileChooser.ExtensionFilter("PNG Image", "*.png"),
	        new FileChooser.ExtensionFilter("All image files", "*.jpg", "*.png")
	    );
	    File selectedFile = fileChooser.showOpenDialog(stage);

	    if (selectedFile != null) {
	        try {
	            QcidBackLabel.setText(selectedFile.getName());
	            Image QcidBackimage = new Image(selectedFile.toURI().toString());
	            QcidBackImage.setImage(QcidBackimage);
	        } catch (Exception e) {
	            // Handle image loading error
	            System.err.println("Error loading image: " + e.getMessage());
	        }
	    } else {
	        QcidBackLabel.setText("Insert File");
	    }
	}
	public void FrontSchoolFile(ActionEvent event) {
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Open a file");
	    fileChooser.setInitialDirectory(new File("C:\\"));
	    fileChooser.getExtensionFilters().addAll(
	        new FileChooser.ExtensionFilter("JPEG Image", "*.jpg"),
	        new FileChooser.ExtensionFilter("PNG Image", "*.png"),
	        new FileChooser.ExtensionFilter("All image files", "*.jpg", "*.png")
	    );
	    File selectedFile = fileChooser.showOpenDialog(stage);

	    if (selectedFile != null) {
	        try {
	            SchoolFrontLabel.setText(selectedFile.getName());
	            Image SchoolFrontimage = new Image(selectedFile.toURI().toString());
	            SchoolFrontImage.setImage(SchoolFrontimage);
	        } catch (Exception e) {
	            // Handle image loading error
	            System.err.println("Error loading image: " + e.getMessage());
	        }
	    } else {
	        SchoolFrontLabel.setText("Insert File");
	    }
	}
	public void BackSchoolFile(ActionEvent event) {
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Open a file");
	    fileChooser.setInitialDirectory(new File("C:\\"));
	    fileChooser.getExtensionFilters().addAll(
	        new FileChooser.ExtensionFilter("JPEG Image", "*.jpg"),
	        new FileChooser.ExtensionFilter("PNG Image", "*.png"),
	        new FileChooser.ExtensionFilter("All image files", "*.jpg", "*.png")
	    );
	    File selectedFile = fileChooser.showOpenDialog(stage);

	    if (selectedFile != null) {
	        try {
	            SchoolBackLabel.setText(selectedFile.getName());
	            Image SchoolBackimage = new Image(selectedFile.toURI().toString());
	            SchoolBackImage.setImage(SchoolBackimage);
	        } catch (Exception e) {
	            // Handle image loading error
	            System.err.println("Error loading image: " + e.getMessage());
	        }
	    } else {
	        SchoolBackLabel.setText("Insert File");
	    }
	}
}