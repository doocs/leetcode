---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2359.Find%20Closest%20Node%20to%20Given%20Two%20Nodes/README_EN.md
rating: 1714
source: Weekly Contest 304 Q3
tags:
    - Depth-First Search
    - Graph
---

<!-- problem:start -->

# [2359. Find Closest Node to Given Two Nodes](https://leetcode.com/problems/find-closest-node-to-given-two-nodes)

[中文文档](/solution/2300-2399/2359.Find%20Closest%20Node%20to%20Given%20Two%20Nodes/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>directed</strong> graph of <code>n</code> nodes numbered from <code>0</code> to <code>n - 1</code>, where each node has <strong>at most one</strong> outgoing edge.</p>

<p>The graph is represented with a given <strong>0-indexed</strong> array <code>edges</code> of size <code>n</code>, indicating that there is a directed edge from node <code>i</code> to node <code>edges[i]</code>. If there is no outgoing edge from <code>i</code>, then <code>edges[i] == -1</code>.</p>

<p>You are also given two integers <code>node1</code> and <code>node2</code>.</p>

<p>Return <em>the <strong>index</strong> of the node that can be reached from both </em><code>node1</code><em> and </em><code>node2</code><em>, such that the <strong>maximum</strong> between the distance from </em><code>node1</code><em> to that node, and from </em><code>node2</code><em> to that node is <strong>minimized</strong></em>. If there are multiple answers, return the node with the <strong>smallest</strong> index, and if no possible answer exists, return <code>-1</code>.</p>

<p>Note that <code>edges</code> may contain cycles.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2359.Find%20Closest%20Node%20to%20Given%20Two%20Nodes/images/graph4drawio-2.png" style="width: 321px; height: 161px;" />
<pre>
<strong>Input:</strong> edges = [2,2,3,-1], node1 = 0, node2 = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> The distance from node 0 to node 2 is 1, and the distance from node 1 to node 2 is 1.
The maximum of those two distances is 1. It can be proven that we cannot get a node with a smaller maximum distance than 1, so we return node 2.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2359.Find%20Closest%20Node%20to%20Given%20Two%20Nodes/images/graph4drawio-4.png" style="width: 195px; height: 161px;" />
<pre>
<strong>Input:</strong> edges = [1,2,-1], node1 = 0, node2 = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> The distance from node 0 to node 2 is 2, and the distance from node 2 to itself is 0.
The maximum of those two distances is 2. It can be proven that we cannot get a node with a smaller maximum distance than 2, so we return node 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == edges.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-1 &lt;= edges[i] &lt; n</code></li>
	<li><code>edges[i] != i</code></li>
	<li><code>0 &lt;= node1, node2 &lt; n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: BFS + Enumerate Common Nodes

We can first use BFS to calculate the distance from $node1$ and $node2$ to every node, denoted as $d_1$ and $d_2$ respectively. Then, enumerate all common nodes $i$, and for each, compute $\max(d_1[i], d_2[i])$. The answer is the node with the minimal such value.

The complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of nodes.

Related problems:

- [2203. Minimum Weighted Subgraph With the Required Paths](https://github.com/doocs/leetcode/blob/main/solution/2200-2299/2203.Minimum%20Weighted%20Subgraph%20With%20the%20Required%20Paths/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def closestMeetingNode(self, edges: List[int], node1: int, node2: int) -> int:
        def f(i):
            dist = [inf] * n
            dist[i] = 0
            q = deque([i])
            while q:
                i = q.popleft()
                for j in g[i]:
                    if dist[j] == inf:
                        dist[j] = dist[i] + 1
                        q.append(j)
            return dist

        g = defaultdict(list)
        for i, j in enumerate(edges):
            if j != -1:
                g[i].append(j)
        n = len(edges)
        d1 = f(node1)
        d2 = f(node2)
        ans, d = -1, inf
        for i, (a, b) in enumerate(zip(d1, d2)):
            if (t := max(a, b)) < d:
                d = t
                ans = i
        return ans
```

#### Java

```java
class Solution {
    private int n;
    private List<Integer>[] g;

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        n = edges.length;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < n; ++i) {
            if (edges[i] != -1) {
                g[i].add(edges[i]);
            }
        }
        int[] d1 = f(node1);
        int[] d2 = f(node2);
        int d = 1 << 30;
        int ans = -1;
        for (int i = 0; i < n; ++i) {
            int t = Math.max(d1[i], d2[i]);
            if (t < d) {
                d = t;
                ans = i;
            }
        }
        return ans;
    }

    private int[] f(int i) {
        int[] dist = new int[n];
        Arrays.fill(dist, 1 << 30);
        dist[i] = 0;
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(i);
        while (!q.isEmpty()) {
            i = q.poll();
            for (int j : g[i]) {
                if (dist[j] == 1 << 30) {
                    dist[j] = dist[i] + 1;
                    q.offer(j);
                }
            }
        }
        return dist;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int closestMeetingNode(vector<int>& edges, int node1, int node2) {
        int n = edges.size();
        vector<vector<int>> g(n);
        for (int i = 0; i < n; ++i) {
            if (edges[i] != -1) {
                g[i].push_back(edges[i]);
            }
        }
        const int inf = 1 << 30;
        using pii = pair<int, int>;
        auto f = [&](int i) {
            vector<int> dist(n, inf);
            dist[i] = 0;
            queue<int> q{{i}};
            while (!q.empty()) {
                i = q.front();
                q.pop();
                for (int j : g[i]) {
                    if (dist[j] == inf) {
                        dist[j] = dist[i] + 1;
                        q.push(j);
                    }
                }
            }
            return dist;
        };
        vector<int> d1 = f(node1);
        vector<int> d2 = f(node2);
        int ans = -1, d = inf;
        for (int i = 0; i < n; ++i) {
            int t = max(d1[i], d2[i]);
            if (t < d) {
                d = t;
                ans = i;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func closestMeetingNode(edges []int, node1 int, node2 int) int {
	n := len(edges)
	g := make([][]int, n)
	for i, j := range edges {
		if j != -1 {
			g[i] = append(g[i], j)
		}
	}
	const inf int = 1 << 30
	f := func(i int) []int {
		dist := make([]int, n)
		for j := range dist {
			dist[j] = inf
		}
		dist[i] = 0
		q := []int{i}
		for len(q) > 0 {
			i = q[0]
			q = q[1:]
			for _, j := range g[i] {
				if dist[j] == inf {
					dist[j] = dist[i] + 1
					q = append(q, j)
				}
			}
		}
		return dist
	}
	d1 := f(node1)
	d2 := f(node2)
	ans, d := -1, inf
	for i, a := range d1 {
		b := d2[i]
		t := max(a, b)
		if t < d {
			d = t
			ans = i
		}
	}
	return ans
}
```

#### TypeScript

```ts
function closestMeetingNode(edges: number[], node1: number, node2: number): number {
    const n = edges.length;
    const g = Array.from({ length: n }, () => []);
    for (let i = 0; i < n; ++i) {
        if (edges[i] != -1) {
            g[i].push(edges[i]);
        }
    }
    const inf = 1 << 30;
    const f = (i: number) => {
        const dist = new Array(n).fill(inf);
        dist[i] = 0;
        const q: number[] = [i];
        while (q.length) {
            i = q.shift();
            for (const j of g[i]) {
                if (dist[j] == inf) {
                    dist[j] = dist[i] + 1;
                    q.push(j);
                }
            }
        }
        return dist;
    };
    const d1 = f(node1);
    const d2 = f(node2);
    let ans = -1;
    let d = inf;
    for (let i = 0; i < n; ++i) {
        const t = Math.max(d1[i], d2[i]);
        if (t < d) {
            d = t;
            ans = i;
        }
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::VecDeque;

impl Solution {
    pub fn closest_meeting_node(edges: Vec<i32>, node1: i32, node2: i32) -> i32 {
        let n = edges.len();
        let mut g = vec![Vec::new(); n];
        for i in 0..n {
            if edges[i] != -1 {
                g[i].push(edges[i] as usize);
            }
        }
        let inf = 1 << 30;
        let f = |mut i: usize| -> Vec<i32> {
            let mut dist = vec![inf; n];
            dist[i] = 0;
            let mut q = VecDeque::new();
            q.push_back(i);
            while !q.is_empty() {
                i = q.pop_front().unwrap();
                for &j in &g[i] {
                    if dist[j] == inf {
                        dist[j] = dist[i] + 1;
                        q.push_back(j);
                    }
                }
            }
            dist
        };
        let d1 = f(node1 as usize);
        let d2 = f(node2 as usize);
        let mut ans = -1;
        let mut d = inf;
        for i in 0..n {
            let t = std::cmp::max(d1[i], d2[i]);
            if t < d {
                d = t;
                ans = i as i32;
            }
        }
        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int ClosestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.Length;
        List<int>[] g = new List<int>[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new List<int>();
            if (edges[i] != -1) {
                g[i].Add(edges[i]);
            }
        }
        int inf = 1 << 30;
        int[] f(int i) {
            int[] dist = new int[n];
            Array.Fill(dist, inf);
            dist[i] = 0;
            Queue<int> q = new Queue<int>();
            q.Enqueue(i);
            while (q.Count > 0) {
                i = q.Dequeue();
                foreach (int j in g[i]) {
                    if (dist[j] == inf) {
                        dist[j] = dist[i] + 1;
                        q.Enqueue(j);
                    }
                }
            }
            return dist;
        }
        int[] d1 = f(node1);
        int[] d2 = f(node2);
        int ans = -1, d = inf;
        for (int i = 0; i < n; ++i) {
            int t = Math.Max(d1[i], d2[i]);
            if (t < d) {
                d = t;
                ans = i;
            }
        }
        return ans;
    }
}
```

#### Swift

```swift
class Solution {
    func closestMeetingNode(_ edges: [Int], _ node1: Int, _ node2: Int) -> Int {
        let n = edges.count
        var g = [[Int]](repeating: [], count: n)
        for i in 0..<n {
            if edges[i] != -1 {
                g[i].append(edges[i])
            }
        }
        let inf = 1 << 30

        func f(_ i: Int) -> [Int] {
            var dist = [Int](repeating: inf, count: n)
            dist[i] = 0
            var q = [i]
            var idx = 0
            while idx < q.count {
                let i = q[idx]
                idx += 1
                for j in g[i] {
                    if dist[j] == inf {
                        dist[j] = dist[i] + 1
                        q.append(j)
                    }
                }
            }
            return dist
        }

        let d1 = f(node1)
        let d2 = f(node2)
        var ans = -1, d = inf
        for i in 0..<n {
            let t = max(d1[i], d2[i])
            if t < d {
                d = t
                ans = i
            }
        }
        return ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
