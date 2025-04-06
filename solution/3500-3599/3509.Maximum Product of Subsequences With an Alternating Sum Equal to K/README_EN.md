---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3509.Maximum%20Product%20of%20Subsequences%20With%20an%20Alternating%20Sum%20Equal%20to%20K/README_EN.md
---

<!-- problem:start -->

# [3509. Maximum Product of Subsequences With an Alternating Sum Equal to K](https://leetcode.com/problems/maximum-product-of-subsequences-with-an-alternating-sum-equal-to-k)

[中文文档](/solution/3500-3599/3509.Maximum%20Product%20of%20Subsequences%20With%20an%20Alternating%20Sum%20Equal%20to%20K/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and two integers, <code>k</code> and <code>limit</code>. Your task is to find a non-empty <strong><span data-keyword="subsequence-array">subsequence</span></strong> of <code>nums</code> that:</p>

<ul>
	<li>Has an <strong>alternating sum</strong> equal to <code>k</code>.</li>
	<li><strong>Maximizes</strong> the product of all its numbers <em>without the product exceeding</em> <code>limit</code>.</li>
</ul>

<p>Return the <em>product</em> of the numbers in such a subsequence. If no subsequence satisfies the requirements, return -1.</p>

<p>The <strong>alternating sum</strong> of a <strong>0-indexed</strong> array is defined as the <strong>sum</strong> of the elements at <strong>even</strong> indices <strong>minus</strong> the <strong>sum</strong> of the elements at <strong>odd</strong> indices.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], k = 2, limit = 10</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>The subsequences with an alternating sum of 2 are:</p>

<ul>
	<li><code>[1, 2, 3]</code>

    <ul>
    	<li>Alternating Sum: <code>1 - 2 + 3 = 2</code></li>
    	<li>Product: <code>1 * 2 * 3 = 6</code></li>
    </ul>
    </li>
    <li><code>[2]</code>
    <ul>
    	<li>Alternating Sum: 2</li>
    	<li>Product: 2</li>
    </ul>
    </li>

</ul>

<p>The maximum product within the limit is 6.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,2,3], k = -5, limit = 12</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>A subsequence with an alternating sum of exactly -5 does not exist.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,2,3,3], k = 0, limit = 9</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<p>The subsequences with an alternating sum of 0 are:</p>

<ul>
	<li><code>[2, 2]</code>

    <ul>
    	<li>Alternating Sum: <code>2 - 2 = 0</code></li>
    	<li>Product: <code>2 * 2 = 4</code></li>
    </ul>
    </li>
    <li><code>[3, 3]</code>
    <ul>
    	<li>Alternating Sum: <code>3 - 3 = 0</code></li>
    	<li>Product: <code>3 * 3 = 9</code></li>
    </ul>
    </li>
    <li><code>[2, 2, 3, 3]</code>
    <ul>
    	<li>Alternating Sum: <code>2 - 2 + 3 - 3 = 0</code></li>
    	<li>Product: <code>2 * 2 * 3 * 3 = 36</code></li>
    </ul>
    </li>

</ul>

<p>The subsequence <code>[2, 2, 3, 3]</code> has the greatest product with an alternating sum equal to <code>k</code>, but <code>36 &gt; 9</code>. The next greatest product is 9, which is within the limit.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 150</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 12</code></li>
	<li><code>-10<sup>5</sup> &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= limit &lt;= 5000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

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
