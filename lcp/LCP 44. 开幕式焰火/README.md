---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2044.%20%E5%BC%80%E5%B9%95%E5%BC%8F%E7%84%B0%E7%81%AB/README.md
---

<!-- problem:start -->

# [LCP 44. 开幕式焰火](https://leetcode.cn/problems/sZ59z6)

## 题目描述

<!-- description:start -->

「力扣挑战赛」开幕式开始了，空中绽放了一颗二叉树形的巨型焰火。
给定一棵二叉树 `root` 代表焰火，节点值表示巨型焰火这一位置的颜色种类。请帮小扣计算巨型焰火有多少种不同的颜色。

**示例 1：**

> 输入：`root = [1,3,2,1,null,2]`
>
> 输出：`3`
>
> 解释：焰火中有 3 个不同的颜色，值分别为 1、2、3

**示例 2：**

> 输入：`root = [3,3,3]`
>
> 输出：`1`
>
> 解释：焰火中仅出现 1 个颜色，值为 3

**提示：**

-   `1 <= 节点个数 <= 1000`
-   `1 <= Node.val <= 1000`

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
class Solution:
    def numColor(self, root: TreeNode) -> int:
        s = set()

        def dfs(root):
            if root:
                s.add(root.val)
                dfs(root.left)
                dfs(root.right)

        dfs(root)
        return len(s)
```

#### Java

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private Set<Integer> s;

    public int numColor(TreeNode root) {
        s = new HashSet<>();
        dfs(root);
        return s.size();
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        s.add(root.val);
        dfs(root.left);
        dfs(root.right);
    }
}
```

#### C++

```cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    unordered_set<int> s;

    int numColor(TreeNode* root) {
        dfs(root);
        return s.size();
    }

    void dfs(TreeNode* root) {
        if (!root) return;
        s.insert(root->val);
        dfs(root->left);
        dfs(root->right);
    }
};
```

#### Go

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
var s map[int]bool

func numColor(root *TreeNode) int {
	s = make(map[int]bool)
	dfs(root)
	return len(s)
}

func dfs(root *TreeNode) {
	if root != nil {
		s[root.Val] = true
		dfs(root.Left)
		dfs(root.Right)
	}
}
```

#### Swift

```swift
/* class TreeNode {
*     var val: Int
*     var left: TreeNode?
*     var right: TreeNode?
*     init(_ val: Int) {
*         self.val = val
*         self.left = nil
*         self.right = nil
*     }
* }
*/

class Solution {
    private var uniqueColors: Set<Int> = []

    func numColor(_ root: TreeNode?) -> Int {
        dfs(root)
        return uniqueColors.count
    }

    private func dfs(_ node: TreeNode?) {
        guard let node = node else { return }
        uniqueColors.insert(node.val)
        dfs(node.left)
        dfs(node.right)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
