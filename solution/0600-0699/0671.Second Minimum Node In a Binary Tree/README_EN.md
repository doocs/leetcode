# [671. Second Minimum Node In a Binary Tree](https://leetcode.com/problems/second-minimum-node-in-a-binary-tree)

[中文文档](/solution/0600-0699/0671.Second%20Minimum%20Node%20In%20a%20Binary%20Tree/README.md)

## Description

<p>Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly <code>two</code> or <code>zero</code> sub-node. If the node has two sub-nodes, then this node&#39;s value is the smaller value among its two sub-nodes. More formally, the property&nbsp;<code>root.val = min(root.left.val, root.right.val)</code>&nbsp;always holds.</p>

<p>Given such a binary tree, you need to output the <b>second minimum</b> value in the set made of all the nodes&#39; value in the whole tree.</p>

<p>If no such second minimum value exists, output -1 instead.</p>

<p>&nbsp;</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0671.Second%20Minimum%20Node%20In%20a%20Binary%20Tree/images/smbt1.jpg" style="width: 431px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [2,2,5,null,null,5,7]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The smallest value is 2, the second smallest value is 5.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0671.Second%20Minimum%20Node%20In%20a%20Binary%20Tree/images/smbt2.jpg" style="width: 321px; height: 182px;" />
<pre>
<strong>Input:</strong> root = [2,2,2]
<strong>Output:</strong> -1
<strong>Explanation:</strong> The smallest value is 2, but there isn&#39;t any second smallest value.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 25]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>root.val == min(root.left.val, root.right.val)</code>&nbsp;for each internal node of the tree.</li>
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
    def findSecondMinimumValue(self, root: Optional[TreeNode]) -> int:
        def dfs(root):
            if root:
                dfs(root.left)
                dfs(root.right)
                nonlocal ans, v
                if root.val > v:
                    ans = root.val if ans == -1 else min(ans, root.val)

        ans, v = -1, root.val
        dfs(root)
        return ans
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
    private int ans = -1;

    public int findSecondMinimumValue(TreeNode root) {
        dfs(root, root.val);
        return ans;
    }

    private void dfs(TreeNode root, int val) {
        if (root != null) {
            dfs(root.left, val);
            dfs(root.right, val);
            if (root.val > val) {
                ans = ans == -1 ? root.val : Math.min(ans, root.val);
            }
        }
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
    int ans = -1;

    int findSecondMinimumValue(TreeNode* root) {
        dfs(root, root->val);
        return ans;
    }

    void dfs(TreeNode* root, int val) {
        if (!root) return;
        dfs(root->left, val);
        dfs(root->right, val);
        if (root->val > val) ans = ans == -1 ? root->val : min(ans, root->val);
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
func findSecondMinimumValue(root *TreeNode) int {
	ans, v := -1, root.Val
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Left)
		dfs(root.Right)
		if root.Val > v {
			if ans == -1 || ans > root.Val {
				ans = root.Val
			}
		}
	}
	dfs(root)
	return ans
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
 * @return {number}
 */
var findSecondMinimumValue = function (root) {
    let ans = -1;
    const v = root.val;
    function dfs(root) {
        if (!root) {
            return;
        }
        dfs(root.left);
        dfs(root.right);
        if (root.val > v) {
            if (ans == -1 || ans > root.val) {
                ans = root.val;
            }
        }
    }
    dfs(root);
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
