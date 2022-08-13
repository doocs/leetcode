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
<p><strong>Example 1:</strong></p>

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

<p><strong>Example 2:</strong></p>

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
        def check(a, b):
            if (a, b) not in vis:
                vis.add((a, b))
                q.append((a, b))

        n = len(grid)
        target = (n * n - 2, n * n - 1)
        q = deque([(0, 1)])
        vis = {(0, 1)}
        ans = 0
        while q:
            for _ in range(len(q)):
                a, b = q.popleft()
                if (a, b) == target:
                    return ans
                i1, j1 = a // n, a % n
                i2, j2 = b // n, b % n
                if (
                    j1 + 1 < n
                    and j2 + 1 < n
                    and grid[i1][j1 + 1] == 0
                    and grid[i2][j2 + 1] == 0
                ):
                    check(i1 * n + j1 + 1, i2 * n + j2 + 1)
                    if j1 == j2:
                        check(a, i1 * n + j2 + 1)
                if (
                    i1 + 1 < n
                    and i2 + 1 < n
                    and grid[i1 + 1][j1] == 0
                    and grid[i2 + 1][j2] == 0
                ):
                    check((i1 + 1) * n + j1, (i2 + 1) * n + j2)
                    if i1 == i2:
                        check(a, (i2 + 1) * n + j1)
            ans += 1
        return -1
```

### **Java**

```java
class Solution {
    public int minimumMoves(int[][] grid) {
        int n = grid.length;
        int[] target = new int[]{n * n - 2, n * n - 1};
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 1});
        boolean[][] vis = new boolean[n * n][n * n];
        int ans = 0;
        vis[0][1] = true;
        while (!q.isEmpty()) {
            for (int k = q.size(); k > 0; --k) {
                int[] p = q.poll();
                if (p[0] == target[0] && p[1] == target[1]) {
                    return ans;
                }
                int a = p[0], b = p[1];
                int i1 = a / n, j1 = a % n;
                int i2 = b / n, j2 = b % n;
                if (j1 + 1 < n && j2 + 1 < n && grid[i1][j1 + 1] == 0 && grid[i2][j2 + 1] == 0) {
                    check(i1 * n + j1 + 1, i2 * n + j2 + 1, q, vis);
                    if (j1 == j2) {
                        check(a, i1 * n + j2 + 1, q, vis);
                    }
                }
                if (i1 + 1 < n && i2 + 1 < n && grid[i1 + 1][j1] == 0 && grid[i2 + 1][j2] == 0) {
                    check((i1 + 1) * n + j1, (i2 + 1) * n + j2, q, vis);
                    if (i1 == i2) {
                        check(a, (i2 + 1) * n + j1, q, vis);
                    }
                }
            }
            ++ans;
        }
        return -1;
    }

    private void check(int a, int b, Deque<int[]> q, boolean[][] vis) {
        if (!vis[a][b]) {
            vis[a][b] = true;
            q.offer(new int[]{a, b});
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
        vector<int> target = {n * n - 2, n * n - 1};
        queue<vector<int>> q;
        q.push({0, 1});
        vector<vector<bool>> vis(n * n, vector<bool>(n * n));
        int ans = 0;
        vis[0][1] = true;
        while (!q.empty()) {
            for (int k = q.size(); k; --k) {
                auto p = q.front();
                if (p == target) return ans;
                q.pop();
                int a = p[0], b = p[1];
                int i1 = a / n, j1 = a % n;
                int i2 = b / n, j2 = b % n;
                if (j1 + 1 < n && j2 + 1 < n && grid[i1][j1 + 1] == 0 && grid[i2][j2 + 1] == 0) {
                    check(i1 * n + j1 + 1, i2 * n + j2 + 1, q, vis);
                    if (j1 == j2) check(a, i1 * n + j2 + 1, q, vis);
                }
                if (i1 + 1 < n && i2 + 1 < n && grid[i1 + 1][j1] == 0 && grid[i2 + 1][j2] == 0) {
                    check((i1 + 1) * n + j1, (i2 + 1) * n + j2, q, vis);
                    if (i1 == i2) check(a, (i2 + 1) * n + j1, q, vis);
                }
            }
            ++ans;
        }
        return -1;
    }

    void check(int a, int b, queue<vector<int>>& q, vector<vector<bool>>& vis) {
        if (!vis[a][b]) {
            vis[a][b] = true;
            q.push({a, b});
        }
    }
};
```

### **Go**

```go
func minimumMoves(grid [][]int) int {
	n := len(grid)
	target := []int{n*n - 2, n*n - 1}
	q := [][]int{{0, 1}}
	vis := make([][]bool, n*n)
	for i := range vis {
		vis[i] = make([]bool, n*n)
	}
	vis[0][1] = true
	ans := 0
	check := func(a, b int) {
		if !vis[a][b] {
			vis[a][b] = true
			q = append(q, []int{a, b})
		}
	}
	for len(q) > 0 {
		for k := len(q); k > 0; k-- {
			p := q[0]
			q = q[1:]
			if p[0] == target[0] && p[1] == target[1] {
				return ans
			}
			a, b := p[0], p[1]
			i1, j1 := a/n, a%n
			i2, j2 := b/n, b%n
			if j1+1 < n && j2+1 < n && grid[i1][j1+1] == 0 && grid[i2][j2+1] == 0 {
				check(i1*n+j1+1, i2*n+j2+1)
				if j1 == j2 {
					check(a, i1*n+j2+1)
				}
			}
			if i1+1 < n && i2+1 < n && grid[i1+1][j1] == 0 && grid[i2+1][j2] == 0 {
				check((i1+1)*n+j1, (i2+1)*n+j2)
				if i1 == i2 {
					check(a, (i2+1)*n+j1)
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
