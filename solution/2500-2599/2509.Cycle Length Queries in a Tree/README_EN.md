# [2509. Cycle Length Queries in a Tree](https://leetcode.com/problems/cycle-length-queries-in-a-tree)

[中文文档](/solution/2500-2599/2509.Cycle%20Length%20Queries%20in%20a%20Tree/README.md)

## Description

<p>You are given an integer <code>n</code>. There is a <strong>complete binary tree</strong> with <code>2<sup>n</sup> - 1</code> nodes. The root of that tree is the node with the value <code>1</code>, and every node with a value <code>val</code> in the range <code>[1, 2<sup>n - 1</sup> - 1]</code> has two children where:</p>

<ul>
	<li>The left node has the value <code>2 * val</code>, and</li>
	<li>The right node has the value <code>2 * val + 1</code>.</li>
</ul>

<p>You are also given a 2D integer array <code>queries</code> of length <code>m</code>, where <code>queries[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>. For each query, solve the following problem:</p>

<ol>
	<li>Add an edge between the nodes with values <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code>.</li>
	<li>Find the length of the cycle in the graph.</li>
	<li>Remove the added edge between nodes with values <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code>.</li>
</ol>

<p><strong>Note</strong> that:</p>

<ul>
	<li>A <strong>cycle</strong> is a path that starts and ends at the same node, and each edge in the path is visited only once.</li>
	<li>The length of a cycle is the number of edges visited in the cycle.</li>
	<li>There could be multiple edges between two nodes in the tree after adding the edge of the query.</li>
</ul>

<p>Return <em>an array </em><code>answer</code><em> of length </em><code>m</code><em> where</em> <code>answer[i]</code> <em>is the answer to the</em> <code>i<sup>th</sup></code> <em>query.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2509.Cycle%20Length%20Queries%20in%20a%20Tree/images/bexample1.png" style="width: 647px; height: 128px;" />
<pre>
<strong>Input:</strong> n = 3, queries = [[5,3],[4,7],[2,3]]
<strong>Output:</strong> [4,5,3]
<strong>Explanation:</strong> The diagrams above show the tree of 2<sup>3</sup> - 1 nodes. Nodes colored in red describe the nodes in the cycle after adding the edge.
- After adding the edge between nodes 3 and 5, the graph contains a cycle of nodes [5,2,1,3]. Thus answer to the first query is 4. We delete the added edge and process the next query.
- After adding the edge between nodes 4 and 7, the graph contains a cycle of nodes [4,2,1,3,7]. Thus answer to the second query is 5. We delete the added edge and process the next query.
- After adding the edge between nodes 2 and 3, the graph contains a cycle of nodes [2,1,3]. Thus answer to the third query is 3. We delete the added edge.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2509.Cycle%20Length%20Queries%20in%20a%20Tree/images/aexample2.png" style="width: 146px; height: 71px;" />
<pre>
<strong>Input:</strong> n = 2, queries = [[1,2]]
<strong>Output:</strong> [2]
<strong>Explanation:</strong> The diagram above shows the tree of 2<sup>2</sup> - 1 nodes. Nodes colored in red describe the nodes in the cycle after adding the edge.
- After adding the edge between nodes 1 and 2, the graph contains a cycle of nodes [2,1]. Thus answer for the first query is 2. We delete the added edge.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 30</code></li>
	<li><code>m == queries.length</code></li>
	<li><code>1 &lt;= m &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>1 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= 2<sup>n</sup> - 1</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def cycleLengthQueries(self, n: int, queries: List[List[int]]) -> List[int]:
        ans = []
        for a, b in queries:
            t = 1
            while a != b:
                if a > b:
                    a >>= 1
                else:
                    b >>= 1
                t += 1
            ans.append(t)
        return ans
```

### **Java**

```java
class Solution {
    public int[] cycleLengthQueries(int n, int[][] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int a = queries[i][0], b = queries[i][1];
            int t = 1;
            while (a != b) {
                if (a > b) {
                    a >>= 1;
                } else {
                    b >>= 1;
                }
                ++t;
            }
            ans[i] = t;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> cycleLengthQueries(int n, vector<vector<int>>& queries) {
        vector<int> ans;
        for (auto& q : queries) {
            int a = q[0], b = q[1];
            int t = 1;
            while (a != b) {
                if (a > b) {
                    a >>= 1;
                } else {
                    b >>= 1;
                }
                ++t;
            }
            ans.emplace_back(t);
        }
        return ans;
    }
};
```

### **Go**

```go
func cycleLengthQueries(n int, queries [][]int) []int {
	ans := []int{}
	for _, q := range queries {
		a, b := q[0], q[1]
		t := 1
		for a != b {
			if a > b {
				a >>= 1
			} else {
				b >>= 1
			}
			t++
		}
		ans = append(ans, t)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
