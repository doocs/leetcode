# [794. Valid Tic-Tac-Toe State](https://leetcode.com/problems/valid-tic-tac-toe-state)

[中文文档](/solution/0700-0799/0794.Valid%20Tic-Tac-Toe%20State/README.md)

## Description

<p>A Tic-Tac-Toe board is given as a string array <code>board</code>. Return True if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.</p>

<p>The <code>board</code> is a 3 x 3 array, and consists of characters <code>&quot; &quot;</code>, <code>&quot;X&quot;</code>, and <code>&quot;O&quot;</code>.&nbsp; The &quot; &quot; character represents an empty square.</p>

<p>Here are the rules of Tic-Tac-Toe:</p>

<ul>

    <li>Players take turns placing characters into empty squares (&quot; &quot;).</li>

    <li>The first player always places &quot;X&quot; characters, while the second player always places &quot;O&quot; characters.</li>

    <li>&quot;X&quot; and &quot;O&quot; characters are always placed into empty squares, never filled ones.</li>

    <li>The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.</li>

    <li>The game also ends if all squares are non-empty.</li>

    <li>No more moves can be played if the game is over.</li>

</ul>

<pre>

<strong>Example 1:</strong>

<strong>Input:</strong> board = [&quot;O&nbsp; &quot;, &quot;&nbsp; &nbsp;&quot;, &quot;&nbsp; &nbsp;&quot;]

<strong>Output:</strong> false

<strong>Explanation:</strong> The first player always plays &quot;X&quot;.



<strong>Example 2:</strong>

<strong>Input:</strong> board = [&quot;XOX&quot;, &quot; X &quot;, &quot;   &quot;]

<strong>Output:</strong> false

<strong>Explanation:</strong> Players take turns making moves.



<strong>Example 3:</strong>

<strong>Input:</strong> board = [&quot;XXX&quot;, &quot;   &quot;, &quot;OOO&quot;]

<strong>Output:</strong> false



<strong>Example 4:</strong>

<strong>Input:</strong> board = [&quot;XOX&quot;, &quot;O O&quot;, &quot;XOX&quot;]

<strong>Output:</strong> true

</pre>

<p><strong>Note:</strong></p>

<ul>

    <li><code>board</code> is a length-3 array of strings, where each string <code>board[i]</code> has length 3.</li>

    <li>Each <code>board[i][j]</code> is a character in the set <code>{&quot; &quot;, &quot;X&quot;, &quot;O&quot;}</code>.</li>

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
