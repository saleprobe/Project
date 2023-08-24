# DB에 json값 저장

import json
import requests
import time
import random

url = "http://203.230.100.170:10095/raspberrypi"
# url = "http://203.230.100.170:10095/react"
# url = "http://localhost:3000/data"
# url = "http://localhost:3000/"
# url = "http://203.230.100.170:10095/"



sf_id = 78
temp = round(29.2, 1)
hum = round(70.3, 1)
led_state = 1
led_toggle = 1
led_adj = 4
water_state = 0
water_toggle = 1
fan_state = 0
fan_toggle = 0

i = 0

while 1:
    i = i + 1
    if i % 2 == 0:
        temp = temp + random.randrange(1, 5)
        hum = hum + random.randrange(1, 5)
    elif i % 2 == 1:
        temp = temp - random.randrange(1, 5)
        hum = hum - random.randrange(1, 5)

    time.sleep(1)
    # data = {"sf_id": sf_id, "temp": temp, "hum": hum, "led_state": led_state, "led_toggle": led_toggle,
    #         "led_adj": led_adj,
    #         "water_state": water_state, "water_toggle": water_toggle, "fan_state": fan_state, "fan_toggle": fan_toggle}
    data = { "sf_id": sf_id, "temp": temp, "hum": hum, "led_state": led_state, "led_toggle": led_toggle,
            "led_adj": led_adj,
            "water_state": water_state, "water_toggle": water_toggle, "fan_state": fan_state, "fan_toggle": fan_toggle}

    response = requests.post(url, json=data)
    print(data)  # 서버로 데이터를 보낼때, 내가 보낸 데이터 확인
    # print(response.json())  # 서버에서부터 데이터를 받을때, 받은 데이터 확인