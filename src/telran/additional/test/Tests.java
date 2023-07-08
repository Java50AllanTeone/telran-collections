package telran.additional.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.additional.Lottery;

class Tests {
	int[] win = new int[] {1, 5, 7, 15, 22, 44, 3};
	int[] choise = new int[] {13, 2, 1, 43, 23, 7, 9};
	Lottery lt;
	
	@BeforeEach
	void init() {
		lt = new Lottery(win);
	}

	@Test
	void testRateInner() {
		for (int i = 0; i < 100_000_000; i++) {
			lt.rateCombinationInner(choise);
		}
	}
	
	@Test
	void testRateOuter() {
		for (int i = 0; i < 100_000_000; i++) {
			lt.rateCombinationOuter(choise);
		}
	}
	
	@Test
	void testRateSetInner() {
		for (int i = 0; i < 100_000_000; i++) {
			lt.rateCombinationSetInner(choise);
		}
	}
	
	@Test
	void testRateSetOuter() {
		for (int i = 0; i < 100_000_000; i++) {
			lt.rateCombinationSetOuter(choise);
		}
	}

}
