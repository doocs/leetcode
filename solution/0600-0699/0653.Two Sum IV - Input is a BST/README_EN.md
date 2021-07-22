# [653. Two Sum IV - Input is a BST](https://leetcode.com/problems/two-sum-iv-input-is-a-bst)

[中文文档](/solution/0600-0699/0653.Two%20Sum%20IV%20-%20Input%20is%20a%20BST/README.md)

## Description

<p>Given the <code>root</code> of a Binary Search Tree and a target number <code>k</code>, return <em><code>true</code> if there exist two elements in the BST such that their sum is equal to the given target</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0653.Two%20Sum%20IV%20-%20Input%20is%20a%20BST/images/sum_tree_1.jpg" style="width: 562px; height: 322px;" />
<pre>
<strong>Input:</strong> root = [5,3,6,2,4,null,7], k = 9
<strong>Output:</strong> true
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0653.Two%20Sum%20IV%20-%20Input%20is%20a%20BST/images/sum_tree_2.jpg" style="width: 562px; height: 322px;" />
<pre>
<strong>Input:</strong> root = [5,3,6,2,4,null,7], k = 28
<strong>Output:</strong> false
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> root = [2,1,3], k = 4
<strong>Output:</strong> true
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> root = [2,1,3], k = 1
<strong>Output:</strong> false
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> root = [2,1,3], k = 3
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>4</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>4</sup></code></li>
	<li><code>root</code> is guaranteed to be a <strong>valid</strong> binary search tree.</li>
	<li><code>-10<sup>5</sup>&nbsp;&lt;= k &lt;= 10<sup>5</sup></code></li>
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
    def findTarget(self, root: TreeNode, k: int) -> bool:
        def find(node):
            if not node:
                return False
            if k - node.val in nodes:
                return True
            nodes.add(node.val)
            return find(node.left) or find(node.right)

        nodes = set()
        return find(root)
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
    private Set<Integer> nodes;

    public boolean findTarget(TreeNode root, int k) {
        nodes = new HashSet<>();
        return find(root, k);
    }

    private boolean find(TreeNode node, int k) {
        if (node == null) {
            return false;
        }
        if (nodes.contains(k - node.val)) {
            return true;
        }
        nodes.add(node.val);
        return find(node.left, k) || find(node.right, k);
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
    unordered_set<int> nodes;

    bool findTarget(TreeNode* root, int k) {
        return find(root, k);    
    }

    bool find(TreeNode* node, int k) {
        if (node == nullptr) {
            return false;
        }
        if (nodes.count(k - node->val)) {
            return true;
        }
        nodes.insert(node->val);
        return find(node->left, k) || find(node->right, k);
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
func findTarget(root *TreeNode, k int) bool {
	nodes := make(map[int]bool)
	var find func(node *TreeNode, k int) bool
	find = func(node *TreeNode, k int) bool {
		if node == nil {
			return false
		}
		if nodes[k-node.Val] {
			return true
		}
		nodes[node.Val] = true
		return find(node.Left, k) || find(node.Right, k)
	}
	return find(root, k)

}
```

### **...**

```

```

<!-- tabs:end -->
