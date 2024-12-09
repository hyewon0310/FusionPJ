package Project;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class ClientHandler {
    private static Socket clientSocket;
    private static InputStream is;
    private static OutputStream os;
    // private static BufferedReader br;
    // private static BufferedWriter bw;
    private static final int MAX_RETRYCOUNT = 3;


    // 클라이언트 관리자 생성자
//    public ClientHandler() {
//        try {
//            clientSocekt = new Socket("192.168.0.10", 7777);
//            // 서버의 ip번호랑 port 번호
//            is = clientSocekt.getInputStream();
//            // buffer 정의를 여기서 해주어야 하는 지 고민
//            //br = new BufferedReader(new InputStreamReader(is));
//            os = clientSocekt.getOutputStream();
//            //bw = new BufferedWriter(new OutputStreamWriter(os));
//            System.out.println("Client Connected / 서버 접속 중입니다.");
//        } catch (IOException ioException) {
//            System.err.println(ioException);
//        }
//    }

    public ClientHandler() {
        try {
            clientSocket = new Socket("192.168.0.10", 7777);
            is = clientSocket.getInputStream();  // 클라이언트에서 InputStream 연결
            os = clientSocket.getOutputStream();  // 클라이언트에서 OutputStream 연결
            System.out.println("[DEBUG] ClientHandler - InputStream 및 OutputStream 연결됨");
        } catch (IOException ioException) {
            System.err.println("[ERROR] ClientHandler 연결 오류: " + ioException);
        }
    }

    // 프로토콜 송신
    //private static void send(Protocol protocol) throws Exception {
//    private void send(Protocol protocol) throws Exception {
//
//       try {
//            os.write(protocol.createPacket());
//            os.flush();
//            System.out.println("서버에게" + " 전송");
//        } catch (Exception e) {
//            System.err.println(e);
//        }
//    }

    private void send(Protocol protocol) throws Exception {
        try {
            byte[] packet = protocol.createPacket();
            os.write(packet);
            os.flush(); // 버퍼 강제 전송
            System.out.println("[DEBUG] 송신 데이터: " + Arrays.toString(packet));
        } catch (Exception e) {
            System.err.println("[ERROR] 데이터 송신 실패: " + e.getMessage());
            throw e;
        }
    }


    // 프로토콜 수신
    //private static Protocol receive(int type, int code) throws Exception
//    private Protocol receive(int type, int code) throws Exception {
//        // header부터 처리
//        int retryCount = 0;
//        byte[] header = new byte[Protocol.LEN_HEADER];
//        System.out.println("clientHandler 클래스의 receive 함수 호출중입니다.");
//        Protocol protocol = new Protocol();
//        while (retryCount < MAX_RETRYCOUNT) {
//            try {
//                // 프로토콜 헤더 수신 및 설정
//                is.read(header, 0, Protocol.LEN_HEADER);
//                protocol.setPacketHeader(header);
//
//                // 본문 길이 확인
//                int bodyLength = protocol.getBodyLength();
//                if (bodyLength < 0 || bodyLength > Protocol.LEN_BODYLENGTH) {
//                    throw new Exception("통신오류: 비정상적인 본문 길이");
//                }
//
//                // 프로토콜 본문 수신
//                byte[] buf = new byte[bodyLength];
//                int ReceivedLength = 0;
//                while (ReceivedLength < bodyLength) {
//                    int readSize = is.read(buf, ReceivedLength, bodyLength - ReceivedLength);
//                    if (readSize == -1) {
//                        throw new Exception("통신오류: 연결 끊어짐");
//                    }
//                    ReceivedLength += readSize;
//                }
//                protocol.setPacketBody(buf);
//
//                // 타입 검사: 성공 시 반환
//                if (protocol.getType() == type && protocol.getCode() == code) {
//                    return protocol; // 성공하면 종료
//                }
//                // 타입이 맞지 않으면 다음 시도
//                retryCount++;
//            } catch (IOException e) {
//                // 연결 실패 등 예외 발생 시
//                retryCount++;
//                System.err.println("수신 실패 시도: " + retryCount + " / " + MAX_RETRYCOUNT);
//            }
//        }
//        throw new Exception("통신오류: 응답 타임아웃");
//    }

//    private Protocol receive(int type, int code) throws Exception {
//        System.out.println("[DEBUG] receive() 호출: type=" + type + ", code=" + code);
//        int retryCount = 0;
//        byte[] header = new byte[Protocol.LEN_HEADER];
//        Protocol protocol = new Protocol();
//
//        while (retryCount < MAX_RETRYCOUNT) {
//            try {
//                System.out.println("[DEBUG] 헤더 읽기 시도 중...");
//
//                int bytesRead = is.read(header, 0, Protocol.LEN_HEADER);
//                System.out.println("[DEBUG] 헤더 읽기 완료: bytesRead=" + bytesRead);
//
//                if (bytesRead < Protocol.LEN_HEADER) {
//                    throw new IOException("헤더 데이터가 충분하지 않습니다. 읽은 바이트 수: " + bytesRead);
//                }
//
//                protocol.setPacketHeader(header);
//                System.out.println("[DEBUG] 헤더 설정 완료: bodyLength=" + protocol.getBodyLength());
//
//                // 본문 길이 확인
//                int bodyLength = protocol.getBodyLength();
//                if (bodyLength < 0 || bodyLength > Protocol.LEN_BODYLENGTH) {
//                    throw new Exception("통신오류: 비정상적인 본문 길이 (" + bodyLength + ")");
//                }
//
//                // 본문 읽기
//                byte[] buf = new byte[bodyLength];
//                int receivedLength = 0;
//                while (receivedLength < bodyLength) {
//                    System.out.println("[DEBUG] 본문 읽기 중: receivedLength=" + receivedLength);
//                    int readSize = is.read(buf, receivedLength, bodyLength - receivedLength);
//                    if (readSize == -1) {
//                        throw new IOException("통신오류: 연결이 끊어졌습니다.");
//                    }
//                    receivedLength += readSize;
//                }
//                protocol.setPacketBody(buf);
//                System.out.println("[DEBUG] 본문 읽기 완료: receivedLength=" + receivedLength);
//
//                // 타입과 코드 확인
//                if (protocol.getType() == type && protocol.getCode() == code) {
//                    System.out.println("[DEBUG] 프로토콜 확인 완료: type=" + type + ", code=" + code);
//                    return protocol;
//                }
//
//                retryCount++;
//                System.err.println("[ERROR] 타입 또는 코드 불일치: type=" + protocol.getType() + ", code=" + protocol.getCode());
//            } catch (IOException e) {
//                retryCount++;
//                System.err.println("[ERROR] receive() 중 IOException 발생: " + e.getMessage());
//            }
//        }
//
//        throw new Exception("통신오류: 응답 타임아웃");
//    }

//    private Protocol receive(int type, int code) throws Exception {
//        System.out.println("[DEBUG] receive() 호출: type=" + type + ", code=" + code);
//        int retryCount = 0;
//        byte[] header = new byte[Protocol.LEN_HEADER];
//        Protocol protocol = new Protocol();
//
//        while (retryCount < MAX_RETRYCOUNT) {
//            try {
//                System.out.println("[DEBUG] 헤더 읽기 시도 중... (재시도: " + retryCount + ")");
//                int bytesRead = is.read(header, 0, Protocol.LEN_HEADER);
//                if (bytesRead == -1) {
//                    throw new IOException("EOF 도달: 스트림이 닫혔습니다.");
//                }
//
//                if (bytesRead < Protocol.LEN_HEADER) {
//                    throw new IOException("헤더 데이터가 충분하지 않습니다. 읽은 바이트 수: " + bytesRead);
//                }
//
//                protocol.setPacketHeader(header);
//                System.out.println("[DEBUG] 헤더 설정 완료: bodyLength=" + protocol.getBodyLength());
//
//                // 본문 길이 확인
//                int bodyLength = protocol.getBodyLength();
//                if (bodyLength < 0 || bodyLength > Protocol.LEN_BODYLENGTH) {
//                    throw new Exception("통신오류: 비정상적인 본문 길이 (" + bodyLength + ")");
//                }
//
//                // 본문 읽기
//                if (bodyLength == 0) {
//                    protocol.setPacketBody(new byte[0]);
//                    if (protocol.getType() == type && protocol.getCode() == code) {
//                        System.out.println("[DEBUG] 본문 없음, 프로토콜 검증 성공");
//                        return protocol;
//                    }
//                    retryCount++;
//                    continue;
//                }
//
//                byte[] buf = new byte[bodyLength];
//                int receivedLength = 0;
//                while (receivedLength < bodyLength) {
//                    System.out.println("[DEBUG] 본문 읽기 중: receivedLength=" + receivedLength);
//                    int readSize = is.read(buf, receivedLength, bodyLength - receivedLength);
//                    if (readSize == -1) {
//                        throw new IOException("EOF 도달: 연결이 끊어졌습니다.");
//                    }
//                    receivedLength += readSize;
//                }
//                protocol.setPacketBody(buf);
//                System.out.println("[DEBUG] 본문 읽기 완료: receivedLength=" + receivedLength);
//
//                // 타입과 코드 확인
//                if (protocol.getType() == type && protocol.getCode() == code) {
//                    System.out.println("[DEBUG] 프로토콜 확인 완료: type=" + type + ", code=" + code);
//                    return protocol;
//                }
//
//                System.err.println("[ERROR] 타입 또는 코드 불일치: type=" + protocol.getType() + ", code=" + protocol.getCode());
//            } catch (IOException e) {
//                System.err.println("[ERROR] IOException 발생: " + e.getMessage());
//            }
//
//            retryCount++;
//            System.out.println("[DEBUG] 재시도 중... (현재 재시도 횟수: " + retryCount + ")");
//        }
//
//        throw new Exception("통신오류: 응답 타임아웃 (최대 재시도 횟수 초과)");
//    }


    private Protocol receive(int type, int code) throws Exception {
        int retryCount = 0;
        byte[] header = new byte[Protocol.LEN_HEADER];
        Protocol protocol = new Protocol();

        while (retryCount < MAX_RETRYCOUNT) {
            try {
                // 헤더 읽기
                int bytesRead = is.read(header, 0, Protocol.LEN_HEADER);
                if (bytesRead < Protocol.LEN_HEADER) {
                    throw new IOException("[ERROR] 헤더 수신 실패: 읽은 바이트 수=" + bytesRead);
                }
                System.out.println("[DEBUG] 수신된 헤더: " + Arrays.toString(header));
                protocol.setPacketHeader(header);

                // 본문 길이 확인
                int bodyLength = protocol.getBodyLength();
                if (bodyLength < 0 || bodyLength > Protocol.LEN_BODYLENGTH) {
                    throw new Exception("통신오류: 비정상적인 본문 길이=" + bodyLength);
                }

                // 본문 읽기
                byte[] buf = new byte[bodyLength];
                int totalRead = 0;
                while (totalRead < bodyLength) {
                    int readSize = is.read(buf, totalRead, bodyLength - totalRead);
                    if (readSize == -1) {
                        throw new IOException("[ERROR] 본문 수신 중 EOF 도달");
                    }
                    totalRead += readSize;
                }
                protocol.setPacketBody(buf);
                System.out.println("[DEBUG] 수신된 본문: " + protocol.getType());

                // 타입 및 코드 확인
                if (protocol.getType() == type && protocol.getCode() == code) {
                    return protocol;
                } else {
                    System.err.println("[ERROR] 수신된 프로토콜 타입/코드 불일치: type=" + protocol.getType() +
                            ", code=" + protocol.getCode());
                    retryCount++;
                }
            } catch (IOException e) {
                retryCount++;
                System.err.println("[ERROR] receive() IOException 발생: " + e.getMessage());
            }
        }

        throw new Exception("통신오류: 응답 타임아웃");
    }


    // 선발 일정 및 비용 확인 조회 요청 및 확인
    // public static String[] scheduleList_viewReq() throws Exception {
    public String[] scheduleList_viewReq() throws Exception {
        Protocol requestNameList = new Protocol(Protocol.TYPE6_VIEW_REQ, Protocol.T6_CODE0_SELECTION_SCHEDULENAME_LIST);
        send(requestNameList);
        Protocol responseNameList = receive(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE0_SELECTION_SCHEDULENAME_LIST);
        String[] nameList= responseNameList.byteToStrArr(responseNameList.getBody(),10);
//        for (int i = 0; i < nameList.length; i++)
//        {
//            System.out.println("일정 이름 : " + nameList[i]);
//        }

        Protocol requestPeriodList = new Protocol(Protocol.TYPE6_VIEW_REQ, Protocol.T6_CODE1_SELECTION_PERIOD_OF_SCHEDULE_LIST);
        send(requestPeriodList);
        Protocol responsePeriodList = receive(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE1_SELECTION_PERIOD_OF_SCHEDULE_LIST);
        String[] periodList = responsePeriodList.byteToStrArr(responsePeriodList.getBody(),10);
//        for (int i = 0; i < periodList.length; i++)
//        {
//            System.out.println("일정 기간 : " + periodList[i]);
//        }
        String[] result = new String[nameList.length];
        for (int i = 0; i < nameList.length; i++)
        {
            result[i] =  nameList[i] + " " + periodList[i];
        }
        return result;
    }

    // 로그인 진행 (User의 회원가입은 진행 X, DB가 회원 정보를 가지고 있다고 가정한다.)
    public int[] longin(String id, String pwd) throws Exception {
        int[] result = new int[2];
        Protocol requestCheckID = new Protocol(Protocol.TYPE0_LOGIN, Protocol.T0_CODE0_ID);
        requestCheckID.setBody(id);
        send(requestCheckID);
        Protocol responseCheckID = receive(Protocol.TYPE0_LOGIN, Protocol.T0_CODE2_SUCCESS);
        result[0] = responseCheckID.getCode();

        Protocol requestCheckPWD = new Protocol(Protocol.TYPE0_LOGIN, Protocol.T0_CODE1_PWD);
        String[] pwdInfo = { id, pwd};
        requestCheckPWD.setBody(pwdInfo, 5);
        send(requestCheckPWD);
        Protocol responseCheckPWD = receive(Protocol.TYPE0_LOGIN, Protocol.T0_CODE2_SUCCESS);
        result[1] = responseCheckPWD.getCode();
        return result;
    }



    // 기숙사 사용료 리스트 조회
    // public static String[] dormitoryFeeList_viewReq() throws Exception {
    public String[] dormitoryFeeList_viewReq() throws Exception {
        Protocol requestNameList = new Protocol(Protocol.TYPE6_VIEW_REQ, Protocol.T6_CODE2_DORMITORY_NAME_LIST);
        send(requestNameList);
        Protocol responseNameList = receive(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE2_DORMITORY_NAME_LIST);
        String[] nameList= responseNameList.byteToStrArr(responseNameList.getBody(),10);

        Protocol requestTypeList = new Protocol(Protocol.TYPE6_VIEW_REQ, Protocol.T6_CODE3_DORMITORY_TYPE_LIST);
        send(requestTypeList);
        Protocol responseTypeList = receive(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE3_DORMITORY_TYPE_LIST);
        String[] typeList= responseTypeList.byteToStrArr(responseTypeList.getBody(),1);
        // int형이라 fixByteLength를 1로 해두었어요~~ 혹시 크기가 작아서 에러가 난다면 키워주세용

        Protocol requestGenderList = new Protocol(Protocol.TYPE6_VIEW_REQ, Protocol.T6_CODE4_DORMITORY_GENDER_LIST);
        send(requestGenderList);
        Protocol responseGenderList = receive(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE4_DORMITORY_GENDER_LIST);
        String[] genderList= responseGenderList.byteToStrArr(responseGenderList.getBody(),1);
        // int형이라 fixByteLength를 1로 해두었어요~~ 혹시 크기가 작아서 에러가 난다면 키워주세용

        Protocol requestFeeList = new Protocol(Protocol.TYPE6_VIEW_REQ, Protocol.T6_CODE5_DORMITORYFEE_LIST);
        send(requestFeeList);
        Protocol responseFeeList = receive(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE5_DORMITORYFEE_LIST);
        String[] feeList= responseFeeList.byteToStrArr(responseFeeList.getBody(),10);

        String[] result = new String[nameList.length];
        for (int i = 0; i < nameList.length; i++)
        {
            result[i] = nameList[i] + " " + typeList[i] + " " + genderList[i] + " " + feeList[i];
            // 한 문자열에서 공백을 기준으로 데이터를 나누어서 클라이언트 프론트에서 처리
        }
        return result;
    }


    // 급식비 리스트 조회
    // public static String[] mealCostList_viewReq() throws Exception {
    public String[] mealCostList_viewReq() throws Exception {
        Protocol requestNameList = new Protocol(Protocol.TYPE6_VIEW_REQ, Protocol.T6_CODE2_DORMITORY_NAME_LIST);
        send(requestNameList);
        Protocol responseNameList = receive(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE2_DORMITORY_NAME_LIST);
        String[] nameList= responseNameList.byteToStrArr(responseNameList.getBody(),10);

        Protocol requestDormitoryTypeList = new Protocol(Protocol.TYPE6_VIEW_REQ, Protocol.T6_CODE3_DORMITORY_TYPE_LIST);
        send(requestDormitoryTypeList);
        Protocol responseDormitoryTypeList = receive(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE3_DORMITORY_TYPE_LIST);
        String[] typeList= responseDormitoryTypeList.byteToStrArr(responseDormitoryTypeList.getBody(),1);

        Protocol requestGenderList = new Protocol(Protocol.TYPE6_VIEW_REQ, Protocol.T6_CODE4_DORMITORY_GENDER_LIST);
        send(requestGenderList);
        Protocol responseGenderList = receive(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE4_DORMITORY_GENDER_LIST);
        String[] genderList= responseGenderList.byteToStrArr(responseGenderList.getBody(),1);

        Protocol requestMealTypeList = new Protocol(Protocol.TYPE6_VIEW_REQ, Protocol.T6_CODE6_MEALTYPE_LIST);
        send(requestMealTypeList);
        Protocol responseMealTypeList = receive(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE6_MEALTYPE_LIST);
        String[] mealTypeList= responseMealTypeList.byteToStrArr(responseMealTypeList.getBody(),1);

        Protocol requestMealCostList = new Protocol(Protocol.TYPE6_VIEW_REQ, Protocol.T6_CODE7_MEALCOST_LIST);
        send(requestMealCostList);
        Protocol responseMealCostList = receive(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE7_MEALCOST_LIST);
        String[] mealCostList= responseMealCostList.byteToStrArr(responseMealCostList.getBody(),10);

        String[] result = new String[nameList.length];
        for (int i = 0; i < nameList.length; i++)
        {
            result[i] = nameList[i] + " " + typeList[i] + " " + genderList[i] + " " + mealTypeList[i] + " " + mealCostList[i];
            // 한 문자열에서 공백을 기준으로 데이터를 나누어서 클라이언트 프론트에서 처리
        }
        return result;
    }

    // 선발 일정 명 등록
    // public static int scheduleName_registerReq(String scheduleName) throws Exception {
    public int scheduleName_registerReq(String scheduleName) throws Exception {
        // 선발일정 id 넘겨주어서 선발일정 리스트 조회
        Protocol request = new Protocol(Protocol.TYPE2_REGISTER_REQ, Protocol.T2_CODE4_SELECTION_SCHEDULE_NAME);
        request.setBody(scheduleName);
        send(request);
        Protocol response = receive(Protocol.TYPE3_REGISTER_RES, Protocol.T3_CODE0_SUCCESS);
        return response.getCode();
    }   // 리턴 값 code = 0이면 성공

    // 선발 일정 기간 등록
    public int schedulePeriod_registerReq(String schedulePeriod) throws Exception {
        Protocol request = new Protocol(Protocol.TYPE2_REGISTER_REQ, Protocol.T2_CODE5_SELECTION_SCHEDULE_PERIOD);
        request.setBody(schedulePeriod);
        send(request);
        Protocol response = new Protocol(Protocol.TYPE3_REGISTER_RES, Protocol.T3_CODE0_SUCCESS);
        return response.getCode();
    }

    // 선발된 생활관 사용료 조회 (로그인 한 학생의 정보를 DB에서 이용)
    // public int dormitoryFee_viewReq(String dormitoryName, String dormitryType) throws Exception {
    public int dormitoryFee_viewReq() throws Exception {
        Protocol viewRequest = new Protocol(Protocol.TYPE6_VIEW_REQ, Protocol.T6_CODE10_DORMITORY_FEE);
        send(viewRequest);
        Protocol viewResponse = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE10_DORMITORY_FEE);
        return viewResponse.getCode();
    }

    // 급식비 조회 (로그인 한 학생의 정보를 DB에서 이용)
    public int mealCost_viewReq() throws Exception {
        Protocol viewRequest = new Protocol(Protocol.TYPE6_VIEW_REQ, Protocol.T6_CODE11_MEAL_COST);
        send(viewRequest);
        Protocol viewResponse = new Protocol(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE11_MEAL_COST);
        return viewResponse.getCode();
    }

    // 보조 기능 - 생활관ID 구하기 (꼭 구현해야 하는 기능이 아닌 기능 구현에 반복적으로 사용되는 부분 추출)
    // 모든 정보를 받은 후 찾는 경우를 위한 함수
    public String getdormitoryID(String dormitoryName, String dormitoryType, String gender) throws Exception {
        Protocol viewRequest = new Protocol (Protocol.TYPE6_VIEW_REQ, Protocol.T6_CODE13_DORMITORY_ID);
        String[] dormitoryInfo = {dormitoryName, dormitoryType, gender};
        viewRequest.setBody(dormitoryInfo, 3, 1, 1);
        send(viewRequest);
        Protocol viewResponse = receive(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE13_DORMITORY_ID);
        String dormitoryID = viewResponse.getBody().toString();
        return dormitoryID;
    }

    // 보조 기능 - 생활관ID 구하기 (DB의 성별을 통해 구하므로 매개변수 2개 사용)
    // 반환값 동일, 매개변수만 다른 경우 오버라이딩 불가능 (다른 네이밍 생각해보기)
    public String getdormitoryID(String dormitoryName, String dormitoryType) throws Exception {
        Protocol viewRequest = new Protocol (Protocol.TYPE6_VIEW_REQ, Protocol.T6_CODE13_DORMITORY_ID);
        String[] dormitoryInfo = {dormitoryName, dormitoryType};
        viewRequest.setBody(dormitoryInfo, 10);
        send(viewRequest);
        Protocol viewResponse = receive(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE13_DORMITORY_ID);
        String dormitoryID = viewResponse.getBody().toString();
        return dormitoryID;
    }


    // 생활관 사용료 등록 (관리자)
    public int dormitoryFee_registerReq(String dormitoryName, String dormitoryType, String gender, String dormitoryFee) throws Exception {
        // 흐름 1. 생활관iD 조회
        String dormitoryID = getdormitoryID(dormitoryName, dormitoryType, gender);

        // 흐름 2. 생활관 사용료 등록
        Protocol registerRequest = new Protocol(Protocol.TYPE2_REGISTER_REQ, Protocol.T2_CODE6_DORMITORY_FEE);
        String[] dormitoryFeeInfo = { dormitoryID, dormitoryFee };
        registerRequest.setBody(dormitoryFeeInfo, 4);
        // dormitoryFee 제외는 생활관 종류를 의미, dormitoryFee는 등록할 생활관 사용료 정보
        send(registerRequest);
        Protocol regusterResponse = receive(Protocol.TYPE3_REGISTER_RES, Protocol.T3_CODE0_SUCCESS);
        return regusterResponse.getCode();
    }

    // 급식비 등록
    public int mealCost_registerReq(String dormitoryName, String dormitoryType, String gender, String mealType, String mealCost) throws Exception {
        // 흐름 1. 생활관iD 조회
        String dormitoryID = getdormitoryID(dormitoryName, dormitoryType, gender);

        // 흐름 2. 급식비 등록
        Protocol registerRequest = new Protocol(Protocol.TYPE2_REGISTER_REQ, Protocol.T2_CODE7_MEAL_COST);
        String[] dormitoryFeeInfo = { dormitoryID, mealType, mealCost };
        registerRequest.setBody(dormitoryFeeInfo, 4);
        send(registerRequest);
        Protocol registerResponse = receive(Protocol.TYPE3_REGISTER_RES, Protocol.T3_CODE0_SUCCESS);
        return registerResponse.getCode();
    }

   // 입사 신청 (입사신청일은 해당 데이터를 받으면 자동 값이 들어가도록 코드로 처리)
    public int application_registerReq(String dormitoryName, String dormitoryType, String mealSelection, String priority) throws Exception {
        // 흐름 1. 입사하고자 하는 기숙사 ID 받아오기
        String dormitoryID = getdormitoryID(dormitoryName, dormitoryType);

        Protocol registerRequest = new Protocol(Protocol.TYPE2_REGISTER_REQ, Protocol.T2_CODE0_APPLICATION_INFO);
        String[] applicationInfo = { dormitoryID, mealSelection, priority };
        registerRequest.setBody(applicationInfo, 2);
        send(registerRequest);
        Protocol registerResponse = receive(Protocol.TYPE3_REGISTER_RES, Protocol.T3_CODE0_SUCCESS);
        return registerResponse.getCode();
    }

    //public String[] dormitoryFeeList_viewReq

    // TEST용 ID를 통해 해당 기숙사 정보 조회
    public String[] dormitoryInfo_viewReq(String dormitoryName, String dormitoryType, String gender) throws Exception {
        String dormitoryID = getdormitoryID(dormitoryName, dormitoryType, gender);

        Protocol viewRequest = new Protocol(Protocol.TYPE6_VIEW_REQ, Protocol.T6_CODE22_DORMITORYINFO);
        viewRequest.setBody(dormitoryID);
        send(viewRequest);
        Protocol response = receive(Protocol.TYPE7_VIEW_RES, Protocol.T7_CODE22_DORMITORYINFO);
        // 넘어오는 값 순서대로 : DormitoryId, DormitoryName, DormitoryType, Cost, Gender, Capacity
        String[] dormotoryInfo = response.dataExtraction(response.getBody(), 6);
        return dormotoryInfo;
    }


}
