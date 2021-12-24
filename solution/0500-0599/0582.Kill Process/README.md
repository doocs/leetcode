# [582. 杀掉进程](https://leetcode-cn.com/problems/kill-process)

[English Version](/solution/0500-0599/0582.Kill%20Process/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>系统中存在 <code>n</code> 个进程，形成一个有根树结构。给你两个整数数组 <code>pid</code> 和 <code>ppid</code> ，其中 <code>pid[i]</code> 是第 <code>i</code> 个进程的 ID ，<code>ppid[i]</code> 是第 <code>i</code> 个进程的父进程 ID 。</p>

<p>每一个进程只有 <strong>一个父进程</strong> ，但是可能会有 <strong>一个或者多个子进程</strong> 。只有一个进程的 <code>ppid[i] = 0</code> ，意味着这个进程 <strong>没有父进程</strong> 。</p>

<p>当一个进程 <strong>被杀掉</strong> 的时候，它所有的子进程和后代进程都要被杀掉。</p>

<p>给你一个整数 <code>kill</code> 表示要杀掉​​进程的 ID ，返回杀掉该进程后的所有进程 ID 的列表。可以按 <strong>任意顺序</strong> 返回答案。</p>
 

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0582.Kill%20Process/images/ptree.jpg" style="width: 207px; height: 302px;" />
<pre>
<strong>输入：</strong>pid = [1,3,10,5], ppid = [3,0,5,3], kill = 5
<strong>输出：</strong>[5,10]
<strong>解释：</strong>涂为红色的进程是应该被杀掉的进程。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>pid = [1], ppid = [0], kill = 1
<strong>输出：</strong>[1]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == pid.length</code></li>
	<li><code>n == ppid.length</code></li>
	<li><code>1 <= n <= 5 * 10<sup>4</sup></code></li>
	<li><code>1 <= pid[i] <= 5 * 10<sup>4</sup></code></li>
	<li><code>0 <= ppid[i] <= 5 * 10<sup>4</sup></code></li>
	<li>仅有一个进程没有父进程</li>
	<li><code>pid</code> 中的所有值 <strong>互不相同</strong></li>
	<li>题目数据保证 <code>kill</code> 在 <code>pid</code> 中</li>
</ul>

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
