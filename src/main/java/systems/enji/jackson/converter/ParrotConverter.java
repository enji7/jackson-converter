package systems.enji.jackson.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * This adapter shows Jackson how to deserialize with the unmappable "NastyParrot" 
 * by adapting it to the mappable "GoodParrot".
 */
public class ParrotConverter extends StdConverter<GoodParrot, NastyParrot> {

  @Override
  public NastyParrot convert(GoodParrot value) {
    return new NastyParrot(value.getName());
  }

}
