# [270. 最接近的二叉搜索树值](https://leetcode.cn/problems/closest-binary-search-tree-value)

[English Version](/solution/0200-0299/0270.Closest%20Binary%20Search%20Tree%20Value/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

给你二叉搜索树的根节点 <code>root</code> 和一个目标值 <code>target</code> ，请在该二叉搜索树中找到最接近目标值 <code>target</code> 的数值。如果有多个答案，返回最小的那个。

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0270.Closest%20Binary%20Search%20Tree%20Value/images/closest1-1-tree.jpg" style="width: 292px; height: 302px;" />
<pre>
<strong>输入：</strong>root = [4,2,5,1,3], target = 3.714286
<strong>输出：</strong>4
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = [1], target = 4.428571
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数目在范围 <code>[1, 10<sup>4</sup>]</code> 内</li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= target &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：中序遍历**

我们用一个变量 $mi$ 维护最小的差值，用一个变量 $ans$ 维护答案。初始时 $mi=\infty$, $ans=root.val$。

接下来，进行中序遍历，每次计算当前节点与目标值 $target$ 的差的绝对值 $t$。如果 $t \lt mi$，或者 $t = mi$ 且当前节点的值小于 $ans$，则更新 $mi$ 和 $ans$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉搜索树的节点数。

**方法二：二分查找**

与方法一类似，我们用一个变量 $mi$ 维护最小的差值，用一个变量 $ans$ 维护答案。初始时 $mi=\infty$, $ans=root.val$。

接下来，进行二分查找，每次计算当前节点与目标值 $target$ 的差的绝对值 $t$。如果 $t \lt mi$，或者 $t = mi$ 且当前节点的值小于 $ans$，则更新 $mi$ 和 $ans$。如果当前节点的值大于 $target$，则查找左子树，否则查找右子树。当我们遍历到叶子节点时，就可以结束二分查找了。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 是二叉搜索树的节点数。

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
    def closestValue(self, root: Optional[TreeNode], target: float) -> int:
        def dfs(root):
            if root is None:
                return
            dfs(root.left)
            nonlocal ans, mi
            t = abs(root.val - target)
            if t < mi:
                mi = t
                ans = root.val
            dfs(root.right)

        ans, mi = root.val, inf
        dfs(root)
        return ans
```

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def closestValue(self, root: Optional[TreeNode], target: float) -> int:
        ans, mi = root.val, inf
        while root:
            t = abs(root.val - target)
            if t < mi or (t == mi and root.val < ans):
                mi = t
                ans = root.val
            if root.val > target:
                root = root.left
            else:
                root = root.right
        return ans
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
    private int ans;
    private double target;
    private double mi = Double.MAX_VALUE;

    public int closestValue(TreeNode root, double target) {
        this.target = target;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        double t = Math.abs(root.val - target);
        if (t < mi) {
            mi = t;
            ans = root.val;
        }
        dfs(root.right);
    }
}
```

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
        int ans = root.val;
        double mi = Double.MAX_VALUE;
        while (root != null) {
            double t = Math.abs(root.val - target);
            if (t < mi || (t == mi && root.val < ans)) {
                mi = t;
                ans = root.val;
            }
            if (root.val > target) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return ans;
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
    int closestValue(TreeNode* root, double target) {
        int ans = root->val;
        double mi = INT_MAX;
        function<void(TreeNode*)> dfs = [&](TreeNode* root) {
            if (!root) {
                return;
            }
            dfs(root->left);
            double t = abs(root->val - target);
            if (t < mi) {
                mi = t;
                ans = root->val;
            }
            dfs(root->right);
        };
        dfs(root);
        return ans;
    }
};
```

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
        int ans = root->val;
        double mi = INT_MAX;
        while (root) {
            double t = abs(root->val - target);
            if (t < mi || (t == mi && root->val < ans)) {
                mi = t;
                ans = root->val;
            }
            if (root->val > target) {
                root = root->left;
            } else {
                root = root->right;
            }
        }
        return ans;
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
func closestValue(root *TreeNode, target float64) int {
	ans := root.Val
	mi := math.MaxFloat64
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Left)
		t := math.Abs(float64(root.Val) - target)
		if t < mi {
			mi = t
			ans = root.Val
		}
		dfs(root.Right)
	}
	dfs(root)
	return ans
}
```

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func closestValue(root *TreeNode, target float64) int {
	ans := root.Val
	mi := math.MaxFloat64
	for root != nil {
		t := math.Abs(float64(root.Val) - target)
		if t < mi || (t == mi && root.Val < ans) {
			mi = t
			ans = root.Val
		}
		if float64(root.Val) > target {
			root = root.Left
		} else {
			root = root.Right
		}
	}
	return ans
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
var closestValue = function (root, target) {
    let mi = Infinity;
    let ans = root.val;
    const dfs = root => {
        if (!root) {
            return;
        }
        dfs(root.left);
        const t = Math.abs(root.val - target);
        if (t < mi) {
            mi = t;
            ans = root.val;
        }
        dfs(root.right);
    };
    dfs(root);
    return ans;
};
```

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
var closestValue = function (root, target) {
    let ans = root.val;
    let mi = Number.MAX_VALUE;
    while (root) {
        const t = Math.abs(root.val - target);
        if (t < mi || (t === mi && root.val < ans)) {
            mi = t;
            ans = root.val;
        }
        if (root.val > target) {
            root = root.left;
        } else {
            root = root.right;
        }
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
