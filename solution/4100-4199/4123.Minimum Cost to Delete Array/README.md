---
comments: true
difficulty: 困难
---

<!-- problem:start -->

# [4123. 删除数组的最小代价](https://leetcode.cn/problems/minimum-cost-to-delete-array)

[English Version](/solution/4100-4199/4123.Minimum%20Cost%20to%20Delete%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>每次操作，你可以从当前数组中任选一个长度 <strong>至少为 2</strong> 的 <strong>子数组</strong>，并将其删除。该操作的代价为所选子数组的 <strong>首尾</strong> 元素之和。</p>

<p>每次操作后，将剩余元素按原有顺序拼接起来。</p>

<p>返回删除 <strong>整个</strong> 数组所需的 <strong>最小</strong> 总代价。</p>

<p><strong>子数组</strong> 是数组中一段连续且 <strong>非空</strong> 的元素序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,-7,3,-5]</span></p>

<p><strong>输出：</strong> <span class="example-io">-7</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>删除 <code>[-7, 3]</code>，代价为 <code>-7 + 3 = -4</code>。数组变为 <code>[2, -5]</code>。</li>
	<li>删除 <code>[2, -5]</code>，代价为 <code>2 + (-5) = -3</code>。</li>
	<li>总代价为 <code>-4 + (-3) = -7</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [4,0,-5,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>删除 <code>[0, -5]</code>，代价为 <code>0 + (-5) = -5</code>。数组变为 <code>[4, 2]</code>。</li>
	<li>删除 <code>[4, 2]</code>，代价为 <code>4 + 2 = 6</code>。</li>
	<li>总代价为 <code>-5 + 6 = 1</code>。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [7,-8,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">8</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>要删除整个数组，必须在一次操作中删除全部三个元素。如果只删除两个元素，剩下的一个元素将无法删除。</li>
	<li>删除 <code>[7, -8, 1]</code> 的代价为 <code>7 + 1 = 8</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
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
