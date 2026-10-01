---
comments: true
difficulty: Medium
tags:
    - Linked List
    - Sorting
---

<!-- problem:start -->

# [147. Insertion Sort List](https://leetcode.com/problems/insertion-sort-list)

[中文文档](/solution/0100-0199/0147.Insertion%20Sort%20List/README.md)

## Mô tả

<!-- description:start -->

<p>Với <code>head</code> của một danh sách liên kết đơn, hãy sắp xếp danh sách bằng <strong>sắp xếp chèn</strong> và trả về <em>head của danh sách đã sắp xếp</em>.</p>

<p>Các bước của thuật toán <strong>sắp xếp chèn</strong>:</p>

<ol>
	<li>Thuật toán sắp xếp chèn lặp lại, mỗi lần lấy một phần tử đầu vào và mở rộng danh sách đầu ra đã sắp xếp.</li>
	<li>Ở mỗi lần lặp, thuật toán sắp xếp chèn loại bỏ một phần tử khỏi dữ liệu đầu vào, tìm vị trí mà phần tử đó thuộc về trong danh sách đã sắp xếp rồi chèn phần tử vào vị trí đó.</li>
	<li>Thuật toán lặp lại cho đến khi không còn phần tử đầu vào nào.</li>
</ol>

<p>Hình dưới đây minh họa bằng đồ họa cho thuật toán sắp xếp chèn. Danh sách được sắp xếp một phần (màu đen) ban đầu chỉ chứa phần tử đầu tiên trong danh sách. Ở mỗi lần lặp, một phần tử (màu đỏ) được loại bỏ khỏi dữ liệu đầu vào và chèn tại chỗ vào danh sách đã sắp xếp.</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0147.Insertion%20Sort%20List/images/Insertion-sort-example-300px.gif" style="height:180px; width:300px" />
<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0147.Insertion%20Sort%20List/images/sort1linked-list.jpg" style="width: 422px; height: 222px;" />
<pre>
<strong>Đầu vào:</strong> head = [4,2,1,3]
<strong>Đầu ra:</strong> [1,2,3,4]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0147.Insertion%20Sort%20List/images/sort2linked-list.jpg" style="width: 542px; height: 222px;" />
<pre>
<strong>Đầu vào:</strong> head = [-1,5,3,4,0]
<strong>Đầu ra:</strong> [-1,0,3,4,5]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số lượng nút trong danh sách nằm trong phạm vi <code>[1, 5000]</code>.</li>
	<li><code>-5000 &lt;= Node.val &lt;= 5000</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> Sắp xếp chèn một danh sách. $n\le 5000$, nên thời gian bậc hai là chấp nhận được. Sắp xếp một mảng rồi xây dựng lại danh sách không phải là sắp xếp chèn.
>
> $\textit{pre}$ đánh dấu phần cuối của tiền tố đã sắp xếp. Nếu giá trị hiện tại lớn hơn, mở rộng tiền tố; nếu không, duyệt từ một nút giả đến nút đầu tiên lớn hơn rồi nối nó vào. Nút giả loại bỏ trường hợp đặc biệt khi chèn ở đầu danh sách.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def insertionSortList(self, head: ListNode) -> ListNode:
        if head is None or head.next is None:
            return head
        dummy = ListNode(head.val, head)
        pre, cur = dummy, head
        while cur:
            if pre.val <= cur.val:
                pre, cur = cur, cur.next
                continue
            p = dummy
            while p.next.val <= cur.val:
                p = p.next
            t = cur.next
            cur.next = p.next
            p.next = cur
            pre.next = t
            cur = t
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
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(head.val, head);
        ListNode pre = dummy, cur = head;
        while (cur != null) {
            if (pre.val <= cur.val) {
                pre = cur;
                cur = cur.next;
                continue;
            }
            ListNode p = dummy;
            while (p.next.val <= cur.val) {
                p = p.next;
            }
            ListNode t = cur.next;
            cur.next = p.next;
            p.next = cur;
            pre.next = t;
            cur = t;
        }
        return dummy.next;
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
var insertionSortList = function (head) {
    if (head == null || head.next == null) return head;
    let dummy = new ListNode(head.val, head);
    let prev = dummy,
        cur = head;
    while (cur != null) {
        if (prev.val <= cur.val) {
            prev = cur;
            cur = cur.next;
            continue;
        }
        let p = dummy;
        while (p.next.val <= cur.val) {
            p = p.next;
        }
        let t = cur.next;
        cur.next = p.next;
        p.next = cur;
        prev.next = t;
        cur = t;
    }
    return dummy.next;
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
func insertionSortList(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	dummy := &ListNode{head.Val, head}
	pre, cur := dummy, head
	for cur != nil {
		if pre.Val <= cur.Val {
			pre = cur
			cur = cur.Next
			continue
		}
		p := dummy
		for p.Next.Val <= cur.Val {
			p = p.Next
		}
		t := cur.Next
		cur.Next = p.Next
		p.Next = cur
		pre.Next = t
		cur = t
	}
	return dummy.Next
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
