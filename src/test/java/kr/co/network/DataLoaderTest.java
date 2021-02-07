package kr.co.network;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataLoaderTest {
	@Test
	public void dataLoadTest() {
		DataLoader dataLoader = new DataLoader();
		Map<String, Station> map = dataLoader.load();
		assertEquals(730, map.size());
	}

	@Test
	public void createGraphTest1() {
		DataLoader dataLoader = new DataLoader();
		Graph graph = dataLoader.createGraph();
		List<Edge> edges = graph.getAdjStations(Station.builder().name("주안").nameEng("Juan").line("01호선").frCode("156").build());
		assertEquals("주안국가산단,시민공원,간석,도화,주안,주안",
			edges.stream().map(Edge::getDestination).map(Station::name).collect(Collectors.joining(",")));
	}

	@Test
	public void createGraphTest2() {
		DataLoader dataLoader = new DataLoader();
		Graph graph = dataLoader.createGraph();
		List<Edge> edges = graph.getAdjStations(Station.builder().name("주안").nameEng("Juan").line("01호선").frCode("156").build());
		assertEquals("주안,주안,주안,주안,주안,주안",
			edges.stream().map(Edge::getDeparture).map(Station::name).collect(Collectors.joining(",")));
	}

	@Test
	public void sinjungJisunTest() {
		DataLoader dataLoader = new DataLoader();
		Graph graph = dataLoader.createGraph();
		List<Edge> edges = graph.getAdjStations(Station.builder().name("신도림").build());
		assertEquals("영등포,구로,대림,도림천,신도림,신도림",
			edges.stream().map(Edge::getDestination).map(Station::name).collect(Collectors.joining(",")));
	}

	@Test
	public void kkachisanTest() {
		DataLoader dataLoader = new DataLoader();
		Graph graph = dataLoader.createGraph();
		List<Edge> edges = graph.getAdjStations(Station.builder().name("까치산").build());
		assertEquals("신정네거리,화곡,신정,까치산,까치산",
			edges.stream().map(Edge::getDestination).map(Station::name).collect(Collectors.joining(",")));
	}

	@Test
	public void circularLineTest() {
		DataLoader dataLoader = new DataLoader();
		Graph graph = dataLoader.createGraph();
		List<Edge> edges = graph.getAdjStations(Station.builder().name("시청").build());
		assertEquals("종각,서울역,을지로입구,충정로,시청,시청",
			edges.stream().map(Edge::getDestination).map(Station::name).collect(Collectors.joining(",")));
	}
}
