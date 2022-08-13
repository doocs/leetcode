# [1676. Lowest Common Ancestor of a Binary Tree IV](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv)

[中文文档](/solution/1600-1699/1676.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20IV/README.md)

## Description

<p>Given the <code>root</code> of a binary tree and an array of <code>TreeNode</code> objects <code>nodes</code>, return <em>the lowest common ancestor (LCA) of <strong>all the nodes</strong> in </em><code>nodes</code>. All the nodes will exist in the tree, and all values of the tree&#39;s nodes are <strong>unique</strong>.</p>

<p>Extending the <strong><a href="https://en.wikipedia.org/wiki/Lowest_common_ancestor" target="_blank">definition of LCA on Wikipedia</a></strong>: &quot;The lowest common ancestor of <code>n</code> nodes <code>p<sub>1</sub></code>, <code>p<sub>2</sub></code>, ..., <code>p<sub>n</sub></code> in a binary tree <code>T</code> is the lowest node that has every <code>p<sub>i</sub></code> as a <strong>descendant</strong> (where we allow <b>a node to be a descendant of itself</b>) for every valid <code>i</code>&quot;. A <strong>descendant</strong> of a node <code>x</code> is a node <code>y</code> that is on the path from node <code>x</code> to some leaf node.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1676.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20IV/images/binarytree.png" />
<pre>
<strong>Input:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [4,7]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The lowest common ancestor of nodes 4 and 7 is node 2.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1676.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20IV/images/binarytree.png" />
<pre>
<strong>Input:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [1]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The lowest common ancestor of a single node is the node itself.

</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1676.Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree%20IV/images/binarytree.png" />
<pre>
<strong>Input:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [7,6,2,4]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The lowest common ancestor of the nodes 7, 6, 2, and 4 is node 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>9</sup> &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
	<li>All <code>Node.val</code> are <strong>unique</strong>.</li>
	<li>All <code>nodes[i]</code> will exist in the tree.</li>
	<li>All <code>nodes[i]</code> are distinct.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def lowestCommonAncestor(
        self, root: 'TreeNode', nodes: 'List[TreeNode]'
    ) -> 'TreeNode':
        def dfs(root):
            if root is None or root.val in s:
                return root
            left, right = dfs(root.left), dfs(root.right)
            if left and right:
                return root
            return left or right

        s = {node.val for node in nodes}
        return dfs(root)
```

### **Java**

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
    private Set<Integer> s = new HashSet<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        for (TreeNode node : nodes) {
            s.add(node.val);
        }
        return dfs(root);
    }

    private TreeNode dfs(TreeNode root) {
        if (root == null || s.contains(root.val)) {
            return root;
        }
        TreeNode left = dfs(root.left);
        TreeNode right = dfs(root.right);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
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
    unordered_set<int> s;

    TreeNode* lowestCommonAncestor(TreeNode* root, vector<TreeNode*>& nodes) {
        for (auto node : nodes) s.insert(node->val);
        return dfs(root);
    }

    TreeNode* dfs(TreeNode* root) {
        if (!root || s.count(root->val)) return root;
        auto left = dfs(root->left);
        auto right = dfs(root->right);
        if (!left) return right;
        if (!right) return left;
        return root;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
