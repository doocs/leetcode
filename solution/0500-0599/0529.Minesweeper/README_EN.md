# [529. Minesweeper](https://leetcode.com/problems/minesweeper)

[中文文档](/solution/0500-0599/0529.Minesweeper/README.md)

## Description

<p>Let&#39;s play the minesweeper game (<a href="https://en.wikipedia.org/wiki/Minesweeper_(video_game)" target="_blank">Wikipedia</a>, <a href="http://minesweeperonline.com" target="_blank">online game</a>)!</p>

<p>You are given an <code>m x n</code> char matrix <code>board</code> representing the game board where:</p>

<ul>
	<li><code>&#39;M&#39;</code> represents an unrevealed mine,</li>
	<li><code>&#39;E&#39;</code> represents an unrevealed empty square,</li>
	<li><code>&#39;B&#39;</code> represents a revealed blank square that has no adjacent mines (i.e., above, below, left, right, and all 4 diagonals),</li>
	<li>digit (<code>&#39;1&#39;</code> to <code>&#39;8&#39;</code>) represents how many mines are adjacent to this revealed square, and</li>
	<li><code>&#39;X&#39;</code> represents a revealed mine.</li>
</ul>

<p>You are also given an integer array <code>click</code> where <code>click = [click<sub>r</sub>, click<sub>c</sub>]</code> represents the next click position among all the unrevealed squares (<code>&#39;M&#39;</code> or <code>&#39;E&#39;</code>).</p>

<p>Return <em>the board after revealing this position according to the following rules</em>:</p>

<ol>
	<li>If a mine <code>&#39;M&#39;</code> is revealed, then the game is over. You should change it to <code>&#39;X&#39;</code>.</li>
	<li>If an empty square <code>&#39;E&#39;</code> with no adjacent mines is revealed, then change it to a revealed blank <code>&#39;B&#39;</code> and all of its adjacent unrevealed squares should be revealed recursively.</li>
	<li>If an empty square <code>&#39;E&#39;</code> with at least one adjacent mine is revealed, then change it to a digit (<code>&#39;1&#39;</code> to <code>&#39;8&#39;</code>) representing the number of adjacent mines.</li>
	<li>Return the board when no more squares will be revealed.</li>
</ol>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0529.Minesweeper/images/minesweeper_example_1.png" style="width: 500px; max-width: 400px; height: 269px;" />
<pre>
<strong>Input:</strong> board = [[&quot;E&quot;,&quot;E&quot;,&quot;E&quot;,&quot;E&quot;,&quot;E&quot;],[&quot;E&quot;,&quot;E&quot;,&quot;M&quot;,&quot;E&quot;,&quot;E&quot;],[&quot;E&quot;,&quot;E&quot;,&quot;E&quot;,&quot;E&quot;,&quot;E&quot;],[&quot;E&quot;,&quot;E&quot;,&quot;E&quot;,&quot;E&quot;,&quot;E&quot;]], click = [3,0]
<strong>Output:</strong> [[&quot;B&quot;,&quot;1&quot;,&quot;E&quot;,&quot;1&quot;,&quot;B&quot;],[&quot;B&quot;,&quot;1&quot;,&quot;M&quot;,&quot;1&quot;,&quot;B&quot;],[&quot;B&quot;,&quot;1&quot;,&quot;1&quot;,&quot;1&quot;,&quot;B&quot;],[&quot;B&quot;,&quot;B&quot;,&quot;B&quot;,&quot;B&quot;,&quot;B&quot;]]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0529.Minesweeper/images/minesweeper_example_2.png" style="width: 500px; max-width: 400px; height: 275px;" />
<pre>
<strong>Input:</strong> board = [[&quot;B&quot;,&quot;1&quot;,&quot;E&quot;,&quot;1&quot;,&quot;B&quot;],[&quot;B&quot;,&quot;1&quot;,&quot;M&quot;,&quot;1&quot;,&quot;B&quot;],[&quot;B&quot;,&quot;1&quot;,&quot;1&quot;,&quot;1&quot;,&quot;B&quot;],[&quot;B&quot;,&quot;B&quot;,&quot;B&quot;,&quot;B&quot;,&quot;B&quot;]], click = [1,2]
<strong>Output:</strong> [[&quot;B&quot;,&quot;1&quot;,&quot;E&quot;,&quot;1&quot;,&quot;B&quot;],[&quot;B&quot;,&quot;1&quot;,&quot;X&quot;,&quot;1&quot;,&quot;B&quot;],[&quot;B&quot;,&quot;1&quot;,&quot;1&quot;,&quot;1&quot;,&quot;B&quot;],[&quot;B&quot;,&quot;B&quot;,&quot;B&quot;,&quot;B&quot;,&quot;B&quot;]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n == board[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>board[i][j]</code> is either <code>&#39;M&#39;</code>, <code>&#39;E&#39;</code>, <code>&#39;B&#39;</code>, or a digit from <code>&#39;1&#39;</code> to <code>&#39;8&#39;</code>.</li>
	<li><code>click.length == 2</code></li>
	<li><code>0 &lt;= click<sub>r</sub> &lt; m</code></li>
	<li><code>0 &lt;= click<sub>c</sub> &lt; n</code></li>
	<li><code>board[click<sub>r</sub>][click<sub>c</sub>]</code> is either <code>&#39;M&#39;</code> or <code>&#39;E&#39;</code>.</li>
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
