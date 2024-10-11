---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1474.Delete%20N%20Nodes%20After%20M%20Nodes%20of%20a%20Linked%20List/README.md
tags:
    - 链表
---

<!-- problem:start -->

# [1474. 删除链表 M 个节点之后的 N 个节点 🔒](https://leetcode.cn/problems/delete-n-nodes-after-m-nodes-of-a-linked-list)

[English Version](/solution/1400-1499/1474.Delete%20N%20Nodes%20After%20M%20Nodes%20of%20a%20Linked%20List/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定链表&nbsp;<code>head</code>&nbsp;和两个整数&nbsp;<code>m</code>&nbsp;和&nbsp;<code>n</code>. 遍历该链表并按照如下方式删除节点:</p>

<ul>
	<li>开始时以头节点作为当前节点.</li>
	<li>保留以当前节点开始的前&nbsp;<code>m</code>&nbsp;个节点.</li>
	<li>删除接下来的&nbsp;<code>n</code>&nbsp;个节点.</li>
	<li>重复步骤 2 和 3,&nbsp;直到到达链表结尾.</li>
</ul>

<p>在删除了指定结点之后,&nbsp;返回修改过后的链表的头节点.</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1474.Delete%20N%20Nodes%20After%20M%20Nodes%20of%20a%20Linked%20List/images/sample_1_1848.png" style="height: 95px; width: 620px;" /></strong></p>

<pre>
<strong>输入:</strong> head = [1,2,3,4,5,6,7,8,9,10,11,12,13], m = 2, n = 3
<strong>输出:</strong> [1,2,6,7,11,12]
<strong>解析: </strong>保留前(m = 2)个结点,  也就是以黑色节点表示的从链表头结点开始的结点(1 -&gt;2).
删除接下来的(n = 3)个结点(3 -&gt; 4 -&gt; 5), 在图中以红色结点表示.
继续相同的操作, 直到链表的末尾.
返回删除结点之后的链表的头结点.</pre>

<p><strong>示例 2:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1474.Delete%20N%20Nodes%20After%20M%20Nodes%20of%20a%20Linked%20List/images/sample_2_1848.png" style="height: 123px; width: 620px;" /></strong></p>

<pre>
<strong>输入:</strong> head = [1,2,3,4,5,6,7,8,9,10,11], m = 1, n = 3
<strong>输出:</strong> [1,5,9]
<strong>解析:</strong> 返回删除结点之后的链表的头结点.</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> head = [1,2,3,4,5,6,7,8,9,10,11], m = 3, n = 1
<strong>输出:</strong> [1,2,3,5,6,7,9,10,11]
</pre>

<p><strong>示例&nbsp;4:</strong></p>

<pre>
<strong>输入:</strong> head = [9,3,7,7,9,10,8,2], m = 1, n = 2
<strong>输出:</strong> [9,7,8]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li>链表中节点数目在范围 <code>[1, 10<sup>4</sup>]</code> 内</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= m, n &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶:</strong> 你能通过<strong> 就地 </strong>修改链表的方式解决这个问题吗?</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以模拟整个删除过程，首先用 $\textit{pre}$ 指针指向链表头部，然后遍历链表，移动 $m - 1$ 步，如果 $\textit{pre}$ 为空，说明从当前节点开始的节点个数小于 $m$，直接返回头部；否则，用 $\textit{cur}$ 指针指向 $\textit{pre}$，然后移动 $n$ 步，如果 $\textit{cur}$ 为空，说明从 $\textit{pre}$ 开始的节点个数小于 $m + n$，直接将 $\textit{pre}$ 的 $\textit{next}$ 指向 $\text{null}$；否则，将 $\textit{pre}$ 的 $\textit{next}$ 指向 $\textit{cur}$ 的 $\textit{next}$，然后将 $\textit{pre}$ 移动到 $\textit{pre}$ 的 $\textit{next}$。继续遍历链表，直到 $\textit{pre}$ 为空，返回头部。

时间复杂度 $O(n)$，其中 $n$ 是链表中节点的个数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def deleteNodes(self, head: ListNode, m: int, n: int) -> ListNode:
        pre = head
        while pre:
            for _ in range(m - 1):
                if pre:
                    pre = pre.next
            if pre is None:
                return head
            cur = pre
            for _ in range(n):
                if cur:
                    cur = cur.next
            pre.next = None if cur is None else cur.next
            pre = pre.next
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
    public ListNode deleteNodes(ListNode head, int m, int n) {
        ListNode pre = head;
        while (pre != null) {
            for (int i = 0; i < m - 1 && pre != null; ++i) {
                pre = pre.next;
            }
            if (pre == null) {
                return head;
            }
            ListNode cur = pre;
            for (int i = 0; i < n && cur != null; ++i) {
                cur = cur.next;
            }
            pre.next = cur == null ? null : cur.next;
            pre = pre.next;
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
    ListNode* deleteNodes(ListNode* head, int m, int n) {
        auto pre = head;
        while (pre) {
            for (int i = 0; i < m - 1 && pre; ++i) {
                pre = pre->next;
            }
            if (!pre) {
                return head;
            }
            auto cur = pre;
            for (int i = 0; i < n && cur; ++i) {
                cur = cur->next;
            }
            pre->next = cur ? cur->next : nullptr;
            pre = pre->next;
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
func deleteNodes(head *ListNode, m int, n int) *ListNode {
	pre := head
	for pre != nil {
		for i := 0; i < m-1 && pre != nil; i++ {
			pre = pre.Next
		}
		if pre == nil {
			return head
		}
		cur := pre
		for i := 0; i < n && cur != nil; i++ {
			cur = cur.Next
		}
		pre.Next = nil
		if cur != nil {
			pre.Next = cur.Next
		}
		pre = pre.Next
	}
	return head
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

function deleteNodes(head: ListNode | null, m: number, n: number): ListNode | null {
    let pre = head;
    while (pre) {
        for (let i = 0; i < m - 1 && pre; ++i) {
            pre = pre.next;
        }
        if (!pre) {
            break;
        }
        let cur = pre;
        for (let i = 0; i < n && cur; ++i) {
            cur = cur.next;
        }
        pre.next = cur?.next || null;
        pre = pre.next;
    }
    return head;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
