---
comment: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/02.06.Palindrome%20Linked%20List/README_EN.md
---

# [02.06. Palindrome Linked List](https://leetcode.cn/problems/palindrome-linked-list-lcci)

[中文文档](/lcci/02.06.Palindrome%20Linked%20List/README.md)

## Description

<p>Implement a function to check if a linked list is a palindrome.</p>

<p>&nbsp;</p>

<p><strong>Example 1: </strong></p>

<pre>

<strong>Input:  </strong>1-&gt;2

<strong>Output: </strong> false

</pre>

<p><strong>Example 2: </strong></p>

<pre>

<strong>Input:  </strong>1-&gt;2-&gt;2-&gt;1

<strong>Output: </strong> true

</pre>

<p>&nbsp;</p>

<p><b>Follow up:</b><br />

Could you do it in O(n) time and O(1) space?</p>

## Solutions

### Solution 1: Fast and Slow Pointers + Reverse List

First, we check if the list is empty. If it is, we return `true` directly.

Next, we use fast and slow pointers to find the midpoint of the list. If the length of the list is odd, the slow pointer points to the midpoint. If the length of the list is even, the slow pointer points to the first of the two middle nodes.

Then, we reverse the list after the slow pointer, giving us the second half of the list, with the head node as $p$.

Finally, we loop to compare the first half and the second half of the list. If there are any unequal nodes, we return `false` directly. Otherwise, we return `true` after traversing the list.

The time complexity is $O(n)$, where $n$ is the length of the list. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None
class Solution:
    def isPalindrome(self, head: ListNode) -> bool:
        if head is None:
            return True
        slow, fast = head, head.next
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
        p = slow.next
        slow.next = None
        dummy = ListNode()
        while p:
            next = p.next
            p.next = dummy.next
            dummy.next = p
            p = next
        p = dummy.next
        while p:
            if head.val != p.val:
                return False
            head = head.next
            p = p.next
        return True
```

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
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode p = slow.next;
        slow.next = null;
        ListNode dummy = new ListNode(0);
        while (p != null) {
            ListNode next = p.next;
            p.next = dummy.next;
            dummy.next = p;
            p = next;
        }
        p = dummy.next;
        while (p != null) {
            if (head.val != p.val) {
                return false;
            }
            head = head.next;
            p = p.next;
        }
        return true;
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
    bool isPalindrome(ListNode* head) {
        if (!head) {
            return true;
        }
        ListNode* slow = head;
        ListNode* fast = head->next;
        while (fast && fast->next) {
            slow = slow->next;
            fast = fast->next->next;
        }
        ListNode* p = slow->next;
        slow->next = nullptr;
        ListNode* dummy = new ListNode(0);
        while (p) {
            ListNode* next = p->next;
            p->next = dummy->next;
            dummy->next = p;
            p = next;
        }
        p = dummy->next;
        while (p) {
            if (head->val != p->val) {
                return false;
            }
            head = head->next;
            p = p->next;
        }
        return true;
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
func isPalindrome(head *ListNode) bool {
	if head == nil {
		return true
	}
	slow, fast := head, head.Next
	for fast != nil && fast.Next != nil {
		slow, fast = slow.Next, fast.Next.Next
	}
	p := slow.Next
	slow.Next = nil
	dummy := &ListNode{}
	for p != nil {
		next := p.Next
		p.Next = dummy.Next
		dummy.Next = p
		p = next
	}
	p = dummy.Next
	for p != nil {
		if head.Val != p.Val {
			return false
		}
		head = head.Next
		p = p.Next
	}
	return true
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

function isPalindrome(head: ListNode | null): boolean {
    if (!head) {
        return true;
    }
    let slow = head;
    let fast = head.next;
    while (fast && fast.next) {
        slow = slow.next;
        fast = fast.next.next;
    }
    let p = slow.next;
    slow.next = null;
    const dummy = new ListNode(0);
    while (p) {
        const next = p.next;
        p.next = dummy.next;
        dummy.next = p;
        p = next;
    }
    p = dummy.next;
    while (p) {
        if (head.val !== p.val) {
            return false;
        }
        head = head.next;
        p = p.next;
    }
    return true;
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
 * @return {boolean}
 */
var isPalindrome = function (head) {
    if (!head) {
        return true;
    }
    let slow = head;
    let fast = head.next;
    while (fast && fast.next) {
        slow = slow.next;
        fast = fast.next.next;
    }
    let p = slow.next;
    slow.next = null;
    const dummy = new ListNode(0);
    while (p) {
        const next = p.next;
        p.next = dummy.next;
        dummy.next = p;
        p = next;
    }
    p = dummy.next;
    while (p) {
        if (head.val !== p.val) {
            return false;
        }
        head = head.next;
        p = p.next;
    }
    return true;
};
```

```cs
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public bool IsPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode p = slow.next;
        slow.next = null;
        ListNode dummy = new ListNode(0);
        while (p != null) {
            ListNode next = p.next;
            p.next = dummy.next;
            dummy.next = p;
            p = next;
        }
        p = dummy.next;
        while (p != null) {
            if (head.val != p.val) {
                return false;
            }
            head = head.next;
            p = p.next;
        }
        return true;
    }
}
```

```swift
/**
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
    func isPalindrome(_ head: ListNode?) -> Bool {
        if head == nil {
            return true
        }

        var slow = head
        var fast = head?.next
        while fast != nil && fast?.next != nil {
            slow = slow?.next
            fast = fast?.next?.next
        }

        var p = slow?.next
        slow?.next = nil
        var dummy = ListNode(0)

        while p != nil {
            let next = p?.next
            p?.next = dummy.next
            dummy.next = p
            p = next
        }

        p = dummy.next
        var currentHead = head
        while p != nil {
            if currentHead?.val != p?.val {
                return false
            }
            currentHead = currentHead?.next
            p = p?.next
        }

        return true
    }
}
```

<!-- tabs:end -->

<!-- end -->
