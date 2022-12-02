package bridge;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import bridge.model.BridgeMaker;
import bridge.model.BridgeNumberGenerator;
import bridge.model.BridgeRandomNumberGenerator;
import bridge.validator.Validator;
import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class BirdieErrorTest extends NsTest {

    private static final String ERROR_MESSAGE = "[ERROR]";

    Validator validator = new Validator();
    BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();

    @Test
    @DisplayName("다리길이 올바른 입력")
    void correctBridgeSizeInput() {
        assertThatCode(() -> validator.validateBridgeSize(10))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어 이동 시 올바른 입력")
    void correctMove() {
        assertThatCode(() -> validator.validateUpDown("U"))
                .doesNotThrowAnyException();

        assertThatCode(() -> validator.validateUpDown("D"))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("랜덤 다리 생성 테스트")
    void random_Bridge_Make_Test() {
        List<String> bridge;
        BridgeRandomNumberGenerator bridgeRandomNumberGenerator = new BridgeRandomNumberGenerator();
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        int size = 10;
        bridge = bridgeMaker.makeBridge(size);
        assertThat(bridge.size()).isEqualTo(size);
    }

    /**
     * 값이 랜덤으로 생성되는지 확인하는 테스트
     * RepeatedTest 어노테이션 통해 10번 반복해서 0 혹은 1이 생성되었는지 확인 가능
     * 값의 범위가 넓어질 수록 테스트 하기 어려워질텐데, 반복횟수를 증가시켜 검증하는 확률 증가시킨다
     * 로또에서도 동일하게 적용 가능할 듯 하다.
     */
    @RepeatedTest(10)
    @DisplayName("랜덤하게 0, 1 생성 테스트")
    void random_Generate(){
        assertThat(bridgeNumberGenerator.generate())
                .isLessThanOrEqualTo(1)
                .isGreaterThanOrEqualTo(0);
    }


    @Override
    protected void runMain() {

    }
}
// 테스트 코드의 목적이 예외처리 확인뿐 아니라 모든 기능이 정상적으로 작동하는지 확인하는 용도로 더 자세하게 작성하는 연습을 해야겠다.