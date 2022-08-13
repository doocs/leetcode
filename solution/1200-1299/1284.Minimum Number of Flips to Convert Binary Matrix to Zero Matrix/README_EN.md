# [1284. Minimum Number of Flips to Convert Binary Matrix to Zero Matrix](https://leetcode.com/problems/minimum-number-of-flips-to-convert-binary-matrix-to-zero-matrix)

[中文文档](/solution/1200-1299/1284.Minimum%20Number%20of%20Flips%20to%20Convert%20Binary%20Matrix%20to%20Zero%20Matrix/README.md)

## Description

<p>Given a <code>m x n</code> binary matrix <code>mat</code>. In one step, you can choose one cell and flip it and all the four neighbors of it if they exist (Flip is changing <code>1</code> to <code>0</code> and <code>0</code> to <code>1</code>). A pair of cells are called neighbors if they share one edge.</p>

<p>Return the <em>minimum number of steps</em> required to convert <code>mat</code> to a zero matrix or <code>-1</code> if you cannot.</p>

<p>A <strong>binary matrix</strong> is a matrix with all cells equal to <code>0</code> or <code>1</code> only.</p>

<p>A <strong>zero matrix</strong> is a matrix with all cells equal to <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1284.Minimum%20Number%20of%20Flips%20to%20Convert%20Binary%20Matrix%20to%20Zero%20Matrix/images/matrix.png" style="width: 409px; height: 86px;" />
<pre>
<strong>Input:</strong> mat = [[0,0],[0,1]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> One possible solution is to flip (1, 0) then (0, 1) and finally (1, 1) as shown.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> mat = [[0]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Given matrix is a zero matrix. We do not need to change it.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> mat = [[1,0,0],[1,0,0]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> Given matrix cannot be a zero matrix.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 3</code></li>
	<li><code>mat[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minFlips(self, mat: List[List[int]]) -> int:
        m, n = len(mat), len(mat[0])
        state = sum(1 << (i * n + j) for i in range(m) for j in range(n) if mat[i][j])
        q = deque([state])
        vis = {state}
        ans = 0
        dirs = [0, -1, 0, 1, 0, 0]
        while q:
            for _ in range(len(q)):
                state = q.popleft()
                if state == 0:
                    return ans
                for i in range(m):
                    for j in range(n):
                        nxt = state
                        for k in range(5):
                            x, y = i + dirs[k], j + dirs[k + 1]
                            if not 0 <= x < m or not 0 <= y < n:
                                continue
                            if nxt & (1 << (x * n + y)):
                                nxt -= 1 << (x * n + y)
                            else:
                                nxt |= 1 << (x * n + y)
                        if nxt not in vis:
                            vis.add(nxt)
                            q.append(nxt)
            ans += 1
        return -1
```

### **Java**

```java
class Solution {
    public int minFlips(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int state = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] == 1) {
                    state |= 1 << (i * n + j);
                }
            }
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(state);
        Set<Integer> vis = new HashSet<>();
        vis.add(state);
        int ans = 0;
        int[] dirs = {0, -1, 0, 1, 0, 0};
        while (!q.isEmpty()) {
            for (int t = q.size(); t > 0; --t) {
                state = q.poll();
                if (state == 0) {
                    return ans;
                }
                for (int i = 0; i < m; ++i) {
                    for (int j = 0; j < n; ++j) {
                        int nxt = state;
                        for (int k = 0; k < 5; ++k) {
                            int x = i + dirs[k], y = j + dirs[k + 1];
                            if (x < 0 || x >= m || y < 0 || y >= n) {
                                continue;
                            }
                            if ((nxt & (1 << (x * n + y))) != 0) {
                                nxt -= 1 << (x * n + y);
                            } else {
                                nxt |= 1 << (x * n + y);
                            }
                        }
                        if (!vis.contains(nxt)) {
                            vis.add(nxt);
                            q.offer(nxt);
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
    int minFlips(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        int state = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (mat[i][j])
                    state |= (1 << (i * n + j));
        queue<int> q {{state}};
        unordered_set<int> vis {{state}};
        int ans = 0;
        vector<int> dirs = {0, -1, 0, 1, 0, 0};
        while (!q.empty()) {
            for (int t = q.size(); t; --t) {
                state = q.front();
                if (state == 0) return ans;
                q.pop();
                for (int i = 0; i < m; ++i) {
                    for (int j = 0; j < n; ++j) {
                        int nxt = state;
                        for (int k = 0; k < 5; ++k) {
                            int x = i + dirs[k], y = j + dirs[k + 1];
                            if (x < 0 || x >= m || y < 0 || y >= n) continue;
                            if ((nxt & (1 << (x * n + y))) != 0)
                                nxt -= 1 << (x * n + y);
                            else
                                nxt |= 1 << (x * n + y);
                        }
                        if (!vis.count(nxt)) {
                            vis.insert(nxt);
                            q.push(nxt);
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
func minFlips(mat [][]int) int {
	m, n := len(mat), len(mat[0])
	state := 0
	for i, row := range mat {
		for j, v := range row {
			if v == 1 {
				state |= 1 << (i*n + j)
			}
		}
	}
	q := []int{state}
	vis := map[int]bool{state: true}
	ans := 0
	dirs := []int{0, -1, 0, 1, 0, 0}
	for len(q) > 0 {
		for t := len(q); t > 0; t-- {
			state = q[0]
			if state == 0 {
				return ans
			}
			q = q[1:]
			for i := 0; i < m; i++ {
				for j := 0; j < n; j++ {
					nxt := state
					for k := 0; k < 5; k++ {
						x, y := i+dirs[k], j+dirs[k+1]
						if x < 0 || x >= m || y < 0 || y >= n {
							continue
						}
						if (nxt & (1 << (x*n + y))) != 0 {
							nxt -= 1 << (x*n + y)
						} else {
							nxt |= 1 << (x*n + y)
						}
					}
					if !vis[nxt] {
						vis[nxt] = true
						q = append(q, nxt)
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
