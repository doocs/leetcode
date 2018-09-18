## 合并两个有序链表
### 题目描述

将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。

示例:
```
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
```

### 解法
利用链表天然的递归性。如果 l1 为空，返回 l2；如果 l2 为空，返回 l1。如果 `l1.val < l2.val`，返回 l1->mergeTwoLists(l1.next, l2)；否则返回 l2->mergeTwoLists(l1, l2.next)。

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
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        l2.next = mergeTwoLists(l1, l2.next);
        return l2;
    }
}
```