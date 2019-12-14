package ThomasMatragran0.github.io;
/**
 * @author Thomas Matragrano
 * @brief Constructs an object for Movie Players. Overrides all methods implemented from Multimedia
 */
public class MoviePlayer extends Product implements MultimediaControl {

  private Screen myScreen;
  private MonitorType monType;

  public MoviePlayer(String name, String manufacturer, Screen myScreen, MonitorType monType) {
    super(name);
    this.manufacturer = manufacturer;
    this.myScreen = myScreen;
    this.monType = monType;
    type = ItemType.VISUAL;
  }

  @Override
  public void play() {
    System.out.println("Playing movie");
  }

  @Override
  public void stop() {
    System.out.println("Stopping movie");
  }

  @Override
  public void next() {
    System.out.println("Next movie");
  }

  @Override
  public void previous() {
    System.out.println("Previous movie");
  }

  public String toString() {
    return super.toString() + "\n" + "Screen: " + myScreen + "\n" + "Monitor Type: " + monType;
  }
}
