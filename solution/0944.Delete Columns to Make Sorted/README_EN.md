# [944. Delete Columns to Make Sorted](https://leetcode.com/problems/delete-columns-to-make-sorted)

## Description
<p>We are given an array&nbsp;<code>A</code> of <code>N</code> lowercase letter strings, all of the same length.</p>

<p>Now, we may choose any set of deletion indices, and for each string, we delete all the characters in those indices.</p>

<p>For example, if we have an array <code>A = [&quot;</code><code>abcdef</code><code>&quot;,&quot;uvwxyz&quot;]</code> and deletion indices <code>{0, 2, 3}</code>, then the final array after deletions is <code>[&quot;bef&quot;, &quot;vyz&quot;]</code>,&nbsp;and the remaining columns of <code>A</code> are&nbsp;<code>[&quot;b&quot;</code><code>,&quot;</code><code>v&quot;]</code>, <code>[&quot;e&quot;,&quot;y&quot;]</code>, and <code>[&quot;f&quot;,&quot;z&quot;]</code>.&nbsp; (Formally, the <code>c</code>-th column is <code>[A[0][c], A[1][c], ..., A[A.length-1][c]]</code>.)</p>

<p>Suppose we chose a set of deletion indices <code>D</code> such that after deletions, each remaining column in A is in <strong>non-decreasing</strong> sorted order.</p>

<p>Return the minimum possible value of <code>D.length</code>.</p>

<p>&nbsp;</p>

<div>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input: </strong><span id="example-input-1-1">[&quot;cba&quot;,&quot;daf&quot;,&quot;ghi&quot;]</span>
<strong>Output: </strong><span id="example-output-1">1</span>
<strong>Explanation: </strong>
After choosing D = {1}, each column [&quot;c&quot;,&quot;d&quot;,&quot;g&quot;] and [&quot;a&quot;,&quot;f&quot;,&quot;i&quot;] are in non-decreasing sorted order.
If we chose D = {}, then a column [&quot;b&quot;,&quot;a&quot;,&quot;h&quot;] would not be in non-decreasing sorted order.
</pre>

<div>
<p><strong>Example 2:</strong></p>

<pre>
<strong>Input: </strong><span id="example-input-2-1">[&quot;a&quot;,&quot;b&quot;]</span>
<strong>Output: </strong><span id="example-output-2">0</span>
<strong>Explanation: </strong>D = {}
</pre>

<div>
<p><strong>Example 3:</strong></p>

<pre>
<strong>Input: </strong><span id="example-input-3-1">[&quot;zyx&quot;,&quot;wvu&quot;,&quot;tsr&quot;]</span>
<strong>Output: </strong><span id="example-output-3">3</span>
<strong>Explanation: </strong>D = {0, 1, 2}
</pre>

<p>&nbsp;</p>

<p><strong><span>Note:</span></strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 100</code></li>
	<li><code>1 &lt;= A[i].length &lt;= 1000</code></li>
</ol>
</div>
</div>
</div>



## Solution
<!-- Type common method here -->


### Python3
<!-- Type special method here -->

```python

```

### Java
<!-- Type special method here -->

```java

```

### ...
```

```

