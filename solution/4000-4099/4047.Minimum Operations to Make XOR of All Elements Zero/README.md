---
comments: true
difficulty: 困难
---

<!-- problem:start -->

# [4047. 使所有元素的异或为零所需的最小操作次数 🔒](https://leetcode.cn/problems/minimum-operations-to-make-xor-of-all-elements-zero)

[English Version](/solution/4000-4099/4047.Minimum%20Operations%20to%20Make%20XOR%20of%20All%20Elements%20Zero/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由&nbsp;<strong>正整数&nbsp;</strong>组成的整数数组 <code>nums</code>。</p>

<p>你可以执行以下&nbsp;<strong>操作&nbsp;</strong>任意次：</p>

<ul>
	<li>选择两个&nbsp;<strong>不同的&nbsp;</strong>下标 <code>i</code> 和 <code>j</code>，满足 <code>nums[i] != nums[j]</code>，并将 <code>nums[i]</code> 或 <code>nums[j]</code> 中的一个替换为 <code>nums[i] ^ nums[j]</code>，其中 <code>^</code> 表示&nbsp;<strong>按位异或</strong>。</li>
</ul>

<p>返回使 <code>nums</code> 中所有元素的&nbsp;<strong>按位异或&nbsp;</strong>结果等于 0 所需的&nbsp;<strong>最少&nbsp;</strong>操作次数。如果无法做到，返回 -1。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [8,1,4,8,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>一种最优的操作序列如下：</p>

<ul>
	<li>选择下标 0 和 1，并将 <code>nums[0]</code> 替换为 <code>8 ^ 1 = 9</code>。数组变为 <code>[9, 1, 4, 8, 2]</code>。</li>
	<li>选择下标 2 和 3，并将 <code>nums[3]</code> 替换为 <code>4 ^ 8 = 12</code>。数组变为 <code>[9, 1, 4, 12, 2]</code>。</li>
	<li>选择下标 0 和 4，并将 <code>nums[0]</code> 替换为 <code>9 ^ 2 = 11</code>。数组变为 <code>[11, 1, 4, 12, 2]</code>。</li>
</ul>

<p><code>nums</code> 中所有元素的异或结果为 <code>11 ^ 1 ^ 4 ^ 12 ^ 2 = 0</code>，因此答案为 3。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code> 中所有元素的异或结果为 <code>1 ^ 2 ^ 3 = 0</code>，因此不需要执行任何操作。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<p>无法使 <code>nums</code> 中所有元素的异或结果等于 0，因此答案为 -1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 2000</code></li>
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
