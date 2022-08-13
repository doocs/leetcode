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

<!-- tabs:start -->

### **Python3**

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def removeDuplicateNodes(self, head: ListNode) -> ListNode:
        if head is None or head.next is None:
            return head
        cache = set()
        cache.add(head.val)
        cur, p = head, head.next
        while p:
            if p.val not in cache:
                cur.next = p
                cur = cur.next
                cache.add(p.val)
            p = p.next
        cur.next = None
        return head
```

### **Java**

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
        if (head == null || head.next == null) {
            return head;
        }
        Set<Integer> s = new HashSet<>();
        s.add(head.val);
        ListNode cur = head;
        for (ListNode p = head.next; p != null; p = p.next) {
            if (!s.contains(p.val)) {
                cur.next = p;
                cur = cur.next;
                s.add(p.val);
            }
        }
        cur.next = null;
        return head;
    }
}
```

### **JavaScript**

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
    if (head == null || head.next == null) return head;
    const cache = new Set([]);
    cache.add(head.val);
    let cur = head,
        fast = head.next;
    while (fast !== null) {
        if (!cache.has(fast.val)) {
            cur.next = fast;
            cur = cur.next;
            cache.add(fast.val);
        }
        fast = fast.next;
    }
    cur.next = null;
    return head;
};
```

### **C++**

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
        if (head == nullptr || head->next == nullptr) {
            return head;
        }
        unordered_set<int> cache = {head->val};
        ListNode* cur = head;
        for (ListNode* p = head->next; p != nullptr; p = p->next) {
            if (!cache.count(p->val)) {
                cur->next = p;
                cur = cur->next;
                cache.insert(p->val);
            }
        }
        cur->next = nullptr;
        return head;
    }
};
```

### **Go**

```go
func removeDuplicateNodes(head *ListNode) *ListNode {
	if head == nil {
		return nil
	}
	vis := map[int]bool{head.Val: true}
	p := head
	for p.Next != nil {
		if vis[p.Next.Val] {
			p.Next = p.Next.Next
		} else {
			vis[p.Next.Val] = true
			p = p.Next
		}
	}
	return head
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

function removeDuplicateNodes(head: ListNode | null): ListNode | null {
    if (head == null) {
        return head;
    }
    const set = new Set<number>([head.val]);
    let cur = head;
    while (cur.next != null) {
        if (set.has(cur.next.val)) {
            cur.next = cur.next.next;
        } else {
            set.add(cur.next.val);
            cur = cur.next;
        }
    }
    return head;
}
```

Violence (not recommended)

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
    let n1 = head;
    while (n1 != null) {
        let n2 = n1;
        while (n2.next != null) {
            if (n1.val === n2.next.val) {
                n2.next = n2.next.next;
            } else {
                n2 = n2.next;
            }
        }
        n1 = n1.next;
    }
    return head;
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
use std::collections::HashSet;

impl Solution {
    pub fn remove_duplicate_nodes(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        match head {
            None => head,
            Some(mut head) => {
                let mut set = HashSet::new();
                set.insert(head.val);
                let mut pre = &mut head;
                while let Some(cur) = &pre.next {
                    if set.contains(&cur.val) {
                        pre.next = pre.next.take().unwrap().next;
                    } else {
                        set.insert(cur.val);
                        pre = pre.next.as_mut().unwrap();
                    }
                }
                Some(head)
            }
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
