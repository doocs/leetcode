## 删除排序链表中的重复元素
### 题目描述

给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

示例 1:
```
输入: 1->1->2
输出: 1->2
```

示例 2:
```
输入: 1->1->2->3->3
输出: 1->2->3
```

### 解法
利用链表的递归性，先判断当前结点的值与下个结点值是否相等，是的话，链表为 deleteDuplicates(ListNode head)，否则为 head->deleteDuplicates(head.next)。

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
        
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
        
    }
}
```