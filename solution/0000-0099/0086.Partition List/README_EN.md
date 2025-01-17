---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0086.Partition%20List/README_EN.md
tags:
    - Linked List
    - Two Pointers
---

<!-- problem:start -->

# [86. Partition List](https://leetcode.com/problems/partition-list)

[中文文档](/solution/0000-0099/0086.Partition%20List/README.md)

## Description

<!-- description:start -->

<p>Given the <code>head</code> of a linked list and a value <code>x</code>, partition it such that all nodes <strong>less than</strong> <code>x</code> come before nodes <strong>greater than or equal</strong> to <code>x</code>.</p>

<p>You should <strong>preserve</strong> the original relative order of the nodes in each of the two partitions.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0086.Partition%20List/images/partition.jpg" style="width: 662px; height: 222px;" />
<pre>
<strong>Input:</strong> head = [1,4,3,2,5,2], x = 3
<strong>Output:</strong> [1,2,2,4,3,5]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> head = [2,1], x = 2
<strong>Output:</strong> [1,2]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is in the range <code>[0, 200]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
	<li><code>-200 &lt;= x &lt;= 200</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We create two linked lists $l$ and $r$, one to store nodes less than $x$ and the other to store nodes greater than or equal to $x$. Then we concatenate them.

The time complexity is $O(n)$, where $n$ is the length of the original linked list. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def partition(self, head: Optional[ListNode], x: int) -> Optional[ListNode]:
        l = ListNode()
        r = ListNode()
        tl, tr = l, r
        while head:
            if head.val < x:
                tl.next = head
                tl = tl.next
            else:
                tr.next = head
                tr = tr.next
            head = head.next
        tr.next = None
        tl.next = r.next
        return l.next
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
    public ListNode partition(ListNode head, int x) {
        ListNode l = new ListNode();
        ListNode r = new ListNode();
        ListNode tl = l, tr = r;
        for (; head != null; head = head.next) {
            if (head.val < x) {
                tl.next = head;
                tl = tl.next;
            } else {
                tr.next = head;
                tr = tr.next;
            }
        }
        tr.next = null;
        tl.next = r.next;
        return l.next;
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
    ListNode* partition(ListNode* head, int x) {
        ListNode* l = new ListNode();
        ListNode* r = new ListNode();
        ListNode* tl = l;
        ListNode* tr = r;
        for (; head; head = head->next) {
            if (head->val < x) {
                tl->next = head;
                tl = tl->next;
            } else {
                tr->next = head;
                tr = tr->next;
            }
        }
        tr->next = nullptr;
        tl->next = r->next;
        return l->next;
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
func partition(head *ListNode, x int) *ListNode {
	l, r := &ListNode{}, &ListNode{}
	tl, tr := l, r
	for ; head != nil; head = head.Next {
		if head.Val < x {
			tl.Next = head
			tl = tl.Next
		} else {
			tr.Next = head
			tr = tr.Next
		}
	}
	tr.Next = nil
	tl.Next = r.Next
	return l.Next
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

function partition(head: ListNode | null, x: number): ListNode | null {
    const [l, r] = [new ListNode(), new ListNode()];
    let [tl, tr] = [l, r];
    for (; head; head = head.next) {
        if (head.val < x) {
            tl.next = head;
            tl = tl.next;
        } else {
            tr.next = head;
            tr = tr.next;
        }
    }
    tr.next = null;
    tl.next = r.next;
    return l.next;
}
```

#### Rust

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
impl Solution {
    pub fn partition(head: Option<Box<ListNode>>, x: i32) -> Option<Box<ListNode>> {
        let mut l = ListNode::new(0);
        let mut r = ListNode::new(0);
        let mut tl = &mut l;
        let mut tr = &mut r;
        let mut current = head;
        while let Some(mut node) = current {
            current = node.next.take();
            if node.val < x {
                tl.next = Some(node);
                tl = tl.next.as_mut().unwrap();
            } else {
                tr.next = Some(node);
                tr = tr.next.as_mut().unwrap();
            }
        }
        tr.next = None;
        tl.next = r.next;
        l.next
    }
}
```

#### JavaScript

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
 * @param {number} x
 * @return {ListNode}
 */
var partition = function (head, x) {
    const [l, r] = [new ListNode(), new ListNode()];
    let [tl, tr] = [l, r];
    for (; head; head = head.next) {
        if (head.val < x) {
            tl.next = head;
            tl = tl.next;
        } else {
            tr.next = head;
            tr = tr.next;
        }
    }
    tr.next = null;
    tl.next = r.next;
    return l.next;
};
```

#### C#

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
    public ListNode Partition(ListNode head, int x) {
        ListNode l = new ListNode();
        ListNode r = new ListNode();
        ListNode tl = l, tr = r;
        for (; head != null; head = head.next) {
            if (head.val < x) {
                tl.next = head;
                tl = tl.next;
            } else {
                tr.next = head;
                tr = tr.next;
            }
        }
        tr.next = null;
        tl.next = r.next;
        return l.next;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
