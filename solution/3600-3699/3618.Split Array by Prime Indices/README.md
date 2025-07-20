---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3618.Split%20Array%20by%20Prime%20Indices/README.md
---

<!-- problem:start -->

# [3618. 根据质数下标分割数组](https://leetcode.cn/problems/split-array-by-prime-indices)

[English Version](/solution/3600-3699/3618.Split%20Array%20by%20Prime%20Indices/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>根据以下规则将 <code>nums</code> 分割成两个数组 <code>A</code> 和 <code>B</code>：</p>

<ul>
	<li><code>nums</code> 中位于&nbsp;<strong>质数 </strong>下标的元素必须放入数组 <code>A</code>。</li>
	<li>所有其他元素必须放入数组 <code>B</code>。</li>
</ul>

<p>返回两个数组和的&nbsp;<strong>绝对&nbsp;</strong>差值：<code>|sum(A) - sum(B)|</code>。</p>

<p><strong>质数&nbsp;</strong>是一个大于 1 的自然数，它只有两个因子，1和它本身。</p>

<p><strong>注意</strong>：空数组的和为 0。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [2,3,4]</span></p>

<p><strong>输出:</strong> <span class="example-io">1</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>数组中唯一的质数下标是 2，所以 <code>nums[2] = 4</code> 被放入数组 <code>A</code>。</li>
	<li>其余元素 <code>nums[0] = 2</code> 和 <code>nums[1] = 3</code> 被放入数组 <code>B</code>。</li>
	<li><code>sum(A) = 4</code>，<code>sum(B) = 2 + 3 = 5</code>。</li>
	<li>绝对差值是 <code>|4 - 5| = 1</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [-1,5,7,0]</span></p>

<p><strong>输出:</strong> <span class="example-io">3</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>数组中的质数下标是 2 和 3，所以 <code>nums[2] = 7</code> 和 <code>nums[3] = 0</code> 被放入数组 <code>A</code>。</li>
	<li>其余元素 <code>nums[0] = -1</code> 和 <code>nums[1] = 5</code> 被放入数组 <code>B</code>。</li>
	<li><code>sum(A) = 7 + 0 = 7</code>，<code>sum(B) = -1 + 5 = 4</code>。</li>
	<li>绝对差值是 <code>|7 - 4| = 3</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
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
