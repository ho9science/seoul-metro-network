package kr.co.network;

import java.util.*;

public class Search {
	private Map<Station, Boolean> visited = new HashMap<>();
	private Map<Station, List<Edge>> adjStations = new HashMap<>();

	public Search(Graph graph){
		this.adjStations = graph.all();
	}

	private void dfsUtil(Station station, Map<Station, Boolean> visited) {
		visited.put(station, true);

		System.out.print(station.name() + "("+station.line()+")| ->");

		ListIterator<Edge> s = adjStations.get(station).listIterator();
		while (s.hasNext()) {
			Station nextStation = s.next().getDestination();
			if (visited.get(nextStation)==null)
				dfsUtil(nextStation, visited);
		}
	}

	private void bfsUtil(Station station, Map<Station, Boolean> visited){
		LinkedList<Station> queue = new LinkedList<>();

		visited.put(station, true);
		queue.add(station);

		while (!queue.isEmpty()) {
			station = queue.poll();
			System.out.print(station.name() + "("+station.line()+")| ->");

			Iterator<Edge> edges = adjStations.get(station).listIterator();
			while (edges.hasNext()) {
				Station nextStation = edges.next().getDestination();
				if (visited.get(nextStation) == null) {
					visited.put(nextStation, true);
					queue.add(nextStation);
				}
			}
		}
	}

	public void dfs(Station station){
		dfsUtil(station, visited);
	}

	public void bfs(Station station){
		bfsUtil(station, visited);
	}

}
