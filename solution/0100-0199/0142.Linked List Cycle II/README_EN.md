# [142. Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii)

[中文文档](/solution/0100-0199/0142.Linked%20List%20Cycle%20II/README.md)

## Description

<p>Given the <code>head</code> of a linked list, return <em>the node where the cycle begins. If there is no cycle, return </em><code>null</code>.</p>

<p>There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the <code>next</code> pointer. Internally, <code>pos</code> is used to denote the index of the node that tail&#39;s <code>next</code> pointer is connected to (<strong>0-indexed</strong>). It is <code>-1</code> if there is no cycle. <strong>Note that</strong> <code>pos</code> <strong>is not passed as a parameter</strong>.</p>

<p><strong>Do not modify</strong> the linked list.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0142.Linked%20List%20Cycle%20II/images/circularlinkedlist.png" style="height: 145px; width: 450px;" />
<pre>
<strong>Input:</strong> head = [3,2,0,-4], pos = 1
<strong>Output:</strong> tail connects to node index 1
<strong>Explanation:</strong> There is a cycle in the linked list, where tail connects to the second node.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0142.Linked%20List%20Cycle%20II/images/circularlinkedlist_test2.png" style="height: 105px; width: 201px;" />
<pre>
<strong>Input:</strong> head = [1,2], pos = 0
<strong>Output:</strong> tail connects to node index 0
<strong>Explanation:</strong> There is a cycle in the linked list, where tail connects to the first node.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0142.Linked%20List%20Cycle%20II/images/circularlinkedlist_test3.png" style="height: 65px; width: 65px;" />
<pre>
<strong>Input:</strong> head = [1], pos = -1
<strong>Output:</strong> no cycle
<strong>Explanation:</strong> There is no cycle in the linked list.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of the nodes in the list is in the range <code>[0, 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
	<li><code>pos</code> is <code>-1</code> or a <strong>valid index</strong> in the linked-list.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Can you solve it using <code>O(1)</code> (i.e. constant) memory?</p>

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
    def detectCycle(self, head: ListNode) -> ListNode:
        slow = fast = head
        has_cycle = False
        while not has_cycle and fast and fast.next:
            slow, fast = slow.next, fast.next.next
            has_cycle = slow == fast
        if not has_cycle:
            return None
        p = head
        while p != slow:
            p, slow = p.next, slow.next
        return p
```

### **Java**

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        boolean hasCycle = false;
        while (!hasCycle && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            hasCycle = slow == fast;
        }
        if (!hasCycle) {
            return null;
        }
        ListNode p = head;
        while (p != slow) {
            p = p.next;
            slow = slow.next;
        }
        return p;
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

function detectCycle(head: ListNode | null): ListNode | null {
    let slow = head,
        fast = head;
    while (fast) {
        slow = slow.next;
        if (!fast.next) return null;
        fast = fast.next.next;

        if (fast == slow) {
            let cur = head;
            while (cur != slow) {
                slow = slow.next;
                cur = cur.next;
            }
            return cur;
        }
    }
    return null;
}
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
    ListNode* detectCycle(ListNode* head) {
        ListNode* slow = head;
        ListNode* fast = head;
        bool hasCycle = false;
        while (!hasCycle && fast && fast->next) {
            slow = slow->next;
            fast = fast->next->next;
            hasCycle = slow == fast;
        }
        if (!hasCycle) {
            return nullptr;
        }
        ListNode* p = head;
        while (p != slow) {
            p = p->next;
            slow = slow->next;
        }
        return p;
    }
};
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
var detectCycle = function (head) {
    let slow = head;
    let fast = head;
    let hasCycle = false;
    while (!hasCycle && fast && fast.next) {
        slow = slow.next;
        fast = fast.next.next;
        hasCycle = slow == fast;
    }
    if (!hasCycle) {
        return null;
    }
    let p = head;
    while (p != slow) {
        p = p.next;
        slow = slow.next;
    }
    return p;
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
func detectCycle(head *ListNode) *ListNode {
    slow, fast := head, head
    hasCycle := false
    for !hasCycle && fast != nil && fast.Next != nil {
        slow, fast = slow.Next, fast.Next.Next
        hasCycle = slow == fast
    }
    if !hasCycle {
        return nil
    }
    p := head
    for p != slow {
        p, slow = p.Next, slow.Next
    }
    return p
}
```

### **...**

```

```

<!-- tabs:end -->
