# [369. 给单链表加一](https://leetcode-cn.com/problems/plus-one-linked-list)

[English Version](/solution/0300-0399/0369.Plus%20One%20Linked%20List/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>用一个 <strong>非空 </strong>单链表来表示一个非负整数，然后将这个整数加一。</p>

<p>你可以假设这个整数除了 0 本身，没有任何前导的 0。</p>

<p>这个整数的各个数位按照 <strong>高位在链表头部</strong>、<strong>低位在链表尾部&nbsp;</strong>的顺序排列。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入: </strong>[1,2,3]
<strong>输出: </strong>[1,2,4]
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

找出链表最右一个 `val ≠ 9` 的节点 target，将 target 值加 1。然后将 target 之后的所有节点值置为 0。

若遇到如 `9 -> 9 -> 9` 的链表，就找不到 target 了，因此，我们可以定义一个虚拟头节点 dummy，初始值为 0。刚开始将 target 指向 dummy，这样就确保链表一定存在一个 `val ≠ 9` 的节点了。

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
    def plusOne(self, head: ListNode) -> ListNode:
        dummy = ListNode(val=0, next=head)
        target = dummy
        while head:
            if head.val != 9:
                target = head
            head = head.next
        target.val += 1
        target = target.next
        while target:
            target.val = 0
            target = target.next
        return dummy if dummy.val else dummy.next
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
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode target = dummy;
        while (head != null) {
            if (head.val != 9) {
                target = head;
            }
            head = head.next;
        }
        target.val += 1;
        target = target.next;
        while (target != null) {
            target.val = 0;
            target = target.next;
        }
        return dummy.val == 1 ? dummy : dummy.next;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
