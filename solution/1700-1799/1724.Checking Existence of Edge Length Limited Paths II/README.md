# [1724. 检查边长度限制的路径是否存在 II](https://leetcode.cn/problems/checking-existence-of-edge-length-limited-paths-ii)

[English Version](/solution/1700-1799/1724.Checking%20Existence%20of%20Edge%20Length%20Limited%20Paths%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一张有&nbsp;<code>n</code>&nbsp;个节点的无向图以边的列表&nbsp;<code>edgeList</code>&nbsp;的形式定义，其中&nbsp;<code>edgeList[i] = [u<sub>i</sub>, v<sub>i</sub>, dis<sub>i</sub>]</code>&nbsp;表示一条连接&nbsp;<code>u<sub>i</sub></code>&nbsp;和&nbsp;<code>v<sub>i</sub></code>&nbsp;，距离为&nbsp;<code>dis<sub>i</sub></code>&nbsp;的边。注意，同一对节点间可能有<strong>多条</strong>边，且该图可能不是连通的。</p>

<p>实现&nbsp;<code>DistanceLimitedPathsExist</code>&nbsp;类：</p>

<ul>
	<li><code>DistanceLimitedPathsExist(int n, int[][] edgeList)</code>&nbsp;以给定的无向图初始化对象。</li>
	<li><code>boolean query(int p, int q, int limit)</code>&nbsp;当存在一条从&nbsp;<code>p</code>&nbsp;到 <code>q</code> 的路径，且路径中每条边的距离都<strong>严格小于</strong> <code>limit</code> 时，返回 <code>true</code> ，否则返回 <code>false</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><b>示例 1:</b></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1724.Checking%20Existence%20of%20Edge%20Length%20Limited%20Paths%20II/images/1693449815-oSOAxI-%E6%88%AA%E5%B1%8F2023-08-31%2010.43.30.png){:width=400}" style="width: 400px;" /><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1724.Checking%20Existence%20of%20Edge%20Length%20Limited%20Paths%20II/images/1693449815-oSOAxI-%E6%88%AA%E5%B1%8F2023-08-31%2010.43.30.png" style="width: 400px; height: 352px;" /></p>

<pre>
<b>输入：</b>
["DistanceLimitedPathsExist", "query", "query", "query", "query"]
[[6, [[0, 2, 4], [0, 3, 2], [1, 2, 3], [2, 3, 1], [4, 5, 5]]], [2, 3, 2], [1, 3, 3], [2, 0, 3], [0, 5, 6]]
<b>输出：</b>
[null, true, false, true, false]

<b>解释：</b>
DistanceLimitedPathsExist distanceLimitedPathsExist = new DistanceLimitedPathsExist(6, [[0, 2, 4], [0, 3, 2], [1, 2, 3], [2, 3, 1], [4, 5, 5]]);
distanceLimitedPathsExist.query(2, 3, 2); // 返回 true。存在一条从 2 到 3 ，距离为 1 的边，
&nbsp;                                         // 这条边的距离小于 2。
distanceLimitedPathsExist.query(1, 3, 3); // 返回 false。从 1 到 3 之间不存在每条边的距离都
                                          // <strong>严格</strong>小于 3 的路径。
distanceLimitedPathsExist.query(2, 0, 3); // 返回 true。存在一条从 2 到 0 的路径，使得每条边的
                                          // 距离 &lt; 3：从 2 到 3 到 0 行进即可。
distanceLimitedPathsExist.query(0, 5, 6); // 返回 false。从 0 到 5 之间不存在路径。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= edgeList.length &lt;= 10<sup>4</sup></code></li>
	<li><code>edgeList[i].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub>, p, q &lt;= n-1</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>p != q</code></li>
	<li><code>1 &lt;= dis<sub>i</sub>, limit &lt;= 10<sup>9</sup></code></li>
	<li>最多调用&nbsp;<code>10<sup>4</sup></code>&nbsp;次&nbsp;<code>query</code>&nbsp;。</li>
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
