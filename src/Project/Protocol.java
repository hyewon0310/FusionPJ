package Project;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class Protocol {
    final static public int TYPE0_LOGIN = 0;
    final static public int TYPE1_LOGINOUT = 1;
    final static public int TYPE2_REGISTER_REQ = 2;
    final static public int TYPE3_REGISTER_RES = 3;
    final static public int TYPE4_DELETE_REQ = 4;
    final static public int TYPE5_DELETE_RES = 5;
    final static public int TYPE6_VIEW_REQ = 6;
    final static public int TYPE7_VIEW_RES = 7;
    final static public int TYPE8_UPDATE_REQ = 8;
    final static public int TYPE9_UPDATE_RES = 9;
    final static public int UNDEFINED_TYPE = 10;

    final static public int T0_CODE0_ID = 0;
    final static public int T0_CODE1_PWD = 1;
    final static public int T0_CODE2_SUCCESS = 2;
    final static public int T0_CODE3_FAIL = 3;

    final static public int T1_CODE0_SUCCESS = 0;
    final static public int T1_CODE1_FAIL = 1;

    // 입사신청서
    final static public int T2_CODE0_APPLICATION_INFO = 0;
    // 납부 금액
    final static public int T2_CODE1_PAYMENT_AMOUNT = 1;
    // 결핵진단서
    final static public int T2_CODE2_TUBERCULOSIS_CERTIFICATE = 2;
    // 퇴사신청서
    final static public int T2_CODE3_UNAPPLICATION_INFO = 3;
    // 선발일정명
    final static public int T2_CODE4_SELECTION_SCHEDULE_NAME = 4;
    // 선발 일정 기간
    final static public int T2_CODE5_SELECTION_SCHEDULE_PERIOD = 5;
    // 생활관 사용료
    final static public int T2_CODE6_DORMITORY_FEE = 6;
    // 급식비
    final static public int T2_CODE7_MEAL_COST = 7;


    final static public int T3_CODE0_SUCCESS = 0;
    final static public int T3_CODE1_FAIL = 1;

    // DELETE의 코드는 어떻게 해주어야 할까용...??

    // 선발일정 리스트
    final static public int T6_CODE0_SELECTION_SCHEDULE_LIST = 0;
    // 생활관 사용료 리스트
    final static public int T6_CODE1_DORMITORY_FEE_LIST = 1;
    // 급식비 리스트
    final static public int T6_CODE2_MEAL_COST_LIST = 2;
    // 합격 여부
    final static public int T6_CODE3_SELECTION_STATUS = 3;
    // 선발 정보
    final static public int T6_CODE4_SELECTION_INFO = 4;
    // 생활관 사용료
    final static public int T6_CODE5_DORMITORY_FEE = 5;
    // 급식비
    final static public int T6_CODE6_MEAL_COST = 6;
    // 환불여부
    final static public int T6_CODE7_REFUND_STATUS = 7;
    // 생활관ID
    final static public int T6_CODE8_DORMITORY_ID = 8;
    // 입사 신청 학생 목록
    final static public int T6_CODE9_APPLICATION_STUDENT_LIST = 9;
    // 선발 학생 리스트
    final static public int T6_CODE10_SETECTION_STUDENT_LIST = 10;
    // 남은 침대 정보 리스트
    final static public int T6_CODE11_UNUSEDBED_LIST = 11;
    // 납부자 조회
    final static public int T6_CODE12_PAID_STUDENT = 12;
    // 미납부자 조회
    final static public int T6_CODE13_UNPAID_STUDENT = 13;
    // 결핵진단서 제출자
    final static public int T6_CODE14_TUBERCULOSIS_CERTIFICATE_SUBMITTER = 14;
    // 결핵진단서
    final static public int T6_CODE15_TUBERCULOSIS_CERTIFICATE = 15;
    // 퇴사신청자 명단
    final static public int T6_CODE16_UNAPPLICATION_STUDENT = 16;


    final static public int T7_CODE0_SELECTION_SCHEDULE_LIST = 0;
    final static public int T7_CODE1_DORMITORY_FEE_LIST = 1;
    final static public int T7_CODE2_MEAL_COST_LIST = 2;
    final static public int T7_CODE3_SELECTION_STATUS = 3;
    final static public int T7_CODE4_SELECTION_INFO = 4;
    final static public int T7_CODE5_DORMITORY_FEE = 5;
    final static public int T7_CODE6_MEAL_COST = 6;
    final static public int T7_CODE7_REFUND_STATUS = 7;
    final static public int T7_CODE8_DORMITORY_ID = 8;
    final static public int T7_CODE9_APPLICATION_STUDENT_LIST = 9;
    final static public int T7_CODE10_SETECTION_STUDENT_LIST = 10;
    final static public int T7_CODE11_UNUSEDBED_LIST = 11;
    final static public int T7_CODE12_PAID_STUDENT = 12;
    final static public int T7_CODE13_UNPAID_STUDENT = 13;
    final static public int T7_CODE14_TUBERCULOSIS_CERTIFICATE_SUBMITTER = 14;
    final static public int T7_CODE15_TUBERCULOSIS_CERTIFICATE = 15;
    final static public int T7_CODE16_UNAPPLICATION_STUDENT = 16;
    final static public int T7_CODE17_FAIL = 17;


    final static public int T8_CODE0_ROOM_AND_BED_ASSIGNMENT = 0;
    final static public int T8_CODE1_REFUND_STATUS = 1;

    final static public int T9_CODE0_SUCCESS = 0;
    final static public int T9_CODE1_FAIL = 1;

    public static final int LEN_TYPE = 1;
    public static final int LEN_CODE = 1;
    public static final int LEN_BODYLENGTH = 4;
    public static final int LEN_HEADER = LEN_TYPE + LEN_CODE + LEN_BODYLENGTH;

    private byte type;
    private byte code;
    private int bodyLength;
    private byte[] body;

    // ㅁ ㅁ ㅁ ㅁ => int
    private int bodyIndex = 0;

    public Protocol(){
        new Protocol(UNDEFINED_TYPE, 0);
    }
    // Constructor: Type과 Code만 설정 (Body는 나중에 추가)
    public Protocol(int type, int code) {
        this.type = (byte) type;
        this.code = (byte) code;
        this.bodyLength = 0;
    }

    // body 설정
    public void setBody(int data) {
        this.body = new byte[LEN_BODYLENGTH];
        byte[] input = intToByte(data);
        this.body = input;
        this.bodyLength = input.length;
    }

    public void setBody(String[] data) {
        this.body = new byte[LEN_BODYLENGTH];
        byte[] input;
        int inputLength = 0;
        for(int i = 0 ; i < data.length ; i++){
            input = data[i].getBytes();
            System.arraycopy(input, 0, this.body, inputLength, input.length);
            inputLength += input.length;
        }
        this.bodyLength = inputLength;
    }

    public void setBody(String data) {
        this.body = new byte[LEN_BODYLENGTH];
        byte[] input = data.getBytes();
        this.body = input;
        this.bodyLength = input.length;
    }

    // 전체 packet 생성
    public byte[] createPacket() {
        byte[] packet = new byte[LEN_HEADER + bodyLength];
        packet[0] = type;
        packet[LEN_TYPE] = code;
        // 헤더만 packet에 담는 코드
        System.arraycopy(intToByte(getBodyLength()), 0, packet, LEN_TYPE + LEN_CODE, LEN_HEADER);

        // body에 데이터가 있다면 추가
        if (getBodyLength() > 0) {
            System.arraycopy(body, 0, packet, LEN_HEADER, getBodyLength());
        }
        return packet;
    }

        // 패킷에서 헤더와 바디의 복원
    public Protocol dataExtraction(byte[] packet) {
        // 헤더 복원
        byte type = packet[0];
        byte code = packet[LEN_TYPE];
        int bodyLength = byteToInt(Arrays.copyOfRange(packet, LEN_TYPE + LEN_CODE, LEN_HEADER));

        // 바디 복원
        byte[] body = Arrays.copyOfRange(packet, LEN_HEADER, LEN_HEADER + bodyLength);

        Protocol protocol = new Protocol(type, code);
        protocol.setBody(byteToInt(body));
        return protocol;
    }

    public Object getBody() {
        return dataExtraction(body);
    }

    private byte[] intToByte(int i) {
        return ByteBuffer.allocate(LEN_BODYLENGTH).putInt(i).array();
    }

    private int byteToInt(byte[] b) {
        return ByteBuffer.wrap(b).getInt();
    }

    public int getBodyLength() {
        return bodyLength;
    }

    public byte getType() {
        return type;
    }

    public byte getCode() {
        return code;
    }

    public void setPacketHeader(byte[] packet) {
        byte[] data;

        type = packet[0];
        code = packet[LEN_TYPE];

        data = new byte[LEN_BODYLENGTH];
        System.arraycopy(packet, LEN_TYPE + LEN_CODE, data, 0, LEN_BODYLENGTH);
        bodyLength = byteToInt(data);
    }


    // 매개 변수 packet을 통해 body를 생성
    public void setPacketBody(byte[] packet) {
        byte[] data;

        if (packet.length > LEN_HEADER){
            data = new byte[packet.length - LEN_HEADER];
            System.arraycopy(packet, LEN_HEADER, data, 0, data.length);
        }
    }
}