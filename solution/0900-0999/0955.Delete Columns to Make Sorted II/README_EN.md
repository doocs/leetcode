# [955. Delete Columns to Make Sorted II](https://leetcode.com/problems/delete-columns-to-make-sorted-ii)

## Description
<p>We are given an array&nbsp;<code>A</code> of <code>N</code> lowercase letter strings, all of the same length.</p>

<p>Now, we may choose any set of deletion indices, and for each string, we delete all the characters in those indices.</p>

<p>For example, if we have an array <code>A = [&quot;abcdef&quot;,&quot;uvwxyz&quot;]</code> and deletion indices <code>{0, 2, 3}</code>, then the final array after deletions is <code>[&quot;bef&quot;,&quot;vyz&quot;]</code>.</p>

<p>Suppose we chose a set of deletion indices <code>D</code> such that after deletions, the final array has its elements in <strong>lexicographic</strong> order (<code>A[0] &lt;= A[1] &lt;= A[2] ... &lt;= A[A.length - 1]</code>).</p>

<p>Return the minimum possible value of <code>D.length</code>.</p>

<p>&nbsp;</p>

<div>
<div>
<ol>
</ol>
</div>
</div>

<div>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input: </strong><span id="example-input-1-1">[&quot;ca&quot;,&quot;bb&quot;,&quot;ac&quot;]</span>
<strong>Output: </strong><span id="example-output-1">1</span>
<strong>Explanation: </strong>
After deleting the first column, A = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;].
Now A is in lexicographic order (ie. A[0] &lt;= A[1] &lt;= A[2]).
We require at least 1 deletion since initially A was not in lexicographic order, so the answer is 1.
</pre>

<div>
<p><strong>Example 2:</strong></p>

<pre>
<strong>Input: </strong><span>[&quot;xc&quot;,&quot;yb&quot;,&quot;za&quot;]</span>
<strong>Output: </strong><span id="example-output-2">0</span>
<strong>Explanation: </strong>
A is already in lexicographic order, so we don&#39;t need to delete anything.
Note that the rows of A are not necessarily in lexicographic order:
ie. it is NOT necessarily true that (A[0][0] &lt;= A[0][1] &lt;= ...)
</pre>

<div>
<p><strong>Example 3:</strong></p>

<pre>
<strong>Input: </strong><span id="example-input-3-1">[&quot;zyx&quot;,&quot;wvu&quot;,&quot;tsr&quot;]</span>
<strong>Output: </strong><span id="example-output-3">3</span>
<strong>Explanation: </strong>
We have to delete every column.
</pre>

<p>&nbsp;</p>

<div>
<div>
<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 100</code></li>
	<li><code>1 &lt;= A[i].length &lt;= 100</code></li>
</ol>
</div>
</div>
</div>
</div>
</div>



## Solutions


### Python3

```python

```

### Java

```java

```

### ...
```

```
