package bridge.controller;

public class Validate {
    final int MIN_RANGE = 3;
    final int MAX_RANGE = 20;
    final String NUM_REGEX = "[0-9]";
    final String ERROR = "[ERROR] ";

    //
    public boolean checkRange(String input) {
        try {
            if (Integer.parseInt(input) < MIN_RANGE || Integer.parseInt(input) > MAX_RANGE) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR + "다리 길이는 3부터 20 사이의 숫자여야 합니다.");
            return true;
        }
        return false;
    }

    public boolean checkMoving(String moving) {
        try {
            if (!moving.equals("U") && !moving.equals("D")) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR + "U와 D만 입력해주세요.");
            return true;
        }
        return false;
    }

    public boolean checkRetry(String retryKeyword) {
        try {
            if (!retryKeyword.equals("R") && !retryKeyword.equals("Q")) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR + "R과 Q만 입력해주세요.");
            return true;
        }
        return false;
    }

    public boolean checkNumOnly(String input) {
        try {
            if (!input.matches(NUM_REGEX)) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR + "숫자만 입력해주세요.");
            return true;
        }
        return false;
    }
}
