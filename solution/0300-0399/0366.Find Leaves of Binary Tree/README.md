# [366. 寻找二叉树的叶子节点](https://leetcode.cn/problems/find-leaves-of-binary-tree)

[English Version](/solution/0300-0399/0366.Find%20Leaves%20of%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵二叉树，请按以下要求的顺序收集它的全部节点：</p>

<ol>
	<li>依次从左到右，每次收集并删除所有的叶子节点</li>
	<li>重复如上过程直到整棵树为空</li>
</ol>

<p>&nbsp;</p>

<p><strong>示例:</strong></p>

<pre><strong>输入: </strong>[1,2,3,4,5]
&nbsp; 
&nbsp;         1
         / \
        2   3
       / \     
      4   5    

<strong>输出: </strong>[[4,5,3],[2],[1]]
</pre>

<p>&nbsp;</p>

<p><strong>解释:</strong></p>

<p>1. 删除叶子节点&nbsp;<code>[4,5,3]</code> ，得到如下树结构：</p>

<pre>          1
         / 
        2          
</pre>

<p>&nbsp;</p>

<p>2. 现在删去叶子节点&nbsp;<code>[2]</code>&nbsp;，得到如下树结构：</p>

<pre>          1          
</pre>

<p>&nbsp;</p>

<p>3. 现在删去叶子节点&nbsp;<code>[1]</code>&nbsp;，得到空树：</p>

<pre>          []         
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

添加前置节点 prev，初始时 `prev.left = root`。

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
    def findLeaves(self, root: TreeNode) -> List[List[int]]:
        def dfs(root, prev, t):
            if root is None:
                return
            if root.left is None and root.right is None:
                t.append(root.val)
                if prev.left == root:
                    prev.left = None
                else:
                    prev.right = None
            dfs(root.left, root, t)
            dfs(root.right, root, t)

        res = []
        prev = TreeNode(left=root)
        while prev.left:
            t = []
            dfs(prev.left, prev, t)
            res.append(t)
        return res
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
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        TreeNode prev = new TreeNode(0, root, null);
        while (prev.left != null) {
            List<Integer> t = new ArrayList<>();
            dfs(prev.left, prev, t);
            res.add(t);
        }
        return res;
    }

    private void dfs(TreeNode root, TreeNode prev, List<Integer> t) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            t.add(root.val);
            if (prev.left == root) {
                prev.left = null;
            } else {
                prev.right = null;
            }
        }
        dfs(root.left, root, t);
        dfs(root.right, root, t);
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
    vector<vector<int>> findLeaves(TreeNode* root) {
        vector<vector<int>> res;
        TreeNode* prev = new TreeNode(0, root, nullptr);
        while (prev->left) {
            vector<int> t;
            dfs(prev->left, prev, t);
            res.push_back(t);
        }
        return res;
    }

    void dfs(TreeNode* root, TreeNode* prev, vector<int>& t) {
        if (!root) return;
        if (!root->left && !root->right) {
            t.push_back(root->val);
            if (prev->left == root)
                prev->left = nullptr;
            else
                prev->right = nullptr;
        }
        dfs(root->left, root, t);
        dfs(root->right, root, t);
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
func findLeaves(root *TreeNode) [][]int {
	prev := &TreeNode{
		Val:   0,
		Left:  root,
		Right: nil,
	}
	var res [][]int
	for prev.Left != nil {
		var t []int
		dfs(prev.Left, prev, &t)
		res = append(res, t)
	}
	return res
}

func dfs(root, prev *TreeNode, t *[]int) {
	if root == nil {
		return
	}
	if root.Left == nil && root.Right == nil {
		*t = append(*t, root.Val)
		if prev.Left == root {
			prev.Left = nil
		} else {
			prev.Right = nil
		}
	}
	dfs(root.Left, root, t)
	dfs(root.Right, root, t)
}
```

### **...**

```

```

<!-- tabs:end -->
