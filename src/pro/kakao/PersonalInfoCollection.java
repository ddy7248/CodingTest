package pro.kakao;

import java.util.ArrayList;
import java.util.Arrays;
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
        int[] resultTarget = {};
        List<Integer> resultTargetList = new ArrayList<Integer>();
        

        // 약관 종류 - 유효기간 매핑
        
        
        // 수집된 개인정보 유효기간 만료 확인
        
        
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