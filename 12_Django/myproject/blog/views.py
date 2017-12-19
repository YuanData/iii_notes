from django.shortcuts import render
from django.utils import timezone
from .models import Post
from .forms import PostForm
from django.contrib.auth.models import User
from django.shortcuts import redirect
from django.views.decorators.csrf import csrf_exempt
from .forms import PostForm
me = User.objects.get(username='djangoadmin')
# Create your views here.
def post_list(request):
    posts = Post.objects.all().order_by('created_date')
    post_form = PostForm()
    #return render(request,'blog/post_list.html',{'posts':posts,'post_form':post_form})
    return render(request,'blog/post_list.html',locals())

@csrf_exempt
#此段可刪除start#
def add_record(request):
    if request.POST:
        title=request.POST['title'].encode('utf-8')
        text=request.POST['text'].encode('utf-8')
        newpost=Post.objects.create(author=me,title=title,text=text)
    #return post_list(request)    
    return redirect('/blog')
#此段可刪除end#  
def add_record(request):
    if request.method =='POST':
        data = request.POST
        form = PostForm(data)
        Post_form = form.save(commit=False)
        Post_form.author = me
        Post_form.save()
        return post_list(request)
    else:
        return render(request,'blog/404.html',{})
        #http://127.0.0.1:8000/blog/add_record?title=get&text=get

def post_record(request,id):
    post = Post.objects.get(id=id)
    return render(request,'blog/post_record.html',locals())

def post_record(request,id):
    posts = Post.objects.filter(id__gte=id)[:2]
    if len(posts)==1:
        post=posts[0]
        next_post=post
    else:
        post=posts[0]
        next_post=posts[1]
    return render(request,'blog/post_record.html', locals())