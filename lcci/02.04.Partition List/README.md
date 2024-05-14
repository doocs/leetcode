---
comment: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/02.04.Partition%20List/README.md
---

# [面试题 02.04. 分割链表](https://leetcode.cn/problems/partition-list-lcci)

[English Version](/lcci/02.04.Partition%20List/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个链表的头节点 <code>head</code> 和一个特定值<em> </em><code>x</code> ，请你对链表进行分隔，使得所有 <strong>小于</strong> <code>x</code> 的节点都出现在 <strong>大于或等于</strong> <code>x</code> 的节点之前。</p>

<p>你不需要&nbsp;<strong>保留</strong>&nbsp;每个分区中各节点的初始相对位置。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcci/02.04.Partition%20List/images/partition.jpg" style="width: 662px; height: 222px;" />

<pre>
<strong>输入：</strong>head = [1,4,3,2,5,2], x = 3
<strong>输出</strong>：[1,2,2,4,3,5]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>head = [2,1], x = 2
<strong>输出</strong>：[1,2]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
    <li>链表中节点的数目在范围 <code>[0, 200]</code> 内</li>
    <li><code>-100 &lt;= Node.val &lt;= 100</code></li>
    <li><code>-200 &lt;= x &lt;= 200</code></li>
</ul>

## 解法

### 方法一：拼接链表

我们创建两个链表 $left$ 和 $right$，分别用于存储小于 $x$ 的节点和大于等于 $x$ 的节点。

然后我们用两个指针 $p1$ 和 $p2$ 分别指向 $left$ 和 $right$ 的最后一个节点，初始时 $p1$ 和 $p2$ 都指向一个虚拟头节点。

接下来我们遍历链表 $head$，如果当前节点的值小于 $x$，我们就将当前节点添加到 $left$ 链表的末尾，即 $p1.next = head$，然后令 $p1 = p1.next$；否则我们就将当前节点添加到 $right$ 链表的末尾，即 $p2.next = head$，然后令 $p2 = p2.next$。

遍历结束后，我们将 $left$ 链表的尾节点指向 $right$ 链表的第一个有效节点，即 $p1.next = right.next$，然后将 $right$ 链表的尾节点指向空节点，即 $p2.next = null$。

最后我们返回 $left$ 链表的第一个有效节点。

时间复杂度 $O(n)$，其中 $n$ 是链表的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def partition(self, head: ListNode, x: int) -> ListNode:
        left, right = ListNode(0), ListNode(0)
        p1, p2 = left, right
        while head:
            if head.val < x:
                p1.next = head
                p1 = p1.next
            else:
                p2.next = head
                p2 = p2.next
            head = head.next
        p1.next = right.next
        p2.next = None
        return left.next
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
    public ListNode partition(ListNode head, int x) {
        ListNode left = new ListNode(0);
        ListNode right = new ListNode(0);
        ListNode p1 = left;
        ListNode p2 = right;
        for (; head != null; head = head.next) {
            if (head.val < x) {
                p1.next = head;
                p1 = p1.next;
            } else {
                p2.next = head;
                p2 = p2.next;
            }
        }
        p1.next = right.next;
        p2.next = null;
        return left.next;
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
    ListNode* partition(ListNode* head, int x) {
        ListNode* left = new ListNode(0);
        ListNode* right = new ListNode(0);
        ListNode* p1 = left;
        ListNode* p2 = right;
        for (; head; head = head->next) {
            if (head->val < x) {
                p1->next = head;
                p1 = p1->next;
            } else {
                p2->next = head;
                p2 = p2->next;
            }
        }
        p1->next = right->next;
        p2->next = nullptr;
        return left->next;
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
func partition(head *ListNode, x int) *ListNode {
	left, right := &ListNode{}, &ListNode{}
	p1, p2 := left, right
	for ; head != nil; head = head.Next {
		if head.Val < x {
			p1.Next = head
			p1 = p1.Next
		} else {
			p2.Next = head
			p2 = p2.Next
		}
	}
	p1.Next = right.Next
	p2.Next = nil
	return left.Next
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

function partition(head: ListNode | null, x: number): ListNode | null {
    const [left, right] = [new ListNode(), new ListNode()];
    let [p1, p2] = [left, right];
    for (; head; head = head.next) {
        if (head.val < x) {
            p1.next = head;
            p1 = p1.next;
        } else {
            p2.next = head;
            p2 = p2.next;
        }
    }
    p1.next = right.next;
    p2.next = null;
    return left.next;
}
```

```swift
/** public class ListNode {
*    var val: Int
*    var next: ListNode?
*    init(_ x: Int) {
*        self.val = x
*        self.next = nil
*    }
* }
*/

class Solution {
    func partition(_ head: ListNode?, _ x: Int) -> ListNode? {
        let leftDummy = ListNode(0)
        let rightDummy = ListNode(0)
        var left = leftDummy
        var right = rightDummy
        var head = head

        while let current = head {
            if current.val < x {
                left.next = current
                left = left.next!
            } else {
                right.next = current
                right = right.next!
            }
            head = head?.next
        }

        right.next = nil
        left.next = rightDummy.next

        return leftDummy.next
    }
}
```

<!-- tabs:end -->

<!-- end -->
