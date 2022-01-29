package pro.lev2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


public class FeeCalculator {
	
	public static void main(String[] args) {
		FeeCalculator fc = new FeeCalculator();
		int[] fees = {180, 5000, 10, 600};
		String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
		int[] result = fc.calcFees(fees, records);

		Arrays.stream(result).forEach(em -> System.out.println(em));
	}
	
	public int[] calcFees(int[] fees, String[] records) {
        int[] feeResult = {};
        ArrayList<Integer> feeList = new ArrayList<Integer>();
        
        int baseTime = fees[0];		// 기본 시간
        int baseFee = fees[1];		// 기본 요금
        int unitTime = fees[2];		// 단위 시간
        int unitFee = fees[3];		// 단위 요금
        
        Set<String> carSet = new TreeSet<String>();
        Map<String, Date> inOutTimeMap = new HashMap<String, Date>();				// 차량 입차시간
        Map<String, Integer> parkingTimeMap = new TreeMap<String, Integer>();		// 누적 주차시간

        // 차량별 주차 시간 계산
        Arrays.stream(records).forEach( record -> {
			String[] recordArr = record.split(" ");
			
			String inOutTime = recordArr[0];
			String carNumber = recordArr[1];
			String entState = recordArr[2];
			
			// IN / OUT 별 분기
			if(("IN").equals(entState)) {
				Date timeFormat = convertStringToDate(inOutTime);
				
				carSet.add(carNumber);
				inOutTimeMap.put(carNumber, timeFormat);
			} else if(("OUT").equals(entState)){
				// 차량 입차 시간 관리 테이블에서 제거
				Date entTimeFormat = inOutTimeMap.remove(carNumber);
				
				// 차량 누적 주차시간 계산 후 테이블에 추가
				Date leaveTimeFormat = convertStringToDate(inOutTime);
				
				long parkingTime = (leaveTimeFormat.getTime() - entTimeFormat.getTime()) / (1000*60);		// 밀리세컨즈 -> 분
				
//				System.out.println("================");
//				System.out.println(carNumber);
//				System.out.println(entTimeFormat);
//				System.out.println(leaveTimeFormat);
//				System.out.println(parkingTime);
				
				boolean containsCar = parkingTimeMap.containsKey(carNumber);
				if(containsCar) {
					int preParkingTime = parkingTimeMap.remove(carNumber);
					parkingTimeMap.put(carNumber, preParkingTime + (int)parkingTime);
				} else {
					parkingTimeMap.put(carNumber, (int)parkingTime);
				}
			}
        });
        
        // 출차 기록 없을시 주차 시간 업데이트
        if(!inOutTimeMap.isEmpty()) {
        	Date leaveTime = convertStringToDate("23:59");
        	
        	inOutTimeMap.forEach((carNumber, entTime) -> {
        		long parkingTime = (leaveTime.getTime() - entTime.getTime()) / (1000*60);		// 밀리세컨즈 -> 분
        		
        		boolean containsCar = parkingTimeMap.containsKey(carNumber);
				if(containsCar) {
					int preParkingTime = parkingTimeMap.remove(carNumber);
					parkingTimeMap.put(carNumber, preParkingTime + (int)parkingTime);
				} else {
					parkingTimeMap.put(carNumber, (int)parkingTime);
				}
        	});
        }

        System.out.println(parkingTimeMap.entrySet());		// 최종 차량별 누적 주차 시간
        
        // 차량별 주차요금 계산
        parkingTimeMap.forEach((carNumber, parkingTime)-> {
        	int fee = 0;
        	int addTime = 0;
        	int feeTime = 0;
        	
        	addTime = parkingTime - baseTime;
        	
        	if(addTime > 0) {
        		feeTime = addTime%unitTime == 0 ? addTime/unitTime : addTime/unitTime + 1 ;
            	fee = baseFee + unitFee * feeTime; 
        	} else {
        		fee = baseFee;
        	}
        	
        	feeList.add(fee);
        });
        
        int listSize = feeList.size();
        feeResult = feeList.stream().mapToInt(fee -> fee).toArray();
        
        return feeResult;
    }
	
	public Date convertStringToDate(String target) {
		
		Date convertDate = null;
		
		try {
			SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm", Locale.KOREA);
			convertDate = timeFormatter.parse(target);
//			System.out.println(String.valueOf(entTimeFormat));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return convertDate;
	}
	
}

//fees					records							result
//[180, 5000, 10, 600]	["05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"]	
//[14600, 34400, 5000]

//[120, 0, 60, 591]		["16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"]		
//[0, 591]

//[1, 461, 1, 10]		["00:00 1234 IN"]	
//[14841]
 