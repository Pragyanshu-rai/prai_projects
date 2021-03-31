from django.db import models

from django.contrib.auth.models import User

# Create your models here.

class Doctor(models.Model):
    
    name = models.CharField(max_length=80)
    
    age = models.CharField(max_length=4)
    
    experience = models.CharField(max_length=4)
    
    domain = models.CharField(max_length=30)
    
    office_number = models.CharField(max_length=11)
    
    def __str__(self):
        
        return str(self.id)+" - "+self.name+" - "+self.domain
    

class Contact(models.Model):
    
    user = models.OneToOneField(User, on_delete=models.CASCADE)    
    
    address = models.CharField(max_length=80)    
    
    phone = models.CharField(max_length=11)
    
    def make_contact(self, user, address, phone):
        self.user = user
        self.address = address
        self.phone = phone
    
    def __str__(self):
        
        return str(self.user.username)+" - "+self.phone

class Details(models.Model):
    
    contact = models.ForeignKey(Contact, on_delete=models.CASCADE) 
        
    prescription = models.CharField(max_length=30, default="day - none")
    
    detail = models.ImageField(null=True) 
    
    def __str__(self):
        
        return self.prescription
