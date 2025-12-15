---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2039.The%20Time%20When%20the%20Network%20Becomes%20Idle/README_EN.md
rating: 1865
source: Biweekly Contest 63 Q3
tags:
    - Breadth-First Search
    - Graph
    - Array
---

<!-- problem:start -->

# [2039. The Time When the Network Becomes Idle](https://leetcode.com/problems/the-time-when-the-network-becomes-idle)

[中文文档](/solution/2000-2099/2039.The%20Time%20When%20the%20Network%20Becomes%20Idle/README.md)

## Description

<!-- description:start -->

<p>There is a network of <code>n</code> servers, labeled from <code>0</code> to <code>n - 1</code>. You are given a 2D integer array <code>edges</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates there is a message channel between servers <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code>, and they can pass <strong>any</strong> number of messages to <strong>each other</strong> directly in <strong>one</strong> second. You are also given a <strong>0-indexed</strong> integer array <code>patience</code> of length <code>n</code>.</p>

<p>All servers are <strong>connected</strong>, i.e., a message can be passed from one server to any other server(s) directly or indirectly through the message channels.</p>

<p>The server labeled <code>0</code> is the <strong>master</strong> server. The rest are <strong>data</strong> servers. Each data server needs to send its message to the master server for processing and wait for a reply. Messages move between servers <strong>optimally</strong>, so every message takes the <strong>least amount of time</strong> to arrive at the master server. The master server will process all newly arrived messages <strong>instantly</strong> and send a reply to the originating server via the <strong>reversed path</strong> the message had gone through.</p>

<p>At the beginning of second <code>0</code>, each data server sends its message to be processed. Starting from second <code>1</code>, at the <strong>beginning</strong> of <strong>every</strong> second, each data server will check if it has received a reply to the message it sent (including any newly arrived replies) from the master server:</p>

<ul>
	<li>If it has not, it will <strong>resend</strong> the message periodically. The data server <code>i</code> will resend the message every <code>patience[i]</code> second(s), i.e., the data server <code>i</code> will resend the message if <code>patience[i]</code> second(s) have <strong>elapsed</strong> since the <strong>last</strong> time the message was sent from this server.</li>
	<li>Otherwise, <strong>no more resending</strong> will occur from this server.</li>
</ul>

<p>The network becomes <strong>idle</strong> when there are <strong>no</strong> messages passing between servers or arriving at servers.</p>

<p>Return <em>the <strong>earliest second</strong> starting from which the network becomes <strong>idle</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="example 1" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2039.The%20Time%20When%20the%20Network%20Becomes%20Idle/images/quiet-place-example1.png" style="width: 750px; height: 384px;" />
<pre>
<strong>Input:</strong> edges = [[0,1],[1,2]], patience = [0,2,1]
<strong>Output:</strong> 8
<strong>Explanation:</strong>
At (the beginning of) second 0,
- Data server 1 sends its message (denoted 1A) to the master server.
- Data server 2 sends its message (denoted 2A) to the master server.

At second 1,

- Message 1A arrives at the master server. Master server processes message 1A instantly and sends a reply 1A back.
- Server 1 has not received any reply. 1 second (1 &lt; patience[1] = 2) elapsed since this server has sent the message, therefore it does not resend the message.
- Server 2 has not received any reply. 1 second (1 == patience[2] = 1) elapsed since this server has sent the message, therefore it resends the message (denoted 2B).

At second 2,

- The reply 1A arrives at server 1. No more resending will occur from server 1.
- Message 2A arrives at the master server. Master server processes message 2A instantly and sends a reply 2A back.
- Server 2 resends the message (denoted 2C).
  ...
  At second 4,
- The reply 2A arrives at server 2. No more resending will occur from server 2.
  ...
  At second 7, reply 2D arrives at server 2.

Starting from the beginning of the second 8, there are no messages passing between servers or arriving at servers.
This is the time when the network becomes idle.

</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="example 2" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2039.The%20Time%20When%20the%20Network%20Becomes%20Idle/images/network_a_quiet_place_2.png" style="width: 100px; height: 85px;" />
<pre>
<strong>Input:</strong> edges = [[0,1],[0,2],[1,2]], patience = [0,10,10]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Data servers 1 and 2 receive a reply back at the beginning of second 2.
From the beginning of the second 3, the network becomes idle.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == patience.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>patience[0] == 0</code></li>
	<li><code>1 &lt;= patience[i] &lt;= 10<sup>5</sup></code> for <code>1 &lt;= i &lt; n</code></li>
	<li><code>1 &lt;= edges.length &lt;= min(10<sup>5</sup>, n * (n - 1) / 2)</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li>There are no duplicate edges.</li>
	<li>Each server can directly or indirectly reach another server.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: BFS

First, we construct an undirected graph $g$ based on the 2D array $edges$, where $g[u]$ represents all neighboring nodes of node $u$.

Then, we can use breadth-first search (BFS) to find the shortest distance $d_i$ from each node $i$ to the main server. The earliest time that node $i$ can receive a reply after sending a message is $2 \times d_i$. Since each data server $i$ resends a message every $patience[i]$ seconds, the last time that each data server sends a message is $(2 \times d_i - 1) / patience[i] \times patience[i]$. Therefore, the latest time that the network becomes idle is $(2 \times d_i - 1) / patience[i] \times patience[i] + 2 \times d_i$, plus 1 second for processing time. We find the latest of these times, which is the earliest time that the computer network becomes idle.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def networkBecomesIdle(self, edges: List[List[int]], patience: List[int]) -> int:
        g = defaultdict(list)
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        q = deque([0])
        vis = {0}
        ans = d = 0
        while q:
            d += 1
            t = d * 2
            for _ in range(len(q)):
                u = q.popleft()
                for v in g[u]:
                    if v not in vis:
                        vis.add(v)
                        q.append(v)
                        ans = max(ans, (t - 1) // patience[v] * patience[v] + t + 1)
        return ans
```

#### Java

```java
class Solution {
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        int n = patience.length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        boolean[] vis = new boolean[n];
        vis[0] = true;
        int ans = 0, d = 0;
        while (!q.isEmpty()) {
            ++d;
            int t = d * 2;
            for (int i = q.size(); i > 0; --i) {
                int u = q.poll();
                for (int v : g[u]) {
                    if (!vis[v]) {
                        vis[v] = true;
                        q.offer(v);
                        ans = Math.max(ans, (t - 1) / patience[v] * patience[v] + t + 1);
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
class Solution {
public:
    int networkBecomesIdle(vector<vector<int>>& edges, vector<int>& patience) {
        int n = patience.size();
        vector<int> g[n];
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        queue<int> q{{0}};
        bool vis[n];
        memset(vis, false, sizeof(vis));
        vis[0] = true;
        int ans = 0, d = 0;
        while (!q.empty()) {
            ++d;
            int t = d * 2;
            for (int i = q.size(); i; --i) {
                int u = q.front();
                q.pop();
                for (int v : g[u]) {
                    if (!vis[v]) {
                        vis[v] = true;
                        q.push(v);
                        ans = max(ans, (t - 1) / patience[v] * patience[v] + t + 1);
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
func networkBecomesIdle(edges [][]int, patience []int) (ans int) {
	n := len(patience)
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	q := []int{0}
	vis := make([]bool, n)
	vis[0] = true
	for d := 1; len(q) > 0; d++ {
		t := d * 2
		for i := len(q); i > 0; i-- {
			u := q[0]
			q = q[1:]
			for _, v := range g[u] {
				if !vis[v] {
					vis[v] = true
					q = append(q, v)
					ans = max(ans, (t-1)/patience[v]*patience[v]+t+1)
				}
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function networkBecomesIdle(edges: number[][], patience: number[]): number {
    const n = patience.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }
    const vis: boolean[] = Array.from({ length: n }, () => false);
    vis[0] = true;
    let q: number[] = [0];
    let ans = 0;
    for (let d = 1; q.length > 0; ++d) {
        const t = d * 2;
        const nq: number[] = [];
        for (const u of q) {
            for (const v of g[u]) {
                if (!vis[v]) {
                    vis[v] = true;
                    nq.push(v);
                    ans = Math.max(ans, (((t - 1) / patience[v]) | 0) * patience[v] + t + 1);
                }
            }
        }
        q = nq;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
