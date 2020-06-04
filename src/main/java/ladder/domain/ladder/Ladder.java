package ladder.domain.ladder;

import ladder.domain.ladder.shape.LadderShapeInfo;
import ladder.exception.ErrorMessage;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ladder {

    private final List<RowPillars> rowPillars;

    private Ladder(final List<RowPillars> rowPillars) {
        validatePillars(rowPillars);
        this.rowPillars = rowPillars;
    }

    private void validatePillars(final List<RowPillars> rowPillars) {
        if (Objects.isNull(rowPillars) || rowPillars.size() == 0) {
            throw new IllegalArgumentException(ErrorMessage.IS_NULL_OR_EMPTY);
        }
    }

    public static Ladder of(final Height height, final int numOfPlayers) {
        return new Ladder(createPillars(height, numOfPlayers));
    }

    private static List<RowPillars> createPillars(final Height height, final int numOfPlayers) {
        return Stream.generate(() -> RowPillars.of(PillarCount.of(numOfPlayers)))
                .limit(height.getValue())
                .collect(Collectors.toList());
    }

    public static Ladder of(final LadderShapeInfo ladderShapeInfo) {
        return new Ladder(createPillars2(ladderShapeInfo));
    }

    private static List<RowPillars> createPillars2(final LadderShapeInfo ladderShapeInfo) {
        return Stream.generate(() -> RowPillars.of(ladderShapeInfo.getWidth()))
                .limit(ladderShapeInfo.getHeight())
                .collect(Collectors.toList());
    }

    public List<RowPillars> getRowPillars() {
        return rowPillars;
    }
}
