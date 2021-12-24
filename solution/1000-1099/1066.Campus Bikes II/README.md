# [1066. 校园自行车分配 II](https://leetcode-cn.com/problems/campus-bikes-ii)

[English Version](/solution/1000-1099/1066.Campus%20Bikes%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在由 2D 网格表示的校园里有&nbsp;<code>n</code>&nbsp;位工人（<code>worker</code>）和 <code>m</code>&nbsp;辆自行车（<code>bike</code>），<code>n &lt;= m</code>。所有工人和自行车的位置都用网格上的 2D 坐标表示。</p>

<p>我们为每一位工人分配一辆专属自行车，使每个工人与其分配到的自行车之间的曼哈顿距离最小化。</p>

<p><code>p1</code> 和&nbsp;<code>p2</code>&nbsp;之间的曼哈顿距离为&nbsp;<code>Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|</code>。</p>

<p>返回每个工人与分配到的自行车之间的曼哈顿距离的最小可能总和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1066.Campus%20Bikes%20II/images/1261_example_1_v2.png" style="height: 264px; width: 264px;"></p>

<pre><strong>输入：</strong>workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
<strong>输出：</strong>6
<strong>解释：</strong>
自行车 0 分配给工人 0，自行车 1 分配给工人 1 。分配得到的曼哈顿距离都是 3, 所以输出为 6 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1066.Campus%20Bikes%20II/images/1261_example_2_v2.png" style="height: 264px; width: 264px;"></p>

<pre><strong>输入：</strong>workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
<strong>输出：</strong>4
<strong>解释：</strong>
先将自行车 0 分配给工人 0，再将自行车 1 分配给工人 1（或工人 2），自行车 2 给工人 2（或工人 1）。如此分配使得曼哈顿距离的总和为 4。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>0 &lt;= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] &lt; 1000</code></li>
	<li>所有工人和自行车的位置都不相同。</li>
	<li><code>1 &lt;= workers.length &lt;= bikes.length &lt;= 10</code></li>
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
