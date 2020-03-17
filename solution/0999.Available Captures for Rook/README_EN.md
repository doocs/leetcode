# [999. Available Captures for Rook](https://leetcode.com/problems/available-captures-for-rook)

## Description
<p>On an 8 x 8 chessboard, there is one white rook.&nbsp; There also may be empty squares, white bishops, and black pawns.&nbsp; These are given as characters &#39;R&#39;, &#39;.&#39;, &#39;B&#39;, and &#39;p&#39; respectively. Uppercase characters represent white pieces, and lowercase characters represent black pieces.</p>

<p>The rook moves as in the rules of Chess: it chooses one of four cardinal directions (north, east, west, and south), then moves in that direction until it chooses to stop, reaches the edge of the board, or captures an opposite colored pawn by moving to the same square it occupies.&nbsp; Also, rooks cannot move into the same square as other friendly bishops.</p>

<p>Return the number of pawns the rook can capture in one move.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2019/02/20/1253_example_1_improved.PNG" style="width: 300px; height: 305px;" /></p>

<pre>
<strong>Input: </strong><span id="example-input-1-1">[[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;R&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;p&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;]]</span>
<strong>Output: </strong><span id="example-output-1">3</span>
<strong>Explanation: </strong>
In this example the rook is able to capture all the pawns.
</pre>

<p><strong>Example 2:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2019/02/19/1253_example_2_improved.PNG" style="width: 300px; height: 306px;" /></p>

<pre>
<strong>Input: </strong><span id="example-input-2-1">[[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;p&quot;,&quot;p&quot;,&quot;p&quot;,&quot;p&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;p&quot;,&quot;p&quot;,&quot;B&quot;,&quot;p&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;p&quot;,&quot;B&quot;,&quot;R&quot;,&quot;B&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;p&quot;,&quot;p&quot;,&quot;B&quot;,&quot;p&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;p&quot;,&quot;p&quot;,&quot;p&quot;,&quot;p&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;]]</span>
<strong>Output: </strong><span id="example-output-2">0</span>
<strong>Explanation: </strong>
Bishops are blocking the rook to capture any pawn.
</pre>

<p><strong>Example 3:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2019/02/20/1253_example_3_improved.PNG" style="width: 300px; height: 305px;" /></p>

<pre>
<strong>Input: </strong><span id="example-input-3-1">[[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;p&quot;,&quot;p&quot;,&quot;.&quot;,&quot;R&quot;,&quot;.&quot;,&quot;p&quot;,&quot;B&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;B&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;p&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;]]</span>
<strong>Output: </strong><span id="example-output-3">3</span>
<strong>Explanation: </strong>
The rook can capture the pawns at positions b5, d6 and f5.
</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>board.length == board[i].length == 8</code></li>
	<li><code>board[i][j]</code> is either <code>&#39;R&#39;</code>, <code>&#39;.&#39;</code>, <code>&#39;B&#39;</code>, or&nbsp;<code>&#39;p&#39;</code></li>
	<li>There is exactly one cell with <code>board[i][j] == &#39;R&#39;</code></li>
</ol>



## Solution
<!-- Type common method here -->


### Python3
<!-- Type special method here -->

```python

```

### Java
<!-- Type special method here -->

```java

```

### ...
```

```

