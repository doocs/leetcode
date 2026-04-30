---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3520.Minimum%20Threshold%20for%20Inversion%20Pairs%20Count/README.md
tags:
    - 树状数组
    - 线段树
    - 数组
    - 二分查找
    - 分治
    - 归并排序
---

<!-- problem:start -->

# [3520. 逆序对计数的最小阈值 🔒](https://leetcode.cn/problems/minimum-threshold-for-inversion-pairs-count)

[English Version](/solution/3500-3599/3520.Minimum%20Threshold%20for%20Inversion%20Pairs%20Count/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>。</p>

<p><strong>阈值&nbsp;</strong>为&nbsp;<code>x</code>&nbsp;的逆序对是一对下标&nbsp;<code>(i, j)</code>&nbsp;满足：</p>

<ul>
	<li><code>i &lt; j</code></li>
	<li><code>nums[i] &gt; nums[j]</code></li>
	<li>两个数字的差&nbsp;<strong>最多为</strong>&nbsp;<code>x</code>（即&nbsp;<code>nums[i] - nums[j] &lt;= x</code>）。</li>
</ul>

<p>你的任务是确定最小的整数 <code>min_threshold</code>，使得 <strong>至少</strong> 有 <code>k</code> 个逆序对的阈值是&nbsp;<code>min_threshold</code>。</p>

<p>如果没有这样的整数，返回&nbsp;<code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,3,4,3,2,1], k = 7</span></p>

<p><strong>输出：</strong><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>对于阈值&nbsp;<code>x = 2</code>，逆序对有：</p>

<ol>
	<li><code>(3, 4)</code> 其中&nbsp;<code>nums[3] == 4</code> 和 <code>nums[4] == 3</code>.</li>
	<li><code>(2, 5)</code> 其中 <code>nums[2] == 3</code> 和 <code>nums[5] == 2</code>.</li>
	<li><code>(3, 5)</code> 其中 <code>nums[3] == 4</code> 和 <code>nums[5] == 2</code>.</li>
	<li><code>(4, 5)</code> 其中 <code>nums[4] == 3</code> 和 <code>nums[5] == 2</code>.</li>
	<li><code>(1, 6)</code> 其中 <code>nums[1] == 2</code> 和 <code>nums[6] == 1</code>.</li>
	<li><code>(2, 6)</code> 其中 <code>nums[2] == 3</code> 和 <code>nums[6] == 1</code>.</li>
	<li><code>(4, 6)</code> 其中 <code>nums[4] == 3</code> 和 <code>nums[6] == 1</code>.</li>
	<li><code>(5, 6)</code> 其中 <code>nums[5] == 2</code> 和 <code>nums[6] == 1</code>.</li>
</ol>

<p>如果我们选择小于 2 的任意整数作为阈值，则逆序对的数量少于 <code>k</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [10,9,9,9,1], k = 4</span></p>

<p><span class="example-io"><b>输出：</b>8</span></p>

<p><strong>解释：</strong></p>

<p>对于阈值&nbsp;<code>x = 8</code>，逆序对有：</p>

<ol>
	<li><code>(0, 1)</code> 其中&nbsp;<code>nums[0] == 10</code> 和&nbsp;<code>nums[1] == 9</code>。</li>
	<li><code>(0, 2)</code> 其中 <code>nums[0] == 10</code> 和 <code>nums[2] == 9</code>。</li>
	<li><code>(0, 3)</code> 其中 <code>nums[0] == 10</code> 和 <code>nums[3] == 9</code>。</li>
	<li><code>(1, 4)</code> 其中 <code>nums[1] == 9</code> 和 <code>nums[4] == 1</code>。</li>
	<li><code>(2, 4)</code> 其中 <code>nums[2] == 9</code> 和 <code>nums[4] == 1</code>。</li>
	<li><code>(3, 4)</code> 其中 <code>nums[3] == 9</code> 和 <code>nums[4] == 1</code>。</li>
</ol>

<p>如果我们选择小于 8 的任意整数作为阈值，则逆序对的数量少于 <code>k</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
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
