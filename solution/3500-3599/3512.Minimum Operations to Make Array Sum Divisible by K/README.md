---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3512.Minimum%20Operations%20to%20Make%20Array%20Sum%20Divisible%20by%20K/README.md
---

<!-- problem:start -->

# [3512. 使数组和能被 K 整除的最少操作次数](https://leetcode.cn/problems/minimum-operations-to-make-array-sum-divisible-by-k)

[English Version](/solution/3500-3599/3512.Minimum%20Operations%20to%20Make%20Array%20Sum%20Divisible%20by%20K/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code>。你可以执行以下操作任意次：</p>

<ul>
	<li>选择一个下标&nbsp;<code>i</code>，并将 <code>nums[i]</code> 替换为 <code>nums[i] - 1</code>。</li>
</ul>

<p>返回使数组元素之和能被 <code>k</code> 整除所需的<strong>最小</strong>操作次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,9,7], k = 5</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>对 <code>nums[1] = 9</code> 执行 4 次操作。现在 <code>nums = [3, 5, 7]</code>。</li>
	<li>数组之和为 15，可以被 5 整除。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [4,1,3], k = 4</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>数组之和为 8，已经可以被 4 整除。因此不需要操作。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,2], k = 6</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>对 <code>nums[0] = 3</code> 执行 3 次操作，对 <code>nums[1] = 2</code> 执行 2 次操作。现在 <code>nums = [0, 0]</code>。</li>
	<li>数组之和为 0，可以被 6 整除。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
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
