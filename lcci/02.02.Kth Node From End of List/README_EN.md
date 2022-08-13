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

<!-- tabs:start -->

### **Python3**

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
            slow, fast = slow.next, fast.next
        return slow.val
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
 * @param {number} k
 * @return {number}
 */
var kthToLast = function (head, k) {
    let fast = head,
        slow = head;
    for (let i = 0; i < k; i++) {
        fast = fast.next;
    }
    while (fast != null) {
        fast = fast.next;
        slow = slow.next;
    }
    return slow.val;
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
    int kthToLast(ListNode* head, int k) {
        ListNode* fast = head;
        ListNode* slow = head;
        while (k-- > 0) {
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

### **Go**

```go
func kthToLast(head *ListNode, k int) int {
	slow, fast := head, head
	for i := 0; i < k; i++ {
		fast = fast.Next
	}
	for fast != nil {
		slow = slow.Next
		fast = fast.Next
	}
	return slow.Val
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

### **...**

```

```

<!-- tabs:end -->
