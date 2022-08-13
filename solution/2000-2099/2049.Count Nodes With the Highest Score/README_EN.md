# [2049. Count Nodes With the Highest Score](https://leetcode.com/problems/count-nodes-with-the-highest-score)

[中文文档](/solution/2000-2099/2049.Count%20Nodes%20With%20the%20Highest%20Score/README.md)

## Description

<p>There is a <strong>binary</strong> tree rooted at <code>0</code> consisting of <code>n</code> nodes. The nodes are labeled from <code>0</code> to <code>n - 1</code>. You are given a <strong>0-indexed</strong> integer array <code>parents</code> representing the tree, where <code>parents[i]</code> is the parent of node <code>i</code>. Since node <code>0</code> is the root, <code>parents[0] == -1</code>.</p>

<p>Each node has a <strong>score</strong>. To find the score of a node, consider if the node and the edges connected to it were <strong>removed</strong>. The tree would become one or more <strong>non-empty</strong> subtrees. The <strong>size</strong> of a subtree is the number of the nodes in it. The <strong>score</strong> of the node is the <strong>product of the sizes</strong> of all those subtrees.</p>

<p>Return <em>the <strong>number</strong> of nodes that have the <strong>highest score</strong></em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
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

<p><strong>Example 2:</strong></p>
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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countHighestScoreNodes(self, parents: List[int]) -> int:
        n, max_score, ans = len(parents), 0, 0
        g = [[] for _ in range(n)]
        for i in range(1, n):
            g[parents[i]].append(i)

        def dfs(cur: int) -> int:
            nonlocal max_score, ans
            size, score = 1, 1
            for c in g[cur]:
                s = dfs(c)
                size += s
                score *= s
            if cur > 0:
                score *= n - size
            if score > max_score:
                max_score = score
                ans = 1
            elif score == max_score:
                ans += 1
            return size

        dfs(0)
        return ans
```

### **Java**

```java
class Solution {

    private int n;
    private long maxScore;
    private int ans;
    private List<List<Integer>> graph;

    public int countHighestScoreNodes(int[] parents) {
        n = parents.length;
        maxScore = 0;
        ans = 0;
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            graph.get(parents[i]).add(i);
        }
        dfs(0);
        return ans;
    }

    private int dfs(int cur) {
        int size = 1;
        long score = 1;
        for (int child : graph.get(cur)) {
            int s = dfs(child);
            size += s;
            score *= s;
        }
        if (cur > 0) {
            score *= n - size;
        }
        if (score > maxScore) {
            maxScore = score;
            ans = 1;
        } else if (score == maxScore) {
            ans++;
        }
        return size;
    }
}
```

### **TypeScript**

```ts
function countHighestScoreNodes(parents: number[]): number {
    const n = parents.length;
    let edge = Array.from({ length: n }, (v, i) => []);
    for (let i = 0; i < n; i++) {
        const parent = parents[i];
        if (parent != -1) {
            edge[parent].push(i);
        }
    }

    let ans = 0;
    let max = 0;
    function dfs(idx: number): number {
        let size = 1,
            score = 1;
        for (let i = 0; i < edge[idx].length; i++) {
            const child = edge[idx][i];
            let childSize = dfs(child);
            size += childSize;
            score *= childSize;
        }
        if (idx > 0) {
            score *= n - size;
        }
        if (score > max) {
            max = score;
            ans = 1;
        } else if (score == max) {
            ans++;
        }
        return size;
    }
    dfs(0);
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int ans;
    long long maxScore;
    int n;

    int countHighestScoreNodes(vector<int>& parents) {
        ans = 0;
        maxScore = 0;
        n = parents.size();
        unordered_map<int, vector<int>> g;
        for (int i = 1; i < n; ++i) g[parents[i]].push_back(i);
        dfs(0, g);
        return ans;
    }

    int dfs(int u, unordered_map<int, vector<int>>& g) {
        int size = 1;
        long long score = 1;
        for (int v : g[u]) {
            int t = dfs(v, g);
            size += t;
            score *= t;
        }
        if (u > 0) score *= (n - size);
        if (score > maxScore) {
            maxScore = score;
            ans = 1;
        } else if (score == maxScore)
            ++ans;
        return size;
    }
};
```

### **Go**

```go
func countHighestScoreNodes(parents []int) int {
	n := len(parents)
	g := make([][]int, n)
	for i := 1; i < n; i++ {
		p := parents[i]
		g[p] = append(g[p], i)
	}
	maxScore, ans := 0, 0
	var dfs func(int) int
	dfs = func(u int) int {
		size, score := 1, 1
		for _, v := range g[u] {
			t := dfs(v)
			size += t
			score *= t
		}
		if u > 0 {
			score *= n - size
		}
		if score > maxScore {
			maxScore, ans = score, 1
		} else if score == maxScore {
			ans++
		}
		return size
	}
	dfs(0)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
