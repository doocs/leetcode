# [1057. 校园自行车分配](https://leetcode-cn.com/problems/campus-bikes)

[English Version](/solution/1000-1099/1057.Campus%20Bikes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在由 2D 网格表示的校园里有&nbsp;<code>n</code>&nbsp;位工人（<code>worker</code>）和 <code>m</code>&nbsp;辆自行车（<code>bike</code>），<code>n &lt;= m</code>。所有工人和自行车的位置都用网格上的 2D 坐标表示。</p>

<p>我们需要为每位工人分配一辆自行车。在所有可用的自行车和工人中，我们选取彼此之间曼哈顿距离最短的工人自行车对&nbsp; (worker, bike) ，并将其中的自行车分配給工人。如果有多个 (worker, bike) 对之间的曼哈顿距离相同，那么我们选择工人索引最小的那对。类似地，如果有多种不同的分配方法，则选择自行车索引最小的一对。不断重复这一过程，直到所有工人都分配到自行车为止。</p>

<p>给定两点&nbsp;<code>p1</code>&nbsp;和&nbsp;<code>p2</code>&nbsp;之间的曼哈顿距离为&nbsp;<code>Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|</code>。</p>

<p>返回长度为 <code>n</code> 的向量 <code>ans</code>，其中 <code>a[i]</code>&nbsp;是第 <code>i</code>&nbsp;位工人分配到的自行车的索引（从 0 开始）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1057.Campus%20Bikes/images/1261_example_1_v2.png" style="height: 264px; width: 264px;"></p>

<pre><strong>输入：</strong>workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
<strong>输出：</strong>[1,0]
<strong>解释：</strong>
工人 1 分配到自行车 0，因为他们最接近且不存在冲突，工人 0 分配到自行车 1 。所以输出是 [1,0]。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1057.Campus%20Bikes/images/1261_example_2_v2.png" style="height: 264px; width: 264px;"></p>

<pre><strong>输入：</strong>workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
<strong>输出：</strong>[0,2,1]
<strong>解释：</strong>
工人 0 首先分配到自行车 0 。工人 1 和工人 2 与自行车 2 距离相同，因此工人 1 分配到自行车 2，工人 2 将分配到自行车 1 。因此输出为 [0,2,1]。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>0 &lt;= workers[i][j], bikes[i][j] &lt; 1000</code></li>
	<li>所有工人和自行车的位置都不相同。</li>
	<li><code>1 &lt;= workers.length &lt;= bikes.length &lt;= 1000</code></li>
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
