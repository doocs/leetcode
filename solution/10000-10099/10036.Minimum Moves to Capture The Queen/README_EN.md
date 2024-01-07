# [10036. Minimum Moves to Capture The Queen](https://leetcode.com/problems/minimum-moves-to-capture-the-queen)

[中文文档](/solution/10000-10099/10036.Minimum%20Moves%20to%20Capture%20The%20Queen/README.md)

## Description

<p>There is a <strong>1-indexed</strong> <code>8 x 8</code> chessboard containing <code>3</code> pieces.</p>

<p>You are given <code>6</code> integers <code>a</code>, <code>b</code>, <code>c</code>, <code>d</code>, <code>e</code>, and <code>f</code> where:</p>

<ul>
	<li><code>(a, b)</code> denotes the position of the white rook.</li>
	<li><code>(c, d)</code> denotes the position of the white bishop.</li>
	<li><code>(e, f)</code> denotes the position of the black queen.</li>
</ul>

<p>Given that you can only move the white pieces, return <em>the <strong>minimum</strong> number of moves required to capture the black queen</em>.</p>

<p><strong>Note</strong> that:</p>

<ul>
	<li>Rooks can move any number of squares either vertically or horizontally, but cannot jump over other pieces.</li>
	<li>Bishops can move any number of squares diagonally, but cannot jump over other pieces.</li>
	<li>A rook or a bishop can capture the queen if it is located in a square that they can move to.</li>
	<li>The queen does not move.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/10000-10099/10036.Minimum%20Moves%20to%20Capture%20The%20Queen/images//ex1.png" style="width: 600px; height: 600px; padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>Input:</strong> a = 1, b = 1, c = 8, d = 8, e = 2, f = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can capture the black queen in two moves by moving the white rook to (1, 3) then to (2, 3).
It is impossible to capture the black queen in less than two moves since it is not being attacked by any of the pieces at the beginning.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/10000-10099/10036.Minimum%20Moves%20to%20Capture%20The%20Queen/images//ex2.png" style="width: 600px; height: 600px;padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>Input:</strong> a = 5, b = 3, c = 3, d = 4, e = 5, f = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can capture the black queen in a single move by doing one of the following: 
- Move the white rook to (5, 2).
- Move the white bishop to (5, 2).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= a, b, c, d, e, f &lt;= 8</code></li>
	<li>No two pieces are on the same square.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
