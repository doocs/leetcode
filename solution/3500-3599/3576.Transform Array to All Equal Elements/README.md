---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3576.Transform%20Array%20to%20All%20Equal%20Elements/README.md
---

<!-- problem:start -->

# [3576. 数组元素相等转换](https://leetcode.cn/problems/transform-array-to-all-equal-elements)

[English Version](/solution/3500-3599/3576.Transform%20Array%20to%20All%20Equal%20Elements/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个大小为 <code>n</code> 的整数数组 <code>nums</code>，其中只包含 <code>1</code> 和 <code>-1</code>，以及一个整数 <code>k</code>。</p>

<p>你可以最多进行 <code>k</code> 次以下操作：</p>

<ul>
	<li>
	<p>选择一个下标&nbsp;<code>i</code>（<code>0 &lt;= i &lt; n - 1</code>），然后将 <code>nums[i]</code> 和 <code>nums[i + 1]</code> 同时&nbsp;<strong>乘以</strong>&nbsp;<code>-1</code>。</p>
	</li>
</ul>

<p><strong>注意：</strong>你可以在&nbsp;<strong>不同&nbsp;</strong>的操作中多次选择相同的下标&nbsp;<code>i</code>。</p>

<p>如果在最多 <code>k</code> 次操作后可以使数组的所有元素相等，则返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,-1,1,-1,1], k = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<p>我们可以通过以下两次操作使数组的所有元素相等：</p>

<ul>
	<li>选择下标&nbsp;<code>i = 1</code>，将 <code>nums[1]</code> 和 <code>nums[2]</code> 同时乘以 -1。此时 <code>nums = [1,1,-1,-1,1]</code>。</li>
	<li>选择下标&nbsp;<code>i = 2</code>，将 <code>nums[2]</code> 和 <code>nums[3]</code> 同时乘以 -1。此时 <code>nums = [1,1,1,1,1]</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [-1,-1,-1,1,1,1], k = 5</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<p>在最多 5 次操作内，无法使数组的所有元素相等。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> 的值为 <code>-1</code> 或 <code>1</code>。</li>
	<li><code>1 &lt;= k &lt;= n</code></li>
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
