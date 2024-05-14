---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1382.Balance%20a%20Binary%20Search%20Tree/README.md
rating: 1540
tags:
    - 贪心
    - 树
    - 深度优先搜索
    - 二叉搜索树
    - 分治
    - 二叉树
---

# [1382. 将二叉搜索树变平衡](https://leetcode.cn/problems/balance-a-binary-search-tree)

[English Version](/solution/1300-1399/1382.Balance%20a%20Binary%20Search%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵二叉搜索树，请你返回一棵&nbsp;<strong>平衡后</strong>&nbsp;的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。如果有多种构造方法，请你返回任意一种。</p>

<p>如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 <code>1</code> ，我们就称这棵二叉搜索树是&nbsp;<strong>平衡的</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1382.Balance%20a%20Binary%20Search%20Tree/images/balance1-tree.jpg" style="height: 319px; width: 500px;" /></p>

<pre>
<strong>输入：</strong>root = [1,null,2,null,3,null,4,null,null]
<strong>输出：</strong>[2,1,3,null,null,null,4]
<strong>解释：</strong>这不是唯一的正确答案，[3,1,4,null,2,null,null] 也是一个可行的构造方案。
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1382.Balance%20a%20Binary%20Search%20Tree/images/balanced2-tree.jpg" style="height: 145px; width: 224px;" /></p>

<pre>
<strong>输入:</strong> root = [2,1,3]
<strong>输出:</strong> [2,1,3]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树节点的数目在&nbsp;<code>[1, 10<sup>4</sup>]</code>&nbsp;范围内。</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一：中序遍历 + 构造平衡二叉树

由于原树是一棵二叉搜索树，因此我们可以将其中序遍历的结果保存在一个数组 $nums$ 中，然后我们设计一个函数 $build(i, j)$，它用来构造 $nums$ 中下标范围 $[i, j]$ 内的平衡二叉搜索树，那么答案就是 $build(0, |nums| - 1)$。

函数 $build(i, j)$ 的执行逻辑如下：

-   如果 $i > j$，那么平衡二叉搜索树为空，返回空节点；
-   否则，我们取 $mid = (i + j) / 2$ 作为根节点，然后递归构建左子树和右子树，返回根节点。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉搜索树的节点数。

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def balanceBST(self, root: TreeNode) -> TreeNode:
        def dfs(root: TreeNode):
            if root is None:
                return
            dfs(root.left)
            nums.append(root.val)
            dfs(root.right)

        def build(i: int, j: int) -> TreeNode:
            if i > j:
                return None
            mid = (i + j) >> 1
            left = build(i, mid - 1)
            right = build(mid + 1, j)
            return TreeNode(nums[mid], left, right)

        nums = []
        dfs(root)
        return build(0, len(nums) - 1)
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
    private List<Integer> nums = new ArrayList<>();

    public TreeNode balanceBST(TreeNode root) {
        dfs(root);
        return build(0, nums.size() - 1);
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        nums.add(root.val);
        dfs(root.right);
    }

    private TreeNode build(int i, int j) {
        if (i > j) {
            return null;
        }
        int mid = (i + j) >> 1;
        TreeNode left = build(i, mid - 1);
        TreeNode right = build(mid + 1, j);
        return new TreeNode(nums.get(mid), left, right);
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
    TreeNode* balanceBST(TreeNode* root) {
        dfs(root);
        return build(0, nums.size() - 1);
    }

private:
    vector<int> nums;

    void dfs(TreeNode* root) {
        if (!root) {
            return;
        }
        dfs(root->left);
        nums.push_back(root->val);
        dfs(root->right);
    }

    TreeNode* build(int i, int j) {
        if (i > j) {
            return nullptr;
        }
        int mid = (i + j) >> 1;
        TreeNode* left = build(i, mid - 1);
        TreeNode* right = build(mid + 1, j);
        return new TreeNode(nums[mid], left, right);
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
func balanceBST(root *TreeNode) *TreeNode {
	ans := []int{}
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Left)
		ans = append(ans, root.Val)
		dfs(root.Right)
	}
	var build func(i, j int) *TreeNode
	build = func(i, j int) *TreeNode {
		if i > j {
			return nil
		}
		mid := (i + j) >> 1
		left := build(i, mid-1)
		right := build(mid+1, j)
		return &TreeNode{Val: ans[mid], Left: left, Right: right}
	}
	dfs(root)
	return build(0, len(ans)-1)
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

function balanceBST(root: TreeNode | null): TreeNode | null {
    const nums: number[] = [];
    const dfs = (root: TreeNode | null): void => {
        if (root == null) {
            return;
        }
        dfs(root.left);
        nums.push(root.val);
        dfs(root.right);
    };
    const build = (i: number, j: number): TreeNode | null => {
        if (i > j) {
            return null;
        }
        const mid: number = (i + j) >> 1;
        const left: TreeNode | null = build(i, mid - 1);
        const right: TreeNode | null = build(mid + 1, j);
        return new TreeNode(nums[mid], left, right);
    };
    dfs(root);
    return build(0, nums.length - 1);
}
```

<!-- tabs:end -->

<!-- end -->
