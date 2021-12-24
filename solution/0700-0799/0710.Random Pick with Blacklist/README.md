# [710. 黑名单中的随机数](https://leetcode-cn.com/problems/random-pick-with-blacklist)

[English Version](/solution/0700-0799/0710.Random%20Pick%20with%20Blacklist/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个包含 [0，n ) 中独特的整数的黑名单 B，写一个函数从 [ 0，n ) 中返回一个<strong>不在</strong> B 中的随机整数。</p>

<p>对它进行优化使其尽量少调用系统方法 <code>Math.random()</code> 。</p>

<p><strong>提示:</strong></p>

<ol>
	<li><code>1 &lt;= N &lt;= 1000000000</code></li>
	<li><code>0 &lt;= B.length &lt; min(100000, N)</code></li>
	<li><code>[0, N)</code>&nbsp;不包含&nbsp;N，详细参见&nbsp;<a href="https://en.wikipedia.org/wiki/Interval_(mathematics)" target="_blank">interval notation</a>&nbsp;。</li>
</ol>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: 
</strong>[&quot;Solution&quot;,&quot;pick&quot;,&quot;pick&quot;,&quot;pick&quot;]
[[1,[]],[],[],[]]
<strong>输出: </strong>[null,0,0,0]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: 
</strong>[&quot;Solution&quot;,&quot;pick&quot;,&quot;pick&quot;,&quot;pick&quot;]
[[2,[]],[],[],[]]
<strong>输出: </strong>[null,1,1,1]
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入: 
</strong>[&quot;Solution&quot;,&quot;pick&quot;,&quot;pick&quot;,&quot;pick&quot;]
[[3,[1]],[],[],[]]
<strong>Output: </strong>[null,0,0,2]
</pre>

<p><strong>示例 4:</strong></p>

<pre>
<strong>输入: 
</strong>[&quot;Solution&quot;,&quot;pick&quot;,&quot;pick&quot;,&quot;pick&quot;]
[[4,[2]],[],[],[]]
<strong>输出: </strong>[null,1,3,1]
</pre>

<p><strong>输入语法说明：</strong></p>

<p>输入是两个列表：调用成员函数名和调用的参数。<code>Solution</code>的构造函数有两个参数，<code>N</code>&nbsp;和黑名单&nbsp;<code>B</code>。<code>pick</code>&nbsp;没有参数，输入参数是一个列表，即使参数为空，也会输入一个 [] 空列表。</p>

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
