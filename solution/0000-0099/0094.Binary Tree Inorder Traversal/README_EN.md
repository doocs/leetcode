# [94. Binary Tree Inorder Traversal](https://leetcode.com/problems/binary-tree-inorder-traversal)

[中文文档](/solution/0000-0099/0094.Binary%20Tree%20Inorder%20Traversal/README.md)

## Description

<p>Given the <code>root</code> of a binary tree, return <em>the inorder traversal of its nodes&#39; values</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0094.Binary%20Tree%20Inorder%20Traversal/images/inorder_1.jpg" style="width: 202px; height: 324px;" />
<pre>
<strong>Input:</strong> root = [1,null,2,3]
<strong>Output:</strong> [1,3,2]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = []
<strong>Output:</strong> []
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> root = [1]
<strong>Output:</strong> [1]
</pre>

<p><strong>Example 4:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0094.Binary%20Tree%20Inorder%20Traversal/images/inorder_5.jpg" style="width: 202px; height: 202px;" />
<pre>
<strong>Input:</strong> root = [1,2]
<strong>Output:</strong> [2,1]
</pre>

<p><strong>Example 5:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0094.Binary%20Tree%20Inorder%20Traversal/images/inorder_4.jpg" style="width: 202px; height: 202px;" />
<pre>
<strong>Input:</strong> root = [1,null,2]
<strong>Output:</strong> [1,2]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[0, 100]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>Follow up:</strong></p>

<p>Recursive solution is trivial, could you do it iteratively?</p>

<p>&nbsp;</p>

## Solutions

**1. Recusive Traversal**

**2. Non-recursive using Stack**

**3. Morris Traversal**

<!-- tabs:start -->

### **Python3**

Recusive:

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def inorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        def dfs(root):
            if root is None:
                return
            dfs(root.left)
            nonlocal ans
            ans.append(root.val)
            dfs(root.right)

        ans = []
        dfs(root)
        return ans
```

Non-recursive:

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def inorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        ans, stk = [], []
        while root or stk:
            if root:
                stk.append(root)
                root = root.left
            else:
                root = stk.pop()
                ans.append(root.val)
                root = root.right
        return ans
```

Morris Traversal:

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def inorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        ans = []
        while root:
            if root.left is None:
                ans.append(root.val)
                root = root.right
            else:
                prev = root.left
                while prev.right and prev.right != root:
                    prev = prev.right
                if prev.right is None:
                    prev.right = root
                    root = root.left
                else:
                    ans.append(root.val)
                    prev.right = None
                    root = root.right
        return ans
```

### **Java**

Recursive:

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
    private List<Integer> ans;

    public List<Integer> inorderTraversal(TreeNode root) {
        ans = new ArrayList<>();
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        ans.add(root.val);
        dfs(root.right);
    }
}
```

Non-recursive:

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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stk = new ArrayDeque<>();
        while (root != null || !stk.isEmpty()) {
            if (root != null) {
                stk.push(root);
                root = root.left;
            } else {
                root = stk.pop();
                ans.add(root.val);
                root = root.right;
            }
        }
        return ans;
    }
}
```

Morris Traversal:

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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        while (root != null) {
            if (root.left == null) {
                ans.add(root.val);
                root = root.right;
            } else {
                TreeNode prev = root.left;
                while (prev.right != null && prev.right != root) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    prev.right = root;
                    root = root.left;
                } else {
                    ans.add(root.val);
                    prev.right = null;
                    root = root.right;
                }
            }
        }
        return ans;
    }
}
```

### **JavaScript**

Recursive:

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
var inorderTraversal = function (root) {
    let ans = [];
    function dfs(root) {
        if (!root) return;
        dfs(root.left);
        ans.push(root.val);
        dfs(root.right);
    }
    dfs(root);
    return ans;
};
```

Non-recursive:

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
var inorderTraversal = function (root) {
    let ans = [],
        stk = [];
    while (root || stk.length > 0) {
        if (root) {
            stk.push(root);
            root = root.left;
        } else {
            root = stk.pop();
            ans.push(root.val);
            root = root.right;
        }
    }
    return ans;
};
```

Morris Traversal:

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
var inorderTraversal = function (root) {
    let ans = [];
    while (root) {
        if (!root.left) {
            ans.push(root.val);
            root = root.right;
        } else {
            let prev = root.left;
            while (prev.right && prev.right != root) {
                prev = prev.right;
            }
            if (!prev.right) {
                prev.right = root;
                root = root.left;
            } else {
                ans.push(root.val);
                prev.right = null;
                root = root.right;
            }
        }
    }
    return ans;
};
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
    vector<int> inorderTraversal(TreeNode* root) {
        vector<int> ans;
        while (root)
        {
            if (!root->left)
            {
                ans.push_back(root->val);
                root = root->right;
            }
            else
            {
                TreeNode* prev = root->left;
                while (prev->right && prev->right != root)
                {
                    prev = prev->right;
                }
                if (!prev->right)
                {
                    prev->right = root;
                    root = root->left;
                }
                else
                {
                    ans.push_back(root->val);
                    prev->right = nullptr;
                    root = root->right;
                }
            }
        }
        return ans;
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
func inorderTraversal(root *TreeNode) []int {
	var ans []int
	for root != nil {
		if root.Left == nil {
			ans = append(ans, root.Val)
			root = root.Right
		} else {
			prev := root.Left
			for prev.Right != nil && prev.Right != root {
				prev = prev.Right
			}
			if prev.Right == nil {
				prev.Right = root
				root = root.Left
			} else {
				ans = append(ans, root.Val)
				prev.Right = nil
				root = root.Right
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
