---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2181.Merge%20Nodes%20in%20Between%20Zeros/README.md
rating: 1333
source: 第 281 场周赛 Q2
tags:
    - 链表
    - 模拟
---

<!-- problem:start -->

# [2181. 合并零之间的节点](https://leetcode.cn/problems/merge-nodes-in-between-zeros)

[English Version](/solution/2100-2199/2181.Merge%20Nodes%20in%20Between%20Zeros/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个链表的头节点 <code>head</code> ，该链表包含由 <code>0</code> 分隔开的一连串整数。链表的 <strong>开端</strong> 和 <strong>末尾</strong> 的节点都满足 <code>Node.val == 0</code> 。</p>

<p>对于每两个相邻的 <code>0</code> ，请你将它们之间的所有节点合并成一个节点，其值是所有已合并节点的值之和。然后将所有 <code>0</code> 移除，修改后的链表不应该含有任何 <code>0</code> 。</p>

<p>&nbsp;返回修改后链表的头节点 <code>head</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：<br />
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2181.Merge%20Nodes%20in%20Between%20Zeros/images/ex1-1.png" style="width: 600px; height: 41px;" /></strong></p>

<pre>
<strong>输入：</strong>head = [0,3,1,0,4,5,2,0]
<strong>输出：</strong>[4,11]
<strong>解释：</strong>
上图表示输入的链表。修改后的链表包含：
- 标记为绿色的节点之和：3 + 1 = 4
- 标记为红色的节点之和：4 + 5 + 2 = 11
</pre>

<p><strong>示例 2：<br />
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2181.Merge%20Nodes%20in%20Between%20Zeros/images/ex2-1.png" style="width: 600px; height: 41px;" /></strong></p>

<pre>
<strong>输入：</strong>head = [0,1,0,3,0,2,2,0]
<strong>输出：</strong>[1,3,4]
<strong>解释：</strong>
上图表示输入的链表。修改后的链表包含：
- 标记为绿色的节点之和：1 = 1
- 标记为红色的节点之和：3 = 3
- 标记为黄色的节点之和：2 + 2 = 4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>列表中的节点数目在范围 <code>[3, 2 * 10<sup>5</sup>]</code> 内</li>
	<li><code>0 &lt;= Node.val &lt;= 1000</code></li>
	<li><strong>不</strong> 存在连续两个&nbsp;<code>Node.val == 0</code> 的节点</li>
	<li>链表的 <strong>开端</strong> 和 <strong>末尾</strong> 节点都满足 <code>Node.val == 0</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们定义一个虚拟头节点 $\textit{dummy}$，以及一个指向当前节点的指针 $\textit{tail}$，一个变量 $\textit{s}$ 用来记录当前节点的值之和。

接下来，我们从链表的第二个节点开始遍历，如果当前节点的值不为 0，我们将其加到 $\textit{s}$ 上，否则我们将 $\textit{s}$ 加到 $\textit{tail}$ 的后面，并将 $\textit{s}$ 置为 0，更新 $\textit{tail}$ 为 $\textit{tail}$ 的下一个节点。

最后，我们返回 $\textit{dummy}$ 的下一个节点。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为链表的长度。

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def mergeNodes(self, head: Optional[ListNode]) -> Optional[ListNode]:
        dummy = tail = ListNode()
        s = 0
        cur = head.next
        while cur:
            if cur.val:
                s += cur.val
            else:
                tail.next = ListNode(s)
                tail = tail.next
                s = 0
            cur = cur.next
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
    public ListNode mergeNodes(ListNode head) {
        ListNode dummy = new ListNode();
        int s = 0;
        ListNode tail = dummy;
        for (ListNode cur = head.next; cur != null; cur = cur.next) {
            if (cur.val != 0) {
                s += cur.val;
            } else {
                tail.next = new ListNode(s);
                tail = tail.next;
                s = 0;
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
    ListNode* mergeNodes(ListNode* head) {
        ListNode* dummy = new ListNode();
        ListNode* tail = dummy;
        int s = 0;
        for (ListNode* cur = head->next; cur; cur = cur->next) {
            if (cur->val) {
                s += cur->val;
            } else {
                tail->next = new ListNode(s);
                tail = tail->next;
                s = 0;
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
func mergeNodes(head *ListNode) *ListNode {
	dummy := &ListNode{}
	tail := dummy
	s := 0
	for cur := head.Next; cur != nil; cur = cur.Next {
		if cur.Val != 0 {
			s += cur.Val
		} else {
			tail.Next = &ListNode{Val: s}
			tail = tail.Next
			s = 0
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

function mergeNodes(head: ListNode | null): ListNode | null {
    const dummy = new ListNode();
    let tail = dummy;
    let s = 0;
    for (let cur = head.next; cur; cur = cur.next) {
        if (cur.val) {
            s += cur.val;
        } else {
            tail.next = new ListNode(s);
            tail = tail.next;
            s = 0;
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
impl Solution {
    pub fn merge_nodes(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut dummy = Box::new(ListNode::new(0));
        let mut tail = &mut dummy;
        let mut s = 0;
        let mut cur = head.unwrap().next;

        while let Some(mut node) = cur {
            if node.val != 0 {
                s += node.val;
            } else {
                tail.next = Some(Box::new(ListNode::new(s)));
                tail = tail.next.as_mut().unwrap();
                s = 0;
            }
            cur = node.next.take();
        }

        dummy.next
    }
}
```

#### C

```c
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */

struct ListNode* mergeNodes(struct ListNode* head) {
    struct ListNode dummy;
    struct ListNode* cur = &dummy;
    int sum = 0;
    while (head) {
        if (head->val == 0 && sum != 0) {
            cur->next = malloc(sizeof(struct ListNode));
            cur->next->val = sum;
            cur->next->next = NULL;
            cur = cur->next;
            sum = 0;
        }
        sum += head->val;
        head = head->next;
    }
    return dummy.next;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
