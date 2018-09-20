## 删除排序链表中的重复元素 II
### 题目描述

给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中**没有重复出现**的数字。

示例 1:
```
输入: 1->2->3->3->4->4->5
输出: 1->2->5
```

示例 2:
```
输入: 1->1->1->2->3
输出: 2->3
```

### 解法
利用链表的递归性，需要注意处理连续 n(n>=3) 个结点相等的情况。若相邻只有两个结点相等，则直接返回deleteDuplicates(head.next.next)；若相邻结点超过 3 个相等，返回 deleteDuplicates(head.next)。

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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        if (head.val == head.next.val) {
            if (head.next.next == null) {
                return null;
            }
            if (head.val == head.next.next.val) {
                return deleteDuplicates(head.next);
            }
            return deleteDuplicates(head.next.next);
        }
        head.next = deleteDuplicates(head.next);
        return head;
    }
}
```