package ladder.domain;

import ladder.domain.dto.LadderShapeResult;
import ladder.domain.ladder.Ladder;
import ladder.domain.ladder.shape.LadderShapeInfo;
import ladder.domain.player.Players;
import ladder.exception.ErrorMessage;

import java.util.Objects;

public class LadderGame {

    private final Players players;
    private final Ladder ladder;

    private LadderGame(final LadderShapeInfo ladderShapeInfo) {
        validateLadderShapeInfo(ladderShapeInfo);
        this.players = ladderShapeInfo.getPlayers();
        this.ladder = Ladder.of(ladderShapeInfo);
    }

    public static LadderGame of(final LadderShapeInfo ladderShapeInfo) {
        return new LadderGame(ladderShapeInfo);
    }

    private static void validateLadderShapeInfo(final LadderShapeInfo ladderShapeInfo) {
        if (Objects.isNull(ladderShapeInfo)) {
            throw new IllegalArgumentException(ErrorMessage.NULL_VALUE);
        }
    }

    public LadderShapeResult play() {
        return LadderShapeResult.of(players.getNames(), ladder);
    }
}
