# [82. Remove Duplicates from Sorted List II](https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii)

[中文文档](/solution/0000-0099/0082.Remove%20Duplicates%20from%20Sorted%20List%20II/README.md)

## Description

<p>Given the <code>head</code> of a sorted linked list, <em>delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list</em>. Return <em>the linked list <strong>sorted</strong> as well</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0082.Remove%20Duplicates%20from%20Sorted%20List%20II/images/linkedlist1.jpg" style="width: 500px; height: 142px;" />
<pre>
<strong>Input:</strong> head = [1,2,3,3,4,4,5]
<strong>Output:</strong> [1,2,5]
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0082.Remove%20Duplicates%20from%20Sorted%20List%20II/images/linkedlist2.jpg" style="width: 500px; height: 205px;" />
<pre>
<strong>Input:</strong> head = [1,1,1,2,3]
<strong>Output:</strong> [2,3]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is in the range <code>[0, 300]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
	<li>The list is guaranteed to be <strong>sorted</strong> in ascending order.</li>
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
    def deleteDuplicates(self, head: ListNode) -> ListNode:
        dummy = ListNode(-1, head)
        cur = dummy
        while cur.next and cur.next.next:
            if cur.next.val == cur.next.next.val:
                val = cur.next.val
                while cur.next and cur.next.val == val:
                    cur.next = cur.next.next
            else:
                cur = cur.next
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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int val = cur.next.val;
                while (cur.next != null && cur.next.val == val) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
```

### **C++**

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
    ListNode* deleteDuplicates(ListNode* head) {
        ListNode* dummy = new ListNode(-1, head);
        ListNode* cur = dummy;
        while (cur->next != nullptr && cur->next->next != nullptr) {
            if (cur->next->val == cur->next->next->val) {
                int val = cur->next->val;
                while (cur->next != nullptr && cur->next->val == val) {
                    cur->next = cur->next->next;
                }
            } else {
                cur = cur->next;
            }
        }
        return dummy->next;
    }
};
```

### **C#**

```cs
public class Solution {
    private ListNode newHead;
    private ListNode last;
    private ListNode candidate;
    private int count;

    public ListNode DeleteDuplicates(ListNode head) {
        while (head != null)
        {
            if (candidate == null || candidate.val != head.val)
            {
                TryAppend();
                candidate = head;
                count = 1;
            }
            else
            {
                ++count;
            }

            head = head.next;
        }
        TryAppend();
        if (last != null) last.next = null;
        return newHead;
    }

    private void TryAppend()
    {
        if (count == 1)
        {
            if (newHead == null)
            {
                newHead = last = candidate;
            }
            else
            {
                last.next = candidate;
                last = last.next;
            }
        }
    }
}
```

### **JavaScript**

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
 * @return {ListNode}
 */
var deleteDuplicates = function (head) {
    let cur = head;
    let pre = new ListNode(0);
    pre.next = head;
    let dummy = pre;
    let rep = false;
    if (!head || !head.next) {
        return head;
    }
    while (cur) {
        while (cur.next && cur.val == cur.next.val) {
            cur = cur.next;
            rep = true;
        }
        if (rep) {
            pre.next = cur.next;
            cur = cur.next;
        } else {
            pre = cur;
            cur = cur.next;
        }
        rep = false;
    }
    return dummy.next;
};
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

function deleteDuplicates(head: ListNode | null): ListNode | null {
    const dummy = new ListNode(101, head);
    let p = dummy;
    let c = dummy;
    let count = 1;
    while (c != null) {
        if (c.val !== (c.next ?? {}).val) {
            if (count === 1) {
                p = c;
            } else {
                p.next = c.next;
            }
            count = 0;
        }
        c = c.next;
        count++;
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
    pub fn delete_duplicates(mut head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut dummy = Some(Box::new(ListNode::new(101)));
        let mut pev = dummy.as_mut().unwrap();
        let mut cur = head;
        let mut pre = 101;
        while let Some(mut node) = cur {
            cur = node.next.take();
            if node.val == pre || (cur.is_some() && cur.as_ref().unwrap().val == node.val) {
                pre = node.val;
            } else {
                pre = node.val;
                pev.next = Some(node);
                pev = pev.next.as_mut().unwrap();
            }
        }
        dummy.unwrap().next
    }
}
```

### **...**

```

```

<!-- tabs:end -->
