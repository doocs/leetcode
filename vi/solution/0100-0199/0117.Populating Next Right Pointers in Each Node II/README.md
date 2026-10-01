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

# [117. Populating Next Right Pointers in Each Node II](https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii)

[中文文档](/solution/0100-0199/0117.Populating%20Next%20Right%20Pointers%20in%20Each%20Node%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một cây nhị phân</p>

<pre>
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
</pre>

<p>Gán mỗi con trỏ next để trỏ đến nút bên phải kế tiếp của nó. Nếu không có nút bên phải kế tiếp, con trỏ next phải được đặt thành <code>NULL</code>.</p>

<p>Ban đầu, tất cả các con trỏ next đều được đặt thành <code>NULL</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0117.Populating%20Next%20Right%20Pointers%20in%20Each%20Node%20II/images/117_sample.png" style="width: 500px; height: 171px;" />
<pre>
<strong>Đầu vào:</strong> root = [1,2,3,4,5,null,7]
<strong>Đầu ra:</strong> [1,#,2,3,#,4,5,7,#]
<strong>Giải thích: </strong>Cho cây nhị phân ở trên (Hình A), hàm của bạn cần gán mỗi con trỏ next để trỏ đến nút bên phải kế tiếp của nó, giống như trong Hình B. Đầu ra được tuần tự hóa theo thứ tự từng mức khi nối bằng các con trỏ next, trong đó dấu &#39;#&#39; biểu thị phần cuối của mỗi mức.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> root = []
<strong>Đầu ra:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số lượng nút trong cây nằm trong khoảng <code>[0, 6000]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
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
> Không giống bài toán trước, cây không nhất thiết phải là cây hoàn hảo, vì vậy nút con trái không phải lúc nào cũng nối với nút con phải. Duyệt theo thứ tự từng mức vẫn đúng: lấy ra một mức và nối các nút lân cận. $n \le 6000$.

<!-- thinking:end -->

Chúng ta sử dụng một hàng đợi $q$ để duyệt theo thứ tự từng mức. Mỗi khi duyệt một mức, chúng ta nối các nút của mức hiện tại theo thứ tự.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(n)$. Ở đây, $n$ là số lượng nút trong cây nhị phân.

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
    def connect(self, root: "Node") -> "Node":
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
        q.offerLast(root);
        while (!q.isEmpty()) {
            Node p = null;
            for (int n = q.size(); n > 0; --n) {
                Node node = q.pollFirst();
                if (p != null) {
                    p.next = node;
                }
                p = node;
                if (node.left != null) {
                    q.offerLast(node.left);
                }
                if (node.right != null) {
                    q.offerLast(node.right);
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
    if (!root) {
        return null;
    }
    const q: Node[] = [root];
    while (q.length) {
        const nq: Node[] = [];
        let p: Node | null = null;
        for (const node of q) {
            if (p) {
                p.next = node;
            }
            p = node;
            const { left, right } = node;
            left && nq.push(left);
            right && nq.push(right);
        }
        q.splice(0, q.length, ...nq);
    }
    return root;
}
```

#### C#

```cs
/*
// Definition for a Node.
public class Node {
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
}
*/

public class Solution {
    public Node Connect(Node root) {
        if (root == null) {
            return null;
        }
        var q = new Queue<Node>();
        q.Enqueue(root);
        while (q.Count > 0) {
            Node p = null;
            for (int i = q.Count; i > 0; --i) {
                var node = q.Dequeue();
                if (p != null) {
                    p.next = node;
                }
                p = node;
                if (node.left != null) {
                    q.Enqueue(node.left);
                }
                if (node.right != null) {
                    q.Enqueue(node.right);
                }
            }
        }
        return root;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Tối ưu hóa không gian

<!-- thinking:start -->

> **Tư duy**
>
> Hàng đợi của Lời giải 1 có độ rộng bằng một mức; câu hỏi mở rộng yêu cầu không gian $O(1)$. Mức hiện tại vốn đã là một danh sách $\textit{next}$. Khi duyệt qua nó, chúng ta nối các nút con khác null của mức tiếp theo theo thứ tự và ghi nhớ đầu mức đó, sau đó chuyển sang danh sách mới. Không cần hàng đợi.

<!-- thinking:end -->

Độ phức tạp không gian của Lời giải 1 tương đối cao vì cần một hàng đợi để lưu các nút của mỗi mức. Chúng ta có thể triển khai nó với không gian hằng số.

Chúng ta định nghĩa hai con trỏ $prev$ và $next$, lần lượt trỏ đến nút trước đó và nút đầu tiên của mức tiếp theo. Khi duyệt các nút của mức hiện tại, chúng ta nối các nút của mức tiếp theo lại với nhau và tìm nút đầu tiên của mức tiếp theo. Sau khi duyệt xong mức hiện tại, chúng ta gán nút đầu tiên $next$ của mức tiếp theo cho $node$ và tiếp tục duyệt.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là số lượng nút trong cây nhị phân. Độ phức tạp không gian là $O(1)$.

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
    def connect(self, root: 'Node') -> 'Node':
        def modify(curr):
            nonlocal prev, next
            if curr is None:
                return
            next = next or curr
            if prev:
                prev.next = curr
            prev = curr

        node = root
        while node:
            prev = next = None
            while node:
                modify(node.left)
                modify(node.right)
                node = node.next
            node = next
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
    private Node prev, next;

    public Node connect(Node root) {
        Node node = root;
        while (node != null) {
            prev = null;
            next = null;
            while (node != null) {
                modify(node.left);
                modify(node.right);
                node = node.next;
            }
            node = next;
        }
        return root;
    }

    private void modify(Node curr) {
        if (curr == null) {
            return;
        }
        if (next == null) {
            next = curr;
        }
        if (prev != null) {
            prev.next = curr;
        }
        prev = curr;
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
        Node* node = root;
        Node* prev = nullptr;
        Node* next = nullptr;
        auto modify = [&](Node* curr) {
            if (!curr) {
                return;
            }
            if (!next) {
                next = curr;
            }
            if (prev) {
                prev->next = curr;
            }
            prev = curr;
        };
        while (node) {
            prev = next = nullptr;
            while (node) {
                modify(node->left);
                modify(node->right);
                node = node->next;
            }
            node = next;
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
	node := root
	var prev, next *Node
	modify := func(curr *Node) {
		if curr == nil {
			return
		}
		if next == nil {
			next = curr
		}
		if prev != nil {
			prev.Next = curr
		}
		prev = curr
	}
	for node != nil {
		prev, next = nil, nil
		for node != nil {
			modify(node.Left)
			modify(node.Right)
			node = node.Next
		}
		node = next
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
    const modify = (curr: Node | null): void => {
        if (!curr) {
            return;
        }
        next = next || curr;
        if (prev) {
            prev.next = curr;
        }
        prev = curr;
    };
    let node = root;
    let [prev, next] = [null, null];
    while (node) {
        while (node) {
            modify(node.left);
            modify(node.right);
            node = node.next;
        }
        node = next;
        [prev, next] = [null, null];
    }
    return root;
}
```

#### C#

```cs
/*
// Definition for a Node.
public class Node {
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
}
*/

public class Solution {
    private Node prev, next;

    public Node Connect(Node root) {
        Node node = root;
        while (node != null) {
            prev = null;
            next = null;
            while (node != null) {
                modify(node.left);
                modify(node.right);
                node = node.next;
            }
            node = next;
        }
        return root;
    }

    private void modify(Node curr) {
        if (curr == null) {
            return;
        }
        if (next == null) {
            next = curr;
        }
        if (prev != null) {
            prev.next = curr;
        }
        prev = curr;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
