# [04.10. Check SubTree](https://leetcode-cn.com/problems/check-subtree-lcci)

[中文文档](/lcci/04.10.Check%20SubTree/README.md)

## Description

<p>T1&nbsp;and T2 are two very large binary trees, with T1&nbsp;much bigger than T2. Create an algorithm to determine if T2 is a subtree of T1.</p>

<p>A tree T2 is a subtree of T1&nbsp;if there exists a node n in T1&nbsp;such that the subtree of n is identical to T2. That is, if you cut off the tree at node n, the two trees would be identical.</p>

<p><strong>Example1:</strong></p>

<pre>

<strong> Input</strong>: t1 = [1, 2, 3], t2 = [2]

<strong> Output</strong>: true

</pre>

<p><strong>Example2:</strong></p>

<pre>

<strong> Input</strong>: t1 = [1, null, 2, 4], t2 = [3, 2]

<strong> Output</strong>: false

</pre>

<p><strong>Note: </strong></p>

<ol>
	<li>The node numbers of both tree are in [0, 20000].</li>
</ol>

## Solutions

Find the t2 node in t1 first, then use the depth-first search (DFS) algorithm to make sure that the subtree and the subtree of t2 are identical, otherwise return FALSE.

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
    def checkSubTree(self, t1: TreeNode, t2: TreeNode) -> bool:
        def dfs(t1, t2):
            if t2 is None:
                return True
            if t1 is None:
                return False
            if t1.val == t2.val:
                return dfs(t1.left, t2.left) and dfs(t1.right, t2.right)
            return dfs(t1.left, t2) or dfs(t1.right, t2)

        return dfs(t1, t2)
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
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return true;
        }
        if (t1 == null) {
            return false;
        }
        if (t1.val == t2.val) {
            return checkSubTree(t1.left, t2.left) && checkSubTree(t1.right, t2.right);
        }
        return checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
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
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    bool checkSubTree(TreeNode* t1, TreeNode* t2) {
        if (!t2) return 1;
        if (!t1) return 0;
        if (t1->val == t2->val) return checkSubTree(t1->left, t2->left) && checkSubTree(t1->right, t2->right);
        return checkSubTree(t1->left, t2) || checkSubTree(t1->right, t2);
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
func checkSubTree(t1 *TreeNode, t2 *TreeNode) bool {
	if t2 == nil {
		return true
	}
	if t1 == nil {
		return false
	}
	if t1.Val == t2.Val {
		return checkSubTree(t1.Left, t2.Left) && checkSubTree(t1.Right, t2.Right)
	}
	return checkSubTree(t1.Left, t2) || checkSubTree(t1.Right, t2)
}
```

### **...**

```

```

<!-- tabs:end -->
