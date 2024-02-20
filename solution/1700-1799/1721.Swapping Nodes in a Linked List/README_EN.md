# [1721. Swapping Nodes in a Linked List](https://leetcode.com/problems/swapping-nodes-in-a-linked-list)

[中文文档](/solution/1700-1799/1721.Swapping%20Nodes%20in%20a%20Linked%20List/README.md)

<!-- tags:Linked List,Two Pointers -->

## Description

<p>You are given the <code>head</code> of a linked list, and an integer <code>k</code>.</p>

<p>Return <em>the head of the linked list after <strong>swapping</strong> the values of the </em><code>k<sup>th</sup></code> <em>node from the beginning and the </em><code>k<sup>th</sup></code> <em>node from the end (the list is <strong>1-indexed</strong>).</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1721.Swapping%20Nodes%20in%20a%20Linked%20List/images/linked1.jpg" style="width: 400px; height: 112px;" />
<pre>
<strong>Input:</strong> head = [1,2,3,4,5], k = 2
<strong>Output:</strong> [1,4,3,2,5]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> head = [7,9,6,6,7,8,3,0,9,5], k = 5
<strong>Output:</strong> [7,9,6,6,8,7,3,0,9,5]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is <code>n</code>.</li>
	<li><code>1 &lt;= k &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= Node.val &lt;= 100</code></li>
</ul>

## Solutions

### Solution 1: Two Pointers

We can first use a fast pointer `fast` to find the $k$th node of the linked list, and use a pointer `p` to point to it. Then, we use a slow pointer `slow` to start from the head node of the linked list, and move both pointers forward at the same time. When the fast pointer reaches the last node of the linked list, the slow pointer `slow` points to the $k$th node from the end of the linked list, and we use a pointer `q` to point to it. At this point, we only need to swap the values of `p` and `q`.

The time complexity is $O(n)$, where $n$ is the length of the linked list. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def swapNodes(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        fast = slow = head
        for _ in range(k - 1):
            fast = fast.next
        p = fast
        while fast.next:
            fast, slow = fast.next, slow.next
        q = slow
        p.val, q.val = q.val, p.val
        return head
```

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
            fast = fast.next;
            slow = slow.next;
        }
        ListNode q = slow;
        int t = p.val;
        p.val = q.val;
        q.val = t;
        return head;
    }
}
```

```cpp
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* swapNodes(ListNode* head, int k) {
        ListNode* fast = head;
        while (--k) {
            fast = fast->next;
        }
        ListNode* slow = head;
        ListNode* p = fast;
        while (fast->next) {
            fast = fast->next;
            slow = slow->next;
        }
        ListNode* q = slow;
        swap(p->val, q->val);
        return head;
    }
};
```

```go
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func swapNodes(head *ListNode, k int) *ListNode {
	fast := head
	for ; k > 1; k-- {
		fast = fast.Next
	}
	p := fast
	slow := head
	for fast.Next != nil {
		fast, slow = fast.Next, slow.Next
	}
	q := slow
	p.Val, q.Val = q.Val, p.Val
	return head
}
```

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

function swapNodes(head: ListNode | null, k: number): ListNode | null {
    let [fast, slow] = [head, head];
    while (--k) {
        fast = fast.next;
    }
    const p = fast;
    while (fast.next) {
        fast = fast.next;
        slow = slow.next;
    }
    const q = slow;
    [p.val, q.val] = [q.val, p.val];
    return head;
}
```

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
    public ListNode SwapNodes(ListNode head, int k) {
        ListNode fast = head;
        while (--k > 0) {
            fast = fast.next;
        }
        ListNode p = fast;
        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        ListNode q = slow;
        int t = p.val;
        p.val = q.val;
        q.val = t;
        return head;
    }
}
```

<!-- tabs:end -->

<!-- end -->
