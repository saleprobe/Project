from flask import Flask, render_template, request
from jinja2 import Environment

app = Flask(__name__)

# 임시로 게시물을 저장할 리스트
posts = []

# 페이지당 게시물 개수
POSTS_PER_PAGE = 5

# Jinja2 환경 설정
env = Environment()

def length(obj):
    return len(obj)

env.filters['length'] = length

@app.route('/', methods=['GET', 'POST'])
def index():
    if request.method == 'POST':
        # 게시물 등록 요청을 처리하는 부분
        title = request.form.get('title')
        content = request.form.get('content')
        # 게시물 등록
        posts.append({'title': title, 'content': content})

    # GET 요청이 들어오면 페이지 번호를 1로 설정하여 기존 게시물 목록과 페이지 번호를 템플릿에 전달
    return render_template('index.html', posts=posts, page=1, POSTS_PER_PAGE=POSTS_PER_PAGE)

@app.route('/page/<int:page>', methods=['GET'])
def view_page(page):
    # 해당 페이지의 게시물 목록과 페이지 번호를 템플릿에 전달
    return render_template('index.html', posts=posts, page=page, POSTS_PER_PAGE=POSTS_PER_PAGE)


def get_posts_for_page(page):
    # 페이지에 해당하는 게시물 목록을 반환
    start_index = (page - 1) * POSTS_PER_PAGE
    end_index = start_index + POSTS_PER_PAGE
    return posts[::-1][start_index:end_index]

if __name__ == '__main__':
    app.run(debug=True, port=8080)