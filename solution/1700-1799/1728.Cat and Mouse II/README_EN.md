---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1728.Cat%20and%20Mouse%20II/README_EN.md
rating: 2849
source: Weekly Contest 224 Q4
tags:
    - Graph
    - Topological Sort
    - Memoization
    - Array
    - Math
    - Dynamic Programming
    - Game Theory
    - Matrix
---

<!-- problem:start -->

# [1728. Cat and Mouse II](https://leetcode.com/problems/cat-and-mouse-ii)

[中文文档](/solution/1700-1799/1728.Cat%20and%20Mouse%20II/README.md)

## Description

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canMouseWin(self, grid: List[str], catJump: int, mouseJump: int) -> bool:
        dirs = [0, 1, 0, -1, 0]
        m = len(grid)
        n = len(grid[0])
        nFloors = 0
        cat = 0  # cat's position
        mouse = 0  # mouse's position

        def hash(i: int, j: int) -> int:
            return i * n + j

        for i in range(m):
            for j in range(n):
                if grid[i][j] != "#":
                    nFloors += 1
                if grid[i][j] == "C":
                    cat = hash(i, j)
                elif grid[i][j] == "M":
                    mouse = hash(i, j)

        # dp(i, j, k) := True if mouse can win w//
        # Cat on (i // 8, i % 8), mouse on (j // 8, j % 8), and turns = k
        @functools.lru_cache(None)
        def dp(cat: int, mouse: int, turn: int) -> bool:
            # We already search whole touchable grid
            if turn == nFloors * 2:
                return False

            if turn % 2 == 0:
                # mouse's turn
                i = mouse // n
                j = mouse % n
                for k in range(4):
                    for jump in range(mouseJump + 1):
                        x = i + dirs[k] * jump
                        y = j + dirs[k + 1] * jump
                        if x < 0 or x == m or y < 0 or y == n:
                            break
                        if grid[x][y] == "#":
                            break
                        if grid[x][y] == "F":  # Mouse eats the food, so mouse win
                            return True
                        if dp(cat, hash(x, y), turn + 1):
                            return True
                # Mouse can't win, so mouse lose
                return False
            else:
                # cat's turn
                i = cat // n
                j = cat % n
                for k in range(4):
                    for jump in range(catJump + 1):
                        x = i + dirs[k] * jump
                        y = j + dirs[k + 1] * jump
                        if x < 0 or x == m or y < 0 or y == n:
                            break
                        if grid[x][y] == "#":
                            break
                        if grid[x][y] == "F":  # Cat eats the food, so mouse lose
                            return False
                        nextCat = hash(x, y)
                        if nextCat == mouse:  # Cat catches mouse, so mouse lose
                            return False
                        if not dp(nextCat, mouse, turn + 1):
                            return False
                # Cat can't win, so mouse win
                return True

        return dp(cat, mouse, 0)
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
