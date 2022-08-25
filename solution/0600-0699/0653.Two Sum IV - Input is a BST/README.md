# [653. 两数之和 IV - 输入二叉搜索树](https://leetcode.cn/problems/two-sum-iv-input-is-a-bst)

[English Version](/solution/0600-0699/0653.Two%20Sum%20IV%20-%20Input%20is%20a%20BST/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉搜索树 <code>root</code> 和一个目标结果 <code>k</code>，如果二叉搜索树中存在两个元素且它们的和等于给定的目标结果，则返回 <code>true</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0653.Two%20Sum%20IV%20-%20Input%20is%20a%20BST/images/sum_tree_1.jpg" style="height: 229px; width: 400px;" />
<pre>
<strong>输入:</strong> root = [5,3,6,2,4,null,7], k = 9
<strong>输出:</strong> true
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0653.Two%20Sum%20IV%20-%20Input%20is%20a%20BST/images/sum_tree_2.jpg" style="height: 229px; width: 400px;" />
<pre>
<strong>输入:</strong> root = [5,3,6,2,4,null,7], k = 28
<strong>输出:</strong> false
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li>二叉树的节点个数的范围是&nbsp;&nbsp;<code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>4</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>4</sup></code></li>
	<li>题目数据保证，输入的 <code>root</code> 是一棵 <strong>有效</strong> 的二叉搜索树</li>
	<li><code>-10<sup>5</sup>&nbsp;&lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

用哈希表记录访问过的节点。

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
    def findTarget(self, root: TreeNode, k: int) -> bool:
        def find(root):
            if not root:
                return False
            if k - root.val in nodes:
                return True
            nodes.add(root.val)
            return find(root.left) or find(root.right)

        nodes = set()
        return find(root)
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
    private Set<Integer> nodes;

    public boolean findTarget(TreeNode root, int k) {
        nodes = new HashSet<>();
        return find(root, k);
    }

    private boolean find(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        if (nodes.contains(k - root.val)) {
            return true;
        }
        nodes.add(root.val);
        return find(root.left, k) || find(root.right, k);
    }
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

function findTarget(root: TreeNode | null, k: number): boolean {
    let nodes: Set<number> = new Set();
    return find(root, k, nodes);
}

function find(root: TreeNode | null, k: number, nodes: Set<number>): boolean {
    if (!root) return false;
    if (nodes.has(k - root.val)) return true;
    nodes.add(root.val);
    return find(root.left, k, nodes) || find(root.right, k, nodes);
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

function findTarget(root: TreeNode | null, k: number): boolean {
    if (root == null) {
        return false;
    }
    const set = new Set<number>();
    const dfs = (root: TreeNode | null) => {
        if (root == null) {
            return false;
        }
        if (set.has(root.val)) {
            return true;
        }
        set.add(k - root.val);
        return dfs(root.left) || dfs(root.right);
    };
    return dfs(root);
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
    unordered_set<int> nodes;

    bool findTarget(TreeNode* root, int k) {
        return find(root, k);
    }

    bool find(TreeNode* root, int k) {
        if (!root) return false;
        if (nodes.count(k - root->val)) return true;
        nodes.insert(root->val);
        return find(root->left, k) || find(root->right, k);
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
func findTarget(root *TreeNode, k int) bool {
	nodes := make(map[int]bool)

	var find func(root *TreeNode, k int) bool
	find = func(root *TreeNode, k int) bool {
		if root == nil {
			return false
		}
		if nodes[k-root.Val] {
			return true
		}
		nodes[root.Val] = true
		return find(root.Left, k) || find(root.Right, k)
	}
	return find(root, k)
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
use std::collections::{HashSet, VecDeque};
impl Solution {
    pub fn find_target(root: Option<Rc<RefCell<TreeNode>>>, k: i32) -> bool {
        let mut set = HashSet::new();
        let mut q = VecDeque::new();
        q.push_back(root);
        while let Some(node) = q.pop_front() {
            if let Some(node) = node {
                let mut node = node.as_ref().borrow_mut();
                if set.contains(&node.val) {
                    return true;
                }
                set.insert(k - node.val);
                q.push_back(node.left.take());
                q.push_back(node.right.take());
            }
        }
        false
    }
}
```

### **...**

```

```

<!-- tabs:end -->
