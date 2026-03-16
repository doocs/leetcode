---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3873.Maximum%20Points%20Activated%20with%20One%20Addition/README_EN.md
---

<!-- problem:start -->

# [3873. Maximum Points Activated with One Addition](https://leetcode.com/problems/maximum-points-activated-with-one-addition)

[中文文档](/solution/3800-3899/3873.Maximum%20Points%20Activated%20with%20One%20Addition/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>points</code>, where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> represents the coordinates of the <code>i<sup>th</sup></code> point. All coordinates in <code>points</code> are <strong>distinct</strong>.</p>

<p>If a point is <strong>activated</strong>, then all points that have the <strong>same</strong> x-coordinate <strong>or</strong> y-coordinate become <strong>activated</strong> as well.</p>

<p>Activation continues until no additional points can be activated.</p>

<p>You may add <strong>one additional</strong> point at any integer coordinate <code>(x, y)</code> not already present in <code>points</code>. Activation begins by <strong>activating</strong> this <strong>newly added point</strong>.</p>

<p>Return an integer denoting the <strong>maximum</strong> number of points that can be activated, including the newly added point.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">points = [[1,1],[1,2],[2,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>Adding and activating a point such as <code>(1, 3)</code> causes activations:</p>

<ul>
	<li><code>(1, 3)</code> shares <code>x = 1</code> with <code>(1, 1)</code> and <code>(1, 2)</code> -&gt; <code>(1, 1)</code> and <code>(1, 2)</code> become activated.</li>
	<li><code>(1, 2)</code> shares <code>y = 2</code> with <code>(2, 2)</code> -&gt; <code>(2, 2)</code> becomes activated.</li>
</ul>

<p>Thus, the activated points are <code>(1, 3)</code>, <code>(1, 1)</code>, <code>(1, 2)</code>, <code>(2, 2)</code>, so 4 points in total. We can show this is the maximum activated.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">points = [[2,2],[1,1],[3,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>Adding and activating a point such as <code>(1, 2)</code> causes activations:</p>

<ul>
	<li><code>(1, 2)</code> shares <code>x = 1</code> with <code>(1, 1)</code> -&gt; <code>(1, 1)</code> becomes activated.</li>
	<li><code>(1, 2)</code> shares <code>y = 2</code> with <code>(2, 2)</code> -&gt; <code>(2, 2)</code> becomes activated.</li>
</ul>

<p>Thus, the activated points are <code>(1, 2)</code>, <code>(1, 1)</code>, <code>(2, 2)</code>, so 3 points in total. We can show this is the maximum activated.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">points = [[2,3],[2,2],[1,1],[4,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>Adding and activating a point such as <code>(2, 1)</code> causes activations:</p>

<ul>
	<li><code>(2, 1)</code> shares <code>x = 2</code> with <code>(2, 3)</code> and <code>(2, 2)</code> -&gt; <code>(2, 3)</code> and <code>(2, 2)</code> become activated.</li>
	<li><code>(2, 1)</code> shares <code>y = 1</code> with <code>(1, 1)</code> -&gt; <code>(1, 1)</code> becomes activated.</li>
</ul>

<p>Thus, the activated points are <code>(2, 1)</code>, <code>(2, 3)</code>, <code>(2, 2)</code>, <code>(1, 1)</code>, so 4 points in total.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= points.length &lt;= 10<sup>5</sup></code></li>
	<li><code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code></li>
	<li><code>-10<sup>9</sup> &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>points</code> contains all <strong>distinct</strong> coordinates.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Union-Find

We can use a Union-Find data structure to solve this problem.

First, we map the $x$ coordinates and $y$ coordinates of all points into the same Union-Find structure. Specifically, we add a sufficiently large constant $m$ (e.g., $3 \times 10^9$) to each $y$ coordinate to ensure that the $x$ and $y$ coordinates do not conflict.

Next, we iterate over all points and union those that share the same $x$ coordinate or the same $y$ coordinate. This way, points with the same $x$ or $y$ coordinate will be grouped into the same set.

Finally, we count the number of points in each set and find the sizes of the two largest sets. Since we can add one new point to connect these two sets, the final answer is the sum of the sizes of the two largest sets plus $1$.

The time complexity is $O(n \alpha(n))$, where $n$ is the number of points and $\alpha$ is the inverse Ackermann function. The space complexity is $O(n)$.

<!-- tabs:start -->

#### Python3

```python
class UnionFind:
    def __init__(self):
        self.p = {}
        self.size = {}

    def find(self, x):
        if x not in self.p:
            self.p[x] = x
            self.size[x] = 1
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
    def maxActivated(self, points: List[List[int]]) -> int:
        uf = UnionFind()
        m = int(3e9)

        for x, y in points:
            uf.union(x, y + m)

        cnt = Counter()
        for x, _ in points:
            cnt[uf.find(x)] += 1

        mx1 = mx2 = 0
        for x in cnt.values():
            if mx1 < x:
                mx2 = mx1
                mx1 = x
            elif mx2 < x:
                mx2 = x
        return mx1 + mx2 + 1
```

#### Java

```java
class UnionFind {
    Map<Long, Long> p = new HashMap<>();
    Map<Long, Integer> size = new HashMap<>();

    long find(long x) {
        if (!p.containsKey(x)) {
            p.put(x, x);
            size.put(x, 1);
        }
        if (p.get(x) != x) {
            p.put(x, find(p.get(x)));
        }
        return p.get(x);
    }

    boolean union(long a, long b) {
        long pa = find(a), pb = find(b);
        if (pa == pb) {
            return false;
        }

        int sa = size.get(pa), sb = size.get(pb);
        if (sa > sb) {
            p.put(pb, pa);
            size.put(pa, sa + sb);
        } else {
            p.put(pa, pb);
            size.put(pb, sa + sb);
        }
        return true;
    }
}

class Solution {
    public int maxActivated(int[][] points) {
        UnionFind uf = new UnionFind();
        long m = (long) 3e9;

        for (int[] p : points) {
            uf.union(p[0], p[1] + m);
        }

        Map<Long, Integer> cnt = new HashMap<>();
        for (int[] p : points) {
            cnt.merge(uf.find(p[0]), 1, Integer::sum);
        }

        int mx1 = 0, mx2 = 0;
        for (int x : cnt.values()) {
            if (mx1 < x) {
                mx2 = mx1;
                mx1 = x;
            } else if (mx2 < x) {
                mx2 = x;
            }
        }

        return mx1 + mx2 + 1;
    }
}
```

#### C++

```cpp
class UnionFind {
public:
    unordered_map<long long, long long> p;
    unordered_map<long long, int> sz;

    long long find(long long x) {
        if (!p.count(x)) {
            p[x] = x;
            sz[x] = 1;
        }
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    bool unite(long long a, long long b) {
        long long pa = find(a), pb = find(b);
        if (pa == pb) return false;

        if (sz[pa] > sz[pb]) {
            p[pb] = pa;
            sz[pa] += sz[pb];
        } else {
            p[pa] = pb;
            sz[pb] += sz[pa];
        }
        return true;
    }
};

class Solution {
public:
    int maxActivated(vector<vector<int>>& points) {
        UnionFind uf;
        long long m = (long long) 3e9;

        for (auto& p : points) {
            uf.unite(p[0], p[1] + m);
        }

        unordered_map<long long, int> cnt;
        for (auto& p : points) {
            long long root = uf.find(p[0]);
            cnt[root]++;
        }

        int mx1 = 0, mx2 = 0;
        for (auto& [_, x] : cnt) {
            if (mx1 < x) {
                mx2 = mx1;
                mx1 = x;
            } else if (mx2 < x) {
                mx2 = x;
            }
        }

        return mx1 + mx2 + 1;
    }
};
```

#### Go

```go
type UnionFind struct {
	p    map[int64]int64
	size map[int64]int
}

func NewUnionFind() *UnionFind {
	return &UnionFind{
		p:    map[int64]int64{},
		size: map[int64]int{},
	}
}

func (uf *UnionFind) find(x int64) int64 {
	if _, ok := uf.p[x]; !ok {
		uf.p[x] = x
		uf.size[x] = 1
	}
	if uf.p[x] != x {
		uf.p[x] = uf.find(uf.p[x])
	}
	return uf.p[x]
}

func (uf *UnionFind) union(a, b int64) bool {
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

func maxActivated(points [][]int) int {
	uf := NewUnionFind()
	m := int64(3e9)

	for _, p := range points {
		uf.union(int64(p[0]), int64(p[1])+m)
	}

	cnt := map[int64]int{}
	for _, p := range points {
		root := uf.find(int64(p[0]))
		cnt[root]++
	}

	mx1, mx2 := 0, 0
	for _, x := range cnt {
		if mx1 < x {
			mx2 = mx1
			mx1 = x
		} else if mx2 < x {
			mx2 = x
		}
	}

	return mx1 + mx2 + 1
}
```

#### TypeScript

```ts
class UnionFind {
    p: Map<number, number> = new Map();
    size: Map<number, number> = new Map();

    find(x: number): number {
        if (!this.p.has(x)) {
            this.p.set(x, x);
            this.size.set(x, 1);
        }
        if (this.p.get(x)! !== x) {
            this.p.set(x, this.find(this.p.get(x)!));
        }
        return this.p.get(x)!;
    }

    union(a: number, b: number): boolean {
        const pa = this.find(a);
        const pb = this.find(b);
        if (pa === pb) return false;

        const sa = this.size.get(pa)!;
        const sb = this.size.get(pb)!;

        if (sa > sb) {
            this.p.set(pb, pa);
            this.size.set(pa, sa + sb);
        } else {
            this.p.set(pa, pb);
            this.size.set(pb, sa + sb);
        }
        return true;
    }
}

function maxActivated(points: number[][]): number {
    const uf = new UnionFind();
    const m = 3e9;

    for (const [x, y] of points) {
        uf.union(x, y + m);
    }

    const cnt = new Map<number, number>();
    for (const [x] of points) {
        const root = uf.find(x);
        cnt.set(root, (cnt.get(root) ?? 0) + 1);
    }

    let mx1 = 0,
        mx2 = 0;
    for (const x of cnt.values()) {
        if (mx1 < x) {
            mx2 = mx1;
            mx1 = x;
        } else if (mx2 < x) {
            mx2 = x;
        }
    }

    return mx1 + mx2 + 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
