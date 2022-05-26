# [1340. Jump Game V](https://leetcode.com/problems/jump-game-v)

[中文文档](/solution/1300-1399/1340.Jump%20Game%20V/README.md)

## Description

<p>Given an array of&nbsp;integers <code>arr</code> and an integer <code>d</code>. In one step you can jump from index <code>i</code> to index:</p>

<ul>
	<li><code>i + x</code> where:&nbsp;<code>i + x &lt; arr.length</code> and <code> 0 &lt;&nbsp;x &lt;= d</code>.</li>
	<li><code>i - x</code> where:&nbsp;<code>i - x &gt;= 0</code> and <code> 0 &lt;&nbsp;x &lt;= d</code>.</li>
</ul>

<p>In addition, you can only jump from index <code>i</code> to index <code>j</code>&nbsp;if <code>arr[i] &gt; arr[j]</code> and <code>arr[i] &gt; arr[k]</code> for all indices <code>k</code> between <code>i</code> and <code>j</code> (More formally <code>min(i,&nbsp;j) &lt; k &lt; max(i, j)</code>).</p>

<p>You can choose any index of the array and start jumping. Return <em>the maximum number of indices</em>&nbsp;you can visit.</p>

<p>Notice that you can not jump outside of the array at any time.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1340.Jump%20Game%20V/images/meta-chart.jpeg" style="width: 633px; height: 419px;" />
<pre>
<strong>Input:</strong> arr = [6,4,14,6,8,13,9,7,10,6,12], d = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> You can start at index 10. You can jump 10 --&gt; 8 --&gt; 6 --&gt; 7 as shown.
Note that if you start at index 6 you can only jump to index 7. You cannot jump to index 5 because 13 &gt; 9. You cannot jump to index 4 because index 5 is between index 4 and 6 and 13 &gt; 9.
Similarly You cannot jump from index 3 to index 2 or index 1.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [3,3,3,3,3], d = 3
<strong>Output:</strong> 1
<strong>Explanation:</strong> You can start at any index. You always cannot jump to any index.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [7,6,5,4,3,2,1], d = 1
<strong>Output:</strong> 7
<strong>Explanation:</strong> Start at index 0. You can visit all the indicies. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= d &lt;= arr.length</code></li>
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
