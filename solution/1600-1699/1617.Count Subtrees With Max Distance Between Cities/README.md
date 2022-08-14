# [1617. 统计子树中城市之间最大距离](https://leetcode.cn/problems/count-subtrees-with-max-distance-between-cities)

[English Version](/solution/1600-1699/1617.Count%20Subtrees%20With%20Max%20Distance%20Between%20Cities/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你 <code>n</code> 个城市，编号为从 <code>1</code> 到 <code>n</code> 。同时给你一个大小为 <code>n-1</code> 的数组 <code>edges</code> ，其中 <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> 表示城市 <code>u<sub>i</sub></code> 和 <code>v<sub>i</sub></code><sub> </sub>之间有一条双向边。题目保证任意城市之间只有唯一的一条路径。换句话说，所有城市形成了一棵 <strong>树</strong> 。</p>

<p>一棵 <strong>子树</strong> 是城市的一个子集，且子集中任意城市之间可以通过子集中的其他城市和边到达。两个子树被认为不一样的条件是至少有一个城市在其中一棵子树中存在，但在另一棵子树中不存在。</p>

<p>对于 <code>d</code> 从 <code>1</code> 到 <code>n-1</code> ，请你找到城市间 <strong>最大距离</strong> 恰好为 <code>d</code> 的所有子树数目。</p>

<p>请你返回一个大小为 <code>n-1</code> 的数组，其中第<em> </em><code>d</code><em> </em>个元素（<strong>下标从 1 开始</strong>）是城市间 <strong>最大距离</strong> 恰好等于 <code>d</code> 的子树数目。</p>

<p><strong>请注意</strong>，两个城市间距离定义为它们之间需要经过的边的数目。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1617.Count%20Subtrees%20With%20Max%20Distance%20Between%20Cities/images/p1.png" style="width: 161px; height: 181px;" /></strong></p>

<pre>
<b>输入：</b>n = 4, edges = [[1,2],[2,3],[2,4]]
<b>输出：</b>[3,4,0]
<strong>解释：
</strong>子树 {1,2}, {2,3} 和 {2,4} 最大距离都是 1 。
子树 {1,2,3}, {1,2,4}, {2,3,4} 和 {1,2,3,4} 最大距离都为 2 。
不存在城市间最大距离为 3 的子树。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>n = 2, edges = [[1,2]]
<b>输出：</b>[1]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>n = 3, edges = [[1,2],[2,3]]
<b>输出：</b>[2,1]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= n <= 15</code></li>
	<li><code>edges.length == n-1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>1 <= u<sub>i</sub>, v<sub>i</sub> <= n</code></li>
	<li>题目保证 <code>(u<sub>i</sub>, v<sub>i</sub>)</code> 所表示的边互不相同。</li>
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
