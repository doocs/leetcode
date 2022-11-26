# [2242. Maximum Score of a Node Sequence](https://leetcode.com/problems/maximum-score-of-a-node-sequence)

[中文文档](/solution/2200-2299/2242.Maximum%20Score%20of%20a%20Node%20Sequence/README.md)

## Description

<p>There is an <strong>undirected</strong> graph with <code>n</code> nodes, numbered from <code>0</code> to <code>n - 1</code>.</p>

<p>You are given a <strong>0-indexed</strong> integer array <code>scores</code> of length <code>n</code> where <code>scores[i]</code> denotes the score of node <code>i</code>. You are also given a 2D integer array <code>edges</code> where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> denotes that there exists an <strong>undirected</strong> edge connecting nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code>.</p>

<p>A node sequence is <b>valid</b> if it meets the following conditions:</p>

<ul>
	<li>There is an edge connecting every pair of <strong>adjacent</strong> nodes in the sequence.</li>
	<li>No node appears more than once in the sequence.</li>
</ul>

<p>The score of a node sequence is defined as the <strong>sum</strong> of the scores of the nodes in the sequence.</p>

<p>Return <em>the <strong>maximum score</strong> of a valid node sequence with a length of </em><code>4</code><em>. </em>If no such sequence exists, return<em> </em><code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2242.Maximum%20Score%20of%20a%20Node%20Sequence/images/ex1new3.png" style="width: 290px; height: 215px;" />
<pre>
<strong>Input:</strong> scores = [5,2,9,8,4], edges = [[0,1],[1,2],[2,3],[0,2],[1,3],[2,4]]
<strong>Output:</strong> 24
<strong>Explanation:</strong> The figure above shows the graph and the chosen node sequence [0,1,2,3].
The score of the node sequence is 5 + 2 + 9 + 8 = 24.
It can be shown that no other node sequence has a score of more than 24.
Note that the sequences [3,1,2,0] and [1,0,2,3] are also valid and have a score of 24.
The sequence [0,3,2,4] is not valid since no edge connects nodes 0 and 3.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2242.Maximum%20Score%20of%20a%20Node%20Sequence/images/ex2.png" style="width: 333px; height: 151px;" />
<pre>
<strong>Input:</strong> scores = [9,20,6,4,11,12], edges = [[0,3],[5,3],[2,4],[1,3]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> The figure above shows the graph.
There are no valid node sequences of length 4, so we return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == scores.length</code></li>
	<li><code>4 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= scores[i] &lt;= 10<sup>8</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>There are no duplicate edges.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumScore(self, scores: List[int], edges: List[List[int]]) -> int:
        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        for k in g.keys():
            g[k] = nlargest(3, g[k], key=lambda x: scores[x])
        ans = -1
        for a, b in edges:
            for c in g[a]:
                for d in g[b]:
                    if b != c != d != a:
                        t = scores[a] + scores[b] + scores[c] + scores[d]
                        ans = max(ans, t)
        return ans
```

### **Java**

```java
class Solution {
    public int maximumScore(int[] scores, int[][] edges) {
        int n = scores.length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        for (int i = 0; i < n; ++i) {
            g[i].sort((a, b) -> scores[b] - scores[a]);
            g[i] = g[i].subList(0, Math.min(3, g[i].size()));
        }
        int ans = -1;
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            for (int c : g[a]) {
                for (int d : g[b]) {
                    if (c != b && c != d && a != d) {
                        int t = scores[a] + scores[b] + scores[c] + scores[d];
                        ans = Math.max(ans, t);
                    }
                }
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
