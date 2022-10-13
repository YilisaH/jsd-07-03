### 前端MVC设计模式

- MVC是将实现前端某一个业务的所有代码划分为三部分
  - M:Model  模型,  指数据模型 ,数据相关代码
  - V: View 视图, 指页面标签相关代码
  - C:Controller控制器, 指将数据展示到页面中的过程代码

- MVC设计模式中Controller部分需要频繁的进行DOM相关操作,频繁的DOM相关操作会导致资源浪费, MVVM设计模式可以解决此问题.

### 前端MVVM设计模式

- MVVM是将实现前端某一个业务的所有代码划分为三部分

- M:Model  模型,  指数据模型 ,数据相关代码
- V: View 视图, 指页面标签相关代码
- VM: ViewModel 视图模型,   视图模型负责将页面中可能发生改变的元素和某个变量,在内存中进行绑定, 并且会不断监听变量的值的改变, 当变量的值发生改变,可以立即从绑定的关系中找到对应的页面元素, 这样就不需要每次遍历查找了, 从而提高了执行效率

### VUE

- 此框架是目前前端最流行的一款基于MVVM设计模式的框架,使用此框架可以让程序员更加便捷的使用MVVM设计模式  

- VUE框架两种使用方式:
  - 多页面模式:   在html页面中引入vue.js框架文件即可
  - 单页面模式(脚手架模式): 第四个阶段成恒来讲

- 引入vue.js框架到页面中的两种方式:

  - 引入本地文件的方式, 需要先将vue.js框架文件下载到自己电脑上 然后引入

  ![1664336914144](1664336914144.png)

  - 引入CDN服务上面的vue.js,  这种只需要有框架的url路径即可  

    ![1664337131025](1664337131025.png)

- 以下推荐国外比较稳定的两个 CDN，国内还没发现哪一家比较好，目前还是建议下载到本地。
   Staticfile CDN（国内） : https://cdn.staticfile.org/vue/2.2.2/vue.min.js
   unpkg：https://unpkg.com/vue@2.6.14/dist/vue.min.js
   cdnjs : https://cdnjs.cloudfla
  re.com/ajax/libs/vue/2.1.8/vue.min.js



- ```
  * Vue对象相当于是MVVM设计模式中的VM视图模型, 负责将页面中可能发生改变的元素和
  * data里面的变量进行绑定, 变量的值是什么页面元素就显示什么
  * 而且会不断监听变量值的改变, 值只要一变会立即找到对应的元素并让元素跟着改变
  ```

### Vue中的各种指令

1. {{变量}} :  插值,  让此处的文本内容和变量进行绑定 不依赖于某个标签
2. v-text="变量", 让元素的文本内容和变量进行绑定
3. v-html="变量", 让元素的标签内容和变量进行绑定  
4. v-bind:属性名="值", 让元素某个属性的值和变量进行绑定 , 可以去掉v-bind 只写:
5. v-model="变量", 双向绑定, 变量会影响页面控件的内容,页面控件内容改变也会影响变量  
6. v-on:事件名="方法", 事件绑定, 给元素添加事件, 事件触发时调用指定的方法, 方法必须声明在Vue对象里面的methods当中.
7.  v-for="变量 in 数组", 循环遍历, 遍历的过程中会生成当前元素,生成的数量取决于数组中元素的数量



### 晚课: 表设计之权限管理

- 权限管理需要用到三张主表以及两张关系表

  - 三张主表: 用户表, 角色表, 权限表

    create database mydb1 charset=utf8;

    use mydb1;

    create table user(id int primary key auto_increment,name varchar(20));

    create table role(id int primary key auto_increment,name varchar(20));

    create table module(id int primary key auto_increment,name varchar(20));

  - 两张关系表: 用户角色关系表, 角色权限关系表

    create table u_r(uid int, rid int);

    create table r_m(rid int,mid int);

  - 往表中插入数据:刘德华:男浏览,男评论,男发帖,女浏览      杨幂:女浏览,女评论,女发帖,女删帖

    - 用户表

      insert into user values(null,'刘德华'),(null,'杨幂');

    - 角色表

      insert into role values(null,'男会员'),(null,'女游客'),(null,'女管理员');

    - 权限表

      insert into module values(null,'男浏览'),(null,'男评论'),(null,'男发帖'),(null,'女浏览'),(null,'女评论'),(null,'女发帖'),(null,'女删帖');

    - 用户角色关系表

      insert into u_r values(1,1),(1,2),(2,3);

    - 角色权限关系表

      insert into r_m values(1,1),(1,2),(1,3),(2,4),(3,4),(3,5),(3,6),(3,7);

    ![image-20220928204214560](image-20220928204214560.png)

    ### 根据以上数据做以下几道关联查询的题

    1. 查询刘德华拥有的角色

       select r.name

       from user u join u_r ur on u.id=ur.uid

       join role r on r.id = ur.rid 

       where u.name='刘德华';

    2. 查询女管理员这个角色对应的用户是谁

       select u.name

       from user u join u_r ur on u.id=ur.uid

       join role r on r.id=ur.rid

       where r.name='女管理员';

    3. 查询女管理员拥有哪些权限

       select m.name

       from role r join r_m rm on r.id=rm.rid

       join module m on m.id=rm.mid

       where r.name='女管理员';

    4. 查询刘德华拥有哪些权限

       select m.name

       from user u join u_r ur on u.id=ur.uid 

       join r_m rm on rm.rid=ur.rid

       join module m on m.id = rm.mid

       where u.name='刘德华';

    5. 查询有女浏览权限的用户都有谁

       select u.name

       from user u join u_r ur on u.id=ur.uid 

       join r_m rm on rm.rid=ur.rid

       join module m on m.id = rm.mid

       where m.name='女浏览';









