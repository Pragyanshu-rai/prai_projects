from django.shortcuts import render, get_object_or_404, redirect

from django.contrib.auth.models import User, auth

from django.http import HttpResponse

from django.contrib import messages

from .models import *

from .mail import send_email

from os import path

from pathlib import Path

from random import randint

import datetime

# Create your views here.

user="pranshu: 12, user_test: user1 "

stuff = dict()

OTP = dict()

stuff['change'] = False

stuff['slots'] = ['09:00 AM', '09:30 AM', '10:00 AM', '10:30 AM', '11:00 AM', '11:30 AM']

def home(request):   
    
    global stuff 
    
    if request.user.is_authenticated == True:
        
        contact = Contact.objects.get(user = request.user)
        
        stuff['address'] = contact.address
        
        stuff['number'] = contact.phone
        
        stuff['gender'] = contact.gender
        
        stuff['dob'] = contact.dob
        
        stuff['age'] = age(to_date(str(contact.dob)))
        
        # for developers and testers
        print(stuff)
        
        messages.info(request, 'Logged in ')
            
        stuff['warning'] = False
        
        return render(request, 'profile.html', stuff)
    
    return render(request, 'index.html', stuff);

def aboutme(request):
    
    global stuff
    
    return render(request, 'about.html', stuff)

def profile(request):
    
    if request.user.is_authenticated == True:
        
        contact = Contact.objects.get(user = request.user)
        
        stuff['address'] = contact.address
        
        stuff['number'] = contact.phone
        
        stuff['gender'] = contact.gender
        
        stuff['dob'] = contact.dob
        
        stuff['age'] = age(to_date(str(contact.dob)))
            
        return render(request, 'profile.html', stuff)
    
    return redirect('login')

def pastconsult(request):
    
    global stuff
    
    if request.user.is_authenticated == True:
        
        contact = Contact.objects.get(user = request.user)
        
        stuff['records'] = Details.objects.filter(contact=contact)
        
        return render(request, 'pastconsult.html', stuff)
    
    return render(request, 'forbid.html', stuff)

def dashboard(request):
    
    global stuff
    
    if request.user.is_authenticated == True:
        
        contact = Contact.objects.get(user = request.user)
        
        return render(request, 'dashboard.html', stuff)
    
    return render(request, 'forbid.html', stuff)

def pastbooking(request):
    
    global stuff
    
    if request.user.is_authenticated == True:
        
        check_bookings(datetime.date.today())
        
        stuff['bookings'] = History.objects.filter(patient_id = request.user.id)
        
        stuff['page'] = "Past Appointments"
        
        stuff['line'] = "All your past appointments"
        
        return render(request, 'active_or_pastbooking.html', stuff)
    
    return render(request, 'forbid.html', stuff)

def activebooking(request):
    
    global stuff
    
    if request.user.is_authenticated == True:
        
        check_bookings(datetime.date.today())
        
        stuff['bookings'] = Booking.objects.filter(user_id = request.user.id)
        
        stuff['page'] = "Active Appointments"
        
        stuff['line'] = "All your pending or active appointments"
        
        if request.method == "POST":
            
            checks = request.POST.getlist('book_no')
            
            if len(checks) > 0: 
                for check in checks:
                    res = cancel_booking(check)
                if res != "oops":
                    stuff['warning'] = False
                    if len(checks) == 1:
                        messages.info(request, " Appointment Canceled")
                    else:
                        messages.info(request, " Appointments Canceled")
            
            return redirect('dashboard')
        
        return render(request, 'active_or_pastbooking.html', stuff)
    
    return render(request, 'forbid.html', stuff)

def report(request):
    
    global stuff
    
    if request.user.is_authenticated == True:
        
        contact = Contact.objects.get(user = request.user)
        
        stuff['reports'] = Reports.objects.filter(contact=contact)
        
        return render(request, 'reports.html', stuff)
    
    return render(request, 'forbid.html', stuff)

def doctors(request):
    
    global stuff
    
    if request.user.is_authenticated == True:
        
        check_bookings(datetime.date.today())
        
        doctor = Doctor.objects.all()
        
        stuff['doctors'] = doctor
        
        if request.method == 'POST':
            
            doctor = request.POST['booking']
            
            stuff['doctor'] = Doctor.objects.get(id=doctor)
            
            return redirect('booking')
        
        return render(request, 'doctors.html', stuff)
    
    return render(request, 'forbid.html', stuff)

def booking(request):
    
    if request.user.is_authenticated == True:
        
        doc = stuff.get('doctor', None)
        
        if doc == None:
            
            return redirect('doctors')
        
        print(stuff['doctor'].id)
        
        if request.method == 'POST':
            
            date = request.POST['date']
            
            slot = request.POST['slot']
            
            print(slot)
            
            result = has_duplicate(date, slot, stuff['doctor'].id, request.user.id)
            
            if result != "none":
                
                if result == "Doc":
                    messages.info(request, "This Slot Is Not Available!")
                else :
                    messages.info(request, "You Already Have An Appointment In This Slot And Date!")
                
                stuff['warning'] = True
                
                return render(request, 'booking.html', stuff)
            
            stuff['warning'] = False
            
            messages.info(request, "Slot Booked")
            
            booking = Booking()
            
            user = User.objects.get(id=request.user.id)
            
            doc = Doctor.objects.get(id=stuff['doctor'].id)
            
            booking.make_booking(user, doc, to_date(date), slot, user.first_name, doc.name)
            
            booking.save()
            
            print(booking)
            
            print(stuff)
            
            return redirect('doctors')#render(request, 'booking.html', stuff)
        
        return render(request, 'booking.html', stuff)
    
    return render(request, 'forbid.html', stuff) 

def login(request):
    
    global stuff
    
    if request.method == 'POST':
        
        name = request.POST['name']
        
        email = request.POST['email']
        
        password = request.POST['password']
        
        print(email, password)
        
        user = auth.authenticate(username = name+"_"+email, password=password)
        
        if user == None:
            
            messages.info(request, 'Invalid Login Details')
            
            stuff['warning'] = True
            
            return redirect('login')
        
        else:
            
            auth.login(request, user)
            
            stuff['warning'] = False
            
            return redirect('home')
    
    else:
        
        return render(request, 'login.html', stuff)

def register(request):
    
    global stuff
    
    if request.method == 'POST':
        
        name = request.POST['name']
        
        email = request.POST['email']
        
        gender = request.POST['gender']
        
        dob = request.POST['dob']
        
        phone = request.POST['contact']
        
        address = request.POST['address']
        
        password = request.POST['password']
        
        confirm = request.POST['confirm']
        
        print(dob, gender)
        
        dob = to_date(dob)
        
        print(age(dob), gender)
        
        if password != confirm:
            
            messages.info(request, 'Password does not match')
            
            stuff['warning'] = True
            
            return redirect('register')
        
        if User.objects.filter(email=email).exists():   
            
            messages.info(request, 'User Exists')
            
            stuff['warning'] = True
            
            return redirect('register')
        
        user = User.objects.create_user(username =name+"_"+email, password=password, email=email, first_name=name)
        #user.save()
        contact = Contact()
        contact.make_contact(user, gender, dob, address, phone)
        contact.save()
        messages.info(request, 'User Registered')
            
        stuff['warning'] = False
        
        return redirect("login")
    
    else:
        return render(request, 'register.html', stuff)

def otp(request, uid=-1):
    
    global stuff
    
    print("uid =",uid)
    
    if request.method == 'POST':
        
        if OTP[request.user.id] == request.POST['otp'] :
            
            messages.info(request, 'Correct OTP')
            
            stuff['warning'] = False
            
            stuff['change'] = True
            
            return redirect('change', uid=uid)
        else :
            
            messages.info(request, 'Invalid - OTP')
            
            stuff['warning'] = True
            
            return render(request, 'otp.html', stuff)
        
    return render(request, 'otp.html', stuff)

def forgot(request):
    
    global stuff
    
    if request.method == "POST":
        
        email = request.POST['email']
        
        try:
            user=User.objects.get(email=email)
        except:
            messages.info(request, 'User not register')
            
            stuff['warning'] = True
            
            return render(request, 'forgot.html', stuff)
            
        print("Email - ", user.email)
        
        uid= user.id
        
        otp_otp =  str(randint(100000, 999999))
        
        OTP[request.user.id] = otp_otp
        
        print(otp_otp)
        
        msg="""Dear Customer,

               Thank you for being associated with our company (mention company details).

                You have requested for password change for which One Time Password (OTP):- """+str(otp_otp)+""" has been generated at """+str(datetime.datetime.now()).split(".")[0]+""".

                In case you have not requested for password change, please call our Customer Care. You can also write an email at noreply.services.2001@gmail.com.

            Disclaimer

            Our executives never ask you about one time password. We recommend you do not share this with anyone to prevent fraudulent transactions.
            
            Sincerely,

            Clinic Management System (CMS)"""
        
        send_email("OTP", msg, email)
        
        messages.info(request, 'OTP is sent to your regietered email id ')
            
        stuff['warning'] = False
        
        return redirect('otp', uid=uid)
    
    return render(request, 'forgot.html', stuff)

def change(request, uid=-1):
    
    global stuff
    
    if stuff['change'] == True :
        
        stuff['change'] = False
        
        user = User.objects.get(id=uid)
        
        stuff['name'] = user.first_name
        
        return render(request, 'change.html', stuff)
    
    if request.method == "POST" :
        
        passw = request.POST['password']
        
        confirm = request.POST['confirm']
        
        if passw != confirm :
            
            messages.info(request, 'Password does not match')
            
            stuff['warning'] = True
            
            return render(request, 'change.html', stuff)
        
        user = User.objects.get(pk=uid)
        
        user.set_password(passw)
        
        user.save()
            
        messages.info(request, 'Password is changed')
            
        stuff['warning'] = False
            
        return redirect('login')
    
    return render(request, 'forbid.html', stuff)

def logout(request):
    
    global stuff
    
    auth.logout(request)
        
    messages.info(request, 'User Logged Out ')
            
    stuff['warning'] = False
    
    return redirect('home')

