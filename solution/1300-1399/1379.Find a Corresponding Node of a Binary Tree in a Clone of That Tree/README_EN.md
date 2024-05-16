---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1379.Find%20a%20Corresponding%20Node%20of%20a%20Binary%20Tree%20in%20a%20Clone%20of%20That%20Tree/README_EN.md
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
    - Binary Tree
---

<!-- problem:start -->

# [1379. Find a Corresponding Node of a Binary Tree in a Clone of That Tree](https://leetcode.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree)

[中文文档](/solution/1300-1399/1379.Find%20a%20Corresponding%20Node%20of%20a%20Binary%20Tree%20in%20a%20Clone%20of%20That%20Tree/README.md)

## Description

<!-- description:start -->

<p>Given two binary trees <code>original</code> and <code>cloned</code> and given a reference to a node <code>target</code> in the original tree.</p>

<p>The <code>cloned</code> tree is a <strong>copy of</strong> the <code>original</code> tree.</p>

<p>Return <em>a reference to the same node</em> in the <code>cloned</code> tree.</p>

<p><strong>Note</strong> that you are <strong>not allowed</strong> to change any of the two trees or the <code>target</code> node and the answer <strong>must be</strong> a reference to a node in the <code>cloned</code> tree.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1379.Find%20a%20Corresponding%20Node%20of%20a%20Binary%20Tree%20in%20a%20Clone%20of%20That%20Tree/images/e1.png" style="width: 544px; height: 426px;" />
<pre>
<strong>Input:</strong> tree = [7,4,3,null,null,6,19], target = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> In all examples the original and cloned trees are shown. The target node is a green node from the original tree. The answer is the yellow node from the cloned tree.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1379.Find%20a%20Corresponding%20Node%20of%20a%20Binary%20Tree%20in%20a%20Clone%20of%20That%20Tree/images/e2.png" style="width: 221px; height: 159px;" />
<pre>
<strong>Input:</strong> tree = [7], target =  7
<strong>Output:</strong> 7
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1379.Find%20a%20Corresponding%20Node%20of%20a%20Binary%20Tree%20in%20a%20Clone%20of%20That%20Tree/images/e3.png" style="width: 459px; height: 486px;" />
<pre>
<strong>Input:</strong> tree = [8,null,6,null,5,null,4,null,3,null,2,null,1], target = 4
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the <code>tree</code> is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li>The values of the nodes of the <code>tree</code> are unique.</li>
	<li><code>target</code> node is a node from the <code>original</code> tree and is not <code>null</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you solve the problem if repeated values on the tree are allowed?</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

We design a function $dfs(root1, root2)$, which performs DFS traversal simultaneously in trees $root1$ and $root2$. When traversing to a node, if this node happens to be $target$, then we return the corresponding node in $root2$. Otherwise, we recursively search for $target$ in the left and right subtrees of $root1$ and $root2$, and return the result that is not empty.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the number of nodes in the tree.

<!-- tabs:start -->

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
