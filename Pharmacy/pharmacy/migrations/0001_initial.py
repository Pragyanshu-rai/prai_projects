# Generated by Django 3.1.6 on 2021-04-12 13:07

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
    ]

    operations = [
        migrations.CreateModel(
            name='Manufacturer',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=25)),
                ('address', models.CharField(max_length=50)),
                ('email', models.CharField(max_length=30)),
                ('mobile', models.CharField(max_length=11)),
            ],
        ),
        migrations.CreateModel(
            name='Product',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=40)),
                ('description', models.CharField(max_length=200)),
                ('direction', models.CharField(max_length=50)),
                ('precautions', models.CharField(max_length=50)),
                ('available', models.IntegerField(default=0)),
                ('manufacturer', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to='pharmacy.manufacturer')),
            ],
        ),
        migrations.CreateModel(
            name='Customer',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('profile_pic', models.ImageField(null=True, upload_to='')),
                ('date_of_birth', models.DateField(blank=True)),
                ('address', models.CharField(max_length=50)),
                ('mobile', models.CharField(max_length=11)),
                ('customer', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL)),
            ],
        ),
    ]