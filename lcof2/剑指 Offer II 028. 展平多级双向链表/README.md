# [剑指 Offer II 028. 展平多级双向链表](https://leetcode.cn/problems/Qv1Da2)

## 题目描述

<!-- 这里写题目描述 -->

<p>多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。</p>

<p>给定位于列表第一级的头节点，请扁平化列表，即将这样的多级双向链表展平成普通的双向链表，使所有结点出现在单级双链表中。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
<strong>输出：</strong>[1,2,3,7,8,11,12,9,10,4,5,6]
<strong>解释：
</strong>
输入的多级列表如下图所示：

<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20028.%20%E5%B1%95%E5%B9%B3%E5%A4%9A%E7%BA%A7%E5%8F%8C%E5%90%91%E9%93%BE%E8%A1%A8/images/multilevellinkedlist.png" style="height: 363px; width: 640px;" />

扁平化后的链表如下图：

<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20028.%20%E5%B1%95%E5%B9%B3%E5%A4%9A%E7%BA%A7%E5%8F%8C%E5%90%91%E9%93%BE%E8%A1%A8/images/multilevellinkedlistflattened.png" style="height: 80px; width: 1100px;" />
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>head = [1,2,null,3]
<strong>输出：</strong>[1,3,2]
<strong>解释：

</strong>输入的多级列表如下图所示：

  1---2---NULL
  |
  3---NULL
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>head = []
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>如何表示测试用例中的多级链表？</strong></p>

<p>以 <strong>示例 1</strong> 为例：</p>

<pre>
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL</pre>

<p>序列化其中的每一级之后：</p>

<pre>
[1,2,3,4,5,6,null]
[7,8,9,10,null]
[11,12,null]
</pre>

<p>为了将每一级都序列化到一起，我们需要每一级中添加值为 null 的元素，以表示没有节点连接到上一级的上级节点。</p>

<pre>
[1,2,3,4,5,6,null]
[null,null,7,8,9,10,null]
[null,11,12,null]
</pre>

<p>合并所有序列化结果，并去除末尾的 null 。</p>

<pre>
[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>节点数目不超过 <code>1000</code></li>
	<li><code>1 &lt;= Node.val &lt;= 10^5</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 430&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/flatten-a-multilevel-doubly-linked-list/">https://leetcode.cn/problems/flatten-a-multilevel-doubly-linked-list/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

仔细观察一下这个结构，不难发现其实就是前序遍历二叉树

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, val, prev, next, child):
        self.val = val
        self.prev = prev
        self.next = next
        self.child = child
"""


class Solution:
    def flatten(self, head: 'Node') -> 'Node':
        if head is None:
            return None
        dummy = Node()
        tail = dummy

        def preOrder(node: 'Node'):
            nonlocal tail
            if node is None:
                return
            next = node.next
            child = node.child
            tail.next = node
            node.prev = tail
            tail = tail.next
            node.child = None
            preOrder(child)
            preOrder(next)

        preOrder(head)
        dummy.next.prev = None
        return dummy.next
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    private Node dummy = new Node();
    private Node tail = dummy;

    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        preOrder(head);
        dummy.next.prev = null;
        return dummy.next;
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        Node next = node.next;
        Node child = node.child;
        tail.next = node;
        node.prev = tail;
        tail = tail.next;
        node.child = null;
        preOrder(child);
        preOrder(next);
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
    Node* prev;
    Node* next;
    Node* child;
};
*/

class Solution {
public:
    Node* flatten(Node* head) {
        flattenGetTail(head);
        return head;
    }

    Node* flattenGetTail(Node* head) {
        Node* cur = head;
        Node* tail = nullptr;

        while (cur) {
            Node* next = cur->next;
            if (cur->child) {
                Node* child = cur->child;
                Node* childTail = flattenGetTail(cur->child);

                cur->child = nullptr;
                cur->next = child;
                child->prev = cur;
                childTail->next = next;

                if (next)
                    next->prev = childTail;

                tail = childTail;
            } else {
                tail = cur;
            }

            cur = next;
        }

        return tail;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
