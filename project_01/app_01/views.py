from django.shortcuts import render

from django.http import HttpResponse

# Create your views here.

def base(request):
    return HttpResponse("<title>Base page :)</title><h1 style='color:green'>Hey boiiii....</h1><hr style='color:blue' /><h3 style='color:red'>(hint: type '/app_01/home' after the url)</h3><hr style='color:blue' /><a style='text-decoration:none; font-size:20px' href='/app_01/home'> home page</a>")

def home(request):
    return render(request, 'home.html');
