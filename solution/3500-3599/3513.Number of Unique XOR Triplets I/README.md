---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3513.Number%20of%20Unique%20XOR%20Triplets%20I/README.md
rating: 1663
source: 第 154 场双周赛 Q2
tags:
    - 位运算
    - 数组
    - 数学
---

<!-- problem:start -->

# [3513. 不同 XOR 三元组的数目 I](https://leetcode.cn/problems/number-of-unique-xor-triplets-i)

[English Version](/solution/3500-3599/3513.Number%20of%20Unique%20XOR%20Triplets%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>，其中 <code>nums</code> 是范围 <code>[1, n]</code> 内所有数的&nbsp;<strong>排列&nbsp;</strong>。</p>

<p><strong>XOR 三元组</strong> 定义为三个元素的异或值 <code>nums[i] XOR nums[j] XOR nums[k]</code>，其中 <code>i &lt;= j &lt;= k</code>。</p>

<p>返回所有可能三元组 <code>(i, j, k)</code> 中&nbsp;<strong>不同&nbsp;</strong>的 XOR 值的数量。</p>

<p><strong>排列</strong> 是一个集合中所有元素的重新排列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>所有可能的 XOR 三元组值为：</p>

<ul>
	<li><code>(0, 0, 0) → 1 XOR 1 XOR 1 = 1</code></li>
	<li><code>(0, 0, 1) → 1 XOR 1 XOR 2 = 2</code></li>
	<li><code>(0, 1, 1) → 1 XOR 2 XOR 2 = 1</code></li>
	<li><code>(1, 1, 1) → 2 XOR 2 XOR 2 = 2</code></li>
</ul>

<p>不同的 XOR 值为 <code>{1, 2}</code>，因此输出为 2。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,1,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>可能的 XOR 三元组值包括：</p>

<ul>
	<li><code>(0, 0, 0) → 3 XOR 3 XOR 3 = 3</code></li>
	<li><code>(0, 0, 1) → 3 XOR 3 XOR 1 = 1</code></li>
	<li><code>(0, 0, 2) → 3 XOR 3 XOR 2 = 2</code></li>
	<li><code>(0, 1, 2) → 3 XOR 1 XOR 2 = 0</code></li>
</ul>

<p>不同的 XOR 值为 <code>{0, 1, 2, 3}</code>，因此输出为 4。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= n</code></li>
	<li><code>nums</code> 是从 <code>1</code> 到 <code>n</code> 的整数的一个排列。</li>
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
