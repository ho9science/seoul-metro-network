package kr.co.network;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DijkstraTest {

	@Test
	public void dijkstraTest1(){
		DataLoader dataLoader = new DataLoader();
		Graph graph = dataLoader.createGraph();
		Dijkstra dijkstra = new Dijkstra(graph);
		dijkstra.start(Station.builder().name("당고개").line("04호선").build());

		List<Station> path = dijkstra.getPath(Station.builder().name("중계").line("07호선").build());

		assertNotNull(path);
		assertTrue(path.size() > 0);

		for (Station station : path) {
			System.out.println(station);
		}
	}

	@Test
	public void dijkstraTest2(){
		DataLoader dataLoader = new DataLoader();
		Graph graph = dataLoader.createGraph();
		Dijkstra dijkstra = new Dijkstra(graph);
		dijkstra.start(Station.builder().name("당고개").line("04호선").build());

		List<Station> path = dijkstra.getPath(Station.builder().name("당산").line("02호선").build());

		assertNotNull(path);
		assertTrue(path.size() > 0);

		for (Station station : path) {
			System.out.println(station);
		}
	}

	@Test
	public void dijkstraTest3(){
		DataLoader dataLoader = new DataLoader();
		Graph graph = dataLoader.createGraph();
		Dijkstra dijkstra = new Dijkstra(graph);
		dijkstra.start(Station.builder().name("독립문").line("03호선").build());

		List<Station> path = dijkstra.getPath(Station.builder().name("종각").line("01호선").build());

		assertNotNull(path);
		assertTrue(path.size() > 0);

		for (Station station : path) {
			System.out.println(station);
		}
	}

	@Test
	public void dijkstraTest4(){
		DataLoader dataLoader = new DataLoader();
		Graph graph = dataLoader.createGraph();
		Dijkstra dijkstra = new Dijkstra(graph);
		dijkstra.start(Station.builder().name("응암").line("06호선").build());

		List<Station> path = dijkstra.getPath(Station.builder().name("구산").line("06호선").build());

		assertNotNull(path);
		assertTrue(path.size() > 0);

		for (Station station : path) {
			System.out.println(station);
		}
	}

	@Test
	public void dijkstraAllTest(){
		DataLoader dataLoader = new DataLoader();
		Graph graph = dataLoader.createGraph();
		Dijkstra dijkstra = new Dijkstra(graph);
		dijkstra.start();
		Map<Station, Map<Station, Station>> stations = dijkstra.getPathAll();
		Station start = Station.builder().name("독립문").line("03호선").build();
		Station departure = Station.builder().name("수서").line("03호선").build();
		Map<Station, Station> predecessors = stations.get(start);
		List<Station> path = dijkstra.getPath(departure, predecessors);

		assertNotNull(path);
		assertTrue(path.size() > 0);

		for(Station station : path){
			System.out.println(station);
		}
	}
}
