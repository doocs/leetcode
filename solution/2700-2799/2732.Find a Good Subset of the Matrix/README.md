# [2732. 找到矩阵中的好子集](https://leetcode.cn/problems/find-a-good-subset-of-the-matrix)

[English Version](/solution/2700-2799/2732.Find%20a%20Good%20Subset%20of%20the%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始大小为&nbsp;<code>m x n</code>&nbsp;的二进制矩阵&nbsp;<code>grid</code>&nbsp;。</p>

<p>从原矩阵中选出若干行构成一个行的 <strong>非空&nbsp;</strong>子集，如果子集中任何一列的和至多为子集大小的一半，那么我们称这个子集是 <strong>好子集</strong>。</p>

<p>更正式的，如果选出来的行子集大小（即行的数量）为 k，那么每一列的和至多为&nbsp;<code>floor(k / 2)</code>&nbsp;。</p>

<p>请你返回一个整数数组，它包含好子集的行下标，请你将子集中的元素&nbsp;<b>升序</b>&nbsp;返回。</p>

<p>如果有多个好子集，你可以返回任意一个。如果没有好子集，请你返回一个空数组。</p>

<p>一个矩阵 <code>grid</code>&nbsp;的行 <strong>子集</strong> ，是删除 <code>grid</code>&nbsp;中某些（也可能不删除）行后，剩余行构成的元素集合。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>grid = [[0,1,1,0],[0,0,0,1],[1,1,1,1]]
<b>输出：</b>[0,1]
<b>解释：</b>我们可以选择第 0 和第 1 行构成一个好子集。
选出来的子集大小为 2 。
- 第 0&nbsp;列的和为 0 + 0 = 0 ，小于等于子集大小的一半。
- 第 1&nbsp;列的和为 1 + 0 = 1 ，小于等于子集大小的一半。
- 第 2&nbsp;列的和为 1 + 0 = 1 ，小于等于子集大小的一半。
- 第 3&nbsp;列的和为 0 + 1 = 1 ，小于等于子集大小的一半。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>grid = [[0]]
<b>输出：</b>[0]
<strong>解释：</strong>我们可以选择第 0 行构成一个好子集。
选出来的子集大小为 1 。
- 第 0 列的和为 0 ，小于等于子集大小的一半。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>grid = [[1,1,1],[1,1,1]]
<b>输出：</b>[]
<b>解释：</b>没有办法得到一个好子集。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= n &lt;= 5</code></li>
	<li><code>grid[i][j]</code>&nbsp;要么是&nbsp;<code>0</code>&nbsp;，要么是&nbsp;<code>1</code> 。</li>
</ul>

## 解法

<!-- end -->
