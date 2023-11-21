# [1724. 检查边长度限制的路径是否存在 II](https://leetcode.cn/problems/checking-existence-of-edge-length-limited-paths-ii)

[English Version](/solution/1700-1799/1724.Checking%20Existence%20of%20Edge%20Length%20Limited%20Paths%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一张有&nbsp;<code>n</code>&nbsp;个节点的无向图以边的列表&nbsp;<code>edgeList</code>&nbsp;的形式定义，其中&nbsp;<code>edgeList[i] = [u<sub>i</sub>, v<sub>i</sub>, dis<sub>i</sub>]</code>&nbsp;表示一条连接&nbsp;<code>u<sub>i</sub></code>&nbsp;和&nbsp;<code>v<sub>i</sub></code>&nbsp;，距离为&nbsp;<code>dis<sub>i</sub></code>&nbsp;的边。注意，同一对节点间可能有<strong>多条</strong>边，且该图可能不是连通的。</p>

<p>实现&nbsp;<code>DistanceLimitedPathsExist</code>&nbsp;类：</p>

<ul>
	<li><code>DistanceLimitedPathsExist(int n, int[][] edgeList)</code>&nbsp;以给定的无向图初始化对象。</li>
	<li><code>boolean query(int p, int q, int limit)</code>&nbsp;当存在一条从&nbsp;<code>p</code>&nbsp;到 <code>q</code> 的路径，且路径中每条边的距离都<strong>严格小于</strong> <code>limit</code> 时，返回 <code>true</code> ，否则返回 <code>false</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><b>示例 1:</b></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1724.Checking%20Existence%20of%20Edge%20Length%20Limited%20Paths%20II/images/1693449815-oSOAxI-%E6%88%AA%E5%B1%8F2023-08-31%2010.43.30.png){:width=400}" style="width: 400px;" /><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1724.Checking%20Existence%20of%20Edge%20Length%20Limited%20Paths%20II/images/1693449815-oSOAxI-%E6%88%AA%E5%B1%8F2023-08-31%2010.43.30.png" style="width: 400px; height: 352px;" /></p>

<pre>
<b>输入：</b>
["DistanceLimitedPathsExist", "query", "query", "query", "query"]
[[6, [[0, 2, 4], [0, 3, 2], [1, 2, 3], [2, 3, 1], [4, 5, 5]]], [2, 3, 2], [1, 3, 3], [2, 0, 3], [0, 5, 6]]
<b>输出：</b>
[null, true, false, true, false]

<b>解释：</b>
DistanceLimitedPathsExist distanceLimitedPathsExist = new DistanceLimitedPathsExist(6, [[0, 2, 4], [0, 3, 2], [1, 2, 3], [2, 3, 1], [4, 5, 5]]);
distanceLimitedPathsExist.query(2, 3, 2); // 返回 true。存在一条从 2 到 3 ，距离为 1 的边，
&nbsp;                                         // 这条边的距离小于 2。
distanceLimitedPathsExist.query(1, 3, 3); // 返回 false。从 1 到 3 之间不存在每条边的距离都
                                          // <strong>严格</strong>小于 3 的路径。
distanceLimitedPathsExist.query(2, 0, 3); // 返回 true。存在一条从 2 到 0 的路径，使得每条边的
                                          // 距离 &lt; 3：从 2 到 3 到 0 行进即可。
distanceLimitedPathsExist.query(0, 5, 6); // 返回 false。从 0 到 5 之间不存在路径。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= edgeList.length &lt;= 10<sup>4</sup></code></li>
	<li><code>edgeList[i].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub>, p, q &lt;= n-1</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>p != q</code></li>
	<li><code>1 &lt;= dis<sub>i</sub>, limit &lt;= 10<sup>9</sup></code></li>
	<li>最多调用&nbsp;<code>10<sup>4</sup></code>&nbsp;次&nbsp;<code>query</code>&nbsp;。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：可持久化并查集**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class PersistentUnionFind:
    def __init__(self, n):
        self.rank = [0] * n
        self.p = list(range(n))
        self.version = [inf] * n

    def find(self, x, t=inf):
        if self.p[x] == x or self.version[x] >= t:
            return x
        return self.find(self.p[x], t)

    def union(self, a, b, t):
        pa, pb = self.find(a), self.find(b)
        if pa == pb:
            return False
        if self.rank[pa] > self.rank[pb]:
            self.version[pb] = t
            self.p[pb] = pa
        else:
            self.version[pa] = t
            self.p[pa] = pb
            if self.rank[pa] == self.rank[pb]:
                self.rank[pb] += 1
        return True


class DistanceLimitedPathsExist:
    def __init__(self, n: int, edgeList: List[List[int]]):
        self.puf = PersistentUnionFind(n)
        edgeList.sort(key=lambda x: x[2])
        for u, v, dis in edgeList:
            self.puf.union(u, v, dis)

    def query(self, p: int, q: int, limit: int) -> bool:
        return self.puf.find(p, limit) == self.puf.find(q, limit)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class PersistentUnionFind {
    private final int inf = 1 << 30;
    private int[] rank;
    private int[] parent;
    private int[] version;

    public PersistentUnionFind(int n) {
        rank = new int[n];
        parent = new int[n];
        version = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            version[i] = inf;
        }
    }

    public int find(int x, int t) {
        if (parent[x] == x || version[x] >= t) {
            return x;
        }
        return find(parent[x], t);
    }

    public boolean union(int a, int b, int t) {
        int pa = find(a, inf);
        int pb = find(b, inf);
        if (pa == pb) {
            return false;
        }
        if (rank[pa] > rank[pb]) {
            version[pb] = t;
            parent[pb] = pa;
        } else {
            version[pa] = t;
            parent[pa] = pb;
            if (rank[pa] == rank[pb]) {
                rank[pb]++;
            }
        }
        return true;
    }
}

public class DistanceLimitedPathsExist {
    private PersistentUnionFind puf;

    public DistanceLimitedPathsExist(int n, int[][] edgeList) {
        puf = new PersistentUnionFind(n);
        Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
        for (var e : edgeList) {
            puf.union(e[0], e[1], e[2]);
        }
    }

    public boolean query(int p, int q, int limit) {
        return puf.find(p, limit) == puf.find(q, limit);
    }
}

/**
 * Your DistanceLimitedPathsExist object will be instantiated and called as such:
 * DistanceLimitedPathsExist obj = new DistanceLimitedPathsExist(n, edgeList);
 * boolean param_1 = obj.query(p,q,limit);
 */
```

### **C++**

```cpp
class PersistentUnionFind {
private:
    vector<int> rank;
    vector<int> parent;
    vector<int> version;

public:
    PersistentUnionFind(int n)
        : rank(n, 0)
        , parent(n)
        , version(n, INT_MAX) {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int find(int x, int t) {
        if (parent[x] == x || version[x] >= t) {
            return x;
        }
        return find(parent[x], t);
    }

    bool unionSet(int a, int b, int t) {
        int pa = find(a, INT_MAX);
        int pb = find(b, INT_MAX);
        if (pa == pb) {
            return false;
        }
        if (rank[pa] > rank[pb]) {
            version[pb] = t;
            parent[pb] = pa;
        } else {
            version[pa] = t;
            parent[pa] = pb;
            if (rank[pa] == rank[pb]) {
                rank[pb]++;
            }
        }
        return true;
    }
};

class DistanceLimitedPathsExist {
private:
    PersistentUnionFind puf;

public:
    DistanceLimitedPathsExist(int n, vector<vector<int>>& edgeList)
        : puf(n) {
        sort(edgeList.begin(), edgeList.end(),
            [](const vector<int>& a, const vector<int>& b) {
                return a[2] < b[2];
            });

        for (const auto& edge : edgeList) {
            puf.unionSet(edge[0], edge[1], edge[2]);
        }
    }

    bool query(int p, int q, int limit) {
        return puf.find(p, limit) == puf.find(q, limit);
    }
};

/**
 * Your DistanceLimitedPathsExist object will be instantiated and called as such:
 * DistanceLimitedPathsExist* obj = new DistanceLimitedPathsExist(n, edgeList);
 * bool param_1 = obj->query(p,q,limit);
 */
```

### **Go**

```go
type PersistentUnionFind struct {
	rank    []int
	parent  []int
	version []int
}

func NewPersistentUnionFind(n int) *PersistentUnionFind {
	rank := make([]int, n)
	parent := make([]int, n)
	version := make([]int, n)

	for i := 0; i < n; i++ {
		parent[i] = i
	}

	return &PersistentUnionFind{
		rank:    rank,
		parent:  parent,
		version: version,
	}
}

func (uf *PersistentUnionFind) find(x int, t int) int {
	if uf.parent[x] == x || uf.version[x] >= t {
		return x
	}
	return uf.find(uf.parent[x], t)
}

func (uf *PersistentUnionFind) union(a, b, t int) bool {
	pa := uf.find(a, int(^uint(0)>>1))
	pb := uf.find(b, int(^uint(0)>>1))

	if pa == pb {
		return false
	}

	if uf.rank[pa] > uf.rank[pb] {
		uf.version[pb] = t
		uf.parent[pb] = pa
	} else {
		uf.version[pa] = t
		uf.parent[pa] = pb
		if uf.rank[pa] == uf.rank[pb] {
			uf.rank[pb]++
		}
	}

	return true
}

type DistanceLimitedPathsExist struct {
	puf *PersistentUnionFind
}

func Constructor(n int, edgeList [][]int) DistanceLimitedPathsExist {
	sort.Slice(edgeList, func(i, j int) bool {
		return edgeList[i][2] < edgeList[j][2]
	})

	puf := NewPersistentUnionFind(n)

	for _, edge := range edgeList {
		puf.union(edge[0], edge[1], edge[2])
	}

	return DistanceLimitedPathsExist{
		puf: puf,
	}
}

func (dle *DistanceLimitedPathsExist) Query(p, q, limit int) bool {
	return dle.puf.find(p, limit) == dle.puf.find(q, limit)
}

/**
 * Your DistanceLimitedPathsExist object will be instantiated and called as such:
 * obj := Constructor(n, edgeList);
 * param_1 := obj.Query(p,q,limit);
 */
```

### **TypeScript**

```ts
class PersistentUnionFind {
    private rank: number[];
    private parent: number[];
    private version: number[];

    constructor(n: number) {
        this.rank = Array(n).fill(0);
        this.parent = Array.from({ length: n }, (_, i) => i);
        this.version = Array(n).fill(Infinity);
    }

    find(x: number, t: number): number {
        if (this.parent[x] === x || this.version[x] >= t) {
            return x;
        }
        return this.find(this.parent[x], t);
    }

    union(a: number, b: number, t: number): boolean {
        const pa = this.find(a, Infinity);
        const pb = this.find(b, Infinity);

        if (pa === pb) {
            return false;
        }

        if (this.rank[pa] > this.rank[pb]) {
            this.version[pb] = t;
            this.parent[pb] = pa;
        } else {
            this.version[pa] = t;
            this.parent[pa] = pb;
            if (this.rank[pa] === this.rank[pb]) {
                this.rank[pb]++;
            }
        }

        return true;
    }
}

class DistanceLimitedPathsExist {
    private puf: PersistentUnionFind;

    constructor(n: number, edgeList: number[][]) {
        this.puf = new PersistentUnionFind(n);
        edgeList.sort((a, b) => a[2] - b[2]);
        for (const [u, v, dis] of edgeList) {
            this.puf.union(u, v, dis);
        }
    }

    query(p: number, q: number, limit: number): boolean {
        return this.puf.find(p, limit) === this.puf.find(q, limit);
    }
}

/**
 * Your DistanceLimitedPathsExist object will be instantiated and called as such:
 * var obj = new DistanceLimitedPathsExist(n, edgeList)
 * var param_1 = obj.query(p,q,limit)
 */

/**
 * Your DistanceLimitedPathsExist object will be instantiated and called as such:
 * var obj = new DistanceLimitedPathsExist(n, edgeList)
 * var param_1 = obj.query(p,q,limit)
 */
```

### **...**

```

```

<!-- tabs:end -->
