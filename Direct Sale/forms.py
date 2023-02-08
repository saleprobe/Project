from flask import flash, redirect, url_for
from flask_wtf import FlaskForm
from models import Fcuser
from wtforms import StringField, PasswordField
from wtforms.validators import DataRequired, EqualTo

class RegisterForm(FlaskForm):
    userid = StringField('userid', validators=[DataRequired()])
    username = StringField('username', validators=[DataRequired()])
    address = StringField('address', validators=[DataRequired()])
    password = PasswordField('password', validators=[DataRequired(), EqualTo('re_password')]) #equalTo("필드네임")
    re_password = PasswordField('re_password', validators=[DataRequired()])

class LoginForm(FlaskForm):
    class UserPassword(object):
        def __init__(self, message=None):
            self.message = message
        def __call__(self,form,field):
            userid = form['userid'].data
            print(userid)
            password = field.data
            if Fcuser.query.filter_by(userid=userid).first() != None:  # userid 필드에서 입력한 userid와 일치 하는 튜플의 첫번째 값이 존재한다면
                fcuser = Fcuser.query.filter_by(userid=userid).first()
                print(fcuser.password)
            else:
                flash("없는 아이디 입니다.")
                # return redirect('/index.html')
                form['userid'].data = ''
                return redirect(url_for('main'))
            if fcuser.password != password:
                # raise ValidationError(message % d)
                # return "비밀번호가 틀립니다"
                flash("비밀번호가 틀립니다.")
                # return redirect('/index.html')
                form['userid'].data = ''
                return redirect(url_for('main'))
    userid = StringField('userid', validators=[DataRequired()])
    password = PasswordField('password', validators=[DataRequired(), UserPassword()])