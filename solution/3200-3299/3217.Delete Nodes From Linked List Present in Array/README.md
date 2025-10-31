---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3217.Delete%20Nodes%20From%20Linked%20List%20Present%20in%20Array/README.md
rating: 1341
source: 第 406 场周赛 Q2
tags:
    - 数组
    - 哈希表
    - 链表
---

<!-- problem:start -->

# [3217. 从链表中移除在数组中存在的节点](https://leetcode.cn/problems/delete-nodes-from-linked-list-present-in-array)

[English Version](/solution/3200-3299/3217.Delete%20Nodes%20From%20Linked%20List%20Present%20in%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 和一个链表的头节点 <code>head</code>。从链表中<strong>移除</strong>所有存在于 <code>nums</code> 中的节点后，返回修改后的链表的头节点。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3], head = [1,2,3,4,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">[4,5]</span></p>

<p><strong>解释：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3217.Delete%20Nodes%20From%20Linked%20List%20Present%20in%20Array/images/linkedlistexample0.png" style="width: 400px; height: 66px;" /></strong></p>

<p>移除数值为 1, 2 和 3 的节点。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1], head = [1,2,1,2,1,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">[2,2,2]</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3217.Delete%20Nodes%20From%20Linked%20List%20Present%20in%20Array/images/linkedlistexample1.png" style="height: 62px; width: 450px;" /></p>

<p>移除数值为 1 的节点。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [5], head = [1,2,3,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">[1,2,3,4]</span></p>

<p><strong>解释：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3217.Delete%20Nodes%20From%20Linked%20List%20Present%20in%20Array/images/linkedlistexample2.png" style="width: 400px; height: 83px;" /></strong></p>

<p>链表中不存在值为 5 的节点。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>nums</code> 中的所有元素都是唯一的。</li>
	<li>链表中的节点数在 <code>[1, 10<sup>5</sup>]</code> 的范围内。</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
	<li>输入保证链表中至少有一个值没有在&nbsp;<code>nums</code> 中出现过。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们可以使用一个哈希表 $\textit{s}$ 来存储数组 $\textit{nums}$ 中的所有元素，然后定义一个虚拟节点 $\textit{dummy}$，将其指向链表的头节点 $\textit{head}$。

接下来，我们遍历从虚拟节点 $\textit{dummy}$ 开始的链表，如果当前节点的下一个节点的值在哈希表 $\textit{s}$ 中，我们就将当前节点的指针指向下下个节点，否则我们就将当前节点指针指向下一个节点。

最后，我们返回虚拟节点 $\textit{dummy}$ 的下一个节点。

时间复杂度 $O(n + m)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度，而 $m$ 为链表 $\textit{head}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def modifiedList(
        self, nums: List[int], head: Optional[ListNode]
    ) -> Optional[ListNode]:
        s = set(nums)
        pre = dummy = ListNode(next=head)
        while pre.next:
            if pre.next.val in s:
                pre.next = pre.next.next
            else:
                pre = pre.next
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
    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> s = new HashSet<>();
        for (int x : nums) {
            s.add(x);
        }
        ListNode dummy = new ListNode(0, head);
        for (ListNode pre = dummy; pre.next != null;) {
            if (s.contains(pre.next.val)) {
                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
        }
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
    ListNode* modifiedList(vector<int>& nums, ListNode* head) {
        unordered_set<int> s(nums.begin(), nums.end());
        ListNode* dummy = new ListNode(0, head);
        for (ListNode* pre = dummy; pre->next;) {
            if (s.count(pre->next->val)) {
                pre->next = pre->next->next;
            } else {
                pre = pre->next;
            }
        }
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
func modifiedList(nums []int, head *ListNode) *ListNode {
	s := map[int]bool{}
	for _, x := range nums {
		s[x] = true
	}
	dummy := &ListNode{Next: head}
	for pre := dummy; pre.Next != nil; {
		if s[pre.Next.Val] {
			pre.Next = pre.Next.Next
		} else {
			pre = pre.Next
		}
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

function modifiedList(nums: number[], head: ListNode | null): ListNode | null {
    const s: Set<number> = new Set(nums);
    const dummy = new ListNode(0, head);
    for (let pre = dummy; pre.next; ) {
        if (s.has(pre.next.val)) {
            pre.next = pre.next.next;
        } else {
            pre = pre.next;
        }
    }
    return dummy.next;
}
```

#### Rust

```rust
// Definition for singly-linked list.
// #[derive(PartialEq, Eq, Clone, Debug)]
// pub struct ListNode {
//   pub val: i32,
//   pub next: Option<Box<ListNode>>
// }
//
// impl ListNode {
//   #[inline]
//   fn new(val: i32) -> Self {
//     ListNode {
//       next: None,
//       val
//     }
//   }
// }

use std::collections::HashSet;

impl Solution {
    pub fn modified_list(nums: Vec<i32>, head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let s: HashSet<i32> = nums.into_iter().collect();
        let mut dummy = Box::new(ListNode { val: 0, next: head });
        let mut pre = &mut dummy;

        while let Some(ref mut node) = pre.next {
            if s.contains(&node.val) {
                pre.next = node.next.take();
            } else {
                pre = pre.next.as_mut().unwrap();
            }
        }

        dummy.next
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
