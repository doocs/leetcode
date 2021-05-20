# [1721. Swapping Nodes in a Linked List](https://leetcode.com/problems/swapping-nodes-in-a-linked-list)

[中文文档](/solution/1700-1799/1721.Swapping%20Nodes%20in%20a%20Linked%20List/README.md)

## Description

<p>You are given the <code>head</code> of a linked list, and an integer <code>k</code>.</p>

<p>Return <em>the head of the linked list after <strong>swapping</strong> the values of the </em><code>k<sup>th</sup></code> <em>node from the beginning and the </em><code>k<sup>th</sup></code> <em>node from the end (the list is <strong>1-indexed</strong>).</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1721.Swapping%20Nodes%20in%20a%20Linked%20List/images/linked1.jpg" style="width: 722px; height: 202px;" />
<pre>
<strong>Input:</strong> head = [1,2,3,4,5], k = 2
<strong>Output:</strong> [1,4,3,2,5]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> head = [7,9,6,6,7,8,3,0,9,5], k = 5
<strong>Output:</strong> [7,9,6,6,8,7,3,0,9,5]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> head = [1], k = 1
<strong>Output:</strong> [1]
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> head = [1,2], k = 1
<strong>Output:</strong> [2,1]
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> head = [1,2,3], k = 2
<strong>Output:</strong> [1,2,3]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is <code>n</code>.</li>
	<li><code>1 &lt;= k &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= Node.val &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def swapNodes(self, head: ListNode, k: int) -> ListNode:
        fast = head
        for _ in range(k - 1):
            fast = fast.next
        p = fast
        slow = head
        while fast.next:
            slow, fast = slow.next, fast.next
        q = slow
        p.val, q.val = q.val, p.val
        return head
```

### **Java**

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
    public ListNode swapNodes(ListNode head, int k) {
        ListNode fast = head;
        while (--k > 0) {
            fast = fast.next;
        }
        ListNode p = fast;
        ListNode slow = head;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        ListNode q = slow;
        int t = p.val;
        p.val = q.val;
        q.val = t;
        return head;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
