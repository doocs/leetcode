# [113. 路径总和 II](https://leetcode.cn/problems/path-sum-ii)

[English Version](/solution/0100-0199/0113.Path%20Sum%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你二叉树的根节点 <code>root</code> 和一个整数目标和 <code>targetSum</code> ，找出所有 <strong>从根节点到叶子节点</strong> 路径总和等于给定目标和的路径。</p>

<p><strong>叶子节点</strong> 是指没有子节点的节点。</p>

<div class="original__bRMd">
<div>
<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0113.Path%20Sum%20II/images/pathsumii1.jpg" style="width: 500px; height: 356px;" />
<pre>
<strong>输入：</strong>root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
<strong>输出：</strong>[[5,4,11,2],[5,8,4,5]]
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0113.Path%20Sum%20II/images/pathsum2.jpg" style="width: 212px; height: 181px;" />
<pre>
<strong>输入：</strong>root = [1,2,3], targetSum = 5
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = [1,2], targetSum = 0
<strong>输出：</strong>[]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点总数在范围 <code>[0, 5000]</code> 内</li>
	<li><code>-1000 <= Node.val <= 1000</code></li>
	<li><code>-1000 <= targetSum <= 1000</code></li>
</ul>
</div>
</div>

## 解法

<!-- 这里可写通用的实现逻辑 -->

深度优先搜索+路径记录。

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
    def pathSum(self, root: Optional[TreeNode], targetSum: int) -> List[List[int]]:
        def dfs(root, s):
            if root is None:
                return
            t.append(root.val)
            s += root.val
            if root.left is None and root.right is None:
                if s == targetSum:
                    ans.append(t[:])
            dfs(root.left, s)
            dfs(root.right, s)
            t.pop()

        ans = []
        t = []
        if root is None:
            return ans
        dfs(root, 0)
        return ans
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
    private List<List<Integer>> ans;
    private List<Integer> t;
    private int target;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        ans = new ArrayList<>();
        t = new ArrayList<>();
        target = targetSum;
        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode root, int s) {
        if (root == null) {
            return;
        }
        t.add(root.val);
        s += root.val;
        if (root.left == null && root.right == null) {
            if (s == target) {
                ans.add(new ArrayList<>(t));
            }
        }
        dfs(root.left, s);
        dfs(root.right, s);
        t.remove(t.size() - 1);
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
    vector<vector<int>> ans;
    vector<int> t;
    int target;

    vector<vector<int>> pathSum(TreeNode* root, int targetSum) {
        target = targetSum;
        dfs(root, 0);
        return ans;
    }

    void dfs(TreeNode* root, int s) {
        if (!root) return;
        t.push_back(root->val);
        s += root->val;
        if (!root->left && !root->right && s == target) ans.push_back(t);
        dfs(root->left, s);
        dfs(root->right, s);
        t.pop_back();
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
func pathSum(root *TreeNode, targetSum int) [][]int {
	ans := [][]int{}
	t := []int{}
	var dfs func(root *TreeNode, s int)
	dfs = func(root *TreeNode, s int) {
		if root == nil {
			return
		}
		t = append(t, root.Val)
		s += root.Val
		if root.Left == nil && root.Right == nil && s == targetSum {
			cp := make([]int, len(t))
			copy(cp, t)
			ans = append(ans, cp)
		}
		dfs(root.Left, s)
		dfs(root.Right, s)
		t = t[:len(t)-1]
	}
	dfs(root, 0)
	return ans
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
        root: Option<Rc<RefCell<TreeNode>>>,
        paths: &mut  Vec<i32>,
        mut target_sum: i32,
        res: &mut Vec<Vec<i32>>,
    ) {
        if let Some(node) = root {
            let mut node = node.borrow_mut();
            target_sum -= node.val;
            paths.push(node.val);
            if node.left.is_none() && node.right.is_none() {
                if target_sum == 0 {
                    res.push(paths.clone());
                }
            } else {
                if node.left.is_some() {
                    Self::dfs(node.left.take(), paths, target_sum, res);
                }
                if node.right.is_some() {
                    Self::dfs(node.right.take(), paths, target_sum, res);
                }
            }
            paths.pop();
        }
    }

    pub fn path_sum(root: Option<Rc<RefCell<TreeNode>>>, target_sum: i32) -> Vec<Vec<i32>> {
        let mut res = vec![];
        let mut paths = vec![];
        Self::dfs(root, &mut paths,  target_sum, &mut res);
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
