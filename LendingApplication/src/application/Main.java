package application;

	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;

public class Main extends Application {
     public void logout(Stage primaryStage) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText("You are about to Exit the Page");
		alert.setContentText("Are you sure you want to leave?");
		
		ButtonType buttonTypeYes = new ButtonType("YES");
		ButtonType buttonTypeNo = new ButtonType("NO");
		
		alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
		 
		if(alert.showAndWait().get() == buttonTypeYes) {
		System.out.println("Thank you for using our Application!");
		primaryStage.close();
	}
	}
	 public static void main(String[] args) {
		launch(args);
	}
	//@Override
	 public void start(Stage primaryStage) {
		try {
			
			Parent root = (Parent) FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root);
			Image icon = new Image("QCULOANLOGO.png");
			primaryStage.getIcons().add(icon);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Loan Application");
			primaryStage.setResizable(false);
			
			primaryStage.setOnCloseRequest(event -> {
				event.consume();
				logout(primaryStage);
				});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
