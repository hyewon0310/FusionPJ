package Project;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
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
    final static public int T6_CODE1_SELECTION_PERIOD_OF_SCHEDULE_LIST = 1;
    final static public int T6_CODE2_DORMITORY_NAME_LIST = 2;
    // 기숙사 이름 들고 올 때 필드 값의 갯수로 들고오면 맞추기 어려움
    // 따라서 생활관id에 맞는 이름 넣도록 하기 (가능하다면....?)
    final static public int T6_CODE3_DORMITORY_TYPE_LIST = 3;
    final static public int T6_CODE4_DORMITORY_GENDER_LIST = 4;
    final static public int T6_CODE5_DORMITORYFEE_LIST = 5;
    final static public int T6_CODE6_MEALTYPE_LIST = 6;
    final static public int T6_CODE7_MEALCOST_LIST = 7;
    // 합격 여부
    final static public int T6_CODE8_SELECTION_STATUS = 8;
    // 선발 정보
    final static public int T6_CODE9_SELECTION_INFO = 9;
    // 생활관 사용료
    final static public int T6_CODE10_DORMITORY_FEE = 10;
    // 급식비
    final static public int T6_CODE11_MEAL_COST = 11;
    // 환불여부
    final static public int T6_CODE12_REFUND_STATUS = 12;
    // 생활관ID
    final static public int T6_CODE13_DORMITORY_ID = 13;
    // 입사 신청 학생 목록
    final static public int T6_CODE14_APPLICATION_STUDENT_LIST = 14;
    // 선발 학생 리스트
    final static public int T6_CODE15_SETECTION_STUDENT_LIST = 15;
    // 남은 침대 정보 리스트
    final static public int T6_CODE16_UNUSEDBED_LIST = 16;
    // 납부자 조회
    final static public int T6_CODE17_PAID_STUDENT = 17;
    // 미납부자 조회
    final static public int T6_CODE18_UNPAID_STUDENT = 18;
    // 결핵진단서 제출자
    final static public int T6_CODE19_TUBERCULOSIS_CERTIFICATE_SUBMITTER = 19;
    // 결핵진단서
    final static public int T6_CODE20_TUBERCULOSIS_CERTIFICATE = 20;
    // 퇴사신청자 명단
    final static public int T6_CODE21_UNAPPLICATION_STUDENT = 21;
    final static public int T6_CODE22_DORMITORYINFO = 22;


    final static public int T7_CODE0_SELECTION_SCHEDULENAME_LIST = 0;
    final static public int T7_CODE1_SELECTION_PERIOD_OF_SCHEDULE_LIST = 1;
    final static public int T7_CODE2_DORMITORY_NAME_LIST = 2;
    // 기숙사 이름 들고 올 때 필드 값의 갯수로 들고오면 맞추기 어려움
    // 따라서 생활관id에 맞는 이름 넣도록 하기 (가능하다면....?)
    final static public int T7_CODE3_DORMITORY_TYPE_LIST = 3;
    final static public int T7_CODE4_DORMITORY_GENDER_LIST = 4;
    final static public int T7_CODE5_DORMITORYFEE_LIST = 5;
    final static public int T7_CODE6_MEALTYPE_LIST = 6;
    final static public int T7_CODE7_MEALCOST_LIST = 7;
    final static public int T7_CODE8_SELECTION_STATUS = 8;
    final static public int T7_CODE9_SELECTION_INFO = 9;
    final static public int T7_CODE10_DORMITORY_FEE = 10;
    final static public int T7_CODE11_MEAL_COST = 11;
    final static public int T7_CODE12_REFUND_STATUS = 12;
    final static public int T7_CODE13_DORMITORY_ID = 13;
    final static public int T7_CODE14_APPLICATION_STUDENT_LIST = 14;
    final static public int T7_CODE15_SETECTION_STUDENT_LIST = 15;
    final static public int T7_CODE16_UNUSEDBED_LIST = 16;
    final static public int T7_CODE17_PAID_STUDENT = 17;
    final static public int T7_CODE18_UNPAID_STUDENT = 18;
    final static public int T7_CODE19_TUBERCULOSIS_CERTIFICATE_SUBMITTER = 19;
    final static public int T7_CODE20_TUBERCULOSIS_CERTIFICATE = 20;
    final static public int T7_CODE21_UNAPPLICATION_STUDENT = 21;
    final static public int T7_CODE22_DORMITORYINFO = 22;
    final static public int T7_CODE23_FAIL = 23;


    final static public int T8_CODE0_ROOM_AND_BED_ASSIGNMENT = 0;
    final static public int T8_CODE1_REFUND_STATUS = 1;

    final static public int T9_CODE0_SUCCESS = 0;
    final static public int T9_CODE1_FAIL = 1;

    public static final int LEN_TYPE = 1;
    public static final int LEN_CODE = 1;
    public static final int LEN_BODYLENGTH = 20;
    public static final int LEN_HEADER = LEN_TYPE + LEN_CODE + LEN_BODYLENGTH;
    public static final int MAX_BODY_LENGTH = 5000;

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

//    public void setBody(String[] data, int fixByteLength) {
//        System.out.println("Setting body with String array...");
//        this.body = new byte[data.length * fixByteLength]; // String 배열의 원소의 개수에 고정 길이를 곱한 값
//        byte[] input;
//        int inputLength = 0;
//        for (int i = 0; i < data.length; i++) {
//            input = data[i].getBytes(); // 데이터를 바이트 형태로 바꿈
//            int copyLength = Math.min(input.length, fixByteLength); // 복사 길이 제한 -> 기존것에서 이거 추가됨
//            System.arraycopy(input, 0, this.body, inputLength, copyLength);
//            inputLength += fixByteLength; // 고정 길이만큼 이동
//        }
//        this.bodyLength = inputLength;
//        System.out.println("Body set successfully. Total bodyLength=" + this.bodyLength);
//    }


//    public void setBody(Object[] data, int fixByteLength) {
//        System.out.println("Setting body with String array...");
//        if(data.)
//        byte[] input = strArrToByteArr(data,fixByteLength);
//        this.body = input;
//        this.bodyLength = input.length;
//        System.out.println("Body set successfully. Total bodyLength=" + this.bodyLength);
//    }

    public void setBody(String[] data, int fixByteLength) {
        System.out.println("Setting body with Object array...");

        // 데이터가 null인지 확인
        if (data == null) {
            throw new IllegalArgumentException("[ERROR] data 배열이 null입니다.");
        }

        // Object 배열을 바이트 배열로 변환
        byte[] input = stringArrToByteArr(data, fixByteLength);
        String [] test = byteToStrArr(input, fixByteLength);
        for(int i = 0 ; i < test.length ; i++) {
            System.out.println("데이터 바이트로 변환 잘되었는지 확인용 "+ input[i]);
        }

        // body 설정
        this.body = input;
        this.bodyLength = input.length;

        System.out.println("Body set successfully. Total bodyLength=" + this.bodyLength);
    }


    private byte[] stringArrToByteArr(String [] data, int fixByteLength) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            for (String obj : data) {
                if (obj == null) {
                    throw new IllegalArgumentException("[ERROR] 배열 요소가 null입니다.");
                }

                // 객체를 문자열로 변환 (기본적으로 `toString` 사용)
                String str = obj;

                // 문자열을 바이트 배열로 변환
                byte[] bytes = str.getBytes("UTF-8");

                // 고정 길이 맞추기
                if (fixByteLength > 0) {
                    if (bytes.length > fixByteLength) {
                        // 고정 길이 초과 시 잘라냄
                        baos.write(Arrays.copyOf(bytes, fixByteLength));
                    } else {
                        // 고정 길이에 맞게 패딩 추가
                        baos.write(bytes);
                        baos.write(new byte[fixByteLength - bytes.length]);
                    }
                } else {
                    baos.write(bytes);
                }
            }
        } catch (IOException e) {
            System.err.println("[ERROR] Object 배열 변환 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }

        return baos.toByteArray();
    }


    //이 부분이 혜원이가 말한 부분
    public void setBody(String[] data, int fixByteLength1, int fixByteLength2) {
        System.out.println("Setting body with String array...");
        byte[] input1 = data[0].getBytes();
        byte[] input2 = data[1].getBytes();
        byte[] input3 = data[2].getBytes();

        //헤더에 각각 1바이트씩 정보를 담음
        byte[] header = new byte[2];
        header[0] = (byte) fixByteLength1;
        header[1] = (byte) fixByteLength2;

        this.bodyLength = header.length+ input1.length + input2.length + input3.length;
        this.body = new byte[this.bodyLength];

        System.arraycopy(header, 0, this.body, 0, header.length);
        System.arraycopy(input1, 0, this.body, header.length, input1.length);
        System.arraycopy(input2, 0, this.body, header.length + input1.length, input2.length);
        System.arraycopy(input3, 0, this.body, header.length + input1.length + input2.length, input3.length);
        System.out.println("Body set successfully. Total bodyLength=" + this.bodyLength);
    }

    public String[] dataExtraction(byte[] data) {
        byte count = 0;
        byte[] header = new byte[3];
        String[] result = new String[3];

        // 헤더 추출
        while (count < 3) {
            header[count] = data[count];
            count++;
        }

        // 데이터를 추출
        int offset = count;
        for (int i = 0; i < 3; i++) {
            int length = header[i];
            byte[] extractedData = new byte[length];
            System.arraycopy(data, offset, extractedData, 0, length);
            result[i] = new String(extractedData);
            offset += length;
        }

//        for (int i = count; i < data.length; ) {
//            if (i - count < header[0]) {
//                byte[] extractedData = new byte[header[0]];
//                System.arraycopy(data, count, extractedData, 0, header[0]);
//                result[0] = new String(extractedData);
//                i += header[0];
//                count += header[0];
//            } else if (i - count < header[1]) {
//                byte[] extractedData = new byte[header[1]];
//                System.arraycopy(data, count, extractedData, 0, header[1]);
//                result[1] = new String(extractedData);
//                i += header[1];
//                count += header[1];
//            } else if (i - count < header[2]) {
//                byte[] extractedData = new byte[header[2]];
//                System.arraycopy(data, count, extractedData, 0, header[2]);
//                result[2] = new String(extractedData);
//                i += header[2];
//                count += header[2];
//            }
//        }

        return result;
    }
    //추가
    public String[] dataExtraction(byte[] data, int num) {
        byte count = 0;
        byte[] header = new byte[num];
        String[] result = new String[num];

        // 헤더 추출
        while (count < num) {
            header[count] = data[count];
            count++;
        }

        // 데이터를 추출
        int offset = count;
        for (int i = 0; i < num; i++) {
            int length = header[i];
            byte[] extractedData = new byte[length];
            System.arraycopy(data, offset, extractedData, 0, length);
            result[i] = new String(extractedData);
            offset += length;
        }

//        for (int i = count; i < data.length; ) {
//            if (i - count < header[0]) {
//                byte[] extractedData = new byte[header[0]];
//                System.arraycopy(data, count, extractedData, 0, header[0]);
//                result[0] = new String(extractedData);
//                i += header[0];
//                count += header[0];
//            } else if (i - count < header[1]) {
//                byte[] extractedData = new byte[header[1]];
//                System.arraycopy(data, count, extractedData, 0, header[1]);
//                result[1] = new String(extractedData);
//                i += header[1];
//                count += header[1];
//            } else if (i - count < header[2]) {
//                byte[] extractedData = new byte[header[2]];
//                System.arraycopy(data, count, extractedData, 0, header[2]);
//                result[2] = new String(extractedData);
//                i += header[2];
//                count += header[2];
//            }
//        }

        return result;
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

//    // 패킷에서 헤더와 바디의 복원
//    public Protocol dataExtraction(byte[] packet) {
//        System.out.println("[DEBUG] dataExtraction() called with packet length=" + packet.length);
//        try {
//            byte type = packet[0];
//            byte code = packet[LEN_TYPE];
//            int bodyLength = byteToInt(Arrays.copyOfRange(packet, LEN_TYPE + LEN_CODE, LEN_HEADER));
//
//            if (bodyLength < 0 || bodyLength > LEN_BODYLENGTH) {
//                throw new IllegalArgumentException("Invalid body length in packet.");
//            }
//
//            byte[] nbody = Arrays.copyOfRange(packet, LEN_HEADER, LEN_HEADER + bodyLength);
//            Protocol protocol = new Protocol(type, code);
//            protocol.body = nbody;
//            System.out.println("[DEBUG] Protocol extracted with type=" + type + ", code=" + code + ", bodyLength=" + bodyLength);
//            return protocol;
//        } catch (Exception e) {
//            System.err.println("[ERROR] dataExtraction() failed: " + e.getMessage());
//            e.printStackTrace();
//            return null;
//        }
//    }

    public byte[] strArrToByteArr(String[] data, int fixByteLength){
        byte[] result = new byte[data.length * fixByteLength];
        byte[] input;
        int inputLength = 0;
        for (int i = 0; i < data.length; i++) {
            input = data[i].getBytes(); // 데이터를 바이트 형태로 바꿈
            int copyLength = Math.min(input.length, fixByteLength); // 복사 길이 제한 -> 기존것에서 이거 추가됨
            System.arraycopy(input, 0, result, inputLength, copyLength);
            inputLength += fixByteLength; // 고정 길이만큼 이동
        }
        return result;
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

//    public String[] byteToStrArr(byte[] packet, int fixByteLength) {
//        System.out.println("Converting byte array to String array...");
//
//        // 입력 검증
//        if (packet == null || packet.length == 0) {
//            System.out.println("[ERROR] Packet is null or empty.");
//            return new String[0]; // 빈 배열 반환
//        }
//        if (fixByteLength <= 0) {
//            throw new IllegalArgumentException("[ERROR] fixByteLength must be greater than 0.");
//        }
//
//        System.out.println("[DEBUG] Packet length: " + packet.length);
//        System.out.println("[DEBUG] Packet data: " + Arrays.toString(packet));
//
//        // 결과 배열 생성
//        int resultLength = (packet.length + fixByteLength - 1) / fixByteLength; // 올림 처리
//        String[] result = new String[resultLength];
//
//        int j = 0;
//        for (int i = 0; i < packet.length; i += fixByteLength) {
//            int end = Math.min(i + fixByteLength, packet.length);
//            result[j] = new String(packet, i, end - i).trim(); // 트림으로 불필요 공백 제거
//            j++;
//        }
//
//        System.out.println("[DEBUG] Conversion complete. Array length: " + result.length);
//        return result;
//    }

    public String[] byteToStrArr(byte[] packet, int fixByteLength) {
        // 배열 크기 계산
        int resultLength = packet.length / fixByteLength;
        String[] result = new String[resultLength];

        for (int i = 0; i < resultLength; i++) {
            int start = i * fixByteLength;
            // 안전한 바이트 슬라이싱 및 UTF-8 변환
            String str = new String(Arrays.copyOfRange(packet, start, start + fixByteLength), StandardCharsets.UTF_8).trim();
            // 빈 데이터 기본값 설정
            result[i] = str.isEmpty() ? "빈 데이터" : str;
        }

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

    public void setPacketHeader(byte[] header) {
        if (header == null || header.length != LEN_HEADER) {
            throw new IllegalArgumentException("잘못된 헤더 길이");
        }

        type = header[0];
        code = header[1];
        bodyLength = ((header[2] & 0xFF) << 24)
                | ((header[3] & 0xFF) << 16)
                | ((header[4] & 0xFF) << 8)
                | (header[5] & 0xFF);

        // 바디 길이 유효성 검증
        if (bodyLength < 0 || bodyLength > MAX_BODY_LENGTH) {
            throw new IllegalArgumentException("비정상적인 bodyLength 값: " + bodyLength);
        }

        System.out.println("[DEBUG] 헤더 설정 완료: bodyLength=" + bodyLength);
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

    //추가
    public void setBody(String[] data, int fixByteLength1, int fixByteLength2, int fixByteLength3, int fix4, int fix5, int fix6) {
        System.out.println("Setting body with String array...");
        byte[] input1 = data[0].getBytes();
        byte[] input2 = data[1].getBytes();
        byte[] input3 = data[2].getBytes();
        byte[] input4 = data[3].getBytes();
        byte[] input5 = data[4].getBytes();
        byte[] input6 = data[5].getBytes();


        //헤더에 각각 1바이트씩 정보를 담음
        byte[] header = new byte[5];
        header[0] = (byte) fixByteLength1;
        header[1] = (byte) fixByteLength2;
        header[2] = (byte) fixByteLength3;
        header[3] = (byte) fix4;
        header[4] = (byte) fix5;

        this.bodyLength = header.length+ input1.length + input2.length + input3.length + input4.length + input5.length + input6.length;
        this.body = new byte[this.bodyLength];

        System.arraycopy(header, 0, this.body, 0, header.length);
        System.arraycopy(input1, 0, this.body, header.length, input1.length);
        System.arraycopy(input2, 0, this.body, header.length + input1.length, input2.length);
        System.arraycopy(input3, 0, this.body, header.length + input1.length + input2.length, input3.length);
        System.arraycopy(input4, 0, this.body, header.length + input1.length + input2.length, input3.length + input4.length);
        System.arraycopy(input5, 0, this.body, header.length + input1.length + input2.length, input3.length + input4.length + input5.length);
        System.arraycopy(input6, 0, this.body, header.length + input1.length + input2.length, input3.length + input4.length + input5.length + input6.length);

        System.out.println("Body set successfully. Total bodyLength=" + this.bodyLength);
    }
}