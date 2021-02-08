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
import java.util.*;

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
					if(isBranchLine(station2)){
						station1 = null;
					}else{
						station1 = station2.getValue();
					}
				} else {
					station1 = station2.getValue();
				}
			}
			station1 = null;
		}
		createBranchAndCircularLine2(graph, sortedLineMap.get(Line.LINE2.getName()));
		createBranchLine5(graph, sortedLineMap.get(Line.LINE5.getName()));
		createBranchLineGyeongChun(graph, sortedLineMap.get(Line.GYEONGCHUN.getName()));
		createBranchLineGyeongUi(graph, sortedLineMap.get(Line.GYEONGUI.getName()));
		createBranchLine1(graph, sortedLineMap.get(Line.LINE1.getName()));
		createBranchLine6(graph, sortedLineMap.get(Line.LINE6.getName()));
		station1 = null;
		Map<String, List<Map.Entry<String, Station>>> sortedNameMap = treeMap.entrySet().stream()
			.collect(groupingBy(entry -> (String) entry.getValue().name()));
		for (Map.Entry<String, List<Map.Entry<String, Station>>> sortedNameInner : sortedNameMap.entrySet()) {
			for (Map.Entry<String, Station> station2 : sortedNameInner.getValue()) {
				if(sortedNameInner.getValue().size()>1){
					if (station1 != null && isHomonymyStationName(station1.name())) {
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

	private boolean isHomonymyStationName(String name){
		if(HomonymyStation.YANGPYEONG.getName().equals(name)){
			return false;
		}else return !HomonymyStation.SINCHON.getName().equals(name);
	}

	private boolean isBranchLine(Map.Entry<String, Station> station){
		if(station.getKey().equals("234-4")){
			return true;
		}
		if(station.getKey().equals("211-4")){
			return true;
		}
		if(station.getKey().equals("P142")){
			return true;
		}
		if(station.getKey().equals("556")){
			return true;
		}
		if(station.getKey().equals("P116")){
			return true;
		}
		if(station.getKey().equals("K138")){
			return true;
		}
		if(station.getKey().equals("K336")){
			return true;
		}
		if(station.getKey().equals("K826")){
			return true;
		}
		if(station.getKey().equals("P312")){
			return true;
		}
		if(station.getKey().equals("P313")){
			return true;
		}
		if(station.getKey().equals("161")){
			return true;
		}
		if(station.getKey().equals("P144-1")){
			return true;
		}
		if(station.getKey().equals("P157-1")){
			return true;
		}
		if(station.getKey().equals("610")){
			return true;
		}
		if(station.getKey().equals("611")){
			return true;
		}
		if(station.getKey().equals("612")){
			return true;
		}
		if(station.getKey().equals("613")){
			return true;
		}
		if(station.getKey().equals("614")){
			return true;
		}
		if(station.getKey().equals("615")){
			return true;
		}
		return false;
	}

	private void createBranchAndCircularLine2(Graph graph, List<Map.Entry<String, Station>> line){
		graph.addEdge(line.get(0).getValue(), line.get(50).getValue());
		graph.addEdge(line.get(10).getValue(), line.get(15).getValue());
		graph.addEdge(line.get(37).getValue(), line.get(42).getValue());
	}

	private void createBranchLine5(Graph graph, List<Map.Entry<String, Station>> line){
		graph.addEdge(line.get(38).getValue(), line.get(46).getValue());
	}

	private void createBranchLineGyeongChun(Graph graph, List<Map.Entry<String, Station>> line){
		graph.addEdge(line.get(0).getValue(), line.get(4).getValue());
	}

	private void createBranchLineGyeongUi(Graph graph, List<Map.Entry<String, Station>> line){
		graph.addEdge(line.get(29).getValue(), line.get(53).getValue());
		graph.addEdge(line.get(53).getValue(), line.get(54).getValue());
		graph.addEdge(line.get(32).getValue(), line.get(54).getValue());
		graph.addEdge(line.get(54).getValue(), line.get(55).getValue());
	}

	private void createBranchLine1(Graph graph, List<Map.Entry<String, Station>> line){
		graph.addEdge(line.get(41).getValue(), line.get(62).getValue());
		graph.addEdge(line.get(64).getValue(), line.get(66).getValue());
		graph.addEdge(line.get(78).getValue(), line.get(80).getValue());
	}

	private void createBranchLine6(Graph graph, List<Map.Entry<String, Station>> line){
		graph.addEdgeOneDirection(line.get(0).getValue(), line.get(1).getValue());
		graph.addEdgeOneDirection(line.get(1).getValue(), line.get(2).getValue());
		graph.addEdgeOneDirection(line.get(2).getValue(), line.get(3).getValue());
		graph.addEdgeOneDirection(line.get(3).getValue(), line.get(4).getValue());
		graph.addEdgeOneDirection(line.get(4).getValue(), line.get(5).getValue());
		graph.addEdgeOneDirection(line.get(5).getValue(), line.get(0).getValue());
	}
}
