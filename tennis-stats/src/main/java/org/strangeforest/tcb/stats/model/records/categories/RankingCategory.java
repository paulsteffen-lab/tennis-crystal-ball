package org.strangeforest.tcb.stats.model.records.categories;

import org.strangeforest.tcb.stats.model.core.*;
import org.strangeforest.tcb.stats.model.records.*;
import org.strangeforest.tcb.stats.model.records.details.*;

import static java.lang.String.*;
import static java.util.Arrays.*;
import static org.strangeforest.tcb.stats.model.records.RecordDomain.*;
import static org.strangeforest.tcb.stats.model.records.categories.RankingCategory.AgeType.*;

public abstract class RankingCategory extends RecordCategory {

	private static final String WEEKS_WIDTH =   "120";
	private static final String SEASONS_WIDTH = "120";
	private static final String TIMES_WIDTH =   "120";
	private static final String POINTS_WIDTH =  "110";
	private static final String DATE_WIDTH =     "85";
	private static final String RANK_WIDTH =     "65";
	private static final String SEASON_WIDTH =   "80";
	private static final String AGE_WIDTH =     "150";
	private static final String SPAN_WIDTH =    "150";
	private static final String PLAYER_WIDTH =  "150";

	private static final String INVALID_RANKING_PLAYERS = "'Jaime Fillol', 'Chris Lewis', 'Olivier Cayla'";

	private final RankClass rankClass;
	private final RankType rankType;
	private final String dateCondition;
	private final String seasonCondition;

	public enum RankClass {
		ATP("ATP", "ATP"),
		OPEN_ERA("OpenEra", "Open Era"),
		ELO("Elo", "Elo");

		private final String id;
		private final String name;

		RankClass(String id, String name) {
			this.id = id;
			this.name = name;
		}
	}

	enum AgeType {
		YOUNGEST("Youngest", "min", "r.value"),
		OLDEST("Oldest", "max", "r.value DESC");

		private final String name;
		private final String function;
		private final String order;

		AgeType(String name, String function, String order) {
			this.name = name;
			this.function = function;
			this.order = order;
		}
	}

	protected RankingCategory(RankClass rankClass, RankType rankType, String name, String dateCondition, String seasonCondition) {
		super(name);
		this.rankClass = rankClass;
		this.rankType = rankType;
		this.dateCondition = dateCondition;
		this.seasonCondition = seasonCondition;
	}

	protected void registerRanking(RecordDomain domain, String rankDBName) {
		register(mostWeeksAt(domain, NO_1, NO_1_NAME, rankDBName, NO_1_RANK, NO_1_RANK));
		register(mostWeeksAt(domain, NO_2, NO_2_NAME, rankDBName, NO_2_RANK, TOP_2_RANK));
		register(mostWeeksAt(domain, NO_3, NO_3_NAME, rankDBName, NO_3_RANK, TOP_3_RANK));
		register(mostWeeksAt(domain, TOP_2, TOP_2_NAME, rankDBName, TOP_2_RANK, TOP_2_RANK));
		register(mostWeeksAt(domain, TOP_3, TOP_3_NAME, rankDBName, TOP_3_RANK, TOP_3_RANK));
		register(mostWeeksAt(domain, TOP_5, TOP_5_NAME, rankDBName, TOP_5_RANK, TOP_5_RANK));
		register(mostWeeksAt(domain, TOP_10, TOP_10_NAME, rankDBName, TOP_10_RANK, TOP_10_RANK));
		register(mostWeeksAt(domain, TOP_20, TOP_20_NAME, rankDBName, TOP_20_RANK, TOP_20_RANK));
		register(mostConsecutiveWeeksAt(domain, NO_1, NO_1_NAME, rankDBName, NO_1_RANK, NO_1_RANK));
		register(mostConsecutiveWeeksAt(domain, NO_2, NO_2_NAME, rankDBName, NO_2_RANK, TOP_2_RANK));
		register(mostConsecutiveWeeksAt(domain, NO_3, NO_3_NAME, rankDBName, NO_3_RANK, TOP_3_RANK));
		register(mostConsecutiveWeeksAt(domain, TOP_2, TOP_2_NAME, rankDBName, TOP_2_RANK, TOP_2_RANK));
		register(mostConsecutiveWeeksAt(domain, TOP_3, TOP_3_NAME, rankDBName, TOP_3_RANK, TOP_3_RANK));
		register(mostConsecutiveWeeksAt(domain, TOP_5, TOP_5_NAME, rankDBName, TOP_5_RANK, TOP_5_RANK));
		register(mostConsecutiveWeeksAt(domain, TOP_10, TOP_10_NAME, rankDBName, TOP_10_RANK, TOP_10_RANK));
		register(mostConsecutiveWeeksAt(domain, TOP_20, TOP_20_NAME, rankDBName, TOP_20_RANK, TOP_20_RANK));
		if (domain == ALL) {
			register(mostEndsOfSeasonAt(NO_1, NO_1_NAME, rankDBName, NO_1_RANK));
			register(mostEndsOfSeasonAt(NO_2, NO_2_NAME, rankDBName, NO_2_RANK));
			register(mostEndsOfSeasonAt(NO_3, NO_3_NAME, rankDBName, NO_3_RANK));
			register(mostEndsOfSeasonAt(TOP_2, TOP_2_NAME, rankDBName, TOP_2_RANK));
			register(mostEndsOfSeasonAt(TOP_3, TOP_3_NAME, rankDBName, TOP_3_RANK));
			register(mostEndsOfSeasonAt(TOP_5, TOP_5_NAME, rankDBName, TOP_5_RANK));
			register(mostEndsOfSeasonAt(TOP_10, TOP_10_NAME, rankDBName, TOP_10_RANK));
			register(mostEndsOfSeasonAt(TOP_20, TOP_20_NAME, rankDBName, TOP_20_RANK));
			register(mostConsecutiveEndsOfSeasonAt(NO_1, NO_1_NAME, rankDBName, NO_1_RANK));
			register(mostConsecutiveEndsOfSeasonAt(NO_2, NO_2_NAME, rankDBName, NO_2_RANK));
			register(mostConsecutiveEndsOfSeasonAt(NO_3, NO_3_NAME, rankDBName, NO_3_RANK));
			register(mostConsecutiveEndsOfSeasonAt(TOP_2, TOP_2_NAME, rankDBName, TOP_2_RANK));
			register(mostConsecutiveEndsOfSeasonAt(TOP_3, TOP_3_NAME, rankDBName, TOP_3_RANK));
			register(mostConsecutiveEndsOfSeasonAt(TOP_5, TOP_5_NAME, rankDBName, TOP_5_RANK));
			register(mostConsecutiveEndsOfSeasonAt(TOP_10, TOP_10_NAME, rankDBName, TOP_10_RANK));
			register(mostConsecutiveEndsOfSeasonAt(TOP_20, TOP_20_NAME, rankDBName, TOP_20_RANK));
		}
		register(mostTimesAt(domain, NO_1, NO_1_NAME, rankDBName, NO_1_RANK, NO_1_RANK));
		register(mostTimesAt(domain, NO_2, NO_2_NAME, rankDBName, NO_2_RANK, TOP_2_RANK));
		register(mostTimesAt(domain, NO_3, NO_3_NAME, rankDBName, NO_3_RANK, TOP_3_RANK));
		register(youngestOldestRanking(domain, YOUNGEST, NO_1, NO_1_NAME, rankDBName, NO_1_RANK));
		register(youngestOldestRanking(domain, YOUNGEST, TOP_2, TOP_2_NAME, rankDBName, TOP_2_RANK));
		register(youngestOldestRanking(domain, YOUNGEST, TOP_3, TOP_3_NAME, rankDBName, TOP_3_RANK));
		register(youngestOldestRanking(domain, YOUNGEST, TOP_5, TOP_5_NAME, rankDBName, TOP_5_RANK));
		register(youngestOldestRanking(domain, YOUNGEST, TOP_10, TOP_10_NAME, rankDBName, TOP_10_RANK));
		register(youngestOldestRanking(domain, YOUNGEST, TOP_20, TOP_20_NAME, rankDBName, TOP_20_RANK));
		register(youngestOldestRanking(domain, OLDEST, NO_1, NO_1_NAME, rankDBName, NO_1_RANK));
		register(youngestOldestRanking(domain, OLDEST, TOP_2, TOP_2_NAME, rankDBName, TOP_2_RANK));
		register(youngestOldestRanking(domain, OLDEST, TOP_3, TOP_3_NAME, rankDBName, TOP_3_RANK));
		register(youngestOldestRanking(domain, OLDEST, TOP_5, TOP_5_NAME, rankDBName, TOP_5_RANK));
		register(youngestOldestRanking(domain, OLDEST, TOP_10, TOP_10_NAME, rankDBName, TOP_10_RANK));
		register(youngestOldestRanking(domain, OLDEST, TOP_20, TOP_20_NAME, rankDBName, TOP_20_RANK));
		register(careerSpanRanking(domain, NO_1, NO_1_NAME, rankDBName, NO_1_RANK));
		register(careerSpanRanking(domain, TOP_2, TOP_2_NAME, rankDBName, TOP_2_RANK));
		register(careerSpanRanking(domain, TOP_3, TOP_3_NAME, rankDBName, TOP_3_RANK));
		register(careerSpanRanking(domain, TOP_5, TOP_5_NAME, rankDBName, TOP_5_RANK));
		register(careerSpanRanking(domain, TOP_10, TOP_10_NAME, rankDBName, TOP_10_RANK));
		register(careerSpanRanking(domain, TOP_20, TOP_20_NAME, rankDBName, TOP_20_RANK));
		if (domain == ALL && rankClass != RankClass.ELO) {
			register(youngestOldestEndOfSeasonRanking(domain, YOUNGEST, NO_1, NO_1_NAME, rankDBName, NO_1_RANK));
			register(youngestOldestEndOfSeasonRanking(domain, YOUNGEST, TOP_2, TOP_2_NAME, rankDBName, TOP_2_RANK));
			register(youngestOldestEndOfSeasonRanking(domain, YOUNGEST, TOP_3, TOP_3_NAME, rankDBName, TOP_3_RANK));
			register(youngestOldestEndOfSeasonRanking(domain, YOUNGEST, TOP_5, TOP_5_NAME, rankDBName, TOP_5_RANK));
			register(youngestOldestEndOfSeasonRanking(domain, YOUNGEST, TOP_10, TOP_10_NAME, rankDBName, TOP_10_RANK));
			register(youngestOldestEndOfSeasonRanking(domain, YOUNGEST, TOP_20, TOP_20_NAME, rankDBName, TOP_20_RANK));
			register(youngestOldestEndOfSeasonRanking(domain, OLDEST, NO_1, NO_1_NAME, rankDBName, NO_1_RANK));
			register(youngestOldestEndOfSeasonRanking(domain, OLDEST, TOP_2, TOP_2_NAME, rankDBName, TOP_2_RANK));
			register(youngestOldestEndOfSeasonRanking(domain, OLDEST, TOP_3, TOP_3_NAME, rankDBName, TOP_3_RANK));
			register(youngestOldestEndOfSeasonRanking(domain, OLDEST, TOP_5, TOP_5_NAME, rankDBName, TOP_5_RANK));
			register(youngestOldestEndOfSeasonRanking(domain, OLDEST, TOP_10, TOP_10_NAME, rankDBName, TOP_10_RANK));
			register(youngestOldestEndOfSeasonRanking(domain, OLDEST, TOP_20, TOP_20_NAME, rankDBName, TOP_20_RANK));
			register(careerSpanEndOfSeasonRanking(domain, NO_1, NO_1_NAME, rankDBName, NO_1_RANK));
			register(careerSpanEndOfSeasonRanking(domain, TOP_2, TOP_2_NAME, rankDBName, TOP_2_RANK));
			register(careerSpanEndOfSeasonRanking(domain, TOP_3, TOP_3_NAME, rankDBName, TOP_3_RANK));
			register(careerSpanEndOfSeasonRanking(domain, TOP_5, TOP_5_NAME, rankDBName, TOP_5_RANK));
			register(careerSpanEndOfSeasonRanking(domain, TOP_10, TOP_10_NAME, rankDBName, TOP_10_RANK));
			register(careerSpanEndOfSeasonRanking(domain, TOP_20, TOP_20_NAME, rankDBName, TOP_20_RANK));
		}
	}

	protected Record mostWeeksAt(RecordDomain domain, String id, String name, String rankDBName, String condition, String bestCondition) {
		return new Record<>(
			"WeeksAt" + domain.id + rankClass.id + id, "Most Weeks at " + suffix(domain.name, " ") + rankClass.name + " " + name,
			/* language=SQL */
			"WITH player_ranking_weeks AS (\n" +
			"  SELECT player_id, rank_date, " + domain.columnPrefix + "rank AS rank, weeks(rank_date, lead(rank_date) OVER (PARTITION BY player_id ORDER BY rank_date)) AS weeks\n" +
			"  FROM player_" + rankDBName + "ranking\n" +
			"  INNER JOIN player_best_" + rankDBName + "rank USING (player_id)\n" +
			"  WHERE best_" + domain.columnPrefix + rankDBName + "rank " + bestCondition + dateCondition + "\n" +
			")\n" +
			"SELECT player_id, ceil(sum(weeks) FILTER (WHERE weeks <= 54)) AS value, max(rank_date) AS last_date\n" +
			"FROM player_ranking_weeks\n" +
			"INNER JOIN player_v p USING (player_id)\n" +
			"WHERE rank " + condition + "\n" +
			"AND p.name NOT IN (" + INVALID_RANKING_PLAYERS + ")\n" + // TODO Remove after data is fixed
			"GROUP BY player_id",
			"r.value", "r.value DESC NULLS LAST", "r.value DESC NULLS LAST, r.last_date",
			IntegerRecordDetail.class, null,
			asList(new RecordColumn("value", "numeric", null, WEEKS_WIDTH, "right", "Weeks At " + name))
		);
	}

	protected Record mostConsecutiveWeeksAt(RecordDomain domain, String id, String name, String rankDBName, String condition, String bestCondition) {
		return new Record<>(
			"ConsecutiveWeeksAt" + domain.id + rankClass.id + id, "Most Consecutive Weeks at " + suffix(domain.name, " ") + rankClass.name + " " + name,
			/* language=SQL */
			"WITH player_ranking_weeks AS (\n" +
			"  SELECT player_id, rank_date, " + domain.columnPrefix + "rank AS rank, lag(" + domain.columnPrefix + "rank) OVER pr AS prev_rank, weeks(rank_date, lead(rank_date) OVER pr) AS weeks\n" +
			"  FROM player_" + rankDBName + "ranking\n" +
			"  INNER JOIN player_best_" + rankDBName + "rank USING (player_id)\n" +
			"  WHERE best_" + domain.columnPrefix + rankDBName + "rank " + bestCondition + dateCondition + "\n" +
			"  WINDOW pr AS (PARTITION BY player_id ORDER BY rank_date)\n" +
			"), player_ranking_weeks2 AS (\n" +
			"  SELECT player_id, rank, rank_date, prev_rank, weeks, count(player_id) FILTER (WHERE NOT(prev_rank " + condition + ") OR prev_rank IS NULL) OVER (PARTITION BY player_id ORDER BY rank_date) AS not_rank\n" +
			"  FROM player_ranking_weeks\n" +
			"), player_consecutive_weeks AS (\n" +
			"  SELECT player_id, rank, prev_rank, ceil(sum(weeks) FILTER (WHERE weeks <= 54) OVER rs) AS weeks,\n" +
			"    first_value(rank_date) OVER rs AS start_date,\n" +
			"    (last_value(rank_date) OVER (PARTITION BY player_id, not_rank ORDER BY rank_date ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING) - INTERVAL '1 day')::DATE AS end_date\n" +
			"  FROM player_ranking_weeks2\n" +
			"  WHERE rank " + condition + "\n" +
			"  WINDOW rs AS (PARTITION BY player_id, not_rank ORDER BY rank_date)\n" +
			")\n" +
			"SELECT player_id, name, start_date, end_date, max(weeks) AS value\n" +
			"FROM player_consecutive_weeks INNER JOIN player_v USING (player_id)\n" +
			"WHERE prev_rank " + condition + "\n" +
			"GROUP BY player_id, name, start_date, end_date",
			"r.value, r.start_date, r.end_date", "r.value DESC NULLS LAST", "r.value DESC NULLS LAST, r.end_date",
			DateRangeIntegerRecordDetail.class, null,
			asList(
				new RecordColumn("value", "numeric", null, WEEKS_WIDTH, "right", "Weeks at " + name),
				new RecordColumn("startDate", null, "startDate", DATE_WIDTH, "center", "Start Date"),
				new RecordColumn("endDate", null, "endDate", DATE_WIDTH, "center", "End Date")
			)
		);
	}

	protected Record mostEndsOfSeasonAt(String id, String name, String rankDBName, String condition) {
		return new Record<>(
			"EndsOfSeasonAt" + rankClass.id + id, "Most Ends of Season at " + rankClass.name + " " + name,
			/* language=SQL */
			"SELECT player_id, count(*) AS value, max(season) AS last_season\n" +
			"FROM player_year_end_" + rankDBName + "rank\n" +
			"WHERE year_end_rank " + condition + seasonCondition + "\n" +
			"GROUP BY player_id",
			"r.value", "r.value DESC", "r.value DESC, r.last_season",
			IntegerRecordDetail.class, null,
			asList(new RecordColumn("value", "numeric", null, SEASONS_WIDTH, "right", "Seasons at " + name))
		);
	}

	protected Record mostConsecutiveEndsOfSeasonAt(String id, String name, String rankDBName, String condition) {
		return new Record<>(
         "ConsecutiveEndsOfSeasonAt" + rankClass.id + id, "Most Consecutive Ends of Season at " + rankClass.name + " " + name,
			/* language=SQL */
			"WITH player_seasons AS (\n" +
			"  SELECT DISTINCT player_id, season\n" +
			"  FROM player_year_end_" + rankDBName + "rank\n" +
			"  WHERE year_end_rank " + condition + seasonCondition + "\n" +
			"), player_seasons_2 AS (\n" +
			"  SELECT player_id, season, season - row_number() OVER (PARTITION BY player_id ORDER BY season) AS grouping_season\n" +
			"  FROM player_seasons\n" +
			"), player_consecutive_seasons AS (\n" +
			"  SELECT player_id, season, grouping_season, dense_rank() OVER (PARTITION BY player_id, grouping_season ORDER BY season) AS consecutive_seasons\n" +
			"  FROM player_seasons_2\n" +
			")\n" +
			"SELECT player_id, max(season) - max(consecutive_seasons) + 1 AS start_season, max(season) AS end_season, max(consecutive_seasons) AS value\n" +
			"FROM player_consecutive_seasons\n" +
			"GROUP BY player_id, grouping_season\n" +
			"HAVING max(consecutive_seasons) > 1",
			"r.value, r.start_season, r.end_season", "r.value DESC", "r.value DESC, r.end_season",
			SeasonRangeIntegerRecordDetail.class, null,
			asList(
				new RecordColumn("value", "numeric", null, SEASONS_WIDTH, "right", "Seasons at " + name),
				new RecordColumn("startSeason", "numeric", null, SEASON_WIDTH, "center", "Start Season"),
				new RecordColumn("endSeason", "numeric", null, SEASON_WIDTH, "center", "End Season")
			)
		);
	}

	protected Record mostTimesAt(RecordDomain domain, String id, String name, String rankDBName, String condition, String bestCondition) {
		return new Record<>(
			"TimesAt" + domain.id + rankClass.id + id, "Most Times at " + suffix(domain.name, " ") + rankClass.name + " " + name,
			/* language=SQL */
			"WITH ranking AS (\n" +
			"  SELECT player_id, rank_date, " + domain.columnPrefix + "rank AS rank, lag(" + domain.columnPrefix + "rank) OVER (PARTITION BY player_id ORDER BY rank_date) AS prev_rank\n" +
			"  FROM player_" + rankDBName + "ranking\n" +
			"  INNER JOIN player_best_" + rankDBName + "rank USING (player_id)\n" +
			"  WHERE best_" + domain.columnPrefix + rankDBName + "rank " + bestCondition + dateCondition + "\n" +
			")\n" +
			"SELECT player_id, count(rank_date) AS value, max(rank_date) AS last_date\n" +
			"FROM ranking\n" +
			"WHERE rank " + condition + " AND (NOT prev_rank " + condition + " OR prev_rank IS NULL)\n" +
			"GROUP BY player_id",
			"r.value", "r.value DESC", "r.value DESC, r.last_date",
			IntegerRecordDetail.class, null,
			asList(new RecordColumn("value", "numeric", null, TIMES_WIDTH, "right", "Times at " + name))
		);
	}

	protected Record youngestOldestRanking(RecordDomain domain, AgeType type, String id, String name, String rankDBName, String condition) {
		return new Record<>(
			type.name + domain.id + rankClass.id + id, type.name + prefix(domain.name, " ") + prefix(rankClass.name, " ") + prefix(name, " "),
			/* language=SQL */
			"SELECT player_id, " + type.function + "(age(r.rank_date, p.dob)) AS value, " + type.function + "(r.rank_date) AS date\n" +
			"FROM player_" + rankDBName + "ranking r INNER JOIN player_v p USING (player_id)\n" +
			"WHERE " + domain.columnPrefix + "rank " + condition + dateCondition + "\n" +
			"AND p.name NOT IN (" + INVALID_RANKING_PLAYERS + ")\n" + // TODO Remove after data is fixed
			"GROUP BY player_id",
			"r.value, r.date", type.order, type.order + ", r.date",
			DateAgeRecordDetail.class, (playerId, recordDetail) -> format("/rankingsTable?rankType=%1$s&date=%2$td-%2$tm-%2$tY", rankType, recordDetail.getDate()),
			asList(
				new RecordColumn("value", null, "valueUrl", AGE_WIDTH, "left", "Age"),
				new RecordColumn("date", null, "date", DATE_WIDTH, "center", "Date")
			)
		);
	}

	protected Record careerSpanRanking(RecordDomain domain, String id, String name, String rankDBName, String condition) {
		name = suffix(domain.name, " ") + suffix(rankClass.name, " ") + name;
		return new Record<>(
			"Longest" + domain.id + rankClass.id + id + "Span", "Longest Career First " + name + " to Last " + name,
			/* language=SQL */
			"SELECT player_id, age(max(rank_date), min(rank_date)) AS value, min(rank_date) AS start_date, max(rank_date) AS end_date\n" +
			"FROM player_" + rankDBName + "ranking\n" +
			"WHERE " + domain.columnPrefix + "rank " + condition + dateCondition + "\n" +
			"GROUP BY player_id",
			"r.value, r.start_date, r.end_date", "r.value DESC", "r.value DESC, r.end_date",
			CareerSpanRecordDetail.class, (playerId, recordDetail) -> format("/playerProfile?playerId=%1$d&tab=ranking&rankType=%2$s", playerId, rankType),
			asList(
				new RecordColumn("value", null, "valueUrl", SPAN_WIDTH, "left", "Career Span"),
				new RecordColumn("startDate", null, "startDate", DATE_WIDTH, "center", "Start Date"),
				new RecordColumn("endDate", null, "endDate", DATE_WIDTH, "center", "End Date")
			)
		);
	}

	protected Record youngestOldestEndOfSeasonRanking(RecordDomain domain, AgeType type, String id, String name, String rankDBName, String condition) {
		return new Record<>(
			type.name + domain.id + "EndOfSeason" + rankClass.id + id, type.name + prefix(domain.name, " ") + " End of Season " + rankClass.name + prefix(name, " "),
			/* language=SQL */
			"SELECT player_id, " + type.function + "(age(season_end(r.season), p.dob)) AS value, " + type.function + "(r.season) AS season\n" +
			"FROM player_year_end_" + rankDBName + "rank r INNER JOIN player_v p USING (player_id)\n" +
			"WHERE " + domain.columnPrefix + "year_end_rank " + condition + seasonCondition + " AND p.dob IS NOT NULL\n" +
			"GROUP BY player_id",
			"r.value, r.season", type.order, type.order + ", r.season",
			SeasonAgeRecordDetail.class, (playerId, recordDetail) -> format("/rankingsTable?rankType=%1$s&season=%2$d", rankType, recordDetail.getSeason()),
			asList(
				new RecordColumn("value", null, "valueUrl", AGE_WIDTH, "left", "Age"),
				new RecordColumn("season", "numeric", null, SEASON_WIDTH, "center", "Season")
			)
		);
	}

	protected Record careerSpanEndOfSeasonRanking(RecordDomain domain, String id, String name, String rankDBName, String condition) {
		name = suffix(domain.name, " ") + suffix(rankClass.name, " ") + name;
		return new Record<>(
			"Longest" + domain.id + "EndOfSeason" + rankClass.id + id + "Span", "Longest Career First End of Season " + name + " to Last " + name,
			/* language=SQL */
			"SELECT player_id, max(season) - min(season) + 1 AS value, min(season) AS start_season, max(season) AS end_season\n" +
			"FROM player_year_end_" + rankDBName + "rank\n" +
			"WHERE " + domain.columnPrefix + "year_end_rank " + condition + seasonCondition + "\n" +
			"GROUP BY player_id",
			"r.value, r.start_season, r.end_season", "r.value DESC", "r.value DESC, r.end_season",
			SeasonRangeIntegerRecordDetail.class, (playerId, recordDetail) -> format("/playerProfile?playerId=%1$d&tab=ranking&rankType=%2$s&bySeason=true", playerId, rankType),
			asList(
				new RecordColumn("value", null, "valueUrl", SEASONS_WIDTH, "right", "Season Span"),
				new RecordColumn("startSeason", "numeric", null, SEASON_WIDTH, "center", "First Season"),
				new RecordColumn("endSeason", "numeric", null, SEASON_WIDTH, "center", "Last Season")
			)
		);
	}

	protected Record mostPoints(String id, String name, String tableName, String columnName, String dateColumnName, String caption, RankType rankType, String notes) {
		return new Record<>(
			id, name,
			/* language=SQL */
			"SELECT player_id, " + columnName + " AS value, " + dateColumnName + " AS date\n" +
			"FROM " + tableName,
			"r.value, r.date", "r.value DESC NULLS LAST", "r.value DESC NULLS LAST, r.date",
			DateIntegerRecordDetail.class, (playerId, recordDetail) -> format("/rankingsTable?rankType=%1$s&date=%2$td-%2$tm-%2$tY", rankType, recordDetail.getDate()),
			asList(
				new RecordColumn("value", null, "valueUrl", POINTS_WIDTH, "right", caption),
				new RecordColumn("date", null, "date", DATE_WIDTH, "center", "Date")
			),
			notes
		);
	}

	protected Record leastPointsAsNo1(String id, String name, String tableName, String expression, String columnName, String caption, RankType rankType, String notes) {
		return new Record<>(
			id, name,
			/* language=SQL */
			"WITH least_points AS (\n" +
			"  SELECT player_id, min(" + expression + ") AS value\n" +
			"  FROM " + tableName + " r1\n" +
			"  WHERE rank = 1 AND rank_date >= DATE '1968-07-01' AND " + columnName + " > 0\n" +
			"  GROUP BY player_id\n" +
         ")\n" +
			"SELECT player_id, value, (SELECT min(r.rank_date) FROM " + tableName + " r WHERE r.player_id = l.player_id AND r.rank = 1 AND " + expression + " = l.value) AS date\n" +
			"FROM least_points l",
			"r.value, r.date", "r.value", "r.value, r.date",
			DateIntegerRecordDetail.class, (playerId, recordDetail) -> format("/rankingsTable?rankType=%1$s&date=%2$td-%2$tm-%2$tY", rankType, recordDetail.getDate()),
			asList(
				new RecordColumn("value", null, "valueUrl", POINTS_WIDTH, "right", caption),
				new RecordColumn("date", null, "date", DATE_WIDTH, "center", "Date")
			),
			notes
		);
	}

	protected Record mostEndOfSeasonPoints(String id, String name, String tableName, String columnName, String caption, RankType rankType, String notes) {
		return new Record<>(
			id, name,
			/* language=SQL */
			"SELECT player_id, " + columnName + " AS value, year_end_rank AS value2, season\n" +
			"FROM " + tableName + "\n" +
			"WHERE " + columnName + " > 0",
			"r.value, r.value2, r.season", "r.value DESC", "r.value DESC, r.value2, r.season",
			SeasonTwoIntegersRecordDetail.class, (playerId, recordDetail) -> format("/rankingsTable?rankType=%1$s&season=%2$d", rankType, recordDetail.getSeason()),
			asList(
				new RecordColumn("value", null, "valueUrl", POINTS_WIDTH, "right", caption),
				new RecordColumn("value2", "numeric", null, RANK_WIDTH, "right", "Rank"),
				new RecordColumn("season", "numeric", null, SEASON_WIDTH, "center", "Season")
			),
			notes
		);
	}

	protected Record leastEndOfSeasonPointsAsNo1(String id, String name, String tableName, String columnName, String caption, RankType rankType, String notes) {
		return new Record<>(
			id, name,
			/* language=SQL */
			"SELECT player_id, " + columnName + " AS value, season\n" +
			"FROM " + tableName + "\n" +
			"WHERE year_end_rank = 1 AND " + columnName + " > 0",
			"r.value, r.season", "r.value", "r.value, r.season",
			SeasonIntegerRecordDetail.class, (playerId, recordDetail) -> format("/rankingsTable?rankType=%1$s&season=%2$d", rankType, recordDetail.getSeason()),
			asList(
				new RecordColumn("value", null, "valueUrl", POINTS_WIDTH, "right", caption),
				new RecordColumn("season", "numeric", null, SEASON_WIDTH, "center", "Season")
			),
			notes
		);
	}

	protected Record pointsDifferenceBetweenNo1andNo2(
		String id, String name, String tableName, String columnName, String rankColumnName, String condition,
		String expression, String expression1, String expression2, String order,
		Class<? extends BaseDateRankingDiffRecordDetail> detailClass, String caption, String diffCaption, RankType rankType, String notes
	) {
		return new Record<>(
			id, name,
			/* language=SQL */
			"WITH ranking_diff AS (\n" +
			"  SELECT r1.player_id, r2.player_id AS player_id2, " + expression1 + " AS value1, " + expression2 + " AS value2,\n" +
			"    " + expression + " AS value, r1.rank_date\n" +
			"  FROM " + tableName + " r1\n" +
			"  INNER JOIN " + tableName + " r2 ON r2.rank_date = r1.rank_date AND r2." + rankColumnName + " = 2 AND r2." + columnName + " > 0\n" +
			"  WHERE r1." + rankColumnName + " = 1 AND r1." + columnName + " > 0" + prefix(condition, " AND ") + "\n" +
			"), ranking_diff2 AS (\n" +
			"  SELECT player_id, player_id2, first_value(value1) OVER diff AS value1, first_value(value2) OVER diff AS value2,\n" +
			"    first_value(value) OVER diff AS value, first_value(rank_date) OVER diff AS date\n" +
			"  FROM ranking_diff\n" +
			"  GROUP BY player_id, player_id2, value1, value2, value, rank_date\n" +
			"  WINDOW diff AS (PARTITION BY player_id, player_id2 ORDER BY " + order + ")\n" +
			")\n" +
			"SELECT DISTINCT d.player_id, d.player_id2, p2.name AS name2, p2.country_id AS country_id2, p2.active AS active2, d.value1, d.value2, d.value, d.date\n" +
			"FROM ranking_diff2 d\n" +
			"INNER JOIN player_v p2 ON p2.player_id = d.player_id2",
			"r.player_id2, r.name2, r.country_id2, r.active2, r.value1, r.value2, r.value, r.date", "r." + order, "r." + order + ", r.date",
			detailClass, (playerId, recordDetail) -> format("/rankingsTable?rankType=%1$s&date=%2$td-%2$tm-%2$tY", rankType, recordDetail.getDate()),
			asList(
				new RecordColumn("player2", null, "player2", PLAYER_WIDTH, "left", "No. 2 Player"),
				new RecordColumn("value1", "numeric", null, POINTS_WIDTH, "right", caption + " No. 1"),
				new RecordColumn("value2", "numeric", null, POINTS_WIDTH, "right", caption + " No. 2"),
				new RecordColumn("value", null, "valueUrl", POINTS_WIDTH, "right", diffCaption),
				new RecordColumn("date", null, "date", DATE_WIDTH, "center", "Date")
			),
			notes
		);
	}

	protected Record endOfSeasonPointsDifferenceBetweenNo1andNo2(
		String id, String name, String tableName, String columnName, String rankColumnName, String condition,
		String expression, String expression1, String expression2, String order,
		Class<? extends BaseSeasonRankingDiffRecordDetail> detailClass, String caption, String diffCaption, RankType rankType, String notes
	) {
		return new Record<>(
			id, name,
			/* language=SQL */
			"WITH ranking_diff AS (\n" +
			"  SELECT r1.player_id, r2.player_id AS player_id2, " + expression1 + " AS value1, " + expression2 + " AS value2,\n" +
			"    " + expression + " AS value, r1.season\n" +
			"  FROM " + tableName + " r1\n" +
			"  INNER JOIN " + tableName + " r2 ON r2.season = r1.season AND r2." + rankColumnName + " = 2 AND r2." + columnName + " > 0\n" +
			"  WHERE r1." + rankColumnName + " = 1 AND r1." + columnName + " > 0" + prefix(condition, " AND ") + "\n" +
			")\n" +
			"SELECT DISTINCT d.player_id, d.player_id2, p2.name AS name2, p2.country_id AS country_id2, p2.active AS active2, d.value1, d.value2, d.value, d.season\n" +
			"FROM ranking_diff d\n" +
			"INNER JOIN player_v p2 ON p2.player_id = d.player_id2",
			"r.player_id2, r.name2, r.country_id2, r.active2, r.value1, r.value2, r.value, r.season", "r." + order, "r." + order + ", r.season",
			detailClass, (playerId, recordDetail) -> format("/rankingsTable?rankType=%1$s&season=%2$d", rankType, recordDetail.getSeason()),
			asList(
				new RecordColumn("player2", null, "player2", PLAYER_WIDTH, "left", "No. 2 Player"),
				new RecordColumn("value1", "numeric", null, POINTS_WIDTH, "right", caption + " No. 1"),
				new RecordColumn("value2", "numeric", null, POINTS_WIDTH, "right", caption + " No. 2"),
				new RecordColumn("value", null, "valueUrl", POINTS_WIDTH, "right", diffCaption),
				new RecordColumn("season", "numeric", null, SEASON_WIDTH, "center", "Season")
			),
			notes
		);
	}
}
