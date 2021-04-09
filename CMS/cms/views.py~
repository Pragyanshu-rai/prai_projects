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
    
    return redirect('login')

def dashboard(request):
    
    global stuff
    
    if request.user.is_authenticated == True:
        
        contact = Contact.objects.get(user = request.user)
        
        return render(request, 'dashboard.html', stuff)
    
    return redirect('login')

def pastbooking(request):
    
    global stuff
    
    if request.user.is_authenticated == True:
        
        contact = Contact.objects.get(user = request.user)
        
        stuff['records'] = Details.objects.filter(contact=contact)
        
        return render(request, 'pastconsult.html', stuff)
    
    return redirect('login')

def activebooking(request):
    
    global stuff
    
    if request.user.is_authenticated == True:
        
        contact = Contact.objects.get(user = request.user)
        
        stuff['records'] = Details.objects.filter(contact=contact)
        
        return render(request, 'pastconsult.html', stuff)
    
    return redirect('login')

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
            
            stuff['doctors'] = Doctor.objects.all()
            
            stuff['doctor'] = stuff['doctors'][0]
        
        print(stuff['doctor'].id)
        
        if request.method == 'POST':
            
            date = request.POST['date']
            
            slot = request.POST['slot']
            
            if has_duplicate(date, slot, stuff['doctor'].id) == True:
                
                messages.info(request, "This Slot Is Not Available!")
                
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
            
            return redirect('profile')
        
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
        
        msg="""This is your otp:- """+str(otp_otp)+""" please do not share your otp with anyone.\nThis is a system genrated message, DO NOT REPLY!"""
        
        send_email("OTP", msg, email)
        
        messages.info(request, 'OTP is sent to your regietered email id ')
            
        stuff['warning'] = False
        
        return redirect('otp', uid=uid)
    
    return render(request, 'forgot.html', stuff)

def change(request, uid=-1):
    
    global stuff
    
    if stuff['change'] == True :
        
        stuff['change'] = False
        
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

