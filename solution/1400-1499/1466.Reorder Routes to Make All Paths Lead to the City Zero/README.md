# [1466. 重新规划路线](https://leetcode-cn.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero)

[English Version](/solution/1400-1499/1466.Reorder%20Routes%20to%20Make%20All%20Paths%20Lead%20to%20the%20City%20Zero/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><code>n</code> 座城市，从 <code>0</code> 到 <code>n-1</code> 编号，其间共有 <code>n-1</code> 条路线。因此，要想在两座不同城市之间旅行只有唯一一条路线可供选择（路线网形成一颗树）。去年，交通运输部决定重新规划路线，以改变交通拥堵的状况。</p>

<p>路线用 <code>connections</code> 表示，其中 <code>connections[i] = [a, b]</code> 表示从城市 <code>a</code> 到 <code>b</code> 的一条有向路线。</p>

<p>今年，城市 0 将会举办一场大型比赛，很多游客都想前往城市 0 。</p>

<p>请你帮助重新规划路线方向，使每个城市都可以访问城市 0 。返回需要变更方向的最小路线数。</p>

<p>题目数据 <strong>保证</strong> 每个城市在重新规划路线方向后都能到达城市 0 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1466.Reorder%20Routes%20to%20Make%20All%20Paths%20Lead%20to%20the%20City%20Zero/images/sample_1_1819.png" style="height: 150px; width: 240px;"></strong></p>

<pre><strong>输入：</strong>n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
<strong>输出：</strong>3
<strong>解释：</strong>更改以红色显示的路线的方向，使每个城市都可以到达城市 0 。</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1466.Reorder%20Routes%20to%20Make%20All%20Paths%20Lead%20to%20the%20City%20Zero/images/sample_2_1819.png" style="height: 60px; width: 380px;"></strong></p>

<pre><strong>输入：</strong>n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
<strong>输出：</strong>2
<strong>解释：</strong>更改以红色显示的路线的方向，使每个城市都可以到达城市 0 。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 3, connections = [[1,0],[2,0]]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 5 * 10^4</code></li>
	<li><code>connections.length == n-1</code></li>
	<li><code>connections[i].length == 2</code></li>
	<li><code>0 &lt;= connections[i][0], connections[i][1] &lt;= n-1</code></li>
	<li><code>connections[i][0] != connections[i][1]</code></li>
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
