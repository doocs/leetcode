# [782. 变为棋盘](https://leetcode-cn.com/problems/transform-to-chessboard)

[English Version](/solution/0700-0799/0782.Transform%20to%20Chessboard/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个 N&nbsp;x N的 <code>board</code>&nbsp;仅由&nbsp;<code>0</code>&nbsp;和&nbsp;<code>1</code>&nbsp;组成&nbsp;。每次移动，你能任意交换两列或是两行的位置。</p>

<p>输出将这个矩阵变为 &ldquo;棋盘&rdquo; 所需的最小移动次数。&ldquo;棋盘&rdquo; 是指任意一格的上下左右四个方向的值均与本身不同的矩阵。如果不存在可行的变换，输出 -1。</p>

<pre><strong>示例:</strong>
<strong>输入:</strong> board = [[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]
<strong>输出:</strong> 2
<strong>解释:</strong>
一种可行的变换方式如下，从左到右：

0110     1010     1010
0110 --&gt; 1010 --&gt; 0101
1001     0101     1010
1001     0101     0101

第一次移动交换了第一列和第二列。
第二次移动交换了第二行和第三行。


<strong>输入:</strong> board = [[0, 1], [1, 0]]
<strong>输出:</strong> 0
<strong>解释:</strong>
注意左上角的格值为0时也是合法的棋盘，如：

01
10

也是合法的棋盘.

<strong>输入:</strong> board = [[1, 0], [1, 0]]
<strong>输出:</strong> -1
<strong>解释:</strong>
任意的变换都不能使这个输入变为合法的棋盘。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>board</code>&nbsp;是方阵，且行列数的范围是<code>[2, 30]</code>。</li>
	<li><code>board[i][j]</code>&nbsp;将只包含&nbsp;<code>0</code>或&nbsp;<code>1</code>。</li>
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
