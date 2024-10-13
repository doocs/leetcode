---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3315.Construct%20the%20Minimum%20Bitwise%20Array%20II/README.md
---

<!-- problem:start -->

# [3315. 构造最小位运算数组 II](https://leetcode.cn/problems/construct-the-minimum-bitwise-array-ii)

[English Version](/solution/3300-3399/3315.Construct%20the%20Minimum%20Bitwise%20Array%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code>&nbsp;的<span data-keyword="prime">质数</span>数组&nbsp;<code>nums</code>&nbsp;。你的任务是返回一个长度为 <code>n</code>&nbsp;的数组 <code>ans</code>&nbsp;，对于每个下标 <code>i</code>&nbsp;，以下<strong>&nbsp;条件</strong>&nbsp;均成立：</p>

<ul>
	<li><code>ans[i] OR (ans[i] + 1) == nums[i]</code></li>
</ul>

<p>除此以外，你需要 <strong>最小化</strong>&nbsp;结果数组里每一个&nbsp;<code>ans[i]</code>&nbsp;。</p>

<p>如果没法找到符合 <strong>条件</strong>&nbsp;的&nbsp;<code>ans[i]</code>&nbsp;，那么&nbsp;<code>ans[i] = -1</code>&nbsp;。</p>

<p><strong>质数</strong>&nbsp;指的是一个大于 1 的自然数，且它只有 1 和自己两个因数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [2,3,5,7]</span></p>

<p><span class="example-io"><b>输出：</b>[-1,1,4,3]</span></p>

<p><b>解释：</b></p>

<ul>
	<li>对于&nbsp;<code>i = 0</code>&nbsp;，不存在&nbsp;<code>ans[0]</code>&nbsp;满足&nbsp;<code>ans[0] OR (ans[0] + 1) = 2</code>&nbsp;，所以&nbsp;<code>ans[0] = -1</code>&nbsp;。</li>
	<li>对于&nbsp;<code>i = 1</code>&nbsp;，满足 <code>ans[1] OR (ans[1] + 1) = 3</code>&nbsp;的最小&nbsp;<code>ans[1]</code>&nbsp;为&nbsp;<code>1</code>&nbsp;，因为&nbsp;<code>1 OR (1 + 1) = 3</code>&nbsp;。</li>
	<li>对于&nbsp;<code>i = 2</code>&nbsp;，满足 <code>ans[2] OR (ans[2] + 1) = 5</code>&nbsp;的最小 <code>ans[2]</code>&nbsp;为&nbsp;<code>4</code>&nbsp;，因为&nbsp;<code>4 OR (4 + 1) = 5</code>&nbsp;。</li>
	<li>对于&nbsp;<code>i = 3</code>&nbsp;，满足&nbsp;<code>ans[3] OR (ans[3] + 1) = 7</code>&nbsp;的最小&nbsp;<code>ans[3]</code>&nbsp;为&nbsp;<code>3</code>&nbsp;，因为&nbsp;<code>3 OR (3 + 1) = 7</code>&nbsp;。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [11,13,31]</span></p>

<p><span class="example-io"><b>输出：</b>[9,12,15]</span></p>

<p><b>解释：</b></p>

<ul>
	<li>对于&nbsp;<code>i = 0</code>&nbsp;，满足&nbsp;<code>ans[0] OR (ans[0] + 1) = 11</code> 的最小&nbsp;<code>ans[0]</code>&nbsp;为&nbsp;<code>9</code>&nbsp;，因为&nbsp;<code>9 OR (9 + 1) = 11</code>&nbsp;。</li>
	<li>对于&nbsp;<code>i = 1</code>&nbsp;，满足&nbsp;<code>ans[1] OR (ans[1] + 1) = 13</code>&nbsp;的最小&nbsp;<code>ans[1]</code>&nbsp;为&nbsp;<code>12</code>&nbsp;，因为&nbsp;<code>12 OR (12 + 1) = 13</code>&nbsp;。</li>
	<li>对于&nbsp;<code>i = 2</code>&nbsp;，满足&nbsp;<code>ans[2] OR (ans[2] + 1) = 31</code>&nbsp;的最小&nbsp;<code>ans[2]</code>&nbsp;为&nbsp;<code>15</code>&nbsp;，因为&nbsp;<code>15 OR (15 + 1) = 31</code>&nbsp;。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>2 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>nums[i]</code>&nbsp;是一个质数。</li>
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
