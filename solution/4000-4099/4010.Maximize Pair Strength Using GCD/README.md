---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4010.Maximize%20Pair%20Strength%20Using%20GCD/README.md
---

<!-- problem:start -->

# [4010. 数对的最大强度](https://leetcode.cn/problems/maximize-pair-strength-using-gcd)

[English Version](/solution/4000-4099/4010.Maximize%20Pair%20Strength%20Using%20GCD/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>选择&nbsp;<strong>恰好一对&nbsp;</strong>不同下标 <code>i</code> 和 <code>j</code>。该数对的&nbsp;<strong>强度&nbsp;</strong>定义为：</p>

<p><code>(nums[i] * nums[j]) / gcd(nums[i], nums[j])<sup>2</sup></code></p>

<p>返回所有可能数对中的<strong>&nbsp;最大&nbsp;</strong>强度。</p>

<p><code>gcd(a, b)</code> 表示 <code>a</code> 和 <code>b</code> 的<strong>&nbsp;最大公约数&nbsp;</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,3,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">15</span></p>

<p><strong>解释：</strong></p>

<p>选择 <code>i = 1</code> 和 <code>j = 2</code>，得到强度：</p>

<p><code>(3 * 5) / gcd(3, 5)<sup>2</sup> = 15 / 1 = 15</code>，这是所有数对中的最大值。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [4,6,8]</span></p>

<p><strong>输出：</strong> <span class="example-io">12</span></p>

<p><strong>解释：</strong></p>

<p>选择 <code>i = 1</code> 和 <code>j = 2</code>，得到强度：</p>

<p><code>(6 * 8) / gcd(6, 8)<sup>2</sup> = 48 / 4 = 12</code>，这是所有数对中的最大值。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>选择 <code>i = 0</code> 和 <code>j = 1</code>，得到强度：</p>

<p><code>(3 * 3) / gcd(3, 3)<sup>2</sup> = 9 / 9 = 1</code>，这是唯一数对的强度。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
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
