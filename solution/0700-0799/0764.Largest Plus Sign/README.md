# [764. 最大加号标志](https://leetcode-cn.com/problems/largest-plus-sign)

[English Version](/solution/0700-0799/0764.Largest%20Plus%20Sign/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个大小在 (0, 0) 到 (N-1, N-1) 的2D网格&nbsp;<code>grid</code>&nbsp;中，除了在&nbsp;<code>mines</code>&nbsp;中给出的单元为&nbsp;<code>0</code>，其他每个单元都是&nbsp;<code>1</code>。网格中包含&nbsp;<code>1</code>&nbsp;的最大的轴对齐加号标志是多少阶？返回加号标志的阶数。如果未找到加号标志，则返回 0。</p>

<p>一个&nbsp;<strong>k</strong>&quot; 阶由&nbsp;<em><code>1</code></em>&nbsp;组成的&ldquo;轴对称&rdquo;加号标志具有中心网格&nbsp;&nbsp;<code>grid[x][y] = 1</code>&nbsp;，以及4个从中心向上、向下、向左、向右延伸，长度为&nbsp;<code>k-1</code>，由&nbsp;<code>1</code>&nbsp;组成的臂。下面给出&nbsp;<strong>k</strong>&quot; 阶&ldquo;轴对称&rdquo;加号标志的示例。注意，只有加号标志的所有网格要求为 1，别的网格可能为 0 也可能为 1。</p>

<p>&nbsp;</p>

<p><strong>k 阶轴对称加号标志示例:</strong></p>

<pre>
阶 1:
000
0<strong>1</strong>0
000

阶 2:
00000
00<strong>1</strong>00
0<strong>111</strong>0
00<strong>1</strong>00
00000

阶 3:
0000000
000<strong>1</strong>000
000<strong>1</strong>000
0<strong>11111</strong>0
000<strong>1</strong>000
000<strong>1</strong>000
0000000
</pre>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> N = 5, mines = [[4, 2]]
<strong>输出:</strong> 2
<strong>解释:
</strong>
11111
11111
1<em><strong>1</strong></em>111
<em><strong>111</strong></em>11
1<em><strong>1</strong></em>011

在上面的网格中，最大加号标志的阶只能是2。一个标志已在图中标出。
</pre>

<p>&nbsp;</p>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入:</strong> N = 2, mines = []
<strong>输出:</strong> 1
<strong>解释:

11
11
</strong>
没有 2 阶加号标志，有 1 阶加号标志。
</pre>

<p>&nbsp;</p>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入:</strong> N = 1, mines = [[0, 0]]
<strong>输出:</strong> 0
<strong>解释:

0
</strong>
没有加号标志，返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li>整数<code>N</code> 的范围：&nbsp;<code>[1, 500]</code>.</li>
	<li><code>mines</code> 的最大长度为&nbsp;<code>5000</code>.</li>
	<li><code>mines[i]</code> 是长度为2的由2个&nbsp;<code>[0, N-1]</code>&nbsp;中的数组成.</li>
	<li><em>(另外,使用 C, C++, 或者&nbsp;C# 编程将以稍小的时间限制进行​​判断.)</em></li>
</ol>

<p>&nbsp;</p>

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
