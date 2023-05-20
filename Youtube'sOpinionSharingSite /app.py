from flask import Flask, render_template, request

app = Flask(__name__)

# 임시로 게시물을 저장할 리스트
posts = []

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

if __name__ == '__main__':
    app.run(debug=True, port=8080)