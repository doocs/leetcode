# [545. Boundary of Binary Tree](https://leetcode.com/problems/boundary-of-binary-tree)

[中文文档](/solution/0500-0599/0545.Boundary%20of%20Binary%20Tree/README.md)

## Description

<p>The <strong>boundary</strong> of a binary tree is the concatenation of the <strong>root</strong>, the <strong>left boundary</strong>, the <strong>leaves</strong> ordered from left-to-right, and the <strong>reverse order</strong> of the <strong>right boundary</strong>.</p>

<p>The <strong>left boundary</strong> is the set of nodes defined by the following:</p>

<ul>
	<li>The root node&#39;s left child is in the left boundary. If the root does not have a left child, then the left boundary is <strong>empty</strong>.</li>
	<li>If a node in the left boundary and has a left child, then the left child is in the left boundary.</li>
	<li>If a node is in the left boundary, has <strong>no</strong> left child, but has a right child, then the right child is in the left boundary.</li>
	<li>The leftmost leaf is <strong>not</strong> in the left boundary.</li>
</ul>

<p>The <strong>right boundary</strong> is similar to the <strong>left boundary</strong>, except it is the right side of the root&#39;s right subtree. Again, the leaf is <strong>not</strong> part of the <strong>right boundary</strong>, and the <strong>right boundary</strong> is empty if the root does not have a right child.</p>

<p>The <strong>leaves</strong> are nodes that do not have any children. For this problem, the root is <strong>not</strong> a leaf.</p>

<p>Given the <code>root</code> of a binary tree, return <em>the values of its <strong>boundary</strong></em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0545.Boundary%20of%20Binary%20Tree/images/boundary1.jpg" style="width: 299px; height: 290px;" />
<pre>
<strong>Input:</strong> root = [1,null,2,3,4]
<strong>Output:</strong> [1,3,4,2]
<b>Explanation:</b>
- The left boundary is empty because the root does not have a left child.
- The right boundary follows the path starting from the root&#39;s right child 2 -&gt; 4.
  4 is a leaf, so the right boundary is [2].
- The leaves from left to right are [3,4].
Concatenating everything results in [1] + [] + [3,4] + [2] = [1,3,4,2].
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0545.Boundary%20of%20Binary%20Tree/images/boundary2.jpg" style="width: 599px; height: 411px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,4,5,6,null,null,null,7,8,9,10]
<strong>Output:</strong> [1,2,4,7,8,9,10,6,3]
<b>Explanation:</b>
- The left boundary follows the path starting from the root&#39;s left child 2 -&gt; 4.
  4 is a leaf, so the left boundary is [2].
- The right boundary follows the path starting from the root&#39;s right child 3 -&gt; 6 -&gt; 10.
  10 is a leaf, so the right boundary is [3,6], and in reverse order is [6,3].
- The leaves from left to right are [4,7,8,9,10].
Concatenating everything results in [1] + [2] + [4,7,8,9,10] + [6,3] = [1,2,4,7,8,9,10,6,3].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
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
    def boundaryOfBinaryTree(self, root: TreeNode) -> List[int]:
        self.res = []
        if not root:
            return self.res
        # root
        if not self.is_leaf(root):
            self.res.append(root.val)

        # left boundary
        t = root.left
        while t:
            if not self.is_leaf(t):
                self.res.append(t.val)
            t = t.left if t.left else t.right

        # leaves
        self.add_leaves(root)

        # right boundary(reverse order)
        s = []
        t = root.right
        while t:
            if not self.is_leaf(t):
                s.append(t.val)
            t = t.right if t.right else t.left
        while s:
            self.res.append(s.pop())

        # output
        return self.res

    def add_leaves(self, root):
        if self.is_leaf(root):
            self.res.append(root.val)
            return
        if root.left:
            self.add_leaves(root.left)
        if root.right:
            self.add_leaves(root.right)

    def is_leaf(self, node) -> bool:
        return node and node.left is None and node.right is None
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
    private List<Integer> res;

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        res = new ArrayList<>();

        // root
        if (!isLeaf(root)) {
            res.add(root.val);
        }

        // left boundary
        TreeNode t = root.left;
        while (t != null) {
            if (!isLeaf(t)) {
                res.add(t.val);
            }
            t = t.left == null ? t.right : t.left;
        }

        // leaves
        addLeaves(root);

        // right boundary(reverse order)
        Deque<Integer> s = new ArrayDeque<>();
        t = root.right;
        while (t != null) {
            if (!isLeaf(t)) {
                s.offer(t.val);
            }
            t = t.right == null ? t.left : t.right;
        }
        while (!s.isEmpty()) {
            res.add(s.pollLast());
        }

        // output
        return res;
    }

    private void addLeaves(TreeNode root) {
        if (isLeaf(root)) {
            res.add(root.val);
            return;
        }
        if (root.left != null) {
            addLeaves(root.left);
        }
        if (root.right != null) {
            addLeaves(root.right);
        }
    }

    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }
}
```

### **JavaScript**

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
 * @return {number[]}
 */
var boundaryOfBinaryTree = function (root) {
    let leftBoundary = function (root, res) {
        while (root) {
            let curVal = root.val;
            if (root.left) {
                root = root.left;
            } else if (root.right) {
                root = root.right;
            } else {
                break;
            }
            res.push(curVal);
        }
    };
    let rightBoundary = function (root, res) {
        let stk = [];
        while (root) {
            let curVal = root.val;
            if (root.right) {
                root = root.right;
            } else if (root.left) {
                root = root.left;
            } else {
                break;
            }
            stk.push(curVal);
        }
        let len = stk.length;
        for (let i = 0; i < len; i++) {
            res.push(stk.pop());
        }
    };
    let levelBoundary = function (root, res) {
        if (root) {
            levelBoundary(root.left, res);
            if (!root.left && !root.right) {
                res.push(root.val);
            }
            levelBoundary(root.right, res);
        }
    };
    let res = [];
    if (root) {
        res.push(root.val);
        leftBoundary(root.left, res);
        if (root.left || root.right) {
            levelBoundary(root, res);
        }
        rightBoundary(root.right, res);
    }
    return res;
};
```

### **...**

```

```

<!-- tabs:end -->
