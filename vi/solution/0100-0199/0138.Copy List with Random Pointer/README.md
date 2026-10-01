---
comments: true
difficulty: Medium
tags:
    - Hash Table
    - Linked List
---

<!-- problem:start -->

# [138. Copy List with Random Pointer](https://leetcode.com/problems/copy-list-with-random-pointer)

[中文文档](/solution/0100-0199/0138.Copy%20List%20with%20Random%20Pointer/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một danh sách liên kết có độ dài <code>n</code>, trong đó mỗi nút chứa thêm một con trỏ random có thể trỏ tới bất kỳ nút nào trong danh sách hoặc <code>null</code>.</p>

<p>Hãy xây dựng một <a href="https://en.wikipedia.org/wiki/Object_copying#Deep_copy" target="_blank"><strong>bản sao sâu</strong></a> của danh sách. Bản sao sâu phải gồm chính xác <code>n</code> nút <strong>hoàn toàn mới</strong>, trong đó mỗi nút mới có giá trị được đặt bằng giá trị của nút gốc tương ứng. Cả con trỏ <code>next</code> và <code>random</code> của các nút mới phải trỏ tới các nút mới trong danh sách được sao chép sao cho các con trỏ trong danh sách gốc và danh sách được sao chép biểu diễn cùng một trạng thái danh sách. <strong>Không con trỏ nào trong danh sách mới được trỏ tới các nút trong danh sách gốc</strong>.</p>

<p>Ví dụ, nếu danh sách gốc có hai nút <code>X</code> và <code>Y</code>, trong đó <code>X.random --&gt; Y</code>, thì với hai nút tương ứng <code>x</code> và <code>y</code> trong danh sách được sao chép, <code>x.random --&gt; y</code>.</p>

<p>Hãy trả về <em>nút đầu của danh sách liên kết được sao chép</em>.</p>

<p>Danh sách liên kết được biểu diễn trong đầu vào/đầu ra dưới dạng một danh sách gồm <code>n</code> nút. Mỗi nút được biểu diễn dưới dạng một cặp <code>[val, random_index]</code>, trong đó:</p>

<ul>
	<li><code>val</code>: một số nguyên biểu diễn <code>Node.val</code></li>
	<li><code>random_index</code>: chỉ số của nút (nằm trong khoảng từ <code>0</code> đến <code>n-1</code>) mà con trỏ <code>random</code> trỏ tới, hoặc <code>null</code> nếu nó không trỏ tới nút nào.</li>
</ul>

<p>Mã của bạn sẽ <strong>chỉ</strong> được cung cấp <code>head</code> của danh sách liên kết gốc.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0138.Copy%20List%20with%20Random%20Pointer/images/e1.png" style="width: 700px; height: 142px;" />
<pre>
<strong>Đầu vào:</strong> head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
<strong>Đầu ra:</strong> [[7,null],[13,0],[11,4],[10,2],[1,0]]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0138.Copy%20List%20with%20Random%20Pointer/images/e2.png" style="width: 700px; height: 114px;" />
<pre>
<strong>Đầu vào:</strong> head = [[1,1],[2,1]]
<strong>Đầu ra:</strong> [[1,1],[2,1]]
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0138.Copy%20List%20with%20Random%20Pointer/images/e3.png" style="width: 700px; height: 122px;" /></strong></p>

<pre>
<strong>Đầu vào:</strong> head = [[3,null],[3,0],[3,null]]
<strong>Đầu ra:</strong> [[3,null],[3,0],[3,null]]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 1000</code></li>
	<li><code>-10<sup>4</sup> &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
	<li><code>Node.random</code> là <code>null</code> hoặc trỏ tới một nút nào đó trong danh sách liên kết.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Bảng băm

<!-- thinking:start -->

> **Tư duy**
>
> Sao chép một danh sách mà con trỏ random có thể trỏ tới bất kỳ nút nào hoặc null. Nếu sao chép $\textit{next}$ trước, đích của random có thể chưa tồn tại. $n\le 1000$.
>
> Một lượt duyệt đầu tiên xây dựng danh sách mới theo $\textit{next}$ và ánh xạ mỗi nút gốc tới bản sao của nó; lượt duyệt thứ hai nối $\textit{random}$ thông qua ánh xạ đó.

<!-- thinking:end -->

Chúng ta có thể định nghĩa một nút đầu giả $\textit{dummy}$ và dùng một con trỏ $\textit{tail}$ trỏ tới nút đầu giả. Sau đó, chúng ta duyệt qua danh sách liên kết, sao chép từng nút và lưu ánh xạ giữa mỗi nút với bản sao của nó trong một bảng băm $\textit{d}$, đồng thời nối các con trỏ $\textit{next}$ của các nút được sao chép.

Tiếp theo, chúng ta duyệt lại danh sách liên kết và sử dụng các ánh xạ được lưu trong bảng băm để nối các con trỏ $\textit{random}$ của các nút được sao chép.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(n)$. Ở đây, $n$ là độ dài của danh sách liên kết.

<!-- tabs:start -->

#### Python3

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random
"""


class Solution:
    def copyRandomList(self, head: "Optional[Node]") -> "Optional[Node]":
        d = {}
        dummy = tail = Node(0)
        cur = head
        while cur:
            node = Node(cur.val)
            tail.next = node
            tail = tail.next
            d[cur] = node
            cur = cur.next
        cur = head
        while cur:
            d[cur].random = d[cur.random] if cur.random else None
            cur = cur.next
        return dummy.next
```

#### Java

```java
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Node> d = new HashMap<>();
        Node dummy = new Node(0);
        Node tail = dummy;
        for (Node cur = head; cur != null; cur = cur.next) {
            Node node = new Node(cur.val);
            tail.next = node;
            tail = node;
            d.put(cur, node);
        }
        for (Node cur = head; cur != null; cur = cur.next) {
            d.get(cur).random = cur.random == null ? null : d.get(cur.random);
        }
        return dummy.next;
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
    Node* next;
    Node* random;

    Node(int _val) {
        val = _val;
        next = NULL;
        random = NULL;
    }
};
*/

class Solution {
public:
    Node* copyRandomList(Node* head) {
        Node* dummy = new Node(0);
        Node* tail = dummy;
        unordered_map<Node*, Node*> d;
        for (Node* cur = head; cur; cur = cur->next) {
            Node* node = new Node(cur->val);
            tail->next = node;
            tail = node;
            d[cur] = node;
        }
        for (Node* cur = head; cur; cur = cur->next) {
            d[cur]->random = cur->random ? d[cur->random] : nullptr;
        }
        return dummy->next;
    }
};
```

#### Go

```go
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Next *Node
 *     Random *Node
 * }
 */

func copyRandomList(head *Node) *Node {
	dummy := &Node{}
	tail := dummy
	d := map[*Node]*Node{}
	for cur := head; cur != nil; cur = cur.Next {
		node := &Node{Val: cur.Val}
		d[cur] = node
		tail.Next = node
		tail = node
	}
	for cur := head; cur != nil; cur = cur.Next {
		if cur.Random != nil {
			d[cur].Random = d[cur.Random]
		}
	}
	return dummy.Next
}
```

#### TypeScript

```ts
/**
 * Definition for _Node.
 * class _Node {
 *     val: number
 *     next: _Node | null
 *     random: _Node | null
 *
 *     constructor(val?: number, next?: _Node, random?: _Node) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.next = (next===undefined ? null : next)
 *         this.random = (random===undefined ? null : random)
 *     }
 * }
 */

function copyRandomList(head: _Node | null): _Node | null {
    const d: Map<_Node, _Node> = new Map();
    const dummy = new _Node();
    let tail = dummy;
    for (let cur = head; cur; cur = cur.next) {
        const node = new _Node(cur.val);
        tail.next = node;
        tail = node;
        d.set(cur, node);
    }
    for (let cur = head; cur; cur = cur.next) {
        d.get(cur)!.random = cur.random ? d.get(cur.random)! : null;
    }
    return dummy.next;
}
```

#### JavaScript

```js
/**
 * // Definition for a _Node.
 * function _Node(val, next, random) {
 *    this.val = val;
 *    this.next = next;
 *    this.random = random;
 * };
 */

/**
 * @param {_Node} head
 * @return {_Node}
 */
var copyRandomList = function (head) {
    const d = new Map();
    const dummy = new _Node();
    let tail = dummy;
    for (let cur = head; cur; cur = cur.next) {
        const node = new _Node(cur.val);
        tail.next = node;
        tail = node;
        d.set(cur, node);
    }
    for (let cur = head; cur; cur = cur.next) {
        d.get(cur).random = cur.random ? d.get(cur.random) : null;
    }
    return dummy.next;
};
```

#### C#

```cs
/*
// Definition for a Node.
public class Node {
    public int val;
    public Node next;
    public Node random;

    public Node(int _val) {
        val = _val;
        next = null;
        random = null;
    }
}
*/

public class Solution {
    public Node CopyRandomList(Node head) {
        Dictionary<Node, Node> d = new Dictionary<Node, Node>();
        Node dummy = new Node(0);
        Node tail = dummy;

        for (Node cur = head; cur != null; cur = cur.next) {
            Node node = new Node(cur.val);
            tail.next = node;
            tail = node;
            d[cur] = node;
        }

        for (Node cur = head; cur != null; cur = cur.next) {
            if (cur.random != null) {
                d[cur].random = d[cur.random];
            }
        }

        return dummy.next;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Mô phỏng (Tối ưu hóa không gian)

<!-- thinking:start -->

> **Tư duy**
>
> Bảng ánh xạ của Lời giải 1 chiếm $O(n)$ không gian. Chèn mỗi bản sao ngay sau nút gốc tương ứng để cặp nút nằm cạnh nhau; nút random tương ứng là $\textit{cur.random.next}$. Sau đó tách danh sách. Không cần bảng băm.

<!-- thinking:end -->

Trong Lời giải 1, chúng ta sử dụng thêm một bảng băm để lưu ánh xạ giữa các nút gốc và các nút được sao chép. Chúng ta cũng có thể thực hiện việc này mà không cần không gian bổ sung như sau:

1. Duyệt qua danh sách liên kết gốc, với mỗi nút, tạo một nút mới và chèn nó vào giữa nút gốc và nút kế tiếp của nút gốc.
2. Duyệt lại danh sách liên kết, rồi đặt con trỏ $\textit{random}$ của nút mới dựa trên con trỏ $\textit{random}$ của nút gốc.
3. Cuối cùng, tách danh sách liên kết thành danh sách liên kết gốc và danh sách liên kết được sao chép.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của danh sách liên kết. Không tính không gian được dùng cho danh sách liên kết kết quả, độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random
"""


class Solution:
    def copyRandomList(self, head: "Optional[Node]") -> "Optional[Node]":
        if head is None:
            return None
        cur = head
        while cur:
            node = Node(cur.val, cur.next)
            cur.next = node
            cur = node.next
        cur = head
        while cur:
            cur.next.random = cur.random.next if cur.random else None
            cur = cur.next.next
        cur = head
        ans = head.next
        while cur.next:
            node = cur.next
            cur.next = node.next
            cur = node
        return ans
```

#### Java

```java
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

public class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        while (cur != null) {
            Node node = new Node(cur.val);
            node.next = cur.next;
            cur.next = node;
            cur = node.next;
        }
        cur = head;
        while (cur != null) {
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = cur.next.next;
        }
        cur = head;
        Node ans = head.next;
        while (cur.next != null) {
            Node node = cur.next;
            cur.next = node.next;
            cur = node;
        }
        return ans;
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
    Node* next;
    Node* random;

    Node(int _val) {
        val = _val;
        next = NULL;
        random = NULL;
    }
};
*/

class Solution {
public:
    Node* copyRandomList(Node* head) {
        if (!head) {
            return nullptr;
        }
        Node* cur = head;
        while (cur != nullptr) {
            Node* node = new Node(cur->val);
            node->next = cur->next;
            cur->next = node;
            cur = node->next;
        }
        cur = head;
        while (cur != nullptr) {
            cur->next->random = cur->random == nullptr ? nullptr : cur->random->next;
            cur = cur->next->next;
        }
        cur = head;
        Node* ans = head->next;
        while (cur->next != nullptr) {
            Node* node = cur->next;
            cur->next = node->next;
            cur = node;
        }
        return ans;
    }
};
```

#### Go

```go
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Next *Node
 *     Random *Node
 * }
 */

func copyRandomList(head *Node) *Node {
	if head == nil {
		return nil
	}
	for cur := head; cur != nil; {
		node := &Node{cur.Val, cur.Next, nil}
		cur.Next = node
		cur = node.Next
	}
	for cur := head; cur != nil; cur = cur.Next.Next {
		if cur.Random != nil {
			cur.Next.Random = cur.Random.Next
		}
	}
	ans := head.Next
	for cur := head; cur.Next != nil; {
		node := cur.Next
		cur.Next = node.Next
		cur = node
	}
	return ans
}
```

#### TypeScript

```ts
/**
 * Definition for _Node.
 * class _Node {
 *     val: number
 *     next: _Node | null
 *     random: _Node | null
 *
 *     constructor(val?: number, next?: _Node, random?: _Node) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.next = (next===undefined ? null : next)
 *         this.random = (random===undefined ? null : random)
 *     }
 * }
 */

function copyRandomList(head: _Node | null): _Node | null {
    if (head === null) {
        return null;
    }
    let cur = head;
    while (cur !== null) {
        const node = new _Node(cur.val);
        node.next = cur.next;
        cur.next = node;
        cur = node.next;
    }
    cur = head;
    while (cur !== null) {
        cur.next.random = cur.random === null ? null : cur.random.next;
        cur = cur.next.next;
    }
    cur = head;
    const ans = head.next;
    while (cur.next !== null) {
        const node = cur.next;
        cur.next = node.next;
        cur = node;
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * // Definition for a _Node.
 * function _Node(val, next, random) {
 *    this.val = val;
 *    this.next = next;
 *    this.random = random;
 * };
 */

/**
 * @param {_Node} head
 * @return {_Node}
 */
var copyRandomList = function (head) {
    if (head === null) {
        return null;
    }
    let cur = head;
    while (cur !== null) {
        const node = new _Node(cur.val);
        node.next = cur.next;
        cur.next = node;
        cur = node.next;
    }
    cur = head;
    while (cur !== null) {
        cur.next.random = cur.random === null ? null : cur.random.next;
        cur = cur.next.next;
    }
    cur = head;
    const ans = head.next;
    while (cur.next !== null) {
        const node = cur.next;
        cur.next = node.next;
        cur = node;
    }
    return ans;
};
```

#### C#

```cs
/*
// Definition for a Node.
public class Node {
    public int val;
    public Node next;
    public Node random;

    public Node(int _val) {
        val = _val;
        next = null;
        random = null;
    }
}
*/

public class Solution {
    public Node CopyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        while (cur != null) {
            Node node = new Node(cur.val);
            node.next = cur.next;
            cur.next = node;
            cur = node.next;
        }
        cur = head;
        while (cur != null) {
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = cur.next.next;
        }
        cur = head;
        Node ans = head.next;
        while (cur.next != null) {
            Node node = cur.next;
            cur.next = node.next;
            cur = node;
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
