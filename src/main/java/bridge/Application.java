package bridge;

import bridge.controller.BridgeGameController;

public class Application {

    public static void main(String[] args) {
        BridgeGameController bridgeGameController = new BridgeGameController();
        bridgeGameController.gameStart();
        int size = bridgeGameController.bridgeSizeChecker();
        bridgeGameController.gameProgress(size);

    }
}
