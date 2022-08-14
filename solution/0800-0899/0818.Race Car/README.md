# [818. 赛车](https://leetcode.cn/problems/race-car)

[English Version](/solution/0800-0899/0818.Race%20Car/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

你的赛车可以从位置 <code>0</code> 开始，并且速度为 <code>+1</code> ，在一条无限长的数轴上行驶。赛车也可以向负方向行驶。赛车可以按照由加速指令 <code>'A'</code> 和倒车指令 <code>'R'</code> 组成的指令序列自动行驶。

<ul>
	<li>当收到指令 <code>'A'</code> 时，赛车这样行驶：
	<ul>
		<li><code>position += speed</code></li>
		<li><code>speed *= 2</code></li>
	</ul>
	</li>
	<li>当收到指令 <code>'R'</code> 时，赛车这样行驶：
	<ul>
		<li>如果速度为正数，那么<code>speed = -1</code></li>
		<li>否则 <code>speed = 1</code></li>
	</ul>
	当前所处位置不变。</li>
</ul>

<p>例如，在执行指令 <code>"AAR"</code> 后，赛车位置变化为 <code>0 --&gt; 1 --&gt; 3 --&gt; 3</code> ，速度变化为 <code>1 --&gt; 2 --&gt; 4 --&gt; -1</code> 。</p>

<p>给你一个目标位置 <code>target</code> ，返回能到达目标位置的最短指令序列的长度。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>target = 3
<strong>输出：</strong>2
<strong>解释：</strong>
最短指令序列是 "AA" 。
位置变化 0 --&gt; 1 --&gt; 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>target = 6
<strong>输出：</strong>5
<strong>解释：</strong>
最短指令序列是 "AAARA" 。
位置变化 0 --&gt; 1 --&gt; 3 --&gt; 7 --&gt; 7 --&gt; 6 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= target &lt;= 10<sup>4</sup></code></li>
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
