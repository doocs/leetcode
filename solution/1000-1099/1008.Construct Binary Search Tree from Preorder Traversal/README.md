# [1008. 前序遍历构造二叉搜索树](https://leetcode.cn/problems/construct-binary-search-tree-from-preorder-traversal)

[English Version](/solution/1000-1099/1008.Construct%20Binary%20Search%20Tree%20from%20Preorder%20Traversal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

根据二叉搜索树的性质，DFS 构建即可。

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
    def bstFromPreorder(self, preorder: List[int]) -> Optional[TreeNode]:
        def dfs(preorder):
            if not preorder:
                return None
            root = TreeNode(preorder[0])
            left, right = 1, len(preorder)
            while left < right:
                mid = (left + right) >> 1
                if preorder[mid] > preorder[0]:
                    right = mid
                else:
                    left = mid + 1
            root.left = dfs(preorder[1:left])
            root.right = dfs(preorder[left:])
            return root

        return dfs(preorder)
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

    public TreeNode bstFromPreorder(int[] preorder) {
        return dfs(preorder, 0, preorder.length - 1);
    }

    private TreeNode dfs(int[] preorder, int i, int j) {
        if (i > j || i >= preorder.length) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[i]);
        int left = i + 1, right = j + 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (preorder[mid] > preorder[i]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        root.left = dfs(preorder, i + 1, left - 1);
        root.right = dfs(preorder, left, j);
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
    TreeNode* bstFromPreorder(vector<int>& preorder) {
        return dfs(preorder, 0, preorder.size() - 1);
    }

    TreeNode* dfs(vector<int>& preorder, int i, int j) {
        if (i > j || i >= preorder.size()) return nullptr;
        TreeNode* root = new TreeNode(preorder[i]);
        int left = i + 1, right = j + 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (preorder[mid] > preorder[i])
                right = mid;
            else
                left = mid + 1;
        }
        root->left = dfs(preorder, i + 1, left - 1);
        root->right = dfs(preorder, left, j);
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
func bstFromPreorder(preorder []int) *TreeNode {
	var dfs func(i, j int) *TreeNode
	dfs = func(i, j int) *TreeNode {
		if i > j || i >= len(preorder) {
			return nil
		}
		root := &TreeNode{Val: preorder[i]}
		left, right := i+1, len(preorder)
		for left < right {
			mid := (left + right) >> 1
			if preorder[mid] > preorder[i] {
				right = mid
			} else {
				left = mid + 1
			}
		}
		root.Left = dfs(i+1, left-1)
		root.Right = dfs(left, j)
		return root
	}
	return dfs(0, len(preorder)-1)
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

function bstFromPreorder(preorder: number[]): TreeNode | null {
    const n = preorder.length;
    const next = new Array(n);
    const stack = [];
    for (let i = n - 1; i >= 0; i--) {
        while (
            stack.length !== 0 &&
            preorder[stack[stack.length - 1]] < preorder[i]
        ) {
            stack.pop();
        }
        next[i] = stack[stack.length - 1] ?? n;
        stack.push(i);
    }

    const dfs = (left: number, right: number) => {
        if (left >= right) {
            return null;
        }
        return new TreeNode(
            preorder[left],
            dfs(left + 1, next[left]),
            dfs(next[left], right),
        );
    };
    return dfs(0, n);
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
    fn dfs(
        preorder: &Vec<i32>,
        next: &Vec<usize>,
        left: usize,
        right: usize,
    ) -> Option<Rc<RefCell<TreeNode>>> {
        if left >= right {
            return None;
        }
        Some(Rc::new(RefCell::new(TreeNode {
            val: preorder[left],
            left: Self::dfs(preorder, next, left + 1, next[left]),
            right: Self::dfs(preorder, next, next[left], right),
        })))
    }

    pub fn bst_from_preorder(preorder: Vec<i32>) -> Option<Rc<RefCell<TreeNode>>> {
        let n = preorder.len();
        let mut stack = Vec::new();
        let mut next = vec![n; n];
        for i in (0..n).rev() {
            while !stack.is_empty() && preorder[*stack.last().unwrap()] < preorder[i] {
                stack.pop();
            }
            if !stack.is_empty() {
                next[i] = *stack.last().unwrap();
            }
            stack.push(i);
        }
        Self::dfs(&preorder, &next, 0, n)
    }
}
```

### **...**

```

```

<!-- tabs:end -->
