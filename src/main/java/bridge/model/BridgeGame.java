package bridge.model;

import bridge.controller.BridgeController;
import bridge.validator.Validator;
import bridge.view.InputView;
import bridge.view.OutputView;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    OutputView outputView = new OutputView();
    InputView inputView = new InputView();
    Validator validator = new Validator();
    BridgeController bridgeController = new BridgeController();

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public int move(int moveIndex) {
        return moveIndex++;
    }
    // move 에 다른 역할을 부여하기 보단, 현재 다리 위치를 표현해 주는 역할을 수행하는 것이 옳다고 생각
    // 4주차 미션을 진행하면서는 index 생각을 못하고 U D 를 받아서 반환만 해주는 필요없는 메서드 역할만 수행
    // 이번에는 하나의 행동을 담당하는 역할로 성장...?



// retry 또한  R Q 를 반환해주는 역할에서 뭔가 주체적으로 행동하는 메서드로 진화...?
    public boolean retry(boolean checkGameCommand, StringBuilder sbUp, StringBuilder sbDown) {

        while (checkGameCommand) {
            outputView.printSelectGameRestart();
            String gameCommand = inputView.readGameCommand();
            validator.validateReadCommand(gameCommand);
            if (gameCommand.equals("Q")) {
                return checkGameCommand = false;
            }
            if(gameCommand.equals("R")){
                sbUp.delete(0, sbUp.length());
                sbDown.delete(0, sbDown.length());
                bridgeController.gameCount++;
                break;
            }
        }
        return checkGameCommand;
    }
}
