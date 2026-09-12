---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0369.Plus%20One%20Linked%20List/README.md
tags:
    - 链表
    - 数学
---

<!-- problem:start -->

# [369. 给单链表加一 🔒](https://leetcode.cn/problems/plus-one-linked-list)

[English Version](/solution/0300-0399/0369.Plus%20One%20Linked%20List/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个用<strong>链表</strong>表示的非负整数， 然后将这个整数&nbsp;<em>再加上 1</em> 。</p>

<p>这些数字的存储是这样的：最高位有效的数字位于链表的首位<meta charset="UTF-8" />&nbsp;<code>head</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong>head = [1,2,3]
<strong>输出: </strong>[1,2,4]
</pre>

<p><meta charset="UTF-8" /></p>

<p><strong>示例</strong><strong>&nbsp;2:</strong></p>

<pre>
<strong>输入: </strong>head = [0]
<strong>输出: </strong>[1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>链表中的节点数在<meta charset="UTF-8" />&nbsp;<code>[1, 100]</code>&nbsp;的范围内。</li>
	<li><code>0 &lt;= Node.val &lt;= 9</code></li>
	<li>由链表表示的数字不包含前导零，除了零本身。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：链表遍历

<!-- thinking:start -->

> **思考**
>
> 链表表示的非负整数加一。转成数组再重建需 $O(n)$ 额外空间。进位只影响末尾连续的 $9$。
>
> 设哑结点以免最高位进位丢头。一次扫描记下最后一个非 $9$，将其加一，其后一律置 $0$。若哑结点变为 $1$ 则它就是新头。

<!-- thinking:end -->

我们先设置一个虚拟头节点 $\textit{dummy}$，初始时 $\textit{dummy}$ 的值为 $0$，并且 $\textit{dummy}$ 的后继节点为链表 $\textit{head}$。

接下来，我们从虚拟头节点开始遍历链表，找到最后一个不为 $9$ 的节点，将其值加 $1$，并将该节点之后的所有节点的值置为 $0$。

最后，我们判断虚拟头节点的值是否为 $1$，如果为 $1$，则返回 $\textit{dummy}$，否则返回 $\textit{dummy}$ 的后继节点。

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
    def plusOne(self, head: Optional[ListNode]) -> Optional[ListNode]:
        dummy = ListNode(0, head)
        target = dummy
        while head:
            if head.val != 9:
                target = head
            head = head.next
        target.val += 1
        target = target.next
        while target:
            target.val = 0
            target = target.next
        return dummy if dummy.val else dummy.next
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
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode target = dummy;
        while (head != null) {
            if (head.val != 9) {
                target = head;
            }
            head = head.next;
        }
        ++target.val;
        target = target.next;
        while (target != null) {
            target.val = 0;
            target = target.next;
        }
        return dummy.val == 1 ? dummy : dummy.next;
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
    ListNode* plusOne(ListNode* head) {
        ListNode* dummy = new ListNode(0, head);
        ListNode* target = dummy;
        for (; head; head = head->next) {
            if (head->val != 9) {
                target = head;
            }
        }
        target->val++;
        for (target = target->next; target; target = target->next) {
            target->val = 0;
        }
        return dummy->val ? dummy : dummy->next;
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
func plusOne(head *ListNode) *ListNode {
	dummy := &ListNode{0, head}
	target := dummy
	for head != nil {
		if head.Val != 9 {
			target = head
		}
		head = head.Next
	}
	target.Val++
	for target = target.Next; target != nil; target = target.Next {
		target.Val = 0
	}
	if dummy.Val == 1 {
		return dummy
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

function plusOne(head: ListNode | null): ListNode | null {
    const dummy = new ListNode(0, head);
    let target = dummy;
    while (head) {
        if (head.val !== 9) {
            target = head;
        }
        head = head.next;
    }
    target.val++;
    for (target = target.next; target; target = target.next) {
        target.val = 0;
    }
    return dummy.val ? dummy : dummy.next;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
