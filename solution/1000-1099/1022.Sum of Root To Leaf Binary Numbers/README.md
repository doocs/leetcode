# [1022. 从根到叶的二进制数之和](https://leetcode.cn/problems/sum-of-root-to-leaf-binary-numbers)

[English Version](/solution/1000-1099/1022.Sum%20of%20Root%20To%20Leaf%20Binary%20Numbers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出一棵二叉树，其上每个结点的值都是&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>&nbsp;。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。</p>

<ul>
	<li>例如，如果路径为&nbsp;<code>0 -&gt; 1 -&gt; 1 -&gt; 0 -&gt; 1</code>，那么它表示二进制数&nbsp;<code>01101</code>，也就是&nbsp;<code>13</code>&nbsp;。</li>
</ul>

<p>对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。</p>

<p>返回这些数字之和。题目数据保证答案是一个 <strong>32 位 </strong>整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1022.Sum%20of%20Root%20To%20Leaf%20Binary%20Numbers/images/sum-of-root-to-leaf-binary-numbers.png" />
<pre>
<strong>输入：</strong>root = [1,0,1,0,1,0,1]
<strong>输出：</strong>22
<strong>解释：</strong>(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = [0]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中的节点数在&nbsp;<code>[1, 1000]</code>&nbsp;范围内</li>
	<li><code>Node.val</code>&nbsp;仅为 <code>0</code> 或 <code>1</code>&nbsp;</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：递归**

我们设计递归函数 `dfs(root, t)`，它接收两个参数：当前节点 `root` 和当前节点的父节点对应的二进制数 `t`。函数的返回值是从当前节点到叶子节点的路径所表示的二进制数之和。答案即为 `dfs(root, 0)`。

递归函数的逻辑如下：

-   如果当前节点 `root` 为空，则返回 `0`，否则计算当前节点对应的二进制数 `t`，即 `t = t << 1 | root.val`。
-   如果当前节点是叶子节点，则返回 `t`，否则返回 `dfs(root.left, t)` 和 `dfs(root.right, t)` 的和。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点数。对每个节点访问一次；递归栈需要 $O(n)$ 的空间。

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
    def sumRootToLeaf(self, root: TreeNode) -> int:
        def dfs(root, t):
            if root is None:
                return 0
            t = (t << 1) | root.val
            if root.left is None and root.right is None:
                return t
            return dfs(root.left, t) + dfs(root.right, t)

        return dfs(root, 0)
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
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int t) {
        if (root == null) {
            return 0;
        }
        t = (t << 1) | root.val;
        if (root.left == null && root.right == null) {
            return t;
        }
        return dfs(root.left, t) + dfs(root.right, t);
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
    int sumRootToLeaf(TreeNode* root) {
        return dfs(root, 0);
    }

    int dfs(TreeNode* root, int t) {
        if (!root) return 0;
        t = (t << 1) | root->val;
        if (!root->left && !root->right) return t;
        return dfs(root->left, t) + dfs(root->right, t);
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
func sumRootToLeaf(root *TreeNode) int {
	var dfs func(root *TreeNode, t int) int
	dfs = func(root *TreeNode, t int) int {
		if root == nil {
			return 0
		}
		t = (t << 1) | root.Val
		if root.Left == nil && root.Right == nil {
			return t
		}
		return dfs(root.Left, t) + dfs(root.Right, t)
	}

	return dfs(root, 0)
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

function sumRootToLeaf(root: TreeNode | null): number {
    const dfs = (root: TreeNode | null, num: number) => {
        if (root == null) {
            return 0;
        }
        const { val, left, right } = root;
        num = (num << 1) | val;
        if (left == null && right == null) {
            return num;
        }
        return dfs(left, num) + dfs(right, num);
    };
    return dfs(root, 0);
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
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, mut num: i32) -> i32 {
        if root.is_none() {
            return 0;
        }
        let root = root.as_ref().unwrap().borrow();
        num = (num << 1) | root.val;
        if root.left.is_none() && root.right.is_none() {
            return num;
        }
        Self::dfs(&root.left, num) + Self::dfs(&root.right, num)
    }

    pub fn sum_root_to_leaf(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        Self::dfs(&root, 0)
    }
}
```

### **...**

```

```

<!-- tabs:end -->
