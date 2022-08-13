# [面试题 35. 复杂链表的复制](https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>请实现 <code>copyRandomList</code> 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 <code>next</code> 指针指向下一个节点，还有一个 <code>random</code> 指针指向链表中的任意节点或者 <code>null</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9835.%20%E5%A4%8D%E6%9D%82%E9%93%BE%E8%A1%A8%E7%9A%84%E5%A4%8D%E5%88%B6/images/e1.png"></p>

<pre><strong>输入：</strong>head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
<strong>输出：</strong>[[7,null],[13,0],[11,4],[10,2],[1,0]]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9835.%20%E5%A4%8D%E6%9D%82%E9%93%BE%E8%A1%A8%E7%9A%84%E5%A4%8D%E5%88%B6/images/e2.png"></p>

<pre><strong>输入：</strong>head = [[1,1],[2,1]]
<strong>输出：</strong>[[1,1],[2,1]]
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9835.%20%E5%A4%8D%E6%9D%82%E9%93%BE%E8%A1%A8%E7%9A%84%E5%A4%8D%E5%88%B6/images/e3.png"></strong></p>

<pre><strong>输入：</strong>head = [[3,null],[3,0],[3,null]]
<strong>输出：</strong>[[3,null],[3,0],[3,null]]
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>head = []
<strong>输出：</strong>[]
<strong>解释：</strong>给定的链表为空（空指针），因此返回 null。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-10000 &lt;= Node.val &lt;= 10000</code></li>
	<li><code>Node.random</code>&nbsp;为空（null）或指向链表中的节点。</li>
	<li>节点数目不超过 1000 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>注意：</strong>本题与主站 138 题相同：<a href="https://leetcode.cn/problems/copy-list-with-random-pointer/">https://leetcode.cn/problems/copy-list-with-random-pointer/</a></p>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

-   相比正常的克隆链表，多了一步操作，克隆随机指针。
-   随机指针并不是读取时进行随机指向，而是没有规律。但可以确定的是，随机指向的对象都是现存的节点或 `null`。
-   而难点在于克隆随机指针，如克隆链表头节点时，随机指针指向链尾，而链尾还未创造出来。
-   对此，需要先完成链表的基础克隆，再回头关注随机指针。

### 哈希表

-   使用哈希表记录所有节点， `key` 为原节点，`value` 为克隆节点。
-   遍历链表，完成记录，克隆节点暂时只记录对应的 `val`。
-   再次遍历，完善克隆节点的 `next` 与 `random`。

### 原地算法

首先，遍历链表，完成对每个旧节点的复制。

```bash
A -> B -> C -> D -> null

=>

A -> A1 -> B -> B1 -> C -> C1 -> D -> D1 -> null
```

接着设置新节点的 `random` 指针。

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

### **JavaScript**

-   哈希表

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
        return head;
    }

    let cur = head;
    const map = new Map();
    while (cur != null) {
        map.set(cur, new Node(cur.val));
        cur = cur.next;
    }

    const res = new Node();
    let newCur = res;
    cur = head;
    while (cur != null) {
        const node = map.get(cur);
        node.random = map.get(cur.random);
        newCur.next = node;
        newCur = node;
        cur = cur.next;
    }

    return res.next;
};
```

-   原地算法

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

### **...**

```

```

<!-- tabs:end -->
