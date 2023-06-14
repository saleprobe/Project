from flask import Flask, render_template, request
from jinja2 import Environment
import re

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
        image_url = request.form.get('image_url')

        # 이미지 URL 변환
        if 'youtu.be' in image_url:
            video_id = re.search(r'youtu.be/([^/?]+)', image_url).group(1)
            image_url = f'https://img.youtube.com/vi/{video_id}/0.jpg'

        # 게시물 등록
        posts.append({'title': title, 'content': content, 'image_url': image_url})

    # GET 요청이 들어오면 페이지 번호를 1로 설정하여 기존 게시물 목록과 페이지 번호를 템플릿에 전달
    return render_template('index.html', posts=posts[::-1], page=1, total_pages=get_total_pages(), POSTS_PER_PAGE=POSTS_PER_PAGE)

@app.route('/page/<int:page>', methods=['GET'])
def view_page(page):
    # 해당 페이지의 게시물 목록과 페이지 번호를 템플릿에 전달
    return render_template('index.html', posts=get_posts_for_page(page), page=page, total_pages=get_total_pages(), POSTS_PER_PAGE=POSTS_PER_PAGE)

def get_posts_for_page(page):
    # 페이지에 해당하는 게시물 목록을 반환
    start_index = (page - 1) * POSTS_PER_PAGE
    end_index = start_index + POSTS_PER_PAGE
    return posts[start_index:end_index]

def get_total_pages():
    # 총 페이지 수 계산
    return (len(posts) + POSTS_PER_PAGE - 1) // POSTS_PER_PAGE

if __name__ == '__main__':
    app.run(debug=True, port=8080)