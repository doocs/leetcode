# [281. Zigzag Iterator](https://leetcode.com/problems/zigzag-iterator)

[中文文档](/solution/0200-0299/0281.Zigzag%20Iterator/README.md)

## Description

<p>Given two vectors of integers <code>v1</code> and <code>v2</code>, implement an iterator to return their elements alternately.</p>

<p>Implement the <code>ZigzagIterator</code> class:</p>

<ul>
	<li><code>ZigzagIterator(List&lt;int&gt; v1, List&lt;int&gt; v2)</code> initializes the object with the two vectors <code>v1</code> and <code>v2</code>.</li>
	<li><code>boolean hasNext()</code> returns <code>true</code> if the iterator still has elements, and <code>false</code> otherwise.</li>
	<li><code>int next()</code> returns the current element of the iterator and moves the iterator to the next element.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> v1 = [1,2], v2 = [3,4,5,6]
<strong>Output:</strong> [1,3,2,4,5,6]
<strong>Explanation:</strong> By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,3,2,4,5,6].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> v1 = [1], v2 = []
<strong>Output:</strong> [1]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> v1 = [], v2 = [1]
<strong>Output:</strong> [1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= v1.length, v2.length &lt;= 1000</code></li>
	<li><code>1 &lt;= v1.length + v2.length &lt;= 2000</code></li>
	<li><code>-2<sup>31</sup> &lt;= v1[i], v2[i] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> What if you are given <code>k</code> vectors? How well can your code be extended to such cases?</p>

<p><strong>Clarification for the follow-up question:</strong></p>

<p>The &quot;Zigzag&quot; order is not clearly defined and is ambiguous for <code>k &gt; 2</code> cases. If &quot;Zigzag&quot; does not look right to you, replace &quot;Zigzag&quot; with &quot;Cyclic&quot;.</p>

<p><strong>Example:</strong></p>

<pre>
<strong>Input:</strong> v1 = [1,2,3], v2 = [4,5,6,7], v3 = [8,9]
<strong>Output:</strong> [1,4,8,2,5,9,3,6,7]
</pre>


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
