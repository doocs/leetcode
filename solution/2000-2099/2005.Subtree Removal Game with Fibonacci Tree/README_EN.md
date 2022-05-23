# [2005. Subtree Removal Game with Fibonacci Tree](https://leetcode.com/problems/subtree-removal-game-with-fibonacci-tree)

[中文文档](/solution/2000-2099/2005.Subtree%20Removal%20Game%20with%20Fibonacci%20Tree/README.md)

## Description

<p>A <strong>Fibonacci</strong> tree is a binary tree created using the order function <code>order(n)</code>:</p>

<ul>
	<li><code>order(0)</code> is the empty tree.</li>
	<li><code>order(1)</code> is a binary tree with only <strong>one node</strong>.</li>
	<li><code>order(n)</code> is a binary tree that consists of a root node with the left subtree as <code>order(n - 2)</code> and the right subtree as <code>order(n - 1)</code>.</li>
</ul>

<p>Alice and Bob are playing a game with a <strong>Fibonacci</strong> tree with Alice staring first. On each turn, a player selects a node and removes that node <strong>and</strong> its subtree. The player that is forced to delete <code>root</code> loses.</p>

<p>Given the integer <code>n</code>, return <code>true</code> if Alice wins the game or <code>false</code> if Bob wins, assuming both players play optimally.</p>

<p>A subtree of a binary tree <code>tree</code> is a tree that consists of a node in <code>tree</code> and all of this node&#39;s descendants. The tree <code>tree</code> could also be considered as a subtree of itself.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong><br />
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2005.Subtree%20Removal%20Game%20with%20Fibonacci%20Tree/images/image-20210914173520-3.png" style="width: 200px; height: 184px;" /></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> true
<strong>Explanation:</strong>
Alice takes the node 1 in the right subtree.
Bob takes either the 1 in the left subtree or the 2 in the right subtree.
Alice takes whichever node Bob doesn&#39;t take.
Bob is forced to take the root node 3, so Bob will lose.
Return true because Alice wins.
</pre>

<p><strong>Example 2:</strong><br />
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2005.Subtree%20Removal%20Game%20with%20Fibonacci%20Tree/images/image-20210914173634-4.png" style="width: 75px; height: 75px;" /></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> false
<strong>Explanation:</strong>
Alice is forced to take the root node 1, so Alice will lose.
Return false because Alice loses.
</pre>

<p><strong>Example 3:</strong><br />
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2005.Subtree%20Removal%20Game%20with%20Fibonacci%20Tree/images/image-20210914173425-1.png" style="width: 100px; height: 106px;" /></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> true
<strong>Explanation:</strong>
Alice takes the node 1.
Bob is forced to take the root node 2, so Bob will lose.
Return true because Alice wins.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
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
