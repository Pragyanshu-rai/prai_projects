from django.shortcuts import render, get_object_or_404, redirect

from django.contrib.auth.models import User, auth

from django.http import HttpResponse

from django.contrib import messages

from .models import Contact, Details, Doctor

from os import path

from pathlib import Path

# Create your views here.

stuff = dict()

def home(request):    
    
    if request.user.is_authenticated == True:
        
        contact = Contact.objects.get(user = request.user)
        
        stuff['address'] = contact.address
        
        stuff['number'] = contact.phone
        
        print(stuff)
        
        return render(request, 'profile.html', stuff)
    
    return render(request, 'index.html');

def aboutme(request):
    
    return render(request, 'about.html')

def dashboard(request):
    
    if request.user.is_authenticated == True:
        
        contact = Contact.objects.get(user = request.user)
        
        stuff['gender'] = contact.gender
        
        stuff['age'] = contact.age
        
        stuff['address'] = contact.address
        
        stuff['number'] = contact.phone
        
        return render(request, 'dashboard.html', stuff)
    
    return redirect('login')

def doctors(request):
    
    if request.user.is_authenticated == True:
        
        doctor = Doctor.objects.all()
        
        stuff['doctors'] = doctor
        
        return render(request, 'doctors.html', stuff)
    
    return redirect('login')

def login(request):
    
    if request.method == 'POST':
        
        name = request.POST['name']
        
        email = request.POST['email']
        
        password = request.POST['password']
        
        print(email, password)
        
        user = auth.authenticate(username = name+"_"+email, password=password)
        
        if user == None:
            
            messages.info(request, 'Invalid Login Details')
            
            return redirect('login')
        
        else:
            
            auth.login(request, user)
            
            messages.info(request, 'Logged in')
            
            return redirect('home')
    
    else:
        
        return render(request, 'login.html')

def register(request):
    
    if request.method == 'POST':
        
        email = request.POST['email']
        
        name = request.POST['name']
        
        password = request.POST['password']
        
        confirm = request.POST['confirm']
        
        phone = request.POST['contact']
        
        address = request.POST['address']
        
        if password != confirm:
            messages.info(request, 'Password does not match')
            return redirect('register')
        
        if User.objects.filter(email=email).exists():
            messages.info(request, 'User Exists')
            return redirect('register')
        
        user = User.objects.create_user(username =name+"_"+email, password=password, email=email, first_name=name)
        #user.save()
        contact = Contact()
        contact.make_contact(user, address, phone)
        contact.save()
        messages.info(request, 'User Registered')
        return redirect("login")
    
    else:
        return render(request, 'register.html')

def logout(request):
    
    auth.logout(request)
    
    return redirect('home')
