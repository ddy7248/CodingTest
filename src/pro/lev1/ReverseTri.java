package pro.lev1;

public class ReverseTri {
	
	public static void main(String[] args) {
		ReverseTri tester = new ReverseTri();
		int n = 45;
		System.out.println(n+"를 3진법으로 뒤집은 수: "+tester.solution(n));
	}
	
	public int solution(int n) {
        int answer = 0;
        String triStr = "";
        String temp = "";
        
        //3진법 전환
        while(n/3>0) {
        	triStr = n%3 + triStr;
        	n /= 3;
        }
        triStr = n%3 + triStr;
        
        //뒤집기
        for(int i=0;i<triStr.length();i++) {
        	temp = triStr.charAt(i)+temp;
        }
        System.out.println(triStr+" "+temp);
        
        //10진법으로 변환
        for(int i=0;i<temp.length();i++) {
        	answer += Math.pow(3, i)*(temp.charAt(temp.length()-i-1)-'0');
        }
        
        
        return answer;
    }
	
	
}

//n	result
//45	7
//125	229
