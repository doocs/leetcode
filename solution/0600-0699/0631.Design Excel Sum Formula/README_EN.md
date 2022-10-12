# [631. Design Excel Sum Formula](https://leetcode.com/problems/design-excel-sum-formula)

[中文文档](/solution/0600-0699/0631.Design%20Excel%20Sum%20Formula/README.md)

## Description

<p>Design the basic function of <strong>Excel</strong> and implement the function of the sum formula.</p>

<p>Implement the <code>Excel</code> class:</p>

<ul>
	<li><code>Excel(int height, char width)</code> Initializes the object with the <code>height</code> and the <code>width</code> of the sheet. The sheet is an integer matrix <code>mat</code> of size <code>height x width</code> with the row index in the range <code>[1, height]</code> and the column index in the range <code>[&#39;A&#39;, width]</code>. All the values should be <strong>zero</strong> initially.</li>
	<li><code>void set(int row, char column, int val)</code> Changes the value at <code>mat[row][column]</code> to be <code>val</code>.</li>
	<li><code>int get(int row, char column)</code> Returns the value at <code>mat[row][column]</code>.</li>
	<li><code>int sum(int row, char column, List&lt;String&gt; numbers)</code> Sets the value at <code>mat[row][column]</code> to be the sum of cells represented by <code>numbers</code> and returns the value at <code>mat[row][column]</code>. This sum formula <strong>should exist</strong> until this cell is overlapped by another value or another sum formula. <code>numbers[i]</code> could be on the format:
	<ul>
		<li><code>&quot;ColRow&quot;</code> that represents a single cell.
		<ul>
			<li>For example, <code>&quot;F7&quot;</code> represents the cell <code>mat[7][&#39;F&#39;]</code>.</li>
		</ul>
		</li>
		<li><code>&quot;ColRow1:ColRow2&quot;</code> that represents a range of cells. The range will always be a rectangle where <code>&quot;ColRow1&quot;</code> represent the position of the top-left cell, and <code>&quot;ColRow2&quot;</code> represents the position of the bottom-right cell.
		<ul>
			<li>For example, <code>&quot;B3:F7&quot;</code> represents the cells <code>mat[i][j]</code> for <code>3 &lt;= i &lt;= 7</code> and <code>&#39;B&#39; &lt;= j &lt;= &#39;F&#39;</code>.</li>
		</ul>
		</li>
	</ul>
	</li>
</ul>

<p><strong>Note:</strong> You could assume that there will not be any circular sum reference.</p>

<ul>
	<li>For example, <code>mat[1][&#39;A&#39;] == sum(1, &quot;B&quot;)</code> and <code>mat[1][&#39;B&#39;] == sum(1, &quot;A&quot;)</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;Excel&quot;, &quot;set&quot;, &quot;sum&quot;, &quot;set&quot;, &quot;get&quot;]
[[3, &quot;C&quot;], [1, &quot;A&quot;, 2], [3, &quot;C&quot;, [&quot;A1&quot;, &quot;A1:B2&quot;]], [2, &quot;B&quot;, 2], [3, &quot;C&quot;]]
<strong>Output</strong>
[null, null, 4, null, 6]

<strong>Explanation</strong>
Excel excel = new Excel(3, &quot;C&quot;);
 // construct a 3*3 2D array with all zero.
 //   A B C
 // 1 0 0 0
 // 2 0 0 0
 // 3 0 0 0
excel.set(1, &quot;A&quot;, 2);
 // set mat[1][&quot;A&quot;] to be 2.
 //   A B C
 // 1 2 0 0
 // 2 0 0 0
 // 3 0 0 0
excel.sum(3, &quot;C&quot;, [&quot;A1&quot;, &quot;A1:B2&quot;]); // return 4
 // set mat[3][&quot;C&quot;] to be the sum of value at mat[1][&quot;A&quot;] and the values sum of the rectangle range whose top-left cell is mat[1][&quot;A&quot;] and bottom-right cell is mat[2][&quot;B&quot;].
 //   A B C
 // 1 2 0 0
 // 2 0 0 0
 // 3 0 0 4
excel.set(2, &quot;B&quot;, 2);
 // set mat[2][&quot;B&quot;] to be 2. Note mat[3][&quot;C&quot;] should also be changed.
 //   A B C
 // 1 2 0 0
 // 2 0 2 0
 // 3 0 0 6
excel.get(3, &quot;C&quot;); // return 6
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= height &lt;= 26</code></li>
	<li><code>&#39;A&#39; &lt;= width &lt;= &#39;Z&#39;</code></li>
	<li><code>1 &lt;= row &lt;= height</code></li>
	<li><code>&#39;A&#39; &lt;= column &lt;= width</code></li>
	<li><code>-100 &lt;= val &lt;= 100</code></li>
	<li><code>1 &lt;= numbers.length &lt;= 5</code></li>
	<li><code>numbers[i]</code> has the format <code>&quot;ColRow&quot;</code> or <code>&quot;ColRow1:ColRow2&quot;</code>.</li>
	<li>At most <code>100</code> calls will be made to <code>set</code>, <code>get</code>, and <code>sum</code>.</li>
</ul>

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
