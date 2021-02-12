package kr.co.network;

import java.util.HashMap;
import java.util.Map;

public class Search {
	private Map<Station, Boolean> visited = new HashMap<>();

	public void dfs(Station station){
		DataLoader dataLoader = new DataLoader();
		Graph graph = dataLoader.createGraph();
		graph.dfsUtil(station, visited);
	}

	public void bfs(Station station){
		DataLoader dataLoader = new DataLoader();
		Graph graph = dataLoader.createGraph();
		graph.bfsUtil(station, visited);
	}

}
