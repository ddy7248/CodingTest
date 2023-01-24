package pro.kakao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EmoticonDiscountEvent {
	
	static int[] bestResult = {0, 0};
	static int[] discountPolicy = {10, 20, 30, 40};		// 이모티콘 할인율 선택 목록
	static List<LinkedHashMap<Integer, Integer>> discountCaseList = new ArrayList<LinkedHashMap<Integer,Integer>>();

	public static void main(String[] args) {
		int[][] users = {{40, 10000}, {25, 10000}};		// (비율, 가격)
		int[] emoticons = {7000, 9000};					// 이모티콘 가격
		int[] result;
		
		result = getBestDiscountPolicy(users, emoticons);
		System.out.println("Best Result : " + Arrays.toString(result));
	}

	/**
	 * 이모티콘 할인행사 우선순위
	 * 1.이모티콘 플러스 서비스 가입자를 최대한 늘리는 것.
	 * 2.이모티콘 판매액을 최대한 늘리는 것. 
	 */
	public static int[] getBestDiscountPolicy(int[][] users, int[] emoticons) {
		int emoLen = 0;
		int discountPolicyLen = 0;
		
		
		emoLen = emoticons.length;
		discountPolicyLen = discountPolicy.length;
		
		
		// TODO 이모티콘 할인율 배정 LOOP 필요
		LinkedHashMap<Integer, Integer> emoticonDiscountMap = new LinkedHashMap<Integer, Integer>();
		int idx = 0;
		
		for(int emoticon: emoticons) {
			emoticonDiscountMap.put(idx, discountPolicy[3]);
			idx++;
		}

		// 이모티콘 판매결과
		int svcJoinUserCount 		= 0;	// 이모티콘 플러스 서비스 가입자 수
		int emoticonPurchaseTotCost = 0;	// 이모티콘 총 판매액

		// 이모티콘 구매 비용 계산
		for(int[] user: users) {
			int purchaseStandardDiscount	= 0;		// 사용자의 이모티콘 구매 기준 할인율
			int svcJoinStandardCost  		= 0;		// 사용자의 이모티콘 플러스 서비스 가입 기준 가격
			int emoticonPurchaseCost		= 0;		// 이모티콘 구매 총 비용
			boolean svcJoinStatus 	 		= false;	// 이모티콘 플러스 서비스 가입 여부

			purchaseStandardDiscount	= user[0]; 
			svcJoinStandardCost			= user[1];
			
			for (Map.Entry<Integer, Integer> entry : emoticonDiscountMap.entrySet()) {
				Integer no = entry.getKey();
				Integer emotDiscount = entry.getValue();
				
				if(emotDiscount >= purchaseStandardDiscount) {
					emoticonPurchaseCost += emoticons[no] * (100 - emotDiscount) / 100;
				}
			}
			
			if(emoticonPurchaseCost >= svcJoinStandardCost) {
				svcJoinStatus = true;
			}
			
			if(svcJoinStatus) {
				svcJoinUserCount++;
			} else {
				emoticonPurchaseTotCost += emoticonPurchaseCost;
			}
		}
		
		if(svcJoinUserCount > bestResult[0]) {
			bestResult[0] = svcJoinUserCount;
			bestResult[1] = emoticonPurchaseTotCost;
		} else if(svcJoinUserCount == bestResult[0]) {
			if(emoticonPurchaseTotCost > bestResult[1]) {
				bestResult[1] = emoticonPurchaseTotCost;
			}
		}
		
		return bestResult;
	}
	
	public static void getDiscountCase(int depth, int emoLen) {
		LinkedHashMap<Integer, Integer> emoticonDiscountMap = new LinkedHashMap<Integer, Integer>();
		int discountPolicyLen = 0;
		
		if(depth == emoLen) {
			discountCaseList.add(emoticonDiscountMap);
			return;
		} 
		
		for (int i = 0; i < discountPolicyLen; i++) {
			emoticonDiscountMap.put(depth, discountPolicy[i]);
			getDiscountCase(depth + 1, emoLen);
		}
	}
	
}


// users																				emoticons					result
// [[40, 10000], [25, 10000]]															[7000, 9000]				[1, 5400]
// [[40, 2900], [23, 10000], [11, 5200], [5, 5900], [40, 3100], [27, 9200], [32, 6900]]	[1300, 1500, 1600, 4900]	[4, 13860]