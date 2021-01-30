package kr.co.subway;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphTest {

	@Test
	public void createGraph() {
		Graph graph = new Graph();
		Station station1 = Station.builder().name("망원").nameEng("Mangwon").line("06호선").frCode("621").build();
		Station station2 = Station.builder().name("합정").nameEng("Hapjeong").line("06호선").frCode("622").build();
		Station station3 = Station.builder().name("상수").nameEng("Sangsu").line("06호선").frCode("623").build();
		Station station4 = Station.builder().name("광흥창").nameEng("Gwangheungchang").line("06호선").frCode("624").build();

		graph.addStation(station1);
		graph.addStation(station2);
		graph.addStation(station3);
		graph.addStation(station4);
		graph.addEdge(station1, station2);
		graph.addEdge(station2, station3);
		graph.addEdge(station3, station4);

		List<Station> stations = Arrays.asList(station1, station3);
		assertTrue(adjacentStation(graph.getAdjVertices(station2)).equals(adjacentStation(stations)));
	}

	private String adjacentStation(List<Station> stations){
		return stations.stream().map(Station::name).collect(Collectors.joining());
	}

	@Test
	public void createStation(){
		Station station = Station.builder().name("신정").nameEng("Sinjeong").line("05호선").frCode("519").build();
		assertEquals("신정", station.name());
	}
}
