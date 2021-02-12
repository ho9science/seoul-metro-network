package kr.co.network;


import java.util.*;
import java.util.stream.Collectors;

public class Dijkstra {
	private Set<Station> settledNodes;
	private Set<Station> unSettledNodes;
	private Map<Station, Station> predecessors;
	private Map<Station, Integer> distance;
	private Map<Station, List<Edge>> neighbor;

	public Dijkstra(Graph graph) {
		this.neighbor = graph.all();
	}

	public void execute(Station source) {
		settledNodes = new HashSet<>();
		unSettledNodes = new HashSet<>();
		distance = new HashMap<>();
		predecessors = new HashMap<>();
		distance.put(source, 0);
		unSettledNodes.add(source);
		while (unSettledNodes.size() > 0) {
			Station node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
	}

	private void findMinimalDistances(Station station) {
		List<Edge> adjacentEdge = neighbor.get(station);
		for (Station target : adjacentEdge.stream().map(Edge::getDeparture)
			.filter(s -> !isSettled(s))
			.collect(Collectors.toList())) {
			if (getShortestDistance(target) > getShortestDistance(station)
				+ getDistance(station, target)) {
				distance.put(target, getShortestDistance(station)
					+ getDistance(station, target));
				predecessors.put(target, station);
				unSettledNodes.add(target);
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

	private Station getMinimum(Set<Station> vertexes) {
		Station minimum = null;
		for (Station vertex : vertexes) {
			if (minimum == null) {
				minimum = vertex;
			} else {
				if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
					minimum = vertex;
				}
			}
		}
		return minimum;
	}

	private boolean isSettled(Station vertex) {
		return settledNodes.contains(vertex);
	}

	private int getShortestDistance(Station destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	/*
	 * This method returns the path from the source to the selected target and
	 * NULL if no path exists
	 */
	public LinkedList<Station> getPath(Station target) {
		LinkedList<Station> path = new LinkedList<Station>();
		Station step = target;
		// check if a path exists
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		// Put it into the correct order
		Collections.reverse(path);
		return path;
	}
}
