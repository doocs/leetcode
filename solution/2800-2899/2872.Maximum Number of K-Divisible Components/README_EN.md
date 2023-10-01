# [2872. Maximum Number of K-Divisible Components](https://leetcode.com/problems/maximum-number-of-k-divisible-components/)

[中文文档](/solution/2800-2899/2872.Maximum%20Number%20of%20K-Divisible%20Components/README.md)

## Description

<p>There is an undirected tree with <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>. You are given the integer <code>n</code> and a 2D integer array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the tree.</p>

<p>You are also given a <strong>0-indexed</strong> integer array <code>values</code> of length <code>n</code>, where <code>values[i]</code> is the <strong>value</strong> associated with the <code>i<sup>th</sup></code> node, and an integer <code>k</code>.</p>

<p>A <strong>valid split</strong> of the tree is obtained by removing any set of edges, possibly empty, from the tree such that the resulting components all have values that are divisible by <code>k</code>, where the <strong>value of a connected component</strong> is the sum of the values of its nodes.</p>

<p>Return <em>the <strong>maximum number of components</strong> in any valid split</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="./images/example1.jpg" style="width: 1024px; height: 453px;">
<pre><strong>Input:</strong> n = 5, edges = [[0,2],[1,2],[1,3],[2,4]], values = [1,8,1,4,4], k = 6
<strong>Output:</strong> 2
<strong>Explanation:</strong> We remove the edge connecting node 1 with 2. The resulting split is valid because:
- The value of the component containing nodes 1 and 3 is values[1] + values[3] = 12.
- The value of the component containing nodes 0, 2, and 4 is values[0] + values[2] + values[4] = 6.
It can be shown that no other valid split has more than 2 connected components.</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="./images/example2.jpg" style="width: 999px; height: 338px;">
<pre><strong>Input:</strong> n = 7, edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]], values = [3,0,6,1,5,2,1], k = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> We remove the edge connecting node 0 with 2, and the edge connecting node 0 with 1. The resulting split is valid because:
- The value of the component containing node 0 is values[0] = 3.
- The value of the component containing nodes 2, 5, and 6 is values[2] + values[5] + values[6] = 9.
- The value of the component containing nodes 1, 3, and 4 is values[1] + values[3] + values[4] = 6.
It can be shown that no other valid split has more than 3 connected components.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>values.length == n</code></li>
	<li><code>0 &lt;= values[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
	<li>Sum of <code>values</code> is divisible by <code>k</code>.</li>
	<li>The input is generated such that <code>edges</code> represents a valid tree.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java
class Solution {
    int n, k;
    int[] values;
    int[] dfs(int curr, int parent, List<List<Integer>> adj) {
        int[] res = new int[] {0, values[curr] % k};
        for (int next : adj.get(curr)) {
            if (next == parent) {
                continue;
            }
            int[] update = dfs(next, curr, adj);
            res[0] += update[0];
            res[1] += update[1];
        }
        res[1] %= k;
        res[0] += res[1] == 0 ? 1 : 0;
        return res;
    }
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        this.n = n;
        this.k = k;
        this.values = values;
        List<List<Integer>> adj = new ArrayList<>();
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        int[] ans = dfs(0, -1, adj);
        return ans[1] == 0 ? ans[0] : 0;
    }
}
```

### **C++**

```cpp

```

### **Go**

```go

```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
