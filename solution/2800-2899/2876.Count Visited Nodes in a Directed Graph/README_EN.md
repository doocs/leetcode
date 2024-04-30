# [2876. Count Visited Nodes in a Directed Graph](https://leetcode.com/problems/count-visited-nodes-in-a-directed-graph)

[中文文档](/solution/2800-2899/2876.Count%20Visited%20Nodes%20in%20a%20Directed%20Graph/README.md)

<!-- tags:Graph,Memoization,Dynamic Programming -->

## Description

<p>There is a <strong>directed</strong> graph consisting of <code>n</code> nodes numbered from <code>0</code> to <code>n - 1</code> and <code>n</code> directed edges.</p>

<p>You are given a <strong>0-indexed</strong> array <code>edges</code> where <code>edges[i]</code> indicates that there is an edge from node <code>i</code> to node <code>edges[i]</code>.</p>

<p>Consider the following process on the graph:</p>

<ul>
	<li>You start from a node <code>x</code> and keep visiting other nodes through edges until you reach a node that you have already visited before on this <strong>same</strong> process.</li>
</ul>

<p>Return <em>an array </em><code>answer</code><em> where </em><code>answer[i]</code><em> is the number of <strong>different</strong> nodes that you will visit if you perform the process starting from node </em><code>i</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2876.Count%20Visited%20Nodes%20in%20a%20Directed%20Graph/images/graaphdrawio-1.png" />
<pre>
<strong>Input:</strong> edges = [1,2,0,0]
<strong>Output:</strong> [3,3,3,4]
<strong>Explanation:</strong> We perform the process starting from each node in the following way:
- Starting from node 0, we visit the nodes 0 -&gt; 1 -&gt; 2 -&gt; 0. The number of different nodes we visit is 3.
- Starting from node 1, we visit the nodes 1 -&gt; 2 -&gt; 0 -&gt; 1. The number of different nodes we visit is 3.
- Starting from node 2, we visit the nodes 2 -&gt; 0 -&gt; 1 -&gt; 2. The number of different nodes we visit is 3.
- Starting from node 3, we visit the nodes 3 -&gt; 0 -&gt; 1 -&gt; 2 -&gt; 0. The number of different nodes we visit is 4.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2876.Count%20Visited%20Nodes%20in%20a%20Directed%20Graph/images/graaph2drawio.png" style="width: 191px; height: 251px;" />
<pre>
<strong>Input:</strong> edges = [1,2,3,4,0]
<strong>Output:</strong> [5,5,5,5,5]
<strong>Explanation:</strong> Starting from any node we can visit every node in the graph in the process.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == edges.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges[i] &lt;= n - 1</code></li>
	<li><code>edges[i] != i</code></li>
</ul>

## Solutions

### Solution 1: Basic Tree + Traversal

We can use an array $ans$ to record the answer for each node, and an array $vis$ to record the visit order for each node.

For each node $i$, if it has not been visited yet, we start traversing from node $i$. There are two cases:

-   If we encounter a node that has been visited before during the traversal, then we must have first entered the cycle and then walked around the cycle. For nodes outside the cycle, their answer is the length of the cycle plus the distance from the node to the cycle; for nodes inside the cycle, their answer is the length of the cycle.
-   If we encounter a node that has been visited before during the traversal, then for each visited node, its answer is the distance from the current node to this node plus the answer of this node.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array edges.

<!-- tabs:start -->

```python
class Solution:
    def countVisitedNodes(self, edges: List[int]) -> List[int]:
        n = len(edges)
        ans = [0] * n
        vis = [0] * n
        for i in range(n):
            if not ans[i]:
                cnt, j = 0, i
                while not vis[j]:
                    cnt += 1
                    vis[j] = cnt
                    j = edges[j]
                cycle, total = 0, cnt + ans[j]
                if not ans[j]:
                    cycle = cnt - vis[j] + 1
                    total = cnt
                j = i
                while not ans[j]:
                    ans[j] = max(total, cycle)
                    total -= 1
                    j = edges[j]
        return ans
```

```java
class Solution {
    public int[] countVisitedNodes(List<Integer> edges) {
        int n = edges.size();
        int[] ans = new int[n];
        int[] vis = new int[n];
        for (int i = 0; i < n; ++i) {
            if (ans[i] == 0) {
                int cnt = 0, j = i;
                while (vis[j] == 0) {
                    vis[j] = ++cnt;
                    j = edges.get(j);
                }
                int cycle = 0, total = cnt + ans[j];
                if (ans[j] == 0) {
                    cycle = cnt - vis[j] + 1;
                }
                j = i;
                while (ans[j] == 0) {
                    ans[j] = Math.max(total--, cycle);
                    j = edges.get(j);
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> countVisitedNodes(vector<int>& edges) {
        int n = edges.size();
        vector<int> ans(n), vis(n);
        for (int i = 0; i < n; ++i) {
            if (!ans[i]) {
                int cnt = 0, j = i;
                while (vis[j] == 0) {
                    vis[j] = ++cnt;
                    j = edges[j];
                }
                int cycle = 0, total = cnt + ans[j];
                if (ans[j] == 0) {
                    cycle = cnt - vis[j] + 1;
                }
                j = i;
                while (ans[j] == 0) {
                    ans[j] = max(total--, cycle);
                    j = edges[j];
                }
            }
        }
        return ans;
    }
};
```

```go
func countVisitedNodes(edges []int) []int {
	n := len(edges)
	ans := make([]int, n)
	vis := make([]int, n)
	for i := range ans {
		if ans[i] == 0 {
			cnt, j := 0, i
			for vis[j] == 0 {
				cnt++
				vis[j] = cnt
				j = edges[j]
			}
			cycle, total := 0, cnt+ans[j]
			if ans[j] == 0 {
				cycle = cnt - vis[j] + 1
			}
			j = i
			for ans[j] == 0 {
				ans[j] = max(total, cycle)
				total--
				j = edges[j]
			}
		}
	}
	return ans
}
```

```ts
function countVisitedNodes(edges: number[]): number[] {
    const n = edges.length;
    const ans: number[] = Array(n).fill(0);
    const vis: number[] = Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        if (ans[i] === 0) {
            let [cnt, j] = [0, i];
            while (vis[j] === 0) {
                vis[j] = ++cnt;
                j = edges[j];
            }
            let [cycle, total] = [0, cnt + ans[j]];
            if (ans[j] === 0) {
                cycle = cnt - vis[j] + 1;
            }
            j = i;
            while (ans[j] === 0) {
                ans[j] = Math.max(total--, cycle);
                j = edges[j];
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```java
class Solution {
    void dfs(int curr, List<Integer> edges, int[] ans) {

        List<Integer> path = new ArrayList<>();
        int prev = -1;
        while (ans[curr] == 0) {
            path.add(curr);
            ans[curr] = prev == -1 ? -1 : ans[prev] - 1;
            prev = curr;
            curr = edges.get(curr);
        }
        int idx = path.size() - 1;
        if (ans[curr] < 0) {
            int cycle = ans[curr] - ans[path.get(idx)] + 1;
            int start = ans[curr];
            for (; idx >= 0 && ans[path.get(idx)] <= start; idx--) {
                ans[path.get(idx)] = cycle;
            }
        }
        for (; idx >= 0; idx--) {
            ans[path.get(idx)] = ans[edges.get(path.get(idx))] + 1;
        }
    }

    public int[] countVisitedNodes(List<Integer> edges) {
        int n = edges.size();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (ans[i] > 0) {
                continue;
            }
            dfs(i, edges, ans);
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- end -->
