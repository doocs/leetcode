# [1743. Restore the Array From Adjacent Pairs](https://leetcode.com/problems/restore-the-array-from-adjacent-pairs)

[中文文档](/solution/1700-1799/1743.Restore%20the%20Array%20From%20Adjacent%20Pairs/README.md)

## Description

<p>There is an integer array <code>nums</code> that consists of <code>n</code> <strong>unique </strong>elements, but you have forgotten it. However, you do remember every pair of adjacent elements in <code>nums</code>.</p>

<p>You are given a 2D integer array <code>adjacentPairs</code> of size <code>n - 1</code> where each <code>adjacentPairs[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates that the elements <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> are adjacent in <code>nums</code>.</p>

<p>It is guaranteed that every adjacent pair of elements <code>nums[i]</code> and <code>nums[i+1]</code> will exist in <code>adjacentPairs</code>, either as <code>[nums[i], nums[i+1]]</code> or <code>[nums[i+1], nums[i]]</code>. The pairs can appear <strong>in any order</strong>.</p>

<p>Return <em>the original array </em><code>nums</code><em>. If there are multiple solutions, return <strong>any of them</strong></em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> adjacentPairs = [[2,1],[3,4],[3,2]]
<strong>Output:</strong> [1,2,3,4]
<strong>Explanation:</strong> This array has all its adjacent pairs in adjacentPairs.
Notice that adjacentPairs[i] may not be in left-to-right order.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> adjacentPairs = [[4,-2],[1,4],[-3,1]]
<strong>Output:</strong> [-2,4,1,-3]
<strong>Explanation:</strong> There can be negative numbers.
Another solution is [-3,1,4,-2], which would also be accepted.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> adjacentPairs = [[100000,-100000]]
<strong>Output:</strong> [100000,-100000]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>nums.length == n</code></li>
	<li><code>adjacentPairs.length == n - 1</code></li>
	<li><code>adjacentPairs[i].length == 2</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i], u<sub>i</sub>, v<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li>There exists some <code>nums</code> that has <code>adjacentPairs</code> as its pairs.</li>
</ul>


## Solutions

Traverse the graph from the point where the degree is one.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def restoreArray(self, adjacentPairs: List[List[int]]) -> List[int]:
        graph = collections.defaultdict(list)
        for pair in adjacentPairs:
            graph[pair[0]].append(pair[1])
            graph[pair[1]].append(pair[0])
        ans = []
        vis = set()

        def dfs(idx):
            if idx in vis:
                return
            vis.add(idx)
            ans.append(idx)
            for nxt in graph[idx]:
                dfs(nxt)

        start = -1
        for idx, adj in graph.items():
            if len(adj) == 1:
                start = idx
                break

        dfs(start)
        return ans
```

### **Java**

```java
class Solution {
    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] pair : adjacentPairs) {
            graph.computeIfAbsent(pair[0], k -> new ArrayList<>()).add(pair[1]);
            graph.computeIfAbsent(pair[1], k -> new ArrayList<>()).add(pair[0]);
        }
        List<Integer> ans = new ArrayList<>();
        Set<Integer> vis = new HashSet<>();
        int start = -1;
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            if (entry.getValue().size() == 1) {
                start = entry.getKey();
                break;
            }
        }
        dfs(graph, ans, vis, start);
        return ans.stream().mapToInt(Integer::valueOf).toArray();
    }

    private void dfs(Map<Integer, List<Integer>> graph, List<Integer> ans, Set<Integer> vis, int idx) {
        if (vis.contains(idx)) {
            return;
        }
        vis.add(idx);
        ans.add(idx);
        for (Integer next : graph.get(idx)) {
            dfs(graph, ans, vis, next);
        }
    }
}
```

### **Go**

```go
func restoreArray(adjacentPairs [][]int) []int {
	graph := make(map[int][]int)
	for _, pair := range adjacentPairs {
		graph[pair[0]] = append(graph[pair[0]], pair[1])
		graph[pair[1]] = append(graph[pair[1]], pair[0])
	}
	ans := make([]int, 0)
	vis := make(map[int]bool)
	var start int
	for idx, adj := range graph {
		if len(adj) == 1 {
			start = idx
			break
		}
	}
	dfs(graph, &ans, vis, start)
	return ans
}

func dfs(graph map[int][]int, ans *[]int, vis map[int]bool, idx int) {
	if vis[idx] {
		return
	}
	vis[idx] = true
	*ans = append(*ans, idx)
	for _, next := range graph[idx] {
		dfs(graph, ans, vis, next)
	}
}
```

### **...**

```

```

<!-- tabs:end -->
