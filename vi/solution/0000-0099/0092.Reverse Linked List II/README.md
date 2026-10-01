---
comments: true
difficulty: Medium
tags:
    - Linked List
---

<!-- problem:start -->

# [92. Reverse Linked List II](https://leetcode.com/problems/reverse-linked-list-ii)

[中文文档](/solution/0000-0099/0092.Reverse%20Linked%20List%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Cho trước <code>head</code> của một danh sách liên kết đơn và hai số nguyên <code>left</code> và <code>right</code> sao cho <code>left &lt;= right</code>, hãy đảo ngược các nút của danh sách từ vị trí <code>left</code> đến vị trí <code>right</code>, rồi trả về <em>danh sách đã đảo ngược</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0092.Reverse%20Linked%20List%20II/images/rev2ex2.jpg" style="width: 542px; height: 222px;" />
<pre>
<strong>Đầu vào:</strong> head = [1,2,3,4,5], left = 2, right = 4
<strong>Đầu ra:</strong> [1,4,3,2,5]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> head = [5], left = 1, right = 1
<strong>Đầu ra:</strong> [5]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số nút trong danh sách là <code>n</code>.</li>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>-500 &lt;= Node.val &lt;= 500</code></li>
	<li><code>1 &lt;= left &lt;= right &lt;= n</code></li>
</ul>

<p>&nbsp;</p>
<strong>Câu hỏi mở rộng:</strong> Bạn có thể thực hiện việc này trong một lượt duyệt không?

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Mô phỏng

<!-- thinking:start -->

> **Tư duy**
>
> Chúng ta có thể tách đoạn $[left,right]$, đảo ngược nó rồi ghép trở lại, nhưng khi $left=1$, nút đầu sẽ bị thay thế và chúng ta phải ghi nhớ nhiều điểm tách. Câu hỏi mở rộng yêu cầu thực hiện trong một lượt duyệt.
>
> Mấu chốt là: chèn đầu từng nút trong phạm vi ngay sau nút đứng trước $left$. Cách này đảo ngược danh sách tại chỗ. Một nút giả xử lý trường hợp $left=1$. Duyệt đến $left-1$, sau đó chèn đầu $right-left+1$ lần.

<!-- thinking:end -->

Xác định một nút đầu giả `dummy`, trỏ đến nút đầu `head` của danh sách liên kết. Sau đó xác định một con trỏ `pre` trỏ đến `dummy`. Bắt đầu duyệt danh sách liên kết từ nút đầu giả. Khi duyệt đến nút `left`, trỏ `pre` đến nút này. Sau đó bắt đầu duyệt `right - left + 1` lần từ nút này, và chèn các nút đã duyệt vào phía sau `pre`. Cuối cùng, trả về `dummy.next`.

Độ phức tạp thời gian là $O(n)$, còn độ phức tạp không gian là $O(1)$. Trong đó, $n$ là độ dài của danh sách liên kết.

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reverseBetween(
        self, head: Optional[ListNode], left: int, right: int
    ) -> Optional[ListNode]:
        if head.next is None or left == right:
            return head
        dummy = ListNode(0, head)
        pre = dummy
        for _ in range(left - 1):
            pre = pre.next
        p, q = pre, pre.next
        cur = q
        for _ in range(right - left + 1):
            t = cur.next
            cur.next = pre
            pre, cur = cur, t
        p.next = pre
        q.next = cur
        return dummy.next
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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head.next == null || left == right) {
            return head;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        for (int i = 0; i < left - 1; ++i) {
            pre = pre.next;
        }
        ListNode p = pre;
        ListNode q = pre.next;
        ListNode cur = q;
        for (int i = 0; i < right - left + 1; ++i) {
            ListNode t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
        }
        p.next = pre;
        q.next = cur;
        return dummy.next;
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
    ListNode* reverseBetween(ListNode* head, int left, int right) {
        if (!head->next || left == right) {
            return head;
        }
        ListNode* dummy = new ListNode(0, head);
        ListNode* pre = dummy;
        for (int i = 0; i < left - 1; ++i) {
            pre = pre->next;
        }
        ListNode *p = pre, *q = pre->next;
        ListNode* cur = q;
        for (int i = 0; i < right - left + 1; ++i) {
            ListNode* t = cur->next;
            cur->next = pre;
            pre = cur;
            cur = t;
        }
        p->next = pre;
        q->next = cur;
        return dummy->next;
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
func reverseBetween(head *ListNode, left int, right int) *ListNode {
	if head.Next == nil || left == right {
		return head
	}
	dummy := &ListNode{0, head}
	pre := dummy
	for i := 0; i < left-1; i++ {
		pre = pre.Next
	}
	p, q := pre, pre.Next
	cur := q
	for i := 0; i < right-left+1; i++ {
		t := cur.Next
		cur.Next = pre
		pre = cur
		cur = t
	}
	p.Next = pre
	q.Next = cur
	return dummy.Next
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

function reverseBetween(head: ListNode | null, left: number, right: number): ListNode | null {
    const n = right - left;
    if (n === 0) {
        return head;
    }

    const dummy = new ListNode(0, head);
    let pre = null;
    let cur = dummy;
    for (let i = 0; i < left; i++) {
        pre = cur;
        cur = cur.next;
    }
    const h = pre;
    pre = null;
    for (let i = 0; i <= n; i++) {
        const next = cur.next;
        cur.next = pre;
        pre = cur;
        cur = next;
    }
    h.next.next = cur;
    h.next = pre;
    return dummy.next;
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
impl Solution {
    pub fn reverse_between(
        head: Option<Box<ListNode>>,
        left: i32,
        right: i32,
    ) -> Option<Box<ListNode>> {
        let mut dummy = Some(Box::new(ListNode { val: 0, next: head }));
        let mut pre = &mut dummy;
        for _ in 1..left {
            pre = &mut pre.as_mut().unwrap().next;
        }
        let mut cur = pre.as_mut().unwrap().next.take();
        for _ in 0..right - left + 1 {
            let mut next = cur.as_mut().unwrap().next.take();
            cur.as_mut().unwrap().next = pre.as_mut().unwrap().next.take();
            pre.as_mut().unwrap().next = cur.take();
            cur = next;
        }
        for _ in 0..right - left + 1 {
            pre = &mut pre.as_mut().unwrap().next;
        }
        pre.as_mut().unwrap().next = cur;
        dummy.unwrap().next
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
 * @param {number} left
 * @param {number} right
 * @return {ListNode}
 */
var reverseBetween = function (head, left, right) {
    if (!head.next || left == right) {
        return head;
    }
    const dummy = new ListNode(0, head);
    let pre = dummy;
    for (let i = 0; i < left - 1; ++i) {
        pre = pre.next;
    }
    const p = pre;
    const q = pre.next;
    let cur = q;
    for (let i = 0; i < right - left + 1; ++i) {
        const t = cur.next;
        cur.next = pre;
        pre = cur;
        cur = t;
    }
    p.next = pre;
    q.next = cur;
    return dummy.next;
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
    public ListNode ReverseBetween(ListNode head, int left, int right) {
        if (head.next == null || left == right) {
            return head;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        for (int i = 0; i < left - 1; ++i) {
            pre = pre.next;
        }
        ListNode p = pre;
        ListNode q = pre.next;
        ListNode cur = q;
        for (int i = 0; i < right - left + 1; ++i) {
            ListNode t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
        }
        p.next = pre;
        q.next = cur;
        return dummy.next;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
