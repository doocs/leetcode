# [1989. 捉迷藏中可捕获的最大人数](https://leetcode.cn/problems/maximum-number-of-people-that-can-be-caught-in-tag)

[English Version](/solution/1900-1999/1989.Maximum%20Number%20of%20People%20That%20Can%20Be%20Caught%20in%20Tag/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你正在和你的朋友玩捉迷藏游戏。在捉迷藏比赛中，人们被分成两组：是 “鬼” 的人，和不是 “鬼” 的人。是 “鬼” 的人想要抓住尽可能多的不是 “鬼” 的人。</p>

<p>给定一个 <strong>从 0 开始建立索引</strong> 的整数数组 <code>team</code>，其中只包含 0 (表示&nbsp;<strong>不是</strong> “鬼” 的人) 和 1 (表示是 “鬼” 的人)，以及一个整数 <code>dist</code>。索引 <code>i</code> 为 “鬼” 的人可以捕获索引在 <code>[i - dist, i + dist]</code>(<strong>包括</strong>) 范围内且 <strong>不是</strong> “鬼” 的任何<strong>一个</strong>人。</p>

<p>返回 <em>“鬼” 所能捕获的最大人数</em>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入:</strong> team = [0,1,0,1,0], dist = 3
<strong>输出:</strong> 2
<strong>解释:</strong>
在索引 1 的 “鬼” 可以捕获范围 [i-dist, i+dist] = [1-3, 1+3] = [-2, 4] 内的人。
他们可以抓住索引 2 中不是 “鬼” 的人。
在索引 3 的 “鬼” 可以捕获范围 [i-dist, i+dist] = [3-3, 3+3] = [0, 6] 内的人。
他们可以抓住索引 0 中不是 “鬼” 的人。
在索引 4 上不是 “鬼” 的人不会被抓住，因为在索引 1 和 3 上的人已经抓住了一个人。</pre>

<p><strong class="example">示例 2:</strong></p>

<pre>
<strong>输入:</strong> team = [1], dist = 1
<strong>输出:</strong> 0
<strong>解释:</strong>
没有 “鬼" 要抓的人。
</pre>

<p><strong class="example">示例 3:</strong></p>

<pre>
<strong>输入:</strong> team = [0], dist = 1
<strong>输出:</strong> 0
<strong>解释:
</strong>没有 “鬼” 来zh
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= team.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= team[i] &lt;= 1</code></li>
	<li><code>1 &lt;= dist &lt;= team.length</code></li>
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
