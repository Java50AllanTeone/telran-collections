package telran.additional;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;



public class Lottery {
	private final int[] WINNING_RANGE = {1, 49};
	private final int[] WINNING_COMBINATION;
	
	int[] lTable = new int[WINNING_RANGE[1] + 1];
	Set<Integer> lTableSet = new HashSet<>(14);

	
	public Lottery(int[] winningCombination) {
		this.WINNING_COMBINATION = winningCombination;

		for (var num : winningCombination) {
			lTableSet.add(num);
		}
	}
	
	
	//1.855s
	public int rateCombinationLoop(int playerCombination[]) {
		int count = 0;

		for (int i = 0; i < playerCombination.length; i++) {
			count += lTable[playerCombination[i]]++;
			count += lTable[WINNING_COMBINATION[i]]++;
		}
		for (int i = 0; i < lTable.length; i++) {
			lTable[i] = 0;
		}
		return count;
	}
	
	
	// 1.988s
	public int rateCombinationOuter(int playerCombination[]) {
		int count = 0;

		for (int i = 0; i < playerCombination.length; i++) {
			count += lTable[playerCombination[i]]++;
			count += lTable[WINNING_COMBINATION[i]]++;
		}
		Arrays.fill(lTable, 0);
		return count;
	}

	// 2.171s
	public int rateCombinationInner(int playerCombination[]) {
		int[] lTable = new int[WINNING_RANGE[1] + 1];
		int count = 0;

		for (int i = 0; i < playerCombination.length; i++) {
			count += lTable[playerCombination[i]]++;
			count += lTable[WINNING_COMBINATION[i]]++;
		}
		return count;
	}

	// 18.982s
//	public int rateCombinationSetInner(int playerCombination[]) {
//		Set<Integer> lTable = new HashSet<>(14);
//
//		for (int i = 0; i < playerCombination.length; i++) {
//			lTable.add(playerCombination[i]);
//			lTable.add(WINNING_COMBINATION[i]);
//		}
//		return playerCombination.length * 2 - lTable.size();
//	}
//
//	
//	//12.193s
//	public int rateCombinationSetOuter(int playerCombination[]) {
//		for (int i = 0; i < playerCombination.length; i++) {
//			lTableSet.add(playerCombination[i]);
//		}
//		var newSize = lTableSet.size();
//		lTableSet.clear();
//		return playerCombination.length * 2 - newSize;
//	}

	public static void main(String[] args) {
		Lottery loto = new Lottery(new int[] { 5, 2, 17, 49, 43, 7, 9 });
		if (loto.rateCombinationOuter(new int[] { 3, 5, 12, 17, 44, 10, 7 }) != 3) {
			System.out.println("Test 1 failed");
			return;
		}
		if (loto.rateCombinationOuter(new int[] { 3, 5, 12, 17, 49, 9, 7 }) != 5) {
			System.out.println("Test 2 failed");
			return;
		}
		System.out.println("Success");
	}

}
