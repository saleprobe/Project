from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()           #SQLAlchemy를 사용해 데이터베이스 저장

class Fcuser(db.Model):
    __tablename__ = 'fcuser'   #테이블 이름 : fcuser
    id = db.Column(db.Integer, primary_key = True)   #id를 프라이머리키로 설정
    password = db.Column(db.String(64))     #패스워드를 받아올 문자열길이
    userid = db.Column(db.String(32))       #이하 위와 동일
    username = db.Column(db.String(8))
    number = db.Column(db.String(13))
    address = db.Column(db.String(64))

class Receipt(db.Model):
    __tablename__ = 'receipt'   #테이블 이름 : receipt
    id = db.Column(db.Integer, primary_key = True)   #id를 프라이머리키로 설정
    userid = db.Column(db.String(32))  # userid를 받아올 문자열 길이
    username = db.Column(db.String(8))  # 이하 위와 동일
    number = db.Column(db.String(13))
    address = db.Column(db.String(64))
    weight = db.Column(db.String(3))
    kind = db.Column(db.String(10))