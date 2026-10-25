---
comments: true
difficulty: 简单
---

<!-- problem:start -->

# [4088. 峰值与谷值的差](https://leetcode.cn/problems/difference-between-peaks-and-valleys)

[English Version](/solution/4000-4099/4088.Difference%20Between%20Peaks%20and%20Valleys/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>。</p>

<p>如果满足以下&nbsp;<strong>所有&nbsp;</strong>条件，则下标志 <code>i</code> 被称为&nbsp;<strong>峰值&nbsp;</strong>：</p>

<ul>
	<li><code>0 &lt; i &lt; n - 1</code></li>
	<li><code>nums[i] &gt; nums[i - 1]</code></li>
	<li><code>nums[i] &gt; nums[i + 1]</code></li>
</ul>

<p>如果满足以下&nbsp;<strong>所有&nbsp;</strong>条件，则下标志 <code>i</code> 被称为&nbsp;<strong>谷值&nbsp;</strong>：</p>

<ul>
	<li><code>0 &lt; i &lt; n - 1</code></li>
	<li><code>nums[i] &lt; nums[i - 1]</code></li>
	<li><code>nums[i] &lt; nums[i + 1]</code></li>
</ul>

<p>返回一个整数，表示所有峰值之和与所有谷值之和的&nbsp;<strong>差值&nbsp;</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,5,2,4,1,3,0]</span></p>

<p><strong>输出：</strong> <span class="example-io">9</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>峰值分别是 <code>nums[1] = 5</code>、<code>nums[3] = 4</code> 和 <code>nums[5] = 3</code>。</li>
	<li>谷值分别是 <code>nums[2] = 2</code> 和 <code>nums[4] = 1</code>。</li>
	<li>峰值之和与谷值之和的差值为 <code>(5 + 4 + 3) - (2 + 1) = 9</code>。因此，答案是 9。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [-5,-6,-1,-2,5,3,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">12</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>峰值分别是 <code>nums[2] = -1</code> 和 <code>nums[4] = 5</code>。</li>
	<li>谷值分别是 <code>nums[1] = -6</code> 和 <code>nums[3] = -2</code>。</li>
	<li>峰值之和与谷值之和的差值为 <code>(-1 + 5) - (-6 + (-2)) = 12</code>。因此，答案是 12。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [6,5,2,5,5,3,6]</span></p>

<p><strong>输出：</strong> <span class="example-io">-5</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>数组中没有峰值。</li>
	<li>谷值分别是 <code>nums[2] = 2</code> 和 <code>nums[5] = 3</code>。</li>
	<li>峰值之和与谷值之和的差值为 <code>(0) - (2 + 3) = -5</code>。因此，答案是 -5。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n == nums.length &lt;= 100</code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
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
