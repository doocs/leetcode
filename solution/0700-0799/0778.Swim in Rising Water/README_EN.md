# [778. Swim in Rising Water](https://leetcode.com/problems/swim-in-rising-water)

## Description
<p>On an N x N <code>grid</code>, each square <code>grid[i][j]</code> represents the elevation at that point <code>(i,j)</code>.</p>

<p>Now rain starts to fall. At time <code>t</code>, the depth of the water everywhere is <code>t</code>. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are&nbsp;at most&nbsp;<code>t</code>. You can swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.</p>

<p>You start at the top left square <code>(0, 0)</code>. What is the least time until you can reach the bottom right square <code>(N-1, N-1)</code>?</p>

<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> [[0,2],[1,3]]
<strong>Output:</strong> 3
<strong>Explanation:</strong>
At time <code>0</code>, you are in grid location <code>(0, 0)</code>.
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.

You cannot reach point <code>(1, 1)</code> until time <code>3</code>.
When the depth of water is <code>3</code>, we can swim anywhere inside the grid.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
<strong>Output:</strong> 16
<strong>Explanation:</strong>
<strong> 0  1  2  3  4</strong>
24 23 22 21  <strong>5</strong>
<strong>12 13 14 15 16</strong>
<strong>11</strong> 17 18 19 20
<strong>10  9  8  7  6</strong>

The final route is marked in bold.
We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
</pre>

<p><strong>Note:</strong></p>

<ol>
	<li><code>2 &lt;= N &lt;= 50</code>.</li>
	<li>grid[i][j] is a permutation of [0, ..., N*N - 1].</li>
</ol>



## Solutions


### Python3

```python

```

### Java

```java

```

### ...
```

```
