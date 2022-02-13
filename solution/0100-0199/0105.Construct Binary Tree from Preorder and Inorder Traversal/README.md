# [105. 从前序与中序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal)

[English Version](/solution/0100-0199/0105.Construct%20Binary%20Tree%20from%20Preorder%20and%20Inorder%20Traversal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>根据一棵树的前序遍历与中序遍历构造二叉树。</p>

<p><strong>注意:</strong><br>
你可以假设树中没有重复的元素。</p>

<p>例如，给出</p>

<pre>前序遍历 preorder =&nbsp;[3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]</pre>

<p>返回如下的二叉树：</p>

<pre>    3
   / \
  9  20
    /  \
   15   7</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

前序序列的第一个结点 `preorder[0]` 为根节点，我们在中序序列中找到根节点的位置 i，可以将中序序列划分为左子树 `inorder[:i]` 、右子树 `inorder[i+1:]`。

通过左右子树的区间，可以计算出左、右子树节点的个数，假设为 m、n。然后在前序节点中，从根节点往后的 m 个节点为左子树，再往后的 n 个节点为右子树。

递归求解即可。

> 前序遍历：先遍历根节点，再遍历左右子树；中序遍历：先遍历左子树，再遍历根节点，最后遍历右子树。

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
    def buildTree(self, preorder: List[int], inorder: List[int]) -> TreeNode:
        if not preorder:
            return None
        v = preorder[0]
        root = TreeNode(val=v)
        i = inorder.index(v)
        root.left = self.buildTree(preorder[1:1 + i], inorder[:i])
        root.right = self.buildTree(preorder[1 + i:], inorder[i + 1:])
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
    private Map<Integer, Integer> indexes = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; ++i) {
            indexes.put(inorder[i], i);
        }
        return dfs(preorder, inorder, 0, 0, preorder.length);
    }

    private TreeNode dfs(int[] preorder, int[] inorder, int i, int j, int n) {
        if (n <= 0) {
            return null;
        }
        int v = preorder[i];
        int k = indexes.get(v);
        TreeNode root = new TreeNode(v);
        root.left = dfs(preorder, inorder, i + 1, j, k - j);
        root.right = dfs(preorder, inorder, i + 1 + k - j, k + 1, n - k + j - 1);
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
    unordered_map<int, int> indexes;

    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        for (int i = 0; i < inorder.size(); ++i) indexes[inorder[i]] = i;
        return dfs(preorder, inorder, 0, 0, inorder.size());
    }

    TreeNode* dfs(vector<int>& preorder, vector<int>& inorder, int i, int j, int n) {
        if (n <= 0) return nullptr;
        int v = preorder[i];
        int k = indexes[v];
        TreeNode* root = new TreeNode(v);
        root->left = dfs(preorder, inorder, i + 1, j, k - j);
        root->right = dfs(preorder, inorder, i + 1 + k - j, k + 1, n - k + j - 1);
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
func buildTree(preorder []int, inorder []int) *TreeNode {
	indexes := make(map[int]int)
	for i, v := range inorder {
		indexes[v] = i
	}
	var dfs func(i, j, n int) *TreeNode
	dfs = func(i, j, n int) *TreeNode {
		if n <= 0 {
			return nil
		}
		v := preorder[i]
		k := indexes[v]
		root := &TreeNode{Val: v}
		root.Left = dfs(i+1, j, k-j)
		root.Right = dfs(i+1+k-j, k+1, n-k+j-1)
		return root
	}
	return dfs(0, 0, len(inorder))
}
```

### **...**

```

```

<!-- tabs:end -->
