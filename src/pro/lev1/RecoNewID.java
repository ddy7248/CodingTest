package pro.lev1;

public class RecoNewID {

	public static void main(String[] args) {
		RecoNewID reco = new RecoNewID();
		String new_id = "...abc...";
		String id = reco.solution(new_id);
		System.out.println("id: "+id);
		
		System.out.println("Commit test!!");
		System.out.println("Commit test2!");
	}

	public String solution(String new_id) {
		String conId = "";

		//소문자로 치환
		conId = new_id.toLowerCase();

		//특수문자 확인 
		char[] id = conId.toCharArray();
		String temp = "";
		for (int i = 0; i < id.length; i++) {
			char ch = id[i];

			if('a'<=ch && ch<='z') { temp+=ch; continue;}
			else if('0'<=ch && ch<='9') { temp+=ch; continue;}
			else if(ch=='-' || ch=='_' || ch=='.') { temp+=ch; continue; }
		}
		conId = temp;
		
		//연속된 . 제거
		id = conId.toCharArray();
		temp = "";
		for (int i = 0; i < id.length; i++) {
			char ch = id[i];
			char nextCh = ' ';
			if( i != id.length-1) nextCh = id[i+1];
			
			if(ch=='.' && nextCh=='.') { continue; }
			
			temp += ch;
		}
		conId = temp;
		
		//.위치에 따른 제거
		if(conId.startsWith(".")) conId = conId.substring(1);
		if(conId.endsWith(".")) conId = conId.substring(0, conId.length()-1);
		
		//빈문자열일 경우 'a' 대입
		if(conId.equals("") || conId.equals(" ")) conId = "a";
		
		//길이 조절
		if(conId.length()>15) {
			conId = conId.substring(0, 15);
			if(conId.endsWith(".")) conId = conId.substring(0, conId.length()-1);
		}
		
		while(conId.length()<=2) {
			conId += conId.charAt(conId.length()-1);
		}

		return conId;
	}
}


//no	new_id	result
//예1	"...!@BaT#*..y.abcdefghijklm"	"bat.y.abcdefghi"
//예2	"z-+.^."	"z--"
//예3	"=.="	"aaa"
//예4	"123_.def"	"123_.def"
//예5	"abcdefghijklmn.p"	"abcdefghijklmn"