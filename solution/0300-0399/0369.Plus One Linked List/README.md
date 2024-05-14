---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0369.Plus%20One%20Linked%20List/README.md
tags:
    - 链表
    - 数学
---

# [369. 给单链表加一 🔒](https://leetcode.cn/problems/plus-one-linked-list)

[English Version](/solution/0300-0399/0369.Plus%20One%20Linked%20List/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

### 方法一：链表遍历

我们先设置一个虚拟头节点 `dummy`，初始值为 $0$，指向链表头节点 `head`。

然后从链表头节点开始遍历，找出链表最后一个值不等于 $9$ 的节点 `target`，将 `target` 的值加 $1$。接着将 `target` 之后的所有节点值置为 $0$。

需要注意的是，如果链表中所有节点值都为 $9$，那么遍历结束后，`target` 会指向空节点，这时我们需要将 `dummy` 的值加 $1$，然后返回 `dummy`，否则返回 `dummy` 的下一个节点。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为链表的长度。

<!-- tabs:start -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def plusOne(self, head: ListNode) -> ListNode:
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
        while (head) {
            if (head->val != 9) target = head;
            head = head->next;
        }
        ++target->val;
        target = target->next;
        while (target) {
            target->val = 0;
            target = target->next;
        }
        return dummy->val == 1 ? dummy : dummy->next;
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
	target = target.Next
	for target != nil {
		target.Val = 0
		target = target.Next
	}
	if dummy.Val == 1 {
		return dummy
	}
	return dummy.Next
}
```

<!-- tabs:end -->

<!-- end -->
