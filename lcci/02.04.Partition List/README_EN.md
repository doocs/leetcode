# [02.04. Partition List](https://leetcode.cn/problems/partition-list-lcci)

[中文文档](/lcci/02.04.Partition%20List/README.md)

## Description

<p>Write code to partition a linked list around a value x, such that all nodes less than x come before all nodes greater than or equal to x. If x is contained within the list, the values of x only need to be after the elements less than x (see below). The partition element x can appear anywhere in the &quot;right partition&quot;; it does not need to appear between the left and right partitions.</p>

<p><strong>Example:</strong></p>

<pre>

<strong>Input:</strong> head = 3-&gt;5-&gt;8-&gt;5-&gt;10-&gt;2-&gt;1, <em>x</em> = 5

<strong>Output:</strong> 3-&gt;1-&gt;2-&gt;10-&gt;5-&gt;5-&gt;8

</pre>

## Solutions

### Solution 1: Concatenating Lists

We create two lists, `left` and `right`, to store nodes that are less than `x` and nodes that are greater than or equal to `x`, respectively.

Then we use two pointers `p1` and `p2` to point to the last node of `left` and `right` respectively, initially both `p1` and `p2` point to a dummy head node.

Next, we traverse the list `head`. If the value of the current node is less than `x`, we add the current node to the end of the `left` list, i.e., `p1.next = head`, and then set `p1 = p1.next`; otherwise, we add the current node to the end of the `right` list, i.e., `p2.next = head`, and then set `p2 = p2.next`.

After the traversal, we point the tail node of the `left` list to the first valid node of the `right` list, i.e., `p1.next = right.next`, and then point the tail node of the `right` list to a null node, i.e., `p2.next = null`.

Finally, we return the first valid node of the `left` list.

The time complexity is $O(n)$, where $n$ is the length of the list. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def partition(self, head: ListNode, x: int) -> ListNode:
        left, right = ListNode(0), ListNode(0)
        p1, p2 = left, right
        while head:
            if head.val < x:
                p1.next = head
                p1 = p1.next
            else:
                p2.next = head
                p2 = p2.next
            head = head.next
        p1.next = right.next
        p2.next = None
        return left.next
```

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode left = new ListNode(0);
        ListNode right = new ListNode(0);
        ListNode p1 = left;
        ListNode p2 = right;
        for (; head != null; head = head.next) {
            if (head.val < x) {
                p1.next = head;
                p1 = p1.next;
            } else {
                p2.next = head;
                p2 = p2.next;
            }
        }
        p1.next = right.next;
        p2.next = null;
        return left.next;
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
    ListNode* partition(ListNode* head, int x) {
        ListNode* left = new ListNode(0);
        ListNode* right = new ListNode(0);
        ListNode* p1 = left;
        ListNode* p2 = right;
        for (; head; head = head->next) {
            if (head->val < x) {
                p1->next = head;
                p1 = p1->next;
            } else {
                p2->next = head;
                p2 = p2->next;
            }
        }
        p1->next = right->next;
        p2->next = nullptr;
        return left->next;
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
func partition(head *ListNode, x int) *ListNode {
	left, right := &ListNode{}, &ListNode{}
	p1, p2 := left, right
	for ; head != nil; head = head.Next {
		if head.Val < x {
			p1.Next = head
			p1 = p1.Next
		} else {
			p2.Next = head
			p2 = p2.Next
		}
	}
	p1.Next = right.Next
	p2.Next = nil
	return left.Next
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

function partition(head: ListNode | null, x: number): ListNode | null {
    const [left, right] = [new ListNode(), new ListNode()];
    let [p1, p2] = [left, right];
    for (; head; head = head.next) {
        if (head.val < x) {
            p1.next = head;
            p1 = p1.next;
        } else {
            p2.next = head;
            p2 = p2.next;
        }
    }
    p1.next = right.next;
    p2.next = null;
    return left.next;
}
```

<!-- tabs:end -->

<!-- end -->
