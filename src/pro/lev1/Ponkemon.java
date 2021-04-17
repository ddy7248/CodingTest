package pro.lev1;

import java.util.Stack;

public class Ponkemon {

	public static void main(String[] args) {
		int[] nums = {3,3,3,2,2,4};
		int result = findMaxPonkemonType(nums);
		
		System.out.println("최대 종류 수 : " + result);
		
	}
	
	public static int findMaxPonkemonType(int[] nums) {
	        int maxPonkemon = 0;
	        int carryNum = 0;
	        int cntType = 0;
	        
	        carryNum = nums.length/2;
	        
	        Stack<Integer> tempPon = new Stack<Integer>();
	        
	        for(int i=0;i<nums.length;i++) {
	        	if(!tempPon.contains(nums[i])) {
	        		tempPon.push(nums[i]);
	        		cntType++;
	        	}
	        }

	        maxPonkemon = carryNum > cntType ? cntType : carryNum;
	        
	        return maxPonkemon;
    }
	
	public static int pickPonkemon(int[] ponkemonList, int startIdx) {
		int ponkemon = 0;
		
		ponkemon = ponkemonList[startIdx];
		
		return ponkemon;
	}
}


//nums	result
//[3,1,2,3]	2
//[3,3,3,2,2,4]	3
//[3,3,3,2,2,2]	2