# [1182. Shortest Distance to Target Color](https://leetcode.com/problems/shortest-distance-to-target-color)

[中文文档](/solution/1100-1199/1182.Shortest%20Distance%20to%20Target%20Color/README.md)

## Description
<p>You are given an array <code>colors</code>, in which there are three colors: <code>1</code>, <code>2</code> and <code>3</code>.</p>

<p>You are also given some queries. Each query consists of two integers <code>i</code> and <code>c</code>, return the shortest distance between the given index <code>i</code> and the target color <code>c</code>. If there is no solution return <code>-1</code>.</p>

<p> </p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
<strong>Output:</strong> [3,0,3]
<strong>Explanation: </strong>
The nearest 3 from index 1 is at index 4 (3 steps away).
The nearest 2 from index 2 is at index 2 itself (0 steps away).
The nearest 1 from index 6 is at index 3 (3 steps away).
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> colors = [1,2], queries = [[0,3]]
<strong>Output:</strong> [-1]
<strong>Explanation: </strong>There is no 3 in the array.
</pre>

<p> </p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 <= colors.length <= 5*10^4</code></li>
	<li><code>1 <= colors[i] <= 3</code></li>
	<li><code>1 <= queries.length <= 5*10^4</code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 <= queries[i][0] < colors.length</code></li>
	<li><code>1 <= queries[i][1] <= 3</code></li>
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