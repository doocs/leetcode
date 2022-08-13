# [2174. Remove All Ones With Row and Column Flips II](https://leetcode.cn/problems/remove-all-ones-with-row-and-column-flips-ii)

[English Version](/solution/2100-2199/2174.Remove%20All%20Ones%20With%20Row%20and%20Column%20Flips%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>You are given a <strong>0-indexed</strong> <code>m x n</code> <strong>binary</strong> matrix <code>grid</code>.</p>

<p>In one operation, you can choose any <code>i</code> and <code>j</code> that meet the following conditions:</p>

<ul>
	<li><code>0 &lt;= i &lt; m</code></li>
	<li><code>0 &lt;= j &lt; n</code></li>
	<li><code>grid[i][j] == 1</code></li>
</ul>

<p>and change the values of <strong>all</strong> cells in row <code>i</code> and column <code>j</code> to zero.</p>

<p>Return <em>the <strong>minimum</strong> number of operations needed to remove all </em><code>1</code><em>&#39;s from </em><code>grid</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2174.Remove%20All%20Ones%20With%20Row%20and%20Column%20Flips%20II/images/image-20220213162716-1.png" style="width: 709px; height: 200px;" />
<pre>
<strong>Input:</strong> grid = [[1,1,1],[1,1,1],[0,1,0]]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
In the first operation, change all cell values of row 1 and column 1 to zero.
In the second operation, change all cell values of row 0 and column 0 to zero.
</pre>

<p><strong>Example 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2174.Remove%20All%20Ones%20With%20Row%20and%20Column%20Flips%20II/images/image-20220213162737-2.png" style="width: 734px; height: 200px;" />
<pre>
<strong>Input:</strong> grid = [[0,1,0],[1,0,1],[0,1,0]]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
In the first operation, change all cell values of row 1 and column 0 to zero.
In the second operation, change all cell values of row 2 and column 1 to zero.
Note that we cannot perform an operation using row 1 and column 1 because grid[1][1] != 1.
</pre>

<p><strong>Example 3:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2174.Remove%20All%20Ones%20With%20Row%20and%20Column%20Flips%20II/images/image-20220213162752-3.png" style="width: 156px; height: 150px;" />
<pre>
<strong>Input:</strong> grid = [[0,0],[0,0]]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
There are no 1&#39;s to remove so return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 15</code></li>
	<li><code>1 &lt;= m * n &lt;= 15</code></li>
	<li><code>grid[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：状态压缩 + BFS**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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
        queue<int> q {{state}};
        unordered_set<int> vis {{state}};
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

### **Go**

```go
func removeOnes(grid [][]int) int {
    m, n := len(grid), len(grid[0])
    state := 0
    for i, row := range grid {
        for j, v := range row {
            if v == 1 {
                state |= 1 << (i * n + j)
            }
        }
    }
    q := []int{state}
    vis := map[int]bool{state:true}
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
                        nxt &= ^(1 << (r * n + j))
                    }
                    for c := 0; c < n; c++ {
                        nxt &= ^(1 << (i * n + c))
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

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
