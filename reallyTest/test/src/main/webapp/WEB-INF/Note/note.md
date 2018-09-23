### SSM中遇到的问题
> - -1).在SpringMVC中我们可以通过控制器方法直接访问到`WEB-INF`下面的视图,但是如果在`WEB-INF`的视图中再与往常一样通过类RequestMapping映射 + 方法RequestMapping映射是不成功的。
action="dealAccessReq/dealSignUp"
> - 默认系统会自动加上前缀 -> http://localhost:8080/test/dealAccessReq/dealAccessReq/dealSignUp
所以我们在`WEB-INF`下面的视图中直接写方法RequestMapping映射即可。
即action="dealSignUp"
> - -2).在使用mybatis进行参数映射的时候,如果对应的setter与getter名称与属性名称不对应的话江恩那个会有问题。char型的就变成了`(Binary/Image)`型的了。
> - -3).就是在controller层调用service层的下面是完全可以用接收前台参数的POJO对象来获取所需数据的,如果这一次的指令已经成功运行的话!
> - -4).如果我们通过response对象来进行重定向跳转的话而且目标页面是WEB-INF下面的视图的话
如果我们的控制器类上面有映射的话,我们应该直接写控制器方法映射来进行跳转 -> 言外之意我们想要跳转页面就必须借助controller进行跳转。
> - -5).如果我们想在SpingMVC中的控制器方法中重定向到 WEB-INF 下面的views下面的视图的时候我们不可以直接去通过`redirect:[\]Xxx.jsp`的方式去跳转,我们只能通过重定向到控制器方法完成跳转。
> - -6).我们在做项目的时候,在页面通过相对路径引入静态资源的时候可能会碰见一种情况就是静态资源的路径往往不像是我们想要的那个路径,所以这就需要我们通过绝对路径来引入便可以解决这个问题。
eg: -> ${pageContext.request.contextPath}/js/JHAjax.min.js
> - -7).session 是在服务器停掉的时候就死亡了。而且其在TomCat中存活的时间一般就20~30分钟。只有你不断的去发送一些与session相关的请求,他才会重新计算时间保持的更长久。
> - -8).cookie 一般情况下在浏览器关闭的时候不会死掉,除非你将其设置为-1/0。
