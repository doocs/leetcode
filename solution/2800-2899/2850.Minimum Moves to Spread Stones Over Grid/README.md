# [2850. 将石头分散到网格图的最少移动次数](https://leetcode.cn/problems/minimum-moves-to-spread-stones-over-grid)

[English Version](/solution/2800-2899/2850.Minimum%20Moves%20to%20Spread%20Stones%20Over%20Grid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>3 * 3</code>&nbsp;，下标从 <strong>0</strong>&nbsp;开始的二维整数矩阵&nbsp;<code>grid</code>&nbsp;，分别表示每一个格子里石头的数目。网格图中总共恰好有&nbsp;<code>9</code>&nbsp;个石头，一个格子里可能会有 <strong>多个</strong>&nbsp;石头。</p>

<p>每一次操作中，你可以将一个石头从它当前所在格子移动到一个至少有一条公共边的相邻格子。</p>

<p>请你返回每个格子恰好有一个石头的 <strong>最少移动次数</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2850.Minimum%20Moves%20to%20Spread%20Stones%20Over%20Grid/images/example1-3.svg" style="width: 401px; height: 281px;" /></p>

<pre>
<b>输入：</b>grid = [[1,1,0],[1,1,1],[1,2,1]]
<b>输出：</b>3
<b>解释：</b>让每个格子都有一个石头的一个操作序列为：
1 - 将一个石头从格子 (2,1) 移动到 (2,2) 。
2 - 将一个石头从格子 (2,2) 移动到 (1,2) 。
3 - 将一个石头从格子 (1,2) 移动到 (0,2) 。
总共需要 3 次操作让每个格子都有一个石头。
让每个格子都有一个石头的最少操作次数为 3 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2850.Minimum%20Moves%20to%20Spread%20Stones%20Over%20Grid/images/example2-2.svg" style="width: 401px; height: 281px;" /></p>

<pre>
<b>输入：</b>grid = [[1,3,0],[1,0,0],[1,0,3]]
<b>输出：</b>4
<b>解释：</b>让每个格子都有一个石头的一个操作序列为：
1 - 将一个石头从格子 (0,1) 移动到 (0,2) 。
2 - 将一个石头从格子 (0,1) 移动到 (1,1) 。
3 - 将一个石头从格子 (2,2) 移动到 (1,2) 。
4 - 将一个石头从格子 (2,2) 移动到 (2,1) 。
总共需要 4 次操作让每个格子都有一个石头。
让每个格子都有一个石头的最少操作次数为 4 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>grid.length == grid[i].length == 3</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 9</code></li>
	<li><code>grid</code>&nbsp;中元素之和为&nbsp;<code>9</code> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumMoves(self, grid: List[List[int]]) -> int:
        q = deque([tuple(tuple(row) for row in grid)])
        vis = set(q)
        ans = 0
        dirs = (-1, 0, 1, 0, -1)
        while 1:
            for _ in range(len(q)):
                cur = q.popleft()
                if all(x for row in cur for x in row):
                    return ans
                for i in range(3):
                    for j in range(3):
                        if cur[i][j] > 1:
                            for a, b in pairwise(dirs):
                                x, y = i + a, j + b
                                if 0 <= x < 3 and 0 <= y < 3 and cur[x][y] < 2:
                                    nxt = [list(row) for row in cur]
                                    nxt[i][j] -= 1
                                    nxt[x][y] += 1
                                    nxt = tuple(tuple(row) for row in nxt)
                                    if nxt not in vis:
                                        vis.add(nxt)
                                        q.append(nxt)
            ans += 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumMoves(int[][] grid) {
        Deque<String> q = new ArrayDeque<>();
        q.add(f(grid));
        Set<String> vis = new HashSet<>();
        vis.add(f(grid));
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int ans = 0;; ++ans) {
            for (int k = q.size(); k > 0; --k) {
                String p = q.poll();
                if ("111111111".equals(p)) {
                    return ans;
                }
                int[][] cur = g(p);
                for (int i = 0; i < 3; ++i) {
                    for (int j = 0; j < 3; ++j) {
                        if (cur[i][j] > 1) {
                            for (int d = 0; d < 4; ++d) {
                                int x = i + dirs[d];
                                int y = j + dirs[d + 1];
                                if (x >= 0 && x < 3 && y >= 0 && y < 3 && cur[x][y] < 2) {
                                    int[][] nxt = new int[3][3];
                                    for (int r = 0; r < 3; ++r) {
                                        for (int c = 0; c < 3; ++c) {
                                            nxt[r][c] = cur[r][c];
                                        }
                                    }
                                    nxt[i][j]--;
                                    nxt[x][y]++;
                                    String s = f(nxt);
                                    if (!vis.contains(s)) {
                                        vis.add(s);
                                        q.add(s);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private String f(int[][] grid) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : grid) {
            for (int x : row) {
                sb.append(x);
            }
        }
        return sb.toString();
    }

    private int[][] g(String s) {
        int[][] grid = new int[3][3];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                grid[i][j] = s.charAt(i * 3 + j) - '0';
            }
        }
        return grid;
    }
}
```

### **C++**

```cpp

```

### **Go**

```go

```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
