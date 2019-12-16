package ThomasMatragran0.github.io;

/**
 * @author Thomas Matragrano
 * @brief Parent class that sets, gets, and displays general product features
 */
import javafx.scene.canvas.Canvas;

public abstract class Product implements Item {
  int id;
  ItemType type;
  String manufacturer;
  String name;

  public Product(String name) {

    this.name = name;
    type = ItemType.AUDIO;
  }

  public int getId() {
    return id;
  }

  public void setName(String s) {
    name = s;
  }

  public String getName() {
    return name;
  }

  public void setManufacturer(String s) {
    manufacturer = s;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public String toString() {
    return "Name: " + name + "\nManufacturer: " + manufacturer + "\nType: " + type;
  }

  public static class Widget extends Product {

    public Widget(String name, String manufacturer, ItemType T) {
      super(name);
      this.manufacturer = manufacturer;
      T = type;
    }
  }
}
