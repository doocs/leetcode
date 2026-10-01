---
comments: true
difficulty: Hard
tags:
    - Recursion
    - Linked List
---

<!-- problem:start -->

# [25. Reverse Nodes in k-Group](https://leetcode.com/problems/reverse-nodes-in-k-group)

[中文文档](/solution/0000-0099/0025.Reverse%20Nodes%20in%20k-Group/README.md)

## Mô tả

<!-- description:start -->

<p>Cho <code>head</code> của một danh sách liên kết, hãy đảo ngược các nút của danh sách theo từng nhóm <code>k</code> và trả về <em>danh sách đã được sửa đổi</em>.</p>

<p><code>k</code> là một số nguyên dương và nhỏ hơn hoặc bằng độ dài của danh sách liên kết. Nếu số lượng nút không phải là bội số của <code>k</code> thì các nút còn lại ở cuối phải được giữ nguyên.</p>

<p>Bạn không được thay đổi các giá trị trong các nút của danh sách, chỉ được thay đổi chính các nút.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0025.Reverse%20Nodes%20in%20k-Group/images/reverse_ex1.jpg" style="width: 542px; height: 222px;" />
<pre>
<strong>Đầu vào:</strong> head = [1,2,3,4,5], k = 2
<strong>Đầu ra:</strong> [2,1,4,3,5]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0025.Reverse%20Nodes%20in%20k-Group/images/reverse_ex2.jpg" style="width: 542px; height: 222px;" />
<pre>
<strong>Đầu vào:</strong> head = [1,2,3,4,5], k = 3
<strong>Đầu ra:</strong> [3,2,1,4,5]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số lượng nút trong danh sách là <code>n</code>.</li>
	<li><code>1 &lt;= k &lt;= n &lt;= 5000</code></li>
	<li><code>0 &lt;= Node.val &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Câu hỏi mở rộng:</strong> Bạn có thể giải bài toán với không gian bộ nhớ bổ sung <code>O(1)</code> không?</p>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Mô phỏng

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là dùng đệ quy theo nhóm: đảo ngược $k$ nút, sau đó đệ quy trên phần còn lại. Với $n \le 5000$, cách này sẽ vượt qua, nhưng câu hỏi mở rộng yêu cầu không gian bổ sung $O(1)$, trong khi ngăn xếp đệ quy tăng theo số lượng nhóm.
>
> Có hai chỗ rất dễ mắc lỗi: chúng ta phải xác nhận có đủ một nhóm gồm $k$ nút (nếu không thì giữ nguyên phần đuôi), và phải nối đoạn đã đảo ngược trở lại mà không làm mất một con trỏ.
>
> Bên trong một nhóm, công việc là đảo ngược danh sách thông thường; giữa các nhóm, chúng ta chỉ cần phần đuôi trước đó. Bắt đầu từ một nút giả, đi $k$ bước để kiểm tra, sau đó tách, đảo ngược và nối lại; dừng khi lần kiểm tra bị thiếu nút.
>
> Kiểm tra, đảo ngược và nối lại đều chỉ là các thao tác ghi con trỏ, nên không gian bổ sung là $O(1)$.

<!-- thinking:end -->

Chúng ta có thể mô phỏng toàn bộ quá trình đảo ngược theo mô tả của đề bài.

Đầu tiên, chúng ta định nghĩa một hàm hỗ trợ $\textit{reverse}$ để đảo ngược một danh sách liên kết. Sau đó, chúng ta định nghĩa một nút đầu giả $\textit{dummy}$ và đặt con trỏ $\textit{next}$ của nó trỏ tới $\textit{head}$.

Tiếp theo, chúng ta duyệt qua danh sách liên kết, xử lý mỗi lần $k$ nút. Nếu số nút còn lại ít hơn $k$, chúng ta không thực hiện đảo ngược. Nếu không, chúng ta tách $k$ nút và gọi hàm $\textit{reverse}$ để đảo ngược $k$ nút này. Sau đó, chúng ta nối danh sách liên kết đã đảo ngược trở lại danh sách liên kết ban đầu. Chúng ta tiếp tục xử lý $k$ nút tiếp theo cho đến khi duyệt hết toàn bộ danh sách liên kết.

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
    def reverseKGroup(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        def reverse(head: Optional[ListNode]) -> Optional[ListNode]:
            dummy = ListNode()
            cur = head
            while cur:
                nxt = cur.next
                cur.next = dummy.next
                dummy.next = cur
                cur = nxt
            return dummy.next

        dummy = pre = ListNode(next=head)
        while pre:
            cur = pre
            for _ in range(k):
                cur = cur.next
                if cur is None:
                    return dummy.next
            node = pre.next
            nxt = cur.next
            cur.next = None
            pre.next = reverse(node)
            node.next = nxt
            pre = node
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
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        dummy.next = head;
        ListNode pre = dummy;
        while (pre != null) {
            ListNode cur = pre;
            for (int i = 0; i < k; i++) {
                cur = cur.next;
                if (cur == null) {
                    return dummy.next;
                }
            }
            ListNode node = pre.next;
            ListNode nxt = cur.next;
            cur.next = null;
            pre.next = reverse(node);
            node.next = nxt;
            pre = node;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
            cur = nxt;
        }
        return dummy.next;
    }
}
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
func reverseKGroup(head *ListNode, k int) *ListNode {
	dummy := &ListNode{Next: head}
	pre := dummy

	for pre != nil {
		cur := pre
		for i := 0; i < k; i++ {
			cur = cur.Next
			if cur == nil {
				return dummy.Next
			}
		}

		node := pre.Next
		nxt := cur.Next
		cur.Next = nil
		pre.Next = reverse(node)
		node.Next = nxt
		pre = node
	}
	return dummy.Next
}

func reverse(head *ListNode) *ListNode {
	var dummy *ListNode
	cur := head
	for cur != nil {
		nxt := cur.Next
		cur.Next = dummy
		dummy = cur
		cur = nxt
	}
	return dummy
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

function reverseKGroup(head: ListNode | null, k: number): ListNode | null {
    const dummy = new ListNode(0, head);
    let pre = dummy;
    while (pre !== null) {
        let cur: ListNode | null = pre;
        for (let i = 0; i < k; i++) {
            cur = cur?.next || null;
            if (cur === null) {
                return dummy.next;
            }
        }

        const node = pre.next;
        const nxt = cur?.next || null;
        cur!.next = null;
        pre.next = reverse(node);
        node!.next = nxt;
        pre = node!;
    }

    return dummy.next;
}

function reverse(head: ListNode | null): ListNode | null {
    let dummy: ListNode | null = null;
    let cur = head;

    while (cur !== null) {
        const nxt = cur.next;
        cur.next = dummy;
        dummy = cur;
        cur = nxt;
    }

    return dummy;
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
    pub fn reverse_k_group(head: Option<Box<ListNode>>, k: i32) -> Option<Box<ListNode>> {
        fn reverse(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
            let mut head = head;
            let mut pre = None;
            while let Some(mut node) = head {
                head = node.next.take();
                node.next = pre.take();
                pre = Some(node);
            }
            pre
        }

        let mut dummy = Some(Box::new(ListNode::new(0)));
        let mut pre = &mut dummy;
        let mut cur = head;
        while cur.is_some() {
            let mut q = &mut cur;
            for _ in 0..k - 1 {
                if q.is_none() {
                    break;
                }
                q = &mut q.as_mut().unwrap().next;
            }
            if q.is_none() {
                pre.as_mut().unwrap().next = cur;
                return dummy.unwrap().next;
            }

            let b = q.as_mut().unwrap().next.take();
            pre.as_mut().unwrap().next = reverse(cur);
            while pre.is_some() && pre.as_mut().unwrap().next.is_some() {
                pre = &mut pre.as_mut().unwrap().next;
            }
            cur = b;
        }
        dummy.unwrap().next
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
 *     public ListNode(int val = 0, ListNode next = null) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */
public class Solution {
    public ListNode ReverseKGroup(ListNode head, int k) {
        var dummy = new ListNode(0);
        dummy.next = head;
        var pre = dummy;

        while (pre != null) {
            var cur = pre;
            for (int i = 0; i < k; i++) {
                if (cur.next == null) {
                    return dummy.next;
                }
                cur = cur.next;
            }

            var node = pre.next;
            var nxt = cur.next;
            cur.next = null;
            pre.next = Reverse(node);
            node.next = nxt;
            pre = node;
        }

        return dummy.next;
    }

    private ListNode Reverse(ListNode head) {
        ListNode prev = null;
        var cur = head;
        while (cur != null) {
            var nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
        return prev;
    }
}
```

#### PHP

```php
/**
 * Definition for a singly-linked list.
 * class ListNode {
 *     public $val = 0;
 *     public $next = null;
 *     function __construct($val = 0, $next = null) {
 *         $this->val = $val;
 *         $this->next = $next;
 *     }
 * }
 */
class Solution {
    /**
     * @param ListNode $head
     * @param Integer $k
     * @return ListNode
     */
    function reverseKGroup($head, $k) {
        $dummy = new ListNode(0);
        $dummy->next = $head;
        $pre = $dummy;

        while ($pre !== null) {
            $cur = $pre;
            for ($i = 0; $i < $k; $i++) {
                if ($cur->next === null) {
                    return $dummy->next;
                }
                $cur = $cur->next;
            }

            $node = $pre->next;
            $nxt = $cur->next;
            $cur->next = null;
            $pre->next = $this->reverse($node);
            $node->next = $nxt;
            $pre = $node;
        }

        return $dummy->next;
    }

    /**
     * Helper function to reverse a linked list.
     * @param ListNode $head
     * @return ListNode
     */
    function reverse($head) {
        $prev = null;
        $cur = $head;
        while ($cur !== null) {
            $nxt = $cur->next;
            $cur->next = $prev;
            $prev = $cur;
            $cur = $nxt;
        }
        return $prev;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
