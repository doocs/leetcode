---
comments: true
difficulty: Easy
tags:
    - Hash Table
    - Linked List
    - Two Pointers
    - Floyd Cycle Detection
---

<!-- problem:start -->

# [141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle)

[中文文档](/solution/0100-0199/0141.Linked%20List%20Cycle/README.md)

## Mô tả

<!-- description:start -->

<p>Cho trước <code>head</code>, nút đầu của một danh sách liên kết, hãy xác định xem danh sách liên kết có chu kỳ hay không.</p>

<p>Một danh sách liên kết có chu kỳ nếu có một nút trong danh sách có thể được đến lại bằng cách liên tục đi theo con trỏ&nbsp;<code>next</code>. Bên trong, <code>pos</code>&nbsp;được dùng để biểu thị chỉ số của nút mà con trỏ&nbsp;<code>next</code>&nbsp;của nút cuối được nối tới.&nbsp;<strong>Lưu ý rằng&nbsp;<code>pos</code>&nbsp;không được truyền dưới dạng tham số</strong>.</p>

<p>Trả về&nbsp;<code>true</code><em> nếu danh sách liên kết có chu kỳ</em>. Nếu không, trả về <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0141.Linked%20List%20Cycle/images/circularlinkedlist.png" style="width: 300px; height: 97px; margin-top: 8px; margin-bottom: 8px;" />
<pre>
<strong>Đầu vào:</strong> head = [3,2,0,-4], pos = 1
<strong>Đầu ra:</strong> true
<strong>Giải thích:</strong> Có một chu kỳ trong danh sách liên kết, trong đó nút cuối nối tới nút thứ 1 (đánh chỉ số từ 0).
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0141.Linked%20List%20Cycle/images/circularlinkedlist_test2.png" style="width: 141px; height: 74px;" />
<pre>
<strong>Đầu vào:</strong> head = [1,2], pos = 0
<strong>Đầu ra:</strong> true
<strong>Giải thích:</strong> Có một chu kỳ trong danh sách liên kết, trong đó nút cuối nối tới nút thứ 0.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0141.Linked%20List%20Cycle/images/circularlinkedlist_test3.png" style="width: 45px; height: 45px;" />
<pre>
<strong>Đầu vào:</strong> head = [1], pos = -1
<strong>Đầu ra:</strong> false
<strong>Giải thích:</strong> Danh sách liên kết không có chu kỳ.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số lượng nút trong danh sách nằm trong khoảng <code>[0, 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
	<li><code>pos</code> là <code>-1</code> hoặc một <strong>chỉ số hợp lệ</strong> trong danh sách liên kết.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Câu hỏi mở rộng:</strong> Bạn có thể giải bài toán bằng bộ nhớ <code>O(1)</code> (tức là hằng số) không?</p>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Bảng băm

<!-- thinking:start -->

> **Tư duy**
>
> Phát hiện chu kỳ. $n\le 10^4$. Lưu các nút đã duyệt trong một tập hợp; gặp lại nghĩa là có chu kỳ, gặp null nghĩa là không có. Đơn giản, không gian $O(n)$. Câu hỏi mở rộng yêu cầu không gian hằng số.

<!-- thinking:end -->

Chúng ta có thể duyệt danh sách liên kết và dùng một bảng băm $s$ để ghi lại từng nút. Khi một nút xuất hiện lần thứ hai, điều đó cho biết có một chu kỳ, và chúng ta trực tiếp trả về `true`. Nếu không, khi quá trình duyệt danh sách liên kết kết thúc, chúng ta trả về `false`.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(n)$, trong đó $n$ là số nút trong danh sách liên kết.

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def hasCycle(self, head: Optional[ListNode]) -> bool:
        s = set()
        while head:
            if head in s:
                return True
            s.add(head)
            head = head.next
        return False
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
    public boolean hasCycle(ListNode head) {
        Set<ListNode> s = new HashSet<>();
        for (; head != null; head = head.next) {
            if (!s.add(head)) {
                return true;
            }
        }
        return false;
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
    bool hasCycle(ListNode* head) {
        unordered_set<ListNode*> s;
        for (; head; head = head->next) {
            if (s.contains(head)) {
                return true;
            }
            s.insert(head);
        }
        return false;
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
func hasCycle(head *ListNode) bool {
	s := map[*ListNode]bool{}
	for ; head != nil; head = head.Next {
		if s[head] {
			return true
		}
		s[head] = true
	}
	return false
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

function hasCycle(head: ListNode | null): boolean {
    const s: Set<ListNode> = new Set();
    for (; head; head = head.next) {
        if (s.has(head)) {
            return true;
        }
        s.add(head);
    }
    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Con trỏ nhanh và chậm

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 sử dụng không gian bổ sung tuyến tính. Một con trỏ nhanh đi hai bước và một con trỏ chậm đi một bước chắc chắn sẽ gặp nhau bên trong chu kỳ, hoặc con trỏ nhanh sẽ chạm đến cuối danh sách. Chỉ cần hai con trỏ.

<!-- thinking:end -->

Chúng ta định nghĩa hai con trỏ, $fast$ và $slow$, ban đầu đều trỏ tới $head$.

Con trỏ nhanh di chuyển hai bước mỗi lần, còn con trỏ chậm di chuyển một bước mỗi lần, trong một vòng lặp liên tục. Khi con trỏ nhanh và chậm gặp nhau, điều đó cho biết danh sách liên kết có một chu kỳ. Nếu vòng lặp kết thúc mà các con trỏ chưa gặp nhau, điều đó cho biết danh sách liên kết không có chu kỳ.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(1)$, trong đó $n$ là số nút trong danh sách liên kết.

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def hasCycle(self, head: ListNode) -> bool:
        slow = fast = head
        while fast and fast.next:
            slow, fast = slow.next, fast.next.next
            if slow == fast:
                return True
        return False
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
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
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
    bool hasCycle(ListNode* head) {
        ListNode* slow = head;
        ListNode* fast = head;
        while (fast && fast->next) {
            slow = slow->next;
            fast = fast->next->next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
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
func hasCycle(head *ListNode) bool {
	slow, fast := head, head
	for fast != nil && fast.Next != nil {
		slow, fast = slow.Next, fast.Next.Next
		if slow == fast {
			return true
		}
	}
	return false
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

function hasCycle(head: ListNode | null): boolean {
    let slow = head;
    let fast = head;
    while (fast !== null && fast.next !== null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow === fast) {
            return true;
        }
    }
    return false;
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
 * @return {boolean}
 */
var hasCycle = function (head) {
    let slow = head;
    let fast = head;
    while (fast && fast.next) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow === fast) {
            return true;
        }
    }
    return false;
};
```

#### C#

```cs
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public bool HasCycle(ListNode head) {
        var fast = head;
        var slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
