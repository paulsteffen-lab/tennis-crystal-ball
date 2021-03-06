package org.strangeforest.tcb.stats.model;

import org.assertj.core.data.*;
import org.junit.jupiter.api.*;
import org.strangeforest.tcb.stats.model.prediction.*;

import static org.assertj.core.api.Assertions.*;
import static org.strangeforest.tcb.stats.model.core.MatchRules.*;
import static org.strangeforest.tcb.stats.model.prediction.LivePredictor.*;

class LivePredictorTest {

	private static final Offset<Double> OFFSET = Offset.offset(0.0001);

	@Test
	void testNormalization() {
		BaseProbabilities probs = normalize(new BaseProbabilities(0.7, 0.75, 0.3), BEST_OF_5_MATCH);
		assertThat(new MatchOutcome(probs.getpServe(), probs.getpReturn(), BEST_OF_5_MATCH).pWin()).isCloseTo(0.7, OFFSET);

		probs = normalize(new BaseProbabilities(0.9, 0.8, 0.35), BEST_OF_5_MATCH);
		assertThat(new MatchOutcome(probs.getpServe(), probs.getpReturn(), BEST_OF_5_MATCH).pWin()).isCloseTo(0.9, OFFSET);
	}
}
