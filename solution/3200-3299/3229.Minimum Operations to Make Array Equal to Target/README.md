---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3229.Minimum%20Operations%20to%20Make%20Array%20Equal%20to%20Target/README.md
---

<!-- problem:start -->

# [3229. 使数组等于目标数组所需的最少操作次数](https://leetcode.cn/problems/minimum-operations-to-make-array-equal-to-target)

[English Version](/solution/3200-3299/3229.Minimum%20Operations%20to%20Make%20Array%20Equal%20to%20Target/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个长度相同的正整数数组 <code>nums</code> 和 <code>target</code>。</p>

<p>在一次操作中，你可以选择 <code>nums</code> 的任何<span data-keyword="subarray">子数组</span>，并将该子数组内的每个元素的值增加或减少 1。</p>

<p>返回使 <code>nums</code> 数组变为 <code>target</code> 数组所需的 <strong>最少 </strong>操作次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,5,1,2], target = [4,6,2,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>执行以下操作可以使 <code>nums</code> 等于 <code>target</code>：<br />
- <code>nums[0..3]</code> 增加 1，<code>nums = [4,6,2,3]</code>。<br />
- <code>nums[3..3]</code> 增加 1，<code>nums = [4,6,2,4]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,3,2], target = [2,1,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<p>执行以下操作可以使 <code>nums</code> 等于 <code>target</code>：<br />
- <code>nums[0..0]</code> 增加 1，<code>nums = [2,3,2]</code>。<br />
- <code>nums[1..1]</code> 减少 1，<code>nums = [2,2,2]</code>。<br />
- <code>nums[1..1]</code> 减少 1，<code>nums = [2,1,2]</code>。<br />
- <code>nums[2..2]</code> 增加 1，<code>nums = [2,1,3]</code>。<br />
- <code>nums[2..2]</code> 增加 1，<code>nums = [2,1,4]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length == target.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], target[i] &lt;= 10<sup>8</sup></code></li>
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
