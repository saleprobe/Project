import requests

# Spring Boot 애플리케이션의 URL 및 /inject_sf_id 엔드포인트에 세션 ID를 전달
url = 'http://localhost:3000/session-login/inject_sf_id'
user_sf_id = 115  # 주입하려는 sf_id 값
session_id = 'D9AE3DDAAEDA53CC939F70EDCF435D84'  # 세션 ID를 여기에 입력
headers = {'Cookie': f'JSESSIONID={session_id}'}
# JSESSIONID는 Spring Boot에서 세션 ID의 기본 이름

params = {'user_sf_id': user_sf_id}

response = requests.post(url, headers=headers, params=params)

# 요청 결과를 확인할 수 있음
print(response.status_code)
print(response.text)