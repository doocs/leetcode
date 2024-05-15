---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0305.Number%20of%20Islands%20II/README.md
tags:
    - 并查集
    - 数组
    - 哈希表
---

# [305. 岛屿数量 II 🔒](https://leetcode.cn/problems/number-of-islands-ii)

[English Version](/solution/0300-0399/0305.Number%20of%20Islands%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>m x n</code> 的二进制网格 <code>grid</code> 。网格表示一个地图，其中，<code>0</code> 表示水，<code>1</code> 表示陆地。最初，<code>grid</code> 中的所有单元格都是水单元格（即，所有单元格都是 <code>0</code>）。</p>

<p>可以通过执行 <code>addLand</code> 操作，将某个位置的水转换成陆地。给你一个数组 <code>positions</code> ，其中 <code>positions[i] = [r<sub>i</sub>, c<sub>i</sub>]</code> 是要执行第 <code>i</code> 次操作的位置 <code>(r<sub>i</sub>, c<sub>i</sub>)</code> 。</p>

<p>返回一个整数数组 <code>answer</code> ，其中 <code>answer[i]</code> 是将单元格 <code>(r<sub>i</sub>, c<sub>i</sub>)</code> 转换为陆地后，地图中岛屿的数量。</p>

<p><strong>岛屿</strong> 的定义是被「水」包围的「陆地」，通过水平方向或者垂直方向上相邻的陆地连接而成。你可以假设地图网格的四边均被无边无际的「水」所包围。</p>
&nbsp;

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0305.Number%20of%20Islands%20II/images/tmp-grid.jpg" style="width: 500px; height: 294px;" />
<pre>
<strong>输入：</strong>m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
<strong>输出：</strong>[1,1,2,3]
<strong>解释：</strong>
起初，二维网格&nbsp;<code>grid</code>&nbsp;被全部注入「水」。（0 代表「水」，1 代表「陆地」）
- 操作&nbsp;#1：<code>addLand(0, 0)</code> 将&nbsp;<code>grid[0][0]</code> 的水变为陆地。此时存在 1 个岛屿。
- 操作&nbsp;#2：<code>addLand(0, 1)</code> 将&nbsp;<code>grid[0][1]</code> 的水变为陆地。此时存在 1 个岛屿。
- 操作&nbsp;#3：<code>addLand(1, 2)</code> 将&nbsp;<code>grid[1][2]</code> 的水变为陆地。此时存在 2 个岛屿。
- 操作&nbsp;#4：<code>addLand(2, 1)</code> 将&nbsp;<code>grid[2][1]</code> 的水变为陆地。此时存在 3 个岛屿。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>m = 1, n = 1, positions = [[0,0]]
<strong>输出：</strong>[1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m, n, positions.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>4</sup></code></li>
	<li><code>positions[i].length == 2</code></li>
	<li><code>0 &lt;= r<sub>i</sub> &lt; m</code></li>
	<li><code>0 &lt;= c<sub>i</sub> &lt; n</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以设计一个时间复杂度 <code>O(k log(mn))</code> 的算法解决此问题吗？（其中 <code>k == positions.length</code>）</p>

## 解法

### 方法一：并查集

我们用一个二维数组 $grid$ 来表示一个地图，其中 $0$ 和 $1$ 分别表示水和陆地。初始时 $grid$ 中的所有单元格都是水单元格（即所有单元格都是 $0$），用一个变量 $cnt$ 来记录岛屿的数量。而岛屿之间的连通关系可以用一个并查集 $uf$ 来维护。

接下来，我们遍历数组 $positions$ 中的每个位置 $(i, j)$，如果 $grid[i][j]$ 为 $1$，说明该位置已经是陆地，我们直接将 $cnt$ 添加到答案中；否则，我们将 $grid[i][j]$ 的值改为 $1$，并且将 $cnt$ 的值增加 $1$。然后，我们遍历该位置的上下左右四个方向，如果某个方向的位置为 $1$，并且该位置与 $(i, j)$ 不属于同一个连通分量，那么我们就将该位置与 $(i, j)$ 进行合并，同时将 $cnt$ 的值减少 $1$。遍历完该位置的上下左右四个方向之后，我们将 $cnt$ 添加到答案中。

时间复杂度 $O(k \times \alpha(m \times n))$ 或 $O(k \times \log(m \times n))$，其中 $k$ 是 $positions$ 的长度，而 $\alpha$ 是阿克曼函数的反函数，本题中 $\alpha(m \times n)$ 可以认为是一个很小的常数。

<!-- tabs:start -->

```python
class UnionFind:
    def __init__(self, n: int):
        self.p = list(range(n))
        self.size = [1] * n

    def find(self, x: int):
        if self.p[x] != x:
            self.p[x] = self.find(self.p[x])
        return self.p[x]

    def union(self, a: int, b: int) -> bool:
        pa, pb = self.find(a - 1), self.find(b - 1)
        if pa == pb:
            return False
        if self.size[pa] > self.size[pb]:
            self.p[pb] = pa
            self.size[pa] += self.size[pb]
        else:
            self.p[pa] = pb
            self.size[pb] += self.size[pa]
        return True


class Solution:
    def numIslands2(self, m: int, n: int, positions: List[List[int]]) -> List[int]:
        uf = UnionFind(m * n)
        grid = [[0] * n for _ in range(m)]
        ans = []
        dirs = (-1, 0, 1, 0, -1)
        cnt = 0
        for i, j in positions:
            if grid[i][j]:
                ans.append(cnt)
                continue
            grid[i][j] = 1
            cnt += 1
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if (
                    0 <= x < m
                    and 0 <= y < n
                    and grid[x][y]
                    and uf.union(i * n + j, x * n + y)
                ):
                    cnt -= 1
            ans.append(cnt)
        return ans
```

```java
class UnionFind {
    private final int[] p;
    private final int[] size;

    public UnionFind(int n) {
        p = new int[n];
        size = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    public boolean union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return false;
        }
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        return true;
    }
}

class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[][] grid = new int[m][n];
        UnionFind uf = new UnionFind(m * n);
        int[] dirs = {-1, 0, 1, 0, -1};
        int cnt = 0;
        List<Integer> ans = new ArrayList<>();
        for (var p : positions) {
            int i = p[0], j = p[1];
            if (grid[i][j] == 1) {
                ans.add(cnt);
                continue;
            }
            grid[i][j] = 1;
            ++cnt;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1
                    && uf.union(i * n + j, x * n + y)) {
                    --cnt;
                }
            }
            ans.add(cnt);
        }
        return ans;
    }
}
```

```cpp
class UnionFind {
public:
    UnionFind(int n) {
        p = vector<int>(n);
        size = vector<int>(n, 1);
        iota(p.begin(), p.end(), 0);
    }

    bool unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return false;
        }
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        return true;
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

private:
    vector<int> p, size;
};

class Solution {
public:
    vector<int> numIslands2(int m, int n, vector<vector<int>>& positions) {
        int grid[m][n];
        memset(grid, 0, sizeof(grid));
        UnionFind uf(m * n);
        int dirs[5] = {-1, 0, 1, 0, -1};
        int cnt = 0;
        vector<int> ans;
        for (auto& p : positions) {
            int i = p[0], j = p[1];
            if (grid[i][j]) {
                ans.push_back(cnt);
                continue;
            }
            grid[i][j] = 1;
            ++cnt;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] && uf.unite(i * n + j, x * n + y)) {
                    --cnt;
                }
            }
            ans.push_back(cnt);
        }
        return ans;
    }
};
```

```go
type unionFind struct {
	p, size []int
}

func newUnionFind(n int) *unionFind {
	p := make([]int, n)
	size := make([]int, n)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	return &unionFind{p, size}
}

func (uf *unionFind) find(x int) int {
	if uf.p[x] != x {
		uf.p[x] = uf.find(uf.p[x])
	}
	return uf.p[x]
}

func (uf *unionFind) union(a, b int) bool {
	pa, pb := uf.find(a), uf.find(b)
	if pa == pb {
		return false
	}
	if uf.size[pa] > uf.size[pb] {
		uf.p[pb] = pa
		uf.size[pa] += uf.size[pb]
	} else {
		uf.p[pa] = pb
		uf.size[pb] += uf.size[pa]
	}
	return true
}

func numIslands2(m int, n int, positions [][]int) (ans []int) {
	uf := newUnionFind(m * n)
	grid := make([][]int, m)
	for i := range grid {
		grid[i] = make([]int, n)
	}
	dirs := [5]int{-1, 0, 1, 0, -1}
	cnt := 0
	for _, p := range positions {
		i, j := p[0], p[1]
		if grid[i][j] == 1 {
			ans = append(ans, cnt)
			continue
		}
		grid[i][j] = 1
		cnt++
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 && uf.union(i*n+j, x*n+y) {
				cnt--
			}
		}
		ans = append(ans, cnt)
	}
	return
}
```

```ts
class UnionFind {
    p: number[];
    size: number[];
    constructor(n: number) {
        this.p = Array(n)
            .fill(0)
            .map((_, i) => i);
        this.size = Array(n).fill(1);
    }

    find(x: number): number {
        if (this.p[x] !== x) {
            this.p[x] = this.find(this.p[x]);
        }
        return this.p[x];
    }

    union(a: number, b: number): boolean {
        const [pa, pb] = [this.find(a), this.find(b)];
        if (pa === pb) {
            return false;
        }
        if (this.size[pa] > this.size[pb]) {
            this.p[pb] = pa;
            this.size[pa] += this.size[pb];
        } else {
            this.p[pa] = pb;
            this.size[pb] += this.size[pa];
        }
        return true;
    }
}

function numIslands2(m: number, n: number, positions: number[][]): number[] {
    const grid: number[][] = Array.from({ length: m }, () => Array(n).fill(0));
    const uf = new UnionFind(m * n);
    const ans: number[] = [];
    const dirs: number[] = [-1, 0, 1, 0, -1];
    let cnt = 0;
    for (const [i, j] of positions) {
        if (grid[i][j]) {
            ans.push(cnt);
            continue;
        }
        grid[i][j] = 1;
        ++cnt;
        for (let k = 0; k < 4; ++k) {
            const [x, y] = [i + dirs[k], j + dirs[k + 1]];
            if (x < 0 || x >= m || y < 0 || y >= n || !grid[x][y]) {
                continue;
            }
            if (uf.union(i * n + j, x * n + y)) {
                --cnt;
            }
        }
        ans.push(cnt);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
