# [2689. Extract Kth Character From The Rope Tree](https://leetcode.com/problems/extract-kth-character-from-the-rope-tree)

[中文文档](/solution/2600-2699/2689.Extract%20Kth%20Character%20From%20The%20Rope%20Tree/README.md)

## Description

<p>You are given the&nbsp;<code>root</code> of a binary tree&nbsp;and an integer <code>k</code>. Besides&nbsp;the left and right children, every node of this tree has two other properties, a&nbsp;<strong>string&nbsp;</strong><code>node.val</code>&nbsp;containing only lowercase English letters (possibly empty) and a non-negative integer&nbsp;<code>node.len</code>.&nbsp;There are two types of nodes in this tree:</p>

<ul>
	<li><strong>Leaf</strong>: These nodes have no children, <code>node.len = 0</code>, and <code>node.val</code> is some <strong>non-empty</strong> string.</li>
	<li><strong>Internal</strong>: These nodes have at least one child (also at most two children),&nbsp;<code>node.len&nbsp;&gt; 0</code>,&nbsp;and <code>node.val</code> is an <strong>empty</strong> string.</li>
</ul>

<p>The tree described above is called a <em>Rope</em> binary tree. Now we define <code>S[node]</code>&nbsp;recursively as follows:</p>

<ul>
	<li>If <code>node</code>&nbsp;is some leaf node, <code>S[node] = node.val</code>,</li>
	<li>Otherwise if <code>node</code> is some internal node, <code>S[node] = concat(S[node.left], S[node.right])</code>.</li>
</ul>

<p>&nbsp;Return<em> k-th character of the string&nbsp;</em><code>S[root]</code>.</p>

<p><strong>Note:</strong> If <code>s</code> and <code>p</code> are two strings, <code>concat(s, p)</code> is a string obtained by concatenating <code>p</code> to&nbsp;<code>s</code>. For example, <code>concat(&quot;ab&quot;, &quot;zz&quot;) = &quot;abzz&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> root = [10,4,&quot;abcpoe&quot;,&quot;g&quot;,&quot;rta&quot;], k = 6
<strong>Output:</strong> &quot;b&quot;
<strong>Explanation:</strong> In the picture below, we put an integer on internal nodes that represents node.len, and a string on leaf nodes that represents node.val.
You can see that S[root] = concat(concat(&quot;g&quot;, &quot;rta&quot;), &quot;abcpoe&quot;) = &quot;grtaabcpoe&quot;. So S[root][5], which represents 6th character&nbsp;of it, is equal to &quot;b&quot;.
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2689.Extract%20Kth%20Character%20From%20The%20Rope%20Tree/images/example1.png" style="width: 300px; height: 213px; margin-left: 280px; margin-right: 280px;" /></p>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [12,6,6,&quot;abc&quot;,&quot;efg&quot;,&quot;hij&quot;,&quot;klm&quot;], k = 3
<strong>Output:</strong> &quot;c&quot;
<strong>Explanation:</strong> In the picture below, we put an integer on internal nodes that represents node.len, and a string on leaf nodes that represents node.val.
You can see that S[root] = concat(concat(&quot;abc&quot;, &quot;efg&quot;), concat(&quot;hij&quot;, &quot;klm&quot;)) = &quot;abcefghijklm&quot;. So S[root][2], which represents the 3rd character of it, is equal to &quot;c&quot;.
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2689.Extract%20Kth%20Character%20From%20The%20Rope%20Tree/images/example2.png" style="width: 400px; height: 232px; margin-left: 255px; margin-right: 255px;" /></p>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> root = [&quot;ropetree&quot;], k = 8
<strong>Output:</strong> &quot;e&quot;
<strong>Explanation:</strong> In the picture below, we put an integer on internal nodes that represents node.len, and a string on leaf nodes that represents node.val.
You can see that S[root] = &quot;ropetree&quot;. So S[root][7], which represents 8th character of it, is equal to &quot;e&quot;.
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2689.Extract%20Kth%20Character%20From%20The%20Rope%20Tree/images/example3.png" style="width: 80px; height: 78px; margin-left: 400px; margin-right: 400px;" /></p>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>3</sup>]</code></li>
	<li><code>node.val</code> contains only lowercase English letters</li>
	<li><code>0 &lt;= node.val.length &lt;= 50</code></li>
	<li><code>0 &lt;= node.len &lt;= 10<sup>4</sup></code></li>
	<li>for leaf nodes, <code>node.len = 0</code> and <code>node.val</code> is non-empty</li>
	<li>for internal nodes, <code>node.len &gt; 0</code> and <code>node.val</code> is empty</li>
	<li><code>1 &lt;= k &lt;= S[root].length</code></li>
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
