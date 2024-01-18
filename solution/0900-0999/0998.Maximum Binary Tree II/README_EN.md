# [998. Maximum Binary Tree II](https://leetcode.com/problems/maximum-binary-tree-ii)

[中文文档](/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/README.md)

## Description

<p>A <strong>maximum tree</strong> is a tree where every node has a value greater than any other value in its subtree.</p>

<p>You are given the <code>root</code> of a maximum binary tree and an integer <code>val</code>.</p>

<p>Just as in the <a href="https://leetcode.com/problems/maximum-binary-tree/" target="_blank">previous problem</a>, the given tree was constructed from a list <code>a</code> (<code>root = Construct(a)</code>) recursively with the following <code>Construct(a)</code> routine:</p>

<ul>
	<li>If <code>a</code> is empty, return <code>null</code>.</li>
	<li>Otherwise, let <code>a[i]</code> be the largest element of <code>a</code>. Create a <code>root</code> node with the value <code>a[i]</code>.</li>
	<li>The left child of <code>root</code> will be <code>Construct([a[0], a[1], ..., a[i - 1]])</code>.</li>
	<li>The right child of <code>root</code> will be <code>Construct([a[i + 1], a[i + 2], ..., a[a.length - 1]])</code>.</li>
	<li>Return <code>root</code>.</li>
</ul>

<p>Note that we were not given <code>a</code> directly, only a root node <code>root = Construct(a)</code>.</p>

<p>Suppose <code>b</code> is a copy of <code>a</code> with the value <code>val</code> appended to it. It is guaranteed that <code>b</code> has unique values.</p>

<p>Return <code>Construct(b)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/images/maxtree1.jpg" style="width: 376px; height: 235px;" />
<pre>
<strong>Input:</strong> root = [4,1,3,null,null,2], val = 5
<strong>Output:</strong> [5,4,null,1,3,null,null,2]
<strong>Explanation:</strong> a = [1,4,2,3], b = [1,4,2,3,5]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/images/maxtree21.jpg" style="width: 358px; height: 156px;" />
<pre>
<strong>Input:</strong> root = [5,2,4,null,1], val = 3
<strong>Output:</strong> [5,2,4,null,1,null,3]
<strong>Explanation:</strong> a = [2,1,5,4], b = [2,1,5,4,3]
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/images/maxtree3.jpg" style="width: 404px; height: 180px;" />
<pre>
<strong>Input:</strong> root = [5,2,3,null,1], val = 4
<strong>Output:</strong> [5,2,4,null,1,3]
<strong>Explanation:</strong> a = [2,1,5,3], b = [2,1,5,3,4]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 100]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 100</code></li>
	<li>All the values of the tree are <strong>unique</strong>.</li>
	<li><code>1 &lt;= val &lt;= 100</code></li>
</ul>

## Solutions

### Solution 1: Recursion

If $val$ is the maximum number, then make $val$ the new root node, and $root$ the left subtree of the new root node.

If $val$ is not the maximum number, since $val$ is the last appended number, it must be on the right side of $root$. Therefore, we can insert $val$ as a new node into the right subtree of $root$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes in the tree.

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def insertIntoMaxTree(
        self, root: Optional[TreeNode], val: int
    ) -> Optional[TreeNode]:
        if root is None or root.val < val:
            return TreeNode(val, root)
        root.right = self.insertIntoMaxTree(root.right, val)
        return root
```

```java
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
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null || root.val < val) {
            return new TreeNode(val, root, null);
        }
        root.right = insertIntoMaxTree(root.right, val);
        return root;
    }
}
```

```cpp
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
    TreeNode* insertIntoMaxTree(TreeNode* root, int val) {
        if (!root || root->val < val) return new TreeNode(val, root, nullptr);
        root->right = insertIntoMaxTree(root->right, val);
        return root;
    }
};
```

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func insertIntoMaxTree(root *TreeNode, val int) *TreeNode {
	if root == nil || root.Val < val {
		return &TreeNode{val, root, nil}
	}
	root.Right = insertIntoMaxTree(root.Right, val)
	return root
}
```

```ts
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

function insertIntoMaxTree(root: TreeNode | null, val: number): TreeNode | null {
    if (!root || root.val < val) {
        return new TreeNode(val, root);
    }
    root.right = insertIntoMaxTree(root.right, val);
    return root;
}
```

```rust
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
use std::rc::Rc;
use std::cell::RefCell;
impl Solution {
    pub fn insert_into_max_tree(
        mut root: Option<Rc<RefCell<TreeNode>>>,
        val: i32
    ) -> Option<Rc<RefCell<TreeNode>>> {
        if root.is_none() || root.as_ref().unwrap().as_ref().borrow().val < val {
            return Some(
                Rc::new(
                    RefCell::new(TreeNode {
                        val,
                        left: root.take(),
                        right: None,
                    })
                )
            );
        }
        {
            let mut root = root.as_ref().unwrap().as_ref().borrow_mut();
            root.right = Self::insert_into_max_tree(root.right.take(), val);
        }
        root
    }
}
```

```c
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */

struct TreeNode* insertIntoMaxTree(struct TreeNode* root, int val) {
    if (!root || root->val < val) {
        struct TreeNode* res = (struct TreeNode*) malloc(sizeof(struct TreeNode));
        res->val = val;
        res->left = root;
        res->right = NULL;
        return res;
    }
    root->right = insertIntoMaxTree(root->right, val);
    return root;
}
```

<!-- tabs:end -->

### Solution 2: Iteration

Search the right subtree, find the node where $curr.val \gt val \gt curr.right.val$, then create a new node $node$, point $node.left$ to $curr.right$, and then point $curr.right$ to $node$.

Finally, return $root$.

The time complexity is $O(n)$, where $n$ is the number of nodes in the tree. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def insertIntoMaxTree(
        self, root: Optional[TreeNode], val: int
    ) -> Optional[TreeNode]:
        if root.val < val:
            return TreeNode(val, root)
        curr = root
        node = TreeNode(val)
        while curr.right and curr.right.val > val:
            curr = curr.right
        node.left = curr.right
        curr.right = node
        return root
```

```java
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
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root.val < val) {
            return new TreeNode(val, root, null);
        }
        TreeNode curr = root;
        TreeNode node = new TreeNode(val);
        while (curr.right != null && curr.right.val > val) {
            curr = curr.right;
        }
        node.left = curr.right;
        curr.right = node;
        return root;
    }
}
```

```cpp
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
    TreeNode* insertIntoMaxTree(TreeNode* root, int val) {
        if (root->val < val) return new TreeNode(val, root, nullptr);
        TreeNode* curr = root;
        TreeNode* node = new TreeNode(val);
        while (curr->right && curr->right->val > val) curr = curr->right;
        node->left = curr->right;
        curr->right = node;
        return root;
    }
};
```

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func insertIntoMaxTree(root *TreeNode, val int) *TreeNode {
	if root.Val < val {
		return &TreeNode{val, root, nil}
	}
	node := &TreeNode{Val: val}
	curr := root
	for curr.Right != nil && curr.Right.Val > val {
		curr = curr.Right
	}
	node.Left = curr.Right
	curr.Right = node
	return root
}
```

```ts
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

function insertIntoMaxTree(root: TreeNode | null, val: number): TreeNode | null {
    if (root.val < val) {
        return new TreeNode(val, root);
    }
    const node = new TreeNode(val);
    let curr = root;
    while (curr.right && curr.right.val > val) {
        curr = curr.right;
    }
    node.left = curr.right;
    curr.right = node;
    return root;
}
```

<!-- tabs:end -->

<!-- end -->
