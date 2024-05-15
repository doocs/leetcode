---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1660.Correct%20a%20Binary%20Tree/README_EN.md
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
    - Hash Table
    - Binary Tree
---

# [1660. Correct a Binary Tree 🔒](https://leetcode.com/problems/correct-a-binary-tree)

[中文文档](/solution/1600-1699/1660.Correct%20a%20Binary%20Tree/README.md)

## Description

<p>You have a binary tree with a small defect. There is <strong>exactly one</strong> invalid node where its right child incorrectly points to another node at the <strong>same depth</strong> but to the <b>invalid node&#39;s right</b>.</p>

<p>Given the root of the binary tree with this defect, <code>root</code>, return <em>the root of the binary tree after <strong>removing</strong> this invalid node <strong>and every node underneath it</strong> (minus the node it incorrectly points to).</em></p>

<p><strong>Custom testing:</strong></p>

<p>The test input is read as 3 lines:</p>

<ul>

    <li><code>TreeNode root</code></li>

    <li><code>int fromNode</code> (<strong>not available to </strong><code>correctBinaryTree</code>)</li>

    <li><code>int toNode</code> (<strong>not available to </strong><code>correctBinaryTree</code>)</li>

</ul>

<p>After the binary tree rooted at <code>root</code> is parsed, the <code>TreeNode</code> with value of <code>fromNode</code> will have its right child pointer pointing to the <code>TreeNode</code> with a value of <code>toNode</code>. Then, <code>root</code> is passed to <code>correctBinaryTree</code>.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1660.Correct%20a%20Binary%20Tree/images/ex1v2.png" style="width: 250px; height: 177px;" /></strong></p>

<pre>

<strong>Input:</strong> root = [1,2,3], fromNode = 2, toNode = 3

<strong>Output:</strong> [1,null,3]

<strong>Explanation:</strong> The node with value 2 is invalid, so remove it.

</pre>

<p><strong class="example">Example 2:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1660.Correct%20a%20Binary%20Tree/images/ex2v3.png" style="width: 350px; height: 255px;" /></strong></p>

<pre>

<strong>Input:</strong> root = [8,3,1,7,null,9,4,2,null,null,null,5,6], fromNode = 7, toNode = 4

<strong>Output:</strong> [8,3,1,null,null,9,4,null,null,5,6]

<strong>Explanation:</strong> The node with value 7 is invalid, so remove it and the node underneath it, node 2.

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li>The number of nodes in the tree is in the range <code>[3, 10<sup>4</sup>]</code>.</li>

    <li><code>-10<sup>9</sup> &lt;= Node.val &lt;= 10<sup>9</sup></code></li>

    <li>All <code>Node.val</code> are <strong>unique</strong>.</li>

    <li><code>fromNode != toNode</code></li>

    <li><code>fromNode</code> and <code>toNode</code> will exist in the tree and will be on the same depth.</li>

    <li><code>toNode</code> is to the <strong>right</strong> of <code>fromNode</code>.</li>

    <li><code>fromNode.right</code> is <code>null</code> in the initial tree from the test data.</li>

</ul>

## Solutions

### Solution 1: DFS

We design a function `dfs(root)` to handle the subtree with `root` as the root. If `root` is `null` or `root.right` has been visited, `root` is an invalid node, so we return `null`. Otherwise, we recursively process `root.right` and `root.left`, and return `root`.

Finally, we return `dfs(root)`.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes in the binary tree.

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def correctBinaryTree(self, root: TreeNode) -> TreeNode:
        def dfs(root):
            if root is None or root.right in vis:
                return None
            vis.add(root)
            root.right = dfs(root.right)
            root.left = dfs(root.left)
            return root

        vis = set()
        return dfs(root)
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
    private Set<TreeNode> vis = new HashSet<>();

    public TreeNode correctBinaryTree(TreeNode root) {
        return dfs(root);
    }

    private TreeNode dfs(TreeNode root) {
        if (root == null || vis.contains(root.right)) {
            return null;
        }
        vis.add(root);
        root.right = dfs(root.right);
        root.left = dfs(root.left);
        return root;
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
    TreeNode* correctBinaryTree(TreeNode* root) {
        unordered_set<TreeNode*> vis;
        function<TreeNode*(TreeNode*)> dfs = [&](TreeNode* root) -> TreeNode* {
            if (!root || vis.count(root->right)) {
                return nullptr;
            }
            vis.insert(root);
            root->right = dfs(root->right);
            root->left = dfs(root->left);
            return root;
        };
        return dfs(root);
    }
};
```

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @param {number} from
 * @param {number} to
 * @return {TreeNode}
 */
var correctBinaryTree = function (root) {
    const dfs = root => {
        if (!root || vis.has(root.right)) {
            return null;
        }
        vis.add(root);
        root.right = dfs(root.right);
        root.left = dfs(root.left);
        return root;
    };
    const vis = new Set();
    return dfs(root);
};
```

<!-- tabs:end -->

<!-- end -->
