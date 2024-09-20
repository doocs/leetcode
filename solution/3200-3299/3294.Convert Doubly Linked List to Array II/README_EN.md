---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3294.Convert%20Doubly%20Linked%20List%20to%20Array%20II/README_EN.md
---

<!-- problem:start -->

# [3294. Convert Doubly Linked List to Array II 🔒](https://leetcode.com/problems/convert-doubly-linked-list-to-array-ii)

[中文文档](/solution/3200-3299/3294.Convert%20Doubly%20Linked%20List%20to%20Array%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an <strong>arbitrary</strong> <code>node</code> from a <strong>doubly linked list</strong>, which contains nodes that have a next pointer and a previous pointer.</p>

<p>Return an integer array which contains the elements of the linked list <strong>in order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">head = [1,2,3,4,5], node = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,3,4,5]</span></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">head = [4,5,6,7,8], node = 8</span></p>

<p><strong>Output:</strong> <span class="example-io">[4,5,6,7,8]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the given list is in the range <code>[1, 500]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 1000</code></li>
	<li>All nodes have unique <code>Node.val</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Traverse the Linked List

We can start from the given node and traverse the linked list backward until we reach the head node. Then, we traverse the linked list forward from the head node, adding the values of the nodes we encounter to the answer array.

After the traversal is complete, return the answer array.

The time complexity is $O(n)$, where $n$ is the number of nodes in the linked list. Ignoring the space consumption of the answer array, the space complexity is $O(1)$.

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
        while node.prev:
            node = node.prev
        ans = []
        while node:
            ans.append(node.val)
            node = node.next
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
        while (node != null && node.prev != null) {
            node = node.prev;
        }
        var ans = new ArrayList<Integer>();
        for (; node != null; node = node.next) {
            ans.add(node.val);
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
        while (node && node->prev) {
            node = node->prev;
        }
        vector<int> ans;
        for (; node; node = node->next) {
            ans.push_back(node->val);
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
	for node != nil && node.Prev != nil {
		node = node.Prev
	}
	for ; node != nil; node = node.Next {
		ans = append(ans, node.Val)
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
    while (node && node.prev) {
        node = node.prev;
    }
    const ans: number[] = [];
    for (; node; node = node.next) {
        ans.push(node.val);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
