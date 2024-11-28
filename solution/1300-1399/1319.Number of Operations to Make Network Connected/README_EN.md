---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1319.Number%20of%20Operations%20to%20Make%20Network%20Connected/README_EN.md
rating: 1633
source: Weekly Contest 171 Q3
tags:
    - Depth-First Search
    - Breadth-First Search
    - Union Find
    - Graph
---

<!-- problem:start -->

# [1319. Number of Operations to Make Network Connected](https://leetcode.com/problems/number-of-operations-to-make-network-connected)

[中文文档](/solution/1300-1399/1319.Number%20of%20Operations%20to%20Make%20Network%20Connected/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> computers numbered from <code>0</code> to <code>n - 1</code> connected by ethernet cables <code>connections</code> forming a network where <code>connections[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> represents a connection between computers <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code>. Any computer can reach any other computer directly or indirectly through the network.</p>

<p>You are given an initial computer network <code>connections</code>. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected.</p>

<p>Return <em>the minimum number of times you need to do this in order to make all the computers connected</em>. If it is not possible, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1319.Number%20of%20Operations%20to%20Make%20Network%20Connected/images/sample_1_1677.png" style="width: 500px; height: 148px;" />
<pre>
<strong>Input:</strong> n = 4, connections = [[0,1],[0,2],[1,2]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> Remove cable between computer 1 and 2 and place between computers 1 and 3.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1319.Number%20of%20Operations%20to%20Make%20Network%20Connected/images/sample_2_1677.png" style="width: 500px; height: 129px;" />
<pre>
<strong>Input:</strong> n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There are not enough cables.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= connections.length &lt;= min(n * (n - 1) / 2, 10<sup>5</sup>)</code></li>
	<li><code>connections[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>There are no repeated connections.</li>
	<li>No two computers are connected by more than one cable.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Union-Find

We can use a union-find data structure to maintain the connectivity between computers. Traverse all connections, and for each connection $(a, b)$, if $a$ and $b$ are already connected, then this connection is redundant, and we increment the count of redundant connections. Otherwise, we connect $a$ and $b$, and decrement the number of connected components.

Finally, if the number of connected components minus one is greater than the number of redundant connections, it means we cannot connect all computers, so we return -1. Otherwise, we return the number of connected components minus one.

The time complexity is $O(m \times \log n)$, and the space complexity is $O(n)$. Here, $n$ and $m$ are the number of computers and the number of connections, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def makeConnected(self, n: int, connections: List[List[int]]) -> int:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        cnt = 0
        p = list(range(n))
        for a, b in connections:
            pa, pb = find(a), find(b)
            if pa == pb:
                cnt += 1
            else:
                p[pa] = pb
                n -= 1
        return -1 if n - 1 > cnt else n - 1
```

#### Java

```java
class Solution {
    private int[] p;

    public int makeConnected(int n, int[][] connections) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        int cnt = 0;
        for (int[] e : connections) {
            int pa = find(e[0]), pb = find(e[1]);
            if (pa == pb) {
                ++cnt;
            } else {
                p[pa] = pb;
                --n;
            }
        }
        return n - 1 > cnt ? -1 : n - 1;
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
    int makeConnected(int n, vector<vector<int>>& connections) {
        vector<int> p(n);
        iota(p.begin(), p.end(), 0);
        int cnt = 0;
        function<int(int)> find = [&](int x) -> int {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };
        for (const auto& c : connections) {
            int pa = find(c[0]), pb = find(c[1]);
            if (pa == pb) {
                ++cnt;
            } else {
                p[pa] = pb;
                --n;
            }
        }
        return cnt >= n - 1 ? n - 1 : -1;
    }
};
```

#### Go

```go
func makeConnected(n int, connections [][]int) int {
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	cnt := 0
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for _, e := range connections {
		pa, pb := find(e[0]), find(e[1])
		if pa == pb {
			cnt++
		} else {
			p[pa] = pb
			n--
		}
	}
	if n-1 > cnt {
		return -1
	}
	return n - 1
}
```

#### TypeScript

```ts
function makeConnected(n: number, connections: number[][]): number {
    const p: number[] = Array.from({ length: n }, (_, i) => i);
    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    let cnt = 0;
    for (const [a, b] of connections) {
        const [pa, pb] = [find(a), find(b)];
        if (pa === pb) {
            ++cnt;
        } else {
            p[pa] = pb;
            --n;
        }
    }
    return cnt >= n - 1 ? n - 1 : -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
