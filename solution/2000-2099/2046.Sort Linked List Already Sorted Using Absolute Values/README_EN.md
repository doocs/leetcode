# [2046. Sort Linked List Already Sorted Using Absolute Values 🔒](https://leetcode.com/problems/sort-linked-list-already-sorted-using-absolute-values)

[中文文档](/solution/2000-2099/2046.Sort%20Linked%20List%20Already%20Sorted%20Using%20Absolute%20Values/README.md)

<!-- tags:Linked List,Two Pointers,Sorting -->

<!-- difficulty:Medium -->

## Description

Given the <code>head</code> of a singly linked list that is sorted in <strong>non-decreasing</strong> order using the <strong>absolute values</strong> of its nodes, return <em>the list sorted in <strong>non-decreasing</strong> order using the <strong>actual values</strong> of its nodes</em>.

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2046.Sort%20Linked%20List%20Already%20Sorted%20Using%20Absolute%20Values/images/image-20211017201240-3.png" style="width: 621px; height: 250px;" />
<pre>
<strong>Input:</strong> head = [0,2,-5,5,10,-10]
<strong>Output:</strong> [-10,-5,0,2,5,10]
<strong>Explanation:</strong>
The list sorted in non-descending order using the absolute values of the nodes is [0,2,-5,5,10,-10].
The list sorted in non-descending order using the actual values is [-10,-5,0,2,5,10].
</pre>

<p><strong class="example">Example 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2046.Sort%20Linked%20List%20Already%20Sorted%20Using%20Absolute%20Values/images/image-20211017201318-4.png" style="width: 338px; height: 250px;" />
<pre>
<strong>Input:</strong> head = [0,1,2]
<strong>Output:</strong> [0,1,2]
<strong>Explanation:</strong>
The linked list is already sorted in non-decreasing order.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> head = [1]
<strong>Output:</strong> [1]
<strong>Explanation:</strong>
The linked list is already sorted in non-decreasing order.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is the range <code>[1, 10<sup>5</sup>]</code>.</li>
	<li><code>-5000 &lt;= Node.val &lt;= 5000</code></li>
	<li><code>head</code> is sorted in non-decreasing order using the absolute value of its nodes.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong>
<ul>
	<li>Can you think of a solution with <code>O(n)</code> time complexity?</li>
</ul>

## Solutions

### Solution 1: Head Insertion Method

We first assume that the first node is already sorted. Starting from the second node, when we encounter a node with a negative value, we use the head insertion method. For non-negative values, we continue to traverse down.

The time complexity is $O(n)$, where $n$ is the length of the linked list. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def sortLinkedList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        prev, curr = head, head.next
        while curr:
            if curr.val < 0:
                t = curr.next
                prev.next = t
                curr.next = head
                head = curr
                curr = t
            else:
                prev, curr = curr, curr.next
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
    public ListNode sortLinkedList(ListNode head) {
        ListNode prev = head, curr = head.next;
        while (curr != null) {
            if (curr.val < 0) {
                ListNode t = curr.next;
                prev.next = t;
                curr.next = head;
                head = curr;
                curr = t;
            } else {
                prev = curr;
                curr = curr.next;
            }
        }
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
    ListNode* sortLinkedList(ListNode* head) {
        ListNode* prev = head;
        ListNode* curr = head->next;
        while (curr) {
            if (curr->val < 0) {
                auto t = curr->next;
                prev->next = t;
                curr->next = head;
                head = curr;
                curr = t;
            } else {
                prev = curr;
                curr = curr->next;
            }
        }
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
func sortLinkedList(head *ListNode) *ListNode {
	prev, curr := head, head.Next
	for curr != nil {
		if curr.Val < 0 {
			t := curr.Next
			prev.Next = t
			curr.Next = head
			head = curr
			curr = t
		} else {
			prev, curr = curr, curr.Next
		}
	}
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

function sortLinkedList(head: ListNode | null): ListNode | null {
    let [prev, curr] = [head, head.next];
    while (curr !== null) {
        if (curr.val < 0) {
            const t = curr.next;
            prev.next = t;
            curr.next = head;
            head = curr;
            curr = t;
        } else {
            [prev, curr] = [curr, curr.next];
        }
    }
    return head;
}
```

<!-- tabs:end -->

<!-- end -->
