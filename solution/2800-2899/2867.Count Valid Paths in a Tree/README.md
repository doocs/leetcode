---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2867.Count%20Valid%20Paths%20in%20a%20Tree/README.md
rating: 2428
source: 第 364 场周赛 Q4
tags:
    - 树
    - 深度优先搜索
    - 数学
    - 动态规划
    - 数论
---

<!-- problem:start -->

# [2867. 统计树中的合法路径数目](https://leetcode.cn/problems/count-valid-paths-in-a-tree)

[English Version](/solution/2800-2899/2867.Count%20Valid%20Paths%20in%20a%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一棵 <code>n</code>&nbsp;个节点的无向树，节点编号为&nbsp;<code>1</code>&nbsp;到&nbsp;<code>n</code>&nbsp;。给你一个整数&nbsp;<code>n</code>&nbsp;和一个长度为 <code>n - 1</code>&nbsp;的二维整数数组&nbsp;<code>edges</code>&nbsp;，其中&nbsp;<code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code>&nbsp;表示节点&nbsp;<code>u<sub>i</sub></code> 和&nbsp;<code>v<sub>i</sub></code>&nbsp;在树中有一条边。</p>

<p>请你返回树中的 <strong>合法路径数目</strong>&nbsp;。</p>

<p>如果在节点 <code>a</code>&nbsp;到节点 <code>b</code>&nbsp;之间 <strong>恰好有一个</strong>&nbsp;节点的编号是质数，那么我们称路径&nbsp;<code>(a, b)</code>&nbsp;是 <strong>合法的</strong>&nbsp;。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>路径&nbsp;<code>(a, b)</code>&nbsp;指的是一条从节点 <code>a</code>&nbsp;开始到节点 <code>b</code>&nbsp;结束的一个节点序列，序列中的节点 <strong>互不相同</strong>&nbsp;，且相邻节点之间在树上有一条边。</li>
	<li>路径&nbsp;<code>(a, b)</code>&nbsp;和路径&nbsp;<code>(b, a)</code>&nbsp;视为 <strong>同一条</strong>&nbsp;路径，且只计入答案 <strong>一次</strong>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2867.Count%20Valid%20Paths%20in%20a%20Tree/images/example1.png" style="width: 440px; height: 357px;" /></p>

<pre>
<b>输入：</b>n = 5, edges = [[1,2],[1,3],[2,4],[2,5]]
<b>输出：</b>4
<b>解释：</b>恰好有一个质数编号的节点路径有：
- (1, 2) 因为路径 1 到 2 只包含一个质数 2 。
- (1, 3) 因为路径 1 到 3 只包含一个质数 3 。
- (1, 4) 因为路径 1 到 4 只包含一个质数 2 。
- (2, 4) 因为路径 2 到 4 只包含一个质数 2 。
只有 4 条合法路径。
</pre>

<p><strong class="example">示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2867.Count%20Valid%20Paths%20in%20a%20Tree/images/example2.png" style="width: 488px; height: 384px;" /></p>

<pre>
<b>输入：</b>n = 6, edges = [[1,2],[1,3],[2,4],[3,5],[3,6]]
<b>输出：</b>6
<b>解释：</b>恰好有一个质数编号的节点路径有：
- (1, 2) 因为路径 1 到 2 只包含一个质数 2 。
- (1, 3) 因为路径 1 到 3 只包含一个质数 3 。
- (1, 4) 因为路径 1 到 4 只包含一个质数 2 。
- (1, 6) 因为路径 1 到 6 只包含一个质数 3 。
- (2, 4) 因为路径 2 到 4 只包含一个质数 2 。
- (3, 6) 因为路径 3 到 6 只包含一个质数 3 。
只有 6 条合法路径。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>1 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n</code></li>
	<li>输入保证&nbsp;<code>edges</code>&nbsp;形成一棵合法的树。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：预处理 + 并查集 + 枚举

我们可以预处理得到 $[1, n]$ 中的所有质数，其中 $prime[i]$ 表示 $i$ 是否为质数。

接下来，我们根据二维整数整数构建图 $g$，其中 $g[i]$ 表示节点 $i$ 的所有邻居节点。如果一条边的两个节点都不是质数，那么我们就将这两个节点合并到同一个连通分量中。

然后，我们在 $[1, n]$ 的范围内枚举所有质数 $i$，考虑包含 $i$ 的所有路径。

由于 $i$ 已经是质数，如果 $i$ 是路径的一个端点，那么我们只需要累计与节点 $i$ 相邻的所有连通分量的大小即可。如果 $i$ 是路径上的某个中间点，那么我们需要累计相邻的任意两个连通分量的大小之积。

时间复杂度 $O(n \times \alpha(n))$，空间复杂度 $O(n)$。其中 $n$ 为节点数，而 $\alpha$ 为阿克曼函数的反函数。

<!-- tabs:start -->

#### Python3

```python
class UnionFind:
    def __init__(self, n):
        self.p = list(range(n))
        self.size = [1] * n

    def find(self, x):
        if self.p[x] != x:
            self.p[x] = self.find(self.p[x])
        return self.p[x]

    def union(self, a, b):
        pa, pb = self.find(a), self.find(b)
        if pa == pb:
            return False
        if self.size[pa] > self.size[pb]:
            self.p[pb] = pa
            self.size[pa] += self.size[pb]
        else:
            self.p[pa] = pb
            self.size[pb] += self.size[pa]
        return True


mx = 10**5 + 10
prime = [True] * (mx + 1)
prime[0] = prime[1] = False
for i in range(2, mx + 1):
    if prime[i]:
        for j in range(i * i, mx + 1, i):
            prime[j] = False


class Solution:
    def countPaths(self, n: int, edges: List[List[int]]) -> int:
        g = [[] for _ in range(n + 1)]
        uf = UnionFind(n + 1)
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
            if prime[u] + prime[v] == 0:
                uf.union(u, v)

        ans = 0
        for i in range(1, n + 1):
            if prime[i]:
                t = 0
                for j in g[i]:
                    if not prime[j]:
                        cnt = uf.size[uf.find(j)]
                        ans += cnt
                        ans += t * cnt
                        t += cnt
        return ans
```

#### Java

```java
class PrimeTable {
    private final boolean[] prime;

    public PrimeTable(int n) {
        prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;
        for (int i = 2; i <= n; ++i) {
            if (prime[i]) {
                for (int j = i + i; j <= n; j += i) {
                    prime[j] = false;
                }
            }
        }
    }

    public boolean isPrime(int x) {
        return prime[x];
    }
}

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

    public int size(int x) {
        return size[find(x)];
    }
}

class Solution {
    private static final PrimeTable PT = new PrimeTable(100010);

    public long countPaths(int n, int[][] edges) {
        List<Integer>[] g = new List[n + 1];
        Arrays.setAll(g, i -> new ArrayList<>());
        UnionFind uf = new UnionFind(n + 1);
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
            if (!PT.isPrime(u) && !PT.isPrime(v)) {
                uf.union(u, v);
            }
        }
        long ans = 0;
        for (int i = 1; i <= n; ++i) {
            if (PT.isPrime(i)) {
                long t = 0;
                for (int j : g[i]) {
                    if (!PT.isPrime(j)) {
                        long cnt = uf.size(j);
                        ans += cnt;
                        ans += cnt * t;
                        t += cnt;
                    }
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
const int mx = 1e5 + 10;
bool prime[mx + 1];
int init = []() {
    for (int i = 2; i <= mx; ++i) prime[i] = true;
    for (int i = 2; i <= mx; ++i) {
        if (prime[i]) {
            for (int j = i + i; j <= mx; j += i) {
                prime[j] = false;
            }
        }
    }
    return 0;
}();

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

    int getSize(int x) {
        return size[find(x)];
    }

private:
    vector<int> p, size;
};

class Solution {
public:
    long long countPaths(int n, vector<vector<int>>& edges) {
        vector<int> g[n + 1];
        UnionFind uf(n + 1);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
            if (!prime[u] && !prime[v]) {
                uf.unite(u, v);
            }
        }
        long long ans = 0;
        for (int i = 1; i <= n; ++i) {
            if (prime[i]) {
                long long t = 0;
                for (int j : g[i]) {
                    if (!prime[j]) {
                        long long cnt = uf.getSize(j);
                        ans += cnt;
                        ans += cnt * t;
                        t += cnt;
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
const mx int = 1e5 + 10

var prime [mx]bool

func init() {
	for i := 2; i < mx; i++ {
		prime[i] = true
	}
	for i := 2; i < mx; i++ {
		if prime[i] {
			for j := i + i; j < mx; j += i {
				prime[j] = false
			}
		}
	}
}

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

func (uf *unionFind) getSize(x int) int {
	return uf.size[uf.find(x)]
}

func countPaths(n int, edges [][]int) (ans int64) {
	uf := newUnionFind(n + 1)
	g := make([][]int, n+1)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
		if !prime[u] && !prime[v] {
			uf.union(u, v)
		}
	}
	for i := 1; i <= n; i++ {
		if prime[i] {
			t := 0
			for _, j := range g[i] {
				if !prime[j] {
					cnt := uf.getSize(j)
					ans += int64(cnt + cnt*t)
					t += cnt
				}
			}
		}
	}
	return
}
```

#### TypeScript

```ts
const mx = 100010;
const prime = Array(mx).fill(true);
prime[0] = prime[1] = false;
for (let i = 2; i <= mx; ++i) {
    if (prime[i]) {
        for (let j = i + i; j <= mx; j += i) {
            prime[j] = false;
        }
    }
}

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

    getSize(x: number): number {
        return this.size[this.find(x)];
    }
}

function countPaths(n: number, edges: number[][]): number {
    const uf = new UnionFind(n + 1);
    const g: number[][] = Array(n + 1)
        .fill(0)
        .map(() => []);
    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
        if (!prime[u] && !prime[v]) {
            uf.union(u, v);
        }
    }
    let ans = 0;
    for (let i = 1; i <= n; ++i) {
        if (prime[i]) {
            let t = 0;
            for (let j of g[i]) {
                if (!prime[j]) {
                    const cnt = uf.getSize(j);
                    ans += cnt + t * cnt;
                    t += cnt;
                }
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPaths(self, n: int, edges: List[List[int]]) -> int:
        def mul(x, y):
            return x * y

        def dfs(x, f, con, prime, r):
            v = [1 - prime[x], prime[x]]
            for y in con[x]:
                if y == f:
                    continue
                p = dfs(y, x, con, prime, r)
                r[0] += mul(p[0], v[1]) + mul(p[1], v[0])
                if prime[x]:
                    v[1] += p[0]
                else:
                    v[0] += p[0]
                    v[1] += p[1]
            return v

        prime = [True] * (n + 1)
        prime[1] = False

        all_primes = []
        for i in range(2, n + 1):
            if prime[i]:
                all_primes.append(i)
            for x in all_primes:
                temp = i * x
                if temp > n:
                    break
                prime[temp] = False
                if i % x == 0:
                    break

        con = [[] for _ in range(n + 1)]
        for e in edges:
            con[e[0]].append(e[1])
            con[e[1]].append(e[0])

        r = [0]
        dfs(1, 0, con, prime, r)
        return r[0]
```

#### Java

```java
class Solution {
    public long countPaths(int n, int[][] edges) {
        List<Boolean> prime = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; ++i) {
            prime.add(true);
        }
        prime.set(1, false);

        List<Integer> all = new ArrayList<>();
        for (int i = 2; i <= n; ++i) {
            if (prime.get(i)) {
                all.add(i);
            }
            for (int x : all) {
                int temp = i * x;
                if (temp > n) {
                    break;
                }
                prime.set(temp, false);
                if (i % x == 0) {
                    break;
                }
            }
        }

        List<List<Integer>> con = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; ++i) {
            con.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            con.get(e[0]).add(e[1]);
            con.get(e[1]).add(e[0]);
        }

        long[] r = {0};
        dfs(1, 0, con, prime, r);
        return r[0];
    }

    private long mul(long x, long y) {
        return x * y;
    }

    private class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    private Pair dfs(int x, int f, List<List<Integer>> con, List<Boolean> prime, long[] r) {
        Pair v = new Pair(!prime.get(x) ? 1 : 0, prime.get(x) ? 1 : 0);
        for (int y : con.get(x)) {
            if (y == f) continue;
            Pair p = dfs(y, x, con, prime, r);
            r[0] += mul(p.first, v.second) + mul(p.second, v.first);
            if (prime.get(x)) {
                v.second += p.first;
            } else {
                v.first += p.first;
                v.second += p.second;
            }
        }
        return v;
    }
}
```

#### C++

```cpp
class Solution {
    long long mul(long long x, long long y) {
        return x * y;
    }

    pair<int, int> dfs(int x, int f, const vector<vector<int>>& con, const vector<bool>& prime, long long& r) {
        pair<int, int> v = {!prime[x], prime[x]};
        for (int y : con[x]) {
            if (y == f) continue;
            const auto& p = dfs(y, x, con, prime, r);
            r += mul(p.first, v.second) + mul(p.second, v.first);
            if (prime[x]) {
                v.second += p.first;
            } else {
                v.first += p.first;
                v.second += p.second;
            }
        }
        return v;
    }

public:
    long long countPaths(int n, vector<vector<int>>& edges) {
        vector<bool> prime(n + 1, true);
        prime[1] = false;
        vector<int> all;
        for (int i = 2; i <= n; ++i) {
            if (prime[i]) {
                all.push_back(i);
            }
            for (int x : all) {
                const int temp = i * x;
                if (temp > n) {
                    break;
                }
                prime[temp] = false;
                if (i % x == 0) {
                    break;
                }
            }
        }
        vector<vector<int>> con(n + 1);
        for (const auto& e : edges) {
            con[e[0]].push_back(e[1]);
            con[e[1]].push_back(e[0]);
        }
        long long r = 0;
        dfs(1, 0, con, prime, r);
        return r;
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
