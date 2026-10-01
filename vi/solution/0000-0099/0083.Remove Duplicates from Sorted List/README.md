---
comments: true
difficulty: Easy
tags:
    - Linked List
---

<!-- problem:start -->

# [83. Remove Duplicates from Sorted List](https://leetcode.com/problems/remove-duplicates-from-sorted-list)

[中文文档](/solution/0000-0099/0083.Remove%20Duplicates%20from%20Sorted%20List/README.md)

## Mô tả

<!-- description:start -->

<p>Cho <code>head</code> của một danh sách liên kết đã được sắp xếp, hãy <em>xóa tất cả các phần tử trùng lặp sao cho mỗi phần tử chỉ xuất hiện một lần</em>. Trả về <em>danh sách liên kết cũng được <strong>sắp xếp</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0083.Remove%20Duplicates%20from%20Sorted%20List/images/list1.jpg" style="width: 302px; height: 242px;" />
<pre>
<strong>Đầu vào:</strong> head = [1,1,2]
<strong>Đầu ra:</strong> [1,2]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0083.Remove%20Duplicates%20from%20Sorted%20List/images/list2.jpg" style="width: 542px; height: 222px;" />
<pre>
<strong>Đầu vào:</strong> head = [1,1,2,3,3]
<strong>Đầu ra:</strong> [1,2,3]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số lượng nút trong danh sách nằm trong phạm vi <code>[0, 300]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
	<li>Danh sách được đảm bảo <strong>sắp xếp</strong> theo thứ tự tăng dần.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Duyệt một lượt

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là đưa các giá trị vào một set rồi xây dựng lại. Cách này đúng, và $n \le 300$ rất nhỏ, nhưng bỏ qua tính kề nhau do danh sách đã sắp xếp và sử dụng thêm không gian.
>
> Nút thắt không nằm ở việc viết lại các liên kết tại chỗ. Các phần tử trùng lặp nằm cạnh nhau, vì vậy chỉ cần bỏ qua $cur.next$ khi các giá trị trùng nhau là đủ.
>
> Khác với bài toán trước, chúng ta giữ lại lần xuất hiện đầu tiên. Duyệt bằng một con trỏ: loại bỏ nút kế tiếp khi bằng nhau, chỉ di chuyển con trỏ khi khác nhau. head luôn được giữ lại, nên không cần nút giả.

<!-- thinking:end -->

Chúng ta sử dụng một con trỏ $cur$ để duyệt qua danh sách liên kết. Nếu phần tử tương ứng với $cur$ giống với phần tử tương ứng với $cur.next$, chúng ta gán con trỏ $next$ của $cur$ trỏ đến nút tiếp theo của $cur.next$. Ngược lại, điều đó có nghĩa là phần tử tương ứng với $cur$ trong danh sách liên kết không bị trùng lặp, nên chúng ta có thể di chuyển con trỏ $cur$ đến nút tiếp theo.

Sau khi quá trình duyệt kết thúc, trả về nút head của danh sách liên kết.

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
    def deleteDuplicates(self, head: Optional[ListNode]) -> Optional[ListNode]:
        cur = head
        while cur and cur.next:
            if cur.val == cur.next.val:
                cur.next = cur.next.next
            else:
                cur = cur.next
        return head
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
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
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
        ListNode* cur = head;
        while (cur != nullptr && cur->next != nullptr) {
            if (cur->val == cur->next->val) {
                cur->next = cur->next->next;
            } else {
                cur = cur->next;
            }
        }
        return head;
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
	cur := head
	for cur != nil && cur.Next != nil {
		if cur.Val == cur.Next.Val {
			cur.Next = cur.Next.Next
		} else {
			cur = cur.Next
		}
	}
	return head
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
    pub fn delete_duplicates(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut dummy = Some(Box::new(ListNode::new(i32::MAX)));
        let mut p = &mut dummy;

        let mut current = head;
        while let Some(mut node) = current {
            current = node.next.take();
            if p.as_mut().unwrap().val != node.val {
                p.as_mut().unwrap().next = Some(node);
                p = &mut p.as_mut().unwrap().next;
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
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} head
 * @return {ListNode}
 */
var deleteDuplicates = function (head) {
    let cur = head;
    while (cur && cur.next) {
        if (cur.next.val === cur.val) {
            cur.next = cur.next.next;
        } else {
            cur = cur.next;
        }
    }
    return head;
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
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
