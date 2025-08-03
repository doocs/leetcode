---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3634.Minimum%20Removals%20to%20Balance%20Array/README.md
---

<!-- problem:start -->

# [3634. 使数组平衡的最少移除数目](https://leetcode.cn/problems/minimum-removals-to-balance-array)

[English Version](/solution/3600-3699/3634.Minimum%20Removals%20to%20Balance%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code>。</p>

<p>如果一个数组的&nbsp;<strong>最大&nbsp;</strong>元素的值&nbsp;<strong>至多&nbsp;</strong>是其&nbsp;<strong>最小&nbsp;</strong>元素的 <code>k</code> 倍，则该数组被称为是&nbsp;<strong>平衡&nbsp;</strong>的。</p>

<p>你可以从 <code>nums</code> 中移除&nbsp;<strong>任意&nbsp;</strong>数量的元素，但不能使其变为&nbsp;<strong>空&nbsp;</strong>数组。</p>

<p>返回为了使剩余数组平衡，需要移除的元素的&nbsp;<strong>最小&nbsp;</strong>数量。</p>

<p><strong>注意：</strong>大小为 1 的数组被认为是平衡的，因为其最大值和最小值相等，且条件总是成立。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [2,1,5], k = 2</span></p>

<p><strong>输出：</strong><span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>移除 <code>nums[2] = 5</code> 得到 <code>nums = [2, 1]</code>。</li>
	<li>现在 <code>max = 2</code>, <code>min = 1</code>，且 <code>max &lt;= min * k</code>，因为 <code>2 &lt;= 1 * 2</code>。因此，答案是 1。</li>
</ul>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [1,6,2,9], k = 3</span></p>

<p><strong>输出：</strong><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>移除 <code>nums[0] = 1</code> 和 <code>nums[3] = 9</code> 得到 <code>nums = [6, 2]</code>。</li>
	<li>现在 <code>max = 6</code>, <code>min = 2</code>，且 <code>max &lt;= min * k</code>，因为 <code>6 &lt;= 2 * 3</code>。因此，答案是 2。</li>
</ul>
</div>

<p><strong class="example">示例 3:</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [4,6], k = 2</span></p>

<p><strong>输出：</strong><span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>由于 <code>nums</code> 已经平衡，因为 <code>6 &lt;= 4 * 2</code>，所以不需要移除任何元素。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
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
