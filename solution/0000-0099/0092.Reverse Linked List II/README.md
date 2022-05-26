# [92. 反转链表 II](https://leetcode-cn.com/problems/reverse-linked-list-ii)

[English Version](/solution/0000-0099/0092.Reverse%20Linked%20List%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>反转从位置 <em>m</em> 到 <em>n</em> 的链表。请使用一趟扫描完成反转。</p>

<p><strong>说明:</strong><br>
1 &le;&nbsp;<em>m</em>&nbsp;&le;&nbsp;<em>n</em>&nbsp;&le; 链表长度。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> 1-&gt;2-&gt;3-&gt;4-&gt;5-&gt;NULL, <em>m</em> = 2, <em>n</em> = 4
<strong>输出:</strong> 1-&gt;4-&gt;3-&gt;2-&gt;5-&gt;NULL</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def reverseBetween(self, head: ListNode, m: int, n: int) -> ListNode:
        if head is None or head.next is None or m == n:
            return head
        dummy = ListNode(0)
        dummy.next = head
        pre, cur = dummy, head
        for _ in range(m - 1):
            pre = cur
            cur = cur.next
        p, q = pre, cur
        for _ in range(n - m + 1):
            t = cur.next
            cur.next = pre
            pre = cur
            cur = t
        p.next = pre
        q.next = cur
        return dummy.next
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
        if (head == null || head.next == null || m == n) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, cur = head;
        for (int i = 0; i < m - 1; ++i) {
            pre = cur;
            cur = cur.next;
        }
        ListNode p = pre, q = cur;
        for (int i = 0; i < n - m + 1; ++i) {
            ListNode t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
        }
        p.next = pre;
        q.next = cur;
        return dummy.next;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
