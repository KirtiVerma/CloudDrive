from django.conf.urls import patterns, url
from django.conf import settings
from django.conf.urls.static import static

from minor import views

urlpatterns = patterns('',
 
    url(r'^login/$', views.my_view, name='login'),
    url(r'^login1/$', views.login1.as_view(), name='login1'),
    url(r'^wrong/$', views.wrong.as_view(), name='wrong'),
    url(r'^my_view/$', views.my_view, name='my_view'),
    url(r'^$', views.index.as_view(), name='index'),
    url(r'^register1/$', views.register1.as_view(), name='register1'),
    url(r'^register/$', views.register, name='register'),
    #url(r'^fileupload/$', views.fileupload.as_view(), name='fileupload'),
    url(r'^file_upload/$', views.file_upload, name='file_upload'),
    url(r'^uploadsuccess/$', views.uploadsuccess.as_view(), name='uploadsuccess'),
    url(r'^try1/$', views.try1.as_view(), name='try1'),
    url(r'^logout/$', views.logout_view, name='logout'),
    url(r'^viewfile/$', views.viewfile.as_view(), name='viewfile'),
    url(r'^FileUpload/$', views.FileUpload.as_view(), name='FileUpload'),
    url(r'^myfiles_page/$', views.myfiles_page, name='myfiles_page'),
    url(r'^delete/$', views.Delete.as_view(), name='delete'),
    url(r'^afterdelete/$', views.afterdelete, name='afterdelete'),
    url(r'^sharing/$', views.sharing.as_view(), name='sharing'),
    url(r'^shared/$', views.shared, name='shared'),
    url(r'^sizeerror/$', views.sizeerror.as_view(), name='sizeerror'),
    url(r'^usernotexist/$', views.usernotexist.as_view(), name='usernotexist'),
    url(r'^help1/$', views.help1.as_view(), name='help'),
    url(r'^enotvalid/$', views.enotvalid.as_view(), name='enotvalid'),
    url(r'^sentsuccess/$', views.sentsuccess.as_view(), name='sentsuccess'),
    url(r'^notunique/$', views.notunique.as_view(), name='notunique'),
)

