from django.shortcuts import render, get_object_or_404, redirect

from django.contrib.auth.models import User, auth

from django.http import HttpResponse

from django.contrib import messages

from .models import projects

from os import path

from pathlib import Path

# Create your views here.

BASE_DIR = Path(__file__).resolve().parent.parent

static = path.join(BASE_DIR, 'static/')

def log_in(request):
    
    titles=dict()
    
    titles['heading']="Welcome Back!"
    
    titles['title']="Log In"
    
    if request.method == 'POST':
        
        username = request.POST['user_name']
        
        password = request.POST['password']
        
        user = auth.authenticate(username=username, password=password)
        
        if user == None:
            
            messages.info(request, 'Invalid Login Details')
            
            return redirect('log_in')
        
        else:
            
            auth.login(request, user)
            
            messages.info(request, 'Logged in')
            
            return redirect('useraccount', uid=user.id)
    
    else:
        
        return render(request, 'log_in.html', titles)


def sign_up(request):
    
    titles=dict()
    
    titles['heading']="Join Now"
    
    titles['title']="Sign Up"
    
    if request.method == 'POST':
        
        email = request.POST['email']
        
        username = request.POST['user_name']
        
        password = request.POST['password']
        
        confirm = request.POST['confirm']
        
        if password != confirm:
            messages.info(request, 'Password does not match')
            return redirect(sign_up)
        
        if User.objects.filter(email=email).exists():
            messages.info(request, 'User Exists')
            return redirect('sign_up')
        
        if User.objects.filter(username=username).exists():
            messages.info(request, 'User Name Already Taken')
            return redirect('sign_up')
        
        user = User.objects.create_user(username=username, password=password, email=email)
        user.save()
        messages.info(request, 'User Registered')
        return redirect("log_in")
    
    else:
        return render(request, 'signup.html', titles)

def log_out(request):
    auth.logout(request)
    return redirect('home')

def useraccount(request, uid):    
    
    titles=dict()
    
    titles['heading']="Profile"
    
    titles['title']="Account"
    
    return render(request, 'account.html', titles)

def home(request):
    
    titles=dict()
    
    titles['heading']="Pragyanshu Rai"
    
    titles['title']="Pragyanshu Rai"    
    
    return render(request, 'home.html', titles);


def source(request, project_key): 
    
    cards = get_object_or_404(projects, pk=project_key)
    
    file_name = static + cards.file_name    

    read_file = open(file_name, 'r')
    
    content = read_file.read() #.lstrip('\n').lstrip(' ')
    
    read_file.close()
    
    #print(content)    
    
    #content = content.replace('\n', '<br />')      
    
    titles=dict()
    
    titles['heading']="Source Code"
    
    titles['title']="Source Code"
    
    titles['content'] = content;  
    
    titles['card_name'] = cards.project_title
    
    return render(request, 'source.html', titles);


def project(request):
    
    cards = projects.objects.all()
    
    titles=dict()
    
    titles['heading']="Projects"
    
    titles['title']="Projects"
    
    titles['cards'] = cards;    
    
    return render(request, 'tools.html', titles);


def task_manager(request): 
    
    cards = projects.objects.all()    
    
    titles=dict()
    
    titles['heading']="Task Manager"
    
    titles['title']="Task Manager"
    
    titles['cards']=cards
        
    return render(request, 'task_manager.html', titles)


