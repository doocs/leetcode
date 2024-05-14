# [530. 二叉搜索树的最小绝对差](https://leetcode.cn/problems/minimum-absolute-difference-in-bst)

[English Version](/solution/0500-0599/0530.Minimum%20Absolute%20Difference%20in%20BST/README_EN.md)

<!-- tags:树,深度优先搜索,广度优先搜索,二叉搜索树,二叉树 -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二叉搜索树的根节点 <code>root</code> ，返回 <strong>树中任意两不同节点值之间的最小差值</strong> 。</p>

<p>差值是一个正数，其数值等于两值之差的绝对值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0530.Minimum%20Absolute%20Difference%20in%20BST/images/bst1.jpg" style="width: 292px; height: 301px;" />
<pre>
<strong>输入：</strong>root = [4,2,6,1,3]
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0530.Minimum%20Absolute%20Difference%20in%20BST/images/bst2.jpg" style="width: 282px; height: 301px;" />
<pre>
<strong>输入：</strong>root = [1,0,48,null,null,12,49]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数目范围是 <code>[2, 10<sup>4</sup>]</code></li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><strong>注意：</strong>本题与 783 <a href="https://leetcode.cn/problems/minimum-distance-between-bst-nodes/">https://leetcode.cn/problems/minimum-distance-between-bst-nodes/</a> 相同</p>

## 解法

### 方法一：中序遍历

中序遍历二叉搜索树，获取当前节点与上个节点差值的最小值即可。

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def getMinimumDifference(self, root: TreeNode) -> int:
        def dfs(root):
            if root is None:
                return
            dfs(root.left)
            nonlocal ans, prev
            ans = min(ans, abs(prev - root.val))
            prev = root.val
            dfs(root.right)

        ans = prev = inf
        dfs(root)
        return ans
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
    private int ans;
    private int prev;
    private int inf = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        ans = inf;
        prev = inf;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        ans = Math.min(ans, Math.abs(root.val - prev));
        prev = root.val;
        dfs(root.right);
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
    const int inf = INT_MAX;
    int ans;
    int prev;

    int getMinimumDifference(TreeNode* root) {
        ans = inf, prev = inf;
        dfs(root);
        return ans;
    }

    void dfs(TreeNode* root) {
        if (!root) return;
        dfs(root->left);
        ans = min(ans, abs(prev - root->val));
        prev = root->val;
        dfs(root->right);
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
func getMinimumDifference(root *TreeNode) int {
	inf := 0x3f3f3f3f
	ans, prev := inf, inf
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Left)
		ans = min(ans, abs(prev-root.Val))
		prev = root.Val
		dfs(root.Right)
	}
	dfs(root)
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
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
    pub fn get_minimum_difference(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        let mut ret = i32::MAX;
        let mut prev = i32::MAX;
        Self::traverse(root, &mut prev, &mut ret);
        ret
    }

    #[allow(dead_code)]
    fn traverse(root: Option<Rc<RefCell<TreeNode>>>, prev: &mut i32, ans: &mut i32) {
        let left = root.as_ref().unwrap().borrow().left.clone();
        let right = root.as_ref().unwrap().borrow().right.clone();
        let val = root.as_ref().unwrap().borrow().val;
        if !left.is_none() {
            Self::traverse(left.clone(), prev, ans);
        }
        *ans = std::cmp::min(*ans, (*prev - val).abs());
        *prev = val;
        if !right.is_none() {
            Self::traverse(right.clone(), prev, ans);
        }
    }
}
```

<!-- tabs:end -->

<!-- end -->
