package kr.co.network;


import java.util.*;
import java.util.stream.Collectors;

public class Dijkstra {
	private Set<Station> settledStations;
	private Set<Station> unSettledStations;
	private Map<Station, Station> predecessors;
	private Map<Station, Integer> distance;
	private Map<Station, List<Edge>> neighbor;

	public Dijkstra(Graph graph) {
		this.neighbor = graph.all();
	}

	public void execute(Station source) {
		settledStations = new HashSet<>();
		unSettledStations = new HashSet<>();
		distance = new HashMap<>();
		predecessors = new HashMap<>();
		distance.put(source, 0);
		unSettledStations.add(source);
		while (!unSettledStations.isEmpty()) {
			Station station = getMinimum(unSettledStations);
			settledStations.add(station);
			unSettledStations.remove(station);
			findMinimalDistances(station);
		}
	}

	private void findMinimalDistances(Station station) {
		List<Edge> adjacentEdge = neighbor.get(station);
		for (Station target : adjacentEdge.stream().map(Edge::getDestination)
			.filter(s -> !isSettled(s))
			.collect(Collectors.toList())) {
			if (getShortestDistance(target) > getShortestDistance(station)
				+ getDistance(station, target)) {
				distance.put(target, getShortestDistance(station)
					+ getDistance(station, target));
				predecessors.put(target, station);
				unSettledStations.add(target);
			}
		}
	}

	private int getDistance(Station source, Station destination) {
		for (Edge edge : neighbor.get(source)) {
			if (edge.getDestination().equals(destination)) {
				return edge.getTime();
			}
		}
		throw new RuntimeException("Should not happen");
	}

	private Station getMinimum(Set<Station> stations) {
		Station minimum = null;
		for (Station station : stations) {
			if (minimum == null) {
				minimum = station;
			} else {
				if (getShortestDistance(station) < getShortestDistance(minimum)) {
					minimum = station;
				}
			}
		}
		return minimum;
	}

	private boolean isSettled(Station station) {
		return settledStations.contains(station);
	}

	private int getShortestDistance(Station destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	public List<Station> getPath(Station destination) {
		List<Station> path = new LinkedList<>();
		Station step = destination;
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		Collections.reverse(path);
		return path;
	}
}
