from flask import flash, redirect
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
            if Fcuser.query.filter_by(userid=userid).first() != None:
                fcuser = Fcuser.query.filter_by(userid=userid).first()
                print(fcuser.password)
            else:
                flash("없는 아이디 입니다.")
                # return redirect('/index.html')
                raise ValueError('Wrong id')
            if fcuser.password != password:
                # raise ValidationError(message % d)
                # return "비밀번호가 틀립니다"
                flash("비밀번호가 틀립니다.")
                # return redirect('/index.html')
                raise ValueError('Wrong password')
    userid = StringField('userid', validators=[DataRequired()])
    password = PasswordField('password', validators=[DataRequired(), UserPassword()])