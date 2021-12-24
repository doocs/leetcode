# [1040. Moving Stones Until Consecutive II](https://leetcode.com/problems/moving-stones-until-consecutive-ii)

[中文文档](/solution/1000-1099/1040.Moving%20Stones%20Until%20Consecutive%20II/README.md)

## Description

<p>On an <strong>infinite</strong> number line, the position of the i-th stone is given by&nbsp;<code>stones[i]</code>.&nbsp; Call a stone an <em>endpoint stone</em> if it has the smallest or largest position.</p>

<p>Each turn, you pick up an endpoint stone and move it to an unoccupied position so that it is no longer an endpoint stone.</p>

<p>In particular,&nbsp;if the stones are at say, <code>stones = [1,2,5]</code>, you <strong>cannot</strong> move the endpoint stone at position 5, since moving it to any position (such as 0, or 3) will still keep that stone as an endpoint stone.</p>

<p>The game ends when you cannot make any more moves, ie. the stones are in consecutive positions.</p>

<p>When the game ends, what is the minimum and maximum number of moves that you could have made?&nbsp; Return the answer as an length 2 array:&nbsp;<code>answer = [minimum_moves, maximum_moves]</code></p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">[7,4,9]</span>

<strong>Output: </strong><span id="example-output-1">[1,2]</span>

<strong>Explanation: </strong>

We can move 4 -&gt; 8 for one move to finish the game.

Or, we can move 9 -&gt; 5, 4 -&gt; 6 for two moves to finish the game.

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-2-1">[6,5,4,3,10]</span>

<strong>Output: </strong><span id="example-output-2">[2,3]</span>

We can move 3 -&gt; 8 then 10 -&gt; 7 to finish the game.

Or, we can move 3 -&gt; 7, 4 -&gt; 8, 5 -&gt; 9 to finish the game.

Notice we cannot move 10 -&gt; 2 to finish the game, because that would be an illegal move.

</pre>

<div>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-3-1">[100,101,104,102,103]</span>

<strong>Output: </strong><span id="example-output-3">[0,0]</span></pre>

<p>&nbsp;</p>

</div>

</div>

<p><strong>Note:</strong></p>

<ol>
	<li><code>3 &lt;= stones.length &lt;= 10^4</code></li>
	<li><code>1 &lt;= stones[i] &lt;= 10^9</code></li>
	<li><code>stones[i]</code> have distinct values.</li>
</ol>

<div>

<div>

<div>&nbsp;</div>

</div>

</div>

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
