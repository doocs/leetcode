# [257. Binary Tree Paths](https://leetcode.com/problems/binary-tree-paths)

[中文文档](/solution/0200-0299/0257.Binary%20Tree%20Paths/README.md)

## Description

<p>Given the <code>root</code> of a binary tree, return <em>all root-to-leaf paths in <strong>any order</strong></em>.</p>

<p>A <strong>leaf</strong> is a node with no children.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0257.Binary%20Tree%20Paths/images/paths-tree.jpg" style="width: 207px; height: 293px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,null,5]
<strong>Output:</strong> [&quot;1-&gt;2-&gt;5&quot;,&quot;1-&gt;3&quot;]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [1]
<strong>Output:</strong> [&quot;1&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 100]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
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
    def binaryTreePaths(self, root: TreeNode) -> List[str]:
        def dfs(root):
            if root is None:
                return
            t.append(str(root.val))
            if root.left is None and root.right is None:
                ans.append('->'.join(t))
            dfs(root.left)
            dfs(root.right)
            t.pop()

        t = []
        ans = []
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
    private List<String> ans;
    private List<String> t;

    public List<String> binaryTreePaths(TreeNode root) {
        ans = new ArrayList<>();
        t = new ArrayList<>();
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        t.add(root.val + "");
        if (root.left == null && root.right == null) {
            ans.add(String.join("->", t));
        }
        dfs(root.left);
        dfs(root.right);
        t.remove(t.size() - 1);
    }
}
```

### **TypeScript**

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

function binaryTreePaths(root: TreeNode | null): string[] {
    let ans = [];
    let t = [];
    function dfs(root) {
        if (!root) return;
        t.push(String(root.val));
        if (!root.left && !root.right) ans.push(t.join('->'));
        dfs(root.left);
        dfs(root.right);
        t.pop();
    }
    dfs(root);
    return ans;
}
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
func binaryTreePaths(root *TreeNode) []string {
	var ans []string
	var t []string
	var dfs func(root *TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		t = append(t, strconv.Itoa(root.Val))
		if root.Left == nil && root.Right == nil {
			ans = append(ans, strings.Join(t, "->"))
		}
		dfs(root.Left)
		dfs(root.Right)
		t = t[:len(t)-1]
	}
	dfs(root)
	return ans
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
    vector<string> ans;

    vector<string> binaryTreePaths(TreeNode* root) {
        dfs(root, "");
        return ans;
    }

    void dfs(TreeNode* root, string t) {
        t += to_string(root->val);
        if (!root->left && !root->right) {
            ans.push_back(t);
            return;
        }
        t += "->";
        if (root->left) dfs(root->left, t);
        if (root->right) dfs(root->right, t);
    }
};
```

### **...**

```

```

<!-- tabs:end -->
