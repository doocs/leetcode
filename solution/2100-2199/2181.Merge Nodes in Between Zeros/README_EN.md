# [2181. Merge Nodes in Between Zeros](https://leetcode.com/problems/merge-nodes-in-between-zeros)

[中文文档](/solution/2100-2199/2181.Merge%20Nodes%20in%20Between%20Zeros/README.md)

## Description

<p>You are given the <code>head</code> of a linked list, which contains a series of integers <strong>separated</strong> by <code>0</code>&#39;s. The <strong>beginning</strong> and <strong>end</strong> of the linked list will have <code>Node.val == 0</code>.</p>

<p>For <strong>every </strong>two consecutive <code>0</code>&#39;s, <strong>merge</strong> all the nodes lying in between them into a single node whose value is the <strong>sum</strong> of all the merged nodes. The modified list should not contain any <code>0</code>&#39;s.</p>

<p>Return <em>the</em> <code>head</code> <em>of the modified linked list</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2181.Merge%20Nodes%20in%20Between%20Zeros/images/ex1-1.png" style="width: 600px; height: 41px;" />
<pre>
<strong>Input:</strong> head = [0,3,1,0,4,5,2,0]
<strong>Output:</strong> [4,11]
<strong>Explanation:</strong> 
The above figure represents the given linked list. The modified list contains
- The sum of the nodes marked in green: 3 + 1 = 4.
- The sum of the nodes marked in red: 4 + 5 + 2 = 11.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2181.Merge%20Nodes%20in%20Between%20Zeros/images/ex2-1.png" style="width: 600px; height: 41px;" />
<pre>
<strong>Input:</strong> head = [0,1,0,3,0,2,2,0]
<strong>Output:</strong> [1,3,4]
<strong>Explanation:</strong> 
The above figure represents the given linked list. The modified list contains
- The sum of the nodes marked in green: 1 = 1.
- The sum of the nodes marked in red: 3 = 3.
- The sum of the nodes marked in yellow: 2 + 2 = 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is in the range <code>[3, 2 * 10<sup>5</sup>]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 1000</code></li>
	<li>There are <strong>no</strong> two consecutive nodes with <code>Node.val == 0</code>.</li>
	<li>The <strong>beginning</strong> and <strong>end</strong> of the linked list have <code>Node.val == 0</code>.</li>
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
    def mergeNodes(self, head: Optional[ListNode]) -> Optional[ListNode]:
        dummy = tail = ListNode()
        s = 0
        cur = head.next
        while cur:
            if cur.val != 0:
                s += cur.val
            else:
                tail.next = ListNode(s)
                tail = tail.next
                s = 0
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
    public ListNode mergeNodes(ListNode head) {
        ListNode dummy = new ListNode();
        int s = 0;
        ListNode tail = dummy;
        for (ListNode cur = head.next; cur != null; cur = cur.next) {
            if (cur.val != 0) {
                s += cur.val;
            } else {
                tail.next = new ListNode(s);
                tail = tail.next;
                s = 0;
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
    ListNode* mergeNodes(ListNode* head) {
        ListNode* dummy = new ListNode();
        ListNode* tail = dummy;
        int s = 0;
        for (ListNode* cur = head->next; cur; cur = cur->next) {
            if (cur->val)
                s += cur->val;
            else {
                tail->next = new ListNode(s);
                tail = tail->next;
                s = 0;
            }
        }
        return dummy->next;
    }
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
func mergeNodes(head *ListNode) *ListNode {
	dummy := &ListNode{}
	tail := dummy
	s := 0
	for cur := head.Next; cur != nil; cur = cur.Next {
		if cur.Val != 0 {
			s += cur.Val
		} else {
			tail.Next = &ListNode{Val: s}
			tail = tail.Next
			s = 0
		}
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

function mergeNodes(head: ListNode | null): ListNode | null {
    const dummy = new ListNode();
    let cur = dummy;
    let sum = 0;
    while (head) {
        if (head.val === 0 && sum !== 0) {
            cur.next = new ListNode(sum);
            cur = cur.next;
            sum = 0;
        }
        sum += head.val;
        head = head.next;
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
    pub fn merge_nodes(mut head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut dummy = Box::new(ListNode::new(-1));
        let mut cur = &mut dummy;
        let mut sum = 0;
        while let Some(node) = head {
            if node.val == 0 && sum != 0 {
                cur.next = Some(Box::new(ListNode::new(sum)));
                cur = cur.as_mut().next.as_mut().unwrap();
                sum = 0;
            }
            sum += node.val;
            head = node.next;
        }
        dummy.next.take()
    }
}
```

### **C**

```c
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */


struct ListNode *mergeNodes(struct ListNode *head) {
    struct ListNode dummy;
    struct ListNode *cur = &dummy;
    int sum = 0;
    while (head) {
        if (head->val == 0 && sum != 0) {
            cur->next = malloc(sizeof(struct ListNode));
            cur->next->val = sum;
            cur->next->next = NULL;
            cur = cur->next;
            sum = 0;
        }
        sum += head->val;
        head = head->next;
    }
    return dummy.next;
}
```

### **...**

```

```

<!-- tabs:end -->
