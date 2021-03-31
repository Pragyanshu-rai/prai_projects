from django.shortcuts import render, get_object_or_404, redirect

from django.contrib.auth.models import User, auth

from django.http import HttpResponse

from django.contrib import messages

from .models import Contact, Details, Doctor

from os import path

from pathlib import Path

# Create your views here.

otp = dict()

def home(request):
    
    if request.user.is_authenticated == True:
        
        return render(request, 'profile.html')
    
    return render(request, 'index.html');

def aboutme(request):
    
    return render(request, 'about.html')

def dashboard(request):
    
    if request.user.is_authenticated == True:
        
        return render(request, 'dashboard.html')
    
    return redirect('login')

def doctors(request):
    
    if request.user.is_authenticated == True:
        
        return render(request, 'doctors.html')
    
    return redirect('login')

def login(request):
    
    if request.method == 'POST':
        
        email = request.POST['email']
        
        password = request.POST['password']
        
        user = auth.authenticate(email=email, password=password)
        
        if user == None:
            
            messages.info(request, 'Invalid Login Details')
            
            return redirect('login')
        
        else:
            
            auth.login(request, user)
            
            #messages.info(request, 'Logged in')
            
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
        
        user = User.objects.create_user(username =name, password=password, email=email)
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
