---
comments: true
difficulty: Medium
tags:
    - Linked List
    - Two Pointers
---

<!-- problem:start -->

# [82. Remove Duplicates from Sorted List II](https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii)

[中文文档](/solution/0000-0099/0082.Remove%20Duplicates%20from%20Sorted%20List%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Cho trước <code>head</code> của một danh sách liên kết đã được sắp xếp, hãy <em>xóa tất cả các nút có số trùng lặp, chỉ giữ lại các số phân biệt từ danh sách ban đầu</em>. <em>Trả về <strong>danh sách liên kết cũng được sắp xếp</strong>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0082.Remove%20Duplicates%20from%20Sorted%20List%20II/images/linkedlist1.jpg" style="width: 500px; height: 142px;" />
<pre>
<strong>Đầu vào:</strong> head = [1,2,3,3,4,4,5]
<strong>Đầu ra:</strong> [1,2,5]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0082.Remove%20Duplicates%20from%20Sorted%20List%20II/images/linkedlist2.jpg" style="width: 500px; height: 205px;" />
<pre>
<strong>Đầu vào:</strong> head = [1,1,1,2,3]
<strong>Đầu ra:</strong> [2,3]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số lượng nút trong danh sách nằm trong khoảng <code>[0, 300]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
	<li>Danh sách được đảm bảo <strong>được sắp xếp</strong> theo thứ tự tăng dần.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Duyệt một lượt

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là đếm tần suất, sau đó xây dựng lại một danh sách gồm các giá trị xuất hiện đúng một lần. Cách này đúng, và $n \le 300$ là rất nhỏ, nhưng nó bỏ phí lợi thế “danh sách đã được sắp xếp, nên các phần tử trùng lặp nằm cạnh nhau” và dùng thêm $O(n)$ không gian.
>
> Nút thắt nằm ở việc chưa tận dụng tính kề nhau. Các giá trị bằng nhau tạo thành một đoạn liên tiếp; một lượt duyệt có thể quyết định giữ lại hay loại bỏ toàn bộ đoạn.
>
> Đầu danh sách có thể bị xóa, nên chúng ta cần một nút giả. Và khi loại bỏ một đoạn, nút đứng trước không được tiến vào đoạn đó. Giữ $pre$ tại nút được giữ lại cuối cùng và di chuyển $cur$ qua các nút có cùng giá trị: nếu $pre.next$ vẫn là $cur$, đoạn có độ dài một và $pre$ tiến lên; nếu không, bỏ qua toàn bộ đoạn.

<!-- thinking:end -->

Đầu tiên, chúng ta tạo một nút đầu giả $dummy$, và đặt $dummy.next = head$. Sau đó, chúng ta tạo một con trỏ $pre$ trỏ tới $dummy$, cùng một con trỏ $cur$ trỏ tới $head$, rồi bắt đầu duyệt danh sách liên kết.

Khi giá trị nút mà $cur$ trỏ tới giống với giá trị nút mà $cur.next$ trỏ tới, chúng ta tiếp tục di chuyển $cur$ về phía trước cho đến khi giá trị nút mà $cur$ trỏ tới khác với giá trị nút mà $cur.next$ trỏ tới. Tại thời điểm này, chúng ta kiểm tra xem $pre.next$ có bằng $cur$ hay không. Nếu bằng nhau, điều đó có nghĩa là không có nút trùng lặp nào giữa $pre$ và $cur$, nên chúng ta di chuyển $pre$ tới vị trí của $cur$; nếu không, điều đó có nghĩa là có các nút trùng lặp giữa $pre$ và $cur$, nên chúng ta đặt $pre.next$ thành $cur.next$. Sau đó, chúng ta tiếp tục di chuyển $cur$ về phía trước. Tiếp tục thao tác trên cho đến khi $cur$ là null thì quá trình duyệt kết thúc.

Cuối cùng, trả về $dummy.next$.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(1)$. Ở đây, $n$ là độ dài của danh sách liên kết.

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def deleteDuplicates(self, head: Optional[ListNode]) -> Optional[ListNode]:
        dummy = pre = ListNode(next=head)
        cur = head
        while cur:
            while cur.next and cur.next.val == cur.val:
                cur = cur.next
            if pre.next == cur:
                pre = cur
            else:
                pre.next = cur.next
            cur = cur.next
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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            while (cur.next != null && cur.next.val == cur.val) {
                cur = cur.next;
            }
            if (pre.next == cur) {
                pre = cur;
            } else {
                pre.next = cur.next;
            }
            cur = cur.next;
        }
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
    ListNode* deleteDuplicates(ListNode* head) {
        ListNode* dummy = new ListNode(0, head);
        ListNode* pre = dummy;
        ListNode* cur = head;
        while (cur) {
            while (cur->next && cur->next->val == cur->val) {
                cur = cur->next;
            }
            if (pre->next == cur) {
                pre = cur;
            } else {
                pre->next = cur->next;
            }
            cur = cur->next;
        }
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
func deleteDuplicates(head *ListNode) *ListNode {
	dummy := &ListNode{Next: head}
	pre, cur := dummy, head
	for cur != nil {
		for cur.Next != nil && cur.Next.Val == cur.Val {
			cur = cur.Next
		}
		if pre.Next == cur {
			pre = cur
		} else {
			pre.Next = cur.Next
		}
		cur = cur.Next
	}
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

function deleteDuplicates(head: ListNode | null): ListNode | null {
    const dummy = new ListNode(0, head);
    let pre = dummy;
    let cur = head;
    while (cur) {
        while (cur.next && cur.val === cur.next.val) {
            cur = cur.next;
        }
        if (pre.next === cur) {
            pre = cur;
        } else {
            pre.next = cur.next;
        }
        cur = cur.next;
    }
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
    pub fn delete_duplicates(mut head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut dummy = Some(Box::new(ListNode::new(101)));
        let mut pev = dummy.as_mut().unwrap();
        let mut cur = head;
        let mut pre = 101;
        while let Some(mut node) = cur {
            cur = node.next.take();
            if node.val == pre || (cur.is_some() && cur.as_ref().unwrap().val == node.val) {
                pre = node.val;
            } else {
                pre = node.val;
                pev.next = Some(node);
                pev = pev.next.as_mut().unwrap();
            }
        }
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
 * @return {ListNode}
 */
var deleteDuplicates = function (head) {
    const dummy = new ListNode(0, head);
    let pre = dummy;
    let cur = head;
    while (cur) {
        while (cur.next && cur.val === cur.next.val) {
            cur = cur.next;
        }
        if (pre.next === cur) {
            pre = cur;
        } else {
            pre.next = cur.next;
        }
        cur = cur.next;
    }
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
    public ListNode DeleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            while (cur.next != null && cur.next.val == cur.val) {
                cur = cur.next;
            }
            if (pre.next == cur) {
                pre = cur;
            } else {
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
