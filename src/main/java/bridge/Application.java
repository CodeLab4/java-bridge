package bridge;

public class Application {

    public static void main(String[] args) {
        BridgeGame bridgeGame = new BridgeGame();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        BridgeGameController bridgeGameController = new BridgeGameController(bridgeGame, inputView, outputView);
        bridgeGameController.initialSetting();
        bridgeGameController.gameStart();
        bridgeGameController.endGame();
    }
}
