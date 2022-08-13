# [1660. Correct a Binary Tree](https://leetcode.com/problems/correct-a-binary-tree)

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

<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1660.Correct%20a%20Binary%20Tree/images/ex1v2.png" style="width: 250px; height: 177px;" /></strong></p>

<pre>

<strong>Input:</strong> root = [1,2,3], fromNode = 2, toNode = 3

<strong>Output:</strong> [1,null,3]

<strong>Explanation:</strong> The node with value 2 is invalid, so remove it.

</pre>

<p><strong>Example 2:</strong></p>

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
    def correctBinaryTree(self, root: TreeNode) -> TreeNode:
        q = deque([root])
        res = root
        p = {}
        while q:
            n = len(q)
            mp = {}
            for _ in range(n):
                node = q.popleft()
                if node.val in mp:
                    left, father = p[mp[node.val]]
                    if left:
                        father.left = None
                    else:
                        father.right = None
                    return res
                if node.left:
                    q.append(node.left)
                    p[node.left.val] = [True, node]
                if node.right:
                    q.append(node.right)
                    p[node.right.val] = [False, node]
                    mp[node.right.val] = node.val
        return res
```

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def correctBinaryTree(self, root: TreeNode) -> TreeNode:
        q = deque([root])
        while q:
            n = len(q)
            for _ in range(n):
                node = q.popleft()
                if node.right:
                    if node.right.right in q:
                        node.right = None
                        return root
                    q.append(node.right)
                if node.left:
                    if node.left.right in q:
                        node.left = None
                        return root
                    q.append(node.left)
        return root
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
    public TreeNode correctBinaryTree(TreeNode root) {
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int n = q.size();
            while (n-- > 0) {
                TreeNode node = q.pollFirst();
                if (node.right != null) {
                    if (node.right.right != null && q.contains(node.right.right)) {
                        node.right = null;
                        return root;
                    }
                    q.offer(node.right);
                }
                if (node.left != null) {
                    if (node.left.right != null && q.contains(node.left.right)) {
                        node.left = null;
                        return root;
                    }
                    q.offer(node.left);
                }
            }
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
    TreeNode* correctBinaryTree(TreeNode* root) {
        queue<TreeNode*> q;
        q.push(root);
        unordered_set<TreeNode*> s;
        while (!q.empty()) {
            int n = q.size();
            while (n--) {
                TreeNode* node = q.front();
                q.pop();
                if (node->right) {
                    if (s.count(node->right->right)) {
                        node->right = nullptr;
                        return root;
                    }
                    q.push(node->right);
                    s.insert(node->right);
                }
                if (node->left) {
                    if (s.count(node->left->right)) {
                        node->left = nullptr;
                        return root;
                    }
                    q.push(node->left);
                    s.insert(node->left);
                }
            }
        }
        return root;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
