package org.strangeforest.tcb.stats.model.records.details;

import com.fasterxml.jackson.annotation.*;

public class SeasonDoubleRecordDetail extends SimpleRecordDetail<Double> implements SeasonRecordDetail<Double> {

	private final int season;

	public SeasonDoubleRecordDetail(
		@JsonProperty("value") double value,
		@JsonProperty("season") int season
	) {
		super(value);
		this.season = season;
	}

	@Override public int getSeason() {
		return season;
	}

	@Override public String toDetailString() {
		return String.valueOf(season);
	}
}
