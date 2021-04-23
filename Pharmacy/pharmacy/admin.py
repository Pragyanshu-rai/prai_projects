from django.contrib import admin

# Register your models here.

from pharmacy.models import *
    
admin.site.register(Customer)
admin.site.register(Manufacturer)
admin.site.register(Product)
admin.site.register(Product_images)
admin.site.register(Product_instance)
admin.site.register(Order)
admin.site.register(Payment)
admin.site.register(Return)
