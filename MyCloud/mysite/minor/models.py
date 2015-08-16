from django.db import models

# Create your models here.
class Person(models.Model):
    first_name = models.CharField(max_length=30)
    last_name = models.CharField(max_length=30)
    email = models.EmailField(max_length=30)
    password = models.CharField(max_length=40)
    def _unicode_(self):
        return self.email

class Files(models.Model):
    pid = models.ForeignKey(Person)
    file = models.CharField(max_length=30)
    
    
