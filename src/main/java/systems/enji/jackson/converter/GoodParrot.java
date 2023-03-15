package systems.enji.jackson.converter;

/**
 * In contrast to the "NastyParrot", this good parrot has a default constructor and hence,
 * can be used by Jackson during deserialization.
 */
public class GoodParrot {

  private String name;

  public GoodParrot() {
  }
  
  public GoodParrot(String name) {
    this.name = name;
  }
  
  public String getName() {
    return name;
  }
  
}
