# [1339. Maximum Product of Splitted Binary Tree](https://leetcode.com/problems/maximum-product-of-splitted-binary-tree)

[中文文档](/solution/1300-1399/1339.Maximum%20Product%20of%20Splitted%20Binary%20Tree/README.md)

## Description

<p>Given the <code>root</code> of a binary tree, split the binary tree into two subtrees by removing one edge such that the product of the sums of the subtrees is maximized.</p>

<p>Return <em>the maximum product of the sums of the two subtrees</em>. Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p><strong>Note</strong> that you need to maximize the answer before taking the mod and not after taking it.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1339.Maximum%20Product%20of%20Splitted%20Binary%20Tree/images/sample_1_1699.png" style="width: 500px; height: 167px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,4,5,6]
<strong>Output:</strong> 110
<strong>Explanation:</strong> Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1339.Maximum%20Product%20of%20Splitted%20Binary%20Tree/images/sample_2_1699.png" style="width: 500px; height: 211px;" />
<pre>
<strong>Input:</strong> root = [1,null,2,3,4,null,null,5,6]
<strong>Output:</strong> 90
<strong>Explanation:</strong> Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[2, 5 * 10<sup>4</sup>]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
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
    def maxProduct(self, root: Optional[TreeNode]) -> int:
        def sum(root):
            if root is None:
                return 0
            return root.val + sum(root.left) + sum(root.right)

        def dfs(root):
            nonlocal s, ans
            if root is None:
                return 0
            t = root.val + dfs(root.left) + dfs(root.right)
            if t < s:
                ans = max(ans, t * (s - t))
            return t

        s = sum(root)
        ans = 0
        dfs(root)
        ans %= (10**9 + 7)
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
    private long ans;
    private long s;
    private static final int MOD = (int) 1e9 + 7;

    public int maxProduct(TreeNode root) {
        s = sum(root);
        dfs(root);
        ans %= MOD;
        return (int) ans;
    }

    private long sum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return root.val + sum(root.left) + sum(root.right);
    }

    private long dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        long t = root.val + dfs(root.left) + dfs(root.right);
        if (t < s) {
            ans = Math.max(ans, t * (s - t));
        }
        return t;
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
using ll = long long;
const int MOD = 1e9 + 7;

class Solution {
public:
    ll ans;
    ll s;

    int maxProduct(TreeNode* root) {
        s = sum(root);
        dfs(root);
        ans %= MOD;
        return (int) ans;
    }

    ll sum(TreeNode* root) {
        if (!root) return 0;
        return root->val + sum(root->left) + sum(root->right);
    }

    ll dfs(TreeNode* root) {
        if (!root) return 0;
        ll t = root->val + dfs(root->left) + dfs(root->right);
        if (t < s) {
            ans = max(ans, t * (s - t));
        }
        return t;
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
func maxProduct(root *TreeNode) int {
	mod := int(1e9) + 7
	var sum func(*TreeNode) int
	sum = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		return root.Val + sum(root.Left) + sum(root.Right)
	}
	s := sum(root)
	ans := 0
	var dfs func(*TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		t := root.Val + dfs(root.Left) + dfs(root.Right)
		if t < s {
			ans = max(ans, t*(s-t))
		}
		return t
	}
	dfs(root)
	return ans % mod
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
