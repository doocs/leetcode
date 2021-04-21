# [803. 打砖块](https://leetcode-cn.com/problems/bricks-falling-when-hit)

[English Version](/solution/0800-0899/0803.Bricks%20Falling%20When%20Hit/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个 <code>m x n</code> 的二元网格，其中 <code>1</code> 表示砖块，<code>0</code> 表示空白。砖块 <strong>稳定</strong>（不会掉落）的前提是：</p>

<ul>
	<li>一块砖直接连接到网格的顶部，或者</li>
	<li>至少有一块相邻（4 个方向之一）砖块<strong> 稳定 </strong>不会掉落时</li>
</ul>

<p>给你一个数组 <code>hits</code> ，这是需要依次消除砖块的位置。每当消除 <code>hits[i] = (rowi, coli)</code> 位置上的砖块时，对应位置的砖块（若存在）会消失，然后其他的砖块可能因为这一消除操作而掉落。一旦砖块掉落，它会立即从网格中消失（即，它不会落在其他稳定的砖块上）。</p>

<p>返回一个数组 <code>result</code> ，其中 <code>result[i]</code> 表示第 <code>i</code> 次消除操作对应掉落的砖块数目。</p>

<p><strong>注意</strong>，消除可能指向是没有砖块的空白位置，如果发生这种情况，则没有砖块掉落。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
<strong>输出：</strong>[2]
<strong>解释：</strong>
网格开始为：
[[1,0,0,0]，
 [<strong>1</strong>,1,1,0]]
消除 (1,0) 处加粗的砖块，得到网格：
[[1,0,0,0]
 [0,<strong>1</strong>,<strong>1</strong>,0]]
两个加粗的砖不再稳定，因为它们不再与顶部相连，也不再与另一个稳定的砖相邻，因此它们将掉落。得到网格：
[[1,0,0,0],
 [0,0,0,0]]
因此，结果为 [2] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
<strong>输出：</strong>[0,0]
<strong>解释：</strong>
网格开始为：
[[1,0,0,0],
 [1,<strong>1</strong>,0,0]]
消除 (1,1) 处加粗的砖块，得到网格：
[[1,0,0,0],
 [1,0,0,0]]
剩下的砖都很稳定，所以不会掉落。网格保持不变：
[[1,0,0,0], 
 [<strong>1</strong>,0,0,0]]
接下来消除 (1,0) 处加粗的砖块，得到网格：
[[1,0,0,0],
 [0,0,0,0]]
剩下的砖块仍然是稳定的，所以不会有砖块掉落。
因此，结果为 [0,0] 。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 <= m, n <= 200</code></li>
	<li><code>grid[i][j]</code> 为 <code>0</code> 或 <code>1</code></li>
	<li><code>1 <= hits.length <= 4 * 10<sup>4</sup></code></li>
	<li><code>hits[i].length == 2</code></li>
	<li><code>0 <= x<sub>i </sub><= m - 1</code></li>
	<li><code>0 <= y<sub>i</sub> <= n - 1</code></li>
	<li>所有 <code>(x<sub>i</sub>, y<sub>i</sub>)</code> 互不相同</li>
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
