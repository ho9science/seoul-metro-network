package kr.co.network;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Station {

	private final String name;
	private final String nameEng;
	private final String line;
	private final String frCode;
	private Set<Edge> edges;

	private Station(Builder builder) {
		this.name = builder.name;
		this.nameEng = builder.nameEng;
		this.line = builder.line;
		this.frCode = builder.frCode;
		this.edges = builder.edges;
	}

	public String name(){
		return name;
	}

	public String nameEng(){
		return nameEng;
	}

	public String line(){
		return line;
	}

	public String frCode(){
		return frCode;
	}

	public List<Edge> edges(){
		return new ArrayList<>(edges);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder{
		private String name;
		private String nameEng;
		private String line;
		private String frCode;
		private Set<Edge> edges;

		public Builder name(String name){
			this.name = name;
			return this;
		}

		public Builder nameEng(String nameEng){
			this.nameEng = nameEng;
			return this;
		}

		public Builder line(String line){
			this.line = line;
			return this;
		}

		public Builder frCode(String frCode){
			this.frCode = frCode;
			return this;
		}

		public Builder edges(Set<Edge> edges){
			this.edges = edges;
			return this;
		}

		public Station build(){
			return new Station(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = result + ((line == null) ? 0 : line.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Station other = (Station) obj;
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String toString() {
		return name+"("+nameEng+")-"+line+" - "+frCode+"";
	}
}
