## 两两交换链表中的节点
### 题目描述

给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

示例:
```
给定 1->2->3->4, 你应该返回 2->1->4->3.
```
说明:

- 你的算法只能使用常数的额外空间。
- 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

### 解法
指针 p, q 分别指示链表的前后两个结点，利用指针 t 临时保存 q 的下一个结点地址。交换 p, q 指向。
注意链表为空或者链表个数为奇数的情况，做特殊判断。
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
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head.next;
        ListNode p = head;
        ListNode q = head.next;
        
        while (q != null) {
            ListNode t = q.next;
            q.next = p;
            if (t == null || t.next == null) {
                p.next = t;
                break;
            }
            p.next = t.next;
            p = t;
            q = p.next;
        }
        
        return pre;
    }
}
```