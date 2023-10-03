import requests

# 서버 URL
base_url = "http://localhost:3000/user_related/get_nickname"  # 백엔드 서버의 URL로 변경해야 합니다.

# 세션 쿠키를 저장할 변수
session_cookie = "F5D645BD11E18074A23EB1CD132FE901"

# 세션 쿠키를 출력
print("Session Cookie:", session_cookie)

# 세션 쿠키를 포함하여 GET 요청 보내기
def get_user_nickname():
    headers = {
        "Cookie": f"JSESSIONID={session_cookie}"  # 세션 쿠키를 헤더에 추가
    }
    response = requests.get(url=base_url, headers=headers)

    if response.status_code == 200:
        nickname = response.text
        print("현재 로그인된 사용자의 닉네임:", nickname)
    elif response.status_code == 400:
        error_message = response.text
        print("에러 메시지:", error_message)
    else:
        print("알 수 없는 오류 발생")

if __name__ == "__main__":
    get_user_nickname()