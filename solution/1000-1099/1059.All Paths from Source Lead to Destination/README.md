# [1059. 从始点到终点的所有路径](https://leetcode-cn.com/problems/all-paths-from-source-lead-to-destination)

[English Version](/solution/1000-1099/1059.All%20Paths%20from%20Source%20Lead%20to%20Destination/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定有向图的边&nbsp;<code>edges</code>，以及该图的始点&nbsp;<code>source</code>&nbsp;和目标终点&nbsp;<code>destination</code>，确定从始点&nbsp;<code>source</code>&nbsp;出发的所有路径是否最终结束于目标终点&nbsp;<code>destination</code>，即：</p>

<ul>
	<li>从始点&nbsp;<code>source</code> 到目标终点&nbsp;<code>destination</code> 存在至少一条路径</li>
	<li>如果存在从始点&nbsp;<code>source</code> 到没有出边的节点的路径，则该节点就是路径终点。</li>
	<li>从始点<code>source</code>到目标终点&nbsp;<code>destination</code> 可能路径数是有限数字</li>
</ul>

<p>当从始点&nbsp;<code>source</code> 出发的所有路径都可以到达目标终点&nbsp;<code>destination</code> 时返回&nbsp;<code>true</code>，否则返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1059.All%20Paths%20from%20Source%20Lead%20to%20Destination/images/485_example_1.png" style="height: 208px; width: 200px;"></p>

<pre><strong>输入：</strong>n = 3, edges = [[0,1],[0,2]], source = 0, destination = 2
<strong>输出：</strong>false
<strong>说明：</strong>节点 1 和节点 2 都可以到达，但也会卡在那里。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1059.All%20Paths%20from%20Source%20Lead%20to%20Destination/images/485_example_2.png" style="height: 230px; width: 200px;"></p>

<pre><strong>输入：</strong>n = 4, edges = [[0,1],[0,3],[1,2],[2,1]], source = 0, destination = 3
<strong>输出：</strong>false
<strong>说明：</strong>有两种可能：在节点 3 处结束，或是在节点 1 和节点 2 之间无限循环。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1059.All%20Paths%20from%20Source%20Lead%20to%20Destination/images/485_example_3.png" style="height: 183px; width: 200px;"></p>

<pre><strong>输入：</strong>n = 4, edges = [[0,1],[0,2],[1,3],[2,3]], source = 0, destination = 3
<strong>输出：</strong>true
</pre>

<p><strong>示例 4：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1059.All%20Paths%20from%20Source%20Lead%20to%20Destination/images/485_example_4.png" style="height: 183px; width: 200px;"></p>

<pre><strong>输入：</strong>n = 3, edges = [[0,1],[1,1],[1,2]], source = 0, destination = 2
<strong>输出：</strong>false
<strong>说明：</strong>从始点出发的所有路径都在目标终点结束，但存在无限多的路径，如 0-1-2，0-1-1-2，0-1-1-1-2，0-1-1-1-1-2 等。
</pre>

<p><strong>示例 5：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1059.All%20Paths%20from%20Source%20Lead%20to%20Destination/images/485_example_5.png" style="height: 131px; width: 150px;"></p>

<pre><strong>输入：</strong>n = 2, edges = [[0,1],[1,1]], source = 0, destination = 1
<strong>输出：</strong>false
<strong>说明：</strong>在目标节点上存在无限的自环。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li>给定的图中可能带有自环和平行边。</li>
	<li>图中的节点数&nbsp;<code>n</code>&nbsp;介于&nbsp;<code>1</code> 和&nbsp;<code>10000</code>&nbsp;之间。</li>
	<li>图中的边数在&nbsp;<code>0</code> 到&nbsp;<code>10000</code>&nbsp;之间。</li>
	<li><code>0 &lt;= edges.length &lt;= 10000</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= source &lt;= n - 1</code></li>
	<li><code>0 &lt;= destination &lt;= n - 1</code></li>
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
