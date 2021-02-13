package kr.co.network;

import org.junit.jupiter.api.Test;

public class SearchTest {
	@Test
	public void dfsTest(){
		DataLoader dataLoader = new DataLoader();
		Graph graph = dataLoader.createGraph();
		Search search = new Search(graph);
		search.dfs(Station.builder().name("당고개").line("04호선").build());
	}

	@Test
	public void bfsTest(){
		DataLoader dataLoader = new DataLoader();
		Graph graph = dataLoader.createGraph();
		Search search = new Search(graph);
		search.bfs(Station.builder().name("당고개").line("04호선").build());
	}
	
}
