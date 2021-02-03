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
		List<Station> stations = graph.getAdjStations(Station.builder().name("주안").nameEng("Juan").line("01호선").frCode("156").build());
		assertEquals("주안국가산단,시민공원,간석,도화,주안,주안", stations.stream().map(Station::name).collect(Collectors.joining(",")));
	}

	@Test
	public void createGraphTest2() {
		DataLoader dataLoader = new DataLoader();
		Graph graph = dataLoader.createGraph();
		List<Station> stations = graph.getAdjStations(Station.builder().name("양평").build());
		assertEquals("오빈,원덕,오목교,영등포구청", stations.stream().map(Station::name).collect(Collectors.joining(",")));
	}
}
