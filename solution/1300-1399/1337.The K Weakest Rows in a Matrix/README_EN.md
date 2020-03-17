# [1337. The K Weakest Rows in a Matrix](https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix)

## Description
<p>Given a <code>m&nbsp;* n</code>&nbsp;matrix <code>mat</code> of <em>ones</em>&nbsp;(representing soldiers) and <em>zeros</em>&nbsp;(representing civilians), return the indexes of the <code>k</code> weakest rows in the matrix ordered from the weakest to the strongest.</p>

<p>A row <em><strong>i</strong></em> is weaker than row <em><strong>j</strong></em>, if the number of soldiers in row <em><strong>i</strong></em> is less than the number of soldiers in row <em><strong>j</strong></em>, or they have the same number of soldiers but <em><strong>i</strong></em> is less than <em><strong>j</strong></em>. Soldiers are <strong>always</strong> stand in the frontier of a row, that is, always <em>ones</em>&nbsp;may appear first and then <em>zeros</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> mat = 
[[1,1,0,0,0],
 [1,1,1,1,0],
 [1,0,0,0,0],
 [1,1,0,0,0],
 [1,1,1,1,1]], 
k = 3
<strong>Output:</strong> [2,0,3]
<strong>Explanation:</strong> 
The number of soldiers for each row is: 
row 0 -&gt; 2 
row 1 -&gt; 4 
row 2 -&gt; 1 
row 3 -&gt; 2 
row 4 -&gt; 5 
Rows ordered from the weakest to the strongest are [2,0,3,1,4]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> mat = 
[[1,0,0,0],
&nbsp;[1,1,1,1],
&nbsp;[1,0,0,0],
&nbsp;[1,0,0,0]], 
k = 2
<strong>Output:</strong> [0,2]
<strong>Explanation:</strong> 
The number of soldiers for each row is: 
row 0 -&gt; 1 
row 1 -&gt; 4 
row 2 -&gt; 1 
row 3 -&gt; 1 
Rows ordered from the weakest to the strongest are [0,2,3,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code><font face="monospace">2 &lt;= n, m &lt;= 100</font></code></li>
	<li><code>1 &lt;= k &lt;= m</code></li>
	<li><code>matrix[i][j]</code> is either 0 <strong>or</strong> 1.</li>
</ul>


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

