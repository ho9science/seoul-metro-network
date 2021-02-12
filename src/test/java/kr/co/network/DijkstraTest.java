package kr.co.network;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DijkstraTest {

	@Test
	public void dijkstraTest1(){
		DataLoader dataLoader = new DataLoader();
		Graph graph = dataLoader.createGraph();
		Dijkstra dijkstra = new Dijkstra(graph);
		dijkstra.execute(Station.builder().name("당고개").line("04호선").build());

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
		dijkstra.execute(Station.builder().name("당고개").line("04호선").build());

		List<Station> path = dijkstra.getPath(Station.builder().name("당산").line("02호선").build());

		assertNotNull(path);
		assertTrue(path.size() > 0);

		for (Station station : path) {
			System.out.println(station);
		}

	}
}
