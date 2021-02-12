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
		assertEquals("간석,도화,주안",
			edges.stream().map(Edge::getDestination).map(Station::name).collect(Collectors.joining(",")));
	}

	@Test
	public void createGraphTest2() {
		DataLoader dataLoader = new DataLoader();
		Graph graph = dataLoader.createGraph();
		List<Edge> edges = graph.getAdjStations(Station.builder().name("주안").nameEng("Juan").line("01호선").frCode("156").build());
		assertEquals("주안,주안,주안",
			edges.stream().map(Edge::getDeparture).map(Station::name).collect(Collectors.joining(",")));
	}

	@Test
	public void sinjungJisunTest() {
		DataLoader dataLoader = new DataLoader();
		Graph graph = dataLoader.createGraph();
		List<Edge> edges = graph.getAdjStations(Station.builder().name("신도림").line("02호선").build());
		assertEquals("대림,도림천,문래,신도림",
			edges.stream().map(Edge::getDestination).map(Station::name).collect(Collectors.joining(",")));
	}

	@Test
	public void kkachisanTest() {
		DataLoader dataLoader = new DataLoader();
		Graph graph = dataLoader.createGraph();
		List<Edge> edges = graph.getAdjStations(Station.builder().name("까치산").line("02호선").build());
		assertEquals("신정네거리,까치산",
			edges.stream().map(Edge::getDestination).map(Station::name).collect(Collectors.joining(",")));
	}

	@Test
	public void mullaeTest() {
		DataLoader dataLoader = new DataLoader();
		Graph graph = dataLoader.createGraph();
		List<Edge> edges = graph.getAdjStations(Station.builder().name("문래").line("02호선").build());
		assertEquals("영등포구청,신도림",
			edges.stream().map(Edge::getDestination).map(Station::name).collect(Collectors.joining(",")));
	}

	@Test
	public void circularLineTest() {
		DataLoader dataLoader = new DataLoader();
		Graph graph = dataLoader.createGraph();
		List<Edge> edges = graph.getAdjStations(Station.builder().name("시청").line("02호선").build());
		assertEquals("을지로입구,충정로,시청",
			edges.stream().map(Edge::getDestination).map(Station::name).collect(Collectors.joining(",")));
	}
}
