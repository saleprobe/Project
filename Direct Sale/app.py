import os  #절대경로를 지정하기 위한 Os모듈 임포트
from flask import Flask
from flask import request  #회원정보 제출했을때 받아오기 위한 request, post요청을 활성화시키기 위함
from flask import redirect  #페이지 이동시키는 함수
from flask import render_template
from models import db
from models import Fcuser
from flask import session
from flask_wtf.csrf import CSRFProtect
from forms import RegisterForm, LoginForm
from flask import flash

app = Flask(__name__)

@app.route("/")
def hello():
    userid = session.get('userid', None)
    return render_template('index.html',userid=userid)

@app.route('/register', methods=['GET','POST'])
def register():
    form = RegisterForm()
    if form.validate_on_submit():  # POST검사의 유효성검사가 정상적으로 되었는지 확인할 수 있다. 입력 안한것들이 있는지 확인됨.
        # 비밀번호 = 비밀번호 확인 -> EqulaTo

        fcuser = Fcuser()  # models.py에 있는 Fcuser
        fcuser.userid = form.data.get('userid')
        fcuser.username = form.data.get('username')
        fcuser.address = form.data.get('address')
        fcuser.password = form.data.get('password')

        print(fcuser.userid, fcuser.password)  # 회원가입 요청시 콘솔창에 ID만 출력 (확인용, 딱히 필요없음)
        db.session.add(fcuser)  # id, name 변수에 넣은 회원정보 DB에 저장
        db.session.commit()  # 커밋
        flash("가입이 완료 되었습니다.")  # 팝업
        return redirect('/')  # 회원가입 완료시 화면이동
    return render_template('register.html', form=form)

@app.route("/listofproduct")
def list_of_product():
    flash("Test")
    return "미구현"
@app.route("/aori")
def aori():
    return "아오리 구매 페이지 미구현"
@app.route("/hongro")
def hongro():
    return "홍로 구매 페이지 미구현"
@app.route("/busa")
def busa():
    return "부사 구매 페이지 미구현"

@app.route('/login', methods=['GET', 'POST'])
def login():
    form = LoginForm()  # 로그인 폼 생성
    if form.validate_on_submit():  # 유효성 검사
        session['userid'] = form.data.get('userid')  # form에서 가져온 userid를 session에 저장

        return redirect('/')  # 로그인에 성공하면 홈화면으로 redirect


    return render_template('login.html', form=form)

@app.route('/logout',methods=['GET'])
def logout():
    session.pop('userid',None)
    return redirect('/')

if __name__ == "__main__":
    basedir = os.path.abspath(os.path.dirname(__file__))  # db파일을 절대경로로 생성
    dbfile = os.path.join(basedir, 'db.sqlite')  # db파일을 절대경로로 생성

    app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///' + dbfile
    # sqlite를 사용함. (만약 mysql을 사용한다면, id password 등... 더 필요한게많다.)
    app.config['SQLALCHEMY_COMMIT_ON_TEARDOWN'] = True
    # 사용자 요청의 끝마다 커밋(데이터베이스에 저장,수정,삭제등의 동작을 쌓아놨던 것들의 실행명령)을 한다.
    app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
    # 수정사항에 대한 track을 하지 않는다. True로 한다면 warning 메시지유발
    app.config['SECRET_KEY'] = 'wcsfeufhwiquehfdx'

    csrf = CSRFProtect()
    csrf.init_app(app)

    db.init_app(app)
    db.app = app
    with app.app_context():
        db.create_all() # db생성

    app.run(host="127.0.0.1", port=5000, debug=True)