---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3968.Maximum%20Manhattan%20Distance%20After%20All%20Moves/README_EN.md
---

<!-- problem:start -->

# [3968. Maximum Manhattan Distance After All Moves](https://leetcode.com/problems/maximum-manhattan-distance-after-all-moves)

[中文文档](/solution/3900-3999/3968.Maximum%20Manhattan%20Distance%20After%20All%20Moves/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>moves</code> consisting of the characters <code>&#39;U&#39;</code>, <code>&#39;D&#39;</code>, <code>&#39;L&#39;</code>, <code>&#39;R&#39;</code>, and <code>&#39;_&#39;</code>.</p>

<p>Starting from the origin <code>(0, 0)</code>, each character represents one move on a 2D plane:</p>

<ul>
	<li><code>&#39;U&#39;</code>: Move up by 1 unit.</li>
	<li><code>&#39;D&#39;</code>: Move down by 1 unit.</li>
	<li><code>&#39;L&#39;</code>: Move left by 1 unit.</li>
	<li><code>&#39;R&#39;</code>: Move right by 1 unit.</li>
	<li><code>&#39;_&#39;</code>: Can be independently replaced with any one of <code>&#39;U&#39;</code>, <code>&#39;D&#39;</code>, <code>&#39;L&#39;</code>, or <code>&#39;R&#39;</code>.</li>
</ul>

<p>Return the maximum <span data-keyword="manhattan-distance"><strong>Manhattan distance</strong></span> from the origin that can be achieved after all moves have been performed.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">moves = &quot;L_D_&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal choice is:</p>

<ul>
	<li><code>&#39;L&#39;</code>: <code>(0, 0) -&gt; (-1, 0)</code></li>
	<li><code>&#39;_&#39;</code> treated as <code>&#39;D&#39;</code>: <code>(-1, 0) -&gt; (-1, -1)</code></li>
	<li><code>&#39;D&#39;</code>: <code>(-1, -1) -&gt; (-1, -2)</code></li>
	<li><code>&#39;_&#39;</code> treated as <code>&#39;L&#39;</code>: <code>(-1, -2) -&gt; (-2, -2)</code></li>
</ul>

<p>The final Manhattan distance from the origin is <code>|0 - (-2)| + |0 - (-2)| = 4</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">moves = &quot;U_R&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal choice is:</p>

<ul>
	<li><code>&#39;U&#39;</code>: <code>(0, 0) -&gt; (0, 1)</code></li>
	<li><code>&#39;_&#39;</code> treated as <code>&#39;U&#39;</code>: <code>(0, 1) -&gt; (0, 2)</code></li>
	<li><code>&#39;R&#39;</code>: <code>(0, 2) -&gt; (1, 2)</code></li>
</ul>

<p>The final Manhattan distance from the origin is <code>|0 - 1| + |0 - 2| = 3</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= moves.length &lt;= 10<sup>5</sup></code></li>
	<li><code>moves</code> consists of only <code>&#39;U&#39;</code>, <code>&#39;D&#39;</code>, <code>&#39;L&#39;</code>, <code>&#39;R&#39;</code>, and <code>&#39;_&#39;</code>.</li>
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
