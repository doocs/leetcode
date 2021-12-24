# [988. Smallest String Starting From Leaf](https://leetcode.com/problems/smallest-string-starting-from-leaf)

[中文文档](/solution/0900-0999/0988.Smallest%20String%20Starting%20From%20Leaf/README.md)

## Description

<p>Given the <code>root</code> of a binary tree, each node has a value from <code>0</code> to <code>25</code> representing the letters <code>&#39;a&#39;</code> to <code>&#39;z&#39;</code>: a value of <code>0</code> represents <code>&#39;a&#39;</code>, a value of <code>1</code> represents <code>&#39;b&#39;</code>, and so on.</p>

<p>Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.</p>

<p><em>(As a reminder, any shorter prefix of a string is lexicographically smaller: for example, <code>&quot;ab&quot;</code> is lexicographically smaller than <code>&quot;aba&quot;</code>.&nbsp; A leaf of a node is a node that has no children.)</em></p>

<div>

<div>

<p>&nbsp;</p>

<ol>

</ol>

</div>

</div>

<div>

<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0988.Smallest%20String%20Starting%20From%20Leaf/images/tree1.png" style="width: 160px; height: 107px;" /></strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">[0,1,2,3,4,3,4]</span>

<strong>Output: </strong><span id="example-output-1">&quot;dba&quot;</span>

</pre>

<div>

<p><strong>Example 2:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0988.Smallest%20String%20Starting%20From%20Leaf/images/tree2.png" style="width: 160px; height: 107px;" /></strong></p>

<pre>

<strong>Input: </strong><span id="example-input-2-1">[25,1,3,1,3,0,2]</span>

<strong>Output: </strong><span id="example-output-2">&quot;adz&quot;</span>

</pre>

<div>

<p><strong>Example 3:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0988.Smallest%20String%20Starting%20From%20Leaf/images/tree3.png" style="height: 170px; width: 172px;" /></strong></p>

<pre>

<strong>Input: </strong><span id="example-input-3-1">[2,2,1,null,1,0,null,0]</span>

<strong>Output: </strong><span id="example-output-3">&quot;abc&quot;</span>

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li>The number of nodes in the given tree will be between <code>1</code> and <code>8500</code>.</li>
	<li>Each node in the tree will have a value between <code>0</code> and <code>25</code>.</li>
</ol>

</div>

</div>

</div>

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
    def smallestFromLeaf(self, root: TreeNode) -> str:
        ans = chr(ord('z') + 1)

        def dfs(root, path):
            nonlocal ans
            if root:
                path.append(chr(ord('a') + root.val))
                if root.left is None and root.right is None:
                    ans = min(ans, ''.join(reversed(path)))
                dfs(root.left, path)
                dfs(root.right, path)
                path.pop()

        dfs(root, [])
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
    private StringBuilder path;
    private String ans;

    public String smallestFromLeaf(TreeNode root) {
        path = new StringBuilder();
        ans = String.valueOf((char) ('z' + 1));
        dfs(root, path);
        return ans;
    }

    private void dfs(TreeNode root, StringBuilder path) {
        if (root != null) {
            path.append((char) ('a' + root.val));
            if (root.left == null && root.right == null) {
                String t = path.reverse().toString();
                if (t.compareTo(ans) < 0) {
                    ans = t;
                }
                path.reverse();
            }
            dfs(root.left, path);
            dfs(root.right, path);
            path.deleteCharAt(path.length() - 1);
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
    string ans = "";

    string smallestFromLeaf(TreeNode* root) {
        string path = "";
        dfs(root, path);
        return ans;
    }

    void dfs(TreeNode* root, string& path) {
        if (!root) return;
        path += 'a' + root->val;
        if (!root->left && !root->right)
        {
            string t = path;
            reverse(t.begin(), t.end());
            if (ans == "" || t < ans) ans = t;
        }
        dfs(root->left, path);
        dfs(root->right, path);
        path.pop_back();
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
func smallestFromLeaf(root *TreeNode) string {
	ans := ""
	var dfs func(root *TreeNode, path string)
	dfs = func(root *TreeNode, path string) {
		if root == nil {
			return
		}
		path = string('a'+root.Val) + path
		if root.Left == nil && root.Right == nil {
			if ans == "" || path < ans {
				ans = path
			}
			return
		}
		dfs(root.Left, path)
		dfs(root.Right, path)
	}

	dfs(root, "")
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
