package ladder;

import ladder.domain.LadderGame;
import ladder.domain.dto.LadderShapeResult;
import ladder.domain.ladder.Height;
import ladder.domain.ladder.shape.LadderShapeInfo;
import ladder.domain.player.Players;
import ladder.domain.prize.Prizes;
import ladder.view.InputView;
import ladder.view.ResultView;

public class LadderApplication {

    public static void main(String[] args) {
        Players players = Players.of(InputView.inputPlayers());
        Prizes prizes = Prizes.of(InputView.inputPrizes());
        Height height = Height.of(InputView.inputHeight());

        LadderShapeInfo ladderShapeInfo = LadderShapeInfo.valueOf(players, prizes, height);
        LadderGame ladderGame = LadderGame.of(ladderShapeInfo);

        LadderShapeResult ladderShapeResult = ladderGame.ready();
        ResultView.printLadderShape(ladderShapeResult);

        String targetPlayerNames = InputView.inputTargetPlayers();
    }
}
