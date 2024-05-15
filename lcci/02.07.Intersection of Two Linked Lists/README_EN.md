---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/02.07.Intersection%20of%20Two%20Linked%20Lists/README_EN.md
---

# [02.07. Intersection of Two Linked Lists](https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci)

[中文文档](/lcci/02.07.Intersection%20of%20Two%20Linked%20Lists/README.md)

## Description

<p>Given two (singly) linked lists, determine if the two lists intersect. Return the inter&shy; secting node. Note that the intersection is defined based on reference, not value. That is, if the kth node of the first linked list is the exact same node (by reference) as the jth node of the second linked list, then they are intersecting.</p>

<p><strong>Example 1: </strong></p>

<pre>

<strong>Input: </strong>intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3

<strong>Output: </strong>Reference of the node with value = 8

<strong>Input Explanation:</strong> The intersected node&#39;s value is 8 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,0,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.</pre>

<p><strong>Example 2: </strong></p>

<pre>

<strong>Input: </strong>intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1

<strong>Output: </strong>Reference of the node with value = 2

<strong>Input Explanation:</strong>&nbsp;The intersected node&#39;s value is 2 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [0,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.</pre>

<p><strong>Example 3: </strong></p>

<pre>

<strong>Input: </strong>intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2

<strong>Output: </strong>null

<strong>Input Explanation:</strong> From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.

<strong>Explanation:</strong> The two lists do not intersect, so return null.</pre>

<p><b>Notes:</b></p>

-   If the two linked lists have no intersection at all, return&nbsp;<code>null</code>.
-   The linked lists must retain their original structure after the function returns.
-   You may assume there are no cycles anywhere in the entire linked structure.
-   Your code should preferably run in O(n) time and use only O(1) memory.

## Solutions

### Solution 1: Two Pointers

We use two pointers $a$ and $b$ to point to two linked lists $headA$ and $headB$ respectively.

We traverse the linked lists simultaneously. When $a$ reaches the end of the linked list $headA$, it is repositioned to the head node of the linked list $headB$. When $b$ reaches the end of the linked list $headB$, it is repositioned to the head node of the linked list $headA$.

If the two pointers meet, the node they point to is the first common node. If they don't meet, it means that the two linked lists have no common nodes. At this time, both pointers point to `null`, and we can return either one.

The time complexity is $O(m+n)$, where $m$ and $n$ are the lengths of the linked lists $headA$ and $headB$ respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def getIntersectionNode(self, headA: ListNode, headB: ListNode) -> ListNode:
        a, b = headA, headB
        while a != b:
            a = a.next if a else headB
            b = b.next if b else headA
        return a
```

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
}
```

```cpp
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* getIntersectionNode(ListNode* headA, ListNode* headB) {
        ListNode *a = headA, *b = headB;
        while (a != b) {
            a = a ? a->next : headB;
            b = b ? b->next : headA;
        }
        return a;
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
func getIntersectionNode(headA, headB *ListNode) *ListNode {
	a, b := headA, headB
	for a != b {
		if a == nil {
			a = headB
		} else {
			a = a.Next
		}
		if b == nil {
			b = headA
		} else {
			b = b.Next
		}
	}
	return a
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

function getIntersectionNode(headA: ListNode | null, headB: ListNode | null): ListNode | null {
    let a = headA;
    let b = headB;
    while (a != b) {
        a = a ? a.next : headB;
        b = b ? b.next : headA;
    }
    return a;
}
```

```js
/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */

/**
 * @param {ListNode} headA
 * @param {ListNode} headB
 * @return {ListNode}
 */
var getIntersectionNode = function (headA, headB) {
    let a = headA;
    let b = headB;
    while (a != b) {
        a = a ? a.next : headB;
        b = b ? b.next : headA;
    }
    return a;
};
```

```swift
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public var val: Int
 *     public var next: ListNode?
 *     public init(_ val: Int) {
 *         self.val = val
 *         self.next = nil
 *     }
 * }
 */

class Solution {
    func getIntersectionNode(_ headA: ListNode?, _ headB: ListNode?) -> ListNode? {
        var a = headA
        var b = headB
        while a !== b {
            a = a == nil ? headB : a?.next
            b = b == nil ? headA : b?.next
        }
        return a
    }
}
```

<!-- tabs:end -->

<!-- end -->
