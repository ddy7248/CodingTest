package pro.kakao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class PersonalInfoCollection {

	public static void main(String[] args) {
		String today = "2022.05.19";																// "YYYY.MM.DD" 형태의 오늘 날짜
		String[] terms = {"A 6", "B 12", "C 3"};													// "약관 종류 유효기간"
		String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};		// "날짜 약관 종류"
		
		int[] resultTarget = {};
		
		resultTarget = sortExpiredPersonalInfo(today, terms, privacies);
		
		System.out.println("유효기간 만료 대상: " + Arrays.toString(resultTarget));
		
	}
	
   public static int[] sortExpiredPersonalInfo(String today, String[] terms, String[] privacies) {
	    int idx = 0;
        int[] resultTarget = {};																// 약관 만료 대상(Array)
        List<Integer> resultTargetList = new ArrayList<Integer>();								// 약관 만료 대상(List) 
        HashMap<String, String> termsValidityMap = new HashMap<String, String>();				// 약관 종류:유효기간 매핑 정보 
        LinkedHashMap<Integer, String> expiredDateMap = new LinkedHashMap<Integer, String>();	// 약관 만료 일자 매핑 정보
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

        // 약관 종류 - 유효기간 매핑
        for(String term : terms) {
        	String[] termInfo = term.split(" ");
        	String termType = "";		// 약관 종류
        	String termValidity = "";	// 약관 유효기간(월)
        	
        	termType = termInfo[0];
        	termValidity = termInfo[1];

        	termsValidityMap.put(termType, termValidity);
        }
        
        // 수집된 개인정보 유효기간 만료 확인
        for (String privacy : privacies) {
        	idx++;
        	
			String[] personalInfo = privacy.split(" ");
			
			String acceptDay = personalInfo[0];		// 약관 동의 일자(YYYY.MM.DD)
			String termsType = personalInfo[1];		// 약관 종류
			String expiredDay = "";					// 약관 만료 일자(YYYY.MM.DD)
        	String termValidity = "";				// 약관 유효기간(월)

			termValidity = termsValidityMap.get(termsType);
			
			try {
				Date acceptDayDateFormat = sdf.parse(acceptDay);
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(acceptDayDateFormat);
				cal.add(Calendar.MONTH, Integer.valueOf(termValidity));
				cal.add(Calendar.DATE, -1);
				
				acceptDayDateFormat = cal.getTime();
				if(acceptDayDateFormat.getDate() > 28) {
					acceptDayDateFormat.setDate(28);
				}		

				expiredDay = sdf.format(acceptDayDateFormat);
				
			} catch(ParseException e) {
				System.out.println("Parse error!!");
			}
			
			expiredDateMap.put(idx, expiredDay);
		}
        
        // today 기준 만료 대상 추출
        expiredDateMap.forEach((no, expiredDay) -> {
        	try {
        		Date todayDateFormat = sdf.parse(today);
            	Date expiredDayDateFormat = sdf.parse(expiredDay);

            	if(expiredDayDateFormat.compareTo(todayDateFormat) < 0) {
            		resultTargetList.add(no);
            	}
            	
        	} catch(ParseException e) {
        		System.out.println("Parse Error!!");
        	}
        			
        });
        
        // List<Integer> => int[]
        resultTarget = resultTargetList.stream()
        					.filter(i -> i != null)
        					.mapToInt(Integer::intValue)
        					.sorted()
        					.toArray();
        
        return resultTarget;
    }
}


//today			terms					privacies																			result
//"2022.05.19"	["A 6", "B 12", "C 3"]	["2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"]					[1, 3]
//"2020.01.01"	["Z 3", "D 5"]			["2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"]	[1, 4, 5]