# [16.04. Tic-Tac-Toe](https://leetcode.cn/problems/tic-tac-toe-lcci)

[中文文档](/lcci/16.04.Tic-Tac-Toe/README.md)

## Description

<p>Design an algorithm to figure out if someone has won a game of tic-tac-toe.&nbsp;Input is a string array&nbsp;of size N x N, including characters &quot; &quot;, &quot;X&quot; and &quot;O&quot;, where &quot; &quot; represents a empty grid.</p>
<p>The rules of tic-tac-toe are as follows:</p>
<ul>
	<li>Players place characters into an empty grid(&quot; &quot;) in turn.</li>
	<li>The first player always place character &quot;O&quot;, and the second one place &quot;X&quot;.</li>
	<li>Players are only allowed to place characters in empty grid. Replacing a character is not allowed.</li>
	<li>If there is any row, column or diagonal filled with N&nbsp;same characters, the game ends. The player who place the last charater wins.</li>
	<li>When there is no empty grid, the game ends.</li>
	<li>If the game ends, players cannot place any character further.</li>
</ul>
<p>If there is any winner, return the character that the winner used. If there&#39;s a draw, return &quot;Draw&quot;. If the game doesn&#39;t end and there is no winner, return &quot;Pending&quot;.</p>
<p><strong>Example 1: </strong></p>
<pre>

<strong>Input: </strong> board = [&quot;O X&quot;,&quot; XO&quot;,&quot;X O&quot;]

<strong>Output: </strong> &quot;X&quot;

</pre>
<p><strong>Example 2: </strong></p>
<pre>

<strong>Input: </strong> board = [&quot;OOX&quot;,&quot;XXO&quot;,&quot;OXO&quot;]

<strong>Output: </strong> &quot;Draw&quot;

<strong>Explanation: </strong> no player wins and no empty grid left

</pre>
<p><strong>Example 3: </strong></p>
<pre>

<strong>Input: </strong> board = [&quot;OOX&quot;,&quot;XXO&quot;,&quot;OX &quot;]

<strong>Output: </strong> &quot;Pending&quot;

<strong>Explanation: </strong> no player wins but there is still a empty grid

</pre>
<p><strong>Note: </strong></p>
<ul>
	<li><code>1 &lt;= board.length == board[i].length &lt;= 100</code></li>
	<li>Input follows the rules.</li>
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
