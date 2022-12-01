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

}
