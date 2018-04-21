<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

	<style>
		p{
			margin :5px;
			padding-left : 20px;
		}
	</style>
</head>
<body>
	<p><b>${subject} Appointment Request</b></p>
	<p>Dear ${name},</p>
	<br>
	<p>New Appointment Request</p>
	<p>Source: ${source}</p>
	<br>
	<p>Type: Appointment Request</p>
	<p>Hospital: ${hospitalName}</p>
	<br>
	<p><b>PATIENT DETAILS</b></p>
	<p>Patient's Name: ${patientName}</p>
	<p>Email: ${email}</p>
	<p>Nationality: ${nationality}</p>
	<p>Gender: ${gender}</p>
	<p>Date of Birth: ${dob}</p>
	<p>NRIC / Passport No.: ${idNo}</p>
	<p>Country of Residence: ${residency}</p>
	<p>Contact No: ${contactNo}</p>
	<p>Enquiry/Current medical conditions / symptoms / diagnosis: ${type}</p>
	<br>
	<p><b>APPOINTMENT INFORMATION</b></p>
	<p>Preferred doctor: ${doctorName}</p>
	<p>Appointment date: ${appointmentDate}</p>
	<p>Appointment Time : ${startTime} to  ${endTime}</p>
	<br>
	<br>
	<p>Regards,</p>
	<p>Parkway.</p>
	<p>Do not reply to this auto-generated message</p>
</body>
</html>