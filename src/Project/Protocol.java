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
    final static public int T6_CODE0_SELECTION_SCHEDULENAME_LIST = 0;
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
    final static public int T6_CODE17_SELECTION_PERIOD_OF_SCHEDULE_LIST = 17 ;




    final static public int T7_CODE0_SELECTION_SCHEDULENAME_LIST = 0;
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
    final static public int T7_CODE17_SELECTION_PERIOD_OF_SCHEDULE_LIST = 17;
    final static public int T7_CODE18_FAIL = 18;


    final static public int T8_CODE0_ROOM_AND_BED_ASSIGNMENT = 0;
    final static public int T8_CODE1_REFUND_STATUS = 1;

    final static public int T9_CODE0_SUCCESS = 0;
    final static public int T9_CODE1_FAIL = 1;

    public static final int LEN_TYPE = 1;
    public static final int LEN_CODE = 1;
    public static final int LEN_BODYLENGTH = 5;
    public static final int LEN_HEADER = LEN_TYPE + LEN_CODE + LEN_BODYLENGTH;

    private byte type;
    private byte code;
    private int bodyLength;
    private byte[] body;

    public Protocol() {
        this(UNDEFINED_TYPE, 0);
    }

    public Protocol(int type, int code) {
        this.type = (byte) type;
        this.code = (byte) code;
        this.bodyLength = 0;
        this.body = null;
        System.out.println("[DEBUG] Protocol initialized: type=" + type + ", code=" + code);
    }

    public void setBody(int data) {
        System.out.println("[DEBUG] setBody(int) called with data=" + data);
        try {
            byte[] input = intToByte(data);
            this.body = new byte[input.length];
            System.arraycopy(input, 0, this.body, 0, input.length); //추가됨
            //this.body = input; <-기존코드
            this.bodyLength = input.length;
            System.out.println("[DEBUG] Body set with length=" + bodyLength);
        } catch (Exception e) {
            System.err.println("[ERROR] setBody(int) failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setBody(String[] data, int fixByteLength) {
        System.out.println("Setting body with String array...");
        this.body = new byte[data.length * fixByteLength]; // String 배열의 원소의 개수에 고정 길이를 곱한 값
        byte[] input;
        int inputLength = 0;
        for (int i = 0; i < data.length; i++) {
            input = data[i].getBytes(); // 데이터를 바이트 형태로 바꿈
            int copyLength = Math.min(input.length, fixByteLength); // 복사 길이 제한 -> 기존것에서 이거 추가됨
            System.arraycopy(input, 0, this.body, inputLength, copyLength);
            inputLength += fixByteLength; // 고정 길이만큼 이동
        }
        this.bodyLength = inputLength;
        System.out.println("Body set successfully. Total bodyLength=" + this.bodyLength);
    }

    public void setBody(String data) {
        System.out.println("[DEBUG] setBody(String) called with data=" + data);
        try {
            byte[] input = data.getBytes();
            this.body = new byte[input.length];
            System.arraycopy(input, 0, this.body, 0, input.length); //이거 추가됨
            //this.body = input; <-기존코드
            this.bodyLength = input.length;
            System.out.println("[DEBUG] Body set with length=" + bodyLength);
        } catch (Exception e) {
            System.err.println("[ERROR] setBody(String) failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 전체 packet 생성
    public byte[] createPacket() {
        System.out.println("[DEBUG] createPacket() called with bodyLength=" + bodyLength);
        try {
            byte[] packet = new byte[LEN_HEADER + bodyLength];
            packet[0] = type;
            packet[LEN_TYPE] = code;
            System.arraycopy(intToByte(bodyLength), 0, packet, LEN_TYPE + LEN_CODE, Integer.BYTES);

            if (bodyLength > 0) {
                System.arraycopy(body, 0, packet, LEN_HEADER, bodyLength);
            }
            System.out.println("[DEBUG] Packet created with total length=" + packet.length);
            return packet;
        } catch (Exception e) {
            System.err.println("[ERROR] createPacket() failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    // 패킷에서 헤더와 바디의 복원
    public Protocol dataExtraction(byte[] packet) {
        System.out.println("[DEBUG] dataExtraction() called with packet length=" + packet.length);
        try {
            byte type = packet[0];
            byte code = packet[LEN_TYPE];
            int bodyLength = byteToInt(Arrays.copyOfRange(packet, LEN_TYPE + LEN_CODE, LEN_HEADER));

            if (bodyLength < 0 || bodyLength > LEN_BODYLENGTH) {
                throw new IllegalArgumentException("Invalid body length in packet.");
            }

            byte[] nbody = Arrays.copyOfRange(packet, LEN_HEADER, LEN_HEADER + bodyLength);
            Protocol protocol = new Protocol(type, code);
            protocol.body = nbody;
            System.out.println("[DEBUG] Protocol extracted with type=" + type + ", code=" + code + ", bodyLength=" + bodyLength);
            return protocol;
        } catch (Exception e) {
            System.err.println("[ERROR] dataExtraction() failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public byte[] getBody() {
        return this.body;
    }

//    public String[] byteToStringArray(byte[] packet, int fixByteLength) {
//        System.out.println("Converting byte array to String array...");
//        int resultLength = (packet.length + fixByteLength - 1) / fixByteLength; // 올림 처리
//        String[] result = new String[resultLength];
//        int j = 0;
//        for (int i = 0; i < packet.length; i += fixByteLength) {
//            int end = Math.min(i + fixByteLength, packet.length);
//            result[j] = new String(packet, i, end - i).trim(); // 트림으로 불필요 공백 제거
//            j++;
//        }
//        System.out.println("Conversion complete. Array length: " + result.length);
//        return result;
//    }

    public String[] byteToStringArray(byte[] packet, int fixByteLength) {
        System.out.println("Converting byte array to String array...");

        // 입력 검증
        if (packet == null || packet.length == 0) {
            System.out.println("[ERROR] Packet is null or empty.");
            return new String[0]; // 빈 배열 반환
        }
        if (fixByteLength <= 0) {
            throw new IllegalArgumentException("[ERROR] fixByteLength must be greater than 0.");
        }

        System.out.println("[DEBUG] Packet length: " + packet.length);
        System.out.println("[DEBUG] Packet data: " + Arrays.toString(packet));

        // 결과 배열 생성
        int resultLength = (packet.length + fixByteLength - 1) / fixByteLength; // 올림 처리
        String[] result = new String[resultLength];

        int j = 0;
        for (int i = 0; i < packet.length; i += fixByteLength) {
            int end = Math.min(i + fixByteLength, packet.length);
            result[j] = new String(packet, i, end - i).trim(); // 트림으로 불필요 공백 제거
            j++;
        }

        System.out.println("[DEBUG] Conversion complete. Array length: " + result.length);
        return result;
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

//    public void setPacketHeader(byte[] packet) {
//        System.out.println("[DEBUG] setPacketHeader() called with packet length=" + packet.length);
//        try {
//            if (packet.length < LEN_HEADER) {
//                throw new IllegalArgumentException("Header 길이가 충분하지 않습니다.");
//            }
//            type = packet[0];
//            code = packet[LEN_TYPE];
//
//            byte[] data = new byte[Integer.BYTES];
//            System.arraycopy(packet, LEN_TYPE + LEN_CODE, data, 0, Integer.BYTES);
//            bodyLength = byteToInt(data);
//
//            if (bodyLength < 0 || bodyLength > LEN_BODYLENGTH) {
//                throw new IllegalArgumentException("비정상적인 bodyLength 값: " + bodyLength);
//            }
//            System.out.println("[DEBUG] Header set: type=" + type + ", code=" + code + ", bodyLength=" + bodyLength);
//        } catch (Exception e) {
//            System.err.println("[ERROR] setPacketHeader() failed: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }

    public void setPacketHeader(byte[] packet) {
        System.out.println("[DEBUG] setPacketHeader() 호출");
        try {
            if (packet.length < Protocol.LEN_HEADER) {
                throw new IllegalArgumentException("패킷 길이가 헤더 길이보다 작습니다.");
            }

            type = packet[0];
            code = packet[LEN_TYPE];
            byte[] bodyLengthBytes = Arrays.copyOfRange(packet, 2, 6); // 4바이트 길이 추출
            bodyLength = ByteBuffer.wrap(bodyLengthBytes).getInt();

            if (bodyLength < 0 || bodyLength > Protocol.LEN_BODYLENGTH) {
                throw new IllegalArgumentException("비정상적인 bodyLength 값: " + bodyLength);
            }

            System.out.println("[DEBUG] 헤더 설정 완료: type=" + type + ", code=" + code + ", bodyLength=" + bodyLength);
        } catch (Exception e) {
            System.err.println("[ERROR] setPacketHeader() 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setPacketBody(byte[] body) {
        System.out.println("Setting packet body...");

        // 입력 데이터 검증
        if (body == null || body.length == 0) {
            System.out.println("[ERROR] Body is null or empty.");
            this.body = new byte[0];
            this.bodyLength = 0;
            return;
        }

        // 바디 설정
        this.body = body;
        this.bodyLength = body.length;

        // 디버깅 메시지 출력
        System.out.println("[DEBUG] Body set successfully. bodyLength=" + this.bodyLength);
        System.out.println("[DEBUG] Body data: " + Arrays.toString(this.body));
    }
}