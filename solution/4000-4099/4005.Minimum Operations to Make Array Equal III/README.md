---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4005.Minimum%20Operations%20to%20Make%20Array%20Equal%20III/README.md
tags:
    - 数组
    - 哈希表
    - 数学
    - 计数
    - 数论
---

<!-- problem:start -->

# [4005. 使数组中所有元素相等的最小操作数 III 🔒](https://leetcode.cn/problems/minimum-operations-to-make-array-equal-iii)

[English Version](/solution/4000-4099/4005.Minimum%20Operations%20to%20Make%20Array%20Equal%20III/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组 <code>nums</code>。</p>

<p>在一次操作中，你可以选择任意元素 <code>nums[i]</code>，并执行以下操作之一：</p>

<ul>
	<li><strong>乘法</strong>：将 <code>nums[i]</code> 乘以一个整数 <code>k</code>，其中 <code>k &gt;= 2</code>。</li>
	<li><strong>除法</strong>：将 <code>nums[i]</code> 除以一个整数 <code>k</code>，其中 <code>2 &lt;= k &lt; nums[i]</code>，并且要求 <code>nums[i]</code> 可以被 <code>k</code> 整除。</li>
</ul>

<p>返回使 <code>nums</code> 中所有元素 <strong>相等</strong> 所需的 <strong>最少操作次数</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [6,12,8]</span></p>

<p><strong>输出：</strong> 3</p>

<p><strong>解释：</strong></p>

<p>我们可以执行以下操作，使所有数字变为 6：</p>

<ul>
	<li>将 <code>nums[1] = 12</code> 除以 2，得到 6。</li>
	<li>将 <code>nums[2] = 8</code> 除以 4，得到 2。</li>
	<li>将 <code>nums[2] = 2</code> 乘以 3，得到 6。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [5,15,20]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>我们可以执行以下操作，使所有数字变为 5：</p>

<ul>
	<li>将 <code>nums[1] = 15</code> 除以 3，得到 5。</li>
	<li>将 <code>nums[2] = 20</code> 除以 4，得到 5。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [7,7,7]</span></p>

<p><strong>输出：</strong> 0</p>

<p><strong>解释：</strong></p>

<p>所有元素已经相等，因此不需要任何操作。</p>
</div>

<p>&nbsp;</p>

<p><strong>约束条件：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
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
