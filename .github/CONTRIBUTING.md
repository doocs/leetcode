# Contribute to doocs/leetcode project

![hardcore-forking-yanglbme](http://p9ucdlghd.bkt.clouddn.com/hardcore-forking-yanglbme.gif)

由于此前不少小伙伴跑来问我要怎么参与这个项目，他们还没有在 GitHub 上搞过这种团队合作项目，对整个流程不太熟悉。因此，我实际操作了一遍，写下了这个教程，希望可以帮助到那些想要参与进来却不太熟悉操作的小伙伴们，让你们能够快速入门😄~


##  安装并配置 Git
首先你需要在自己电脑上安装 Git，对于已经安装过 Git 并配置好相关信息的小伙伴们，这第一步就不用再做啦~

对于 Windows 用户，Git 官网下载链接，[请戳这里](https://git-scm.com/downloads)，下载完安装即可。

对于 Linux 用户，只需要执行以下命令即可安装：
```bash
sudo apt-get install git
```

安装完成之后，
Windows 用户打开 Git Bash 命令行窗口，而 Linux 用户打开 `Terminal` 窗口，进行后续操作。

设置 Git 用户名及邮箱，注意，要与 GitHub 上的用户名及邮箱**保持一致**，此前有小伙伴邮箱没有与 GitHub 帐户邮箱同步，导致后续出现了一点小问题。

本次演示，我使用 GitHub 帐户 `igayhub`，邮箱为 `contact@yanglibin.info`。因此，

设置 Git 用户名：
```bash
git config --global user.name "igayhub"
```

设置 Git 邮箱：
```bash
git config --global user.email "contact@yanglibin.info"
```

> 说明：此后的所有演示，都使用 GitHub 帐户 `igayhub`，小伙伴们操作的时候，用自己的帐户跟着演示操作即可。

## Fork 代码仓库

在[本代码仓库](https://github.com/doocs/leetcode)中，点击图示中的 `Fork` 按钮。 

![fork-doocs-leetcode](http://p9ucdlghd.bkt.clouddn.com/fork-doocs-leetcode.png)

这个操作会将代码仓库复制到你的账户名下，如：`igayhub/leetcode`

![result-of-fork](http://p9ucdlghd.bkt.clouddn.com/result-of-fork.png)


## Clone 代码仓库
将复制后的代码仓库克隆到你的本地电脑上，点击绿色按钮 `Clone or download`，可以看到链接。链接有 `HTTPS` 跟 `SSH` 两种，在这里，我选择 `HTTPS` 链接。

![clone-fork-doocs-leetcode](http://p9ucdlghd.bkt.clouddn.com/clone-fork-doocs-leetcode.png)

复制该链接，在命令行窗口中执行命令：
```
git clone https://github.com/igayhub/leetcode.git
```

注意啦，这里是 clone 自己帐户下（如: `igayhub`）的 leetcode 仓库噢，不是 doocs 下的。

命令执行的过程如下：
```bash
git clone https://github.com/igayhub/leetcode.git
Cloning into 'leetcode'...
remote: Enumerating objects: 77, done.
remote: Counting objects: 100% (77/77), done.
remote: Compressing objects: 100% (53/53), done.
remote: Total 1177 (delta 32), reused 51 (delta 22), pack-reused 1100
Receiving objects: 100% (1177/1177), 228.01 KiB | 11.00 KiB/s, done.
Resolving deltas: 100% (495/495), done.
Checking connectivity... done.

```

## 创建新分支
克隆完成后，本地电脑就有一份代码了，`cd` 进入 `leetcode` 目录。
```bash
cd leetcode
```

创建新分支 `dev`，`dev` 为分支名，当然，你也可以命名为其它名字，这个看个人喜好~

```bash
git checkout -b dev
```

可以看到，它创建并切换到新分支 `dev` 下了。
```
Switched to a new branch 'dev'
```

## 做出更改并 commit
之后你可以对仓库中的文件进行修改，或者创建新文件，添加 `Solution` 代码等。在这里我对 `Solution 020` 下的 `README.md` 文件做了修改。
```bash
vim README.md 
```

修改后，执行 `add` 命令添加你的改动，然后执行 `commit` 命令提交你的改动到本地 Git。`-m` 之后是一些备注信息，备注信息要尽量体现你的改动，比如我是对  `README.md` 做了修改，那我备注信息就写 `Update solution 020....`。

```bash
git add -A
git commit -m "Update solution 020 [README.md]"

```

## 将改动发布到你的 GitHub 帐户
执行命令：
```bash
git push origin dev
```
该命令会将代码 push 到你的 leetcode 仓库 dev 分支下，如果没有该分支，则会创建一个。此操作会弹出用户名密码输入框让你输入，输入完成之后，等待一会儿，在自己的 GitHub 上就能看到提交的结果啦~
```bash
Username for 'https://github.com': igayhub
Password for 'https://igayhub@github.com': 
Counting objects: 5, done.
Delta compression using up to 4 threads.
Compressing objects: 100% (5/5), done.
Writing objects: 100% (5/5), 510 bytes | 0 bytes/s, done.
Total 5 (delta 4), reused 0 (delta 0)
remote: Resolving deltas: 100% (4/4), completed with 4 local objects.
remote: 
remote: Create a pull request for 'dev' on GitHub by visiting:
remote:      https://github.com/igayhub/leetcode/pull/new/dev
remote: 
To https://github.com/igayhub/leetcode.git
 * [new branch]      dev -> dev

```

## 提出 pull request
push 之后，在你的 GitHub 项目上，可以看到 `Compare & pull request` 绿色按钮：

![dev-pull-request](http://p9ucdlghd.bkt.clouddn.com/dev-pull-request.png)

点击按钮，创建 pull request。

![create-pull-request](http://p9ucdlghd.bkt.clouddn.com/create-pull-request.png)

pull request 完成之后，我这边就能看到啦~


（完）
