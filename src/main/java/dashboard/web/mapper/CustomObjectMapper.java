package dashboard.web.mapper;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;




public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper() {
    	configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); 
    	configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false); 
    	configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    	configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    	
    	configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
    	 // Uses Enum.toString() for serialization of an Enum
        //configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, false);
        // Uses Enum.toString() for deserialization of an Enum
        //configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, false);
        setDateFormat(new SimpleDateFormat("dd-MM-yyyy H:mm"));
    }

}
