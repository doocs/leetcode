---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3224.Minimum%20Array%20Changes%20to%20Make%20Differences%20Equal/README.md
---

<!-- problem:start -->

# [3224. 使差值相等的最少数组改动次数](https://leetcode.cn/problems/minimum-array-changes-to-make-differences-equal)

[English Version](/solution/3200-3299/3224.Minimum%20Array%20Changes%20to%20Make%20Differences%20Equal/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;，<code>n</code>&nbsp;是 <strong>偶数</strong>&nbsp;，同时给你一个整数&nbsp;<code>k</code>&nbsp;。</p>

<p>你可以对数组进行一些操作。每次操作中，你可以将数组中 <strong>任一</strong>&nbsp;元素替换为 <code>0</code>&nbsp;到 <code>k</code>&nbsp;之间的<strong>&nbsp;任一</strong>&nbsp;整数。</p>

<p>执行完所有操作以后，你需要确保最后得到的数组满足以下条件：</p>

<ul>
	<li>存在一个整数 <code>X</code>&nbsp;，满足对于所有的&nbsp;<code>(0 &lt;= i &lt; n)</code>&nbsp;都有&nbsp;<code>abs(a[i] - a[n - i - 1]) = X</code>&nbsp;。</li>
</ul>

<p>请你返回满足以上条件 <strong>最少</strong>&nbsp;修改次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,0,1,2,4,3], k = 4</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong><br />
我们可以执行以下操作：</p>

<ul>
	<li>将&nbsp;<code>nums[1]</code>&nbsp;变为 2 ，结果数组为&nbsp;<code>nums = [1,<em><strong>2</strong></em>,1,2,4,3]</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[3]</code>&nbsp;变为 3 ，结果数组为&nbsp;<code>nums = [1,2,1,<em><strong>3</strong></em>,4,3]</code>&nbsp;。</li>
</ul>

<p>整数&nbsp;<code>X</code>&nbsp;为 2 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [0,1,2,3,3,6,5,4], k = 6</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong><br />
我们可以执行以下操作：</p>

<ul>
	<li>将&nbsp;<code>nums[3]</code>&nbsp;变为 0 ，结果数组为&nbsp;<code>nums = [0,1,2,<em><strong>0</strong></em>,3,6,5,4]</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[4]</code>&nbsp;变为 4 ，结果数组为&nbsp;<code>nums = [0,1,2,0,<em><strong>4</strong></em>,6,5,4]</code>&nbsp;。</li>
</ul>

<p>整数 <code>X</code>&nbsp;为 4 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>n</code>&nbsp;是偶数。</li>
	<li><code>0 &lt;= nums[i] &lt;= k &lt;= 10<sup>5</sup></code></li>
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
