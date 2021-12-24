# [25. K 个一组翻转链表](https://leetcode-cn.com/problems/reverse-nodes-in-k-group)

[English Version](/solution/0000-0099/0025.Reverse%20Nodes%20in%20k-Group/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个链表，每 <em>k </em>个节点一组进行翻转，请你返回翻转后的链表。</p>

<p><em>k </em>是一个正整数，它的值小于或等于链表的长度。</p>

<p>如果节点总数不是 <em>k </em>的整数倍，那么请将最后剩余的节点保持原有顺序。</p>

<p><strong>进阶：</strong></p>

<ul>
	<li>你可以设计一个只使用常数额外空间的算法来解决此问题吗？</li>
	<li><strong>你不能只是单纯的改变节点内部的值</strong>，而是需要实际进行节点交换。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0025.Reverse%20Nodes%20in%20k-Group/images/reverse_ex1.jpg" style="width: 542px; height: 222px;" />
<pre>
<strong>输入：</strong>head = [1,2,3,4,5], k = 2
<strong>输出：</strong>[2,1,4,3,5]
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0025.Reverse%20Nodes%20in%20k-Group/images/reverse_ex2.jpg" style="width: 542px; height: 222px;" />
<pre>
<strong>输入：</strong>head = [1,2,3,4,5], k = 3
<strong>输出：</strong>[3,2,1,4,5]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>head = [1,2,3,4,5], k = 1
<strong>输出：</strong>[1,2,3,4,5]
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>head = [1], k = 1
<strong>输出：</strong>[1]
</pre>

<ul>
</ul>

<p><strong>提示：</strong></p>

<ul>
	<li>列表中节点的数量在范围 <code>sz</code> 内</li>
	<li><code>1 <= sz <= 5000</code></li>
	<li><code>0 <= Node.val <= 1000</code></li>
	<li><code>1 <= k <= sz</code></li>
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
    def reverseKGroup(self, head: ListNode, k: int) -> ListNode:
        def reverseList(head):
            pre, p = None, head
            while p:
                q = p.next
                p.next = pre
                pre = p
                p = q
            return pre

        dummy = ListNode(next=head)
        pre = cur = dummy
        while cur.next:
            for _ in range(k):
                cur = cur.next
                if cur is None:
                    return dummy.next
            t = cur.next
            cur.next = None
            start = pre.next
            pre.next = reverseList(start)
            start.next = t
            pre = start
            cur = pre
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
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy, cur = dummy;
        while (cur.next != null) {
            for (int i = 0; i < k && cur != null; ++i) {
                cur = cur.next;
            }
            if (cur == null) {
                return dummy.next;
            }
            ListNode t = cur.next;
            cur.next = null;
            ListNode start = pre.next;
            pre.next = reverseList(start);
            start.next = t;
            pre = start;
            cur = pre;
        }
        return dummy.next;
    }

    private ListNode reverseList(ListNode head) {
        ListNode pre = null, p = head;
        while (p != null) {
            ListNode q = p.next;
            p.next = pre;
            pre = p;
            p = q;
        }
        return pre;
    }
}
```

### **TypeScript**

```ts
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     val: number
 *     next: ListNode | null
 *     constructor(val?: number, next?: ListNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.next = (next===undefined ? null : next)
 *     }
 * }
 */

function reverseKGroup(head: ListNode | null, k: number): ListNode | null {
    let dummy = new ListNode(0, head);
    let pre = dummy;
    // pre->head-> ... ->tail-> next
    while (head != null) {
        let tail = pre;
        for (let i = 0; i < k; ++i) {
            tail = tail.next;
            if (tail == null) {
                return dummy.next;
            }
        }
        let t = tail.next;
        [head, tail] = reverse(head, tail);
        // set next
        pre.next = head;
        tail.next = t;
        // set new pre and new head
        pre = tail;
        head = t;
    }
    return dummy.next;
}

function reverse(head: ListNode, tail: ListNode) {
    let cur = head;
    let pre = tail.next;
    // head -> next -> ... -> tail -> pre
    while (pre != tail) {
        let t = cur.next;
        cur.next = pre;
        pre = cur;
        cur = t;
    }
    return [tail, head];
}
```

### **Go**

```go
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func reverseKGroup(head *ListNode, k int) *ListNode {
	dummy := &ListNode{0, head}
	pre := dummy
	cur := dummy
	for cur.Next != nil {
		for i := 0; i < k && cur != nil; i++ {
			cur = cur.Next
		}
		if cur == nil {
			return dummy.Next
		}
		t := cur.Next
		cur.Next = nil
		start := pre.Next
		pre.Next = reverseList(start)
		start.Next = t
		pre = start
		cur = pre
	}
	return dummy.Next
}

func reverseList(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	dummyHead := &ListNode{}
	cur := head
	for cur != nil {
		tmp := cur.Next
		cur.Next = dummyHead.Next
		dummyHead.Next = cur
		cur = tmp
	}
	return dummyHead.Next
}
```

### **C#**

```cs
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int val=0, ListNode next=null) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */
public class Solution {
    public ListNode ReverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy, cur = dummy;
        while (cur.next != null)
        {
            for (int i = 0; i < k && cur != null; ++i)
            {
                cur = cur.next;
            }
            if (cur == null)
            {
                return dummy.next;
            }
            ListNode t = cur.next;
            cur.next = null;
            ListNode start = pre.next;
            pre.next = ReverseList(start);
            start.next = t;
            pre = start;
            cur = pre;
        }
        return dummy.next;
    }

    private ListNode ReverseList(ListNode head) {
        ListNode pre = null, p = head;
        while (p != null)
        {
            ListNode q = p.next;
            p.next = pre;
            pre = p;
            p = q;
        }
        return pre;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
