import requests

# Spring Boot 애플리케이션의 URL 및 /join 엔드포인트 지정
url = 'http://localhost:3000/user_related/join'

# 회원가입에 사용할 데이터를 딕셔너리로 정의
data = {
    'loginId': 'seon',  # 원하는 로그인 아이디
    'password': '5512',  # 원하는 비밀번호
    'passwordCheck': '5512',  # 비밀번호 확인
    'nickname': '홍성믠'  # 원하는 닉네임
}

# POST 요청을 보내기 위해 requests.post() 함수 사용
response = requests.post(url, data=data)

# 요청 결과를 확인할 수 있음
if response.status_code == 200:
    print("회원가입 성공")
else:
    print("회원가입 실패")

print(response.status_code)