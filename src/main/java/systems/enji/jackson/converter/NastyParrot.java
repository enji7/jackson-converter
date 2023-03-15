package systems.enji.jackson.converter;

import java.util.Objects;

/**
 * This nasty parrot does not have a default constructor and hence, cannot be used
 * by Jackson for deserialization. 
 * 
 * Assume that this class belongs to a third party library that you cannot change.  
 */
public class NastyParrot {

  private String name;

  public NastyParrot(String name) {
    this.name = name;
  }
  
  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof NastyParrot)) {
      return false;
    }
    return Objects.equals(this.name, ((NastyParrot) obj).getName());
  }
  
}
