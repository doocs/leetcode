---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2174.Remove%20All%20Ones%20With%20Row%20and%20Column%20Flips%20II/README.md
tags:
    - 位运算
    - 广度优先搜索
    - 数组
    - 矩阵
---

<!-- problem:start -->

# [2174. 通过翻转行或列来去除所有的 1 II 🔒](https://leetcode.cn/problems/remove-all-ones-with-row-and-column-flips-ii)

[English Version](/solution/2100-2199/2174.Remove%20All%20Ones%20With%20Row%20and%20Column%20Flips%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定&nbsp;<strong>下标从 0 开始&nbsp;</strong>的 <code>m x n</code> <strong>二进制&nbsp;</strong>矩阵 <code>grid</code>。</p>

<p>在一次操作中，可以选择满足以下条件的任意 <code>i</code> 和 <code>j</code>:</p>

<ul>
	<li><code>0 &lt;= i &lt; m</code></li>
	<li><code>0 &lt;= j &lt; n</code></li>
	<li><code>grid[i][j] == 1</code></li>
</ul>

<p>并将第 <code>i</code> 行和第 <code>j</code> 列中的&nbsp;<strong>所有&nbsp;</strong>单元格的值更改为零。</p>

<p>返回<em>从&nbsp;</em><code>grid</code><em> 中删除所有 <code>1</code> 所需的最小操作数。</em></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2174.Remove%20All%20Ones%20With%20Row%20and%20Column%20Flips%20II/images/image-20220213162716-1.png" style="width: 709px; height: 200px;" />
<pre>
<strong>输入:</strong> grid = [[1,1,1],[1,1,1],[0,1,0]]
<strong>输出:</strong> 2
<strong>解释:</strong>
在第一个操作中，将第 1 行和第 1 列的所有单元格值更改为 0。
在第二个操作中，将第 0 行和第 0 列的所有单元格值更改为 0。
</pre>

<p><strong class="example">示例 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2174.Remove%20All%20Ones%20With%20Row%20and%20Column%20Flips%20II/images/image-20220213162737-2.png" style="width: 734px; height: 200px;" />
<pre>
<strong>输入:</strong> grid = [[0,1,0],[1,0,1],[0,1,0]]
<strong>输出:</strong> 2
<strong>解释:</strong>
在第一个操作中，将第 1 行和第 0 列的所有单元格值更改为 0。
在第二个操作中，将第 2 行和第 1 列的所有单元格值更改为 0。
注意，我们不能使用行 1 和列 1 执行操作，因为 grid[1][1]!= 1。
</pre>

<p><strong class="example">示例 3:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2174.Remove%20All%20Ones%20With%20Row%20and%20Column%20Flips%20II/images/image-20220213162752-3.png" style="width: 156px; height: 150px;" />
<pre>
<strong>输入:</strong> grid = [[0,0],[0,0]]
<strong>输出:</strong> 0
<strong>解释:</strong>
没有 1 可以移除，所以返回0。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 15</code></li>
	<li><code>1 &lt;= m * n &lt;= 15</code></li>
	<li><code>grid[i][j]</code> 为&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：状态压缩 + BFS

<!-- tabs:start -->

```python
class Solution:
    def removeOnes(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        state = sum(1 << (i * n + j) for i in range(m) for j in range(n) if grid[i][j])
        q = deque([state])
        vis = {state}
        ans = 0
        while q:
            for _ in range(len(q)):
                state = q.popleft()
                if state == 0:
                    return ans
                for i in range(m):
                    for j in range(n):
                        if grid[i][j] == 0:
                            continue
                        nxt = state
                        for r in range(m):
                            nxt &= ~(1 << (r * n + j))
                        for c in range(n):
                            nxt &= ~(1 << (i * n + c))
                        if nxt not in vis:
                            vis.add(nxt)
                            q.append(nxt)
            ans += 1
        return -1
```

```java
class Solution {
    public int removeOnes(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int state = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    state |= 1 << (i * n + j);
                }
            }
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(state);
        Set<Integer> vis = new HashSet<>();
        vis.add(state);
        int ans = 0;
        while (!q.isEmpty()) {
            for (int k = q.size(); k > 0; --k) {
                state = q.poll();
                if (state == 0) {
                    return ans;
                }
                for (int i = 0; i < m; ++i) {
                    for (int j = 0; j < n; ++j) {
                        if (grid[i][j] == 0) {
                            continue;
                        }
                        int nxt = state;
                        for (int r = 0; r < m; ++r) {
                            nxt &= ~(1 << (r * n + j));
                        }
                        for (int c = 0; c < n; ++c) {
                            nxt &= ~(1 << (i * n + c));
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

```cpp
class Solution {
public:
    int removeOnes(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int state = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (grid[i][j])
                    state |= (1 << (i * n + j));
        queue<int> q{{state}};
        unordered_set<int> vis{{state}};
        int ans = 0;
        while (!q.empty()) {
            for (int k = q.size(); k > 0; --k) {
                state = q.front();
                q.pop();
                if (state == 0) return ans;
                for (int i = 0; i < m; ++i) {
                    for (int j = 0; j < n; ++j) {
                        if (grid[i][j] == 0) continue;
                        int nxt = state;
                        for (int r = 0; r < m; ++r) nxt &= ~(1 << (r * n + j));
                        for (int c = 0; c < n; ++c) nxt &= ~(1 << (i * n + c));
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

```go
func removeOnes(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	state := 0
	for i, row := range grid {
		for j, v := range row {
			if v == 1 {
				state |= 1 << (i*n + j)
			}
		}
	}
	q := []int{state}
	vis := map[int]bool{state: true}
	ans := 0
	for len(q) > 0 {
		for k := len(q); k > 0; k-- {
			state = q[0]
			if state == 0 {
				return ans
			}
			q = q[1:]
			for i, row := range grid {
				for j, v := range row {
					if v == 0 {
						continue
					}
					nxt := state
					for r := 0; r < m; r++ {
						nxt &= ^(1 << (r*n + j))
					}
					for c := 0; c < n; c++ {
						nxt &= ^(1 << (i*n + c))
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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
