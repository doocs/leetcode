# [2674. Split a Circular Linked List](https://leetcode.com/problems/split-a-circular-linked-list)

[中文文档](/solution/2600-2699/2674.Split%20a%20Circular%20Linked%20List/README.md)

## Description

<p>Given a <strong>circular linked list</strong> <code>list</code> of positive integers, your task is to split it into 2 <strong>circular linked lists</strong> so that the first one contains the <strong>first half</strong> of the nodes in <code>list</code> (exactly <code>ceil(list.length / 2)</code> nodes) in the same order they appeared in <code>list</code>, and the second one contains <strong>the rest</strong> of the nodes in <code>list</code> in the same order they appeared in <code>list</code>.</p>

<p>Return <em>an array answer of length 2 in which the first element is a <strong>circular linked list</strong> representing the <strong>first half</strong> and the second element is a <strong>circular linked list</strong> representing the <strong>second half</strong>.</em></p>

<div>A <strong>circular linked list</strong> is a normal linked list with the only difference being that the last node&#39;s next node, is the first node.</div>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,5,7]
<strong>Output:</strong> [[1,5],[7]]
<strong>Explanation:</strong> The initial list has 3 nodes so the first half would be the first 2 elements since ceil(3 / 2) = 2 and the rest which is 1 node is in the second half.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,6,1,5]
<strong>Output:</strong> [[2,6],[1,5]]
<strong>Explanation:</strong> The initial list has 4 nodes so the first half would be the first 2 elements since ceil(4 / 2) = 2 and the rest which is 2 nodes are in the second half.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in <code>list</code>&nbsp;is in the range <code>[2, 10<sup>5</sup>]</code></li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
	<li><font face="monospace"><code>LastNode.next = FirstNode</code></font> where <code>LastNode</code> is the last node of the list and <code>FirstNode</code> is the first one</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def splitCircularLinkedList(
        self, list: Optional[ListNode]
    ) -> List[Optional[ListNode]]:
        a = b = list
        while b.next != list and b.next.next != list:
            a = a.next
            b = b.next.next
        if b.next != list:
            b = b.next
        list2 = a.next
        b.next = list2
        a.next = list
        return [list, list2]
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
    public ListNode[] splitCircularLinkedList(ListNode list) {
        ListNode a = list, b = list;
        while (b.next != list && b.next.next != list) {
            a = a.next;
            b = b.next.next;
        }
        if (b.next != list) {
            b = b.next;
        }
        ListNode list2 = a.next;
        b.next = list2;
        a.next = list;
        return new ListNode[] {list, list2};
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
    vector<ListNode*> splitCircularLinkedList(ListNode* list) {
        ListNode* a = list;
        ListNode* b = list;
        while (b->next != list && b->next->next != list) {
            a = a->next;
            b = b->next->next;
        }
        if (b->next != list) {
            b = b->next;
        }
        ListNode* list2 = a->next;
        b->next = list2;
        a->next = list;
        return {list, list2};
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
func splitCircularLinkedList(list *ListNode) []*ListNode {
	a, b := list, list
	for b.Next != list && b.Next.Next != list {
		a = a.Next
		b = b.Next.Next
	}
	if b.Next != list {
		b = b.Next
	}
	list2 := a.Next
	b.Next = list2
	a.Next = list
	return []*ListNode{list, list2}
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

function splitCircularLinkedList(list: ListNode | null): Array<ListNode | null> {
    let a = list;
    let b = list;
    while (b.next !== list && b.next.next !== list) {
        a = a.next;
        b = b.next.next;
    }
    if (b.next !== list) {
        b = b.next;
    }
    const list2 = a.next;
    b.next = list2;
    a.next = list;
    return [list, list2];
}
```

<!-- tabs:end -->

<!-- end -->
