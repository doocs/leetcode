# [3106. Lexicographically Smallest String After Operations With Constraint](https://leetcode.com/problems/lexicographically-smallest-string-after-operations-with-constraint)

[中文文档](/solution/3100-3199/3106.Lexicographically%20Smallest%20String%20After%20Operations%20With%20Constraint/README.md)

<!-- tags: -->

## Description

<p>You are given a string <code>s</code> and an integer <code>k</code>.</p>

<p>Define a function <code>distance(s<sub>1</sub>, s<sub>2</sub>)</code> between two strings <code>s<sub>1</sub></code> and <code>s<sub>2</sub></code> of the same length <code>n</code> as:</p>

<ul>
	<li>The<strong> sum</strong> of the <strong>minimum distance</strong> between <code>s<sub>1</sub>[i]</code> and <code>s<sub>2</sub>[i]</code> when the characters from <code>&#39;a&#39;</code> to <code>&#39;z&#39;</code> are placed in a <strong>cyclic</strong> order, for all <code>i</code> in the range <code>[0, n - 1]</code>.</li>
</ul>

<p>For example, <code>distance(&quot;ab&quot;, &quot;cd&quot;) == 4</code>, and <code>distance(&quot;a&quot;, &quot;z&quot;) == 1</code>.</p>

<p>You can <strong>change</strong> any letter of <code>s</code> to <strong>any</strong> other lowercase English letter, <strong>any</strong> number of times.</p>

<p>Return a string denoting the <strong><span data-keyword="lexicographically-smaller-string">lexicographically smallest</span></strong> string <code>t</code> you can get after some changes, such that <code>distance(s, t) &lt;= k</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;zbbz&quot;, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;aaaz&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>Change <code>s</code> to <code>&quot;aaaz&quot;</code>. The distance between <code>&quot;zbbz&quot;</code> and <code>&quot;aaaz&quot;</code> is equal to <code>k = 3</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;xaxcd&quot;, k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;aawcd&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>The distance between &quot;xaxcd&quot; and &quot;aawcd&quot; is equal to k = 4.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;lol&quot;, k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;lol&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>It&#39;s impossible to change any character as <code>k = 0</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>0 &lt;= k &lt;= 2000</code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
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
