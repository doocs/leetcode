---
comment: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/02.01.Remove%20Duplicate%20Node/README_EN.md
---

# [02.01. Remove Duplicate Node](https://leetcode.cn/problems/remove-duplicate-node-lcci)

[中文文档](/lcci/02.01.Remove%20Duplicate%20Node/README.md)

## Description

<p>Write code to remove duplicates from an unsorted linked list.</p>

<p><strong>Example1:</strong></p>

<pre>

<strong> Input</strong>: [1, 2, 3, 3, 2, 1]

<strong> Output</strong>: [1, 2, 3]

</pre>

<p><strong>Example2:</strong></p>

<pre>

<strong> Input</strong>: [1, 1, 1, 1, 2]

<strong> Output</strong>: [1, 2]

</pre>

<p><strong>Note: </strong></p>

<ol>
	<li>The length of the list is within the range[0, 20000].</li>
    <li>The values of the list elements are within the range [0, 20000].</li>
</ol>

<p><strong>Follow Up: </strong></p>

<p>How would you solve this problem if a temporary buffer is not allowed?</p>

## Solutions

### Solution 1: Hash Table

We create a hash table $vis$ to record the values of the nodes that have been visited.

Then we create a dummy node $pre$ such that $pre.next = head$.

Next, we traverse the linked list. If the value of the current node is already in the hash table, we delete the current node, i.e., $pre.next = pre.next.next$; otherwise, we add the value of the current node to the hash table and move $pre$ to the next node.

After the traversal, we return the head of the linked list.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the linked list.

<!-- tabs:start -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def removeDuplicateNodes(self, head: ListNode) -> ListNode:
        vis = set()
        pre = ListNode(0, head)
        while pre.next:
            if pre.next.val in vis:
                pre.next = pre.next.next
            else:
                vis.add(pre.next.val)
                pre = pre.next
        return head
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
    public ListNode removeDuplicateNodes(ListNode head) {
        Set<Integer> vis = new HashSet<>();
        ListNode pre = new ListNode(0, head);
        while (pre.next != null) {
            if (vis.add(pre.next.val)) {
                pre = pre.next;
            } else {
                pre.next = pre.next.next;
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
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* removeDuplicateNodes(ListNode* head) {
        unordered_set<int> vis;
        ListNode* pre = new ListNode(0, head);
        while (pre->next) {
            if (vis.count(pre->next->val)) {
                pre->next = pre->next->next;
            } else {
                vis.insert(pre->next->val);
                pre = pre->next;
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
func removeDuplicateNodes(head *ListNode) *ListNode {
	vis := map[int]bool{}
	pre := &ListNode{0, head}
	for pre.Next != nil {
		if vis[pre.Next.Val] {
			pre.Next = pre.Next.Next
		} else {
			vis[pre.Next.Val] = true
			pre = pre.Next
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

function removeDuplicateNodes(head: ListNode | null): ListNode | null {
    const vis: Set<number> = new Set();
    let pre: ListNode = new ListNode(0, head);
    while (pre.next) {
        if (vis.has(pre.next.val)) {
            pre.next = pre.next.next;
        } else {
            vis.add(pre.next.val);
            pre = pre.next;
        }
    }
    return head;
}
```

```rust
// Definition for singly-linked list.
// #[derive(PartialEq, Eq, Clone, Debug)]
// pub struct ListNode {
//   pub val: i32,
//   pub next: Option<Box<ListNode>>
// }
//
// impl ListNode {
//   #[inline]
//   fn new(val: i32) -> Self {
//     ListNode {
//       next: None,
//       val
//     }
//   }
// }
use std::collections::HashSet;

impl Solution {
    pub fn remove_duplicate_nodes(mut head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut vis = HashSet::new();
        let mut pre = ListNode::new(0);
        pre.next = head;
        let mut cur = &mut pre;
        while let Some(node) = cur.next.take() {
            if vis.contains(&node.val) {
                cur.next = node.next;
            } else {
                vis.insert(node.val);
                cur.next = Some(node);
                cur = cur.next.as_mut().unwrap();
            }
        }
        pre.next
    }
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
 * @param {ListNode} head
 * @return {ListNode}
 */
var removeDuplicateNodes = function (head) {
    const vis = new Set();
    let pre = new ListNode(0, head);
    while (pre.next) {
        if (vis.has(pre.next.val)) {
            pre.next = pre.next.next;
        } else {
            vis.add(pre.next.val);
            pre = pre.next;
        }
    }
    return head;
};
```

```swift
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *   var val: Int
 *   var next: ListNode?
 *   init(_ x: Int, _ next: ListNode? = nil) {
 *       self.val = x
 *       self.next = next
 *   }
 * }
*/

class Solution {
    func removeDuplicateNodes(_ head: ListNode?) -> ListNode? {
        var vis = Set<Int>()
        let pre = ListNode(0, head)
        var current: ListNode? = pre

        while current?.next != nil {
            if vis.insert(current!.next!.val).inserted {
                current = current?.next
            } else {
                current?.next = current?.next?.next
            }
        }

        return head
    }
}
```

<!-- tabs:end -->

<!-- end -->
