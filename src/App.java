import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{

    private Cipher cipher = new Cipher();

    public static void main(String[] args) {
        App app = new App();
        app.runGUI(args);
    }

    public void runGUI(String[] args){
        launch(args);
    }

    public void start(Stage stage){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
            Scene mainMenu = new Scene(root);
            stage.setScene(mainMenu);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
