# [998. Maximum Binary Tree II](https://leetcode.com/problems/maximum-binary-tree-ii)

[中文文档](/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/README.md)

## Description

<p>We are given the <code>root</code>&nbsp;node of a <em>maximum tree:</em> a tree where every node has a value greater than any other value in its subtree.</p>

<p>Just as in the <a href="https://leetcode.com/problems/maximum-binary-tree/">previous problem</a>, the given tree&nbsp;was constructed from an list&nbsp;<code>A</code>&nbsp;(<code>root = Construct(A)</code>) recursively with the following&nbsp;<code>Construct(A)</code> routine:</p>

<ul>
	<li>If <code>A</code> is empty, return <code>null</code>.</li>
	<li>Otherwise, let <code>A[i]</code> be the largest element of <code>A</code>.&nbsp; Create a <code>root</code> node with value <code>A[i]</code>.</li>
	<li>The left child of <code>root</code> will be <code>Construct([A[0], A[1], ..., A[i-1]])</code></li>
	<li>The right child of <code>root</code>&nbsp;will be <code>Construct([A[i+1], A[i+2], ..., A[A.length - 1]])</code></li>
	<li>Return <code>root</code>.</li>
</ul>

<p>Note that we were not given A directly, only a root node <code>root = Construct(A)</code>.</p>

<p>Suppose <code>B</code> is a copy of <code>A</code> with the value <code>val</code> appended to it.&nbsp; It is guaranteed that <code>B</code> has unique values.</p>

<p>Return <code>Construct(B)</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/images/maximum-binary-tree-1-1.png" style="width: 159px; height: 160px;" /><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/images/maximum-binary-tree-1-2.png" style="width: 169px; height: 160px;" /></strong></p>

<pre>
<strong>Input: </strong>root = <span id="example-input-1-1">[4,1,3,null,null,2]</span>, val = <span id="example-input-1-2">5</span>
<strong>Output: </strong><span id="example-output-1">[5,4,null,1,3,null,null,2]
<strong>Explanation:</strong> A = </span><span>[1,4,2,3], B = </span><span>[1,4,2,3,5]</span>
</pre>

<p><strong>Example 2:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/images/maximum-binary-tree-2-1.png" style="width: 180px; height: 160px;" /><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/images/maximum-binary-tree-2-2.png" style="width: 214px; height: 160px;" /></strong></p>

<pre>
<strong>Input: </strong>root = <span id="example-input-2-1">[5,2,4,null,1]</span>, val = <span id="example-input-2-2">3</span>
<strong>Output: </strong><span id="example-output-2">[5,2,4,null,1,null,3]
</span><span id="example-output-1"><strong>Explanation:</strong> A = </span><span>[2,1,5,4], B = </span><span>[2,1,5,4,3]</span>
</pre>

<p><strong>Example 3:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/images/maximum-binary-tree-3-1.png" style="width: 180px; height: 160px;" /><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/images/maximum-binary-tree-3-2.png" style="width: 201px; height: 160px;" /></strong></p>

<pre>
<strong>Input: </strong>root = <span id="example-input-3-1">[5,2,3,null,1]</span>, val = <span id="example-input-3-2">4</span>
<strong>Output: </strong><span id="example-output-3">[5,2,4,null,1,3]
</span><span id="example-output-1"><strong>Explanation:</strong> A = </span><span>[2,1,5,3], B = </span><span>[2,1,5,3,4]</span>
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= B.length &lt;= 100</code></li>
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
    def insertIntoMaxTree(self, root: TreeNode, val: int) -> TreeNode:
        if root is None or root.val < val:
            return TreeNode(val, root, None)
        root.right = self.insertIntoMaxTree(root.right, val)
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
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null || root.val < val) {
            return new TreeNode(val, root, null);
        }
        root.right = insertIntoMaxTree(root.right, val);
        return root;
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

function insertIntoMaxTree(
    root: TreeNode | null,
    val: number
): TreeNode | null {
    if (root == null || val > root.val) {
        return new TreeNode(val, root);
    }
    root.right = insertIntoMaxTree(root.right, val);
    return root;
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
    TreeNode* insertIntoMaxTree(TreeNode* root, int val) {
        if (root == nullptr || root->val < val) {
            return new TreeNode(val, root, nullptr);
        }
        root->right = insertIntoMaxTree(root->right, val);
        return root;
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
func insertIntoMaxTree(root *TreeNode, val int) *TreeNode {
	if root == nil || root.Val < val {
		return &TreeNode{
			Val:   val,
			Left:  root,
			Right: nil,
		}
	}
	root.Right = insertIntoMaxTree(root.Right, val)
	return root
}
```

### **...**

```

```

<!-- tabs:end -->
