package bridge;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import bridge.model.BridgeMaker;
import bridge.model.BridgeRandomNumberGenerator;
import bridge.validator.Validator;
import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BirdieErrorTest extends NsTest {

    Validator validator = new Validator();
    private static final String ERROR_MESSAGE = "[ERROR]";

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
    void Random_Bridge_Make_Test() {
        List<String> bridge;
        BridgeRandomNumberGenerator bridgeRandomNumberGenerator = new BridgeRandomNumberGenerator();
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        int size = 10;
        bridge = bridgeMaker.makeBridge(size);
        assertThat(bridge.size()).isEqualTo(size);
    }


    @Override
    protected void runMain() {

    }
}
