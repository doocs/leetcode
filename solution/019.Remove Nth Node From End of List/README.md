## 删除链表的倒数第N个节点
### 题目描述

给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：
```
给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.
```
说明：

给定的 n 保证是有效的。

进阶：

你能尝试使用一趟扫描实现吗？

### 解法
快指针 fast 先走 n 步，接着快指针 fast 与慢指针 slow 同时前进，等到快指针指向链表最后一个结点时，停止前进。然后将 slow 的 next 指向 slow.next.next，即删除了第 n 个结点。最后返回头指针。

这里设置了 pre 虚拟结点(指向 head )是为了方便处理只有一个结点的情况。

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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(-1);
        pre.next = head;
        ListNode fast = pre;
        ListNode slow = pre;
        
        // 快指针先走 n 步
        for (int i = 0; i < n; ++i) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return pre.next;
    }
}
```