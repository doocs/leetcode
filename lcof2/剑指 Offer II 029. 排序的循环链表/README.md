# [剑指 Offer II 029. 排序的循环链表](https://leetcode.cn/problems/4ueAj6)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定循环升序列表中的一个点，写一个函数向这个列表中插入一个新元素&nbsp;<code>insertVal</code> ，使这个列表仍然是循环升序的。</p>

<p>给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。</p>

<p>如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。</p>

<p>如果列表为空（给定的节点是 <code>null</code>），需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20029.%20%E6%8E%92%E5%BA%8F%E7%9A%84%E5%BE%AA%E7%8E%AF%E9%93%BE%E8%A1%A8/images/example_1_before_65p.jpg" style="height: 149px; width: 250px;" /><br />
&nbsp;</p>

<pre>
<strong>输入：</strong>head = [3,4,1], insertVal = 2
<strong>输出：</strong>[3,4,1,2]
<strong>解释：</strong>在上图中，有一个包含三个元素的循环有序列表，你获得值为 3 的节点的指针，我们需要向表中插入元素 2 。新插入的节点应该在 1 和 3 之间，插入之后，整个列表如上图所示，最后返回节点 3 。

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20029.%20%E6%8E%92%E5%BA%8F%E7%9A%84%E5%BE%AA%E7%8E%AF%E9%93%BE%E8%A1%A8/images/example_1_after_65p.jpg" style="height: 149px; width: 250px;" />
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>head = [], insertVal = 1
<strong>输出：</strong>[1]
<strong>解释：</strong>列表为空（给定的节点是 <code>null</code>），创建一个循环有序列表并返回这个节点。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>head = [1], insertVal = 0
<strong>输出：</strong>[1,0]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= Number of Nodes &lt;= 5 * 10^4</code></li>
	<li><code><font face="monospace">-10^6 &lt;= Node.val &lt;= 10^6</font></code></li>
	<li><code>-10^6 &lt;=&nbsp;insertVal &lt;= 10^6</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 708&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/insert-into-a-sorted-circular-linked-list/">https://leetcode.cn/problems/insert-into-a-sorted-circular-linked-list/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

1. 头节点如果为空，直接返回 `node`
2. 如果 `insertVal` 在链表的最小值和最大值之间，找到合适的位置插入
3. 如果 `insertVal` 小于链表的最小值或大于链表的最大值，则在头节点和尾节点之间插入
4. 链表的所有值和 `insertVal` 都相等，任意位置插入

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, next=None):
        self.val = val
        self.next = next
"""


class Solution:
    def insert(self, head: 'Node', insertVal: int) -> 'Node':
        node = Node(insertVal)
        if head is None:
            node.next = node
            return node
        p = head
        while True:
            if (
                p.val <= insertVal
                and insertVal <= p.next.val
                or p.val > p.next.val
                and (insertVal <= p.next.val or insertVal >= p.val)
                or p.next == head
            ):

                node.next = p.next
                p.next = node
                break

            p = p.next
        return head
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

class Solution {
    public Node insert(Node head, int insertVal) {
        Node node = new Node(insertVal);
        if (head == null) {
            node.next = node;
            return node;
        }
        Node p = head;
        for (;;) {
            if (p.val <= insertVal && insertVal <= p.next.val
                || p.val > p.next.val && (insertVal <= p.next.val || insertVal >= p.val)
                || p.next == head) {
                node.next = p.next;
                p.next = node;
                break;
            }
            p = p.next;
        }
        return head;
    }
}
```

### **C++**

```cpp
/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* next;

    Node() {}

    Node(int _val) {
        val = _val;
        next = NULL;
    }

    Node(int _val, Node* _next) {
        val = _val;
        next = _next;
    }
};
*/

class Solution {
public:
    Node* insert(Node* head, int insertVal) {
        Node* node = new Node(insertVal);
        if (!head) {
            node->next = node;
            return node;
        }
        Node *prev = head, *curr = head->next;
        while (curr != head) {
            if ((prev->val <= insertVal && insertVal <= curr->val) || (prev->val > curr->val && (insertVal >= prev->val || insertVal <= curr->val))) break;
            prev = curr;
            curr = curr->next;
        }
        prev->next = node;
        node->next = curr;
        return head;
    }
};
```

### **Go**

```go
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Next *Node
 * }
 */

func insert(head *Node, x int) *Node {
	node := &Node{Val: x}
	if head == nil {
		node.Next = node
		return node
	}
	prev, curr := head, head.Next
	for curr != head {
		if (prev.Val <= x && x <= curr.Val) || (prev.Val > curr.Val && (x >= prev.Val || x <= curr.Val)) {
			break
		}
		prev, curr = curr, curr.Next
	}
	prev.Next = node
	node.Next = curr
	return head
}
```

### **TypeScript**

```ts
/**
 * Definition for node.
 * class Node {
 *     val: number
 *     next: Node | null
 *     constructor(val?: number, next?: Node) {
 *         this.val = (val===undefined ? 0 : val);
 *         this.next = (next===undefined ? null : next);
 *     }
 * }
 */

function insert(head: Node | null, insertVal: number): Node | null {
    const newNode = new Node(insertVal);
    if (head == null) {
        newNode.next = newNode;
        return newNode;
    }
    const dummy = new Node(0, head);
    let cur = dummy.next;
    while (cur.next != dummy.next) {
        const val = cur.val;
        const nextVal = cur.next.val;
        if (val > nextVal) {
            if (
                (insertVal >= val && insertVal >= nextVal) ||
                (insertVal <= val && insertVal <= nextVal)
            ) {
                break;
            }
        } else {
            if (insertVal >= val && insertVal <= nextVal) {
                break;
            }
        }
        cur = cur.next;
    }
    newNode.next = cur.next;
    cur.next = newNode;
    return dummy.next;
}
```

### **...**

```

```

<!-- tabs:end -->
