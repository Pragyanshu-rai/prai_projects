# Generated by Django 3.1.6 on 2021-04-12 16:57

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('pharmacy', '0014_auto_20210412_1655'),
    ]

    operations = [
        migrations.AddField(
            model_name='order',
            name='order_price',
            field=models.FloatField(default=0.0),
        ),
    ]
