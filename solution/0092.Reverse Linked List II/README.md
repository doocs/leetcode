## 反转链表 II
### 题目描述

反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。

说明:
1 ≤ m ≤ n ≤ 链表长度。

示例:

```
输入: 1->2->3->4->5->NULL, m = 2, n = 4
输出: 1->4->3->2->5->NULL
```

### 解法
利用头插法，对 [m + 1, n] 范围内的元素逐一插入。

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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        int i = 0;
        for (; i < m - 1; ++i) {
            pre = pre.next;
        }
        
        ListNode head2 = pre;
        pre = pre.next;
        ListNode cur = pre.next;
        for (; i < n - 1; ++i) {
            pre.next = cur.next;
            cur.next = head2.next;
            head2.next = cur;
            cur = pre.next;
        }
        
        return dummy.next;
        
        
    }
}
```