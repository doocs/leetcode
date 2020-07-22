# [807. Max Increase to Keep City Skyline](https://leetcode.com/problems/max-increase-to-keep-city-skyline)

## Description
<p>In a 2 dimensional array <code>grid</code>, each value <code>grid[i][j]</code> represents the height of a building located there. We are allowed to increase the height of any number of buildings, by any amount (the amounts&nbsp;can be different for different buildings). Height&nbsp;0 is considered to be a&nbsp;building&nbsp;as well.&nbsp;</p>



<p>At the end, the &quot;skyline&quot; when viewed from all four directions&nbsp;of the grid, i.e.&nbsp;top, bottom, left, and right,&nbsp;must be the same as the&nbsp;skyline of the original grid. A city&#39;s skyline is the outer contour of the rectangles formed by all the buildings when viewed from a distance. See&nbsp;the following example.</p>



<p>What is the maximum total sum that the height of the buildings can be increased?</p>



<pre>

<strong>Example:</strong>

<strong>Input:</strong> grid = [[3,0,8,4],[2,4,5,7],[9,2,6,3],[0,3,1,0]]

<strong>Output:</strong> 35

<strong>Explanation:</strong> 

The grid is:

[ [3, 0, 8, 4], 

  [2, 4, 5, 7],

  [9, 2, 6, 3],

  [0, 3, 1, 0] ]



The skyline viewed from top or bottom is: [9, 4, 8, 7]

The skyline viewed from left or right is: [8, 7, 9, 3]



The grid after increasing the height of buildings without affecting skylines is:



gridNew = [ [8, 4, 8, 7],

            [7, 4, 7, 7],

            [9, 4, 8, 7],

            [3, 3, 3, 3] ]



</pre>



<p><strong>Notes: </strong></p>



<ul>

	<li><code>1 &lt; grid.length = grid[0].length &lt;= 50</code>.</li>

	<li>All heights <code>grid[i][j]</code> are in the range <code>[0, 100]</code>.</li>

	<li>All buildings in <code>grid[i][j]</code> occupy the entire grid cell: that is, they are a <code>1 x 1 x grid[i][j]</code> rectangular prism.</li>

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