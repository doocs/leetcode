---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3263.Convert%20Doubly%20Linked%20List%20to%20Array%20I/README.md
---

<!-- problem:start -->

# [3263. Convert Doubly Linked List to Array I 🔒](https://leetcode.cn/problems/convert-doubly-linked-list-to-array-i)

[English Version](/solution/3200-3299/3263.Convert%20Doubly%20Linked%20List%20to%20Array%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>You are given the <code>head</code> of a <strong>doubly linked list</strong>, which contains nodes that have a next pointer and a previous pointer.</p>

<p>Return an integer array which contains the elements of the linked list <strong>in order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">head = [1,2,3,4,3,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,3,4,3,2,1]</span></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">head = [2,2,2,2,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,2,2,2,2]</span></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">head = [3,2,3,2,3,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,2,3,2,3,2]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the given list is in the range <code>[1, 50]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 50</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：直接遍历

我们可以直接遍历链表，将节点的值依次添加到答案数组 $\textit{ans}$ 中。

遍历结束后，返回答案数组 $\textit{ans}$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为链表的长度。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

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
    def toArray(self, root: "Optional[Node]") -> List[int]:
        ans = []
        while root:
            ans.append(root.val)
            root = root.next
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
    public int[] toArray(Node head) {
        List<Integer> ans = new ArrayList<>();
        for (; head != null; head = head.next) {
            ans.add(head.val);
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
    vector<int> toArray(Node* head) {
        vector<int> ans;
        for (; head; head = head->next) {
            ans.push_back(head->val);
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

func toArray(head *Node) (ans []int) {
	for ; head != nil; head = head.Next {
		ans = append(ans, head.Val)
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

function toArray(head: _Node | null): number[] {
    const ans: number[] = [];
    for (; head; head = head.next) {
        ans.push(head.val);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
