# [606. Construct String from Binary Tree](https://leetcode.com/problems/construct-string-from-binary-tree)

[中文文档](/solution/0600-0699/0606.Construct%20String%20from%20Binary%20Tree/README.md)

<!-- tags:Tree,Depth-First Search,String,Binary Tree -->

<!-- difficulty:Medium -->

## Description

<p>Given the <code>root</code> node of a binary tree, your task is to create a string representation of the tree following a specific set of formatting rules. The representation should be based on a preorder traversal of the binary tree and must adhere to the following guidelines:</p>

<ul>
	<li>
	<p><strong>Node Representation</strong>: Each node in the tree should be represented by its integer value.</p>
	</li>
	<li>
	<p><strong>Parentheses for Children</strong>: If a node has at least one child (either left or right), its children should be represented inside parentheses. Specifically:</p>

    <ul>
    	<li>If a node has a left child, the value of the left child should be enclosed in parentheses immediately following the node&#39;s value.</li>
    	<li>If a node has a right child, the value of the right child should also be enclosed in parentheses. The parentheses for the right child should follow those of the left child.</li>
    </ul>
    </li>
    <li>
    <p><strong>Omitting Empty Parentheses</strong>: Any empty parentheses pairs (i.e., <code>()</code>) should be omitted from the final string representation of the tree, with one specific exception: when a node has a right child but no left child. In such cases, you must include an empty pair of parentheses to indicate the absence of the left child. This ensures that the one-to-one mapping between the string representation and the original binary tree structure is maintained.</p>

    <p>In summary, empty parentheses pairs should be omitted when a node has only a left child or no children. However, when a node has a right child but no left child, an empty pair of parentheses must precede the representation of the right child to reflect the tree&#39;s structure accurately.</p>
    </li>

</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0606.Construct%20String%20from%20Binary%20Tree/images/cons1-tree.jpg" style="padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>Input:</strong> root = [1,2,3,4]
<strong>Output:</strong> &quot;1(2(4))(3)&quot;
<strong>Explanation:</strong> Originally, it needs to be &quot;1(2(4)())(3()())&quot;, but you need to omit all the empty parenthesis pairs. And it will be &quot;1(2(4))(3)&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0606.Construct%20String%20from%20Binary%20Tree/images/cons2-tree.jpg" style="padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>Input:</strong> root = [1,2,3,null,4]
<strong>Output:</strong> &quot;1(2()(4))(3)&quot;
<strong>Explanation:</strong> Almost the same as the first example, except the <code>()</code> after <code>2</code> is necessary to indicate the absence of a left child for <code>2</code> and the presence of a right child.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def tree2str(self, root: Optional[TreeNode]) -> str:
        def dfs(root):
            if root is None:
                return ''
            if root.left is None and root.right is None:
                return str(root.val)
            if root.right is None:
                return f'{root.val}({dfs(root.left)})'
            return f'{root.val}({dfs(root.left)})({dfs(root.right)})'

        return dfs(root)
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
    public String tree2str(TreeNode root) {
        if (root == null) {
            return "";
        }
        if (root.left == null && root.right == null) {
            return root.val + "";
        }
        if (root.right == null) {
            return root.val + "(" + tree2str(root.left) + ")";
        }
        return root.val + "(" + tree2str(root.left) + ")(" + tree2str(root.right) + ")";
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
    string tree2str(TreeNode* root) {
        if (!root) return "";
        if (!root->left && !root->right) return to_string(root->val);
        if (!root->right) return to_string(root->val) + "(" + tree2str(root->left) + ")";
        return to_string(root->val) + "(" + tree2str(root->left) + ")(" + tree2str(root->right) + ")";
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
func tree2str(root *TreeNode) string {
	if root == nil {
		return ""
	}
	if root.Left == nil && root.Right == nil {
		return strconv.Itoa(root.Val)
	}
	if root.Right == nil {
		return strconv.Itoa(root.Val) + "(" + tree2str(root.Left) + ")"
	}
	return strconv.Itoa(root.Val) + "(" + tree2str(root.Left) + ")(" + tree2str(root.Right) + ")"
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

function tree2str(root: TreeNode | null): string {
    if (root == null) {
        return '';
    }
    if (root.left == null && root.right == null) {
        return `${root.val}`;
    }
    return `${root.val}(${root.left ? tree2str(root.left) : ''})${
        root.right ? `(${tree2str(root.right)})` : ''
    }`;
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
use std::cell::RefCell;
use std::rc::Rc;
impl Solution {
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, res: &mut String) {
        if let Some(node) = root {
            let node = node.borrow();
            res.push_str(node.val.to_string().as_str());

            if node.left.is_none() && node.right.is_none() {
                return;
            }
            res.push('(');
            if node.left.is_some() {
                Self::dfs(&node.left, res);
            }
            res.push(')');
            if node.right.is_some() {
                res.push('(');
                Self::dfs(&node.right, res);
                res.push(')');
            }
        }
    }

    pub fn tree2str(root: Option<Rc<RefCell<TreeNode>>>) -> String {
        let mut res = String::new();
        Self::dfs(&root, &mut res);
        res
    }
}
```

<!-- tabs:end -->

<!-- end -->
