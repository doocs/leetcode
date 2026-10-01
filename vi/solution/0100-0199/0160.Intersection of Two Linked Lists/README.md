---
comments: true
difficulty: Easy
tags:
    - Hash Table
    - Linked List
    - Two Pointers
---

<!-- problem:start -->

# [160. Intersection of Two Linked Lists](https://leetcode.com/problems/intersection-of-two-linked-lists)

[中文文档](/solution/0100-0199/0160.Intersection%20of%20Two%20Linked%20Lists/README.md)

## Mô tả

<!-- description:start -->

<p>Cho phần đầu của hai danh sách liên kết đơn <code>headA</code> và <code>headB</code>, hãy trả về <em>nút tại đó hai danh sách giao nhau</em>. Nếu hai danh sách liên kết hoàn toàn không giao nhau, hãy trả về <code>null</code>.</p>

<p>Ví dụ, hai danh sách liên kết sau đây bắt đầu giao nhau tại nút <code>c1</code>:</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0160.Intersection%20of%20Two%20Linked%20Lists/images/160_statement.png" style="width: 500px; height: 162px;" />
<p>Các trường hợp kiểm thử được tạo sao cho không có chu kỳ nào trong toàn bộ cấu trúc liên kết.</p>

<p><strong>Lưu ý</strong> rằng các danh sách liên kết phải <strong>giữ nguyên cấu trúc ban đầu</strong> sau khi hàm trả về.</p>

<p><strong>Trình chấm tùy chỉnh:</strong></p>

<p>Các đầu vào cho <strong>trình chấm</strong> được cung cấp như sau (chương trình của bạn <strong>không</strong> được cung cấp các đầu vào này):</p>

<ul>
	<li><code>intersectVal</code> - Giá trị của nút tại đó xảy ra giao nhau. Giá trị này là <code>0</code> nếu không có nút giao nhau.</li>
	<li><code>listA</code> - Danh sách liên kết thứ nhất.</li>
	<li><code>listB</code> - Danh sách liên kết thứ hai.</li>
	<li><code>skipA</code> - Số nút cần bỏ qua trong <code>listA</code> (bắt đầu từ phần đầu) để đến nút giao nhau.</li>
	<li><code>skipB</code> - Số nút cần bỏ qua trong <code>listB</code> (bắt đầu từ phần đầu) để đến nút giao nhau.</li>
</ul>

<p>Sau đó, trình chấm sẽ tạo cấu trúc liên kết dựa trên các đầu vào này và truyền hai phần đầu, <code>headA</code> và <code>headB</code>, cho chương trình của bạn. Nếu bạn trả về đúng nút giao nhau, lời giải của bạn sẽ được <strong>chấp nhận</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0160.Intersection%20of%20Two%20Linked%20Lists/images/160_example_1_1.png" style="width: 500px; height: 162px;" />
<pre>
<strong>Đầu vào:</strong> intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
<strong>Đầu ra:</strong> Intersected at &#39;8&#39;
<strong>Giải thích:</strong> Giá trị của nút giao nhau là 8 (lưu ý rằng giá trị này phải khác 0 nếu hai danh sách giao nhau).
Từ phần đầu của A, ta đọc được [4,1,8,4,5]. Từ phần đầu của B, ta đọc được [5,6,1,8,4,5]. Có 2 nút trước nút giao nhau trong A; có 3 nút trước nút giao nhau trong B.
- Lưu ý rằng giá trị của nút giao nhau không phải là 1 vì các nút có giá trị 1 trong A và B (nút 2<sup>nd</sup> trong A và nút 3<sup>rd</sup> trong B) là các tham chiếu nút khác nhau. Nói cách khác, chúng trỏ đến hai vị trí khác nhau trong bộ nhớ, trong khi các nút có giá trị 8 trong A và B (nút 3<sup>rd</sup> trong A và nút 4<sup>th</sup> trong B) trỏ đến cùng một vị trí trong bộ nhớ.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0160.Intersection%20of%20Two%20Linked%20Lists/images/160_example_2.png" style="width: 500px; height: 194px;" />
<pre>
<strong>Đầu vào:</strong> intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
<strong>Đầu ra:</strong> Intersected at &#39;2&#39;
<strong>Giải thích:</strong> Giá trị của nút giao nhau là 2 (lưu ý rằng giá trị này phải khác 0 nếu hai danh sách giao nhau).
Từ phần đầu của A, ta đọc được [1,9,1,2,4]. Từ phần đầu của B, ta đọc được [3,2,4]. Có 3 nút trước nút giao nhau trong A; có 1 nút trước nút giao nhau trong B.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0160.Intersection%20of%20Two%20Linked%20Lists/images/160_example_3.png" style="width: 300px; height: 189px;" />
<pre>
<strong>Đầu vào:</strong> intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
<strong>Đầu ra:</strong> No intersection
<strong>Giải thích:</strong> Từ phần đầu của A, ta đọc được [2,6,4]. Từ phần đầu của B, ta đọc được [1,5]. Vì hai danh sách không giao nhau, intersectVal phải là 0, còn skipA và skipB có thể nhận các giá trị bất kỳ.
Giải thích: Hai danh sách không giao nhau, vì vậy hãy trả về null.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Số nút của <code>listA</code> là <code>m</code>.</li>
	<li>Số nút của <code>listB</code> là <code>n</code>.</li>
	<li><code>1 &lt;= m, n &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= skipA &lt;= m</code></li>
	<li><code>0 &lt;= skipB &lt;= n</code></li>
	<li><code>intersectVal</code> là <code>0</code> nếu <code>listA</code> và <code>listB</code> không giao nhau.</li>
	<li><code>intersectVal == listA[skipA] == listB[skipB]</code> nếu <code>listA</code> và <code>listB</code> giao nhau.</li>
</ul>

<p>&nbsp;</p>
<strong>Câu hỏi mở rộng:</strong> Bạn có thể viết một lời giải chạy trong thời gian <code>O(m + n)</code> và chỉ sử dụng bộ nhớ <code>O(1)</code> không?

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Hai con trỏ

<!-- thinking:start -->

> **Tư duy**
>
> Tìm nút chung đầu tiên. Lưu một danh sách vào một set sẽ tốn thời gian $O(m+n)$ và không gian $O(m)$; câu hỏi mở rộng yêu cầu bộ nhớ $O(1)$. $m,n\le 3\times 10^4$.
>
> Mỗi con trỏ duyệt qua danh sách của nó rồi đến danh sách còn lại, vì vậy cả hai đều đi qua $a+b$. Chúng gặp nhau tại giao điểm, hoặc cả hai cùng đi đến null. Không cần đo độ dài trước.

<!-- thinking:end -->

Chúng ta sử dụng hai con trỏ $a$ và $b$ lần lượt trỏ đến phần đầu của hai danh sách liên kết $\textit{headA}$ và $\textit{headB}$.

Duyệt đồng thời hai danh sách liên kết. Khi $a$ đến cuối $\textit{headA}$, chuyển nó đến phần đầu của $\textit{headB}$. Tương tự, khi $b$ đến cuối $\textit{headB}$, chuyển nó đến phần đầu của $\textit{headA}$.

Nếu hai con trỏ gặp nhau, nút mà chúng trỏ đến là nút chung đầu tiên. Nếu chúng không gặp nhau, điều đó có nghĩa là hai danh sách liên kết không có nút chung và cả hai con trỏ sẽ trỏ đến `null`. Trả về một trong hai con trỏ.

Độ phức tạp thời gian là $O(m + n)$, trong đó $m$ và $n$ lần lượt là độ dài của các danh sách liên kết $\textit{headA}$ và $\textit{headB}$. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
# Định nghĩa cho danh sách liên kết đơn.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def getIntersectionNode(self, headA: ListNode, headB: ListNode) -> ListNode:
        a, b = headA, headB
        while a != b:
            a = a.next if a else headB
            b = b.next if b else headA
        return a
```

#### Java

```java
/**
 * Định nghĩa cho danh sách liên kết đơn.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
}
```

#### C++

```cpp
/**
 * Định nghĩa cho danh sách liên kết đơn.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* getIntersectionNode(ListNode* headA, ListNode* headB) {
        ListNode *a = headA, *b = headB;
        while (a != b) {
            a = a ? a->next : headB;
            b = b ? b->next : headA;
        }
        return a;
    }
};
```

#### Go

```go
/**
 * Định nghĩa cho danh sách liên kết đơn.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func getIntersectionNode(headA, headB *ListNode) *ListNode {
	a, b := headA, headB
	for a != b {
		if a == nil {
			a = headB
		} else {
			a = a.Next
		}
		if b == nil {
			b = headA
		} else {
			b = b.Next
		}
	}
	return a
}
```

#### TypeScript

```ts
/**
 * Định nghĩa cho danh sách liên kết đơn.
 * class ListNode {
 *     val: number
 *     next: ListNode | null
 *     constructor(val?: number, next?: ListNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.next = (next===undefined ? null : next)
 *     }
 * }
 */

function getIntersectionNode(headA: ListNode | null, headB: ListNode | null): ListNode | null {
    let [a, b] = [headA, headB];
    while (a !== b) {
        a = a ? a.next : headB;
        b = b ? b.next : headA;
    }
    return a;
}
```

#### JavaScript

```js
/**
 * Định nghĩa cho danh sách liên kết đơn.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */

/**
 * @param {ListNode} headA
 * @param {ListNode} headB
 * @return {ListNode}
 */
var getIntersectionNode = function (headA, headB) {
    let [a, b] = [headA, headB];
    while (a !== b) {
        a = a ? a.next : headB;
        b = b ? b.next : headA;
    }
    return a;
};
```

#### Swift

```swift
/**
 * Định nghĩa cho danh sách liên kết đơn.
 * public class ListNode {
 *     public var val: Int
 *     public var next: ListNode?
 *     public init(_ val: Int) {
 *         self.val = val
 *         self.next = nil
 *     }
 * }
 */

class Solution {
    func getIntersectionNode(_ headA: ListNode?, _ headB: ListNode?) -> ListNode? {
        var a = headA
        var b = headB
        while a !== b {
            a = a == nil ? headB : a?.next
            b = b == nil ? headA : b?.next
        }
        return a
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
