# [1728. Cat and Mouse II](https://leetcode.com/problems/cat-and-mouse-ii)

[中文文档](/solution/1700-1799/1728.Cat%20and%20Mouse%20II/README.md)

## Description

<p>A game is played by a cat and a mouse named Cat and Mouse.</p>

<p>The environment is represented by a <code>grid</code> of size <code>rows x cols</code>, where each element is a wall, floor, player (Cat, Mouse), or food.</p>

<ul>
	<li>Players are represented by the characters <code>&#39;C&#39;</code>(Cat)<code>,&#39;M&#39;</code>(Mouse).</li>
	<li>Floors are represented by the character <code>&#39;.&#39;</code> and can be walked on.</li>
	<li>Walls are represented by the character <code>&#39;#&#39;</code> and cannot be walked on.</li>
	<li>Food is represented by the character <code>&#39;F&#39;</code> and can be walked on.</li>
	<li>There is only one of each character <code>&#39;C&#39;</code>, <code>&#39;M&#39;</code>, and <code>&#39;F&#39;</code> in <code>grid</code>.</li>
</ul>

<p>Mouse and Cat play according to the following rules:</p>

<ul>
	<li>Mouse <strong>moves first</strong>, then they take turns to move.</li>
	<li>During each turn, Cat and Mouse can jump in one of the four directions (left, right, up, down). They cannot jump over the wall nor outside of the <code>grid</code>.</li>
	<li><code>catJump, mouseJump</code> are the maximum lengths Cat and Mouse can jump at a time, respectively. Cat and Mouse can jump less than the maximum length.</li>
	<li>Staying in the same position is allowed.</li>
	<li>Mouse can jump over Cat.</li>
</ul>

<p>The game can end in 4 ways:</p>

<ul>
	<li>If Cat occupies the same position as Mouse, Cat wins.</li>
	<li>If Cat reaches the food first, Cat wins.</li>
	<li>If Mouse reaches the food first, Mouse wins.</li>
	<li>If Mouse cannot get to the food within 1000 turns, Cat wins.</li>
</ul>

<p>Given a <code>rows x cols</code> matrix <code>grid</code> and two integers <code>catJump</code> and <code>mouseJump</code>, return <code>true</code><em> if Mouse can win the game if both Cat and Mouse play optimally, otherwise return </em><code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1728.Cat%20and%20Mouse%20II/images/sample_111_1955.png" style="width: 580px; height: 239px;" />
<pre>
<strong>Input:</strong> grid = [&quot;####F&quot;,&quot;#C...&quot;,&quot;M....&quot;], catJump = 1, mouseJump = 2
<strong>Output:</strong> true
<strong>Explanation:</strong> Cat cannot catch Mouse on its turn nor can it get the food before Mouse.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1728.Cat%20and%20Mouse%20II/images/sample_2_1955.png" style="width: 580px; height: 175px;" />
<pre>
<strong>Input:</strong> grid = [&quot;M.C...F&quot;], catJump = 1, mouseJump = 4
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> grid = [&quot;M.C...F&quot;], catJump = 1, mouseJump = 3
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>rows == grid.length</code></li>
	<li><code>cols = grid[i].length</code></li>
	<li><code>1 &lt;= rows, cols &lt;= 8</code></li>
	<li><code>grid[i][j]</code> consist only of characters <code>&#39;C&#39;</code>, <code>&#39;M&#39;</code>, <code>&#39;F&#39;</code>, <code>&#39;.&#39;</code>, and <code>&#39;#&#39;</code>.</li>
	<li>There is only one of each character <code>&#39;C&#39;</code>, <code>&#39;M&#39;</code>, and <code>&#39;F&#39;</code> in <code>grid</code>.</li>
	<li><code>1 &lt;= catJump, mouseJump &lt;= 8</code></li>
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
