# [968. 监控二叉树](https://leetcode.cn/problems/binary-tree-cameras)

[English Version](/solution/0900-0999/0968.Binary%20Tree%20Cameras/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉树，我们在树的节点上安装摄像头。</p>

<p>节点上的每个摄影头都可以监视<strong>其父对象、自身及其直接子对象。</strong></p>

<p>计算监控树的所有节点所需的最小摄像头数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0968.Binary%20Tree%20Cameras/images/bst_cameras_01.png" style="height: 163px; width: 138px;"></p>

<pre><strong>输入：</strong>[0,0,null,0,0]
<strong>输出：</strong>1
<strong>解释：</strong>如图所示，一台摄像头足以监控所有节点。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0968.Binary%20Tree%20Cameras/images/bst_cameras_02.png" style="height: 312px; width: 139px;"></p>

<pre><strong>输入：</strong>[0,0,null,0,null,0,null,null,0]
<strong>输出：</strong>2
<strong>解释：</strong>需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
</pre>

<p><br>
<strong>提示：</strong></p>

<ol>
	<li>给定树的节点数的范围是&nbsp;<code>[1, 1000]</code>。</li>
	<li>每个节点的值都是 0。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

贪心 + DFS。

我们知道，

1. 如果在叶子节点放置摄像头，摄像头会覆盖当前叶子节点以及它的父节点；
1. 如果在非叶子节点放置摄像头，摄像头会覆盖当前节点、它的子节点以及它的父节点。

第二种方案始终优于第一种方案。

因此，一种贪心的解法是，将摄像头放置在叶子节点的父节点上，然后移除所有被覆盖的节点，重复这一步骤，直至所有节点被移除。

我们用数字 0, 1, 2 表示每个节点可能的三种状态，

-   0: 叶子节点
-   1: 叶子节点的父节点，并且放置了摄像头
-   2: 没放置摄像头，但是被摄像头覆盖

定义 dfs(node) 返回每个节点的状态，对于每个节点，

1. 如果存在子节点，并且是叶子节点(`left == 0 || right == 0`)，那么该节点需要放置摄像头，累加摄像头 ans，返回 1；
1. 如果存在子节点，并且子节点放置了摄像头(`left == 1 || right == 1`)，那么该节点可以直接被覆盖，返回 2；
1. 否则把当前节点视为叶子节点，继续向上递归。

判断 `dfs(root)` 结果，若等于 0，说明还存在当前这一个叶子节点未被覆盖，摄像头数量 ans + 1 并返回，否则返回 ans。

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
    def minCameraCover(self, root: TreeNode) -> int:
        def dfs(root):
            nonlocal ans
            if root is None:
                return 2
            left, right = dfs(root.left), dfs(root.right)
            if left == 0 or right == 0:
                ans += 1
                return 1
            return 2 if left == 1 or right == 1 else 0

        ans = 0
        return (dfs(root) == 0) + ans
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

    public int minCameraCover(TreeNode root) {
        ans = 0;
        return (dfs(root) == 0) ? ans + 1 : ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 2;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        if (left == 0 || right == 0) {
            ++ans;
            return 1;
        }
        if (left == 1 || right == 1) {
            return 2;
        }
        return 0;
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
    int ans;

    int minCameraCover(TreeNode* root) {
        ans = 0;
        if (dfs(root) == 0) return ans + 1;
        return ans;
    }

    int dfs(TreeNode* root) {
        if (!root) return 2;
        int left = dfs(root->left), right = dfs(root->right);
        if (left == 0 || right == 0) {
            ++ans;
            return 1;
        }
        if (left == 1 || right == 1) return 2;
        return 0;
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
func minCameraCover(root *TreeNode) int {
	ans := 0
	var dfs func(root *TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 2
		}
		left, right := dfs(root.Left), dfs(root.Right)
		if left == 0 || right == 0 {
			ans++
			return 1
		}
		if left == 1 || right == 1 {
			return 2
		}
		return 0
	}
	if dfs(root) == 0 {
		return ans + 1
	}
	return ans
}
```

<!-- tabs:end -->
