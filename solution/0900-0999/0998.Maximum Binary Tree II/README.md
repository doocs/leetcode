# [998. 最大二叉树 II](https://leetcode-cn.com/problems/maximum-binary-tree-ii)

[English Version](/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>最大树定义：一个树，其中每个节点的值都大于其子树中的任何其他值。</p>

<p>给出最大树的根节点 <code>root</code>。</p>

<p>就像<a href="https://leetcode-cn.com/problems/maximum-binary-tree/">之前的问题</a>那样，给定的树是从列表 <code>A</code>（<code>root = Construct(A)</code>）递归地使用下述 <code>Construct(A)</code> 例程构造的：</p>

<ul>
	<li>如果 <code>A</code> 为空，返回 <code>null</code></li>
	<li>否则，令 <code>A[i]</code> 作为 A 的最大元素。创建一个值为 <code>A[i]</code> 的根节点 <code>root</code></li>
	<li><code>root</code> 的左子树将被构建为 <code>Construct([A[0], A[1], ..., A[i-1]])</code></li>
	<li><code>root</code> 的右子树将被构建为 <code>Construct([A[i+1], A[i+2], ..., A[A.length - 1]])</code></li>
	<li>返回 <code>root</code></li>
</ul>

<p>请注意，我们没有直接给定 A，只有一个根节点 <code>root = Construct(A)</code>.</p>

<p>假设 <code>B</code> 是 <code>A</code> 的副本，并在末尾附加值 <code>val</code>。题目数据保证 <code>B</code> 中的值是不同的。</p>

<p>返回 <code>Construct(B)</code>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/images/maximum-binary-tree-1-1.png" style="height: 160px; width: 159px;" /><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/images/maximum-binary-tree-1-2.png" style="height: 160px; width: 169px;" /></strong></p>

<pre>
<strong>输入：</strong>root = [4,1,3,null,null,2], val = 5
<strong>输出：</strong>[5,4,null,1,3,null,null,2]
<strong>解释：</strong>A = [1,4,2,3], B = [1,4,2,3,5]
</pre>

<p><strong>示例 2：<br />
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/images/maximum-binary-tree-2-1.png" style="height: 160px; width: 180px;" /><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/images/maximum-binary-tree-2-2.png" style="height: 160px; width: 214px;" /></strong></p>

<pre>
<strong>输入：</strong>root = [5,2,4,null,1], val = 3
<strong>输出：</strong>[5,2,4,null,1,null,3]
<strong>解释：</strong>A = [2,1,5,4], B = [2,1,5,4,3]
</pre>

<p><strong>示例 3：<br />
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/images/maximum-binary-tree-3-1.png" style="height: 160px; width: 180px;" /><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/images/maximum-binary-tree-3-2.png" style="height: 160px; width: 201px;" /></strong></p>

<pre>
<strong>输入：</strong>root = [5,2,3,null,1], val = 4
<strong>输出：</strong>[5,2,4,null,1,3]
<strong>解释：</strong>A = [2,1,5,3], B = [2,1,5,3,4]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= B.length <= 100</code></li>
</ul>

<p> </p>

<p> </p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

已知最大树 A，插入一个值 val 后，返回插入后的树。

如果 val 是最大数，那么将 val 作为新的根节点，root 作为新的根节点的左子树。

如果 val 不是最大数，由于 val 是在最后追加的数，那么一定是在 root 的右边，所以将 val 作为新节点插入 root 的右子树即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

 function insertIntoMaxTree(root: TreeNode | null, val: number): TreeNode | null {
    if (root == null || val > root.val) {
        return new TreeNode(val, root);
    }
    root.right = insertIntoMaxTree(root.right, val);
    return root;
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
