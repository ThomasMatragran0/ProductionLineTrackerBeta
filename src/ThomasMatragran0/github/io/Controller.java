package ThomasMatragran0.github.io;
/**
 * @author Thomas Matragrano
 * @brief Controller class for javafx and GUI implementation and Population
 */
import ThomasMatragran0.github.io.Product.Widget;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class Controller extends Application {
  @FXML public ComboBox<String> quantityComboBox;
  @FXML public Button addProductButton;
  @FXML public ChoiceBox newItemType;
  @FXML public TextField newProductManufacturer;
  @FXML public TextField newProductName;
  @FXML public TextArea productionLogTextArea;
  @FXML public TableView<Product> existingTableView;
  @FXML public TableColumn<?, ?> existingProductCol;
  @FXML public TableColumn<?, ?> existingManufacturingCol;
  @FXML public TableColumn<?, ?> existingTypeCol;
  @FXML public ListView choosingProductListView;

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
      pstmt.setString(1, newItemType.getSelectionModel().getSelectedItem().toString());
      pstmt.setString(2, newProductManufacturer.getText());
      pstmt.setString(3, newProductName.getText());
      pstmt.executeUpdate();

      sql =
          "INSERT INTO PRODUCTIONRECORD(PRODUCT_ID,SERIAL_NUM,DATE_PRODUCED) VALUES (?,?,CURRENT_TIMESTAMP);";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, 1);
      pstmt.setString(2, "1");

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
      String sql = "SELECT * FROM PRODUCTIONRECORD;";
      ResultSet rs = stmt.executeQuery(sql);
      // Execute SQL string
      while (rs.next()) {
        int prodNum = (rs.getInt(1));
        int ID = (rs.getInt(2));
        String serialNum = (rs.getString(3));
        Date date = (rs.getDate(4));

        // Populating production log TextArea
        Product productProduced = new Widget("iPod", "Apple", ItemType.AUDIO);
        // test constructor used when creating production records from user interface
        int numProduced = 3; // this will come from the combobox in the UI
        int itemCount = 0;

        for (int productionRunProduct = 0;
            productionRunProduct < numProduced;
            productionRunProduct++) {
          ProductionRecord pr = new ProductionRecord(productProduced, itemCount++);
          // using the iterator as the product id for testing
          System.out.println(pr.toString());
          productionLogTextArea.appendText(pr.toString() + "\n");
        }
      }

      // Populate the Choosing Products ListView
      sql = "SELECT * FROM PRODUCT;";
      rs = stmt.executeQuery(sql);

      while (rs.next()) {}

      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    // Populates the new Item Type ChoiceBox
    newItemType
        .getItems()
        .addAll(ItemType.AUDIO, ItemType.VISUAL, ItemType.AUDIO_MOBILE, ItemType.VISUAL_MOBILE);
    newItemType.getSelectionModel().selectFirst();

    // Populates the quantity Combobox
    quantityComboBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    quantityComboBox.getSelectionModel().selectFirst();
    quantityComboBox.setEditable(true);

    // Setting the ObservableList to the TableView
    ObservableList<Product> data = productLine();
    existingProductCol.setCellValueFactory(new PropertyValueFactory("name"));
    existingManufacturingCol.setCellValueFactory(new PropertyValueFactory("Manufacturer"));
    existingTypeCol.setCellValueFactory(new PropertyValueFactory("T"));
    existingTableView.setItems(data);
    choosingProductListView.setItems(data);

    testMultimedia();
  }

  // This static, void method creates products of different media types and prints them to the
  // console
  public static void testMultimedia() {
    AudioPlayer newAudioProduct =
        new AudioPlayer(
            "DP-X1A", "Onkyo", "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
    Screen newScreen = new Screen("720x480", 40, 22);
    MoviePlayer newMovieProduct =
        new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen, MonitorType.LCD);
    ArrayList<MultimediaControl> productList = new ArrayList<MultimediaControl>();
    productList.add(newAudioProduct);
    productList.add(newMovieProduct);
    for (MultimediaControl p : productList) {
      System.out.println(p);
      p.play();
      p.stop();
      p.next();
      p.previous();

      for (int productionRunProduct = 0; productionRunProduct < 3; productionRunProduct++) {
        ProductionRecord pr = new ProductionRecord(0);
        System.out.println(pr.toString());
      }

      // test constructor used when creating production records from reading database
      ProductionRecord pr = new ProductionRecord(0, 3, "1", new Date());
      System.out.println(pr.toString());

      // testing accessors and mutators
      pr.setProductionNum(1);
      System.out.println(pr.getProductionNum());

      pr.setProductID(4);
      System.out.println(pr.getProductID());

      pr.setSerialNum("2");
      System.out.println(pr.getSerialNum());

      pr.setProdDate(new java.util.Date());
      System.out.println(pr.getProdDate());
    }
  }

  public static ObservableList<Product> productLine() {

    return FXCollections.observableArrayList(
        new Widget("Apple", "Smith", ItemType.AUDIO),
        new Widget("Isabella", "Johnson", ItemType.AUDIO),
        new Widget("Ethan", "Williams", ItemType.AUDIO),
        new Widget("Emma", "Jones", ItemType.AUDIO),
        new Widget("Michael", "Brown", ItemType.AUDIO));
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
