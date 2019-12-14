package ThomasMatragran0.github.io;
/**
 * @author Thomas Matragrano
 * @brief Class that implements and overrides all methods of ScreenSpec to be used for subclasses of
 *     Product that require a screen
 */
public class Screen implements ScreenSpec {

  private String resolution;
  private int refreshRate;
  private int responseTime;

  public Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }

  @Override
  public String getResolution() {
    return resolution;
  }

  @Override
  public int getRefreshRate() {
    return refreshRate;
  }

  @Override
  public int getResponseTime() {
    return responseTime;
  }

  public String toString() {
    return "Resolution: "
        + resolution
        + "\nRefresh Rate: "
        + refreshRate
        + "\nResponse Time: "
        + responseTime;
  }
}
