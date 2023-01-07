# [1038. 从二叉搜索树到更大和树](https://leetcode.cn/problems/binary-search-tree-to-greater-sum-tree)

[English Version](/solution/1000-1099/1038.Binary%20Search%20Tree%20to%20Greater%20Sum%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><span style="font-size:10.5pt"><span style="font-family:Calibri"><span style="font-size:10.5000pt"><span style="font-family:宋体"><font face="宋体">给定一个二叉搜索树</font></span></span></span></span>&nbsp;<code>root</code>&nbsp;(BST)<span style="font-size:10.5pt"><span style="font-family:Calibri"><span style="font-size:10.5000pt"><span style="font-family:宋体"><font face="宋体">，请将它的每个</font></span></span></span></span>节点<span style="font-size:10.5pt"><span style="font-family:Calibri"><span style="font-size:10.5000pt"><span style="font-family:宋体"><font face="宋体">的值替换成树中大于或者等于该</font></span></span></span></span>节点<span style="font-size:10.5pt"><span style="font-family:Calibri"><span style="font-size:10.5000pt"><span style="font-family:宋体"><font face="宋体">值的所有</font></span></span></span></span>节点<span style="font-size:10.5pt"><span style="font-family:Calibri"><span style="font-size:10.5000pt"><span style="font-family:宋体"><font face="宋体">值之和。</font></span></span></span></span></p>

<p>提醒一下， <em>二叉搜索树</em> 满足下列约束条件：</p>

<ul>
	<li>节点的左子树仅包含键<strong> 小于 </strong>节点键的节点。</li>
	<li>节点的右子树仅包含键<strong> 大于</strong> 节点键的节点。</li>
	<li>左右子树也必须是二叉搜索树。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1038.Binary%20Search%20Tree%20to%20Greater%20Sum%20Tree/images/tree.png" style="height:273px; width:400px" /></strong></p>

<pre>
<strong>输入：</strong>[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
<strong>输出：</strong>[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = [0,null,1]
<strong>输出：</strong>[1,null,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中的节点数在&nbsp;<code>[1, 100]</code>&nbsp;范围内。</li>
	<li><code>0 &lt;= Node.val &lt;= 100</code></li>
	<li>树中的所有值均 <strong>不重复</strong>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>注意：</strong>该题目与 538:&nbsp;<a href="https://leetcode.cn/problems/convert-bst-to-greater-tree/">https://leetcode.cn/problems/convert-bst-to-greater-tree/&nbsp; </a>相同</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**前言**

二叉搜索树的中序遍历（左根右）结果是一个单调递增的有序序列，我们反序进行中序遍历（右根左），即可以得到一个单调递减的有序序列。通过累加单调递减的有序序列，我们可以得到大于等于 `node.val` 的新值，并重新赋值给 `node`。

关于反序中序遍历，有三种方法，一是递归遍历，二是栈实现非递归遍历，三是 Morris 遍历。

**方法一：递归**

按照“右根左”的顺序，递归遍历二叉搜索树，累加遍历到的所有节点值到 $s$ 中，然后每次赋值给对应的 `node` 节点。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉搜索树的节点数。

**方法二：Morris 遍历**

Morris 遍历无需使用栈，时间复杂度 $O(n)$，空间复杂度为 $O(1)$。核心思想是：

定义 s 表示二叉搜索树节点值累加和。遍历二叉树节点：

1. 若当前节点 root 的右子树为空，**将当前节点值添加至 s** 中，更新当前节点值为 s，并将当前节点更新为 `root.left`。
2. 若当前节点 root 的右子树不为空，找到右子树的最左节点 next（也即是 root 节点在中序遍历下的后继节点）：
    - 若后继节点 next 的左子树为空，将后继节点的左子树指向当前节点 root，并将当前节点更新为 `root.right`。
    - 若后继节点 next 的左子树不为空，**将当前节点值添加 s** 中，更新当前节点值为 s，然后将后继节点左子树指向空（即解除 next 与 root 的指向关系），并将当前节点更新为 `root.left`。
3. 循环以上步骤，直至二叉树节点为空，遍历结束。
4. 最后返回二叉搜索树根节点即可。

> Morris 反序中序遍历跟 Morris 中序遍历思路一致，只是将中序遍历的“左根右”变为“右根左”。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

递归遍历：

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def bstToGst(self, root: TreeNode) -> TreeNode:
        def dfs(root):
            nonlocal s
            if root is None:
                return
            dfs(root.right)
            s += root.val
            root.val = s
            dfs(root.left)

        s = 0
        dfs(root)
        return root
```

Morris 遍历：

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def bstToGst(self, root: TreeNode) -> TreeNode:
        s = 0
        node = root
        while root:
            if root.right is None:
                s += root.val
                root.val = s
                root = root.left
            else:
                next = root.right
                while next.left and next.left != root:
                    next = next.left
                if next.left is None:
                    next.left = root
                    root = root.right
                else:
                    s += root.val
                    root.val = s
                    next.left = None
                    root = root.left
        return node
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

递归遍历：

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
    private int s;

    public TreeNode bstToGst(TreeNode root) {
        dfs(root);
        return root;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.right);
        s += root.val;
        root.val = s;
        dfs(root.left);
    }
}
```

Morris 遍历：

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
    public TreeNode bstToGst(TreeNode root) {
        int s = 0;
        TreeNode node = root;
        while (root != null) {
            if (root.right == null) {
                s += root.val;
                root.val = s;
                root = root.left;
            } else {
                TreeNode next = root.right;
                while (next.left != null && next.left != root) {
                    next = next.left;
                }
                if (next.left == null) {
                    next.left = root;
                    root = root.right;
                } else {
                    s += root.val;
                    root.val = s;
                    next.left = null;
                    root = root.left;
                }
            }
        }
        return node;
    }
}
```

### **C++**

递归遍历：

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
    int s = 0;

    TreeNode* bstToGst(TreeNode* root) {
        dfs(root);
        return root;
    }

    void dfs(TreeNode* root) {
        if (!root) return;
        dfs(root->right);
        s += root->val;
        root->val = s;
        dfs(root->left);
    }
};
```

Morris 遍历：

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
    TreeNode* bstToGst(TreeNode* root) {
        int s = 0;
        TreeNode* node = root;
        while (root)
        {
            if (root->right == nullptr) {
                s += root->val;
                root->val = s;
                root = root->left;
            }
            else
            {
                TreeNode* next = root->right;
                while (next->left && next->left != root)
                {
                    next = next->left;
                }
                if (next->left == nullptr)
                {
                    next->left = root;
                    root = root->right;
                }
                else
                {
                    s += root->val;
                    root->val = s;
                    next->left = nullptr;
                    root = root->left;
                }
            }
        }
        return node;
    }
};
```

### **Go**

递归遍历：

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func bstToGst(root *TreeNode) *TreeNode {
	s := 0
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Right)
		s += root.Val
		root.Val = s
		dfs(root.Left)
	}
	dfs(root)
	return root
}
```

Morris 遍历：

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func bstToGst(root *TreeNode) *TreeNode {
	s := 0
	node := root
	for root != nil {
		if root.Right == nil {
			s += root.Val
			root.Val = s
			root = root.Left
		} else {
			next := root.Right
			for next.Left != nil && next.Left != root {
				next = next.Left
			}
			if next.Left == nil {
				next.Left = root
				root = root.Right
			} else {
				s += root.Val
				root.Val = s
				next.Left = nil
				root = root.Left
			}
		}
	}
	return node
}
```

### **JavaScript**

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {TreeNode}
 */
var bstToGst = function (root) {
    let s = 0;
    function dfs(root) {
        if (!root) {
            return;
        }
        dfs(root.right);
        s += root.val;
        root.val = s;
        dfs(root.left);
    }
    dfs(root);
    return root;
};
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

function bstToGst(root: TreeNode | null): TreeNode | null {
    const dfs = (root: TreeNode | null, sum: number) => {
        if (root == null) {
            return sum;
        }
        const { val, left, right } = root;
        sum = dfs(right, sum) + val;
        root.val = sum;
        sum = dfs(left, sum);
        return sum;
    };
    dfs(root, 0);
    return root;
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

function bstToGst(root: TreeNode | null): TreeNode | null {
    let cur = root;
    let sum = 0;
    while (cur != null) {
        const { val, left, right } = cur;
        if (right == null) {
            sum += val;
            cur.val = sum;
            cur = left;
        } else {
            let next = right;
            while (next.left != null && next.left != cur) {
                next = next.left;
            }
            if (next.left == null) {
                next.left = cur;
                cur = right;
            } else {
                next.left = null;
                sum += val;
                cur.val = sum;
                cur = left;
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
    fn dfs(root: &mut Option<Rc<RefCell<TreeNode>>>, mut sum: i32) -> i32 {
        if let Some(node) = root {
            let mut node = node.as_ref().borrow_mut();
            sum = Self::dfs(&mut node.right, sum) + node.val;
            node.val = sum;
            sum = Self::dfs(&mut node.left, sum);
        }
        sum
    }

    pub fn bst_to_gst(mut root: Option<Rc<RefCell<TreeNode>>>) -> Option<Rc<RefCell<TreeNode>>> {
        Self::dfs(&mut root, 0);
        root
    }
}
```

### **C**

```c
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */


int dfs(struct TreeNode *root, int sum) {
    if (root) {
        sum = dfs(root->right, sum) + root->val;
        root->val = sum;
        sum = dfs(root->left, sum);
    }
    return sum;
}

struct TreeNode *bstToGst(struct TreeNode *root) {
    dfs(root, 0);
    return root;
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


struct TreeNode *bstToGst(struct TreeNode *root) {
    struct TreeNode *cur = root;
    int sum = 0;
    while (cur) {
        if (!cur->right) {
            sum += cur->val;
            cur->val = sum;
            cur = cur->left;
        } else {
            struct TreeNode *next = cur->right;
            while (next->left && next->left != cur) {
                next = next->left;
            }
            if (!next->left) {
                next->left = cur;
                cur = cur->right;
            } else {
                next->left = NULL;
                sum += cur->val;
                cur->val = sum;
                cur = cur->left;
            }
        }
    }
    return root;
}
```

### **...**

```

```

<!-- tabs:end -->
