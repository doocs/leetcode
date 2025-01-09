---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3378.Count%20Connected%20Components%20in%20LCM%20Graph/README_EN.md
rating: 2532
source: Biweekly Contest 145 Q4
tags:
    - Union Find
    - Array
    - Hash Table
    - Math
    - Number Theory
---

<!-- problem:start -->

# [3378. Count Connected Components in LCM Graph](https://leetcode.com/problems/count-connected-components-in-lcm-graph)

[中文文档](/solution/3300-3399/3378.Count%20Connected%20Components%20in%20LCM%20Graph/README.md)

## Description

<!-- description:start -->

<p>You are given an array of integers <code>nums</code> of size <code>n</code> and a <strong>positive</strong> integer <code>threshold</code>.</p>

<p>There is a graph consisting of <code>n</code> nodes with the&nbsp;<code>i<sup>th</sup></code>&nbsp;node having a value of <code>nums[i]</code>. Two nodes <code>i</code> and <code>j</code> in the graph are connected via an <strong>undirected</strong> edge if <code>lcm(nums[i], nums[j]) &lt;= threshold</code>.</p>

<p>Return the number of <strong>connected components</strong> in this graph.</p>

<p>A <strong>connected component</strong> is a subgraph of a graph in which there exists a path between any two vertices, and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.</p>

<p>The term <code>lcm(a, b)</code> denotes the <strong>least common multiple</strong> of <code>a</code> and <code>b</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4,8,3,9], threshold = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong>&nbsp;</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3378.Count%20Connected%20Components%20in%20LCM%20Graph/images/example0.png" style="width: 250px; height: 251px;" /></p>

<p>&nbsp;</p>

<p>The four connected components are <code>(2, 4)</code>, <code>(3)</code>, <code>(8)</code>, <code>(9)</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4,8,3,9,12], threshold = 10</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong>&nbsp;</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3378.Count%20Connected%20Components%20in%20LCM%20Graph/images/example1.png" style="width: 250px; height: 252px;" /></p>

<p>The two connected components are <code>(2, 3, 4, 8, 9)</code>, and <code>(12)</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li>All elements of <code>nums</code> are unique.</li>
	<li><code>1 &lt;= threshold &lt;= 2 * 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Union Find

<!-- tabs:start -->

#### Python3

```python
class DSU:
    def __init__(self, n):
        self.parent = {i: i for i in range(n)}
        self.rank = {i: 0 for i in range(n)}

    def make_set(self, v):
        self.parent[v] = v
        self.rank[v] = 1

    def find(self, x):
        if self.parent[x] != x:
            self.parent[x] = self.find(self.parent[x])
        return self.parent[x]

    def union_set(self, u, v):
        u = self.find(u)
        v = self.find(v)
        if u != v:
            if self.rank[u] < self.rank[v]:
                u, v = v, u
            self.parent[v] = u
            if self.rank[u] == self.rank[v]:
                self.rank[u] += 1


class Solution:
    def countComponents(self, nums, threshold):
        dsu = DSU(threshold + 1)

        for num in nums:
            for j in range(num, threshold + 1, num):
                dsu.union_set(num, j)

        unique_parents = set()
        for num in nums:
            if num > threshold:
                unique_parents.add(num)
            else:
                unique_parents.add(dsu.find(num))

        return len(unique_parents)
```

#### Java

```java
class DSU {
    private Map<Integer, Integer> parent;
    private Map<Integer, Integer> rank;

    public DSU(int n) {
        parent = new HashMap<>();
        rank = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            parent.put(i, i);
            rank.put(i, 0);
        }
    }

    public void makeSet(int v) {
        parent.put(v, v);
        rank.put(v, 1);
    }

    public int find(int x) {
        if (parent.get(x) != x) {
            parent.put(x, find(parent.get(x)));
        }
        return parent.get(x);
    }

    public void unionSet(int u, int v) {
        u = find(u);
        v = find(v);
        if (u != v) {
            if (rank.get(u) < rank.get(v)) {
                int temp = u;
                u = v;
                v = temp;
            }
            parent.put(v, u);
            if (rank.get(u).equals(rank.get(v))) {
                rank.put(u, rank.get(u) + 1);
            }
        }
    }
}

class Solution {
    public int countComponents(int[] nums, int threshold) {
        DSU dsu = new DSU(threshold);

        for (int num : nums) {
            for (int j = num; j <= threshold; j += num) {
                dsu.unionSet(num, j);
            }
        }

        Set<Integer> uniqueParents = new HashSet<>();
        for (int num : nums) {
            if (num > threshold) {
                uniqueParents.add(num);
            } else {
                uniqueParents.add(dsu.find(num));
            }
        }

        return uniqueParents.size();
    }
}
```

#### C++

```cpp
typedef struct DSU {
    unordered_map<int, int> par, rank;
    DSU(int n) {
        for (int i = 0; i < n; ++i) {
            par[i] = i;
            rank[i] = 0;
        }
    }

    void makeSet(int v) {
        par[v] = v;
        rank[v] = 1;
    }

    int find(int x) {
        if (par[x] == x) {
            return x;
        }
        return par[x] = find(par[x]);
    }

    void unionSet(int u, int v) {
        u = find(u);
        v = find(v);
        if (u != v) {
            if (rank[u] < rank[v]) swap(u, v);
            par[v] = u;
            if (rank[u] == rank[v]) rank[u]++;
        }
    }
} DSU;

class Solution {
public:
    int countComponents(vector<int> &nums, int threshold) {
        DSU dsu(threshold);
        for (auto &num : nums) {
            for (int j = num; j <= threshold; j += num) {
                dsu.unionSet(num, j);
            }
        }
        unordered_set<int> par;
        for (auto &num : nums) {
            if (num > threshold) {
                par.insert(num);
            } else {
                par.insert(dsu.find(num));
            }
        }
        return par.size();
    }
};
```

#### Go

```go
type DSU struct {
	parent map[int]int
	rank   map[int]int
}

func NewDSU(n int) *DSU {
	dsu := &DSU{
		parent: make(map[int]int),
		rank:   make(map[int]int),
	}
	for i := 0; i <= n; i++ {
		dsu.parent[i] = i
		dsu.rank[i] = 0
	}
	return dsu
}

func (dsu *DSU) Find(x int) int {
	if dsu.parent[x] != x {
		dsu.parent[x] = dsu.Find(dsu.parent[x])
	}
	return dsu.parent[x]
}

func (dsu *DSU) Union(u, v int) {
	uRoot := dsu.Find(u)
	vRoot := dsu.Find(v)
	if uRoot != vRoot {
		if dsu.rank[uRoot] < dsu.rank[vRoot] {
			uRoot, vRoot = vRoot, uRoot
		}
		dsu.parent[vRoot] = uRoot
		if dsu.rank[uRoot] == dsu.rank[vRoot] {
			dsu.rank[uRoot]++
		}
	}
}

func countComponents(nums []int, threshold int) int {
	dsu := NewDSU(threshold)

	for _, num := range nums {
		for j := num; j <= threshold; j += num {
			dsu.Union(num, j)
		}
	}

	uniqueParents := make(map[int]struct{})
	for _, num := range nums {
		if num > threshold {
			uniqueParents[num] = struct{}{}
		} else {
			uniqueParents[dsu.Find(num)] = struct{}{}
		}
	}

	return len(uniqueParents)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: DFS

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def dfs(self, node, adj, vis):
        if vis[node]:
            return
        vis[node] = True
        for neighbor in adj[node]:
            self.dfs(neighbor, adj, vis)

    def countComponents(self, nums, threshold):
        adj = [[] for _ in range(threshold + 1)]
        vis = [False] * (threshold + 1)
        ans = 0

        for num in nums:
            if num > threshold:
                ans += 1
                continue
            for j in range(2 * num, threshold + 1, num):
                adj[num].append(j)
                adj[j].append(num)

        for num in nums:
            if num <= threshold and not vis[num]:
                self.dfs(num, adj, vis)
                ans += 1

        return ans
```

#### Java

```java
class Solution {
    private void dfs(int node, List<List<Integer>> adj, boolean[] visited) {
        if (visited[node]) return;
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            dfs(neighbor, adj, visited);
        }
    }

    public int countComponents(int[] nums, int threshold) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= threshold; i++) {
            adj.add(new ArrayList<>());
        }
        boolean[] visited = new boolean[threshold + 1];
        int ans = 0;

        for (int num : nums) {
            if (num > threshold) {
                ans++;
                continue;
            }
            for (int j = 2 * num; j <= threshold; j += num) {
                adj.get(num).add(j);
                adj.get(j).add(num);
            }
        }

        for (int num : nums) {
            if (num <= threshold && !visited[num]) {
                dfs(num, adj, visited);
                ans++;
            }
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
private:
    void dfs(int node, vector<vector<int>> &adj, vector<bool> &vis) {
        if (vis[node]) return;
        vis[node] = true;
        for (auto &u : adj[node]) {
            dfs(u, adj, vis);
        }
    }

public:
    int countComponents(vector<int> &nums, int threshold) {
        vector<vector<int>> adj(threshold + 1);
        vector<bool> vis(threshold + 1, false);
        int ans = 0;
        for (auto &num : nums) {
            if (num > threshold) {
                ++ans;
                continue;
            }
            for (int j = 2 * num; j <= threshold; j += num) {
                adj[num].push_back(j);
                adj[j].push_back(num);
            }
        }
        for (auto &num : nums) {
            if (num <= threshold && !vis[num]) {
                dfs(num, adj, vis);
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func dfs(node int, adj [][]int, visited []bool) {
	if visited[node] {
		return
	}
	visited[node] = true
	for _, neighbor := range adj[node] {
		dfs(neighbor, adj, visited)
	}
}

func countComponents(nums []int, threshold int) int {
	adj := make([][]int, threshold+1)
	for i := range adj {
		adj[i] = []int{}
	}

	visited := make([]bool, threshold+1)
	components := 0

	for _, num := range nums {
		if num > threshold {
			components++
			continue
		}
		for j := 2 * num; j <= threshold; j += num {
			adj[num] = append(adj[num], j)
			adj[j] = append(adj[j], num)
		}
	}

	for _, num := range nums {
		if num <= threshold && !visited[num] {
			dfs(num, adj, visited)
			components++
		}
	}

	return components
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
