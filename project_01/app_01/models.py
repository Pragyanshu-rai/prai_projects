from django.db import models

# Create your models here.

class projects(models.Model):
    
    project_title = models.CharField(max_length = 50)
    
    file_name = models.CharField(max_length = 50)
    
    language = models.CharField(max_length = 20)
    
    lines = models.IntegerField(default = 0)
    
    def __str__(this):
        
        return str(this.id)+" "+this.project_title    

