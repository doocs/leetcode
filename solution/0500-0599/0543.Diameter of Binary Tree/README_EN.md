# [543. Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree)

[中文文档](/solution/0500-0599/0543.Diameter%20of%20Binary%20Tree/README.md)

## Description

<p>Given the <code>root</code> of a binary tree, return <em>the length of the <strong>diameter</strong> of the tree</em>.</p>

<p>The <strong>diameter</strong> of a binary tree is the <strong>length</strong> of the longest path between any two nodes in a tree. This path may or may not pass through the <code>root</code>.</p>

<p>The <strong>length</strong> of a path between two nodes is represented by the number of edges between them.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0543.Diameter%20of%20Binary%20Tree/images/diamtree.jpg" style="width: 292px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,4,5]
<strong>Output:</strong> 3
<strong>Explanation:</strong> 3 is the length of the path [4,2,1,3] or [5,2,1,3].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [1,2]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
</ul>

## Solutions

Similar to problem [687. Longest Univalue Path](/solution/0600-0699/0687.Longest%20Univalue%20Path/README_EN.md).

<!-- tabs:start -->

### **Python3**

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def diameterOfBinaryTree(self, root: TreeNode) -> int:
        def dfs(root):
            if root is None:
                return 0
            nonlocal ans
            left, right = dfs(root.left), dfs(root.right)
            ans = max(ans, left + right)
            return 1 + max(left, right)

        ans = 0
        dfs(root)
        return ans
```

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def diameterOfBinaryTree(self, root: TreeNode) -> int:
        def build(root):
            if root is None:
                return
            nonlocal d
            if root.left:
                d[root].add(root.left)
                d[root.left].add(root)
            if root.right:
                d[root].add(root.right)
                d[root.right].add(root)
            build(root.left)
            build(root.right)

        def dfs(u, t):
            nonlocal ans, vis, d, next
            if u in vis:
                return
            vis.add(u)
            if t > ans:
                ans = t
                next = u
            for v in d[u]:
                dfs(v, t + 1)

        d = defaultdict(set)
        ans = 0
        next = root
        build(root)
        vis = set()
        dfs(next, 0)
        vis.clear()
        dfs(next, 0)
        return ans
```

### **Java**

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
    private int ans;

    public int diameterOfBinaryTree(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        ans = Math.max(ans, left + right);
        return 1 + Math.max(left, right);
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
    int ans;

    int diameterOfBinaryTree(TreeNode* root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    int dfs(TreeNode* root) {
        if (!root) return 0;
        int left = dfs(root->left);
        int right = dfs(root->right);
        ans = max(ans, left + right);
        return 1 + max(left, right);
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
func diameterOfBinaryTree(root *TreeNode) int {
	ans := 0
	var dfs func(root *TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		left, right := dfs(root.Left), dfs(root.Right)
		ans = max(ans, left+right)
		return 1 + max(left, right)
	}
	dfs(root)
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
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

#define max(a,b) (((a) > (b)) ? (a) : (b))

int dfs(struct TreeNode *root, int *res) {
    if (!root) {
        return 0;
    }
    int left = dfs(root->left, res);
    int right = dfs(root->right, res);
    *res = max(*res, left + right);
    return max(left, right) + 1;
}

int diameterOfBinaryTree(struct TreeNode *root) {
    int res = 0;
    dfs(root, &res);
    return res;
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

function diameterOfBinaryTree(root: TreeNode | null): number {
    let res = 0;
    const dfs = (root: TreeNode | null) => {
        if (root == null) {
            return 0;
        }
        const { left, right } = root;
        const l = dfs(left);
        const r = dfs(right);
        res = Math.max(res, l + r);
        return Math.max(l, r) + 1;
    };
    dfs(root);
    return res;
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
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, res: &mut i32) -> i32 {
        if root.is_none() {
            return 0;
        }
        let root = root.as_ref().unwrap().as_ref().borrow();
        let left = Self::dfs(&root.left, res);
        let right = Self::dfs(&root.right, res);
        *res = (*res).max(left + right);
        left.max(right) + 1
    }

    pub fn diameter_of_binary_tree(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        let mut res = 0;
        Self::dfs(&root, &mut res);
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
