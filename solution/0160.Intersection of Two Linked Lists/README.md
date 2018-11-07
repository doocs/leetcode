## 相交链表
### 题目描述

编写一个程序，找到两个单链表相交的起始节点。


示例 1：

例如，下面的两个链表：

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
在节点 c1 开始相交。


注意：

如果两个链表没有交点，返回 null.
在返回结果后，两个链表仍须保持原有的结构。
可假定整个链表结构中没有循环。
程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。

### 解法
第一遍循环，找出两个链表的长度差N
第二遍循环，长链表先走N步，然后同时移动，判断是否有相同节点

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
            return null;
        ListNode p = headA;
        ListNode q = headB;
        int pCount = 0;
        int qCount = 0;
        while(p.next != null || q.next != null){
            if(p == q)
                return p;
            
            if(p.next != null)
                p = p.next;
            else
                qCount++;
            
            if(q.next != null)
                q = q.next;
            else
                pCount++;
        }
        if(p != q)
            return null;
        p = headA;
        q = headB;
        while(pCount-- != 0){
            p = p.next;
        }
        while(qCount-- != 0){
            q = q.next;
        }
        while(p != q){
            p = p.next;
            q = q.next;
        }
            
        return p;
            
            
    }
}
```