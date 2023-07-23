# [2791. Count Paths That Can Form a Palindrome in a Tree](https://leetcode.com/problems/count-paths-that-can-form-a-palindrome-in-a-tree)

[中文文档](/solution/2700-2799/2791.Count%20Paths%20That%20Can%20Form%20a%20Palindrome%20in%20a%20Tree/README.md)

## Description

<p>You are given a <strong>tree</strong> (i.e. a connected, undirected graph that has no cycles) <strong>rooted</strong> at node <code>0</code> consisting of <code>n</code> nodes numbered from <code>0</code> to <code>n - 1</code>. The tree is represented by a <strong>0-indexed</strong> array <code>parent</code> of size <code>n</code>, where <code>parent[i]</code> is the parent of node <code>i</code>. Since node <code>0</code> is the root, <code>parent[0] == -1</code>.</p>

<p>You are also given a string <code>s</code> of length <code>n</code>, where <code>s[i]</code> is the character assigned to the edge between <code>i</code> and <code>parent[i]</code>. <code>s[0]</code> can be ignored.</p>

<p>Return <em>the number of pairs of nodes </em><code>(u, v)</code><em> such that </em><code>u &lt; v</code><em> and the characters assigned to edges on the path from </em><code>u</code><em> to </em><code>v</code><em> can be <strong>rearranged</strong> to form a <strong>palindrome</strong></em>.</p>

<p>A string is a <strong>palindrome</strong> when it reads the same backwards as forwards.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2791.Count%20Paths%20That%20Can%20Form%20a%20Palindrome%20in%20a%20Tree/images/treedrawio-8drawio.png" style="width: 281px; height: 181px;" /></p>

<pre>
<strong>Input:</strong> parent = [-1,0,0,1,1,2], s = &quot;acaabc&quot;
<strong>Output:</strong> 8
<strong>Explanation:</strong> The valid pairs are:
- All the pairs (0,1), (0,2), (1,3), (1,4) and (2,5) result in one character which is always a palindrome.
- The pair (2,3) result in the string &quot;aca&quot; which is a palindrome.
- The pair (1,5) result in the string &quot;cac&quot; which is a palindrome.
- The pair (3,5) result in the string &quot;acac&quot; which can be rearranged into the palindrome &quot;acca&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> parent = [-1,0,0,0,0], s = &quot;aaaaa&quot;
<strong>Output:</strong> 10
<strong>Explanation:</strong> Any pair of nodes (u,v) where u &lt; v is valid.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == parent.length == s.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= parent[i] &lt;= n - 1</code> for all <code>i &gt;= 1</code></li>
	<li><code>parent[0] == -1</code></li>
	<li><code>parent</code> represents a valid tree.</li>
	<li><code>s</code> consists of only lowercase English letters.</li>
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
