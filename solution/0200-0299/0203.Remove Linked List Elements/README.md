# [203. 移除链表元素](https://leetcode-cn.com/problems/remove-linked-list-elements)

[English Version](/solution/0200-0299/0203.Remove%20Linked%20List%20Elements/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

给你一个链表的头节点 <code>head</code> 和一个整数 <code>val</code> ，请你删除链表中所有满足 <code>Node.val == val</code> 的节点，并返回 <strong>新的头节点</strong> 。
<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="/solution/0200-0299/0203.Remove Linked List Elements/images/removelinked-list.jpg" style="width: 500px; height: 142px;" />
<pre>
<strong>输入：</strong>head = [1,2,6,3,4,5,6], val = 6
<strong>输出：</strong>[1,2,3,4,5]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>head = [], val = 1
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>head = [7,7,7,7], val = 7
<strong>输出：</strong>[]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>列表中的节点在范围 <code>[0, 10<sup>4</sup>]</code> 内</li>
	<li><code>1 <= Node.val <= 50</code></li>
	<li><code>0 <= k <= 50</code></li>
</ul>


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
    def removeElements(self, head: ListNode, val: int) -> ListNode:
        dummy = ListNode(-1, head)
        pre = dummy
        while pre and pre.next:
            if pre.next.val != val:
                pre = pre.next
            else:
                pre.next = pre.next.next
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
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        while (pre != null && pre.next != null) {
            if (pre.next.val != val) pre = pre.next;
            else pre.next = pre.next.next;
        }
        return dummy.next;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
