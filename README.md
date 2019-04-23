# zhidao
安卓端 吱道
借这个搭建的框架
https://blog.csdn.net/renjianhhong/article/details/72844622

#用到的框架：

UI—-面向对象

数据交互—-MVP模式 

数据库——GreenDao 

网络图片加载—–picasso 

json解析—–gson 

http请求—-OKHttp 

事件总线—-eventbus


#包的简介

bean——————存放Java model对象 

biz———————业务模块，根据不能业务建立子模块 

bridge————底层功能实现跟UI层的衔接层 

capabilities———————底层功能具体实现（后期项目迭代到一定程度稳定后会考虑以jar形式导入） 

constant———————————常量 

ui—————————————————界面，根据不同业务建立子模块 

util———————————————业务层公共方法，工具 

view———————————————自定义view实现
