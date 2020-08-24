# [305. Number of Islands II](https://leetcode.com/problems/number-of-islands-ii)

[中文文档](/solution/0300-0399/0305.Number%20of%20Islands%20II/README.md)

## Description
<p>A 2d grid map of <code>m</code> rows and <code>n</code> columns is initially filled with water. We may perform an <i>addLand</i> operation which turns the water at position (row, col) into a land. Given a list of positions to operate, <b>count the number of islands after each <i>addLand</i> operation</b>. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.</p>

<p><b>Example:</b></p>

<pre>
<b>Input:</b> m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
<b>Output:</b> [1,1,2,3]
</pre>

<p><b>Explanation:</b></p>

<p>Initially, the 2d grid <code>grid</code> is filled with water. (Assume 0 represents water and 1 represents land).</p>

<pre>
0 0 0
0 0 0
0 0 0
</pre>

<p>Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.</p>

<pre>
1 0 0
0 0 0   Number of islands = 1
0 0 0
</pre>

<p>Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.</p>

<pre>
1 1 0
0 0 0   Number of islands = 1
0 0 0
</pre>

<p>Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.</p>

<pre>
1 1 0
0 0 1   Number of islands = 2
0 0 0
</pre>

<p>Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.</p>

<pre>
1 1 0
0 0 1   Number of islands = 3
0 1 0
</pre>

<p><b>Follow up:</b></p>

<p>Can you do it in time complexity O(k log mn), where k is the length of the <code>positions</code>?</p>



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