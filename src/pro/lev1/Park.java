package pro.lev1;

import java.util.Iterator;

public class Park {

	public static void main(String[] args) {
		
		int price = 3;
		int money = 20;
		int count = 4;
		long result = 0;
		
		result = calcFee(price, money, count);
		System.out.println(result);
	}
	
    public static long calcFee(int price, int money, int count) {
        long result = -1;
        long reqFee = 0L;
        
        for (int i = 0; i < count; i++) {
			reqFee += (long)price*(i+1);
		}
        
        result = reqFee-(long)money;
        
        result = result > 0 ? result : 0L;  
        
        return result;
    }
}

//price	money	count	result
//3	20	4	10