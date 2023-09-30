import requests

# Spring Boot 애플리케이션의 로그인 URL
login_url = 'http://localhost:3000/user_related/login'

# 로그인에 사용할 사용자 정보 (예: 로그인 아이디와 비밀번호)
login_data = {
    'loginId': 'seongmin',
    'password': '123123'
}

# 세션 쿠키를 저장할 변수
session_cookie = None

# 세션 쿠키를 얻기 위한 세션 생성 요청
with requests.Session() as session:
    # 로그인 요청을 보냄.
    response = session.post(login_url, data=login_data)

    # 로그인이 성공하면 세션 쿠키를 얻어냄
    if response.status_code == 200:
        session_cookie = session.cookies.get_dict()

# 세션 쿠키를 출력
print("Session Cookie:", session_cookie)
print(response.status_code)

# 이제 세션 쿠키를 사용하여 다른 요청을 보낼 수 있음.