# [1066. 校园自行车分配 II](https://leetcode.cn/problems/campus-bikes-ii)

[English Version](/solution/1000-1099/1066.Campus%20Bikes%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在由 2D 网格表示的校园里有&nbsp;<code>n</code>&nbsp;位工人（<code>worker</code>）和 <code>m</code>&nbsp;辆自行车（<code>bike</code>），<code>n &lt;= m</code>。所有工人和自行车的位置都用网格上的 2D 坐标表示。</p>

<p>我们为每一位工人分配一辆专属自行车，使每个工人与其分配到的自行车之间的 <strong>曼哈顿距离</strong> 最小化。</p>

<p>返回 <code>每个工人与分配到的自行车之间的曼哈顿距离的最小可能总和</code> 。</p>

<p><code>p1</code> 和&nbsp;<code>p2</code>&nbsp;之间的 <strong>曼哈顿距离</strong> 为&nbsp;<code>Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1066.Campus%20Bikes%20II/images/1261_example_1_v2.png" /></p>

<pre>
<strong>输入：</strong>workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
<strong>输出：</strong>6
<strong>解释：</strong>
自行车 0 分配给工人 0，自行车 1 分配给工人 1 。分配得到的曼哈顿距离都是 3, 所以输出为 6 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1066.Campus%20Bikes%20II/images/1261_example_2_v2.png" /></p>

<pre>
<strong>输入：</strong>workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
<strong>输出：</strong>4
<strong>解释：</strong>
先将自行车 0 分配给工人 0，再将自行车 1 分配给工人 1（或工人 2），自行车 2 给工人 2（或工人 1）。如此分配使得曼哈顿距离的总和为 4。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入：</strong>workers = [[0,0],[1,0],[2,0],[3,0],[4,0]], bikes = [[0,999],[1,999],[2,999],[3,999],[4,999]]
<strong>输出：</strong>4995
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == workers.length</code></li>
	<li><code>m == bikes.length</code></li>
	<li><code>1 &lt;= n &lt;= m &lt;= 10</code></li>
	<li><code>workers[i].length == 2</code></li>
	<li><code>bikes[i].length == 2</code></li>
	<li><code>0 &lt;= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] &lt; 1000</code></li>
	<li>所有的工人和自行车的位置都是 <strong>不同</strong>&nbsp;的。</li>
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
