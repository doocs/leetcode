# [面试题 24. 反转链表](https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/)

## 题目描述

定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。

**示例:**

```
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
```

**限制：**

- `0 <= 节点个数 <= 5000`

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

### **...**

```

```

<!-- tabs:end -->
