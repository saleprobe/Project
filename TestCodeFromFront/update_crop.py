import requests

# Spring Boot 애플리케이션의 URL 및 /inject_sf_id 엔드포인트에 세션 ID를 전달
url = 'http://localhost:3000/user_related/inject_crop'
cropname = "토마토"  # 주입하려는 crop_name 값
period = 7  # 주입하려는 period 값

session_id = '655556295A06F6D5CB1C74C1E9B54AC3'  # 세션 ID를 여기에 입력
headers = {'Cookie': f'JSESSIONID={session_id}'}
# JSESSIONID는 Spring Boot에서 세션 ID의 기본 이름

params = {'cropname': cropname, 'period': period}

response = requests.post(url, headers=headers, params=params)

# 요청 결과를 확인할 수 있음
print(response.status_code)
print(response.text)