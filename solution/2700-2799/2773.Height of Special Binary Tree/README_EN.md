# [2773. Height of Special Binary Tree 🔒](https://leetcode.com/problems/height-of-special-binary-tree)

[中文文档](/solution/2700-2799/2773.Height%20of%20Special%20Binary%20Tree/README.md)

<!-- tags:Tree,Depth-First Search,Breadth-First Search,Binary Tree -->

## Description

<p>You are given a <code>root</code>, which is the root of a <strong>special</strong> binary tree with <code>n</code> nodes. The nodes of the special binary tree are numbered from <code>1</code> to <code>n</code>. Suppose the tree has <code>k</code> leaves in the following order: <code>b<sub>1 </sub>&lt;<sub> </sub>b<sub>2 </sub>&lt; ... &lt; b<sub>k</sub></code>.</p>

<p>The leaves of this tree have a <strong>special</strong> property! That is, for every leaf <code>b<sub>i</sub></code>, the following conditions hold:</p>

<ul>
	<li>The right child of <code>b<sub>i</sub></code> is <code>b<sub>i + 1</sub></code> if <code>i &lt; k</code>, and <code>b<sub>1</sub></code> otherwise.</li>
	<li>The left child of <code>b<sub>i</sub></code> is <code>b<sub>i - 1</sub></code> if <code>i &gt; 1</code>, and <code>b<sub>k</sub></code> otherwise.</li>
</ul>

<p>Return<em> the height of the given tree.</em></p>

<p><strong>Note:</strong> The height of a binary tree is the length of the <strong>longest path</strong> from the root to any other node.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> root = [1,2,3,null,null,4,5]
<strong>Output:</strong> 2
<strong>Explanation: </strong>The given tree is shown in the following picture. Each leaf&#39;s left child is the leaf to its left (shown with the blue edges). Each leaf&#39;s right child is the leaf to its right (shown with the red edges). We can see that the graph has a height of 2.
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2773.Height%20of%20Special%20Binary%20Tree/images/1.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 200px; height: 200px;" /></p>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [1,2]
<strong>Output:</strong> 1
<strong>Explanation: </strong>The given tree is shown in the following picture. There is only one leaf, so it doesn&#39;t have any left or right child. We can see that the graph has a height of 1.
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2773.Height%20of%20Special%20Binary%20Tree/images/2.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 95px; height: 122px;" /></p>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> root = [1,2,3,null,null,4,null,5,6]
<strong>Output:</strong> 3
<strong>Explanation: </strong>The given tree is shown in the following picture. Each leaf&#39;s left child is the leaf to its left (shown with the blue edges). Each leaf&#39;s right child is the leaf to its right (shown with the red edges). We can see that the graph has a height of 3.
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2773.Height%20of%20Special%20Binary%20Tree/images/3.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 200px; height: 280px;" /></p>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == number of nodes in the tree</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= node.val &lt;= n</code></li>
	<li>The input is generated such that each <code>node.val</code> is unique.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def heightOfTree(self, root: Optional[TreeNode]) -> int:
        def dfs(root: Optional[TreeNode], d: int):
            nonlocal ans
            ans = max(ans, d)
            if root.left and root.left.right != root:
                dfs(root.left, d + 1)
            if root.right and root.right.left != root:
                dfs(root.right, d + 1)

        ans = 0
        dfs(root, 0)
        return ans
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
    private int ans;

    public int heightOfTree(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode root, int d) {
        ans = Math.max(ans, d++);
        if (root.left != null && root.left.right != root) {
            dfs(root.left, d);
        }
        if (root.right != null && root.right.left != root) {
            dfs(root.right, d);
        }
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
    int heightOfTree(TreeNode* root) {
        int ans = 0;
        function<void(TreeNode*, int)> dfs = [&](TreeNode* root, int d) {
            ans = max(ans, d++);
            if (root->left && root->left->right != root) {
                dfs(root->left, d);
            }
            if (root->right && root->right->left != root) {
                dfs(root->right, d);
            }
        };
        dfs(root, 0);
        return ans;
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
func heightOfTree(root *TreeNode) (ans int) {
	var dfs func(*TreeNode, int)
	dfs = func(root *TreeNode, d int) {
		if ans < d {
			ans = d
		}
		d++
		if root.Left != nil && root.Left.Right != root {
			dfs(root.Left, d)
		}
		if root.Right != nil && root.Right.Left != root {
			dfs(root.Right, d)
		}
	}
	dfs(root, 0)
	return
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

function heightOfTree(root: TreeNode | null): number {
    let ans = 0;
    const dfs = (root: TreeNode | null, d: number) => {
        ans = Math.max(ans, d++);
        if (root.left && root.left.right !== root) {
            dfs(root.left, d);
        }
        if (root.right && root.right.left !== root) {
            dfs(root.right, d);
        }
    };
    dfs(root, 0);
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
