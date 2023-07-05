package telran.additional;

import java.util.Set;
import java.util.HashSet;



public class Lottery {
	private final int[] WINNING_RANGE = {1, 49};
	private final int[] WINNING_COMBINATION;

		public Lottery(int[] winningCombination) {
			this.WINNING_COMBINATION = winningCombination;
		}


		public int rateCombination(int playerCombination[]) {
			int[] vTable = new int[WINNING_RANGE[1] + 1];
			int count = 0;
			
			for (int i = 0; i < playerCombination.length; i++) {	
				count += vTable[playerCombination[i]];
				vTable[playerCombination[i]]++;	
				count += vTable[WINNING_COMBINATION[i]];
				vTable[WINNING_COMBINATION[i]]++;
			}
			return count;
		}
		
//		public int rateCombination(int playerCombination[]) {
//			Set<Integer> vTable = new HashSet<>(14);
//
//			for (int i = 0; i < playerCombination.length; i++) {	
//				vTable.add(playerCombination[i]);
//				vTable.add(WINNING_COMBINATION[i]);
//			}
//			return playerCombination.length * 2 - vTable.size();
//		}


		public static void main(String[] args) {
			Lottery loto = new Lottery(new int[]{5,2,17,49,43,7,9}) ;
			if (loto.rateCombination(new int[] {3,5,12,17,44,10,7}) != 3) {
				System.out.println("Test 1 failed");
	            return;			
			}
			if (loto.rateCombination(new int[] {3,5,12,17,49,9,7}) != 5) {
				System.out.println("Test 2 failed");
	            return;			
			}
			System.out.println("Success");
		}

}
