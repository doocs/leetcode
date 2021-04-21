# [143. 重排链表](https://leetcode-cn.com/problems/reorder-list)

[English Version](/solution/0100-0199/0143.Reorder%20List/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个单链表&nbsp;<em>L</em>：<em>L</em><sub>0</sub>&rarr;<em>L</em><sub>1</sub>&rarr;&hellip;&rarr;<em>L</em><sub><em>n</em>-1</sub>&rarr;<em>L</em><sub>n ，</sub><br>
将其重新排列后变为： <em>L</em><sub>0</sub>&rarr;<em>L</em><sub><em>n</em></sub>&rarr;<em>L</em><sub>1</sub>&rarr;<em>L</em><sub><em>n</em>-1</sub>&rarr;<em>L</em><sub>2</sub>&rarr;<em>L</em><sub><em>n</em>-2</sub>&rarr;&hellip;</p>

<p>你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>给定链表 1-&gt;2-&gt;3-&gt;4, 重新排列为 1-&gt;4-&gt;2-&gt;3.</pre>

<p><strong>示例 2:</strong></p>

<pre>给定链表 1-&gt;2-&gt;3-&gt;4-&gt;5, 重新排列为 1-&gt;5-&gt;2-&gt;4-&gt;3.</pre>


## 解法

<!-- 这里可写通用的实现逻辑 -->

先通过快慢指针找到链表中点，将链表划分为左右两部分。之后反转右半部分的链表，然后将左右两个链接依次连接即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reorderList(self, head: ListNode) -> None:
        """
        Do not return anything, modify head in-place instead.
        """
        if head is None or head.next is None:
            return
        slow, fast = head, head.next
        # 快慢指针找到链表中点
        while fast and fast.next:
            slow, fast = slow.next, fast.next.next
        cur = slow.next
        slow.next = None
        pre = None
        # cur 指向右半部分的链表，反转
        while cur:
            t = cur.next
            cur.next = pre
            pre = cur
            cur = t
        cur = head

        # 将左右链表依次连接
        while pre:
            t1 = cur.next
            cur.next = pre
            cur = t1
            t2 = pre.next
            pre.next = t1
            pre = t2
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode cur = slow.next;
        slow.next = null;
        ListNode pre = null;
        while (cur != null) {
            ListNode t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
        }
        cur = head;
        while (pre != null) {
            ListNode t1 = cur.next;
            cur.next = pre;
            cur = t1;
            ListNode t2 = pre.next;
            pre.next = cur;
            pre = t2;
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
