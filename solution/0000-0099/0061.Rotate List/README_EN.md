# [61. Rotate List](https://leetcode.com/problems/rotate-list)

[中文文档](/solution/0000-0099/0061.Rotate%20List/README.md)

## Description

<p>Given the <code>head</code> of a linked&nbsp;list, rotate the list to the right by <code>k</code> places.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0061.Rotate%20List/images/rotate1.jpg" style="width: 450px; height: 191px;" />
<pre>
<strong>Input:</strong> head = [1,2,3,4,5], k = 2
<strong>Output:</strong> [4,5,1,2,3]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0061.Rotate%20List/images/roate2.jpg" style="width: 305px; height: 350px;" />
<pre>
<strong>Input:</strong> head = [0,1,2], k = 4
<strong>Output:</strong> [2,0,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is in the range <code>[0, 500]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
	<li><code>0 &lt;= k &lt;= 2 * 10<sup>9</sup></code></li>
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
    def rotateRight(self, head: ListNode, k: int) -> ListNode:
        if k == 0 or head is None or head.next is None:
            return head
        n, cur = 0, head
        while cur:
            n, cur = n + 1, cur.next
        k %= n
        if k == 0:
            return head

        slow = fast = head
        for _ in range(k):
            fast = fast.next
        while fast.next:
            slow, fast = slow.next, fast.next

        start = slow.next
        slow.next = None
        fast.next = head
        return start
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
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        int n = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            ++n;
        }
        k %= n;
        if (k == 0) {
            return head;
        }
        ListNode slow = head, fast = head;
        while (k-- > 0) {
            fast = fast.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode start = slow.next;
        slow.next = null;
        fast.next = head;
        return start;
    }
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

function rotateRight(head: ListNode | null, k: number): ListNode | null {
    if (k == 0 || head == null || head.next == null) return head;
    // mod n
    let n = 0;
    let p = head;
    while (p != null) {
        ++n;
        p = p.next;
    }
    k %= n;
    if (k == 0) return head;

    let fast = head,
        slow = head;
    for (let i = 0; i < k; ++i) {
        fast = fast.next;
    }
    while (fast.next != null) {
        slow = slow.next;
        fast = fast.next;
    }
    let start = slow.next;
    slow.next = null;
    fast.next = head;
    return start;
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

function rotateRight(head: ListNode | null, k: number): ListNode | null {
    if (head == null || k === 0) {
        return head;
    }

    let n = 0;
    let cur = head;
    while (cur != null) {
        cur = cur.next;
        n++;
    }
    k = k % n;
    if (k === 0) {
        return head;
    }

    cur = head;
    for (let i = 0; i < n - k - 1; i++) {
        cur = cur.next;
    }

    const res = cur.next;
    cur.next = null;
    cur = res;
    while (cur.next != null) {
        cur = cur.next;
    }
    cur.next = head;
    return res;
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
    ListNode* rotateRight(ListNode* head, int k) {
        if (k == 0 || !head || !head->next) {
            return head;
        }
        int n = 0;
        for (ListNode* cur = head; !!cur; cur = cur->next) {
            ++n;
        }
        k %= n;
        if (k == 0) {
            return head;
        }
        ListNode *slow = head, *fast = head;
        while (k-- > 0) {
            fast = fast->next;
        }
        while (fast->next) {
            slow = slow->next;
            fast = fast->next;
        }

        ListNode* start = slow->next;
        slow->next = nullptr;
        fast->next = head;
        return start;
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
    public ListNode RotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null)
        {
            return head;
        }
        var n = 0;
        for (ListNode cur = head; cur != null; cur = cur.next)
        {
            ++n;
        }
        k %= n;
        if (k == 0)
        {
            return head;
        }
        ListNode slow = head, fast = head;
        while (k-- > 0)
        {
            fast = fast.next;
        }
        while (fast.next != null)
        {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode start = slow.next;
        slow.next = null;
        fast.next = head;
        return start;
    }
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
    pub fn rotate_right(mut head: Option<Box<ListNode>>, mut k: i32) -> Option<Box<ListNode>> {
        if head.is_none() || k == 0 {
            return head;
        }
        let n = {
            let mut cur = &head;
            let mut res = 0;
            while cur.is_some() {
                cur = &cur.as_ref().unwrap().next;
                res += 1;
            }
            res
        };
        k = k % n;
        if k == 0 {
            return head;
        }

        let mut cur = &mut head;
        for _ in 0..n - k - 1 {
            cur = &mut cur.as_mut().unwrap().next;
        }
        let mut res = cur.as_mut().unwrap().next.take();
        cur = &mut res;
        while cur.is_some() {
            cur = &mut cur.as_mut().unwrap().next;
        }
        *cur = head.take();
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
