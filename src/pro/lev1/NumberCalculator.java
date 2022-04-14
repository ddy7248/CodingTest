package pro.lev1;

import java.util.HashSet;
import java.util.Set;

public class NumberCalculator {

	public static void main(String[] args) {
		
		int[] numbers = {5,8,4,0,6,7,9};
		int result = 0;
		
		NumberCalculator nc = new NumberCalculator();
		result = nc.addMissingNumber(numbers);
		
		System.out.println(result);
		
	}
	
	public int addMissingNumber(int[] numbers) {
        int result = -1;
        Set<Integer> numSet = new HashSet<Integer>();
        
        //1.정수 배열 세팅(0~9)
        for(int i=0; i < 10; i++) {
        	numSet.add(i);
        }
        
        //2.존재하는 숫자 제거
        for (Integer number : numbers) {
			if(numSet.contains(number)) {
				numSet.remove(number);
			}
		}
        
        //3.남은 숫자 더하기
        if(!numSet.isEmpty()){
        	result = 0;
        	
        	for (Integer number : numSet) {
            	result += number;
    		}
        }
        
        return result;
    }
	
	public int minusExistNumber(int[] numbers) {
		int result = 0;
		
		//1. 0~9까지의 합 구하기
		for(int i=0; i < 10; i++) {
			result += i;
		}
		
		//2. 존재하는 숫자 빼기
		for(int number : numbers) {
			result -= number;
		}
		
		return result;
	}
}


//numbers	result
//[1,2,3,4,6,7,8,0]	14
//[5,8,4,0,6,7,9]	6