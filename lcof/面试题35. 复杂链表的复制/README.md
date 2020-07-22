# [面试题35. 复杂链表的复制](https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof/)

## 题目描述
<!-- 这里写题目描述 -->
请实现 `copyRandomList` 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 `next` 指针指向下一个节点，还有一个 `random` 指针指向链表中的任意节点或者 `null`。

**示例 1：**

![](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e1.png)

```
输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
```

**示例 2：**

![](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e2.png)

```
输入：head = [[1,1],[2,1]]
输出：[[1,1],[2,1]]
```


**示例 3：**

![](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e3.png)

```
输入：head = [[3,null],[3,0],[3,null]]
输出：[[3,null],[3,0],[3,null]]
```

**示例 4：**

```
输入：head = []
输出：[]
解释：给定的链表为空（空指针），因此返回 null。
```

**提示：**

- `-10000 <= Node.val <= 10000`
- `Node.random` 为空（null）或指向链表中的节点。
- 节点数目不超过 1000 。

## 解法
<!-- 这里可写通用的实现逻辑 -->


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
        if not head:
            return None
        copy_head = Node(-1)
        cur, t = copy_head, head
        cache = {}
        while t:
            cur.next = Node(t.val)
            cache[t] = cur.next
            cur, t = cur.next, t.next
        t, cur = head, copy_head.next
        while t:
            cur.random = cache.get(t.random)
            cur, t = cur.next, t.next
        return copy_head.next

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
        Map<Node, Node> map = new HashMap<>();
        Node copyHead = new Node(-1);
        Node cur = copyHead, t = head;
        while (t != null) {
            Node node = new Node(t.val);
            map.put(t, node);
            cur.next = node;
            cur = node;
            t = t.next;
        }
        cur = copyHead.next;
        while (head != null) {
            cur.random = map.get(head.random);
            cur = cur.next;
            head = head.next;
        }
        return copyHead.next;

    }
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
var copyRandomList = function(head) {
    function copy(node) {
        if(!node) return null
        if(isRead.get(node)) return isRead.get(node)
        let newNode = new Node(node.val)
        isRead.set(node, newNode)
        newNode.random = copy(node.random)
        newNode.next = copy(node.next)
        return newNode
    }
    let isRead = new Map()
    return copy(head)
};
```

### **Go**

```go
func copyRandomList(head *Node) *Node {
    if head == nil {
        return nil
    }
    cur, next := head,head
    //完成对当前节点的复制
    for cur != nil {
        next = cur.Next
        cur.Next = &Node{
            Val: cur.Val,
            Next: next,
            Random: nil,
        }
        cur = next
    }
    //设置复制节点的random节点
    cur = head
    curCopy := head
    for cur != nil {
        next = cur.Next.Next
        curCopy = cur.Next
        if cur.Random == nil {
            curCopy.Random = nil
        } else {
            curCopy.Random = cur.Random.Next
        }
        cur = next
    }
    res := head.Next
    cur = head
    for cur != nil {
        next = cur.Next.Next
        curCopy = cur.Next
        cur.Next = next
        if next != nil {
            curCopy.Next = next.Next
        } else {
            curCopy.Next = nil
        }
        cur = cur.Next
    }
    return res
}
```



### **...**

```

```

<!-- tabs:end -->