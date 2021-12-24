# [1530. Number of Good Leaf Nodes Pairs](https://leetcode.com/problems/number-of-good-leaf-nodes-pairs)

[中文文档](/solution/1500-1599/1530.Number%20of%20Good%20Leaf%20Nodes%20Pairs/README.md)

## Description

<p>Given the <code>root</code> of a binary tree and an integer <code>distance</code>. A pair of two different <strong>leaf</strong> nodes of a binary tree is said to be good if the length of <strong>the shortest path</strong> between them is less than or equal to <code>distance</code>.</p>

<p>Return <em>the number of good leaf node pairs</em> in the tree.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1530.Number%20of%20Good%20Leaf%20Nodes%20Pairs/images/e1.jpg" style="width: 321px; height: 321px;" />

<pre>

<strong>Input:</strong> root = [1,2,3,null,4], distance = 3

<strong>Output:</strong> 1

<strong>Explanation:</strong> The leaf nodes of the tree are 3 and 4 and the length of the shortest path between them is 3. This is the only good pair.

</pre>

<p><strong>Example 2:</strong></p>

<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1530.Number%20of%20Good%20Leaf%20Nodes%20Pairs/images/e2.jpg" style="width: 441px; height: 321px;" />

<pre>

<strong>Input:</strong> root = [1,2,3,4,5,6,7], distance = 3

<strong>Output:</strong> 2

<strong>Explanation:</strong> The good pairs are [4,5] and [6,7] with shortest path = 2. The pair [4,6] is not good because the length of ther shortest path between them is 4.

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3

<strong>Output:</strong> 1

<strong>Explanation:</strong> The only good pair is [2,5].

</pre>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input:</strong> root = [100], distance = 1

<strong>Output:</strong> 0

</pre>

<p><strong>Example 5:</strong></p>

<pre>

<strong>Input:</strong> root = [1,1,1], distance = 2

<strong>Output:</strong> 1

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the&nbsp;<code>tree</code>&nbsp;is in the range&nbsp;<code>[1, 2^10].</code></li>
	<li>Each node&#39;s value is between&nbsp;<code>[1, 100]</code>.</li>
	<li><code>1 &lt;= distance &lt;= 10</code></li>
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
