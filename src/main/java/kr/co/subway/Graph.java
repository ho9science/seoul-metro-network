package kr.co.subway;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Graph {
	private Map<Station, List<Station>> adjVertices;

	Graph() {
		this.adjVertices = new HashMap<>();
	}

	void addStation(Station station) {
		adjVertices.putIfAbsent(station, new ArrayList<>());
	}

	void removeStation(Station station) {
		adjVertices.values().stream().forEach(e -> e.remove(station));
		adjVertices.remove(station);
	}

	void addEdge(Station station1, Station station2) {
		adjVertices.get(station1).add(station2);
		adjVertices.get(station2).add(station1);
	}

	void removeEdge(Station station1, Station station2) {
		List<Station> eV1 = adjVertices.get(station1);
		List<Station> eV2 = adjVertices.get(station2);
		if (eV1 != null)
			eV1.remove(station2);
		if (eV2 != null)
			eV2.remove(station1);
	}

	List<Station> getAdjVertices(Station station) {
		return adjVertices.get(station);
	}

	String printGraph() {
		StringBuffer sb = new StringBuffer();
		for(Station station : adjVertices.keySet()) {
			sb.append(station.name());
			sb.append("-");
			sb.append(adjVertices.get(station).stream().map(Station::name).collect(Collectors.joining(",")));
			sb.append(" ");
		}
		return sb.toString();
	}
}