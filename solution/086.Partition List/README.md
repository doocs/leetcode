## 分隔链表
### 题目描述

给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。

你应当保留两个分区中每个节点的初始相对位置。

示例:

```
输入: head = 1->4->3->2->5->2, x = 3
输出: 1->2->2->4->3->5
```

### 解法
维护 `left`, `right` 两个链表，遍历 `head` 链表，若对应元素值小于 `x`，将该结点插入 `left` 链表中，否则插入 `right` 链表中。最后 `right` 尾部指向空，并将 `left` 尾部指向 `right` 头部，使得它们串在一起。

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
    public ListNode partition(ListNode head, int x) {
        ListNode leftDummy = new ListNode(-1);
        ListNode rightDummy = new ListNode(-1);
        
        ListNode leftCur = leftDummy;
        ListNode rightCur = rightDummy;
        
        while (head != null) {
            if (head.val < x) {
                leftCur.next = head;
                leftCur = leftCur.next;
            } else {
                rightCur.next = head;
                rightCur = rightCur.next;
            }
            head = head.next;
        }
        
        leftCur.next = rightDummy.next;
        rightCur.next = null;
        return leftDummy.next;
        
    }
}
```