# [1197. Minimum Knight Moves](https://leetcode.com/problems/minimum-knight-moves)

[中文文档](/solution/1100-1199/1197.Minimum%20Knight%20Moves/README.md)

## Description

<p>In an <strong>infinite</strong> chess board with coordinates from <code>-infinity</code>&nbsp;to <code>+infinity</code>, you have a <strong>knight</strong> at square&nbsp;<code>[0, 0]</code>.</p>

<p>A&nbsp;knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.</p>

<p><img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1197.Minimum%20Knight%20Moves/images/knight.png" style="height: 200px; width: 200px;" /></p>

<p>Return the&nbsp;minimum number of steps needed to move the knight to the square <code>[x, y]</code>.&nbsp; It is guaranteed the answer exists.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> x = 2, y = 1
<strong>Output:</strong> 1
<strong>Explanation: </strong>[0, 0] &rarr; [2, 1]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> x = 5, y = 5
<strong>Output:</strong> 4
<strong>Explanation: </strong>[0, 0] &rarr; [2, 1] &rarr; [4, 2] &rarr; [3, 4] &rarr; [5, 5]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>|x| + |y| &lt;= 300</code></li>
</ul>

## Solutions

BFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minKnightMoves(self, x: int, y: int) -> int:
        q = deque([(0, 0)])
        ans = 0
        vis = set([(0, 0)])
        while q:
            for _ in range(len(q), 0, -1):
                i, j = q.popleft()
                if (i, j) == (x, y):
                    return ans
                for a, b in [[-2, 1], [-1, 2], [1, 2], [2, 1], [2, -1], [1, -2], [-1, -2], [-2, -1]]:
                    c, d = i + a, j + b
                    if (c, d) not in vis:
                        vis.add((c, d))
                        q.append((c, d))
            ans += 1
        return -1
```

### **Java**

```java
class Solution {
    public int minKnightMoves(int x, int y) {
        x += 310;
        y += 310;
        int ans = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{310, 310});
        boolean[][] vis = new boolean[700][700];
        vis[310][310] = true;
        int[][] dirs = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
        while (!q.isEmpty()) {
            for (int k = q.size(); k > 0; --k) {
                int[] p = q.poll();
                if (p[0] == x && p[1] == y) {
                    return ans;
                }
                for (int[] dir : dirs) {
                    int c = p[0] + dir[0];
                    int d = p[1] + dir[1];
                    if (!vis[c][d]) {
                        vis[c][d] = true;
                        q.offer(new int[]{c, d});
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
    int minKnightMoves(int x, int y) {
        x += 310;
        y += 310;
        int ans = 0;
        queue<pair<int, int>> q;
        q.push({310, 310});
        vector<vector<bool>> vis(700, vector<bool>(700));
        vis[310][310] = true;
        vector<vector<int>> dirs = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
        while (!q.empty())
        {
            for (int k = q.size(); k > 0; --k)
            {
                auto p = q.front();
                q.pop();
                if (p.first == x && p.second == y) return ans;
                for (auto& dir : dirs)
                {
                    int c = p.first + dir[0], d = p.second + dir[1];
                    if (!vis[c][d])
                    {
                        vis[c][d] = true;
                        q.push({c, d});
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
func minKnightMoves(x int, y int) int {
	x, y = x+310, y+310
	ans := 0
	q := [][]int{{310, 310}}
	vis := make([][]bool, 700)
	for i := range vis {
		vis[i] = make([]bool, 700)
	}
	dirs := [][]int{{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}}
	for len(q) > 0 {
		for k := len(q); k > 0; k-- {
			p := q[0]
			q = q[1:]
			if p[0] == x && p[1] == y {
				return ans
			}
			for _, dir := range dirs {
				c, d := p[0]+dir[0], p[1]+dir[1]
				if !vis[c][d] {
					vis[c][d] = true
					q = append(q, []int{c, d})
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
