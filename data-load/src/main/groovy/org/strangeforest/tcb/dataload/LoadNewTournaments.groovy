package org.strangeforest.tcb.dataload

import org.jsoup.*

import java.time.*

import static org.strangeforest.tcb.dataload.BaseATPWorldTourTournamentLoader.*

loadTournaments(new SqlPool())

static loadTournaments(SqlPool sqlPool) {
	sqlPool.withSql {sql ->
		def atpTournamentLoader = new ATPWorldTourTournamentLoader(sql)
		def season = LocalDate.now().year
		def eventInfos = findCompletedEvents(season)
		def seasonExtIds = atpTournamentLoader.findSeasonEventExtIds(season)
		eventInfos.removeAll { info -> info.extId in seasonExtIds }
		def newExtIds = eventInfos.collect { info -> info.extId }
		println "New completed tournaments for season $season: $newExtIds"
		eventInfos.each { info ->
			atpTournamentLoader.loadTournament(season, info.urlId, info.extId, info.current)
		}
	}
}

static findCompletedEvents(int season) {
	def doc = Jsoup.connect("http://www.atpworldtour.com/en/scores/results-archive?year=$season").timeout(TIMEOUT).get()
	Set eventInfos = new TreeSet()
	doc.select('tr.tourney-result').each {result ->
		def url = result.select('td.tourney-details > a.button-border').attr('href')
		if (result.select('div.tourney-detail-winner > a'))
			eventInfos << new EventInfo(url)
	}
	eventInfos
}