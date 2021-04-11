from django.db import models

from django.contrib.auth.models import User

import datetime

# Create your models here.

def cancel_booking(booking_id):
    
    try:
        booking = Booking.objects.get(id=booking_id)
    except:
        return "oops"
    
    his = History()
    his.add_to_history(booking.id, booking.patient_name, booking.doctor_name, booking.user_id, booking.doctor_id, booking.booking_date,  booking.time_slot)
    his.reason = "Canceled"
    his.save()
    
    booking.delete()
    

def to_date(date):
    
    if type(date) == str :
        date = date.replace(" ", "")
        date = list(map(int, date.split("-")))
        date = datetime.date(date[0], date[1], date[2]) 
      
    return date

def has_duplicate(date, slot, doctor_id, patient_id):
    
    booking = Booking.objects.filter(doctor_id=doctor_id)
    
    if type(date) == str :
        date = to_date(date)
    
    for index in range(len(booking)):
        if booking[index].booking_date == date and booking[index].time_slot == slot:
            return "Doc"
    
    booking = Booking.objects.filter(user_id=patient_id)
    
    if type(date) == str :
        date = to_date(date)
    
    for index in range(len(booking)):
        if booking[index].booking_date == date and booking[index].time_slot == slot:
            return "Pat"
    
    return "none"

def check_bookings(today):
    
    booking = Booking.objects.all()
    
    for index in range(len(booking)):
        if booking[index].booking_date < today:
            his = History()
            his.add_to_history(booking[index].id, booking[index].patient_name, booking[index].doctor_name, booking[index].user_id, booking[index].doctor_id, booking[index].booking_date,  booking[index].time_slot)
            his.save()
            booking[index].delete()

def age(dob):
    
    today = datetime.date.today()
    
    if today.month == dob.month:
        return str(today.day - dob.day)+" Day(s)"
    if today.year == dob.year:
        return str(today.month - dob.month)+" Month(s)"
    
    return str(today.year - dob.year - ((today.month, today.day)<(dob.month, dob.day)))+" Year(s)"

class Doctor(models.Model):
    
    name = models.CharField(max_length=80)
    
    age = models.CharField(max_length=4)
    
    gender = models.CharField(max_length=6, default="other")
    
    experience = models.CharField(max_length=4)
    
    domain = models.CharField(max_length=30)
    
    office_number = models.CharField(max_length=11)
    
    """slots = models.CharField(max_length=7, default="")
    
    def set_slot(self, slot):
        
        self.slots=slot
    
    def add_slot(self, slot):
        
        self.slots=self.slots+slot"""
    
    def __str__(self):
        return str(self.id)+" - "+self.name+" - "+self.gender+" - "+self.domain
    

class Contact(models.Model):
    
    user = models.OneToOneField(User, on_delete=models.CASCADE)    
    
    gender = models.CharField(max_length=10, default="other")
    
    dob = models.DateField(auto_now_add=False, auto_now=False, blank=True)
    
    address = models.CharField(max_length=80)    
    
    phone = models.CharField(max_length=11)
    
    def make_contact(self, user, gender, dob, address, phone):
        self.user = user
        self.gender = gender
        self.dob = dob
        self.address = address
        self.phone = phone
    
    def __str__(self):
        return str(self.user.id)+" - "+str(self.user.username)+" - "+self.gender+" - "+self.phone

class Details(models.Model):
    
    contact = models.ForeignKey(Contact, on_delete=models.CASCADE) 
    
    doctor = models.ForeignKey(Doctor, on_delete=models.CASCADE)
    
    date_of_visit = models.DateField(auto_now_add=False, auto_now=False, blank=True)
    
    purpose = models.CharField(max_length=30, default="General Check up")
    
    detail = models.ImageField(null=True) 
    
    def __str__(self):
        return str(self.contact.user.first_name)+" - "+str(self.doctor.name)+" - "+self.purpose+" - "+str(self.date_of_visit)

class Reports(models.Model):
    
    contact = models.ForeignKey(Contact, on_delete=models.CASCADE)
    
    doctor = models.ForeignKey(Doctor, on_delete=models.CASCADE)
    
    lab = models.CharField(max_length=40, default="General")
    
    date_of_report = models.DateField(auto_now_add=False, auto_now=False, blank=True)
    
    report_status = models.BooleanField(default=False)
    
    report_img = models.ImageField(null=True)
    
    def __str__(self):
        return str(self.id)+" - "+str(self.contact.user.first_name)+" - "+str(self.doctor.name)+" - "+str(self.report_status)+" - "+str(self.date_of_report)    

class Booking(models.Model):
    
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    
    doctor = models.ForeignKey(Doctor, on_delete=models.CASCADE)
    
    booking_date = models.DateField(auto_now_add=False, auto_now=False, blank=True)
    
    time_slot = models.CharField(max_length=10, default="0")
    
    patient_name = models.CharField(max_length=40)
    
    doctor_name = models.CharField(max_length=40)
    
    def make_booking(self, user, doctor, booking_date, time_slot, patient_name, doctor_name):
        self.user = user
        self.doctor = doctor
        self.booking_date = booking_date
        self.time_slot = time_slot
        self.patient_name = patient_name
        self.doctor_name = doctor_name        
    
    def __str__(self):
        return self.patient_name+" - "+self.doctor_name+" - "+str(self.booking_date)+" - "+self.time_slot

class History(models.Model):
    
    book_no = models.CharField(max_length=20)
    
    patient_name = models.CharField(max_length=20)
    
    doctor_name = models.CharField(max_length=20)
    
    patient_id = models.CharField(max_length=20)
    
    doctor_id = models.CharField(max_length=20)
    
    book_date = models.DateField(auto_now_add=False, auto_now=False, blank=True)
    
    book_slot = models.CharField(max_length=10)
    
    reason = models.CharField(max_length=20, default="--------")
    
    def add_to_history(self, book_no, patient_name, doctor_name, patient_id, doctor_id, date, slot):
        self.book_no = book_no
        self.patient_name = patient_name
        self.doctor_name = doctor_name
        self.patient_id = patient_id
        self.doctor_id = doctor_id
        self.book_date = date
        self.book_slot = slot
    
    def __str__(self):
        return str(self.id)+" - "+str(self.book_no)+" - "+str(self.patient_name)+" - "+str(self.doctor_name)+" - "+str(self.book_date)+" - "+str(self.book_slot)
    

