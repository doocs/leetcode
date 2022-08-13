# [1284. 转化为全零矩阵的最少反转次数](https://leetcode.cn/problems/minimum-number-of-flips-to-convert-binary-matrix-to-zero-matrix)

[English Version](/solution/1200-1299/1284.Minimum%20Number%20of%20Flips%20to%20Convert%20Binary%20Matrix%20to%20Zero%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个&nbsp;<code>m x n</code>&nbsp;的二进制矩阵&nbsp;<code>mat</code>。每一步，你可以选择一个单元格并将它反转（反转表示 <code>0</code> 变 <code>1</code> ，<code>1</code> 变 <code>0</code> ）。如果存在和它相邻的单元格，那么这些相邻的单元格也会被反转。相邻的两个单元格共享同一条边。</p>

<p>请你返回将矩阵&nbsp;<code>mat</code> 转化为全零矩阵的<em>最少反转次数</em>，如果无法转化为全零矩阵，请返回&nbsp;<code>-1</code>&nbsp;。</p>

<p><strong>二进制矩阵</strong>&nbsp;的每一个格子要么是 <code>0</code> 要么是 <code>1</code> 。</p>

<p><strong>全零矩阵</strong>&nbsp;是所有格子都为 <code>0</code> 的矩阵。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1284.Minimum%20Number%20of%20Flips%20to%20Convert%20Binary%20Matrix%20to%20Zero%20Matrix/images/matrix.png" /></p>

<pre>
<strong>输入：</strong>mat = [[0,0],[0,1]]
<strong>输出：</strong>3
<strong>解释：</strong>一个可能的解是反转 (1, 0)，然后 (0, 1) ，最后是 (1, 1) 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>mat = [[0]]
<strong>输出：</strong>0
<strong>解释：</strong>给出的矩阵是全零矩阵，所以你不需要改变它。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>mat = [[1,0,0],[1,0,0]]
<strong>输出：</strong>-1
<strong>解释：</strong>该矩阵无法转变成全零矩阵
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m ==&nbsp;mat.length</code></li>
	<li><code>n ==&nbsp;mat[0].length</code></li>
	<li><code>1 &lt;= m&nbsp;&lt;= 3</code></li>
	<li><code>1 &lt;= n&nbsp;&lt;= 3</code></li>
	<li><code>mat[i][j]</code>&nbsp;是 0 或 1 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：状态压缩 + BFS**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
