---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0025.Reverse%20Nodes%20in%20k-Group/README.md
tags:
    - 递归
    - 链表
---

<!-- problem:start -->

# [25. K 个一组翻转链表](https://leetcode.cn/problems/reverse-nodes-in-k-group)

[English Version](/solution/0000-0099/0025.Reverse%20Nodes%20in%20k-Group/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你链表的头节点 <code>head</code> ，每&nbsp;<code>k</code><em>&nbsp;</em>个节点一组进行翻转，请你返回修改后的链表。</p>

<p><code>k</code> 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是&nbsp;<code>k</code><em>&nbsp;</em>的整数倍，那么请将最后剩余的节点保持原有顺序。</p>

<p>你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0025.Reverse%20Nodes%20in%20k-Group/images/reverse_ex1.jpg" style="width: 542px; height: 222px;" />
<pre>
<strong>输入：</strong>head = [1,2,3,4,5], k = 2
<strong>输出：</strong>[2,1,4,3,5]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0025.Reverse%20Nodes%20in%20k-Group/images/reverse_ex2.jpg" style="width: 542px; height: 222px;" /></p>

<pre>
<strong>输入：</strong>head = [1,2,3,4,5], k = 3
<strong>输出：</strong>[3,2,1,4,5]
</pre>

<p>&nbsp;</p>
<strong>提示：</strong>

<ul>
	<li>链表中的节点数目为 <code>n</code></li>
	<li><code>1 &lt;= k &lt;= n &lt;= 5000</code></li>
	<li><code>0 &lt;= Node.val &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以设计一个只用 <code>O(1)</code> 额外内存空间的算法解决此问题吗？</p>

<ul>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以根据题意，模拟整个翻转的过程。

首先，我们定义一个辅助函数 $\textit{reverse}$，用于翻转一个链表。然后，我们定义一个虚拟头结点 $\textit{dummy}$，并将其 $\textit{next}$ 指针指向 $\textit{head}$。

接着，我们遍历链表，每次遍历 $k$ 个节点，若剩余节点不足 $k$ 个，则不进行翻转。否则，我们将 $k$ 个节点取出，然后调用 $\textit{reverse}$ 函数翻转这 $k$ 个节点。然后将翻转后的链表与原链表连接起来。继续遍历下一个 $k$ 个节点，直到遍历完整个链表。

时间复杂度 $O(n)$，其中 $n$ 为链表的长度。空间复杂度 $O(1)$。

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
