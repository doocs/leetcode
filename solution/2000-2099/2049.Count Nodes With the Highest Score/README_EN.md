# [2049. Count Nodes With the Highest Score](https://leetcode.com/problems/count-nodes-with-the-highest-score)

[中文文档](/solution/2000-2099/2049.Count%20Nodes%20With%20the%20Highest%20Score/README.md)

## Description

<p>There is a <strong>binary</strong> tree rooted at <code>0</code> consisting of <code>n</code> nodes. The nodes are labeled from <code>0</code> to <code>n - 1</code>. You are given a <strong>0-indexed</strong> integer array <code>parents</code> representing the tree, where <code>parents[i]</code> is the parent of node <code>i</code>. Since node <code>0</code> is the root, <code>parents[0] == -1</code>.</p>

<p>Each node has a <strong>score</strong>. To find the score of a node, consider if the node and the edges connected to it were <strong>removed</strong>. The tree would become one or more <strong>non-empty</strong> subtrees. The <strong>size</strong> of a subtree is the number of the nodes in it. The <strong>score</strong> of the node is the <strong>product of the sizes</strong> of all those subtrees.</p>

<p>Return <em>the <strong>number</strong> of nodes that have the <strong>highest score</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="example-1" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2049.Count%20Nodes%20With%20the%20Highest%20Score/images/example-1.png" style="width: 604px; height: 266px;" />
<pre>
<strong>Input:</strong> parents = [-1,2,0,2,0]
<strong>Output:</strong> 3
<strong>Explanation:</strong>
- The score of node 0 is: 3 * 1 = 3
- The score of node 1 is: 4 = 4
- The score of node 2 is: 1 * 1 * 2 = 2
- The score of node 3 is: 4 = 4
- The score of node 4 is: 4 = 4
The highest score is 4, and three nodes (node 1, node 3, and node 4) have the highest score.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="example-2" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2049.Count%20Nodes%20With%20the%20Highest%20Score/images/example-2.png" style="width: 95px; height: 143px;" />
<pre>
<strong>Input:</strong> parents = [-1,2,0]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
- The score of node 0 is: 2 = 2
- The score of node 1 is: 2 = 2
- The score of node 2 is: 1 * 1 = 1
The highest score is 2, and two nodes (node 0 and node 1) have the highest score.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == parents.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>parents[0] == -1</code></li>
	<li><code>0 &lt;= parents[i] &lt;= n - 1</code> for <code>i != 0</code></li>
	<li><code>parents</code> represents a valid binary tree.</li>
</ul>

## Solutions

### Solution 1: DFS

First, we construct a graph $g$ based on the given parent array `parents`, where $g[i]$ represents all child nodes of node $i$. We define a variable $ans$ to represent the number of nodes with the highest score, and a variable $mx$ to represent the highest score.

Then, we design a function `dfs(i, fa)` to calculate the score of node $i$ and return the number of nodes in the subtree rooted at node $i$.

The calculation process of the function `dfs(i, fa)` is as follows:

We first initialize a variable $cnt = 1$, representing the number of nodes in the subtree rooted at node $i$; a variable $score = 1$, representing the initial score of node $i$.

Next, we traverse all child nodes $j$ of node $i$. If $j$ is not the parent node $fa$ of node $i$, then we recursively call `dfs(j, i)`, and multiply the return value into $score$, and add the return value to $cnt$.

After traversing the child nodes, if $n - cnt > 0$, then we multiply $n - cnt$ into $score$.

Then, we check whether $mx$ is less than $score$. If it is less, then we update $mx$ to $score$, and update $ans$ to $1$; if it is equal, then we update $ans$ to $ans + 1$.

Finally, we return $cnt$.

In the end, we call `dfs(0, -1)` and return $ans$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes.

<!-- tabs:start -->

```python
class Solution:
    def countHighestScoreNodes(self, parents: List[int]) -> int:
        def dfs(i: int, fa: int):
            cnt = score = 1
            for j in g[i]:
                if j != fa:
                    t = dfs(j, i)
                    score *= t
                    cnt += t
            if n - cnt:
                score *= n - cnt
            nonlocal ans, mx
            if mx < score:
                mx = score
                ans = 1
            elif mx == score:
                ans += 1
            return cnt

        n = len(parents)
        g = [[] for _ in range(n)]
        for i in range(1, n):
            g[parents[i]].append(i)
        ans = mx = 0
        dfs(0, -1)
        return ans
```

```java
class Solution {
    private List<Integer>[] g;
    private int ans;
    private long mx;
    private int n;

    public int countHighestScoreNodes(int[] parents) {
        n = parents.length;
        g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 1; i < n; ++i) {
            g[parents[i]].add(i);
        }
        dfs(0, -1);
        return ans;
    }

    private int dfs(int i, int fa) {
        int cnt = 1;
        long score = 1;
        for (int j : g[i]) {
            if (j != fa) {
                int t = dfs(j, i);
                cnt += t;
                score *= t;
            }
        }
        if (n - cnt > 0) {
            score *= n - cnt;
        }
        if (mx < score) {
            mx = score;
            ans = 1;
        } else if (mx == score) {
            ++ans;
        }
        return cnt;
    }
}
```

```cpp
class Solution {
public:
    int countHighestScoreNodes(vector<int>& parents) {
        int n = parents.size();
        vector<int> g[n];
        for (int i = 1; i < n; ++i) {
            g[parents[i]].push_back(i);
        }
        int ans = 0;
        long long mx = 0;
        function<int(int, int)> dfs = [&](int i, int fa) {
            long long score = 1;
            int cnt = 1;
            for (int j : g[i]) {
                if (j != fa) {
                    int t = dfs(j, i);
                    cnt += t;
                    score *= t;
                }
            }
            if (n - cnt) {
                score *= n - cnt;
            }
            if (mx < score) {
                mx = score;
                ans = 1;
            } else if (mx == score) {
                ++ans;
            }
            return cnt;
        };
        dfs(0, -1);
        return ans;
    }
};
```

```go
func countHighestScoreNodes(parents []int) (ans int) {
	n := len(parents)
	g := make([][]int, n)
	for i := 1; i < n; i++ {
		g[parents[i]] = append(g[parents[i]], i)
	}
	mx := 0
	var dfs func(i, fa int) int
	dfs = func(i, fa int) int {
		cnt, score := 1, 1
		for _, j := range g[i] {
			if j != fa {
				t := dfs(j, i)
				cnt += t
				score *= t
			}
		}
		if n-cnt > 0 {
			score *= n - cnt
		}
		if mx < score {
			mx = score
			ans = 1
		} else if mx == score {
			ans++
		}
		return cnt
	}
	dfs(0, -1)
	return
}
```

```ts
function countHighestScoreNodes(parents: number[]): number {
    const n = parents.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (let i = 1; i < n; i++) {
        g[parents[i]].push(i);
    }
    let [ans, mx] = [0, 0];
    const dfs = (i: number, fa: number): number => {
        let [cnt, score] = [1, 1];
        for (const j of g[i]) {
            if (j !== fa) {
                const t = dfs(j, i);
                cnt += t;
                score *= t;
            }
        }
        if (n - cnt) {
            score *= n - cnt;
        }
        if (mx < score) {
            mx = score;
            ans = 1;
        } else if (mx === score) {
            ans++;
        }
        return cnt;
    };
    dfs(0, -1);
    return ans;
}
```

```cs
public class Solution {
    private List<int>[] g;
    private int ans;
    private long mx;
    private int n;

    public int CountHighestScoreNodes(int[] parents) {
        n = parents.Length;
        g = new List<int>[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new List<int>();
        }
        for (int i = 1; i < n; ++i) {
            g[parents[i]].Add(i);
        }

        Dfs(0, -1);
        return ans;
    }

    private int Dfs(int i, int fa) {
        int cnt = 1;
        long score = 1;

        foreach (int j in g[i]) {
            if (j != fa) {
                int t = Dfs(j, i);
                cnt += t;
                score *= t;
            }
        }

        if (n - cnt > 0) {
            score *= n - cnt;
        }

        if (mx < score) {
            mx = score;
            ans = 1;
        } else if (mx == score) {
            ++ans;
        }

        return cnt;
    }
}
```

<!-- tabs:end -->

<!-- end -->
