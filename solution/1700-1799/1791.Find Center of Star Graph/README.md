# [1791. 找出星型图的中心节点](https://leetcode-cn.com/problems/find-center-of-star-graph)

[English Version](/solution/1700-1799/1791.Find%20Center%20of%20Star%20Graph/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个无向的 <strong>星型</strong> 图，由 <code>n</code> 个编号从 <code>1</code> 到 <code>n</code> 的节点组成。星型图有一个 <strong>中心</strong> 节点，并且恰有 <code>n - 1</code> 条边将中心节点与其他每个节点连接起来。</p>

<p>给你一个二维整数数组 <code>edges</code> ，其中 <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> 表示在节点 <code>u<sub>i</sub></code> 和 <code>v<sub>i</sub></code> 之间存在一条边。请你找出并返回 <code>edges</code> 所表示星型图的中心节点。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1791.Find%20Center%20of%20Star%20Graph/images/star_graph.png" style="width: 331px; height: 321px;" />
<pre>
<strong>输入：</strong>edges = [[1,2],[2,3],[4,2]]
<strong>输出：</strong>2
<strong>解释：</strong>如上图所示，节点 2 与其他每个节点都相连，所以节点 2 是中心节点。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>edges = [[1,2],[5,1],[1,3],[1,4]]
<strong>输出：</strong>1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 <= n <= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>1 <= u<sub>i,</sub> v<sub>i</sub> <= n</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li>题目数据给出的 <code>edges</code> 表示一个有效的星型图</li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findCenter(self, edges: List[List[int]]) -> int:
        return edges[0][0] if edges[0][0]==edges[1][0] or edges[0][0]==edges[1][1] else edges[0][1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
