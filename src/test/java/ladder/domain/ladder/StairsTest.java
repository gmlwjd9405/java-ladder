package ladder.domain.ladder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.*;

public class StairsTest {

    @DisplayName("기둥의 개수가 null 이면 예외 반환")
    @NullSource
    @ParameterizedTest
    void createFailureByPillarCountIsNull(final PillarCount pillarCount) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Stairs.of(pillarCount));
    }

    @DisplayName("기둥의 최소 개수인 1보다 작은 수로 계단을 만들 수 없음")
    @Test
    void createFailure() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Stairs.of(PillarCount.of(PillarCount.MIN_COUNT - 1)));
    }

    @DisplayName("기둥의 개수 만큼 수평을 따라 계단 생성")
    @Test
    void create() {
        assertThatCode(() -> Stairs.of(PillarCount.of(PillarCount.MIN_COUNT)))
                .doesNotThrowAnyException();
    }

    @DisplayName("기둥의 개수가 1개면 계단의 개수는 0 (하나의 기둥으로만 구성)")
    @Test
    void pillarCountIsMinCount() {
        PillarCount pillarCount = PillarCount.of(PillarCount.MIN_COUNT);

        assertThat(Stairs.of(pillarCount).getStairs().size())
                .isEqualTo(0);
    }
}
