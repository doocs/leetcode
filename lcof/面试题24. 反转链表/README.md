# [面试题 24. 反转链表](https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/)

## 题目描述

<p>定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。</p>

<p>&nbsp;</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> 1-&gt;2-&gt;3-&gt;4-&gt;5-&gt;NULL
<strong>输出:</strong> 5-&gt;4-&gt;3-&gt;2-&gt;1-&gt;NULL</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>0 &lt;= 节点个数 &lt;= 5000</code></p>

<p>&nbsp;</p>

<p><strong>注意</strong>：本题与主站 206 题相同：<a href="https://leetcode.cn/problems/reverse-linked-list/">https://leetcode.cn/problems/reverse-linked-list/</a></p>

## 解法

定义指针 `pre`，`cur` 分别指向 null 和头节点。

遍历链表，将 `cur.next` 临时保存到 `t` 中，然后改变指针 `cur` 指向的节点的指向，将其指向 `pre` 指针指向的节点，即 `cur.next = pre`。然后 `pre` 指针指向 `cur`，`cur` 指针往前走。

当遍历结束后，返回 `pre` 指针即可。

<!-- tabs:start -->

### **Python3**

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def reverseList(self, head: ListNode) -> ListNode:
        pre, cur = None, head
        while cur:
            t = cur.next
            cur.next = pre
            pre = cur
            cur = t
        return pre
```

### **Java**

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
        }
        return pre;
    }
}
```

### **JavaScript**

```js
/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} head
 * @return {ListNode}
 */
var reverseList = function (head) {
    let node = head;
    let pre = null;
    while (node) {
        let cur = node;
        node = cur.next;
        cur.next = pre;
        pre = cur;
    }
    return pre;
};
```

### **Go**

```go
func reverseList(head *ListNode) *ListNode {
    if head == nil ||head.Next == nil {
        return head
    }
    dummyHead := &ListNode{}
    cur := head
    for cur != nil {
        tmp := cur.Next
        cur.Next = dummyHead.Next
        dummyHead.Next = cur
        cur = tmp
    }
    return dummyHead.Next
}
```

### **C++**

```cpp
class Solution {
public:
    ListNode* reverseList(ListNode* head) {
        // 通过头插实现逆序
        // ListNode *first = new ListNode(-1);
        // ListNode *p = head, *q;
        // while (p) {
        //     q = p->next;
        //     p->next = first->next;
        //     first->next = p;
        //     p = q;
        // }
        // return first->next;

        // 常规方法
        ListNode *pre = NULL, *cur = head;
        while (cur) {
            ListNode* temp = cur->next;
            cur->next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
};
```

### **TypeScript**

```ts
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     val: number
 *     next: ListNode | null
 *     constructor(val?: number, next?: ListNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.next = (next===undefined ? null : next)
 *     }
 * }
 */

function reverseList(head: ListNode | null): ListNode | null {
    let cur = head;
    let pre = null;
    while (cur != null) {
        const temp = cur.next;
        cur.next = pre;
        pre = cur;
        cur = temp;
    }
    return pre;
}
```

### **Rust**

```rust
// Definition for singly-linked list.
// #[derive(PartialEq, Eq, Clone, Debug)]
// pub struct ListNode {
//   pub val: i32,
//   pub next: Option<Box<ListNode>>
// }
//
// impl ListNode {
//   #[inline]
//   fn new(val: i32) -> Self {
//     ListNode {
//       next: None,
//       val
//     }
//   }
// }
impl Solution {
    pub fn reverse_list(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut pre = None;
        let mut cur = head;

        while let Some(mut node) = cur {
            cur = node.next.take();
            node.next = pre.take();
            pre = Some(node);
        }
        pre
    }
}
```

### **C#**

```cs
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode ReverseList(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
