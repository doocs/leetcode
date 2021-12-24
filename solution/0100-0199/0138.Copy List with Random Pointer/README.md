# [138. 复制带随机指针的链表](https://leetcode-cn.com/problems/copy-list-with-random-pointer)

[English Version](/solution/0100-0199/0138.Copy%20List%20with%20Random%20Pointer/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的链表，每个节点包含一个额外增加的随机指针 <code>random</code> ，该指针可以指向链表中的任何节点或空节点。</p>

<p>构造这个链表的 <strong><a href="https://baike.baidu.com/item/深拷贝/22785317?fr=aladdin" target="_blank">深拷贝</a></strong>。 深拷贝应该正好由 <code>n</code> 个 <strong>全新</strong> 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 <code>next</code> 指针和 <code>random</code> 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。<strong>复制链表中的指针都不应指向原链表中的节点 </strong>。</p>

<p>例如，如果原链表中有 <code>X</code> 和 <code>Y</code> 两个节点，其中 <code>X.random --> Y</code> 。那么在复制链表中对应的两个节点 <code>x</code> 和 <code>y</code> ，同样有 <code>x.random --> y</code> 。</p>

<p>返回复制链表的头节点。</p>

<p>用一个由 <code>n</code> 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 <code>[val, random_index]</code> 表示：</p>

<ul>
	<li><code>val</code>：一个表示 <code>Node.val</code> 的整数。</li>
	<li><code>random_index</code>：随机指针指向的节点索引（范围从 <code>0</code> 到 <code>n-1</code>）；如果不指向任何节点，则为  <code>null</code> 。</li>
</ul>

<p>你的代码 <strong>只</strong> 接受原链表的头节点 <code>head</code> 作为传入参数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0138.Copy%20List%20with%20Random%20Pointer/images/e1.png" style="height: 138px; width: 680px;" /></p>

<pre>
<strong>输入：</strong>head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
<strong>输出：</strong>[[7,null],[13,0],[11,4],[10,2],[1,0]]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0138.Copy%20List%20with%20Random%20Pointer/images/e2.png" style="height: 111px; width: 680px;" /></p>

<pre>
<strong>输入：</strong>head = [[1,1],[2,1]]
<strong>输出：</strong>[[1,1],[2,1]]
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0138.Copy%20List%20with%20Random%20Pointer/images/e3.png" style="height: 119px; width: 680px;" /></strong></p>

<pre>
<strong>输入：</strong>head = [[3,null],[3,0],[3,null]]
<strong>输出：</strong>[[3,null],[3,0],[3,null]]
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>head = []
<strong>输出：</strong>[]
<strong>解释：</strong>给定的链表为空（空指针），因此返回 null。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= n <= 1000</code></li>
	<li><code>-10000 <= Node.val <= 10000</code></li>
	<li><code>Node.random</code> 为空（null）或指向链表中的节点。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

首先，遍历链表，完成对每个旧节点的复制。

```bash
A -> B -> C -> D -> null

=>

A -> A1 -> B -> B1 -> C -> C1 -> D -> D1 -> null
```

接着设置新节点的 ramdom 指针。

然后遍历链表，修改旧节点和新节点的指向，将旧节点指向下一个旧节点，而新节点指向下一个新节点。

最后返回第一个新节点即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
    def copyRandomList(self, head: 'Node') -> 'Node':
        if head is None:
            return None

        cur = head
        while cur:
            node = Node(cur.val, cur.next)
            cur.next = node
            cur = node.next

        cur = head
        while cur:
            cur.next.random = None if cur.random is None else cur.random.next
            cur = cur.next.next

        copy = head.next
        cur = head
        while cur:
            next = cur.next
            cur.next = next.next
            next.next = None if next.next is None else next.next.next
            cur = cur.next
        return copy
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

        Node copy = head.next;
        cur = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = next.next;
            next.next = next.next == null ? null : next.next.next;
            cur = cur.next;
        }
        return copy;
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
        while (cur) {
            Node* node = new Node(cur->val);
            node->next = cur->next;
            cur->next = node;
            cur = node->next;
        }

        cur = head;
        while (cur) {
            cur->next->random = cur->random ? cur->random->next : nullptr;
            cur = cur->next->next;
        }

        Node* copy = head->next;
        cur = head;
        while (cur) {
            Node* next = cur->next;
            cur->next = next->next;
            next->next = next->next ? next->next->next : nullptr;
            cur = cur->next;
        }
        return copy;
    }
};
```

### **C#**

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

        Node copy = head.next;
        cur = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = next.next;
            next.next = next.next == null ? null : next.next.next;
            cur = cur.next;
        }
        return copy;
    }
}
```

### **Go**

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

    cur := head
    for cur != nil {
        node := &Node{
            Val: cur.Val,
            Next: cur.Next,
            Random: nil,
        }
        cur.Next = node
        cur = node.Next
    }

    cur = head
    for cur != nil {
        if cur.Random == nil {
            cur.Next.Random = nil
        } else {
            cur.Next.Random = cur.Random.Next
        }
        cur = cur.Next.Next
    }

    copy := head.Next
    cur = head
    for cur != nil {
        next := cur.Next
        cur.Next = next.Next
        if (next.Next == nil) {
            next.Next = nil
        } else {
            next.Next = next.Next.Next
        }
        cur = cur.Next
    }
    return copy
}
```

### **JavaScript**

```js
/**
 * // Definition for a Node.
 * function Node(val, next, random) {
 *    this.val = val;
 *    this.next = next;
 *    this.random = random;
 * };
 */

/**
 * @param {Node} head
 * @return {Node}
 */
var copyRandomList = function (head) {
    if (head == null) {
        return null;
    }
    let cur = head;
    while (cur != null) {
        let node = new Node(cur.val, cur.next);
        cur.next = node;
        cur = node.next;
    }

    cur = head;
    while (cur != null) {
        cur.next.random = cur.random == null ? null : cur.random.next;
        cur = cur.next.next;
    }

    let copy = head.next;
    cur = head;
    while (cur != null) {
        let next = cur.next;
        cur.next = next.next;
        next.next = next.next == null ? null : next.next.next;
        cur = cur.next;
    }
    return copy;
};
```

### **...**

```

```

<!-- tabs:end -->
