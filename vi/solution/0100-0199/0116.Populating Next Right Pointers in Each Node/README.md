---
comments: true
difficulty: Medium
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
    - Linked List
    - Binary Tree
---

<!-- problem:start -->

# [116. Populating Next Right Pointers in Each Node](https://leetcode.com/problems/populating-next-right-pointers-in-each-node)

[中文文档](/solution/0100-0199/0116.Populating%20Next%20Right%20Pointers%20in%20Each%20Node/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn được cung cấp một <strong>cây nhị phân hoàn hảo</strong> trong đó tất cả các lá nằm cùng một mức, và mỗi nút cha có hai nút con. Cây nhị phân được định nghĩa như sau:</p>

<pre>
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
</pre>

<p>Hãy gán mỗi con trỏ next để trỏ tới nút bên phải kế tiếp của nó. Nếu không có nút bên phải kế tiếp, con trỏ next phải được đặt thành <code>NULL</code>.</p>

<p>Ban đầu, tất cả các con trỏ next được đặt thành <code>NULL</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0116.Populating%20Next%20Right%20Pointers%20in%20Each%20Node/images/116_sample.png" style="width: 500px; height: 171px;" />
<pre>
<strong>Đầu vào:</strong> root = [1,2,3,4,5,6,7]
<strong>Đầu ra:</strong> [1,#,2,3,#,4,5,6,7,#]
<strong>Giải thích: </strong>Với cây nhị phân hoàn hảo như trên (Hình A), hàm của bạn cần gán mỗi con trỏ next để trỏ tới nút bên phải kế tiếp của nó, giống như trong Hình B. Đầu ra được tuần tự hóa theo thứ tự mức, dựa trên các con trỏ next, trong đó dấu &#39;#&#39; biểu thị điểm kết thúc của mỗi mức.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> root = []
<strong>Đầu ra:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số lượng nút trong cây nằm trong khoảng <code>[0, 2<sup>12</sup> - 1]</code>.</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Câu hỏi mở rộng:</strong></p>

<ul>
	<li>Bạn chỉ được sử dụng không gian phụ hằng số.</li>
	<li>Cách tiếp cận đệ quy là phù hợp. Bạn có thể giả sử rằng không gian ngăn xếp ngầm không được tính là không gian phụ cho bài toán này.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: BFS

<!-- thinking:start -->

> **Tư duy**
>
> Cây là cây hoàn hảo, nên mọi mức đều đầy đủ. BFS có thể nối $\textit{next}$ qua từng mức. $n < 2^{12}$, và hàng đợi có độ rộng bằng một mức. Câu hỏi mở rộng yêu cầu không gian phụ hằng số; trước hết, phương pháp này thực hiện đúng việc nối các nút theo mức.

<!-- thinking:end -->

Sử dụng một hàng đợi để duyệt theo thứ tự mức, và mỗi khi duyệt một mức, nối các nút của mức hiện tại theo thứ tự.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là số lượng nút trong cây nhị phân.

<!-- tabs:start -->

#### Python3

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, val: int = 0, left: 'Node' = None, right: 'Node' = None, next: 'Node' = None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next
"""


class Solution:
    def connect(self, root: "Optional[Node]") -> "Optional[Node]":
        if root is None:
            return root
        q = deque([root])
        while q:
            p = None
            for _ in range(len(q)):
                node = q.popleft()
                if p:
                    p.next = node
                p = node
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
        return root
```

#### Java

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Deque<Node> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node p = null;
            for (int n = q.size(); n > 0; --n) {
                Node node = q.poll();
                if (p != null) {
                    p.next = node;
                }
                p = node;
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }
        return root;
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
    Node* left;
    Node* right;
    Node* next;

    Node() : val(0), left(NULL), right(NULL), next(NULL) {}

    Node(int _val) : val(_val), left(NULL), right(NULL), next(NULL) {}

    Node(int _val, Node* _left, Node* _right, Node* _next)
        : val(_val), left(_left), right(_right), next(_next) {}
};
*/

class Solution {
public:
    Node* connect(Node* root) {
        if (!root) {
            return root;
        }
        queue<Node*> q{{root}};
        while (!q.empty()) {
            Node* p = nullptr;
            for (int n = q.size(); n; --n) {
                Node* node = q.front();
                q.pop();
                if (p) {
                    p->next = node;
                }
                p = node;
                if (node->left) {
                    q.push(node->left);
                }
                if (node->right) {
                    q.push(node->right);
                }
            }
        }
        return root;
    }
};
```

#### Go

```go
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Left *Node
 *     Right *Node
 *     Next *Node
 * }
 */

func connect(root *Node) *Node {
	if root == nil {
		return root
	}
	q := []*Node{root}
	for len(q) > 0 {
		var p *Node
		for n := len(q); n > 0; n-- {
			node := q[0]
			q = q[1:]
			if p != nil {
				p.Next = node
			}
			p = node
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
	}
	return root
}
```

#### TypeScript

```ts
/**
 * Definition for Node.
 * class Node {
 *     val: number
 *     left: Node | null
 *     right: Node | null
 *     next: Node | null
 *     constructor(val?: number, left?: Node, right?: Node, next?: Node) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *         this.next = (next===undefined ? null : next)
 *     }
 * }
 */

function connect(root: Node | null): Node | null {
    if (root == null || root.left == null) {
        return root;
    }
    const { left, right, next } = root;
    left.next = right;
    if (next != null) {
        right.next = next.left;
    }
    connect(left);
    connect(right);
    return root;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: DFS

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 lưu cả một mức trong hàng đợi. Trong cây hoàn hảo, hai nút con của một nút nằm kề nhau, và nút con bên phải được nối tiếp bởi nút con bên trái của nút anh em. Khi mức cha đã được nối, các con trỏ $\textit{next}$ đó sẽ duyệt qua mức kế tiếp, vì vậy đệ quy có thể nối các nút con mà không cần hàng đợi.

<!-- thinking:end -->

Sử dụng đệ quy để duyệt preorder, và mỗi khi đi tới một nút, nối các nút con trái và phải của nó theo thứ tự.

Cụ thể, chúng ta thiết kế một hàm $dfs(left, right)$, hàm này trỏ con trỏ $next$ của nút $left$ tới nút $right$. Trong hàm, trước tiên chúng ta kiểm tra xem $left$ và $right$ có phải là null hay không. Nếu cả hai đều khác null, trỏ $left.next$ tới $right$, sau đó lần lượt gọi đệ quy $dfs(left.left, left.right)$, $dfs(left.right, right.left)$, $dfs(right.left, right.right)$.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là số lượng nút trong cây nhị phân.

<!-- tabs:start -->

#### Python3

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, val: int = 0, left: 'Node' = None, right: 'Node' = None, next: 'Node' = None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next
"""


class Solution:
    def connect(self, root: 'Optional[Node]') -> 'Optional[Node]':
        def dfs(left, right):
            if left is None or right is None:
                return
            left.next = right
            dfs(left.left, left.right)
            dfs(left.right, right.left)
            dfs(right.left, right.right)

        if root:
            dfs(root.left, root.right)
        return root
```

#### Java

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if (root != null) {
            dfs(root.left, root.right);
        }
        return root;
    }

    private void dfs(Node left, Node right) {
        if (left == null || right == null) {
            return;
        }
        left.next = right;
        dfs(left.left, left.right);
        dfs(left.right, right.left);
        dfs(right.left, right.right);
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
    Node* left;
    Node* right;
    Node* next;

    Node() : val(0), left(NULL), right(NULL), next(NULL) {}

    Node(int _val) : val(_val), left(NULL), right(NULL), next(NULL) {}

    Node(int _val, Node* _left, Node* _right, Node* _next)
        : val(_val), left(_left), right(_right), next(_next) {}
};
*/

class Solution {
public:
    Node* connect(Node* root) {
        function<void(Node*, Node*)> dfs = [&](Node* left, Node* right) {
            if (!left || !right) {
                return;
            }
            left->next = right;
            dfs(left->left, left->right);
            dfs(left->right, right->left);
            dfs(right->left, right->right);
        };
        if (root) {
            dfs(root->left, root->right);
        }
        return root;
    }
};
```

#### Go

```go
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Left *Node
 *     Right *Node
 *     Next *Node
 * }
 */

func connect(root *Node) *Node {
	var dfs func(*Node, *Node)
	dfs = func(left, right *Node) {
		if left == nil || right == nil {
			return
		}
		left.Next = right
		dfs(left.Left, left.Right)
		dfs(left.Right, right.Left)
		dfs(right.Left, right.Right)
	}
	if root != nil {
		dfs(root.Left, root.Right)
	}
	return root
}
```

#### TypeScript

```ts
/**
 * Definition for Node.
 * class Node {
 *     val: number
 *     left: Node | null
 *     right: Node | null
 *     next: Node | null
 *     constructor(val?: number, left?: Node, right?: Node, next?: Node) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *         this.next = (next===undefined ? null : next)
 *     }
 * }
 */

function connect(root: Node | null): Node | null {
    if (root == null) {
        return root;
    }
    const queue = [root];
    while (queue.length !== 0) {
        const n = queue.length;
        let pre = null;
        for (let i = 0; i < n; i++) {
            const node = queue.shift();
            node.next = pre;
            pre = node;
            const { left, right } = node;
            left && queue.push(right, left);
        }
    }
    return root;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
