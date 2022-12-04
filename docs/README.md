#### 기능 목록
** 새로 학습할 키워드 **
enum
StringJoiner
정규표현식

** TO DO **
1) 게임 재시작 로직 수정(index == size 일 때 종료되도록)
2) 예외 처리
3) enum 클래스 만들기 // 수정 예정


index의 역할
컴퓨터가 만든 다리, 사용자가 입력한 횟수
사용자가 현재 위치한 칸이라고 생각하기

1. 게임 시작
- [x] 게임 시작 문구를 출력한다. OutputView#printStart
- [x] 다리 길이 입력 안내 문구를 출력한다. OutputView#printInputSize

2. 게임 실행
2-1. 다리 생성
- [x] 다리 길이를 숫자로 입력받는다. InputView#readBridgeSize()
- [x] 입력받은 숫자의 길이를 가진 다리를 생성한다.
    - [x] 다리는 위아래 두 칸으로 출력한다.
    - [x] 사용자가 3부터 20 사이 외 숫자를 입력하면 오류 메시지를 출력한다. Validate // 문자
- [x] '다리 길이 입력 안내 문구'부터 과정을 반복한다.

2-2.칸 이동
- [x] 이동할 칸 입력 안내 문구를 출력한다. OutputView#printInputMoving
- [x] 다리를 생성할 때 위 칸과 아래 칸 중 건널 수 있는 칸은 0과 1 중 무작위 값을 이용해서 정한다. BridgeRandomNumberGenerator#generate()
    - [x] 이동한 칸을 건널 수 있다면 O로 표시, 건널 수 없다면 X로 표시하도록 한다. BridgeMaker
    - [x] 이동은 왼쪽에서 오른쪽으로만 가능하도록 한다. BridgeGame#move()
    - [x] 이동은 한 번에 위아래로 한 칸만 가능하도록 한다. BridgeGame#move()

- [x] 플레이어가 이동할 칸을 입력받는다.(위: U, 아래: D) InputView#readMoving()
    - [x] 이동 키워드 외 문자를 입력하면 오류 메시지를 출력한다. Validate // 숫자

- [x] 현재까지 이동한 다리의 칸을 모두 출력한다. OutputView#printMap()
    - [x] 생성한 다리를 출력한다.(플레이어가 위치한 칸) OutputView#printMap()
    - [x] 다리의 시작과 끝은 '[', ']'로 출력한다. OutputView#printMap()
    - [x] 다리 사이의 구분은 ' | '로 출력한다.(앞뒤 공백 포함) OutputView#printMap()

    - [x] 입력받은 칸으로 이동했다면 'O'를 출력한다. OutputView#printMap()
    - [x] 입력받은 칸이 이동 불가한 칸이라면 'X'를 출력한다. OutputView#printMap()
    - [x] 선택되지 않은 칸은 공백으로 출력한다. OutputView#printMap()

- [x] 이동 성공 시(이동한 칸이 O라면), '이동할 칸 입력 안내 문구 출력 ~ O,X 출력'까지의 과정을 반복한다.

3. 게임 실행 결과
- [x] 이동 실패 시(이동한 칸이 X라면), 게임 재시작 문구를 출력한다.(재시도: R, 종료: Q) OutputView
  - [x] 재시작 키워드를 입력 받는다.(재시도: R, 종료: Q) InputView#readGameCommand()
  - [x] R을 입력받으면 게임을 재시작하도록 한다. BridgeGame#retry()
  - [x] 게임 재시작 시 생성했던 다리 그대로 처음부터 게임을 재시작하도록 한다. BridgeGame#retry()
  - [x] Q를 입력받으면 게임을 종료하도록 한다.
  - [x] 게임 종료 문구를 출력한다.(최종 게임 결과, 게임 성공 여부, 총 시도한 횟수) OutputView#printResult()
  - [x] 재시작 키워드 외 문자를 입력하면 오류 메시지를 출력한다. Validate // 숫자
- [x] 모든 칸 이동에 성공했다면(이동한 칸이 모두 O), 게임 종료 문구를 출력한다.(최종 게임 결과, 게임 성공 여부, 총 시도한 횟수) OutputView#printResult()

#### 예외 처리
- [x] 예외가 발생해도 게임은 계속 진행되도록 한다.
- [x] 에러 문구는 '[ERROR]' 로 시작하도록 한다.