
from flask import Flask, render_template, request, redirect
from flask_wtf import FlaskForm
from wtforms import StringField, RadioField
from wtforms.validators import InputRequired, Length
import sqlite3
conn = sqlite3.connect('SnMDB')
from flask import flash

app = Flask(__name__, template_folder='template')
app.secret_key='mysecretekey'

class SafetyReportForm(FlaskForm):
    student_number = StringField('Student number', validators=[InputRequired()])
    staff_number = StringField('Staff number', validators=[InputRequired()])
    report_number = StringField('Report number', validators=[InputRequired()])
    title = StringField('Title', validators=[InputRequired()])
    description = StringField('Describe the issue', validators=[InputRequired(), Length(max=200)])
    suggestion = StringField('Suggest the solution you want to see implemented')
    campus = StringField('Campus', validators=[InputRequired()])
    location = StringField('Location', validators=[InputRequired()])
    urgency = RadioField('Urgency', choices=[('high', 'High'), ('medium', 'Medium'), ('low', 'Low')], validators=[InputRequired()])
@app.route('/safety-report', methods=['GET', 'POST'])
def submit_report():
    form = SafetyReportForm()
    if form.validate_on_submit():
        conn = sqlite3.connect('SnMDB.sqlite')
        c = conn.cursor()
        c.execute('''INSERT INTO tbl_SafetyR(SStudStaffNo, SReportNo, STitle, SDescription, SSolution, SCampus, SLocation, SUrgency, StatusofReport)VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)''',
                  (form.student_number.data, form.staff_number.data, form.report_number.data, form.title.data, form.description.data, form.suggestion.data, form.campus.data, form.location.data, form.urgency.data))
        conn.commit()
        conn.close()
        flash('Report was successfully submitted', 'success')
        return redirect(url_for("home"))
    return render_template('safety-report-page.html', form=form)

@app.route('/')
@app.route("/report_safety/", methods=['GET','POST'])
def safety_report():
    form = SafetyReportForm()
    if request.method == 'POST' and form.validate():
        # Save the report to the database or do something else
        return 'Report submitted successfully'
    return render_template('safety-report-page.html')

@app.route('/data/<int:SReportNo>/update',methods = ['GET','POST'])
def update(SReportNo):
    SafetyR = SafetyRModel.query.filter_by(SReportNo=SafetyR).first()
    if request.method == 'POST':
        if SafetyR:
            db.session.delete(SafetyR)
            db.session.commit()
            SStudStaffNo = request.form['Student/Staff Number']
            SReportNo = request.form['Safety Report No']
            STitle = request.form['Title']
            SDescription = request.form['Description']
            SSolution = request.form['Solution']
            SCampus = request.form['Campus']
            SLocation = request.form['Location']
            SUrgency = request.form['Urgency']
            StatusofReport = request.form['Status of Report']
            SafetyR = SafetyRModel(SReportNo=SafetyR, SStudStaffNo=Student/StaffNumber, STitle=Title, SDescription=Description, SSolution=Solution, SCampus=Campus, SLocation=Location, SUrgency=Urgency)
            db.session.add(SafetyR)
            db.session.commit()
            return redirect(f'/data/{SReportNo}')
        return f"Safety Report = {SReportNo} was not found"
    return render_template('update.html', SReportNo = SReportNo)

@app.route('/data/<int:SReportNo>/delete', methods=['GET','POST'])
def delete(SReportNo):
    SafetyR = SafetyRModel.query.filter_by(SafetyR=SReportNo).first()
    if request.method == 'POST':
        if SReportNo:
            db.session.delete(SafetyR)
            db.session.commit()
            return redirect('/data')
        abort(404)
    return render_template('delete.html')

if __name__=='__main__':
    app.run(debug=True)



        
        
