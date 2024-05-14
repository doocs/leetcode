# [1627. 带阈值的图连通性](https://leetcode.cn/problems/graph-connectivity-with-threshold)

[English Version](/solution/1600-1699/1627.Graph%20Connectivity%20With%20Threshold/README_EN.md)

<!-- tags:并查集,数组,数学,数论 -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>有 <code>n</code> 座城市，编号从 <code>1</code> 到 <code>n</code> 。编号为 <code>x</code> 和 <code>y</code> 的两座城市直接连通的前提是： <code>x</code> 和 <code>y</code> 的公因数中，至少有一个 <strong>严格大于</strong> 某个阈值 <code>threshold</code> 。更正式地说，如果存在整数 <code>z</code> ，且满足以下所有条件，则编号 <code>x</code> 和 <code>y</code> 的城市之间有一条道路：</p>

<ul>
	<li><code>x % z == 0</code></li>
	<li><code>y % z == 0</code></li>
	<li><code>z > threshold</code></li>
</ul>

<p>给你两个整数 <code>n</code> 和 <code>threshold</code> ，以及一个待查询数组，请你判断每个查询<code> queries[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> 指向的城市 <code>a<sub>i</sub></code> 和 <code>b<sub>i</sub></code> 是否连通（即，它们之间是否存在一条路径）。</p>

<p>返回数组 <code>answer</code> ，其中<code>answer.length == queries.length</code> 。如果第 <code>i</code> 个查询中指向的城市 <code>a<sub>i</sub></code> 和 <code>b<sub>i</sub></code> 连通，则 <code>answer[i]</code> 为 <code>true</code> ；如果不连通，则 <code>answer[i]</code> 为 <code>false</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1627.Graph%20Connectivity%20With%20Threshold/images/ex1.jpg" style="width: 382px; height: 181px;" /></p>

<p> </p>

<pre>
<strong>输入：</strong>n = 6, threshold = 2, queries = [[1,4],[2,5],[3,6]]
<strong>输出：</strong>[false,false,true]
<strong>解释：</strong>每个数的因数如下：
1:   1
2:   1, 2
3:   1, <strong>3</strong>
4:   1, 2, <strong>4</strong>
5:   1, <strong>5</strong>
6:   1, 2, <strong>3</strong>, <strong>6</strong>
所有大于阈值的的因数已经加粗标识，只有城市 3 和 6 共享公约数 3 ，因此结果是： 
[1,4]   1 与 4 不连通
[2,5]   2 与 5 不连通
[3,6]   3 与 6 连通，存在路径 3--6
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1627.Graph%20Connectivity%20With%20Threshold/images/tmp.jpg" style="width: 532px; height: 302px;" /></p>

<p> </p>

<pre>
<strong>输入：</strong>n = 6, threshold = 0, queries = [[4,5],[3,4],[3,2],[2,6],[1,3]]
<strong>输出：</strong>[true,true,true,true,true]
<strong>解释：</strong>每个数的因数与上一个例子相同。但是，由于阈值为 0 ，所有的因数都大于阈值。因为所有的数字共享公因数 1 ，所以所有的城市都互相连通。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1627.Graph%20Connectivity%20With%20Threshold/images/ex3.jpg" style="width: 282px; height: 282px;" /></p>

<p> </p>

<pre>
<strong>输入：</strong>n = 5, threshold = 1, queries = [[4,5],[4,5],[3,2],[2,3],[3,4]]
<strong>输出：</strong>[false,false,false,false,false]
<strong>解释：</strong>只有城市 2 和 4 共享的公约数 2 严格大于阈值 1 ，所以只有这两座城市是连通的。
注意，同一对节点 [x, y] 可以有多个查询，并且查询 [x，y] 等同于查询 [y，x] 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= n <= 10<sup>4</sup></code></li>
	<li><code>0 <= threshold <= n</code></li>
	<li><code>1 <= queries.length <= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>1 <= a<sub>i</sub>, b<sub>i</sub> <= cities</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
</ul>

## 解法

### 方法一：并查集

我们可以枚举 $z$ 以及 $z$ 的倍数，用并查集将它们连通起来。这样，对于每个查询 $[a, b]$，我们只需要判断 $a$ 和 $b$ 是否在同一个连通块中即可。

时间复杂度 $O(n \times \log n \time (\alpha(n) + q))$，空间复杂度 $O(n)$。其中 $n$ 和 $q$ 分别是节点数和查询数，而 $\alpha$ 是阿克曼函数的反函数。

<!-- tabs:start -->

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
    def areConnected(
        self, n: int, threshold: int, queries: List[List[int]]
    ) -> List[bool]:
        uf = UnionFind(n + 1)
        for a in range(threshold + 1, n + 1):
            for b in range(a + a, n + 1, a):
                uf.union(a, b)
        return [uf.find(a) == uf.find(b) for a, b in queries]
```

```java
class UnionFind {
    private int[] p;
    private int[] size;

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
    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        UnionFind uf = new UnionFind(n + 1);
        for (int a = threshold + 1; a <= n; ++a) {
            for (int b = a + a; b <= n; b += a) {
                uf.union(a, b);
            }
        }
        List<Boolean> ans = new ArrayList<>();
        for (var q : queries) {
            ans.add(uf.find(q[0]) == uf.find(q[1]));
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
    vector<bool> areConnected(int n, int threshold, vector<vector<int>>& queries) {
        UnionFind uf(n + 1);
        for (int a = threshold + 1; a <= n; ++a) {
            for (int b = a + a; b <= n; b += a) {
                uf.unite(a, b);
            }
        }
        vector<bool> ans;
        for (auto& q : queries) {
            ans.push_back(uf.find(q[0]) == uf.find(q[1]));
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

func areConnected(n int, threshold int, queries [][]int) []bool {
	uf := newUnionFind(n + 1)
	for a := threshold + 1; a <= n; a++ {
		for b := a + a; b <= n; b += a {
			uf.union(a, b)
		}
	}
	ans := make([]bool, len(queries))
	for i, q := range queries {
		ans[i] = uf.find(q[0]) == uf.find(q[1])
	}
	return ans
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

function areConnected(n: number, threshold: number, queries: number[][]): boolean[] {
    const uf = new UnionFind(n + 1);
    for (let a = threshold + 1; a <= n; ++a) {
        for (let b = a * 2; b <= n; b += a) {
            uf.union(a, b);
        }
    }
    return queries.map(([a, b]) => uf.find(a) === uf.find(b));
}
```

<!-- tabs:end -->

<!-- end -->
