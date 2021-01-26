package pro.lev2;

import java.util.Stack;

public class Kakao {
	public static void main(String[] args) {
		Kakao comp = new Kakao();
		int strLen = 0;
		String str = "ababcdcdababcdcd";
		
		strLen = comp.compStr(str);
		System.out.println("가장 짧은 길이: "+strLen);
	}
	
	public int compStr(String s) {
        int strLen = 0;
        int unit = 0;
        int arbitLen = 0;
        
        unit = 1;
        strLen = s.length();
        
        while(s.length()>unit) {
        	//문자열 분리
        	Stack<String> cutStr = new Stack<String>();
        	for (int i = 0; i < s.length(); i+=unit) {
        		if(i+unit>s.length()-1)
        			cutStr.push(s.substring(i,s.length()));
        		else
        			cutStr.push(s.substring(i, i+unit));
			}
        	
			/*
			 * for (String e : cutStr) { System.out.println(e); }
			 * System.out.println("=====");
			 */
        	
        	//문자열 형태 만들기
        	String temp = "";
			/*
			 * for (String e : cutStr) { temp += e; }
			 */
        	
        	int cnt[] = new int[cutStr.size()];
        	boolean flag[] = new boolean[cutStr.size()];
        	
        	//문자열 비교
        	for (int i = 0; i < cutStr.size()-1; i++) {
        		if(cutStr.get(i).equals(cutStr.get(i+1))) {
        			//if(cnt>0)
       				cnt[i] = cnt[i] + cnt[i-1];
        			flag[i-1] = true;
        		}
        		else {
        			if(flag[i-1]) {
        			}
        			else
        				temp += cutStr.get(i);
        		}
			}
        	
        	//분리 단위 증가
        	unit++;
        	
        	arbitLen = temp.length();
        	if(arbitLen<strLen) strLen = arbitLen;
        }
                
        return strLen;
    }
	
}

//s	result
//"aabbaccc"	7
//"ababcdcdababcdcd"	9
//"abcabcdede"	8
//"abcabcabcabcdededededede"	14
//"xababcdcdababcdcd"	17