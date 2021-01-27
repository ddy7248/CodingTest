package pro.lev1;

import java.util.Arrays;

public class LostGymSuit {

	public static void main(String[] args) {
		LostGymSuit tester = new LostGymSuit();
		int n = 3;
		int[] lost = {1,2};
		int[] reserve = {2,3};
		int stuMaxNum = tester.solution(n, lost, reserve);
		System.out.println("최대 학생 수: " + stuMaxNum);
	}
	
	public int solution(int n, int[] lost, int[] reserve) {
		
		Arrays.sort(lost);
		Arrays.sort(reserve);
		
		//여벌은 있으나 본인이 도난당했을 경우
		for(int i=0;i<lost.length;i++) {
			for(int j=0;j<reserve.length;j++) {
				if(lost[i] == reserve[j]) {
					lost[i] = 0;
					reserve[j] = 0;
					break;
				}
			}
		}
		
		
		//체육복 빌려주기
		int flag = 0;
        for (int i=0; i<lost.length; i++) {
			for(int j=flag; j<reserve.length; j++) {
				if(lost[i] != 0 && reserve[j] != 0) {
					if(Math.abs(lost[i]-reserve[j]) <= 1) {
						lost[i] = 0;
						reserve[j] = 0;
						flag = j+1;
						break;
					}
				}
			}
		}
		
        //최대 학생 수 구하기
        int stuMaxNum = 0;
        stuMaxNum = n-lost.length;
        for(int i=0;i<lost.length;i++) {
        	if( lost[i] == 0 ) stuMaxNum++;
        }
        
        return stuMaxNum;
    }
}

//n	lost	reserve		return
//5	[2, 4]	[1, 3, 5]		5
//5	[2, 4]	[3]				4
//3	[3]		[1]				2