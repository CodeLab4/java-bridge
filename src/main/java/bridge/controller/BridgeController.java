package bridge.controller;

import bridge.model.BridgeGame;
import bridge.model.BridgeMaker;
import bridge.model.CompareBridge;
import bridge.validator.Validator;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BridgeController {

    InputView inputView;
    OutputView outputView;
    BridgeGame bridgeGame;
    BridgeMaker bridgeMaker;
    CompareBridge compareBridge;
    Validator validator;

    public BridgeController(InputView inputView, OutputView outputView,BridgeGame bridgeGame, BridgeMaker bridgeMaker, CompareBridge compareBridge, Validator validator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.bridgeGame = bridgeGame;
        this.bridgeMaker = bridgeMaker;
        this.compareBridge = compareBridge;
        this.validator = validator;
    }

    public void gameStart() {
        int bridgeSize = 0; // 다리 길이
        List<String> randomBridge = new ArrayList<>(); // 랜덤한 다리 생성

        // 다리 길이 입력, 예외 발생 시 계속 돌아가도록
        outputView.printStartGame();
        outputView.printPutBridgeSize();
        bridgeSize = inputView.readBridgeSize();


        // 브릿지 생성하고
        randomBridge = bridgeMaker.makeBridge(bridgeSize);
        // 생성된 브릿지 출력, 나중에 삭제할 것
        System.out.println(randomBridge);


//        while(true;){

            // 여기서 입력받고
            // 비교하고
            // 비교한 값 출력하고
            // 게임을 종료한다면 end = false;

//        }


    }


}
