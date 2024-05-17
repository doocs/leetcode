---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0547.Number%20of%20Provinces/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 并查集
    - 图
---

<!-- problem:start -->

# [547. 省份数量](https://leetcode.cn/problems/number-of-provinces)

[English Version](/solution/0500-0599/0547.Number%20of%20Provinces/README_EN.md)

## 题目描述

<!-- description:start -->

<div class="original__bRMd">
<div>
<p>有 <code>n</code> 个城市，其中一些彼此相连，另一些没有相连。如果城市 <code>a</code> 与城市 <code>b</code> 直接相连，且城市 <code>b</code> 与城市 <code>c</code> 直接相连，那么城市 <code>a</code> 与城市 <code>c</code> 间接相连。</p>

<p><strong>省份</strong> 是一组直接或间接相连的城市，组内不含其他没有相连的城市。</p>

<p>给你一个 <code>n x n</code> 的矩阵 <code>isConnected</code> ，其中 <code>isConnected[i][j] = 1</code> 表示第 <code>i</code> 个城市和第 <code>j</code> 个城市直接相连，而 <code>isConnected[i][j] = 0</code> 表示二者不直接相连。</p>

<p>返回矩阵中 <strong>省份</strong> 的数量。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0547.Number%20of%20Provinces/images/graph1.jpg" style="width: 222px; height: 142px;" />
<pre>
<strong>输入：</strong>isConnected = [[1,1,0],[1,1,0],[0,0,1]]
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0547.Number%20of%20Provinces/images/graph2.jpg" style="width: 222px; height: 142px;" />
<pre>
<strong>输入：</strong>isConnected = [[1,0,0],[0,1,0],[0,0,1]]
<strong>输出：</strong>3
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 200</code></li>
	<li><code>n == isConnected.length</code></li>
	<li><code>n == isConnected[i].length</code></li>
	<li><code>isConnected[i][j]</code> 为 <code>1</code> 或 <code>0</code></li>
	<li><code>isConnected[i][i] == 1</code></li>
	<li><code>isConnected[i][j] == isConnected[j][i]</code></li>
</ul>
</div>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们创建一个数组 $vis$，用于记录每个城市是否被访问过。

接下来，遍历每个城市 $i$，如果该城市未被访问过，则从该城市开始深度优先搜索，通过矩阵 $isConnected$ 得到与该城市直接相连的城市有哪些，这些城市和该城市属于同一个省，然后对这些城市继续深度优先搜索，直到同一个省的所有城市都被访问到，即可得到一个省，将答案 $ans$ 加 $1$，然后遍历下一个未被访问过的城市，直到遍历完所有的城市。

最后返回答案即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是城市的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findCircleNum(self, isConnected: List[List[int]]) -> int:
        def dfs(i: int):
            vis[i] = True
            for j, x in enumerate(isConnected[i]):
                if not vis[j] and x:
                    dfs(j)

        n = len(isConnected)
        vis = [False] * n
        ans = 0
        for i in range(n):
            if not vis[i]:
                dfs(i)
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    private int[][] g;
    private boolean[] vis;

    public int findCircleNum(int[][] isConnected) {
        g = isConnected;
        int n = g.length;
        vis = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                dfs(i);
                ++ans;
            }
        }
        return ans;
    }

    private void dfs(int i) {
        vis[i] = true;
        for (int j = 0; j < g.length; ++j) {
            if (!vis[j] && g[i][j] == 1) {
                dfs(j);
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findCircleNum(vector<vector<int>>& isConnected) {
        int n = isConnected.size();
        int ans = 0;
        bool vis[n];
        memset(vis, false, sizeof(vis));
        function<void(int)> dfs = [&](int i) {
            vis[i] = true;
            for (int j = 0; j < n; ++j) {
                if (!vis[j] && isConnected[i][j]) {
                    dfs(j);
                }
            }
        };
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                dfs(i);
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findCircleNum(isConnected [][]int) (ans int) {
	n := len(isConnected)
	vis := make([]bool, n)
	var dfs func(int)
	dfs = func(i int) {
		vis[i] = true
		for j, x := range isConnected[i] {
			if !vis[j] && x == 1 {
				dfs(j)
			}
		}
	}
	for i, v := range vis {
		if !v {
			ans++
			dfs(i)
		}
	}
	return
}
```

#### TypeScript

```ts
function findCircleNum(isConnected: number[][]): number {
    const n = isConnected.length;
    const vis: boolean[] = new Array(n).fill(false);
    const dfs = (i: number) => {
        vis[i] = true;
        for (let j = 0; j < n; ++j) {
            if (!vis[j] && isConnected[i][j]) {
                dfs(j);
            }
        }
    };
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        if (!vis[i]) {
            dfs(i);
            ++ans;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    fn dfs(is_connected: &mut Vec<Vec<i32>>, vis: &mut Vec<bool>, i: usize) {
        vis[i] = true;
        for j in 0..is_connected.len() {
            if vis[j] || is_connected[i][j] == 0 {
                continue;
            }
            Self::dfs(is_connected, vis, j);
        }
    }

    pub fn find_circle_num(mut is_connected: Vec<Vec<i32>>) -> i32 {
        let n = is_connected.len();
        let mut vis = vec![false; n];
        let mut res = 0;
        for i in 0..n {
            if vis[i] {
                continue;
            }
            res += 1;
            Self::dfs(&mut is_connected, &mut vis, i);
        }
        res
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：并查集

我们也可以用并查集维护每个连通分量，初始时，每个城市都属于不同的连通分量，所以省份数量为 $n$。

接下来，遍历矩阵 $isConnected$，如果两个城市 $(i, j)$ 之间有相连关系，并且处于两个不同的连通分量，则它们将被合并成为一个连通分量，然后将省份数量减去 $1$。

最后返回省份数量即可。

时间复杂度 $O(n^2 \times \alpha(n))$，空间复杂度 $O(n)$。其中 $n$ 是城市的数量，而 $\alpha$ 是阿克曼函数的反函数，在渐进意义下 $\alpha(n)$ 可以认为是一个很小的常数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findCircleNum(self, isConnected: List[List[int]]) -> int:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        n = len(isConnected)
        p = list(range(n))
        ans = n
        for i in range(n):
            for j in range(i + 1, n):
                if isConnected[i][j]:
                    pa, pb = find(i), find(j)
                    if pa != pb:
                        p[pa] = pb
                        ans -= 1
        return ans
```

#### Java

```java
class Solution {
    private int[] p;

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        int ans = n;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (isConnected[i][j] == 1) {
                    int pa = find(i), pb = find(j);
                    if (pa != pb) {
                        p[pa] = pb;
                        --ans;
                    }
                }
            }
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findCircleNum(vector<vector<int>>& isConnected) {
        int n = isConnected.size();
        int p[n];
        iota(p, p + n, 0);
        function<int(int)> find = [&](int x) -> int {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };
        int ans = n;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (isConnected[i][j]) {
                    int pa = find(i), pb = find(j);
                    if (pa != pb) {
                        p[pa] = pb;
                        --ans;
                    }
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findCircleNum(isConnected [][]int) (ans int) {
	n := len(isConnected)
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	ans = n
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if isConnected[i][j] == 1 {
				pa, pb := find(i), find(j)
				if pa != pb {
					p[pa] = pb
					ans--
				}
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function findCircleNum(isConnected: number[][]): number {
    const n = isConnected.length;
    const p: number[] = new Array(n);
    for (let i = 0; i < n; ++i) {
        p[i] = i;
    }
    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    let ans = n;
    for (let i = 0; i < n; ++i) {
        for (let j = i + 1; j < n; ++j) {
            if (isConnected[i][j]) {
                const pa = find(i);
                const pb = find(j);
                if (pa !== pb) {
                    p[pa] = pb;
                    --ans;
                }
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
