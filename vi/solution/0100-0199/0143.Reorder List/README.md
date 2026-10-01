---
comments: true
difficulty: Medium
tags:
    - Stack
    - Recursion
    - Linked List
    - Two Pointers
---

<!-- problem:start -->

# [143. Reorder List](https://leetcode.com/problems/reorder-list)

[中文文档](/solution/0100-0199/0143.Reorder%20List/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn được cho đầu của một danh sách liên kết đơn. Danh sách có thể được biểu diễn như sau:</p>

<pre>
L<sub>0</sub> &rarr; L<sub>1</sub> &rarr; &hellip; &rarr; L<sub>n - 1</sub> &rarr; L<sub>n</sub>
</pre>

<p><em>Sắp xếp lại danh sách theo dạng sau:</em></p>

<pre>
L<sub>0</sub> &rarr; L<sub>n</sub> &rarr; L<sub>1</sub> &rarr; L<sub>n - 1</sub> &rarr; L<sub>2</sub> &rarr; L<sub>n - 2</sub> &rarr; &hellip;
</pre>

<p>Bạn không được thay đổi các giá trị trong các nút của danh sách. Chỉ được thay đổi chính các nút.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0143.Reorder%20List/images/reorder1linked-list.jpg" style="width: 422px; height: 222px;" />
<pre>
<strong>Đầu vào:</strong> head = [1,2,3,4]
<strong>Đầu ra:</strong> [1,4,2,3]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0143.Reorder%20List/images/reorder2-linked-list.jpg" style="width: 542px; height: 222px;" />
<pre>
<strong>Đầu vào:</strong> head = [1,2,3,4,5]
<strong>Đầu ra:</strong> [1,5,2,4,3]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số nút trong danh sách nằm trong khoảng <code>[1, 5 * 10<sup>4</sup>]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Con trỏ nhanh và chậm + Đảo ngược danh sách + Hợp nhất các danh sách

<!-- thinking:start -->

> **Tư duy**
>
> Sắp xếp lại thành $L_0\to L_n\to L_1\to L_{n-1}\cdots$. Một mảng các con trỏ sử dụng không gian $O(n)$. $n\le 5\times 10^4$.
>
> Nửa sau phải được đảo ngược và đan xen vào nửa đầu: tìm điểm giữa bằng hai con trỏ, tách danh sách, đảo ngược nửa sau, rồi lần lượt hợp nhất hai nửa. Ba lượt duyệt tuyến tính, thực hiện tại chỗ.

<!-- thinking:end -->

Trước tiên, chúng ta sử dụng con trỏ nhanh và chậm để tìm điểm giữa của danh sách liên kết, sau đó đảo ngược nửa sau của danh sách, và cuối cùng hợp nhất hai nửa.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của danh sách liên kết. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reorderList(self, head: Optional[ListNode]) -> None:
        fast = slow = head
        while fast.next and fast.next.next:
            slow = slow.next
            fast = fast.next.next

        cur = slow.next
        slow.next = None

        pre = None
        while cur:
            t = cur.next
            cur.next = pre
            pre, cur = cur, t
        cur = head

        while pre:
            t = pre.next
            pre.next = cur.next
            cur.next = pre
            cur, pre = pre.next, t
```

#### Java

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
    public void reorderList(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode cur = slow.next;
        slow.next = null;

        ListNode pre = null;
        while (cur != null) {
            ListNode t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
        }
        cur = head;

        while (pre != null) {
            ListNode t = pre.next;
            pre.next = cur.next;
            cur.next = pre;
            cur = pre.next;
            pre = t;
        }
    }
}
```

#### C++

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
    void reorderList(ListNode* head) {
        ListNode* fast = head;
        ListNode* slow = head;
        while (fast->next && fast->next->next) {
            slow = slow->next;
            fast = fast->next->next;
        }

        ListNode* cur = slow->next;
        slow->next = nullptr;

        ListNode* pre = nullptr;
        while (cur) {
            ListNode* t = cur->next;
            cur->next = pre;
            pre = cur;
            cur = t;
        }
        cur = head;

        while (pre) {
            ListNode* t = pre->next;
            pre->next = cur->next;
            cur->next = pre;
            cur = pre->next;
            pre = t;
        }
    }
};
```

#### Go

```go
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func reorderList(head *ListNode) {
	fast, slow := head, head
	for fast.Next != nil && fast.Next.Next != nil {
		slow, fast = slow.Next, fast.Next.Next
	}

	cur := slow.Next
	slow.Next = nil

	var pre *ListNode
	for cur != nil {
		t := cur.Next
		cur.Next = pre
		pre, cur = cur, t
	}
	cur = head

	for pre != nil {
		t := pre.Next
		pre.Next = cur.Next
		cur.Next = pre
		cur, pre = pre.Next, t
	}
}
```

#### TypeScript

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

/**
 Do not return anything, modify head in-place instead.
 */
function reorderList(head: ListNode | null): void {
    let slow = head;
    let fast = head;
    while (fast && fast.next) {
        slow = slow.next;
        fast = fast.next.next;
    }

    let next = slow.next;
    slow.next = null;
    while (next) {
        [next.next, slow, next] = [slow, next, next.next];
    }

    let left = head;
    let right = slow;
    while (right.next) {
        const next = left.next;
        left.next = right;
        right = right.next;
        left.next.next = next;
        left = left.next.next;
    }
}
```

#### Rust

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
use std::collections::VecDeque;
impl Solution {
    pub fn reorder_list(head: &mut Option<Box<ListNode>>) {
        let mut tail = &mut head.as_mut().unwrap().next;
        let mut head = tail.take();
        let mut deque = VecDeque::new();
        while head.is_some() {
            let next = head.as_mut().unwrap().next.take();
            deque.push_back(head);
            head = next;
        }
        let mut flag = false;
        while !deque.is_empty() {
            *tail = if flag {
                deque.pop_front().unwrap()
            } else {
                deque.pop_back().unwrap()
            };
            tail = &mut tail.as_mut().unwrap().next;
            flag = !flag;
        }
    }
}
```

#### JavaScript

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
 * @return {void} Do not return anything, modify head in-place instead.
 */
var reorderList = function (head) {
    let slow = head;
    let fast = head;
    while (fast.next && fast.next.next) {
        slow = slow.next;
        fast = fast.next.next;
    }

    let cur = slow.next;
    slow.next = null;

    let pre = null;
    while (cur) {
        const t = cur.next;
        cur.next = pre;
        pre = cur;
        cur = t;
    }
    cur = head;

    while (pre) {
        const t = pre.next;
        pre.next = cur.next;
        cur.next = pre;
        cur = pre.next;
        pre = t;
    }
};
```

#### C#

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
    public void ReorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode cur = slow.next;
        slow.next = null;

        ListNode pre = null;
        while (cur != null) {
            ListNode t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
        }
        cur = head;

        while (pre != null) {
            ListNode t = pre.next;
            pre.next = cur.next;
            cur.next = pre;
            cur = pre.next;
            pre = t;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
