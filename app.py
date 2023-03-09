# Import required libraries
from flask import Flask, render_template, request, redirect, url_for
from werkzeug.utils import secure_filename
import os

# Initialise Flask app
app = Flask("__maintenance_app__")

# Define allowed file extensions for picture upload
ALLOWED_EXTENSIONS = {'jpg', 'jpeg', 'png', 'gif'}

# Define route for the Maintenance Report page
@app.route('/maintenance_report', methods=['GET', 'POST'])
def maintenance_report():
    # Check if request method is POST
    if request.method == 'POST':
        # Get form inputs
        student_number = request.form['student_number']
        staff_number = request.form['staff_number']
        title = request.form['title']
        requirement1 = request.form['requirement1']
        requirement2 = request.form['requirement2']
        description = request.form['description']
        suggestion = request.form['suggestion']
        infrastructure = request.form['infrastructure']
        campus = request.form['campus']
        floor = request.form['floor']
        venue_type = request.form['venue_type']
        venue_number = request.form['venue_number']

        # Check if either student number or staff number is provided
        if not student_number and not staff_number:
            return render_template('maintenance_report.html', error='Please provide either Student Number or Staff Number')

        # Check if picture upload is provided and allowed file extension
        if 'picture_upload' not in request.files:
            return render_template('maintenance_report.html', error='Please provide a picture upload')
        picture_upload = request.files['picture_upload']
        if not picture_upload.filename:
            return render_template('maintenance_report.html', error='Please provide a picture upload')
        if not allowed_file(picture_upload.filename):
            return render_template('maintenance_report.html', error='Picture upload must be a JPG, JPEG, PNG, or GIF file')

        # Secure filename and save uploaded picture
        picture_filename = secure_filename(picture_upload.filename)
        picture_upload.save(os.path.join(app.config['UPLOAD_FOLDER'], picture_filename))

        # Check if description and suggestion are not longer than 200 characters
        if len(description) > 200:
            return render_template('maintenance_report.html', error='Description must be 200 characters or less')
        if len(suggestion) > 200:
            return render_template('maintenance_report.html', error='Suggestion must be 200 characters or less')

        # Process Maintenance Report data
        # Here you can write the code to process the Maintenance Report data,
        # such as storing it in a database or sending it via email.

        # Render success page
        return redirect(url_for('maintenance_report_success'))

    else:
        # Render Maintenance Report form
        return render_template('maintenance_report.html')

# Define route for the Maintenance Report success page
@app.route('/maintenance_report/success')
def maintenance_report_success():
    # Render success page
    return render_template('maintenance_report_success.html')

# Define function to check allowed file extensions for picture upload
def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

# Configure app to allow picture uploads and set upload folder
app.config['UPLOAD_FOLDER'] = 'uploads'
app.config['MAX_CONTENT_LENGTH'] = 16 * 1024 * 1024 # 16 MB max file size
