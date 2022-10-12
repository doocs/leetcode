# [203. Remove Linked List Elements](https://leetcode.com/problems/remove-linked-list-elements)

[中文文档](/solution/0200-0299/0203.Remove%20Linked%20List%20Elements/README.md)

## Description

<p>Given the <code>head</code> of a linked list and an integer <code>val</code>, remove all the nodes of the linked list that has <code>Node.val == val</code>, and return <em>the new head</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0203.Remove%20Linked%20List%20Elements/images/removelinked-list.jpg" style="width: 500px; height: 142px;" />
<pre>
<strong>Input:</strong> head = [1,2,6,3,4,5,6], val = 6
<strong>Output:</strong> [1,2,3,4,5]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> head = [], val = 1
<strong>Output:</strong> []
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> head = [7,7,7,7], val = 7
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is in the range <code>[0, 10<sup>4</sup>]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 50</code></li>
	<li><code>0 &lt;= val &lt;= 50</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def removeElements(self, head: ListNode, val: int) -> ListNode:
        dummy = ListNode(-1, head)
        pre = dummy
        while pre.next:
            if pre.next.val != val:
                pre = pre.next
            else:
                pre.next = pre.next.next
        return dummy.next
```

### **Java**

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
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        while (pre.next != null) {
            if (pre.next.val != val)
                pre = pre.next;
            else
                pre.next = pre.next.next;
        }
        return dummy.next;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    ListNode* removeElements(ListNode* head, int val) {
        ListNode* dummy = new ListNode();
        dummy->next = head;
        ListNode* p = dummy;
        while (p->next) {
            if (p->next->val == val) {
                p->next = p->next->next;
            } else {
                p = p->next;
            }
        }
        return dummy->next;
    }
};
```

### **C#**

```cs
public class Solution {
    public ListNode RemoveElements(ListNode head, int val) {
        ListNode newHead = null;
        ListNode newTail = null;
        var current = head;
        while (current != null)
        {
            if (current.val != val)
            {
                if (newHead == null)
                {
                    newHead = newTail = current;
                }
                else
                {
                    newTail.next = current;
                    newTail = current;
                }
            }
            current = current.next;
        }
        if (newTail != null) newTail.next = null;
        return newHead;
    }
}
```

### **Go**

```go
func removeElements(head *ListNode, val int) *ListNode {
	dummy := new(ListNode)
	dummy.Next = head
	p := dummy
	for p.Next != nil {
		if p.Next.Val == val {
			p.Next = p.Next.Next
		} else {
			p = p.Next
		}
	}
	return dummy.Next
}
```

### **TypeScript**

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

function removeElements(head: ListNode | null, val: number): ListNode | null {
    const dummy: ListNode = new ListNode(0, head);
    let cur: ListNode = dummy;
    while (cur.next != null) {
        if (cur.next.val === val) {
            cur.next = cur.next.next;
        } else {
            cur = cur.next;
        }
    }
    return dummy.next;
}
```

### **Rust**

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
    pub fn remove_elements(head: Option<Box<ListNode>>, val: i32) -> Option<Box<ListNode>> {
        let mut dummy = Box::new(ListNode { val: 0, next: head });
        let mut cur = &mut dummy;
        while let Some(mut node) = cur.next.take() {
            if node.val == val {
                cur.next = node.next.take();
            } else {
                cur.next = Some(node);
                cur = cur.next.as_mut().unwrap();
            }
        }
        dummy.next.take()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
