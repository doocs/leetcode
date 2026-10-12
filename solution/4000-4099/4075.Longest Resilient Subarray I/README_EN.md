---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4075. Longest Resilient Subarray I](https://leetcode.com/problems/longest-resilient-subarray-i)

[中文文档](/solution/4000-4099/4075.Longest%20Resilient%20Subarray%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>A <span data-keyword="subarray-nonempty">subarray</span> is <strong>resilient</strong> if, for <strong>every</strong> position in it, deleting the element at that position leaves the <strong>remaining</strong> elements with a <strong>sum divisible</strong> by <code>k</code>.</p>

<p>For a subarray of length 1, the remaining sum is 0, which is divisible by <code>k</code>.</p>

<p>Return the length of the <strong>longest resilient subarray</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4,6,3], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The subarray <code>[2, 4, 6]</code> sums to 12. Deleting 2, 4, or 6 leaves a sum of 10, 8, or 6, respectively, each of which is divisible by 2. Therefore, the answer is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,4,7,1], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The subarray <code>[1, 4, 7, 1]</code> sums to 13. Deleting the first 1, the 4, the 7, or the last 1 leaves a sum of 12, 9, 6, or 12, respectively, each of which is divisible by 3. Therefore, the answer is 4.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,5,4,8], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The subarray <code>[5, 5]</code> is not resilient, since deleting either 5 leaves a sum of 5, which is not divisible by 4.</p>

<p>The subarray <code>[4, 8]</code> sums to 12. Deleting 4 or 8 leaves a sum of 8 or 4, respectively, each of which is divisible by 4. Therefore, the answer is 2.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2500</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
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
