from flask import Flask, render_template, request

app = Flask(__name__)

# 임시로 게시물을 저장할 리스트
posts = []

# 페이지당 게시물 개수
POSTS_PER_PAGE = 5

@app.route('/', methods=['GET', 'POST'])
def index():
    if request.method == 'POST':
        # 게시물 등록 요청을 처리하는 부분
        title = request.form.get('title')
        content = request.form.get('content')
        # 게시물 등록
        posts.append({'title': title, 'content': content})
        return render_template('index.html', posts=posts)
    # GET 요청이 들어오면 기존 게시물 목록을 템플릿에 전달
    return render_template('index.html', posts=posts)

@app.route('/page/<int:page>', methods=['GET'])
def view_page(page):
    # 해당 페이지의 게시물 목록을 템플릿에 전달
    return render_template('index.html', posts=get_posts_for_page(page), page=page)

def get_posts_for_page(page):
    pass

if __name__ == '__main__':
    app.run(debug=True, port=8080)