# [1214. Two Sum BSTs](https://leetcode.com/problems/two-sum-bsts)

[中文文档](/solution/1200-1299/1214.Two%20Sum%20BSTs/README.md)

## Description

<p>Given the roots of two binary search trees, <code>root1</code> and <code>root2</code>, return <code>true</code> if and only if there is a node in the first tree and a node in the second tree whose values sum up to a given integer <code>target</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1214.Two%20Sum%20BSTs/images/ex1.png" style="width: 369px; height: 169px;" />
<pre>
<strong>Input:</strong> root1 = [2,1,4], root2 = [1,0,3], target = 5
<strong>Output:</strong> true
<strong>Explanation: </strong>2 and 3 sum up to 5.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1214.Two%20Sum%20BSTs/images/ex2.png" style="width: 453px; height: 290px;" />
<pre>
<strong>Input:</strong> root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in each tree is in the range <code>[1, 5000]</code>.</li>
	<li><code>-10<sup>9</sup> &lt;= Node.val, target &lt;= 10<sup>9</sup></code></li>
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
    def twoSumBSTs(self, root1: TreeNode, root2: TreeNode, target: int) -> bool:
        vals1, vals2 = [], []

        def inorder(root, vals):
            if root:
                inorder(root.left, vals)
                vals.append(root.val)
                inorder(root.right, vals)

        inorder(root1, vals1)
        inorder(root2, vals2)

        i, j = 0, len(vals2) - 1
        while i < len(vals1) and j >= 0:
            if vals1[i] + vals2[j] == target:
                return True
            if vals1[i] + vals2[j] < target:
                i += 1
            else:
                j -= 1
        return False
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
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        List<Integer> vals1 = new ArrayList<>();
        List<Integer> vals2 = new ArrayList<>();
        inorder(root1, vals1);
        inorder(root2, vals2);
        int i = 0, j = vals2.size() - 1;
        while (i < vals1.size() && j >= 0) {
            int s = vals1.get(i) + vals2.get(j);
            if (s == target) {
                return true;
            }
            if (s < target) {
                ++i;
            } else {
                --j;
            }
        }
        return false;
    }

    private void inorder(TreeNode root, List<Integer> vals) {
        if (root != null) {
            inorder(root.left, vals);
            vals.add(root.val);
            inorder(root.right, vals);
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
    bool twoSumBSTs(TreeNode* root1, TreeNode* root2, int target) {
        vector<int> vals1, vals2;
        inorder(root1, vals1);
        inorder(root2, vals2);
        int i = 0, j = vals2.size() - 1;
        while (i < vals1.size() && j >= 0) {
            int s = vals1[i] + vals2[j];
            if (s == target)
                return true;
            if (s < target)
                ++i;
            else
                --j;
        }
        return false;
    }

    void inorder(TreeNode* root, vector<int>& vals) {
        if (root) {
            inorder(root->left, vals);
            vals.push_back(root->val);
            inorder(root->right, vals);
        }
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
func twoSumBSTs(root1 *TreeNode, root2 *TreeNode, target int) bool {
	vals1 := inorder(root1)
	vals2 := inorder(root2)
	i, j := 0, len(vals2)-1
	for i < len(vals1) && j >= 0 {
		s := vals1[i] + vals2[j]
		if s == target {
			return true
		}
		if s < target {
			i++
		} else {
			j--
		}
	}
	return false
}

func inorder(root *TreeNode) []int {
	if root == nil {
		return nil
	}
	left := inorder(root.Left)
	right := inorder(root.Right)
	return append(append(left, root.Val), right...)
}
```

### **...**

```

```

<!-- tabs:end -->
