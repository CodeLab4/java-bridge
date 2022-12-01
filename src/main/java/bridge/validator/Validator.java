package bridge.validator;

import bridge.view.BridgeConstant;

public class Validator {

    private final int BRIDGE_LEAST_SIZE = 3;
    private final int BRIDGE_MAX_SIZE = 20;

    public void validateBridgeSize(int bridgeSize) {
        try {
            if (bridgeSize < BRIDGE_LEAST_SIZE || bridgeSize > BRIDGE_MAX_SIZE) {
                throw new IllegalArgumentException(BridgeConstant.Error.getValue());
            }
        }catch (IllegalArgumentException illegalArgumentException){
            System.out.println(BridgeConstant.Error.getValue() + BridgeConstant.BridgeSizeError.getValue());
            System.out.println(BridgeConstant.InputBridgeSize.getValue());
        }
    }

    // U 와 D 이외의 오류
    public void validateUpDown(String input) {
        try{
        if(!input.equals("U") || !input.equals("D")) {
            throw new IllegalArgumentException(BridgeConstant.Error.getValue() + BridgeConstant.BridgeInputError.getValue());
        }
        }catch (IllegalArgumentException illegalArgumentException){
            System.out.println(BridgeConstant.Error.getValue() + BridgeConstant.BridgeInputError.getValue());
            System.out.println(BridgeConstant.SelectBridgeStep.getValue());
        }
    }

}
