# [931. 下降路径最小和](https://leetcode-cn.com/problems/minimum-falling-path-sum)

[English Version](/solution/0900-0999/0931.Minimum%20Falling%20Path%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>n x n</code> 的<strong> 方形 </strong>整数数组 <code>matrix</code> ，请你找出并返回通过 <code>matrix</code> 的<strong>下降路径</strong><em> </em>的<strong> </strong><strong>最小和</strong> 。</p>

<p><strong>下降路径</strong> 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。具体来说，位置 <code>(row, col)</code> 的下一个元素应当是 <code>(row + 1, col - 1)</code>、<code>(row + 1, col)</code> 或者 <code>(row + 1, col + 1)</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[2,1,3],[6,5,4],[7,8,9]]
<strong>输出：</strong>13
<strong>解释：</strong>下面是两条和最小的下降路径，用加粗标注：
[[2,<strong>1</strong>,3],      [[2,<strong>1</strong>,3],
 [6,<strong>5</strong>,4],       [6,5,<strong>4</strong>],
 [<strong>7</strong>,8,9]]       [7,<strong>8</strong>,9]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[-19,57],[-40,-5]]
<strong>输出：</strong>-59
<strong>解释：</strong>下面是一条和最小的下降路径，用加粗标注：
[[<strong>-19</strong>,57],
 [<strong>-40</strong>,-5]]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[-48]]
<strong>输出：</strong>-48
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 <= n <= 100</code></li>
	<li><code>-100 <= matrix[i][j] <= 100</code></li>
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
