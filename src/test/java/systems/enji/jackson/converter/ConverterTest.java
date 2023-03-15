package systems.enji.jackson.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class ConverterTest {

  /** Pirate to be (de-)serialized. */
  private Pirate blackbeard = new Pirate("Blackbeard", new NastyParrot("Polly"));

  @Test
  void failsWithoutConverter() throws JsonProcessingException {
    
    ObjectMapper mapper = new ObjectMapper();
    
    // serialization works
    String blackbeardJson = mapper.writeValueAsString(blackbeard);
    System.out.println(blackbeardJson);
    
    // deserialization fails due to the nasty parrot
    Assertions.assertThrows(MismatchedInputException.class, 
        () -> mapper.readValue(blackbeardJson, Pirate.class));
    
  }

  @Test
  void worksWithConverter() throws JsonProcessingException {

    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    module.addDeserializer(NastyParrot.class, new StdDelegatingDeserializer<>(new ParrotConverter()));
    mapper.registerModule(module);
    
    // serialization works
    String blackbeardJson = mapper.writeValueAsString(blackbeard);
    System.out.println(blackbeardJson);

    Pirate blackbeard2 = mapper.readValue(blackbeardJson, Pirate.class);

    // compare original with copy
    Assertions.assertEquals(blackbeard, blackbeard2);

  }

  /**
   * This solution is not recommended:
   * - see JavaDoc for {@link ObjectMapper#setAnnotationIntrospector(com.fasterxml.jackson.databind.AnnotationIntrospector)}
   * - more cumbersome than using a module + deserializer
   */
  @Test
  void worksWithConverterByAnnotationIntrospector() throws JsonProcessingException {

    ObjectMapper mapper = new ObjectMapper();
    mapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector() {
      public Object findDeserializationConverter(Annotated a) {
        if (NastyParrot.class == a.getRawType()) {
          return new ParrotConverter();
        } else {
          return super.findDeserializationConverter(a);
        }
      }
    });
    
    // serialization works
    String blackbeardJson = mapper.writeValueAsString(blackbeard);
    System.out.println(blackbeardJson);

    Pirate blackbeard2 = mapper.readValue(blackbeardJson, Pirate.class);

    // compare original with copy
    Assertions.assertEquals(blackbeard, blackbeard2);

  }

}
