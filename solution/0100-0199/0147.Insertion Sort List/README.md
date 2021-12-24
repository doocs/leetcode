# [147. 对链表进行插入排序](https://leetcode-cn.com/problems/insertion-sort-list)

[English Version](/solution/0100-0199/0147.Insertion%20Sort%20List/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>对链表进行插入排序。</p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0147.Insertion%20Sort%20List/images/Insertion-sort-example-300px.gif"><br>
<small>插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。<br>
每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。</small></p>

<p>&nbsp;</p>

<p><strong>插入排序算法：</strong></p>

<ol>
	<li>插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。</li>
	<li>每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。</li>
	<li>重复直到所有输入数据插入完为止。</li>
</ol>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入:</strong> 4-&gt;2-&gt;1-&gt;3
<strong>输出:</strong> 1-&gt;2-&gt;3-&gt;4
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre><strong>输入:</strong> -1-&gt;5-&gt;3-&gt;4-&gt;0
<strong>输出:</strong> -1-&gt;0-&gt;3-&gt;4-&gt;5
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

遍历链表，每次将遍历到的结点 cur 与前一个结点 pre 进行值比较：

- 若结点 cur 的值比 pre 的大，说明当前 cur 已在正确的位置，直接往下遍历。
- 否则，从链表第一个结点开始遍历，将结点 cur 插入到正确的位置。

依次遍历，直至 cur 指向空，遍历结束。

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
