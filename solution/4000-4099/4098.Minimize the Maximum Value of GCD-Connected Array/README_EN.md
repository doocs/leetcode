---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4098. Minimize the Maximum Value of GCD-Connected Array](https://leetcode.com/problems/minimize-the-maximum-value-of-gcd-connected-array)

[中文文档](/solution/4000-4099/4098.Minimize%20the%20Maximum%20Value%20of%20GCD-Connected%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given three integers <code>n</code>, <code>index</code>, and <code>value</code>.</p>

<p>An integer array <code>nums</code> of length <code>n</code> is called <strong>GCD-Connected</strong> if:</p>

<ul>
	<li><code>nums[i] &gt; 0</code> for <code>0 &lt;= i &lt;= n - 1</code>.</li>
	<li><code>nums[i] &lt; nums[i + 1]</code> for <code>0 &lt;= i &lt;= n - 2</code>.</li>
	<li><code>nums[index] = value</code>.</li>
	<li><code><span data-keyword="gcd-function">gcd</span>(nums[i], nums[i + 1]) &gt; 1</code> for <code>0 &lt;= i &lt;= n - 2</code>.</li>
</ul>

<p>Return the <strong>minimum</strong> possible maximum value, <code>nums[n - 1]</code>, among all GCD-Connected arrays <code>nums</code>. If no such array exists, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, index = 2, value = 8</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The GCD-Connected array will look like: ​​​​​​​<code>nums = [?, ?, 8, ?]</code> since <code>nums[2] = 8</code>.</li>
	<li>Since <code>nums[3] &gt; 8</code> and <code>gcd(8, 9) = 1</code>, every valid GCD-Connected array must have <code>nums[3] &gt;= 10</code>.</li>
	<li>One optimal array satisfying all conditions is <code>[2, 4, 8, 10]</code>.</li>
	<li>Thus, the minimum possible value of <code>nums[3]</code> is 10.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, index = 1, value = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The GCD-Connected array will look like: <code>nums = [?, 3]</code> since <code>nums[1] = 3</code>.</li>
	<li>Since we need <code>0 &lt; nums[0] &lt; nums[1] = 3</code>, there are only two possible arrays: <code>[1, 3]</code> and <code>[2, 3]</code>. Neither of them satisfies <code>gcd(nums[0], nums[1]) &gt; 1</code>.</li>
	<li>Therefore, no valid GCD-Connected array exists, and the answer is -1.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, index = 0, value = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The GCD-Connected array will look like: <code>nums = [3, ?]</code> since <code>nums[0] = 3</code>.</li>
	<li>Since <code>nums[1] &gt; 3</code> and <code>gcd(3, 4) = gcd(3, 5) = 1</code>, every valid GCD-Connected array must have <code>nums[1] &gt;= 6</code>.</li>
	<li>One optimal array is <code>[3, 6]</code>.</li>
	<li>Thus, the minimum possible value of <code>nums[1]</code> is 6.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= value &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= index &lt;= n - 1</code></li>
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
