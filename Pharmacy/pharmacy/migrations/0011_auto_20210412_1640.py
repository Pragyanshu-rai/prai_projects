# Generated by Django 3.1.6 on 2021-04-12 16:40

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('pharmacy', '0010_auto_20210412_1639'),
    ]

    operations = [
        migrations.RenameField(
            model_name='product',
            old_name='product_offset',
            new_name='product_sold',
        ),
    ]
