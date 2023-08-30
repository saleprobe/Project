# DB에 json값 저장

import json
import requests
import time
import random

# url = "http://203.230.100.170:10095/register"
# url = "http://203.230.100.170:10095/react"
# url = "http://localhost:3000/member/register"
url = "http://localhost:3000/member/login"
# url = "http://203.230.100.170:10095/"


memberId = "sseongmin"
memberPw = "1234"

i = 0

time.sleep(1)
# data = {"sf_id": sf_id, "temp": temp, "hum": hum, "led_state": led_state, "led_toggle": led_toggle,
#         "led_adj": led_adj,
#         "water_state": water_state, "water_toggle": water_toggle, "fan_state": fan_state, "fan_toggle": fan_toggle}
data = {"memberId": memberId, "memberPw": memberPw}

response = requests.post(url, json=data)
print(data)  # 서버로 데이터를 보낼때, 내가 보낸 데이터 확인
# print(response.json())  # 서버에서부터 데이터를 받을때, 받은 데이터 확인
print(response.status_code)  # 응답의 상태 코드 출력
print(response.text)  # 응답의 내용 출력
print(response.s)