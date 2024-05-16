---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3082.Find%20the%20Sum%20of%20the%20Power%20of%20All%20Subsequences/README_EN.md
rating: 2241
source: Biweekly Contest 126 Q4
tags:
    - Array
    - Dynamic Programming
---

# [3082. Find the Sum of the Power of All Subsequences](https://leetcode.com/problems/find-the-sum-of-the-power-of-all-subsequences)

[中文文档](/solution/3000-3099/3082.Find%20the%20Sum%20of%20the%20Power%20of%20All%20Subsequences/README.md)

## Description

<p>You are given an integer array <code>nums</code> of length <code>n</code> and a <strong>positive</strong> integer <code>k</code>.</p>

<p>The <strong>power</strong> of an array of integers is defined as the number of <span data-keyword="subsequence-array">subsequences</span> with their sum <strong>equal</strong> to <code>k</code>.</p>

<p>Return <em>the <strong>sum</strong> of <strong>power</strong> of all subsequences of</em> <code>nums</code><em>.</em></p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> nums = [1,2,3], k = 3 </span></p>

<p><strong>Output: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> 6 </span></p>

<p><strong>Explanation:</strong></p>

<p>There are <code>5</code> subsequences of nums with non-zero power:</p>

<ul>
	<li>The subsequence <code>[<u><strong>1</strong></u>,<u><strong>2</strong></u>,<u><strong>3</strong></u>]</code> has <code>2</code> subsequences with <code>sum == 3</code>: <code>[1,2,<u>3</u>]</code> and <code>[<u>1</u>,<u>2</u>,3]</code>.</li>
	<li>The subsequence <code>[<u><strong>1</strong></u>,2,<u><strong>3</strong></u>]</code> has <code>1</code> subsequence with <code>sum == 3</code>: <code>[1,2,<u>3</u>]</code>.</li>
	<li>The subsequence <code>[1,<u><strong>2</strong></u>,<u><strong>3</strong></u>]</code> has <code>1</code> subsequence with <code>sum == 3</code>: <code>[1,2,<u>3</u>]</code>.</li>
	<li>The subsequence <code>[<u><strong>1</strong></u>,<u><strong>2</strong></u>,3]</code> has <code>1</code> subsequence with <code>sum == 3</code>: <code>[<u>1</u>,<u>2</u>,3]</code>.</li>
	<li>The subsequence <code>[1,2,<u><strong>3</strong></u>]</code> has <code>1</code> subsequence with <code>sum == 3</code>: <code>[1,2,<u>3</u>]</code>.</li>
</ul>

<p>Hence the answer is <code>2 + 1 + 1 + 1 + 1 = 6</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> nums = [2,3,3], k = 5 </span></p>

<p><strong>Output: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> 4 </span></p>

<p><strong>Explanation:</strong></p>

<p>There are <code>3</code> subsequences of nums with non-zero power:</p>

<ul>
	<li>The subsequence <code>[<u><strong>2</strong></u>,<u><strong>3</strong></u>,<u><strong>3</strong></u>]</code> has 2 subsequences with <code>sum == 5</code>: <code>[<u>2</u>,3,<u>3</u>]</code> and <code>[<u>2</u>,<u>3</u>,3]</code>.</li>
	<li>The subsequence <code>[<u><strong>2</strong></u>,3,<u><strong>3</strong></u>]</code> has 1 subsequence with <code>sum == 5</code>: <code>[<u>2</u>,3,<u>3</u>]</code>.</li>
	<li>The subsequence <code>[<u><strong>2</strong></u>,<u><strong>3</strong></u>,3]</code> has 1 subsequence with <code>sum == 5</code>: <code>[<u>2</u>,<u>3</u>,3]</code>.</li>
</ul>

<p>Hence the answer is <code>2 + 1 + 1 = 4</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> nums = [1,2,3], k = 7 </span></p>

<p><strong>Output: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> 0 </span></p>

<p><strong>Explanation:&nbsp;</strong>There exists no subsequence with sum <code>7</code>. Hence all subsequences of nums have <code>power = 0</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
