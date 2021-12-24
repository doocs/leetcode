# [1145. Binary Tree Coloring Game](https://leetcode.com/problems/binary-tree-coloring-game)

[中文文档](/solution/1100-1199/1145.Binary%20Tree%20Coloring%20Game/README.md)

## Description

<p>Two players play a turn based game on a binary tree.&nbsp; We are given&nbsp;the <code>root</code> of this binary tree, and the number of nodes <code>n</code>&nbsp;in the tree.&nbsp; <code>n</code> is odd, and&nbsp;each node has a distinct value from <code>1</code> to <code>n</code>.</p>

<p>Initially, the first player names a value <code>x</code> with <code>1 &lt;= x &lt;= n</code>, and the second player names a value <code>y</code> with <code>1 &lt;= y &lt;= n</code> and <code>y != x</code>.&nbsp; The first player colors the node with value <code>x</code> red, and the second player colors the node with value <code>y</code> blue.</p>

<p>Then, the players take turns starting with the first player.&nbsp; In each turn, that player chooses a node of their color (red if player 1, blue if player 2) and colors an <strong>uncolored</strong> neighbor of the chosen node (either the left child, right child, or parent of the chosen node.)</p>

<p>If (and only if)&nbsp;a player cannot choose such a node in this way, they must pass their turn.&nbsp; If both players pass their turn, the game ends, and the winner is the player that colored more nodes.</p>

<p>You are the second player.&nbsp; If it is possible to choose such a <code>y</code>&nbsp;to ensure you win the game, return <code>true</code>.&nbsp; If it is not possible, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1145.Binary%20Tree%20Coloring%20Game/images/1480-binary-tree-coloring-game.png" style="width: 300px; height: 186px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
<strong>Output:</strong> true
<strong>Explanation: </strong>The second player can choose the node with value 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>root</code> is the root of a binary tree with <code>n</code> nodes and distinct node values from <code>1</code> to <code>n</code>.</li>
	<li><code>n</code> is odd.</li>
	<li><code>1 &lt;= x &lt;= n&nbsp;&lt;= 100</code></li>
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
