# [898. Bitwise ORs of Subarrays](https://leetcode.com/problems/bitwise-ors-of-subarrays)

## Description
<p>We have an array <code>A</code> of non-negative integers.</p>



<p>For every (contiguous) subarray <code>B =&nbsp;[A[i], A[i+1], ..., A[j]]</code> (with <code>i &lt;= j</code>), we take the bitwise OR of all the elements in <code>B</code>, obtaining a result <font face="monospace"><code>A[i] | A[i+1] | ... | A[j]</code>.</font></p>



<p>Return the number of possible&nbsp;results.&nbsp; (Results that occur more than once are only counted once in the final answer.)</p>



<p>&nbsp;</p>



<div>

<p><strong>Example 1:</strong></p>



<pre>

<strong>Input: </strong><span id="example-input-1-1">[0]</span>

<strong>Output: </strong><span id="example-output-1">1</span>

<strong>Explanation: </strong>

There is only one possible result: 0.

</pre>



<div>

<p><strong>Example 2:</strong></p>



<pre>

<strong>Input: </strong><span id="example-input-2-1">[1,1,2]</span>

<strong>Output: </strong><span id="example-output-2">3</span>

<strong>Explanation: </strong>

The possible subarrays are [1], [1], [2], [1, 1], [1, 2], [1, 1, 2].

These yield the results 1, 1, 2, 1, 3, 3.

There are 3 unique values, so the answer is 3.

</pre>



<div>

<p><strong>Example 3:</strong></p>



<pre>

<strong>Input: </strong><span id="example-input-3-1">[1,2,4]</span>

<strong>Output: </strong><span id="example-output-3">6</span>

<strong>Explanation: </strong>

The possible results are 1, 2, 3, 4, 6, and 7.

</pre>

</div>

</div>

</div>



<p>&nbsp;</p>



<p><strong>Note:</strong></p>



<ol>

	<li><code>1 &lt;= A.length &lt;= 50000</code></li>

	<li><code>0 &lt;= A[i] &lt;= 10^9</code></li>

</ol>




## Solutions


<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**
```

```

<!-- tabs:end -->