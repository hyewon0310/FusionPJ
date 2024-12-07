클래스 상호작용 정리
Client → ClientHandler: 서버에 요청 전송
Protocol 객체를 사용해 요청 데이터를 패킷 형태로 생성
Server → ServerThread: 클라이언트 요청을 개별 스레드에서 처리
요청 데이터를 해석후 처리 결과 응답
ServerThread → ClientHandler: 서버 응답을 패킷으로 생성하여 전송
ClientHandler는 응답 데이터를 처리하고 클라이언트에 전달
