---
comments: true
difficulty: Easy
tags:
    - Recursion
    - Linked List
---

<!-- problem:start -->

# [206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list)

[中文文档](/solution/0200-0299/0206.Reverse%20Linked%20List/README.md)

## Mô tả

<!-- description:start -->

<p>Cho trước <code>head</code> của một danh sách liên kết đơn, hãy đảo ngược danh sách và trả về <em>danh sách đã đảo ngược</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0206.Reverse%20Linked%20List/images/rev1ex1.jpg" style="width: 542px; height: 222px;" />
<pre>
<strong>Đầu vào:</strong> head = [1,2,3,4,5]
<strong>Đầu ra:</strong> [5,4,3,2,1]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0206.Reverse%20Linked%20List/images/rev1ex2.jpg" style="width: 182px; height: 222px;" />
<pre>
<strong>Đầu vào:</strong> head = [1,2]
<strong>Đầu ra:</strong> [2,1]
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> head = []
<strong>Đầu ra:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số lượng nút trong danh sách nằm trong khoảng <code>[0, 5000]</code>.</li>
	<li><code>-5000 &lt;= Node.val &lt;= 5000</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Câu hỏi mở rộng:</strong> Một danh sách liên kết có thể được đảo ngược bằng cách lặp hoặc đệ quy. Bạn có thể triển khai cả hai cách không?</p>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Phương pháp chèn đầu

<!-- thinking:start -->

> **Tư duy**
>
> Việc lưu các nút trong một mảng rồi dựng lại theo chiều ngược lại là khả thi, nhưng chỉ cần ghi đè các con trỏ. Phương pháp chèn đầu đảo ngược danh sách trong một lần duyệt: mỗi nút được chèn ngay sau một nút giả.
>
> Khi quá trình duyệt kết thúc, $\textit{dummy.next}$ là đầu mới và không gian bổ sung là hằng số.

<!-- thinking:end -->

Chúng ta tạo một nút giả $\textit{dummy}$, sau đó duyệt qua danh sách liên kết và chèn từng nút vào sau nút $\textit{dummy}$. Sau khi duyệt xong, trả về $\textit{dummy.next}$.

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
    def reverseList(self, head: ListNode) -> ListNode:
        dummy = ListNode()
        curr = head
        while curr:
            next = curr.next
            curr.next = dummy.next
            dummy.next = curr
            curr = next
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
    public ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = dummy.next;
            dummy.next = curr;
            curr = next;
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
    ListNode* reverseList(ListNode* head) {
        ListNode* dummy = new ListNode();
        ListNode* curr = head;
        while (curr) {
            ListNode* next = curr->next;
            curr->next = dummy->next;
            dummy->next = curr;
            curr = next;
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
func reverseList(head *ListNode) *ListNode {
	dummy := &ListNode{}
	curr := head
	for curr != nil {
		next := curr.Next
		curr.Next = dummy.Next
		dummy.Next = curr
		curr = next
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

function reverseList(head: ListNode | null): ListNode | null {
    if (head == null) {
        return head;
    }
    let pre = null;
    let cur = head;
    while (cur != null) {
        const next = cur.next;
        cur.next = pre;
        [pre, cur] = [cur, next];
    }
    return pre;
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
    pub fn reverse_list(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut head = head;
        let mut pre = None;
        while let Some(mut node) = head {
            head = node.next.take();
            node.next = pre.take();
            pre = Some(node);
        }
        pre
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
var reverseList = function (head) {
    let dummy = new ListNode();
    let curr = head;
    while (curr) {
        let next = curr.next;
        curr.next = dummy.next;
        dummy.next = curr;
        curr = next;
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
    public ListNode ReverseList(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = dummy.next;
            dummy.next = curr;
            curr = next;
        }
        return dummy.next;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Đệ quy

<!-- thinking:start -->

> **Tư duy**
>
> Phương pháp chèn đầu vốn đã có độ phức tạp tuyến tính. Thay vào đó, chúng ta có thể đảo ngược phần hậu tố bằng đệ quy, rồi gắn nút hiện tại vào phía sau đuôi mới.
>
> Các cập nhật con trỏ ngắn hơn, đổi lại phải dùng $O(n)$ không gian cho ngăn xếp lời gọi.

<!-- thinking:end -->

Chúng ta đệ quy đảo ngược tất cả các nút từ nút thứ hai đến cuối danh sách, rồi gắn $head$ vào cuối danh sách đã đảo ngược.

Độ phức tạp thời gian là $O(n)$ và độ phức tạp không gian là $O(n)$. Trong đó $n$ là độ dài của danh sách liên kết.

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reverseList(self, head: ListNode) -> ListNode:
        if head is None or head.next is None:
            return head
        ans = self.reverseList(head.next)
        head.next.next = head
        head.next = None
        return ans
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
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode ans = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return ans;
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
    ListNode* reverseList(ListNode* head) {
        if (!head || !head->next) return head;
        ListNode* ans = reverseList(head->next);
        head->next->next = head;
        head->next = nullptr;
        return ans;
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
func reverseList(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	ans := reverseList(head.Next)
	head.Next.Next = head
	head.Next = nil
	return ans
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
const rev = (pre: ListNode | null, cur: ListNode | null): ListNode | null => {
    if (cur == null) {
        return pre;
    }
    const next = cur.next;
    cur.next = pre;
    return rev(cur, next);
};

function reverseList(head: ListNode | null): ListNode | null {
    if (head == null) {
        return head;
    }
    const next = head.next;
    head.next = null;
    return rev(head, next);
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
    fn rev(pre: Option<Box<ListNode>>, cur: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        match cur {
            None => pre,
            Some(mut node) => {
                let next = node.next;
                node.next = pre;
                Self::rev(Some(node), next)
            }
        }
    }

    pub fn reverse_list(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        Self::rev(None, head)
    }
}
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
    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode ans = ReverseList(head.next);
        head.next.next = head;
        head.next = null;
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
