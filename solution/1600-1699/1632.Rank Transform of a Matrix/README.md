# [1632. 矩阵转换后的秩](https://leetcode.cn/problems/rank-transform-of-a-matrix)

[English Version](/solution/1600-1699/1632.Rank%20Transform%20of%20a%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>m x n</code> 的矩阵 <code>matrix</code> ，请你返回一个新的矩阵<em> </em><code>answer</code> ，其中<em> </em><code>answer[row][col]</code> 是 <code>matrix[row][col]</code> 的秩。</p>

<p>每个元素的 <b>秩</b> 是一个整数，表示这个元素相对于其他元素的大小关系，它按照如下规则计算：</p>

<ul>
	<li>秩是从 1 开始的一个整数。</li>
	<li>如果两个元素 <code>p</code> 和 <code>q</code> 在 <strong>同一行</strong> 或者 <strong>同一列</strong> ，那么：
	<ul>
		<li>如果 <code>p < q</code> ，那么 <code>rank(p) < rank(q)</code></li>
		<li>如果 <code>p == q</code> ，那么 <code>rank(p) == rank(q)</code></li>
		<li>如果 <code>p > q</code> ，那么 <code>rank(p) > rank(q)</code></li>
	</ul>
	</li>
	<li><b>秩</b> 需要越 <strong>小</strong> 越好。</li>
</ul>

<p>题目保证按照上面规则 <code>answer</code> 数组是唯一的。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1632.Rank%20Transform%20of%20a%20Matrix/images/rank1.jpg" style="width: 442px; height: 162px;" />
<pre>
<b>输入：</b>matrix = [[1,2],[3,4]]
<b>输出：</b>[[1,2],[2,3]]
<strong>解释：</strong>
matrix[0][0] 的秩为 1 ，因为它是所在行和列的最小整数。
matrix[0][1] 的秩为 2 ，因为 matrix[0][1] > matrix[0][0] 且 matrix[0][0] 的秩为 1 。
matrix[1][0] 的秩为 2 ，因为 matrix[1][0] > matrix[0][0] 且 matrix[0][0] 的秩为 1 。
matrix[1][1] 的秩为 3 ，因为 matrix[1][1] > matrix[0][1]， matrix[1][1] > matrix[1][0] 且 matrix[0][1] 和 matrix[1][0] 的秩都为 2 。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1632.Rank%20Transform%20of%20a%20Matrix/images/rank2.jpg" style="width: 442px; height: 162px;" />
<pre>
<b>输入：</b>matrix = [[7,7],[7,7]]
<b>输出：</b>[[1,1],[1,1]]
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1632.Rank%20Transform%20of%20a%20Matrix/images/rank3.jpg" style="width: 601px; height: 322px;" />
<pre>
<b>输入：</b>matrix = [[20,-21,14],[-19,4,19],[22,-47,24],[-19,4,19]]
<b>输出：</b>[[4,2,3],[1,3,4],[5,1,6],[1,3,4]]
</pre>

<p><strong>示例 4：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1632.Rank%20Transform%20of%20a%20Matrix/images/rank4.jpg" style="width: 601px; height: 242px;" />
<pre>
<b>输入：</b>matrix = [[7,3,6],[1,4,5],[9,8,2]]
<b>输出：</b>[[5,1,4],[1,2,3],[6,3,1]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 <= m, n <= 500</code></li>
	<li><code>-10<sup>9</sup> <= matrix[row][col] <= 10<sup>9</sup></code></li>
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
