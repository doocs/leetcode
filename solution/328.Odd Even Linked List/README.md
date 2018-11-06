## 奇偶链表
### 题目描述

给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。

请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。

**示例 1:**
```
输入: 1->2->3->4->5->NULL
输出: 1->3->5->2->4->NULL
```

**示例 2:**
```
输入: 2->1->3->5->6->4->7->NULL 
输出: 2->3->6->7->1->5->4->NULL
```

**说明:**

- 应当保持奇数节点和偶数节点的相对顺序。
- 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。

### 解法
利用 `pre`, `cur`指针指向链表的第 1、2 个结点，循环：将 `cur` 的下一个结点挂在 `pre` 的后面，`pre` 和 `cur` 指针向右移动。

eg. 
```
1->2->3->4->5: `pre`指向 1,`cur`指向 2
第一次循环后：1->3->2->4->5: `pre`指向 2, `cur`指向 4
...
```

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
    public ListNode oddEvenList(ListNode head) {
        // 链表结点数小于 3,直接返回
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = head, t = pre.next, cur = t;
        while (cur != null && cur.next != null) {
            pre.next = cur.next;
            cur.next = cur.next.next;
            pre.next.next = t;
            pre = pre.next;
            // cur.next可能为空，所以在下一次循环要判断 cur!= null 是否满足
            cur = cur.next;
            t = pre.next;
        }
        
        return dummy.next;
    }
}
```