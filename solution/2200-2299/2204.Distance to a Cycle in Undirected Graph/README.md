# [2204. 无向图中到环的距离](https://leetcode.cn/problems/distance-to-a-cycle-in-undirected-graph)

[English Version](/solution/2200-2299/2204.Distance%20to%20a%20Cycle%20in%20Undirected%20Graph/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个正整数 <code>n</code>，表示一个 <strong>连通无向图</strong> 中的节点数，该图&nbsp;<strong>只包含一个&nbsp;</strong>环。节点编号为 <code>0</code> ~ <code>n - 1</code>(<strong>含</strong>)。</p>

<p>你还得到了一个二维整数数组 <code>edges</code>，其中 <code>edges[i] = [node1<sub>i</sub>, node2<sub>i</sub>]</code> 表示有一条&nbsp;<strong>双向&nbsp;</strong>边连接图中的 <code>node1<sub>i</sub></code> 和 <code>node2<sub>i</sub></code>。</p>

<p>两个节点 <code>a</code> 和 <code>b</code> 之间的距离定义为从 <code>a</code> 到 <code>b</code> 所需的&nbsp;<strong>最小边数</strong>。</p>

<p>返回<em>一个长度为 <code>n</code> 的整数数组 <code>answer</code>，其中 </em><code>answer[i]</code><em> 是第 <code>i</code> 个节点与环中任何节点之间的最小距离</em>。</p>

<p><strong class="example">示例 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2204.Distance%20to%20a%20Cycle%20in%20Undirected%20Graph/images/image-20220315154238-1.png" style="width: 350px; height: 237px;" />
<pre>
<strong>输入:</strong> n = 7, edges = [[1,2],[2,4],[4,3],[3,1],[0,1],[5,2],[6,5]]
<strong>输出:</strong> [1,0,0,0,0,1,2]
<strong>解释:</strong>
节点 1, 2, 3, 和 4 来自环。
0 到 1 的距离是 1。
1 到 1 的距离是 0。
2 到 2 的距离是 0。
3 到 3 的距离是 0。
4 到 4 的距离是 0。
5 到 2 的距离是 1。
6 到 2 的距离是 2。
</pre>

<p><strong class="example">示例 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2204.Distance%20to%20a%20Cycle%20in%20Undirected%20Graph/images/image-20220315154634-1.png" style="width: 400px; height: 297px;" />
<pre>
<strong>输入:</strong> n = 9, edges = [[0,1],[1,2],[0,2],[2,6],[6,7],[6,8],[0,3],[3,4],[3,5]]
<strong>输出:</strong> [0,0,0,1,2,2,1,2,2]
<strong>解释:</strong>
节点 0, 1, 和 2 来自环.
0 到 0 的距离是 0。
1 到 1 的距离是 0。
2 到 2 的距离是 0。
3 到 1 的距离是 1。
4 到 1 的距离是 2。
5 到 1 的距离是 2。
6 到 2 的距离是 1。
7 到 2 的距离是 2。
8 到 2 的距离是 2。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= node1<sub>i</sub>, node2<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>node1<sub>i</sub> != node2<sub>i</sub></code></li>
	<li>图是连通的。</li>
	<li>这个图只有一个环。</li>
	<li>任何顶点对之间最多只有一条边。</li>
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
