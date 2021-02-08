package kr.co.network.constant;

public enum HomonymyStation implements EnumModel{
	YANGPYEONG("양평"),
	SINCHON("신촌");

	private final String name;

	@Override
	public String getKey() {
		return name();
	}

	@Override
	public String getName() {
		return this.name;
	}

	private HomonymyStation(String name){
		this.name = name;
	}
}
