# [2906. 构造乘积矩阵](https://leetcode.cn/problems/construct-product-matrix)

[English Version](/solution/2900-2999/2906.Construct%20Product%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始、大小为 <code>n * m</code> 的二维整数矩阵 <code><font face="monospace">grid</font></code><font face="monospace"> ，定义一个下标从 <strong>0</strong> 开始、大小为 <code>n * m</code> 的的二维矩阵</font> <code>p</code>。如果满足以下条件，则称 <code>p</code> 为 <code>grid</code> 的 <strong>乘积矩阵</strong> ：</p>

<ul>
	<li>对于每个元素 <code>p[i][j]</code> ，它的值等于除了 <code>grid[i][j]</code> 外所有元素的乘积。乘积对 <code>12345</code> 取余数。</li>
</ul>

<p>返回 <code>grid</code> 的乘积矩阵。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>grid = [[1,2],[3,4]]
<strong>输出：</strong>[[24,12],[8,6]]
<strong>解释：</strong>p[0][0] = grid[0][1] * grid[1][0] * grid[1][1] = 2 * 3 * 4 = 24
p[0][1] = grid[0][0] * grid[1][0] * grid[1][1] = 1 * 3 * 4 = 12
p[1][0] = grid[0][0] * grid[0][1] * grid[1][1] = 1 * 2 * 4 = 8
p[1][1] = grid[0][0] * grid[0][1] * grid[1][0] = 1 * 2 * 3 = 6
所以答案是 [[24,12],[8,6]] 。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [[12345],[2],[1]]
<strong>输出：</strong>[[2],[0],[0]]
<strong>解释：</strong>p[0][0] = grid[0][1] * grid[0][2] = 2 * 1 = 2
p[0][1] = grid[0][0] * grid[0][2] = 12345 * 1 = 12345. 12345 % 12345 = 0 ，所以 p[0][1] = 0
p[0][2] = grid[0][0] * grid[0][1] = 12345 * 2 = 24690. 24690 % 12345 = 0 ，所以 p[0][2] = 0
所以答案是 [[2],[0],[0]] 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == grid.length&nbsp;&lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m == grid[i].length&nbsp;&lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= n * m &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>9</sup></code></li>
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

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
