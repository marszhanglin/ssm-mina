# ssm-mina
  服务端目标：

  客户端目标：

  运维目标：

  整体目标：



## question
1.ioHander内传递数据 

## ************************************************************ ##


## 忽略文件： ##
### 规则： ###

下面我们看看常用的规则：

/mtk/ 过滤整个文件夹

*.zip 过滤所有.zip文件

/mtk/do.c 过滤某个具体文件
#### 本项目 ####

*.class

/web/minassm/war

/web/minassm/WebRoot/img

/web/minassm/WebRoot/fonts

/web/minassm/WebRoot/WEB-INF

## ************************************************************ ##

# 任务列表 #

<b>1、客户端心跳每隔一会发送一个心跳（后续实现后端可配置参考mtms）
 *实现：动定时轮序ALARMMANAGER+RECEIVE  (后续根据网络RECEIVE)* 
<br>2、将服务端mysql数据库改为本地（阿里的马上过期）



## ************************************************************ ##
# mina特性 #
<b>1、当服务端强制关闭，mina客户端马上抛异常，并关闭session 
<br>2、服务端sessionClosed，客户端是不知道的 
<br>3、当客户端关闭时 服务端inputClosed-->sessionClosed 
<br>4、当客户端网络断开，服务端是不知道的 
 