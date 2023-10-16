import requests

# 세션 ID
session_id = '4114066657FBE3F7EA81988DEA350ECC'

# 요청 헤더 설정
headers = {
    'Cookie': f'JSESSIONID={session_id}'  # 세션 ID를 Cookie 헤더에 추가
}

# 요청 보내기
url = 'http://localhost:3000/user_related/get_n_ussi_crn'  # 사용자 정보를 가져올 URL
response = requests.get(url, headers=headers)

# JSON 응답 확인
data = response.json()

# 사용자 정보 및 조건 출력
print(f"User Nickname: {data.get('nickname')}")
print(f"User usersfidegistable: {data.get('usersfidRegistable')}")
print(f"User cropnameRegistable: {data.get('cropnameRegistable')}")
