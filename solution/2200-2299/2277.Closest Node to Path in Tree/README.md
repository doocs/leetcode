# [2277. 树中最接近路径的节点](https://leetcode.cn/problems/closest-node-to-path-in-tree)

[English Version](/solution/2200-2299/2277.Closest%20Node%20to%20Path%20in%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个正整数 <code>n</code>，表示树中的节点数，编号从 <code>0</code> 到 <code>n - 1</code> (<strong>含边界</strong>)。还给定一个长度为 <code>n - 1</code> 的二维整数数组 <code>edges</code>，其中&nbsp;<code>edges[i] = [node1<sub>i</sub>, node2<sub>i</sub>]</code> 表示有一条&nbsp;<strong>双向&nbsp;</strong>边连接树中的 <code>node1<sub>i</sub></code> 和 <code>node2<sub>i</sub></code>。</p>

<p>给定一个长度为 <code>m</code>&nbsp;，<strong>下标从 0 开始</strong>&nbsp;的整数数组 <code>query</code>，其中 <code>query[i] = [start<sub>i</sub>, end<sub>i</sub>, node<sub>i</sub>]</code>&nbsp;意味着对于第 <code>i</code> 个查询，您的任务是从 <code>start<sub>i</sub></code> 到 <code>end<sub>i</sub></code> 的路径上找到&nbsp;<strong>最接近</strong> <code>node<sub>i</sub></code><sub>&nbsp;</sub>的节点。</p>

<p>返回<em>长度为 <code>m</code> 的整数数组 </em><code>answer</code><em>，其中 </em><code>answer[i]</code>&nbsp;<em>是第 <code>i</code> 个查询的答案。</em></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2277.Closest%20Node%20to%20Path%20in%20Tree/images/image-20220514132158-1.png" style="width: 300px; height: 211px;" />
<pre>
<strong>输入:</strong> n = 7, edges = [[0,1],[0,2],[0,3],[1,4],[2,5],[2,6]], query = [[5,3,4],[5,3,6]]
<strong>输出:</strong> [0,2]
<strong>解释:</strong>
节点 5 到节点 3 的路径由节点 5、2、0、3 组成。
节点 4 到节点 0 的距离为 2。
节点 0 是距离节点 4 最近的路径上的节点，因此第一个查询的答案是 0。
节点 6 到节点 2 的距离为 1。
节点 2 是距离节点 6 最近的路径上的节点，因此第二个查询的答案是 2。
</pre>

<p><strong class="example">示例 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2277.Closest%20Node%20to%20Path%20in%20Tree/images/image-20220514132318-2.png" style="width: 300px; height: 89px;" />
<pre>
<strong>输入:</strong> n = 3, edges = [[0,1],[1,2]], query = [[0,1,2]]
<strong>输出:</strong> [1]
<strong>解释:</strong>
从节点 0 到节点 1 的路径由节点 0,1 组成。
节点 2 到节点 1 的距离为 1。
节点 1 是距离节点 2 最近的路径上的节点，因此第一个查询的答案是 1。
</pre>

<p><strong class="example">示例 3:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2277.Closest%20Node%20to%20Path%20in%20Tree/images/image-20220514132333-3.png" style="width: 300px; height: 89px;" />
<pre>
<strong>输入:</strong> n = 3, edges = [[0,1],[1,2]], query = [[0,0,0]]
<strong>输出:</strong> [0]
<strong>解释:</strong>
节点 0 到节点 0 的路径由节点 0 组成。
因为 0 是路径上唯一的节点，所以第一个查询的答案是0。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= node1<sub>i</sub>, node2<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>node1<sub>i</sub> != node2<sub>i</sub></code></li>
	<li><code>1 &lt;= query.length &lt;= 1000</code></li>
	<li><code>query[i].length == 3</code></li>
	<li><code>0 &lt;= start<sub>i</sub>, end<sub>i</sub>, node<sub>i</sub> &lt;= n - 1</code></li>
	<li>
	<p data-group="1-1">这个图是一个树。</p>
	</li>
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
