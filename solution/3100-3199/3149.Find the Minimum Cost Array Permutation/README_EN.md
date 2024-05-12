# [3149. Find the Minimum Cost Array Permutation](https://leetcode.com/problems/find-the-minimum-cost-array-permutation)

[中文文档](/solution/3100-3199/3149.Find%20the%20Minimum%20Cost%20Array%20Permutation/README.md)

<!-- tags: -->

## Description

<p>You are given an array <code>nums</code> which is a <span data-keyword="permutation">permutation</span> of <code>[0, 1, 2, ..., n - 1]</code>. The <strong>score</strong> of any permutation of <code>[0, 1, 2, ..., n - 1]</code> named <code>perm</code> is defined as:</p>

<p><code>score(perm) = |perm[0] - nums[perm[1]]| + |perm[1] - nums[perm[2]]| + ... + |perm[n - 1] - nums[perm[0]]|</code></p>

<p>Return the permutation <code>perm</code> which has the <strong>minimum</strong> possible score. If <em>multiple</em> permutations exist with this score, return the one that is <span data-keyword="lexicographically-smaller-array">lexicographically smallest</span> among them.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,0,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,1,2]</span></p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3149.Find%20the%20Minimum%20Cost%20Array%20Permutation/images/example0gif.gif" style="width: 235px; height: 235px;" /></strong></p>

<p>The lexicographically smallest permutation with minimum cost is <code>[0,1,2]</code>. The cost of this permutation is <code>|0 - 0| + |1 - 2| + |2 - 1| = 2</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,2,1]</span></p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3149.Find%20the%20Minimum%20Cost%20Array%20Permutation/images/example1gif.gif" style="width: 235px; height: 235px;" /></strong></p>

<p>The lexicographically smallest permutation with minimum cost is <code>[0,2,1]</code>. The cost of this permutation is <code>|0 - 1| + |2 - 2| + |1 - 0| = 2</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 14</code></li>
	<li><code>nums</code> is a permutation of <code>[0, 1, 2, ..., n - 1]</code>.</li>
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
