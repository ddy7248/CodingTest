package pro.lev1;

import java.util.LinkedHashMap;
import java.util.Map;

public class NumberString {
	
	public static void main(String[] args) {
		NumberString ns = new NumberString();
		String charset = null;
		int result = 0;
		
		charset = "one4seveneight";
		result = ns.convertStringToNumber(charset);
		
		System.out.println("입력 값: " + charset);
		System.out.println("결과 : " + result);
		
	}
	
	public int convertStringToNumber(String charset) {
        int result = 0;
        
        LinkedHashMap<Integer, String> numberMap = new LinkedHashMap<Integer, String>();
        numberMap.put(0, "zero");
        numberMap.put(1, "one");
        numberMap.put(2, "two");
        numberMap.put(3, "three");
        numberMap.put(4, "four");
        numberMap.put(5, "five");
        numberMap.put(6, "six");
        numberMap.put(7, "seven");
        numberMap.put(8, "eight");
        numberMap.put(9, "nine");
        
        for (Map.Entry<Integer, String> entry : numberMap.entrySet()) {
        	Integer key = entry.getKey();
        	String val = entry.getValue();
        	
        	if(charset.indexOf(val) != -1) {
        		charset = charset.replaceAll(val, String.valueOf(key));
        	}
		}
        
        result = Integer.parseInt(charset);
        
        return result;
    }
}


//s						result
//"one4seveneight"		1478
//"23four5six7"			234567
//"2three45sixseven"	234567
//"123"					123