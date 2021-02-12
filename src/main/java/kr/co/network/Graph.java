package kr.co.network;


import java.util.*;
import java.util.stream.Collectors;

public class Graph {
	private Map<Station, List<Edge>> adjStations;

	Graph() {
		this.adjStations = new HashMap<>();
	}

	Station getStation(Station station){
		return adjStations.get(station).stream()
			.map(Edge::getDeparture).findFirst()
			.orElse(Station.builder().build());
	}
	void addStation(Station station) {
		adjStations.putIfAbsent(station, new ArrayList<>());
	}

	void removeStation(Station station) {
		adjStations.values().stream().forEach(e -> e.remove(station));
		adjStations.remove(station);
	}

	void addEdge(Station departure, Station destination) {
		adjStations.get(departure).add(new Edge(departure, destination));
		adjStations.get(destination).add(new Edge(destination, departure));
	}

	void addEdgeOneDirection(Station departure, Station destination){
		adjStations.get(departure).add(new Edge(departure, destination));
	}

	void removeEdge(Station station1, Station station2) {
		List<Edge> eV1 = adjStations.get(station1);
		List<Edge> eV2 = adjStations.get(station2);
		if (eV1 != null)
			eV1.remove(station2);
		if (eV2 != null)
			eV2.remove(station1);
	}

	List<Edge> getAdjStations(Station station) {
		return adjStations.get(station);
	}

	int size(){
		return adjStations.size();
	}

	Map<Station, List<Edge>> all(){
		return this.adjStations;
	}

	String printGraph() {
		StringBuilder sb = new StringBuilder();
		for(Station station : adjStations.keySet()) {
			sb.append(station.name());
			sb.append("-");
			sb.append(adjStations.get(station).stream()
				.map(Edge::getDestination).map(Station::name)
				.collect(Collectors.joining(",")));
			sb.append(" ");
		}
		return sb.toString();
	}

	protected void dfsUtil(Station station, Map<Station, Boolean> visited) {
		// 현재 노드를 방문한 것으로 표시하고 값을 출력
		visited.put(station, true);

		System.out.print(station + "("+station.line()+")| ->");

		// 방문한 노드와 인접한 모든 노드를 가져온다.
		ListIterator<Edge> s = adjStations.get(station).listIterator();
		while (s.hasNext()) {
			Station nextStation = s.next().getDestination();
			// 방문하지 않은 노드면 해당 노드를 시작 노드로 다시 DFSUtil 호출
			if (visited.get(nextStation)==null)
				dfsUtil(nextStation, visited); // 순환 호출
		}
	}

	protected void bfsUtil(Station station, Map<Station, Boolean> visited){
		// Create a queue for BFS
		LinkedList<Station> queue = new LinkedList<>();

		// Mark the current node as visited and enqueue it
		visited.put(station, true);
		queue.add(station);

		while (!queue.isEmpty())
		{
			// Dequeue a vertex from queue and print it
			station = queue.poll();
			System.out.print(station + "("+station.line()+")| ->");

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it
			Iterator<Edge> edges = adjStations.get(station).listIterator();
			while (edges.hasNext())
			{
				Station nextStation = edges.next().getDestination();
				if (visited.get(nextStation) == null) {
					visited.put(nextStation, true);
					queue.add(nextStation);
				}
			}
		}
	}

	protected void dijkstraUtil(Graph graph, Station source, Station target, Map<Station, Boolean> visited){
		int minLength = Integer.MAX_VALUE;
		LinkedList<Station> queue = new LinkedList<>();
		Queue<Station> shortestDistance = new PriorityQueue<>();
		Station station = graph.getStation(source);
		System.out.println(station);
		queue.add(station);
		visited.put(station, true);

		while (!queue.isEmpty())
		{
			// Dequeue a vertex from queue and print it
			source = queue.poll();
			System.out.print(source + "("+source.line()+")| ->");

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it
			Iterator<Edge> edges = adjStations.get(source).listIterator();
			while (edges.hasNext())
			{
				Edge ed = edges.next();
				Station nextStation = ed.getDestination();
				if (ed.getTime() < minLength) {
//					visited.put(nextStation, true);
					queue.add(nextStation);
					minLength += ed.getTime();
				}
			}
		}
	}
}