# Generated by Django 3.1.6 on 2021-04-12 16:55

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('pharmacy', '0013_auto_20210412_1654'),
    ]

    operations = [
        migrations.RenameModel(
            old_name='Cart',
            new_name='Order',
        ),
    ]
