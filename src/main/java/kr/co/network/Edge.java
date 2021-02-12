package kr.co.network;


public class Edge {
	Station departure;
	Station destination;
	int time;

	Edge(Station departure, Station destination){
		this.departure = departure;
		this.destination = destination;
		this.time = 2;
	}

	Edge(Station departure, Station destination, int time){
		this.departure = departure;
		this.destination = destination;
		this.time = time;
	}

	public Station getDeparture(){
		return this.departure;
	}

	public Station getDestination(){
		return this.destination;
	}

	public int getTime() {
		return this.time;
	}
}
