# [864. Shortest Path to Get All Keys](https://leetcode.com/problems/shortest-path-to-get-all-keys)

[中文文档](/solution/0800-0899/0864.Shortest%20Path%20to%20Get%20All%20Keys/README.md)

## Description

<p>You are given an <code>m x n</code> grid <code>grid</code> where:</p>

<ul>
	<li><code>&#39;.&#39;</code> is an empty cell.</li>
	<li><code>&#39;#&#39;</code> is a wall.</li>
	<li><code>&#39;@&#39;</code> is the starting point.</li>
	<li>Lowercase letters represent keys.</li>
	<li>Uppercase letters represent locks.</li>
</ul>

<p>You start at the starting point and one move consists of walking one space in one of the four cardinal directions. You cannot walk outside the grid, or walk into a wall.</p>

<p>If you walk over a key, you can pick it up and you cannot walk over a lock unless you have its corresponding key.</p>

<p>For some <code><font face="monospace">1 &lt;= k &lt;= 6</font></code>, there is exactly one lowercase and one uppercase letter of the first <code>k</code> letters of the English alphabet in the grid. This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.</p>

<p>Return <em>the lowest number of moves to acquire all keys</em>. If it is impossible, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0864.Shortest%20Path%20to%20Get%20All%20Keys/images/lc-keys2.jpg" style="width: 404px; height: 245px;" />
<pre>
<strong>Input:</strong> grid = [&quot;@.a..&quot;,&quot;###.#&quot;,&quot;b.A.B&quot;]
<strong>Output:</strong> 8
<strong>Explanation:</strong> Note that the goal is to obtain all the keys not to open all the locks.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0864.Shortest%20Path%20to%20Get%20All%20Keys/images/lc-key2.jpg" style="width: 404px; height: 245px;" />
<pre>
<strong>Input:</strong> grid = [&quot;@..aA&quot;,&quot;..B#.&quot;,&quot;....b&quot;]
<strong>Output:</strong> 6
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0864.Shortest%20Path%20to%20Get%20All%20Keys/images/lc-keys3.jpg" style="width: 244px; height: 85px;" />
<pre>
<strong>Input:</strong> grid = [&quot;@Aa&quot;]
<strong>Output:</strong> -1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 30</code></li>
	<li><code>grid[i][j]</code> is either an English letter, <code>&#39;.&#39;</code>, <code>&#39;#&#39;</code>, or <code>&#39;@&#39;</code>.</li>
	<li>The number of keys in the grid is in the range <code>[1, 6]</code>.</li>
	<li>Each key in the grid is <strong>unique</strong>.</li>
	<li>Each key in the grid has a matching lock.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def shortestPathAllKeys(self, grid: List[str]) -> int:
        m, n = len(grid), len(grid[0])
        cnt, start = 0, None
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                cnt += v.islower()
                if v == '@':
                    start = (i, j)
        q = deque([(start[0], start[1], 0)])
        dirs = [-1, 0, 1, 0, -1]
        ans = 0
        mask = (1 << cnt) - 1
        vis = {(*start, 0)}
        while q:
            for _ in range(len(q)):
                i, j, state = q.popleft()
                if state == mask:
                    return ans
                for k in range(4):
                    nxt = state
                    x, y = i + dirs[k], j + dirs[k + 1]
                    if 0 <= x < m and 0 <= y < n and grid[x][y] != '#':
                        if (
                            grid[x][y].isupper()
                            and (nxt & (1 << (ord(grid[x][y]) - ord('A')))) == 0
                        ):
                            continue
                        if grid[x][y].islower():
                            nxt |= 1 << (ord(grid[x][y]) - ord('a'))
                        if (x, y, nxt) not in vis:
                            q.append((x, y, nxt))
                            vis.add((x, y, nxt))
            ans += 1
        return -1
```

### **Java**

```java
class Solution {
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length();
        int cnt = 0;
        int sx = 0, sy = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                char c = grid[i].charAt(j);
                if (Character.isLowerCase(c)) {
                    ++cnt;
                } else if (c == '@') {
                    sx = i;
                    sy = j;
                }
            }
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sx, sy, 0});
        int[] dirs = {-1, 0, 1, 0, -1};
        int ans = 0;
        int mask = (1 << cnt) - 1;
        boolean[][][] vis = new boolean[m][n][1 << cnt];
        vis[sx][sy][0] = true;
        while (!q.isEmpty()) {
            for (int t = q.size(); t > 0; --t) {
                int[] p = q.poll();
                int i = p[0], j = p[1], state = p[2];
                if (state == mask) {
                    return ans;
                }
                for (int k = 0; k < 4; ++k) {
                    int nxt = state;
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        char c = grid[x].charAt(y);
                        if (c == '#' || (Character.isUpperCase(c) && (nxt & (1 << (c - 'A'))) == 0)) {
                            continue;
                        }
                        if (Character.isLowerCase(c)) {
                            nxt |= 1 << (c - 'a');
                        }
                        if (!vis[x][y][nxt]) {
                            vis[x][y][nxt] = true;
                            q.offer(new int[]{x, y, nxt});
                        }
                    }
                }
            }
            ++ans;
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int shortestPathAllKeys(vector<string>& grid) {
        int m = grid.size(), n = grid[0].size();
        int cnt = 0;
        int sx = 0, sy = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                char c = grid[i][j];
                if (islower(c))
                    ++cnt;
                else if (c == '@') {
                    sx = i;
                    sy = j;
                }
            }
        }
        queue<vector<int>> q;
        q.push({sx, sy, 0});
        int mask = (1 << cnt) - 1;
        vector<vector<vector<bool>>> vis(m, vector<vector<bool>>(n, vector<bool>(1 << cnt)));
        vis[sx][sy][0] = true;
        int ans = 0;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            for (int t = q.size(); t; --t) {
                auto p = q.front();
                q.pop();
                int i = p[0], j = p[1], state = p[2];
                if (state == mask) return ans;
                for (int k = 0; k < 4; ++k) {
                    int nxt = state;
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        char c = grid[x][y];
                        if (c == '#' || (isupper(c) && (nxt & (1 << (c - 'A'))) == 0)) continue;
                        if (islower(c)) nxt |= 1 << (c - 'a');
                        if (!vis[x][y][nxt]) {
                            vis[x][y][nxt] = true;
                            q.push({x, y, nxt});
                        }
                    }
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
func shortestPathAllKeys(grid []string) int {
	m, n := len(grid), len(grid[0])
	cnt := 0
	sx, sy := 0, 0
	for i, row := range grid {
		for j, c := range row {
			if 'a' <= c && c <= 'z' {
				cnt++
			} else if c == '@' {
				sx, sy = i, j
			}
		}
	}
	q := [][]int{{sx, sy, 0}}
	vis := make([][][]bool, m)
	for i := range vis {
		vis[i] = make([][]bool, n)
		for j := range vis[i] {
			vis[i][j] = make([]bool, 1<<cnt)
		}
	}
	vis[sx][sy][0] = true
	dirs := []int{-1, 0, 1, 0, -1}
	ans := 0
	mask := (1 << cnt) - 1
	for len(q) > 0 {
		for t := len(q); t > 0; t-- {
			p := q[0]
			q = q[1:]
			i, j, state := p[0], p[1], p[2]
			if state == mask {
				return ans
			}
			for k := 0; k < 4; k++ {
				nxt := state
				x, y := i+dirs[k], j+dirs[k+1]
				if x >= 0 && x < m && y >= 0 && y < n {
					c := grid[x][y]
					if c == '#' {
						continue
					}
					if 'A' <= c && c <= 'Z' && (nxt&(1<<(c-'A'))) == 0 {
						continue
					}
					if 'a' <= c && c <= 'z' {
						nxt |= 1 << (c - 'a')
					}
					if !vis[x][y][nxt] {
						vis[x][y][nxt] = true
						q = append(q, []int{x, y, nxt})
					}
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
