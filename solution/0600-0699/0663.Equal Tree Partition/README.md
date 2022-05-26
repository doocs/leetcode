# [663. 均匀树划分](https://leetcode.cn/problems/equal-tree-partition)

[English Version](/solution/0600-0699/0663.Equal%20Tree%20Partition/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一棵有 <code>n</code> 个结点的二叉树，你的任务是检查是否可以通过去掉树上的一条边将树分成两棵，且这两棵树结点之和相等。</p>

<p><strong>样例 1:</strong></p>

<pre><strong>输入:</strong>     
    5
   / \
  10 10
    /  \
   2   3

<strong>输出:</strong> True
<strong>解释:</strong> 
    5
   / 
  10
      
和: 15

   10
  /  \
 2    3

和: 15
</pre>

<p>&nbsp;</p>

<p><strong>样例 2:</strong></p>

<pre><strong>输入:</strong>     
    1
   / \
  2  10
    /  \
   2   20

<strong>输出:</strong> False
<strong>解释:</strong> 无法通过移除一条树边将这棵树划分成结点之和相等的两棵子树。
</pre>

<p>&nbsp;</p>

<p><strong>注释 :</strong></p>

<ol>
	<li>树上结点的权值范围 [-100000, 100000]。</li>
	<li>1 &lt;= n &lt;= 10000</li>
</ol>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

后序遍历，记录每个子树的和。

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
    def checkEqualTree(self, root: TreeNode) -> bool:
        def sum(root):
            if root is None:
                return 0
            l, r = sum(root.left), sum(root.right)
            seen.append(l + r + root.val)
            return seen[-1]

        seen = []
        s = sum(root)
        if s % 2 == 1:
            return False
        seen.pop()
        return s // 2 in seen
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
    private List<Integer> seen;

    public boolean checkEqualTree(TreeNode root) {
        seen = new ArrayList<>();
        int s = sum(root);
        if (s % 2 != 0) {
            return false;
        }
        seen.remove(seen.size() - 1);
        return seen.contains(s / 2);
    }

    private int sum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = sum(root.left);
        int r = sum(root.right);
        int s = l + r + root.val;
        seen.add(s);
        return s;
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
    vector<int> seen;

    bool checkEqualTree(TreeNode* root) {
        int s = sum(root);
        if (s % 2 != 0) return false;
        seen.pop_back();
        return count(seen.begin(), seen.end(), s / 2);
    }

    int sum(TreeNode* root) {
        if (!root) return 0;
        int l = sum(root->left), r = sum(root->right);
        int s = l + r + root->val;
        seen.push_back(s);
        return s;
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
func checkEqualTree(root *TreeNode) bool {
	var seen []int
	var sum func(root *TreeNode) int
	sum = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		l, r := sum(root.Left), sum(root.Right)
		s := l + r + root.Val
		seen = append(seen, s)
		return s
	}

	s := sum(root)
	if s%2 != 0 {
		return false
	}
	seen = seen[:len(seen)-1]
	for _, v := range seen {
		if v == s/2 {
			return true
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
