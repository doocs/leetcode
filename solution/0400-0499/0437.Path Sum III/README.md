# [437. 路径总和 III](https://leetcode.cn/problems/path-sum-iii)

[English Version](/solution/0400-0499/0437.Path%20Sum%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉树的根节点 <code>root</code> ，和一个整数 <code>targetSum</code> ，求该二叉树里节点值之和等于 <code>targetSum</code> 的 <strong>路径</strong> 的数目。</p>

<p><strong>路径</strong> 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0437.Path%20Sum%20III/images/pathsum3-1-tree.jpg" style="width: 452px; " /></p>

<pre>
<strong>输入：</strong>root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
<strong>输出：</strong>3
<strong>解释：</strong>和等于 8 的路径有 3 条，如图所示。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
<strong>输出：</strong>3
</pre>

<p> </p>

<p><strong>提示:</strong></p>

<ul>
	<li>二叉树的节点个数的范围是 <code>[0,1000]</code></li>
	<li><meta charset="UTF-8" /><code>-10<sup>9</sup> <= Node.val <= 10<sup>9</sup></code> </li>
	<li><code>-1000 <= targetSum <= 1000</code> </li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

在遍历的过程中，记录当前路径上的前缀和

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
    def pathSum(self, root: TreeNode, targetSum: int) -> int:
        preSum = defaultdict(int)
        preSum[0] = 1

        def dfs(node: TreeNode, cur: int) -> int:
            if not node:
                return 0

            cur += node.val
            ret = preSum[cur - targetSum]

            preSum[cur] += 1
            ret += dfs(node.left, cur)
            ret += dfs(node.right, cur)
            preSum[cur] -= 1

            return ret

        return dfs(root, 0)
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

    private final Map<Integer, Integer> preSum = new HashMap<>();

    public int pathSum(TreeNode root, int targetSum) {
        preSum.put(0, 1);
        return dfs(root, 0, targetSum);
    }

    private int dfs(TreeNode node, int cur, int targetSum) {
        if (node == null) {
            return 0;
        }

        cur += node.val;
        int ret = preSum.getOrDefault(cur - targetSum, 0);

        preSum.merge(cur, 1, Integer::sum);
        ret += dfs(node.left, cur, targetSum);
        ret += dfs(node.right, cur, targetSum);
        preSum.merge(cur, -1, Integer::sum);

        return ret;
    }
}
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
func pathSum(root *TreeNode, targetSum int) int {
	preSum := make(map[int]int)
	preSum[0] = 1

	var dfs func(*TreeNode, int) int
	dfs = func(node *TreeNode, cur int) int {
		if node == nil {
			return 0
		}

		cur += node.Val
		ret := preSum[cur-targetSum]

		preSum[cur]++
		ret += dfs(node.Left, cur)
		ret += dfs(node.Right, cur)
		preSum[cur]--

		return ret
	}

	return dfs(root, 0)
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
    int pathSum(TreeNode* root, int targetSum) {
        unordered_map<int, int> preSum;
        preSum[0] = 1;

        function<int(TreeNode*, int)> dfs = [&](TreeNode* node, int cur) {
            if (node == nullptr) {
                return 0;
            }

            cur += node->val;
            int ret = preSum[cur - targetSum];

            ++preSum[cur];
            ret += dfs(node->left, cur);
            ret += dfs(node->right, cur);
            --preSum[cur];

            return ret;
        };

        return dfs(root, 0);
    }
};
```

### **...**

```

```

<!-- tabs:end -->
