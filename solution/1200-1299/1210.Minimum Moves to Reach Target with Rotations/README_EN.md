# [1210. Minimum Moves to Reach Target with Rotations](https://leetcode.com/problems/minimum-moves-to-reach-target-with-rotations)

[中文文档](/solution/1200-1299/1210.Minimum%20Moves%20to%20Reach%20Target%20with%20Rotations/README.md)

## Description

<p>In an&nbsp;<code>n*n</code>&nbsp;grid, there is a snake that spans 2 cells and starts moving from the top left corner at <code>(0, 0)</code> and <code>(0, 1)</code>. The grid has empty cells represented by zeros and blocked cells represented by ones. The snake wants to reach the lower right corner at&nbsp;<code>(n-1, n-2)</code>&nbsp;and&nbsp;<code>(n-1, n-1)</code>.</p>

<p>In one move the snake can:</p>

<ul>
	<li>Move one cell to the right&nbsp;if there are no blocked cells there. This move keeps the horizontal/vertical position of the snake as it is.</li>
	<li>Move down one cell&nbsp;if there are no blocked cells there. This move keeps the horizontal/vertical position of the snake as it is.</li>
	<li>Rotate clockwise if it&#39;s in a horizontal position and the two cells under it are both empty. In that case the snake moves from&nbsp;<code>(r, c)</code>&nbsp;and&nbsp;<code>(r, c+1)</code>&nbsp;to&nbsp;<code>(r, c)</code>&nbsp;and&nbsp;<code>(r+1, c)</code>.<br />
	<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1210.Minimum%20Moves%20to%20Reach%20Target%20with%20Rotations/images/image-2.png" style="width: 300px; height: 134px;" /></li>
	<li>Rotate counterclockwise&nbsp;if it&#39;s in a vertical position and the two cells to its right are both empty. In that case the snake moves from&nbsp;<code>(r, c)</code>&nbsp;and&nbsp;<code>(r+1, c)</code>&nbsp;to&nbsp;<code>(r, c)</code>&nbsp;and&nbsp;<code>(r, c+1)</code>.<br />
	<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1210.Minimum%20Moves%20to%20Reach%20Target%20with%20Rotations/images/image-1.png" style="width: 300px; height: 121px;" /></li>
</ul>

<p>Return the minimum number of moves to reach the target.</p>

<p>If there is no way to reach the target, return&nbsp;<code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1210.Minimum%20Moves%20to%20Reach%20Target%20with%20Rotations/images/image.png" style="width: 400px; height: 439px;" /></strong></p>

<pre>
<strong>Input:</strong> grid = [[0,0,0,0,0,1],
               [1,1,0,0,1,0],
&nbsp;              [0,0,0,0,1,1],
&nbsp;              [0,0,1,0,1,0],
&nbsp;              [0,1,1,0,0,0],
&nbsp;              [0,1,1,0,0,0]]
<strong>Output:</strong> 11
<strong>Explanation:
</strong>One possible solution is [right, right, rotate clockwise, right, down, down, down, down, rotate counterclockwise, right, down].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[0,0,1,1,1,1],
&nbsp;              [0,0,0,0,1,1],
&nbsp;              [1,1,0,0,0,1],
&nbsp;              [1,1,1,0,0,1],
&nbsp;              [1,1,1,0,0,1],
&nbsp;              [1,1,1,0,0,0]]
<strong>Output:</strong> 9
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 1</code></li>
	<li>It is guaranteed that the snake starts at empty cells.</li>
</ul>

## Solutions

BFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumMoves(self, grid: List[List[int]]) -> int:
        def move(i1, j1, i2, j2):
            if 0 <= i1 < n and 0 <= j1 < n and 0 <= i2 < n and 0 <= j2 < n:
                a, b = i1 * n + j1, i2 * n + j2
                status = 0 if i1 == i2 else 1
                if (a, status) not in vis and grid[i1][j1] == 0 and grid[i2][j2] == 0:
                    q.append((a, b))
                    vis.add((a, status))

        n = len(grid)
        target = (n * n - 2, n * n - 1)
        q = deque([(0, 1)])
        vis = {(0, 0)}
        ans = 0
        while q:
            for _ in range(len(q)):
                a, b = q.popleft()
                if (a, b) == target:
                    return ans
                i1, j1 = a // n, a % n
                i2, j2 = b // n, b % n
                move(i1, j1 + 1, i2, j2 + 1)
                move(i1 + 1, j1, i2 + 1, j2)
                if i1 == i2 and i1 + 1 < n and grid[i1 + 1][j2] == 0:
                    move(i1, j1, i1 + 1, j1)
                if j1 == j2 and j1 + 1 < n and grid[i2][j1 + 1] == 0:
                    move(i1, j1, i1, j1 + 1)
            ans += 1
        return -1
```

### **Java**

```java
class Solution {
    private int n;
    private int[][] grid;
    private boolean[][] vis;
    private Deque<int[]> q = new ArrayDeque<>();

    public int minimumMoves(int[][] grid) {
        this.grid = grid;
        n = grid.length;
        vis = new boolean[n * n][2];
        int[] target = {n * n - 2, n * n - 1};
        q.offer(new int[] {0, 1});
        vis[0][0] = true;
        int ans = 0;
        while (!q.isEmpty()) {
            for (int k = q.size(); k > 0; --k) {
                var p = q.poll();
                if (p[0] == target[0] && p[1] == target[1]) {
                    return ans;
                }
                int i1 = p[0] / n, j1 = p[0] % n;
                int i2 = p[1] / n, j2 = p[1] % n;
                move(i1, j1 + 1, i2, j2 + 1);
                move(i1 + 1, j1, i2 + 1, j2);
                if (i1 == i2 && i1 + 1 < n && grid[i1 + 1][j2] == 0) {
                    move(i1, j1, i1 + 1, j1);
                }
                if (j1 == j2 && j1 + 1 < n && grid[i2][j1 + 1] == 0) {
                    move(i1, j1, i1, j1 + 1);
                }
            }
            ++ans;
        }
        return -1;
    }

    private void move(int i1, int j1, int i2, int j2) {
        if (i1 >= 0 && i1 < n && j1 >= 0 && j1 < n && i2 >= 0 && i2 < n && j2 >= 0 && j2 < n) {
            int a = i1 * n + j1, b = i2 * n + j2;
            int status = i1 == i2 ? 0 : 1;
            if (!vis[a][status] && grid[i1][j1] == 0 && grid[i2][j2] == 0) {
                q.offer(new int[] {a, b});
                vis[a][status] = true;
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumMoves(vector<vector<int>>& grid) {
        int n = grid.size();
        auto target = make_pair(n * n - 2, n * n - 1);
        queue<pair<int, int>> q;
        q.emplace(0, 1);
        bool vis[n * n][2];
        memset(vis, 0, sizeof vis);
        vis[0][0] = true;

        auto move = [&](int i1, int j1, int i2, int j2) {
            if (i1 >= 0 && i1 < n && j1 >= 0 && j1 < n && i2 >= 0 && i2 < n && j2 >= 0 && j2 < n) {
                int a = i1 * n + j1, b = i2 * n + j2;
                int status = i1 == i2 ? 0 : 1;
                if (!vis[a][status] && grid[i1][j1] == 0 && grid[i2][j2] == 0) {
                    q.emplace(a, b);
                    vis[a][status] = true;
                }
            }
        };

        int ans = 0;
        while (!q.empty()) {
            for (int k = q.size(); k; --k) {
                auto p = q.front();
                q.pop();
                if (p == target) {
                    return ans;
                }
                auto [a, b] = p;
                int i1 = a / n, j1 = a % n;
                int i2 = b / n, j2 = b % n;
                move(i1, j1 + 1, i2, j2 + 1);
                move(i1 + 1, j1, i2 + 1, j2);
                if (i1 == i2 && i1 + 1 < n && grid[i1 + 1][j2] == 0) {
                    move(i1, j1, i1 + 1, j1);
                }
                if (j1 == j2 && j1 + 1 < n && grid[i2][j1 + 1] == 0) {
                    move(i1, j1, i1, j1 + 1);
                }
            }
            ++ans;
        }
        return -1;
    }
};
```

### **Go**

```go
func minimumMoves(grid [][]int) int {
	n := len(grid)
	type pair struct{ a, b int }
	target := pair{n*n - 2, n*n - 1}
	q := []pair{pair{0, 1}}
	vis := make([][2]bool, n*n)
	vis[0][0] = true

	move := func(i1, j1, i2, j2 int) {
		if i1 >= 0 && i1 < n && j1 >= 0 && j1 < n && i2 >= 0 && i2 < n && j2 >= 0 && j2 < n {
			a, b := i1*n+j1, i2*n+j2
			status := 1
			if i1 == i2 {
				status = 0
			}
			if !vis[a][status] && grid[i1][j1] == 0 && grid[i2][j2] == 0 {
				q = append(q, pair{a, b})
				vis[a][status] = true
			}
		}
	}

	ans := 0
	for len(q) > 0 {
		for k := len(q); k > 0; k-- {
			p := q[0]
			q = q[1:]
			if p == target {
				return ans
			}
			a, b := p.a, p.b
			i1, j1 := a/n, a%n
			i2, j2 := b/n, b%n
			move(i1, j1+1, i2, j2+1)
			move(i1+1, j1, i2+1, j2)
			if i1 == i2 && i1+1 < n && grid[i1+1][j2] == 0 {
				move(i1, j1, i1+1, j1)
			}
			if j1 == j2 && j1+1 < n && grid[i2][j1+1] == 0 {
				move(i1, j1, i1, j1+1)
			}
		}
		ans++
	}
	return -1
}
```

### **TypeScript**

```ts
function minimumMoves(grid: number[][]): number {
    const n = grid.length;
    const target: number[] = [n * n - 2, n * n - 1];
    const q: number[][] = [[0, 1]];
    const vis = Array.from({ length: n * n }, () => Array(2).fill(false));
    vis[0][0] = true;

    const move = (i1: number, j1: number, i2: number, j2: number) => {
        if (
            i1 >= 0 &&
            i1 < n &&
            j1 >= 0 &&
            j1 < n &&
            i2 >= 0 &&
            i2 < n &&
            j2 >= 0 &&
            j2 < n
        ) {
            const a = i1 * n + j1;
            const b = i2 * n + j2;
            const status = i1 === i2 ? 0 : 1;
            if (!vis[a][status] && grid[i1][j1] == 0 && grid[i2][j2] == 0) {
                q.push([a, b]);
                vis[a][status] = true;
            }
        }
    };

    let ans = 0;
    while (q.length) {
        for (let k = q.length; k; --k) {
            const p: number[] = q.shift();
            if (p[0] === target[0] && p[1] === target[1]) {
                return ans;
            }
            const [i1, j1] = [~~(p[0] / n), p[0] % n];
            const [i2, j2] = [~~(p[1] / n), p[1] % n];
            move(i1, j1 + 1, i2, j2 + 1);
            move(i1 + 1, j1, i2 + 1, j2);
            if (i1 == i2 && i1 + 1 < n && grid[i1 + 1][j2] == 0) {
                move(i1, j1, i1 + 1, j1);
            }
            if (j1 == j2 && j1 + 1 < n && grid[i2][j1 + 1] == 0) {
                move(i1, j1, i1, j1 + 1);
            }
        }
        ++ans;
    }
    return -1;
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} grid
 * @return {number}
 */
var minimumMoves = function (grid) {
    const n = grid.length;
    const target = [n * n - 2, n * n - 1];
    const q = [[0, 1]];
    const vis = Array.from({ length: n * n }, () => Array(2).fill(false));
    vis[0][0] = true;

    const move = (i1, j1, i2, j2) => {
        if (
            i1 >= 0 &&
            i1 < n &&
            j1 >= 0 &&
            j1 < n &&
            i2 >= 0 &&
            i2 < n &&
            j2 >= 0 &&
            j2 < n
        ) {
            const a = i1 * n + j1;
            const b = i2 * n + j2;
            const status = i1 === i2 ? 0 : 1;
            if (!vis[a][status] && grid[i1][j1] == 0 && grid[i2][j2] == 0) {
                q.push([a, b]);
                vis[a][status] = true;
            }
        }
    };

    let ans = 0;
    while (q.length) {
        for (let k = q.length; k; --k) {
            const p = q.shift();
            if (p[0] === target[0] && p[1] === target[1]) {
                return ans;
            }
            const [i1, j1] = [~~(p[0] / n), p[0] % n];
            const [i2, j2] = [~~(p[1] / n), p[1] % n];
            move(i1, j1 + 1, i2, j2 + 1);
            move(i1 + 1, j1, i2 + 1, j2);
            if (i1 == i2 && i1 + 1 < n && grid[i1 + 1][j2] == 0) {
                move(i1, j1, i1 + 1, j1);
            }
            if (j1 == j2 && j1 + 1 < n && grid[i2][j1 + 1] == 0) {
                move(i1, j1, i1, j1 + 1);
            }
        }
        ++ans;
    }
    return -1;
};
```

### **...**

```

```

<!-- tabs:end -->
