# [684. Redundant Connection](https://leetcode.com/problems/redundant-connection)

[中文文档](/solution/0600-0699/0684.Redundant%20Connection/README.md)

## Description

<p>

In this problem, a tree is an <b>undirected</b> graph that is connected and has no cycles.

</p><p>

The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

</p><p>

The resulting graph is given as a 2D-array of <code>edges</code>. Each element of <code>edges</code> is a pair <code>[u, v]</code> with <code>u < v</code>, that represents an <b>undirected</b> edge connecting nodes <code>u</code> and <code>v</code>.

</p><p>

Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge <code>[u, v]</code> should be in the same format, with <code>u < v</code>.

</p><p><b>Example 1:</b><br />

<pre>

<b>Input:</b> [[1,2], [1,3], [2,3]]

<b>Output:</b> [2,3]

<b>Explanation:</b> The given undirected graph will be like this:

  1

 / \

2 - 3

</pre>

</p>

<p><b>Example 2:</b><br />

<pre>

<b>Input:</b> [[1,2], [2,3], [3,4], [1,4], [1,5]]

<b>Output:</b> [1,4]

<b>Explanation:</b> The given undirected graph will be like this:

5 - 1 - 2

    |   |

    4 - 3

</pre>

</p>

<p><b>Note:</b><br />

<li>The size of the input 2D-array will be between 3 and 1000.</li>

<li>Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.</li>

</p>

<br />

<p>

<b><font color="red">Update (2017-09-26):</font></b><br>

We have overhauled the problem description + test cases and specified clearly the graph is an <b><i>undirected</i></b> graph. For the <b><i>directed</i></b> graph follow up please see <b><a href="https://leetcode.com/problems/redundant-connection-ii/description/">Redundant Connection II</a></b>). We apologize for any inconvenience caused.

</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findRedundantConnection(self, edges: List[List[int]]) -> List[int]:
        p = list(range(1010))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]
        
        for a, b in edges:
            if find(a) == find(b):
                return [a, b]
            p[find(a)] = find(b)
        return []
```

### **Java**

```java
class Solution {
    private int[] p;

    public int[] findRedundantConnection(int[][] edges) {
        p = new int[1010];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        for (int[] e : edges) {
            if (find(e[0]) == find(e[1])) {
                return e;
            }
            p[find(e[0])] = find(e[1]);
        }
        return null;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> p;

    vector<int> findRedundantConnection(vector<vector<int>>& edges) {
        p.resize(1010);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        for (auto e : edges)
        {
            if (find(e[0]) == find(e[1])) return e;
            p[find(e[0])] = find(e[1]);
        }
        return {};
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
var p []int

func findRedundantConnection(edges [][]int) []int {
	p = make([]int, 1010)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	for _, e := range edges {
		if find(e[0]) == find(e[1]) {
			return e
		}
		p[find(e[0])] = find(e[1])
	}
	return nil
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}
```

### **...**

```

```

<!-- tabs:end -->
