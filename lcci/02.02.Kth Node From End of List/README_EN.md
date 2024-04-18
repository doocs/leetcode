# [02.02. Kth Node From End of List](https://leetcode.cn/problems/kth-node-from-end-of-list-lcci)

[中文文档](/lcci/02.02.Kth%20Node%20From%20End%20of%20List/README.md)

## Description

<p>Implement an algorithm to find the kth to last element of a singly linked list.&nbsp;Return the value of the element.</p>

<p><strong>Note: </strong>This problem is slightly different from the original one in the book.</p>

<p><strong>Example: </strong></p>

<pre>

<strong>Input: </strong> 1-&gt;2-&gt;3-&gt;4-&gt;5 和 <em>k</em> = 2

<strong>Output:  </strong>4</pre>

<p><strong>Note: </strong></p>

<p>k is always valid.</p>

## Solutions

### Solution 1: Two Pointers

We define two pointers `slow` and `fast`, both initially pointing to the head node `head`. Then the `fast` pointer moves forward $k$ steps first, and then the `slow` and `fast` pointers move forward together until the `fast` pointer points to the end of the list. At this point, the node pointed to by the `slow` pointer is the $k$-th node from the end of the list.

The time complexity is $O(n)$, where $n$ is the length of the list. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def kthToLast(self, head: ListNode, k: int) -> int:
        slow = fast = head
        for _ in range(k):
            fast = fast.next
        while fast:
            slow = slow.next
            fast = fast.next
        return slow.val
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
    public int kthToLast(ListNode head, int k) {
        ListNode slow = head, fast = head;
        while (k-- > 0) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow.val;
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
    int kthToLast(ListNode* head, int k) {
        ListNode* fast = head;
        ListNode* slow = head;
        while (k--) {
            fast = fast->next;
        }
        while (fast) {
            slow = slow->next;
            fast = fast->next;
        }
        return slow->val;
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
func kthToLast(head *ListNode, k int) int {
	slow, fast := head, head
	for ; k > 0; k-- {
		fast = fast.Next
	}
	for fast != nil {
		slow = slow.Next
		fast = fast.Next
	}
	return slow.Val
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

function kthToLast(head: ListNode | null, k: number): number {
    let [slow, fast] = [head, head];
    while (k--) {
        fast = fast.next;
    }
    while (fast !== null) {
        slow = slow.next;
        fast = fast.next;
    }
    return slow.val;
}
```

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
    pub fn kth_to_last(head: Option<Box<ListNode>>, k: i32) -> i32 {
        let mut fast = &head;
        for _ in 0..k {
            fast = &fast.as_ref().unwrap().next;
        }
        let mut slow = &head;
        while let (Some(f), Some(s)) = (fast, slow) {
            fast = &f.next;
            slow = &s.next;
        }
        slow.as_ref().unwrap().val
    }
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
 * @param {number} k
 * @return {number}
 */
var kthToLast = function (head, k) {
    let [slow, fast] = [head, head];
    while (k--) {
        fast = fast.next;
    }
    while (fast !== null) {
        slow = slow.next;
        fast = fast.next;
    }
    return slow.val;
};
```

```swift
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     var val: Int
 *     var next: ListNode?
 *     init(_ x: Int, _ next: ListNode? = nil) {
 *         self.val = x
 *         self.next = next
 *     }
 * }
 */

class Solution {
    func kthToLast(_ head: ListNode?, _ k: Int) -> Int {
        var slow = head
        var fast = head
        var k = k
        
        while k > 0 {
            fast = fast?.next
            k -= 1
        }
        
        while fast != nil {
            slow = slow?.next
            fast = fast?.next
        }
        
        return slow?.val ?? 0
    }
}
```

<!-- tabs:end -->

<!-- end -->
