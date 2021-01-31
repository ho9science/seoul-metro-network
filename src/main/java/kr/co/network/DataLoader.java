package kr.co.network;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataLoader {
	public Graph load() {
		Graph graph = new Graph();
		try {
			Path path = Paths.get("src/main/resources/data/서울특별시 노선별 지하철역 정보(신규).csv");
			BufferedReader reader = Files.newBufferedReader(path, Charset.forName("MS949"));
			Iterable <CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse( reader );
			for ( CSVRecord record : records ) {
				String name = record.get("전철역명");
				String nameEng = record.get("전철명명(영문)");
				String line = record.get("호선");
				String frCode = record.get("외부코드");
				graph.addStation(Station.builder().name(name).nameEng(nameEng).line(line).frCode(frCode).build());
			}
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		return graph;
	}
}
