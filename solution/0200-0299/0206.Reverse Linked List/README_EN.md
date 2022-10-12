# [206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list)

[中文文档](/solution/0200-0299/0206.Reverse%20Linked%20List/README.md)

## Description

<p>Given the <code>head</code> of a singly linked list, reverse the list, and return <em>the reversed list</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0206.Reverse%20Linked%20List/images/rev1ex1.jpg" style="width: 542px; height: 222px;" />
<pre>
<strong>Input:</strong> head = [1,2,3,4,5]
<strong>Output:</strong> [5,4,3,2,1]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0206.Reverse%20Linked%20List/images/rev1ex2.jpg" style="width: 182px; height: 222px;" />
<pre>
<strong>Input:</strong> head = [1,2]
<strong>Output:</strong> [2,1]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> head = []
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is the range <code>[0, 5000]</code>.</li>
	<li><code>-5000 &lt;= Node.val &lt;= 5000</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> A linked list can be reversed either iteratively or recursively. Could you implement both?</p>

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
    def reverseList(self, head: ListNode) -> ListNode:
        dummy = ListNode()
        curr = head
        while curr:
            next = curr.next
            curr.next = dummy.next
            dummy.next = curr
            curr = next
        return dummy.next
```

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reverseList(self, head: ListNode) -> ListNode:
        if head is None or head.next is None:
            return head
        ans = self.reverseList(head.next)
        head.next.next = head
        head.next = None
        return ans
```

### **Java**

Iterative version:

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
    public ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = dummy.next;
            dummy.next = curr;
            curr = next;
        }
        return dummy.next;
    }
}
```

Recursive version:

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
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode ans = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return ans;
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
var reverseList = function (head) {
    let dummy = new ListNode();
    let curr = head;
    while (curr) {
        let next = curr.next;
        curr.next = dummy.next;
        dummy.next = curr;
        curr = next;
    }
    return dummy.next;
};
```

### **Go**

```go
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func reverseList(head *ListNode) *ListNode {
	dummy := &ListNode{}
	curr := head
	for curr != nil {
		next := curr.Next
		curr.Next = dummy.Next
		dummy.Next = curr
		curr = next
	}
	return dummy.Next
}
```

```go
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func reverseList(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	ans := reverseList(head.Next)
	head.Next.Next = head
	head.Next = nil
	return ans
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
    ListNode* reverseList(ListNode* head) {
        ListNode* dummy = new ListNode();
        ListNode* curr = head;
        while (curr) {
            ListNode* next = curr->next;
            curr->next = dummy->next;
            dummy->next = curr;
            curr = next;
        }
        return dummy->next;
    }
};
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
    ListNode* reverseList(ListNode* head) {
        if (!head || !head->next) return head;
        ListNode* ans = reverseList(head->next);
        head->next->next = head;
        head->next = nullptr;
        return ans;
    }
};
```

### **C#**

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
    public ListNode ReverseList(ListNode head) {
        ListNode pre = null;
        for (ListNode p = head; p != null;)
        {
            ListNode t = p.next;
            p.next = pre;
            pre = p;
            p = t;
        }
        return pre;
    }
}
```

### **TypeScript**

Loop：

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

function reverseList(head: ListNode | null): ListNode | null {
    if (head == null) {
        return head;
    }
    let pre = null;
    let cur = head;
    while (cur != null) {
        const next = cur.next;
        cur.next = pre;
        [pre, cur] = [cur, next];
    }
    return pre;
}
```

Recursion：

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
const rev = (pre: ListNode | null, cur: ListNode | null): ListNode | null => {
    if (cur == null) {
        return pre;
    }
    const next = cur.next;
    cur.next = pre;
    return rev(cur, next);
};

function reverseList(head: ListNode | null): ListNode | null {
    if (head == null) {
        return head;
    }
    const next = head.next;
    head.next = null;
    return rev(head, next);
}
```

### **Rust**

Loop：

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
    pub fn reverse_list(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        match head {
            None => None,
            Some(mut head) => {
                let mut cur = head.next.take();
                let mut pre = Some(head);
                while let Some(mut node) = cur {
                    let next = node.next.take();
                    node.next = pre;
                    pre = Some(node);
                    cur = next;
                }
                pre
            }
        }
    }
}
```

Recursion：

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
    fn rev (pre: Option<Box<ListNode>>, cur: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        match cur {
            None => pre,
            Some(mut node) => {
                let next = node.next;
                node.next = pre;
                Self::rev(Some(node), next)
            },
        }
    }

    pub fn reverse_list(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        Self::rev(None, head)
    }
}
```

### **...**

```

```

<!-- tabs:end -->
