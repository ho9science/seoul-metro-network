package kr.co.network;

import kr.co.network.constant.HomonymyStation;
import kr.co.network.constant.Line;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.util.stream.Collectors.groupingBy;

public class DataLoader {
	public Map<String, Station> load() {
		try {
			Map<String, Station> map = new HashMap<>();
			Path path = Paths.get("src/main/resources/data/서울특별시 노선별 지하철역 정보(신규).csv");
			BufferedReader reader = Files.newBufferedReader(path, Charset.forName("MS949"));
			Iterable <CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse( reader );
			for ( CSVRecord record : records ) {
				String code = record.get("전철역코드");
				String name = record.get("전철역명");
				String nameEng = record.get("전철명명(영문)");
				String line = record.get("호선");
				String frCode = record.get("외부코드");
				if("1845".equals(code)){
					frCode = "K209"; //분당선 청량리 외부코드 임시 삽입
				}
				map.put(frCode, Station.builder().name(name).nameEng(nameEng).line(line).frCode(frCode).build());
			}
			return map;
		} catch ( IOException e ) {
			e.printStackTrace();
			return new HashMap<>();
		}
	}

	/**
	 * 그래프 생성
	 * */
	public Graph createGraph(){
		Graph graph = new Graph();
		DataLoader dataLoader = new DataLoader();
		Map<String, Station> map = dataLoader.load();
		map.entrySet().stream().forEach(entry -> graph.addStation(entry.getValue()));

		TreeMap<String, Station> treeMap = new TreeMap<>(map);
		Map<String, List<Map.Entry<String, Station>>> sortedLineMap = treeMap.entrySet().stream()
			.collect(groupingBy(entry -> (String) entry.getValue().line()));
		Station station1 = null;
		for (Map.Entry<String, List<Map.Entry<String, Station>>> line : sortedLineMap.entrySet()) {
			for (Map.Entry<String, Station> station2 : line.getValue()) {
				if (station1 != null) {
					graph.addEdge(station1, station2.getValue());
					station1 = createComplexEdge(station2);
				} else {
					station1 = station2.getValue();
				}
			}
			station1 = null;
		}
		createCircularLine(graph, sortedLineMap.get(Line.LINE2.getName()));
		station1 = null;
		Map<String, List<Map.Entry<String, Station>>> sortedNameMap = treeMap.entrySet().stream()
			.collect(groupingBy(entry -> (String) entry.getValue().name()));
		for (Map.Entry<String, List<Map.Entry<String, Station>>> sortedNameInner : sortedNameMap.entrySet()) {
			for (Map.Entry<String, Station> station2 : sortedNameInner.getValue()) {
				if(sortedNameInner.getValue().size()>1){
					if (station1 != null && !HomonymyStation.YANGPYEONG.getName().equals(station1.name())) {
						graph.addEdge(station1, station2.getValue());
						station1 = station2.getValue();
					} else {
						station1 = station2.getValue();
					}
				}
			}
			station1 = null;
		}
		return graph;
	}

	private Station createComplexEdge(Map.Entry<String, Station> station){
		if(station.getKey().equals("234-4")){
			return null;
		}
		return station.getValue();
	}

	private void createCircularLine(Graph graph, List<Map.Entry<String, Station>> line){
		graph.addEdge(line.get(0).getValue(), line.get(50).getValue());
	}
}
