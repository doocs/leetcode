---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4118. 构造山型数组的最少操作次数 I](https://leetcode.cn/problems/minimum-operations-to-make-mountain-array-i)

[English Version](/solution/4100-4199/4118.Minimum%20Operations%20to%20Make%20Mountain%20Array%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>。</p>

<p>在一次操作中，你可以将 <code>nums</code> 中的任意一个元素 <strong>增加</strong> 1 或 <strong>减少</strong> 1。</p>

<p>每次操作后，<code>nums</code> 中的所有元素都必须保持为 <strong>正数</strong>（<strong>至少</strong> 为 1）。</p>

<p>如果存在一个下标 <code>peak</code> 满足 <code>0 &lt; peak &lt; n - 1</code>，并且：</p>

<ul>
	<li><code>nums[0] &lt; nums[1] &lt; ... &lt; nums[peak]</code></li>
	<li><code>nums[peak] &gt; nums[peak + 1] &gt; ... &gt; nums[n - 1]</code></li>
</ul>

<p>则称该数组为 <strong>山脉数组</strong>。</p>

<p>返回将 <code>nums</code> 变为山脉数组所需的 <strong>最少</strong> 操作次数。如果无法做到，返回 -1。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,2,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>一种最优方案如下：</p>

<ul>
	<li>将 <code>nums[1]</code> 增加 1，并将 <code>nums[2]</code> 减少 1。</li>
	<li>得到的数组为 <code>[2, 3, 2]</code>，这是一个 <code>peak = 1</code> 的山脉数组。</li>
	<li>因此，总操作次数为 <code>1 + 1 = 2</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [9,4,9]</span></p>

<p><strong>输出：</strong> <span class="example-io">6</span></p>

<p><strong>解释：</strong></p>

<p>一种最优方案如下：</p>

<ul>
	<li>将 <code>nums[1]</code> 增加 1，共执行六次。</li>
	<li>得到的数组为 <code>[9, 10, 9]</code>，这是一个 <code>peak = 1</code> 的山脉数组。</li>
	<li>因此，总操作次数为 6。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [5,7,7,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>一种最优方案如下：</p>

<ul>
	<li>将 <code>nums[1]</code> 减少 1。</li>
	<li>得到的数组为 <code>[5, 6, 7, 4]</code>，这是一个 <code>peak = 2</code> 的山脉数组。</li>
	<li>因此，总操作次数为 1。</li>
</ul>
</div>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<p>山脉数组必须有一个峰顶，且峰顶两侧各至少有一个元素。由于 <code>n = 2</code>，且操作不会改变数组的长度，因此无法构成山脉数组。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
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
