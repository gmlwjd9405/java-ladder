package ladder.domain.ladder;

import ladder.domain.ladder.strategy.RandomStairGenerationStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class StateTest {

    @DisplayName("첫 번째 기둥의 State 생성")
    @Test
    void createOfFirstPillar() {
        assertThatCode(() -> State.ofFirstPillar(RandomStairGenerationStrategy.getInstance()))
                .doesNotThrowAnyException();
    }

    @DisplayName("중간 기둥의 State 생성")
    @Test
    void createOfMiddlePillar() {
        assertThatCode(() -> State.ofFirstPillar(RandomStairGenerationStrategy.getInstance())
                .ofNextPillar(RandomStairGenerationStrategy.getInstance()))
                .doesNotThrowAnyException();
    }

    @DisplayName("마지막 기둥의 계단 또는 앞에 이미 계단이 있는 중간 기둥의 위치에는 계단이 없음")
    @Test
    void ofMiddlePillarWithNoLine() {
        assertThat(State.ofFirstPillar(RandomStairGenerationStrategy.getInstance())
                .ofLastPillar()
                .isExistLine())
                .isEqualTo(false);
    }

    @DisplayName("오른쪽 계단이 있는지 확인")
    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    void isExistLine(final boolean isExistLine) {
        assertThat(State.ofFirstPillar(() -> isExistLine).isExistLine())
                .isEqualTo(isExistLine);
    }

    @DisplayName("논리적 동치성 비교")
    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    void equals(final boolean strategy) {
        assertThat(State.ofFirstPillar(() -> strategy))
                .isEqualTo(State.ofFirstPillar(() -> strategy));
    }

    @DisplayName("해당 기둥의 계단의 상태에 따라 다음 위치를 반환")
    @ParameterizedTest
    @MethodSource
    void move(final State state, final int position, final int expected) {
        assertThat(state.move(position)).isEqualTo(expected);
    }

    private static Stream<Arguments> move() {
        return Stream.of(
                Arguments.of(State.ofFirstPillar(() -> false), 0, 0),
                Arguments.of(State.ofFirstPillar(() -> true), 0, 1),
                Arguments.of(State.ofFirstPillar(() -> true).ofNextPillar(() -> false), 1, 0)
        );
    }
}
