# [864. 获取所有钥匙的最短路径](https://leetcode.cn/problems/shortest-path-to-get-all-keys)

[English Version](/solution/0800-0899/0864.Shortest%20Path%20to%20Get%20All%20Keys/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二维网格&nbsp;<code>grid</code>&nbsp;，其中：</p>

<ul>
	<li><font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">'.'</span></span></font></font> 代表一个空房间</li>
	<li><font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">'#'</span></span></font></font> 代表一堵</li>
	<li><font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">'@'</span></span></font></font>&nbsp;是起点</li>
	<li>小写字母代表钥匙</li>
	<li>大写字母代表锁</li>
</ul>

<p>我们从起点开始出发，一次移动是指向四个基本方向之一行走一个单位空间。我们不能在网格外面行走，也无法穿过一堵墙。如果途经一个钥匙，我们就把它捡起来。除非我们手里有对应的钥匙，否则无法通过锁。</p>

<p>假设 k&nbsp;为 钥匙/锁 的个数，且满足&nbsp;<code>1 &lt;= k&nbsp;&lt;= 6</code>，字母表中的前 <code>k</code>&nbsp;个字母在网格中都有自己对应的一个小写和一个大写字母。换言之，每个锁有唯一对应的钥匙，每个钥匙也有唯一对应的锁。另外，代表钥匙和锁的字母互为大小写并按字母顺序排列。</p>

<p>返回获取所有钥匙所需要的移动的最少次数。如果无法获取所有钥匙，返回&nbsp;<code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0864.Shortest%20Path%20to%20Get%20All%20Keys/images/lc-keys2.jpg" /></p>

<pre>
<strong>输入：</strong>grid = ["@.a.#","###.#","b.A.B"]
<strong>输出：</strong>8
<strong>解释：</strong>目标是获得所有钥匙，而不是打开所有锁。
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0864.Shortest%20Path%20to%20Get%20All%20Keys/images/lc-key2.jpg" /></p>

<pre>
<strong>输入：</strong>grid = ["@..aA","..B#.","....b"]
<strong>输出：</strong>6
</pre>

<p><strong>示例 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0864.Shortest%20Path%20to%20Get%20All%20Keys/images/lc-keys3.jpg" />
<pre>
<strong>输入:</strong> grid = ["@Aa"]
<strong>输出:</strong> -1</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 30</code></li>
	<li><code>grid[i][j]</code>&nbsp;只含有&nbsp;<code>'.'</code>,&nbsp;<code>'#'</code>,&nbsp;<code>'@'</code>,&nbsp;<code>'a'-</code><code>'f</code><code>'</code>&nbsp;以及&nbsp;<code>'A'-'F'</code></li>
	<li>钥匙的数目范围是&nbsp;<code>[1, 6]</code>&nbsp;</li>
	<li>每个钥匙都对应一个 <strong>不同</strong> 的字母</li>
	<li>每个钥匙正好打开一个对应的锁</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：状态压缩 + BFS**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
