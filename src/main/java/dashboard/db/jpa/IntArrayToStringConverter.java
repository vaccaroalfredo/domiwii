package dashboard.db.jpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.util.StringUtils;




@Converter
public class IntArrayToStringConverter implements AttributeConverter<List<Integer>, String>	 {

	@Override
	public String convertToDatabaseColumn(List<Integer> attribute) {
		// TODO Auto-generated method stub
		return attribute == null ? null : IntArrayToStringConverter.strJoin(attribute,",");
	}

	@Override
	public List<Integer> convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		if (IntArrayToStringConverter.isBlank(dbData))
            return new ArrayList<>();

        try (
        		Stream<String> stream = Arrays.stream((dbData.split(",")))) {
            return stream.map(Integer::parseInt).collect(Collectors.toList());
        }
	}
	
	public static String strJoin(List<Integer> aArr, String sSep) {
	    StringBuilder sbStr = new StringBuilder();
	    for (int i = 0, il = aArr.size(); i < il; i++) {
	        if (i > 0)
	            sbStr.append(sSep);
	        sbStr.append(aArr.get(i));
	    }
	    return sbStr.toString();
	}
	public static Boolean isBlank(String data) {
	    if( data == null || data.equalsIgnoreCase("") || data.equalsIgnoreCase(" ")){
	    	
	    	return true;
	    	
	    }
	    
	    return false;
	}

}
