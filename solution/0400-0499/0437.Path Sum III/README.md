# [437. 路径总和 III](https://leetcode-cn.com/problems/path-sum-iii)

[English Version](/solution/0400-0499/0437.Path%20Sum%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉树，它的每个结点都存放着一个整数值。</p>

<p>找出路径和等于给定数值的路径总数。</p>

<p>路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。</p>

<p>二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。</p>

<p><strong>示例：</strong></p>

<pre>root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    <strong>5</strong>   <strong>-3</strong>
   <strong>/</strong> <strong>\</strong>    <strong>\</strong>
  <strong>3</strong>   <strong>2</strong>   <strong>11</strong>
 / \   <strong>\</strong>
3  -2   <strong>1</strong>

返回 3。和等于 8 的路径有:

1.  5 -&gt; 3
2.  5 -&gt; 2 -&gt; 1
3.  -3 -&gt; 11
</pre>


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
