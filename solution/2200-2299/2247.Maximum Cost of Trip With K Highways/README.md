# [2247. K 条高速公路的最大旅行费用](https://leetcode.cn/problems/maximum-cost-of-trip-with-k-highways)

[English Version](/solution/2200-2299/2247.Maximum%20Cost%20of%20Trip%20With%20K%20Highways/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一系列高速公路连接从 <code>0</code> 到 <code>n - 1</code> 的 <code>n</code> 个城市。给定一个二维整数数组 <code>highways</code>，其中 <code>highways[i] = [city1<sub>i</sub>, city2<sub>i</sub>, toll<sub>i</sub>]</code> 表示有一条高速公路连接 <code>city1<sub>i</sub></code> 和<code>city2<sub>i</sub></code>，允许一辆汽车从 <code>city1<sub>i</sub></code> 前往 <code>city2<sub>i</sub></code>，<strong>反之亦然</strong>，费用为 <code>toll<sub>i</sub></code>。</p>

<p>给你一个整数 <code>k</code>，你要<strong>正好</strong>经过 <code>k</code> 条公路。你可以从任何一个城市出发，但在旅途中每个城市<strong>最多</strong>只能访问一次。</p>

<p>返回<em>您旅行的最大费用。如果没有符合要求的行程，则返回 <code>-1</code>。</em></p>

<p><strong class="example">示例 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2247.Maximum%20Cost%20of%20Trip%20With%20K%20Highways/images/image-20220418173304-1.png" style="height: 200px; width: 327px;" />
<pre>
<strong>输入:</strong> n = 5, highways = [[0,1,4],[2,1,3],[1,4,11],[3,2,3],[3,4,2]], k = 3
<strong>输出:</strong> 17
<strong>解释:</strong>
一个可能的路径是从 0 -&gt; 1 -&gt; 4 -&gt; 3。这次旅行的费用是 4 + 11 + 2 = 17。
另一种可能的路径是从 4 -&gt; 1 -&gt; 2 -&gt; 3。这次旅行的费用是 11 + 3 + 3 = 17。
可以证明，17 是任何有效行程的最大可能费用。
注意，旅行 4 -&gt; 1 -&gt; 0 -&gt; 1 是不允许的，因为你访问了城市 1 两次。
</pre>

<p>&nbsp;</p>

<p><strong class="example">示例 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2247.Maximum%20Cost%20of%20Trip%20With%20K%20Highways/images/image-20220418173342-2.png" style="height: 200px; width: 217px;" />
<pre>
<strong>输入:</strong> n = 4, highways = [[0,1,3],[2,3,2]], k = 2
<strong>输出:</strong> -1
<strong>解释:</strong> 没有长度为 2 的有效行程，因此返回-1。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 15</code></li>
	<li><code>1 &lt;= highways.length &lt;= 50</code></li>
	<li><code>highways[i].length == 3</code></li>
	<li><code>0 &lt;= city1<sub>i</sub>, city2<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>city1<sub>i</sub> != city2<sub>i</sub></code></li>
	<li><code>0 &lt;= toll<sub>i</sub> &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 50</code></li>
	<li>
	<p data-group="1-1">没有重复的高速公路。</p>
	</li>
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

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
