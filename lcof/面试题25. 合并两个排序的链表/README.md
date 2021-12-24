# [面试题 25. 合并两个排序的链表](https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/)

## 题目描述

输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。

**示例 1：**

```
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
```

**限制：**

- `0 <= 链表长度 <= 1000`

## 解法

同时遍历两个链表，归并插入新链表中即可。

<!-- tabs:start -->

### **Python3**

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def mergeTwoLists(self, l1: ListNode, l2: ListNode) -> ListNode:
        dummy = ListNode(0)
        p = dummy
        while l1 and l2:
            if l1.val <= l2.val:
                p.next = l1
                l1 = l1.next
            else:
                p.next = l2
                l2 = l2.next
            p = p.next
        p.next = l1 or l2
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        p.next = l1 == null ? l2 : l1;
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
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */
var mergeTwoLists = function (l1, l2) {
    // 法一 - 递归
    if (!l1) return l2;
    if (!l2) return l1;
    if (l1.val < l2.val) {
        l1.next = mergeTwoLists(l1.next, l2);
        return l1;
    } else {
        l2.next = mergeTwoLists(l2.next, l1);
        return l2;
    }
    // 法二 - 遍历
    // if(!l1 || !l2) return l1 ? l1 : l2
    // let a = l1
    // let b = l2
    // let res = l1
    // if(a.val > b.val) {
    //     let c = a
    //     a = b
    //     b = c
    //     res = l2
    // }
    // while(a && b) {
    //     while(a.next && a.next.val < b.val) {
    //         a = a.next
    //     }
    //     let tmp = a.next
    //     let rec = b.next
    //     a.next = b
    //     a.next.next = tmp
    //     a = a.next
    //     b = rec
    // }
    // return res
};
```

### **Go**

```go
func mergeTwoLists(l1 *ListNode, l2 *ListNode) *ListNode {
    if l1 == nil {
        return l2
    }
    if l2 == nil {
        return l1
    }
    if l1.Val <= l2.Val {
        l1.Next = mergeTwoLists(l1.Next,l2)
        return l1
    }
    l2.Next = mergeTwoLists(l1, l2.Next)
    return l2
}
```

### **C++**

```cpp
class Solution {
public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        if (nullptr == l1 && nullptr == l2) {
            return nullptr;    // 两个都为空，则直接返回
        }

        if (nullptr == l1 || nullptr == l2) {
            return l1 == nullptr ? l2 : l1;    // 有且仅有一个为空，则返回非空节点
        }

        ListNode* node = nullptr;
        if (l1->val > l2->val) {
            node = l2;
            node->next = mergeTwoLists(l1, l2->next);
        } else {
            node = l1;
            node->next = mergeTwoLists(l1->next, l2);
        }

        return node;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
