# [1001. Grid Illumination](https://leetcode.com/problems/grid-illumination)

[中文文档](/solution/1000-1099/1001.Grid%20Illumination/README.md)

## Description
<p>On a <code>N x N</code> grid of cells, each cell <code>(x, y)</code> with <code>0 &lt;= x &lt; N</code> and <code>0 &lt;= y &lt; N</code> has a lamp.</p>



<p>Initially, some number of lamps are on.&nbsp; <code>lamps[i]</code> tells us the location of the <code>i</code>-th lamp that is on.&nbsp; Each lamp that is on illuminates every square on its x-axis, y-axis, and both diagonals (similar to a Queen in chess).</p>



<p>For the i-th query&nbsp;<code>queries[i] = (x, y)</code>, the answer to the query is 1 if the cell (x, y) is illuminated, else 0.</p>



<p>After each query <code>(x, y)</code> [in the order given by <code>queries</code>], we turn off any&nbsp;lamps that are at cell <code>(x, y)</code>&nbsp;or are adjacent 8-directionally (ie., share a corner or edge with cell <code>(x, y)</code>.)</p>



<p>Return an array of answers.&nbsp; Each&nbsp;value <code>answer[i]</code> should be equal to the answer of the <code>i</code>-th query <code>queries[i]</code>.</p>



<p>&nbsp;</p>



<p><strong>Example 1:</strong></p>



<pre>

<strong>Input: </strong>N = <span id="example-input-1-1">5</span>, lamps = <span id="example-input-1-2">[[0,0],[4,4]]</span>, queries = <span id="example-input-1-3">[[1,1],[1,0]]</span>

<strong>Output: </strong><span id="example-output-1">[1,0]</span>

<strong>Explanation: </strong>

Before performing the first query we have both lamps [0,0] and [4,4] on.

The grid representing which cells are lit looks like this, where [0,0] is the top left corner, and [4,4] is the bottom right corner:

1 1 1 1 1

1 1 0 0 1

1 0 1 0 1

1 0 0 1 1

1 1 1 1 1

Then the query at [1, 1] returns 1 because the cell is lit.  After this query, the lamp at [0, 0] turns off, and the grid now looks like this:

1 0 0 0 1

0 1 0 0 1

0 0 1 0 1

0 0 0 1 1

1 1 1 1 1

Before performing the second query we have only the lamp [4,4] on.  Now the query at [1,0] returns 0, because the cell is no longer lit.

</pre>



<p>&nbsp;</p>



<p><strong>Note:</strong></p>



<ol>

	<li><code>1 &lt;= N &lt;= 10^9</code></li>

	<li><code>0 &lt;= lamps.length &lt;= 20000</code></li>

	<li><code>0 &lt;= queries.length &lt;= 20000</code></li>

	<li><code>lamps[i].length == queries[i].length == 2</code></li>

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
<!-- tabs:end -->