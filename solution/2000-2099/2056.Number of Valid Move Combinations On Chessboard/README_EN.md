# [2056. Number of Valid Move Combinations On Chessboard](https://leetcode.com/problems/number-of-valid-move-combinations-on-chessboard)

[中文文档](/solution/2000-2099/2056.Number%20of%20Valid%20Move%20Combinations%20On%20Chessboard/README.md)

## Description

<p>There is an <code>8 x 8</code> chessboard containing <code>n</code> pieces (rooks, queens, or bishops). You are given a string array <code>pieces</code> of length <code>n</code>, where <code>pieces[i]</code> describes the type (rook, queen, or bishop) of the <code>i<sup>th</sup></code> piece. In addition, you are given a 2D integer array <code>positions</code> also of length <code>n</code>, where <code>positions[i] = [r<sub>i</sub>, c<sub>i</sub>]</code> indicates that the <code>i<sup>th</sup></code> piece is currently at the <strong>1-based</strong> coordinate <code>(r<sub>i</sub>, c<sub>i</sub>)</code> on the chessboard.</p>

<p>Each piece on the chessboard can be moved <strong>at most once</strong> according to the type of piece. In a <strong>move</strong>, you may choose the <strong>direction</strong> the piece moves and the <strong>number of squares</strong> it travels without moving the piece outside the chessboard. The pieces are allowed to move as follows:</p>

<ul>
	<li>A rook can move <strong>horizontally or vertically</strong> from <code>(r, c)</code> to the direction of <code>(r+1, c)</code>, <code>(r-1, c)</code>, <code>(r, c+1)</code>, or <code>(r, c-1)</code>.</li>
	<li>A queen can move <strong>horizontally, vertically, or diagonally</strong> from <code>(r, c)</code> to the direction of <code>(r+1, c)</code>, <code>(r-1, c)</code>, <code>(r, c+1)</code>, <code>(r, c-1)</code>, <code>(r+1, c+1)</code>, <code>(r+1, c-1)</code>, <code>(r-1, c+1)</code>, <code>(r-1, c-1)</code>.</li>
	<li>A bishop can move <strong>diagonally</strong> from <code>(r, c)</code> to the direction of <code>(r+1, c+1)</code>, <code>(r+1, c-1)</code>, <code>(r-1, c+1)</code>, <code>(r-1, c-1)</code>.</li>
</ul>

<p>A <strong>move combination</strong> consists of all the <strong>moves</strong> performed on all the given pieces. Every second, each piece will instantaneously travel <strong>one square</strong> towards their destination if they are not already at it. All pieces start traveling at the <code>0<sup>th</sup></code> second. A move combination is <strong>invalid</strong> if, at a given time, <strong>two or more</strong> pieces occupy the same square.</p>

<p>Return <em>the number of <strong>valid</strong> move combinations</em>​​​​​​.</p>

<p><strong>Notes:</strong></p>

<ul>
	<li><strong>No two pieces</strong> will start in the<strong> same</strong> square.</li>
	<li>It is possible that a piece is not moved in a move combination.</li>
	<li>If two pieces are <strong>directly adjacent</strong> to each other, it is valid for them to <strong>move past each other</strong> and swap positions in one second.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2056.Number%20of%20Valid%20Move%20Combinations%20On%20Chessboard/images/a1.png" style="width: 215px; height: 215px;" />
<pre>
<strong>Input:</strong> pieces = [&quot;rook&quot;], positions = [[1,1]]
<strong>Output:</strong> 15
<strong>Explanation:</strong> The image above shows the possible squares the piece can move to.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2056.Number%20of%20Valid%20Move%20Combinations%20On%20Chessboard/images/a2.png" style="width: 215px; height: 215px;" />
<pre>
<strong>Input:</strong> pieces = [&quot;queen&quot;], positions = [[1,1]]
<strong>Output:</strong> 22
<strong>Explanation:</strong> The image above shows the possible squares the piece can move to.
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2056.Number%20of%20Valid%20Move%20Combinations%20On%20Chessboard/images/a3.png" style="width: 214px; height: 215px;" />
<pre>
<strong>Input:</strong> pieces = [&quot;bishop&quot;], positions = [[4,3]]
<strong>Output:</strong> 12
<strong>Explanation:</strong> The image above shows the possible squares the piece can move to.
</pre>

<p><strong>Example 4:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2056.Number%20of%20Valid%20Move%20Combinations%20On%20Chessboard/images/a4.png" style="width: 216px; height: 219px;" />
<pre>
<strong>Input:</strong> pieces = [&quot;rook&quot;,&quot;rook&quot;], positions = [[1,1],[8,8]]
<strong>Output:</strong> 223
<strong>Explanation:</strong> There are 15 moves for each rook which results in 15 * 15 = 225 move combinations.
However, there are two invalid move combinations:
- Move both rooks to (8, 1), where they collide.
- Move both rooks to (1, 8), where they collide.
Thus there are 225 - 2 = 223 valid move combinations.
Note that there are two valid move combinations that would result in one rook at (1, 8) and the other at (8, 1).
Even though the board state is the same, these two move combinations are considered different since the moves themselves are different.
</pre>

<p><strong>Example 5:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2056.Number%20of%20Valid%20Move%20Combinations%20On%20Chessboard/images/a5.png" style="width: 214px; height: 213px;" />
<pre>
<strong>Input:</strong> pieces = [&quot;queen&quot;,&quot;bishop&quot;], positions = [[5,7],[3,4]]
<strong>Output:</strong> 281
<strong>Explanation:</strong> There are 12 * 24 = 288 move combinations.
However, there are several invalid move combinations:
- If the queen stops at (6, 7), it blocks the bishop from moving to (6, 7) or (7, 8).
- If the queen stops at (5, 6), it blocks the bishop from moving to (5, 6), (6, 7), or (7, 8).
- If the bishop stops at (5, 2), it blocks the queen from moving to (5, 2) or (5, 1).
Of the 288 move combinations, 281 are valid.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == pieces.length </code></li>
	<li><code>n == positions.length</code></li>
	<li><code>1 &lt;= n &lt;= 4</code></li>
	<li><code>pieces</code>&nbsp;only contains the strings&nbsp;<code>&quot;rook&quot;</code>,&nbsp;<code>&quot;queen&quot;</code>, and&nbsp;<code>&quot;bishop&quot;</code>.</li>
	<li>There will be at most one queen on the chessboard.</li>
	<li><code>1 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 8</code></li>
	<li>Each&nbsp;<code>positions[i]</code>&nbsp;is distinct.</li>
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
