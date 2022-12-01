package bridge.view;

import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {
    public static StringBuilder stringBuilderUp = new StringBuilder();
    public static StringBuilder stringBuilderDown = new StringBuilder();

    public enum Message {
        GAME_START_MESSAGE("다리 건너기 게임을 시작합니다. \n"),
        GAME_BRIDGE_LENGTH_INPUT_MESSAGE("다리의 길이를 입력해주세요."),
        GAME_PROGRESS_MOVE_POSITION_MESSAGE("이동할 칸을 선택해주세요. (위: U, 아래: D)"),
        GAME_RETRY_MESSAGE("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)"),
        GAME_RESULT_HEADER_MESSAGE("최종 게임 결과"),
        GAME_RESULT_BODY_FLAG_MESSAGE("게임 성공 여부: "),
        GAME_RESULT_BODY_COUNT_MESSAGE("총 시도한 횟수: "),
        ERROR_INPUT_RANGE_MESSAGE("다리 길이는 3부터 20 사이의 숫자여야 합니다."),
        ERROR_INPUT_NUMBER_MESSAGE("숫자만 입력해 주세요."),
        ERROR_INPUT_UP_DOWN_MESSAGE("U, D만 입력홰 주세요."),
        ERROR_INPUT_RETRY_MESSAGE("R, Q만 입력해 주세요.");
        private final String message;

        Message(String message) {
            this.message = message;
        }

        public String errorMessage() {
            System.out.println("[ERROR] " + message);
            return "[Error] " + message;
        }

        public void infoMessage() {
            System.out.println(message);
        }

    }

    public void printMap(int moveIndex, String inputUpDown, List<String> bridges) {
        checkFirst(moveIndex);
        checkUp(moveIndex, inputUpDown, bridges);
        stringBuilderUp.append(" ]");
        stringBuilderDown.append(" ]");
        System.out.println(stringBuilderUp);
        System.out.println(stringBuilderDown);
    }

    public void checkFirst(int moveIndex) {
        if (moveIndex == 0) {
            stringBuilderUp.append("[ ");
            stringBuilderDown.append("[ ");
            return;
        }
        stringBuilderUp.replace(stringBuilderUp.length() - 2, stringBuilderUp.length(), " | ");
        stringBuilderDown.replace(stringBuilderDown.length() - 2, stringBuilderDown.length(), " | ");
    }

    public void checkUp(int moveIndex, String inputUpDown, List<String> bridges) {
        if (inputUpDown.equals("U")) {
            if (bridges.get(moveIndex).equals("U")) {
                stringBuilderUp.append("O");
                stringBuilderDown.append(" ");
                return;
            }
            stringBuilderUp.append("X");
            stringBuilderDown.append(" ");
        }
        checkDown(moveIndex, inputUpDown, bridges);
    }

    public void checkDown(int moveIndex, String inputUpDown, List<String> bridges) {
        if (inputUpDown.equals("D")) {
            if (bridges.get(moveIndex).equals("D")) {
                stringBuilderUp.append(" ");
                stringBuilderDown.append("O");
                return;
            }
            stringBuilderUp.append(" ");
            stringBuilderDown.append("X");
        }
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(String gameFlag, int gameCount) {
        System.out.println(stringBuilderUp);
        System.out.println(stringBuilderDown + "\n");
        System.out.println("게임 성공 여부: " + gameFlag);
        System.out.println("총 시도한 횟수: " + gameCount);
    }
}
