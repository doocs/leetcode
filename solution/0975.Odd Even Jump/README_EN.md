# [975. Odd Even Jump](https://leetcode.com/problems/odd-even-jump)

## Description
<p>You are given an integer array <code>A</code>.&nbsp; From&nbsp;some starting index, you can make a series of jumps.&nbsp; The (1st, 3rd, 5th, ...)&nbsp;jumps in the series are called <em>odd numbered jumps</em>, and the (2nd, 4th, 6th, ...) jumps in the series are called <em>even numbered jumps</em>.</p>

<p>You may from index <code>i</code>&nbsp;jump forward to index <code><font face="monospace">j</font></code>&nbsp;(with <code>i&nbsp;&lt; j</code>) in the following way:</p>

<ul>
	<li>During odd numbered jumps (ie. jumps 1, 3, 5, ...), you jump to the index <font face="monospace">j</font>&nbsp;such that <code>A[i] &lt;= A[j]</code> and <code>A[j]</code> is the smallest possible value.&nbsp; If there are multiple such indexes <code><font face="monospace">j</font></code>, you can only jump to the <strong>smallest</strong> such index <code><font face="monospace">j</font></code>.</li>
	<li>During even numbered jumps (ie. jumps 2, 4, 6, ...), you jump to the index <font face="monospace">j</font>&nbsp;such that <code>A[i] &gt;= A[j]</code> and <code>A[j]</code> is the largest&nbsp;possible value.&nbsp; If there are multiple such indexes <code><font face="monospace">j</font></code>, you can only jump to the <strong>smallest</strong> such index <code><font face="monospace">j</font></code>.</li>
	<li>(It may be the case that for some index <code><font face="monospace">i</font>,</code> there are no legal jumps.)</li>
</ul>

<p>A starting index is <em>good</em> if, starting from that index, you can reach the end of the array (index <code>A.length - 1</code>) by jumping some number of times (possibly 0 or more than once.)</p>

<p>Return the number of good starting indexes.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>
<strong>Input: </strong><span id="example-input-1-1">[10,13,12,14,15]</span>
<strong>Output: </strong><span id="example-output-1">2</span>
<strong>Explanation: </strong>
From starting index i = 0, we can jump to i = 2 (since A[2] is the smallest among A[1], A[2], A[3], A[4] that is greater or equal to A[0]), then we can&#39;t jump any more.
From starting index i = 1 and i = 2, we can jump to i = 3, then we can&#39;t jump any more.
From starting index i = 3, we can jump to i = 4, so we&#39;ve reached the end.
From starting index i = 4, we&#39;ve reached the end already.
In total, there are 2 different starting indexes (i = 3, i = 4) where we can reach the end with some number of jumps.
</pre>

<div>
<p><strong>Example 2:</strong></p>

<pre>
<strong>Input: </strong><span id="example-input-2-1">[2,3,1,1,4]</span>
<strong>Output: </strong><span id="example-output-2">3</span>
<strong>Explanation: </strong>
From starting index i = 0, we make jumps to i = 1, i = 2, i = 3:

During our 1st jump (odd numbered), we first jump to i = 1 because A[1] is the smallest value in (A[1], A[2], A[3], A[4]) that is greater than or equal to A[0].

During our 2nd jump (even numbered), we jump from i = 1 to i = 2 because A[2] is the largest value in (A[2], A[3], A[4]) that is less than or equal to A[1].  A[3] is also the largest value, but 2 is a smaller index, so we can only jump to i = 2 and not i = 3.

During our 3rd jump (odd numbered), we jump from i = 2 to i = 3 because A[3] is the smallest value in (A[3], A[4]) that is greater than or equal to A[2].

We can&#39;t jump from i = 3 to i = 4, so the starting index i = 0 is not good.

In a similar manner, we can deduce that:
From starting index i = 1, we jump to i = 4, so we reach the end.
From starting index i = 2, we jump to i = 3, and then we can&#39;t jump anymore.
From starting index i = 3, we jump to i = 4, so we reach the end.
From starting index i = 4, we are already at the end.
In total, there are 3 different starting indexes (i = 1, i = 3, i = 4) where we can reach the end with some number of jumps.
</pre>

<div>
<p><strong>Example 3:</strong></p>

<pre>
<strong>Input: </strong><span id="example-input-3-1">[5,1,3,4,2]</span>
<strong>Output: </strong><span id="example-output-3">3</span>
<strong>Explanation: </strong>
We can reach the end from starting indexes 1, 2, and 4.
</pre>
</div>
</div>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 20000</code></li>
	<li><code>0 &lt;= A[i] &lt; 100000</code></li>
</ol>


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

