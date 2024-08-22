---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3238.Find%20the%20Number%20of%20Winning%20Players/README_EN.md
tags:
    - Array
    - Hash Table
    - Counting
---

<!-- problem:start -->

# [3238. Find the Number of Winning Players](https://leetcode.com/problems/find-the-number-of-winning-players)

[中文文档](/solution/3200-3299/3238.Find%20the%20Number%20of%20Winning%20Players/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> representing the number of players in a game and a 2D array <code>pick</code> where <code>pick[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> represents that the player <code>x<sub>i</sub></code> picked a ball of color <code>y<sub>i</sub></code>.</p>

<p>Player <code>i</code> <strong>wins</strong> the game if they pick <strong>strictly more</strong> than <code>i</code> balls of the <strong>same</strong> color. In other words,</p>

<ul>
	<li>Player 0 wins if they pick any ball.</li>
	<li>Player 1 wins if they pick at least two balls of the <em>same</em> color.</li>
	<li>...</li>
	<li>Player <code>i</code> wins if they pick at least<code>i + 1</code> balls of the <em>same</em> color.</li>
</ul>

<p>Return the number of players who <strong>win</strong> the game.</p>

<p><strong>Note</strong> that <em>multiple</em> players can win the game.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, pick = [[0,0],[1,0],[1,0],[2,1],[2,1],[2,0]]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Player 0 and player 1 win the game, while players 2 and 3 do not win.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, pick = [[1,1],[1,2],[1,3],[1,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>No player wins the game.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, pick = [[1,1],[2,4],[2,4],[2,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>Player 2 wins the game by picking 3 balls with color 4.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10</code></li>
	<li><code>1 &lt;= pick.length &lt;= 100</code></li>
	<li><code>pick[i].length == 2</code></li>
	<li><code>0 &lt;= x<sub>i</sub> &lt;= n - 1 </code></li>
	<li><code>0 &lt;= y<sub>i</sub> &lt;= 10</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We can use a 2D array $\textit{cnt}$ to record the number of balls of each color obtained by each player, and a hash table $\textit{s}$ to record the IDs of the winning players.

Traverse the $\textit{pick}$ array, for each element $[x, y]$, we increment $\textit{cnt}[x][y]$ by one. If $\textit{cnt}[x][y]$ is greater than $x$, we add $x$ to the hash table $\textit{s}$.

Finally, return the size of the hash table $\textit{s}$.

The time complexity is $O(m + n \times M)$, and the space complexity is $O(n \times M)$. Here, $m$ is the length of the $\textit{pick}$ array, and $n$ and $M$ are the number of players and the number of colors, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def winningPlayerCount(self, n: int, pick: List[List[int]]) -> int:
        cnt = [[0] * 11 for _ in range(n)]
        s = set()
        for x, y in pick:
            cnt[x][y] += 1
            if cnt[x][y] > x:
                s.add(x)
        return len(s)
```

#### Java

```java
class Solution {
    public int winningPlayerCount(int n, int[][] pick) {
        int[][] cnt = new int[n][11];
        Set<Integer> s = new HashSet<>();
        for (var p : pick) {
            int x = p[0], y = p[1];
            if (++cnt[x][y] > x) {
                s.add(x);
            }
        }
        return s.size();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int winningPlayerCount(int n, vector<vector<int>>& pick) {
        int cnt[10][11]{};
        unordered_set<int> s;
        for (const auto& p : pick) {
            int x = p[0], y = p[1];
            if (++cnt[x][y] > x) {
                s.insert(x);
            }
        }
        return s.size();
    }
};
```

#### Go

```go
func winningPlayerCount(n int, pick [][]int) int {
	cnt := make([][11]int, n)
	s := map[int]struct{}{}
	for _, p := range pick {
		x, y := p[0], p[1]
		cnt[x][y]++
		if cnt[x][y] > x {
			s[x] = struct{}{}
		}
	}
	return len(s)
}
```

#### TypeScript

```ts
function winningPlayerCount(n: number, pick: number[][]): number {
    const cnt: number[][] = Array.from({ length: n }, () => Array(11).fill(0));
    const s = new Set<number>();
    for (const [x, y] of pick) {
        if (++cnt[x][y] > x) {
            s.add(x);
        }
    }
    return s.size;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
