---
comments: true
difficulty: Hard
---

<!-- problem:start -->

# [4082. Minimum Operations to Stabilize a Binary Tree 🔒](https://leetcode.com/problems/minimum-operations-to-stabilize-a-binary-tree)

[中文文档](/solution/4000-4099/4082.Minimum%20Operations%20to%20Stabilize%20a%20Binary%20Tree/README.md)

## Description

<!-- description:start -->

<p>You are given the <code>root</code> of a <strong>binary tree</strong>. Each node contains an integer value representing its current state.</p>

<p>A binary tree is considered <strong>stable</strong> if for every node <code>u</code>:</p>

<ul>
	<li>If <code>u.left</code> exists, then <code>|u.val - u.left.val| &lt;= 1</code></li>
	<li>If <code>u.right</code> exists, then <code>|u.val - u.right.val| &lt;= 1</code></li>
</ul>

<p>In one operation, you may choose any node and <strong>increase</strong> or <strong>decrease</strong> its value by 1.</p>

<p>Return the <strong>minimum</strong> number of operations required to make the entire binary tree <strong>stable</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">root = [5,8,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4082.Minimum%20Operations%20to%20Stabilize%20a%20Binary%20Tree/images/screenshot-2026-02-16-at-21240pm.png" style="width: 180px; height: 153px;" /></p>

<ul>
	<li>Let <code>u</code> be the root node with value 5. Decrease the left child value from 8 to 6 using 2 operations. Then <code>|u.val - u.left.val| = |5 - 6| = 1</code>.</li>
	<li>Increase the right child value from 3 to 4 using 1 operation. Then <code>|u.val - u.right.val| = |5 - 4| = 1</code>.</li>
	<li>Thus, the answer is <code>2 + 1 = 3</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">root = [2,-1,-4]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4082.Minimum%20Operations%20to%20Stabilize%20a%20Binary%20Tree/images/screenshot-2026-02-16-at-21340pm.png" style="width: 170px; height: 148px;" /></p>

<ul>
	<li>Let <code>u</code> be the root node with value 2. Decrease the value of <code>u</code> from 2 to &minus;2 using 4 operations.</li>
	<li>Decrease the right child value from &minus;4 to &minus;3 using 1 operation. Then <code>|u.val - u.right.val| = |-2 - (&minus;3)| = 1</code>.</li>
	<li>Thus, the answer is <code>4 + 1 = 5</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">root = [0,-2,2,-3,null,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p><strong>​​​​​​​</strong><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4082.Minimum%20Operations%20to%20Stabilize%20a%20Binary%20Tree/images/screenshot-2026-02-16-at-21826pm.png" style="width: 180px; height: 201px;" /><strong>​​​​​​​​​​​​​​</strong></p>

<ul>
	<li>Let <code>u</code> be the root node with value 0. Increase the left child value from &minus;2 to &minus;1 using 1 operation, and decrease the right child value from 2 to 1 using 1 operation.</li>
	<li>Now <code>|u.val - u.left.val| = |0 - (&minus;1)| = 1</code> and <code>|u.val - u.right.val| = |0 - 1| = 1</code>.</li>
	<li>Let <code>v</code> be the left child of <code>u</code>, now having value &minus;1. Increase its left child from &minus;3 to &minus;2 using 1 operation so that <code>|v.val - v.left.val| = |&minus;1 - (&minus;2)| = 1</code>.</li>
	<li>The right subtree already satisfies the condition since <code>|1 - 1| = 0</code>.</li>
	<li>Thus, the answer is <code>1 + 1 + 1 = 3</code>.​​​​​​​</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 5 * 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
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
