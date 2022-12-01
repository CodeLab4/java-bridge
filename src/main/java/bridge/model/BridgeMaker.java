package bridge.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 다리의 길이를 입력 받아서 다리를 생성해주는 역할을 한다.
 */
public class BridgeMaker {

    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    /**
     * @param size 다리의 길이
     * @return 입력받은 길이에 해당하는 다리 모양. 위 칸이면 "U", 아래 칸이면 "D"로 표현해야 한다.
     * 0 아래칸, 1이면 윗칸
     */
    public List<String> makeBridge(int size) {
        List<String> randomBridge = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            randomBridge = makeStringBridge(randomBridge);
        }
        return randomBridge;
    }

    // 랜덤 다리 생성
    public List<String> makeStringBridge(List<String> randomBridge) {
        int randomNum = bridgeNumberGenerator.generate();
        if (randomNum == 0) {
            randomBridge.add("D");
        }
        if (randomNum == 1) {
            randomBridge.add("U");
        }
        return randomBridge;
    }

}
