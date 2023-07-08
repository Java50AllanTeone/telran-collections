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
	void testRateLocal() {
		for (int i = 0; i < 1_000; i++) {
			lt.rateCombination(choise);
		}
	}
	
	
	void testRate1() {
		for (int i = 0; i < 1_000; i++) {
			lt.rateCombination1(choise);
		}
	}

}
