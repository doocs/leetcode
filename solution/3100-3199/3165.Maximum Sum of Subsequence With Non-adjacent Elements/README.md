---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3165.Maximum%20Sum%20of%20Subsequence%20With%20Non-adjacent%20Elements/README.md
tags:
    - 线段树
    - 数组
    - 分治
    - 动态规划
---

<!-- problem:start -->

# [3165. 不包含相邻元素的子序列的最大和](https://leetcode.cn/problems/maximum-sum-of-subsequence-with-non-adjacent-elements)

[English Version](/solution/3100-3199/3165.Maximum%20Sum%20of%20Subsequence%20With%20Non-adjacent%20Elements/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 和一个二维数组 <code>queries</code>，其中 <code>queries[i] = [pos<sub>i</sub>, x<sub>i</sub>]</code>。</p>

<p>对于每个查询 <code>i</code>，首先将 <code>nums[pos<sub>i</sub>]</code> 设置为 <code>x<sub>i</sub></code>，然后计算查询 <code>i</code> 的答案，该答案为 <code>nums</code> 中 <strong>不包含相邻元素 </strong>的 <span data-keyword="subsequence-array">子序列</span> 的 <strong>最大 </strong>和。</p>

<p>返回所有查询的答案之和。</p>

<p>由于最终答案可能非常大，返回其对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 的结果。</p>

<p><strong>子序列</strong> 是指从另一个数组中删除一些或不删除元素而不改变剩余元素顺序得到的数组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [3,5,9], queries = [[1,-2],[0,-3]]</span></p>

<p><strong>输出：</strong><span class="example-io">21</span></p>

<p><strong>解释：</strong><br />
执行第 1 个查询后，<code>nums = [3,-2,9]</code>，不包含相邻元素的子序列的最大和为 <code>3 + 9 = 12</code>。<br />
执行第 2 个查询后，<code>nums = [-3,-2,9]</code>，不包含相邻元素的子序列的最大和为 9 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [0,-1], queries = [[0,-5]]</span></p>

<p><strong>输出：</strong><span class="example-io">0</span></p>

<p><strong>解释：</strong><br />
执行第 1 个查询后，<code>nums = [-5,-1]</code>，不包含相邻元素的子序列的最大和为 0（选择空子序列）。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>queries[i] == [pos<sub>i</sub>, x<sub>i</sub>]</code></li>
	<li><code>0 &lt;= pos<sub>i</sub> &lt;= nums.length - 1</code></li>
	<li><code>-10<sup>5</sup> &lt;= x<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
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
