package ladder.domain.dto;

import ladder.domain.ladder.Height;
import ladder.domain.ladder.Ladder;
import ladder.domain.ladder.shape.LadderShapeInfo;
import ladder.domain.player.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class StairDtoTest {

    private Players players;
    private Players singlePlayer;

    private Height height;

    @BeforeEach
    void setUp() {
        List<String> names = new ArrayList<>();
        names.add("pobi");
        names.add("honux");
        this.players = Players.of(names);

        List<String> singlePlayerName = new ArrayList<>();
        singlePlayerName.add("heee");
        this.singlePlayer = Players.of(singlePlayerName);

        this.height = Height.of(Height.MIN_HEIGHT * 5);
    }

    @DisplayName("사다리 정보로 각 행의 좌->우로의 계단 라인 정보 리스트 반환")
    @Test
    void create() {
        assertThatCode(() -> StairDto.from(Ladder.of(LadderShapeInfo.valueOf(players, height))))
                .doesNotThrowAnyException();
    }

    @DisplayName("인자로 지정한 높이 만큼 StairDto 리스트가 생성")
    @ParameterizedTest
    @MethodSource
    void from(final Height height, final int heightValue) {
        Ladder ladder = Ladder.of(LadderShapeInfo.valueOf(players, height));

        assertThat(StairDto.from(ladder).size())
                .isEqualTo(heightValue);
    }

    private static Stream<Arguments> from() {
        return Stream.of(
                Arguments.of(Height.of(Height.MIN_HEIGHT), Height.MIN_HEIGHT),
                Arguments.of(Height.of(Height.MIN_HEIGHT * 5), 5)
        );
    }

    @DisplayName("한 명의 참여자로 게임을 실행하면 모든 높이의 기둥에 존재하는 계단 수는 0개")
    @Test
    void isSinglePillar() {
        Ladder ladder = Ladder.of(LadderShapeInfo.valueOf(singlePlayer, height));

        StairDto.from(ladder)
                .forEach(stairDto -> assertThat(stairDto.isSinglePillar()).isEqualTo(true));
    }

    // TODO 행의 계단이 T/F/T/F 로 StairDto 가 생기는지 확인
//    @DisplayName("각 행의 계단 라인인 List<Boolean> 을 반환")
//    @Test
//    void getLines() {
//    }
}
