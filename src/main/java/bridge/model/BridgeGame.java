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
