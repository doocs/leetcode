---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3361.Shift%20Distance%20Between%20Two%20Strings/README_EN.md
---

<!-- problem:start -->

# [3361. Shift Distance Between Two Strings](https://leetcode.com/problems/shift-distance-between-two-strings)

[中文文档](/solution/3300-3399/3361.Shift%20Distance%20Between%20Two%20Strings/README.md)

## Description

<!-- description:start -->

<p>You are given two strings <code>s</code> and <code>t</code> of the same length, and two integer arrays <code>nextCost</code> and <code>previousCost</code>.</p>

<p>In one operation, you can pick any index <code>i</code> of <code>s</code>, and perform <strong>either one</strong> of the following actions:</p>

<ul>
	<li>Shift <code>s[i]</code> to the next letter in the alphabet. If <code>s[i] == &#39;z&#39;</code>, you should replace it with <code>&#39;a&#39;</code>. This operation costs <code>nextCost[j]</code> where <code>j</code> is the index of <code>s[i]</code> in the alphabet.</li>
	<li>Shift <code>s[i]</code> to the previous letter in the alphabet. If <code>s[i] == &#39;a&#39;</code>, you should replace it with <code>&#39;z&#39;</code>. This operation costs <code>previousCost[j]</code> where <code>j</code> is the index of <code>s[i]</code> in the alphabet.</li>
</ul>

<p>The <strong>shift distance</strong> is the <strong>minimum</strong> total cost of operations required to transform <code>s</code> into <code>t</code>.</p>

<p>Return the <strong>shift distance</strong> from <code>s</code> to <code>t</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abab&quot;, t = &quot;baba&quot;, nextCost = [100,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0], previousCost = [1,100,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>We choose index <code>i = 0</code> and shift <code>s[0]</code> 25 times to the previous character for a total cost of 1.</li>
	<li>We choose index <code>i = 1</code> and shift <code>s[1]</code> 25 times to the next character for a total cost of 0.</li>
	<li>We choose index <code>i = 2</code> and shift <code>s[2]</code> 25 times to the previous character for a total cost of 1.</li>
	<li>We choose index <code>i = 3</code> and shift <code>s[3]</code> 25 times to the next character for a total cost of 0.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;leet&quot;, t = &quot;code&quot;, nextCost = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1], previousCost = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">31</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>We choose index <code>i = 0</code> and shift <code>s[0]</code> 9 times to the previous character for a total cost of 9.</li>
	<li>We choose index <code>i = 1</code> and shift <code>s[1]</code> 10 times to the next character for a total cost of 10.</li>
	<li>We choose index <code>i = 2</code> and shift <code>s[2]</code> 1 time to the previous character for a total cost of 1.</li>
	<li>We choose index <code>i = 3</code> and shift <code>s[3]</code> 11 times to the next character for a total cost of 11.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length == t.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> and <code>t</code> consist only of lowercase English letters.</li>
	<li><code>nextCost.length == previousCost.length == 26</code></li>
	<li><code>0 &lt;= nextCost[i], previousCost[i] &lt;= 10<sup>9</sup></code></li>
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
