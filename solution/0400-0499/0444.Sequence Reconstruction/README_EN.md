# [444. Sequence Reconstruction](https://leetcode.com/problems/sequence-reconstruction)

[中文文档](/solution/0400-0499/0444.Sequence%20Reconstruction/README.md)

## Description

<p>Check whether the original sequence <code>org</code> can be uniquely reconstructed from the sequences in <code>seqs</code>. The <code>org</code> sequence is a permutation of the integers from 1 to n, with 1 &le; n &le; 10<sup>4</sup>. Reconstruction means building a shortest common supersequence of the sequences in <code>seqs</code> (i.e., a shortest sequence so that all sequences in <code>seqs</code> are subsequences of it). Determine whether there is only one sequence that can be reconstructed from <code>seqs</code> and it is the <code>org</code> sequence.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> org = [1,2,3], seqs = [[1,2],[1,3]]
<strong>Output:</strong> false
<strong>Explanation:</strong> [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> org = [1,2,3], seqs = [[1,2]]
<strong>Output:</strong> false
<strong>Explanation:</strong> The reconstructed sequence can only be [1,2].
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> org = [1,2,3], seqs = [[1,2],[1,3],[2,3]]
<strong>Output:</strong> true
<strong>Explanation:</strong> The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> org = [4,1,5,2,6,3], seqs = [[5,2,6,3],[4,1,5,2]]
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^4</code></li>
	<li><code>org</code> is a permutation of {1,2,...,n}.</li>
	<li><code>1 &lt;= segs[i].length &lt;= 10^5</code></li>
	<li><code>seqs[i][j]</code>&nbsp;fits in a 32-bit signed integer.</li>
</ul>

<p>&nbsp;</p>

<p><b><font color="red">UPDATE (2017/1/8):</font></b><br />
The <i>seqs</i> parameter had been changed to a list of list of strings (instead of a 2d array of strings). Please reload the code definition to get the latest changes.</p>


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
