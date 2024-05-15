---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0872.Leaf-Similar%20Trees/README.md
tags:
    - 树
    - 深度优先搜索
    - 二叉树
---

# [872. 叶子相似的树](https://leetcode.cn/problems/leaf-similar-trees)

[English Version](/solution/0800-0899/0872.Leaf-Similar%20Trees/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个&nbsp;<strong>叶值序列 </strong>。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0872.Leaf-Similar%20Trees/images/tree.png" style="height: 336px; width: 400px;" /></p>

<p>举个例子，如上图所示，给定一棵叶值序列为&nbsp;<code>(6, 7, 4, 9, 8)</code>&nbsp;的树。</p>

<p>如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是&nbsp;<em>叶相似&nbsp;</em>的。</p>

<p>如果给定的两个根结点分别为&nbsp;<code>root1</code> 和&nbsp;<code>root2</code>&nbsp;的树是叶相似的，则返回&nbsp;<code>true</code>；否则返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0872.Leaf-Similar%20Trees/images/leaf-similar-1.jpg" style="height: 237px; width: 600px;" /></p>

<pre>
<strong>输入：</strong>root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0872.Leaf-Similar%20Trees/images/leaf-similar-2.jpg" style="height: 110px; width: 300px;" /></p>

<pre>
<strong>输入：</strong>root1 = [1,2,3], root2 = [1,3,2]
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>给定的两棵树结点数在&nbsp;<code>[1, 200]</code> 范围内</li>
	<li>给定的两棵树上的值在&nbsp;<code>[0, 200]</code> 范围内</li>
</ul>

## 解法

### 方法一：DFS

后序遍历。

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def leafSimilar(self, root1: Optional[TreeNode], root2: Optional[TreeNode]) -> bool:
        def dfs(root):
            if root is None:
                return []
            ans = dfs(root.left) + dfs(root.right)
            return ans or [root.val]

        return dfs(root1) == dfs(root2)
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
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = dfs(root1);
        List<Integer> l2 = dfs(root2);
        return l1.equals(l2);
    }

    private List<Integer> dfs(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> ans = dfs(root.left);
        ans.addAll(dfs(root.right));
        if (ans.isEmpty()) {
            ans.add(root.val);
        }
        return ans;
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
    bool leafSimilar(TreeNode* root1, TreeNode* root2) {
        return dfs(root1) == dfs(root2);
    }

    vector<int> dfs(TreeNode* root) {
        if (!root) return {};
        auto ans = dfs(root->left);
        auto right = dfs(root->right);
        ans.insert(ans.end(), right.begin(), right.end());
        if (ans.empty()) ans.push_back(root->val);
        return ans;
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
func leafSimilar(root1 *TreeNode, root2 *TreeNode) bool {
	var dfs func(*TreeNode) []int
	dfs = func(root *TreeNode) []int {
		if root == nil {
			return []int{}
		}
		ans := dfs(root.Left)
		ans = append(ans, dfs(root.Right)...)
		if len(ans) == 0 {
			ans = append(ans, root.Val)
		}
		return ans
	}
	return reflect.DeepEqual(dfs(root1), dfs(root2))
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
    #[allow(dead_code)]
    pub fn leaf_similar(
        root1: Option<Rc<RefCell<TreeNode>>>,
        root2: Option<Rc<RefCell<TreeNode>>>
    ) -> bool {
        let mut one_vec: Vec<i32> = Vec::new();
        let mut two_vec: Vec<i32> = Vec::new();

        // Initialize the two vector
        Self::traverse(&mut one_vec, root1);
        Self::traverse(&mut two_vec, root2);

        one_vec == two_vec
    }

    #[allow(dead_code)]
    fn traverse(v: &mut Vec<i32>, root: Option<Rc<RefCell<TreeNode>>>) {
        if root.is_none() {
            return;
        }
        if Self::is_leaf_node(&root) {
            v.push(root.as_ref().unwrap().borrow().val);
        }
        let left = root.as_ref().unwrap().borrow().left.clone();
        let right = root.as_ref().unwrap().borrow().right.clone();
        Self::traverse(v, left);
        Self::traverse(v, right);
    }

    #[allow(dead_code)]
    fn is_leaf_node(node: &Option<Rc<RefCell<TreeNode>>>) -> bool {
        node.as_ref().unwrap().borrow().left.is_none() &&
            node.as_ref().unwrap().borrow().right.is_none()
    }
}
```

```js
var leafSimilar = function (root1, root2) {
    const dfs = root => {
        if (!root) {
            return [];
        }
        let ans = [...dfs(root.left), ...dfs(root.right)];
        if (!ans.length) {
            ans = [root.val];
        }
        return ans;
    };
    const l1 = dfs(root1);
    const l2 = dfs(root2);
    return l1.toString() === l2.toString();
};
```

<!-- tabs:end -->

<!-- end -->
