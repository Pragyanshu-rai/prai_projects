from django.urls import path

from . import views

urlpatterns = [
    path('', views.home, name='home'),
    path('log_out', views.log_out, name="log_out"),
    path('log_in/', views.log_in, name="log_in"),
    path('sign_up/', views.sign_up, name="sign_up"),
    path('useraccount/<int:uid>/', views.useraccount, name="useraccount"),
    path('project/', views.project, name='project'),
    path('task_manager/', views.task_manager, name='task_manager'),
    path('project/<int:project_key>/', views.source, name='source'),
]
