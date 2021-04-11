from django.contrib import admin

from .models import *

# Register your models here.

admin.site.register(Doctor)
    
admin.site.register(Contact)

admin.site.register(Booking)

admin.site.register(Details)

admin.site.register(History)

admin.site.register(Reports)
