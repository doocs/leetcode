---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1379.Find%20a%20Corresponding%20Node%20of%20a%20Binary%20Tree%20in%20a%20Clone%20of%20That%20Tree/README.md
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 二叉树
---

<!-- problem:start -->

# [1379. 找出克隆二叉树中的相同节点](https://leetcode.cn/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree)

[English Version](/solution/1300-1399/1379.Find%20a%20Corresponding%20Node%20of%20a%20Binary%20Tree%20in%20a%20Clone%20of%20That%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两棵二叉树，原始树 <code>original</code> 和克隆树 <code>cloned</code>，以及一个位于原始树 <code>original</code>&nbsp;中的目标节点&nbsp;<code>target</code>。</p>

<p>其中，克隆树 <code>cloned</code>&nbsp;是原始树 <code>original</code>&nbsp;的一个<strong> 副本 </strong>。</p>

<p>请找出在树&nbsp;<code>cloned</code>&nbsp;中，与&nbsp;<code>target</code>&nbsp;<strong>相同&nbsp;</strong>的节点，并返回对该节点的引用（在 C/C++ 等有指针的语言中返回 节点指针，其他语言返回节点本身）。</p>

<p>&nbsp;</p>

<p><strong>注意：</strong>你 <strong>不能</strong> 对两棵二叉树，以及 <code>target</code>&nbsp;节点进行更改。<strong>只能</strong> 返回对克隆树&nbsp;<code>cloned</code>&nbsp;中已有的节点的引用。</p>

<ul>
</ul>

<p>&nbsp;</p>

<ul>
</ul>

<p><strong>示例 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1379.Find%20a%20Corresponding%20Node%20of%20a%20Binary%20Tree%20in%20a%20Clone%20of%20That%20Tree/images/e1.png" /></p>

<pre>
<strong>输入:</strong> tree = [7,4,3,null,null,6,19], target = 3
<strong>输出:</strong> 3
<strong>解释:</strong> 上图画出了树 original 和 cloned。target 节点在树 original 中，用绿色标记。答案是树 cloned 中的黄颜色的节点（其他示例类似）。</pre>

<p><strong>示例 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1379.Find%20a%20Corresponding%20Node%20of%20a%20Binary%20Tree%20in%20a%20Clone%20of%20That%20Tree/images/e2.png" /></p>

<pre>
<strong>输入:</strong> tree = [7], target =  7
<strong>输出:</strong> 7
</pre>

<p><strong>示例 3:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1379.Find%20a%20Corresponding%20Node%20of%20a%20Binary%20Tree%20in%20a%20Clone%20of%20That%20Tree/images/e3.png" /></p>

<pre>
<strong>输入:</strong> tree = [8,null,6,null,5,null,4,null,3,null,2,null,1], target = 4
<strong>输出:</strong> 4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数量范围为<meta charset="UTF-8" />&nbsp;<code>[1, 10<sup>4</sup>]</code>&nbsp;。</li>
	<li>同一棵树中，没有值相同的节点。</li>
	<li><code>target</code>&nbsp;节点是树&nbsp;<code>original</code>&nbsp;中的一个节点，并且不会是&nbsp;<code>null</code>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>如果树中允许出现值相同的节点，将如何解答？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们设计一个函数 $dfs(root1, root2)$，它会在树 $root1$ 和 $root2$ 中同时进行 DFS 遍历，当遍历到某个节点时，如果这个节点恰好为 $target$，那么我们就返回 $root2$ 中对应的节点。否则，我们递归地在 $root1$ 和 $root2$ 的左右子树中寻找 $target$，并返回找到的结果中不为空的那一个。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是树中节点的数量。

<!-- tabs:start -->

#### Python3

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def getTargetCopy(
        self, original: TreeNode, cloned: TreeNode, target: TreeNode
    ) -> TreeNode:
        def dfs(root1: TreeNode, root2: TreeNode) -> TreeNode:
            if root1 is None:
                return None
            if root1 == target:
                return root2
            return dfs(root1.left, root2.left) or dfs(root1.right, root2.right)

        return dfs(original, cloned)
```

#### Java

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    private TreeNode target;

    public final TreeNode getTargetCopy(
        final TreeNode original, final TreeNode cloned, final TreeNode target) {
        this.target = target;
        return dfs(original, cloned);
    }

    private TreeNode dfs(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return null;
        }
        if (root1 == target) {
            return root2;
        }
        TreeNode res = dfs(root1.left, root2.left);
        return res == null ? dfs(root1.right, root2.right) : res;
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
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */

class Solution {
public:
    TreeNode* getTargetCopy(TreeNode* original, TreeNode* cloned, TreeNode* target) {
        function<TreeNode*(TreeNode*, TreeNode*)> dfs = [&](TreeNode* root1, TreeNode* root2) -> TreeNode* {
            if (root1 == nullptr) {
                return nullptr;
            }
            if (root1 == target) {
                return root2;
            }
            TreeNode* left = dfs(root1->left, root2->left);
            return left == nullptr ? dfs(root1->right, root2->right) : left;
        };
        return dfs(original, cloned);
    }
};
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

function getTargetCopy(
    original: TreeNode | null,
    cloned: TreeNode | null,
    target: TreeNode | null,
): TreeNode | null {
    const dfs = (root1: TreeNode | null, root2: TreeNode | null): TreeNode | null => {
        if (!root1) {
            return null;
        }
        if (root1 === target) {
            return root2;
        }
        return dfs(root1.left, root2.left) || dfs(root1.right, root2.right);
    };
    return dfs(original, cloned);
}
```

#### C#

```cs
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int x) { val = x; }
 * }
 */

public class Solution {
    private TreeNode target;

    public TreeNode GetTargetCopy(TreeNode original, TreeNode cloned, TreeNode target) {
        this.target = target;
        return dfs(original, cloned);
    }

    private TreeNode dfs(TreeNode original, TreeNode cloned) {
        if (original == null) {
            return null;
        }
        if (original == target) {
            return cloned;
        }
        TreeNode left = dfs(original.left, cloned.left);
        return left == null ? dfs(original.right, cloned.right) : left;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
