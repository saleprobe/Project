import requests

# Spring Boot 애플리케이션의 URL 및 /inject_sf_id 엔드포인트에 세션 ID를 전달
url = 'http://localhost:3000/user_related/check_sf_id'
user_sf_id = 10000002  # 확인하려는 user_sf_id 값

params = {'user_sf_id': user_sf_id}

response = requests.get(url, params=params)

# 요청 결과를 확인할 수 있음
print(response.status_code)
print(response.text)