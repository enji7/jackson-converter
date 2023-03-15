package systems.enji.jackson.converter;

import java.util.Objects;

public class Pirate {

  private String name;
  
  // if you can change the source code, and want to apply a converter only to specific properties
  // (instead of globally), you can use the following annotation:
  // @JsonDeserialize(converter = ParrotConverter.class)
  private NastyParrot parrot;
  
  public Pirate() {
  }
  
  public Pirate(String name, NastyParrot parrot) {
    this.name = name;
    this.parrot = parrot;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }

  public NastyParrot getParrot() {
    return parrot;
  }
  
  public void setParrot(NastyParrot parrot) {
    this.parrot = parrot;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Pirate)) {
      return false;
    }
    return Objects.equals(this.name, ((Pirate) obj).getName());
  }

}
