# [1074. 元素和为目标值的子矩阵数量](https://leetcode.cn/problems/number-of-submatrices-that-sum-to-target)

[English Version](/solution/1000-1099/1074.Number%20of%20Submatrices%20That%20Sum%20to%20Target/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出矩阵 <code>matrix</code> 和目标值 <code>target</code>，返回元素总和等于目标值的非空子矩阵的数量。</p>

<p>子矩阵 <code>x1, y1, x2, y2</code> 是满足 <code>x1 <= x <= x2</code> 且 <code>y1 <= y <= y2</code> 的所有单元 <code>matrix[x][y]</code> 的集合。</p>

<p>如果 <code>(x1, y1, x2, y2)</code> 和 <code>(x1', y1', x2', y2')</code> 两个子矩阵中部分坐标不同（如：<code>x1 != x1'</code>），那么这两个子矩阵也不同。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1074.Number%20of%20Submatrices%20That%20Sum%20to%20Target/images/mate1.jpg" style="width: 242px; height: 242px;" /></p>

<pre>
<strong>输入：</strong>matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
<strong>输出：</strong>4
<strong>解释：</strong>四个只含 0 的 1x1 子矩阵。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[1,-1],[-1,1]], target = 0
<strong>输出：</strong>5
<strong>解释：</strong>两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[904]], target = 0
<strong>输出：</strong>0
</pre>

<p> </p>

<p><strong><strong>提示：</strong></strong></p>

<ul>
	<li><code>1 <= matrix.length <= 100</code></li>
	<li><code>1 <= matrix[0].length <= 100</code></li>
	<li><code>-1000 <= matrix[i] <= 1000</code></li>
	<li><code>-10^8 <= target <= 10^8</code></li>
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
