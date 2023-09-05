# DB에 랜덤 값 삽입하기

import json
import requests
import time
import random

# url = "http://203.230.100.170:10095/raspberrypi"
# url = "http://203.230.100.170:10095/react"
url = "http://localhost:3000/"
# url = "http://localhost:3000/"
# url = "http://203.230.100.170:10095/data"



sf_id = 11
temp = round(25.8, 1)
hum = round(62.1, 1)
led_state = 0
led_toggle = 1
led_adj = 1
water_state = 0
water_toggle = 1
fan_state = 0
fan_toggle = 1

i = 0

# while 1:
#     i = i + 1
#     if i % 2 == 0:
#         temp = temp + random.randrange(1, 5)
#         hum = hum + random.randrange(1, 5)
#     elif i % 2 == 1:
#         temp = temp - random.randrange(1, 5)
#         hum = hum - random.randrange(1, 5)

    # time.sleep(1)
    # data = {"sf_id": sf_id, "temp": temp, "hum": hum, "led_state": led_state, "led_toggle": led_toggle,
    #         "led_adj": led_adj,
    #         "water_state": water_state, "water_toggle": water_toggle, "fan_state": fan_state, "fan_toggle": fan_toggle}
data = {"sf_id": sf_id, "temp": temp, "hum": hum, "led_state": led_state, "led_toggle": led_toggle,
        "led_adj": led_adj,
        "water_state": water_state, "water_toggle": water_toggle, "fan_state": fan_state, "fan_toggle": fan_toggle}

response = requests.post(url, json=data)
print(data)  # 서버로 데이터를 보낼때, 내가 보낸 데이터 확인
# print(response.json())  # 서버에서부터 데이터를 받을때, 받은 데이터 확인