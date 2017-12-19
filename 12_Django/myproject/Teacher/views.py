from django.shortcuts import render,redirect
from django.contrib.auth.models import User
from .models import Post
from .forms import PostForm, PostForm0
from django.views.decorators.csrf import csrf_exempt
me = User.objects.get(username='jojotenya')

# Create your views here.
def post_list(request):
    posts = Post.objects.all().order_by('created_date')
    post_form = PostForm()
    return render(request,'blog/post_list.html',locals())

@csrf_exempt
def add_record0(request):
    # request.POST = {'title':'test','text':'test'}
    if request.POST:
        title = request.POST['title']
        text  = request.POST['text']
        me    = User.objects.get(username='jojotenya')
        Post.objects.create(author=me, title=title, text=text)
    return post_list(request)
    #return redirect('/blog')

@csrf_exempt
def add_record(request):
    if request.method == 'POST':
        posted_data = request.POST
        form = PostForm(posted_data)    
        #form.save()
        post_form = form.save(commit=False)
        post_form.author = me
        post_form.save()
        return post_list(request)
    else:
        return render(request, '404.html',{})

def post_record(request,id):
    posts = Post.objects.filter(id__gte=id)[:2]
    if len(posts) == 1:
       post = posts[0]
       next_post = post
    else:
       post = posts[0]
       next_post = posts[1]
    return render(request, 'blog/post_record.html', locals())
