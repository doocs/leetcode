---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3587.Minimum%20Adjacent%20Swaps%20to%20Alternate%20Parity/README.md
---

<!-- problem:start -->

# [3587. 最小相邻交换至奇偶交替](https://leetcode.cn/problems/minimum-adjacent-swaps-to-alternate-parity)

[English Version](/solution/3500-3599/3587.Minimum%20Adjacent%20Swaps%20to%20Alternate%20Parity/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由互不相同的整数组成的数组 <code>nums</code>&nbsp;。</p>

<p>在一次操作中，你可以交换任意两个&nbsp;<strong>相邻&nbsp;</strong>元素。</p>

<p>在一个排列中，当所有相邻元素的奇偶性交替出现，我们认为该排列是 <strong>有效排列</strong>。这意味着每对相邻元素中一个是偶数，一个是奇数。</p>

<p>请返回将 <code>nums</code> 变成任意一种&nbsp;<strong>有效排列</strong>&nbsp;所需的最小相邻交换次数。</p>

<p>如果无法重排 <code>nums</code> 来获得有效排列，则返回 <code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,4,6,5,7]</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><strong>解释：</strong></p>

<p>将 5 和 6 交换，数组变成&nbsp; <code>[2,4,5,6,7]</code></p>

<p>将 5 和 4&nbsp;交换，数组变成&nbsp; <code>[2,5,4,6,7]</code></p>

<p>将 6&nbsp;和 7&nbsp;交换，数组变成&nbsp;&nbsp;<code>[2,5,4,7,6]</code>。此时是一个有效排列。因此答案是 3。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,4,5,7]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>将 4&nbsp;和 5&nbsp;交换，数组变成 <code>[2,5,4,7]</code>。此时是一个有效排列。因此答案是 1。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>数组已经是有效排列，因此不需要任何操作。</p>
</div>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block">
<p><b>输入：</b>&nbsp;<span class="example-io">nums = [4,5,6,8]</span></p>

<p><span class="example-io"><b>输出：</b>-1</span></p>

<p><b>解释：</b></p>

<p>没有任何一种排列可以满足奇偶交替的要求，因此返回 -1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>nums</code>&nbsp;中的所有元素都是 <strong>唯一</strong> 的</li>
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
