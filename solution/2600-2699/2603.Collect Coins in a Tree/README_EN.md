# [2603. Collect Coins in a Tree](https://leetcode.com/problems/collect-coins-in-a-tree)

[中文文档](/solution/2600-2699/2603.Collect%20Coins%20in%20a%20Tree/README.md)

## Description

<p>There exists an undirected and unrooted tree with <code>n</code> nodes indexed from <code>0</code> to <code>n - 1</code>. You are given an integer <code>n</code> and a 2D integer array edges of length <code>n - 1</code>, where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the tree. You are also given&nbsp;an array <code>coins</code> of size <code>n</code> where <code>coins[i]</code> can be either <code>0</code> or <code>1</code>, where <code>1</code> indicates the presence of a coin in the vertex <code>i</code>.</p>

<p>Initially, you choose to start at any vertex in&nbsp;the tree.&nbsp;Then, you can perform&nbsp;the following operations any number of times:&nbsp;</p>

<ul>
	<li>Collect all the coins that are at a distance of at most <code>2</code> from the current vertex, or</li>
	<li>Move to any adjacent vertex in the tree.</li>
</ul>

<p>Find <em>the minimum number of edges you need to go through to collect all the coins and go back to the initial vertex</em>.</p>

<p>Note that if you pass an edge several times, you need to count it into the answer several times.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2603.Collect%20Coins%20in%20a%20Tree/images/graph-2.png" style="width: 522px; height: 522px;" />
<pre>
<strong>Input:</strong> coins = [1,0,0,0,0,1], edges = [[0,1],[1,2],[2,3],[3,4],[4,5]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Start at vertex 2, collect the coin at vertex 0, move to vertex 3, collect the coin at vertex 5 then move back to vertex 2.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2603.Collect%20Coins%20in%20a%20Tree/images/graph-4.png" style="width: 522px; height: 522px;" />
<pre>
<strong>Input:</strong> coins = [0,0,0,1,1,0,0,1], edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[5,6],[5,7]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Start at vertex 0, collect the coins at vertices 4 and 3, move to vertex 2,  collect the coin at vertex 7, then move back to vertex 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == coins.length</code></li>
	<li><code>1 &lt;= n &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= coins[i] &lt;= 1</code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>edges</code> represents a valid tree.</li>
</ul>

## Solutions

**Solution 1: Topological sorting**

We first convert the edges in $edges$ to the adjacency list $g$, where $g[i]$ represents all the adjacent nodes of node $i$, represented by a set.

Then we traverse all nodes and find the nodes where $coins[i]=0$ and $g[i]$ only has one node (that is, the leaf node where the coin is $0$), and add them to the queue $q$.

Then we continuously remove nodes from the queue and delete them from the adjacent list. Then we check whether the adjacent nodes meet the condition where $coins[j]=0$ and $g[j]$ only has one node. If it meets, we add it to the queue $q$. Loop until the queue is empty.

After the above operation, we get a new tree, and the leaf nodes of the tree are all nodes where the coin is $1$.

Then, we delete the remaining two layers of leaf nodes, and finally get a tree where all nodes need to be visited. We only need to count the number of edges and multiply it by $2$ to get the answer.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the number of nodes

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def collectTheCoins(self, coins: List[int], edges: List[List[int]]) -> int:
        g = defaultdict(set)
        for a, b in edges:
            g[a].add(b)
            g[b].add(a)
        n = len(coins)
        q = deque(i for i in range(n) if len(g[i]) == 1 and coins[i] == 0)
        while q:
            i = q.popleft()
            for j in g[i]:
                g[j].remove(i)
                if coins[j] == 0 and len(g[j]) == 1:
                    q.append(j)
            g[i].clear()
        for k in range(2):
            q = [i for i in range(n) if len(g[i]) == 1]
            for i in q:
                for j in g[i]:
                    g[j].remove(i)
                g[i].clear()
        return sum(len(g[a]) > 0 and len(g[b]) > 0 for a, b in edges) * 2
```

### **Java**

```java
class Solution {
    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        Set<Integer>[] g = new Set[n];
        Arrays.setAll(g, k -> new HashSet<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (coins[i] == 0 && g[i].size() == 1) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int i = q.poll();
            for (int j : g[i]) {
                g[j].remove(i);
                if (coins[j] == 0 && g[j].size() == 1) {
                    q.offer(j);
                }
            }
            g[i].clear();
        }
        q.clear();
        for (int k = 0; k < 2; ++k) {
            for (int i = 0; i < n; ++i) {
                if (g[i].size() == 1) {
                    q.offer(i);
                }
            }
            for (int i : q) {
                for (int j : g[i]) {
                    g[j].remove(i);
                }
                g[i].clear();
            }
        }
        int ans = 0;
        for (var e : edges) {
            int a = e[0], b = e[1];
            if (g[a].size() > 0 && g[b].size() > 0) {
                ans += 2;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int collectTheCoins(vector<int>& coins, vector<vector<int>>& edges) {
        int n = coins.size();
        unordered_set<int> g[n];
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].insert(b);
            g[b].insert(a);
        }
        queue<int> q;
        for (int i = 0; i < n; ++i) {
            if (coins[i] == 0 && g[i].size() == 1) {
                q.push(i);
            }
        }
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            for (int j : g[i]) {
                g[j].erase(i);
                if (coins[j] == 0 && g[j].size() == 1) {
                    q.push(j);
                }
            }
            g[i].clear();
        }
        for (int k = 0; k < 2; ++k) {
            vector<int> q;
            for (int i = 0; i < n; ++i) {
                if (g[i].size() == 1) {
                    q.push_back(i);
                }
            }
            for (int i : q) {
                for (int j : g[i]) {
                    g[j].erase(i);
                }
                g[i].clear();
            }
        }
        int ans = 0;
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            if (g[a].size() && g[b].size()) {
                ans += 2;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func collectTheCoins(coins []int, edges [][]int) int {
	n := len(coins)
	g := make([]map[int]bool, n)
	for i := range g {
		g[i] = map[int]bool{}
	}
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a][b] = true
		g[b][a] = true
	}
	q := []int{}
	for i, c := range coins {
		if c == 0 && len(g[i]) == 1 {
			q = append(q, i)
		}
	}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		for j := range g[i] {
			delete(g[j], i)
			if coins[j] == 0 && len(g[j]) == 1 {
				q = append(q, j)
			}
		}
		g[i] = map[int]bool{}
	}
	for k := 0; k < 2; k++ {
		q := []int{}
		for i := range coins {
			if len(g[i]) == 1 {
				q = append(q, i)
			}
		}
		for _, i := range q {
			for j := range g[i] {
				delete(g[j], i)
			}
			g[i] = map[int]bool{}
		}
	}
	ans := 0
	for _, e := range edges {
		a, b := e[0], e[1]
		if len(g[a]) > 0 && len(g[b]) > 0 {
			ans += 2
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function collectTheCoins(coins: number[], edges: number[][]): number {
    const n = coins.length;
    const g: Set<number>[] = new Array(n).fill(0).map(() => new Set<number>());
    for (const [a, b] of edges) {
        g[a].add(b);
        g[b].add(a);
    }
    let q: number[] = [];
    for (let i = 0; i < n; ++i) {
        if (coins[i] === 0 && g[i].size === 1) {
            q.push(i);
        }
    }
    while (q.length) {
        const i = q.pop()!;
        for (const j of g[i]) {
            g[j].delete(i);
            if (coins[j] === 0 && g[j].size === 1) {
                q.push(j);
            }
        }
        g[i].clear();
    }
    q = [];
    for (let k = 0; k < 2; ++k) {
        for (let i = 0; i < n; ++i) {
            if (g[i].size === 1) {
                q.push(i);
            }
        }
        for (const i of q) {
            for (const j of g[i]) {
                g[j].delete(i);
            }
            g[i].clear();
        }
    }
    let ans = 0;
    for (const [a, b] of edges) {
        if (g[a].size > 0 && g[b].size > 0) {
            ans += 2;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
