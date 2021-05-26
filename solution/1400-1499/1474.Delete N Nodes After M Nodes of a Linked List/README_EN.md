# [1474. Delete N Nodes After M Nodes of a Linked List](https://leetcode.com/problems/delete-n-nodes-after-m-nodes-of-a-linked-list)

[中文文档](/solution/1400-1499/1474.Delete%20N%20Nodes%20After%20M%20Nodes%20of%20a%20Linked%20List/README.md)

## Description

<p>Given the&nbsp;<code>head</code>&nbsp;of a linked list and two integers <code>m</code> and <code>n</code>. Traverse the linked list and remove some nodes&nbsp;in the following way:</p>

<ul>
	<li>Start with the head as the current node.</li>
	<li>Keep the first <code>m</code> nodes starting with the current node.</li>
	<li>Remove the next <code>n</code> nodes</li>
	<li>Keep repeating steps 2 and 3 until you reach the end of the list.</li>
</ul>

<p>Return the head of the modified list after removing the mentioned nodes.</p>

<p><strong>Follow up question:</strong> How can you solve this problem by modifying the list in-place?</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1474.Delete%20N%20Nodes%20After%20M%20Nodes%20of%20a%20Linked%20List/images/sample_1_1848.png" style="width: 620px; height: 95px;" /></strong></p>

<pre>

<strong>Input:</strong> head = [1,2,3,4,5,6,7,8,9,10,11,12,13], m = 2, n = 3

<strong>Output:</strong> [1,2,6,7,11,12]

<strong>Explanation: </strong>Keep the first (m = 2) nodes starting from the head of the linked List  (1 -&gt;2) show in black nodes.

Delete the next (n = 3) nodes (3 -&gt; 4 -&gt; 5) show in read nodes.

Continue with the same procedure until reaching the tail of the Linked List.

Head of linked list after removing nodes is returned.</pre>

<p><strong>Example 2:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1474.Delete%20N%20Nodes%20After%20M%20Nodes%20of%20a%20Linked%20List/images/sample_2_1848.png" style="width: 620px; height: 123px;" /></strong></p>

<pre>

<strong>Input:</strong> head = [1,2,3,4,5,6,7,8,9,10,11], m = 1, n = 3

<strong>Output:</strong> [1,5,9]

<strong>Explanation:</strong> Head of linked list after removing nodes is returned.</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> head = [1,2,3,4,5,6,7,8,9,10,11], m = 3, n = 1

<strong>Output:</strong> [1,2,3,5,6,7,9,10,11]

</pre>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input:</strong> head = [9,3,7,7,9,10,8,2], m = 1, n = 2

<strong>Output:</strong> [9,7,8]

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li>The given linked list will contain between&nbsp;<code>1</code>&nbsp;and&nbsp;<code>10^4</code>&nbsp;nodes.</li>
	<li>The value of each node in the linked list will be in the range<code>&nbsp;[1, 10^6]</code>.</li>
	<li><code>1 &lt;= m,n &lt;=&nbsp;1000</code></li>
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
    def deleteNodes(self, head: ListNode, m: int, n: int) -> ListNode:
        pre = head
        while pre:
            for i in range(m - 1):
                if pre:
                    pre = pre.next
            if pre is None:
                return head
            cur = pre
            for i in range(n):
                if cur:
                    cur = cur.next
            pre.next = None if cur is None else cur.next
            pre = pre.next
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
    public ListNode deleteNodes(ListNode head, int m, int n) {
        ListNode pre = head;
        while (pre != null) {
            for (int i = 0; i < m - 1 && pre != null; ++i) {
                pre = pre.next;
            }
            if (pre == null) {
                return head;
            }
            ListNode cur = pre;
            for (int i = 0; i < n && cur != null; ++i) {
                cur = cur.next;
            }
            pre.next = cur == null ? null : cur.next;
            pre = pre.next;
        }
        return head;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
