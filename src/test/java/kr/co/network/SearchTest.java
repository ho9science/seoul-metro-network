package kr.co.network;

import org.junit.jupiter.api.Test;

public class SearchTest {
	@Test
	public void dfsTest(){
		Search search = new Search();
		search.dfs(Station.builder().name("당고개").line("04호선").build());
	}
}
