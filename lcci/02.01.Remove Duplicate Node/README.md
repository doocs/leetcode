---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/02.01.Remove%20Duplicate%20Node/README.md
---

# [面试题 02.01. 移除重复节点](https://leetcode.cn/problems/remove-duplicate-node-lcci)

[English Version](/lcci/02.01.Remove%20Duplicate%20Node/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。</p>

<p> <strong>示例1:</strong></p>

<pre>
<strong> 输入</strong>：[1, 2, 3, 3, 2, 1]
<strong> 输出</strong>：[1, 2, 3]
</pre>

<p> <strong>示例2:</strong></p>

<pre>
<strong> 输入</strong>：[1, 1, 1, 1, 2]
<strong> 输出</strong>：[1, 2]
</pre>

<p><strong>提示：</strong></p>

<ol>
<li>链表长度在[0, 20000]范围内。</li>
<li>链表元素在[0, 20000]范围内。</li>
</ol>

<p> <strong>进阶：</strong></p>

<p>如果不得使用临时缓冲区，该怎么解决？</p>

## 解法

### 方法一：哈希表

我们创建一个哈希表 $vis$，用于记录已经访问过的节点的值。

然后我们创建一个虚拟节点 $pre$，使得 $pre.next = head$。

接下来我们遍历链表，如果当前节点的值已经在哈希表中，我们就将当前节点删除，即 $pre.next = pre.next.next$；否则，我们将当前节点的值加入哈希表中，并将 $pre$ 指向下一个节点。

遍历结束后，我们返回链表的头节点。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为链表的长度。

<!-- tabs:start -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def removeDuplicateNodes(self, head: ListNode) -> ListNode:
        vis = set()
        pre = ListNode(0, head)
        while pre.next:
            if pre.next.val in vis:
                pre.next = pre.next.next
            else:
                vis.add(pre.next.val)
                pre = pre.next
        return head
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
    public ListNode removeDuplicateNodes(ListNode head) {
        Set<Integer> vis = new HashSet<>();
        ListNode pre = new ListNode(0, head);
        while (pre.next != null) {
            if (vis.add(pre.next.val)) {
                pre = pre.next;
            } else {
                pre.next = pre.next.next;
            }
        }
        return head;
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
    ListNode* removeDuplicateNodes(ListNode* head) {
        unordered_set<int> vis;
        ListNode* pre = new ListNode(0, head);
        while (pre->next) {
            if (vis.count(pre->next->val)) {
                pre->next = pre->next->next;
            } else {
                vis.insert(pre->next->val);
                pre = pre->next;
            }
        }
        return head;
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
func removeDuplicateNodes(head *ListNode) *ListNode {
	vis := map[int]bool{}
	pre := &ListNode{0, head}
	for pre.Next != nil {
		if vis[pre.Next.Val] {
			pre.Next = pre.Next.Next
		} else {
			vis[pre.Next.Val] = true
			pre = pre.Next
		}
	}
	return head
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

function removeDuplicateNodes(head: ListNode | null): ListNode | null {
    const vis: Set<number> = new Set();
    let pre: ListNode = new ListNode(0, head);
    while (pre.next) {
        if (vis.has(pre.next.val)) {
            pre.next = pre.next.next;
        } else {
            vis.add(pre.next.val);
            pre = pre.next;
        }
    }
    return head;
}
```

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
    pub fn remove_duplicate_nodes(mut head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut vis = HashSet::new();
        let mut pre = ListNode::new(0);
        pre.next = head;
        let mut cur = &mut pre;
        while let Some(node) = cur.next.take() {
            if vis.contains(&node.val) {
                cur.next = node.next;
            } else {
                vis.insert(node.val);
                cur.next = Some(node);
                cur = cur.next.as_mut().unwrap();
            }
        }
        pre.next
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
 * @param {ListNode} head
 * @return {ListNode}
 */
var removeDuplicateNodes = function (head) {
    const vis = new Set();
    let pre = new ListNode(0, head);
    while (pre.next) {
        if (vis.has(pre.next.val)) {
            pre.next = pre.next.next;
        } else {
            vis.add(pre.next.val);
            pre = pre.next;
        }
    }
    return head;
};
```

```swift
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *   var val: Int
 *   var next: ListNode?
 *   init(_ x: Int, _ next: ListNode? = nil) {
 *       self.val = x
 *       self.next = next
 *   }
 * }
*/

class Solution {
    func removeDuplicateNodes(_ head: ListNode?) -> ListNode? {
        var vis = Set<Int>()
        let pre = ListNode(0, head)
        var current: ListNode? = pre

        while current?.next != nil {
            if vis.insert(current!.next!.val).inserted {
                current = current?.next
            } else {
                current?.next = current?.next?.next
            }
        }

        return head
    }
}
```

<!-- tabs:end -->

<!-- end -->
