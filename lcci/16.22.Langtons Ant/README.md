# [面试题 16.22. 兰顿蚂蚁](https://leetcode.cn/problems/langtons-ant-lcci)

[English Version](/lcci/16.22.Langtons%20Ant/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>一只蚂蚁坐在由白色和黑色方格构成的无限网格上。开始时，网格全白，蚂蚁面向右侧。每行走一步，蚂蚁执行以下操作。</p>

<p>(1) 如果在白色方格上，则翻转方格的颜色，向右(顺时针)转 90 度，并向前移动一个单位。<br>
(2) 如果在黑色方格上，则翻转方格的颜色，向左(逆时针方向)转 90 度，并向前移动一个单位。</p>

<p>编写程序来模拟蚂蚁执行的前 K 个动作，并返回最终的网格。</p>

<p>网格由数组表示，每个元素是一个字符串，代表网格中的一行，黑色方格由&nbsp;<code>&#39;X&#39;</code>&nbsp;表示，白色方格由&nbsp;<code>&#39;_&#39;</code>&nbsp;表示，蚂蚁所在的位置由&nbsp;<code>&#39;L&#39;</code>, <code>&#39;U&#39;</code>, <code>&#39;R&#39;</code>, <code>&#39;D&#39;</code>&nbsp;表示，分别表示蚂蚁&nbsp;左、上、右、下 的朝向。只需要返回能够包含蚂蚁走过的所有方格的最小矩形。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> 0
<strong>输出: </strong>[&quot;R&quot;]
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> 2
<strong>输出:
</strong>[
&nbsp; &quot;_X&quot;,
&nbsp; &quot;LX&quot;
]
</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入:</strong> 5
<strong>输出:
</strong>[
&nbsp; &quot;_U&quot;,
&nbsp; &quot;X_&quot;,
&nbsp; &quot;XX&quot;
]
</pre>

<p><strong>说明：</strong></p>

<ul>
	<li><code>K &lt;= 100000</code></li>
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
