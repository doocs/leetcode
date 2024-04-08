# [面试题 02.05. 链表求和](https://leetcode.cn/problems/sum-lists-lcci)

[English Version](/lcci/02.05.Sum%20Lists/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定两个用链表表示的整数，每个节点包含一个数位。</p>
<p>这些数位是反向存放的，也就是个位排在链表首部。</p>
<p>编写函数对这两个整数求和，并用链表形式返回结果。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>(7 -&gt; 1 -&gt; 6) + (5 -&gt; 9 -&gt; 2)，即617 + 295
<strong>输出：</strong>2 -&gt; 1 -&gt; 9，即912
</pre>

<p><strong>进阶：</strong>假设这些数位是正向存放的，请再做一遍。</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>(6 -&gt; 1 -&gt; 7) + (2 -&gt; 9 -&gt; 5)，即617 + 295
<strong>输出：</strong>9 -&gt; 1 -&gt; 2，即912
</pre>

## 解法

### 方法一：模拟

我们同时遍历两个链表 $l_1$ 和 $l_2$，并使用变量 $carry$ 表示当前是否有进位。

每次遍历时，我们取出对应链表的当前位，计算它们与进位 $carry$ 的和，然后更新进位的值，最后将当前位的值加入答案链表。如果两个链表都遍历完了，并且进位为 $0$ 时，遍历结束。

最后我们返回答案链表的头节点即可。

时间复杂度 $O(\max(m, n))$，其中 $m$ 和 $n$ 分别为两个链表的长度。我们需要遍历两个链表的全部位置，而处理每个位置只需要 $O(1)$ 的时间。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        dummy = ListNode()
        carry, curr = 0, dummy
        while l1 or l2 or carry:
            s = (l1.val if l1 else 0) + (l2.val if l2 else 0) + carry
            carry, val = divmod(s, 10)
            curr.next = ListNode(val)
            curr = curr.next
            l1 = l1.next if l1 else None
            l2 = l2.next if l2 else None
        return dummy.next
```

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        int carry = 0;
        ListNode cur = dummy;
        while (l1 != null || l2 != null || carry != 0) {
            int s = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            carry = s / 10;
            cur.next = new ListNode(s % 10);
            cur = cur.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        return dummy.next;
    }
}
```

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
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        ListNode* dummy = new ListNode(0);
        ListNode* cur = dummy;
        int carry = 0;
        while (l1 || l2 || carry) {
            carry += (!l1 ? 0 : l1->val) + (!l2 ? 0 : l2->val);
            cur->next = new ListNode(carry % 10);
            cur = cur->next;
            carry /= 10;
            l1 = l1 ? l1->next : l1;
            l2 = l2 ? l2->next : l2;
        }
        return dummy->next;
    }
};
```

```go
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	dummy := &ListNode{}
	cur := dummy
	carry := 0
	for l1 != nil || l2 != nil || carry > 0 {
		if l1 != nil {
			carry += l1.Val
			l1 = l1.Next
		}
		if l2 != nil {
			carry += l2.Val
			l2 = l2.Next
		}
		cur.Next = &ListNode{Val: carry % 10}
		cur = cur.Next
		carry /= 10
	}
	return dummy.Next
}
```

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

function addTwoNumbers(l1: ListNode | null, l2: ListNode | null): ListNode | null {
    if (l1 == null || l2 == null) {
        return l1 && l2;
    }
    const dummy = new ListNode(0);
    let cur = dummy;
    while (l1 != null || l2 != null) {
        let val = 0;
        if (l1 != null) {
            val += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            val += l2.val;
            l2 = l2.next;
        }
        if (cur.val >= 10) {
            cur.val %= 10;
            val++;
        }
        cur.next = new ListNode(val);
        cur = cur.next;
    }
    if (cur.val >= 10) {
        cur.val %= 10;
        cur.next = new ListNode(1);
    }
    return dummy.next;
}
```

```rust
impl Solution {
    pub fn add_two_numbers(
        mut l1: Option<Box<ListNode>>,
        mut l2: Option<Box<ListNode>>
    ) -> Option<Box<ListNode>> {
        let mut dummy = Some(Box::new(ListNode::new(0)));
        let mut cur = dummy.as_mut();
        while l1.is_some() || l2.is_some() {
            let mut val = 0;
            if let Some(node) = l1 {
                val += node.val;
                l1 = node.next;
            }
            if let Some(node) = l2 {
                val += node.val;
                l2 = node.next;
            }
            if let Some(node) = cur {
                if node.val >= 10 {
                    val += 1;
                    node.val %= 10;
                }
                node.next = Some(Box::new(ListNode::new(val)));
                cur = node.next.as_mut();
            }
        }
        if let Some(node) = cur {
            if node.val >= 10 {
                node.val %= 10;
                node.next = Some(Box::new(ListNode::new(1)));
            }
        }
        dummy.unwrap().next
    }
}
```

```js
/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */
var addTwoNumbers = function (l1, l2) {
    let carry = 0;
    const dummy = new ListNode(0);
    let cur = dummy;
    while (l1 || l2 || carry) {
        carry += (l1?.val || 0) + (l2?.val || 0);
        cur.next = new ListNode(carry % 10);
        carry = Math.floor(carry / 10);
        cur = cur.next;
        l1 = l1?.next;
        l2 = l2?.next;
    }
    return dummy.next;
};
```

```swift
class Solution {
/**
*    class ListNode {
*        var val: Int
*        var next: ListNode?
*        init(_ val: Int) {
*            self.val = val
*            self.next = nil
*        }
*    }
*/

    func addTwoNumbers(_ l1: ListNode?, _ l2: ListNode?) -> ListNode? {
        var carry = 0
        let dummy = ListNode(0)
        var current: ListNode? = dummy
        var l1 = l1, l2 = l2

        while l1 != nil || l2 != nil || carry != 0 {
            let sum = (l1?.val ?? 0) + (l2?.val ?? 0) + carry
            carry = sum / 10
            current?.next = ListNode(sum % 10)
            current = current?.next
            l1 = l1?.next
            l2 = l2?.next
        }

        return dummy.next
    }
}
```

<!-- tabs:end -->

<!-- end -->
