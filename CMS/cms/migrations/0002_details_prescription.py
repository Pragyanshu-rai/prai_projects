# Generated by Django 3.1.6 on 2021-03-31 15:50

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('cms', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='details',
            name='prescription',
            field=models.CharField(max_length=30, null=True),
        ),
    ]