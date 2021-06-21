# [270. 最接近的二叉搜索树值](https://leetcode-cn.com/problems/closest-binary-search-tree-value)

[English Version](/solution/0200-0299/0270.Closest%20Binary%20Search%20Tree%20Value/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个不为空的二叉搜索树和一个目标值 target，请在该二叉搜索树中找到最接近目标值 target 的数值。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>给定的目标值 target 是一个浮点数</li>
	<li>题目保证在该二叉搜索树中只会存在一个最接近目标值的数</li>
</ul>

<p><strong>示例：</strong></p>

<pre><strong>输入:</strong> root = [4,2,5,1,3]，目标值 target = 3.714286

    4
   / \
  2   5
 / \
1   3

<strong>输出:</strong> 4
</pre>


## 解法

<!-- 这里可写通用的实现逻辑 -->

二分查找。

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
    def closestValue(self, root: TreeNode, target: float) -> int:
        res, min_diff = root.val, float('inf')
        while root:
            val = abs(root.val - target)
            if min_diff > val:
                min_diff = val
                res = root.val
            if root.val > target:
                root = root.left
            else:
                root = root.right
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
    public int closestValue(TreeNode root, double target) {
        int res = root.val;
        double minDiff = Double.MAX_VALUE;
        while (root != null) {
            double val = Math.abs(root.val - target);
            if (minDiff > val) {
                minDiff = val;
                res = root.val;
            }
            if (root.val > target) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return res;
    }
}
```

### **JavaScript**

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @param {number} target
 * @return {number}
 */
var closestValue = function(root, target) {
    let res = root.val;
    let minDiff = Math.abs(root.val - target);
    while (root) {
        const val = Math.abs(root.val - target);
        if (minDiff > val) {
            minDiff = val;
            res = root.val;
        }
        if (root.val > target) {
            root = root.left;
        } else {
            root = root.right;
        }
    }
    return res;
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
    int closestValue(TreeNode* root, double target) {
        int res = root->val;
        double minDiff = abs(root->val - target);
        while (root != nullptr) {
            double val = abs(root->val - target);
            if (minDiff > val) {
                minDiff = val;
                res = root->val;
            }
            if (root->val > target) {
                root = root->left;
            } else {
                root = root->right;
            }
        }
        return res;
    }
};
```

### **Go**

```go
import "math"

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func closestValue(root *TreeNode, target float64) int {
	res := root.Val
	minDiff := math.Abs(float64(root.Val) - float64(target))
	for root != nil {
		val := math.Abs(float64(root.Val) - float64(target))
		if minDiff > val {
			minDiff = val
			res = root.Val
		}
		if float64(root.Val) > target {
			root = root.Left
		} else {
			root = root.Right
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
