import requests

# Spring Boot 애플리케이션의 URL 및 /inject-sf-id 엔드포인트에 sfId 값을 전달
url = 'http://localhost:3000/session-login/inject_sf_id'
user_sf_id = 1002  # 주입하려는 sf_id 값
params = {'user_sf_id': user_sf_id}

response = requests.get(url, params=params)

# 요청 결과를 확인할 수 있음
print(response.status_code)