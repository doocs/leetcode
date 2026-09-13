---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4053. Minimum Operations to Make Every Element Palindromic](https://leetcode.com/problems/minimum-operations-to-make-every-element-palindromic)

[中文文档](/solution/4000-4099/4053.Minimum%20Operations%20to%20Make%20Every%20Element%20Palindromic/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>In one <strong>operation</strong>, you may choose an index <code>i</code> and either increment or decrement <code>nums[i]</code> by 2.</p>

<p>Return the <strong>minimum</strong> number of operations required to make every element in <code>nums</code> a <strong>positive</strong> <span data-keyword="palindrome-integer">palindrome</span>. Different elements may be changed into different palindromic integers.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [10,12,14,16]</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal sequence of operations is:</p>

<ul>
	<li>Decrement <code>nums[0]</code> by 2 once to change it from 10 to 8.</li>
	<li>Decrement <code>nums[1]</code> by 2 twice to change it from 12 to 8.</li>
	<li>Decrement <code>nums[2]</code> by 2 three times to change it from 14 to 8.</li>
	<li>Increment <code>nums[3]</code> by 2 three times to change it from 16 to 22.</li>
</ul>

<p>After <code>1 + 2 + 3 + 3 = 9</code> operations, <code>nums = [8, 8, 8, 22]</code>, and every element is a positive palindromic integer.</p>

<p>It can be shown that fewer than 9 operations cannot achieve this.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [9,10,11,10]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Decrement <code>nums[1]</code> and <code>nums[3]</code> by 2 once each.</p>

<p>After 2 operations, <code>nums = [9, 8, 11, 8]</code>, and every element is a positive palindromic integer.</p>

<p>At least one operation is needed for each of these two elements, so the minimum number of operations is 2.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [125]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Decrement <code>nums[0]</code> by 2 twice to change it from 125 to 121, which is a positive palindromic integer.</p>

<p>A single operation would change it to 123 or 127, neither of which is palindromic. Thus, the minimum number of operations is 2.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
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
