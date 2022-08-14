# [1782. 统计点对的数目](https://leetcode.cn/problems/count-pairs-of-nodes)

[English Version](/solution/1700-1799/1782.Count%20Pairs%20Of%20Nodes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个无向图，无向图由整数 <code>n</code>  ，表示图中节点的数目，和 <code>edges</code> 组成，其中 <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> 表示 <code>u<sub>i</sub></code> 和 <code>v<sub>i</sub></code><sub> </sub>之间有一条无向边。同时给你一个代表查询的整数数组 <code>queries</code> 。</p>

<p>第 <code>j</code> 个查询的答案是满足如下条件的点对 <code>(a, b)</code> 的数目：</p>

<ul>
	<li><code>a < b</code></li>
	<li><code>cnt</code> 是与 <code>a</code> <strong>或者 </strong><code>b</code> 相连的边的数目，且 <code>cnt</code> <strong>严格大于 </strong><code>queries[j]</code> 。</li>
</ul>

<p>请你返回一个数组 <code>answers</code> ，其中 <code>answers.length == queries.length</code> 且 <code>answers[j]</code> 是第 <code>j</code> 个查询的答案。</p>

<p>请注意，图中可能会有 <strong>重复边</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1782.Count%20Pairs%20Of%20Nodes/images/1614828447-GMnLVg-image.png" style="width: 310px; height: 278px;" />
<pre>
<b>输入：</b>n = 4, edges = [[1,2],[2,4],[1,3],[2,3],[2,1]], queries = [2,3]
<b>输出：</b>[6,5]
<b>解释：</b>每个点对中，与至少一个点相连的边的数目如上图所示。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>n = 5, edges = [[1,5],[1,5],[3,4],[2,5],[1,3],[5,1],[2,3],[2,5]], queries = [1,2,3,4,5]
<b>输出：</b>[10,10,9,8,6]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= n <= 2 * 10<sup>4</sup></code></li>
	<li><code>1 <= edges.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= u<sub>i</sub>, v<sub>i</sub> <= n</code></li>
	<li><code>u<sub>i </sub>!= v<sub>i</sub></code></li>
	<li><code>1 <= queries.length <= 20</code></li>
	<li><code>0 <= queries[j] < edges.length</code></li>
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
