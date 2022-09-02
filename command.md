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

1. 查看 init system

   `ps --no-headers -o comm 1` --> systemed(systemctl) System V init(service)
