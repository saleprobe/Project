# 웹소켓 관련

import websocket
import json

def on_open(ws):
    # JSON 데이터 생성
    data = {
        'sf_id': 99,
        'led_state': 1
    }
    # JSON 데이터를 문자열로 변환하여 서버로 전송
    ws.send(json.dumps(data))
    print('Sent JSON data:', data)

def on_message(ws, message):
    print('Received message:', message)

# 웹소켓 서버 URL
# websocket_url = 'ws://203.230.100.170:10095/raspberrypi-websocket'
websocket_url = 'ws://localhost:3000/raspberrypi_websocket'
# websocket_url = 'ws://localhost:3000/react-websocket'
# websocket_url = 'ws://203.230.100.170:10095/react-websocket'
# websocket_url = 'ws://203.230.100.170:10095/react-websocket-json'


# 웹소켓 클라이언트 생성 및 이벤트 핸들러 등록
ws = websocket.WebSocketApp(websocket_url, on_open=on_open, on_message=on_message)
ws2 = websocket.WebSocketApp(websocket_url, on_open=on_open, on_message=on_message)

# 웹소켓 서버와 연결
ws.run_forever()

