---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3529.Count%20Cells%20in%20Overlapping%20Horizontal%20and%20Vertical%20Substrings/README.md
tags:
    - 数组
    - 字符串
    - 矩阵
    - 字符串匹配
    - 哈希函数
    - 滚动哈希
---

<!-- problem:start -->

# [3529. 统计水平子串和垂直子串重叠格子的数目](https://leetcode.cn/problems/count-cells-in-overlapping-horizontal-and-vertical-substrings)

[English Version](/solution/3500-3599/3529.Count%20Cells%20in%20Overlapping%20Horizontal%20and%20Vertical%20Substrings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由字符组成的 <code>m x n</code> 矩阵 <code>grid</code> 和一个字符串 <code>pattern</code>。</p>

<p><strong data-end="264" data-start="240">水平子串</strong> 是从左到右的一段连续字符序列。如果子串到达了某行的末尾，它将换行并从下一行的第一个字符继续。<strong>不会&nbsp;</strong>从最后一行回到第一行。</p>

<p><strong data-end="484" data-start="462">垂直子串</strong> 是从上到下的一段连续字符序列。如果子串到达了某列的底部，它将换列并从下一列的第一个字符继续。<strong>不会&nbsp;</strong>从最后一列回到第一列。</p>

<p>请统计矩阵中满足以下条件的单元格数量：</p>

<ul>
	<li>该单元格必须属于 <strong>至少</strong>&nbsp;一个等于 <code>pattern</code>&nbsp;的水平子串，且属于 <strong>至少</strong> 一个等于 <code>pattern</code>&nbsp;的垂直子串。</li>
</ul>

<p>返回满足条件的单元格数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3529.Count%20Cells%20in%20Overlapping%20Horizontal%20and%20Vertical%20Substrings/images/1745660164-PjoTAy-gridtwosubstringsdrawio.png" style="width: 150px; height: 187px;" />
<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [["a","a","c","c"],["b","b","b","c"],["a","a","b","a"],["c","a","a","c"],["a","a","b","a"]], pattern = "abaca"</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p><code>"abaca"</code> 作为一个水平子串（蓝色）和一个垂直子串（红色）各出现一次，并在一个单元格（紫色）处相交。</p>
</div>

<p><strong class="example">示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3529.Count%20Cells%20in%20Overlapping%20Horizontal%20and%20Vertical%20Substrings/images/1745660201-bMoajW-gridexample2fixeddrawio.png" style="width: 150px; height: 150px;" />
<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [["c","a","a","a"],["a","a","b","a"],["b","b","a","a"],["a","a","b","a"]], pattern = "aba"</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>上述被标记的单元格都同时属于至少一个&nbsp;<code>"aba"</code> 的水平和垂直子串。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [["a"]], pattern = "a"</span></p>

<p><strong>输出：</strong> 1</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 1000</code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= pattern.length &lt;= m * n</code></li>
	<li><code>grid</code> 和 <code>pattern</code> 仅由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
