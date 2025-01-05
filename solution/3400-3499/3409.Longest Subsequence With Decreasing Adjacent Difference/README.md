---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3409.Longest%20Subsequence%20With%20Decreasing%20Adjacent%20Difference/README.md
---

<!-- problem:start -->

# [3409. 最长相邻绝对差递减子序列](https://leetcode.cn/problems/longest-subsequence-with-decreasing-adjacent-difference)

[English Version](/solution/3400-3499/3409.Longest%20Subsequence%20With%20Decreasing%20Adjacent%20Difference/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>你的任务是找到 <code>nums</code>&nbsp;中的 <strong>最长子序列</strong>&nbsp;<code>seq</code>&nbsp;，这个子序列中相邻元素的 <strong>绝对差</strong>&nbsp;构成一个 <strong>非递增</strong>&nbsp;整数序列。换句话说，<code>nums</code>&nbsp;中的序列&nbsp;<code>seq<sub>0</sub></code>, <code>seq<sub>1</sub></code>, <code>seq<sub>2</sub></code>, ..., <code>seq<sub>m</sub></code>&nbsp;满足&nbsp;<code>|seq<sub>1</sub> - seq<sub>0</sub>| &gt;= |seq<sub>2</sub> - seq<sub>1</sub>| &gt;= ... &gt;= |seq<sub>m</sub> - seq<sub>m - 1</sub>|</code>&nbsp;。</p>

<p>请你返回这个子序列的长度。</p>

<p>一个&nbsp;<strong>子序列</strong>&nbsp;指的是从一个数组中删除零个或多个元素后，剩下元素不改变顺序得到的&nbsp;<strong>非空</strong>&nbsp;数组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [16,6,3]</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><b>解释：</b></p>

<p>最长子序列是&nbsp;<code>[16, 6, 3]</code>&nbsp;，相邻绝对差值为&nbsp;<code>[10, 3]</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [6,5,3,4,2,1]</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong></p>

<p>最长子序列是&nbsp;<code>[6, 4, 2, 1]</code>&nbsp;，相邻绝对差值为&nbsp;<code>[2, 2, 1]</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [10,20,10,19,10,20]</span></p>

<p><span class="example-io"><b>输出：</b>5</span></p>

<p><b>解释：</b></p>

<p>最长子序列是&nbsp;<code>[10, 20, 10, 19, 10]</code>&nbsp;，相邻绝对差值为&nbsp;<code>[10, 10, 9, 9]</code>&nbsp;。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 300</code></li>
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
