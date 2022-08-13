# [1210. 穿过迷宫的最少移动次数](https://leetcode.cn/problems/minimum-moves-to-reach-target-with-rotations)

[English Version](/solution/1200-1299/1210.Minimum%20Moves%20to%20Reach%20Target%20with%20Rotations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你还记得那条风靡全球的贪吃蛇吗？</p>

<p>我们在一个&nbsp;<code>n*n</code>&nbsp;的网格上构建了新的迷宫地图，蛇的长度为 2，也就是说它会占去两个单元格。蛇会从左上角（<code>(0, 0)</code>&nbsp;和&nbsp;<code>(0, 1)</code>）开始移动。我们用 <code>0</code> 表示空单元格，用 1 表示障碍物。蛇需要移动到迷宫的右下角（<code>(n-1, n-2)</code>&nbsp;和&nbsp;<code>(n-1, n-1)</code>）。</p>

<p>每次移动，蛇可以这样走：</p>

<ul>
	<li>如果没有障碍，则向右移动一个单元格。并仍然保持身体的水平／竖直状态。</li>
	<li>如果没有障碍，则向下移动一个单元格。并仍然保持身体的水平／竖直状态。</li>
	<li>如果它处于水平状态并且其下面的两个单元都是空的，就顺时针旋转 90 度。蛇从（<code>(r, c)</code>、<code>(r, c+1)</code>）移动到 （<code>(r, c)</code>、<code>(r+1, c)</code>）。<br>
	<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1210.Minimum%20Moves%20to%20Reach%20Target%20with%20Rotations/images/image-2.png" style="height: 134px; width: 300px;"></li>
	<li>如果它处于竖直状态并且其右面的两个单元都是空的，就逆时针旋转 90 度。蛇从（<code>(r, c)</code>、<code>(r+1, c)</code>）移动到（<code>(r, c)</code>、<code>(r, c+1)</code>）。<br>
	<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1210.Minimum%20Moves%20to%20Reach%20Target%20with%20Rotations/images/image-1.png" style="height: 121px; width: 300px;"></li>
</ul>

<p>返回蛇抵达目的地所需的最少移动次数。</p>

<p>如果无法到达目的地，请返回&nbsp;<code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1210.Minimum%20Moves%20to%20Reach%20Target%20with%20Rotations/images/image.png" style="height: 439px; width: 400px;"></strong></p>

<pre><strong>输入：</strong>grid = [[0,0,0,0,0,1],
               [1,1,0,0,1,0],
&nbsp;              [0,0,0,0,1,1],
&nbsp;              [0,0,1,0,1,0],
&nbsp;              [0,1,1,0,0,0],
&nbsp;              [0,1,1,0,0,0]]
<strong>输出：</strong>11
<strong>解释：
</strong>一种可能的解决方案是 [右, 右, 顺时针旋转, 右, 下, 下, 下, 下, 逆时针旋转, 右, 下]。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>grid = [[0,0,1,1,1,1],
&nbsp;              [0,0,0,0,1,1],
&nbsp;              [1,1,0,0,0,1],
&nbsp;              [1,1,1,0,0,1],
&nbsp;              [1,1,1,0,0,1],
&nbsp;              [1,1,1,0,0,0]]
<strong>输出：</strong>9
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 1</code></li>
	<li>蛇保证从空单元格开始出发。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
