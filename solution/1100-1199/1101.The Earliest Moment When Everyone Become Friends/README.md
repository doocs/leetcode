# [1101. 彼此熟识的最早时间](https://leetcode-cn.com/problems/the-earliest-moment-when-everyone-become-friends)

[English Version](/solution/1100-1199/1101.The%20Earliest%20Moment%20When%20Everyone%20Become%20Friends/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>在一个社交圈子当中，有 <code>N</code> 个人。每个人都有一个从 <code>0</code> 到 <code>N-1</code> 唯一的 id 编号。</p>

<p>我们有一份日志列表 <code>logs</code>，其中每条记录都包含一个非负整数的时间戳，以及分属两个人的不同 id，<code>logs[i] = [timestamp, id_A, id_B]</code>。</p>

<p>每条日志标识出两个人成为好友的时间，友谊是相互的：如果 A 和 B 是好友，那么 B 和 A 也是好友。</p>

<p>如果 A 是 B 的好友，或者 A 是 B 的好友的好友，那么就可以认为 A 也与 B 熟识。</p>

<p>返回圈子里所有人之间都熟识的最早时间。如果找不到最早时间，就返回 <code>-1</code> 。</p>

<p> </p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>logs = [[20190101,0,1],[20190104,3,4],[20190107,2,3],[20190211,1,5],[20190224,2,4],[20190301,0,3],[20190312,1,2],[20190322,4,5]], N = 6
<strong>输出：</strong>20190301
<strong>解释：</strong>
第一次结交发生在 timestamp = 20190101，0 和 1 成为好友，社交朋友圈如下 [0,1], [2], [3], [4], [5]。
第二次结交发生在 timestamp = 20190104，3 和 4 成为好友，社交朋友圈如下 [0,1], [2], [3,4], [5].
第三次结交发生在 timestamp = 20190107，2 和 3 成为好友，社交朋友圈如下 [0,1], [2,3,4], [5].
第四次结交发生在 timestamp = 20190211，1 和 5 成为好友，社交朋友圈如下 [0,1,5], [2,3,4].
第五次结交发生在 timestamp = 20190224，2 和 4 已经是好友了。
第六次结交发生在 timestamp = 20190301，0 和 3 成为好友，大家都互相熟识了。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 <= N <= 100</code></li>
	<li><code>1 <= logs.length <= 10^4</code></li>
	<li><code>0 <= logs[i][0] <= 10^9</code></li>
	<li><code>0 <= logs[i][1], logs[i][2] <= N - 1</code></li>
	<li>保证 <code>logs[i][0]</code> 中的所有时间戳都不同</li>
	<li><code>Logs</code> 不一定按某一标准排序</li>
	<li><code>logs[i][1] != logs[i][2]</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
