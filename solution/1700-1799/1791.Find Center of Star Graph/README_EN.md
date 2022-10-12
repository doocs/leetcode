# [1791. Find Center of Star Graph](https://leetcode.com/problems/find-center-of-star-graph)

[中文文档](/solution/1700-1799/1791.Find%20Center%20of%20Star%20Graph/README.md)

## Description

<p>There is an undirected <strong>star</strong> graph consisting of <code>n</code> nodes labeled from <code>1</code> to <code>n</code>. A star graph is a graph where there is one <strong>center</strong> node and <strong>exactly</strong> <code>n - 1</code> edges that connect the center node with every other node.</p>

<p>You are given a 2D integer array <code>edges</code> where each <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates that there is an edge between the nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code>. Return the center of the given star graph.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1791.Find%20Center%20of%20Star%20Graph/images/star_graph.png" style="width: 331px; height: 321px;" />
<pre>
<strong>Input:</strong> edges = [[1,2],[2,3],[4,2]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> As shown in the figure above, node 2 is connected to every other node, so 2 is the center.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> edges = [[1,2],[5,1],[1,3],[1,4]]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>1 &lt;= u<sub>i,</sub> v<sub>i</sub> &lt;= n</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li>The given <code>edges</code> represent a valid star graph.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findCenter(self, edges: List[List[int]]) -> int:
        return edges[0][0] if edges[0][0] in edges[1] else edges[0][1]
```

### **Java**

```java
class Solution {
    public int findCenter(int[][] edges) {
        int a = edges[0][0], b = edges[0][1];
        int c = edges[1][0], d = edges[1][1];
        return a == c || a == d ? a : b;
    }
}
```

### **TypeScript**

```ts
function findCenter(edges: number[][]): number {
    for (let num of edges[0]) {
        if (edges[1].includes(num)) {
            return num;
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findCenter(vector<vector<int>>& edges) {
        int a = edges[0][0], b = edges[0][1];
        int c = edges[1][0], d = edges[1][1];
        return a == c || a == d ? a : b;
    }
};
```

### **Go**

```go
func findCenter(edges [][]int) int {
	a, b := edges[0][0], edges[0][1]
	c, d := edges[1][0], edges[1][1]
	if a == c || a == d {
		return a
	}
	return b
}
```

### **Rust**

```rust
impl Solution {
    pub fn find_center(edges: Vec<Vec<i32>>) -> i32 {
        if edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1] {
            return edges[0][0];
        }
        edges[0][1]
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} edges
 * @return {number}
 */
var findCenter = function (edges) {
    const [a, b] = edges[0];
    const [c, d] = edges[1];
    return a == c || a == d ? a : b;
};
```

### **...**

```

```

<!-- tabs:end -->
