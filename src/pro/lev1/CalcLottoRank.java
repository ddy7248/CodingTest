package pro.lev1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalcLottoRank {

	
	public static void main(String[] args) {
		int[] lottos = {44, 1, 0, 0, 31, 25};
		int[] winNums = {31, 10, 45, 1, 6, 19};
		
		int[] result = getLottoRank(lottos, winNums);

		for (int e : result) {
			System.out.println(e);
		}
		
	}
		
	public static int[] getLottoRank(int[] lottos, int[] win_nums) {
        int[] lottoRank = new int[2];
        
        List<Integer> lottoList = new ArrayList<Integer>();
        
        for (int i : lottos) {
			lottoList.add(i);
		}
        
        for(int i = 0; i < lottoList.size(); i++) {
        	for(int j = 0; j < win_nums.length; j++) {
        		if(lottoList.get(i) == win_nums[j]) {
        			lottoList.remove(i);
        			i--;
        			break;
        		}
        	}
        }
        
        Map<Integer, Integer> rankMap = new HashMap<Integer, Integer>();
        rankMap.put(0, 1);
        rankMap.put(1, 2);
        rankMap.put(2, 3);
        rankMap.put(3, 4);
        rankMap.put(4, 5);
        rankMap.put(5, 6);
        rankMap.put(6, 6);
        
        lottoRank[1] = rankMap.get(lottoList.size());
        
        if(lottoList.size() > 0) {
        	for(int i=0;i<lottoList.size();i++) {
        		if(lottoList.get(i) == 0) {
        			lottoList.remove(i);
        			i--;
        		}
        	}
        }
        
        lottoRank[0] = rankMap.get(lottoList.size());
        
        
        return lottoRank;
    }
	
}

//lottos	win_nums	result
//[44, 1, 0, 0, 31, 25]	[31, 10, 45, 1, 6, 19]	[3, 5]
//[0, 0, 0, 0, 0, 0]	[38, 19, 20, 40, 15, 25]	[1, 6]
//[45, 4, 35, 20, 3, 9]	[20, 9, 3, 45, 4, 35]	[1, 1]