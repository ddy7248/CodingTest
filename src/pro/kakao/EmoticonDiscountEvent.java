package pro.kakao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * [문제 설명]
 * 카카오톡에서는 이모티콘을 무제한으로 사용할 수 있는 이모티콘 플러스 서비스 가입자 수를 늘리려고 합니다.
 * 이를 위해 카카오톡에서는 이모티콘 할인 행사를 하는데, 목표는 다음과 같습니다.
 *
 * 이모티콘 플러스 서비스 가입자를 최대한 늘리는 것.
 * 이모티콘 판매액을 최대한 늘리는 것.
 * 1번 목표가 우선이며, 2번 목표가 그 다음입니다.
 *
 * 이모티콘 할인 행사는 다음과 같은 방식으로 진행됩니다.
 *
 * n명의 카카오톡 사용자들에게 이모티콘 m개를 할인하여 판매합니다.
 * 이모티콘마다 할인율은 다를 수 있으며, 할인율은 10%, 20%, 30%, 40% 중 하나로 설정됩니다.
 * 카카오톡 사용자들은 다음과 같은 기준을 따라 이모티콘을 사거나, 이모티콘 플러스 서비스에 가입합니다.
 *
 * 각 사용자들은 자신의 기준에 따라 일정 비율 이상 할인하는 이모티콘을 모두 구매합니다.
 * 각 사용자들은 자신의 기준에 따라 이모티콘 구매 비용의 합이 일정 가격 이상이 된다면, 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입합니다.
 * 다음은 2명의 카카오톡 사용자와 2개의 이모티콘이 있을때의 예시입니다.
 *
 * 사용자	비율		가격
 * 1		40		10,000
 * 2		25		10,000
 * 이모티콘		가격
 * 1		7,000
 * 2		9,000
 * 1번 사용자는 40%이상 할인하는 이모티콘을 모두 구매하고, 이모티콘 구매 비용이 10,000원 이상이 되면 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입합니다.
 * 2번 사용자는 25%이상 할인하는 이모티콘을 모두 구매하고, 이모티콘 구매 비용이 10,000원 이상이 되면 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입합니다.
 *
 * 1번 이모티콘의 가격은 7,000원, 2번 이모티콘의 가격은 9,000원입니다.
 *
 * 만약, 2개의 이모티콘을 모두 40%씩 할인한다면, 1번 사용자와 2번 사용자 모두 1,2번 이모티콘을 구매하게 되고, 결과는 다음과 같습니다.
 *
 * 사용자	구매한 이모티콘	이모티콘 구매 비용	이모티콘 플러스 서비스 가입 여부
 * 1	1, 2	9,600	X
 * 2	1, 2	9,600	X
 * 이모티콘 플러스 서비스 가입자는 0명이 늘어나고 이모티콘 판매액은 19,200원이 늘어납니다.
 *
 * 하지만, 1번 이모티콘을 30% 할인하고 2번 이모티콘을 40% 할인한다면 결과는 다음과 같습니다.
 *
 * 사용자	구매한 이모티콘	이모티콘 구매 비용	이모티콘 플러스 서비스 가입 여부
 * 1	2	5,400	X
 * 2	1, 2	10,300	O
 * 2번 사용자는 이모티콘 구매 비용을 10,000원 이상 사용하여 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입하게 됩니다.
 * 따라서, 이모티콘 플러스 서비스 가입자는 1명이 늘어나고 이모티콘 판매액은 5,400원이 늘어나게 됩니다.
 *
 * 카카오톡 사용자 n명의 구매 기준을 담은 2차원 정수 배열 users, 이모티콘 m개의 정가를 담은 1차원 정수 배열 emoticons가 주어집니다. 이때, 행사 목적을 최대한으로 달성했을 때의 이모티콘 플러스 서비스 가입 수와 이모티콘 매출액을 1차원 정수 배열에 담아 return 하도록 solution 함수를 완성해주세요.
 */

/**
 * [제한사항]
 * 1 ≤ users의 길이 = n ≤ 100
 * users의 원소는 [비율, 가격]의 형태입니다.
 * users[i]는 i+1번 고객의 구매 기준을 의미합니다.
 *
 * 비율% 이상의 할인이 있는 이모티콘을 모두 구매한다는 의미입니다.
 * 1 ≤ 비율 ≤ 40
 * 가격이상의 돈을 이모티콘 구매에 사용한다면, 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입한다는 의미입니다.
 * 100 ≤ 가격 ≤ 1,000,000
 * 가격은 100의 배수입니다.
 *
 * 1 ≤ emoticons의 길이 = m ≤ 7
 * emoticons[i]는 i+1번 이모티콘의 정가를 의미합니다.
 *
 * 100 ≤ emoticons의 원소 ≤ 1,000,000
 * emoticons의 원소는 100의 배수입니다.
 */

public class EmoticonDiscountEvent {

	// 입출력 예시
	// users																				emoticons					result
	// [[40, 10000], [25, 10000]]															[7000, 9000]				[1, 5400]
	// [[40, 2900], [23, 10000], [11, 5200], [5, 5900], [40, 3100], [27, 9200], [32, 6900]]	[1300, 1500, 1600, 4900]	[4, 13860]
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
		int[] bestResult = {0, 0};
		int[] discountPolicy = {10, 20, 30, 40};		// 이모티콘 할인율 선택 목록
		List<LinkedHashMap<Integer, Integer>> discountCaseList = new ArrayList<LinkedHashMap<Integer,Integer>>();

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

//		emoticonDiscountMap.put(0, discountPolicy[2]);
//		emoticonDiscountMap.put(1, discountPolicy[3]);

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
		
		// 이모티콘 Best 판매결과 세팅
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
	
//	public static void getDiscountCase(int depth, int emoLen) {
//		LinkedHashMap<Integer, Integer> emoticonDiscountMap = new LinkedHashMap<Integer, Integer>();
//		int discountPolicyLen = 0;
//
//		if(depth == emoLen) {
//			discountCaseList.add(emoticonDiscountMap);
//			return;
//		}
//
//		for (int i = 0; i < discountPolicyLen; i++) {
//			emoticonDiscountMap.put(depth, discountPolicy[i]);
//			getDiscountCase(depth + 1, emoLen);
//		}
//	}
	
}

