package ThomasMatragran0.github.io;
/**
 * @author Thomas Matragrano
 * @brief Class extension of Product used for audio players
 */
public class AudioPlayer extends Product implements MultimediaControl {

  private String audioSpecification;
  private String mediaType;

  /**
   * @brief Constructor for AudioPlayer that takes parameters for name, manufacturer,
   *     audioSpecification, and MediaType
   */
  public AudioPlayer(
      String name, String manufacturer, String audioSpecification, String mediaType) {
    super(name);
    this.audioSpecification = audioSpecification;
    this.manufacturer = manufacturer;

    this.mediaType = mediaType;
    type = ItemType.AUDIO;
  }

  @Override
  public void play() {
    System.out.println("Playing");
  }

  @Override
  public void stop() {
    System.out.println("Stopping");
  }

  @Override
  public void previous() {
    System.out.println("Previous");
  }

  @Override
  public void next() {
    System.out.println("Next");
  }
  /** @brief Returns a string value for audioplayer */
  public String toString() {
    return super.toString()
        + "\n"
        + "Supported Audio Formats: "
        + audioSpecification
        + "\n"
        + "Supported Playlist Formats: "
        + mediaType;
  }
}
