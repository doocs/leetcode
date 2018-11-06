## 删除链表中的结点
### 题目描述

删除链表中等于给定值 val 的所有节点。

示例:
```
输入: 1->2->6->3->4->5->6, val = 6
输出: 1->2->3->4->5
```

### 解法
利用链表天然的递归性。首先判断头结点 `head` 的值是否为 val，若是，那么最终链表是 head->removeElements(head.next, val)；若不是，则为 removeElements(head.next, val);

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
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        head.next = removeElements(head.next, val);
        return head.val != val ? head : head.next;
    }
}
```