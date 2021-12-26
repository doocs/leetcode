# [663. Equal Tree Partition](https://leetcode.com/problems/equal-tree-partition)

[中文文档](/solution/0600-0699/0663.Equal%20Tree%20Partition/README.md)

## Description

<p>

Given a binary tree with <code>n</code> nodes, your task is to check if it's possible to partition the tree to two trees which have the equal sum of values after removing <b>exactly</b> one edge on the original tree.

</p>

<p><b>Example 1:</b><br />

<pre>

<b>Input:</b>     

    5

   / \

  10 10

    /  \

   2   3



<b>Output:</b> True

<b>Explanation:</b> 

    5

   / 

  10

      

Sum: 15



   10

  /  \

 2    3



Sum: 15

</pre>

</p>

<p><b>Example 2:</b><br />

<pre>

<b>Input:</b>     

    1

   / \

  2  10

    /  \

   2   20



<b>Output:</b> False

<b>Explanation:</b> You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.

</pre>

</p>

<p><b>Note:</b><br>

<ol>

<li>The range of tree node value is in the range of [-100000, 100000].</li>

<li>1 <= n <= 10000</li>

</ol>

</p>

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
