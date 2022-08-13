# [1609. Even Odd Tree](https://leetcode.com/problems/even-odd-tree)

[中文文档](/solution/1600-1699/1609.Even%20Odd%20Tree/README.md)

## Description

<p>A binary tree is named <strong>Even-Odd</strong> if it meets the following conditions:</p>

<ul>
	<li>The root of the binary tree is at level index <code>0</code>, its children are at level index <code>1</code>, their children are at level index <code>2</code>, etc.</li>
	<li>For every <strong>even-indexed</strong> level, all nodes at the level have <strong>odd</strong> integer values in <strong>strictly increasing</strong> order (from left to right).</li>
	<li>For every <b>odd-indexed</b> level, all nodes at the level have <b>even</b> integer values in <strong>strictly decreasing</strong> order (from left to right).</li>
</ul>

<p>Given the <code>root</code> of a binary tree, <em>return </em><code>true</code><em> if the binary tree is <strong>Even-Odd</strong>, otherwise return </em><code>false</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1609.Even%20Odd%20Tree/images/sample_1_1966.png" style="width: 362px; height: 229px;" />
<pre>
<strong>Input:</strong> root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
<strong>Output:</strong> true
<strong>Explanation:</strong> The node values on each level are:
Level 0: [1]
Level 1: [10,4]
Level 2: [3,7,9]
Level 3: [12,8,6,2]
Since levels 0 and 2 are all odd and increasing and levels 1 and 3 are all even and decreasing, the tree is Even-Odd.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1609.Even%20Odd%20Tree/images/sample_2_1966.png" style="width: 363px; height: 167px;" />
<pre>
<strong>Input:</strong> root = [5,4,2,3,3,7]
<strong>Output:</strong> false
<strong>Explanation:</strong> The node values on each level are:
Level 0: [5]
Level 1: [4,2]
Level 2: [3,3,7]
Node values in level 2 must be in strictly increasing order, so the tree is not Even-Odd.
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1609.Even%20Odd%20Tree/images/sample_1_333_1966.png" style="width: 363px; height: 167px;" />
<pre>
<strong>Input:</strong> root = [5,9,1,3,5,7]
<strong>Output:</strong> false
<strong>Explanation:</strong> Node values in the level 1 should be even integers.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>5</sup>]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

BFS.

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
    def isEvenOddTree(self, root: TreeNode) -> bool:
        even = True
        q = deque([root])
        while q:
            n = len(q)
            prev = 0 if even else 10**6
            for _ in range(n):
                node = q.popleft()
                if even and (prev >= node.val or node.val % 2 == 0):
                    return False
                if not even and (prev <= node.val or node.val % 2 == 1):
                    return False
                prev = node.val
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
            even = not even
        return True
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
    public boolean isEvenOddTree(TreeNode root) {
        boolean even = true;
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offerLast(root);
        while (!q.isEmpty()) {
            int prev = even ? 0 : 1000000;
            for (int i = 0, n = q.size(); i < n; ++i) {
                TreeNode node = q.pollFirst();
                if (even && (prev >= node.val || node.val % 2 == 0)) {
                    return false;
                }
                if (!even && (prev <= node.val || node.val % 2 == 1)) {
                    return false;
                }
                prev = node.val;
                if (node.left != null) {
                    q.offerLast(node.left);
                }
                if (node.right != null) {
                    q.offerLast(node.right);
                }
            }
            even = !even;
        }
        return true;
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
    bool isEvenOddTree(TreeNode* root) {
        bool even = true;
        queue<TreeNode*> q;
        q.push(root);
        while (!q.empty()) {
            int prev = even ? 0 : 1000000;
            for (int i = 0, n = q.size(); i < n; ++i) {
                auto node = q.front();
                q.pop();
                if (even && (prev >= node->val || node->val % 2 == 0)) return false;
                if (!even && (prev <= node->val || node->val % 2 == 1)) return false;
                prev = node->val;
                if (node->left) q.push(node->left);
                if (node->right) q.push(node->right);
            }
            even = !even;
        }
        return true;
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
func isEvenOddTree(root *TreeNode) bool {
	even := true
	var q []*TreeNode
	q = append(q, root)
	for len(q) > 0 {
		prev := 0
		if !even {
			prev = 1000000
		}
		n := len(q)
		for i := 0; i < n; i++ {
			node := q[0]
			q = q[1:]
			if even && (prev >= node.Val || node.Val%2 == 0) {
				return false
			}
			if !even && (prev <= node.Val || node.Val%2 == 1) {
				return false
			}
			prev = node.Val
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
		even = !even
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
