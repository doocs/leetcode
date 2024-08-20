---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3261.Count%20Substrings%20That%20Satisfy%20K-Constraint%20II/README_EN.md
---

<!-- problem:start -->

# [3261. Count Substrings That Satisfy K-Constraint II](https://leetcode.com/problems/count-substrings-that-satisfy-k-constraint-ii)

[中文文档](/solution/3200-3299/3261.Count%20Substrings%20That%20Satisfy%20K-Constraint%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>binary</strong> string <code>s</code> and an integer <code>k</code>.</p>

<p>You are also given a 2D integer array <code>queries</code>, where <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code>.</p>

<p>A <strong>binary string</strong> satisfies the <strong>k-constraint</strong> if <strong>either</strong> of the following conditions holds:</p>

<ul>
	<li>The number of <code>0</code>&#39;s in the string is at most <code>k</code>.</li>
	<li>The number of <code>1</code>&#39;s in the string is at most <code>k</code>.</li>
</ul>

<p>Return an integer array <code>answer</code>, where <code>answer[i]</code> is the number of <span data-keyword="substring-nonempty">substrings</span> of <code>s[l<sub>i</sub>..r<sub>i</sub>]</code> that satisfy the <strong>k-constraint</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;0001111&quot;, k = 2, queries = [[0,6]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[26]</span></p>

<p><strong>Explanation:</strong></p>

<p>For the query <code>[0, 6]</code>, all substrings of <code>s[0..6] = &quot;0001111&quot;</code> satisfy the k-constraint except for the substrings <code>s[0..5] = &quot;000111&quot;</code> and <code>s[0..6] = &quot;0001111&quot;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;010101&quot;, k = 1, queries = [[0,5],[1,4],[2,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[15,9,3]</span></p>

<p><strong>Explanation:</strong></p>

<p>The substrings of <code>s</code> with a length greater than 3 do not satisfy the k-constraint.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i] == [l<sub>i</sub>, r<sub>i</sub>]</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt; s.length</code></li>
	<li>All queries are distinct.</li>
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
