package bridge.view;

import bridge.validator.Validator;
import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    Validator validator = new Validator();

    /**
     * 다리의 길이를 입력받는다.
     */
//    public int readBridgeSize() {
//        int bridgeSize = 0;
//        while (!(bridgeSize >= 3 && bridgeSize <= 20)) {
//                String inputBridgeSize = Console.readLine();
////            if(!inputBridgeSize.matches("-?\\d+")){
////                throw new NumberFormatException("[ERROR]"); // 이렇게 발생시키는 것 외의 방법 궁금!!! catch를 해버리니깐 계속해서 a 입력되서 Error 무한출력
////            }
////                validator.validateInteger(inputBridgeSize); // 알파벳 입력 시 에러 발생시키고 진행하기
//
//            bridgeSize = Integer.parseInt(inputBridgeSize);
////            validator.validateBridgeSize(bridgeSize);
//
//
//        }
//        return bridgeSize;
//    }


    public String readStrBridgeSize() {
        String input = Console.readLine();
        return input;
    }

    /**
     * 다리 길이 입력받을 때
     * while (true)
     * try{
     *     옳은 값 입력받으면 break;
     * }catch(NumberFormatException)
     * 시도했지만 인덴트 문제, 뒤에서 값을 받아주지 못하는 문제로 예외처리 후 게임만 종료하도록 처리
     * 재귀를 이용해봤지만 stackoverflow 해결 x
     */

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        String move = Console.readLine();

        return move;
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        String readGameCommand = Console.readLine();
        return readGameCommand;
    }
}
