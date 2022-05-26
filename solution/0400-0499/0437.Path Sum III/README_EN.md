# [437. Path Sum III](https://leetcode.com/problems/path-sum-iii)

[中文文档](/solution/0400-0499/0437.Path%20Sum%20III/README.md)

## Description

<p>Given the <code>root</code> of a binary tree and an integer <code>targetSum</code>, return <em>the number of paths where the sum of the values&nbsp;along the path equals</em>&nbsp;<code>targetSum</code>.</p>

<p>The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0437.Path%20Sum%20III/images/pathsum3-1-tree.jpg" style="width: 450px; height: 386px;" />
<pre>
<strong>Input:</strong> root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
<strong>Output:</strong> 3
<strong>Explanation:</strong> The paths that sum to 8 are shown.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[0, 1000]</code>.</li>
	<li><code>-10<sup>9</sup> &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
	<li><code>-1000 &lt;= targetSum &lt;= 1000</code></li>
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
