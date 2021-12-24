# [面试题 18. 删除链表的节点](https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/)

## 题目描述

给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。

返回删除后的链表的头节点。

注意：此题对比[原题](/solution/0200-0299/0237.Delete%20Node%20in%20a%20Linked%20List/README.md)有改动。

**示例 1:**

```
输入: head = [4,5,1,9], val = 5
输出: [4,1,9]
解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
```

**示例 2:**

```
输入: head = [4,5,1,9], val = 1
输出: [4,5,9]
解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
```

**说明：**

- 题目保证链表中节点的值互不相同
- 若使用 C 或 C++ 语言，你不需要 `free` 或 `delete` 被删除的节点

## 解法

定义一个虚拟头节点 `dummy` 指向 `head`，`pre` 指针初始指向 `dummy`。

循环遍历链表，`pre` 往后移动。当指针 `pre.next` 指向的节点的值等于 `val` 时退出循环，将 `pre.next` 指向 `pre.next.next`，然后返回 `dummy.next`。

<!-- tabs:start -->

### **Python3**

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None
class Solution:
    def deleteNode(self, head: ListNode, val: int) -> ListNode:
        dummy = ListNode(0)
        dummy.next = head
        pre = dummy
        while pre.next and pre.next.val != val:
            pre = pre.next
        pre.next = None if not pre.next else pre.next.next
        return dummy.next
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
    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (pre.next != null && pre.next.val != val) {
            pre = pre.next;
        }
        pre.next = pre.next == null ? null : pre.next.next;
        return dummy.next;
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
 * @param {number} val
 * @return {ListNode}
 */
var deleteNode = function (head, val) {
    const dummy = new ListNode(0);
    dummy.next = head;
    let pre = dummy;
    while (pre.next && pre.next.val != val) {
        pre = pre.next;
    }
    pre.next = pre.next ? pre.next.next : null;
    return dummy.next;
};
```

### **Go**

```go
func deleteNode(head *ListNode, val int) *ListNode {
    res := &ListNode{
        Val: 0,
        Next: head,
    }
    pre := res
    cur := res.Next
    for cur != nil {
        if cur.Val == val {
            pre.Next = cur.Next
            return res.Next
        }
        cur = cur.Next
        pre = pre.Next
    }
    return res.Next
}
```

### **C++**

```cpp
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* deleteNode(ListNode* head, int val) {
        ListNode* cur = head;
        if (!head) {
            return nullptr;
        }

        if (head->val == val) {
            // 第一个就匹配的情况
            return head->next;
        }

        while (cur && cur->next) {
            if (cur->next->val == val) {
                // 如果找到了，直接指向下一个
                cur->next = cur->next->next;
                break;
            } else {
                cur = cur->next;
            }
        }

        return head;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
