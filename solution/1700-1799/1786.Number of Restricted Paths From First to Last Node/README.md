# [1786. 从第一个节点出发到最后一个节点的受限路径数](https://leetcode-cn.com/problems/number-of-restricted-paths-from-first-to-last-node)

[English Version](/solution/1700-1799/1786.Number%20of%20Restricted%20Paths%20From%20First%20to%20Last%20Node/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现有一个加权无向连通图。给你一个正整数 <code>n</code> ，表示图中有 <code>n</code> 个节点，并按从 <code>1</code> 到 <code>n</code> 给节点编号；另给你一个数组 <code>edges</code> ，其中每个 <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, weight<sub>i</sub>]</code> 表示存在一条位于节点 <code>u<sub>i</sub></code> 和 <code>v<sub>i</sub></code> 之间的边，这条边的权重为 <code>weight<sub>i</sub></code> 。</p>

<p>从节点 <code>start</code> 出发到节点 <code>end</code> 的路径是一个形如 <code>[z<sub>0</sub>, z<sub>1</sub>,<sub> </sub>z<sub>2</sub>, ..., z<sub>k</sub>]</code> 的节点序列，满足 <code>z<sub>0 </sub>= start</code> 、<code>z<sub>k</sub> = end</code> 且在所有符合 <code>0 <= i <= k-1</code> 的节点 <code>z<sub>i</sub></code> 和 <code>z<sub>i+1</sub></code> 之间存在一条边。</p>

<p>路径的距离定义为这条路径上所有边的权重总和。用 <code>distanceToLastNode(x)</code> 表示节点 <code>n</code> 和 <code>x</code> 之间路径的最短距离。<strong>受限路径</strong> 为满足 <code>distanceToLastNode(z<sub>i</sub>) > distanceToLastNode(z<sub>i+1</sub>)</code> 的一条路径，其中 <code>0 <= i <= k-1</code> 。</p>

<p>返回从节点 <code>1</code> 出发到节点 <code>n</code> 的 <strong>受限路径数</strong> 。由于数字可能很大，请返回对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 的结果。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1786.Number%20of%20Restricted%20Paths%20From%20First%20to%20Last%20Node/images/restricted_paths_ex1.png" style="width: 351px; height: 341px;" />
<pre>
<strong>输入：</strong>n = 5, edges = [[1,2,3],[1,3,3],[2,3,1],[1,4,2],[5,2,2],[3,5,1],[5,4,10]]
<strong>输出：</strong>3
<strong>解释：</strong>每个圆包含黑色的节点编号和蓝色的 distanceToLastNode 值。三条受限路径分别是：
1) 1 --> 2 --> 5
2) 1 --> 2 --> 3 --> 5
3) 1 --> 3 --> 5
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1786.Number%20of%20Restricted%20Paths%20From%20First%20to%20Last%20Node/images/restricted_paths_ex22.png" style="width: 356px; height: 401px;" />
<pre>
<strong>输入：</strong>n = 7, edges = [[1,3,1],[4,1,2],[7,3,4],[2,5,3],[5,6,1],[6,7,2],[7,5,3],[2,6,4]]
<strong>输出：</strong>1
<strong>解释：</strong>每个圆包含黑色的节点编号和蓝色的 distanceToLastNode 值。唯一一条受限路径是：1 --> 3 --> 7 。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 2 * 10<sup>4</sup></code></li>
	<li><code>n - 1 <= edges.length <= 4 * 10<sup>4</sup></code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>1 <= u<sub>i</sub>, v<sub>i</sub> <= n</code></li>
	<li><code>u<sub>i </sub>!= v<sub>i</sub></code></li>
	<li><code>1 <= weight<sub>i</sub> <= 10<sup>5</sup></code></li>
	<li>任意两个节点之间至多存在一条边</li>
	<li>任意两个节点之间至少存在一条路径</li>
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
