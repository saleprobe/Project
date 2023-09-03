# 회원가입, 로그인 기능 테스트용

import json
import requests
import time
import random

# url = "http://203.230.100.170:10095/member/register"
url = "http://203.230.100.170:10095/member/login"
# url = "http://localhost:3000/member/register"
# url = "http://localhost:3000/member/login"
# url = "http://203.230.100.170:10095/member/register"
# url = "http://203.230.100.170:10095/"




memberId = "seongmin2"
memberPw = "123456"

time.sleep(1)

data = {"memberId": memberId, "memberPw": memberPw}

response = requests.post(url, json=data)
print(data)  # 서버로 데이터를 보낼때, 내가 보낸 데이터 확인
print(response.status_code)  # 응답의 상태 코드 출력
print(response.text)  # 응답의 내용 출력