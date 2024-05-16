---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/02.08.Linked%20List%20Cycle/README_EN.md
---

<!-- problem:start -->

# [02.08. Linked List Cycle](https://leetcode.cn/problems/linked-list-cycle-lcci)

[中文文档](/lcci/02.08.Linked%20List%20Cycle/README.md)

## Description

<!-- description:start -->

<p>Given a circular linked list, implement an algorithm that returns the node at the beginning of the loop.</p>

<p>Circular linked list: A (corrupt) linked list in which a node&#39;s next pointer points to an earlier node, so as to make a loop in the linked list.</p>

<p><strong>Example 1: </strong></p>

<pre>

<strong>Input: </strong>head = [3,2,0,-4], pos = 1

<strong>Output: </strong>tail connects to node index 1</pre>

<p><strong>Example 2: </strong></p>

<pre>

<strong>Input: </strong>head = [1,2], pos = 0

<strong>Output: </strong>tail connects to node index 0</pre>

<p><strong>Example 3: </strong></p>

<pre>

<strong>Input: </strong>head = [1], pos = -1

<strong>Output: </strong>no cycle</pre>

<p><strong>Follow Up: </strong><br />

Can you solve it without using additional space?</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

We first use the fast and slow pointers to judge whether the linked list has a ring. If there is a ring, the fast and slow pointers will definitely meet, and the meeting node must be in the ring.

If there is no ring, the fast pointer will reach the tail of the linked list first, and return `null` directly.

If there is a ring, we then define an answer pointer $ans$ to point to the head of the linked list, and then let $ans$ and the slow pointer move forward together, moving one step at a time, until $ans$ and the slow pointer meet, and the meeting node is the ring entrance node.

Why can this find the entrance node of the ring?

Let's assume that the distance from the head node of the linked list to the entrance of the ring is $x$, the distance from the entrance of the ring to the meeting node is $y$, and the distance from the meeting node to the entrance of the ring is $z$. Then the distance traveled by the slow pointer is $x + y$, and the distance traveled by the fast pointer is $x + y + k \times (y + z)$, where $k$ is the number of times the fast pointer goes around the ring.

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcci/02.08.Linked%20List%20Cycle/images/linked-list-cycle-ii.png" /></p>

Because the speed of the fast pointer is twice that of the slow pointer, it is $2 \times (x + y) = x + y + k \times (y + z)$, which can be deduced that $x + y = k \times (y + z)$, that is $x = (k - 1) \times (y + z) + z$.

That is to say, if we define an answer pointer $ans$ to point to the head of the linked list, and the $ans$ and the slow pointer move forward together, they will definitely meet at the ring entrance.

The time complexity is $O(n)$, where $n$ is the number of nodes in the linked list. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def detectCycle(self, head: Optional[ListNode]) -> Optional[ListNode]:
        fast = slow = head
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
            if slow == fast:
                ans = head
                while ans != slow:
                    ans = ans.next
                    slow = slow.next
                return ans
```

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
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode ans = head;
                while (ans != slow) {
                    ans = ans.next;
                    slow = slow.next;
                }
                return ans;
            }
        }
        return null;
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
    ListNode* detectCycle(ListNode* head) {
        ListNode* fast = head;
        ListNode* slow = head;
        while (fast && fast->next) {
            slow = slow->next;
            fast = fast->next->next;
            if (slow == fast) {
                ListNode* ans = head;
                while (ans != slow) {
                    ans = ans->next;
                    slow = slow->next;
                }
                return ans;
            }
        }
        return nullptr;
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
func detectCycle(head *ListNode) *ListNode {
	fast, slow := head, head
	for fast != nil && fast.Next != nil {
		slow = slow.Next
		fast = fast.Next.Next
		if slow == fast {
			ans := head
			for ans != slow {
				ans = ans.Next
				slow = slow.Next
			}
			return ans
		}
	}
	return nil
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

function detectCycle(head: ListNode | null): ListNode | null {
    let [slow, fast] = [head, head];
    while (fast && fast.next) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow === fast) {
            let ans = head;
            while (ans !== slow) {
                ans = ans.next;
                slow = slow.next;
            }
            return ans;
        }
    }
    return null;
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
var detectCycle = function (head) {
    let [slow, fast] = [head, head];
    while (fast && fast.next) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow === fast) {
            let ans = head;
            while (ans !== slow) {
                ans = ans.next;
                slow = slow.next;
            }
            return ans;
        }
    }
    return null;
};
```

```swift
/*
* public class ListNode {
*    var val: Int
*    var next: ListNode?
*    init(_ x: Int) {
*        self.val = x
*        self.next = nil
*    }
* }
*/

class Solution {
    func detectCycle(_ head: ListNode?) -> ListNode? {
        var slow = head
        var fast = head

        while fast != nil && fast?.next != nil {
            slow = slow?.next
            fast = fast?.next?.next
            if slow === fast {
                var ans = head
                while ans !== slow {
                    ans = ans?.next
                    slow = slow?.next
                }
                return ans
            }
        }
        return nil
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
