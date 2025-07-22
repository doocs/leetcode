---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2095.Delete%20the%20Middle%20Node%20of%20a%20Linked%20List/README.md
rating: 1324
source: 第 270 场周赛 Q2
tags:
    - 链表
    - 双指针
---

<!-- problem:start -->

# [2095. 删除链表的中间节点](https://leetcode.cn/problems/delete-the-middle-node-of-a-linked-list)

[English Version](/solution/2000-2099/2095.Delete%20the%20Middle%20Node%20of%20a%20Linked%20List/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个链表的头节点 <code>head</code> 。<strong>删除</strong> 链表的 <strong>中间节点</strong> ，并返回修改后的链表的头节点 <code>head</code> 。</p>

<p>长度为 <code>n</code> 链表的中间节点是从头数起第 <code>⌊n / 2⌋</code> 个节点（下标从 <strong>0</strong> 开始），其中 <code>⌊x⌋</code> 表示小于或等于 <code>x</code> 的最大整数。</p>

<ul>
	<li>对于 <code>n</code> = <code>1</code>、<code>2</code>、<code>3</code>、<code>4</code> 和 <code>5</code> 的情况，中间节点的下标分别是 <code>0</code>、<code>1</code>、<code>1</code>、<code>2</code> 和 <code>2</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2095.Delete%20the%20Middle%20Node%20of%20a%20Linked%20List/images/eg1drawio.png" style="width: 500px; height: 77px;" /></p>

<pre>
<strong>输入：</strong>head = [1,3,4,7,1,2,6]
<strong>输出：</strong>[1,3,4,1,2,6]
<strong>解释：</strong>
上图表示给出的链表。节点的下标分别标注在每个节点的下方。
由于 n = 7 ，值为 7 的节点 3 是中间节点，用红色标注。
返回结果为移除节点后的新链表。 
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2095.Delete%20the%20Middle%20Node%20of%20a%20Linked%20List/images/eg2drawio.png" style="width: 250px; height: 43px;" /></p>

<pre>
<strong>输入：</strong>head = [1,2,3,4]
<strong>输出：</strong>[1,2,4]
<strong>解释：</strong>
上图表示给出的链表。
对于 n = 4 ，值为 3 的节点 2 是中间节点，用红色标注。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2095.Delete%20the%20Middle%20Node%20of%20a%20Linked%20List/images/eg3drawio.png" style="width: 150px; height: 58px;" /></p>

<pre>
<strong>输入：</strong>head = [2,1]
<strong>输出：</strong>[2]
<strong>解释：</strong>
上图表示给出的链表。
对于 n = 2 ，值为 1 的节点 1 是中间节点，用红色标注。
值为 2 的节点 0 是移除节点 1 后剩下的唯一一个节点。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>链表中节点的数目在范围 <code>[1, 10<sup>5</sup>]</code> 内</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：快慢指针

快慢指针法是一种用于解决链表中的问题的常用技巧。我们可以维护两个指针，一个慢指针 $\textit{slow}$ 和一个快指针 $\textit{fast}$。初始时 $\textit{slow}$ 指向一个虚拟节点，该虚拟节点的 $\textit{next}$ 指针指向链表的头节点 $\textit{head}$，而 $\textit{fast}$ 指向链表的头节点 $\textit{head}$。

然后，我们每次将慢指针向后移动一个位置，将快指针向后移动两个位置，直到快指针到达链表的末尾。此时，慢指针指向的节点的下一个节点就是链表的中间节点。我们将慢指针指向的节点的 $\textit{next}$ 指针指向下下个节点，即可删除中间节点。

时间复杂度 $O(n)$，其中 $n$ 是链表的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def deleteMiddle(self, head: Optional[ListNode]) -> Optional[ListNode]:
        dummy = ListNode(next=head)
        slow, fast = dummy, head
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
        slow.next = slow.next.next
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
    public ListNode deleteMiddle(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = slow.next.next;
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
    ListNode* deleteMiddle(ListNode* head) {
        ListNode* dummy = new ListNode(0, head);
        ListNode* slow = dummy;
        ListNode* fast = head;
        while (fast && fast->next) {
            slow = slow->next;
            fast = fast->next->next;
        }
        slow->next = slow->next->next;
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
func deleteMiddle(head *ListNode) *ListNode {
	dummy := &ListNode{Val: 0, Next: head}
	slow, fast := dummy, dummy.Next
	for fast != nil && fast.Next != nil {
		slow, fast = slow.Next, fast.Next.Next
	}
	slow.Next = slow.Next.Next
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

function deleteMiddle(head: ListNode | null): ListNode | null {
    const dummy = new ListNode(0, head);
    let [slow, fast] = [dummy, head];
    while (fast && fast.next) {
        slow = slow.next;
        fast = fast.next.next;
    }
    slow.next = slow.next.next;
    return dummy.next;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
