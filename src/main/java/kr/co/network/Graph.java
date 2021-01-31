package kr.co.network;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Graph {
	private Map<Station, List<Station>> adjStations;

	Graph() {
		this.adjStations = new HashMap<>();
	}

	void addStation(Station station) {
		adjStations.putIfAbsent(station, new ArrayList<>());
	}

	void removeStation(Station station) {
		adjStations.values().stream().forEach(e -> e.remove(station));
		adjStations.remove(station);
	}

	void addRoute(Station departure, Station destination) {
		adjStations.get(departure).add(destination);
		adjStations.get(destination).add(departure);
	}

	void addRouteOneDirection(Station departure, Station destination){
		adjStations.get(departure).add(destination);
	}

	void removeRoute(Station station1, Station station2) {
		List<Station> eV1 = adjStations.get(station1);
		List<Station> eV2 = adjStations.get(station2);
		if (eV1 != null)
			eV1.remove(station2);
		if (eV2 != null)
			eV2.remove(station1);
	}

	List<Station> getAdjStations(Station station) {
		return adjStations.get(station);
	}

	int size(){
		return adjStations.size();
	}

	String printGraph() {
		StringBuffer sb = new StringBuffer();
		for(Station station : adjStations.keySet()) {
			sb.append(station.name());
			sb.append("-");
			sb.append(adjStations.get(station).stream().map(Station::name).collect(Collectors.joining(",")));
			sb.append(" ");
		}
		return sb.toString();
	}
}