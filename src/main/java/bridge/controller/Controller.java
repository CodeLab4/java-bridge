package bridge.controller;

import bridge.model.BridgeGame;
import bridge.model.BridgeMaker;
import bridge.model.BridgeRandomNumberGenerator;
import bridge.view.InputView;
import bridge.view.OutputView;

import java.util.List;

public class Controller {

    OutputView outputView = new OutputView();
    InputView inputView = new InputView();
    BridgeRandomNumberGenerator bridgeRandomNumberGenerator = new BridgeRandomNumberGenerator();
    BridgeMaker bridgeMaker = new BridgeMaker(bridgeRandomNumberGenerator);
    BridgeGame bridgeGame = new BridgeGame();
    Validate validate = new Validate();


    public void gameStart() {
        // 게임 시작 문구 출력
        outputView.printStart();
        outputView.printInputSize();

        // 다리 길이 입력 받기
        boolean hasSize = true;
        String input = "";
        while (hasSize) {
            input = inputView.readBridgeSize();
            if (!(validate.checkNumOnly(input) || validate.checkRange(input))) {
                break;
            }
        }
        int size = Integer.parseInt(input);

        List<String> bridge = bridgeMaker.makeBridge(size);
        boolean gameRunning = true;
        boolean moveSuccess = false;

        while (gameRunning) {

            while (BridgeGame.index < size) {
                outputView.printInputMoving();

                // 이동 키워드 입력 받기
                boolean hasMoveKeyword = true;
                String moving = "";
                while (hasMoveKeyword) {
                    moving = inputView.readMoving();
                    if (validate.checkMoving(moving)) {     // 이동 키워드 예외 처리
                        continue;
                    }

                    hasMoveKeyword = false;
                }
                moveSuccess = bridgeGame.move(bridge, moving);

                // 이동 실패했을 경우
                if (!moveSuccess) {
                    outputView.printMap(moveSuccess, moving);
                    outputView.printRestart();

                    // 게임 재시작 키워드 입력 받기
                    boolean hasRetryKeyword = true;
                    String retryKeyword = "";
                    while (hasRetryKeyword) {
                        retryKeyword = inputView.readGameCommand();
                        if (validate.checkRetry(retryKeyword)) {    // 재시작 키워드 예외 처리
                            continue;
                        }
                        hasRetryKeyword = false;
                    }
                    boolean retryQuit = bridgeGame.retry(retryKeyword);
                    if (retryQuit) {
                        BridgeGame.index = 0;
                        continue;
                    } else {
                        break;
                    }
                }
                outputView.printMap(moveSuccess, moving);
            }
            outputView.printResult(moveSuccess, BridgeGame.gameCount);

            gameRunning = false;
        }
    }
}
