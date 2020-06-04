package ladder;

import ladder.domain.LadderGame;
import ladder.domain.dto.LadderShapeResult;
import ladder.domain.ladder.Height;
import ladder.domain.ladder.shape.LadderShapeInfo;
import ladder.domain.player.Players;
import ladder.view.InputView;
import ladder.view.ResultView;

public class LadderApplication {

    public static void main(String[] args) {
        Players players = Players.of(InputView.inputPlayers());
        Height height = Height.of(InputView.inputHeight());
        LadderShapeInfo ladderShapeInfo = LadderShapeInfo.valueOf(players, height);

        LadderShapeResult result = LadderGame.of(ladderShapeInfo).play();

        ResultView.printResult(result);
    }
}
