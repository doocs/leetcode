# [面试题18. 删除链表的节点](https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/)

## 题目描述
给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。

返回删除后的链表的头节点。

注意：此题对比原题有改动

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

定义一个虚拟头节点 `dummy` 指向 `head`，再定义指针 `pre` 和 `p` 分别指向 `dummy` 和 `head`。

遍历链表，`pre`、`p` 往后移动。当指针 `p` 指向的节点的值等于 `val` 时，将 `pre.next` 指向 `p.next`，然后返回 `dummy.next`。

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
        pre, p = dummy, head
        while p:
            if p.val == val:
                pre.next = p.next
                break
            pre, p = p, p.next
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
        ListNode pre = dummy, p = head;
        while (p != null) {
            if (p.val == val) {
                pre.next = p.next;
                break;
            }
            pre = p;
            p = p.next;
        }
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
var deleteNode = function(head, val) {
    let node = head
    if(node.val === val) {
        node = node.next
        head = node
    } else {
        while(node.next) {
            if(node.next.val === val) {
                node.next = node.next.next
                break
            }
            node = node.next
        }
    }
    return head
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



### **...**

```

```

<!-- tabs:end -->