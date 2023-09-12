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

**方法一：朴素 BFS**

题目实际上是求一个状态图中从初始状态到目标状态的最短路径，因此可以使用 BFS 求解。初始状态为 `grid`，目标状态为 `[[1, 1, 1], [1, 1, 1], [1, 1, 1]]`，在每次操作中，我们可以将一个单元格大于 $1$ 的石头移动到相邻的一个不超过 $1$ 的单元格。如果找到了目标状态，那么就可以返回当前的层数，即为最少移动次数。

**方法二：状态压缩动态规划**

我们可以把所有值为 $0$ 的单元格坐标 $(i, j)$ 放入数组 $left$ 中，如果单元格的值 $v$ 大于 $1$，那么我们把 $v-1$ 个坐标 $(i, j)$ 放入数组 $right$ 中。那么问题就转化为，每个 $right$ 中的坐标 $(i, j)$ 都要移动到 $left$ 中的一个坐标 $(x, y)$，求最少的移动次数。

我们记 $left$ 的长度为 $n$，那么我们可以使用 $n$ 位二进制数来表示 $left$ 中的每个坐标是否被 $right$ 中的坐标填充，其中 $1$ 表示被填充，而 $0$ 表示未被填充。初始时 $f[i] = \infty$，其余 $f[0]=0$。

考虑 $f[i]$，记当前 $i$ 的二进制表示中 $1$ 的个数为 $k$，我们在 $[0..n)$ 的范围内枚举 $j$，如果 $i$ 的第 $j$ 位为 $1$，那么 $f[i]$ 可以由 $f[i \oplus (1 << j)]$ 转移而来，转移的代价为 $cal(left[k-1], right[j])$，其中 $cal$ 表示两个坐标之间的曼哈顿距离。最终答案为 $f[(1 << n) - 1]$。

时间复杂度 $O(n \times 2^n)$，空间复杂度 $O(2^n)$。其中 $n$ 表示 $left$ 的长度，本题中 $n \le 9$。

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

```python
class Solution:
    def minimumMoves(self, grid: List[List[int]]) -> int:
        def cal(a: tuple, b: tuple) -> int:
            return abs(a[0] - b[0]) + abs(a[1] - b[1])

        left, right = [], []
        for i in range(3):
            for j in range(3):
                if grid[i][j] == 0:
                    left.append((i, j))
                else:
                    for _ in range(grid[i][j] - 1):
                        right.append((i, j))

        n = len(left)
        f = [inf] * (1 << n)
        f[0] = 0
        for i in range(1, 1 << n):
            k = i.bit_count()
            for j in range(n):
                if i >> j & 1:
                    f[i] = min(f[i], f[i ^ (1 << j)] + cal(left[k - 1], right[j]))
        return f[-1]
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

```java
class Solution {
    public int minimumMoves(int[][] grid) {
        List<int[]> left = new ArrayList<>();
        List<int[]> right = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (grid[i][j] == 0) {
                    left.add(new int[] {i, j});
                } else {
                    for (int k = 1; k < grid[i][j]; ++k) {
                        right.add(new int[] {i, j});
                    }
                }
            }
        }
        int n = left.size();
        int[] f = new int[1 << n];
        Arrays.fill(f, 1 << 30);
        f[0] = 0;
        for (int i = 1; i < 1 << n; ++i) {
            int k = Integer.bitCount(i);
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    f[i] = Math.min(f[i], f[i ^ (1 << j)] + cal(left.get(k - 1), right.get(j)));
                }
            }
        }
        return f[(1 << n) - 1];
    }

    private int cal(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumMoves(vector<vector<int>>& grid) {
        using pii = pair<int, int>;
        vector<pii> left, right;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (grid[i][j] == 0) {
                    left.emplace_back(i, j);
                } else {
                    for (int k = 1; k < grid[i][j]; ++k) {
                        right.emplace_back(i, j);
                    }
                }
            }
        }
        auto cal = [](pii a, pii b) {
            return abs(a.first - b.first) + abs(a.second - b.second);
        };
        int n = left.size();
        int f[1 << n];
        memset(f, 0x3f, sizeof(f));
        f[0] = 0;
        for (int i = 1; i < 1 << n; ++i) {
            int k = __builtin_popcount(i);
            for (int j = 0; j < n; ++j) {
                if (i >> j & 1) {
                    f[i] = min(f[i], f[i ^ (1 << j)] + cal(left[k - 1], right[j]));
                }
            }
        }
        return f[(1 << n) - 1];
    }
};
```

### **Go**

```go
func minimumMoves(grid [][]int) int {
	left := [][2]int{}
	right := [][2]int{}
	for i := 0; i < 3; i++ {
		for j := 0; j < 3; j++ {
			if grid[i][j] == 0 {
				left = append(left, [2]int{i, j})
			} else {
				for k := 1; k < grid[i][j]; k++ {
					right = append(right, [2]int{i, j})
				}
			}
		}
	}
	cal := func(a, b [2]int) int {
		return abs(a[0]-b[0]) + abs(a[1]-b[1])
	}
	n := len(left)
	f := make([]int, 1<<n)
	f[0] = 0
	for i := 1; i < 1<<n; i++ {
		f[i] = 1 << 30
		k := bits.OnesCount(uint(i))
		for j := 0; j < n; j++ {
			if i>>j&1 == 1 {
				f[i] = min(f[i], f[i^(1<<j)]+cal(left[k-1], right[j]))
			}
		}
	}
	return f[(1<<n)-1]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

```ts
function minimumMoves(grid: number[][]): number {
    const left: number[][] = [];
    const right: number[][] = [];
    for (let i = 0; i < 3; ++i) {
        for (let j = 0; j < 3; ++j) {
            if (grid[i][j] === 0) {
                left.push([i, j]);
            } else {
                for (let k = 1; k < grid[i][j]; ++k) {
                    right.push([i, j]);
                }
            }
        }
    }
    const cal = (a: number[], b: number[]) => {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    };
    const n = left.length;
    const f: number[] = Array(1 << n).fill(1 << 30);
    f[0] = 0;
    for (let i = 0; i < 1 << n; ++i) {
        let k = 0;
        for (let j = 0; j < n; ++j) {
            if ((i >> j) & 1) {
                ++k;
            }
        }
        for (let j = 0; j < n; ++j) {
            if ((i >> j) & 1) {
                f[i] = Math.min(f[i], f[i ^ (1 << j)] + cal(left[k - 1], right[j]));
            }
        }
    }
    return f[(1 << n) - 1];
}
```

### **...**

```

```

<!-- tabs:end -->
