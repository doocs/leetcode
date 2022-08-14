# [834. 树中距离之和](https://leetcode.cn/problems/sum-of-distances-in-tree)

[English Version](/solution/0800-0899/0834.Sum%20of%20Distances%20in%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个无向、连通的树。树中有 <code>n</code> 个标记为 <code>0...n-1</code> 的节点以及 <code>n-1</code>&nbsp;条边&nbsp;。</p>

<p>给定整数 <code>n</code> 和数组&nbsp;<code>edges</code>&nbsp;，&nbsp;<code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>表示树中的节点&nbsp;<code>a<sub>i</sub></code>&nbsp;和&nbsp;<code>b<sub>i</sub></code>&nbsp;之间有一条边。</p>

<p>返回长度为 <code>n</code> 的数组&nbsp;<code>answer</code>&nbsp;，其中&nbsp;<code>answer[i]</code>&nbsp;是树中第 <code>i</code> 个节点与所有其他节点之间的距离之和。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0834.Sum%20of%20Distances%20in%20Tree/images/lc-sumdist1.jpg" /></p>

<pre>
<strong>输入: </strong>n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
<strong>输出: </strong>[8,12,6,10,10,10]
<strong>解释: </strong>树如图所示。
我们可以计算出 dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5) 
也就是 1 + 1 + 2 + 2 + 2 = 8。 因此，answer[0] = 8，以此类推。
</pre>

<p><strong>示例 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0834.Sum%20of%20Distances%20in%20Tree/images/lc-sumdist2.jpg" />
<pre>
<strong>输入:</strong> n = 1, edges = []
<strong>输出:</strong> [0]
</pre>

<p><strong>示例 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0834.Sum%20of%20Distances%20in%20Tree/images/lc-sumdist3.jpg" />
<pre>
<strong>输入:</strong> n = 2, edges = [[1,0]]
<strong>输出:</strong> [1,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub>&nbsp;&lt; n</code></li>
	<li><code>a<sub>i</sub>&nbsp;!= b<sub>i</sub></code></li>
	<li>给定的输入保证为有效的树</li>
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
