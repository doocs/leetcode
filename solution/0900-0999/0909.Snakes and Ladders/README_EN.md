---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0909.Snakes%20and%20Ladders/README_EN.md
tags:
    - Breadth-First Search
    - Array
    - Matrix
---

<!-- problem:start -->

# [909. Snakes and Ladders](https://leetcode.com/problems/snakes-and-ladders)

[中文文档](/solution/0900-0999/0909.Snakes%20and%20Ladders/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>n x n</code> integer matrix <code>board</code> where the cells are labeled from <code>1</code> to <code>n<sup>2</sup></code> in a <a href="https://en.wikipedia.org/wiki/Boustrophedon" target="_blank"><strong>Boustrophedon style</strong></a> starting from the bottom left of the board (i.e. <code>board[n - 1][0]</code>) and alternating direction each row.</p>

<p>You start on square <code>1</code> of the board. In each move, starting from square <code>curr</code>, do the following:</p>

<ul>
	<li>Choose a destination square <code>next</code> with a label in the range <code>[curr + 1, min(curr + 6, n<sup>2</sup>)]</code>.

    <ul>
    	<li>This choice simulates the result of a standard <strong>6-sided die roll</strong>: i.e., there are always at most 6 destinations, regardless of the size of the board.</li>
    </ul>
    </li>
    <li>If <code>next</code> has a snake or ladder, you <strong>must</strong> move to the destination of that snake or ladder. Otherwise, you move to <code>next</code>.</li>
    <li>The game ends when you reach the square <code>n<sup>2</sup></code>.</li>

</ul>

<p>A board square on row <code>r</code> and column <code>c</code> has a snake or ladder if <code>board[r][c] != -1</code>. The destination of that snake or ladder is <code>board[r][c]</code>. Squares <code>1</code> and <code>n<sup>2</sup></code> are not the starting points of any snake or ladder.</p>

<p>Note that you only take a snake or ladder at most once per dice roll. If the destination to a snake or ladder is the start of another snake or ladder, you do <strong>not</strong> follow the subsequent&nbsp;snake or ladder.</p>

<ul>
	<li>For example, suppose the board is <code>[[-1,4],[-1,3]]</code>, and on the first move, your destination square is <code>2</code>. You follow the ladder to square <code>3</code>, but do <strong>not</strong> follow the subsequent ladder to <code>4</code>.</li>
</ul>

<p>Return <em>the least number of dice rolls required to reach the square </em><code>n<sup>2</sup></code><em>. If it is not possible to reach the square, return </em><code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0909.Snakes%20and%20Ladders/images/snakes.png" style="width: 500px; height: 394px;" />
<pre>
<strong>Input:</strong> board = [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> 
In the beginning, you start at square 1 (at row 5, column 0).
You decide to move to square 2 and must take the ladder to square 15.
You then decide to move to square 17 and must take the snake to square 13.
You then decide to move to square 14 and must take the ladder to square 35.
You then decide to move to square 36, ending the game.
This is the lowest possible number of moves to reach the last square, so return 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> board = [[-1,-1],[-1,3]]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == board.length == board[i].length</code></li>
	<li><code>2 &lt;= n &lt;= 20</code></li>
	<li><code>board[i][j]</code> is either <code>-1</code> or in the range <code>[1, n<sup>2</sup>]</code>.</li>
	<li>The squares labeled <code>1</code> and <code>n<sup>2</sup></code> are not the starting points of any snake or ladder.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: BFS

We can use the Breadth-First Search (BFS) method, starting from the starting point, moving forward 1 to 6 steps each time, and then checking for snakes or ladders. If there are any, move to the destination of the snake or ladder; otherwise, move to the next square.

Specifically, we use a queue $\textit{q}$ to store the current reachable square numbers, initially putting number $1$ into the queue. At the same time, we use a set $\textit{vis}$ to record the squares that have been reached to avoid revisiting them, initially adding number $1$ to the set $\textit{vis}$.

In each operation, we take out the square number $x$ at the front of the queue. If $x$ is the endpoint, we can return the current number of steps. Otherwise, we move $x$ forward 1 to 6 steps, setting the new number as $y$. If $y$ falls outside the board, we skip it directly. Otherwise, we need to find the row and column corresponding to $y$. Since the row numbers decrease from bottom to top, and the column numbers depend on the parity of the row, we need to perform some calculations to get the row and column corresponding to $y$.

If the square corresponding to $y$ has a snake or ladder, we need to move to the destination of the snake or ladder, denoted as $z$. If $z$ has not been visited, we add $z$ to the queue and set, allowing us to continue the breadth-first search.

If we ultimately cannot reach the endpoint, we return $-1$.

The time complexity is $O(n^2)$, and the space complexity is $O(n^2)$. Here, $n$ is the length of the side of the board.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
        q = deque([1])
        vis = {1}
        ans = 0
        m = n * n
        while q:
            for _ in range(len(q)):
                x = q.popleft()
                if x == m:
                    return ans
                for y in range(x + 1, min(x + 6, m) + 1):
                    i, j = divmod(y - 1, n)
                    if i & 1:
                        j = n - j - 1
                    i = n - i - 1
                    z = y if board[i][j] == -1 else board[i][j]
                    if z not in vis:
                        vis.add(z)
                        q.append(z)
            ans += 1
        return -1
```

#### Java

```java
class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(1);
        int m = n * n;
        boolean[] vis = new boolean[m + 1];
        vis[1] = true;
        for (int ans = 0; !q.isEmpty(); ++ans) {
            for (int k = q.size(); k > 0; --k) {
                int x = q.poll();
                if (x == m) {
                    return ans;
                }
                for (int y = x + 1; y <= Math.min(x + 6, m); ++y) {
                    int i = (y - 1) / n, j = (y - 1) % n;
                    if (i % 2 == 1) {
                        j = n - j - 1;
                    }
                    i = n - i - 1;
                    int z = board[i][j] == -1 ? y : board[i][j];
                    if (!vis[z]) {
                        vis[z] = true;
                        q.offer(z);
                    }
                }
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int snakesAndLadders(vector<vector<int>>& board) {
        int n = board.size();
        queue<int> q{{1}};
        int m = n * n;
        vector<bool> vis(m + 1);
        vis[1] = true;

        for (int ans = 0; !q.empty(); ++ans) {
            for (int k = q.size(); k > 0; --k) {
                int x = q.front();
                q.pop();
                if (x == m) {
                    return ans;
                }
                for (int y = x + 1; y <= min(x + 6, m); ++y) {
                    int i = (y - 1) / n, j = (y - 1) % n;
                    if (i % 2 == 1) {
                        j = n - j - 1;
                    }
                    i = n - i - 1;
                    int z = board[i][j] == -1 ? y : board[i][j];
                    if (!vis[z]) {
                        vis[z] = true;
                        q.push(z);
                    }
                }
            }
        }
        return -1;
    }
};
```

#### Go

```go
func snakesAndLadders(board [][]int) int {
	n := len(board)
	q := []int{1}
	m := n * n
	vis := make([]bool, m+1)
	vis[1] = true

	for ans := 0; len(q) > 0; ans++ {
		for k := len(q); k > 0; k-- {
			x := q[0]
			q = q[1:]
			if x == m {
				return ans
			}
			for y := x + 1; y <= min(x+6, m); y++ {
				i, j := (y-1)/n, (y-1)%n
				if i%2 == 1 {
					j = n - j - 1
				}
				i = n - i - 1
				z := y
				if board[i][j] != -1 {
					z = board[i][j]
				}
				if !vis[z] {
					vis[z] = true
					q = append(q, z)
				}
			}
		}
	}
	return -1
}
```

#### TypeScript

```ts
function snakesAndLadders(board: number[][]): number {
    const n = board.length;
    const q: number[] = [1];
    const m = n * n;
    const vis: boolean[] = Array(m + 1).fill(false);
    vis[1] = true;

    for (let ans = 0; q.length > 0; ans++) {
        const nq: number[] = [];
        for (const x of q) {
            if (x === m) {
                return ans;
            }
            for (let y = x + 1; y <= Math.min(x + 6, m); y++) {
                let i = Math.floor((y - 1) / n);
                let j = (y - 1) % n;
                if (i % 2 === 1) {
                    j = n - j - 1;
                }
                i = n - i - 1;
                const z = board[i][j] === -1 ? y : board[i][j];
                if (!vis[z]) {
                    vis[z] = true;
                    nq.push(z);
                }
            }
        }
        q.length = 0;
        for (const x of nq) {
            q.push(x);
        }
    }
    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
