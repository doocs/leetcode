---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0947.Most%20Stones%20Removed%20with%20Same%20Row%20or%20Column/README.md
tags:
    - 深度优先搜索
    - 并查集
    - 图
    - 哈希表
---

<!-- problem:start -->

# [947. 移除最多的同行或同列石头](https://leetcode.cn/problems/most-stones-removed-with-same-row-or-column)

[English Version](/solution/0900-0999/0947.Most%20Stones%20Removed%20with%20Same%20Row%20or%20Column/README_EN.md)

## 题目描述

<!-- description:start -->

<p><code>n</code> 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。</p>

<p>如果一块石头的 <strong>同行或者同列</strong> 上有其他石头存在，那么就可以移除这块石头。</p>

<p>给你一个长度为 <code>n</code> 的数组 <code>stones</code> ，其中 <code>stones[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> 表示第 <code>i</code> 块石头的位置，返回 <strong>可以移除的石子</strong> 的最大数量。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
<strong>输出：</strong>5
<strong>解释：</strong>一种移除 5 块石头的方法如下所示：
1. 移除石头 [2,2] ，因为它和 [2,1] 同行。
2. 移除石头 [2,1] ，因为它和 [0,1] 同列。
3. 移除石头 [1,2] ，因为它和 [1,0] 同行。
4. 移除石头 [1,0] ，因为它和 [0,0] 同列。
5. 移除石头 [0,1] ，因为它和 [0,0] 同行。
石头 [0,0] 不能移除，因为它没有与另一块石头同行/列。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
<strong>输出：</strong>3
<strong>解释：</strong>一种移除 3 块石头的方法如下所示：
1. 移除石头 [2,2] ，因为它和 [2,0] 同行。
2. 移除石头 [2,0] ，因为它和 [0,0] 同列。
3. 移除石头 [0,2] ，因为它和 [0,0] 同行。
石头 [0,0] 和 [1,1] 不能移除，因为它们没有与另一块石头同行/列。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>stones = [[0,0]]
<strong>输出：</strong>0
<strong>解释：</strong>[0,0] 是平面上唯一一块石头，所以不可以移除它。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= stones.length <= 1000</code></li>
	<li><code>0 <= x<sub>i</sub>, y<sub>i</sub> <= 10<sup>4</sup></code></li>
	<li>不会有两块石头放在同一个坐标点上</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：并查集

我们可以用并查集维护石头之间的关系。如果两块石头在同一行或同一列，我们就认为它们之间有关系，可以通过并查集将它们连接起来。最后，我们统计并查集中有多少个连通分量，这个数值就是可以剩余的石头的数量，那么总共可以移除的石头数量就是石头总数减去剩余的石头数量。我们也可以在合并的时候，记录成功合并的次数，这个次数就是可以移除的石头的数量。

时间复杂度 $O(n^2 \times \alpha(n))$，空间复杂度 $O(n)$。其中 $n$ 为石头的数量。

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


class Solution:
    def removeStones(self, stones: List[List[int]]) -> int:
        uf = UnionFind(len(stones))
        ans = 0
        for i, (x1, y1) in enumerate(stones):
            for j, (x2, y2) in enumerate(stones[:i]):
                if x1 == x2 or y1 == y2:
                    ans += uf.union(i, j)
        return ans
```

#### Java

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
    public int removeStones(int[][] stones) {
        int n = stones.length;
        UnionFind uf = new UnionFind(n);
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    ans += uf.union(i, j) ? 1 : 0;
                }
            }
        }
        return ans;
    }
}
```

#### C++

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
    int removeStones(vector<vector<int>>& stones) {
        int n = stones.size();
        UnionFind uf(n);
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    ans += uf.unite(i, j);
                }
            }
        }
        return ans;
    }
};
```

#### Go

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

func removeStones(stones [][]int) (ans int) {
	n := len(stones)
	uf := newUnionFind(n)
	for i, s1 := range stones {
		for j, s2 := range stones[:i] {
			if s1[0] == s2[0] || s1[1] == s2[1] {
				if uf.union(i, j) {
					ans++
				}
			}
		}
	}
	return
}
```

#### TypeScript

```ts
class UnionFind {
    p: number[];
    size: number[];
    constructor(n: number) {
        this.p = Array.from({ length: n }, (_, i) => i);
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

function removeStones(stones: number[][]): number {
    const n = stones.length;
    const uf = new UnionFind(n);
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            if (stones[i][0] === stones[j][0] || stones[i][1] === stones[j][1]) {
                ans += uf.union(i, j) ? 1 : 0;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：并查集（优化）

我们可以将石头的纵坐标加上一个偏移量，这样就可以将横坐标和纵坐标统一起来，然后用并查集维护横坐标和纵坐标之间的关系。

我们遍历每一块石头，将横坐标与纵坐标进行合并。

最后，我们再遍历所有石头，将每块石头的横坐标的根节点放到一个集合中，那么这个集合的数量就是可以剩余的石头的数量，总共可以移除的石头数量就是石头总数减去剩余的石头数量。

时间复杂度 $O(n \times \alpha(m))$，空间复杂度 $O(m)$。其中 $n$ 和 $m$ 分别为石头的数量和横纵坐标的最大值。

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


class Solution:
    def removeStones(self, stones: List[List[int]]) -> int:
        m = 10001
        uf = UnionFind(m << 1)
        for x, y in stones:
            uf.union(x, y + m)
        return len(stones) - len({uf.find(x) for x, _ in stones})
```

#### Java

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
    public int removeStones(int[][] stones) {
        int m = 10001;
        UnionFind uf = new UnionFind(m << 1);
        for (var st : stones) {
            uf.union(st[0], st[1] + m);
        }
        Set<Integer> s = new HashSet<>();
        for (var st : stones) {
            s.add(uf.find(st[0]));
        }
        return stones.length - s.size();
    }
}
```

#### C++

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
    int removeStones(vector<vector<int>>& stones) {
        int m = 10001;
        UnionFind uf(m << 1);
        for (auto& st : stones) {
            uf.unite(st[0], st[1] + m);
        }
        unordered_set<int> s;
        for (auto& st : stones) {
            s.insert(uf.find(st[0]));
        }
        return stones.size() - s.size();
    }
};
```

#### Go

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

func removeStones(stones [][]int) (ans int) {
	m := 10001
	uf := newUnionFind(m << 1)
	for _, st := range stones {
		uf.union(st[0], st[1]+m)
	}
	s := map[int]bool{}
	for _, st := range stones {
		s[uf.find(st[0])] = true
	}
	return len(stones) - len(s)
}
```

#### TypeScript

```ts
class UnionFind {
    p: number[];
    size: number[];
    constructor(n: number) {
        this.p = Array.from({ length: n }, (_, i) => i);
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

function removeStones(stones: number[][]): number {
    const m = 10001;
    const uf = new UnionFind(m << 1);
    for (const [x, y] of stones) {
        uf.union(x, y + m);
    }
    const s = new Set<number>();
    for (const [x, _] of stones) {
        s.add(uf.find(x));
    }
    return stones.length - s.size;
}
```

<!-- tabs:end -->

<!--- solution:end --->

<!-- solution:start -->

### Solution 3: DFS

<!-- tabs:start -->

#### TypeScript

```ts
function removeStones(stones: number[][]): number {
    const n = stones.length;
    const g: number[][] = Array.from({ length: n }, () => []);

    for (let i = 0; i < n; i++) {
        const [y, x] = stones[i];
        for (let j = i + 1; j < n; j++) {
            if (y === stones[j][0] || x === stones[j][1]) {
                g[i].push(j);
                g[j].push(i);
            }
        }
    }

    const dfs = (i: number) => {
        const seen = new Set<number>();

        let q = [i];
        while (q.length) {
            const qNext: number[] = [];

            for (const i of q) {
                if (seen.has(i)) continue;
                seen.add(i);
                set.delete(i);
                qNext.push(...g[i]);
            }

            q = qNext;
        }
    };

    const set = new Set(Array.from({ length: n }, (_, i) => i));
    let ans = n;
    for (const i of set) {
        dfs(i);
        ans--;
    }

    return ans;
}
```

#### JavaScript

```js
function removeStones(stones) {
    const n = stones.length;
    const g = Array.from({ length: n }, () => []);

    for (let i = 0; i < n; i++) {
        const [y, x] = stones[i];
        for (let j = i + 1; j < n; j++) {
            if (y === stones[j][0] || x === stones[j][1]) {
                g[i].push(j);
                g[j].push(i);
            }
        }
    }

    const dfs = i => {
        const seen = new Set();

        let q = [i];
        while (q.length) {
            const qNext = [];

            for (const i of q) {
                if (seen.has(i)) continue;
                seen.add(i);
                set.delete(i);
                qNext.push(...g[i]);
            }

            q = qNext;
        }
    };

    const set = new Set(Array.from({ length: n }, (_, i) => i));
    let ans = n;
    for (const i of set) {
        dfs(i);
        ans--;
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
