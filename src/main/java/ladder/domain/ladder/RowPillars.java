package ladder.domain.ladder;

import java.util.List;

public class RowPillars {

    private final Stairs stairs;

    private RowPillars(final Stairs stairs) {
        this.stairs = stairs;
    }

    public static RowPillars of(final PillarCount pillarCount) {
        return new RowPillars(Stairs.of(pillarCount));
    }

    public List<Stair> getStairs() {
        return stairs.getStairs();
    }

    public int getWidth() {
        return stairs.getWidth();
    }
}
