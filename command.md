# git

## git操作

- 仓库初始化

  `git init`

  完成后生成==.git==文件

- 添加资源至本地仓库

  `git add file`

- 提交资源到本地仓库

  `git commit -m "message"`

- 关联远程仓库

  `git remote add origin git@github.com:user/res.git`

- 推送

  `git push origin main` 

- 查看文件状态

  `git status`

  > 红色——未添加的文件
  >
  > 绿色——已添加未提交的文件
  
- 强制推送

  `git push origin +main` 
  
- 删除远程文件

  `git rm --cached file` 然后提交、推送
  
- 删除远程分支

  `git push origin -d target`
  
  



## 分支不一致

网页修改提交内容后，本地分支与远程分支不一致

`git fetch origin` 获取远程中的最新版本

`git merge -m "message" origin/main` 合并到本地分支

`git commit -m "message"`

## 文件超出100MB

1. 下载“git Large File Storage”

   https://git-lfs.github.com/    
   `apt-get install git-lfs`

   对于大文件进行版本控制的开源 Git 扩展——lfs

   验证安装成功：`git lfs install` -> ==Git LFS initialize==

2. 存储文件与 Git LFS 关联

   `git lfs track file` 

   会生成一个 ==.gitattributes== 文件
   
   最好将生成的文件提交到远程仓库中
   
   



# Linux

基于 CentOS7

## 目录结构

- bin

  存储常用命令

- sbin

  root用户使用的管理程序

- boot

  存储Linux的核心文件和映像文件

- dev

  设备管理，存储Linux所有设备的文件格式

- etc

  存储配置文件

- home

  普通用户目录

- lib lib64

  存储开机所需的基本动态链接共享库

- media

  共享的外部设备挂载目录

- mnt

  挂载的其他文件系统目录，临时挂载

- opt

  推荐安装用户软件的目录

- proc

  虚拟目录，存储系统内存的映射，可查看系统信息

- root

  超级管理员root的数据目录

- run

  存储系统本次运行需要使用的，关闭后删除，下次启动时重新生成创建

- src

  存储服务启动后需要提取的数据

- sys

  存储内核中新出现的文件系统sysfs

- tmp

  临时数据存储目录

- user

  存放用户的应用程序数据

- var

  存储不断扩容的文件，如日志

## 系统服务管理

存储在 /etc/init.d 目录下

1. systemctl stop|start|staus|restart 服务

   firewalld 防火墙、network 网络

2. init 运行级别

   0 停机; 1 单用户，只能root登录，无法远程; 2 多用户，无法网络; 3 完全多用户，登录后是命令模式; 4 未使用; 5 X11控制台，进入图形GUI模式; 6 系统正常关闭重启

3. 关机和重启

   关机：`init 0`  关机并断电

   ​         `halt` 关闭系统不断电

   ​         `poweroff` 直接断电

   ​         `shutdown -h now` 立即关机 `shutdown -c` 取消上次多shutdown命令

   重启：`init 6` 

   ​         `reboot` 

   ​		  `shutdown -r now` 

## 文件/目录操作

- pwd

  print working directory 打印当前目录(绝对路径)

- ls

  查看当前目录下的文件/目录

  -a 含隐藏文件

  -l `ll` 不是独立的命令 

  详细信息 

  文件类型 用户权限 用户组权限 其他用户权限 链接数 用户 用户组 文件大小 修改时间 文件名

  文件类型：-文件	d目录	l软连接

  

  > ==**命令文件在bin目录下，每个独立命令在此位置都有一个独立的脚本。**==

- cd

  change directory 切换目录

  \- 切换到上一次所在路径

  .. 切换到上一层目录(退一层)

- mkdir

  make directory 创建目录

  -p 创建多级目录

- rmdir

  remove directory 删除目录

  -p 递归删除目录

- touch

  创建文件

- cp

  copy 复制文件或目录

  cp -r source targetDir 复制source目录及其子目录到targetDir

- mv

  move 移动文件

- rm

  删除文件或目录

  -f 强制删除，无需确认 force

  -d 删除空目录

  -r 递归删除 recursively

  > `rm -rf` 命令 ==**r f 参数代表的含义**==

- 查看文件

  - cat

    查看文件内容

    `cat -n` 带行号输出

  - tail

    查看文件的末端内容

  - head

    查看文件头的内容

    > ==如何查看文件前x行或后x行数据==
    >
    > `head -n x target.file`
    >
    > `tail -n x target.file`
    >
    > ==当遇到大文件时，可适用`head`或`tail`查看==

  - more

    查看文件内容

    回车 查看下一行

    空格 查看下一页

    Ctrl+B 返回上一页

    Ctrl+F 翻屏

    = 当前所在行

    :f 输出当前查看的文件名和行号

    q 退出more

  - less

    查看文件，可对文件进行查找

    回车 查看下一行

    空格 查看下一页

    /内容 向下搜索，内容标注

    ?内容 向上搜索，内容标注

- echo

  `echo 123`  打印123

  echo -e \`ls` 在当前目录下执行反引号中的命令   

  `echo -e "\t"` 打印制表符

  `echo $PATH` 显示系统变量

  > ==\> 和 \>> 对区别==
  >
  > \> 会覆盖之前的所有内容
  >
  > \>> 不会改变之前的内容，回对内容追加
  >
  > ==*合并两个文件内容*==
  >
  > `cat a >> b`

- ln

  创建软连接

  `ln -s file target` 

## 时间/日期

- date

  `date` 2022年 12月 01日 星期四 10:45:39 CST

  `date +%Y` 2022	`date +%y` 22

  `date +%Y-%m-%d" "%H:%M:%S` 2022-12-01 10:45:39

- cal

  `cal` 打印当前月份的日历

  `cal 2022` 打印2022年的日历

## 用户/用户组

<font size="6" color="cyan">用户</font>

==添加用户==

`useradd tom` 添加用户tom

`useradd -g root bob` 在root下添加bob用户

==密码设置==

`password tom` 给tom设置密码

==切换用户==

`su tom` 切换到tom用户

==删除用户== 

`userdel tom` 删除tom用户，但home下tom用户依然存在

`userdel -r tom` 删除tom用户及home下的tom目录

==查看用户== 

`id tom` 判断是否有tom用户

`cat /etc/passwd` 查看有哪些用户

`whoami` 查看当前所使用的用户

`who am I` 查看当前用户以及登录时间

==root权限==

1.修改文件 /etc/sudoers

2.在文件中添加 tom ALL=(ALL) ALL

3.保存退出

<font size="6" color="cyan">用户组</font>

`groupadd tom` 创建用户组tom

`groupmod -n toms tom` 更改组名

`cat /etc/group` 查看有哪些用户组

`usermod -g toms bob` 将bob用户放入toms组

`groupdel toms` 删除toms组

## 文件权限

文件类型:-文件	d目录	l软连接

用户权限:rwx

用户组权限:rwx	一个组的用户权限

其他用户权限:rwx

> ==**文件删除权限问题**==
>
> r 可读，可查看子文件和子目录的名字
>
> w可写，可在这个目录下增删子文件/子目录
>
> x可执行，可进入该目录

==权限修改==

chmod命令 change mode

`chmod {ugoa}{+-=}{rwx} target` 

> u user 	g group 	o other 	a all
>
> \+ 增加权限	- 取消权限	=只有某个权限

==改变文件的所有者==

`chown user target` 将target目录的所有者变为user

`chown -R user target` 将target目录及其子目录/子文件所有者改为user

`chown user target` 改变target的用户组

`chgrp -R user target` 将target目录及其子目录/子文件的用户组改为user

## 文件搜索

- find

  -name 查找文件的名字 `find ./ -name ".key"` 在当前目录下查找文件名以 key 结尾的文件名

  -user 查找符合指定用户的文件 `find ./ -user tom` 在当前目录下查找tom用户所属的文件

  -size 查找符合大小要求的文件 `find ./ -size +10c` 在当前目录下查找大于10字节的数据

- locate

  `locate *.key` 在所有目录下查找以 key 结尾的文件

- grep

  结合管道("|")过滤搜索

  `cat a | grep -in "abc"` -i 忽略大小写     -n 显示出现的行号

## 磁盘

- du

  disk usage 查看磁盘的占用情况

  -h `du -h` 当前目录下每个文件的大小

  -a `du -a` 查看子目录的大小和文件大小

  -c `du -s` 查看该目录的大小

  --max-depth `du --max-depth=1 /dir` 查看dir目录下的一个深度

- df

  disk free 查看磁盘的剩余空间

  -h 显示容量、已用、可用的大小 G为单位

- 挂载

  `mount -t iso9660 /dev/mon /home/dir` 将/home/dir挂载到/dev/mon上

  `umount /home/dir` 卸载挂载

  -t 文件类型 iso9660 光盘

## 进程

- ps

  process 查看正在运行的进程

  -a 所有带有终端用户的进程

  -u 用户友好显示

  -x 当前用户的所有进程，包含没有终端的进程

  -------------------------------

  USER 启动进程的用户

  PID			进程号

  %CPU		cpu占比

  %GPU	

  %MEM		内存占比

  VSZ			虚拟内存大小

  RSS			实际物理内存的大小

  TTY			那个终端运行

  STAT	  	进程状态 S睡眠	R运行	T暂停	Z僵尸	s含子进程	l多线程		+前台显示

  START		进程的启动时间

  TIME			占用cpu的时间

  COMMAND  启动进程的命令

  -------------------------------------

  

- kill

  `kill 1111` 结束1111号的进程

  `killall firefox` 结束Firefox的进程

  `kill -9 1111` 强制结束1111的进程

- pstree

  显示进程树

  -p 显示进程号

  -u 显示进程的所属用户

- top

  显示实时的进程状态

  -d `top -d 5` 5秒更新一次显示

  -i `top -i` 不显示休眠的进程

  -p `top -p 11111` 指定进程进行监控，显示状态

> ps -aux 显示进程的信息	
>
> ps -ef

## 网络

- netstat

  显示网络状态和端口

  -a 所有正在监控和未监控的socket

  -n 禁止显示别名

  -l 只列出在监听的服务

  -p 哪些进程在使用

  `netstat -anp` 

## 软件安装

- rpm 安装

  rpm，RedHat Package Manager。红帽提供的软件包管理工具，包名格式如：软件名-版本-运行平台.rpm。

  - `rpm -qa` 查看已安装的rpm包
  - `rpm -e --nodeps tree-版本-x86_64` 卸载tree包，`-e` 表示卸载，`--nodeps` 卸载时不检查依赖
  - `rpm -ivh tree-版本-x86_64.rpm` 安装tree软件，`-i` install `-v` verbose `-h` hash

- yum 安装

  yum，Yellow Dog Updater Modified。Red Hat公司的软件管理器，基于rpm包来安装的。安装时会自动处理依赖。

  install	update	check-update	remove	list	clean(清理过期缓存)	deplist(显示依赖关系)

  `yum -y install tree` yum安装tree	-y 确认安装

  



1. 查看 init system

   `ps --no-headers -o comm 1` --> systemed(systemctl) System V init(service)
