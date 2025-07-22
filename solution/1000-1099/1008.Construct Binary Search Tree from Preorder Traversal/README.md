---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1008.Construct%20Binary%20Search%20Tree%20from%20Preorder%20Traversal/README.md
rating: 1562
source: 第 127 场周赛 Q4
tags:
    - 栈
    - 树
    - 二叉搜索树
    - 数组
    - 二叉树
    - 单调栈
---

<!-- problem:start -->

# [1008. 前序遍历构造二叉搜索树](https://leetcode.cn/problems/construct-binary-search-tree-from-preorder-traversal)

[English Version](/solution/1000-1099/1008.Construct%20Binary%20Search%20Tree%20from%20Preorder%20Traversal/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组，它表示BST(即 <strong>二叉搜索树</strong> )的 <strong>先</strong><strong>序遍历</strong> ，构造树并返回其根。</p>

<p><strong>保证</strong> 对于给定的测试用例，总是有可能找到具有给定需求的二叉搜索树。</p>

<p><strong>二叉搜索树</strong> 是一棵二叉树，其中每个节点，&nbsp;<code>Node.left</code>&nbsp;的任何后代的值 <strong>严格小于</strong> <code>Node.val</code>&nbsp;,&nbsp;<code>Node.right</code>&nbsp;的任何后代的值 <strong>严格大于</strong> <code>Node.val</code>。</p>

<p>二叉树的 <strong>前序遍历</strong> 首先显示节点的值，然后遍历<code>Node.left</code>，最后遍历<code>Node.right</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1008.Construct%20Binary%20Search%20Tree%20from%20Preorder%20Traversal/images/1266.png" /></p>

<pre>
<strong>输入：</strong>preorder = [8,5,1,7,10,12]
<strong>输出：</strong>[8,5,10,1,7,null,12]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> preorder = [1,3]
<strong>输出:</strong> [1,null,3]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= preorder.length &lt;= 100</code></li>
	<li><code>1 &lt;= preorder[i]&nbsp;&lt;= 10^8</code></li>
	<li><code>preorder</code> 中的值 <strong>互不相同</strong></li>
</ul>

<p>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS + 二分查找

我们设计一个函数 $\textit{dfs}(i, j)$，表示构造出从 $\textit{preorder}[i]$ 到 $\textit{preorder}[j]$ 这些节点构成的二叉搜索树。那么答案就是 $\textit{dfs}(0, n - 1)$。

在 $\textit{dfs}(i, j)$ 中，我们首先构造根节点，即 $\textit{preorder}[i]$。然后使用二分查找的方法找到第一个大于 $\textit{preorder}[i]$ 的节点的下标 $\textit{mid}$，将 $\textit{dfs}(i + 1, \textit{mid} - 1)$ 作为根节点的左子树，将 $\textit{dfs}(\textit{mid}, j)$ 作为根节点的右子树。

最后返回根节点即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{preorder}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def bstFromPreorder(self, preorder: List[int]) -> Optional[TreeNode]:
        def dfs(i: int, j: int) -> Optional[TreeNode]:
            if i > j:
                return None
            root = TreeNode(preorder[i])
            l, r = i + 1, j + 1
            while l < r:
                mid = (l + r) >> 1
                if preorder[mid] > preorder[i]:
                    r = mid
                else:
                    l = mid + 1
            root.left = dfs(i + 1, l - 1)
            root.right = dfs(l, j)
            return root

        return dfs(0, len(preorder) - 1)
```

#### Java

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
    private int[] preorder;

    public TreeNode bstFromPreorder(int[] preorder) {
        this.preorder = preorder;
        return dfs(0, preorder.length - 1);
    }

    private TreeNode dfs(int i, int j) {
        if (i > j) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[i]);
        int l = i + 1, r = j + 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (preorder[mid] > preorder[i]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        root.left = dfs(i + 1, l - 1);
        root.right = dfs(l, j);
        return root;
    }
}
```

#### C++

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
    TreeNode* bstFromPreorder(vector<int>& preorder) {
        auto dfs = [&](this auto&& dfs, int i, int j) -> TreeNode* {
            if (i > j) {
                return nullptr;
            }
            TreeNode* root = new TreeNode(preorder[i]);
            int l = i + 1, r = j + 1;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (preorder[mid] > preorder[i]) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            root->left = dfs(i + 1, l - 1);
            root->right = dfs(l, j);
            return root;
        };
        return dfs(0, preorder.size() - 1);
    }
};
```

#### Go

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func bstFromPreorder(preorder []int) *TreeNode {
	var dfs func(i, j int) *TreeNode
	dfs = func(i, j int) *TreeNode {
		if i > j {
			return nil
		}
		root := &TreeNode{Val: preorder[i]}
		l, r := i+1, j+1
		for l < r {
			mid := (l + r) >> 1
			if preorder[mid] > preorder[i] {
				r = mid
			} else {
				l = mid + 1
			}
		}
		root.Left = dfs(i+1, l-1)
		root.Right = dfs(l, j)
		return root
	}
	return dfs(0, len(preorder)-1)
}
```

#### TypeScript

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

function bstFromPreorder(preorder: number[]): TreeNode | null {
    const dfs = (i: number, j: number): TreeNode | null => {
        if (i > j) {
            return null;
        }
        const root = new TreeNode(preorder[i]);
        let [l, r] = [i + 1, j + 1];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (preorder[mid] > preorder[i]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        root.left = dfs(i + 1, l - 1);
        root.right = dfs(l, j);
        return root;
    };
    return dfs(0, preorder.length - 1);
}
```

#### Rust

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
    pub fn bst_from_preorder(preorder: Vec<i32>) -> Option<Rc<RefCell<TreeNode>>> {
        fn dfs(preorder: &Vec<i32>, i: usize, j: usize) -> Option<Rc<RefCell<TreeNode>>> {
            if i > j {
                return None;
            }
            let root = Rc::new(RefCell::new(TreeNode::new(preorder[i])));
            let mut l = i + 1;
            let mut r = j + 1;
            while l < r {
                let mid = (l + r) >> 1;
                if preorder[mid] > preorder[i] {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            let mut root_ref = root.borrow_mut();
            root_ref.left = dfs(preorder, i + 1, l - 1);
            root_ref.right = dfs(preorder, l, j);
            Some(root.clone())
        }

        dfs(&preorder, 0, preorder.len() - 1)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
