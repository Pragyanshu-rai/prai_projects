# Generated by Django 3.1.6 on 2021-04-08 10:33

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('cms', '0020_remove_doctor_slots'),
    ]

    operations = [
        migrations.CreateModel(
            name='history',
            fields=[
                ('book_id', models.AutoField(primary_key=True, serialize=False)),
                ('book_no', models.CharField(max_length=20)),
                ('patient_id', models.CharField(max_length=20)),
                ('doctor_id', models.CharField(max_length=20)),
                ('book_detail', models.CharField(max_length=20)),
            ],
        ),
    ]
