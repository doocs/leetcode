# [2940. Find Building Where Alice and Bob Can Meet](https://leetcode.com/problems/find-building-where-alice-and-bob-can-meet)

[中文文档](/solution/2900-2999/2940.Find%20Building%20Where%20Alice%20and%20Bob%20Can%20Meet/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array <code>heights</code> of positive integers, where <code>heights[i]</code> represents the height of the <code>i<sup>th</sup></code> building.</p>

<p>If a person is in building <code>i</code>, they can move to any other building <code>j</code> if and only if <code>i &lt; j</code> and <code>heights[i] &lt; heights[j]</code>.</p>

<p>You are also given another array <code>queries</code> where <code>queries[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>. On the <code>i<sup>th</sup></code> query, Alice is in building <code>a<sub>i</sub></code> while Bob is in building <code>b<sub>i</sub></code>.</p>

<p>Return <em>an array</em> <code>ans</code> <em>where</em> <code>ans[i]</code> <em>is <strong>the index of the leftmost building</strong> where Alice and Bob can meet on the</em> <code>i<sup>th</sup></code> <em>query</em>. <em>If Alice and Bob cannot move to a common building on query</em> <code>i</code>, <em>set</em> <code>ans[i]</code> <em>to</em> <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> heights = [6,4,8,5,2,7], queries = [[0,1],[0,3],[2,4],[3,4],[2,2]]
<strong>Output:</strong> [2,5,-1,5,2]
<strong>Explanation:</strong> In the first query, Alice and Bob can move to building 2 since heights[0] &lt; heights[2] and heights[1] &lt; heights[2]. 
In the second query, Alice and Bob can move to building 5 since heights[0] &lt; heights[5] and heights[3] &lt; heights[5]. 
In the third query, Alice cannot meet Bob since Alice cannot move to any other building.
In the fourth query, Alice and Bob can move to building 5 since heights[3] &lt; heights[5] and heights[4] &lt; heights[5].
In the fifth query, Alice and Bob are already in the same building.  
For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> heights = [5,3,8,2,6,1,4,6], queries = [[0,7],[3,5],[5,2],[3,0],[1,6]]
<strong>Output:</strong> [7,6,-1,4,6]
<strong>Explanation:</strong> In the first query, Alice can directly move to Bob&#39;s building since heights[0] &lt; heights[7].
In the second query, Alice and Bob can move to building 6 since heights[3] &lt; heights[6] and heights[5] &lt; heights[6].
In the third query, Alice cannot meet Bob since Bob cannot move to any other building.
In the fourth query, Alice and Bob can move to building 4 since heights[3] &lt; heights[4] and heights[0] &lt; heights[4].
In the fifth query, Alice can directly move to Bob&#39;s building since heights[1] &lt; heights[6].
For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= heights.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= heights[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>queries[i] = [a<sub>i</sub>, b<sub>i</sub>]</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= heights.length - 1</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
