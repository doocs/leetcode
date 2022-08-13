# [02.05. Sum Lists](https://leetcode.cn/problems/sum-lists-lcci)

[中文文档](/lcci/02.05.Sum%20Lists/README.md)

## Description

<p>You have two numbers represented by a linked list, where each node contains a single digit. The digits are stored in reverse order, such that the 1&#39;s digit is at the head of the list. Write a function that adds the two numbers and returns the sum as a linked list.</p>

<p>&nbsp;</p>

<p><strong>Example: </strong></p>

<pre>

<strong>Input: </strong>(7 -&gt; 1 -&gt; 6) + (5 -&gt; 9 -&gt; 2). That is, 617 + 295.

<strong>Output: </strong>2 -&gt; 1 -&gt; 9. That is, 912.

</pre>

<p><strong>Follow Up:&nbsp;</strong>Suppose the digits are stored in forward order. Repeat the above problem.</p>

<p><strong>Example: </strong></p>

<pre>

<strong>Input: </strong>(6 -&gt; 1 -&gt; 7) + (2 -&gt; 9 -&gt; 5). That is, 617 + 295.

<strong>Output: </strong>9 -&gt; 1 -&gt; 2. That is, 912.

</pre>

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
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        dummy = cur = ListNode(0)
        carry = 0
        while l1 or l2 or carry:
            carry += (0 if not l1 else l1.val) + (0 if not l2 else l2.val)
            cur.next = ListNode(carry % 10)
            cur = cur.next
            carry //= 10
            l1 = None if not l1 else l1.next
            l2 = None if not l2 else l2.next
        return dummy.next
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null || l2 != null || carry != 0) {
            int s =
                (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            carry = s / 10;
            cur.next = new ListNode(s % 10);
            cur = cur.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
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
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        ListNode* dummy = new ListNode(0);
        ListNode* cur = dummy;
        int carry = 0;
        while (l1 || l2 || carry) {
            carry += (!l1 ? 0 : l1->val) + (!l2 ? 0 : l2->val);
            cur->next = new ListNode(carry % 10);
            cur = cur->next;
            carry /= 10;
            l1 = l1 ? l1->next : l1;
            l2 = l2 ? l2->next : l2;
        }
        return dummy->next;
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
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */
var addTwoNumbers = function (l1, l2) {
    let carry = 0;
    const dummy = new ListNode(0);
    let cur = dummy;
    while (l1 || l2 || carry) {
        carry += (l1?.val || 0) + (l2?.val || 0);
        cur.next = new ListNode(carry % 10);
        carry = Math.floor(carry / 10);
        cur = cur.next;
        l1 = l1?.next;
        l2 = l2?.next;
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
func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	dummy := &ListNode{}
	cur := dummy
	carry := 0
	for l1 != nil || l2 != nil || carry > 0 {
		if l1 != nil {
			carry += l1.Val
			l1 = l1.Next
		}
		if l2 != nil {
			carry += l2.Val
			l2 = l2.Next
		}
		cur.Next = &ListNode{Val: carry % 10}
		cur = cur.Next
		carry /= 10
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

function addTwoNumbers(
    l1: ListNode | null,
    l2: ListNode | null,
): ListNode | null {
    if (l1 == null || l2 == null) {
        return l1 && l2;
    }
    const dummy = new ListNode(0);
    let cur = dummy;
    while (l1 != null || l2 != null) {
        let val = 0;
        if (l1 != null) {
            val += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            val += l2.val;
            l2 = l2.next;
        }
        if (cur.val >= 10) {
            cur.val %= 10;
            val++;
        }
        cur.next = new ListNode(val);
        cur = cur.next;
    }
    if (cur.val >= 10) {
        cur.val %= 10;
        cur.next = new ListNode(1);
    }
    return dummy.next;
}
```

### **Rust**

```rust
impl Solution {
    pub fn add_two_numbers(
        mut l1: Option<Box<ListNode>>,
        mut l2: Option<Box<ListNode>>,
    ) -> Option<Box<ListNode>> {
        let mut dummy = Some(Box::new(ListNode::new(0)));
        let mut cur = dummy.as_mut();
        while l1.is_some() || l2.is_some() {
            let mut val = 0;
            if let Some(node) = l1 {
                val += node.val;
                l1 = node.next;
            }
            if let Some(node) = l2 {
                val += node.val;
                l2 = node.next;
            }
            if let Some(node) = cur {
                if node.val >= 10 {
                    val += 1;
                    node.val %= 10;
                }
                node.next = Some(Box::new(ListNode::new(val)));
                cur = node.next.as_mut();
            }
        }
        if let Some(node) = cur {
            if node.val >= 10 {
                node.val %= 10;
                node.next = Some(Box::new(ListNode::new(1)));
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
