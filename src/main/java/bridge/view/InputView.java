package bridge.view;

import bridge.validator.Validator;
import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    Validator validator = new Validator();

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        int bridgeSize = 0;
        while(!(bridgeSize>=3 && bridgeSize <= 20)) {
            String inputBridgeSize = Console.readLine();
            bridgeSize = Integer.parseInt(inputBridgeSize);
            validator.validateBridgeSize(bridgeSize);
        }
        return bridgeSize;
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        return null;
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        return null;
    }
}
