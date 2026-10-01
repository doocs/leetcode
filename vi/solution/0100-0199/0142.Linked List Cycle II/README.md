---
comments: true
difficulty: Medium
tags:
    - Hash Table
    - Linked List
    - Two Pointers
    - Floyd Cycle Detection
---

<!-- problem:start -->

# [142. Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii)

[中文文档](/solution/0100-0199/0142.Linked%20List%20Cycle%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Cho <code>head</code> của một danh sách liên kết, hãy trả về <em>nút nơi chu trình bắt đầu. Nếu không có chu trình, hãy trả về </em><code>null</code>.</p>

<p>Một danh sách liên kết có chu trình nếu tồn tại một nút trong danh sách có thể được truy cập lại bằng cách liên tục đi theo con trỏ <code>next</code>. Nội bộ, <code>pos</code> được dùng để biểu thị chỉ số của nút mà con trỏ <code>next</code> của nút cuối nối tới (<strong>đánh chỉ số từ 0</strong>). Đây là <code>-1</code> nếu không có chu trình. <strong>Lưu ý rằng</strong> <code>pos</code> <strong>không được truyền vào dưới dạng tham số</strong>.</p>

<p><strong>Không được sửa đổi</strong> danh sách liên kết.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0142.Linked%20List%20Cycle%20II/images/circularlinkedlist.png" style="height: 145px; width: 450px;" />
<pre>
<strong>Đầu vào:</strong> head = [3,2,0,-4], pos = 1
<strong>Đầu ra:</strong> tail connects to node index 1
<strong>Giải thích:</strong> Có một chu trình trong danh sách liên kết, trong đó đuôi nối tới nút thứ hai.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0142.Linked%20List%20Cycle%20II/images/circularlinkedlist_test2.png" style="height: 105px; width: 201px;" />
<pre>
<strong>Đầu vào:</strong> head = [1,2], pos = 0
<strong>Đầu ra:</strong> tail connects to node index 0
<strong>Giải thích:</strong> Có một chu trình trong danh sách liên kết, trong đó đuôi nối tới nút đầu tiên.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0142.Linked%20List%20Cycle%20II/images/circularlinkedlist_test3.png" style="height: 65px; width: 65px;" />
<pre>
<strong>Đầu vào:</strong> head = [1], pos = -1
<strong>Đầu ra:</strong> no cycle
<strong>Giải thích:</strong> Không có chu trình trong danh sách liên kết.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số lượng nút trong danh sách nằm trong phạm vi <code>[0, 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
	<li><code>pos</code> là <code>-1</code> hoặc một <strong>chỉ số hợp lệ</strong> trong danh sách liên kết.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Câu hỏi mở rộng:</strong> Bạn có thể giải bài toán bằng cách sử dụng bộ nhớ <code>O(1)</code> (tức là bộ nhớ hằng số) không?</p>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Hai con trỏ

<!-- thinking:start -->

> **Tư duy**
>
> Chúng ta phải trả về điểm bắt đầu chu trình, không chỉ phát hiện chu trình. Một map các nút đã duyệt có thể hoạt động, nhưng câu hỏi mở rộng yêu cầu không gian $O(1)$. $n\le 10^4$.
>
> Sau khi con trỏ nhanh và chậm gặp nhau, đưa một con trỏ về head rồi cho cả hai di chuyển với cùng tốc độ; chúng sẽ gặp nhau tại điểm bắt đầu, vì khoảng cách từ head đến điểm bắt đầu bằng khoảng cách từ điểm gặp đến điểm bắt đầu khi đi quanh chu trình.

<!-- thinking:end -->

Trước tiên, chúng ta sử dụng con trỏ nhanh và con trỏ chậm để xác định xem danh sách liên kết có chu trình hay không. Nếu có chu trình, hai con trỏ nhanh và chậm chắc chắn sẽ gặp nhau, và nút gặp nhau phải nằm trong chu trình.

Nếu không có chu trình, con trỏ nhanh sẽ đến cuối danh sách liên kết trước, và trả về `null` ngay lập tức.

Nếu có chu trình, tiếp đó chúng ta định nghĩa một con trỏ kết quả $ans$ trỏ đến nút head của danh sách liên kết, rồi cho $ans$ và con trỏ chậm cùng di chuyển về phía trước, mỗi lần một bước, cho đến khi $ans$ và con trỏ chậm gặp nhau; nút gặp nhau là nút bắt đầu chu trình.

Tại sao cách này có thể tìm được nút bắt đầu chu trình?

Giả sử khoảng cách từ nút head của danh sách liên kết đến điểm bắt đầu chu trình là $x$, khoảng cách từ điểm bắt đầu chu trình đến nút gặp nhau là $y$, và khoảng cách từ nút gặp nhau đến điểm bắt đầu chu trình là $z$. Khi đó, khoảng cách mà con trỏ chậm đã di chuyển là $x + y$, còn khoảng cách mà con trỏ nhanh đã di chuyển là $x + y + k \times (y + z)$, trong đó $k$ là số lần con trỏ nhanh đi quanh chu trình.

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0142.Linked%20List%20Cycle%20II/images/linked-list-cycle-ii.png" /></p>

Vì tốc độ của con trỏ nhanh gấp đôi con trỏ chậm, ta có $2 \times (x + y) = x + y + k \times (y + z)$, từ đó suy ra $x + y = k \times (y + z)$, tức là $x = (k - 1) \times (y + z) + z$.

Điều đó có nghĩa là nếu ta định nghĩa một con trỏ kết quả $ans$ trỏ đến head của danh sách liên kết, rồi cho $ans$ và con trỏ chậm cùng di chuyển về phía trước, chúng chắc chắn sẽ gặp nhau tại điểm bắt đầu chu trình.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là số lượng nút trong danh sách liên kết. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def detectCycle(self, head: Optional[ListNode]) -> Optional[ListNode]:
        fast = slow = head
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
            if slow == fast:
                ans = head
                while ans != slow:
                    ans = ans.next
                    slow = slow.next
                return ans
```

#### Java

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode ans = head;
                while (ans != slow) {
                    ans = ans.next;
                    slow = slow.next;
                }
                return ans;
            }
        }
        return null;
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
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* detectCycle(ListNode* head) {
        ListNode* fast = head;
        ListNode* slow = head;
        while (fast && fast->next) {
            slow = slow->next;
            fast = fast->next->next;
            if (slow == fast) {
                ListNode* ans = head;
                while (ans != slow) {
                    ans = ans->next;
                    slow = slow->next;
                }
                return ans;
            }
        }
        return nullptr;
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
func detectCycle(head *ListNode) *ListNode {
	fast, slow := head, head
	for fast != nil && fast.Next != nil {
		slow = slow.Next
		fast = fast.Next.Next
		if slow == fast {
			ans := head
			for ans != slow {
				ans = ans.Next
				slow = slow.Next
			}
			return ans
		}
	}
	return nil
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

function detectCycle(head: ListNode | null): ListNode | null {
    let [slow, fast] = [head, head];
    while (fast && fast.next) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow === fast) {
            let ans = head;
            while (ans !== slow) {
                ans = ans.next;
                slow = slow.next;
            }
            return ans;
        }
    }
    return null;
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
var detectCycle = function (head) {
    let [slow, fast] = [head, head];
    while (fast && fast.next) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow === fast) {
            let ans = head;
            while (ans !== slow) {
                ans = ans.next;
                slow = slow.next;
            }
            return ans;
        }
    }
    return null;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
