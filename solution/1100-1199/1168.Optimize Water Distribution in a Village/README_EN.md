# [1168. Optimize Water Distribution in a Village](https://leetcode.com/problems/optimize-water-distribution-in-a-village)

[中文文档](/solution/1100-1199/1168.Optimize%20Water%20Distribution%20in%20a%20Village/README.md)

## Description
<p>There are <code><font face="monospace">n</font></code> houses in a village. We want to supply water for all the houses by building wells and laying pipes.</p>

<p>For each house <code>i</code>, we can either build a well inside it directly with cost <code>wells[i]</code>, or pipe in water from another well to it. The costs to lay pipes between houses are given by the array <code>pipes</code>, where each <code>pipes[i] = [house1, house2, cost]</code> represents the cost to connect <code>house1</code> and <code>house2</code> together using a pipe. Connections are bidirectional.</p>

<p>Find the minimum total cost to supply water to all houses.</p>

<p> </p>
<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2019/05/22/1359_ex1.png" style="width: 189px; height: 196px;" /></strong></p>

<pre>
<strong>Input:</strong> n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
<strong>Output:</strong> 3
<strong>Explanation: </strong>
The image shows the costs of connecting houses using pipes.
The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with cost 2 so the total cost is 3.
</pre>

<p> </p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 <= n <= 10000</code></li>
	<li><code>wells.length == n</code></li>
	<li><code>0 <= wells[i] <= 10^5</code></li>
	<li><code>1 <= pipes.length <= 10000</code></li>
	<li><code>1 <= pipes[i][0], pipes[i][1] <= n</code></li>
	<li><code>0 <= pipes[i][2] <= 10^5</code></li>
	<li><code>pipes[i][0] != pipes[i][1]</code></li>
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