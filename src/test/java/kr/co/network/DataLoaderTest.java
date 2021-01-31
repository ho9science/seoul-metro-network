package kr.co.network;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataLoaderTest {
	@Test
	public void dataLoadTest(){
		DataLoader dataLoader = new DataLoader();
		Graph graph = dataLoader.load();
		assertTrue(graph.size()==730);
	}
}
