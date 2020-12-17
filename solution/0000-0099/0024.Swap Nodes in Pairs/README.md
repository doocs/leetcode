# [24. 两两交换链表中的节点](https://leetcode-cn.com/problems/swap-nodes-in-pairs)

[English Version](/solution/0000-0099/0024.Swap%20Nodes%20in%20Pairs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。</p>

<p><strong>你不能只是单纯的改变节点内部的值</strong>，而是需要实际的进行节点交换。</p>

<p>&nbsp;</p>

<p><strong>示例:</strong></p>

<pre>给定 <code>1-&gt;2-&gt;3-&gt;4</code>, 你应该返回 <code>2-&gt;1-&gt;4-&gt;3</code>.
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

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
    def swapPairs(self, head: ListNode) -> ListNode:
        dummy = ListNode(next=head)
        pre, cur = dummy, head
        while cur and cur.next:
            pre.next = cur.next
            t = cur.next.next
            cur.next.next = cur
            cur.next = t
            pre = cur
            cur = cur.next
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
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy, cur = head;
        while (cur != null && cur.next != null) {
            pre.next = cur.next;
            ListNode t = cur.next.next;
            cur.next.next = cur;
            cur.next = t;
            pre = cur;
            cur = cur.next;
        }
        return dummy.next;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
