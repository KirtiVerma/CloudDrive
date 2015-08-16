import os, time
import shutil
import re
from django.views.decorators.csrf import csrf_exempt
from datetime import datetime
from django.db.models.fields import FloatField
from time import gmtime, strftime
from django.shortcuts import render
from django.shortcuts import render_to_response
from django.http import HttpResponse, HttpResponseRedirect
from django.template import RequestContext, loader
from django.shortcuts import render, get_object_or_404
from django.core.urlresolvers import reverse
from django.views import generic
from minor.models import Person
from django.contrib.auth import authenticate, login, logout
from django.contrib.auth.models import User, Group
from .forms import UploadFileForm
from django.core.context_processors import csrf
from django.contrib.sessions.models import Session
from django.template import Context
from django.contrib.auth.decorators import login_required
from django.core.exceptions import ObjectDoesNotExist
from django.db import IntegrityError
from django.utils.decorators import method_decorator


# Create your views here.

@csrf_exempt
def my_view(request):
    username = request.POST.get('username', False)
    password = request.POST.get('password', False)
    user = authenticate(username=username, password=password)
    if user is not None:
        if user.is_active:
            login(request, user)
            return HttpResponseRedirect(reverse('minor:try1'))
        else:
            # Return a 'disabled account' error message
            return HttpResponseRedirect(reverse('minor:index'))
    else:
        # Return an 'invalid login' error message.
        return HttpResponseRedirect(reverse('minor:wrong'))
    

class try1(generic.ListView):
    model = User
    template_name= 'minor/try1.html'
    
    def get(self, request):
        sessionid = request.session.session_key
        session = Session.objects.get(session_key=sessionid)
        uid = session.get_decoded().get('_auth_user_id')
        user = User.objects.get(pk=uid)
        uname = user.username
        path = os.path.dirname(os.path.abspath(__file__))
        myfiles = os.path.join(path, "document")
        myfiles = os.path.join(myfiles, uname)
        os.chdir(myfiles)
        total_size=0.00
        float(total_size)
        modified_time = []
        t=0.00
        float(t)
        #d = {}
        newlist = []
        for file in os.listdir("."):
            #modified_time = time.ctime(os.path.getmtime(file))
            newlist.append(uname+"\\"+file)
            #print (os.path.getmtime(file))
            modified_time.append(datetime.fromtimestamp((os.path.getmtime(file))).strftime('%Y-%m-%d %H:%M:%S'))
            total_size += os.path.getsize(file)
        total_size = total_size/1073741824
        t=10-total_size
        total_size=round(total_size, 2)
        t=round(t, 2)
        return render_to_response('minor/try1.html', {'files': newlist, 'size1': total_size, 'size2' : t, 'mtime' : modified_time}, context_instance=RequestContext(request))


class viewfile(generic.ListView):
    model = User
    template_name= 'minor/viewfile.html'

class help1(generic.ListView):
    model = User
    template_name= 'minor/help.html'
    
class index(generic.ListView):
    model = Person
    template_name= 'minor/index.html'


class login1(generic.ListView):
    model = Person
    template_name= 'minor/login.html'

def logout_view(request):
    logout(request)
    return HttpResponseRedirect(reverse('minor:index'))

class wrong(generic.ListView):
    model = Person
    template_name= 'minor/wrong.html'

class notunique(generic.ListView):
    model = Person
    template_name= 'minor/notunique.html'


@csrf_exempt
def register(request):
    username = request.POST.get('username', False)
    password = request.POST.get('password', False)
    first = request.POST.get('firstname', False)
    last = request.POST.get('lastname', False)
    email = request.POST.get('email', False)
    if validateEmail(email):
        try:
            user = User.objects.create_user(username, email, password)
            user.last_name = last
            user.first_name = first
            user.save()
            dirname = username
            save_path1 = os.path.dirname(os.path.abspath(__file__))
            save_path1 = os.path.join(save_path1, "document")
            os.mkdir(os.path.join(save_path1, dirname))
            return HttpResponseRedirect(reverse('minor:index'))
        except IntegrityError:
            return HttpResponseRedirect(reverse('minor:notunique'))
            
    else:
        return HttpResponseRedirect(reverse('minor:enotvalid'))

class enotvalid(generic.ListView):
    model = Person
    template_name= 'minor/enotvalid.html'

    
def validateEmail(email):
    if len(email) > 6:
        if re.match(r'\b[\w.-]+@[\w.-]+.\w{2,4}\b', email) != None:
            return 1
    return 0

class register1(generic.ListView):
    model = Person
    template_name= 'minor/register.html'



class FileUpload(generic.ListView):
    model = User
    template_name = 'minor/fileupload.html'

    def get(self, request):
        form = UploadFileForm()
        c = {'form': form}
        c.update(csrf(request))
        return render(request, self.template_name, c)
    
    def post(self, request):
        sessionid = request.session.session_key
        session = Session.objects.get(session_key=sessionid)
        uid = session.get_decoded().get('_auth_user_id')
        user = User.objects.get(pk=uid)
        uname = user.username
        a=request.POST
        form = UploadFileForm(request.POST, request.FILES)
        path = os.path.dirname(os.path.abspath(__file__))
        myfiles = os.path.join(path, "document")
        myfiles = os.path.join(myfiles, uname)
        os.chdir(myfiles) 
        total_size = 0
        t = 0
        fsize = 0
        for file in os.listdir("."):
            total_size += os.path.getsize(file)
        total_size1 = total_size/1048576
        t = 1073741824 - total_size
        print request.FILES['file'].size
        if request.FILES['file'].size < t:
            if form.is_valid():
                handle_uploaded_file(request.FILES['file'], uname)
                return HttpResponseRedirect(reverse('minor:uploadsuccess'))
        else:
            return HttpResponseRedirect(reverse('minor:sizeerror'))
        
            
        

def file_upload(request):
    model = User
    if request.method == 'POST':
        a=request.POST
        form = UploadFileForm(request.POST, request.FILES)
        if form.is_valid():
            handle_uploaded_file(request.FILES['file'])
            return HttpResponseRedirect(reverse('minor:uploadsuccess'))
    else:
        form = UploadFileForm()

    c = {'form': form}
    c.update(csrf(request))
    return render_to_response('minor/fileupload.html', c)



def handle_uploaded_file(f, uname):
    if f:
        #save_path = 'e:/minor2/files'
        save_path = os.path.dirname(os.path.abspath(__file__))
        #destination = open(os.path.join(save_path,f.name), 'wb+')
        save_path = os.path.join(save_path,"document")
        save_path = os.path.join(save_path,uname)
        destination = open(os.path.join(save_path,f.name), 'wb+')
        #destination = open('/tmp', 'wb+')
        for chunk in f.chunks():
            destination.write(chunk)
        destination.close()
        
class uploadsuccess(generic.ListView):
    model = Person
    template_name= 'minor/uploadsuccess.html'
    def get(self, request):
        form = UploadFileForm()
        c = {'form': form}
        c.update(csrf(request))
        return render(request, self.template_name, c)

class sizeerror(generic.ListView):
    model = Person
    template_name= 'minor/sizeerror.html'


def myfiles_page(request):
    sessionid = request.session.session_key
    session = Session.objects.get(session_key=sessionid)
    uid = session.get_decoded().get('_auth_user_id')
    user = User.objects.get(pk=uid)
    uname = user.username
    path = os.path.dirname(os.path.abspath(__file__))
    myfiles = os.path.join(path, "document")
    myfiles = os.path.join(myfiles, uname)
    os.chdir(myfiles)
    float(total_size)
    total_size=0.00
    float(t)
    t=0.00
    #d = {}
    newlist = []
    for file in os.listdir("."):
        newlist.append(uname+"\\"+file)
        total_size += os.path.getsize(file)
    total_size = total_size/1073741824
    t=10-total_size
    return render_to_response('minor/myfiles_page.html', {'files': newlist, 'size1': total_size, 'size2' : t}, context_instance=RequestContext(request))

class Delete(generic.ListView):
    model = User
    template_name = 'minor/delete.html'
    
    @method_decorator(csrf_exempt)
    def get(self, request):
        sessionid = request.session.session_key
        session = Session.objects.get(session_key=sessionid)
        uid = session.get_decoded().get('_auth_user_id')
        user = User.objects.get(pk=uid)
        uname = user.username
        path = os.path.dirname(os.path.abspath(__file__))
        myfiles = os.path.join(path, "document")
        myfiles = os.path.join(myfiles, uname)
        os.chdir(myfiles) 
        #x = 0
        #d = {}
        newlist = []
        for file in os.listdir("."):
            newlist.append(file)
        return render_to_response('minor/delete.html', {'files': newlist}, context_instance=RequestContext(request))

@csrf_exempt
def afterdelete(request):
    sessionid = request.session.session_key
    session = Session.objects.get(session_key=sessionid)
    uid = session.get_decoded().get('_auth_user_id')
    user = User.objects.get(pk=uid)
    uname = user.username
    path = os.path.dirname(os.path.abspath(__file__))
    myfiles = os.path.join(path, "document")
    #myfiles = os.path.join(myfiles, uname)
    a=request.GET['file']
    myfiles = os.path.join(myfiles, a)
    os.remove(myfiles)
    return HttpResponseRedirect(reverse('minor:try1'))

class sharing(generic.ListView):
    model = User
    template_name = 'minor/sharing.html'

    @method_decorator(csrf_exempt)
    def get(self, request):
        sessionid = request.session.session_key
        session = Session.objects.get(session_key=sessionid)
        uid = session.get_decoded().get('_auth_user_id')
        user = User.objects.get(pk=uid)
        uname = user.username
        path = os.path.dirname(os.path.abspath(__file__))
        myfiles = os.path.join(path, "document")
        myfiles = os.path.join(myfiles, uname)
        os.chdir(myfiles) 
        #x = 0
        #d = {}
        newlist = []
        for file in os.listdir("."):
            newlist.append(file)
        return render_to_response('minor/sharing.html', {'files': newlist}, context_instance=RequestContext(request))

@csrf_exempt    
def shared(request):
    username1 = request.GET['username']
    sessionid = request.session.session_key
    session = Session.objects.get(session_key=sessionid)
    uid = session.get_decoded().get('_auth_user_id')
    user = User.objects.get(pk=uid)
    uname = user.username
    path = os.path.dirname(os.path.abspath(__file__))
    myfiles = os.path.join(path, "document")
    myfiles1 = os.path.join(myfiles, uname)
    a=request.GET['file']
    myfiles1 = os.path.join(myfiles1, a)
    shared1 = os.path.join(myfiles, username1)
    #group = Group.objects.get(username1)
    try :
        users1 = User.objects.get(username=username1).username
        #users2 = users1.username
    except ObjectDoesNotExist:
        return HttpResponseRedirect(reverse('minor:usernotexist'))
    shutil.copy(myfiles1, shared1)
    return HttpResponseRedirect(reverse('minor:sentsuccess'))
    #checkuser = User.objects.filter(username = cleaned_info['username']).exist()
    #print checkuser
    #print users1
    #if checkuser.DoesNotExist :
        #return HttpResponseRedirect(reverse('minor:sizeerror'))
        
    #else:
    #shutil.copy(myfiles1, shared1)
    #return HttpResponseRedirect(reverse('minor:try1'))

class sentsuccess(generic.ListView):
    model = Person
    template_name= 'minor/sentsuccess.html'

class usernotexist(generic.ListView):
    model = User
    template_name= 'minor/usernotexist.html'




