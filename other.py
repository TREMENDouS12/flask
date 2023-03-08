from flask import Flask, render_template, request
from flask_wtf import FlaskForm
from wtforms import StringField, RadioField, validators, SubmitField
from wtforms.validators import InputRequired, Length

# app = Flask(__name__)

class SafetyReportForm(FlaskForm):
    student_number = StringField('Student number', validators=[InputRequired()])
    staff_number = StringField('Staff number', validators=[InputRequired()])
    report_number = StringField('Report number', validators=[InputRequired()])
    title = StringField('Title', validators=[InputRequired()])
    description = StringField('Describe the issue', validators=[InputRequired(), Length(max=200)])
    solution = StringField('Suggest the solution you want to see implemented')
    campus = StringField('Campus', validators=[InputRequired()])
    location = StringField('Location', validators=[InputRequired()])
    urgency = RadioField('Urgency', choices=[('urgent', 'Urgent'), ('not that urgent', 'Not that urgent'), ('not urgent', 'Not urgent')], validators=[InputRequired()])
    # submit = SubmitField('Submit')

class MyForm(FlaskForm):
    my_text = StringField('My Text', validators=[InputRequired(), Length(max=200)])

# @app.route('/')
# @app.route("/report_safety", methods=['GET','POST'])
# def safety_report():
#     form = SafetyReportForm()
#     if request.method == 'POST' and form.validate():
#         # Save the report to the database or do something else
#         return 'Report submitted successfully'
#     return render_template('safety_report.html', form=form)

# @app.route("/postform/<report_safety>")
# def safety_report(safety_report):
#     form = SafetyReportForm()
#     if request.method == 'POST' and form.validate():
#         # Save the report to the database or do something else
#         return 'Report submitted successfully'
#     return render_template('safety_report.html', form=form)