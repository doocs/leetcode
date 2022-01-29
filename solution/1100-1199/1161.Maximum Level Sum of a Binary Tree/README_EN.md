# [1161. Maximum Level Sum of a Binary Tree](https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree)

[中文文档](/solution/1100-1199/1161.Maximum%20Level%20Sum%20of%20a%20Binary%20Tree/README.md)

## Description

<p>Given the <code>root</code> of a binary tree, the level of its root is <code>1</code>, the level of its children is <code>2</code>, and so on.</p>

<p>Return the <strong>smallest</strong> level <code>x</code> such that the sum of all the values of nodes at level <code>x</code> is <strong>maximal</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1161.Maximum%20Level%20Sum%20of%20a%20Binary%20Tree/images/capture.jpeg" style="width: 200px; height: 175px;" />
<pre>
<strong>Input:</strong> root = [1,7,0,7,-8,null,null]
<strong>Output:</strong> 2
<strong>Explanation: </strong>
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + -8 = -1.
So we return the level with the maximum sum which is level 2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [989,null,10250,98693,-89388,null,null,null,-32127]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
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
    def maxLevelSum(self, root: TreeNode) -> int:
        ans = (float('-inf'), 0)
        q = deque([root])
        l = 0
        while q:
            l += 1
            n = len(q)
            s = 0
            for _ in range(n):
                node = q.popleft()
                s += node.val
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
            if s > ans[0]:
                ans = (s, l)
        return ans[1]
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

    public int maxLevelSum(TreeNode root) {
        int[] ans = new int[] { Integer.MIN_VALUE, 0 };
        int l = 0;
        Deque<TreeNode> q = new LinkedList<>();
        q.offerLast(root);
        while (!q.isEmpty()) {
            ++l;
            int s = 0;
            for (int i = q.size(); i > 0; --i) {
                TreeNode node = q.pollFirst();
                s += node.val;
                if (node.left != null) {
                    q.offerLast(node.left);
                }
                if (node.right != null) {
                    q.offerLast(node.right);
                }
            }
            if (s > ans[0]) {
                ans[0] = s;
                ans[1] = l;
            }
        }
        return ans[1];
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
    int maxLevelSum(TreeNode* root) {
        vector<int> ans(2);
        ans[0] = INT_MIN;
        queue<TreeNode*> q{{root}};
        int l = 0;
        while (!q.empty())
        {
            ++l;
            int s = 0;
            for (int i = q.size(); i > 0; --i)
            {
                TreeNode* node = q.front();
                q.pop();
                s += node->val;
                if (node->left) q.push(node->left);
                if (node->right) q.push(node->right);
            }
            if (s > ans[0])
            {
                ans[0] = s;
                ans[1] = l;
            }
        }
        return ans[1];
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
func maxLevelSum(root *TreeNode) int {
	ans := [2]int{math.MinInt32, 0}
	q := []*TreeNode{root}
	l := 0
	for len(q) > 0 {
		l++
		s := 0
		for i := len(q); i > 0; i-- {
			node := q[0]
			q = q[1:]
			s += node.Val
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
		if s > ans[0] {
			ans = [2]int{s, l}
		}
	}
	return ans[1]
}
```

### **...**

```

```

<!-- tabs:end -->
