---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2816.Double%20a%20Number%20Represented%20as%20a%20Linked%20List/README_EN.md
rating: 1393
source: Weekly Contest 358 Q2
tags:
    - Stack
    - Linked List
    - Math
---

<!-- problem:start -->

# [2816. Double a Number Represented as a Linked List](https://leetcode.com/problems/double-a-number-represented-as-a-linked-list)

[中文文档](/solution/2800-2899/2816.Double%20a%20Number%20Represented%20as%20a%20Linked%20List/README.md)

## Description

<!-- description:start -->

<p>You are given the <code>head</code> of a <strong>non-empty</strong> linked list representing a non-negative integer without leading zeroes.</p>

<p>Return <em>the </em><code>head</code><em> of the linked list after <strong>doubling</strong> it</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2816.Double%20a%20Number%20Represented%20as%20a%20Linked%20List/images/example.png" style="width: 401px; height: 81px;" />
<pre>
<strong>Input:</strong> head = [1,8,9]
<strong>Output:</strong> [3,7,8]
<strong>Explanation:</strong> The figure above corresponds to the given linked list which represents the number 189. Hence, the returned linked list represents the number 189 * 2 = 378.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2816.Double%20a%20Number%20Represented%20as%20a%20Linked%20List/images/example2.png" style="width: 401px; height: 81px;" />
<pre>
<strong>Input:</strong> head = [9,9,9]
<strong>Output:</strong> [1,9,9,8]
<strong>Explanation:</strong> The figure above corresponds to the given linked list which represents the number 999. Hence, the returned linked list reprersents the number 999 * 2 = 1998. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is in the range <code>[1, 10<sup>4</sup>]</code></li>
	<li><font face="monospace"><code>0 &lt;= Node.val &lt;= 9</code></font></li>
	<li>The input is generated such that the list represents a number that does not have leading zeros, except the number <code>0</code> itself.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Reverse Linked List + Simulation

First, we reverse the linked list, then simulate the multiplication operation, and finally reverse the linked list back.

Time complexity is $O(n)$, where $n$ is the length of the linked list. Ignoring the space taken by the answer linked list, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def doubleIt(self, head: Optional[ListNode]) -> Optional[ListNode]:
        def reverse(head):
            dummy = ListNode()
            cur = head
            while cur:
                next = cur.next
                cur.next = dummy.next
                dummy.next = cur
                cur = next
            return dummy.next

        head = reverse(head)
        dummy = cur = ListNode()
        mul, carry = 2, 0
        while head:
            x = head.val * mul + carry
            carry = x // 10
            cur.next = ListNode(x % 10)
            cur = cur.next
            head = head.next
        if carry:
            cur.next = ListNode(carry)
        return reverse(dummy.next)
```

#### Java

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
    public ListNode doubleIt(ListNode head) {
        head = reverse(head);
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        int mul = 2, carry = 0;
        while (head != null) {
            int x = head.val * mul + carry;
            carry = x / 10;
            cur.next = new ListNode(x % 10);
            cur = cur.next;
            head = head.next;
        }
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return reverse(dummy.next);
    }

    private ListNode reverse(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
            cur = next;
        }
        return dummy.next;
    }
}
```

#### C++

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
    ListNode* doubleIt(ListNode* head) {
        head = reverse(head);
        ListNode* dummy = new ListNode();
        ListNode* cur = dummy;
        int mul = 2, carry = 0;
        while (head) {
            int x = head->val * mul + carry;
            carry = x / 10;
            cur->next = new ListNode(x % 10);
            cur = cur->next;
            head = head->next;
        }
        if (carry) {
            cur->next = new ListNode(carry);
        }
        return reverse(dummy->next);
    }

    ListNode* reverse(ListNode* head) {
        ListNode* dummy = new ListNode();
        ListNode* cur = head;
        while (cur) {
            ListNode* next = cur->next;
            cur->next = dummy->next;
            dummy->next = cur;
            cur = next;
        }
        return dummy->next;
    }
};
```

#### Go

```go
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func doubleIt(head *ListNode) *ListNode {
	head = reverse(head)
	dummy := &ListNode{}
	cur := dummy
	mul, carry := 2, 0
	for head != nil {
		x := head.Val*mul + carry
		carry = x / 10
		cur.Next = &ListNode{Val: x % 10}
		cur = cur.Next
		head = head.Next
	}
	if carry > 0 {
		cur.Next = &ListNode{Val: carry}
	}
	return reverse(dummy.Next)
}

func reverse(head *ListNode) *ListNode {
	dummy := &ListNode{}
	cur := head
	for cur != nil {
		next := cur.Next
		cur.Next = dummy.Next
		dummy.Next = cur
		cur = next
	}
	return dummy.Next
}
```

#### TypeScript

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

function doubleIt(head: ListNode | null): ListNode | null {
    head = reverse(head);
    const dummy = new ListNode();
    let cur = dummy;
    let mul = 2;
    let carry = 0;
    while (head) {
        const x = head.val * mul + carry;
        carry = Math.floor(x / 10);
        cur.next = new ListNode(x % 10);
        cur = cur.next;
        head = head.next;
    }
    if (carry) {
        cur.next = new ListNode(carry);
    }
    return reverse(dummy.next);
}

function reverse(head: ListNode | null): ListNode | null {
    const dummy = new ListNode();
    let cur = head;
    while (cur) {
        const next = cur.next;
        cur.next = dummy.next;
        dummy.next = cur;
        cur = next;
    }
    return dummy.next;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
