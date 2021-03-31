from django.contrib import admin

from django.urls import path

from . import views

urlpatterns = [
    path('', views.home, name='home'),
    path('CMS/', views.home, name='home'),
    path('aboutme/', views.aboutme, name='aboutme'),
    path('dashboard/', views.dashboard, name='dashboard'),
    path('doctors/', views.doctors, name='doctors'),
    path('login/', views.login, name='login'),
    path('register/', views.register, name='register'),
]
