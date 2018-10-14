## 环形链表
### 题目描述

给定一个链表，判断链表中是否有环。

**进阶：**
你能否不使用额外空间解决此题？

### 解法

利用快慢指针，若快指针为 null，则不存在环，若快慢指针相遇，则存在环。

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
```