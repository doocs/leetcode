# [106. 从中序与后序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal)

[English Version](/solution/0100-0199/0106.Construct%20Binary%20Tree%20from%20Inorder%20and%20Postorder%20Traversal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>根据一棵树的中序遍历与后序遍历构造二叉树。</p>

<p><strong>注意:</strong><br>
你可以假设树中没有重复的元素。</p>

<p>例如，给出</p>

<pre>中序遍历 inorder =&nbsp;[9,3,15,20,7]
后序遍历 postorder = [9,15,7,20,3]</pre>

<p>返回如下的二叉树：</p>

<pre>    3
   / \
  9  20
    /  \
   15   7
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

思路同 [105](/solution/0100-0199/0105.Construct%20Binary%20Tree%20from%20Preorder%20and%20Inorder%20Traversal/README.md)。

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
    def buildTree(self, inorder: List[int], postorder: List[int]) -> TreeNode:
        if not postorder:
            return None
        v = postorder[-1]
        root = TreeNode(val=v)
        i = inorder.index(v)
        root.left = self.buildTree(inorder[: i], postorder[:i])
        root.right = self.buildTree(inorder[i + 1:], postorder[i:-1])
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

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; ++i) {
            indexes.put(inorder[i], i);
        }
        return dfs(inorder, postorder, 0, 0, inorder.length);
    }

    private TreeNode dfs(int[] inorder, int[] postorder, int i, int j, int n) {
        if (n <= 0) {
            return null;
        }
        int v = postorder[j + n - 1];
        int k = indexes.get(v);
        TreeNode root = new TreeNode(v);
        root.left = dfs(inorder, postorder, i, j, k - i);
        root.right = dfs(inorder, postorder, k + 1, j + k - i, n - k + i - 1);
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

    TreeNode* buildTree(vector<int>& inorder, vector<int>& postorder) {
        for (int i = 0; i < inorder.size(); ++i) indexes[inorder[i]] = i;
        return dfs(inorder, postorder, 0, 0, inorder.size());
    }

    TreeNode* dfs(vector<int>& inorder, vector<int>& postorder, int i, int j, int n) {
        if (n <= 0) return nullptr;
        int v = postorder[j + n - 1];
        int k = indexes[v];
        TreeNode* root = new TreeNode(v);
        root->left = dfs(inorder, postorder, i, j, k - i);
        root->right = dfs(inorder, postorder, k + 1, j + k - i, n - k + i - 1);
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
func buildTree(inorder []int, postorder []int) *TreeNode {
	indexes := make(map[int]int)
	for i, v := range inorder {
		indexes[v] = i
	}
	var dfs func(i, j, n int) *TreeNode
	dfs = func(i, j, n int) *TreeNode {
		if n <= 0 {
			return nil
		}
		v := postorder[j+n-1]
		k := indexes[v]
		root := &TreeNode{Val: v}
		root.Left = dfs(i, j, k-i)
		root.Right = dfs(k+1, j+k-i, n-k+i-1)
		return root
	}
	return dfs(0, 0, len(inorder))
}
```

### **...**

```

```

<!-- tabs:end -->
