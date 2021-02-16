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
		adjStations.values().forEach(edges -> edges.forEach(edge -> {
			if(edge.getDeparture().equals(station)){
				edges.remove(edge);
			}
		}));
		adjStations.remove(station);
	}

	void addEdge(Station departure, Station destination) {
		adjStations.get(departure).add(new Edge(departure, destination));
		adjStations.get(destination).add(new Edge(destination, departure));
	}

	void addEdge(Station departure, Station destination, int time){
		adjStations.get(departure).add(new Edge(departure, destination, time));
		adjStations.get(destination).add(new Edge(destination, departure, time));
	}

	void addEdgeOneDirection(Station departure, Station destination){
		adjStations.get(departure).add(new Edge(departure, destination));
	}

	void removeEdge(Station station1, Station station2) {
		List<Edge> eV1 = adjStations.get(station1);
		List<Edge> eV2 = adjStations.get(station2);
		if (eV1 != null)
			eV1.remove(new Edge(station1, station2));
		if (eV2 != null)
			eV2.remove(new Edge(station2, station1));
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
}