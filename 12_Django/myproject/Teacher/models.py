from django.db import models
from django.utils import timezone

class Post(models.Model):
    author = models.ForeignKey('auth.User')
    title = models.CharField(max_length=200)
    text = models.TextField()
    degree = models.CharField(max_length=100,null=True)
    created_date = models.DateTimeField(
            default=timezone.now)

    def publish(self):
        self.created_date = timezone.now()
        self.save()

    def __unicode__(self):
        return self.title
