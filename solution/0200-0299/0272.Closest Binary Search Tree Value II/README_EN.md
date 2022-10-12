# [272. Closest Binary Search Tree Value II](https://leetcode.com/problems/closest-binary-search-tree-value-ii)

[中文文档](/solution/0200-0299/0272.Closest%20Binary%20Search%20Tree%20Value%20II/README.md)

## Description

<p>Given the <code>root</code> of a binary search tree, a <code>target</code> value, and an integer <code>k</code>, return <em>the </em><code>k</code><em> values in the BST that are closest to the</em> <code>target</code>. You may return the answer in <strong>any order</strong>.</p>

<p>You are <strong>guaranteed</strong> to have only one unique set of <code>k</code> values in the BST that are closest to the <code>target</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0272.Closest%20Binary%20Search%20Tree%20Value%20II/images/closest1-1-tree.jpg" style="width: 292px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [4,2,5,1,3], target = 3.714286, k = 2
<strong>Output:</strong> [4,3]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [1], target = 0.000000, k = 1
<strong>Output:</strong> [1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is <code>n</code>.</li>
	<li><code>1 &lt;= k &lt;= n &lt;= 10<sup>4</sup></code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= target &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Assume that the BST is balanced. Could you solve it in less than <code>O(n)</code> runtime (where <code>n = total nodes</code>)?</p>

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
    def closestKValues(self, root: TreeNode, target: float, k: int) -> List[int]:
        def dfs(root):
            if root is None:
                return
            dfs(root.left)
            if len(q) < k:
                q.append(root.val)
            else:
                if abs(root.val - target) >= abs(q[0] - target):
                    return
                q.popleft()
                q.append(root.val)
            dfs(root.right)

        q = deque()
        dfs(root)
        return list(q)
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

    private List<Integer> ans;
    private double target;
    private int k;

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        ans = new LinkedList<>();
        this.target = target;
        this.k = k;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (ans.size() < k) {
            ans.add(root.val);
        } else {
            if (Math.abs(root.val - target) >= Math.abs(ans.get(0) - target)) {
                return;
            }
            ans.remove(0);
            ans.add(root.val);
        }
        dfs(root.right);
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
    queue<int> q;
    double target;
    int k;

    vector<int> closestKValues(TreeNode* root, double target, int k) {
        this->target = target;
        this->k = k;
        dfs(root);
        vector<int> ans;
        while (!q.empty()) {
            ans.push_back(q.front());
            q.pop();
        }
        return ans;
    }

    void dfs(TreeNode* root) {
        if (!root) return;
        dfs(root->left);
        if (q.size() < k)
            q.push(root->val);
        else {
            if (abs(root->val - target) >= abs(q.front() - target)) return;
            q.pop();
            q.push(root->val);
        }
        dfs(root->right);
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
func closestKValues(root *TreeNode, target float64, k int) []int {
	var ans []int
	var dfs func(root *TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Left)
		if len(ans) < k {
			ans = append(ans, root.Val)
		} else {
			if math.Abs(float64(root.Val)-target) >= math.Abs(float64(ans[0])-target) {
				return
			}
			ans = ans[1:]
			ans = append(ans, root.Val)
		}
		dfs(root.Right)
	}
	dfs(root)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
