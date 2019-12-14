package ThomasMatragran0.github.io;
/**
 * @author Thomas Matragrano
 * @brief Interface that declares methods which will be used by the product parent and child classes
 */
// Interfaces usually don't hold fields, mostly methods. Fields cannot be private and will be final
public interface Item {
  public int getId();

  public void setName(String s);

  public String getName();

  public void setManufacturer(String s);

  public String getManufacturer();
}
