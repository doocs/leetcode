# [133. Clone Graph](https://leetcode.com/problems/clone-graph)

[中文文档](/solution/0100-0199/0133.Clone%20Graph/README.md)

## Description

<p>Given a reference of a node in a <strong><a href="https://en.wikipedia.org/wiki/Connectivity_(graph_theory)#Connected_graph" target="_blank">connected</a></strong> undirected graph.</p>

<p>Return a <a href="https://en.wikipedia.org/wiki/Object_copying#Deep_copy" target="_blank"><strong>deep copy</strong></a> (clone) of the graph.</p>

<p>Each node in the graph contains a value (<code>int</code>) and a list (<code>List[Node]</code>) of its neighbors.</p>

<pre>
class Node {
    public int val;
    public List&lt;Node&gt; neighbors;
}
</pre>

<p>&nbsp;</p>

<p><strong>Test case format:</strong></p>

<p>For simplicity, each node&#39;s value is the same as the node&#39;s index (1-indexed). For example, the first node with <code>val == 1</code>, the second node with <code>val == 2</code>, and so on. The graph is represented in the test case using an adjacency list.</p>

<p><b>An adjacency list</b> is a collection of unordered <b>lists</b> used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.</p>

<p>The given node will always be the first node with <code>val = 1</code>. You must return the <strong>copy of the given node</strong> as a reference to the cloned graph.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0133.Clone%20Graph/images/133_clone_graph_question.png" style="width: 454px; height: 500px;" />
<pre>
<strong>Input:</strong> adjList = [[2,4],[1,3],[2,4],[1,3]]
<strong>Output:</strong> [[2,4],[1,3],[2,4],[1,3]]
<strong>Explanation:</strong> There are 4 nodes in the graph.
1st node (val = 1)&#39;s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)&#39;s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)&#39;s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)&#39;s neighbors are 1st node (val = 1) and 3rd node (val = 3).
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0133.Clone%20Graph/images/graph.png" style="width: 163px; height: 148px;" />
<pre>
<strong>Input:</strong> adjList = [[]]
<strong>Output:</strong> [[]]
<strong>Explanation:</strong> Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> adjList = []
<strong>Output:</strong> []
<strong>Explanation:</strong> This an empty graph, it does not have any nodes.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the graph is in the range <code>[0, 100]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 100</code></li>
	<li><code>Node.val</code> is unique for each node.</li>
	<li>There are no repeated edges and no self-loops in the graph.</li>
	<li>The Graph is connected and all nodes can be visited starting from the given node.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, val = 0, neighbors = None):
        self.val = val
        self.neighbors = neighbors if neighbors is not None else []
"""


class Solution:
    def cloneGraph(self, node: 'Node') -> 'Node':
        visited = defaultdict()

        def clone(node):
            if node is None:
                return None
            if node in visited:
                return visited[node]
            c = Node(node.val)
            visited[node] = c
            for e in node.neighbors:
                c.neighbors.append(clone(e))
            return c

        return clone(node)
```

### **Java**

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    private Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        Node clone = new Node(node.val);
        visited.put(node, clone);
        for (Node e : node.neighbors) {
            clone.neighbors.add(cloneGraph(e));
        }
        return clone;
    }
}
```

### **TypeScript**

```ts
/**
 * Definition for Node.
 * class Node {
 *     val: number
 *     neighbors: Node[]
 *     constructor(val?: number, neighbors?: Node[]) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.neighbors = (neighbors===undefined ? [] : neighbors)
 *     }
 * }
 */

function cloneGraph(node: Node | null): Node | null {
    if (node == null) return null;

    const visited = new Map();
    visited.set(node, new Node(node.val));
    const queue = [node];
    while (queue.length) {
        const cur = queue.shift();
        for (let neighbor of cur.neighbors || []) {
            if (!visited.has(neighbor)) {
                queue.push(neighbor);
                const newNeighbor = new Node(neighbor.val, []);
                visited.set(neighbor, newNeighbor);
            }
            const newNode = visited.get(cur);
            newNode.neighbors.push(visited.get(neighbor));
        }
    }
    return visited.get(node);
}
```

### **C++**

```cpp
/*
// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> neighbors;
    Node() {
        val = 0;
        neighbors = vector<Node*>();
    }
    Node(int _val) {
        val = _val;
        neighbors = vector<Node*>();
    }
    Node(int _val, vector<Node*> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
*/

class Solution {
public:
    unordered_map<Node*, Node*> visited;

    Node* cloneGraph(Node* node) {
        if (!node) return nullptr;
        if (visited.count(node)) return visited[node];
        Node* clone = new Node(node->val);
        visited[node] = clone;
        for (auto& e : node->neighbors)
            clone->neighbors.push_back(cloneGraph(e));
        return clone;
    }
};
```

### **Go**

```go
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Neighbors []*Node
 * }
 */

func cloneGraph(node *Node) *Node {
	visited := map[*Node]*Node{}
	var clone func(node *Node) *Node
	clone = func(node *Node) *Node {
		if node == nil {
			return nil
		}
		if _, ok := visited[node]; ok {
			return visited[node]
		}
		c := &Node{node.Val, []*Node{}}
		visited[node] = c
		for _, e := range node.Neighbors {
			c.Neighbors = append(c.Neighbors, clone(e))
		}
		return c
	}

	return clone(node)
}
```

### **...**

```

```

<!-- tabs:end -->
