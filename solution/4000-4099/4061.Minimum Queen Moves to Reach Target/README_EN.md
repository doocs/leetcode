---
comments: true
difficulty: Easy
rating: 1206
source: Biweekly Contest 192 Q1
tags:
    - Array
    - Math
---

<!-- problem:start -->

# [4061. Minimum Queen Moves to Reach Target](https://leetcode.com/problems/minimum-queen-moves-to-reach-target)

[中文文档](/solution/4000-4099/4061.Minimum%20Queen%20Moves%20to%20Reach%20Target/README.md)

## Description

<!-- description:start -->

<p>There is an <code>8 x 8</code> empty chessboard with <strong>1-indexed</strong> rows and columns.</p>

<p>You are given an array <code>source = [sr, sc]</code> representing the starting position of a <strong>queen</strong>, and an array <code>target = [tr, tc]</code> representing the target position.</p>

<p>In one move, the queen travels one or more squares along a single <strong>row</strong>, <strong>column</strong>, or <strong>diagonal</strong>, staying within the board.</p>

<p>Return the <strong>minimum</strong> number of moves for the queen to land <strong>exactly</strong> on <code>target</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">source = [8,1], target = [1,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p><strong>​​​​​​​<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4061.Minimum%20Queen%20Moves%20to%20Reach%20Target/images/111.png" style="width: 300px; height: 303px;" />​​​​​​​</strong></p>

<p>A single diagonal move takes the queen straight from <code>(8, 1)</code> to <code>(1, 8)</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">source = [4,2], target = [1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4061.Minimum%20Queen%20Moves%20to%20Reach%20Target/images/1e602ed4-c525-4be4-a6a9-6804cb7d2a55.png" style="width: 300px; height: 305px;" />​​​​​​​</p>

<p>The queen moves from <code>(4, 2)</code> to <code>(4, 3)</code>, then from <code>(4, 3)</code> to <code>(1, 3)</code>, reaching the target in 2 moves.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">source = [1,1], target = [1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The queen is already at the target position, so no moves are needed.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong>​​​​​​​</p>

<ul>
	<li><code>source == [sr, sc]</code></li>
	<li><code>target == [tr, tc]</code></li>
	<li><code>1 &lt;= sr, sc, tr, tc &lt;= 8</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Case Analysis

<!-- thinking:start -->

> **Thinking**
>
> The board is only $8\times 8$, so a BFS from the source also finds the shortest path. One queen move reaches any square on the same row, column, or diagonal, and the distance can only be $0$, $1$, or $2$.
>
> When the two squares differ, move along the row to $(s_r, t_c)$ and then along the column to $(t_r, t_c)$. Two moves always arrive, and the board is empty.
>
> If that intermediate square coincides with either end, the source and the target already share a row or a column, so one move is enough. It remains only to test equality, a shared row or column, and a shared diagonal.

<!-- thinking:end -->

The answer is $0$ when $(s_r, s_c)$ and $(t_r, t_c)$ are the same square.

A queen moves any number of squares along one row, one column, or one diagonal. The answer is $1$ when $s_r=t_r$, $s_c=t_c$, or $|s_r-t_r|=|s_c-t_c|$.

Every remaining pair takes two moves. Go from $(s_r, s_c)$ to $(s_r, t_c)$, then from $(s_r, t_c)$ to $(t_r, t_c)$. The squares share neither a row nor a column, so the intermediate square differs from both ends. The board is empty, and both moves are legal. The answer is $2$.

The time complexity is $O(1)$ and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minQueenMoves(self, source: list[int], target: list[int]) -> int:
        sr, sc = source
        tr, tc = target
        if sr == tr and sc == tc:
            return 0
        if sr == tr or sc == tc or abs(sr - tr) == abs(sc - tc):
            return 1
        return 2
```

#### Java

```java
class Solution {
    public int minQueenMoves(int[] source, int[] target) {
        int sr = source[0], sc = source[1];
        int tr = target[0], tc = target[1];
        if (sr == tr && sc == tc) {
            return 0;
        }
        if (sr == tr || sc == tc || Math.abs(sr - tr) == Math.abs(sc - tc)) {
            return 1;
        }
        return 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minQueenMoves(vector<int>& source, vector<int>& target) {
        int sr = source[0], sc = source[1];
        int tr = target[0], tc = target[1];
        if (sr == tr && sc == tc) {
            return 0;
        }
        if (sr == tr || sc == tc || abs(sr - tr) == abs(sc - tc)) {
            return 1;
        }
        return 2;
    }
};
```

#### Go

```go
func minQueenMoves(source []int, target []int) int {
	sr, sc := source[0], source[1]
	tr, tc := target[0], target[1]
	if sr == tr && sc == tc {
		return 0
	}
	if sr == tr || sc == tc || abs(sr-tr) == abs(sc-tc) {
		return 1
	}
	return 2
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function minQueenMoves(source: number[], target: number[]): number {
    const [sr, sc] = source;
    const [tr, tc] = target;
    if (sr === tr && sc === tc) {
        return 0;
    }
    if (sr === tr || sc === tc || Math.abs(sr - tr) === Math.abs(sc - tc)) {
        return 1;
    }
    return 2;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
