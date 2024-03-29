import os  # 절대경로를 지정하기 위한 Os모듈 임포트
from flask import Flask
from flask import request  # 회원정보 제출했을때 받아오기 위한 request, post요청을 활성화시키기 위함
from flask import redirect  # 페이지 이동시키는 함수
from flask import render_template
from flask import jsonify
from models import db
from models import Fcuser, Receipt
from flask import session
from flask_wtf.csrf import CSRFProtect
from forms import RegisterForm, LoginForm, ReceiptRegisterForm
from flask import flash

app = Flask(__name__)
app.secret_key = 'my_secret_key'

@app.route("/")
def main():
    userid = session.get('userid', None)
    return render_template('index.html', userid=userid)

@app.route('/register', methods=['GET','POST'])
def register():
    form = RegisterForm()
    if form.validate_on_submit():  # POST검사의 유효성검사가 정상적으로 되었는지 확인할 수 있다. 입력 안한것들이 있는지 확인됨.
        # 비밀번호 = 비밀번호 확인 -> EqulaTo

        # 중복된 ID 체크
        existing_fcuser = Fcuser.query.filter_by(userid=form.data.get('userid')).first()
        if existing_fcuser:
            flash("중복된 ID입니다. 다른 ID를 입력해주세요.")
            return render_template('register.html', form=form)

        fcuser = Fcuser()  # models.py에 있는 Fcuser
        fcuser.userid = form.data.get('userid')
        fcuser.username = form.data.get('username')
        fcuser.address = form.data.get('address')
        fcuser.number = form.data.get('number')
        fcuser.password = form.data.get('password')

        print(fcuser.userid, fcuser.password)  # 회원가입 요청시 콘솔창에 ID만 출력 (확인용, 딱히 필요없음)
        db.session.add(fcuser)  # id, name 변수에 넣은 회원정보 DB에 저장
        db.session.commit()  # 커밋
        flash("가입이 완료 되었습니다.")  # 팝업
        return redirect('/')  # 회원가입 완료시 화면이동
    return render_template('register.html', form=form)

@app.route('/check_userid', methods=['GET', 'POST'])
def check_userid():
    # 받은 데이터에서 아이디 추출
    userid = request.args.get('userid')

    # 중복 체크 로직
    existing_user = Fcuser.query.filter_by(userid=userid).first()
    if existing_user:
        return jsonify({'result': False})  # 중복된 경우

    # 중복되지 않은 경우
    return jsonify({'result': True})

# 주문서 작성 페이지
@app.route("/buy", methods=['GET', 'POST'])
def buy_receipt():  # 구매 버튼(주문서 작성) 함수
    kind = request.args.get("kind")  # 영어로 쿼리문자를 받고,
    kind_dict = { "aori": "아오리",  # 해당하는 한글에 매치
                  "hongro": "홍로",
                  "busa": "부사", }
    kind = kind_dict[kind]
    print(kind)
    userid = request.args.get("user")
    print(userid)
    if userid == 'None':  # 로그인 X
        flash("로그인 후 서비스 이용 가능합니다.")
        return redirect('/')
    else:  # 로그인 O
        form = ReceiptRegisterForm()
        fcuser = Fcuser.query.filter_by(userid=userid).first()  # 'fcuser'테이블에서 userid필드가 일치하는 튜플의 커서를 리턴
        print(fcuser.userid)
        if form.validate_on_submit():  # POST검사의 유효성검사가 정상적으로 되었는지 확인할 수 있다. 입력 안한것들이 있는지 확인됨.
            # 비밀번호 = 비밀번호 확인 -> EqulaTo

            receipt = Receipt()  # models.py에 있는 Receipt
            receipt.userid = form.data.get('userid')
            receipt.username = form.data.get('username')
            receipt.address = form.data.get('address')
            receipt.number = form.data.get('number')
            receipt.weight = form.data.get('weight')
            receipt.kind = form.data.get('kind')
            receipt.requested_term = form.data.get('requested_term')

            print(receipt.userid, receipt.weight)
            db.session.add(receipt)  # id, name 변수에 넣은 회원정보 DB에 저장
            db.session.commit()  # 커밋
            flash("주문이 완료 되었습니다.")  # 팝업
            return redirect('/')  # 주문서 작성 완료시 화면이동
        return render_template('transaction.html', form=form, form2=fcuser, kind=kind)


@app.route('/login', methods=['GET', 'POST'])
def login():
    form = LoginForm()  # 로그인 폼 생성
    if form.validate_on_submit():  # 유효성 검사
        session['userid'] = form.data.get('userid')  # form에서 가져온 userid를 session에 저장

        return redirect('/')  # 로그인에 성공하면 홈화면으로 redirect


    return render_template('login.html', form=form)
@app.route('/logout', methods=['GET'])
def logout():
    session.pop('userid',None)
    return redirect('/')

@app.route('/mypage', methods=['GET', 'POST'])
def popup():
    userid = request.args.get("userid")
    print(userid)
    return render_template('mypage.html', userid=userid)

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

    app.run(host="0.0.0.0", port=8080, debug=True)