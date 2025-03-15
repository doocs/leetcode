---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0109.Convert%20Sorted%20List%20to%20Binary%20Search%20Tree/README_EN.md
tags:
    - Tree
    - Binary Search Tree
    - Linked List
    - Divide and Conquer
    - Binary Tree
---

<!-- problem:start -->

# [109. Convert Sorted List to Binary Search Tree](https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree)

[中文文档](/solution/0100-0199/0109.Convert%20Sorted%20List%20to%20Binary%20Search%20Tree/README.md)

## Description

<!-- description:start -->

<p>Given the <code>head</code> of a singly linked list where elements are sorted in <strong>ascending order</strong>, convert <em>it to a </em><span data-keyword="height-balanced"><strong><em>height-balanced</em></strong></span> <em>binary search tree</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0109.Convert%20Sorted%20List%20to%20Binary%20Search%20Tree/images/linked.jpg" style="width: 500px; height: 388px;" />
<pre>
<strong>Input:</strong> head = [-10,-3,0,5,9]
<strong>Output:</strong> [0,-3,9,-10,null,5]
<strong>Explanation:</strong> One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> head = []
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in <code>head</code> is in the range <code>[0, 2 * 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

We first convert the linked list to an array $\textit{nums}$, and then use depth-first search to construct the binary search tree.

We define a function $\textit{dfs}(i, j)$, where $i$ and $j$ represent the current interval $[i, j]$. Each time, we choose the number at the middle position $\textit{mid}$ of the interval as the root node, recursively construct the left subtree for the interval $[i, \textit{mid} - 1]$, and the right subtree for the interval $[\textit{mid} + 1, j]$. Finally, we return the node corresponding to $\textit{mid}$ as the root node of the current subtree.

In the main function, we just need to call $\textit{dfs}(0, n - 1)$ and return the result.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the linked list.

<!-- tabs:start -->

#### Python3

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def sortedListToBST(self, head: Optional[ListNode]) -> Optional[TreeNode]:
        def dfs(i: int, j: int) -> Optional[TreeNode]:
            if i > j:
                return None
            mid = (i + j) >> 1
            l, r = dfs(i, mid - 1), dfs(mid + 1, j)
            return TreeNode(nums[mid], l, r)

        nums = []
        while head:
            nums.append(head.val)
            head = head.next
        return dfs(0, len(nums) - 1)
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
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private List<Integer> nums = new ArrayList<>();

    public TreeNode sortedListToBST(ListNode head) {
        for (; head != null; head = head.next) {
            nums.add(head.val);
        }
        return dfs(0, nums.size() - 1);
    }

    private TreeNode dfs(int i, int j) {
        if (i > j) {
            return null;
        }
        int mid = (i + j) >> 1;
        TreeNode left = dfs(i, mid - 1);
        TreeNode right = dfs(mid + 1, j);
        return new TreeNode(nums.get(mid), left, right);
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
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    TreeNode* sortedListToBST(ListNode* head) {
        vector<int> nums;
        for (; head; head = head->next) {
            nums.push_back(head->val);
        }
        auto dfs = [&](this auto&& dfs, int i, int j) -> TreeNode* {
            if (i > j) {
                return nullptr;
            }
            int mid = (i + j) >> 1;
            TreeNode* left = dfs(i, mid - 1);
            TreeNode* right = dfs(mid + 1, j);
            return new TreeNode(nums[mid], left, right);
        };
        return dfs(0, nums.size() - 1);
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
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func sortedListToBST(head *ListNode) *TreeNode {
	nums := []int{}
	for ; head != nil; head = head.Next {
		nums = append(nums, head.Val)
	}
	var dfs func(i, j int) *TreeNode
	dfs = func(i, j int) *TreeNode {
		if i > j {
			return nil
		}
		mid := (i + j) >> 1
		left := dfs(i, mid-1)
		right := dfs(mid+1, j)
		return &TreeNode{nums[mid], left, right}
	}
	return dfs(0, len(nums)-1)
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

/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function sortedListToBST(head: ListNode | null): TreeNode | null {
    const nums: number[] = [];
    for (; head; head = head.next) {
        nums.push(head.val);
    }
    const dfs = (i: number, j: number): TreeNode | null => {
        if (i > j) {
            return null;
        }
        const mid = (i + j) >> 1;
        const left = dfs(i, mid - 1);
        const right = dfs(mid + 1, j);
        return new TreeNode(nums[mid], left, right);
    };
    return dfs(0, nums.length - 1);
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
// Definition for a binary tree node.
// #[derive(Debug, PartialEq, Eq)]
// pub struct TreeNode {
//   pub val: i32,
//   pub left: Option<Rc<RefCell<TreeNode>>>,
//   pub right: Option<Rc<RefCell<TreeNode>>>,
// }
//
// impl TreeNode {
//   #[inline]
//   pub fn new(val: i32) -> Self {
//     TreeNode {
//       val,
//       left: None,
//       right: None
//     }
//   }
// }
use std::cell::RefCell;
use std::rc::Rc;

impl Solution {
    pub fn sorted_list_to_bst(head: Option<Box<ListNode>>) -> Option<Rc<RefCell<TreeNode>>> {
        let mut nums = Vec::new();
        let mut current = head;
        while let Some(node) = current {
            nums.push(node.val);
            current = node.next;
        }

        fn dfs(nums: &[i32]) -> Option<Rc<RefCell<TreeNode>>> {
            if nums.is_empty() {
                return None;
            }
            let mid = nums.len() / 2;
            Some(Rc::new(RefCell::new(TreeNode {
                val: nums[mid],
                left: dfs(&nums[..mid]),
                right: dfs(&nums[mid + 1..]),
            })))
        }

        dfs(&nums)
    }
}
```

#### JavaScript

```js
/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {ListNode} head
 * @return {TreeNode}
 */
var sortedListToBST = function (head) {
    const nums = [];
    for (; head; head = head.next) {
        nums.push(head.val);
    }
    const dfs = (i, j) => {
        if (i > j) {
            return null;
        }
        const mid = (i + j) >> 1;
        const left = dfs(i, mid - 1);
        const right = dfs(mid + 1, j);
        return new TreeNode(nums[mid], left, right);
    };
    return dfs(0, nums.length - 1);
};
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
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */
struct TreeNode* dfs(int* nums, int i, int j) {
    if (i > j) {
        return NULL;
    }
    int mid = (i + j) >> 1;
    struct TreeNode* left = dfs(nums, i, mid - 1);
    struct TreeNode* right = dfs(nums, mid + 1, j);
    struct TreeNode* root = (struct TreeNode*) malloc(sizeof(struct TreeNode));
    root->val = nums[mid];
    root->left = left;
    root->right = right;
    return root;
}

struct TreeNode* sortedListToBST(struct ListNode* head) {
    int size = 0;
    struct ListNode* temp = head;
    while (temp) {
        size++;
        temp = temp->next;
    }

    int* nums = (int*) malloc(size * sizeof(int));
    temp = head;
    for (int i = 0; i < size; i++) {
        nums[i] = temp->val;
        temp = temp->next;
    }

    struct TreeNode* root = dfs(nums, 0, size - 1);
    free(nums);
    return root;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
