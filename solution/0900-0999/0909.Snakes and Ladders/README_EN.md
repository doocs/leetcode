# [909. Snakes and Ladders](https://leetcode.com/problems/snakes-and-ladders)

[中文文档](/solution/0900-0999/0909.Snakes%20and%20Ladders/README.md)

## Description

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

<p>A board square on row <code>r</code> and column <code>c</code> has a snake or ladder if <code>board[r][c] != -1</code>. The destination of that snake or ladder is <code>board[r][c]</code>. Squares <code>1</code> and <code>n<sup>2</sup></code> do not have a snake or ladder.</p>

<p>Note that you only take a snake or ladder at most once per move. If the destination to a snake or ladder is the start of another snake or ladder, you do <strong>not</strong> follow the subsequent&nbsp;snake or ladder.</p>

<ul>
	<li>For example, suppose the board is <code>[[-1,4],[-1,3]]</code>, and on the first move, your destination square is <code>2</code>. You follow the ladder to square <code>3</code>, but do <strong>not</strong> follow the subsequent ladder to <code>4</code>.</li>
</ul>

<p>Return <em>the least number of moves required to reach the square </em><code>n<sup>2</sup></code><em>. If it is not possible to reach the square, return </em><code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
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

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> board = [[-1,-1],[-1,3]]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == board.length == board[i].length</code></li>
	<li><code>2 &lt;= n &lt;= 20</code></li>
	<li><code>grid[i][j]</code> is either <code>-1</code> or in the range <code>[1, n<sup>2</sup>]</code>.</li>
	<li>The squares labeled <code>1</code> and <code>n<sup>2</sup></code> do not have any ladders or snakes.</li>
</ul>

## Solutions

BFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        def get(x):
            i, j = (x - 1) // n, (x - 1) % n
            if i & 1:
                j = n - 1 - j
            return n - 1 - i, j

        n = len(board)
        q = deque([1])
        vis = {1}
        ans = 0
        while q:
            for _ in range(len(q)):
                curr = q.popleft()
                if curr == n * n:
                    return ans
                for next in range(curr + 1, min(curr + 7, n * n + 1)):
                    i, j = get(next)
                    if board[i][j] != -1:
                        next = board[i][j]
                    if next not in vis:
                        q.append(next)
                        vis.add(next)
            ans += 1
        return -1
```

### **Java**

```java
class Solution {
    private int n;

    public int snakesAndLadders(int[][] board) {
        n = board.length;
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(1);
        boolean[] vis = new boolean[n * n + 1];
        vis[1] = true;
        int ans = 0;
        while (!q.isEmpty()) {
            for (int t = q.size(); t > 0; --t) {
                int curr = q.poll();
                if (curr == n * n) {
                    return ans;
                }
                for (int k = curr + 1; k <= Math.min(curr + 6, n * n); ++k) {
                    int[] p = get(k);
                    int next = k;
                    int i = p[0], j = p[1];
                    if (board[i][j] != -1) {
                        next = board[i][j];
                    }
                    if (!vis[next]) {
                        vis[next] = true;
                        q.offer(next);
                    }
                }
            }
            ++ans;
        }
        return -1;
    }

    private int[] get(int x) {
        int i = (x - 1) / n, j = (x - 1) % n;
        if (i % 2 == 1) {
            j = n - 1 - j;
        }
        return new int[]{n - 1 - i, j};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int n;

    int snakesAndLadders(vector<vector<int>>& board) {
        n = board.size();
        queue<int> q {{1}};
        vector<bool> vis(n * n + 1);
        vis[1] = true;
        int ans = 0;
        while (!q.empty()) {
            for (int t = q.size(); t; --t) {
                int curr = q.front();
                if (curr == n * n) return ans;
                q.pop();
                for (int k = curr + 1; k <= min(curr + 6, n * n); ++k) {
                    auto p = get(k);
                    int next = k;
                    int i = p[0], j = p[1];
                    if (board[i][j] != -1) next = board[i][j];
                    if (!vis[next]) {
                        vis[next] = true;
                        q.push(next);
                    }
                }
            }
            ++ans;
        }
        return -1;
    }

    vector<int> get(int x) {
        int i = (x - 1) / n, j = (x - 1) % n;
        if (i % 2 == 1) j = n - 1 - j;
        return {n - 1 - i, j};
    }
};
```

### **Go**

```go
func snakesAndLadders(board [][]int) int {
	n := len(board)
	get := func(x int) []int {
		i, j := (x-1)/n, (x-1)%n
		if i%2 == 1 {
			j = n - 1 - j
		}
		return []int{n - 1 - i, j}
	}
	q := []int{1}
	vis := make([]bool, n*n+1)
	vis[1] = true
	ans := 0
	for len(q) > 0 {
		for t := len(q); t > 0; t-- {
			curr := q[0]
			if curr == n*n {
				return ans
			}
			q = q[1:]
			for k := curr + 1; k <= curr+6 && k <= n*n; k++ {
				p := get(k)
				next := k
				i, j := p[0], p[1]
				if board[i][j] != -1 {
					next = board[i][j]
				}
				if !vis[next] {
					vis[next] = true
					q = append(q, next)
				}
			}
		}
		ans++
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
