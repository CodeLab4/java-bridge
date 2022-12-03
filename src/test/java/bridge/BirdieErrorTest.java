package bridge;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import bridge.model.BridgeMaker;
import bridge.model.BridgeNumberGenerator;
import bridge.model.BridgeRandomNumberGenerator;
import bridge.validator.Validator;
import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

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
     * 값이 랜덤으로 생성되는지 확인하는 테스트 RepeatedTest 어노테이션 통해 10번 반복해서 0 혹은 1이 생성되었는지 확인 가능 값의 범위가 넓어질 수록 테스트 하기 어려워질텐데, 반복횟수를
     * 증가시켜 검증하는 확률 증가시킨다 로또에서도 동일하게 적용 가능할 듯 하다.
     */
    @RepeatedTest(10)
    @DisplayName("랜덤하게 0, 1 생성 테스트")
    void random_Generate() {
        assertThat(bridgeNumberGenerator.generate())
                .isLessThanOrEqualTo(1)
                .isGreaterThanOrEqualTo(0);
    }

    @Test
    @DisplayName("다리길이 잘못 입력 후 올바른 입력")
    void errorBridgeSizeInput() {
        assertRandomNumberInRangeTest(() ->{
            run("a", "1", "3", "U", "D", "U");
            assertThat(output()).contains(
                    "최종 게임 결과",
                    "[ O |   | O ]",
                    "[   | O |   ]",
                    "게임 성공 여부: 성공",
                    "총 시도한 횟수: 1",
                    "다리 길이는 3부터 20 사이의 숫자입니다",
                    "다리 길이는 3부터 20 사이의 숫자입니다"
            );
            int upSideIndex = output().indexOf("[ O |   | O ]");
            int downSideIndex = output().indexOf("[   | O |   ]");
            Assertions.assertThat(upSideIndex).isLessThan(downSideIndex);
        }, 1, 0, 1);

    }


    @Test
    @DisplayName("이동 시 U D 외의 문자열 입력")
    void errorBridgeMoveInput() {
        assertRandomNumberInRangeTest(() ->{
            run("3","Q", "U", "D", "U");
            assertThat(output()).contains(
                    "최종 게임 결과",
                    "[ O |   | O ]",
                    "[   | O |   ]",
                    "게임 성공 여부: 성공",
                    "총 시도한 횟수: 1",
                    "이동할 칸은 U 와 D 만 입력 가능합니다."
            );
            int upSideIndex = output().indexOf("[ O |   | O ]");
            int downSideIndex = output().indexOf("[   | O |   ]");
            Assertions.assertThat(upSideIndex).isLessThan(downSideIndex);
        }, 1, 0, 1);

    }

    @Test
    @DisplayName("게임 재시작 시 R Q 이외의 문자열 입력 ")
    void errorBridgeRetryInput() {
        assertRandomNumberInRangeTest(() ->{
            run("3","D","WrongInput", "R","U", "D", "U");
            assertThat(output()).contains(
                    "최종 게임 결과",
                    "[ O |   | O ]",
                    "[   | O |   ]",
                    "게임 성공 여부: 성공",
                    "총 시도한 횟수: 2",
                    "R 과 Q 만 입력할 수 있습니다."
            );
            int upSideIndex = output().indexOf("[ O |   | O ]");
            int downSideIndex = output().indexOf("[   | O |   ]");
            Assertions.assertThat(upSideIndex).isLessThan(downSideIndex);
        }, 1, 0, 1);

    }

    @Test
    @DisplayName("게임 재시작 시 R Q 이외의 문자열 입력 ")
    void errorBridgeRetryIntegerInput() {
        assertRandomNumberInRangeTest(() ->{
            run("3","D","1", "R","U", "D", "U");
            assertThat(output()).contains(
                    "최종 게임 결과",
                    "[ O |   | O ]",
                    "[   | O |   ]",
                    "게임 성공 여부: 성공",
                    "총 시도한 횟수: 2",
                    "R 과 Q 만 입력할 수 있습니다."
            );
            int upSideIndex = output().indexOf("[ O |   | O ]");
            int downSideIndex = output().indexOf("[   | O |   ]");
            Assertions.assertThat(upSideIndex).isLessThan(downSideIndex);
        }, 1, 0, 1);

    }

    @Test
    @DisplayName("게임 재시작 시 R Q 이외의 문자열 입력 ")
    void errorBridgeQuitInput() {
        assertRandomNumberInRangeTest(() ->{
            run("3", "D", "WrongInput", "Q");
            assertThat(output()).contains(
                    "최종 게임 결과",
                    "[ X ]",
                    "[   ]",
                    "게임 성공 여부: 실패",
                    "총 시도한 횟수: 1",
                    "R 과 Q 만 입력할 수 있습니다.",
                    "최종 게임 결과"
            );
            int upSideIndex = output().indexOf("[ O |   | O ]");
            int downSideIndex = output().indexOf("[   | O |   ]");
            Assertions.assertThat(upSideIndex).isLessThan(downSideIndex);
        }, 1, 0, 1);

    }

    @Test
    @DisplayName("게임 재시작 시 R Q 이외의 정수 입력 ")
    void errorBridgeQuitIntegerInput() {
        assertRandomNumberInRangeTest(() ->{
            run("3", "D", "1", "Q");
            assertThat(output()).contains(
                    "최종 게임 결과",
                    "[ X ]",
                    "[   ]",
                    "게임 성공 여부: 실패",
                    "총 시도한 횟수: 1",
                    "R 과 Q 만 입력할 수 있습니다.",
                    "최종 게임 결과"
            );
            int upSideIndex = output().indexOf("[ O |   | O ]");
            int downSideIndex = output().indexOf("[   | O |   ]");
            Assertions.assertThat(upSideIndex).isLessThan(downSideIndex);
        }, 1, 0, 1);

    }

    public static void assertRandomNumberInRangeTest(
            final Executable executable,
            final Integer value,
            final Integer... values
    ) {

    }


    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
// 테스트 코드의 목적이 예외처리 확인뿐 아니라 모든 기능이 정상적으로 작동하는지 확인하는 용도로 더 자세하게 작성하는 연습을 해야겠다.