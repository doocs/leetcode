---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3555.Smallest%20Subarray%20to%20Sort%20in%20Every%20Sliding%20Window/README.md
---

<!-- problem:start -->

# [3555. 排序每个滑动窗口中最小的子数组 🔒](https://leetcode.cn/problems/smallest-subarray-to-sort-in-every-sliding-window)

[English Version](/solution/3500-3599/3555.Smallest%20Subarray%20to%20Sort%20in%20Every%20Sliding%20Window/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>。</p>

<p>对于每个长度为 <code>k</code>&nbsp;的连续 <span data-keyword="subarray">子数组</span>，确定必须排序的连续段的最小长度，以便整个窗口成为 <strong>非递减</strong> 的；如果窗口已经排序，则其所需长度为零。</p>

<p>返回一个长度为 <code>n − k + 1</code>&nbsp;的数组，其中每个元素对应其窗口的答案。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,3,2,4,5], k = 3</span></p>

<p><span class="example-io"><b>输出：</b>[2,2,0]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>nums[0...2] = [1, 3, 2]</code>。排序&nbsp;<code>[3, 2]</code> 得到&nbsp;<code>[1, 2, 3]</code>，答案是 2。</li>
	<li><code>nums[1...3] = [3, 2, 4]</code>。排序&nbsp;<code>[3, 2]</code> 得到&nbsp;<code>[2, 3, 4]</code>，答案是 2。</li>
	<li><code>nums[2...4] = [2, 4, 5]</code>&nbsp;已经有序，所以答案是 0。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [5,4,3,2,1], k = 4</span></p>

<p><span class="example-io"><b>输出：</b>[4,4]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>nums[0...3] = [5, 4, 3, 2]</code>。整个子数组必须有序，所以答案是4。</li>
	<li><code>nums[1...4] = [4, 3, 2, 1]</code>。整个子数组必须有序，所以答案是4。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
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
