# [2674. 拆分循环链表](https://leetcode.cn/problems/split-a-circular-linked-list)

[English Version](/solution/2600-2699/2674.Split%20a%20Circular%20Linked%20List/README_EN.md)

<!-- tags:链表,双指针 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>现给定一个由正整数组成的 <strong>循环链表</strong> <code>list</code> ，你的任务是将其拆分为 2 个 <strong>循环链表</strong> ，使得第一个链表包含 <code>list</code> <strong>前半部分&nbsp;</strong>的节点（即 <code>ceil(list.length / 2)</code> 个节点），顺序与 list 中的顺序相同，而第二个链表包含 <code>list</code> 中 <strong>剩余</strong> 的节点，顺序也与 <code>list</code> 中的顺序相同。</p>

<p>返回一个长度为 2 的数组，其中第一个元素是表示 <strong>前半部分</strong> 链表的<strong> 循环链表</strong> ，第二个元素是表示 <strong>后半部分</strong> 链表的 <strong>循环链表</strong> 。</p>

<p><strong>循环链表</strong> 是一个普通的链表，唯一的区别是最后一个节点的下一个节点是头节点。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,5,7]
<b>输出：</b>[[1,5],[7]]
<b>解释：</b>初始链表有3个节点，因此前半部分是前两个元素，剩下的 1 个节点在后半部分。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [2,6,1,5]
<b>输出：</b>[[2,6],[1,5]]
<b>解释：</b>初始链表有4个节点，因此前半部分是前两个元素，剩下的 2 个节点在后半部分。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>list</code> 中的节点数范围为 <code>[2, 105]</code></li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
	<li><code>LastNode.next = FirstNode</code> ，其中 <code>LastNode</code> 是链表的最后一个节点，<code>FirstNode</code> 是第一个节点。</li>
</ul>

## 解法

### 方法一：快慢指针

我们定义两个指针 $a$ 和 $b$，初始时都指向链表的头节点。每次迭代时，指针 $a$ 向前移动一步，指针 $b$ 向前移动两步，直到指针 $b$ 到达链表的末尾。此时，指针 $a$ 指向链表节点数的一半，我们将链表从指针 $a$ 处断开，即可得到两个链表的头节点。

时间复杂度 $O(n)$，其中 $n$ 是链表的长度。需要遍历链表一次。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def splitCircularLinkedList(
        self, list: Optional[ListNode]
    ) -> List[Optional[ListNode]]:
        a = b = list
        while b.next != list and b.next.next != list:
            a = a.next
            b = b.next.next
        if b.next != list:
            b = b.next
        list2 = a.next
        b.next = list2
        a.next = list
        return [list, list2]
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
    public ListNode[] splitCircularLinkedList(ListNode list) {
        ListNode a = list, b = list;
        while (b.next != list && b.next.next != list) {
            a = a.next;
            b = b.next.next;
        }
        if (b.next != list) {
            b = b.next;
        }
        ListNode list2 = a.next;
        b.next = list2;
        a.next = list;
        return new ListNode[] {list, list2};
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
    vector<ListNode*> splitCircularLinkedList(ListNode* list) {
        ListNode* a = list;
        ListNode* b = list;
        while (b->next != list && b->next->next != list) {
            a = a->next;
            b = b->next->next;
        }
        if (b->next != list) {
            b = b->next;
        }
        ListNode* list2 = a->next;
        b->next = list2;
        a->next = list;
        return {list, list2};
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
func splitCircularLinkedList(list *ListNode) []*ListNode {
	a, b := list, list
	for b.Next != list && b.Next.Next != list {
		a = a.Next
		b = b.Next.Next
	}
	if b.Next != list {
		b = b.Next
	}
	list2 := a.Next
	b.Next = list2
	a.Next = list
	return []*ListNode{list, list2}
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

function splitCircularLinkedList(list: ListNode | null): Array<ListNode | null> {
    let a = list;
    let b = list;
    while (b.next !== list && b.next.next !== list) {
        a = a.next;
        b = b.next.next;
    }
    if (b.next !== list) {
        b = b.next;
    }
    const list2 = a.next;
    b.next = list2;
    a.next = list;
    return [list, list2];
}
```

<!-- tabs:end -->

<!-- end -->
