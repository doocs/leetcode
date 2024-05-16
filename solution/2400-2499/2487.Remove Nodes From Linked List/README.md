---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2487.Remove%20Nodes%20From%20Linked%20List/README.md
rating: 1454
source: 第 321 场周赛 Q3
tags:
    - 栈
    - 递归
    - 链表
    - 单调栈
---

# [2487. 从链表中移除节点](https://leetcode.cn/problems/remove-nodes-from-linked-list)

[English Version](/solution/2400-2499/2487.Remove%20Nodes%20From%20Linked%20List/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个链表的头节点 <code>head</code> 。</p>

<p>移除每个右侧有一个更大数值的节点。</p>

<p>返回修改后链表的头节点<em> </em><code>head</code><em> </em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2487.Remove%20Nodes%20From%20Linked%20List/images/drawio.png" style="width: 631px; height: 51px;" /></p>

<pre>
<strong>输入：</strong>head = [5,2,13,3,8]
<strong>输出：</strong>[13,8]
<strong>解释：</strong>需要移除的节点是 5 ，2 和 3 。
- 节点 13 在节点 5 右侧。
- 节点 13 在节点 2 右侧。
- 节点 8 在节点 3 右侧。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>head = [1,1,1,1]
<strong>输出：</strong>[1,1,1,1]
<strong>解释：</strong>每个节点的值都是 1 ，所以没有需要移除的节点。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>给定列表中的节点数目在范围 <code>[1, 10<sup>5</sup>]</code> 内</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一：单调栈模拟

我们可以先将链表中的节点值存入数组 $nums$，然后遍历数组 $nums$，维护一个从栈底到栈顶单调递减的栈 $stk$，如果当前元素比栈顶元素大，则将栈顶元素出栈，直到当前元素小于等于栈顶元素，将当前元素入栈。

最后，我们从栈底到栈顶构造出结果链表，即为答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是链表的长度。

我们也可以不使用数组 $nums$，直接遍历链表，维护一个从栈底到栈顶单调递减的栈 $stk$，如果当前元素比栈顶元素大，则将栈顶元素出栈，直到当前元素小于等于栈顶元素。然后，如果栈不为空，则将栈顶元素的 $next$ 指针指向当前元素，否则将答案链表的虚拟头节点的 $next$ 指针指向当前元素。最后，将当前元素入栈，继续遍历链表。

遍历结束后，将虚拟头节点的 $next$ 指针作为答案返回。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是链表的长度。

<!-- tabs:start -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def removeNodes(self, head: Optional[ListNode]) -> Optional[ListNode]:
        nums = []
        while head:
            nums.append(head.val)
            head = head.next
        stk = []
        for v in nums:
            while stk and stk[-1] < v:
                stk.pop()
            stk.append(v)
        dummy = ListNode()
        head = dummy
        for v in stk:
            head.next = ListNode(v)
            head = head.next
        return dummy.next
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
    public ListNode removeNodes(ListNode head) {
        List<Integer> nums = new ArrayList<>();
        while (head != null) {
            nums.add(head.val);
            head = head.next;
        }
        Deque<Integer> stk = new ArrayDeque<>();
        for (int v : nums) {
            while (!stk.isEmpty() && stk.peekLast() < v) {
                stk.pollLast();
            }
            stk.offerLast(v);
        }
        ListNode dummy = new ListNode();
        head = dummy;
        while (!stk.isEmpty()) {
            head.next = new ListNode(stk.pollFirst());
            head = head.next;
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
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* removeNodes(ListNode* head) {
        vector<int> nums;
        while (head) {
            nums.emplace_back(head->val);
            head = head->next;
        }
        vector<int> stk;
        for (int v : nums) {
            while (!stk.empty() && stk.back() < v) {
                stk.pop_back();
            }
            stk.push_back(v);
        }
        ListNode* dummy = new ListNode();
        head = dummy;
        for (int v : stk) {
            head->next = new ListNode(v);
            head = head->next;
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
func removeNodes(head *ListNode) *ListNode {
	nums := []int{}
	for head != nil {
		nums = append(nums, head.Val)
		head = head.Next
	}
	stk := []int{}
	for _, v := range nums {
		for len(stk) > 0 && stk[len(stk)-1] < v {
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, v)
	}
	dummy := &ListNode{}
	head = dummy
	for _, v := range stk {
		head.Next = &ListNode{Val: v}
		head = head.Next
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

function removeNodes(head: ListNode | null): ListNode | null {
    const nums = [];
    for (; head; head = head.next) {
        nums.push(head.val);
    }
    const stk: number[] = [];
    for (const v of nums) {
        while (stk.length && stk.at(-1)! < v) {
            stk.pop();
        }
        stk.push(v);
    }
    const dummy = new ListNode();
    head = dummy;
    for (const v of stk) {
        head.next = new ListNode(v);
        head = head.next;
    }
    return dummy.next;
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def removeNodes(self, head: Optional[ListNode]) -> Optional[ListNode]:
        dummy = ListNode(inf, head)
        cur = head
        stk = [dummy]
        while cur:
            while stk[-1].val < cur.val:
                stk.pop()
            stk[-1].next = cur
            stk.append(cur)
            cur = cur.next
        return dummy.next
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
    public ListNode removeNodes(ListNode head) {
        ListNode dummy = new ListNode(1 << 30, head);
        Deque<ListNode> stk = new ArrayDeque<>();
        stk.offerLast(dummy);
        for (ListNode cur = head; cur != null; cur = cur.next) {
            while (stk.peekLast().val < cur.val) {
                stk.pollLast();
            }
            stk.peekLast().next = cur;
            stk.offerLast(cur);
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
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* removeNodes(ListNode* head) {
        ListNode* dummy = new ListNode(1e9, head);
        ListNode* cur = head;
        vector<ListNode*> stk = {dummy};
        for (ListNode* cur = head; cur; cur = cur->next) {
            while (stk.back()->val < cur->val) {
                stk.pop_back();
            }
            stk.back()->next = cur;
            stk.push_back(cur);
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
func removeNodes(head *ListNode) *ListNode {
	dummy := &ListNode{1 << 30, head}
	stk := []*ListNode{dummy}
	for cur := head; cur != nil; cur = cur.Next {
		for stk[len(stk)-1].Val < cur.Val {
			stk = stk[:len(stk)-1]
		}
		stk[len(stk)-1].Next = cur
		stk = append(stk, cur)
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

function removeNodes(head: ListNode | null): ListNode | null {
    const dummy = new ListNode(Infinity, head);
    const stk: ListNode[] = [dummy];
    for (let cur = head; cur; cur = cur.next) {
        while (stk.at(-1)!.val < cur.val) {
            stk.pop();
        }
        stk.at(-1)!.next = cur;
        stk.push(cur);
    }
    return dummy.next;
}
```

<!-- tabs:end -->

<!-- end -->
