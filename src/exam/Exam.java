package exam;

public class Exam {
	public static void main(String[] args) {
		String str = "...!@BaT#*..y.abcdefghijklm12";
		String temp = str.replaceAll("[^-_.a-z0-9]", "");
		System.out.println("String: "+temp);
	}
}

//no	new_id	result
//예1	"...!@BaT#*..y.abcdefghijklm"	"bat.y.abcdefghi"
//예2	"z-+.^."	"z--"
//예3	"=.="	"aaa"
//예4	"123_.def"	"123_.def"
//예5	"abcdefghijklmn.p"	"abcdefghijklmn"