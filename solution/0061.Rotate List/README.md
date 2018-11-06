## 旋转链表
### 题目描述

给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。

**示例 1:**
```
输入: 1->2->3->4->5->NULL, k = 2
输出: 4->5->1->2->3->NULL
解释:
向右旋转 1 步: 5->1->2->3->4->NULL
向右旋转 2 步: 4->5->1->2->3->NULL
```

**示例 2:**
```
输入: 0->1->2->NULL, k = 4
输出: 2->0->1->NULL
解释:
向右旋转 1 步: 2->0->1->NULL
向右旋转 2 步: 1->2->0->NULL
向右旋转 3 步: 0->1->2->NULL
向右旋转 4 步: 2->0->1->NULL
```

### 解法
利用双指针`p`,`q`分别指向链表的头部和尾部，题目是右移 k 个位置，右移时，`q`需要指向`q`的前一个位置，似乎不太好做。换种思路，改用左移，右移 k 位相当于左移 len-k 位。循环移位即可。

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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        int len = 1;
        ListNode p = head;
        ListNode q = head;
        ListNode t = p.next;
        
        while (q.next != null) {
            ++len;
            q = q.next;
        }
        if (len == 1 || k % len == 0) {
            return head;
        }
        
        k %= len;
        
        // 右移 k 个位置，相当于左移 (len-k) 个位置
        k = len - k;
        
        for (int i = 0; i < k; ++i) {
            q.next = p;
            p.next = null;
            q = q.next;
            p = t;
            t = p.next;
        }
        
        return p;  
    }
}
```