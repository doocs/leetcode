# [864. 获取所有钥匙的最短路径](https://leetcode-cn.com/problems/shortest-path-to-get-all-keys)

[English Version](/solution/0800-0899/0864.Shortest%20Path%20to%20Get%20All%20Keys/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二维网格&nbsp;<code>grid</code>。&nbsp;<code>&quot;.&quot;</code>&nbsp;代表一个空房间，&nbsp;<code>&quot;#&quot;</code>&nbsp;代表一堵墙，&nbsp;<code>&quot;@&quot;</code>&nbsp;是起点，（<code>&quot;a&quot;</code>,&nbsp;<code>&quot;b&quot;</code>, ...）代表钥匙，（<code>&quot;A&quot;</code>,&nbsp;<code>&quot;B&quot;</code>, ...）代表锁。</p>

<p>我们从起点开始出发，一次移动是指向四个基本方向之一行走一个单位空间。我们不能在网格外面行走，也无法穿过一堵墙。如果途经一个钥匙，我们就把它捡起来。除非我们手里有对应的钥匙，否则无法通过锁。</p>

<p>假设 K 为钥匙/锁的个数，且满足&nbsp;<code>1 &lt;= K &lt;= 6</code>，字母表中的前 K 个字母在网格中都有自己对应的一个小写和一个大写字母。换言之，每个锁有唯一对应的钥匙，每个钥匙也有唯一对应的锁。另外，代表钥匙和锁的字母互为大小写并按字母顺序排列。</p>

<p>返回获取所有钥匙所需要的移动的最少次数。如果无法获取所有钥匙，返回&nbsp;<code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[&quot;@.a.#&quot;,&quot;###.#&quot;,&quot;b.A.B&quot;]
<strong>输出：</strong>8
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[&quot;@..aA&quot;,&quot;..B#.&quot;,&quot;....b&quot;]
<strong>输出：</strong>6
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= grid.length&nbsp;&lt;= 30</code></li>
	<li><code>1 &lt;= grid[0].length&nbsp;&lt;= 30</code></li>
	<li><code>grid[i][j]</code>&nbsp;只含有&nbsp;<code>&#39;.&#39;</code>,&nbsp;<code>&#39;#&#39;</code>,&nbsp;<code>&#39;@&#39;</code>,&nbsp;<code>&#39;a&#39;-</code><code>&#39;f</code><code>&#39;</code>&nbsp;以及&nbsp;<code>&#39;A&#39;-&#39;F&#39;</code></li>
	<li>钥匙的数目范围是&nbsp;<code>[1, 6]</code>，每个钥匙都对应一个不同的字母，正好打开一个对应的锁。</li>
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
