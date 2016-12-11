package dashboard.db.jpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;




@Converter
public class CodeArrayToStringConverter implements AttributeConverter<CodeList, String>	 {

	@Override
	public String convertToDatabaseColumn(CodeList attribute) {
		
		return attribute == null ? null : CodeArrayToStringConverter.codeJoin(attribute);
	}

	@Override
	public CodeList convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		if (CodeArrayToStringConverter.isBlank(dbData))
            return new CodeList();
		Gson gson = new Gson();
		CodeList codeList = (CodeList) gson.fromJson(dbData, CodeList.class);
		return codeList;

        
	}
	
	public static String codeJoin(CodeList aArr) {

	    Gson gson = new Gson();
	    String json = gson.toJson(aArr);	    
	    return json;
	}
	public static Boolean isBlank(String data) {
	    if( data == null || data.equalsIgnoreCase("") || data.equalsIgnoreCase(" ")){
	    	
	    	return true;
	    	
	    }
	    
	    return false;
	}

}
