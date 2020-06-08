package ladder.domain.ladder;

import ladder.domain.ladder.strategy.RandomStairGenerationStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class StairTest {

    @DisplayName("계단의 상태값이 null 이면 예외 반환")
    @Test
    void createFailureByStairStateIsNull() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Stair.of(null));
    }

    @DisplayName("첫 번째 기둥의 계단은 랜던 전략으로 생성")
    @Test
    void createOfFirstPillar() {
        assertThatCode(Stair::createOfFirstPillar)
                .doesNotThrowAnyException();
    }

    @DisplayName("중간 기둥의 계단은 앞에 계단이 있으면 계단을 생성 X, 없으면 랜덤 전략으로 계단 생성 : T -> F, F -> T/F")
    @Test
    void createOfMiddlePillar() {
        Stair previousStair = Stair.of(
                State.ofFirstPillar(RandomStairGenerationStrategy.getInstance()));

        boolean existLineOfPreviousStair = previousStair.isExistLine();
        boolean existLine = previousStair.createOfNextPillar().isExistLine();

        assertThat(existLineOfPreviousStair && existLine).isFalse();
    }

    @DisplayName("마지막 기둥에는 계단을 둘 수 없음")
    @Test
    void createOfLastPillar() {
        assertThat(Stair.createOfFirstPillar().createOfLastPillar()
                .isExistLine())
                .isEqualTo(false);
    }

    @DisplayName("해당 기둥의 계단의 상태에 따라 다음 위치를 반환")
    @ParameterizedTest
    @MethodSource
    void move(final Stair stair, final int position, final int expected) {
        assertThat(stair.move(position)).isEqualTo(expected);
    }

    private static Stream<Arguments> move() {
        return Stream.of(
                Arguments.of(Stair.of(State.ofFirstPillar(() -> false)), 0, 0),
                Arguments.of(Stair.of(State.ofFirstPillar(() -> true)), 0, 1),
                Arguments.of(Stair.of(State.ofFirstPillar(() -> true).ofNextPillar(() -> false)), 1, 0)
        );
    }
}
