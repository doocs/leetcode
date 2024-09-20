---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3294.Convert%20Doubly%20Linked%20List%20to%20Array%20II/README.md
---

<!-- problem:start -->

# [3294. 将双链表转换为数组 II 🔒](https://leetcode.cn/problems/convert-doubly-linked-list-to-array-ii)

[English Version](/solution/3200-3299/3294.Convert%20Doubly%20Linked%20List%20to%20Array%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个 <strong>双链表&nbsp;</strong>的&nbsp;<b>任意</b>&nbsp;<code>node</code>，其中的节点具有指向下一个节点的指针和上一个节点的指针。</p>

<p>返回一个 <strong>按顺序</strong> 包含链表中元素的整数数组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">head = [1,2,3,4,5], node = 5</span></p>

<p><span class="example-io"><b>输出：</b>[1,2,3,4,5]</span></p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>head = [4,5,6,7,8], node = 8</span></p>

<p><span class="example-io"><b>输出：</b>[4,5,6,7,8]</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>给定列表中的节点数在范围&nbsp;<code>[1, 500]</code>&nbsp;内。</li>
	<li><code>1 &lt;= Node.val &lt;= 1000</code></li>
	<li>所有节点的&nbsp;<code>Node.val</code>&nbsp;互不相同。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历链表

我们可以从给定的节点开始，往前遍历链表，直到遍历到头节点，然后再从头节点开始往后遍历链表，将遍历到的节点的值添加到答案数组中。

遍历结束后，返回答案数组即可。

时间复杂度 $O(n)$，其中 $n$ 为链表的节点个数。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, val, prev=None, next=None):
        self.val = val
        self.prev = prev
        self.next = next
"""


class Solution:
    def toArray(self, node: "Optional[Node]") -> List[int]:
        cur = node
        while cur and cur.prev:
            cur = cur.prev
        ans = []
        while cur:
            ans.append(cur.val)
            cur = cur.next
        return ans
```

#### Java

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
};
*/

class Solution {
    public int[] toArray(Node node) {
        var cur = node;
        while (cur != null && cur.prev != null) {
            cur = cur.prev;
        }
        var ans = new ArrayList<Integer>();
        while (cur != null) {
            ans.add(cur.val);
            cur = cur.next;
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }
}
```

#### C++

```cpp
/**
 * Definition for doubly-linked list.
 * class Node {
 *     int val;
 *     Node* prev;
 *     Node* next;
 *     Node() : val(0), next(nullptr), prev(nullptr) {}
 *     Node(int x) : val(x), next(nullptr), prev(nullptr) {}
 *     Node(int x, Node *prev, Node *next) : val(x), next(next), prev(prev) {}
 * };
 */
class Solution {
public:
    vector<int> toArray(Node* node) {
        Node* cur = node;
        while (cur && cur->prev) {
            cur = cur->prev;
        }
        vector<int> ans;
        while (cur) {
            ans.push_back(cur->val);
            cur = cur->next;
        }
        return ans;
    }
};
```

#### Go

```go
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Next *Node
 *     Prev *Node
 * }
 */

func toArray(node *Node) (ans []int) {
	cur := node
	for cur != nil && cur.Prev != nil {
		cur = cur.Prev
	}
	for cur != nil {
		ans = append(ans, cur.Val)
		cur = cur.Next
	}
	return
}
```

#### TypeScript

```ts
/**
 * Definition for _Node.
 * class _Node {
 *     val: number
 *     prev: _Node | null
 *     next: _Node | null
 *
 *     constructor(val?: number, prev? : _Node, next? : _Node) {
 *         this.val = (val===undefined ? 0 : val);
 *         this.prev = (prev===undefined ? null : prev);
 *         this.next = (next===undefined ? null : next);
 *     }
 * }
 */

function toArray(node: _Node | null): number[] {
    let cur = node;
    while (cur && cur.prev) {
        cur = cur.prev;
    }
    const ans: number[] = [];
    while (cur) {
        ans.push(cur.val);
        cur = cur.next;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
