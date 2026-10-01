---
comments: true
difficulty: Medium
tags:
    - Depth-First Search
    - Breadth-First Search
    - Graph
    - Hash Table
---

<!-- problem:start -->

# [133. Clone Graph](https://leetcode.com/problems/clone-graph)

[中文文档](/solution/0100-0199/0133.Clone%20Graph/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một tham chiếu đến một nút trong đồ thị vô hướng <strong><a href="https://en.wikipedia.org/wiki/Connectivity_(graph_theory)#Connected_graph" target="_blank">liên thông</a></strong>.</p>

<p>Hãy trả về một <a href="https://en.wikipedia.org/wiki/Object_copying#Deep_copy" target="_blank"><strong>bản sao sâu</strong></a> (clone) của đồ thị.</p>

<p>Mỗi nút trong đồ thị chứa một giá trị (<code>int</code>) và một danh sách (<code>List[Node]</code>) các nút kề.</p>

<pre>
class Node {
    public int val;
    public List&lt;Node&gt; neighbors;
}
</pre>

<p>&nbsp;</p>

<p><strong>Định dạng test case:</strong></p>

<p>Để đơn giản, giá trị của mỗi nút giống với chỉ số của nút đó (đánh chỉ số từ 1). Ví dụ, nút thứ nhất có <code>val == 1</code>, nút thứ hai có <code>val == 2</code>, v.v. Đồ thị được biểu diễn trong test case bằng một danh sách kề.</p>

<p><b>Danh sách kề</b> là một tập hợp các <b>danh sách</b> không có thứ tự được dùng để biểu diễn một đồ thị hữu hạn. Mỗi danh sách mô tả tập hợp các nút kề của một nút trong đồ thị.</p>

<p>Nút đã cho sẽ luôn là nút đầu tiên có <code>val = 1</code>. Bạn phải trả về <strong>bản sao của nút đã cho</strong> dưới dạng một tham chiếu đến đồ thị đã nhân bản.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0133.Clone%20Graph/images/133_clone_graph_question.png" style="width: 454px; height: 500px;" />
<pre>
<strong>Đầu vào:</strong> adjList = [[2,4],[1,3],[2,4],[1,3]]
<strong>Đầu ra:</strong> [[2,4],[1,3],[2,4],[1,3]]
<strong>Giải thích:</strong> Đồ thị có 4 nút.
Nút thứ 1 (val = 1) có các nút kề là nút thứ 2 (val = 2) và nút thứ 4 (val = 4).
Nút thứ 2 (val = 2) có các nút kề là nút thứ 1 (val = 1) và nút thứ 3 (val = 3).
Nút thứ 3 (val = 3) có các nút kề là nút thứ 2 (val = 2) và nút thứ 4 (val = 4).
Nút thứ 4 (val = 4) có các nút kề là nút thứ 1 (val = 1) và nút thứ 3 (val = 3).
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0133.Clone%20Graph/images/graph.png" style="width: 163px; height: 148px;" />
<pre>
<strong>Đầu vào:</strong> adjList = [[]]
<strong>Đầu ra:</strong> [[]]
<strong>Giải thích:</strong> Lưu ý rằng đầu vào chứa một danh sách rỗng. Đồ thị chỉ gồm một nút có val = 1 và nút đó không có nút kề.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> adjList = []
<strong>Đầu ra:</strong> []
<strong>Giải thích:</strong> Đây là một đồ thị rỗng. Đồ thị không có nút nào.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số lượng nút trong đồ thị nằm trong khoảng <code>[0, 100]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 100</code></li>
	<li><code>Node.val</code> là duy nhất đối với mỗi nút.</li>
	<li>Không có cạnh lặp lại và không có vòng lặp tự thân trong đồ thị.</li>
	<li>Đồ thị liên thông và có thể duyệt qua tất cả các nút bắt đầu từ nút đã cho.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Bảng băm + DFS

<!-- thinking:start -->

> **Tư duy**
>
> Sao chép sâu một đồ thị vô hướng liên thông; các giá trị là duy nhất và $n\le 100$. Chỉ sao chép các giá trị là chưa đủ: các con trỏ đến nút kề phải trỏ tới các nút mới, và các chu trình sẽ lặp vô hạn nếu chúng ta đệ quy một cách mù quáng.
>
> Một ánh xạ từ nút gốc sang bản sao giải quyết việc này. Khi thăm lần đầu, chúng ta tạo bản sao và đệ quy trên các nút kề; lần thăm sau trả về bản sao đã tồn tại, nhờ đó loại bỏ các chu trình.

<!-- thinking:end -->

Chúng ta sử dụng một bảng băm $\textit{g}$ để ghi lại mối tương ứng giữa mỗi nút trong đồ thị gốc và bản sao của nó, sau đó thực hiện tìm kiếm theo chiều sâu.

Chúng ta định nghĩa hàm $\text{dfs}(node)$, hàm này trả về bản sao của $\textit{node}$. Quy trình của $\text{dfs}(node)$ như sau:

- Nếu $\textit{node}$ là $\text{null}$, thì giá trị trả về của $\text{dfs}(node)$ là $\text{null}$.
- Nếu $\textit{node}$ nằm trong $\textit{g}$, thì giá trị trả về của $\text{dfs}(node)$ là $\textit{g}[node]$.
- Nếu không, chúng ta tạo một nút mới $\textit{cloned}$ và đặt giá trị của $\textit{g}[node]$ thành $\textit{cloned}$. Sau đó, chúng ta duyệt qua tất cả các nút kề $\textit{nxt}$ của $\textit{node}$ và thêm $\text{dfs}(nxt)$ vào danh sách nút kề của $\textit{cloned}$.
- Cuối cùng, trả về $\textit{cloned}$.

Trong hàm chính, chúng ta trả về $\text{dfs}(node)$.

Độ phức tạp thời gian là $O(n)$ và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là số lượng nút.

<!-- tabs:start -->

#### Python3

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, val = 0, neighbors = None):
        self.val = val
        self.neighbors = neighbors if neighbors is not None else []
"""

from typing import Optional


class Solution:
    def cloneGraph(self, node: Optional["Node"]) -> Optional["Node"]:
        def dfs(node):
            if node is None:
                return None
            if node in g:
                return g[node]
            cloned = Node(node.val)
            g[node] = cloned
            for nxt in node.neighbors:
                cloned.neighbors.append(dfs(nxt))
            return cloned

        g = defaultdict()
        return dfs(node)
```

#### Java

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
    private Map<Node, Node> g = new HashMap<>();

    public Node cloneGraph(Node node) {
        return dfs(node);
    }

    private Node dfs(Node node) {
        if (node == null) {
            return null;
        }
        Node cloned = g.get(node);
        if (cloned == null) {
            cloned = new Node(node.val);
            g.put(node, cloned);
            for (Node nxt : node.neighbors) {
                cloned.neighbors.add(dfs(nxt));
            }
        }
        return cloned;
    }
}
```

#### C++

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
    Node* cloneGraph(Node* node) {
        unordered_map<Node*, Node*> g;
        auto dfs = [&](this auto&& dfs, Node* node) -> Node* {
            if (!node) {
                return nullptr;
            }
            if (g.contains(node)) {
                return g[node];
            }
            Node* cloned = new Node(node->val);
            g[node] = cloned;
            for (auto& nxt : node->neighbors) {
                cloned->neighbors.push_back(dfs(nxt));
            }
            return cloned;
        };
        return dfs(node);
    }
};
```

#### Go

```go
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Neighbors []*Node
 * }
 */

func cloneGraph(node *Node) *Node {
	g := map[*Node]*Node{}
	var dfs func(node *Node) *Node
	dfs = func(node *Node) *Node {
		if node == nil {
			return nil
		}
		if n, ok := g[node]; ok {
			return n
		}
		cloned := &Node{node.Val, []*Node{}}
		g[node] = cloned
		for _, nxt := range node.Neighbors {
			cloned.Neighbors = append(cloned.Neighbors, dfs(nxt))
		}
		return cloned
	}
	return dfs(node)
}
```

#### TypeScript

```ts
/**
 * Definition for _Node.
 * class _Node {
 *     val: number
 *     neighbors: _Node[]
 *
 *     constructor(val?: number, neighbors?: _Node[]) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.neighbors = (neighbors===undefined ? [] : neighbors)
 *     }
 * }
 *
 */

function cloneGraph(node: _Node | null): _Node | null {
    const g: Map<_Node, _Node> = new Map();
    const dfs = (node: _Node | null): _Node | null => {
        if (!node) {
            return null;
        }
        if (g.has(node)) {
            return g.get(node);
        }
        const cloned = new _Node(node.val);
        g.set(node, cloned);
        for (const nxt of node.neighbors) {
            cloned.neighbors.push(dfs(nxt));
        }
        return cloned;
    };
    return dfs(node);
}
```

#### JavaScript

```js
/**
 * // Definition for a _Node.
 * function _Node(val, neighbors) {
 *    this.val = val === undefined ? 0 : val;
 *    this.neighbors = neighbors === undefined ? [] : neighbors;
 * };
 */

/**
 * @param {_Node} node
 * @return {_Node}
 */
var cloneGraph = function (node) {
    const g = new Map();
    const dfs = node => {
        if (!node) {
            return null;
        }
        if (g.has(node)) {
            return g.get(node);
        }
        const cloned = new _Node(node.val);
        g.set(node, cloned);
        for (const nxt of node.neighbors) {
            cloned.neighbors.push(dfs(nxt));
        }
        return cloned;
    };
    return dfs(node);
};
```

#### C#

```cs
/*
// Definition for a Node.
public class Node {
    public int val;
    public IList<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new List<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new List<Node>();
    }

    public Node(int _val, List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

public class Solution {
    public Node CloneGraph(Node node) {
        var g = new Dictionary<Node, Node>();
        Node Dfs(Node n) {
            if (n == null) {
                return null;
            }
            if (g.ContainsKey(n)) {
                return g[n];
            }
            var cloned = new Node(n.val);
            g[n] = cloned;
            foreach (var neighbor in n.neighbors) {
                cloned.neighbors.Add(Dfs(neighbor));
            }
            return cloned;
        }
        return Dfs(node);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
