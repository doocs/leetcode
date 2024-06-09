---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3171.Find%20Subarray%20With%20Bitwise%20AND%20Closest%20to%20K/README.md
tags:
    - 位运算
    - 线段树
    - 数组
    - 二分查找
---

<!-- problem:start -->

# [3171. 找到按位与最接近 K 的子数组](https://leetcode.cn/problems/find-subarray-with-bitwise-and-closest-to-k)

[English Version](/solution/3100-3199/3171.Find%20Subarray%20With%20Bitwise%20AND%20Closest%20to%20K/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。你需要找到&nbsp;<code>nums</code>&nbsp;的一个&nbsp;<span data-keyword="subarray-nonempty">子数组</span>&nbsp;，满足子数组中所有元素按位与运算&nbsp;<code>AND</code>&nbsp;的值与 <code>k</code>&nbsp;的 <strong>绝对差</strong>&nbsp;尽可能 <strong>小</strong>&nbsp;。换言之，你需要选择一个子数组&nbsp;<code>nums[l..r]</code>&nbsp;满足&nbsp;<code>|k - (nums[l] AND nums[l + 1] ... AND nums[r])|</code>&nbsp;最小。</p>

<p>请你返回 <strong>最小</strong>&nbsp;的绝对差值。</p>

<p><strong>子数组</strong>是数组中连续的&nbsp;<strong>非空</strong>&nbsp;元素序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,4,5], k = 3</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><strong>解释：</strong></p>

<p>子数组&nbsp;<code>nums[2..3]</code> 的按位&nbsp;<code>AND</code>&nbsp;运算值为 4 ，得到最小差值&nbsp;<code>|3 - 4| = 1</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,1,2], k = 2</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong></p>

<p>子数组&nbsp;<code>nums[1..1]</code> 的按位&nbsp;<code>AND</code>&nbsp;运算值为 2 ，得到最小差值&nbsp;<code>|2 - 2| = 0</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1], k = 10</span></p>

<p><span class="example-io"><b>输出：</b>9</span></p>

<p><strong>解释：</strong></p>

<p>只有一个子数组，按位&nbsp;<code>AND</code>&nbsp;运算值为 1 ，得到最小差值&nbsp;<code>|10 - 1| = 9</code>&nbsp;。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
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
