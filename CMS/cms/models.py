from django.db import models

from django.contrib.auth.models import User

# Create your models here.

class Doctor(models.Model):
    
    name = models.CharField(max_length=80)
    
    age = models.CharField(max_length=4)
    
    experience = models.CharField(max_length=4)
    
    domain = models.CharField(max_length=30)
    
    office_number = models.CharField(max_length=11)
    
    def __str__(this):
        
        return str(this.id)+" "+this.name+" "+domain
    

class Contact(models.Model):
    
    user = models.OneToOneField(User, on_delete=models.CASCADE)    
    
    address = models.CharField(max_length=80)    
    
    phone = models.CharField(max_length=11)
    
    def make_contact(self, user, address, phone):
        self.user = user
        self.address = address
        self.phone = phone

class Details(models.Model):
    
    contact = models.ForeignKey(Contact, on_delete=models.CASCADE) 
        
    detail = models.ImageField(null=True) 
