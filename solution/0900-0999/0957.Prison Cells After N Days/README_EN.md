# [957. Prison Cells After N Days](https://leetcode.com/problems/prison-cells-after-n-days)

[中文文档](/solution/0900-0999/0957.Prison%20Cells%20After%20N%20Days/README.md)

## Description

<p>There are <code>8</code> prison cells in a row and each cell is either occupied or vacant.</p>

<p>Each day, whether the cell is occupied or vacant changes according to the following rules:</p>

<ul>
	<li>If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.</li>
	<li>Otherwise, it becomes vacant.</li>
</ul>

<p><strong>Note</strong> that because the prison is a row, the first and the last cells in the row can&#39;t have two adjacent neighbors.</p>

<p>You are given an integer array <code>cells</code> where <code>cells[i] == 1</code> if the <code>i<sup>th</sup></code> cell is occupied and <code>cells[i] == 0</code> if the <code>i<sup>th</sup></code> cell is vacant, and you are given an integer <code>n</code>.</p>

<p>Return the state of the prison after <code>n</code> days (i.e., <code>n</code> such changes described above).</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> cells = [0,1,0,1,1,0,0,1], n = 7
<strong>Output:</strong> [0,0,1,1,0,0,0,0]
<strong>Explanation:</strong> The following table summarizes the state of the prison on each day:
Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> cells = [1,0,0,1,0,0,1,0], n = 1000000000
<strong>Output:</strong> [0,0,1,1,1,1,1,0]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>cells.length == 8</code></li>
	<li><code>cells[i]</code>&nbsp;is either <code>0</code> or <code>1</code>.</li>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
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
