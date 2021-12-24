# [787. K 站中转内最便宜的航班](https://leetcode-cn.com/problems/cheapest-flights-within-k-stops)

[English Version](/solution/0700-0799/0787.Cheapest%20Flights%20Within%20K%20Stops/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有 <code>n</code> 个城市通过 <code>m</code> 个航班连接。每个航班都从城市 <code>u</code> 开始，以价格 <code>w</code> 抵达 <code>v</code>。</p>

<p>现在给定所有的城市和航班，以及出发城市 <code>src</code> 和目的地 <code>dst</code>，你的任务是找到从 <code>src</code> 到 <code>dst</code> 最多经过 <code>k</code> 站中转的最便宜的价格。 如果没有这样的路线，则输出 <code>-1</code>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
<strong>输出:</strong> 200
<strong>解释:</strong> 
城市航班图如下
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0787.Cheapest%20Flights%20Within%20K%20Stops/images/995.png" style="height: 180px; width: 246px;" />

从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入:</strong> 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
<strong>输出:</strong> 500
<strong>解释:</strong> 
城市航班图如下
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0787.Cheapest%20Flights%20Within%20K%20Stops/images/995.png" style="height: 180px; width: 246px;" />

从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n</code> 范围是 <code>[1, 100]</code>，城市标签从 <code>0</code> 到 <code>n</code><code> - 1</code></li>
	<li>航班数量范围是 <code>[0, n * (n - 1) / 2]</code></li>
	<li>每个航班的格式 <code>(src, </code><code>dst</code><code>, price)</code></li>
	<li>每个航班的价格范围是 <code>[1, 10000]</code></li>
	<li><code>k</code> 范围是 <code>[0, n - 1]</code></li>
	<li>航班没有重复，且不存在自环</li>
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
