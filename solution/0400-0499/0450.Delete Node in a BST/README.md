# [450. 删除二叉搜索树中的节点](https://leetcode.cn/problems/delete-node-in-a-bst)

[English Version](/solution/0400-0499/0450.Delete%20Node%20in%20a%20BST/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉搜索树的根节点 <strong>root </strong>和一个值 <strong>key</strong>，删除二叉搜索树中的&nbsp;<strong>key&nbsp;</strong>对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。</p>

<p>一般来说，删除节点可分为两个步骤：</p>

<ol>
	<li>首先找到需要删除的节点；</li>
	<li>如果找到了，删除它。</li>
</ol>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0450.Delete%20Node%20in%20a%20BST/images/del_node_1.jpg" style="width: 800px;" /></p>

<pre>
<strong>输入：</strong>root = [5,3,6,2,4,null,7], key = 3
<strong>输出：</strong>[5,4,6,2,null,null,7]
<strong>解释：</strong>给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
另一个正确答案是 [5,2,6,null,4,null,7]。

<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0450.Delete%20Node%20in%20a%20BST/images/del_node_supp.jpg" style="width: 350px;" />
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> root = [5,3,6,2,4,null,7], key = 0
<strong>输出:</strong> [5,3,6,2,4,null,7]
<strong>解释:</strong> 二叉树不包含值为 0 的节点
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> root = [], key = 0
<strong>输出:</strong> []</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li>节点数的范围&nbsp;<code>[0, 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>5</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>5</sup></code></li>
	<li>节点值唯一</li>
	<li><code>root</code>&nbsp;是合法的二叉搜索树</li>
	<li><code>-10<sup>5</sup>&nbsp;&lt;= key &lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong> 要求算法时间复杂度为&nbsp;O(h)，h 为树的高度。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：递归**

二叉搜索树有以下性质：

1. 若任意节点的左子树不空，则左子树上所有节点的值均小于它的根节点的值；
1. 若任意节点的右子树不空，则右子树上所有节点的值均大于它的根节点的值；
1. 任意节点的左、右子树也分别为二叉搜索树。

我们可以递归判断当前节点 $root$ 与 $key$ 的大小关系：

1. 若 $root.val>key$，则递归左子树；
1. 若 $root.val<key$，则递归右子树；
1. 若 $root.val=key$，则进一步判断：
    1. 若 $root$ 没有左子树，则 $root.right$ 顶替 $root$ 的位置；
    1. 若 $root$ 没有右子树，则 $root.left$ 顶替 $root$ 的位置；
    1. 若 $root$ 同时存在左右子树，则将左子树转移至右子树的最左节点的左子树上，然后 $root.right$ 顶替 $root$ 的位置。

时间复杂度 $O(H)$，其中 $H$ 是树的高度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def deleteNode(self, root: Optional[TreeNode], key: int) -> Optional[TreeNode]:
        if root is None:
            return None
        if root.val > key:
            root.left = self.deleteNode(root.left, key)
            return root
        if root.val < key:
            root.right = self.deleteNode(root.right, key)
            return root
        if root.left is None:
            return root.right
        if root.right is None:
            return root.left
        node = root.right
        while node.left:
            node = node.left
        node.left = root.left
        root = root.right
        return root
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
            return root;
        }
        if (root.left == null) {
            return root.right;
        }
        if (root.right == null) {
            return root.left;
        }
        TreeNode node = root.right;
        while (node.left != null) {
            node = node.left;
        }
        node.left = root.left;
        root = root.right;
        return root;
    }
}
```

### **C++**

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
    TreeNode* deleteNode(TreeNode* root, int key) {
        if (!root) return root;
        if (root->val > key) {
            root->left = deleteNode(root->left, key);
            return root;
        }
        if (root->val < key) {
            root->right = deleteNode(root->right, key);
            return root;
        }
        if (!root->left) return root->right;
        if (!root->right) return root->left;
        TreeNode* node = root->right;
        while (node->left) node = node->left;
        node->left = root->left;
        root = root->right;
        return root;
    }
};
```

### **Go**

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func deleteNode(root *TreeNode, key int) *TreeNode {
	if root == nil {
		return nil
	}
	if root.Val > key {
		root.Left = deleteNode(root.Left, key)
		return root
	}
	if root.Val < key {
		root.Right = deleteNode(root.Right, key)
		return root
	}
	if root.Left == nil {
		return root.Right
	}
	if root.Right == nil {
		return root.Left
	}
	node := root.Right
	for node.Left != nil {
		node = node.Left
	}
	node.Left = root.Left
	root = root.Right
	return root
}
```

### **TypeScript**

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

function deleteNode(root: TreeNode | null, key: number): TreeNode | null {
    if (root == null) {
        return root;
    }
    const { val, left, right } = root;
    if (val > key) {
        root.left = deleteNode(left, key);
    } else if (val < key) {
        root.right = deleteNode(right, key);
    } else {
        if (left == null && right == null) {
            root = null;
        } else if (left == null || right == null) {
            root = left || right;
        } else {
            if (right.left == null) {
                right.left = left;
                root = right;
            } else {
                let minPreNode = right;
                while (minPreNode.left.left != null) {
                    minPreNode = minPreNode.left;
                }
                const minVal = minPreNode.left.val;
                root.val = minVal;
                minPreNode.left = deleteNode(minPreNode.left, minVal);
            }
        }
    }
    return root;
}
```

### **Rust**

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
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>) -> i32 {
        let node = root.as_ref().unwrap().borrow();
        if node.left.is_none() {
            return node.val;
        }
        Self::dfs(&node.left)
    }

    pub fn delete_node(
        mut root: Option<Rc<RefCell<TreeNode>>>,
        key: i32,
    ) -> Option<Rc<RefCell<TreeNode>>> {
        if root.is_some() {
            let mut node = root.as_mut().unwrap().borrow_mut();
            match node.val.cmp(&key) {
                std::cmp::Ordering::Less => {
                    node.right = Self::delete_node(node.right.take(), key);
                }
                std::cmp::Ordering::Greater => {
                    node.left = Self::delete_node(node.left.take(), key);
                }
                std::cmp::Ordering::Equal => {
                    match (node.left.is_some(), node.right.is_some()) {
                        (false, false) => return None,
                        (true, false) => return node.left.take(),
                        (false, true) => return node.right.take(),
                        (true, true) => {
                            if node.right.as_ref().unwrap().borrow().left.is_none() {
                                let mut r = node.right.take();
                                r.as_mut().unwrap().borrow_mut().left = node.left.take();
                                return r;
                            } else {
                                let val = Self::dfs(&node.right);
                                node.val = val;
                                node.right = Self::delete_node(node.right.take(), val);
                            }
                        }
                    };
                }
            }
        }
        root
    }
}
```

### **...**

```

```

<!-- tabs:end -->
