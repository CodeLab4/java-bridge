package bridge.validator;

import bridge.view.BridgeConstant;
import bridge.view.InputView;
import camp.nextstep.edu.missionutils.Console;

public class Validator {

    private final int BRIDGE_LEAST_SIZE = 3;
    private final int BRIDGE_MAX_SIZE = 20;


    // 3 - 20 사이의 숫자만 입력
    public void validateBridgeSize(int bridgeSize) {
        try {
            if (bridgeSize < BRIDGE_LEAST_SIZE || bridgeSize > BRIDGE_MAX_SIZE) {
                throw new IllegalArgumentException(BridgeConstant.Error.getValue());
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(BridgeConstant.Error.getValue() + " " + BridgeConstant.BridgeSizeError.getValue());
            System.out.println(BridgeConstant.InputBridgeSize.getValue());
        }
    }

    // 숫자만 입력
    public void validateInteger(String input) {

            try {
                if(!input.matches("-?\\d+")){
                    throw new NumberFormatException("[ERROR]"); // 이렇게 발생시키는 것 외의 방법 궁금!!!
                }
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println(BridgeConstant.Error.getValue());
            }

    }

    // U 와 D 이외의 오류
    public void validateUpDown(String input) {
            if (!input.equals("U") && !input.equals("D")) {
                throw new IllegalArgumentException(
                        BridgeConstant.Error.getValue() + BridgeConstant.BridgeInputError.getValue());
            }
    }

    // R 과 Q 이외 오류처리
    public void validateReadCommand(String input) {
        try {
            if (!input.equals("Q") && !input.equals("R")) {
                throw new IllegalArgumentException(
                        BridgeConstant.Error.getValue() + BridgeConstant.BridgeRetryError.getValue());
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(BridgeConstant.Error.getValue() + " " + BridgeConstant.BridgeRetryError.getValue());
        }
    }
}
