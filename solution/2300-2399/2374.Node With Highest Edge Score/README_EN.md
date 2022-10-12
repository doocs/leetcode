# [2374. Node With Highest Edge Score](https://leetcode.com/problems/node-with-highest-edge-score)

[中文文档](/solution/2300-2399/2374.Node%20With%20Highest%20Edge%20Score/README.md)

## Description

<p>You are given a directed graph with <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>, where each node has <strong>exactly one</strong> outgoing edge.</p>

<p>The graph is represented by a given <strong>0-indexed</strong> integer array <code>edges</code> of length <code>n</code>, where <code>edges[i]</code> indicates that there is a <strong>directed</strong> edge from node <code>i</code> to node <code>edges[i]</code>.</p>

<p>The <strong>edge score</strong> of a node <code>i</code> is defined as the sum of the <strong>labels</strong> of all the nodes that have an edge pointing to <code>i</code>.</p>

<p>Return <em>the node with the highest <strong>edge score</strong></em>. If multiple nodes have the same <strong>edge score</strong>, return the node with the <strong>smallest</strong> index.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2374.Node%20With%20Highest%20Edge%20Score/images/image-20220620195403-1.png" style="width: 450px; height: 260px;" />
<pre>
<strong>Input:</strong> edges = [1,0,0,0,0,7,7,5]
<strong>Output:</strong> 7
<strong>Explanation:</strong>
- The nodes 1, 2, 3 and 4 have an edge pointing to node 0. The edge score of node 0 is 1 + 2 + 3 + 4 = 10.
- The node 0 has an edge pointing to node 1. The edge score of node 1 is 0.
- The node 7 has an edge pointing to node 5. The edge score of node 5 is 7.
- The nodes 5 and 6 have an edge pointing to node 7. The edge score of node 7 is 5 + 6 = 11.
Node 7 has the highest edge score so return 7.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2374.Node%20With%20Highest%20Edge%20Score/images/image-20220620200212-3.png" style="width: 150px; height: 155px;" />
<pre>
<strong>Input:</strong> edges = [2,0,0,2]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
- The nodes 1 and 2 have an edge pointing to node 0. The edge score of node 0 is 1 + 2 = 3.
- The nodes 0 and 3 have an edge pointing to node 2. The edge score of node 2 is 0 + 3 = 3.
Nodes 0 and 2 both have an edge score of 3. Since node 0 has a smaller index, we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == edges.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges[i] &lt; n</code></li>
	<li><code>edges[i] != i</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def edgeScore(self, edges: List[int]) -> int:
        cnt = Counter()
        for i, v in enumerate(edges):
            cnt[v] += i
        ans = 0
        for i in range(len(edges)):
            if cnt[ans] < cnt[i]:
                ans = i
        return ans
```

### **Java**

```java
class Solution {
    public int edgeScore(int[] edges) {
        int n = edges.length;
        long[] cnt = new long[n];
        for (int i = 0; i < n; ++i) {
            cnt[edges[i]] += i;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (cnt[ans] < cnt[i]) {
                ans = i;
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
    int edgeScore(vector<int>& edges) {
        int n = edges.size();
        vector<long long> cnt(n);
        for (int i = 0; i < n; ++i) {
            cnt[edges[i]] += i;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (cnt[ans] < cnt[i]) {
                ans = i;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func edgeScore(edges []int) int {
	n := len(edges)
	cnt := make([]int, n)
	for i, v := range edges {
		cnt[v] += i
	}
	ans := 0
	for i, v := range cnt {
		if cnt[ans] < v {
			ans = i
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function edgeScore(edges: number[]): number {
    const n = edges.length;
    const sum = new Array(n).fill(0);
    for (let i = 0; i < n; i++) {
        sum[edges[i]] += i;
    }
    let res = 0;
    for (let i = 0; i < n; i++) {
        if (sum[res] < sum[i]) {
            res = i;
        }
    }
    return res;
}
```

### **...**

```


```

<!-- tabs:end -->
