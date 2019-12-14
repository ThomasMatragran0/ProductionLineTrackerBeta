package ThomasMatragran0.github.io;
/**
 * @author Thomas Matragrano
 * @brief Controller class for javafx and GUI implementation and Population
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class Controller extends Application {
  @FXML public ComboBox<String> quantityComboBox;
  @FXML public Button addProductButton;
  @FXML public ChoiceBox newItemType;
  public TextField newProductManufacturer;
  public TextField newProductName;

  @FXML
  void addProductButtonAction(ActionEvent event) {
    System.out.println("Product Added");

    // insert added product into database
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/ProductionDatabase";
    //  Database credentials
    final String USER = "";
    final String PASS = "";
    Connection conn = null;
    Statement stmt = null;

    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      // STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      // STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql = "INSERT INTO Product(type, manufacturer, name) VALUES ( ?, ?, ?);";
      PreparedStatement pstmt = conn.prepareStatement(sql);
      // Execute SQL string
      pstmt.setString(1, (String) newItemType.getSelectionModel().getSelectedItem());
      pstmt.setString(2, newProductManufacturer.getText());
      pstmt.setString(3, newProductName.getText());
      pstmt.executeUpdate();
      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void initialize() {
    // Populates the new Item Type ChoiceBox
    newItemType.getItems().addAll("Audio", "Visual", "AutoMobile", "VisualMoblile");
    newItemType.getSelectionModel().selectFirst();

    // Populates the quanitity Combobox
    quantityComboBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    quantityComboBox.getSelectionModel().selectFirst();
    quantityComboBox.setEditable(true);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("ProductionTracker.fxml"));
    Scene scene = new Scene(root, 800, 500);
    primaryStage.setScene(scene);
    primaryStage.show();
    scene
        .getStylesheets()
        .add(getClass().getResource("sample/ProductionSheet.css").toExternalForm());
  }
}
