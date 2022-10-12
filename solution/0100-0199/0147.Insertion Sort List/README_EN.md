# [147. Insertion Sort List](https://leetcode.com/problems/insertion-sort-list)

[中文文档](/solution/0100-0199/0147.Insertion%20Sort%20List/README.md)

## Description

<p>Given the <code>head</code> of a singly linked list, sort the list using <strong>insertion sort</strong>, and return <em>the sorted list&#39;s head</em>.</p>

<p>The steps of the <strong>insertion sort</strong> algorithm:</p>

<ol>
	<li>Insertion sort iterates, consuming one input element each repetition and growing a sorted output list.</li>
	<li>At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list and inserts it there.</li>
	<li>It repeats until no input elements remain.</li>
</ol>

<p>The following is a graphical example of the insertion sort algorithm. The partially sorted list (black) initially contains only the first element in the list. One element (red) is removed from the input data and inserted in-place into the sorted list with each iteration.</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0147.Insertion%20Sort%20List/images/Insertion-sort-example-300px.gif" style="height:180px; width:300px" />
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0147.Insertion%20Sort%20List/images/sort1linked-list.jpg" style="width: 422px; height: 222px;" />
<pre>
<strong>Input:</strong> head = [4,2,1,3]
<strong>Output:</strong> [1,2,3,4]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0147.Insertion%20Sort%20List/images/sort2linked-list.jpg" style="width: 542px; height: 222px;" />
<pre>
<strong>Input:</strong> head = [-1,5,3,4,0]
<strong>Output:</strong> [-1,0,3,4,5]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is in the range <code>[1, 5000]</code>.</li>
	<li><code>-5000 &lt;= Node.val &lt;= 5000</code></li>
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
    def insertionSortList(self, head: ListNode) -> ListNode:
        if head is None or head.next is None:
            return head
        dummy = ListNode(head.val, head)
        pre, cur = dummy, head
        while cur:
            if pre.val <= cur.val:
                pre, cur = cur, cur.next
                continue
            p = dummy
            while p.next.val <= cur.val:
                p = p.next
            t = cur.next
            cur.next = p.next
            p.next = cur
            pre.next = t
            cur = t
        return dummy.next
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
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(head.val, head);
        ListNode pre = dummy, cur = head;
        while (cur != null) {
            if (pre.val <= cur.val) {
                pre = cur;
                cur = cur.next;
                continue;
            }
            ListNode p = dummy;
            while (p.next.val <= cur.val) {
                p = p.next;
            }
            ListNode t = cur.next;
            cur.next = p.next;
            p.next = cur;
            pre.next = t;
            cur = t;
        }
        return dummy.next;
    }
}
```

### **JavaScript**

```js
/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @return {ListNode}
 */
var insertionSortList = function (head) {
    if (head == null || head.next == null) return head;
    let dummy = new ListNode(head.val, head);
    let prev = dummy,
        cur = head;
    while (cur != null) {
        if (prev.val <= cur.val) {
            prev = cur;
            cur = cur.next;
            continue;
        }
        let p = dummy;
        while (p.next.val <= cur.val) {
            p = p.next;
        }
        let t = cur.next;
        cur.next = p.next;
        p.next = cur;
        prev.next = t;
        cur = t;
    }
    return dummy.next;
};
```

### **...**

```

```

<!-- tabs:end -->
