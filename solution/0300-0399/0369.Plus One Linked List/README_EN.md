---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0369.Plus%20One%20Linked%20List/README_EN.md
tags:
    - Linked List
    - Math
---

<!-- problem:start -->

# [369. Plus One Linked List 🔒](https://leetcode.com/problems/plus-one-linked-list)

[中文文档](/solution/0300-0399/0369.Plus%20One%20Linked%20List/README.md)

## Description

<!-- description:start -->

<p>Given a non-negative integer represented as a linked list of digits, <em>plus one to the integer</em>.</p>

<p>The digits are stored such that the most significant digit is at the <code>head</code> of the list.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> head = [1,2,3]
<strong>Output:</strong> [1,2,4]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> head = [0]
<strong>Output:</strong> [1]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the linked list is in the range <code>[1, 100]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 9</code></li>
	<li>The number represented by the linked list does not contain leading zeros except for the zero itself.&nbsp;</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Linked List Traversal

We first set a dummy head node $\textit{dummy}$, initially with a value of $0$, and the successor node of $\textit{dummy}$ is the linked list $\textit{head}$.

Next, we traverse the linked list starting from the dummy head node, find the last node that is not $9$, increment its value by $1$, and set the values of all nodes after this node to $0$.

Finally, we check if the value of the dummy head node is $1$. If it is $1$, we return $\textit{dummy}$; otherwise, we return the successor node of $\textit{dummy}$.

The time complexity is $O(n)$, where $n$ is the length of the linked list. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def plusOne(self, head: Optional[ListNode]) -> Optional[ListNode]:
        dummy = ListNode(0, head)
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
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode target = dummy;
        while (head != null) {
            if (head.val != 9) {
                target = head;
            }
            head = head.next;
        }
        ++target.val;
        target = target.next;
        while (target != null) {
            target.val = 0;
            target = target.next;
        }
        return dummy.val == 1 ? dummy : dummy.next;
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
    ListNode* plusOne(ListNode* head) {
        ListNode* dummy = new ListNode(0, head);
        ListNode* target = dummy;
        for (; head; head = head->next) {
            if (head->val != 9) {
                target = head;
            }
        }
        target->val++;
        for (target = target->next; target; target = target->next) {
            target->val = 0;
        }
        return dummy->val ? dummy : dummy->next;
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
func plusOne(head *ListNode) *ListNode {
	dummy := &ListNode{0, head}
	target := dummy
	for head != nil {
		if head.Val != 9 {
			target = head
		}
		head = head.Next
	}
	target.Val++
	for target = target.Next; target != nil; target = target.Next {
		target.Val = 0
	}
	if dummy.Val == 1 {
		return dummy
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

function plusOne(head: ListNode | null): ListNode | null {
    const dummy = new ListNode(0, head);
    let target = dummy;
    while (head) {
        if (head.val !== 9) {
            target = head;
        }
        head = head.next;
    }
    target.val++;
    for (target = target.next; target; target = target.next) {
        target.val = 0;
    }
    return dummy.val ? dummy : dummy.next;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
