import json
import requests
import time
import random

# url = "http://203.230.100.170:10095/raspberrypi"
# url = "http://203.230.100.170:10095/react"
# url = "http://localhost:3000/data"
url = "http://localhost:3000/data"
# url = "http://203.230.100.170:10095/"



sf_id = 78
temp = round(29.2, 1)
hum = round(70.3, 1)
led_state = 0
led_toggle = 0
led_adj = 0
water_state = 0
water_toggle = 0
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

    response = requests.get(url, json=data)
    # print(data)
    print(response.json())