---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0025.Reverse%20Nodes%20in%20k-Group/README_EN.md
tags:
    - Recursion
    - Linked List
---

<!-- problem:start -->

# [25. Reverse Nodes in k-Group](https://leetcode.com/problems/reverse-nodes-in-k-group)

[中文文档](/solution/0000-0099/0025.Reverse%20Nodes%20in%20k-Group/README.md)

## Description

<!-- description:start -->

<p>Given the <code>head</code> of a linked list, reverse the nodes of the list <code>k</code> at a time, and return <em>the modified list</em>.</p>

<p><code>k</code> is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of <code>k</code> then left-out nodes, in the end, should remain as it is.</p>

<p>You may not alter the values in the list&#39;s nodes, only nodes themselves may be changed.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0025.Reverse%20Nodes%20in%20k-Group/images/reverse_ex1.jpg" style="width: 542px; height: 222px;" />
<pre>
<strong>Input:</strong> head = [1,2,3,4,5], k = 2
<strong>Output:</strong> [2,1,4,3,5]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0025.Reverse%20Nodes%20in%20k-Group/images/reverse_ex2.jpg" style="width: 542px; height: 222px;" />
<pre>
<strong>Input:</strong> head = [1,2,3,4,5], k = 3
<strong>Output:</strong> [3,2,1,4,5]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is <code>n</code>.</li>
	<li><code>1 &lt;= k &lt;= n &lt;= 5000</code></li>
	<li><code>0 &lt;= Node.val &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow-up:</strong> Can you solve the problem in <code>O(1)</code> extra memory space?</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can simulate the entire reversal process according to the problem description.

First, we define a helper function $\textit{reverse}$ to reverse a linked list. Then, we define a dummy head node $\textit{dummy}$ and set its $\textit{next}$ pointer to $\textit{head}$.

Next, we traverse the linked list, processing $k$ nodes at a time. If the remaining nodes are fewer than $k$, we do not perform the reversal. Otherwise, we extract $k$ nodes and call the $\textit{reverse}$ function to reverse these $k$ nodes. Then, we connect the reversed linked list back to the original linked list. We continue to process the next $k$ nodes until the entire linked list is traversed.

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
    def reverseKGroup(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        def reverse(head: Optional[ListNode]) -> Optional[ListNode]:
            dummy = ListNode()
            cur = head
            while cur:
                nxt = cur.next
                cur.next = dummy.next
                dummy.next = cur
                cur = nxt
            return dummy.next

        dummy = pre = ListNode(next=head)
        while pre:
            cur = pre
            for _ in range(k):
                cur = cur.next
                if cur is None:
                    return dummy.next
            node = pre.next
            nxt = cur.next
            cur.next = None
            pre.next = reverse(node)
            node.next = nxt
            pre = node
        return dummy.next
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
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        dummy.next = head;
        ListNode pre = dummy;
        while (pre != null) {
            ListNode cur = pre;
            for (int i = 0; i < k; i++) {
                cur = cur.next;
                if (cur == null) {
                    return dummy.next;
                }
            }
            ListNode node = pre.next;
            ListNode nxt = cur.next;
            cur.next = null;
            pre.next = reverse(node);
            node.next = nxt;
            pre = node;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
            cur = nxt;
        }
        return dummy.next;
    }
}
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
func reverseKGroup(head *ListNode, k int) *ListNode {
	dummy := &ListNode{Next: head}
	pre := dummy

	for pre != nil {
		cur := pre
		for i := 0; i < k; i++ {
			cur = cur.Next
			if cur == nil {
				return dummy.Next
			}
		}

		node := pre.Next
		nxt := cur.Next
		cur.Next = nil
		pre.Next = reverse(node)
		node.Next = nxt
		pre = node
	}
	return dummy.Next
}

func reverse(head *ListNode) *ListNode {
	var dummy *ListNode
	cur := head
	for cur != nil {
		nxt := cur.Next
		cur.Next = dummy
		dummy = cur
		cur = nxt
	}
	return dummy
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

function reverseKGroup(head: ListNode | null, k: number): ListNode | null {
    const dummy = new ListNode(0, head);
    let pre = dummy;
    while (pre !== null) {
        let cur: ListNode | null = pre;
        for (let i = 0; i < k; i++) {
            cur = cur?.next || null;
            if (cur === null) {
                return dummy.next;
            }
        }

        const node = pre.next;
        const nxt = cur?.next || null;
        cur!.next = null;
        pre.next = reverse(node);
        node!.next = nxt;
        pre = node!;
    }

    return dummy.next;
}

function reverse(head: ListNode | null): ListNode | null {
    let dummy: ListNode | null = null;
    let cur = head;

    while (cur !== null) {
        const nxt = cur.next;
        cur.next = dummy;
        dummy = cur;
        cur = nxt;
    }

    return dummy;
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
    pub fn reverse_k_group(head: Option<Box<ListNode>>, k: i32) -> Option<Box<ListNode>> {
        fn reverse(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
            let mut head = head;
            let mut pre = None;
            while let Some(mut node) = head {
                head = node.next.take();
                node.next = pre.take();
                pre = Some(node);
            }
            pre
        }

        let mut dummy = Some(Box::new(ListNode::new(0)));
        let mut pre = &mut dummy;
        let mut cur = head;
        while cur.is_some() {
            let mut q = &mut cur;
            for _ in 0..k - 1 {
                if q.is_none() {
                    break;
                }
                q = &mut q.as_mut().unwrap().next;
            }
            if q.is_none() {
                pre.as_mut().unwrap().next = cur;
                return dummy.unwrap().next;
            }

            let b = q.as_mut().unwrap().next.take();
            pre.as_mut().unwrap().next = reverse(cur);
            while pre.is_some() && pre.as_mut().unwrap().next.is_some() {
                pre = &mut pre.as_mut().unwrap().next;
            }
            cur = b;
        }
        dummy.unwrap().next
    }
}
```

#### C#

```cs
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int val = 0, ListNode next = null) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */
public class Solution {
    public ListNode ReverseKGroup(ListNode head, int k) {
        var dummy = new ListNode(0);
        dummy.next = head;
        var pre = dummy;

        while (pre != null) {
            var cur = pre;
            for (int i = 0; i < k; i++) {
                if (cur.next == null) {
                    return dummy.next;
                }
                cur = cur.next;
            }

            var node = pre.next;
            var nxt = cur.next;
            cur.next = null;
            pre.next = Reverse(node);
            node.next = nxt;
            pre = node;
        }

        return dummy.next;
    }

    private ListNode Reverse(ListNode head) {
        ListNode prev = null;
        var cur = head;
        while (cur != null) {
            var nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
        return prev;
    }
}
```

#### PHP

```php
/**
 * Definition for a singly-linked list.
 * class ListNode {
 *     public $val = 0;
 *     public $next = null;
 *     function __construct($val = 0, $next = null) {
 *         $this->val = $val;
 *         $this->next = $next;
 *     }
 * }
 */
class Solution {
    /**
     * @param ListNode $head
     * @param Integer $k
     * @return ListNode
     */
    function reverseKGroup($head, $k) {
        $dummy = new ListNode(0);
        $dummy->next = $head;
        $pre = $dummy;

        while ($pre !== null) {
            $cur = $pre;
            for ($i = 0; $i < $k; $i++) {
                if ($cur->next === null) {
                    return $dummy->next;
                }
                $cur = $cur->next;
            }

            $node = $pre->next;
            $nxt = $cur->next;
            $cur->next = null;
            $pre->next = $this->reverse($node);
            $node->next = $nxt;
            $pre = $node;
        }

        return $dummy->next;
    }

    /**
     * Helper function to reverse a linked list.
     * @param ListNode $head
     * @return ListNode
     */
    function reverse($head) {
        $prev = null;
        $cur = $head;
        while ($cur !== null) {
            $nxt = $cur->next;
            $cur->next = $prev;
            $prev = $cur;
            $cur = $nxt;
        }
        return $prev;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
